package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.PointConversionTransactionMapper;
import com.returnp.admin.model.PointConversionTransaction;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;

@Service
public class PointConversionTransactionServiceImpl implements PointCoversionTransactionService {

	@Autowired PointConversionTransactionMapper pctMapper;

	@Override
	public int deleteByPrimaryKey(Integer pointConversionTansactionNo) {
		return this.pctMapper.deleteByPrimaryKey(pointConversionTansactionNo);
	}

	@Override
	public int insert(PointConversionTransaction record) {
		return this.pctMapper.insert(record);
	}

	@Override
	public int insertSelective(PointConversionTransaction record) {
		return this.pctMapper.insertSelective(record);
	}

	@Override
	public PointConversionTransaction selectByPrimaryKey(Integer pointConversionTansactionNo) {
		return this.pctMapper.selectByPrimaryKey(pointConversionTansactionNo);
	}

	@Override
	public int updateByPrimaryKeySelective(PointConversionTransaction record) {
		return this.pctMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PointConversionTransaction record) {
		return this.pctMapper.updateByPrimaryKey(record);
	}

}
