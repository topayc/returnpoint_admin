package com.returnp.admin.common;

import com.returnp.admin.dto.reponse.BaseResponse;

public class ReturnpException extends Exception {
	private BaseResponse data;
	public ReturnpException(BaseResponse res) {
		this.data = res;
	} 
	public BaseResponse getBaseResponse() {
		return data;
	}

	public void setBaseResponse(BaseResponse data) {
		this.data = data;
	}
}