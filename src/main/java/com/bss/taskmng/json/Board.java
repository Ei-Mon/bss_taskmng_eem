package com.bss.taskmng.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Board {
	
	@JsonProperty("_boardId")
	private Integer boardId;
	
	@JsonProperty("_boardTitle")
	private String boardTitle;
	
	@JsonProperty("_teamId")
	private Integer teamId;
	
	@JsonProperty("_visibilityStatus")
	private String visibilityStatus;

	@JsonProperty("_statusCd")
	private String statusCd;
	
	@JsonProperty("_regId")
	private Integer regId;
	
	@JsonProperty("_chgId")
	private Integer chgId;
	
	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
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

}
