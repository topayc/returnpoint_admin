package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.MemberCardInfo;

public interface MemberCardInfoMapper {
    int deleteByPrimaryKey(Integer memberCardInfoNo);

    int insert(MemberCardInfo record);

    int insertSelective(MemberCardInfo record);

    MemberCardInfo selectByPrimaryKey(Integer memberCardInfoNo);

    int updateByPrimaryKeySelective(MemberCardInfo record);

    int updateByPrimaryKey(MemberCardInfo record);
}