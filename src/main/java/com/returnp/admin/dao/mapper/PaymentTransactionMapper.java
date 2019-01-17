package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PaymentTransaction;

public interface PaymentTransactionMapper {
    int deleteByPrimaryKey(Integer paymentTransactionNo);

    int insert(PaymentTransaction record);

    int insertSelective(PaymentTransaction record);

    PaymentTransaction selectByPrimaryKey(Integer paymentTransactionNo);

    int updateByPrimaryKeySelective(PaymentTransaction record);

    int updateByPrimaryKey(PaymentTransaction record);
}