package com.returnp.admin.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.code.CodeGenerator;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.CodeKeyValuePair;
import com.returnp.admin.dto.command.AffiliateCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.model.Category;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MemberAddress;
import com.returnp.admin.model.Policy;
import com.returnp.admin.service.interfaces.AffiliateService;
import com.returnp.admin.service.interfaces.AgencyService;
import com.returnp.admin.service.interfaces.BranchService;
import com.returnp.admin.service.interfaces.CategoryService;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberAddressService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.utils.Common;

@Controller
@RequestMapping("/api")
@SessionAttributes("affiliateFormInfo")
public class AffiliateController extends ApplicationController {

	@Autowired MemberService memberSerivice;
	@Autowired MemberAddressService memberAddressSerivice;
	@Autowired BranchService branchService;
	@Autowired AgencyService agencyService;
	@Autowired AffiliateService affiliateService;
	@Autowired SearchService searchService;
	@Autowired MemberService memberService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired PointCoversionTransactionService pointTransactionService;
	@Autowired CategoryService categoryService;
	
	@RequestMapping(value = "/affiliate/form/createForm", method = RequestMethod.GET)
	public String formAffiliateRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "affiliateNo", defaultValue = "0") int affiliateNo,
			@RequestParam(value = "memberAddressNo", defaultValue = "0") int memberAddressNo,
			Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatusList());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypeList());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatusList());
		model.addAttribute("authTypes", CodeDefine.getAuthtypeList());
		model.addAttribute("affiliateTypes", CodeDefine.getAffiliateTypes());
		
		/*정책 조회*/
		Policy policy = new Policy();
		ArrayList<Policy> policies = this.searchService.findPolicies(policy);
		policy = policies.get(policies.size() -1 );
		model.addAttribute("policy",policy);
		
		Category catetoryCond = new Category();
		catetoryCond.setCategoryLevel("1");
		model.addAttribute("categories1",this.searchService.findCategories(catetoryCond));
	
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("affiliateFormInfo", this.affiliateService.selectByPrimaryKey(affiliateNo));
			model.addAttribute("memberAddressInfo", this.memberAddressSerivice.selectByPrimaryKey(memberAddressNo));
		}
		return "template/form/node/createAffiliate";
	}
	
	@RequestMapping(value = "/locationView", method = {RequestMethod.POST,RequestMethod.GET})
	public String formLocationRequest(HttpServletRequest request,Model model){
		
		@SuppressWarnings("unchecked")
		Map<String,Object> param=request.getParameterMap();
		Iterator<String> itr = param.keySet().iterator();
		String keyAttribute = null;        
		while (itr.hasNext()) {
	        keyAttribute =itr.next();
	        model.addAttribute(keyAttribute,param.get(keyAttribute));
		}
		return "template/form/common/location";
	}
		
	@ResponseBody
	@RequestMapping(value = "/affiliate/get", method = RequestMethod.GET)
	public ReturnpBaseResponse  getAffiliate(
			@RequestParam(value = "affiliateNo", required = true) int  affiliateNo,
			@RequestParam(value = "memberAddressNo", required = true) int  memberAddressNo) {
		
		Affiliate  affiliate= this.affiliateService.selectByPrimaryKey(affiliateNo);
		MemberAddress address = this.memberAddressSerivice.selectByPrimaryKey(memberAddressNo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.putAll(Common.objectToMap(affiliate)) ;
		map.putAll(Common.objectToMap(address)) ;
		
		ObjectResponse<Map<String,Object>> res = new ObjectResponse<Map<String,Object>>();
		res.setData(map);
		this.setSuccessResponse(res);
		return res;
	}


	@ResponseBody
	@RequestMapping(value = "/affiliate/getAffiliateCommand", method = RequestMethod.GET)
	public ReturnpBaseResponse  getAffiliateCommand(
			@RequestParam(value = "affiliateNo", required = true) int  affiliateNo,
			@RequestParam(value = "memberAddressNo", required = true) int  memberAddressNo) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		
		AffiliateCommand  affiliateCommand= this.affiliateService.selectAffiliateCommandByPrimaryKey(affiliateNo);
		MemberAddress address = this.memberAddressSerivice.selectByPrimaryKey(memberAddressNo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.putAll(Common.objectToMap(affiliateCommand)) ;
		map.putAll(Common.objectToMap(address)) ;
		
		ObjectResponse<Map<String,Object>> res = new ObjectResponse<Map<String,Object>>();
		//SingleDataObjectResponse<AffiliateCommand> res = new SingleDataObjectResponse<AffiliateCommand>();
		//res.setData(affiliateCommand);
		res.setData(map);
		this.setSuccessResponse(res);
		return res;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/affiliate/create", method = RequestMethod.POST)
	public  ReturnpBaseResponse createAffiliate(
			Affiliate affiliate, MemberAddress address, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		/*Affiliate cond = new Affiliate();
		cond.setMemberNo(affiliate.getMemberNo());
		cond.setAffiliateEmail(affiliate.getAffiliateEmail());*/
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
	/*	if (this.searchService.findAffiliates(cond).size() > 0) {
			this.setErrorResponse(res,"이미 협력 업체로 등록되어 있는 회원입니다.");
		}else {*/
			if (affiliate.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
				AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
				affiliate.setRegAdminNo(adminSession.getAdmin().getAdminNo());
			}
			
			affiliate.setAffiliateCode(CodeGenerator.generatorAffiliateCode(null));
			affiliate.setGreenPointAccStatus("Y");
			affiliate.setRedPointAccStatus("Y");
			affiliate.setGreenPointUseStatus("Y");
			affiliate.setRedPointUseStatus("Y");
			this.affiliateService.insert(affiliate);
			
			/* Member Address 생성*/
			address.setNodeNo(affiliate.getAffiliateNo());
			address.setNodeType("5");
			address.setMemberNo(affiliate.getMemberNo());
			address.setRegAdminNo(affiliate.getRegAdminNo());
			this.memberAddressSerivice.insert(address);
			
			/* Member Table 업데이트*/
			Member m = new Member();
			m.setMemberNo(affiliate.getMemberNo());
			m.setIsAffiliate("Y");
			this.memberService.updateByPrimaryKeySelective(m);
			
			/* 협력 업체 Green Point 생성, 
			 * 중복 생성을 원천적으로 막기 위해, 그린 포인트가 없는 경우에만 인서트 
			 * Red point는  회원 가입시 생성되므로 생성할 필요 없음
			 * */
			GreenPoint greenPoint = new GreenPoint();
			greenPoint.setMemberNo(affiliate.getMemberNo());
			greenPoint.setNodeNo(affiliate.getAffiliateNo());
			
			if (this.searchService.findGreenPoints(greenPoint).size() <  1) {
				greenPoint.setNodeType(AppConstants.NodeType.AFFILIATE);
				greenPoint.setPointAmount((float)0);
				greenPoint.setNodeTypeName("affiliate");
				this.greenPointService.insert(greenPoint);
			}
			
			this.setSuccessResponse(res, "생성 완료");
		/*}*/
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/affiliate/update", method = RequestMethod.POST)
	public  ReturnpBaseResponse updateAffiliate( 
			@ModelAttribute("affiliateFormInfo") Affiliate affiliate, @ModelAttribute("memberAddressInfo") MemberAddress address,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (affiliate.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			affiliate.setRegAdminNo(adminSession.getAdmin().getAdminNo());			
			address.setRegAdminNo(affiliate.getRegAdminNo());
			
		}
		
		GreenPoint greenPoint = new GreenPoint();
		greenPoint.setMemberNo(affiliate.getMemberNo());
		greenPoint.setNodeType(AppConstants.NodeType.AFFILIATE);
		
		/* 
		 * 수정인 경우, 가맹점의 멤버를 수정할 수 있기 때문세 해당 멤버번호와 Affilaite 타입의 그린포인트가 없을 경우 
		 * 그린 포인트를 생성함 
		*/ 
		ArrayList<GreenPoint> greenPoints = this.searchService.findGreenPoints(greenPoint);
		if (greenPoints.size() == 0 || greenPoints == null) {
			greenPoint.setNodeNo(affiliate.getAffiliateNo());
			greenPoint.setPointAmount((float)0);
			greenPoint.setNodeTypeName("affiliate");
			this.greenPointService.insert(greenPoint);
		}
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.affiliateService.updateByPrimaryKey(affiliate);
		
		address.setNodeType("5");
		address.setMemberNo(affiliate.getMemberNo());
		
		this.memberAddressSerivice.updateByPrimaryKey(address);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/affiliate/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteAffiliate( 
			@RequestParam(value = "affiliateNo", required = true) int  affiliateNo, 
			Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.affiliateService.deleteByPrimaryKey(affiliateNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/affiliate/genTid", method = RequestMethod.GET)
	public  ReturnpBaseResponse genTid(String affiliateType,  Model model) {
		ObjectResponse<String> res = new ObjectResponse<String>();
		res.setData(CodeGenerator.generatorTid(affiliateType));
		this.setSuccessResponse(res, "T-ID 생성 ");
		return res;
	}
}
