package com.bss.taskmng;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bss.taskmng.model.TeamModelBean;
import com.bss.taskmng.service.TeamService;

/* Integration Testing for Team Service */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceTest {
	
	@Autowired
	private TeamService teamService;
	
	@Test
	public void createTeam() {
		TeamModelBean team = new TeamModelBean();
		team.setTeamName("Sale");
		team.setTeamDesc("Sale Team");
		team.setRegId(1);
		team.setRegDt(new Date());
		
		boolean createTeam = teamService.createTeam(team);
		if(createTeam)
			assertThat(createTeam);
	}
	
	@Test
	public void getAllTeams() {
		List<TeamModelBean> list = teamService.getAllTeams();
		assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getTeamById() {
		int teamId = 4;
		TeamModelBean team = teamService.getTeamById(teamId);
		assertThat(team.getTeamId()).isEqualTo(teamId);;
	}
	
	@Test
	public void updateTeam() {
		TeamModelBean team = new TeamModelBean();
		team.setTeamId(4);
		team.setTeamName("Sale Team");
		team.setTeamDesc("Sale Team 1");
		team.setTeamWebsite("www.tender.com");
		team.setChgId(1);
		team.setChgDt(new Date());
		
		boolean updateTeam = teamService.updateTeam(team);
		if(updateTeam)
			assertThat(updateTeam);
	}
	
	@Test
	public void deleteTeam() {
		TeamModelBean team = new TeamModelBean();
		team.setTeamId(4);
		
		boolean deleteTeam = teamService.deleteTeam(team);
		if(deleteTeam)
			assertThat(deleteTeam);
	}
}
