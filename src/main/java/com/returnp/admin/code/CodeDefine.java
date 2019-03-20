package com.returnp.admin.code;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;

import com.returnp.admin.dto.CodeKeyValuePair;
import com.returnp.admin.model.ApiService;
import com.returnp.admin.utils.Common;

public class CodeDefine {
	public static  ArrayList<CodeKeyValuePair> getNodeTypeList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "일반 회원", "Y", "Y", ""));
		list.add(new CodeKeyValuePair("2", "정회원", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "지사", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("4", "대리점", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("5", "협력 업체(가맹점)", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("6", "영업 관리자", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("7", "총판", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getPointBackStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "포인트백 시작 ", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "포인트 백 진행중", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "포인트 백 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("4", "포인트 백 취소 시작", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("5", "포인트 백 취소중", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("6", "포인트 백 취소 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("7", "중지", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getCategoryStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "사용", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "미사용", "Y", "Y" ,""));
		return list;
	}
	
	
	public static ArrayList<CodeKeyValuePair> getConversionStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "전환중 ", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "전환 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "전환 중지", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getAuthtypeList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "이메일 ", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "핸드폰(문자 인증)", "Y", "Y" ,""));
		return list;
	}

	public static ArrayList<CodeKeyValuePair> getKeywordTypeList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("memberEmail", "이메일 ", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("memberPhone", "전화번호", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("memberName", "이름", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getNodeStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "정상 ", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "등록 대기 ", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "미 인증", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("4", "인증 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("5", "사용 중지", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("6", "강제 탈퇴", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("7", "사용자 탈퇴", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("8", "사용자 삭제", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getPaymentStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "입금(결제) 확인중", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "입금(결제) 확인 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "입금(결제) 취소", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("4", "입금(결제) 환불 처리중", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("5", "입금(결제) 환불 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("6", "고객 입금 완료 확인 요청", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getVanPaymentStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "결제 승인", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "결제 승인 취소", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "결제 승인 오류", "Y", "Y" ,"승인 과정을 재 시도 해주세요"));
		return list;
	}
	
	
	public static ArrayList<CodeKeyValuePair> getPaymentTypeList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "온라인 입금 ", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "신용 카드 결제 ", "N", "N" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getPointTypes() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "GREEN 포인트", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "RED 포인트", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getRegistType() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("U", "사용자 등록", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("A", "관리자 등록", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getPointAccStatus() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "적립 가능", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "적립 불가", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getPointUseStatus() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "사용 가능", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "사용 불가", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getPaymentTransactionTypeList(){
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "QR CODE 매출 등록", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "VAN 매출 등록", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "관리자 수동 매출 등록", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("4", "온라인 매출 등록", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getOrderTypes(){
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("asc", "오름차순", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("desc", "내림차순", "Y", "Y" ,""));
		return list;
	}

	public static ArrayList<CodeKeyValuePair> getPaymentApprovalStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "결제 승인", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "결제 승인 취소", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "결제 승인 오류", "Y", "Y" ,"승인 과정을 재 시도 해주세요"));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getApiServiceStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "서비스 숭인", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "서비스 중지", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> geApiServiceTypes() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "쇼핑몰 연동", "Y", "Y" ,""));
	/*	list.add(new CodeKeyValuePair("2", "이체", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "기타", "Y", "Y" ,""));*/
		return list;
	}

	public static ArrayList<CodeKeyValuePair> getPointTransferTypeList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "선물", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "이체", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "기타", "Y", "Y" ,""));
		return list;
	}

	public static ArrayList<CodeKeyValuePair> getPointointTransferStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "송금 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "송금 실패", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "송금 취소", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getBoardTypeList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "공지", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "FAQ", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "일반 문의", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("4", "가맹 문의", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getCommonBoardCategories() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "일반", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "회원 정보", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "포인트", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("4", "기타", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getAffiliateBoardCategories() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "일반 회원", "Y", "Y", ""));
		list.add(new CodeKeyValuePair("2", "정회원", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "지사", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("4", "대리점", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("5", "협력 업체(가맹점)", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("6", "영업 관리자", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("7", "총판", "Y", "Y" ,""));
		return list;
	}
	
	
	public static ArrayList<CodeKeyValuePair> getBoardSearchKeywordTypeList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "제목", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "내용", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "작성자", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getBoardReplyStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "답변 대기", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "답변 환료", "Y", "Y" ,""));
		return list;
	}

	public static ArrayList<CodeKeyValuePair> getWithdrawalStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "출금 처리중", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "출금 처리 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "출금 보류", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("4", "유저 출금 취소", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("5", "관리자 출금 취소", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getPointTypeStatusLIst() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("R_PAY", "R_PAY", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("R_POINT", "R_POINT", "Y", "Y" ,""));
		return list;
	}

	public static ArrayList<CodeKeyValuePair> getMemberBankAccountStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("Y", "사용 가능", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("N", "사용 불가", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getBankList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("003", "기업은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("004", "국민은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("005", "외환은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("007", "수협", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("011", "농협", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("020", "우리은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("023", "제일은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("027", "씨티은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("031", "대구은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("032", "부산은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("034", "광주은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("035", "제주은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("037", "전북은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("039", "경남은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("045", "새마을금고", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("048", "신협", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("071", "우체국", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("081", "하나은행", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("088", "신한은행", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getAffiliateTypes() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("A001", "가맹점", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("A002", "제휴점", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("A003", "무사업자", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("A004", "온라인", "Y", "Y" ,""));
		return list;	
	}
	
	
	public static Object getApiServiceList(ApplicationContext applicationContext) {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		ArrayList<ApiService> apiList = Common.getApiServiceList(applicationContext);
		for(ApiService api : apiList) {
			list.add(new CodeKeyValuePair(api.getApiService(), api.getApiServiceName(), "Y", "Y" ,""));
		}
		return list;
	}

	public static Object getMarketerStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "정상", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "중지", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getProductStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "판매 중", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "판매 중지", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "재고 없음", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getGiftCardTypeList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "오프라인 - 실물 상품권", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "온라인 - 모바일 상품권", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getPurchaseMemberType() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "회원 구매", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "비회원 구매", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getGiftCardAccStatus() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "적립 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "미 적립", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getGiftCardUseStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "사용 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "미 사용", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getGiftCardStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "정상 사용", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "사용 중지", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getOrderStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "주문 접수 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "배송중", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "배송중", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("4", "배송 완료", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("5", "주문 취소", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getGiftCardOrderTypeList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "일반 구매", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "판매 조직 구매 ", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getSaleOrganCodes() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "일반 프랜차이즈", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "판매 조직 ", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getGiftCardSaleOrganDetailCodes() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("10", "본사", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("11", "총판 ", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("12", "판매점", "Y", "Y" ,""));
		return list;
	}
	
	public static ArrayList<CodeKeyValuePair> getGiftCardSaleOrganStatusList() {
		ArrayList<CodeKeyValuePair> list = new ArrayList<CodeKeyValuePair>();
		list.add(new CodeKeyValuePair("1", "정상", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("2", "중지", "Y", "Y" ,""));
		list.add(new CodeKeyValuePair("3", "보류", "Y", "Y" ,""));
		return list;
	}
}	
