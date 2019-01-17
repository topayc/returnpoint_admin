package com.returnp.admin.service.interfaces;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.model.Board;

@Transactional
public interface BoardService {
	public ArrayList<Board> findBoards(Board board);
	
	public Board getBoard(Board board);
	
	public Board getReply(Board board);

	public void  createBoard(Board board);
	
	public void  createReply(Board board);

	public void  deleteBoard(int boardNo);
	public void  deleteReply(int boardNo);

	public void updateBoard(Board board);
}
