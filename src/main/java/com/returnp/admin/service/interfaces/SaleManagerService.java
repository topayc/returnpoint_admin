package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.SaleManager;

@Transactional
public interface SaleManagerService {
	 int deleteByPrimaryKey(Integer saleManagerNo);

	    int insert(SaleManager record);

	    int insertSelective(SaleManager record);

	    SaleManager selectByPrimaryKey(Integer saleManagerNo);

	    int updateByPrimaryKeySelective(SaleManager record);

	    int updateByPrimaryKey(SaleManager record);
}
