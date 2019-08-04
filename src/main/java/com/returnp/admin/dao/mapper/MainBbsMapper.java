package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.MainBbs;

public interface MainBbsMapper {
    int deleteByPrimaryKey(Integer mainBbsNo);

    int insert(MainBbs record);

    int insertSelective(MainBbs record);

    MainBbs selectByPrimaryKey(Integer mainBbsNo);

    int updateByPrimaryKeySelective(MainBbs record);

    int updateByPrimaryKeyWithBLOBs(MainBbs record);

    int updateByPrimaryKey(MainBbs record);
}