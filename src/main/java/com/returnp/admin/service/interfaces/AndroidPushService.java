package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.returnp.admin.model.DeviceInfo;
import com.returnp.admin.model.GiftCardIssue;
import com.returnp.admin.model.PointCodeIssue;

@Transactional
public interface AndroidPushService {
	public String pushGiftCard(DeviceInfo deviceInfo, GiftCardIssue giftCardIssueCommand, int myGiftCardNo) throws FirebaseMessagingException;

	public String pushPointCode(DeviceInfo deviceInfo, PointCodeIssue pointCodeIssue) throws FirebaseMessagingException;

	public String pushMessage(String pushToken, String title, String content, String pushCode, String link) throws FirebaseMessagingException;

}
