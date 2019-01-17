package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.MemberAddress;

public interface MemberAddressMapper {
    int deleteByPrimaryKey(Integer memberAddressNo);

    int insert(MemberAddress record);

    int insertSelective(MemberAddress record);

    MemberAddress selectByPrimaryKey(Integer memberAddressNo);

    int updateByPrimaryKeySelective(MemberAddress record);

    int updateByPrimaryKey(MemberAddress record);
}