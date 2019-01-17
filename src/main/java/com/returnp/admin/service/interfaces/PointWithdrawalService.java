package com.returnp.admin.service.interfaces;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.command.PointWithdrawalCommand;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.model.PointWithdrawal;

@Transactional
public interface PointWithdrawalService {
	public ArrayList<PointWithdrawal> findPointWithdrawals(PointWithdrawal pointWithdrawal);
	public ArrayList<PointWithdrawalCommand> findPointWithdrawalCommands(PointWithdrawalCommand pointWithdrawalCommand);
	public void create(PointWithdrawal pointWithdrawal);
	public void delete(PointWithdrawal pointWithdrawal);
	public void delete(int pointWithdrawalNo);
	public void update(PointWithdrawal pointWithdrawal);
}
