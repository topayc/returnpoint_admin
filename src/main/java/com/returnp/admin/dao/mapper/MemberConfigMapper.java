package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.MemberConfig;

public interface MemberConfigMapper {
    int deleteByPrimaryKey(Integer memberConfigNo);

    int insert(MemberConfig record);

    int insertSelective(MemberConfig record);

    MemberConfig selectByPrimaryKey(Integer memberConfigNo);

    int updateByPrimaryKeySelective(MemberConfig record);

    int updateByPrimaryKey(MemberConfig record);
}