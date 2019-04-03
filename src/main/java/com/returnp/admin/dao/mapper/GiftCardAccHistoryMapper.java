package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.GiftCardAccHistory;

public interface GiftCardAccHistoryMapper {
    int deleteByPrimaryKey(Integer giftCardAccHistoryNo);

    int insert(GiftCardAccHistory record);

    int insertSelective(GiftCardAccHistory record);

    GiftCardAccHistory selectByPrimaryKey(Integer giftCardAccHistoryNo);

    int updateByPrimaryKeySelective(GiftCardAccHistory record);

    int updateByPrimaryKey(GiftCardAccHistory record);
}