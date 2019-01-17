package com.returnp.admin.common;

public class AppConstants {
	public static String ADMIN_SESSION = "adminSession";

	public static class NodeType {
		public static final String MEMBER = "1";
		public static final String RECOMMENDER = "2";
		public static final String BRANCH = "3";
		public static final String AGENCY = "4";
		public static final String AFFILIATE = "5";
		public static final String SALE_MANAGER = "6";
		public static final String SOLE_DIST = "7";
	}

	public static class GreenPointAccStatus {
		public static String POINTBACK_START  = "1";
		public static String POINTBACK_PROGRESS  = "2";
		public static String POINTBACK_COMPLETE  = "3";
		public static String POINTBACK_CANCEL_START= "4";
		public static String POINTBACK_CANCEL_PROGRESS= "5";
		public static String POINTBACK_CANCEL_COMPLETE  = "6";
		public static String POINTBACK_STOP= "7";
		public static String POINTBACK_CANCEL_STOP= "8";
		public static String POINTBACK_REQ_ERROR= "9";
	
	}

	public static class PaymentApprovalStatus {
		public static String PAYMENT_APPROVAL_OK = "1";
		public static String PAYMENT_APPROVAL_CANCEL = "2";
		public static String PAYMENT_APPROVAL_ERROR = "3";
	}

	public static class CategoryStatus {
		public static String USE = "1";
		public static String NOT_USE = "2";

	}

	public static class ConversionStatus {
		public static String PROGRESS = "1";
		public static String STOP = "2";
		public static String COMPLETE = "3";
	}

	public static class ResponsResultCode {
		public static String SUCCESS = "100";
		public static String ERROR = "101";
		public static String FAILED = "102";
	}

	public static class ResponseResult {
		public static String SUCCESS = "success";
		public static String ERROR = "error";
		public static String FAILED = "failed";
	}

	public static class KeywordType {
		public static String EMAIL = "1";
		public static String PHONE = "2";
		public static String NAME = "3";
	}

	public static class ReigistType {
		public static String REGIST_BY_ADMIN = "A";
		public static String REGIST_BY_USER = "U";
	}

	public static class PaymentType {
		public static String PAYMENT_ONLINE = "1";
		public static String PAYMENT_CREDIT = "2";
	}

	/* 고객이 가맹점에서 결제한 수단 */
	public static class AffiliatePaymentType {
		public static String CREDIT = "1";
		public static String CASH = "2";
	}

	public static class PointAccStatus {
		public static String ACC_OK = "Y";
		public static String ACC_NO = "N";
	}

	public static class PointUseStatus {
		public static String USE_OK = "Y";
		public static String USE_NO = "N";
	}

	public static class NodeStatus {
		public static String NORMAL = "1";
		public static String REG_WAIT = "2";
		public static String AUTO_NOT = "3";
		public static String AUTH_OK = "4";
		public static String USE_STOP = "5";
		public static String USE_WITHDRAWAL = "6";
		public static String ADMIN_WITHDRAWAL = "7";
	}

	public static class PaymentStatus {
		public static String PAYMENT_CHECK = "1";
		public static String PAYMENT_OK = "2";
		public static String PAYMENT_CANCEL = "3";
		public static String PAYMENT_CANCEL_PROCESSING = "4";
		public static String PAYMENT_CANCEL_PROCESSING_COMPLETER = "5";
	}

	public static class PaymentTransactionType{
		public static final String  QR = "1";
		public static final  String  VAN= "2";
		public static final  String  ADMIN= "3";
		public static final  String  SHOPPING_MAL= "4";
	}

	public static class VanPaymentStatus {
		public static String VAN_AUTO_REGIST = "1";
		public static String ADMIN_MANUAL_REGIST = "2";
	}

	public static class Point {
		public static String GREEN_POINT = "1";
		public static String RED_POINT = "2";
	}

	public static class PointType {
		public static String R_PAY = "R_PAY";
		public static String R_POINT = "R_POINT";
	}

	public static class WithDrawalStatus {
		public static String WITHDRAWAL_PROCESSING = "1";
		public static String WITHDRAWAL_COMPLETED = "2";
		public static String WITHDRAWAL_CANCEL_BY_USER = "3";
		public static String WITHDRAWAL_CANCEL_BY_ADMIN = "4";
		public static String WITHDRAWAL_HOLDING = "5";
	}
	
	public static class MemberBankAccountStatus{
		public static String USE  = "Y";
		public static String NOT_USE = "N";
	}

	public static class CodeGoup {
		public static String NODE_TYPE = "CD00001";
		public static String POINTBACk_STATUS = "CD00002";
		public static String CATEGORY_STATUS = "CD00003";
		public static String CONVERSON_STATUS = "CD00004";
		public static String AUTH_TYPE = "CD00005";
		public static String KEYWORD_TYPE = "CD00006";
		public static String NODE_STATUS = "CD00007";
		public static String PAYMENT_STATUS = "CD00008";
		public static String VAN_PAYMENT_STATUS = "CD00009";
		public static String PAYMENT_TYPE = "CD00010";
		public static String POINT_TYPE = "CD00011";
		public static String REGIST_TYPE = "CD00012";
		public static String POINT_ACC_STATUS = "CD00013";
		public static String POINT_USE_STATUS = "CD00014";
		public static String PAYMENT_TRANSACTION_TYPE = "CD00015";
		public static String ORDER_TYPE = "CD00016";
		public static String PAYMENT_APPROVAL_STATUS = "CD00017";
		public static String API_SERVICE_STATUS = "CD00018";
		public static String POINT_TRANSFER_TYPE = "CD00019";
		public static String POINT_TRANSFER_STAUS = "CD00020";
		public static String BOARD_TYPE = "CD00021";
	}
	
	public static class reaccumulateType {
		public static final String ACC_AFTER_DEL = "1";
		public static final String ACC_NOT_DEL = "2";
	}
	
	public static class AccumulateRequestFrom {
		public static final String  FROM_BACKOFFICE = "1";
		public static final String  FROM_QR   = "2";
		public static final String  FROM_SHOPPING_MALL   = "3";
	}
	
	
	
	public static class PaymentTransactionStatus{
		public static final String APPROVAL = "1";
		public static final String CANCEL = "2";
	}
	
	public static class AccumulateStatus {
		public static String POINTBACK_START  = "1";
		public static String POINTBACK_PROGRESS  = "2";
		public static String POINTBACK_COMPLETE  = "3";
		public static String POINTBACK_CANCEL_START= "4";
		public static String POINTBACK_CANCEL_PROGRESS= "5";
		public static String POINTBACK_CANCEL_COMPLETE  = "6";
		public static String POINTBACK_STOP= "7";
		public static String POINTBACK_CANCEL_STOP= "8";
		public static String POINTBACK_REQ_ERROR= "9";
	
	}
	
}
