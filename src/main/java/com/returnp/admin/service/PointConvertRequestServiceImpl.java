package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.PointConvertRequestMapper;
import com.returnp.admin.model.PointConvertRequest;
import com.returnp.admin.service.interfaces.PointConvertRequestSerivce;

@Service
public class PointConvertRequestServiceImpl implements PointConvertRequestSerivce {
	
	@Autowired PointConvertRequestMapper pointConvertMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer pointConvertRequestNo) {
		return this.pointConvertMapper.deleteByPrimaryKey(pointConvertRequestNo);
	}

	@Override
	public int insert(PointConvertRequest record) {
		return this.pointConvertMapper.insert(record);
	}

	@Override
	public int insertSelective(PointConvertRequest record) {
		return this.pointConvertMapper.insertSelective(record);
	}

	@Override
	public PointConvertRequest selectByPrimaryKey(Integer pointConvertRequestNo) {
		return this.pointConvertMapper.selectByPrimaryKey(pointConvertRequestNo);
	}

	@Override
	public int updateByPrimaryKeySelective(PointConvertRequest record) {
		return this.pointConvertMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PointConvertRequest record) {
		return this.pointConvertMapper.updateByPrimaryKey(record);
	}
}
