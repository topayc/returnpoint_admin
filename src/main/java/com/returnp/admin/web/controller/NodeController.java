package com.returnp.admin.web.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.QueryCondition;
import com.returnp.admin.dto.command.AffiliateCommand;
import com.returnp.admin.dto.command.AgencyCommand;
import com.returnp.admin.dto.command.BranchCommand;
import com.returnp.admin.dto.command.MemberCommand;
import com.returnp.admin.dto.command.RecommenderCommand2;
import com.returnp.admin.dto.command.SaleManagerCommand;
import com.returnp.admin.dto.command.SoleDistCommand;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.service.interfaces.AffiliateService;
import com.returnp.admin.service.interfaces.AgencyService;
import com.returnp.admin.service.interfaces.BranchService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RecommenderService;
import com.returnp.admin.service.interfaces.SaleManagerService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.utils.ReflectionManager;

@Controller
@RequestMapping("/api")

public class NodeController extends ApplicationController{

	@Autowired SearchService searchService;

	@Autowired MemberService memberService;
	@Autowired RecommenderService recommendeSerivce;
	@Autowired BranchService branchService;
	@Autowired AgencyService agencyService;
	@Autowired AffiliateService affiliateService;;
	@Autowired SaleManagerService saleManagerService;
	@Autowired PointCoversionTransactionService pointTransactionService;
    
	@ResponseBody
	@RequestMapping(value = "/nodes", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public  BaseResponse getNodeList( 
			HttpServletRequest request,
			SearchCondition nodeSearch,
			Model model) throws Exception {
		
		BaseResponse res = this.getNodeArrayList(nodeSearch);
		this.setSuccessResponse(res);
		return res;
	}

	private BaseResponse getNodeArrayList(SearchCondition nodeSearch) throws Exception {
		
		switch(nodeSearch.getSearchNodeType()) {
		case AppConstants.NodeType.MEMBER:
			MemberCommand memberCond = new MemberCommand();
			memberCond.setMemberStatus(nodeSearch.getSearchNodeStatus());

			if (StringUtils.isEmpty(nodeSearch.getSearchKeyword())) {
				nodeSearch.setSearchKeyword(null);
			}
			memberCond.setMemberEmail(nodeSearch.getSearchKeyword());
			memberCond.setMemberName(nodeSearch.getSearchKeyword());
			// memberCond.setMemberPhone(nodeSearch.getSearchKeyword());
			memberCond.valueOf(nodeSearch);
            
			ArrayList<MemberCommand> memberList = this.searchService.findMemberCommands(memberCond);
			ArrayListResponse<MemberCommand> slr = new ArrayListResponse<MemberCommand>();
			slr.setRows(memberList);
			slr.setTotal(this.searchService.selectTotalRecords());
			return slr;

		case AppConstants.NodeType.RECOMMENDER:
			RecommenderCommand2 rCond = new RecommenderCommand2();
			rCond.setRecommenderStatus(nodeSearch.getSearchNodeStatus());

			if (StringUtils.isEmpty(nodeSearch.getSearchKeyword())) {
				nodeSearch.setSearchKeyword(null);
			}

			rCond.setRecommenderName(nodeSearch.getSearchKeyword());
			rCond.setRecommenderCode(nodeSearch.getSearchKeyword());
			rCond.setRecommenderEmail(nodeSearch.getSearchKeyword());
			rCond.setRecommenderAddress(nodeSearch.getSearchKeyword());
			rCond.setRecommenderTel(nodeSearch.getSearchKeyword());
			rCond.setRecommenderPhone(nodeSearch.getSearchKeyword());

			rCond.valueOf(nodeSearch);
			ArrayList<RecommenderCommand2> recommenderList = this.searchService.findRecommenderCommand2s(rCond);
			ArrayListResponse<RecommenderCommand2> slr2 = new ArrayListResponse<RecommenderCommand2>();
			slr2.setRows(recommenderList);
			slr2.setTotal(this.searchService.selectTotalRecords());
			return slr2;

		case AppConstants.NodeType.BRANCH:
			BranchCommand bCond = new BranchCommand();
			bCond.setBranchStatus(nodeSearch.getSearchNodeStatus());

			if (StringUtils.isEmpty(nodeSearch.getSearchKeyword())) {
				nodeSearch.setSearchKeyword(null);
			}

			bCond.setBranchName(nodeSearch.getSearchKeyword());
			bCond.setBranchCode(nodeSearch.getSearchKeyword());
			bCond.setBranchAddress(nodeSearch.getSearchKeyword());
			bCond.setBranchTel(nodeSearch.getSearchKeyword());
			bCond.setBranchPhone(nodeSearch.getSearchKeyword());
			bCond.setBranchEmail(nodeSearch.getSearchKeyword());

			bCond.valueOf(nodeSearch);
			ArrayList<BranchCommand> branchList = this.searchService.findBranchCommands(bCond);

			ArrayListResponse<BranchCommand> slr3 = new ArrayListResponse<BranchCommand>();
			slr3.setRows(branchList);
			slr3.setTotal(this.searchService.selectTotalRecords());
			return slr3;

		case AppConstants.NodeType.AGENCY:
			AgencyCommand aCond = new AgencyCommand();
			aCond.setAgencyStatus(nodeSearch.getSearchNodeStatus());
			
			if (StringUtils.isEmpty(nodeSearch.getSearchKeyword())) {
				nodeSearch.setSearchKeyword(null);
			}

			aCond.setAgencyName(nodeSearch.getSearchKeyword());
			aCond.setAgencyCode(nodeSearch.getSearchKeyword());
			aCond.setAgencyPhone(nodeSearch.getSearchKeyword());
			aCond.setAgencyTel(nodeSearch.getSearchKeyword());
			aCond.setAgencyAddress(nodeSearch.getSearchKeyword());

			aCond.valueOf(nodeSearch);
			ArrayList<AgencyCommand> agencyList = this.searchService.findAgencyCommands(aCond);

			ArrayListResponse<AgencyCommand> slr4 = new ArrayListResponse<AgencyCommand>();
			slr4.setRows(agencyList);
			slr4.setTotal(this.searchService.selectTotalRecords());
			return slr4;

		case AppConstants.NodeType.AFFILIATE:
			AffiliateCommand afCond = new AffiliateCommand();
			afCond.setAffiliateStatus(nodeSearch.getSearchNodeStatus());

			if (StringUtils.isEmpty(nodeSearch.getSearchKeyword())) {
				nodeSearch.setSearchKeyword(null);
			}
			afCond.setAffiliateName(nodeSearch.getSearchKeyword());
			afCond.setAffiliateCode(nodeSearch.getSearchKeyword());
			afCond.setAffiliatePhone(nodeSearch.getSearchKeyword());
			afCond.setAffiliateTel(nodeSearch.getSearchKeyword());
			afCond.setAffiliateAddress(nodeSearch.getSearchKeyword());
			/* afCond.setAffiliateSerial(nodeSearch.getSearchKeyword()); */
			afCond.valueOf(nodeSearch);
			ArrayList<AffiliateCommand> affiliateList = this.searchService.findAffiliateCommands(afCond);

			ArrayListResponse<AffiliateCommand> slr5 = new ArrayListResponse<AffiliateCommand>();
			slr5.setRows(affiliateList);
			slr5.setTotal(this.searchService.selectTotalRecords());
			return slr5;

		case AppConstants.NodeType.SALE_MANAGER:
			SaleManagerCommand sfCond = new SaleManagerCommand();
			sfCond.setSaleManagerStatus(nodeSearch.getSearchNodeStatus());

			if (StringUtils.isEmpty(nodeSearch.getSearchKeyword())) {
				nodeSearch.setSearchKeyword(null);
			}

			sfCond.setSaleManagerName(nodeSearch.getSearchKeyword());
			sfCond.setSaleManagerCode(nodeSearch.getSearchKeyword());
			sfCond.setSaleManagerPhone(nodeSearch.getSearchKeyword());
			sfCond.setSaleManagerTel(nodeSearch.getSearchKeyword());
			sfCond.setSaleManagerAddress(nodeSearch.getSearchKeyword());
			sfCond.valueOf(nodeSearch);
			ArrayList<SaleManagerCommand> saleManagerList = this.searchService.findSaleManagerCommands(sfCond);

			ArrayListResponse<SaleManagerCommand> slr6 = new ArrayListResponse<SaleManagerCommand>();
			slr6.setRows(saleManagerList);
			slr6.setTotal(this.searchService.selectTotalRecords());
			return slr6;

		case AppConstants.NodeType.SOLE_DIST:
			SoleDistCommand sdCond = new SoleDistCommand();
			sdCond.setSoleDistStatus(nodeSearch.getSearchNodeStatus());
			
			if (StringUtils.isEmpty(nodeSearch.getSearchKeyword())) {
				nodeSearch.setSearchKeyword(null);
			}

			sdCond.setSoleDistName(nodeSearch.getSearchKeyword());
			sdCond.setSoleDistCode(nodeSearch.getSearchKeyword());
			sdCond.setSoleDistPhone(nodeSearch.getSearchKeyword());
			sdCond.setSoleDistTel(nodeSearch.getSearchKeyword());
			sdCond.setSoleDistAddress(nodeSearch.getSearchKeyword());

			sdCond.valueOf(nodeSearch);
			ArrayList<SoleDistCommand> soleDistList = this.searchService.findSoleDistCommands(sdCond);
			ArrayListResponse<SoleDistCommand> slr7 = new ArrayListResponse<SoleDistCommand>();
			slr7.setRows(soleDistList);
			slr7.setTotal(this.searchService.selectTotalRecords());
			return slr7;
			
		}
		return null;
	}

	

	/**
	 * 노드 생성
	 * @param nodeSearchQuery
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/nodes/create", method = RequestMethod.POST)
	public BaseResponse createNode( SearchCondition nodeSearchQuery, Model model) {
		System.out.println("createNode");
		return null;
	}	
	
	/**
	 * 노드 삭제
	 * @param nodeSearchQuery
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/nodes/delete", method = RequestMethod.POST)
	public BaseResponse deleteNode( SearchCondition nodeSearchQuery, Model model) {
		System.out.println("deleteNode");
		return null;
	}	
	
	/**
	 * 노드 수정 
	 * @param nodeSearchQuery
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/nodes/update", method = RequestMethod.POST)
	public BaseResponse updateNode( SearchCondition nodeSearchQuery, Model model) {
		System.out.println("updateNode");
		return null;
	}	
}