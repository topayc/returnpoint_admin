package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.RedPointMapper;
import com.returnp.admin.model.RedPoint;
import com.returnp.admin.service.interfaces.RedPointService;

@Service
public class RedPointServiceImple implements RedPointService {

	
	@Autowired RedPointMapper redPointMapper;
	@Override
	public int deleteByPrimaryKey(Integer redPointNo) {
		// TODO Auto-generated method stub
		return this.redPointMapper.deleteByPrimaryKey(redPointNo);
	}

	@Override
	public int insert(RedPoint record) {
		return this.redPointMapper.insert(record);
	}

	@Override
	public int insertSelective(RedPoint record) {
		return this.redPointMapper.insertSelective(record);
	}

	@Override
	public RedPoint selectByPrimaryKey(Integer redPointNo) {
		return this.redPointMapper.selectByPrimaryKey(redPointNo);
	}

	@Override
	public int updateByPrimaryKeySelective(RedPoint record) {
		return this.redPointMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(RedPoint record) {
		return this.redPointMapper.updateByPrimaryKey(record);
	}

}
