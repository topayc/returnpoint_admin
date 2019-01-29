package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.MembershipRequest;

@Transactional
public interface MembershipRequestService {
	 
	int deleteByPrimaryKey(Integer membershipRequestNo);

	    int insert(MembershipRequest record);

	    int insertSelective(MembershipRequest record);

	    MembershipRequest selectByPrimaryKey(Integer membershipRequestNo);

	    int updateByPrimaryKeySelective(MembershipRequest record);

	    int updateByPrimaryKey(MembershipRequest record);
}
