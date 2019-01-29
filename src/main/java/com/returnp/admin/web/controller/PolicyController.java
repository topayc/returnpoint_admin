package com.returnp.admin.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.model.Policy;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.service.interfaces.PolicyService;

@Controller
@RequestMapping("/api")

public class PolicyController extends ApplicationController {

	@Autowired PolicyService policyService;
	@Autowired SearchService searchService;
	
	@ResponseBody
	@RequestMapping(value = "/policy", method = RequestMethod.GET)
	public  ReturnpBaseResponse getPolicy(Policy policy, HttpSession httpSession, Model model) {
		Policy cond = new Policy();
		
		ArrayList<Policy> policies = this.searchService.findPolicies(cond);
		ArrayList<Policy> data = new ArrayList<Policy>();
		data.add(policies.get(policies.size()-1));
		
		ArrayListResponse<Policy>  res = new  ArrayListResponse<Policy>();
		res.setResultCode(AppConstants.ResponsResultCode.SUCCESS);
		res.setResult(AppConstants.ResponseResult.SUCCESS);
		res.setRows(data);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/policy/create", method = RequestMethod.POST)
	public  ReturnpBaseResponse createPolicy( Policy policy, HttpSession httpSession, Model model) {
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		policy.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		
		this.policyService.insert(policy);
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		res.setResultCode(AppConstants.ResponsResultCode.SUCCESS);
		res.setResult(AppConstants.ResponseResult.SUCCESS);
		res.setMessage("정책이 잘 적용되었습니다.");
		return res;
	}
}
