package com.returnp.admin.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.GiftCardPolicyMapper;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardPolicy;

@Controller
@RequestMapping("/api")
public class GiftCardPolicyController extends ApplicationController{
	
	@Autowired GiftCardPolicyMapper giftCardPolicyMapper;
	
	
	@ResponseBody
	@RequestMapping(value = "/giftCardPolicy", method = RequestMethod.GET)
	public ReturnpBaseResponse selectGiftCardPolicy(
			@RequestParam(value = "giftCardPolicyNo", required = true,defaultValue = "0") int giftCardPolicyNo	
		){
		ArrayListResponse<GiftCardPolicy> res = new ArrayListResponse<GiftCardPolicy>();
		GiftCardPolicy policy = this.giftCardPolicyMapper.selectByPrimaryKey(giftCardPolicyNo);
		ArrayList<GiftCardPolicy> policyLists = new ArrayList<GiftCardPolicy>();
		policyLists.add(policy);
		res.setRows(policyLists);
		res.setTotal(1);
		ResponseUtil.setResponse(res, "100", "");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardPolicy/update", method = RequestMethod.POST)
	public ReturnpBaseResponse updateGiftCardPolicy( GiftCardPolicy giftCardPolicy){
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		giftCardPolicy.setSalesCommissionTarget(giftCardPolicy.getSalesCommissionTarget().trim());
		this.giftCardPolicyMapper.updateByPrimaryKeySelective(giftCardPolicy);
		ResponseUtil.setResponse(res, "100", "상품권 정책 업데이트를 완료했습니다");
		return res;
	}
}
