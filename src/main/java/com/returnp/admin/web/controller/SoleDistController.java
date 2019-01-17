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
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.PointConversionTransaction;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.SoleDist;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.service.interfaces.SoleDistService;

@Controller
@RequestMapping("/api")
@SessionAttributes("soleDistFormInfo")
public class SoleDistController extends ApplicationController{
	
	@Autowired SoleDistService soleDistService;
	@Autowired SearchService searchService;
	@Autowired MemberService memberService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired PointCoversionTransactionService pointTransactionService;
	
	@RequestMapping(value = "/soleDist/form/createForm", method = RequestMethod.GET)
	public String formSoleDistRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "soleDistNo", defaultValue = "0") int soleDistNo,
			Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatusList());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypeList());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatusList());
		model.addAttribute("authTypes", CodeDefine.getAuthtypeList());
	
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("soleDistFormInfo", this.soleDistService.selectByPrimaryKey(soleDistNo));
		}
		return "template/form/node/createSoleDist";
	}
	
	@ResponseBody
	@RequestMapping(value = "/soleDist/get", method = RequestMethod.GET)
	public BaseResponse  getSoleDist(
			@RequestParam(value = "soleDistNo", required = true) int  soleDistNo) {
		SoleDist  soleDist= this.soleDistService.selectByPrimaryKey(soleDistNo);
		SingleDataObjectResponse<SoleDist> res = new SingleDataObjectResponse<SoleDist>();
		res.setData(soleDist);
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/soleDist/create", method = RequestMethod.GET)
	public  BaseResponse createSoleDist(
			SoleDist soleDist, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		BaseResponse res = new BaseResponse();
		SoleDist soleDistCond = new SoleDist();
		if (this.searchService.findSoleDists(soleDistCond).size()> 0) {
			this.setErrorResponse(res,"이미 1개의 총판이 등록되어 있습니다. 총판은 1개만 등록이 가능합니다");
			return res;
		}
		
		soleDistCond.setMemberNo(soleDist.getMemberNo());
		soleDistCond.setSoleDistEmail(soleDist.getSoleDistEmail());
		
		
		if (this.searchService.findSoleDists(soleDistCond).size() > 0) {
			this.setErrorResponse(res,"이미 총판으로 등록되어 있는 회원입니다.");
		}else {
			if (soleDist.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
				AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
				soleDist.setRegAdminNo(adminSession.getAdmin().getAdminNo());
			}
			
			/*SoleDist 테이블 삽입*/ 
			soleDist.setSoleDistCode(CodeGenerator.generatorSoleDistCode(null));
			soleDist.setGreenPointAccStatus("Y");
			soleDist.setRedPointAccStatus("Y");
			soleDist.setGreenPointUseStatus("Y");
			soleDist.setRedPointUseStatus("Y");
			this.soleDistService.insert(soleDist);
			
			/* Member Table 업데이트*/
			Member m = new Member();
			m.setMemberNo(soleDist.getMemberNo());
			m.setIsSoleDist("Y");
			this.memberService.updateByPrimaryKeySelective(m);
			
			/* Green Point 생성, Red point 회원 가입시 생성되므로 생성할 필요 없음*/
			GreenPoint greenPoint = new GreenPoint();
			greenPoint.setMemberNo(soleDist.getMemberNo());
			greenPoint.setNodeNo(soleDist.getSoleDistNo());
			greenPoint.setNodeType(AppConstants.NodeType.SOLE_DIST);
			greenPoint.setPointAmount((float)0);
			greenPoint.setNodeTypeName("soledist");
			this.greenPointService.insert(greenPoint);
			
			this.setSuccessResponse(res, "생성 완료");
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/soleDist/update", method = RequestMethod.POST)
	public  BaseResponse updateSoleDist( 
			@ModelAttribute("soleDistFormInfo") SoleDist soleDist,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (soleDist.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			soleDist.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		BaseResponse res = new BaseResponse();
		this.soleDistService.updateByPrimaryKey(soleDist);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/soleDist/delete", method = RequestMethod.POST)
	public  BaseResponse deleteSoleDist( 
			int  soleDistNo, Model model) {
		BaseResponse res = new BaseResponse();
		this.soleDistService.deleteByPrimaryKey(soleDistNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
}
