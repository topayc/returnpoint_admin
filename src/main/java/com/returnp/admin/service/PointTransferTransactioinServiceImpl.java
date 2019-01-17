package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dao.mapper.PointTransferTransactionMapper;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.PointTransferTransaction;
import com.returnp.admin.model.RedPoint;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.PointTransferTransactionServcie;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
@Transactional
public class PointTransferTransactioinServiceImpl implements PointTransferTransactionServcie {
	@Autowired PointTransferTransactionMapper mapper;
	@Autowired GreenPointService greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired SearchService searchService;

	@Override
	public BaseResponse createPointTransferTransaction(PointTransferTransaction pointTransferTransaction) {
		BaseResponse res  = new BaseResponse();
		String trans = String.valueOf(pointTransferTransaction.getPointTransferer()).trim();
		String recv = String.valueOf(pointTransferTransaction.getPointReceiver()).trim();
		if (trans.equals(recv)){
			res.setMessage("자기 자신에게 포인트를 보낼 수 없습니다");
			res.setResultCode("104");
		}
		
		if (AppConstants.Point.RED_POINT.equals(pointTransferTransaction.getPointType())) {
			pointTransferTransaction.setPointNode(null);
		}
		this.insert(pointTransferTransaction);
		
		/* 포인트 타입에 따른 송금자 포인트 삭감 및 수신자 포인트 증가*/
		if (AppConstants.Point.GREEN_POINT.equals(pointTransferTransaction.getPointType())) {
			GreenPoint sgCond = new GreenPoint();
			sgCond.setMemberNo(pointTransferTransaction.getPointTransferer());
			sgCond.setNodeType(pointTransferTransaction.getPointNode());
			GreenPoint sGreenPoint = this.searchService.findGreenPoints(sgCond).get(0);
			sGreenPoint.setPointAmount(sGreenPoint.getPointAmount() - pointTransferTransaction.getPointTransferAmount());
			this.greenPointService.updateByPrimaryKey(sGreenPoint);
			
			GreenPoint tgCond = new GreenPoint();
			tgCond.setMemberNo(pointTransferTransaction.getPointReceiver());
			tgCond.setNodeType("1"); // 받는 쪽은 무조건 멤버 포인트로 적립시킴
			GreenPoint tGreenPoint = this.searchService.findGreenPoints(tgCond).get(0);
			tGreenPoint.setPointAmount(tGreenPoint.getPointAmount() + pointTransferTransaction.getPointTransferAmount());
			this.greenPointService.updateByPrimaryKey(tGreenPoint);
		}
		
		if (AppConstants.Point.RED_POINT.equals(pointTransferTransaction.getPointType())) {
			RedPoint srCond = new RedPoint();
			srCond.setMemberNo(pointTransferTransaction.getPointTransferer());
			RedPoint sRedPoint = this.searchService.findRedPoints(srCond).get(0);
			sRedPoint.setPointAmount(sRedPoint.getPointAmount() - pointTransferTransaction.getPointTransferAmount());
			this.redPointService.updateByPrimaryKey(sRedPoint);
			
			RedPoint trCond = new RedPoint();
			trCond.setMemberNo(pointTransferTransaction.getPointReceiver());
			RedPoint tRedPoint = this.searchService.findRedPoints(trCond).get(0);
			tRedPoint.setPointAmount(tRedPoint.getPointAmount() +  pointTransferTransaction.getPointTransferAmount());
			this.redPointService.updateByPrimaryKey(tRedPoint);
			
		}
		res.setMessage("포인트 이체 완료");
		res.setResultCode("100");
		return res;
	}

	@Override
	public int deleteByPrimaryKey(Integer pointTransferTransactionNo) {
		// TODO Auto-generated method stub
		return this.mapper.deleteByPrimaryKey(pointTransferTransactionNo);
	}

	@Override
	public int insert(PointTransferTransaction record) {
		// TODO Auto-generated method stub
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(PointTransferTransaction record) {
		// TODO Auto-generated method stub
		return this.mapper.insertSelective(record);
	}

	@Override
	public PointTransferTransaction selectByPrimaryKey(Integer pointTransferTransactionNo) {
		// TODO Auto-generated method stub
		return this.mapper.selectByPrimaryKey(pointTransferTransactionNo);
	}

	@Override
	public int updateByPrimaryKeySelective(PointTransferTransaction record) {
		// TODO Auto-generated method stub
		return this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(PointTransferTransaction record) {
		// TODO Auto-generated method stub
		return this.mapper.updateByPrimaryKey(record);
	}

	

}
