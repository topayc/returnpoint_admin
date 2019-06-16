package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PaymentRouter;

public interface PaymentRouterMapper {
    int deleteByPrimaryKey(Integer paymentRouterNo);

    int insert(PaymentRouter record);

    int insertSelective(PaymentRouter record);

    PaymentRouter selectByPrimaryKey(Integer paymentRouterNo);

    int updateByPrimaryKeySelective(PaymentRouter record);

    int updateByPrimaryKey(PaymentRouter record);
}