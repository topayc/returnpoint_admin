package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardOrderItem;

@Transactional
public interface GiftCardOrderItemService {
	public ReturnpBaseResponse createGiftCardOrderItem(GiftCardOrderItem orderItem);
	public ReturnpBaseResponse deleteGiftCardOrderItem(GiftCardOrderItem orderItem);
	public ReturnpBaseResponse updateGiftCardOrderItem(GiftCardOrderItem orderItem);
}
