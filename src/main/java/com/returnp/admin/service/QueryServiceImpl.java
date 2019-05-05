package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.QueryMapper;
import com.returnp.admin.model.AffiliateTid;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.service.interfaces.QueryService;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired QueryMapper queryMapper;
	@Override
	public int deleteAffiliateTid(AffiliateTid affiliateTid) {
		return this.queryMapper.deleteAffiliateTid(affiliateTid);
	}
	@Override
	public int deleteGPoint(GreenPoint gPoint) {
		return this.queryMapper.deleteGPoint(gPoint);
	}
}
