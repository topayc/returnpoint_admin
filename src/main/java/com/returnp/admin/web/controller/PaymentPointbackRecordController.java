package com.returnp.admin.web.controller;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.dto.command.PaymentPointbackRecordCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.Branch;
import com.returnp.admin.model.PaymentPointbackRecord;
import com.returnp.admin.service.interfaces.BranchService;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
public class PaymentPointbackRecordController extends ApplicationController{
	
	@Autowired MemberService memberSerivice;
	@Autowired BranchService branchService;
	@Autowired SearchService searchService;;
	@Autowired MemberService memberService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired PointCoversionTransactionService pointTransactionService;
	
	@RequestMapping(value = "/paymentPointbackRecord/template/paymentPointbackRecordList", method = RequestMethod.GET)
	public String formPaymentPointbackRecordRequest( Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatusList());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypeList());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatusList());
		model.addAttribute("authTypes", CodeDefine.getAuthtypeList());
		model.addAttribute("nodeTypeList", CodeDefine.getNodeTypeList());
		return "template/list/paymentPointbackDetailRecordList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/paymentPointbackRecords", method = RequestMethod.GET)
	public ReturnpBaseResponse  getPaymentPonitbackRecord(
			PaymentPointbackRecordCommand record) {
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/paymentPointbackRecords/get", method = RequestMethod.GET)
	public ReturnpBaseResponse  getPaymentPonitbackRecords(
			PaymentPointbackRecordCommand record) {
		ArrayList<PaymentPointbackRecordCommand> list = this.searchService.findPaymentPointbackRecordCommands(record);
		ArrayListResponse<PaymentPointbackRecordCommand> res = new ArrayListResponse<PaymentPointbackRecordCommand>();
		res.setRows(list);
		res.setTotal(list.size());
		this.setSuccessResponse(res);
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/paymentPointbackRecords2/get", method = RequestMethod.GET)
	public ReturnpBaseResponse  getPaymentPonitbackRecords2(
			@RequestParam(value = "searchKeyword" , required = false) String searchKeyword,
			PaymentPointbackRecordCommand record ) {
		if (!StringUtils.isBlank(searchKeyword)) {
			record.setMemberEmail(searchKeyword);
			record.setMemberName(searchKeyword);
		}
		ArrayList<PaymentPointbackRecordCommand> list = this.searchService.findPaymentPointbackRecordCommands(record);
		ArrayListResponse<PaymentPointbackRecordCommand> res = new ArrayListResponse<PaymentPointbackRecordCommand>();
		res.setRows(list);
		res.setTotal(list.size());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/paymentPointbackRecords/find", method = RequestMethod.GET)
	public ReturnpBaseResponse  findPaymentPointbackRecords(
			SearchCondition searchCondition ) {
		
		PaymentPointbackRecordCommand record = new PaymentPointbackRecordCommand();
		if (!StringUtils.isBlank(searchCondition.getSearchKeyword())) {
			record.setMemberEmail(searchCondition.getSearchKeyword());
			record.setMemberName(searchCondition.getSearchKeyword());
			record.setAffiliateName(searchCondition.getSearchKeyword());
		}
		record.valueOf(searchCondition);
		
		ArrayListResponse<PaymentPointbackRecordCommand> res = new ArrayListResponse<PaymentPointbackRecordCommand>();
		ArrayList<PaymentPointbackRecordCommand> list = this.searchService.findPaymentPointbackRecordCommands(record);
		res.setRows(list);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}
}
