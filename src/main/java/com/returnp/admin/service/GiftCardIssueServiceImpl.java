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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.DeviceInfoMapper;
import com.returnp.admin.dao.mapper.GiftCardIssueMapper;
import com.returnp.admin.dao.mapper.MemberConfigMapper;
import com.returnp.admin.dao.mapper.MyGiftCardMapper;
import com.returnp.admin.dao.mapper.SearchMapper;
import com.returnp.admin.dto.command.GiftCardIssueCommand;
import com.returnp.admin.dto.command.GiftCardOrderCommand;
import com.returnp.admin.dto.command.MemberCommand;
import com.returnp.admin.dto.command.MyGiftCardCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.DeviceInfo;
import com.returnp.admin.model.GiftCardIssue;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MemberConfig;
import com.returnp.admin.service.interfaces.AndroidPushService;
import com.returnp.admin.service.interfaces.GiftCardIssueService;
import com.returnp.admin.service.interfaces.GiftCardOrderService;
import com.returnp.admin.service.interfaces.IOSPushService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.utils.BASE64Util;
import com.returnp.admin.utils.QRManager;

@Service
public class GiftCardIssueServiceImpl implements GiftCardIssueService{

	@Autowired SearchService searchService;;
	@Autowired GiftCardIssueMapper  giftCardIssueMapper;
	@Autowired GiftCardOrderService giftCardOrderService;
	@Autowired DeviceInfoMapper deviceInfoMapper;
	@Autowired SearchMapper searchMapper;;
	@Autowired AndroidPushService androidPushService;
	@Autowired IOSPushService iosPushService;
	@Autowired MyGiftCardMapper myGiftCardMapper;
	@Autowired MemberConfigMapper memberConfigMapper;;
	
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
				throw new ReturnpException(res);
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
			JSONObject arDataJson = null;
			
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
					issue.setDeliveryStatus("4");
					
					arDataJson = new JSONObject();
					arDataJson.put("qr_cmd", QRManager.QRCmd.ACC_BY_GIFTCARD);
					arDataJson.put("pinNumber", issue.getPinNumber());
					issue.setAccQrData(BASE64Util.encodeString(arDataJson.toString()));
					
					arDataJson.put("qr_cmd", QRManager.QRCmd.PAY_BY_GIFTCARD);
					issue.setPayQrData(BASE64Util.encodeString(arDataJson.toString()));
					
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
		}catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
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
				throw new ReturnpException(res);
			}
			
			giftCardOrder = giftOrders.get(0);
			this.giftCardIssueMapper.deleteByGiftCardOrderNo(giftCardOrderNo);
			/* 해당 주문의 상품권 발행 상태를 발행 취소로 변경*/
			giftCardOrder.setIssueStatus("4");
			this.giftCardOrderService.updateGiftCardOrder(giftCardOrder);
			ResponseUtil.setSuccessResponse(res, "100" , "주문에 대한 발행 상품권 일괄 취소 완료");
			return res;
		} catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "일괄 취소 에러");
			return res;
		}
	}
	
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
	@Override
	public ReturnpBaseResponse createQrImage(int giftCardIssueNo, String type, String filePath, String webPath) {
		/*	
	 	System.out.println("파일 경로");
		System.out.println(filePath);
		System.out.println(type);
		*/
		
		ObjectResponse<GiftCardIssueCommand> res = new ObjectResponse<GiftCardIssueCommand>();
		try {
			GiftCardIssueCommand command = new GiftCardIssueCommand();
			command.setGiftCardIssueNo(giftCardIssueNo);
			ArrayList<GiftCardIssueCommand> commandList = this.searchService.selectGiftCardIssueCommands(command);
			if (commandList.size() != 1) {
				ResponseUtil.setResponse(res, "405", "해당 상품권이 존재하지 않습니다");
				throw new ReturnpException(res);
			}
			
			command = commandList.get(0);
			String qrData = type.equals("A") ? command.getAccQrData() : command.getPayQrData();
			String fileName = type.equals("A") ? "gift_card_acc_qr" : "gift_card_pay_gr";
			String qrWebPath = QRManager.genQRCode(filePath ,  webPath, qrData, fileName );
			
			if (type.equals("A")) {
				command.setAccQrCodeWebPath(qrWebPath );
			}else {
				command.setPayQrCodeWebPath(qrWebPath );
			}
			
			/*QR 웹 경로 정보 업데이트*/
			this.giftCardIssueMapper.updateByPrimaryKey(command);
			res.setData(command);
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 QR 이미지 생성 완료");
			return res;
			
		}catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 QR 이미지 생성 에러");
			return res;
		}
	}
	
	@Override
	public ReturnpBaseResponse downQrCoder(int giftCardIssueNo, String type) {
		ObjectResponse<byte[]> res = new ObjectResponse<byte[]>();
		try {
			GiftCardIssueCommand command = new GiftCardIssueCommand();
			command.setGiftCardIssueNo(giftCardIssueNo);
			ArrayList<GiftCardIssueCommand> commandList = this.searchService.selectGiftCardIssueCommands(command);
			if (commandList.size() != 1) {
				ResponseUtil.setResponse(res, "405", "해당 상품권이 존재하지 않습니다");
				throw new ReturnpException(res);
			}
			
			command = commandList.get(0);
			String qrData = type.equals("A") ? command.getAccQrData() : command.getPayQrData();
			byte[] qrCodeBytes = QRManager.genQRCode(qrData);
			res.setData(qrCodeBytes);
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 QR 이미지 생성 완료");
			return res;
			
		}catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 QR 이미지 생성 에러");
			return res;
		}
	}
	
	@Override
	public ReturnpBaseResponse createQrImageBatch(ArrayList<Integer> giftCardIssueNos, HttpServletRequest request,
			HttpServletResponse response) {
		ArrayListResponse<GiftCardIssueCommand> res = new ArrayListResponse<GiftCardIssueCommand>();
		ArrayList<GiftCardIssueCommand> datas = new ArrayList<GiftCardIssueCommand>();
		String filePath = null;
		String webPath  = null;
		String payQrData = null;
		String accQrData = null;
		String fileName = null;
		
		GiftCardIssueCommand issue;
		ArrayList<GiftCardIssueCommand> issuseCommandList = null;
		try {
			for (int giftCardIssueNo : giftCardIssueNos) {
				
				
				issue = new GiftCardIssueCommand();
				issue.setGiftCardIssueNo(giftCardIssueNo);
				issuseCommandList=  this.searchService.selectGiftCardIssueCommands(issue);
				
				if (issuseCommandList.size() != 1) {
					ResponseUtil.setResponse(res, "3097", "해당 상품권이 존재하지 않습니다");
					throw new ReturnpException(res);
				}
				issue = issuseCommandList.get(0);
				
				filePath = request.getSession().getServletContext().getRealPath("/gift_qr/" + giftCardIssueNo);
				webPath = "/gift_qr/" + giftCardIssueNo;
				
				/* 적립큐알 이미지가 없을 경우 적립 QR 코드 이미지 생성 */
				if (issue.getAccQrCodeWebPath() == null || "".equals(issue.getAccQrCodeWebPath().trim())) {
					accQrData = issue.getAccQrData();
					fileName = "gift_card_acc_qr";
					issue.setAccQrCodeFilePath(filePath + giftCardIssueNo + File.separator + fileName + ".png");
					issue.setAccQrCodeWebPath(QRManager.genQRCode(filePath ,  webPath, accQrData, fileName ));
				}
				
				/* 결제 큐알 이미지가 없을 경우 결제 QR 코드 이미지 생성*/
				if (issue.getPayQrCodeWebPath() == null || "".equals(issue.getPayQrCodeWebPath().trim())) {
					payQrData = issue.getPayQrData();
					fileName = "gift_card_pay_qr";
					issue.setPayQrCodeFilePath(filePath + giftCardIssueNo + File.separator + fileName + ".png");
					issue.setPayQrCodeWebPath( QRManager.genQRCode(filePath ,  webPath, payQrData, fileName ));
				}
				
				this.giftCardIssueMapper.updateByPrimaryKey(issue);
				datas.add(issue);
			}
			res.setRows(datas);
			res.setTotal(datas.size());
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 QR 이미지 일괄 생성 완료");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 QR 이미지 생성 에러");
			return res;
		}
	}
	
	@Override
	public ReturnpBaseResponse changeGiftCardStatus(int giftCardIssueNo, String giftCardStatus) {
		
		ObjectResponse<GiftCardIssueCommand> res = new ObjectResponse<GiftCardIssueCommand>();
		try {
			GiftCardIssueCommand command = new GiftCardIssueCommand();
			command.setGiftCardIssueNo(giftCardIssueNo);
			ArrayList<GiftCardIssueCommand> commandList = this.searchService.selectGiftCardIssueCommands(command);
			if (commandList.size() != 1) {
				ResponseUtil.setResponse(res, "3401", "해당 상품권이 존재하지 않습니다");
				throw new ReturnpException(res);
			}
			
			commandList.get(0).setGiftCardStatus(giftCardStatus);
			
			this.giftCardIssueMapper.updateByPrimaryKey(commandList.get(0));
		/*	int affectedRow = this.giftCardIssueMapper.updateByPrimaryKey(command);
			
			if (affectedRow < 0) {
				ResponseUtil.setResponse(res, "500", "상품권 상태 변경 에러");
				throw new ReturnpException(res);
			}*/
			res.setData(commandList.get(0));
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 상태 변경 완료");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 상태 변경 에러");
			return res;
		}
	}
	@Override
	public ReturnpBaseResponse sendGiftCardByMobile(ArrayList<String> pinNumbers, String receiverPhone) {
		ArrayListResponse<GiftCardIssueCommand> res = new ArrayListResponse<GiftCardIssueCommand>();
		try {
			Member m = new Member();
			m.setMemberPhone(receiverPhone);
			ArrayList<Member> memebrs = this.searchService.findMembers(m); 
			if (memebrs.size() != 1) {
				ResponseUtil.setResponse(res, "3502", "발송 번호의 회원이 존재하지 않습니다.</br>리턴 포인트 회원에게만 발송할 수 있습니다");
				throw new ReturnpException(res);
			}
			
			DeviceInfo deviceInfo = new DeviceInfo();
			deviceInfo.setMemberNo(memebrs.get(0).getMemberNo());
			ArrayList<DeviceInfo> deviceInfos = this.searchMapper.selectDeviceInfos(deviceInfo);
			if (deviceInfos.size() != 1) {
				ResponseUtil.setResponse(res, "3502", "해당 회원의 디바이스 정보가 존재하지 않습니다. </br> 리턴포인트 앱의 업데이트 혹은 앱내의 푸쉬 서비스를 활성화 해주세요");
				throw new ReturnpException(res);
			}
			
			MemberConfig memberConfig = new MemberConfig();
			memberConfig.setMemberNo(memebrs.get(0).getMemberNo());
			ArrayList<MemberConfig> memberConfigs = this.searchService.selectMemberConfigs(memberConfig);
			
			if (memberConfigs.size()< 1) {
				memberConfig.setDevicePush("N");
				memberConfig.setEmailReceive("N");
				this.memberConfigMapper.insert(memberConfig);
			}else {
				memberConfig = memberConfigs.get(0);
			}
			
			if (memberConfig.getDevicePush().equals("N")) {
				ResponseUtil.setResponse(res, "3908", "해당 회원은 푸쉬 알림을 허용하지 않았습니다. </br>앱에서 푸쉬 알림을 허용한 후 다시 시도해주세요");
				throw new ReturnpException(res);
			}
			
			String pushReturn = "";
			deviceInfo = deviceInfos.get(0);
		
			/* 
			 * 해당 상품권 배송 정보 및 기타 정보를 업데이트 함
			 * */
			GiftCardIssueCommand giftCardCommand = new GiftCardIssueCommand();
			MemberCommand memberCommand;
			ArrayList<GiftCardIssueCommand> sendGifts = null;
			ArrayList<MemberCommand> memberCommands = null;
			
			ArrayList<GiftCardIssueCommand> returnList = new ArrayList<GiftCardIssueCommand>();
			ArrayList<MyGiftCardCommand> myGiftCardCommands  = null;
			MyGiftCardCommand  myGiftCardCommand = null;
			for (String pinNumber : pinNumbers) {
				giftCardCommand.setPinNumber(pinNumber);
				sendGifts = this.searchService.selectGiftCardIssueCommands(giftCardCommand);
				
				if (sendGifts.size() == 1) {
					sendGifts.get(0).setReceiverPhone(receiverPhone);
					sendGifts.get(0).setDeliveryStatus("6");
					sendGifts.get(0).setReceiverPhone(receiverPhone);
					
					memberCommand = new MemberCommand();
					memberCommand.setMemberPhone(receiverPhone);
					memberCommands = this.searchService.findMemberCommands(memberCommand);
				
					if (memberCommands.size() == 1) {
						sendGifts.get(0).setReceiverIsMember("Y");
						sendGifts.get(0).setReceiverName(memberCommands.get(0).getMemberName());
						sendGifts.get(0).setReceiverEmail(memberCommands.get(0).getMemberEmail());
					}else {
						sendGifts.get(0).setReceiverIsMember("N");
					}
					
					this.giftCardIssueMapper.updateByPrimaryKeySelective(sendGifts.get(0));
					
					/* MyGiftCard 정보 기록*/
					myGiftCardCommand = new MyGiftCardCommand();
					myGiftCardCommand.setMemberNo(memberCommands.get(0).getMemberNo());
					myGiftCardCommand.setGiftCardIssueNo(sendGifts.get(0).getGiftCardIssueNo());
					
					myGiftCardCommands = this.searchMapper.selectMyGiftCards(myGiftCardCommand);
					if (myGiftCardCommands.size()  < 1) {
						this.myGiftCardMapper.insert(myGiftCardCommand);
					}else {
						myGiftCardCommand = myGiftCardCommands.get(0);
					}
					
					memberCommands = null;
					memberCommand = null;
					returnList.add(sendGifts.get(0));
					
				
					/* 선택한 상품권 푸쉬 발송*/
					if (deviceInfo.getOs().equalsIgnoreCase("android")) {
						pushReturn = androidPushService.pushGiftCard(deviceInfo, sendGifts.get(0), myGiftCardCommand.getMyGiftCardNo());
					}else  if (deviceInfo.getOs().equalsIgnoreCase("apple")) {
						pushReturn = iosPushService.push();
					}
					
					if (pushReturn == null) {
						ResponseUtil.setResponse(res, "3512", receiverPhone + " 으로 상품권 발송을 실패했습니다.");
						throw new ReturnpException(res);
					}
				}
			}
			
			res.setRows(returnList);
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 모바일 전송 완료");
			return res;
			
		}catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 모바일 전송 에러");
			return res;
		}
	}


}
