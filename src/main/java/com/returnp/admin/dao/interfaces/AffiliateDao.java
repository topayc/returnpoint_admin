package com.returnp.admin.dao.interfaces;

import com.returnp.admin.model.Affiliate;

public interface AffiliateDao {
	  int deleteByPrimaryKey(Integer affiliateNo);

	    int insert(Affiliate record);

	    int insertSelective(Affiliate record);

	    Affiliate selectByPrimaryKey(Integer affiliateNo);

	    int updateByPrimaryKeySelective(Affiliate record);

	    int updateByPrimaryKey(Affiliate record);
}

