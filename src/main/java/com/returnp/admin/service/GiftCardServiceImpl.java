package com.returnp.admin.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.GiftCardMapper;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCard;
import com.returnp.admin.service.interfaces.GiftCardSearvice;
import com.returnp.admin.utils.FileManager;

@Service
public class GiftCardServiceImpl implements GiftCardSearvice {

	@Autowired GiftCardMapper GiftCardMapper;

	@Override
	public ReturnpBaseResponse createGiftCard(GiftCard giftCard, String saveDir, String webPath) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		File file = null;
		try {
			if (giftCard.getProductImg1().isEmpty() == false ) {
				file = FileManager.saveProductImange(giftCard.getProductImg1(), saveDir);
				giftCard.setGiftCardImgPath1(webPath + "/" + file.getName());
			}
			
			if (giftCard.getProductImg2().isEmpty() == false ) {
				file = FileManager.saveProductImange(giftCard.getProductImg2(), saveDir);
				giftCard.setGiftCardImgPath2(webPath + "/" + file.getName());
			}
			giftCard.setGiftCardCategory("상품권");
			int affectedRow = this.GiftCardMapper.insert(giftCard);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품 생성 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품 생성 에러");
			return res;
		}
	}
	
	@Override
	public ReturnpBaseResponse updateGiftCard(GiftCard giftCard, String saveDir, String webPath) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		File file = null;
		try {
			if (giftCard.getProductImg1().isEmpty() == false ) {
				file = FileManager.saveProductImange(giftCard.getProductImg1(), saveDir);
				giftCard.setGiftCardImgPath1(webPath + "/" + file.getName());
			}
			
			if (giftCard.getProductImg2().isEmpty() == false ) {
				file = FileManager.saveProductImange(giftCard.getProductImg2(), saveDir);
				giftCard.setGiftCardImgPath2(webPath + "/" + file.getName());
			}
			giftCard.setGiftCardCategory("상품권");
			int affectedRow = this.GiftCardMapper.updateByPrimaryKey(giftCard);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품 수정 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품 수정 에러");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse deleteGiftCard(GiftCard giftCard) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			this.GiftCardMapper.deleteByPrimaryKey(giftCard.getGiftCardNo());
			ResponseUtil.setResponse(res, "100" , "상품 삭제 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품 삭제 에러");
			return res;
		}
	}

}
