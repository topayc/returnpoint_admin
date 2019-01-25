package com.returnp.admin.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.CodeKeyValuePair;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.model.Board;
import com.returnp.admin.service.interfaces.BoardService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("boardFormInfo")
public class BoardController extends ApplicationController{
	@Autowired BoardService boardService;
	@Autowired SearchService searchService;
	
	@RequestMapping(value = "/board/form/createForm", method = RequestMethod.GET)
	public String formBoardRequest(
			@RequestParam(value = "action", required = true, defaultValue = "create") String action,
			@RequestParam(value = "mainSubject", required = true, defaultValue = "Y") String mainSubject,
			@RequestParam(value = "boardNo", required = false, defaultValue = "0") int boardNo,
			HttpSession httpSession,
			Model model){
		
		model.addAttribute("boardCates", CodeDefine.getCommonBoardCategories());
		model.addAttribute("boardTypeList", CodeDefine.getBoardTypeList());
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		
		String view = "template/form/board/createBoard";
		if (action.equals("create")) {
			/*메인 주제글 생성*/
			if (mainSubject.equals("Y")) {
				Board board  = new Board();
				board.setBoardLevel(1);
				board.setReplyStatus("1");
				board.setBoardRef(0);
				board.setBoardPid(0);
				board.setBoardWriterEmail(adminSession.getAdmin().getAdminEmail());
				board.setBoardWriterNo(adminSession.getAdmin().getAdminNo());
				board.setBoardWriterName(adminSession.getAdmin().getAdminName());
				board.setBoardWriterType("A");
				board.setViewHitCount(0);
				board.setIsPublic("Y");
				model.addAttribute("boardFormInfo", board);
			}
			/* 메인글에 대한 답글 생성 */
			else {
				Board bCond = new Board();
				bCond .setBoardNo(boardNo);
				Board parBoard = this.boardService.getBoard(bCond);
				
				Board replyBoard = new Board();
				replyBoard.setBoardRef(parBoard.getBoardNo());
				replyBoard.setBoardLevel(2);
				replyBoard.setIsPublic(parBoard.getIsPublic());
				replyBoard.setBoardType(parBoard.getBoardType());
				replyBoard.setBoardCate(parBoard.getBoardCate());
				replyBoard.setBoardWriterEmail(adminSession.getAdmin().getAdminEmail());
				replyBoard.setBoardWriterNo(adminSession.getAdmin().getAdminNo());
				replyBoard.setBoardWriterName(adminSession.getAdmin().getAdminName());
				replyBoard.setBoardWriterType("A");
				replyBoard.setBoardPid(parBoard.getBoardNo());
				replyBoard.setReplyStatus(null);
				replyBoard.setViewHitCount(0);
				replyBoard.setIsPublic("Y");
				model.addAttribute("parBoard", parBoard);
				model.addAttribute("boardFormInfo", replyBoard);
				view = "template/form/board/createReplyBoard"; 
			}
		}else if (action.equals("modify")){
			if (mainSubject.equals("Y")) {
				Board board = new Board();
				board.setBoardNo(boardNo);
				model.addAttribute("boardFormInfo", this.boardService.getBoard(board));	
			}else{
				Board parBoard =  new Board();
				parBoard.setBoardNo(boardNo);
				parBoard = this.boardService.getBoard(parBoard);
						
				Board replyBoard = new Board();
				replyBoard.setBoardPid(boardNo);
				replyBoard  = this.boardService.findBoards(replyBoard).get(0);
				model.addAttribute("boardFormInfo", replyBoard);
				model.addAttribute("parBoard", parBoard);
				model.addAttribute("replyBoard", replyBoard);
				view = "template/form/board/createReplyBoard"; 
			}
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	public BaseResponse  getBoards(Board  board) {
		/*메인 스레드의 글만 불러옴*/
		ArrayListResponse<Board> slr = new ArrayListResponse<Board>();
		board.setBoardPid(0);
		ArrayList<Board> boards = this.boardService.findBoards(board);
		slr.setRows(boards);
		slr.setTotal(boards.size());
		slr.setResultCode("100");
		return slr;
	}
	
	@ResponseBody
	@RequestMapping(value = "/board/get", method = RequestMethod.GET)
	public BaseResponse  getBoard(Board board) {
		SingleDataObjectResponse<Board> res = new SingleDataObjectResponse<Board>();
		res.setData(this.boardService.getBoard(board));
		res.setResultCode("100");
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/boardReply/get", method = RequestMethod.GET)
	public BaseResponse  getReply(Board board) {
		SingleDataObjectResponse< Board> res = new SingleDataObjectResponse<Board>();
		res.setData(this.boardService.getReply(board));
		res.setResultCode("100");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/boardCategories", method = RequestMethod.GET)
	public BaseResponse  getCategories(String boardType) {
		ArrayListResponse<CodeKeyValuePair> slr = new ArrayListResponse<CodeKeyValuePair>();
		ArrayList<CodeKeyValuePair> cateList = null;
		switch(boardType) {
		case "1":
			break;
		case "2":
			cateList = CodeDefine.getCommonBoardCategories();
			break;
		case "3":
			cateList = CodeDefine.getCommonBoardCategories();
			break;
		case "4":
			cateList = CodeDefine.getAffiliateBoardCategories();
			break;
		}
		slr.setRows(cateList);
		slr.setTotal(cateList.size());
		slr.setResultCode("100");
		return slr;
	}
	
	@ResponseBody
	@RequestMapping(value = "/board/create", method = RequestMethod.POST)
	public BaseResponse  createBoard(
			@ModelAttribute("boardFormInfo") Board  board,
			SessionStatus sessionStatus, 
			BindingResult result) {
		
		BaseResponse res = new BaseResponse();
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		switch(board.getBoardLevel()) {
		case 1 : 
			this.boardService.createBoard(board); 
			break;
		case 2 : 
			this.boardService.createReply(board); 
		break;
		}
		res.setResultCode("100");
		res.setMessage("생성 완료");	
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public BaseResponse  updateBoard(
			@ModelAttribute("boardFormInfo") Board  board, SessionStatus sessionStatus, BindingResult result) {
		
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		BaseResponse res = new BaseResponse();
		this.boardService.updateBoard(board);
		res.setResultCode("100");
		res.setMessage("수정 완료");
		sessionStatus.setComplete();
		return res;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/board/delete", method = RequestMethod.POST)
	public  BaseResponse deleteBoard( 
			@RequestParam(value = "boardNo", required  = true ) int  boardNo, Model model) {
		BaseResponse res = new BaseResponse();
		this.boardService.deleteBoard(boardNo);
		res.setResultCode("100");
		res.setMessage("삭제 완료");
		return res;
	}
	@ResponseBody
	@RequestMapping(value = "/boardReply/delete", method = RequestMethod.POST)
	public  BaseResponse deleteReply( 
			@RequestParam(value = "boardNo", required  = true ) int  boardNo, Model model) {
		BaseResponse res = new BaseResponse();
		this.boardService.deleteReply(boardNo);
		res.setResultCode("100");
		res.setMessage("삭제 완료");
		return res;
	}
}
