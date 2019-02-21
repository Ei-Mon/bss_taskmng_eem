package com.bss.taskmng.service;

import java.util.List;

import com.bss.taskmng.model.TeamModelBean;

public interface TeamService {

	boolean createTeam(TeamModelBean team);	
	List<TeamModelBean> getAllTeams();
	TeamModelBean getTeamById(int teamId);
	boolean updateTeam(TeamModelBean team);
	boolean deleteTeam(TeamModelBean team);
	
}
