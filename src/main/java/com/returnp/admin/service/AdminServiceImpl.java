package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.AdminMapper;
import com.returnp.admin.model.Admin;
import com.returnp.admin.service.interfaces.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired private AdminMapper adminMapper;

	@Override
	public int deleteByPrimaryKey(Integer adminNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Admin record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Admin record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Admin selectByPrimaryKey(Integer adminNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Admin record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Admin record) {
		// TODO Auto-generated method stub
		return 0;
	}


}
