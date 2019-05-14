package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dto.GiftCardOrderForm;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardOrder;
import com.returnp.admin.model.GreenPoint;

@Transactional
public interface GiftCardOrderService {
	public ReturnpBaseResponse createGiftCardOrder(GiftCardOrderForm orderForm);
	public ReturnpBaseResponse deleteGiftCardOrder(GiftCardOrder order);
	public ReturnpBaseResponse updateGiftCardOrder(GiftCardOrder order);
	public void increaseGiftCardPoint( int memberNo, int nodeNo, String nodeType, String nodeTypeName, int amount, float giftCardAccRate) throws ReturnpException; 
	public GreenPoint createRecommenderRPoint(int memberNo);
}
