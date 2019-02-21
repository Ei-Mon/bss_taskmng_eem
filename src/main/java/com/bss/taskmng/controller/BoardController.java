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

import com.bss.taskmng.json.Board;
import com.bss.taskmng.model.BoardModelBean;
import com.bss.taskmng.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	Logger logger = LogManager.getLogger(BoardController.class);
	
	//Create New Board
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/createBoard", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean createBoard(@RequestBody Board request) throws Exception{
		logger.info("BoardController - createBoard");
		BoardModelBean board = new BoardModelBean();
		boolean createStatus = false;
		try {
			board.setBoardTitle(request.getBoardTitle());
			board.setTeamId(request.getTeamId());
			board.setVisibilityStatus(request.getVisibilityStatus());
			board.setStatusCd("");
			board.setRegId(request.getRegId());
			board.setRegDt(new Date());
			createStatus = boardService.createBoard(board);		
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return createStatus;
	}
	
	//Get All Boards By Status(Status can Closed or '')->Closed status is closed boards and '' status is actived boards
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/getAllBoardsByStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<BoardModelBean>  getAllBoardsByStatus(@RequestBody Board request) throws Exception{
		logger.info("BoardController - getAllBoardsByStatus");
		List<BoardModelBean> boards = new ArrayList<BoardModelBean>();
		try {
			boards = boardService.getAllBoards(request.getStatusCd());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return boards;
	}
	
	//Get Board By Board Id
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/getBoardById", consumes = MediaType.APPLICATION_JSON_VALUE)
	public BoardModelBean getBoardById(@RequestBody Board request) throws Exception{
		logger.info("BoardController - getBoardById");
		BoardModelBean board = new BoardModelBean();
		try {
			board = boardService.getBoardById(request.getBoardId());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return board;
	}
	
	//Update Board Information
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/updateBoard", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean updateBoard(@RequestBody Board request) throws Exception{
		logger.info("BoardController - updateBoard");
		BoardModelBean board = new BoardModelBean();
		boolean updateStatus = false;
		try {
			board.setBoardId(request.getBoardId());
			board.setBoardTitle(request.getBoardTitle());
			board.setTeamId(request.getTeamId());
			board.setVisibilityStatus(request.getVisibilityStatus());
			board.setChgId(request.getChgId());
			board.setChgDt(new Date());
			updateStatus = boardService.updateBoard(board);			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return updateStatus;
	}
	
	//Close Board
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/closeBoard", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean closeBoard(@RequestBody Board request) throws Exception{
		logger.info("BoardController - closeBoard");
		BoardModelBean board = new BoardModelBean();
		boolean closeStatus = false;
		try {
			board.setBoardId(request.getBoardId());
			board.setStatusCd("Closed");
			closeStatus = boardService.closeOrReOpenBoard(board);
				
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return closeStatus;
	}
	
	//Reopen Board
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/reOpenBoard", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean reOpenBoard(@RequestBody Board request) throws Exception{
		logger.info("BoardController - reOpenBoard");
		BoardModelBean board = new BoardModelBean();
		boolean reOpenStatus = false;
		try {
			board.setBoardId(request.getBoardId());
			board.setStatusCd("");
			reOpenStatus = boardService.closeOrReOpenBoard(board);
				
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return reOpenStatus;
	}
	
	//Delete Board
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/deleteBoard", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public String deleteBoard(@RequestBody Board request) throws Exception{
		logger.info("BoardController - deleteBoard");
		BoardModelBean board = new BoardModelBean();
		String deleteStatus = "";
		boolean returnStatus = false;
		try {
			board.setBoardId(request.getBoardId());
			returnStatus = boardService.deleteBoard(board);
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
	
	//Only Update Board Title By Board Id
	@CrossOrigin(origins = "*")
	 @RequestMapping(method = RequestMethod.POST, value = "/updateBoardTitleById", consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public boolean updateBoardTitleById(@RequestBody Board request) throws Exception{
		logger.info("BoardController - updateBoardTitleById");
		BoardModelBean board = new BoardModelBean();
		boolean updateStatus = false;
		try {
			board.setBoardId(request.getBoardId());
			board.setBoardTitle(request.getBoardTitle());
			board.setChgId(request.getChgId());
			board.setChgDt(new Date());
			updateStatus = boardService.updateBoardTitleById(board);			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return updateStatus;
	}
}
