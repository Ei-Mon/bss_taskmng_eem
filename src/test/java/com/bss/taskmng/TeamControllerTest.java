package com.bss.taskmng;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bss.taskmng.controller.TeamController;
import com.bss.taskmng.json.Team;
import com.bss.taskmng.model.TeamModelBean;
import com.google.gson.Gson;

/* Unit Testing for Team Controller */
@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@WebMvcTest(TeamController.class)
public class TeamControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TeamController teamController;
	
	@Before
	public void setup() throws Exception {
		this.mvc = MockMvcBuilders.standaloneSetup(this.teamController).build();// Standalone context
	}
	
	@Test
    public void createTeam() throws Exception {
		
		Team team = new Team();
		team.setTeamName("HR");
		team.setTeamDesc("HR Team");
		team.setRegId(1);
		
		boolean createStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(team);
		
		BDDMockito.given(teamController.createTeam(Matchers.any(Team.class))).willReturn(createStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/team/createTeam")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().string("true"))
			            .andReturn();
		
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }
	
	@Test
    public void getAllTeams() throws Exception {
		
		List<TeamModelBean> teams = new ArrayList<TeamModelBean>();
	    TeamModelBean team = new TeamModelBean();
	    team.setTeamId(1);
	    team.setTeamName("CRM");
	    team.setTeamDesc("CRM Team");
	    teams.add(team);
	    
		BDDMockito.given(teamController.getAllTeams()).willReturn(teams);
        mvc.perform(MockMvcRequestBuilders.get("/team/getAllTeams").contentType(MediaType.APPLICATION_JSON))
        	.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].teamName", is("CRM")));
    }
	
	@Test
	public void getTeamById() throws Exception {
		
		Team team = new Team();
		team.setTeamId(1);
		team.setTeamName("CRM");
		team.setTeamDesc("CRM Team");  
	    
		TeamModelBean teamBean = new TeamModelBean();
		teamBean.setTeamId(1);
		teamBean.setTeamName("CRM");
		teamBean.setTeamDesc("CRM Team");   
		
		Gson gson = new Gson();
		String json = gson.toJson(team);
		
		BDDMockito.given(teamController.getTeamById(Matchers.any(Team.class))).willReturn(teamBean);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/team/getTeamById")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().json(json))
			            .andReturn();
		
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
	}
	
	@Test
    public void updateTeam() throws Exception {
		
		Team team = new Team();
		team.setTeamId(1);
		team.setTeamName("HR");
		team.setTeamDesc("HR Team");
		team.setTeamWebsite("www.google.com");
		team.setChgId(1);
		
		boolean updateStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(team);
		
		BDDMockito.given(teamController.updateTeam(Matchers.any(Team.class))).willReturn(updateStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/team/updateTeam")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().string("true"))
			            .andReturn();
		
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }
	
	@Test
    public void deleteTeam() throws Exception {
		
		Team team = new Team();
		team.setTeamId(1);
		
		String deleteStatus = "Delete record successful!";
		
        Gson gson = new Gson();
		String json = gson.toJson(team);
		
		BDDMockito.given(teamController.deleteTeam(Matchers.any(Team.class))).willReturn(deleteStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/team/deleteTeam")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().string("Delete record successful!"))
			            .andReturn();
		
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }
}
