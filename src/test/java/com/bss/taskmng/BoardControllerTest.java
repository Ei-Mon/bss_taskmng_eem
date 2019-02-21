package com.bss.taskmng;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

import com.bss.taskmng.controller.BoardController;
import com.bss.taskmng.json.Board;
import com.bss.taskmng.model.BoardModelBean;
import com.google.gson.Gson;

/* Unit Testing for Board Controller */
@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@WebMvcTest(BoardController.class)
public class BoardControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private BoardController boardController;
	
	List<BoardModelBean> boards = new ArrayList<BoardModelBean>();
	
	@Before
	public void setup() throws Exception {
		this.mvc = MockMvcBuilders.standaloneSetup(this.boardController).build();// Standalone context
	}
	
	@Test
    public void createBoard() throws Exception {
		
		Board board = new Board();
		board.setBoardTitle("Testing Board 4");
		board.setTeamId(1);
		board.setVisibilityStatus("private");
		board.setRegId(1);
		
		boolean createStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(board);
		
		BDDMockito.given(boardController.createBoard(Matchers.any(Board.class))).willReturn(createStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/board/createBoard")
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
    public void getAllBoardsByStatus() throws Exception {
		
		Board board = new Board();
		board.setBoardId(1);
		board.setBoardTitle("Testing Board 4");
		board.setTeamId(1);
	    
		List<BoardModelBean> boards = new ArrayList<BoardModelBean>();
		BoardModelBean boardBean = new BoardModelBean();
		boardBean.setBoardId(1);
		boardBean.setBoardTitle("Testing Board 4");
	    boardBean.setTeamId(1);
	    boards.add(boardBean);
	    
	    List<BoardModelBean> boardList = new ArrayList<BoardModelBean>();
		BoardModelBean boardModelBean = new BoardModelBean();
		boardModelBean.setBoardId(1);
		boardModelBean.setBoardTitle("Testing Board 4");
		boardModelBean.setTeamId(1);
	    boardList.add(boardModelBean);
	    
	    Gson gson = new Gson();
		String json = gson.toJson(board);
	    
	    BDDMockito.given(boardController.getAllBoardsByStatus(Matchers.any(Board.class))).willReturn(boards);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/board/getAllBoardsByStatus")
        				.accept(MediaType.APPLICATION_JSON_VALUE)
		        		.contentType(MediaType.APPLICATION_JSON)
			        	.content(json)
			        	.characterEncoding("utf-8"))
			        	.andDo(print())
			            .andExpect(status().isOk())
			            .andExpect(MockMvcResultMatchers.content().json(gson.toJson(boardList)))
			            .andReturn();
        
        String res = result.getResponse().getContentAsString();
        System.out.println("Result is "+res);
    }
	
	@Test
	public void getBoardById() throws Exception {
		
		Board board = new Board();
		board.setBoardId(1);  
	    
		BoardModelBean boardBean = new BoardModelBean();
		boardBean.setBoardId(1); 
		
		Gson gson = new Gson();
		String json = gson.toJson(board);
		
		BDDMockito.given(boardController.getBoardById(Matchers.any(Board.class))).willReturn(boardBean);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/board/getBoardById")
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
    public void updateBoard() throws Exception {
		
		Board board = new Board();
		board.setBoardId(1);
		board.setBoardTitle("Testing Board 4");
		board.setTeamId(1);
		board.setVisibilityStatus("public");
		board.setChgId(1);
		
		boolean updateStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(board);
		
		BDDMockito.given(boardController.updateBoard(Matchers.any(Board.class))).willReturn(updateStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/board/updateBoard")
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
    public void closeBoard() throws Exception {
		
		Board board = new Board();
		board.setBoardId(1);
		
		boolean closeStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(board);
		
		BDDMockito.given(boardController.closeBoard(Matchers.any(Board.class))).willReturn(closeStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/board/closeBoard")
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
    public void reOpenBoard() throws Exception {
		
		Board board = new Board();
		board.setBoardId(1);
		
		boolean reOpenStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(board);
		
		BDDMockito.given(boardController.reOpenBoard(Matchers.any(Board.class))).willReturn(reOpenStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/board/reOpenBoard")
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
    public void deleteBoard() throws Exception {
		
		Board board = new Board();
		board.setBoardId(1);
		
		String deleteStatus = "Delete record successful!";
		
        Gson gson = new Gson();
		String json = gson.toJson(board);
		
		BDDMockito.given(boardController.deleteBoard(Matchers.any(Board.class))).willReturn(deleteStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/board/deleteBoard")
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
	
	@Test
    public void updateBoardTitleById() throws Exception {
		
		Board board = new Board();
		board.setBoardId(1);
		board.setBoardTitle("Testing Board 4");
		board.setChgId(1);
		
		boolean updateStatus = true;
		
        Gson gson = new Gson();
		String json = gson.toJson(board);
		
		BDDMockito.given(boardController.updateBoardTitleById(Matchers.any(Board.class))).willReturn(updateStatus);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/board/updateBoardTitleById")
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
}
