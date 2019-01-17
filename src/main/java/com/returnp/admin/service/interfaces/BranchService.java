package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.command.BranchCommand;
import com.returnp.admin.model.Branch;

@Transactional
public interface BranchService {
	 int deleteByPrimaryKey(Integer branchNo);

	    int insert(Branch record);

	    int insertSelective(Branch record);

	    Branch selectByPrimaryKey(Integer branchNo);
	    BranchCommand selectBranchCommandByPrimaryKey(Integer branchNo);

	    int updateByPrimaryKeySelective(Branch record);

	    int updateByPrimaryKey(Branch record);
}
