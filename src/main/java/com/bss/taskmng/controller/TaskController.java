package com.bss.taskmng.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bss.taskmng.json.Task;
import com.bss.taskmng.model.TaskModelBean;
import com.bss.taskmng.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	Logger logger = LogManager.getLogger(TaskController.class);
	
	//Create New Task(Card)
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/createTask", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean createTask(@RequestBody Task request) throws Exception{
		logger.info("TaskController - createTask");
		TaskModelBean task = new TaskModelBean();
		boolean createStatus = false;
		try {
			task.setTaskTitle(request.getTaskTitle());
			task.setListId(request.getListId());
			task.setBoardId(request.getBoardId());
			task.setStatusCd("");
			task.setRegId(request.getRegId());
			task.setRegDt(new Date());
			createStatus = taskService.createTask(task);		
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return createStatus;
	}
	
	//Get All Tasks By List Id
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST, value = "/getAllTasksById", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public List<TaskModelBean> getAllTasksById(@RequestBody Task request) throws Exception{
		logger.info("TaskController - getAllTasksById");
		List<TaskModelBean> tasks = new ArrayList<TaskModelBean>();
		try {
			tasks = taskService.getAllTasksById(request.getListId());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return tasks;
	}
	
	//Get Task By Task Id
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/getTaskById", consumes = MediaType.APPLICATION_JSON_VALUE)
	public TaskModelBean getTaskById(@RequestBody Task request) throws Exception{
		logger.info("TaskController - getTaskById");
		TaskModelBean task = new TaskModelBean();
		try {
			task = taskService.getTaskById(request.getTaskId());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return task;
	}
	
	//Update Task Information
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/updateTask", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean updateTask(@RequestBody Task request) throws Exception{
		logger.info("TaskController - updateTask");
		TaskModelBean task = new TaskModelBean();
		boolean updateStatus = false;
		try {
			task.setTaskId(request.getTaskId());
			task.setTaskTitle(request.getTaskTitle());
			task.setTaskDesc(request.getTaskDesc());
			task.setComment(request.getComment());
			task.setChgId(request.getChgId());
			task.setChgDt(new Date());
			updateStatus = taskService.updateTask(task);			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return updateStatus;
	}
	
	//Delete Task
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/deleteTask", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public String deleteTask(@RequestBody Task request) throws Exception{
		logger.info("TaskController - deleteTask");
		TaskModelBean task = new TaskModelBean();
		String deleteStatus = "";
		boolean returnStatus = false;
		try {
			task.setTaskId(request.getTaskId());
			returnStatus = taskService.deleteTask(task);
			if(returnStatus)
				deleteStatus = "Delete record successful!";
			else
				deleteStatus = "Delete record fail!";
				
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return deleteStatus;
	}
	
	//Get All Information(List/Task) By Board Id
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST, value = "/getAllInfoByBoardId", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public List<TaskModelBean> getAllInfoByBoardId(@RequestBody Task request) throws Exception{
		logger.info("TaskController - getAllInfoByBoardId");
		List<TaskModelBean> tasks = new ArrayList<TaskModelBean>();
		try {
			tasks = taskService.getAllInfoByBoardId(request.getBoardId());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return tasks;
	}
	
	//Only Update Task Title By Task Id
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/updateTaskTitleById", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean updateTaskTitleById(@RequestBody Task request) throws Exception{
		logger.info("TaskController - updateTaskTitleById");
		TaskModelBean task = new TaskModelBean();
		boolean updateStatus = false;
		try {
			task.setTaskId(request.getTaskId());
			task.setTaskTitle(request.getTaskTitle());
			task.setChgId(request.getChgId());
			task.setChgDt(new Date());
			updateStatus = taskService.updateTaskTitleById(task);			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return updateStatus;
	}
	
}
