package com.returnp.admin.web.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.command.MemberCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.model.Member;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.utils.ReturnpResponseMessageHandler;

@Controller
@RequestMapping("/api")
@SessionAttributes("memberFormInfo")
public class MemberController extends ApplicationController {

	@Autowired MemberService memberService;
	@Autowired SearchService searchService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired PointCoversionTransactionService pointTransactionService;

	@RequestMapping(value = "/member/form/createForm", method = RequestMethod.GET)
	public String formMemberRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "memberNo", defaultValue = "0") int memberNo,
			Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatusList());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypeList());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatusList());
		model.addAttribute("authTypes", CodeDefine.getAuthtypeList());
		String view = null;
		if (action.equals("create")) {
			view = "template/form/node/createMember";
		}else if (action.equals("modify")){
			model.addAttribute("memberFormInfo", this.memberService.selectByPrimaryKey(memberNo));
			view = "template/form/node/createMember";
		}else if (action.equals("memberDetailView")) {
			view = "template/form/node/memberDetailView";
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/member/get", method = RequestMethod.GET)
	public ReturnpBaseResponse  getMember(
			@RequestParam(value = "memberNo", required = true) int  memberNo) {
		Member  mCond =  new Member();
		mCond.setMemberNo(memberNo);	
		
		Member  member= this.memberService.selectByPrimaryKey(memberNo);
		ObjectResponse<Member> res = new ObjectResponse<Member>();
		res.setData(member);
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/member/getMemberCommand", method = RequestMethod.GET)
	public ReturnpBaseResponse  getMemberCommand(
			@RequestParam(value = "memberNo", required = true) int  memberNo) {
		MemberCommand  member= this.memberService.selecMemberCommandtByPrimaryKey(memberNo);
		ObjectResponse<Member> res = new ObjectResponse<Member>();
		res.setData(member);
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/member/create", method = RequestMethod.POST)
	public  ReturnpBaseResponse createMember(
			Member member, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		member.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		return this.memberService.createMember(member);
	}
	
	@ResponseBody
	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public  ReturnpBaseResponse updateMember( 
			@ModelAttribute("memberFormInfo") Member  member,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();

		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
			ReturnpResponseMessageHandler.setRespone(res, "430", "error", "입력된 정보가 완전하지 않습니다");
			return res;
		}
		
		if (member.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			member.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		sessionStatus.setComplete();
		return this.memberService.updateMember(member);
	}
	
	@ResponseBody
	@RequestMapping(value = "/member/checkEmailDuplicated", method = RequestMethod.GET)
	public  ReturnpBaseResponse checkEmailDuplicated( 
			@RequestParam(value = "email", required = true) String  email, HttpSession httpSession, Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		if (this.memberService.isEmailDuplicated(email)) {
			this.setErrorResponse(res,"중복된 이메일입니다. 다시 확인해주세요");
		}else {
			this.setSuccessResponse(res,"사용할 수 있는 이메일입니다");
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/member/checkPhoneDuplicated", method = RequestMethod.GET)
	public  ReturnpBaseResponse checkPhoneDuplicated( 
			@RequestParam(value = "phone", required = true) String  phone, HttpSession httpSession, Model model) {
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		phone = phone.replace("-", "");
		Pattern p = Pattern.compile("	^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$");
		Matcher m = p.matcher(phone);
		if (m.find()) {
			if (this.memberService.isPhoneDuplicated(phone)) {
				ReturnpResponseMessageHandler.setErrorResponse(res,"중복된 핸드폰 번호입니다. 다시 확인해주세요");
			}else {
				ReturnpResponseMessageHandler.setSuccessResponse(res,"유효한 핸드폰 번호입니다");
			}
		}else {
			ReturnpResponseMessageHandler.setErrorResponse(res,"유효하지 않는 모바일 번호입니다. 다시 확인해주세요");
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/member/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteMember( 
			int  memberNo, Model model) {
		return this.memberService.deleteMember(memberNo);
	}
}
