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
import com.returnp.admin.code.CodeGenerator;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.model.CompanyBankAccount;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MembershipRequest;
import com.returnp.admin.model.PointConversionTransaction;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.Recommender;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.MembershipRequestService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RecommenderService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("recommenderFormInfo")

public class RecommenderController extends ApplicationController{

	@Autowired RecommenderService recommenderService;
	@Autowired SearchService searchService;
	@Autowired MembershipRequestService membershipRequestService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired MemberService memberService;
	@Autowired PointCoversionTransactionService pointTransactionService;
	
	@RequestMapping(value = "/recommender/form/createForm", method = RequestMethod.GET)
	public String formMembershipRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "recommenderNo", defaultValue = "0") int recommenderNo,
			Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistTypes());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatuses());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypest());
		model.addAttribute("bankAccounts", this.searchService.findCompanyBanks(new CompanyBankAccount()));
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatuses());
	
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("recommenderFormInfo", this.recommenderService.selectByPrimaryKey(recommenderNo));
		}
		return "template/form/node/createRecommender";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/recommender/get", method = RequestMethod.GET)
	public ReturnpBaseResponse  getRecommender(
			@RequestParam(value = "recommenderNo", required = true) int  recommenderNo) {
		Recommender reCond =  new Recommender();
		reCond.setRecommenderNo(recommenderNo);	
		
		Recommender  recommender = this.searchService.findRecommenders(reCond).get(0);
		ObjectResponse<Recommender> res = new ObjectResponse<Recommender>();
		res.setData(recommender);
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommender/create", method = RequestMethod.POST)
	public  ReturnpBaseResponse createRecommender(
			Recommender recommender, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		ReturnpBaseResponse res = null;;
		AdminSession adminSession = null;
		if (recommender.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			recommender.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		return this.recommenderService.createRecommender(recommender);
	}
	
	/* 아래 버젼은 멤버쉽 정보도 한꺼번에 받아 처리하며, 이 메소드는 사용 안함 (개별로 나눌 것이기 때문*/
/*	@ResponseBody
	@RequestMapping(value = "/recommender/create", method = RequestMethod.POST)
	public  BaseResponse createRecommender(Recommender recommenderCommand, BindingResult result, HttpSession httpSession, Model model) {
		
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (recommenderCommand.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			recommenderCommand.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		Recommender  cond = new Recommender();
		cond.setMemberNo(recommenderCommand.getMemberNo());
		
		BaseResponse res ;
		
		ArrayList<RecommenderCommand> relist = this.searchService.findRecommenderCommands(cond);
		if (relist.size() > 0) {
			res = new BaseResponse();
			this.setErrorRespone(res, "이미 추천인으로 등록된 사용자 입니다. 다시 확인해 주세요");
		}else {
			Recommender  recommender = new Recommender();
			ReflectionManager.reflectionCopy(recommenderCommand, recommender);
			
			recommender.setRecommenderStatus(AppConstants.NodeStatus.NORMAL);
			recommender.setGreenPointAccStatus(AppConstants.PointAccStatus.ACC_OK);
			recommender.setRedPointAccStatus(AppConstants.PointAccStatus.ACC_OK);
			recommender.setGreenPointUseStatus(AppConstants.PointUseStatus.USE_OK);
			recommender.setGreenPointUseStatus(AppConstants.PointUseStatus.USE_OK);
			
			this.recommenderService.insert(recommender);
			
			MembershipRequest membershipRequest = new MembershipRequest();
			ReflectionManager.reflectionCopy(recommenderCommand, membershipRequest);
			membershipRequest.setRecommenderNo(recommender.getRecommenderNo());
			
			this.membershipRequestService.insert(membershipRequest);
			res = new BaseResponse();
			this.setSuccessRespone(res, "생성 완료");
		}
		return res;
	}*/
	
	
	@ResponseBody
	@RequestMapping(value = "/recommender/update", method = RequestMethod.POST)
	public  ReturnpBaseResponse updateRecommender( 
			@ModelAttribute("recommenderFormInfo") Recommender  recommeder,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (recommeder.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			recommeder.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		ReturnpBaseResponse res = this.recommenderService.updateRecommender(recommeder);
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommender/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteRecommender( 
			int  recommenderNo, Model model) {
		return this.recommenderService.deleteRecommender(recommenderNo);
	}
}
