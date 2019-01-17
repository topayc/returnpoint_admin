package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.code.CodeGenerator;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dao.mapper.RecommenderMapper;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MembershipRequest;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.Recommender;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.MembershipRequestService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RecommenderService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.utils.ReturnpResponseMessageHandler;

@Service
public class RecommenderServiceImpl implements RecommenderService{

	@Autowired RecommenderMapper recommenderMapper;
	@Autowired SearchService searchService;
	@Autowired MembershipRequestService membershipRequestService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired MemberService memberService;
	@Autowired PointCoversionTransactionService pointTransactionService;

	@Override
	public int deleteByPrimaryKey(Integer recommenderNo) {
		// TODO Auto-generated method stub
		return this.recommenderMapper.deleteByPrimaryKey(recommenderNo);
	}

	@Override
	public int insert(Recommender record) {
		// TODO Auto-generated method stub
		return this.recommenderMapper.insert(record);
	}

	@Override
	public int insertSelective(Recommender record) {
		// TODO Auto-generated method stub
		return this.recommenderMapper.insertSelective(record);
	}

	@Override
	public Recommender selectByPrimaryKey(Integer recommenderNo) {
		// TODO Auto-generated method stub
		return this.recommenderMapper.selectByPrimaryKey(recommenderNo);
	}

	@Override
	public int updateByPrimaryKeySelective(Recommender record) {
		// TODO Auto-generated method stub
		return this.recommenderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Recommender record) {
		// TODO Auto-generated method stub
		return this.recommenderMapper.updateByPrimaryKey(record);
	}

	@Override
	public BaseResponse createRecommender(Recommender recommender) {
		BaseResponse res = new BaseResponse();
		Recommender  cond = new Recommender();
		cond.setMemberNo(recommender.getMemberNo());
		
		ArrayList<Recommender> recommList = this.searchService.findRecommenders(cond);
		if (recommList.size() > 0) {
			res = new BaseResponse();
			ReturnpResponseMessageHandler.setErrorResponse(res, "이미 추천인으로 등록된 사용자 입니다. 다시 확인해 주세요");
		}else {
			recommender.setRecommenderCode(CodeGenerator.generatorRecommenderCode(null));
			recommender.setRecommenderStatus(AppConstants.NodeStatus.NORMAL);
			
			recommender.setGreenPointAccStatus(AppConstants.PointAccStatus.ACC_OK);
			recommender.setRedPointAccStatus(AppConstants.PointAccStatus.ACC_OK);
			recommender.setGreenPointUseStatus(AppConstants.PointUseStatus.USE_OK);
			recommender.setRedPointUseStatus(AppConstants.PointUseStatus.USE_OK);
			this.insert(recommender);
			
			/* Member Table 업데이트*/
			Member m = new Member();
			m.setMemberNo(recommender.getMemberNo());
			m.setIsRecommender("Y");
			this.memberService.updateByPrimaryKeySelective(m);
			
			/* Green Point 생성, Red point 회원 가입시 생성되므로 생성할 필요 없음*/
			GreenPoint greenPoint = new GreenPoint();
			greenPoint.setMemberNo(recommender.getMemberNo());
			greenPoint.setNodeNo(recommender.getRecommenderNo());
			greenPoint.setNodeType(AppConstants.NodeType.RECOMMENDER);
			greenPoint.setPointAmount((float)0);
			greenPoint.setNodeTypeName("recommender");
			this.greenPointService.insert(greenPoint);
			
			/*PointConversionTransaction table 등록*/
			Policy policyCond = new Policy();
			ArrayList<Policy> policies = this.searchService.findPolicies(policyCond);
			Policy policy  = policies.get(policies.size()-1);
			
			/*
			 * 관리자가 직접 추천인을 생성한 경우 정회원 신청 테이블에 직업 삽입함
			 * */
			
			MembershipRequest mebershipRequest = new MembershipRequest();
			mebershipRequest.setMemberNo(recommender.getMemberNo());
			mebershipRequest.setDepositor(recommender.getRecommenderName());
			mebershipRequest.setRecommenderNo(recommender.getRecommenderNo());
			mebershipRequest.setCompanyBankAccountNo(1);
			mebershipRequest.setPaymentStatus(AppConstants.PaymentStatus.PAYMENT_OK);
			mebershipRequest.setPaymentType(AppConstants.PaymentType.PAYMENT_ONLINE);	
			mebershipRequest.setRegType(AppConstants.ReigistType.REGIST_BY_ADMIN);
			mebershipRequest.setRegAdminNo(recommender.getRegAdminNo());
			mebershipRequest.setPaymentStatus(AppConstants.PaymentStatus.PAYMENT_OK);
			mebershipRequest.setPaymentAmount(policy.getMembershipTransLimit());
			this.membershipRequestService.insert(mebershipRequest);
			
			res = new BaseResponse();
			ReturnpResponseMessageHandler.setSuccessResponse(res, "생성 완료");
		}
		return res;
	}

	@Override
	public BaseResponse updateRecommender(Recommender recommender) {
		BaseResponse res = new BaseResponse();
		this.recommenderMapper.updateByPrimaryKey(recommender);
		ReturnpResponseMessageHandler.setSuccessResponse(res, "수정 완료");
		return res;
	}

	@Override
	public BaseResponse deleteRecommender(int recommenderNo) {
		BaseResponse res = new BaseResponse();
		this.recommenderMapper.deleteByPrimaryKey(recommenderNo);
		ReturnpResponseMessageHandler.setSuccessResponse(res, "삭제 완료");
		return res;
	}
}
