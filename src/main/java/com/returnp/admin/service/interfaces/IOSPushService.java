package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.DeviceInfo;
import com.returnp.admin.model.PointCodeIssue;

@Transactional
public interface IOSPushService {

	String push();

	String pushMessage(String string);

	String pushPointCode(DeviceInfo deviceInfo, PointCodeIssue pointCodeIssue);


}
