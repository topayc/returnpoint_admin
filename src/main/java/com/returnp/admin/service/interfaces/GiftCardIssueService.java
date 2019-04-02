package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardIssue;

@Transactional
public interface GiftCardIssueService {
	public ReturnpBaseResponse createGiftCardIssue(GiftCardIssue record);
	public ReturnpBaseResponse createBatchGiftCardIssue(int giftCardOrderNo);
	public ReturnpBaseResponse deleteGiftCardIssue(GiftCardIssue record);
	public ReturnpBaseResponse updateGiftCardIssue(GiftCardIssue record);
	public ReturnpBaseResponse invalidate(int giftCardOrderNo);
}
