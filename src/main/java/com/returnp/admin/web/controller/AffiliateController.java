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
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.AffiliateMapper;
import com.returnp.admin.dao.mapper.AffiliatePaymentRouterMapper;
import com.returnp.admin.dao.mapper.AffiliateTidMapper;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.command.AffiliateCommand;
import com.returnp.admin.dto.command.AffiliateTidCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.model.AffiliatePaymentRouter;
import com.returnp.admin.model.AffiliateTid;
import com.returnp.admin.model.Category;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MemberAddress;
import com.returnp.admin.model.PaymentRouter;
import com.returnp.admin.model.Policy;
import com.returnp.admin.service.interfaces.AffiliateService;
import com.returnp.admin.service.interfaces.AgencyService;
import com.returnp.admin.service.interfaces.BranchService;
import com.returnp.admin.service.interfaces.CategoryService;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberAddressService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.QueryService;
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
	@Autowired AffiliateTidMapper affiliateTidMapper;
	@Autowired QueryService queryService;
	@Autowired AffiliatePaymentRouterMapper affiliatePaymentRouterMapper;
	@Autowired AffiliateMapper affiliateMapper;
	
	@RequestMapping(value = "/affiliate/form/createForm", method = RequestMethod.GET)
	public String formAffiliateRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "affiliateNo", defaultValue = "0") int affiliateNo,
			@RequestParam(value = "memberAddressNo", defaultValue = "0") int memberAddressNo,
			Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistTypes());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatuses());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypest());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatuses());
		model.addAttribute("authTypes", CodeDefine.getAuthTypes());
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
		
		Affiliate cond = new Affiliate();
		cond.setMemberNo(affiliate.getMemberNo());
		//cond.setAffiliateEmail(affiliate.getAffiliateEmail());
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		if (this.searchService.findAffiliates(cond).size() > 0) {
			this.setErrorResponse(res,"이미 협력 업체로 등록되어 있는 회원입니다.");
		}else {
		/*	if (affiliate.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
				AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
				affiliate.setRegAdminNo(adminSession.getAdmin().getAdminNo());
			}*/
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			
			/*슈퍼 관리자에 의하여 가맹점 생성*/
			if (adminSession.getAdminType() == "1") {
				affiliate.setRegType("A");
				affiliate.setRegAdminNo(adminSession.getAdmin().getAdminNo());
			}
			
			/*상품권 본사에 의한 등록*/
			if (adminSession.getAdminType() == "10") {
				affiliate.setRegType("H");
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
		}
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
		
	/*	if (affiliate.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			affiliate.setRegAdminNo(adminSession.getAdmin().getAdminNo());			
			address.setRegAdminNo(affiliate.getRegAdminNo());
			
		}*/
		
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		
		/*슈퍼 관리자에 의하여 가맹점 생성*/
		if (adminSession.getAdminType() == "1") {
			affiliate.setRegType("A");
			affiliate.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		/*상품권 본사에 의한 등록*/
		if (adminSession.getAdminType() == "10") {
			affiliate.setRegType("H");
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
	@RequestMapping(value = "/affiliate/genTid", method = RequestMethod.GET)
	public  ReturnpBaseResponse genTid(String affiliateType,  Model model) {
		ObjectResponse<String> res = new ObjectResponse<String>();
		res.setData(CodeGenerator.generatorTid(affiliateType));
		this.setSuccessResponse(res, "T-ID 생성 ");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/affiliate/addAffiliateTid", method = RequestMethod.POST)
	public  ReturnpBaseResponse addAffiliateTid( AffiliateTid affiliateTid, HttpSession httpSession, Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		
		Affiliate affOptions = new Affiliate();
		affOptions.setAffiliateNo(affiliateTid.getAffiliateNo());
		
		if (this.searchService.findAffiliates(affOptions).size() != 1) {
			ResponseUtil.setResponse(res, "981", "해당 가맹점이 존재하지 않습니다.확인 후 다시 시도해주세요");
			return res;
		}
		
		AffiliateTidCommand affTidOption = new AffiliateTidCommand();
		affTidOption.setTid(affiliateTid.getTid());
		ArrayList<AffiliateTidCommand> list = this.searchService.selectAffilaiteTidCommands(affTidOption);
		
		if (list.size()> 0) {
			ResponseUtil.setResponse(res, "981", "이미 존재하는 TID 입니다. 확인후 다시 시도해주세요");
			return res;
		}else {
			this.affiliateTidMapper.insert(affiliateTid);
	
			/* 이미 주 affiliateSerial 이 지정되어 있는 지 확인하고, 존재하지 않으면 주 affiliateSerial 로 추가함  */
			Affiliate affiliate = new Affiliate();
			affiliate.setAffiliateNo(affiliateTid.getAffiliateNo());
			affiliate.setAffiliateSerial(affiliateTid.getTid());
			this.affiliateMapper.updateByPrimaryKeySelective(affiliate);
			this.setSuccessResponse(res, "TID 추가 완료");
			return res;
		}
		
	
	
	}

	@ResponseBody
	@RequestMapping(value = "/affiliate/removeAffiliateTid", method = RequestMethod.POST)
	public  ReturnpBaseResponse removeAffiliateTid( AffiliateTid affiliateTid, HttpSession httpSession, Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		
		this.affiliateTidMapper.deleteByPrimaryKey(affiliateTid.getAffiliateTidNo());
		this.setSuccessResponse(res, "TID 삭제 완료");
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/affiliate/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteAffiliate( 
			@RequestParam(value = "affiliateNo", required = true) int  affiliateNo, 
			@RequestParam(value = "memberNo", required = true) int  memberNo,
			Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			Affiliate affiliate = this.affiliateService.selectByPrimaryKey(affiliateNo);
			
			/*가맹점 정보 삭제*/
			this.affiliateService.deleteByPrimaryKey(affiliateNo);
			
			/*가맹점 G포인트 삭제*/
			GreenPoint gPoint = new GreenPoint();
			gPoint.setMemberNo(memberNo);
			gPoint.setNodeNo(affiliateNo);
			this.queryService.deleteGPoint(gPoint);
			
			/*가맹점 연결 TID 삭제*/
			AffiliateTid affiliateTid = new AffiliateTid();
			affiliateTid.setAffiliateNo(affiliateNo);
			this.queryService.deleteAffiliateTid(affiliateTid);
			
			/*회원 정보 업데이트 */
			Member m = new Member();
			m.setMemberNo(affiliate.getMemberNo());
			m.setIsAffiliate("N");
			this.memberService.updateByPrimaryKeySelective(m);
			this.setSuccessResponse(res, "협력업체 및 연결 TID 삭제 및 회원 정보 업데이트 완료 ");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			if (!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR,"2078", "서버 에러");
			return res;
		}
	}
	

	
	@ResponseBody
	@RequestMapping(value = "/affiliate/selectAffiliateTids", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectAffiliateTids( @RequestParam(value = "affiliateNo", required = true) int  affiliateNo) {
		ArrayListResponse<AffiliateTidCommand> res = new ArrayListResponse<AffiliateTidCommand>();

		AffiliateTidCommand command = new AffiliateTidCommand();
		command.setAffiliateNo(affiliateNo);
		ArrayList<AffiliateTidCommand> commands = this.searchService.selectAffilaiteTidCommands(command);
		res.setRows(commands);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/affiliate/updateAffiliateTid", method = RequestMethod.POST)
	public ReturnpBaseResponse  updateAffiliateTid(AffiliateTid affiliateTid) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		
		AffiliateTidCommand option = new AffiliateTidCommand();
		option.setTid(affiliateTid.getTid());
		ArrayList<AffiliateTidCommand> list = this.searchService.selectAffilaiteTidCommands(option);
		
		if (list.size()> 0) {
			ResponseUtil.setResponse(res, "981", "이미 존재하는 TID 입니다. 확인후 다시 시도해주세요");
			return res;
		}else {
			this.affiliateTidMapper.updateByPrimaryKeySelective(affiliateTid);
			
			
			Affiliate affiliate = new Affiliate();
			affiliate.setAffiliateNo(affiliateTid.getAffiliateNo());
			affiliate.setAffiliateSerial(affiliateTid.getTid());
			this.affiliateMapper.updateByPrimaryKeySelective(affiliate);
					
			this.setSuccessResponse(res);
			return res;
		}
	}

	
	@ResponseBody
	@RequestMapping(value = "/affiliate/registerAffiliatePaymentRouter", method = RequestMethod.POST)
	public ReturnpBaseResponse  registerAffiliatePaymentRouter(
			
			@RequestParam(value = "affiliateNo", required = true) int affiliateNo, 
			@RequestParam(value = "paymentRouterType", required = true) String paymentRouterType, 
			@RequestParam(value = "paymentRouterName", required = true)String paymentRouterName) {
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			PaymentRouter pr = new PaymentRouter();
			pr.setPaymentRouterName(paymentRouterName.trim());
			pr.setPaymentRouterType(paymentRouterType.trim());
			
			ArrayList<PaymentRouter> prList = this.searchService.selectPaymentRouters(pr);
			if (prList .size() !=1 ) {
				ResponseUtil.setResponse(res, "997", "등록되어 있지  않은 결제 라우터 입니다.확인후 다시 시도해주세요");
				return res;
			}
			
			/*이미 라우터가 등록되어 있는 가맹점인지 검사*/
			AffiliatePaymentRouter aprCond = new AffiliatePaymentRouter();
			aprCond.setAffiliateNo(affiliateNo);
			ArrayList<AffiliatePaymentRouter> afpList = this.searchService.selectAffiliatePaymentRouters(aprCond);
			
			AffiliatePaymentRouter apr = new AffiliatePaymentRouter();
			
			/* 이미 등록되어 있으며, 등록된 내용을 수정, update */
			if (afpList.size() == 1 ) {
				apr.setAffiliatePaymentRouterNo(afpList.get(0).getAffiliatePaymentRouterNo());
				apr.setAffiliateNo(affiliateNo);
				apr.setPaymentRouterNo(prList.get(0).getPaymentRouterNo());
				this.affiliatePaymentRouterMapper.updateByPrimaryKey(apr);
				ResponseUtil.setResponse(res, "100", "결제라우터가 변경 되었습니다.");
			}
			/* 신규 등록 insert*/
			else  if (afpList.size() == 0) {
				apr.setAffiliateNo(affiliateNo);
				apr.setPaymentRouterNo(prList.get(0).getPaymentRouterNo());
				this.affiliatePaymentRouterMapper.insert(apr);
				ResponseUtil.setResponse(res, "100", "결제 라우터가 등록되었습니다.");
			}else {
				ResponseUtil.setResponse(res, "977", "AffiliatePaymentRouter 등록 오류.");
			}
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseUtil.setResponse(res, "977", "AffiliatePaymentRouter 등록 오류.");
			return res;
		}
	}
}
