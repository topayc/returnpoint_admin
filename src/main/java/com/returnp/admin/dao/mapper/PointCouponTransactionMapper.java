package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PointCouponTransaction;

public interface PointCouponTransactionMapper {
    int deleteByPrimaryKey(Integer pointCouponTransactionNo);

    int insert(PointCouponTransaction record);

    int insertSelective(PointCouponTransaction record);

    PointCouponTransaction selectByPrimaryKey(Integer pointCouponTransactionNo);

    int updateByPrimaryKeySelective(PointCouponTransaction record);

    int updateByPrimaryKey(PointCouponTransaction record);
}