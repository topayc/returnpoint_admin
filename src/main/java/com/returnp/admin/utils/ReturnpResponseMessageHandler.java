package com.returnp.admin.utils;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.reponse.BaseResponse;

public class ReturnpResponseMessageHandler {
	public static void setRespone(BaseResponse res, String resultCode, String result,String message ){ 
		res.setResult(result);
		res.setResultCode(resultCode);	
		res.setMessage(message);
	}
	
	public static void setSuccessResponse(BaseResponse res){ 
		res.setResult(AppConstants.ResponseResult.SUCCESS);
		res.setResultCode(AppConstants.ResponsResultCode.SUCCESS);	
		res.setMessage("요청이 처리 되었습니다");
	}
	
	public static void setSuccessResponse(BaseResponse res, String mes){ 
		res.setResult(AppConstants.ResponseResult.SUCCESS);
		res.setResultCode(AppConstants.ResponsResultCode.SUCCESS);	
		res.setMessage(mes);
	}
	
	public static void setSuccessResponse(BaseResponse res, String title, String mes){ 
		res.setResult(AppConstants.ResponseResult.SUCCESS);
		res.setResultCode(AppConstants.ResponsResultCode.SUCCESS);	
		res.setSummary(title);
		res.setMessage(mes);
	}
	
	
	public static void setErrorResponse(BaseResponse res, String title, String mes){ 
		res.setResult(AppConstants.ResponseResult.ERROR);
		res.setResultCode(AppConstants.ResponsResultCode.ERROR);	
		res.setSummary(title);
		res.setMessage(mes);
	}

	public static void setErrorResponse(BaseResponse res, String mes){ 
		res.setResult(AppConstants.ResponseResult.ERROR);
		res.setResultCode(AppConstants.ResponsResultCode.ERROR);	
		res.setMessage(mes);
	}
	
	public static void setErrorResponse(BaseResponse res){ 
		res.setResult(AppConstants.ResponseResult.FAILED);
		res.setResultCode(AppConstants.ResponsResultCode.ERROR);	
		res.setMessage("요청에러");
	}
}
