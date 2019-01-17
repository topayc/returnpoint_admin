package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.SoleDistMapper;
import com.returnp.admin.model.SoleDist;
import com.returnp.admin.service.interfaces.SoleDistService;

@Service
public class SoleDistServiceImpl implements SoleDistService {

	@Autowired SoleDistMapper soleDistMapper;
	@Override
	public int deleteByPrimaryKey(Integer soleDistNo) {
		// TODO Auto-generated method stub
		return this.soleDistMapper.deleteByPrimaryKey(soleDistNo);
	}

	@Override
	public int insert(SoleDist record) {
		// TODO Auto-generated method stub
		return this.soleDistMapper.insert(record);
	}

	@Override
	public int insertSelective(SoleDist record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SoleDist selectByPrimaryKey(Integer soleDistNo) {
		// TODO Auto-generated method stub
		return this.soleDistMapper.selectByPrimaryKey(soleDistNo);
	}

	@Override
	public int updateByPrimaryKeySelective(SoleDist record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SoleDist record) {
		// TODO Auto-generated method stub
		return this.soleDistMapper.updateByPrimaryKey(record);
	}


}
