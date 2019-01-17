package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PointTransferTransaction;

public interface PointTransferTransactionMapper {
    int deleteByPrimaryKey(Integer pointTransferTransactionNo);

    int insert(PointTransferTransaction record);

    int insertSelective(PointTransferTransaction record);

    PointTransferTransaction selectByPrimaryKey(Integer pointTransferTransactionNo);

    int updateByPrimaryKeySelective(PointTransferTransaction record);

    int updateByPrimaryKey(PointTransferTransaction record);
}