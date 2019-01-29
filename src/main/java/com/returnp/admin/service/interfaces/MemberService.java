package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.command.MemberCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Member;

@Transactional
public interface MemberService {
	 int deleteByPrimaryKey(Integer memberNo);
	   int insert(Member record);
	    int insertSelective(Member record);
	    Member selectByPrimaryKey(Integer memberNo);
	    MemberCommand selecMemberCommandtByPrimaryKey(Integer memberNo);
	    int updateByPrimaryKeySelective(Member record);
	    int updateByPrimaryKey(Member record);
	    public ReturnpBaseResponse createMember(Member member);
	    public ReturnpBaseResponse updateMember(Member member);
	    public ReturnpBaseResponse deleteMember(int memberNo);
	    boolean isEmailDuplicated(String email);
		boolean isPhoneDuplicated(String phone);

}
