package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.returnp.admin.model.DeviceInfo;
import com.returnp.admin.model.GiftCardIssue;

@Transactional
public interface AndroidPushService {
	public String pushGiftCard(DeviceInfo deviceInfo, GiftCardIssue giftCardIssueCommand) throws FirebaseMessagingException;
}
