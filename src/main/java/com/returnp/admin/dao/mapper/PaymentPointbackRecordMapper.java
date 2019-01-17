package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PaymentPointbackRecord;

public interface PaymentPointbackRecordMapper {
    int deleteByPrimaryKey(Integer paymentPointbackRecordNo);

    int insert(PaymentPointbackRecord record);

    int insertSelective(PaymentPointbackRecord record);

    PaymentPointbackRecord selectByPrimaryKey(Integer paymentPointbackRecordNo);

    int updateByPrimaryKeySelective(PaymentPointbackRecord record);

    int updateByPrimaryKey(PaymentPointbackRecord record);
}