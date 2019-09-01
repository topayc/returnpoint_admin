package com.returnp.admin.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import com.returnp.admin.dao.mapper.RedPointMapper;
import com.returnp.admin.dto.command.PointWithdrawalCommand;
import com.returnp.admin.dto.command.RedPointCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.RedPoint;
import com.returnp.admin.service.interfaces.PointWithdrawalService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("pointWithdrawalForm")
public class PointWithdrawalController extends ApplicationController {

	@Autowired PointWithdrawalService pointWithdrawalService;
	@Autowired SearchService searchService;
	@Autowired RedPointMapper redPointMapper;;
	
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
	    model.addAttribute("registTypes", CodeDefine.getRegistTypes());
		model.addAttribute("withdrawalStatusList", CodeDefine.getWithdrawalStatuses());
		return "template/form/pointWithdrawaForm";
	}

	@ResponseBody
	@RequestMapping(value = "/pointWithdrawals", method = RequestMethod.GET)
	public ReturnpBaseResponse  getPointWithdrawals(
			SearchCondition searchCondition) {
		PointWithdrawalCommand cond = new PointWithdrawalCommand();
		cond.valueOf(searchCondition);
		if (searchCondition.getPointWithdrawalNo() != null){
			cond.setPointWithdrawalNo(searchCondition.getPointWithdrawalNo());
		}
		System.out.println("pointWithdrawalNo " + cond.getPointWithdrawalNo());
		if (!StringUtils.isEmpty(searchCondition.getSearchKeyword())) {
			cond.setMemberEmail(searchCondition.getSearchKeyword());
			cond.setMemberName(searchCondition.getSearchKeyword());
		}
		
		ArrayListResponse<PointWithdrawalCommand> res = new ArrayListResponse<PointWithdrawalCommand>();
		ArrayList<PointWithdrawalCommand> resultList = this.pointWithdrawalService.findPointWithdrawalCommands(cond);
		ResponseUtil.setSuccessResponse(res);
		res.setTotal(this.searchService.selectTotalRecords());
		res.setRows(resultList);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointWithdrawal/create", method = RequestMethod.POST)
	public ReturnpBaseResponse  createPointWithdrawal(
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
	public ReturnpBaseResponse  updatePointWithdrawal(
			@ModelAttribute("pointWithdrawalForm") PointWithdrawalCommand pointWithdrawalCommand, 
			SessionStatus sessionStatus, 
			BindingResult result, 
			HttpSession httpSession, 
			Model model) {
		ReturnpBaseResponse res= new ReturnpBaseResponse();
		this.pointWithdrawalService.update(pointWithdrawalCommand);
		
		/*출금 취소 인 경우 포인트 증가*/
		ArrayList<RedPoint> redPoints = null;
		RedPointCommand redPointCommand = new RedPointCommand();
		RedPoint redPoint ;
		if (pointWithdrawalCommand.getWithdrawalStatus().trim().equals("4") || pointWithdrawalCommand.getWithdrawalStatus().trim().equals("5")) {
			redPointCommand.setMemberNo(pointWithdrawalCommand.getMemberNo());;
			redPoints = this.searchService.findRedPoints(redPointCommand);
			redPoint = redPoints.get(0);
			redPoint.setPointAmount(redPoint.getPointAmount() + pointWithdrawalCommand.getWithdrawalAmount());
			this.redPointMapper.updateByPrimaryKey(redPoint);
		}
		sessionStatus.setComplete();
		ResponseUtil.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointWithdrawal/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse  deletePointWithdrawal(
			@RequestParam(value = "pointWithdrawalNo", required = true) int  pointWithdrawalNo) {
		ReturnpBaseResponse res= new ReturnpBaseResponse();
		this.pointWithdrawalService.delete(pointWithdrawalNo);
		ResponseUtil.setSuccessResponse(res);
		return res;
	}
}
