package com.returnp.admin.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PointCodeIssue;
import com.returnp.admin.model.PointCodeIssueRequest;
import com.returnp.admin.service.interfaces.PointCodeService;

@Controller
@RequestMapping("/api")
@SessionAttributes("giftCardFormInfo")
public class PointCodeController extends ApplicationController{
	
	@Autowired PointCodeService  pointCodeService;
	
	// --------------------------------------------------------------------------------------------------------------------
	// 포인트 코드발급 요청 컨트롤러 메서드 
	// --------------------------------------------------------------------------------------------------------------------

	@ResponseBody
	@RequestMapping(value = "/pointCodeIssueRequest/reports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPointCodeIssueRequestReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCodeService.selectPointCodeIssueRequestReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCodeIssueRequest/periodReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodPointCodeIssueRequestReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCodeService.selectPeriodPointCodeIssueRequestReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCodeIssueRequest/get", method = RequestMethod.POST)
	public ReturnpBaseResponse  loadPointCodeIssueRequests(@RequestParam HashMap<String, Object> params) {
		return this.pointCodeService.loadPointCodeIssueRequests(params);
	}

	@ResponseBody
	@RequestMapping(value = "/pointCodeIssueRequest/issuePointCode", method = RequestMethod.POST)
	public ReturnpBaseResponse  issuePointCodeIssueRequest(PointCodeIssue pointCodeIssue) {
		return this.pointCodeService.issuePointCode(pointCodeIssue);
	}

	
	@ResponseBody
	@RequestMapping(value = "/pointCodeIssueRequest/change", method = RequestMethod.GET)
	public ReturnpBaseResponse changePointCodeIssueRequestStatus(PointCodeIssueRequest pointCodeIssueRequest, HttpSession httpSession){
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		ReturnpBaseResponse res = null;
		if (adminSession == null) {
			res = new ReturnpBaseResponse();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "1098", "관리자 세션이 없습니다. 관리자 로그인을 해주세요");
			return res;
		}else {
			return this.pointCodeService.chanagePointCodeRequestStatus(pointCodeIssueRequest);
		}
	}
	
	
	// --------------------------------------------------------------------------------------------------------------------
	// 포인트 코드 컨트롤러 메서드 
	// --------------------------------------------------------------------------------------------------------------------
	
	@ResponseBody
	@RequestMapping(value = "/pointCodeIssue/reports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPointCodeReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCodeService.selectPointCodeReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCodeIssue/periodReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodPointCodeReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCodeService.selectPeriodPointCodeIssueReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCodeIssue/get", method = RequestMethod.POST)
	public ReturnpBaseResponse  loadPointCodeIssues(@RequestParam HashMap<String, Object> params) {
		return this.pointCodeService.loadPointCodeIssues(params);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCodeIssue/change", method = RequestMethod.GET)
	public ReturnpBaseResponse changePointCodeIssueStatus(PointCodeIssue pointCodeIssue, HttpSession httpSession){
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		ReturnpBaseResponse res = null;
		if (adminSession == null) {
			res = new ReturnpBaseResponse();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "1098", "관리자 세션이 없습니다. 관리자 로그인을 해주세요");
			return res;
		}else {
			return this.pointCodeService.chanagePointCodeIssueStatus(pointCodeIssue);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------
	// 포인트 코드 적립 트랜잭션 컨트롤러 메서드 
	// --------------------------------------------------------------------------------------------------------------------
	
	@ResponseBody
	@RequestMapping(value = "/pointCodeTransaction/reports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPointCodeTransactionReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCodeService.selectPointCodeTransactionReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCodeTransaction/periodReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodPointCodeTransactionReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCodeService.selectPeriodPointCodeTransactionReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCodeTransaction/get", method = RequestMethod.POST)
	public ReturnpBaseResponse  loadPointCodeTransactions(@RequestParam HashMap<String, Object> params) {
		return this.pointCodeService.loadPointCodeTransactions(params);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCoupon/pointCodePointbackRecords", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPointCodePointbackRecords(@RequestParam HashMap<String, Object> param) {
		this.checkParameter(param);
		return this.pointCodeService.selectPointCodePointbackRecords(param);
	}
	
	private HashMap<String, Object> checkParameter(HashMap<String, Object> params){
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key   = entry.getKey();
			String  value =  (String)entry.getValue();
			if (!key.equals("offset") && !key.equals("page") && !key.equals("pageSize") && !key.equals("pagination") && !key.equals("total")) {
				if (value.equals("") || value.equals("0")) {
					params.put(key, null);
				}
			}
		}
		return params;
	}
	

	
}
