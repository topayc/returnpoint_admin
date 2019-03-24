package com.returnp.admin.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
import com.returnp.admin.code.CodeGenerator;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.command.MembershipRequestCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.CompanyBankAccount;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MembershipRequest;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.Recommender;
import com.returnp.admin.service.interfaces.EventService;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.MembershipRequestService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RecommenderService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("membershipRequestFormInfo")
public class MembershipRequestController extends ApplicationController{
	
	@Autowired SearchService searchService;
	@Autowired MembershipRequestService membershipRequestService;
	@Autowired RecommenderService recommenderService;
	@Autowired MemberService memberService;
	@Autowired PointCoversionTransactionService pointTransactionService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired EventService eventService;
	
	@RequestMapping(value = "/membershipRequest/form/createForm", method = RequestMethod.GET)
	public String formMembershipRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "membershipRequestNo", defaultValue = "0") int membershipRequestNo,
			Model model){
		model.addAttribute("registTypes", CodeDefine.getRegistTypes());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatuses());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypest());
		model.addAttribute("bankAccounts", this.searchService.findCompanyBanks(new CompanyBankAccount()));
	
		Policy policyCond = new Policy();
		ArrayList<Policy> policies = this.searchService.findPolicies(policyCond);
		Policy policy  = policies.get(policies.size()-1);
		model.addAttribute("policy", policy);
		
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("membershipRequestFormInfo", this.membershipRequestService.selectByPrimaryKey(membershipRequestNo));
		}
		return "template/form/membership/createMembershipForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/membershipRequest/create", method = RequestMethod.POST)
	public ReturnpBaseResponse createMembershipRequest(MembershipRequest  membershipRequest,BindingResult result, HttpSession httpSession){
		System.out.println("createMembershipRequest");
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		AdminSession adminSession = null;
		if (membershipRequest.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			membershipRequest.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		/* 존재하는 회원의 신청인지 검사 */
		MembershipRequest cond = new MembershipRequest();
		cond.setMemberNo(membershipRequest.getMemberNo());
		MembershipRequest mbr = this.searchService.findMembershipRequest(cond);
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		
		if (mbr !=  null) {
			this.setErrorResponse(res,"[중복 신청] 해당 회원의 정회원 신청내용이 이미 존재합니다");
		}
		else {
			/* 멤버쉽 요청 정보 생성 및 삽입*/
			this.membershipRequestService.insert(membershipRequest);
			
			/* 결제 상태가 입금 완료인 경우 정회원 테이블 등록*/
			if (membershipRequest.getPaymentStatus().equals(AppConstants.PaymentStatus.PAYMENT_OK)) {
				Member member = this.memberService.selectByPrimaryKey(membershipRequest.getMemberNo());
				
				Recommender  rCond = new Recommender();
				cond.setMemberNo(rCond.getMemberNo());
				Recommender insertRecom = this.searchService.findRecommenders(rCond).get(0);
				
				if (insertRecom == null) {
					/* Recommender - 정회원 생성*/
					insertRecom = new Recommender();
					insertRecom.setRecommenderCode(CodeGenerator.generatorRecommenderCode(null));
					insertRecom.setMemberNo(member.getMemberNo());
					insertRecom.setRecommenderEmail(member.getMemberEmail());
					insertRecom.setRecommenderName(member.getMemberName());
					insertRecom.setRecommenderPhone(member.getMemberPhone());
					insertRecom.setRegType(AppConstants.ReigistType.REGIST_BY_ADMIN);
					insertRecom.setRegAdminNo(adminSession.getAdmin().getAdminNo());
					insertRecom.setRecommenderStatus(AppConstants.NodeStatus.NORMAL);
					insertRecom.setGreenPointAccStatus(AppConstants.PointAccStatus.ACC_OK);
					insertRecom.setRedPointAccStatus(AppConstants.PointAccStatus.ACC_OK);
					insertRecom.setGreenPointUseStatus(AppConstants.PointUseStatus.USE_OK);
					insertRecom.setRedPointUseStatus(AppConstants.PointUseStatus.USE_OK);
					this.recommenderService.insert(insertRecom);
					
					/* Green Point 생성, Red point는 회원 가입시 생성되므로 생성할 필요 없음*/
					GreenPoint greenPoint = new GreenPoint();
					greenPoint.setMemberNo(insertRecom.getMemberNo());
					greenPoint.setNodeNo(insertRecom.getRecommenderNo());
					greenPoint.setNodeType(AppConstants.NodeType.RECOMMENDER);
					greenPoint.setPointAmount((float)0);
					greenPoint.setNodeTypeName("branch");
					this.greenPointService.insert(greenPoint);
					
					/* Member Table 업데이트*/
					Member m = new Member();
					m.setMemberNo(insertRecom.getMemberNo());
					m.setIsRecommender("Y");
					this.memberService.updateByPrimaryKeySelective(m);
				}
				
				membershipRequest.setRecommenderNo(insertRecom.getRecommenderNo());
				this.membershipRequestService.updateByPrimaryKey(membershipRequest);
			}
			
			this.setSuccessResponse(res);
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/membershipRequest/update", method = RequestMethod.POST)
	public ReturnpBaseResponse updateMembershipRequest(
			@ModelAttribute("membershipRequestFormInfo") MembershipRequest  membershipRequest,
			SessionStatus sessionStatus,
			BindingResult result, 
			HttpSession httpSession) throws ParseException{
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		
		System.out.println("updateMembershipRequest");
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		if (membershipRequest.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			membershipRequest.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		this.membershipRequestService.updateByPrimaryKey(membershipRequest);
		
		/*멤버쉽 요청의 입금 확인이 되었을 경우 정회원 테이블 등록*/
		if (membershipRequest.getPaymentStatus().equals(AppConstants.PaymentStatus.PAYMENT_OK)) {
			/*
			 * 결제 상태가 입금확인이 되었을 경우, 이를 변경하지 못하게 해야 하나. 관리 문제상 현재 변경이 가능함 
			 * 멤버쉽 상태가 승인 상태라고 하더라고, 해당 정회원이 이미 등록되어 있는지 확인해야 함 
			 * */
			Recommender reCond = new Recommender();
			reCond.setMemberNo(membershipRequest.getMemberNo());
			
			ArrayList<Recommender> recomList = this.searchService.findRecommenders(reCond);
			Recommender recommender = null;
			if (recomList.size() > 1) {
				recommender = this.searchService.findRecommenders(reCond).get(0);
			}
			
			if (recommender == null ) {
				Member member = this.memberService.selectByPrimaryKey(membershipRequest.getMemberNo());
				recommender = new Recommender();
				recommender.setRecommenderCode(CodeGenerator.generatorRecommenderCode(null));
				recommender.setMemberNo(member.getMemberNo());
				recommender.setRecommenderEmail(member.getMemberEmail());
				recommender.setRecommenderName(member.getMemberName());
				recommender.setRecommenderPhone(member.getMemberPhone());
				/* 
				 * 정회원 신청을 사용자가 하였다 하더라도 이를 입금완료로 관리자가 전환하여 등록하는 정회원의 경우
				 * 정회원 등록은 관리자가 등록한 것으로 설정함
				 * */
				recommender.setRegType(AppConstants.ReigistType.REGIST_BY_ADMIN);
				recommender.setRegAdminNo(adminSession.getAdmin().getAdminNo());
				recommender.setRecommenderStatus(AppConstants.NodeStatus.NORMAL);
				recommender.setGreenPointAccStatus(AppConstants.PointAccStatus.ACC_OK);
				recommender.setRedPointAccStatus(AppConstants.PointAccStatus.ACC_OK);
				recommender.setGreenPointUseStatus(AppConstants.PointUseStatus.USE_OK);
				recommender.setRedPointUseStatus(AppConstants.PointUseStatus.USE_OK);
				this.recommenderService.insert(recommender);
				
				membershipRequest.setRecommenderNo(recommender.getRecommenderNo());
				this.membershipRequestService.updateByPrimaryKey(membershipRequest);
				
				/* Member Table 업데이트*/
				Member m = new Member();
				m.setMemberNo(recommender.getMemberNo());
				m.setIsRecommender("Y");
				this.memberService.updateByPrimaryKeySelective(m);
				
			}
			
			/*Recommender Green Point 생성 */
			GreenPoint greenPoint= new GreenPoint();
			greenPoint.setMemberNo(membershipRequest.getMemberNo());
			greenPoint.setNodeType(AppConstants.NodeType.RECOMMENDER);
			
			if (this.searchService.findGreenPoints(greenPoint).size()  < 1 ){
				greenPoint.setNodeNo(recommender.getRecommenderNo());
				greenPoint.setPointAmount((float)0);
				greenPoint.setNodeTypeName("recommender");
				this.greenPointService.insert(greenPoint);
			}
		/*	이벤트 실행 
			this.eventService.joinRecommenderEvent(recommender);*/
		}
		
		sessionStatus.setComplete();
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/membershipRequests", method = RequestMethod.GET)
	public ReturnpBaseResponse getMembershipRequests(SearchCondition searchCondition){
		
		/* 검색어 세팅*/
		MembershipRequestCommand mrCond = new MembershipRequestCommand();
		mrCond.setMemberName(searchCondition.getSearchKeyword());
		mrCond.setMemberEmail(searchCondition.getSearchKeyword());
		
		mrCond.setPaymentStatus(searchCondition.getSearchPaymentStatus());
		mrCond.setPaymentType(searchCondition.getSearchPaymentType());
		if (!StringUtils.isBlank(searchCondition.getSearchKeyword())) {
			mrCond.setMemberEmail(searchCondition.getSearchKeyword());
			mrCond.setMemberName(searchCondition.getSearchKeyword());
		} 
		
		ArrayList<MembershipRequestCommand> datas = this.searchService.findMembershipRequestCommands(mrCond);
		ArrayListResponse<MembershipRequestCommand> res = new ArrayListResponse<MembershipRequestCommand>();
		res.setRows(datas);
		res.setTotal(datas.size());	
		this.setSuccessResponse(res);
		return res;
	}
	@ResponseBody
	@RequestMapping(value = "/membershipRequest/get", method = RequestMethod.GET)
	public ReturnpBaseResponse getMembershipRequest(int membershipRequestNo){
		ObjectResponse<MembershipRequest> res = new ObjectResponse<MembershipRequest>();
		res.setData(this.membershipRequestService.selectByPrimaryKey(membershipRequestNo));
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/membershipRequestCommand/get", method = RequestMethod.GET)
	public ReturnpBaseResponse getMembershipRequestCommand(int membershipRequestNo){
		ObjectResponse<MembershipRequestCommand> res = new ObjectResponse<MembershipRequestCommand>();
		MembershipRequestCommand cond = new MembershipRequestCommand();
		cond.setMembershipRequestNo(membershipRequestNo);
		res.setData(this.searchService.findMembershipRequestCommands(cond).get(0));
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/membershipRequest/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteMembershipRequest( int membershipRequestNo) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.membershipRequestService.deleteByPrimaryKey(membershipRequestNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
}
