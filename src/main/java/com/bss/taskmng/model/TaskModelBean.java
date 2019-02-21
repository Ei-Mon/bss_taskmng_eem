package com.bss.taskmng.model;

import java.util.Date;
import java.util.List;

public class TaskModelBean{

	private int taskId;
    private String taskTitle;
    private int listId;
	private String listTitle;
	private int boardId;
    private String boardTitle;
    private int regId;
    private Date regDt;
    private int chgId;
    private Date chgDt;
    private String statusCd;
    private String taskDesc;
    private String comment;
    private int teamId;
    private String teamName;
    private List<TaskModelBean> taskList;
    
    public TaskModelBean() {

    }

    public TaskModelBean(int taskId, String taskTitle, int listId, String listTitle, int boardId, String boardTitle,int regId,Date regDt,int chgId,Date chgDt,
    		String statusCd,String taskDesc,String comment,int teamId,String teamName,List<TaskModelBean> taskList) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
		this.listId = listId;
        this.listTitle = listTitle;
		this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.regId = regId;
        this.regDt = regDt;
        this.chgId = chgId;
        this.chgDt = chgDt;
        this.statusCd = statusCd;
        this.taskDesc = taskDesc;
        this.comment = comment;
        this.teamId = teamId;
        this.teamName = teamName;
        this.setTaskList(taskList);
    }

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
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

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public List<TaskModelBean> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskModelBean> taskList) {
		this.taskList = taskList;
	}
    
}
