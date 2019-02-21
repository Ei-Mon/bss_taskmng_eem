package com.bss.taskmng;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bss.taskmng.controller.TaskController;
import com.bss.taskmng.json.Task;
import com.bss.taskmng.model.TaskModelBean;
import com.google.gson.Gson;

/* Unit Testing for Task Controller */
@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TaskController taskController;
	
	List<TaskModelBean> lists = new ArrayList<TaskModelBean>();
	
	@Before
	public void setup() throws Exception {
		this.mvc = MockMvcBuilders.standaloneSetup(this.taskController).build();// Standalone context
	}
	
	@Test
    public void createTask() throws Exception {
		
		Task task = new Task();
		task.setTaskTitle("Issue 001");
		task.setListId(1);
		task.setBoardId(1);
		task.setRegId(1);
		
		boolean createStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(task);
		
		BDDMockito.given(taskController.createTask(Matchers.any(Task.class))).willReturn(createStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/task/createTask")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().string("true"))
			            .andReturn();
		
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }
	
	@Test
    public void getAllTasksById() throws Exception {
		
		Task task = new Task();
		task.setListId(1);
	    
		List<TaskModelBean> tasks = new ArrayList<TaskModelBean>();
		TaskModelBean taskBean = new TaskModelBean();
		taskBean.setTaskId(1);
		taskBean.setTaskTitle("Issue 001");
		taskBean.setTaskDesc("Issue 001 Desc");
		taskBean.setComment("Customer API Modify");
	    tasks.add(taskBean);
	    
	    List<TaskModelBean> taskList = new ArrayList<TaskModelBean>();
	    TaskModelBean taskModelBean = new TaskModelBean();
	    taskModelBean.setTaskId(1);
		taskModelBean.setTaskTitle("Issue 001");
		taskModelBean.setTaskDesc("Issue 001 Desc");
		taskModelBean.setComment("Customer API Modify");
		taskList.add(taskModelBean);
	    
	    Gson gson = new Gson();
		String json = gson.toJson(task);
	    
	    BDDMockito.given(taskController.getAllTasksById(Matchers.any(Task.class))).willReturn(tasks);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/task/getAllTasksById")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().json(gson.toJson(taskList)))
			            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }
	
	@Test
	public void getTaskById() throws Exception {
		
		Task task = new Task();
		task.setTaskId(1);  
	    
		TaskModelBean taskBean = new TaskModelBean();
		taskBean.setTaskId(1);  
		
		Gson gson = new Gson();
		String json = gson.toJson(task);
		
		BDDMockito.given(taskController.getTaskById(Matchers.any(Task.class))).willReturn(taskBean);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/task/getTaskById")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().json(json))
			            .andReturn();
		
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
	}
	
	@Test
    public void updateTask() throws Exception {
		
		Task task = new Task();
		task.setTaskId(1);
		task.setTaskTitle("Issue 001");
		task.setTaskDesc("Issue 001 Desc");
		task.setComment("Customer API Modify");
		task.setChgId(1);
		
		boolean updateStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(task);
		
		BDDMockito.given(taskController.updateTask(Matchers.any(Task.class))).willReturn(updateStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/task/updateTask")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().string("true"))
			            .andReturn();
		
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }
	
	@Test
    public void deleteTask() throws Exception {
		
		Task task = new Task();
		task.setTaskId(1);
		
		String deleteStatus = "Delete record successful!";
		
        Gson gson = new Gson();
		String json = gson.toJson(task);
		
		BDDMockito.given(taskController.deleteTask(Matchers.any(Task.class))).willReturn(deleteStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/task/deleteTask")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().string("Delete record successful!"))
			            .andReturn();
		
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }
	
	@Test
    public void getAllInfoByBoardId() throws Exception {
		
		Task task = new Task();
		task.setBoardId(1);
	    
		//Return Task List
		List<TaskModelBean> tasks = new ArrayList<TaskModelBean>();
		TaskModelBean taskBean = new TaskModelBean();
		taskBean.setListId(1);
		taskBean.setListTitle("To Do List");
		taskBean.setBoardId(1);
		taskBean.setBoardTitle("Testing Board 4");
		
		List<TaskModelBean> tasksList = new ArrayList<TaskModelBean>();
		TaskModelBean taskBeanList = new TaskModelBean();
		taskBeanList.setTaskId(1);
		taskBeanList.setTaskTitle("Issue 001");
		taskBeanList.setTaskDesc("Issue 001 Desc");
		taskBeanList.setComment("Customer API Modify");
		tasksList.add(taskBeanList);

		taskBean.setTaskList(tasksList);	
	    tasks.add(taskBean);
	    
	    //Expect Task List
	    List<TaskModelBean> expectTaskList = new ArrayList<TaskModelBean>();
	    TaskModelBean taskModelBean = new TaskModelBean();
	    taskModelBean.setListId(1);
	    taskModelBean.setListTitle("To Do List");
	    taskModelBean.setBoardId(1);
	    taskModelBean.setBoardTitle("Testing Board 4");
		
	    List<TaskModelBean> expectTasksList = new ArrayList<TaskModelBean>();
		TaskModelBean expectTaskBeanList = new TaskModelBean();
		expectTaskBeanList.setTaskId(1);
		expectTaskBeanList.setTaskTitle("Issue 001");
		expectTaskBeanList.setTaskDesc("Issue 001 Desc");
		expectTaskBeanList.setComment("Customer API Modify");
		expectTasksList.add(expectTaskBeanList);
		taskModelBean.setTaskList(expectTasksList);	

		expectTaskList.add(taskModelBean);
	    
	    Gson gson = new Gson();
		String json = gson.toJson(task);
	    
	    BDDMockito.given(taskController.getAllInfoByBoardId(Matchers.any(Task.class))).willReturn(tasks);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/task/getAllInfoByBoardId")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().json(gson.toJson(expectTaskList)))
			            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }

	@Test
    public void updateTaskTitleById() throws Exception {
		
		Task task = new Task();
		task.setTaskId(1);
		task.setTaskTitle("Issue 001");
		task.setChgId(1);
		
		boolean updateStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(task);
		
		BDDMockito.given(taskController.updateTaskTitleById(Matchers.any(Task.class))).willReturn(updateStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/task/updateTaskTitleById")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().string("true"))
			            .andReturn();
		
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }
}
