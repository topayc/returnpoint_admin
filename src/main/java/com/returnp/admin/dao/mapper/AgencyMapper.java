package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.Agency;

public interface AgencyMapper {
    int deleteByPrimaryKey(Integer agencyNo);

    int insert(Agency record);

    int insertSelective(Agency record);

    Agency selectByPrimaryKey(Integer agencyNo);

    int updateByPrimaryKeySelective(Agency record);

    int updateByPrimaryKey(Agency record);
}