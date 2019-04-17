package com.returnp.admin.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.returnp.admin.model.DeviceInfo;
import com.returnp.admin.model.GiftCardIssue;
import com.returnp.admin.service.interfaces.AndroidPushService;

@Service
@PropertySource("classpath:/config.properties")
public class AndroidPushServiceImpl implements AndroidPushService {
	
	public  String fcmWebKey;
	@Autowired Environment env;
	
	
	@PostConstruct
	 public void init() {
		this.fcmWebKey = env.getProperty("fcm_key");
	 }
	
	@Override
	public String pushGiftCard(DeviceInfo deviceInfo, GiftCardIssue giftCardIssue) throws FirebaseMessagingException {
		System.out.println("#######################################################");
		System.out.println("AndroidPushServiceImpl.pushGiftCard");
		Message message = Message.builder()
			.putData("title", "리턴포인트 상품권 도착")
			.putData("pinNumber", giftCardIssue.getPinNumber())
			.putData("link", "")
			.putData("pushCode", "1")
			.putData("giftCardIssueNo", String.valueOf(giftCardIssue.getGiftCardIssueNo()))
			.putData("content", "리턴포인트 모바일 상품권이 도착했습니다")
			.setToken(deviceInfo.getPushKey())
			.build();
		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Push Return Value  : " + response);
		System.out.println("#######################################################");
		return response;
	}
}
