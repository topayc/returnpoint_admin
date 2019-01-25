package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.command.AffiliateCommand;
import com.returnp.admin.model.Affiliate;

@Transactional
public interface AffiliateService {
	  int deleteByPrimaryKey(Integer affiliateNo);

	    int insert(Affiliate record);

	    int insertSelective(Affiliate record);

	    Affiliate selectByPrimaryKey(Integer affiliateNo);
	    
	    AffiliateCommand selectAffiliateCommandByPrimaryKey(Integer affiliateNo);

	    int updateByPrimaryKeySelective(Affiliate record);

	    int updateByPrimaryKey(Affiliate record);
}