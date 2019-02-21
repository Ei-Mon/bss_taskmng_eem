package com.bss.taskmng.service;

import com.bss.taskmng.model.UserModelBean;

public interface UserService {

	boolean createUser(UserModelBean user);	
	int getUserByEmail(String email);
	UserModelBean loginUser(String email, String password);
	boolean updateUser(UserModelBean user);
}
