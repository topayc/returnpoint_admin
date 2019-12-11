package com.returnp.admin.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.SplittableRandom;
import java.util.UUID;

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
	
	public static String generatorTid(String str){
		switch(str) {
		case "A001":
			return null;
		case "A002":  // 온라인 사업자, 쇼핑몰
			return "OTID_" + new Date ().getTime();
		case "A003":  // 무사업자 
			return "RTID_" + new Date ().getTime(); 
		}
		return null;
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
		  UUID api = UUID.randomUUID();
		  /*System.out.println(api.toString().replaceAll("-", ""));*/
		  return api.toString().replaceAll("-", "");
   }
/*	  public static String createApiToken(String data) {
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
	  }*/
	  
	  public static String genPointCode() {
		  char[] PIN_CHARACTERS  = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		  SplittableRandom splittableRandom = null;
		  char[] pinCharArrs = new char[20];
		  Collections.shuffle(Arrays.asList(PIN_CHARACTERS));
		  splittableRandom = new SplittableRandom();
		  
		  for (int k = 0; k < pinCharArrs.length; k++) {
			  int elementIndex = splittableRandom.nextInt(PIN_CHARACTERS.length);
			  pinCharArrs[k] = PIN_CHARACTERS[elementIndex];
		  }
		  return String.valueOf(pinCharArrs);
	  }
}
