package com.bss.taskmng.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bss.taskmng.json.Team;
import com.bss.taskmng.model.TeamModelBean;
import com.bss.taskmng.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamService teamService;
	
	Logger logger = LogManager.getLogger(TeamController.class);
	
	//Create New Team
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/createTeam", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean createTeam(@RequestBody Team request) throws Exception{
		logger.info("TeamController - createTeam");
		TeamModelBean team = new TeamModelBean();
		boolean createStatus = false;
		try {
			team.setTeamName(request.getTeamName());
			team.setTeamDesc(request.getTeamDesc());
			team.setRegId(request.getRegId());
			team.setRegDt(new Date());
			createStatus = teamService.createTeam(team);		
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return createStatus;
	}
	
	//Get All Teams 
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.GET, value = "/getAllTeams") 
	public List<TeamModelBean> getAllTeams() throws Exception{
		logger.info("TeamController - getAllTeams");
		List<TeamModelBean> teams = new ArrayList<TeamModelBean>();
		try {
			teams = teamService.getAllTeams();
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return teams;
	}
	
	//Get Team By Team Id
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/getTeamById", consumes = MediaType.APPLICATION_JSON_VALUE)
	public TeamModelBean getTeamById(@RequestBody Team request) throws Exception{
		logger.info("TeamController - getTeamById");
		TeamModelBean team = new TeamModelBean();
		try {
			team = teamService.getTeamById(request.getTeamId());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return team;
	}
	
	//Update Team Information
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/updateTeam", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean updateTeam(@RequestBody Team request) throws Exception{
		logger.info("TeamController - updateTeam");
		TeamModelBean team = new TeamModelBean();
		boolean updateStatus = false;
		try {
			team.setTeamId(request.getTeamId());
			team.setTeamName(request.getTeamName());
			team.setTeamDesc(request.getTeamDesc());
			team.setTeamWebsite(request.getTeamWebsite());
			team.setChgId(request.getChgId());
			team.setChgDt(new Date());
			updateStatus = teamService.updateTeam(team);			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return updateStatus;
	}
	
	//Delete Team
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/deleteTeam", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public String deleteTeam(@RequestBody Team request) throws Exception{
		logger.info("TeamController - deleteTeam");
		TeamModelBean team = new TeamModelBean();
		String deleteStatus = "";
		boolean returnStatus = false;
		try {
			team.setTeamId(request.getTeamId());
			returnStatus = teamService.deleteTeam(team);
			if(returnStatus)
				deleteStatus = "Delete record successful!";
			else
				deleteStatus = "Delete record fail!";
				
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return deleteStatus;
	}
}
