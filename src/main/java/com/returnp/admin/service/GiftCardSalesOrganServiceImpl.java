package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.GiftCardSalesOrganMapper;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardSalesOrgan;
import com.returnp.admin.service.interfaces.GiftCardSalesOrganService;

@Service
public class GiftCardSalesOrganServiceImpl implements GiftCardSalesOrganService {
	
	@Autowired GiftCardSalesOrganMapper organMapper;
	@Override
	public ReturnpBaseResponse createGiftCardSalesOrgan(GiftCardSalesOrgan record) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			int affectedRow  = this.organMapper.insert(record);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 조직 생성 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 조직 생성 에러");
			return res;
		}
	}
	

	@Override
	public ReturnpBaseResponse updateGiftCardSalesOrgan(GiftCardSalesOrgan record) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			int affectedRow  = this.organMapper.updateByPrimaryKey(record);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 조직 수정 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 조직 수정 생성 에러");
			return res;
		}
	}
	
	@Override
	public ReturnpBaseResponse deleteGiftCardSalesOrgan(GiftCardSalesOrgan record) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			int affectedRow  = this.organMapper.deleteByPrimaryKey(record.getGiftCardSalesOrganNo());
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 조직 삭제 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 조직 삭제에러");
			return res;
		}
	}
}
