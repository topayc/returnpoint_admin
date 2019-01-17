package com.returnp.admin.service.interfaces;

import com.returnp.admin.model.PointConversionTransaction;

public interface PointCoversionTransactionService {
	int deleteByPrimaryKey(Integer pointConversionTansactionNo);

    int insert(PointConversionTransaction record);

    int insertSelective(PointConversionTransaction record);

    PointConversionTransaction selectByPrimaryKey(Integer pointConversionTansactionNo);

    int updateByPrimaryKeySelective(PointConversionTransaction record);

    int updateByPrimaryKey(PointConversionTransaction record);
}
