package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.PointWithdrawalMapper;
import com.returnp.admin.dto.command.PointWithdrawalCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PointCodeIssueRequest;
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

	@Override
	public ReturnpBaseResponse changeWithdrawalStatus(ArrayList<Integer> pointWithdrawalNos, String withdrawalStatus) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		PointWithdrawal d = null;
		try {
			for (Integer pointWithdrawalNo : pointWithdrawalNos) {
				d = new PointWithdrawal();
				d.setPointWithdrawalNo(pointWithdrawalNo);
				d.setWithdrawalStatus(withdrawalStatus);
				this.pointWithdrawalMapper.updateByPrimaryKeySelective(d);
			}
			ResponseUtil.setSuccessResponse(res, "100" , "출금 상태 변경 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "조회 에러 ");
			return res;
		}
		
	}
}
