package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.GreenPointMapper;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.service.interfaces.GreenPointService;

@Service
public class GreenPointServiceImpl implements GreenPointService {

	@Autowired GreenPointMapper greenPointMapper;
	@Override
	public int deleteByPrimaryKey(Integer greenPointNo) {
		return this.greenPointMapper.deleteByPrimaryKey(greenPointNo);
	}

	@Override
	public int insert(GreenPoint record) {
		return this.greenPointMapper.insert(record);
	}

	@Override
	public int insertSelective(GreenPoint record) {
		return this.greenPointMapper.insertSelective(record);
	}

	@Override
	public GreenPoint selectByPrimaryKey(Integer greenPointNo) {
		return this.greenPointMapper.selectByPrimaryKey(greenPointNo);
	}

	@Override
	public int updateByPrimaryKeySelective(GreenPoint record) {
		return this.greenPointMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GreenPoint record) {
		return this.greenPointMapper.updateByPrimaryKey(record);
	}

}
