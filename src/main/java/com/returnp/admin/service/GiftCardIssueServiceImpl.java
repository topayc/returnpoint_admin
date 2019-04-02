package com.returnp.admin.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.SplittableRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.GiftCardIssueMapper;
import com.returnp.admin.dao.mapper.GiftCardOrderMapper;
import com.returnp.admin.dto.command.GiftCardOrderCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardIssue;
import com.returnp.admin.service.interfaces.GiftCardIssueService;
import com.returnp.admin.service.interfaces.GiftCardOrderService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.utils.FileManager;

@Service
public class GiftCardIssueServiceImpl implements GiftCardIssueService{

	@Autowired SearchService searchService;;
	@Autowired GiftCardIssueMapper  giftCardIssueMapper;
	@Autowired GiftCardOrderService giftCardOrderService;
	
	
	@Override
	public ReturnpBaseResponse createGiftCardIssue(GiftCardIssue record) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReturnpBaseResponse createBatchGiftCardIssue(int giftCardOrderNo) {
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		char[] PIN_CHARACTERS  = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		Collections.shuffle(Arrays.asList(PIN_CHARACTERS));
		try {
			GiftCardOrderCommand giftCardOrder = new GiftCardOrderCommand();
			giftCardOrder.setOrderNo(giftCardOrderNo);
			ArrayList<GiftCardOrderCommand> giftOrders = this.searchService.selectGiftCardOrderCommands(giftCardOrder);
			if (giftOrders.size() != 1) {
				ResponseUtil.setResponse(res, "405", "요청한 상품권 발주 내역이 존재하지 않습니다");
			}
			giftCardOrder = giftOrders.get(0);
			
			int remainCount = giftCardOrder.getQty();
			int batchCount = (int)Math.ceil((float)giftCardOrder.getQty() / 1000 );
			int rowCount= giftCardOrder.getQty() <= 1000 ?  giftCardOrder.getQty() : 1000;
			
		/*	System.out.println("상품권 카운처");
			System.out.println(batchCount);
			System.out.println(rowCount);*/
			
			HashMap<String, Object> insertBatchMap = new HashMap<String, Object>();
			ArrayList<GiftCardIssue> issueList = new ArrayList<GiftCardIssue>();
			GiftCardIssue issue = null;
			
			char[] pinCharArrs = new char[16];
			SplittableRandom splittableRandom = null;
			Calendar cal = new GregorianCalendar(Locale.KOREA);
			Date issueDate = new Date();
			cal.setTime(issueDate);
			cal.add(Calendar.YEAR, 3);
			Date expirationDate = cal.getTime();
			
			for (int i = 0 ; i < batchCount; i++) {
				for (int j = 0 ; j < rowCount ; j++) {
					issue = new GiftCardIssue();
					issue.setGiftCardOrderNo(giftCardOrder.getOrderNo());
					issue.setGiftCardNo(giftCardOrder.getGiftCardNo());
					
					splittableRandom = new SplittableRandom();
					for (int k = 0; k < pinCharArrs.length; k++) {
						int elementIndex = splittableRandom.nextInt(PIN_CHARACTERS.length);
						pinCharArrs[k] = PIN_CHARACTERS[elementIndex];
					}
					issue.setPinNumber(String.valueOf(pinCharArrs));
					issue.setAccableStatus("Y");
					issue.setPayableStatus("Y");
					issue.setGiftCardStatus("1");
					issue.setGiftCardType(giftCardOrder.getGiftCardType());
					issue.setGiftCardAmount(giftCardOrder.getGiftCardAmount());
					issue.setGiftCardSalePrice(giftCardOrder.getGiftCardSalePrice());
					issue.setAccQrData("999999999");
					issue.setPayQrData("999999999");
					issue.setAccQrScanner(null);
					issue.setPayQrScanner(null);
					issue.setAccQrScanTime(null);
					issue.setPayQrScanTime(null);
					issue.setIssueTime(issueDate);
					issue.setExpirationTime(expirationDate);
					issueList.add(issue);
				}
				insertBatchMap.put("giftCardIssueList", issueList);
				giftCardIssueMapper.insertBatch(insertBatchMap);
				
				remainCount -= rowCount ;
				rowCount= remainCount <= 1000 ?  remainCount : 1000;

				issueList.clear();
				issueList.trimToSize();
				insertBatchMap.clear();
			}
			/* 해당 주문의 상품권 발행 상태를 발행완료 변경*/
			giftCardOrder.setIssueStatus("3");
			this.giftCardOrderService.updateGiftCardOrder(giftCardOrder);
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 발행 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 발행 에러");
			return res;
		}
	}
	
	@Override
	public ReturnpBaseResponse invalidate(int giftCardOrderNo) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			GiftCardOrderCommand giftCardOrder = new GiftCardOrderCommand();
			giftCardOrder.setOrderNo(giftCardOrderNo);
			ArrayList<GiftCardOrderCommand> giftOrders = this.searchService.selectGiftCardOrderCommands(giftCardOrder);
			if (giftOrders.size() != 1) {
				ResponseUtil.setResponse(res, "405", "요청한 상품권 발주 내역이 존재하지 않습니다");
			}
			giftCardOrder = giftOrders.get(0);
			this.giftCardIssueMapper.deleteByGiftCardOrderNo(giftCardOrderNo);
			/* 해당 주문의 상품권 발행 상태를 발행 취소로 변경*/
			giftCardOrder.setIssueStatus("4");
			this.giftCardOrderService.updateGiftCardOrder(giftCardOrder);
			ResponseUtil.setSuccessResponse(res, "100" , "주문에 대한 발행 상품권 일괄 취소 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "일괄 취소 에러");
			return res;
		}
	};
	
	@Override
	public ReturnpBaseResponse deleteGiftCardIssue(GiftCardIssue record) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReturnpBaseResponse updateGiftCardIssue(GiftCardIssue record) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
