package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.SubBbs;

public interface SubBbsMapper {
    int deleteByPrimaryKey(Integer subBbsNo);

    int insert(SubBbs record);

    int insertSelective(SubBbs record);

    SubBbs selectByPrimaryKey(Integer subBbsNo);

    int updateByPrimaryKeySelective(SubBbs record);

    int updateByPrimaryKeyWithBLOBs(SubBbs record);

    int updateByPrimaryKey(SubBbs record);
}