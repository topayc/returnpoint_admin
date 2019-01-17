package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.MemberDetail;

public interface MemberDetailMapper {
    int deleteByPrimaryKey(Integer memberDetailNo);

    int insert(MemberDetail record);

    int insertSelective(MemberDetail record);

    MemberDetail selectByPrimaryKey(Integer memberDetailNo);

    int updateByPrimaryKeySelective(MemberDetail record);

    int updateByPrimaryKey(MemberDetail record);
}