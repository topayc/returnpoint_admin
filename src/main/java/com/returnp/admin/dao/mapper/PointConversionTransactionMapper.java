package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PointConversionTransaction;

public interface PointConversionTransactionMapper {
    int deleteByPrimaryKey(Integer pointConversionTansactionNo);

    int insert(PointConversionTransaction record);

    int insertSelective(PointConversionTransaction record);

    PointConversionTransaction selectByPrimaryKey(Integer pointConversionTansactionNo);

    int updateByPrimaryKeySelective(PointConversionTransaction record);

    int updateByPrimaryKey(PointConversionTransaction record);
}