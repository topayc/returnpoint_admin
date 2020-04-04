package com.returnp.admin.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import com.returnp.admin.dto.QueryCondition;
import com.returnp.admin.dto.command.MemberCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
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

		model.addAttribute("registTypes", CodeDefine.getRegistTypes());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatuses());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypest());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatuses());
		model.addAttribute("authTypes", CodeDefine.getAuthTypes());
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
	
	@RequestMapping(value = "/member/template/memberList", method = RequestMethod.GET)
	public String listTemplate(){
		return "template/list/memberList";
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
	@RequestMapping(value = "/member/myMembers", method = RequestMethod.GET)
	public ReturnpBaseResponse  findMyMemebr(
			@RequestParam(value = "memberNo", required = true) int  memberNo) {
		MemberCommand  mCond =  new MemberCommand();
		mCond.setMemberNo(memberNo);	
		
		ArrayListResponse<MemberCommand> slr = new ArrayListResponse<MemberCommand>();
		ArrayList<MemberCommand> myMembers = this.searchService.findMyMemberCommands((mCond));
		this.setSuccessResponse(slr);
		slr.setRows(myMembers);
		slr.setTotal(myMembers.size());
		return slr;
	}
	
	@ResponseBody
	@RequestMapping(value = "/member/myTotalPointInfo", method = RequestMethod.GET)
	public ReturnpBaseResponse  getMyTotalPointInfo( @RequestParam(value = "searchKeyword", required = false, defaultValue =  "0" ) String  searchKeyword) {
		MemberCommand  mCond =  new MemberCommand();
		if(!"0".equals(searchKeyword)) {
			mCond.setMemberEmail(searchKeyword);
			mCond.setMemberName(searchKeyword);
		}	
		ArrayList<HashMap<String, Object>> datas = this.searchService.selectMyTotalPointInfo(mCond);
		
		ArrayList<HashMap<String, Object>> resMap = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> m = null;
		
		for (HashMap<String, Object> dataMap : datas) {
			boolean iscon = false;
			int memberSeq = (int)dataMap.get("memberNo");
			if (resMap.size()> 0) {
				m  = this.isMapContainsKey(resMap, memberSeq);
			}
			if (m == null) {
				m = new HashMap<String, Object>();
				m.put("memberNo", memberSeq);
				m.put("soleDistGPoint", -1);
				m.put("branchGPoint", -1);
				m.put("agencyGPoint", -1);
				m.put("affiliateGPoint", -1);
				m.put("memberGPoint", -1);
				m.put("memberRPoint", -1);
				iscon = false;
			}else {
				iscon = true;
			}
			
			if (!iscon) {
				resMap.add(m);
			}
			String pointTarget = (String)dataMap.get("pointTarget");
			switch(pointTarget ) {
				case "soledist":
					m.put("soleDistGPoint", (float)dataMap.get("pointAmount"));
					break;
				case "branch":
					m.put("branchGPoint", (float)dataMap.get("pointAmount"));
					break;
				case "agency":
					m.put("agencyGPoint", (float)dataMap.get("pointAmount"));
					break;
				case "affiliate":
					m.put("affiliateGPoint", (float)dataMap.get("pointAmount"));
					break;
				case "member":
					m.put("memberGPoint", (float)dataMap.get("pointAmount"));
					break;
				case "r_member":
					m.put("memberRPoint", (float)dataMap.get("pointAmount"));
					break;
			}
			m.put("memberName", (String)dataMap.get("memberName"));
			m.put("memberEmail", (String)dataMap.get("memberEmail"));
			m.put("memberPhone", (String)dataMap.get("memberPhone"));
			
		}
		ArrayListResponse<HashMap<String, Object>> slr = new ArrayListResponse<HashMap<String, Object>>();
		slr.setRows(resMap);
		slr.setTotal(resMap.size());
		this.setSuccessResponse(slr);
		return slr;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private HashMap<String, Object> isMapContainsKey(ArrayList<HashMap<String, Object>> listMap,  int membeNo) {
		HashMap<String, Object> returnMap = null;
		for (HashMap<String, Object> m : listMap) {
			int value = (int)m.get("memberNo");
			if (value == membeNo) {
				returnMap = m;
				break;
			}
		}
		return returnMap;
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
