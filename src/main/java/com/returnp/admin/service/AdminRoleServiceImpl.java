package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.AdminRoleMapper;
import com.returnp.admin.model.AdminRole;
import com.returnp.admin.service.interfaces.AdminRoleService;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

	@Autowired AdminRoleMapper adminRoleMapper;
	@Override
	public int deleteByPrimaryKey(Integer adminRoleNo) {
		// TODO Auto-generated method stub
		return this.adminRoleMapper.deleteByPrimaryKey(adminRoleNo);
	}

	@Override
	public int insert(AdminRole record) {
		// TODO Auto-generated method stub
		return this.adminRoleMapper.insert(record);
	}

	@Override
	public int insertSelective(AdminRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AdminRole selectByPrimaryKey(Integer adminRoleNo) {
		// TODO Auto-generated method stub
		return this.adminRoleMapper.selectByPrimaryKey(adminRoleNo);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(AdminRole record) {
		// TODO Auto-generated method stub
		return this.adminRoleMapper.updateByPrimaryKey(record);
	}

	@Override
	public ArrayList<AdminRole> selectByAdminNo(Integer adminRoleNo) {
		// TODO Auto-generated method stub
		return this.adminRoleMapper.selectByAdminNo(adminRoleNo);
	}


}
