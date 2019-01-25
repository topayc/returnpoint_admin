package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.BoardMapper;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.model.Board;
import com.returnp.admin.service.interfaces.BoardService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired SearchService searchService;
	@Autowired BoardMapper boardMapper;

	@Override
	public ArrayList<Board> findBoards(Board board) {
		return this.searchService.findBoards(board);
	}

	@Override
	public Board getBoard(Board board) {
		return this.boardMapper.selectByPrimaryKey(board.getBoardNo());
	}
	
	@Override
	public void  createBoard(Board board) {
		String boardName = null;
		switch(board.getBoardType()) {
		case "1" : 
			boardName = "공지";
			board.setBoardCate(null);
			board.setReplyStatus(null);
			break;
		case "2" : 
			boardName = "FAQ";
			board.setBoardCate(null);
			board.setReplyStatus(null);
			break;
		case "3" : 
			boardName = "일반 문의";
			if (board.getBoardLevel().equals("1")) {
				board.setReplyStatus("1");
			}
			break;
		case "4" : 
			boardName = "가맹 문의";
			if (board.getBoardLevel().equals("1")) {
				board.setReplyStatus("1");
			}
			break;
		}
		
		board.setBoardName(boardName);
		this.boardMapper.insert(board);
		board.setBoardRef(board.getBoardNo());
		board.setCreateTime(board.getCreateTime());
		this.boardMapper.updateByPrimaryKeyWithBLOBs(board);
	}

	@Override
	public void createReply(Board board) {
		Board pboard = this.boardMapper.selectByPrimaryKey(board.getBoardRef());
		pboard.setReplyStatus("2");
		this.boardMapper.updateByPrimaryKey(pboard);
		board.setBoardRef(pboard.getBoardNo());
		board.setBoardName(pboard.getBoardName());
		this.boardMapper.insert(board);
	}
	
	@Override
	public void deleteBoard(int boardNo) {
		Board  pBoard;
		Board board = new Board();
		board.setBoardNo(boardNo);
		board = this.boardMapper.selectByPrimaryKey(boardNo);
		if (board.getBoardLevel() == 1) {
			pBoard= new Board();
			pBoard.setBoardRef(board.getBoardRef());
			this.boardMapper.deleteBoards(board);
		} else {
			pBoard = this.boardMapper.selectByPrimaryKey(board.getBoardRef());
			pBoard.setReplyStatus("1");
			this.boardMapper.deleteByPrimaryKey(boardNo);
			this.boardMapper.updateByPrimaryKeyWithBLOBs(pBoard);
		}
	}

	@Override
	public void  updateBoard(Board board) {
		this.boardMapper.updateByPrimaryKeyWithBLOBs(board);
	}

	@Override
	public Board getReply(Board board) {
		Board reBoard = new Board();
		reBoard.setBoardPid(board.getBoardNo()); 
		return this.findBoards(reBoard).get(0);
	}

	@Override
	public void deleteReply(int boardNo) {
		Board pBoard = this.boardMapper.selectByPrimaryKey(boardNo);
		pBoard.setReplyStatus("1");
		this.boardMapper.updateByPrimaryKeyWithBLOBs(pBoard);
		
		Board board = new Board();
		board.setBoardPid(boardNo);
		this.boardMapper.deleteBoards(board);
	}
}
