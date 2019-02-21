package com.bss.taskmng;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bss.taskmng.model.BoardModelBean;
import com.bss.taskmng.service.BoardService;

/* Integration Testing for Board Service */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {

	@Autowired
	private BoardService boardService;
	
	@Test
	public void createBoard() {
		BoardModelBean board = new BoardModelBean();
		board.setBoardTitle("Testing Board 4");
		board.setTeamId(1);
		board.setVisibilityStatus("team");
		board.setStatusCd("");
		board.setRegId(1);
		board.setRegDt(new Date());
		
		boolean createBoard = boardService.createBoard(board);
		if(createBoard)
			assertThat(createBoard);
	}
	
	@Test
	public void getAllBoards() {
		String statusCd = "";
		List<BoardModelBean> list = boardService.getAllBoards(statusCd);
		assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getBoardById() {
		int boardId = 6;
		BoardModelBean board = boardService.getBoardById(boardId);
		assertThat(board.getBoardId()).isEqualTo(boardId);;
	}
	
	@Test
	public void updateBoard() {
		BoardModelBean board = new BoardModelBean();
		board.setBoardId(6);
		board.setBoardTitle("Testing Boarding 4");
		board.setTeamId(5);
		board.setVisibilityStatus("public");
		board.setChgId(1);
		board.setChgDt(new Date());
		
		boolean updateBoard = boardService.updateBoard(board);
		if(updateBoard)
			assertThat(updateBoard);
	}
	
	@Test
	public void closeBoard() {
		BoardModelBean board = new BoardModelBean();
		board.setBoardId(6);
		board.setStatusCd("Closed");
		
		boolean closeBoard = boardService.closeOrReOpenBoard(board);
		if(closeBoard)
			assertThat(closeBoard);
	}
	
	@Test
	public void reOpenBoard() {
		BoardModelBean board = new BoardModelBean();
		board.setBoardId(6);
		board.setStatusCd("");
		
		boolean reOpenBoard = boardService.closeOrReOpenBoard(board);
		if(reOpenBoard)
			assertThat(reOpenBoard);
	}
	
	@Test
	public void deleteBoard() {
		BoardModelBean board = new BoardModelBean();
		board.setBoardId(6);
		
		boolean deleteBoard = boardService.deleteBoard(board);
		if(deleteBoard)
			assertThat(deleteBoard);
	}
	
	@Test
	public void updateBoardTitleById() {
		BoardModelBean board = new BoardModelBean();
		board.setBoardId(6);
		board.setBoardTitle("Testing Board 4");
		board.setChgId(1);
		board.setChgDt(new Date());
		
		boolean updateBoard = boardService.updateBoardTitleById(board);
		if(updateBoard)
			assertThat(updateBoard);
	}
}
