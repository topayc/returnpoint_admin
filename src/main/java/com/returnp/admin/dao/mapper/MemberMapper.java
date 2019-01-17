package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer memberNo);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer memberNo);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}