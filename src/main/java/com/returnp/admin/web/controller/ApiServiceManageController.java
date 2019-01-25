package com.returnp.admin.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.ApiService;
import com.returnp.admin.service.interfaces.ApiServiceManageService;
import com.returnp.admin.utils.ApiServiceInfo;
import com.returnp.admin.utils.Common;

@Controller
@RequestMapping("/api")
@ApiServiceInfo("API 서비스")
@SessionAttributes("apiServiceFormInfo")
public class ApiServiceManageController extends ApplicationController{	
	
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired 
	private ApiServiceManageService apiServiceService;
	
	@RequestMapping(value = "/apiService/form/createForm", method = RequestMethod.GET)
	public String formApiServiceRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "apiServiceNo", defaultValue = "0") int apiServiceNo,
			Model model){
		model.addAttribute("apiServiceStatuses", CodeDefine.getApiServiceStatusList());
		model.addAttribute("apiServiceTypes", CodeDefine.geApiServiceTypes());
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("apiServiceFormInfo", this.apiServiceService.selectByPrimaryKey(apiServiceNo));
		}
		return "template/form/createApiService";
	}
	
	@ResponseBody
	@RequestMapping(value = "/apiServices", method = RequestMethod.GET)
	public  BaseResponse getApiServices( 
			SearchCondition searchQuery,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model)throws Exception {
		ApiService  apiService = new ApiService();	
		ArrayList<ApiService> apiServiceList = apiServiceService.findApiServices(apiService);
		ArrayListResponse<ApiService> res = new ArrayListResponse<ApiService>();
		res.setRows(apiServiceList);
		res.setTotal(apiServiceList.size());	
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/apiService/get", method = RequestMethod.GET)
	public BaseResponse  getApiServicer(
			@RequestParam(value = "apiServiceNo", required = true) int  apiServiceNo) {
		ApiService apiService= this.apiServiceService.selectByPrimaryKey(apiServiceNo);
		SingleDataObjectResponse<ApiService> res = new SingleDataObjectResponse<ApiService>();
		res.setData(apiService);
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/apiService/create", method = RequestMethod.POST)
	public  BaseResponse createApiService(
			ApiService apiService, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}	
		
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		apiService.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		this.apiServiceService.insert(apiService);
		BaseResponse res = new BaseResponse();
		this.setSuccessResponse(res,"생성 완료");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/apiService/update", method = RequestMethod.POST)
	public  BaseResponse updateApiServicer( 
			@ModelAttribute("apiServiceFormInfo") ApiService apiService,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		apiService.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		BaseResponse res = new BaseResponse();
		this.apiServiceService.updateByPrimaryKey(apiService);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/apiService/delete", method = RequestMethod.POST)
	public  BaseResponse deleteApiService( int  apiServiceNo, Model model) {
		BaseResponse res = new BaseResponse();
		this.apiServiceService.deleteByPrimaryKey(apiServiceNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/apiService/list", method = {RequestMethod.GET,RequestMethod.POST})
	public  BaseResponse ApiServiceList(
			ApiService apiService, BindingResult result, HttpSession httpSession, Model model) {
		ArrayList<ApiService> apiServiceList = Common.getApiServiceList(applicationContext);
		ArrayListResponse<ApiService> res = new ArrayListResponse<ApiService>();
		res.setRows(apiServiceList);
		res.setTotal(apiServiceList.size());	
		res.setResultCode("100");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/apiService/makeKey", method = RequestMethod.POST)
	public  BaseResponse makeKey( @RequestParam(value = "apiService", required = true) String  apiService){
		SingleDataObjectResponse<String> res = new SingleDataObjectResponse<String>();
		try {
			/*키발급 샘플*/
			res.setData(CodeGenerator.createApiToken(apiService));	
			/*키검증 샘플*/
			//res.setData(String.valueOf(Common.verifyApiServiceToken("/affiliate/get","UyH.Kx.wL0CvKBOtWUXkYUvnWVPjJ0bjb.")));
			
			res.setMessage("API키가 성공적으로 발급되었습니다.");
			res.setResultCode("100");
			return res;
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/apiService/makeRfId", method = RequestMethod.POST)
	public  BaseResponse makeRfId( @RequestParam(value = "apiService", required = true) String  apiService){
		SingleDataObjectResponse<String> res = new SingleDataObjectResponse<String>();
		try {
			/*키발급 샘플*/
			res.setData(CodeGenerator.generatorRfId(apiService));	
			/*키검증 샘플*/
			//res.setData(String.valueOf(Common.verifyApiServiceToken("/affiliate/get","UyH.Kx.wL0CvKBOtWUXkYUvnWVPjJ0bjb.")));
			
			res.setMessage("RF ID 가  성공적으로 발급되었습니다.");
			res.setResultCode("100");
			return res;
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
}
