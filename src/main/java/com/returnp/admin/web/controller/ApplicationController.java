package com.returnp.admin.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.handler.ReturnPExceptionHandler;

public class ApplicationController extends ReturnPExceptionHandler {
	public class RequestForward{
		public static final String MAIN_VIEW  = "main/adminMain";
		public static final String CONTENT_VIEW	= "main/handleContent";
		public static final String HANDLE_NODE_FORM_TEMPLATE	= "main/handleNodeForm";
		public static final String HANDLE_MEMBERSHIP_FORM_TEMPLATE = "main/handleMembershipForm";
		public static final String HANDLE_NODE_VIEW_TEMPLATE= "main/handleNodeView";
		public static final String HANDLE_COMMON_VIEW_TEMPLATE= "main/handleCommonView";
		public static final String SIGN_IN_VIEW = "main/signIn";
		public static final String SIGN_UP_VIEW = "main/signUp";
	}
	
	public class RequestRedirect {
		public static final String MAIN_REDIRECT 	= "redirect:/" ;
		public static final String LOGIN_REDIRECT  ="redirect:/signIn" ;
	}
	
	protected void setRespone(ReturnpBaseResponse res, String resultCode, String result,String message ){ 
		res.setResult(result);
		res.setResultCode(resultCode);	
		res.setMessage(message);
	}
	
	protected void setSuccessResponse(ReturnpBaseResponse res){ 
		res.setResult(AppConstants.ResponseResult.SUCCESS);
		res.setResultCode(AppConstants.ResponsResultCode.SUCCESS);	
		res.setMessage("요청이 처리 되었습니다");
	}
	
	protected void setSuccessResponse(ReturnpBaseResponse res, String mes){ 
		res.setResult(AppConstants.ResponseResult.SUCCESS);
		res.setResultCode(AppConstants.ResponsResultCode.SUCCESS);	
		res.setMessage(mes);
	}
	
	protected void setSuccessResponse(ReturnpBaseResponse res, String title, String mes){ 
		res.setResult(AppConstants.ResponseResult.SUCCESS);
		res.setResultCode(AppConstants.ResponsResultCode.SUCCESS);	
		res.setSummary(title);
		res.setMessage(mes);
	}
	
	
	protected void setErrorResponse(ReturnpBaseResponse res, String title, String mes){ 
		res.setResult(AppConstants.ResponseResult.ERROR);
		res.setResultCode(AppConstants.ResponsResultCode.ERROR);	
		res.setSummary(title);
		res.setMessage(mes);
	}

	protected void setErrorResponse(ReturnpBaseResponse res, String mes){ 
		res.setResult(AppConstants.ResponseResult.ERROR);
		res.setResultCode(AppConstants.ResponsResultCode.ERROR);	
		res.setMessage(mes);
	}
	
	protected void setErrorRespone(ReturnpBaseResponse res){ 
		res.setResult(AppConstants.ResponseResult.FAILED);
		res.setResultCode(AppConstants.ResponsResultCode.ERROR);	
		res.setMessage("요청에러");
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseBody
	protected ReturnpBaseResponse typeMismatchExceptionHandler(TypeMismatchException e,
			HttpServletResponse response){
		ObjectResponse<String> res = new ObjectResponse<String>();
		res.setData(e.getMessage());
		this.setErrorRespone(res);
		return res;
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	protected ReturnpBaseResponse missingServletRequestParameterExceptionHandler(
			MissingServletRequestParameterException e, 
			HttpServletResponse response){
		ObjectResponse<String> res = new ObjectResponse<String>();
		res.setData(e.getMessage());
		this.setErrorRespone(res);
		return res;
	}
	
	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	protected  ReturnpBaseResponse dataAccessExceptionHandler(DataAccessException e,
			HttpServletResponse response ) {
		ObjectResponse<String> res = new ObjectResponse<String>();
		res.setData(e.getMessage());
		this.setErrorRespone(res);
		return res;
	 }
}
