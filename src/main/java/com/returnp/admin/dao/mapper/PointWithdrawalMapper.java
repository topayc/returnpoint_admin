package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PointWithdrawal;

public interface PointWithdrawalMapper {
    int deleteByPrimaryKey(Integer pointWithdrawalNo);

    int insert(PointWithdrawal record);

    int insertSelective(PointWithdrawal record);

    PointWithdrawal selectByPrimaryKey(Integer pointWithdrawalNo);

    int updateByPrimaryKeySelective(PointWithdrawal record);

    int updateByPrimaryKeyWithBLOBs(PointWithdrawal record);

    int updateByPrimaryKey(PointWithdrawal record);
}