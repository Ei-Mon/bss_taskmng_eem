package com.bss.taskmng.service;

import java.util.List;

import com.bss.taskmng.model.BoardModelBean;

public interface BoardService {

	boolean createBoard(BoardModelBean board);	
	List<BoardModelBean> getAllBoards(String statusCd);
	BoardModelBean getBoardById(int boardId);
	boolean updateBoard(BoardModelBean board);
	boolean closeOrReOpenBoard(BoardModelBean board);
	boolean deleteBoard(BoardModelBean board);
	boolean updateBoardTitleById(BoardModelBean board);
}
