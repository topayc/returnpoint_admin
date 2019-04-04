package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.GiftCardPolicy;

public interface GiftCardPolicyMapper {
    int deleteByPrimaryKey(Integer giftCardPolicyNo);

    int insert(GiftCardPolicy record);

    int insertSelective(GiftCardPolicy record);

    GiftCardPolicy selectByPrimaryKey(Integer giftCardPolicyNo);

    int updateByPrimaryKeySelective(GiftCardPolicy record);

    int updateByPrimaryKey(GiftCardPolicy record);
}