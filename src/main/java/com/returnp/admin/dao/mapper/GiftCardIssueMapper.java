package com.returnp.admin.dao.mapper;

import java.util.HashMap;

import com.returnp.admin.model.GiftCardIssue;
import com.returnp.admin.model.GiftCardIssueKey;

public interface GiftCardIssueMapper {
	int deleteByPrimaryKey(GiftCardIssueKey key);
    int deleteByGiftCardOrderNo(int giftCardOrderNo);

       int insert(GiftCardIssue record);
       
       int insertBatch(HashMap<String, Object> datamap);
       
       int insertSelective(GiftCardIssue record);

       GiftCardIssue selectByPrimaryKey(GiftCardIssueKey key);

       int updateByPrimaryKeySelective(GiftCardIssue record);

       int updateByPrimaryKey(GiftCardIssue record);
}