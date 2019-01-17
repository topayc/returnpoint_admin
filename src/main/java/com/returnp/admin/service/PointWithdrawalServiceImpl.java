package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.PointWithdrawalMapper;
import com.returnp.admin.dto.command.PointWithdrawalCommand;
import com.returnp.admin.model.PointWithdrawal;
import com.returnp.admin.model.RedPoint;
import com.returnp.admin.service.interfaces.PointWithdrawalService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class PointWithdrawalServiceImpl implements PointWithdrawalService {
	@Autowired PointWithdrawalMapper pointWithdrawalMapper;
	@Autowired SearchService searchService;
	@Autowired RedPointService redPointService;
	
	@Override
	public ArrayList<PointWithdrawalCommand> findPointWithdrawalCommands(PointWithdrawalCommand pointWithdrawalCommand) {
		return this.searchService.findPointWithdrawalCommands(pointWithdrawalCommand);
	}
	
	@Override
	public ArrayList<PointWithdrawal> findPointWithdrawals(PointWithdrawal pointWithdrawal) {
		return this.searchService.findPointWithdrawals(pointWithdrawal);
	}
	
	@Override
	public void create(PointWithdrawal record) {
		this.pointWithdrawalMapper.insert(record);
		RedPoint redPoint= new RedPoint();
		redPoint.setMemberNo(record.getMemberNo());
		redPoint = this.searchService.findRedPoints(redPoint).get(0);
		redPoint.setPointAmount(redPoint.getPointAmount() - record.getWithdrawalAmount());
		this.redPointService.updateByPrimaryKey(redPoint);
	}

	@Override
	public void delete(PointWithdrawal record) {
		this.pointWithdrawalMapper.deleteByPrimaryKey(record.getPointWithdrawalNo());
	}

	@Override
	public void delete(int pointWithdrawalNo) {
		this.pointWithdrawalMapper.deleteByPrimaryKey(pointWithdrawalNo);
	}

	@Override
	public void update(PointWithdrawal record) {
		this.pointWithdrawalMapper.updateByPrimaryKey(record);
	}
}
