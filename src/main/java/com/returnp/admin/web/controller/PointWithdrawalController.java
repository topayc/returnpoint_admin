package com.returnp.admin.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dto.command.PointWithdrawalCommand;
import com.returnp.admin.dto.command.RedPointCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.model.Policy;
import com.returnp.admin.service.interfaces.PointWithdrawalService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("pointWithdrawalForm")
public class PointWithdrawalController extends ApplicationController {

	@Autowired PointWithdrawalService pointWithdrawalService;
	@Autowired SearchService searchService;
	
	@RequestMapping(value = "/pointWithdrawal/form/createForm", method = RequestMethod.GET)
	public String form(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "pointWithdrawalNo", defaultValue = "0") int pointWithdrawalNo,
			Model model){
		PointWithdrawalCommand  command  = new PointWithdrawalCommand();
		ArrayList<PointWithdrawalCommand> commands; 
		RedPointCommand redpointConmmand= new RedPointCommand();

		if (action.equals("create")) {
			model.addAttribute("pointWithdrawalForm",command);
		}else if (action.equals("modify")){
			command.setPointWithdrawalNo(pointWithdrawalNo);
			commands = this.pointWithdrawalService.findPointWithdrawalCommands(command);

			if (commands.size() == 1 ) {
				command = this.pointWithdrawalService.findPointWithdrawalCommands(command).get(0);
				model.addAttribute("pointWithdrawalForm", command);
				
				redpointConmmand.setMemberNo(command.getMemberNo());
				redpointConmmand = this.searchService.findRedPointCommands(redpointConmmand).get(0);
				model.addAttribute("redPointCommand", redpointConmmand);
			}
			
		}
	    Policy cond = new Policy();
	    ArrayList<Policy> policies = this.searchService.findPolicies(cond);
	    model.addAttribute("policy", policies.get(policies.size()-1));
	    model.addAttribute("action", action);
	    model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("withdrawalStatusList", CodeDefine.getWithdrawalStatusList());
		return "template/form/pointWithdrawaForm";
	}

	@ResponseBody
	@RequestMapping(value = "/pointWithdrawals", method = RequestMethod.GET)
	public ReturnpBaseResponse  getBranch(
			PointWithdrawalCommand pointWithdrawalCommand) {
		ArrayListResponse<PointWithdrawalCommand> res = new ArrayListResponse<PointWithdrawalCommand>();
		ArrayList<PointWithdrawalCommand> resultList = this.pointWithdrawalService.findPointWithdrawalCommands(pointWithdrawalCommand);
		ResponseUtil.setSuccessResponse(res);
		res.setTotal(resultList.size());
		res.setRows(resultList);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointWithdrawal/create", method = RequestMethod.POST)
	public ReturnpBaseResponse  getMemberBankAccount(
			@ModelAttribute("pointWithdrawalForm") PointWithdrawalCommand pointWithdrawalCommand, 
			BindingResult result, 
			SessionStatus sessionStatus,
			HttpSession httpSession, 
			Model model) {
		ReturnpBaseResponse res= new ReturnpBaseResponse();
		this.pointWithdrawalService.create(pointWithdrawalCommand);
		sessionStatus.setComplete();
		ResponseUtil.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointWithdrawal/update", method = RequestMethod.POST)
	public ReturnpBaseResponse  updateMemberBankAccount(
			@ModelAttribute("pointWithdrawalForm") PointWithdrawalCommand pointWithdrawalCommand, 
			SessionStatus sessionStatus, 
			BindingResult result, 
			HttpSession httpSession, 
			Model model) {
		ReturnpBaseResponse res= new ReturnpBaseResponse();
		this.pointWithdrawalService.update(pointWithdrawalCommand);
		sessionStatus.setComplete();
		ResponseUtil.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointWithdrawal/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse  deleteMemberBankAccount(
			@RequestParam(value = "pointWithdrawalNo", required = true) int  pointWithdrawalNo) {
		ReturnpBaseResponse res= new ReturnpBaseResponse();
		this.pointWithdrawalService.delete(pointWithdrawalNo);
		ResponseUtil.setSuccessResponse(res);
		return res;
	}
}
