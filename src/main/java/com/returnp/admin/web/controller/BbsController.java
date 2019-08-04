package com.returnp.admin.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.dto.CodeKeyValuePair;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Board;
import com.returnp.admin.model.MainBbs;
import com.returnp.admin.service.interfaces.BbsService;
import com.returnp.admin.service.interfaces.BoardService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("boardFormInfo")
public class BbsController extends ApplicationController{
	@Autowired BoardService boardService;
	@Autowired SearchService searchService;
	@Autowired BbsService bbsService;;
	
	
	@ResponseBody
	@RequestMapping(value = "/mainBbses", method = RequestMethod.GET)
	public ReturnpBaseResponse  getMainBbses(MainBbs mainBbs) {
		return this.bbsService.selectMainBbses(mainBbs);
	}
	
	@ResponseBody
	@RequestMapping(value = "/mainBbs/create", method = RequestMethod.POST)
	public ReturnpBaseResponse  createMainBbs(MainBbs mainBbs, HttpSession httpSession) {
		mainBbs.setMainBbsNo(null);
		return this.bbsService.createMainBbs(mainBbs, httpSession);
	}

	@ResponseBody
	@RequestMapping(value = "/mainBbs/update", method = RequestMethod.POST)
	public ReturnpBaseResponse  updateMainBbs(MainBbs mainBbs, HttpSession httpSession) {
		return this.bbsService.updateMainBbs(mainBbs, httpSession);
	}
	
	@ResponseBody
	@RequestMapping(value = "/mainBbs/remove", method = RequestMethod.GET)
	public ReturnpBaseResponse  removeMainBbs(MainBbs mainBbs) {
		return this.bbsService.removeMainBbs(mainBbs);
	}
	
	/*Sub bbs --------------------------------------------------*/
	
	@ResponseBody
	@RequestMapping(value = "/subBbses", method = RequestMethod.GET)
	public ReturnpBaseResponse  getSubBbses(Board board) {
		ObjectResponse<Board> res = new ObjectResponse<Board>();
		res.setData(this.boardService.getBoard(board));
		res.setResultCode("100");
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/subBbs/create", method = RequestMethod.POST)
	public ReturnpBaseResponse  createSubBbs(Board board) {
		ObjectResponse< Board> res = new ObjectResponse<Board>();
		res.setData(this.boardService.getReply(board));
		res.setResultCode("100");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/subBbs/update", method = RequestMethod.POST)
	public ReturnpBaseResponse  updateSubBbs(String boardType) {
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
	@RequestMapping(value = "/subBbs/remove", method = RequestMethod.GET)
	public ReturnpBaseResponse  removeSubBbs(String boardType) {
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
	
}
