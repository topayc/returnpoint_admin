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
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.SearchListResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
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
	
		model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatusList());
		model.addAttribute("vanPaymentStatuses", CodeDefine.getVanPaymentStatusList());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypeList());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatusList());
		model.addAttribute("authTypes", CodeDefine.getAuthtypeList());
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
	public  BaseResponse updateRecommender( 
			SearchCondition searchQuery,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		
		RedPointCommand  cond = new RedPointCommand();
		cond.setMemberStatus(searchQuery.getSearchNodeStatus());
		cond.setMemberStatus(searchQuery.getSearchNodeType());
		
		if (searchQuery.getSearchKeyword()!= null &&  !searchQuery.getSearchKeyword().trim().equals("")) {
			cond.setMemberEmail(searchQuery.getSearchKeyword());
			cond.setMemberName(searchQuery.getSearchKeyword());
			cond.setMemberPhone(searchQuery.getSearchKeyword());
		}
		
		ArrayList<RedPointCommand> commandList = this.searchService.findRedPointCommands(cond);
		SearchListResponse<RedPointCommand> slr = new SearchListResponse<RedPointCommand>();
		slr.setRows(commandList);
		slr.setTotal(commandList.size());	
		this.setSuccessResponse(slr);
		return slr;
	}
	
	@ResponseBody
	@RequestMapping(value = "/redPoint/update", method = RequestMethod.POST)
	public  BaseResponse updateRedPoint( 
			@ModelAttribute("redPointFormInfo") RedPoint redPoint,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		BaseResponse res = new BaseResponse();
		this.redPointService.updateByPrimaryKey(redPoint);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/redPointCommand/get", method = RequestMethod.GET)
	public  BaseResponse getRedPointCommand( 
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
		SingleDataObjectResponse<RedPointCommand> slr = new SingleDataObjectResponse<RedPointCommand>();
		
		slr.setData(command);
		this.setSuccessResponse(slr);
		return slr;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/redPoint/delete", method = RequestMethod.POST)
	public  BaseResponse deleteRedPoint( 
			int  redPointNo, Model model) {
		BaseResponse res = new BaseResponse();
		
		RedPoint pc = new RedPoint();
		pc.setRedPointNo(redPointNo);
		this.redPointService.deleteByPrimaryKey(redPointNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
}
