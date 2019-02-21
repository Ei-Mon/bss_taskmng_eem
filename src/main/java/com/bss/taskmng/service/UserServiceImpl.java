package com.bss.taskmng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.bss.taskmng.model.UserModelBean;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//Create New User(Sing Up)
	@Override
	public boolean createUser(UserModelBean user) {
		boolean returnStatus = false;	
		try{
			String sql = "INSERT INTO TP_USER (user_name, email, password, reg_dt) VALUES(?, ?, ?, ?)";
			int createStatus = jdbcTemplate.update(sql, user.getUserName(), user.getEmail(), user.getPassword(), user.getRegDt());
			if(createStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

	//Check User is exist or not By Email
	@Override
	public int getUserByEmail(String email) {
		int count = 0;
		try {
			String sql = "SELECT count(*) FROM TP_USER WHERE email=?";
			count = jdbcTemplate.queryForObject(sql, Integer.class, email);
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return count;
	}

	//Get Login User By Email and Password
	@Override
	public UserModelBean loginUser(String email, String password) {
		UserModelBean user = new UserModelBean();
		try {
			String sql = "SELECT count(*) FROM TP_USER WHERE email=? AND password=?";
			int count = jdbcTemplate.queryForObject(sql, Integer.class, email,password);
			if(count > 0)
			{
				String sql1 = "SELECT * FROM TP_USER WHERE email=? AND password=?";
				RowMapper<UserModelBean> rowMapper = new BeanPropertyRowMapper<UserModelBean>(UserModelBean.class);	
				user = jdbcTemplate.queryForObject(sql1,rowMapper,email,password);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return user;
	}

	//Update User Information
	@Override
	public boolean updateUser(UserModelBean user) {
		boolean returnStatus = false;	
		try{
			String sql = "UPDATE TP_USER SET user_name=?, chg_dt=? WHERE user_id=?";
			int updateStatus = jdbcTemplate.update(sql, user.getUserName(), user.getChgDt(), user.getUserId());
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
