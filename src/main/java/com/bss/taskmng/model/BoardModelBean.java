package com.bss.taskmng.model;

import java.util.Date;

public class BoardModelBean {

	private int boardId;
    private String boardTitle;
    private int teamId;
    private String teamName;
    private String visibilityStatus;
    private String statusCd;
    private int regId;
    private Date regDt;
    private int chgId;
    private Date chgDt;

    public BoardModelBean() {

    }

    public BoardModelBean(int boardId, String boardTitle, int teamId,String teamName,String visibilityStatus,int regId,Date regDt,int chgId,Date chgDt) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.teamId = teamId;
        this.teamName = teamName;
        this.visibilityStatus = visibilityStatus;
        this.regId = regId;
        this.regDt = regDt;
        this.chgId = chgId;
        this.chgDt = chgDt;
    }

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
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

	public String getVisibilityStatus() {
		return visibilityStatus;
	}

	public void setVisibilityStatus(String visibilityStatus) {
		this.visibilityStatus = visibilityStatus;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
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
