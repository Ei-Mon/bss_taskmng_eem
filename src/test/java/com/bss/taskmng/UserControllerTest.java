package com.bss.taskmng;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.bss.taskmng.controller.UserController;
import com.bss.taskmng.json.User;
import com.bss.taskmng.model.UserModelBean;
import com.google.gson.Gson;

/* Unit Testing for User Controller */
@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserController userController;
	
	@Before
	public void setup() throws Exception {
		this.mvc = MockMvcBuilders.standaloneSetup(this.userController).build();// Standalone context
	}
	
	@Test
    public void createUser() throws Exception {
		
		User user = new User();
		user.setUserName("Sky Blue");
		user.setEmail("skyblue@gmail.com");
		user.setPassword("123456");
		
		String createStatus = "Save record successful!";
		
        Gson gson = new Gson();
		String json = gson.toJson(user);
		
		BDDMockito.given(userController.createUser(Matchers.any(User.class))).willReturn(createStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/login/createUser")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().string("Save record successful!"))
			            .andReturn();
		
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }
	
	@Test
    public void loginUser() throws Exception {
		
		User user = new User();
		user.setEmail("eiei@blue-stone.net");
		user.setPassword("123456");
		
		UserModelBean userBean = new UserModelBean();
		userBean.setEmail("eiei@blue-stone.net");
		userBean.setPassword("123456");
		
		Gson gson = new Gson();
		String json = gson.toJson(user);
		
		//when(userController.loginUser(user)).thenReturn(userBean);
		BDDMockito.given(userController.loginUser(Matchers.any(User.class))).willReturn(userBean);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/login/loginUser")
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
    public void updateUser() throws Exception {
		
		User user = new User();
		user.setUserId(4);
		user.setUserName("Sky Blue");
		
		boolean updateStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(user);
		
		BDDMockito.given(userController.updateUser(Matchers.any(User.class))).willReturn(updateStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/login/updateUser")
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
