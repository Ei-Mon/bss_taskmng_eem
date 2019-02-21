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

import com.bss.taskmng.model.TaskModelBean;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//Create New Task(Card)
	@Override
	public boolean createTask(TaskModelBean task) {
		boolean returnStatus = false;	
		try{
			String sql = "INSERT INTO TP_TASK (task_title, list_id, board_id, status_cd, reg_id, reg_dt) VALUES(?, ?, ?, ?, ?, ?)";
			int createStatus = jdbcTemplate.update(sql, task.getTaskTitle(), task.getListId(), task.getBoardId(),task.getStatusCd(),task.getRegId(),task.getRegDt());
			if(createStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

	//Get All Tasks By List Id
	@Override
	public List<TaskModelBean> getAllTasksById(int listId) {
		List<TaskModelBean> tasks = new ArrayList<TaskModelBean>();
		try {
			String sql = "SELECT task_id,task_title,(CASE WHEN task_desc IS NULL THEN '' ELSE task_desc END)task_desc,(CASE WHEN comment IS NULL THEN '' ELSE comment END)comment, " + 
						"t.list_id,list_title,l.board_id,board_title,t.status_cd,t.reg_id,t.reg_dt,t.chg_id,t.chg_dt FROM TP_TASK T " + 
						"LEFT JOIN TP_LIST L ON L.LIST_ID=T.LIST_ID LEFT JOIN TP_BOARD b on b.BOARD_ID=l.BOARD_ID WHERE L.LIST_ID=?";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,listId);
			for (Map<String, Object> row : rows) 
			{
				TaskModelBean task = new TaskModelBean();
				task.setTaskId((int) row.get("task_id"));
				task.setTaskTitle((String) row.get("task_title"));
				task.setTaskDesc((String) row.get("task_desc"));
				task.setComment((String) row.get("comment"));
				task.setListId((int) row.get("list_id"));
				task.setListTitle((String) row.get("list_title"));
				task.setBoardId((int) row.get("board_id"));
				task.setBoardTitle((String) row.get("board_title"));
				task.setStatusCd((String) row.get("status_cd"));
				task.setRegId((int) row.get("reg_id"));
				task.setRegDt((Date) row.get("reg_dt"));
				if(row.get("chg_id") != null)
				{
					task.setChgId((int) row.get("chg_id"));
					task.setChgDt((Date) row.get("chg_dt"));
				}
				tasks.add(task);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return tasks;
	}
	
	//Get Task By Task Id
	@Override
	public TaskModelBean getTaskById(int taskId) {
		TaskModelBean task = new TaskModelBean();
		try {
			String sql = "SELECT task_id,task_title,(CASE WHEN task_desc IS NULL THEN '' ELSE task_desc END)task_desc,(CASE WHEN comment IS NULL THEN '' ELSE comment END)comment, " + 
					     "t.list_id,l.list_title,t.board_id,b.board_title,t.status_cd,t.reg_id,t.reg_dt,(CASE WHEN t.chg_id IS NULL THEN '' ELSE t.chg_id END)chg_id,(CASE WHEN t.chg_dt IS NULL THEN '' ELSE t.chg_dt END)chg_dt " +
					     "FROM TP_TASK T LEFT JOIN TP_LIST L ON L.LIST_ID=T.LIST_ID LEFT JOIN TP_BOARD b on b.BOARD_ID=l.BOARD_ID WHERE task_id=?";
			RowMapper<TaskModelBean> rowMapper = new BeanPropertyRowMapper<TaskModelBean>(TaskModelBean.class);	
			task = jdbcTemplate.queryForObject(sql,rowMapper,taskId);

		}catch (Exception e) {
			e.printStackTrace();
		}		
		return task;
	}

	//Update Task Information
	@Override
	public boolean updateTask(TaskModelBean task) {
		boolean returnStatus = false;	
		try{
			String sql = "UPDATE TP_TASK SET task_title=?, task_desc=?, comment=?, chg_id=?, chg_dt=? WHERE task_id=?";
			int updateStatus = jdbcTemplate.update(sql, task.getTaskTitle(), task.getTaskDesc(),task.getComment(),task.getChgId(), task.getChgDt(), task.getTaskId());
			if(updateStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

	//Delete Task
	@Override
	public boolean deleteTask(TaskModelBean task) {
		boolean returnStatus = false;	
		try{
			String sql = "DELETE FROM TP_TASK WHERE task_id=?";
			int deleteStatus = jdbcTemplate.update(sql, task.getTaskId());
			if(deleteStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

	//Get All Information(List/Task) By Board Id
	@Override
	public List<TaskModelBean> getAllInfoByBoardId(int boardId) {
		List<TaskModelBean> lists = new ArrayList<TaskModelBean>();
		String sql = "";
		try {
			//Get All List By Board Id
			sql = "SELECT l.list_id,list_title,l.board_id,board_title,t.team_id,t.team_name FROM TP_LIST l " + 
					     "LEFT JOIN TP_BOARD b on b.BOARD_ID=l.BOARD_ID " +
					     "LEFT JOIN TP_TEAM t on t.team_id=b.team_id WHERE l.board_id=?";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,boardId);
			for (Map<String, Object> row : rows) 
			{
				List<TaskModelBean> tasks = new ArrayList<TaskModelBean>();
				TaskModelBean list = new TaskModelBean();
				list.setListId((int) row.get("list_id"));
				list.setListTitle((String) row.get("list_title"));
				list.setBoardId((int) row.get("board_id"));
				list.setBoardTitle((String) row.get("board_title"));	
				list.setTeamId((int) row.get("team_id"));
				list.setTeamName((String) row.get("team_name"));	
				
				//Get All Task/Card By List Id
				sql = "SELECT task_id,task_title,(CASE WHEN task_desc IS NULL THEN '' ELSE task_desc END)task_desc, "+
					  "(CASE WHEN comment IS NULL THEN '' ELSE comment END)comment FROM TP_TASK WHERE list_id=?";
				List<Map<String, Object>> rowsTask = jdbcTemplate.queryForList(sql,list.getListId());
				for (Map<String, Object> taskRow : rowsTask) 
				{
					TaskModelBean task = new TaskModelBean();
					task.setTaskId((int) taskRow.get("task_id"));
					task.setTaskTitle((String) taskRow.get("task_title"));
					task.setTaskDesc((String) taskRow.get("task_desc"));
					task.setComment((String) taskRow.get("comment"));	
					tasks.add(task);
				}
				list.setTaskList(tasks);
				lists.add(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return lists;
	}

	//Only Update Task Title By Task Id
	@Override
	public boolean updateTaskTitleById(TaskModelBean task) {
		boolean returnStatus = false;	
		try{
			String sql = "UPDATE TP_TASK SET task_title=?, chg_id=?, chg_dt=? WHERE task_id=?";
			int updateStatus = jdbcTemplate.update(sql, task.getTaskTitle() ,task.getChgId(), task.getChgDt(), task.getTaskId());
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
