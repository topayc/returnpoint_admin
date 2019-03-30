package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.GiftCard;

public interface GiftCardMapper {
    int deleteByPrimaryKey(Integer giftCardNo);

    int insert(GiftCard record);

    int insertSelective(GiftCard record);

    GiftCard selectByPrimaryKey(Integer giftCardNo);

    int updateByPrimaryKeySelective(GiftCard record);

    int updateByPrimaryKey(GiftCard record);
}