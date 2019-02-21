package com.bss.taskmng.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {

	@JsonProperty("_teamId")
	private Integer teamId;
	
	@JsonProperty("_teamName")
	private String teamName;
	
	@JsonProperty("_teamDesc")
	private String teamDesc;
	
	@JsonProperty("_teamWebsite")
	private String teamWebsite;
	
	@JsonProperty("_regId")
	private Integer regId;
	
	@JsonProperty("_chgId")
	private Integer chgId;

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
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
