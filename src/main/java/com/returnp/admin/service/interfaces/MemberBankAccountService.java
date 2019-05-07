package com.returnp.admin.service.interfaces;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.command.MemberBankAccountCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.MemberBankAccount;

@Transactional
public interface MemberBankAccountService {
	public ArrayList<MemberBankAccountCommand> findMemberBankAccountCommands(MemberBankAccount memberBankAccount); 
	public void create(MemberBankAccount memberBankAccount);
	public void delete(MemberBankAccount memberBankAccount);
	public void delete(int memberBankAccount);
	public void update(MemberBankAccount memberBankAccount);
	public void defaultBankAccount(int memberBankAccountNo, int memberNo);
}
