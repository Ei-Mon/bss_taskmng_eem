package com.bss.taskmng.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

	@JsonProperty("_taskId")
	private Integer taskId;
	
	@JsonProperty("_taskTitle")
	private String taskTitle;
	
	@JsonProperty("_listId")
	private Integer listId;
	
	@JsonProperty("_boardId")
	private Integer boardId;
	
	@JsonProperty("_regId")
	private Integer regId;
	
	@JsonProperty("_chgId")
	private Integer chgId;
	
	@JsonProperty("_taskDesc")
	private String taskDesc;
	
	@JsonProperty("_comment")
	private String comment;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public Integer getListId() {
		return listId;
	}

	public void setListId(Integer listId) {
		this.listId = listId;
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public Integer getRegId() {
		return regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public Integer getChgId() {
		return chgId;
	}

	public void setChgId(Integer chgId) {
		this.chgId = chgId;
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
	
}
