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

import com.bss.taskmng.model.BoardModelBean;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//Create New Board
	@Override
	public boolean createBoard(BoardModelBean board) {
		boolean returnStatus = false;	
		try{
			String sql = "INSERT INTO TP_BOARD (board_title, team_id, visibility_status, status_cd, reg_id, reg_dt) VALUES(?, ?, ?, ?, ?, ?)";
			int createStatus = jdbcTemplate.update(sql, board.getBoardTitle(), board.getTeamId(), board.getVisibilityStatus(), board.getStatusCd(),board.getRegId(),board.getRegDt());
			if(createStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

	//Get All Boards By Status(Status can Closed or '')->Closed status is closed boards and '' status is actived boards
	@Override
	public List<BoardModelBean> getAllBoards(String statusCd) {
		List<BoardModelBean> boards = new ArrayList<BoardModelBean>();
		try {
			String sql = "SELECT B.*,T.TEAM_NAME team_name FROM TP_BOARD B LEFT JOIN TP_TEAM T ON T.TEAM_ID=B.TEAM_ID WHERE B.STATUS_CD=?";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,statusCd);
			for (Map<String, Object> row : rows) 
			{
				BoardModelBean board = new BoardModelBean();
				board.setBoardId((int) row.get("board_id"));
				board.setBoardTitle((String) row.get("board_title"));
				board.setTeamId((int) row.get("team_id"));
				board.setVisibilityStatus((String) row.get("visibility_status"));
				board.setStatusCd((String) row.get("status_cd"));
				board.setRegId((int) row.get("reg_id"));
				board.setRegDt((Date) row.get("reg_dt"));
				if(row.get("chg_id") != null)
				{
					board.setChgId((int) row.get("chg_id"));
					board.setChgDt((Date) row.get("chg_dt"));
				}
				board.setTeamName((String) row.get("team_name"));
				
				boards.add(board);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return boards;
	}

	//Get Board By Board Id
	@Override
	public BoardModelBean getBoardById(int boardId) {
		BoardModelBean board = new BoardModelBean();
		try {
			String sql = "SELECT board_id,board_title,b.team_id,T.TEAM_NAME team_name,visibility_status,b.status_cd,b.reg_id,b.reg_dt, " + 
					     "(CASE WHEN b.chg_id IS NULL THEN '' ELSE b.chg_id END)chg_id,(CASE WHEN b.chg_dt IS NULL THEN '' ELSE b.chg_dt END)chg_dt "+
					     "FROM TP_BOARD b LEFT JOIN TP_TEAM T ON T.TEAM_ID=b.TEAM_ID WHERE b.BOARD_ID=?";
			RowMapper<BoardModelBean> rowMapper = new BeanPropertyRowMapper<BoardModelBean>(BoardModelBean.class);	
			board = jdbcTemplate.queryForObject(sql,rowMapper,boardId);
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return board;
	}

	//Update Board Information
	@Override
	public boolean updateBoard(BoardModelBean board) {
		boolean returnStatus = false;	
		try{
			String sql = "UPDATE TP_BOARD SET board_title=?, team_id=?, visibility_status=?, chg_id=?, chg_dt=?  WHERE board_id=?";
			int updateStatus = jdbcTemplate.update(sql, board.getBoardTitle(), board.getTeamId(), board.getVisibilityStatus(), board.getChgId(), board.getChgDt(), board.getBoardId());
			if(updateStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

	//Close or Reopen Board
	@Override
	public boolean closeOrReOpenBoard(BoardModelBean board) {
		boolean returnStatus = false;	
		String sql = "";
		int count = 0;
		try{
			sql = "SELECT count(*) FROM TP_LIST WHERE board_id=?";
			count = jdbcTemplate.queryForObject(sql, Integer.class, board.getBoardId());
			if(count > 0)
			{
				sql = "SELECT count(*) FROM TP_TASK WHERE board_id=?";
				int taskCount = jdbcTemplate.queryForObject(sql, Integer.class, board.getBoardId());
				if(taskCount > 0)
				{
					sql = "UPDATE TP_TASK SET status_cd=? WHERE board_id=?";
					jdbcTemplate.update(sql, board.getStatusCd(), board.getBoardId());
				}
				sql = "UPDATE TP_LIST SET status_cd=? WHERE board_id=?";
				int listStatus = jdbcTemplate.update(sql, board.getStatusCd(), board.getBoardId());
				if(listStatus > 0)
				{
					sql = "UPDATE TP_BOARD SET status_cd=? WHERE board_id=?";
					int boardStatus = jdbcTemplate.update(sql, board.getStatusCd(), board.getBoardId());
					if(boardStatus > 0)
						returnStatus = true;	
					else
						returnStatus = false;	
				}
				else 
					returnStatus = false;
			}
			else
			{
				sql = "UPDATE TP_BOARD SET status_cd=? WHERE board_id=?";
				int boardStatus = jdbcTemplate.update(sql, board.getStatusCd(), board.getBoardId());
				if(boardStatus > 0)
					returnStatus = true;	
				else
					returnStatus = false;	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}
	
	//Delete Board
	@Override
	public boolean deleteBoard(BoardModelBean board) {
		boolean returnStatus = false;	
		String sql = "";
		int count = 0;
		try{
			sql = "SELECT count(*) FROM TP_LIST WHERE board_id=?";
			count = jdbcTemplate.queryForObject(sql, Integer.class, board.getBoardId());
			if(count > 0)
			{
				sql = "SELECT count(*) FROM TP_TASK WHERE board_id=?";
				int taskCount = jdbcTemplate.queryForObject(sql, Integer.class, board.getBoardId());
				if(taskCount > 0)
				{
					sql = "DELETE FROM TP_TASK WHERE board_id=?";
					jdbcTemplate.update(sql, board.getBoardId());
				}
				sql = "DELETE FROM TP_LIST WHERE board_id=?";
				int listStatus = jdbcTemplate.update(sql, board.getBoardId());
				if(listStatus > 0)
				{
					sql = "DELETE FROM TP_BOARD WHERE board_id=?";
					int boardStatus = jdbcTemplate.update(sql, board.getBoardId());
					if(boardStatus > 0)
						returnStatus = true;	
					else
						returnStatus = false;	
				}
				else 
					returnStatus = false;	
			}
			else
			{
				sql = "DELETE FROM TP_BOARD WHERE board_id=?";
				int boardStatus = jdbcTemplate.update(sql, board.getBoardId());
				if(boardStatus > 0)
					returnStatus = true;	
				else
					returnStatus = false;	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

	//Only Update Board Title By Board Id
	@Override
	public boolean updateBoardTitleById(BoardModelBean board) {
		boolean returnStatus = false;	
		try{
			String sql = "UPDATE TP_BOARD SET board_title=?, chg_id=?, chg_dt=?  WHERE board_id=?";
			int updateStatus = jdbcTemplate.update(sql, board.getBoardTitle(), board.getChgId(), board.getChgDt(), board.getBoardId());
			if(updateStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

}
