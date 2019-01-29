package com.returnp.admin.service.interfaces;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PointTransferTransaction;

public interface PointTransferTransactionServcie {
   
	public ReturnpBaseResponse createPointTransferTransaction(PointTransferTransaction pointTransferTransaction);
	int deleteByPrimaryKey(Integer pointTransferTransactionNo);

    int insert(PointTransferTransaction record);

    int insertSelective(PointTransferTransaction record);

    PointTransferTransaction selectByPrimaryKey(Integer pointTransferTransactionNo);

    int updateByPrimaryKeySelective(PointTransferTransaction record);

    int updateByPrimaryKey(PointTransferTransaction record);
}
