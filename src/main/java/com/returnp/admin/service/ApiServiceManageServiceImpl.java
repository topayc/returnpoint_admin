package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.ApiServiceMapper;
import com.returnp.admin.model.ApiService;
import com.returnp.admin.service.interfaces.ApiServiceManageService;

@Service
public class ApiServiceManageServiceImpl implements ApiServiceManageService {

	@Autowired ApiServiceMapper apiServiceMapper;

	@Override
	public int deleteByPrimaryKey(Integer apiServiceNo) {
		// TODO Auto-generated method stub
		return this.apiServiceMapper.deleteByPrimaryKey(apiServiceNo);
	}

	@Override
	public int insert(ApiService record) {
		// TODO Auto-generated method stub
		return this.apiServiceMapper.insert(record);
	}

	@Override
	public int insertSelective(ApiService record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ApiService selectByPrimaryKey(Integer apiServiceNo) {
		// TODO Auto-generated method stub
		return this.apiServiceMapper.selectByPrimaryKey(apiServiceNo);
	}
	
	@Override
	public ArrayList<ApiService> findApiServices(ApiService apiService) {
		// TODO Auto-generated method stub
		return this.apiServiceMapper.findApiServices(apiService);
	}

	@Override
	public int updateByPrimaryKeySelective(ApiService record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ApiService record) {
		// TODO Auto-generated method stub
		return this.apiServiceMapper.updateByPrimaryKey(record);
	}
}
