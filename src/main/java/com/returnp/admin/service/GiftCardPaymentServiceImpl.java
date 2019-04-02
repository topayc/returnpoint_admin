package com.returnp.admin.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.GiftCardPaymentMapper;
import com.returnp.admin.dto.GiftCardOrderForm;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCard;
import com.returnp.admin.model.GiftCardOrder;
import com.returnp.admin.model.GiftCardPayment;
import com.returnp.admin.model.GiftCardSalesOrgan;
import com.returnp.admin.service.interfaces.GiftCardPaymentService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class GiftCardPaymentServiceImpl implements GiftCardPaymentService{

	@Autowired SearchService searchService;;
	@Autowired GiftCardPaymentMapper giftCardPaymentMapper;
	@Override
	public ReturnpBaseResponse createGiftCardPayment(GiftCardPayment record) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReturnpBaseResponse deleteGiftCardPayment(GiftCardPayment record) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReturnpBaseResponse updateGiftCardPayment(GiftCardPayment record) {
		// TODO Auto-generated method stub
		return null;
	};
}
