package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardSalesOrgan;

@Transactional
public interface GiftCardSalesOrganService {
	public ReturnpBaseResponse createGiftCardSalesOrgan(GiftCardSalesOrgan record);
	public ReturnpBaseResponse deleteGiftCardSalesOrgan(GiftCardSalesOrgan record);
	public ReturnpBaseResponse updateGiftCardSalesOrgan(GiftCardSalesOrgan record);
}
