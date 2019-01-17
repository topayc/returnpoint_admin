package com.returnp.admin.handler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
 
 
public class ReturnPExceptionHandler {
 
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse exceptionHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            Exception exception) {
    	
    	try{
    		System.out.println(exception.getClass().getName());
    		exception.printStackTrace();
    	}catch(NullPointerException ex) {
    		SingleDataObjectResponse<String> res = new SingleDataObjectResponse<String>();
    		res.setResult(AppConstants.ResponseResult.ERROR);
    		res.setResultCode(AppConstants.ResponsResultCode.ERROR);	
    		res.setSummary("unknown error!!!");
    		res.setMessage("An unknown error has occurred.");
    		return res;
    	}
 
        String contentType = request.getHeader("Content-Type");
        String reason= HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();;
        int statusCode= HttpStatus.INTERNAL_SERVER_ERROR.value();
 
        // Content-Type 확인, json 만 View를 따로 처리함.
        if(contentType!=null && MediaType.APPLICATION_JSON_VALUE.equals(contentType)){
            ResponseStatus annotation = exception.getClass().getAnnotation(ResponseStatus.class);
            System.out.println("annotation : " +annotation);
            if(annotation!=null){
                reason = annotation.reason();
                statusCode = annotation.value().value();
            }
        } else {
        	System.out.println("request : " + request);
        	System.out.println("response : " + response);
        }
        response.setStatus(statusCode);        
        System.out.println("reason : " + reason);
        System.out.println("statusCode: " + statusCode);
        
        SingleDataObjectResponse<String> res = new SingleDataObjectResponse<String>();
        res.setResult(AppConstants.ResponseResult.ERROR);
		res.setResultCode(AppConstants.ResponsResultCode.ERROR);	
		res.setSummary(exception.getClass().getName());
		res.setMessage(exception.getMessage());
		return res;
	}
}