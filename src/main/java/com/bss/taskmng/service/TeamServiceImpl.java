package com.bss.taskmng.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.bss.taskmng.model.TeamModelBean;

@Service
public class TeamServiceImpl implements TeamService{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//Create New Team
	@Override
	public boolean createTeam(TeamModelBean team) {
		boolean returnStatus = false;	
		try{
			String sql = "INSERT INTO TP_TEAM (team_name, team_desc, reg_id, reg_dt) VALUES(?, ?, ?, ?)";
			int createStatus = jdbcTemplate.update(sql, team.getTeamName(), team.getTeamDesc(),team.getRegId(),team.getRegDt());
			if(createStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}
	
	//Get All Teams 
	@Override
	public List<TeamModelBean> getAllTeams() {
		List<TeamModelBean> teams = new ArrayList<TeamModelBean>();
		try {
			String sql = "SELECT * FROM TP_TEAM";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			for (Map<String, Object> row : rows) 
			{
				TeamModelBean team = new TeamModelBean();
				team.setTeamId((int) row.get("team_id"));
				team.setTeamName((String) row.get("team_name"));
				team.setTeamDesc((String) row.get("team_desc"));
				team.setTeamWebsite((String) row.get("team_website"));
				team.setRegId((int) row.get("reg_id"));
				team.setRegDt((Date) row.get("reg_dt"));
				if(row.get("chg_id") != null)
				{
					team.setChgId((int) row.get("chg_id"));
					team.setChgDt((Date) row.get("chg_dt"));
				}
				teams.add(team);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return teams;
	}
	
	//Get Team By Team Id
	@Override
	public TeamModelBean getTeamById(int teamId) {
		TeamModelBean team = new TeamModelBean();
		try {
			String sql = "SELECT team_id,team_name,team_desc,(CASE WHEN team_website IS NULL THEN '' ELSE team_website END)team_website,reg_id,reg_dt,(CASE WHEN chg_id IS NULL THEN '' ELSE chg_id END)chg_id,(CASE WHEN chg_dt IS NULL THEN '' ELSE chg_dt END)chg_dt FROM TP_TEAM WHERE team_id=?";
			RowMapper<TeamModelBean> rowMapper = new BeanPropertyRowMapper<TeamModelBean>(TeamModelBean.class);	
			team = jdbcTemplate.queryForObject(sql,rowMapper,teamId);

		}catch (Exception e) {
			e.printStackTrace();
		}		
		return team;
	}

	//Update Team Information
	@Override
	public boolean updateTeam(TeamModelBean team) {
		boolean returnStatus = false;	
		try{
			String sql = "UPDATE TP_TEAM SET team_name=?, team_desc=?, team_website=?, chg_id=?, chg_dt=? WHERE team_id=?";
			int updateStatus = jdbcTemplate.update(sql, team.getTeamName(), team.getTeamDesc(), team.getTeamWebsite(), team.getChgId(), team.getChgDt(), team.getTeamId());
			if(updateStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

	//Delete Team
	@Override
	public boolean deleteTeam(TeamModelBean team) {
		boolean returnStatus = false;	
		try{
			String sql = "DELETE FROM TP_TEAM WHERE team_id=?";
			int deleteStatus = jdbcTemplate.update(sql, team.getTeamId());
			if(deleteStatus > 0)
				returnStatus = true;
			else 
				returnStatus = false;
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return returnStatus;
	}

}
