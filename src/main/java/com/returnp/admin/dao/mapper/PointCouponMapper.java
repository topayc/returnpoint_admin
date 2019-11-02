package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PointCoupon;

public interface PointCouponMapper {
    int deleteByPrimaryKey(Integer pointCouponNo);

    int insert(PointCoupon record);

    int insertSelective(PointCoupon record);

    PointCoupon selectByPrimaryKey(Integer pointCouponNo);

    int updateByPrimaryKeySelective(PointCoupon record);

    int updateByPrimaryKey(PointCoupon record);
}