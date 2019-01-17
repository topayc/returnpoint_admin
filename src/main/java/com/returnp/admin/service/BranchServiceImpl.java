package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.BranchMapper;
import com.returnp.admin.dto.command.AgencyCommand;
import com.returnp.admin.dto.command.BranchCommand;
import com.returnp.admin.model.Branch;
import com.returnp.admin.service.interfaces.BranchService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired BranchMapper branchMapper;
	@Autowired SearchService searchService;

	@Override
	public int deleteByPrimaryKey(Integer branchNo) {
		// TODO Auto-generated method stub
		return this.branchMapper.deleteByPrimaryKey(branchNo);
	}

	@Override
	public int insert(Branch record) {
		// TODO Auto-generated method stub
		return this.branchMapper.insert(record);
	}

	@Override
	public int insertSelective(Branch record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Branch selectByPrimaryKey(Integer branchNo) {
		// TODO Auto-generated method stub
		return this.branchMapper.selectByPrimaryKey(branchNo);
	}

	@Override
	public int updateByPrimaryKeySelective(Branch record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Branch record) {
		// TODO Auto-generated method stub
		return this.branchMapper.updateByPrimaryKey(record);
	}

	@Override
	public BranchCommand selectBranchCommandByPrimaryKey(Integer branchNo) {
		BranchCommand command = new BranchCommand();
		command.setBranchNo(branchNo);
		return this.searchService.findBranchCommands(command).get(0);
	}
}
