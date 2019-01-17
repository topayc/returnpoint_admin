package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.MembershipRequest;

public interface MembershipRequestMapper {
    int deleteByPrimaryKey(Integer membershipRequestNo);

    int insert(MembershipRequest record);

    int insertSelective(MembershipRequest record);

    MembershipRequest selectByPrimaryKey(Integer membershipRequestNo);

    int updateByPrimaryKeySelective(MembershipRequest record);

    int updateByPrimaryKey(MembershipRequest record);
}