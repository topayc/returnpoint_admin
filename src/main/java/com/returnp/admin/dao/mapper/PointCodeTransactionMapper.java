package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PointCodeTransaction;

public interface PointCodeTransactionMapper {
    int deleteByPrimaryKey(Integer pointCodeTransactionNo);

    int insert(PointCodeTransaction record);

    int insertSelective(PointCodeTransaction record);

    PointCodeTransaction selectByPrimaryKey(Integer pointCodeTransactionNo);

    int updateByPrimaryKeySelective(PointCodeTransaction record);

    int updateByPrimaryKey(PointCodeTransaction record);
}