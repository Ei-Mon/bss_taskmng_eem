package com.bss.taskmng.model;

import java.util.Date;

public class TeamModelBean {

	private int teamId;
    private String teamName;
    private String teamDesc;
    private String teamWebsite;
    private int regId;
    private Date regDt;
    private int chgId;
    private Date chgDt;

    public TeamModelBean() {

    }

    public TeamModelBean(int teamId, String teamName, String teamDesc,String teamWebsite,int regId,Date regDt,int chgId,Date chgDt) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamDesc = teamDesc;
        this.teamWebsite = teamWebsite;
        this.regId = regId;
        this.regDt = regDt;
        this.chgId = chgId;
        this.chgDt = chgDt;
    }

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamDesc() {
		return teamDesc;
	}

	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}

	public String getTeamWebsite() {
		return teamWebsite;
	}

	public void setTeamWebsite(String teamWebsite) {
		this.teamWebsite = teamWebsite;
	}

	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public int getChgId() {
		return chgId;
	}

	public void setChgId(int chgId) {
		this.chgId = chgId;
	}

	public Date getChgDt() {
		return chgDt;
	}

	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
    
}
