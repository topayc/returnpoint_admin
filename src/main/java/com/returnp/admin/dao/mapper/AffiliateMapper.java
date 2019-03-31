package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.Affiliate;

public interface AffiliateMapper {
    int deleteByPrimaryKey(Integer affiliateNo);

    int insert(Affiliate record);

    int insertSelective(Affiliate record);

    Affiliate selectByPrimaryKey(Integer affiliateNo);

    int updateByPrimaryKeySelective(Affiliate record);

    int updateByPrimaryKey(Affiliate record);
    
    Affiliate selectBySerial(String serial);
}