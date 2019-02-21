package com.bss.taskmng.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bss.taskmng.json.User;
import com.bss.taskmng.model.UserModelBean;
import com.bss.taskmng.service.UserService;

@RestController
@RequestMapping("/login")
public class UserController {

	@Autowired
	private UserService userService;
	
	Logger logger = LogManager.getLogger(UserController.class);
	
	//Create New User(Sing Up)
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public String createUser(@RequestBody User request) throws Exception{
		logger.info("UserController - createUser");
		UserModelBean user = new UserModelBean();
		String createStatus = "";
		boolean returnStatus = false;
		try {
			user.setUserName(request.getUserName());
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			user.setRegDt(new Date());
			int userCount = userService.getUserByEmail(user.getEmail());	
			if(userCount == 0)
			{
				returnStatus = userService.createUser(user);	
				if(returnStatus)
					createStatus = "Save record successful!";
				else
					createStatus = "Save record fail!";
			}
			else
				createStatus = "Email already in use by another account!";
				
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return createStatus;
	}
	
	//Get Login User By Email and Password
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/loginUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserModelBean loginUser(@RequestBody User request) throws Exception{
		logger.info("UserController - loginUser");
		UserModelBean user = new UserModelBean();
		try {
			user = userService.loginUser(request.getEmail(),request.getPassword());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return user;
	}
	
	//Update User Information
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean updateUser(@RequestBody User request) throws Exception{
		logger.info("UserController - updateUser");
		UserModelBean user = new UserModelBean();
		boolean updateStatus = false;
		try {
			user.setUserId(request.getUserId());
			user.setUserName(request.getUserName());
			user.setChgDt(new Date());
			updateStatus = userService.updateUser(user);			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return updateStatus;
	}
	
}
