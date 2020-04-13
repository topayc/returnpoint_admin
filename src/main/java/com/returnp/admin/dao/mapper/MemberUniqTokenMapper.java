package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.MemberUniqToken;

public interface MemberUniqTokenMapper {
    int deleteByPrimaryKey(Integer memberUniqTokenNo);

    int insert(MemberUniqToken record);

    int insertSelective(MemberUniqToken record);

    MemberUniqToken selectByPrimaryKey(Integer memberUniqTokenNo);

    int updateByPrimaryKeySelective(MemberUniqToken record);

    int updateByPrimaryKey(MemberUniqToken record);
}