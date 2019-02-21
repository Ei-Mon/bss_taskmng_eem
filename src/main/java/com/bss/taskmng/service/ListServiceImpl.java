package com.bss.taskmng.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.bss.taskmng.model.ListModelBean;

@Service
public class ListServiceImpl implements ListService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//Create New List
	@Override
	public boolean createList(ListModelBean list) {
		boolean returnStatus = false;	
		try{
			String sql = "INSERT INTO TP_LIST (list_title, board_id, status_cd, reg_id, reg_dt) VALUES(?, ?, ?, ?, ?)";
			int createStatus = jdbcTemplate.update(sql, list.getListTitle(), list.getBoardId(),list.getStatusCd(),list.getRegId(),list.getRegDt());
			if(createStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

	//Get All Lists only status is '' ->'' status is actived boards 
	@Override
	public List<ListModelBean> getAllLists() {
		List<ListModelBean> lists = new ArrayList<ListModelBean>();
		try {
			String sql = "SELECT l.list_id,list_title,l.board_id,board_title,l.status_cd,l.reg_id,l.reg_dt,l.chg_id,l.chg_dt FROM TP_LIST l " + 
					     "LEFT JOIN TP_BOARD b on b.BOARD_ID=l.BOARD_ID WHERE L.STATUS_CD=''";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			for (Map<String, Object> row : rows) 
			{
				ListModelBean list = new ListModelBean();
				list.setListId((int) row.get("list_id"));
				list.setListTitle((String) row.get("list_title"));
				list.setBoardId((int) row.get("board_id"));
				list.setBoardTitle((String) row.get("board_title"));
				list.setStatusCd((String) row.get("status_cd"));
				list.setRegId((int) row.get("reg_id"));
				list.setRegDt((Date) row.get("reg_dt"));
				if(row.get("chg_id") != null)
				{
					list.setChgId((int) row.get("chg_id"));
					list.setChgDt((Date) row.get("chg_dt"));
				}
				lists.add(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return lists;
	}

	//Get List By List Id
	@Override
	public ListModelBean getListById(int listId) {
		ListModelBean list = new ListModelBean();
		try {
			String sql = "SELECT l.list_id,list_title,l.board_id,board_title,l.status_cd,l.reg_id,l.reg_dt,(CASE WHEN l.chg_id IS NULL THEN '' ELSE l.chg_id END)chg_id,(CASE WHEN l.chg_dt IS NULL THEN '' ELSE l.chg_dt END)chg_dt FROM TP_LIST l " + 
						 "LEFT JOIN TP_BOARD b on b.BOARD_ID=l.BOARD_ID WHERE l.list_id=?";
			RowMapper<ListModelBean> rowMapper = new BeanPropertyRowMapper<ListModelBean>(ListModelBean.class);	
			list = jdbcTemplate.queryForObject(sql,rowMapper,listId);

		}catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}

	//Update List Information
	@Override
	public boolean updateList(ListModelBean list) {
		boolean returnStatus = false;	
		try{
			String sql = "UPDATE TP_LIST SET list_title=?, chg_id=?, chg_dt=? WHERE list_id=?";
			int updateStatus = jdbcTemplate.update(sql, list.getListTitle(), list.getChgId(), list.getChgDt(), list.getListId());
			if(updateStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

	//Delete List
	@Override
	public boolean deleteList(ListModelBean list) {
		boolean returnStatus = false;	
		try{
			String sql = "DELETE FROM TP_LIST WHERE list_id=?";
			int deleteStatus = jdbcTemplate.update(sql, list.getListId());
			if(deleteStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

}
