package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IOSPushService {

	String push();

}
