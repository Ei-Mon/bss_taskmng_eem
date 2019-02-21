package com.bss.taskmng;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bss.taskmng.model.TaskModelBean;
import com.bss.taskmng.service.TaskService;

/* Integration Testing for Task Service */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

	@Autowired
	private TaskService taskService;
	
	@Test
	public void createTask() {
		TaskModelBean task = new TaskModelBean();
		task.setTaskTitle("CRUD For User");
		task.setListId(3);
		task.setBoardId(2);
		task.setStatusCd("");
		task.setRegId(1);
		task.setRegDt(new Date());
		
		boolean createTask = taskService.createTask(task);
		if(createTask)
			assertThat(createTask);
	}
	
	@Test
	public void getAllTasksById() {
		int listId = 8;
		List<TaskModelBean> list = taskService.getAllTasksById(listId);
		assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getTaskById() {
		int taskId = 12;
		TaskModelBean task = taskService.getTaskById(taskId);
		assertThat(task.getTaskId()).isEqualTo(taskId);;
	}
	
	@Test
	public void updateTask() {
		TaskModelBean task = new TaskModelBean();
		task.setTaskId(12);
		task.setTaskTitle("CRUD api for User");
		task.setTaskDesc("CRUD API");
		task.setComment("Deadline is ...");
		task.setChgId(1);
		task.setChgDt(new Date());
		
		boolean updateTask = taskService.updateTask(task);
		if(updateTask)
			assertThat(updateTask);
	}
	
	@Test
	public void deleteTask() {
		TaskModelBean task = new TaskModelBean();
		task.setTaskId(12);
		
		boolean deleteTask = taskService.deleteTask(task);
		if(deleteTask)
			assertThat(deleteTask);
	}
	
	@Test
	public void getAllInfoByBoardId() {
		int boardId = 2;
		List<TaskModelBean> list = taskService.getAllInfoByBoardId(boardId);
		assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void updateTaskTitleById() {
		TaskModelBean task = new TaskModelBean();
		task.setTaskId(12);
		task.setTaskTitle("CRUD rest api for User");
		task.setChgId(1);
		task.setChgDt(new Date());
		
		boolean updateTask = taskService.updateTaskTitleById(task);
		if(updateTask)
			assertThat(updateTask);
	}
}
