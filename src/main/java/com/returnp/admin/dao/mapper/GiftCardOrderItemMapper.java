package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.GiftCardOrderItem;

public interface GiftCardOrderItemMapper {
    int deleteByPrimaryKey(Integer orderItemNo);

    int insert(GiftCardOrderItem record);

    int insertSelective(GiftCardOrderItem record);

    GiftCardOrderItem selectByPrimaryKey(Integer orderItemNo);

    int updateByPrimaryKeySelective(GiftCardOrderItem record);

    int updateByPrimaryKey(GiftCardOrderItem record);
}