package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.GiftCardIssue;

public interface GiftCardIssueMapper {
    int deleteByPrimaryKey(Integer giftCardIssueNo);

    int insert(GiftCardIssue record);

    int insertSelective(GiftCardIssue record);

    GiftCardIssue selectByPrimaryKey(Integer giftCardIssueNo);

    int updateByPrimaryKeySelective(GiftCardIssue record);

    int updateByPrimaryKey(GiftCardIssue record);
}