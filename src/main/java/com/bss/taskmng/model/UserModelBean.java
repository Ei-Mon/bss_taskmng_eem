package com.bss.taskmng.model;

import java.util.Date;

public class UserModelBean {

	private int userId;
	private String userName;
    private String email;
    private String password;
    private Date regDt;
    private Date chgDt;

    public UserModelBean() {

    }

    public UserModelBean(int userId, String userName, String email, String password,Date regDt,Date chgDt) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.regDt = regDt;
        this.chgDt = chgDt;
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public Date getChgDt() {
		return chgDt;
	}

	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}
