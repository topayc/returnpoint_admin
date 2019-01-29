package com.returnp.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.code.CodeGenerator;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;

@Controller
@RequestMapping("/api")
public class CodeController extends ApplicationController {
	
	@ResponseBody
	@RequestMapping(value = "/code/create", method = RequestMethod.GET)
	public ReturnpBaseResponse generatorRecommenderCode(String key, String nodeType, Model mode) {
		
		String code = null;
		switch(nodeType) {
		case AppConstants.NodeType.MEMBER:
			code =  CodeGenerator.generatorMemberCode(key);
			break;
		case AppConstants.NodeType.RECOMMENDER:
			code =  CodeGenerator.generatorRecommenderCode(key);
			break;
		case AppConstants.NodeType.BRANCH:
			code = CodeGenerator.generatorBranchCode(key);
			break;
		case AppConstants.NodeType.AGENCY:
			code = CodeGenerator.generatorAgencyCode(key);
			break;
		case AppConstants.NodeType.AFFILIATE:
			code = CodeGenerator.generatorAffiliateCode(key);
			break;
		case AppConstants.NodeType.SALE_MANAGER:
			code = CodeGenerator.generatorSaleManagerCode(key);
	}
		ObjectResponse<String>  res = new  ObjectResponse<String>();
		res.setData(code);
		this.setSuccessResponse(res);
		return res;
	} 
}
