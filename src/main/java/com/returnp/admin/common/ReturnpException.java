package com.returnp.admin.common;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;

public class ReturnpException extends Exception {
	private ReturnpBaseResponse data;
	public ReturnpException(ReturnpBaseResponse res) {
		this.data = res;
	} 
	public ReturnpBaseResponse getBaseResponse() {
		return data;
	}

	public void setBaseResponse(ReturnpBaseResponse data) {
		this.data = data;
	}
}
