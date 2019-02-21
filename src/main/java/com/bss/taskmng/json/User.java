package com.bss.taskmng.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	@JsonProperty("_userId")
	private Integer userId;
	
	@JsonProperty("_userName")
	private String userName;
	
	@JsonProperty("_email")
	private String email;
	
	@JsonProperty("_password")
	private String password;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
