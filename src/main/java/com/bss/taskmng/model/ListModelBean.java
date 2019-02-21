package com.bss.taskmng.model;

import java.util.Date;

public class ListModelBean {

	private int listId;
    private String listTitle;
    private int boardId;
    private String boardTitle;
    private int regId;
    private Date regDt;
    private int chgId;
    private Date chgDt;
    private String statusCd;

    public ListModelBean() {

    }

    public ListModelBean(int listId, String listTitle, int boardId, String boardTitle,int regId,Date regDt,int chgId,Date chgDt) {
        this.listId = listId;
        this.listTitle = listTitle;
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.regId = regId;
        this.regDt = regDt;
        this.chgId = chgId;
        this.chgDt = chgDt;
    }

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public String getListTitle() {
		return listTitle;
	}

	public void setListTitle(String listTitle) {
		this.listTitle = listTitle;
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

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
    
}
