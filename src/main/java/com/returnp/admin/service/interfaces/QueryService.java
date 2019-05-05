package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.AffiliateTid;
import com.returnp.admin.model.GreenPoint;

@Transactional
public interface QueryService {
	int deleteAffiliateTid(AffiliateTid affiliateTid);

	int deleteGPoint(GreenPoint gPoint);
	
}
