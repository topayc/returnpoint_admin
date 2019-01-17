package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.VanOriData;

public interface VanOriDataMapper {
    int deleteByPrimaryKey(Integer vanOriDataNo);

    int insert(VanOriData record);

    int insertSelective(VanOriData record);

    VanOriData selectByPrimaryKey(Integer vanOriDataNo);

    int updateByPrimaryKeySelective(VanOriData record);

    int updateByPrimaryKey(VanOriData record);
}