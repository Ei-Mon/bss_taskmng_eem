package com.bss.taskmng.service;

import java.util.List;

import com.bss.taskmng.model.TaskModelBean;

public interface TaskService {
	
	boolean createTask(TaskModelBean task);	
	List<TaskModelBean> getAllTasksById(int listId);
	TaskModelBean getTaskById(int taskId);
	boolean updateTask(TaskModelBean task);
	boolean deleteTask(TaskModelBean task);
	List<TaskModelBean> getAllInfoByBoardId(int boardId);
	boolean updateTaskTitleById(TaskModelBean task);
}
