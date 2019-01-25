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
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.command.GreenPointCommand;
import com.returnp.admin.dto.command.PointConvertRequestCommand;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.PointConversionTransaction;
import com.returnp.admin.model.PointConvertRequest;
import com.returnp.admin.model.Policy;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.PointConvertRequestSerivce;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.PolicyService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("pointConvertFormInfo")
public class PointConvertRequestController extends ApplicationController {
	@Autowired SearchService searchService;
	@Autowired PointConvertRequestSerivce pcrService;
	@Autowired PolicyService policyService;
	@Autowired GreenPointService greenPointService;
	@Autowired PointCoversionTransactionService pointTransactionService;
	
	@RequestMapping(value = "/pointConvertRequest/form/createForm", method = RequestMethod.GET)
	public String formConvertPointRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "pointConvertRequestNo", defaultValue = "0") int pointConvertRequestNo,
			@RequestParam(value = "memberNo", required = false, defaultValue = "0") int memberNo,
			@RequestParam(value = "nodeType", required = false, defaultValue = "0") String nodeType,
			Model model){
		model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatusList());
		model.addAttribute("vanPaymentStatuses", CodeDefine.getVanPaymentStatusList());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypeList());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatusList());
		model.addAttribute("authTypes", CodeDefine.getAuthtypeList());
	    model.addAttribute("action", action);
	    model.addAttribute("pointConvertRequestNo", pointConvertRequestNo);
	    
		GreenPointCommand gpcCond= new GreenPointCommand();
		
		Policy cond = new Policy();
		ArrayList<Policy> policies = this.searchService.findPolicies(cond);
		model.addAttribute("policy", policies.get(policies.size()-1));
		
		if (action.equals("create")) {
			gpcCond.setMemberNo(memberNo);
			model.addAttribute("greenPoints" , this.searchService.findGreenPointCommands(gpcCond) );
		}else if (action.equals("modify")){
			gpcCond.setMemberNo(memberNo);
			gpcCond.setNodeType(nodeType);
			model.addAttribute("greenPoint", this.searchService.findGreenPointCommands(gpcCond).get(0));
			model.addAttribute("pointConvertFormInfo", this.pcrService.selectByPrimaryKey(pointConvertRequestNo));
		}
		return "template/form/node/createPointConvertRequest";
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointConvertRequest/get", method = RequestMethod.GET)
	public BaseResponse  getPointConvertRequest(
			@RequestParam(value = "pointConvertRequestNo", required = true) int  pointConvertRequestNo,
			@RequestParam(value = "memberNo", required = false, defaultValue = "0") int  memberNo,
			@RequestParam(value = "nodeType", required = false, defaultValue = "0") int  nodeType) {
		
		PointConvertRequestCommand cond = new PointConvertRequestCommand();
		cond.setPointConvertRequestNo(pointConvertRequestNo);
		cond.setMemberNo(memberNo);
		
		PointConvertRequestCommand  pcrData = this.searchService.findPointConvertRequestCommands(cond).get(0);
		SingleDataObjectResponse<PointConvertRequestCommand> res = new SingleDataObjectResponse<PointConvertRequestCommand>();
		res.setData(pcrData);
		this.setSuccessResponse(res);
		return res;
	}

	
	@ResponseBody
	@RequestMapping(value = "/pointConvertRequests", method = RequestMethod.GET)
	public  BaseResponse getPointConvertRequests(SearchCondition searchCondition, HttpSession httpSession, Model model) {
		PointConvertRequestCommand cond = new PointConvertRequestCommand();
		if (searchCondition.getSearchKeyword()!= null &&  !searchCondition.getSearchKeyword().trim().equals("")) {
			cond.setMemberEmail(searchCondition.getSearchKeyword());
			cond.setMemberName(searchCondition.getSearchKeyword());
		}
		
		ArrayListResponse<PointConvertRequestCommand> res = new ArrayListResponse<PointConvertRequestCommand>();
		ArrayList<PointConvertRequestCommand> list = this.searchService.findPointConvertRequestCommands(cond);
		res.setRows(list);
		res.setTotal(list.size());	
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointConvertRequest/create", method = RequestMethod.GET)
	public  BaseResponse createPointConvertRequest(
			PointConvertRequest pointCovertRequest, HttpSession httpSession, Model model) {
		if (pointCovertRequest.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			pointCovertRequest.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		GreenPoint greenPointCond = new GreenPoint();
		greenPointCond.setMemberNo(pointCovertRequest.getMemberNo());
		greenPointCond.setNodeType(pointCovertRequest.getNodeType());
		
		GreenPoint greenPoint = this.searchService.findGreenPoints(greenPointCond).get(0);
		greenPoint.setPointAmount(greenPoint.getPointAmount() - pointCovertRequest.getConvertPointAmount());
		this.greenPointService.updateByPrimaryKey(greenPoint);

		this.pcrService.insert(pointCovertRequest);
		
		Policy cond = new Policy();
		ArrayList<Policy> policies = this.searchService.findPolicies(cond);
		Policy policy  = policies.get(policies.size()-1);
		
		/*PointConversionTrasnsaction table 레코드 생성*/
		PointConversionTransaction transaction = new PointConversionTransaction();
		//transaction.setPointConvertRequestNo(pointCovertRequest.getPointConvertRequestNo());
		transaction.setMemberNo(pointCovertRequest.getMemberNo());
		transaction.setNodeType(pointCovertRequest.getNodeType());
		transaction.setConversionTotalPoint(pointCovertRequest.getConvertPointAmount());
		transaction.setConversionAccPoint(0.0f);
		transaction.setConversionAccRate(0.0f);
		//transaction.setPointTransRate(policy.getRedPointAccRate());
		transaction.setConversionStatus(AppConstants.ConversionStatus.PROGRESS);
		
		this.pointTransactionService.insert(transaction);
		
		/*
		 * 응답 데이타 생성
		 * */
		SingleDataObjectResponse<GreenPoint> res = new SingleDataObjectResponse<GreenPoint>();
		res.setData(greenPoint);
		this.setSuccessResponse(
				res, pointCovertRequest.getRequestNodeTypeName() + " 의  포인트 전환 신청이 처리되었습니다");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointConvertRequest/update", method = RequestMethod.POST)
	public  BaseResponse updatePointConvertRequest( 
			@ModelAttribute("pointConvertFormInfo") PointConvertRequest pointConvertRequest,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		System.out.println("updatePointConvertRequest 호출됨");
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (pointConvertRequest.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			pointConvertRequest.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		BaseResponse res = new BaseResponse();
		this.pcrService.updateByPrimaryKey(pointConvertRequest);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointConvertRequest/delete", method = RequestMethod.POST)
	public  BaseResponse deletePointConvertRequest( 
			int  pointConvertRequestNo, Model model) {
		System.out.println("deletePointConvertRequest/delete");
		System.out.println(pointConvertRequestNo);
		BaseResponse res = new BaseResponse();
		PointConvertRequest pc = new PointConvertRequest();
		pc.setPointConvertRequestNo(pointConvertRequestNo);
		this.pcrService.deleteByPrimaryKey(pointConvertRequestNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
}
