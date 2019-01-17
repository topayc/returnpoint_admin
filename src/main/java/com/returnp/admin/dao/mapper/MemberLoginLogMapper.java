package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.MemberLoginLog;

public interface MemberLoginLogMapper {
    int deleteByPrimaryKey(Integer memberLoginLogNo);

    int insert(MemberLoginLog record);

    int insertSelective(MemberLoginLog record);

    MemberLoginLog selectByPrimaryKey(Integer memberLoginLogNo);

    int updateByPrimaryKeySelective(MemberLoginLog record);

    int updateByPrimaryKey(MemberLoginLog record);
}