package com.returnp.admin.web.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.PointConversionTransaction;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.SaleManager;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.MembershipRequestService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.service.interfaces.RecommenderService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SaleManagerService;

@Controller
@RequestMapping("/api")
@SessionAttributes("saleManagerFormInfo")
public class SaleManagerController extends ApplicationController {

	@Autowired SaleManagerService saleManagerService;
	@Autowired RecommenderService recommenderService;
	@Autowired SearchService searchService;
	@Autowired MembershipRequestService membershipRequestService;
	@Autowired MemberService memberService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired PointCoversionTransactionService pointTransactionService;
	
	@RequestMapping(value = "/saleManager/form/createForm", method = RequestMethod.GET)
	public String formSaleMangerRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "saleManagerNo", defaultValue = "0") int saleManagerNo,
			Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatusList());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypeList());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatusList());
		model.addAttribute("authTypes", CodeDefine.getAuthtypeList());
	
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("saleManagerFormInfo", this.saleManagerService.selectByPrimaryKey(saleManagerNo));
		}
		return "template/form/node/createSaleManager";
	}
	
	@ResponseBody
	@RequestMapping(value = "/saleManager/get", method = RequestMethod.GET)
	public ReturnpBaseResponse  getSaleManger(
			@RequestParam(value = "saleManagerNo", required = true) int  saleManagerNo) {
		
		SaleManager  saleManager = this.saleManagerService.selectByPrimaryKey(saleManagerNo);
		ObjectResponse<SaleManager> res = new ObjectResponse<SaleManager>();
		res.setData(saleManager);
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saleManager/create", method = RequestMethod.POST)
	public  ReturnpBaseResponse createSaleManger(
			SaleManager saleManager, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		SaleManager cond = new SaleManager();
		cond.setMemberNo(saleManager.getMemberNo());
		cond.setSaleManagerEmail(saleManager.getSaleManagerEmail());
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		
		if (this.searchService.findSaleManagers(cond).size() > 0) {
			this.setErrorResponse(res,"이미 협력 업체로 등록되어 있는 회원입니다.");
		}else {
			if (saleManager.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
				AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
				saleManager.setRegAdminNo(adminSession.getAdmin().getAdminNo());
			}
			
			saleManager.setSaleManagerCode(CodeGenerator.generatorSaleManagerCode(null));
			saleManager.setGreenPointAccStatus("Y");
			saleManager.setRedPointAccStatus("Y");
			saleManager.setGreenPointUseStatus("Y");
			saleManager.setRedPointUseStatus("Y");
			this.saleManagerService.insert(saleManager);
			
			/* Member Table 업데이트*/
			Member m = new Member();
			m.setMemberNo(saleManager.getMemberNo());
			m.setIsSaleManager("Y");
			this.memberService.updateByPrimaryKeySelective(m);
			
			/* Green Point 생성, Red point 회원 가입시 생성되므로 생성할 필요 없음*/
			GreenPoint greenPoint = new GreenPoint();
			greenPoint.setMemberNo(saleManager.getMemberNo());
			greenPoint.setNodeNo(saleManager.getSaleManagerNo());
			greenPoint.setNodeType(AppConstants.NodeType.SALE_MANAGER);
			greenPoint.setPointAmount((float)0);
			greenPoint.setNodeTypeName("salemanager");
			this.greenPointService.insert(greenPoint);
			
			this.setSuccessResponse(res, "생성 완료");
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saleManager/update", method = RequestMethod.POST)
	public  ReturnpBaseResponse updateSaleManger( 
			@ModelAttribute("saleManagerFormInfo") SaleManager saleManager,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (saleManager.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			saleManager.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.saleManagerService.updateByPrimaryKey(saleManager);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saleManager/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteSaleManager( 
			int  saleManagerNo, Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.saleManagerService.deleteByPrimaryKey(saleManagerNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
	
}
