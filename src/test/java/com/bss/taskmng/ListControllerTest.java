package com.bss.taskmng;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import com.bss.taskmng.controller.ListController;
import com.bss.taskmng.json.ListInBoard;
import com.bss.taskmng.model.ListModelBean;
import com.google.gson.Gson;

/* Unit Testing for List Controller */
@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@WebMvcTest(ListController.class)
public class ListControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ListController listController;
	
	List<ListModelBean> lists = new ArrayList<ListModelBean>();
	
	@Before
	public void setup() throws Exception {
		this.mvc = MockMvcBuilders.standaloneSetup(this.listController).build();// Standalone context
	}
	
	@Test
    public void createList() throws Exception {
		
		ListInBoard list = new ListInBoard();
		list.setListTitle("To Do List");
		list.setBoardId(1);
		list.setRegId(1);
		
		boolean createStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(list);
		
		BDDMockito.given(listController.createList(Matchers.any(ListInBoard.class))).willReturn(createStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/list/createList")
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
    public void getAllLists() throws Exception {
		
	    ListModelBean list = new ListModelBean();
	    list.setListId(1);
	    list.setListTitle("To Do List");
	    list.setBoardId(2);
	    lists.add(list);
	    
		BDDMockito.given(listController.getAllLists()).willReturn(lists);
        mvc.perform(MockMvcRequestBuilders.get("/list/getAllLists").contentType(MediaType.APPLICATION_JSON))
        	.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].listTitle", is("To Do List")));
    }
	
	@Test
	public void getListById() throws Exception {
		
		ListInBoard list = new ListInBoard();
		list.setListId(1);
	    
		ListModelBean listBean = new ListModelBean();
		listBean.setListId(1);   
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		BDDMockito.given(listController.getListById(Matchers.any(ListInBoard.class))).willReturn(listBean);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/list/getListById")
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
    public void updateList() throws Exception {
		
		ListInBoard list = new ListInBoard();
		list.setListId(1);
		list.setListTitle("To Do List");
		list.setChgId(1);
		
		boolean updateStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(list);
		
		BDDMockito.given(listController.updateList(Matchers.any(ListInBoard.class))).willReturn(updateStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/list/updateList")
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
    public void deleteList() throws Exception {
		
		ListInBoard list = new ListInBoard();
		list.setListId(1);
		
		String deleteStatus = "Delete record successful!";
		
        Gson gson = new Gson();
		String json = gson.toJson(list);
		
		BDDMockito.given(listController.deleteList(Matchers.any(ListInBoard.class))).willReturn(deleteStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/list/deleteList")
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
}
