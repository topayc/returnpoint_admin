package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.MemberBankAccountMapper;
import com.returnp.admin.dto.command.MemberBankAccountCommand;
import com.returnp.admin.model.MemberBankAccount;
import com.returnp.admin.service.interfaces.MemberBankAccountService;
import com.returnp.admin.service.interfaces.QueryService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class MemberBankAccountServiceImpl implements MemberBankAccountService {

	@Autowired MemberBankAccountMapper memberBankAccountMapper;
	@Autowired SearchService searchService;
	@Autowired QueryService queryService;

	@Override
	public ArrayList<MemberBankAccountCommand> findMemberBankAccountCommands(MemberBankAccount memberBankAccount) {
		return  this.searchService.findMemberBankAccountCommands(memberBankAccount);
	}
	
	public void create(MemberBankAccount memberBankAccount) {
		this.memberBankAccountMapper.insert(memberBankAccount);
	}

	@Override
	public void delete(MemberBankAccount memberBankAccount) {
		memberBankAccountMapper.deleteByPrimaryKey(memberBankAccount.getMemberBankAccountNo());
	}
	
	@Override
	public void  delete(int memberBankAccountNo) {
		memberBankAccountMapper.deleteByPrimaryKey(memberBankAccountNo);
	}

	@Override
	public void  update(MemberBankAccount memberBankAccount) {
		memberBankAccountMapper.updateByPrimaryKeySelective(memberBankAccount);
	}

	@Override
	public void defaultBankAccount(int memberBankAccountNo, int memberNo) {
		MemberBankAccount account = new MemberBankAccount();
		account.setMemberNo(memberNo);
		account.setIsDefault("N");
		this.queryService.updateMemberBankAccount(account);
		
		account = new MemberBankAccount();
		account.setMemberBankAccountNo(memberBankAccountNo);
		account.setIsDefault("Y");
		this.queryService.updateMemberBankAccount(account);
	}
}
