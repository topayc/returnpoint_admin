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

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.command.MemberBankAccountCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.MemberBankAccount;
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
	@RequestMapping(value = "/memberBankAccount/create2", method = RequestMethod.POST)
	public ReturnpBaseResponse  getMemberBankAccount2(
			@RequestParam int memberNo,
			@RequestParam String bankName,
			@RequestParam String  accountOwner,
			@RequestParam String  bankAccount,
			HttpSession httpSession, 
			Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		MemberBankAccount account = new MemberBankAccount();
		
		account.setMemberNo(memberNo);
		account.setAccountOwner(accountOwner);
		account.setBankAccount(bankAccount);
		account.setBankName(bankName);
		account.setIsDefault("N");
		account.setAccountStatus("Y");
		
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		
		if (adminSession.getAuthType() == "1") {
			account.setRegType("A");
			account.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		if (adminSession.getAuthType() == "10") {
			account.setRegType("H");
		}
		
		this.memberBankAccountService.create(account);
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
	@RequestMapping(value = "/memberBankAccount/defaultBankAccount", method = RequestMethod.POST)
	public ReturnpBaseResponse  defaultAccount(
			@RequestParam int memberBankAccountNo,
			@RequestParam int memberNo,
			HttpSession httpSession, 
			Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.memberBankAccountService.defaultBankAccount(memberBankAccountNo,memberNo);
		ResponseUtil.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/memberBankAccount/update2", method = RequestMethod.POST)
	public ReturnpBaseResponse  updateMemberBankAccount2(
			@RequestParam int memberBankAccountNo,
			@RequestParam String bankName,
			@RequestParam String  accountOwner,
			@RequestParam String  bankAccount,
			HttpSession httpSession, 
			Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		MemberBankAccount account = new MemberBankAccount();
		account.setMemberBankAccountNo(memberBankAccountNo);
		account.setAccountOwner(accountOwner);
		account.setBankAccount(bankAccount);
		account.setBankName(bankName);
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		
		if (adminSession.getAuthType() == "1") {
			account.setRegType("A");
			account.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		if (adminSession.getAuthType() == "10") {
			account.setRegType("H");
		}
		
		this.memberBankAccountService.update(account);
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
