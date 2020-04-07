package com.returnp.admin.service.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;

@Transactional
public interface ShopService {

	ReturnpBaseResponse selectOrderReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse selectPeriodOrderReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse loadOrders(HashMap<String, Object> params);

	ReturnpBaseResponse deleteOrder(HashMap<String, Object> params);


	ReturnpBaseResponse changeOrderStatuses(ArrayList<Integer> shopProductOrderNos, String status);
	


}
