package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.GiftCardOrder;

public interface GiftCardOrderMapper {
    int deleteByPrimaryKey(Integer orderNo);

    int insert(GiftCardOrder record);

    int insertSelective(GiftCardOrder record);

    GiftCardOrder selectByPrimaryKey(Integer orderNo);

    int updateByPrimaryKeySelective(GiftCardOrder record);

    int updateByPrimaryKey(GiftCardOrder record);
}