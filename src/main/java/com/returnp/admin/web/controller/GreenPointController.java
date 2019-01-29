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
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Policy;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("greenPointFormInfo")
public class GreenPointController extends ApplicationController {
	
	@Autowired GreenPointService greenPointService;
	@Autowired SearchService searchService;
	
	
	@RequestMapping(value = "/greenPoint/form/createForm", method = RequestMethod.GET)
	public String formGreenPoint(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "greenPointNo", defaultValue = "0") int greenPointNo,
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
	    model.addAttribute("greenPointNo", greenPointNo);
	  
		GreenPointCommand gpcCond= new GreenPointCommand();
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			System.out.println(greenPointNo);
			gpcCond.setGreenPointNo(greenPointNo);
			model.addAttribute("greenPointFormInfo", this.greenPointService.selectByPrimaryKey(greenPointNo));
		}
		return "template/form/node/createGreenPoint";
	}
	
	@ResponseBody
	@RequestMapping(value = "/greenPoints", method = RequestMethod.GET)
	public  ReturnpBaseResponse getGreenPoints( 
			SearchCondition searchQuery,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		GreenPointCommand  cond = new GreenPointCommand();
		cond.valueOf(searchQuery);
		
		cond.setMemberStatus(searchQuery.getSearchNodeStatus());
		cond.setNodeType(searchQuery.getSearchNodeType());
		cond.setMemberNo(searchQuery.getMemberNo());
		
		if (searchQuery.getSearchKeyword()!= null &&  !searchQuery.getSearchKeyword().trim().equals("")) {
			cond.setMemberEmail(searchQuery.getSearchKeyword());
			cond.setMemberName(searchQuery.getSearchKeyword());
			cond.setMemberPhone(searchQuery.getSearchKeyword());
		}
		
		
		ArrayList<GreenPointCommand> commandList = this.searchService.findGreenPointCommands(cond);
		ArrayListResponse<GreenPointCommand> slr = new ArrayListResponse<GreenPointCommand>();
		slr.setRows(commandList);
		slr.setTotal(this.searchService.selectTotalRecords());	
		this.setSuccessResponse(slr);
		return slr;
	}
	
	@ResponseBody
	@RequestMapping(value = "/greenPoint/get", method = RequestMethod.GET)
	public ReturnpBaseResponse  getGreenPoint(
		@RequestParam(value = "greenPointNo", required = false, defaultValue = "0" ) int  greenPointNo, 
		@RequestParam(value = "memberNo", required = false, defaultValue = "0" ) int  memberNo) {
		
		GreenPoint cond = new GreenPoint();
		cond.setGreenPointNo(greenPointNo);
		cond.setMemberNo(memberNo);
		GreenPoint greenPoint= this.searchService.findGreenPoints(cond).get(0);
		
		ObjectResponse<GreenPoint> res = new ObjectResponse<GreenPoint>();
		res.setData(greenPoint);
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/greenPointCommand/get", method = RequestMethod.GET)
	public  ReturnpBaseResponse getGreenPointCommand( 
			@RequestParam(value = "greenPointNo", required = false, defaultValue = "1" ) int  greenPointNo, 
			HttpSession httpSession, Model model) {
		
		System.out.println("getGreenPointCommand 호출 ");
		GreenPointCommand  cond = new GreenPointCommand();
		cond.setGreenPointNo(greenPointNo);
		
		GreenPointCommand command = this.searchService.findGreenPointCommands(cond).get(0);
		ObjectResponse<GreenPointCommand> slr = new ObjectResponse<GreenPointCommand>();
		
		slr.setData(command);
		this.setSuccessResponse(slr);
		return slr;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/greenPoint/update", method = RequestMethod.POST)
	public  ReturnpBaseResponse updateGreenPoint( 
			@ModelAttribute("greenPointFormInfo") GreenPoint greenPoint,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		System.out.println("updatePointConvertRequest 호출됨");
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.greenPointService.updateByPrimaryKey(greenPoint);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/greenPoint/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteGreenPoint( 
			int  greenPointNo, Model model) {
		System.out.println("deleteGreenPoint");
		System.out.println(greenPointNo);
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		
		GreenPoint pc = new GreenPoint();
		pc.setGreenPointNo(greenPointNo);
		this.greenPointService.deleteByPrimaryKey(greenPointNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
	
}
