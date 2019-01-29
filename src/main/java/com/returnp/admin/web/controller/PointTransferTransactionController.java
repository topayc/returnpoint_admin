package com.returnp.admin.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.dto.command.PointTransferTransactionCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.model.PointTransferTransaction;
import com.returnp.admin.model.Policy;
import com.returnp.admin.service.interfaces.PointTransferTransactionServcie;
import com.returnp.admin.service.interfaces.PolicyService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("pointTransferTransactionFormInfo")
public class PointTransferTransactionController extends ApplicationController {
	
	@Autowired SearchService searchService;
	@Autowired PolicyService policyService;
	@Autowired PointTransferTransactionServcie pointTrnasferTransactionService;
	
	@RequestMapping(value = "/pointTransferTransaction/form/createForm", method = RequestMethod.GET)
	public String formPointTransferTransaction(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "pointTransferTransactionNo", defaultValue = "0") int pointTransferTransactionNo,
			Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatusList());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypeList());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatusList());
		model.addAttribute("authTypes", CodeDefine.getAuthtypeList());
		model.addAttribute("pointTypes", CodeDefine.getPointTypes());
		model.addAttribute("pointTransferTypeList", CodeDefine.getPointTransferTypeList());
		model.addAttribute("pointTransferStatusList", CodeDefine.getPointointTransferStatusList());
		model.addAttribute("registTypes", CodeDefine.getRegistType());

		Policy cond = new Policy();
		ArrayList<Policy> policies = this.searchService.findPolicies(cond);
		model.addAttribute("policy", policies.get(policies.size()-1));
		
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("pointTransferTransactionFormInfo", null);
		}
		return "template/form/node/createPointTransferTransaction";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/pointTransferTransactions/find", method = RequestMethod.GET)
	public ReturnpBaseResponse  findPointTransferTransactions( PointTransferTransactionCommand commnd) {
		ArrayList<PointTransferTransactionCommand> list = this.searchService.findPointTransferTransactionCommands(commnd);
		ArrayListResponse<PointTransferTransactionCommand> res = new ArrayListResponse<PointTransferTransactionCommand>();
		res.setRows(list);
		res.setTotal(list.size());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointTransferTransaction/create", method = RequestMethod.POST)
	public ReturnpBaseResponse  createPointTransferTransaction( PointTransferTransaction pointTransferTransaction) {
		return this.pointTrnasferTransactionService.createPointTransferTransaction(pointTransferTransaction);
	}
}
