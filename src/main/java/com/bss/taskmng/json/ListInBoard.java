package com.bss.taskmng.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListInBoard {

	@JsonProperty("_listId")
	private Integer listId;
	
	@JsonProperty("_listTitle")
	private String listTitle;
	
	@JsonProperty("_boardId")
	private Integer boardId;
	
	@JsonProperty("_regId")
	private Integer regId;
	
	@JsonProperty("_chgId")
	private Integer chgId;

	public Integer getListId() {
		return listId;
	}

	public void setListId(Integer listId) {
		this.listId = listId;
	}

	public String getListTitle() {
		return listTitle;
	}

	public void setListTitle(String listTitle) {
		this.listTitle = listTitle;
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

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

}
