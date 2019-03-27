package com.returnp.admin.service.interfaces;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.GiftCardOrderForm;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardOrder;

@Transactional
public interface GiftCardOrderService {
	public ReturnpBaseResponse createGiftCardOrder(GiftCardOrderForm orderForm);
	public ReturnpBaseResponse deleteGiftCardOrder(GiftCardOrder order);
	public ReturnpBaseResponse updateGiftCardOrder(GiftCardOrder order);
}
