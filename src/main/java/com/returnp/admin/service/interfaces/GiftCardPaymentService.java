package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardIssue;
import com.returnp.admin.model.GiftCardPayment;

@Transactional
public interface GiftCardPaymentService {
	public ReturnpBaseResponse createGiftCardPayment(GiftCardPayment record);
	public ReturnpBaseResponse deleteGiftCardPayment(GiftCardPayment record);
	public ReturnpBaseResponse updateGiftCardPayment(GiftCardPayment record);
	public ReturnpBaseResponse changeGiftCardStatus(int giftCardPaymentNo, String refundStatus);
}
