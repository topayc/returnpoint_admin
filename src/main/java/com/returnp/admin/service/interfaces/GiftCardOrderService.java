package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardOrder;

@Transactional
public interface GiftCardOrderService {
	public ReturnpBaseResponse createGiftCardOrder(GiftCardOrder order);
	public ReturnpBaseResponse deleteGiftCardOrder(GiftCardOrder order);
	public ReturnpBaseResponse updateGiftCardOrder(GiftCardOrder order);
}
