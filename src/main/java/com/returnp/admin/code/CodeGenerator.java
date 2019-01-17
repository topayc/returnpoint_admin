package com.returnp.admin.code;

import java.util.Date;

import com.returnp.admin.dto.reponse.BaseResponse;

public class CodeGenerator {
	public static String generatorRecommenderCode(String key) {
		return "REC_" + new Date ().getTime(); 
	}

	public static String generatorMemberCode(String email) {
		return null;
	}

	public static String generatorBranchCode(String email) {
		return "BRC_" + new Date ().getTime(); 
	}

	public static String generatorAgencyCode(String email) {
		return "AGC_" + new Date ().getTime(); 
	}

	public static String generatorAffiliateCode(String email) {
		return "AFC_" + new Date ().getTime(); 
	}

	public static String generatorSaleManagerCode(String email) {
		return "SMC_" + new Date ().getTime(); 
	}

	public static String generatorSoleDistCode(Object object) {
		return "SDC_" + new Date ().getTime(); 
	}
	
	public static String generatorTid(Object object) {
		return "RTID_" + new Date ().getTime(); 
	}
	
	public static String generatorPaymentApprovalNumber(Object object) {
		return "RPAN_" + new Date ().getTime(); 
	}
}
