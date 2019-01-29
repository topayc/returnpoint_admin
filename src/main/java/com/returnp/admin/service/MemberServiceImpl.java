package com.returnp.admin.service;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dao.mapper.MemberMapper;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.command.MemberCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.RedPoint;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.utils.Crypto;
import com.returnp.admin.utils.ReturnpResponseMessageHandler;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired MemberMapper memberMapper;
	@Autowired SearchService searchService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired PointCoversionTransactionService pointTransactionService;
	
	@Override
	public int deleteByPrimaryKey(Integer memberNo) {
		return this.memberMapper.deleteByPrimaryKey(memberNo);
	}

	@Override
	public int insert(Member record) {
		return this.memberMapper.insert(record);
	}

	@Override
	public int insertSelective(Member record) {
		return 0;
	}

	@Override
	public Member selectByPrimaryKey(Integer memberNo) {
		return this.memberMapper.selectByPrimaryKey(memberNo);
	}

	@Override
	public int updateByPrimaryKeySelective(Member record) {
		return this.memberMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Member record) {
		return this.memberMapper.updateByPrimaryKey(record);
	}

	@Override
	public ReturnpBaseResponse createMember(Member member) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		if (this.isEmailDuplicated(member.getMemberEmail())) {
			ReturnpResponseMessageHandler.setErrorResponse(res,"중복된 이메일입니다. 다시 확인해주세요");
		}else {
			member.setMemberPassword(Crypto.sha(member.getMemberPassword()));
			if (StringUtils.isBlank(member.getMemberPassword())) {
				member.setMemberPassword2(member.getMemberPassword());
			} else {
				member.setMemberPassword2(Crypto.sha(member.getMemberPassword2()));
			}
			member.setMemberPassword2(Crypto.sha(member.getMemberPassword2()));
			member.setIsSoleDist("N");
			member.setIsAffiliate("N");
			member.setIsAgency("N");
			member.setIsRecommender("Y");
			member.setIsAgency("N");
			member.setIsBranch("N");
			member.setIsSaleManager("Y");
			
			member.setGreenPointAccStatus("Y");
			member.setRedPointAccStatus("Y");
			member.setGreenPointUseStatus("Y");
			member.setRedPointUseStatus("Y");
			this.memberMapper.insert(member);
			
			/* 일반 회원용 Green Point 생성*/
			GreenPoint greenPoint = new GreenPoint();
			greenPoint.setMemberNo(member.getMemberNo());
			greenPoint.setNodeNo(member.getMemberNo());
			greenPoint.setNodeType(AppConstants.NodeType.MEMBER);
			greenPoint.setPointAmount((float)0);
			greenPoint.setNodeTypeName("member");
			this.greenPointService.insert(greenPoint);
			
			/* 추천인용 Green Point 생성*/
			GreenPoint greenPoint2 = new GreenPoint();
			greenPoint2.setMemberNo(member.getMemberNo());
			greenPoint2.setNodeNo(0);
			greenPoint2.setNodeType(AppConstants.NodeType.RECOMMENDER);
			greenPoint2.setPointAmount((float)0);
			greenPoint2.setNodeTypeName("recommender");
			this.greenPointService.insert(greenPoint2);
			
			/* Red  Point 생성*/
			RedPoint redPoint = new RedPoint();
			redPoint.setMemberNo(member.getMemberNo());
			redPoint.setPointAmount((float)0);
			this.redPointService.insert(redPoint);
			
			ReturnpResponseMessageHandler.setSuccessResponse(res, "생성 완료");
		}
		return res;
	}
	
	@Override
	public ReturnpBaseResponse updateMember(Member member) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		Member oriMember = this.memberMapper.selectByPrimaryKey(member.getMemberNo());
		if (oriMember == null) {
			ReturnpResponseMessageHandler.setRespone(res, "311", "error", "잘못된 회원 정보입니다");
			return res;
		}
		if (!member.getMemberPassword().equals(oriMember.getMemberPassword())) {
			member.setMemberPassword(Crypto.sha(member.getMemberPassword()));
			member.setMemberPassword2(member.getMemberPassword());
		}
		
		if (!member.getMemberPassword2().equals(oriMember.getMemberPassword2())) {
			if (StringUtils.isBlank(member.getMemberPassword2())){
				member.setMemberPassword2(Crypto.sha(member.getMemberPassword()));
			}else {
				member.setMemberPassword2(Crypto.sha(member.getMemberPassword2()));
			}
		}
		
		this.memberMapper.updateByPrimaryKey(member);
		ReturnpResponseMessageHandler.setSuccessResponse(res, "수정 완료");
		return res;
	}
	
	@Override
	public boolean isEmailDuplicated(String email) {
		Member mCond = new Member();
		mCond.setMemberEmail(email);
		ArrayList<Member> mList = this.searchService.findMembers(mCond);
		
		if (mList.size() > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isPhoneDuplicated(String phone) {
		Member mCond = new Member();
		mCond.setMemberPhone(phone);
		ArrayList<Member> mList = this.searchService.findMembers(mCond);
		if (mList.size() > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public ReturnpBaseResponse deleteMember(int memberNo) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.memberMapper.deleteByPrimaryKey(memberNo);
		ReturnpResponseMessageHandler.setSuccessResponse(res, "삭제 완료");
		return res;
	}

	@Override
	public MemberCommand selecMemberCommandtByPrimaryKey(Integer memberNo) {
		MemberCommand command = new MemberCommand();
		command.setMemberNo(memberNo);
		return this.searchService.findMemberCommands(command).get(0);
	}
}
