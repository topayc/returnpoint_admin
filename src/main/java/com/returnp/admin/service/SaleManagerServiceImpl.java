package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.SaleManagerMapper;
import com.returnp.admin.model.SaleManager;
import com.returnp.admin.service.interfaces.SaleManagerService;

@Service
public class SaleManagerServiceImpl implements SaleManagerService {

	@Autowired SaleManagerMapper saleMangerMapper;

	@Override
	public int deleteByPrimaryKey(Integer saleManagerNo) {
		// TODO Auto-generated method stub
		return this.saleMangerMapper.deleteByPrimaryKey(saleManagerNo);
	}

	@Override
	public int insert(SaleManager record) {
		// TODO Auto-generated method stub
		return this.saleMangerMapper.insert(record);
	}

	@Override
	public int insertSelective(SaleManager record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SaleManager selectByPrimaryKey(Integer saleManagerNo) {
		// TODO Auto-generated method stub
		return this.saleMangerMapper.selectByPrimaryKey(saleManagerNo);
	}

	@Override
	public int updateByPrimaryKeySelective(SaleManager record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SaleManager record) {
		// TODO Auto-generated method stub
		return this.saleMangerMapper.updateByPrimaryKey(record);
	}
}
