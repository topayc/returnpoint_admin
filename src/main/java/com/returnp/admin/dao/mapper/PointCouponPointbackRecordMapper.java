package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PointCouponPointbackRecord;

public interface PointCouponPointbackRecordMapper {
    int deleteByPrimaryKey(Integer pointCouponPointbackRecordNo);

    int insert(PointCouponPointbackRecord record);

    int insertSelective(PointCouponPointbackRecord record);

    PointCouponPointbackRecord selectByPrimaryKey(Integer pointCouponPointbackRecordNo);

    int updateByPrimaryKeySelective(PointCouponPointbackRecord record);

    int updateByPrimaryKey(PointCouponPointbackRecord record);
}