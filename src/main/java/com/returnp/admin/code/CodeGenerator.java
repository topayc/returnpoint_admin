package com.returnp.admin.code;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;

import com.returnp.admin.utils.Crypto;
import com.returnp.admin.utils.Util;

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
	
	public static ArrayList<String> generatorMarketerCode(String source, int degree) {
		ArrayList<String> list = new ArrayList<String>();
		source = source == null ? "@" : source;
		for (int i = 0; i < degree; i++) {
			  source = Util.nextAlphabet(source);
			list.add(source);
	    }
		return list;
	}
	
	public static String generatorRfId(Object object) {
		return "RFID_" + new Date ().getTime(); 
	}
	
	  public static String createApiToken(String data) {
   	   String token = null;
   	    SecureRandom secureRandom;
   	    try {
   	        secureRandom = SecureRandom.getInstance("SHA1PRNG");
   	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
   	        secureRandom.setSeed(secureRandom.generateSeed(128));
   	        token= new String(digest.digest((secureRandom.nextLong() + "").getBytes()));
   	        token = Crypto.encode_base64(token.getBytes(),token.length());
   	    } catch (NoSuchAlgorithmException e) {
   	    	e.printStackTrace();
   	    }
   	    return token;
   }
}