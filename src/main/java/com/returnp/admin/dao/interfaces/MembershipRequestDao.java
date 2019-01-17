package com.returnp.admin.dao.interfaces;

import com.returnp.admin.model.MembershipRequest;

public interface MembershipRequestDao {
	   int deleteByPrimaryKey(Integer membershipRequestNo);

	    int insert(MembershipRequest record);

	    int insertSelective(MembershipRequest record);

	    MembershipRequest selectByPrimaryKey(Integer membershipRequestNo);

	    int updateByPrimaryKeySelective(MembershipRequest record);

	    int updateByPrimaryKey(MembershipRequest record);
}
