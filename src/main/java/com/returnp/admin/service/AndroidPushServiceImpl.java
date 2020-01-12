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
import com.returnp.admin.model.PointCodeIssue;
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
	public String pushGiftCard(DeviceInfo deviceInfo, GiftCardIssue giftCardIssue,  int myGiftCardNo) throws FirebaseMessagingException {
		Message message = Message.builder()
			.putData("title", "R 포인트상품권 도착")
			.putData("pinNumber", giftCardIssue.getPinNumber())
			.putData("link", "")
			.putData("pushCode", "1")
			.putData("giftCardIssueNo", String.valueOf(giftCardIssue.getGiftCardIssueNo()))
			.putData("myGiftCardNo", String.valueOf(myGiftCardNo))
			.putData("content", "R 포인트 모바일 상품권이 도착했습니다")
			.setToken(deviceInfo.getPushKey())
			.build();
		String response = FirebaseMessaging.getInstance().send(message);
		return response;
	}

	@Override
	public String pushMessage(String pushToken, String title, String content, String pushCode, String link) throws FirebaseMessagingException {
		Message message = Message.builder()
				.setToken(pushToken)
				.putData("title", title)
				.putData("content", content)
				.putData("pushCode",pushCode)
				.putData("link", link)
				.build();
			String response = FirebaseMessaging.getInstance().send(message);
			return response;
	}

	@Override
	public String pushPointCode(DeviceInfo deviceInfo, PointCodeIssue pointCodeIssue) throws FirebaseMessagingException {
		Message message = Message.builder()
				.putData("title", "포인트 코드 적립 코드 등록")
				.putData("link", "/m/pointCoupon/index.do")
				.putData("pushCode", "2")
				.putData("content", "일반 영수증 적립 코드가 회원님의 계정으로 등록되었습니다.")
				.setToken(deviceInfo.getPushKey())
				.build();
			String response = FirebaseMessaging.getInstance().send(message);
			//System.out.println("Push Return Value  : " + response);
			//System.out.println("#######################################################");
			return response;
	}


}
