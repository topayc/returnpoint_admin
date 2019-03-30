package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCard;;

@Transactional
public interface GiftCardSearvice {
	public ReturnpBaseResponse createGiftCard(GiftCard giftCard, String saveDir, String webPath);
	public ReturnpBaseResponse updateGiftCard(GiftCard giftCard, String saveDir, String webPath);
	public ReturnpBaseResponse deleteGiftCard(GiftCard giftCard);
	
}
