returnp = {};


$(function () {
	
	$.ajaxSetup({
		 
	     beforeSend: function(xhr) {
	        xhr.setRequestHeader("AJAX", true);	        
	     },
	     error: function(xhr, status, err) {
	    	
	    	 if(status != 'success'){
	    		 $('#progress_loading').hide(); 
	    	 }	
	    	 
	    	 if (xhr.status == 403 && !this.url.match("/handleContent") ) {
	    		 $.messager.alert("Access denied", "권한이 없습니다.", '',function(){
	    			
	    		 });	    		
	    		 return;
	         }
	    	 if (status != "success") {
				$.messager.alert("작업 중 ", "현재 진행중이거나 완료되지 않은 메뉴입니다.", '',function(){
	    			console.log(xhr);
	    		});				
				return;
	    	 }
			
	        /*if (xhr.status == 401) {
	        	 alert("인증에 실패 했습니다. 로그인 페이지로 이동합니다.");
	             location.href = "/j_spring_security_logout";
	         } else if (xhr.status == 403) {
	        	 alert("세션이 만료가 되었습니다. 로그인 페이지로 이동합니다.");
	             location.href = "/j_spring_security_logout";
	         }else if (xhr.status == 404) {        	 
	            location.href = "/error_404";
	         }
	         else if (xhr.status == 500) {        	 
		        location.href = "/error_500";
	         }*/
	     },
	     dataFilter:function(data,type) {
	    	return data;
	     },
	     complete(xhr, status){	    
	    	
	    	 if(status !="success"){
	    		 
	    		$(".modal_dialog").each(function(){
	    			
	    			if($(this).closest("#modal_area").length < 1){
	    				var opts = $(this).window('options');
				    		 
	 	    			if (opts.closed === false) {
	 	    				$(this).window('close');
	 	    			}
	    			}
	    		});
	    		
	    		return;
	    	 }
	     }
	});	
	
	
	returnp.api = {
        apiInfo: {
        	/* 노드 리스트 통합 조회*/
        	getNodes : { apiCode: 10000, apiPath : '/api/nodes' ,  requestMethod: "get"},  
        	getNodeChilds : { apiCode: 10000, apiPath : '/api/childNodeList' ,  requestMethod: "get"},  
        	
        	/*카테고리 관련*/
        	createCategory : { apiCode: 10000, apiPath : '/api/category/create' ,  requestMethod: "post"},  
        	getCategories : { apiCode: 10000, apiPath : '/api/categories' ,  requestMethod: "get"},  
        	getCategory : { apiCode: 10000, apiPath : '/api/category/get' ,  requestMethod: "get"},  
        	deleteCategory : { apiCode: 10000, apiPath : '/api/category/delete' ,  requestMethod: "post"},  
        	updateCategory : { apiCode: 10000, apiPath : '/api/category/update' ,  requestMethod: "post"},  
        	
        	/* G -> P 포인트 전환 요청 */
        	getPointConvertRequests : { apiCode: 10000, apiPath : '/api/pointConvertRequests' ,  requestMethod: "get"},  
        	getPointConvertRequest: { apiCode: 10000, apiPath : '/api/pointConvertRequest/get' ,  requestMethod: "get"},  
        	createPointConvertRequest : { apiCode: 10000, apiPath : '/api/pointConvertRequest/create' ,  requestMethod: "get"},  
        	updatePointConvertRequest : { apiCode: 10000, apiPath : '/api/pointConvertRequest/update' ,  requestMethod: "post"},  
        	deletePointConvertRequest: { apiCode: 10000, apiPath : '/api/pointConvertRequest/delete' ,  requestMethod: "post"},  
        	
        	/* 멤버쉽(정회원) 신청 관련*/
        	getMembershipRequests : { apiCode: 10000, apiPath : '/api/membershipRequests' ,  requestMethod: "get"},  
        	getMembershipRequestCommand: { apiCode: 10000, apiPath : '/api/membershipRequestCommand/get' ,  requestMethod: "get"},  
        	createMembershipRequest : { apiCode: 10000, apiPath : '/api/membershipRequest/create' ,  requestMethod: "post"},  
        	updateMembershipRequest : { apiCode: 10000, apiPath : '/api/membershipRequest/update' ,  requestMethod: "post"},  
        	getMembershipRequest: { apiCode: 10000, apiPath : '/api/membershipRequest/get' ,  requestMethod: "get"},  
        	deleteMembershipRequest: { apiCode: 10000, apiPath : '/api/membershipRequest/delete' ,  requestMethod: "post"},  
        	
        	/*총판 관련*/
        	getSoleDists : { apiCode: 10000, apiPath : '/api/soleDist' ,  requestMethod: "get"},  
        	createSoleDist : { apiCode: 10000, apiPath : '/api/soleDist/create' ,  requestMethod: "get"},  
        	updateSoleDist: { apiCode: 10000, apiPath : '/api/soleDist/update' ,  requestMethod: "post"},  
        	getSoleDist: { apiCode: 10000, apiPath : '/api/soleDist/get' ,  requestMethod: "get"},  
        	deleteSoleDist: { apiCode: 10000, apiPath : '/api/soleDist/delete' ,  requestMethod: "post"},  
        	
        	/*추천인 (정회원 관련)*/
        	getRecommender: { apiCode: 10000, apiPath : '/api/recommender/get' ,  requestMethod: "get"},  
        	createRecommender: { apiCode: 10000, apiPath : '/api/recommender/create' ,  requestMethod: "post"},  
        	updateRecommender: { apiCode: 10000, apiPath : '/api/recommender/update' ,  requestMethod: "post"},  
        	deleteRecommender: { apiCode: 10000, apiPath : '/api/recommender/delete' ,  requestMethod: "post"},  
        	
        	/* 멤버 관련*/
        	getMember: { apiCode: 10000, apiPath : '/api/member/get' ,  requestMethod: "get"},  
        	createMember: { apiCode: 10000, apiPath : '/api/member/create' ,  requestMethod: "post"},  
        	updateMember: { apiCode: 10000, apiPath : '/api/member/update' ,  requestMethod: "post"},  
        	deleteMember: { apiCode: 10000, apiPath : '/api/member/delete' ,  requestMethod: "post"},  
        	getMemberCommand: { apiCode: 10000, apiPath : '/api/member/getMemberCommand' ,  requestMethod: "get"},  
        	findMyMembers: { apiCode: 10000, apiPath : '/api/member/myMembers' ,  requestMethod: "get"},  
        	getMyTotalPointInfo: { apiCode: 10000, apiPath : '/api/member/myTotalPointInfo' ,  requestMethod: "get"},  

        	/* 지사 관련*/
        	getBranch: { apiCode: 10000, apiPath : '/api/branch/get' ,  requestMethod: "get"},  
        	getBranchCommand: { apiCode: 10000, apiPath : '/api/branch/getBranchCommand' ,  requestMethod: "get"},  
        	createBranch: { apiCode: 10000, apiPath : '/api/branch/create' ,  requestMethod: "post"},  
        	updateBranch: { apiCode: 10000, apiPath : '/api/branch/update' ,  requestMethod: "post"},
        	deleteBranch: { apiCode: 10000, apiPath : '/api/branch/delete' ,  requestMethod: "post"},

        	/* 대리점 관련*/
        	getAgency: { apiCode: 10000, apiPath : '/api/agency/get' ,  requestMethod: "get"},  
        	getAgencyCommand: { apiCode: 10000, apiPath : '/api/agency/getAgencyCommand' ,  requestMethod: "get"},  
        	createAgency: { apiCode: 10000, apiPath : '/api/agency/create' ,  requestMethod: "post"},  
        	updateAgency: { apiCode: 10000, apiPath : '/api/agency/update' ,  requestMethod: "post"},
        	deleteAgency: { apiCode: 10000, apiPath : '/api/agency/update' ,  requestMethod: "post"},
        	
        	/* 협력 업체(가맹점) 관련*/
        	getAffiliate: { apiCode: 10000, apiPath : '/api/affiliate/get' ,  requestMethod: "get"},  
        	getAffiliateCommand: { apiCode: 10000, apiPath : '/api/affiliate/getAffiliateCommand' ,  requestMethod: "get"},  
        	createAffiliate: { apiCode: 10000, apiPath : '/api/affiliate/create' ,  requestMethod: "post"},  
        	updateAffiliate: { apiCode: 10000, apiPath : '/api/affiliate/update' ,  requestMethod: "post"},
        	deleteAffiliate: { apiCode: 10000, apiPath : '/api/affiliate/delete' ,  requestMethod: "post"},
        	genTid : { apiCode: 10000, apiPath : '/api/affiliate/genTid' ,  requestMethod: "get"},
        	
        	/* 영업 관리자 관련*/
        	getSaleManager: { apiCode: 10000, apiPath : '/api/saleManager/get' ,  requestMethod: "get"},  
        	createSaleManager: { apiCode: 10000, apiPath : '/api/saleManager/create' ,  requestMethod: "post"},  
        	updateSaleManager: { apiCode: 10000, apiPath : '/api/saleManager/update' ,  requestMethod: "post"},
        	deleteSaleManager: { apiCode: 10000, apiPath : '/api/saleManager/update' ,  requestMethod: "post"},
        	
        	/* 그린 포인트*/
        	getGreenPoints: { apiCode: 10000, apiPath : '/api/greenPoints' ,  requestMethod: "get"},  
        	getGreenPoint: { apiCode: 10000, apiPath : '/api/greenPoint/get' ,  requestMethod: "get"},  
        	getGreenPointCommand: { apiCode: 10000, apiPath : '/api/greenPointCommand/get' ,  requestMethod: "get"},  
        	updateGreenPoint: { apiCode: 10000, apiPath : '/api/greenPoint/update' ,  requestMethod: "post"},  
        	deleteGreenPoint: { apiCode: 10000, apiPath : '/api/greenPoint/delete' ,  requestMethod: "post"},  
        	
        	/* 레드 포인트*/
        	getRedPoints: { apiCode: 10000, apiPath : '/api/redPoints' ,  requestMethod: "get"},  
        	getRedPoint: { apiCode: 10000, apiPath : '/api/redPoint/get' ,  requestMethod: "get"},  
        	getRedPointCommand: { apiCode: 10000, apiPath : '/api/redPointCommand/get' ,  requestMethod: "get"},  
        	updateRedPoint: { apiCode: 10000, apiPath : '/api/redPoint/update' ,  requestMethod: "post"},  
        	deleteRedPoint: { apiCode: 10000, apiPath : '/api/redPoint/delete' ,  requestMethod: "post"},  
        	
        	/* 회원 은행 계좌 */
        	getMemberBankAccounts: { apiCode: 10000, apiPath : '/api/memberBankAccounts' ,  requestMethod: "get"},  
        	createMemberBankAccount: { apiCode: 10000, apiPath : '/api/memberBankAccount/create' ,  requestMethod: "post"},  
        	updateMemberBankAccount: { apiCode: 10000, apiPath : '/api/memberBankAccount/update' ,  requestMethod: "post"},  
        	deleteMemberBankAccount: { apiCode: 10000, apiPath : '/api/memberBankAccount/delete' ,  requestMethod: "post"},

        	/* 포인트 현금 출금  */
        	getPointWithdrawals: { apiCode: 10000, apiPath : '/api/pointWithdrawals' ,  requestMethod: "get"},  
        	createPointWithdrawal: { apiCode: 10000, apiPath : '/api/pointWithdrawal/create' ,  requestMethod: "post"},  
        	updatePointWithdrawal: { apiCode: 10000, apiPath : '/api/pointWithdrawal/update' ,  requestMethod: "post"},  
        	deletePointWithdrawal: { apiCode: 10000, apiPath : '/api/pointWithdrawal/delete' ,  requestMethod: "post"},
        	
        	/* 포인트 전환율 , 누적 포인트 등 관리*/
        	getPointConversionTransactions: { apiCode: 10000, apiPath : '/api/pointConversionTransactions' ,	  requestMethod: "get"},  
        	
        	/* 코드 관리 관련*/
        	genCode: { apiCode: 10000, apiPath : '/api/code/create' ,  requestMethod: "get"},  
        	
        	/* 밴 수동 및 자동 결제 관련 api*/ 
        	getPaymentTransactions: { apiCode: 10000, apiPath : '/api/paymentTransactions' ,  requestMethod: "get"},  
        	getPaymentTransactionCommands: { apiCode: 10000, apiPath : '/api/paymentTransactionCommands' ,  requestMethod: "get"},  
        	getPaymentTransaction: { apiCode: 10000, apiPath : '/api/paymentTransaction/get' ,  requestMethod: "get"},  
        	getOvelapPaymentTransactionCommands: { apiCode: 10000, apiPath : '/api/paymentTransactionCommands/overlap' ,  requestMethod: "get"},  
        	genPan: { apiCode: 10000, apiPath : '/api/paymentTransaction/genPan' ,  requestMethod: "get"},  
        	createPaymentTransaction: { apiCode: 10020, apiPath : '/api/paymentTransaction/create' ,  requestMethod: "get"},  
        	createNewPaymentTransaction: { apiCode: 10020, apiPath : '/api/paymentTransaction/newCreate' ,  requestMethod: "get"},  
        	reaccumulatePaymentTransaction: { apiCode: 10020, apiPath : '/api/paymentTransaction/reaccumulate' ,  requestMethod: "get"},  
        	updatePaymentTransaction: { apiCode: 10020, apiPath : '/api/paymentTransaction/update' ,  requestMethod: "post"},  
        	deletePaymentTransaction: { apiCode: 10020, apiPath : '/api/paymentTransaction/delete' ,  requestMethod: "post"},  
        	cancelPaymentTransaction: { apiCode: 10020, apiPath : '/api/paymentTransaction/cancel' ,  requestMethod: "post"},  
        	cancelForcedPaymentTransaction: { apiCode: 10020, apiPath : '/api/paymentTransaction/forceCancel' ,  requestMethod: "post"},  
        	accForcedPaymentTransaction: { apiCode: 10020, apiPath : '/api/paymentTransaction/forceAcc' ,  requestMethod: "post"},  

        	/* 포인트 이체 관련 */ 
        	getPointTransferTransactions: { apiCode: 10000, apiPath : '/api/pointTransferTransactions/find' ,  requestMethod: "get"},  
        	createPointTransferTransaction: { apiCode: 10000, apiPath : '/api/pointTransferTransaction/create' ,  requestMethod: "post"},  

        	/* 결제에 해당하는 포인트 백 리스트 api*/ 
        	formPaymentPointbackRecordRequest: { apiCode: 10000, apiPath : '/api/paymentPointbackRecord/template/pointbackRecordList' ,  requestMethod: "get"},  
        	getPaymentPonitbackRecords: { apiCode: 10000, apiPath : '/api/paymentPointbackRecords/get' ,  requestMethod: "get"},  
        	findPaymentPointbackRecords2: { apiCode: 10000, apiPath : '/api/paymentPointbackRecords2/get' ,  requestMethod: "get"},  
        	findPaymentPointbackRecords: { apiCode: 10000, apiPath : '/api/paymentPointbackRecords/find' ,  requestMethod: "get"},  
        	
        	/* 정책 관련 api */
        	createPolicy : {apiCode: 10002, apiPath : '/api/policy/create' , requestMethod: 'post'}, /* 정책 생성*/
        	getPolicies : {apiCode: 10002, apiPath : '/api/policy' , requestMethod: 'get'}, /* 정책 리스트 */
        	
        	/* 마케팅 조직 관리*/
        	getMarketers: { apiCode: 10000, apiPath : '/api/marketer/gets' ,  requestMethod: "get"},  
        	createMarketer: { apiCode: 10000, apiPath : '/api/marketer/create' ,  requestMethod: "post"},  
        	updateMarketer: { apiCode: 10000, apiPath : '/api/marketer/update' ,  requestMethod: "post"},  
        	deleteMarketer: { apiCode: 10000, apiPath : '/api/marketer/delete' ,  requestMethod: "post"},  
        	
        	/* 상품권 주문 관련*/
        	selectGiftCardOrders: { apiCode: 10000, apiPath : '/api/giftCardOrders' ,  requestMethod: "get"},  
        	createGiftCardOrder: { apiCode: 10000, apiPath : '/api/giftCardOrder/create' ,  requestMethod: "post"},  
        	updateGiftCardOrder: { apiCode: 10000, apiPath : '/api/giftCardOrder/update' ,  requestMethod: "post"},  
        	deleteGiftCardOrder: { apiCode: 10000, apiPath : '/api/giftCardOrder/delete' ,  requestMethod: "post"},  
        	
        	/* 상품권 발행 명령*/
        	issueGiftCard: { apiCode: 10000, apiPath : '/api/giftCardOrder/issueGiftCard' ,  requestMethod: "post"},  

        	/* 주문 아이템 관련*/
        	selectGiftCardItemsOrders: { apiCode: 10000, apiPath : '/api/giftCardOrderItems' ,  requestMethod: "get"},  

        	
        	/* 대쉬보드 관련 api */
        	getDashBoard: {apiCode: 10000, apiPath : '/api/dashBoard/get' , requestMethod: 'get'}, /* 회원 통계 */
        	getDashBoardChart: {apiCode: 10000, apiPath : '/api/dashBoard/getChart' , requestMethod: 'get'}, /* 회원 통계 */
        	
        	/* 외부연동 api */
        	getApiServices: {apiCode: 10000, apiPath : '/api/apiServices' , requestMethod: 'get'}, /* api key 리스트 */
        	getApiService: {apiCode: 10000, apiPath : '/api/apiService/get' , requestMethod: 'get'}, /* api key 단건 */
        	getApiServiceList: {apiCode: 10000, apiPath : '/api/apiService/list' , requestMethod: 'get'}, /* ApiService 분류 조회 */
        	createApiService: {apiCode: 10000, apiPath : '/api/apiService/create' , requestMethod: 'post'}, /* API 키 발급,등록 */
        	updateApiService: {apiCode: 10000, apiPath : '/api/apiService/update' , requestMethod: 'post'}, /* API 키 발급,등록 */
        	deleteApiService: {apiCode: 10000, apiPath : '/api/apiService/delete' , requestMethod: 'post'}, /* API 키 발급,등록 */
        	makeApiKey: {apiCode: 10000, apiPath : '/api/apiService/makeKey' , requestMethod: 'post'}, /* API 키 발급,등록 */
        	makeRfId: {apiCode: 10000, apiPath : '/api/apiService/makeRfId' , requestMethod: 'post'}, /* API 키 발급,등록 */
        	
        	/*상품 */
        	selectProducts: {apiCode: 10000, apiPath : '/api/products' , requestMethod: 'get'}, /* 상품 리스트 가져오기*/
          	createProduct: {apiCode: 10000, apiPath : '/api/product/create' , requestMethod: 'post'}, /* 상품 생성 */
        	updateProduct: {apiCode: 10000, apiPath : '/api/product/update' , requestMethod: 'post'}, /* 상품 수정 */
        	deleteProduct: {apiCode: 10000, apiPath : '/api/product/delete' , requestMethod: 'post'}, /* 상품 삭제 */
        	
        	/*상품권 판매 조직  */
        	selectGiftCardSalesOrgans: {apiCode: 10000, apiPath : '/api/giftCardSalesOrgans' , requestMethod: 'get'}, /* 상품권 판매 조직 리스트 가져오기*/
          	createGiftCardSalesOrgan: {apiCode: 10000, apiPath : '/api/giftCardSalesOrgan/create' , requestMethod: 'post'}, /* 상품권 판매 조직 생성 */
        	updateGiftCardSalesOrgan: {apiCode: 10000, apiPath : '/api/giftCardSalesOrgan/update' , requestMethod: 'post'}, /* 상품권 판매 조직 수정 */
        	deleteGiftCardSalesOrgan: {apiCode: 10000, apiPath : '/api/giftCardSalesOrgan/delete' , requestMethod: 'post'}, /* 삭제 */
        	
        	 /* 로그인 */
        	signIn : { apiCode: 10000, requestMethod: "post"}, 
        	 /* 일반 회원 등록 , 가입*/
        	signUp : { apiCode: 10001, requestMethod: "post"},
        	/* 이메일 중복 체크*/
        	checkEmailDuplicated : { apiCode: 10002, apiPath : '/api/member/checkEmailDuplicated' , requestMethod: 'get'},	
        	/*핸드폰 번호 중복 체크 */
        	checkPhoneDuplicated : { apiCode: 10002, apiPath : '/api/member/checkPhoneDuplicated' , requestMethod: 'get'},
        	
          	/* 게시판 api */
        	getBoards: {apiCode: 10000, apiPath : '/api/boards' , requestMethod: 'get'},
        	getBoard: {apiCode: 10000, apiPath : '/api/board/get' , requestMethod: 'get'},
        	getReply: {apiCode: 10000, apiPath : '/api/boardReply/get' , requestMethod: 'get'},
        	getBoardCategories: {apiCode: 10000, apiPath : '/api/boardCategories' , requestMethod: 'get'},
        	createBoard: {apiCode: 10000, apiPath : '/api/board/create' , requestMethod: 'post'},
        	updateBoard: {apiCode: 10000, apiPath : '/api/board/update' , requestMethod: 'post'},
        	deleteBoard: {apiCode: 10000, apiPath : '/api/board/delete' , requestMethod: 'post'},
        	deleteReply: {apiCode: 10000, apiPath : '/api/boardReply/delete' , requestMethod: 'post'},

        	/*파일 업로드*/
        	getAdminFiles: {apiCode: 10000, apiPath : '/api/adminFileUploads' , requestMethod: 'get'},
        	removeAdminFile: {apiCode: 10000, apiPath : '/api/adminFileUpload/delete' , requestMethod: 'get'},
        	uploadAdminFile: {apiCode: 10000, apiPath : '/api/adminFileUpload/create' , requestMethod: 'post'},
        	
        	uploadSalesFile: {apiCode: 10000, apiPath : '/api/upload/salesFile' , requestMethod: 'post'},

        	/*리포트 */
        	affilaiteSaleReport: {apiCode: 10000, apiPath : '/api/report/affilaiteSaleReport' , requestMethod: 'get'},
        },
        
        init : function(){
            this.apiUrl =  window.location.protocol + "//" + window.location.host  + window.location.pathname;
        },

        makeApiUrl : function(apiName, data){
            var url = this.apiInfo[apiName].apiPath + "?apiCode=" + this.apiInfo[apiName].apiCode;
            //console.log("apiName : " + apiName );
            //console.log(url)
            return url;
        },

        call: function (apiName, data, callback) {
        	//console.log("api url : " +  this.apiUrl);
           //console.log("current Server URL")
           //console.log("window.location.href : " + window.location.href);
           //console.log("window.location.host : " + window.location.host);
           //console.log("window.location.hostname : " + window.location.hostname);
           //console.log("window.location.pathname : " + window.location.pathname);
           //console.log("window.location.protocol : " + window.location.protocol);
           
        	if (!this.apiInfo[apiName]) {
            	console.log(apiName + ' : wrong remote call');
            	return;
            }
        	$("#progress_loading").show(); 
            var requestUrl = this.makeApiUrl(apiName);
            //console.log("requestUrl : " + requestUrl);
            //console.log(data);
            if (this.apiInfo[apiName].requestMethod == 'get') {
                this.sendGetJson(requestUrl, apiName, data, callback);
            } else {
                this.sendPostJson(requestUrl, apiName, data, callback);
            }
        },

        /*get request */
        sendGetJson: function (requestUrl, apiName, data, callback) {
        	//console.log(apiName);
        	var context = this;
            $.getJSON(requestUrl, data, function (result) {
            	//console.log(result);
            	$("#progress_loading").hide(); 
            	if (callback && typeof callback == "function") {
                	if (result.resultCode == "400") {
                		$.messager.alert({
                			title : "세션 오류", 
                			msg : res.message,
                			fn : function(){
                				location.href = "/signIn"
                			}
                		});
                	}else {
                		callback(result);
                	}
                } else {
                    context.handleResponse(apiName , result);
                }
            });
        },

        /*post request */	
        sendPostJson: function (requestUrl, apiName, data, callback) {
        	var context = this;
            $.ajax({
            	type: "POST",
                url: requestUrl,
                data: data,
                success: function (result) {
                	$("#progress_loading").hide()   
                	if (callback && typeof callback == "function") {
                       	if (result.resultCode == "400") {
                       		$.messager.alert({
                       			title : "세션 오류", 
                       			msg : res.message,
                       			fn : function(){
                       				location.href = "/signIn"
                       			}
                       		});
                       	}else {
                       		callback(result);
                       	}
                       } else {
                           context.handleResponse(apiName , result);
                       }
                },
                dataType: 'json'
            });
        },
        
        handleResponse : function(apiName, jsonResult){
            switch (apiName) {
                case 'checkEmailDuplicated':break;
                case 'signIn':break;
                case 'signUp':break;
            }
        }
    };
	returnp.api.init();
});
