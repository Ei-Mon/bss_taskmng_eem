package com.bss.taskmng;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bss.taskmng.model.UserModelBean;
import com.bss.taskmng.service.UserService;

/* Integration Testing for User Service */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void createUser() {
		UserModelBean user = new UserModelBean();
		user.setUserName("EEM");
		user.setEmail("eimonlay@blue-stone.net");
		user.setPassword("13579");
		user.setRegDt(new Date());
		
		boolean createUser = userService.createUser(user);
		if(createUser)
		{
			int isExist = userService.getUserByEmail(user.getEmail());
			
			if(isExist>0)
				assertThat(user.getEmail());
		}
	}
	
	@Test
	public void loginUser() {
		String email = "eimonlay@blue-stone.net";
		String password = "13579";
		UserModelBean user = userService.loginUser(email,password);
		assertThat(user.getEmail()).isEqualTo(email);
	}
	
	@Test
	public void updateUser() {
		UserModelBean user = new UserModelBean();
		user.setUserName("EEM Eimon");
		user.setUserId(3);
		user.setChgDt(new Date());
		
		boolean updateUser = userService.updateUser(user);
		if(updateUser)
			assertThat(updateUser);
	}
}
