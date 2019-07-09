package com.returnp.admin.web.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dao.mapper.SearchMapper;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.Admin;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.model.Agency;
import com.returnp.admin.model.Branch;
import com.returnp.admin.model.CompanyBankAccount;
import com.returnp.admin.model.GiftCard;
import com.returnp.admin.model.GiftCardSalesOrgan;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.Recommender;
import com.returnp.admin.model.SaleManager;
import com.returnp.admin.service.interfaces.AdminService;
import com.returnp.admin.service.interfaces.PolicyService;
import com.returnp.admin.service.interfaces.SearchService;

/**
 * @author 안영철
 * 관리자 백오피스 컨트롤러
 */
@Controller
/*@SessionAttributes({"nodeData"})*/
public class AdminController extends ApplicationController{
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	/**
	 * @author 안영철
	 * URL 요청 엑세스 포인트 정의 클래스 
	 */
	public static class AccessPoint {
		public static final String ROOT = "/";		
		public static final String SIGN_UP = "/signUp";
		public static final String SIGN_IN = "/signIn";
		public static final String LOGOUT = "/logout";
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired private AdminService adminService;
	@Autowired private SearchService serachService;
	@Autowired PolicyService policyService;
	@Autowired SearchMapper  searchMapper;
	
	/**
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = AccessPoint.ROOT, method = RequestMethod.GET)
	public String adminMainView(HttpSession session, Locale locale, Model model) {
		session.setAttribute("adminType", ((AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION)).getAdminType());
		session.setAttribute("adminTypeStr", ((AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION)).getAdminTypeStr());
		session.setAttribute("loginName", ((AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION)).getLoginName());
		
		if (!((AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION)).getAdminType().equals(AppConstants.AdminType.SUPER)){
			session.setAttribute("organCode",((AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION)).getSaleOrgan().getOrganCode());
			session.setAttribute("organType",((AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION)).getSaleOrgan().getOrganType());
		}
		return RequestForward.MAIN_VIEW;
	}
	
	/**
	 * 메뉴별 컨텐츠 처리 
	 * @param viewReqCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{springSecurity}/handleContent", method = RequestMethod.GET)
	public String handleContentView(@RequestParam String viewReqName, SearchCondition searchCondition, Model model) {
		model.addAttribute("viewReqName", viewReqName);
		model.addAttribute("paymentStatusList", CodeDefine.getPaymentStatuses());
		model.addAttribute("paymentTypeList", CodeDefine.getPaymentTypest());
		model.addAttribute("keywordTypeList", CodeDefine.getKeywordTypes());
		
		model.addAttribute("nodeTypeList", CodeDefine.getNodeTypes());
		model.addAttribute("nodeStatusList", CodeDefine.getNodeStatuses());
		model.addAttribute("searchCondition", searchCondition);
		model.addAttribute("vanPaymentStatuses", CodeDefine.getVanPaymentStatusest());
		model.addAttribute("orderTypes", CodeDefine.getQueryOrderTypes());
		model.addAttribute("conversionStatusList", CodeDefine.getConversionStatuses());
		model.addAttribute("paymentApprovalStatusList", CodeDefine.getPaymentApprovalStatuses());
		model.addAttribute("paymentTransactionTypeList", CodeDefine.getPaymentTransactionTypes());
		
		model.addAttribute("pointTypes", CodeDefine.getPointTypes());
		
		model.addAttribute("apiServiceList", CodeDefine.getApiServiceList(applicationContext));
		model.addAttribute("apiServiceStatusList", CodeDefine.getApiServiceStatuses());
		model.addAttribute("boardTypeList", CodeDefine.getBoardTypes());
		model.addAttribute("boardSearchKeywordTypeList", CodeDefine.getBoardSearchKeywordTypest());
		model.addAttribute("memberBankStatusList", CodeDefine.getMemberBankAccountStatuses());
		model.addAttribute("withdrawalStatusList", CodeDefine.getWithdrawalStatuses());
		model.addAttribute("marketerStatuses", CodeDefine.getMarketerStatuses());
		model.addAttribute("productStatusList", CodeDefine.getProductStatuses());
		model.addAttribute("saleOrganTypes", CodeDefine.getSaleOrganTypes());
		model.addAttribute("saleOrganStatusList", CodeDefine.getOrganStatuses());

		model.addAttribute("paymentRouterTypes", CodeDefine.getPaymentRouterTypes());
		
		if (viewReqName.equals("manageGiftCardIssue")) {
			model.addAttribute("giftCards", this.serachService.selectGiftCards(new GiftCard()));
			model.addAttribute("giftCardTypes", CodeDefine.getGiftCardTypes());
			model.addAttribute("giftCardOrderTypes", CodeDefine.getOrganTypes());
			model.addAttribute("orderReasonTypes", CodeDefine.getOrderReasons());
		}
		
		return RequestForward.CONTENT_VIEW;
	}

	/**
	 * 노드 관련 폼  로딩 
	 * @param viewReqCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{springSecurity}/handleNodeForm", method = RequestMethod.GET)
	public String handleNodeForm(
			@RequestParam(value = "formReqName", required = true,defaultValue = "createNode") String formReqName, 
			SearchCondition nodeSearch,
			Model model) {
		model.addAttribute("bankAccounts", this.serachService.findCompanyBanks(new CompanyBankAccount()));
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypest());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatuses());
		model.addAttribute("registTypes", CodeDefine.getRegistTypes());
		model.addAttribute("nodeSatuses", CodeDefine.getNodeStatuses());
		
		Policy cond = new Policy();
		ArrayList<Policy> policies = this.serachService.findPolicies(cond);
		model.addAttribute("policy", policies.get(policies.size()-1));
		
	
		model.addAttribute("formReqName", formReqName);
		model.addAttribute("nodeSearch", nodeSearch);
		//model.addAttribute("formReqName",formReqName);
		return RequestForward.HANDLE_NODE_FORM_TEMPLATE;
	}
	
	private void setNodeData(Model model, SearchCondition nodeSearch) {
		int no = nodeSearch.getNodeNo();
		switch(nodeSearch.getSearchNodeType()) {
		case AppConstants.NodeType.MEMBER:
			Member member = new Member();
			member.setMemberNo(no);
			model.addAttribute("nodeData", this.serachService.findMembers(member).get(0));
			break;
		case AppConstants.NodeType.RECOMMENDER:
			Recommender recomm = new Recommender();
			recomm.setRecommenderNo(no);
			model.addAttribute("nodeData",  this.serachService.findRecommenderCommands(recomm).get(0));
			break;
		case AppConstants.NodeType.BRANCH:
			Branch branch= new Branch();
			branch.setBranchNo(no);
			model.addAttribute("nodeData",  this.serachService.findBranches(branch).get(0));
			break;
		case AppConstants.NodeType.AGENCY:
			Agency agency= new Agency();
			agency.setAgencyNo(no);
			model.addAttribute("nodeData",  this.serachService.findAgencies(agency).get(0));
			break;
		case AppConstants.NodeType.AFFILIATE:
			Affiliate aff= new Affiliate();
			aff.setAffiliateNo(no);
			model.addAttribute("nodeData",  this.serachService.findAffiliates(aff).get(0));
			break;
		case AppConstants.NodeType.SALE_MANAGER:
			SaleManager saleManager = new SaleManager();
			saleManager.setSaleManagerNo(no);
			model.addAttribute("nodeData",  this.serachService.findSaleManagers(saleManager).get(0));
			break;
		}
	}

	/**
	 * 멤버쉽 관련 폼  로딩 
	 * @param viewReqCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{springSecurity}/handleMembershipForm", method = RequestMethod.GET)
	public String handleMembershipFom(
			SearchCondition searchCondition,
			String formReqName,
			Model model) {
		model.addAttribute("handleMembershipFom formReqName", searchCondition.getSearchNodeType());
		model.addAttribute("formReqName", formReqName);
		model.addAttribute("searchCondition", searchCondition);
		return RequestForward.HANDLE_MEMBERSHIP_FORM_TEMPLATE;
	}
	
	/**
	 * 뷰 템플릿 로딩
	 * @param viewReqCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{springSecurity}/handleNodeListView", method = RequestMethod.GET)
	public String handleNodeListView(
			@RequestParam(value = "viewReqName", required = true,defaultValue = "nodeList") String viewReqName,
			SearchCondition nodeSearch,
			Model model) {
		model.addAttribute("viewReqName", viewReqName);
		model.addAttribute("nodeSearch", nodeSearch);
		return RequestForward.HANDLE_NODE_VIEW_TEMPLATE;
	}
	
	/**
	 * 뷰 템플릿 로딩
	 * @param viewReqCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{springSecurity}/handleCommonListView", method = RequestMethod.GET)
	public String handleCommonListView(
			@RequestParam(value = "viewReqName", required = true,defaultValue = "nodeList") String viewReqName,
			Model model) {		
		model.addAttribute("viewReqName", viewReqName);
		return RequestForward.HANDLE_COMMON_VIEW_TEMPLATE;
	}
	
	/**
	 * 회원 가입 폼 처리 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = AccessPoint.SIGN_IN, method = RequestMethod.GET)
	public String signInView(Locale locale, Model model) {
		return RequestForward.SIGN_IN_VIEW;
	}
	
	
	/**
	 * 회원 로그인 처리 
	 * @param memberEmail
	 * @param memberPassword
	 * @param request
	 * @param httpSession
	 * @param model
	 * @return
	 */	
	@RequestMapping(value =  AdminController.AccessPoint.SIGN_IN, method = RequestMethod.POST)
	public String signIn( @RequestParam String id, @RequestParam String password, HttpServletRequest request, HttpSession httpSession, Model model) {
		String error = (String) request.getAttribute("errorMessage");
		//System.out.println("signIn");
	/*	if(!error.equals(null)) {
			model.addAttribute("message", error);
			return RequestForward.SIGN_IN_VIEW;
		}
		return RequestRedirect.MAIN_REDIRECT;*/
		Admin admin = new Admin();
		admin.setAdminEmail(id);
		admin.setAdminPassword(password);
		
		ArrayList<Admin> admins = this.serachService.findAdmins(admin);
		ArrayList<GiftCardSalesOrgan> organs = null;
		GiftCardSalesOrgan organ = null;
		
		AdminSession adminSession = new AdminSession();
		
		if (admins.size() == 1) {
			adminSession.setAdmin(admins.get(0));
			adminSession.setAdminType(AppConstants.AdminType.SUPER);
			httpSession.setAttribute(AppConstants.ADMIN_SESSION, adminSession);
			return RequestRedirect.MAIN_REDIRECT;
			
		}else {
			organ = new GiftCardSalesOrgan();
			organ.setOrganCode(id);
			organ.setOrganPassword(password);
			organs = this.searchMapper.selectGiftCardSalesOrgans(organ);
			
			if (organs.size() == 1) {
				adminSession.setSaleOrgan(organs.get(0));
				adminSession.setAdminType(organs.get(0).getOrganType());
				httpSession.setAttribute(AppConstants.ADMIN_SESSION, adminSession);
				return RequestRedirect.MAIN_REDIRECT;
			}else {
				model.addAttribute("message", "아이디 혹은 비밀번호가 틀립니다");
				return RequestForward.SIGN_IN_VIEW;
			}
		}
	}
	
	/**
	 * 회원 로그인 처리 
	 * @param memberEmail
	 * @param memberPassword
	 * @param request
	 * @param httpSession
	 * @param model
	 * @return
	 */	
	@RequestMapping(value =  "/AjaxRequest", method = RequestMethod.GET)
	public String AjaxRequest( HttpServletRequest request, HttpSession httpSession, Model model) {
		return "main/AjaxRequest";
	}	
	
	/**
	 * 회원 가입 처리 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value =  AdminController.AccessPoint.SIGN_UP, method = RequestMethod.GET)
	public String signUpView(Locale locale, Model model) {
		return RequestForward.SIGN_UP_VIEW;
	}
	
	/**
	 * 로그 아웃 처리 
	 * @return
	 */
	@RequestMapping(value =  AdminController.AccessPoint.LOGOUT, method = RequestMethod.POST)
	public String logout(HttpSession httpSession, Model model ) {
		httpSession.removeAttribute(AppConstants.ADMIN_SESSION);
		return RequestRedirect.LOGIN_REDIRECT;
	}	
	
	/**
	 * 에러 페이지
	 * @return
	 */
	@RequestMapping(value = "/common/error_403", method = {RequestMethod.POST,RequestMethod.GET})
	public String error_403() {		
		return "common/error_403";
	}
	
	@RequestMapping(value = "/common/error_404", method = {RequestMethod.POST,RequestMethod.GET})
	public String error_404() {		
		return "common/error_404";
	}
	
	@RequestMapping(value = "/common/error_500", method = {RequestMethod.POST,RequestMethod.GET})
	public String error_500(Exception e) {	
		e.printStackTrace();
		return "common/error_500";
	}
}
