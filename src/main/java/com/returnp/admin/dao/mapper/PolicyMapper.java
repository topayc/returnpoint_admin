package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.Policy;

public interface PolicyMapper {
    int deleteByPrimaryKey(Integer policyNo);

    int insert(Policy record);

    int insertSelective(Policy record);

    Policy selectByPrimaryKey(Integer policyNo);

    int updateByPrimaryKeySelective(Policy record);

    int updateByPrimaryKey(Policy record);
}