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
import com.returnp.admin.dto.command.GreenPointCommand;
import com.returnp.admin.dto.command.RedPointCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.RedPoint;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("redPointFormInfo")
public class RedPointController extends ApplicationController {

	@Autowired RedPointService redPointService;
	@Autowired SearchService searchService;
	
	@RequestMapping(value = "/redPoint/form/createForm", method = RequestMethod.GET)
	public String formGreenPoint(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "redPointNo", defaultValue = "0") int redPointNo,
			@RequestParam(value = "memberNo", required = false, defaultValue = "0") int memberNo,
			@RequestParam(value = "nodeType", required = false, defaultValue = "0") String nodeType,
			Model model){
	
		model.addAttribute("registTypes", CodeDefine.getRegistTypes());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatuses());
		model.addAttribute("vanPaymentStatuses", CodeDefine.getVanPaymentStatusest());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypest());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatuses());
		model.addAttribute("authTypes", CodeDefine.getAuthTypes());
	    model.addAttribute("action", action);
	    model.addAttribute("redPointNo", redPointNo);
	    
		GreenPointCommand gpcCond= new GreenPointCommand();
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("redPointFormInfo", this.redPointService.selectByPrimaryKey(redPointNo));
		}
		return "template/form/node/createRedPoint";
	}
	
	@ResponseBody
	@RequestMapping(value = "/redPoints", method = RequestMethod.GET)
	public  ReturnpBaseResponse getRedPoints( 
			SearchCondition searchQuery,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		RedPointCommand  cond = new RedPointCommand();
		cond.valueOf(searchQuery);
		cond.setMemberStatus(searchQuery.getSearchNodeStatus());
		
		if (searchQuery.getSearchKeyword()!= null &&  !searchQuery.getSearchKeyword().trim().equals("")) {
			cond.setMemberEmail(searchQuery.getSearchKeyword());
			cond.setMemberName(searchQuery.getSearchKeyword());
			cond.setMemberPhone(searchQuery.getSearchKeyword());
		}
		//System.out.println(searchQuery.getSearchPointMin());
		//System.out.println(searchQuery.getSearchPointMax());
		
		if (searchQuery.getSearchPointMin() == null  || searchQuery.getSearchPointMin() == 0 ) {
			cond.setSearchPointMin(null);
		}else {
			cond.setSearchPointMin(searchQuery.getSearchPointMin());
		}
		
		if (searchQuery.getSearchPointMax() == null ||  searchQuery.getSearchPointMax() == 0 ) {
			cond.setSearchPointMax(null);
		}else {
			cond.setSearchPointMax(searchQuery.getSearchPointMax());
		}
		cond.setOrder("pointAmount DESC");
		ArrayList<RedPointCommand> commandList = this.searchService.findRedPointCommands(cond);
		ArrayListResponse<RedPointCommand> slr = new ArrayListResponse<RedPointCommand>();
		slr.setRows(commandList);
		slr.setTotal(this.searchService.selectTotalRecords());	
		this.setSuccessResponse(slr);
		return slr;
	}
	
	@ResponseBody
	@RequestMapping(value = "/redPoint/update", method = RequestMethod.POST)
	public  ReturnpBaseResponse updateRedPoint( 
			@ModelAttribute("redPointFormInfo") RedPoint redPoint,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.redPointService.updateByPrimaryKey(redPoint);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/redPointCommand/get", method = RequestMethod.GET)
	public  ReturnpBaseResponse getRedPointCommand( 
			@RequestParam(value = "redPointNo", required = false, defaultValue = "0") int  redPointNo, 
			@RequestParam(value = "memberNo", required = false, defaultValue = "0") int  memberNo, 
			HttpSession httpSession, Model model) {
		RedPointCommand  cond = new RedPointCommand();
		if (redPointNo != 0) {
			cond.setRedPointNo(redPointNo);
		}		

		if (memberNo != 0) {
			cond.setMemberNo(memberNo);
		}
		
		RedPointCommand command = this.searchService.findRedPointCommands(cond).get(0);
		ObjectResponse<RedPointCommand> slr = new ObjectResponse<RedPointCommand>();
		
		slr.setData(command);
		this.setSuccessResponse(slr);
		return slr;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/redPoint/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteRedPoint( 
			int  redPointNo, Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		
		RedPoint pc = new RedPoint();
		pc.setRedPointNo(redPointNo);
		this.redPointService.deleteByPrimaryKey(redPointNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
}
