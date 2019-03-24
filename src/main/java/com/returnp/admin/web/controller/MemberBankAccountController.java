package com.returnp.admin.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.axis.utils.StringUtils;
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

import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dto.command.MemberBankAccountCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.service.interfaces.MemberBankAccountService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("memberBankAccountForm")
public class MemberBankAccountController extends ApplicationController {

	@Autowired SearchService searchService;
	@Autowired MemberBankAccountService memberBankAccountService;
	
	@RequestMapping(value = "/memberBankAccount/form/createForm", method = RequestMethod.GET)
	public String form(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "memberBankAccountNo", defaultValue = "0") int memberBankAccountNo,
			Model model){
	    MemberBankAccountCommand account = new MemberBankAccountCommand();
		ArrayList<MemberBankAccountCommand> commands;
	    
		if (action.equals("create")) {
			model.addAttribute("memberBankAccountForm",account);
		}else if (action.equals("modify")){
			account.setMemberBankAccountNo(memberBankAccountNo);
			commands = this.memberBankAccountService.findMemberBankAccountCommands(account);
			if (commands.size() == 1 ) {
				model.addAttribute("memberBankAccountForm", this.memberBankAccountService.findMemberBankAccountCommands(account).get(0));
			}
		}
		
		model.addAttribute("registTypes", CodeDefine.getRegistTypes());
		model.addAttribute("memberBankAccountStatusList", CodeDefine.getMemberBankAccountStatuses());
		return "template/form/memberBankAccountForm";
	}

	@ResponseBody
	@RequestMapping(value = "/memberBankAccounts", method = RequestMethod.GET)
	public ReturnpBaseResponse  findMemberBankAccount( SearchCondition nodeSearch) {
		MemberBankAccountCommand  mbac = new MemberBankAccountCommand();
		
		if (nodeSearch.getMemberNo() != null){
			mbac.setMemberNo(nodeSearch.getMemberNo());
		}
		
		if (StringUtils.isEmpty(nodeSearch.getSearchKeyword())) {
			nodeSearch.setSearchKeyword(null);
		}
		mbac.setMemberBankAccountNo(nodeSearch.getMemberBankAccountNo());
		mbac.setMemberEmail(nodeSearch.getSearchKeyword());
		mbac.setMemberName(nodeSearch.getSearchKeyword());
		mbac.valueOf(nodeSearch);
		
		ArrayListResponse<MemberBankAccountCommand> res= new ArrayListResponse<MemberBankAccountCommand>();
		ArrayList<MemberBankAccountCommand> accountCommands = this.searchService.findMemberBankAccountCommands(mbac);
		res.setTotal(this.searchService.selectTotalRecords());
		res.setRows(accountCommands);
		ResponseUtil.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/memberBankAccount/create", method = RequestMethod.POST)
	public ReturnpBaseResponse  getMemberBankAccount(
			@ModelAttribute("memberBankAccountForm") MemberBankAccountCommand memberBankAccountCommand, 
			BindingResult result, 
			SessionStatus sessionStatus,
			HttpSession httpSession, 
			Model model) {
		ReturnpBaseResponse res= new ReturnpBaseResponse();
		this.memberBankAccountService.create(memberBankAccountCommand);
		sessionStatus.setComplete();
		ResponseUtil.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/memberBankAccount/update", method = RequestMethod.POST)
	public ReturnpBaseResponse  updateMemberBankAccount(
			@ModelAttribute("memberBankAccountForm") MemberBankAccountCommand memberBankAccountCommand,
			SessionStatus sessionStatus, 
			BindingResult result, 
			HttpSession httpSession, 
			Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.memberBankAccountService.update(memberBankAccountCommand);
		sessionStatus.setComplete();
		ResponseUtil.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/memberBankAccount/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse  deleteMemberBankAccount(
			@RequestParam(value = "memberBankAccountNo", required = true) int  memberBankAccountNo) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.memberBankAccountService.delete(memberBankAccountNo);
		ResponseUtil.setSuccessResponse(res);
		return res;
	}
}
