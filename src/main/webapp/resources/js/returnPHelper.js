function getNumber(str)
{
    str = "" + str.replace(/(^\s*)|(\s*$)|,/g, ''); // trim, 콤마 제거
    return (new Number(str));
}

function affiliateTypeFormatter(value,row,index){
	switch(row.affiliateType){
	case "A001": result = "오프라인 사업자" ; break;
	case "A002": result = "온라인 사업자";break;
	case "A003": result = "무사업자";break;
	default :result = "무사업자";break;
	}
	return result; 
}
 function serviceNameFormatter(value,row,index) {
	 switch(row.apiService){
		case "1": result = "쇼핑몰 연동"  ; break;
		case "2":  result = "쇼핑몰 연동" ; break;
		default :result = '-';break;
		}
		return result; 
 }
 
 function marketerStatusFormatter(value,row,index) {
	 switch(row.marketerStatus){
		case "1": result = "정상"  ; break;
		case "2":  result = "중지" ; break;
		default :result = '-';break;
		}
		return result; 
 }
 
 function serviceStatusFormatter(value,row,index) {
	 switch(row.apiServiceStatus){
		case "1": result = "승인"  ; break;
		case "2":  result = "중지" ; break;
		default :result = '-';break;
		}
		return result; 
 }
 
function boardLevelTitleFormatter(value,row,index){
	switch(row.boardLevel){
	case 1: result = addBoldFomatter(row.boardTitle) ; break;
	//case 2: result = '<i style = "" class="fa fa-replyd">' +row.boardTitle+ '</i>';break;
	case 2: result = "&nbsp;&nbsp;ㄴ" + row.boardTitle;break;
	default :result = '-';break;
	}
	return result; 
}

function boardReplyStatusFormatter(value,row,index){
	switch(value){
	case "1": result = '<i style = "color : red" class="fa fa-check-circle"> 답변 대기</i>';break;
	case "2": result = '<i style = "color : #009900" class="fa fa-check-circle"> 답변 완료</i>';break;
	default :result = '-';break;
	}
	return result; 
}

function withdrawalStatusFormatter(value,row,index){
	switch(value){
	case "1": result = '<i style = "color : #FF8200" class="fa fa-check-circle"></i> 출금 처리 중';break;
	case "2": result = '<i style = "color : #009900" class="fa fa-check-circle"></i> 출금 완료';break;
	case "3": result = '<i style = "color : #009900" class="fa fa-check-circle"></i> 출금 보류';break;
	case "4": result = '<i style = "color : #009900" class="fa fa-check-circle"></i> 출금 취소 ';break;
	case "5": result = '<i style = "color : #009900" class="fa fa-check-circle"></i> 관리자 출금 취소';break;
	}
	return result; 
}

function slashFormatter(value,row,index){
	if (value) {
		return value;
	}else {
		return "-";
	}
	return result; 
}

function ynFormatter(value,row,index){
	switch(value){
	case "Y": result = '<i style = "color : #009900" class="fa fa-check-circle"></i>';break;
	case "N": result = "-";break;
	}
	return result; 
}

function boardLevelFormatter(value,row,index){
	var result;
	switch(value){
	case 1:result = '<span style = "color : #000000;" >'+ "main"+ ' </span>';break;
	case 2:result = '<span style = "color : #000000" >'+ "reply"+ ' </span>';break;
	}
	return result; 
}

function categoryFomatter(value,row,index){
	var result;
	switch(value){
	case "1":result = '<span style = "color : #000000;" >'+ "일반회원 관련"+ ' </span>';break;
	case "2":result = '<span style = "color : #000000" >'+ "정회원 관련"+ ' </span>';break;
	case "3":result = '<span style = "color : #000000" >'+ "포인트 관련"+ ' </span>';break;
	case "4":result = '<span style = "color : #000000" >'+ "가맹 관련"+ ' </span>';break;
	default : result = '-';break;
	}
	return result; 
}

function boardLevelParentFormatter(value,row,index){
	var result;
	switch(value){
	case 0:result = '<span style = "color : #000000;" >'+ "-"+ ' </span>';break;
	default : 
	case 2:result = '<span style = "color : #000000" >'+ value+ ' </span>';break;
	}
	return result; 
}

function categoryStatusFormatter(value,row,index){
	switch(value){
	case "1": result = '<span style = "color : green" >'+ "사용중"+ ' </span>';break;
	case "2": result = '<span style = "color : red">'+ "미사용"+ ' </span>';break;
	}
	return result; 
}

function numberFormatter(data){
	data = String(data);
	return '<span style = "color : #000000;font-weight : bold">'  + (numberGreenFormatter(data.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,'))) + '</span>';
}

function numberGreenFormatter(data){
	data = String(data);
	return '<span style = "color : #049931;font-weight : bold">'  + (data.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,')) +  '</span>';
}
function addBoldFomatter(data){
	return '<span style = "font-weight : bold">'  +data +   '</span>';
}
function boldFormatter(data){
	data = String(data);
	return '<span style = "font-weight : bold">'  + (data.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,')) +  '</span>';
}

function numberBoldFormatter(data){
	if (!data) return "-";
	data = typeof data != "String" ?  String(data) : data;
	return '<span style = "font-weight : bold">'  + (data.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,')) +  '</span>';
}

function numberRedFormatter(data){
	data = String(data);
	return '<span style = "color : #F64B1A;font-weight : bold">'  + (data.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,')) +  '</span>';
}

function numberBlueFormatter(data){
	data = String(data);
	return '<span style = "color : #0080FF;font-weight : bold">'  + (data.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,')) +  '</span>';
}


function percentFormatter(data){
	data = parseFloat(data) * 100;
	return '<span style = "color : #FF0000;font-weight : bold">'  +data+" %"+ '</span>';
}

function formatDate(date) {
	var sDate;
	if (date < 10) {
		sDate = "0"+ date;
	}else {
		sDate = date.toString();
	}
	
	return sDate;
}

function dateNow(){
	var date = new Date();
	var year = date.getFullYear();  
	var month = date.getMonth()+1;  
	var d = date.getDate();  
	var hours = date.getHours();  
	var minutes  = date.getMinutes();  
	var seconds = date.getSeconds();  
	return  year + "-" + formatDate(month) + "-" + formatDate(d) + " "+ formatDate(hours) + ":" + formatDate(minutes) + ":" + formatDate(seconds);
}
function dateFormatter(data){
	if (!data || data == "") return "-";
	if (typeof data !== "object") {
		data = new Date(parseInt(data));
	}
	var year = data.getFullYear();  
	var month = data.getMonth()+1;  
	var d = data.getDate();  
	var hours = data.getHours();  
	var minutes  = data.getMinutes();  
	var seconds = data.getSeconds();  
	return  year + "-" + formatDate(month) + "-" + formatDate(d) + " "+ formatDate(hours) + ":" + formatDate(minutes) + ":" + formatDate(seconds);
}

function pointBackStatusFormatter(value,row,index){
	var status = row.pointBackStatus;
	var text; 
	switch(status){
	case "1": text = '<span style = "color: gray;font-weight : bold">적립 시작</span>' ; break;
	case "2": text = '<span style = "color: red;font-weight : bold">적립 진행중</span>' ; break;
	case "3": text = '<span style = "color: green;font-weight : bold">적립 완료</span>' ; break;
	case "4": text = "적립 취소 시작 "; break;
	case "5": text = "적립 취소 진행중"; break;
	case "6": text ='<span style = "color: #FF8000;font-weight : bold">적립 취소 완료</span>' ; break;
	case "7": text = "적립 중지"; break;
	case "8": text = "적립 취소 중지"; break;
	}
	return text; 
}

function authTypeFormatter(value,row,index){
	var status = row.memberAuthType;
	var text; 
	switch(status){
	case "1": text = "이메일"; break;
	case "2": text = "모바일"; break;
	}
	return text; 
}

function paymentTransactionRegistFormatter(value,row,index){
	var status = row.paymentTransactionType;
	var text; 
	switch(status){
	case "1": text = '<span style = "border-radius: 10px;background-color: green;padding: 5px;color : #ffffff;font-weight : bold">QR Code</span>' ; break;
	case "2": text = '<span style = "border-radius: 10px;background-color: red;padding: 5px;color : #ffffff;font-weight : bold">VAN</span>' ; break;
	case "3": text = '<span style = "border-radius: 10px;background-color: #01A9DB;padding: 5px;color : #ffffff;font-weight : bold">Admin</span>' ; break;
	case "4": text = '<span style = "border-radius: 10px;background-color: #01A9DB;padding: 5px;color : #ffffff;font-weight : bold">Online</span>' ; break;
	}
	return text; 
}

function PaymentApprovalStatusFormatter(value,row,index){
	var status = row.paymentApprovalStatus;
	var text; 
	switch(status){
	case "1": text = "결제 승인"; break;
	case "2": text = "결제 승인 취소"; break;
	case "3": text = "결제 오류"; break;
	}
	return text; 
}

function buttonActionDetailFormatter(value,row,index){
	var href = 'innerlist.php?list='+row.id;
	var dhref = 'dellist.php?list='+row.id;
	return '<center><a target="_blank" href="' + href + '"><span class="btn btn-primary btn-xs"><i class="fa fa-search"></i> Preview</span></a><a href="' + dhref + '" class="easyui-linkbutton" iconCls="icon-remove" plain="true" >Remove Entry</a></center>';
}

function conversionStatusFormatter(value,row,index){
	var status =  String(row.conversionStatus);
	switch(status){
	case "1": 
		text = '<span style = "color : #049931;font-weight : bold">전환 중</span>'; 
		break;
	case "2": 
		text = '<span style = "color : #F64B1A;font-weight : bold">전환 중지</span>'; 
		break;
	case "3": 
		text = '<span style = "color : #F64B1A;font-weight : bold">전환 완료</span>'; 
		break;
	default :
		text  = "-";
	}
	return text; 
}

function pointTypeFormatter(value,row,index){
	var status = row.pointType;
	var text; 
	switch(status){
	case "1": text =  '<span style = "color : #049931;font-weight : bold">'  + 'GREEN'+  '</span>'; break;
	case "2": text =  '<span style = "color : #F64B1A;font-weight : bold">'  + 'RED'+  '</span>'; break;
	}
	return text; 
}

function pointTransferStatus(value,row,index){
	var status = row.pointTransferStatus;
	var text; 
	switch(status){
	case "1": text = "송금 완료"; break;
	case "2": text = "송금 실패"; break;
	case "3": text = "송금 강제 취소 "; break;
	}
	return text; 
}

function pointTransferType(value,row,index){
	var status = row.pointTransferType;
	var text; 
	switch(status){
	case "1": text = '<span style = "border-radius: 10px;background-color: green;padding: 5px;color : #ffffff;font-weight : bold">선물</span>' ;break;
	case "2": text = '<span style = "border-radius: 10px;background-color: red;padding: 5px;color : #ffffff;font-weight : bold">이체</span>' ; break;
	case "3": text = '<span style = "border-radius: 10px;background-color: blue;padding: 5px;color : #ffffff;font-weight : bold">기타</span>' ; break;
	}
	return text; 
}

function pointTransferNodeType(value,row,index){
	var status = row.pointNode;
	var text; 
	switch(status){
	case "1": text = "일반 회원"; break;
	case "2": text = "정회원"; break;
	case "3": text = "지사"; break;
	case "4": text = "대리점"; break;
	case "5": text = "협력 업체"; break;
	case "6": text = "영업 관리자"; break;
	case "7": text = "총판"; break;
	}
	return text; 
}

function nodeTypeFormatter(value,row,index){
	var text; 
	var status = value ? value : row.nodeType;
	switch(status){
	case "1": text = "일반 회원"; break;
	case "2": text = "정회원"; break;
	case "3": text = "지사"; break;
	case "4": text = "대리점"; break;
	case "5": text = "협력 업체"; break;
	case "6": text = "영업 관리자"; break;
	case "7": text = "총판"; break;
	}
	return text; 
}

function nodeStatusFormatter(value,row,index){
	var nodeType = searchFormData.nodeType;
	var arr = {
		'1' : 'memberStatus', 
		'2' : 'recommenderStatus', 
		'3' : 'branchStatus', 
		'4' : 'agencyStatus', 
		'5' : 'affiliateStatus', 
		'6' : 'saleManagerStatus',
		'7' : 'soleDistStatus'
	}
	var status = row[arr[nodeType]];
	var text; 
	switch(status){
	case "1": text = "정상"; break;
	case "2": text = "등록 대기"; break;
	case "3": text = "미 인증"; break;
	case "4": text = "인증 완료"; break;
	case "5": text = "사용 중지"; break;
	case "6": text = "강제 탈퇴"; break;
	case "7": text = "탈퇴"; break;
	}
	return text; 
}

function registTypeFormatter(value,row,index){
	var status = row.regType;
	if (!status) status = value;
	var text; 
	switch(status){
	case "U": text = "사용자 등록"; break;
	case "A": text = "관리자 등록";
		break;
	}
	return text; 
}

function registAdminFormatter(value,row,index){
	var status = row.regAdminNo;
	if (status == '0') {
		status = "-"
	}
	return status;
}

function paymentStausCellStyler(value,row,index){
	var status = row.paymentStatus;
	var text; 
	switch(status){
	case "1":  // 입금 결제 확인중
		//text = 'background-color:#F3F305;color:black;';
		break;
	case "2": // 입금(결제) 확인 완료
		break;
	case "3":  //입금(결제) 취소 확인중
		break;
	case "4":  //입금(결제) 취소 완료
		break;
	}
	
	return text;
}

function paymentTypeFormatter(value,row,index){
	var status = row.paymentType;
	var text; 
	switch(status){
	case "1": text = "온라인 입금 "; break;
	case "2": text = "신용카드"; break;
	}
	return  text ; 
}

function projectSizeFormatter(value,row,index){}
function projectStatusFormatter(value,row,index){}
function publicStatusFormatter(value,row,index){}
function projectActionFormatter(value,row,index){}

function paymentStatusFormatter(value,row,index){
	var status = row.paymentStatus;
	var text; 
	switch(status){
	case "1":
		text = "입금(결제) 확인중";
		text =  '<span style = "color : #FF4000"><i class = "fa fa-check" style="color:#424242;"></i>' + '&nbsp;' + addBoldFomatter(text) + '</span';
		break;
	case "2":
		text = "입금(결제) 확인 완료";
		text =  '<span style = "color : #0489B1"><i class = "fa fa-check-circle" style="color:#01DF01;"></i>' + '&nbsp;' + addBoldFomatter(text) + '</span';
		break;
	case "3":
		text = "입금(결제) 취소";
		text =  '<span><i class = "fa fa-check" style="color:#424242;">	</i>' + '&nbsp;' + addBoldFomatter(text) + '</span';
		break;
	case "4":
		text = "입금(결제) 환불 처리중";
		text =  '<span><i class = "fa fa-ban" style="color:red;"></i>' + '&nbsp;' + addBoldFomatter(text) + '</span';
		break;
	case "5":
		text = "입금(결제) 환불 완료";
		text =  '<span><i class = "fa fa-ban" style="color:red;"></i>' + '&nbsp;' + addBoldFomatter(text) + '</span';
		break;
	case "6":
		text = "고객 입금 확인 요청";
		text =  '<span style = ""><i class = "fa fa-exclamation-circle" style="color:red;"></i>' + '&nbsp;' + addBoldFomatter(text) + '</span';
		break;
	}
	return text; 
}

function loadNodeForm(data){
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	//console.log("/handleNodeForm?" + queryParam);
	
	$(data.targetElem).load("/springSecurity/handleNodeForm?" + queryParam,
		function(response, status, xhr) {
		//console.log("오픈할 DIV : " + data.targetElem);	
		
			$(data.targetElem).dialog({
				width:600,
			    modal : true,
			    /*cls:'c6',*/
		/*	    inline:true,*/
			    closable : true,
			    border : 'thick',
			    shadow : true,
			    collapsible : false,
			    minimizable : false,
			    maximizable: false,
			    title : "&nbsp; " + data.title,
			    shadow : false,	
				buttons:[
					{ text:'확인', iconCls:'icon-ok', handler:function(){
						var nodeType = $('input[name=nodeType]').val();
						switch(nodeType){
						case "1": break;
						case "2": createRecommender(data); break;
						case "3": break; 
						case "4": break;
						case "5": break;
						case "6": break;
						}
					} },
					{ text:'취소', handler:function(){
						console.log("닫을 DIV : " + data.targetElem);	
						$(data.targetElem).dialog('close');
					}
				}]
			});
			
		});
}

function loadMembershipForm(data){
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	//console.log("/handleMembershipForm?" + queryParam);
	$(data.targetElem).load("/springSecurity/handleMembershipForm?" + queryParam,
		function(response, status, xhr) {
		//console.log(status);	
		
				$(data.targetElem).dialog({
					width:650,
				    modal : true,
				    /*cls:'c6',*/
			/*	    inline:true,*/
				    border : 'thick',
				    shadow : true,
				    collapsible : false,
				    minimizable : false,
				    maximizable: false,
				    title : "&nbsp; " + data.title,
				    shadow : false,	
				    buttons:[
						{ text:'확인', iconCls:'icon-ok', handler:function(){
							var param = {
								memberNo : 	$('input[name=memberNo]').val(),	
								memberEmail : 	$('input[name=memberEmail]').val(),	
								paymentAmount : 	$('input[name=paymentAmount]').val(),	
								paymentStatus : 	$('#paymentStatus1').combobox('getValue'),	
								paymentType : 	$('#paymentType1').combobox('getValue'),
								regType : 	$('#regType').combobox('getValue'),	
							};
							//console.log(param);
							//console.log(data);
							var valid = true;
							for (var prop in param){
								if (param.hasOwnProperty(prop)) {
									if (param[prop] == '') {
										valid = false;
										break;
									}
								}
							}
							if (!valid) {
								$.messager.alert('알림', '입력 항목이 모두 입력되지 않았습니다');
								return;
							}	
							
							returnp.api.call("createMembershipRequest", param, function(res){
								if (res.resultCode  == "100") {
									$.messager.alert('알림', res.message);
								}else {
									$.messager.alert('오류 발생', res.message);
								}
							});
							
							$(data.targetElem).dialog('close');
						}},
						{text:'취소', handler:function(){
							//console.log("닫을 DIV : " + data.targetElem);	
							$(data.targetElem).dialog('close');
						}
					}]
				});
			
		});
}

function loadNodeListView(data, callback){
	data.targetElem = data.targetElem || "#dlgForm2";
	//console.log(data.targetElem)
	var queryParam = $.param(data.queryOptions);
	//console.log("/handleNodeListView?" + queryParam);
	
	$(data.targetElem).load("/springSecurity/handleNodeListView?" + queryParam,
		function(response, status, xhr) {
			console.log(status);	
			
			$(data.targetElem).dialog({
				width:'55%',
			    height: 650,
			    modal : true,
			   /* cls:'c6',*/
			    /*inline:true,*/
			    collapsible : false,
			    closable : true,
			    minimizable : false,
			    maximizable: false,
			    title : "&nbsp; " + data.title,
			    shadow : false,	
				buttons:[
					{text:'확인', iconCls:'icon-ok', handler:function(){
						var node  = $('#search_result').datagrid('getSelected');
		            	if (!node) {
		            		 $.messager.alert('알림','선택이 필요합니다');
		            		 return;
		            	}
		            	
						if (callback && typeof callback === "function" ) {
							callback(node);
						}
						$(data.targetElem).dialog('close');
					}},
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
					}
				}]
			});
			
		});
}

function loadCommonListView(data, callback){
	data.targetElem = data.targetElem || "#dlgForm";
	//console.log(data.targetElem)
	var queryParam = $.param(data.queryOptions);
	//console.log("/loadCommonListView?" + queryParam);
	
	$(data.targetElem).load("/springSecurity/handleCommonListView?" + queryParam,
		function(response, status, xhr) {
			//console.log(status);	
			
			$(data.targetElem).dialog({
				width:'55%',
			    height: 650,
			    modal : true,
			   /* cls:'c6',*/
			    /*inline:true,*/
			    collapsible : false,
			    closable : true,
			    minimizable : false,
			    maximizable: false,
			    title : "&nbsp; " + data.title,
			    shadow : false,	
				buttons:[
					{text:'확인', iconCls:'icon-ok', handler:function(){
						var node  = $('#search_result').datagrid('getSelected');
		            	if (!node) {
		            		 $.messager.alert('알림','선택이 필요합니다');
		            		 return;
		            	}
		            	
						if (callback && typeof callback === "function" ) {
							callback(node);
						}
						$(data.targetElem).dialog('close');
					}},
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
					}
				}]
			});
			
		});
}

function loadMyMemberList (title,params){
	var queryParam = $.param(params);
	$("#dlgForm").load("/api/member/template/memberList?" + queryParam,
			function(response, status, xhr) {
			
					$('#dlgForm').dialog({
						width:1200,
						cache: false,
					    modal : true,
					    closable : true,
					    border : 'thick',
					    constrain : true,
					    shadow : true,
					    collapsible : false,
					    minimizable : false,
					    maximizable: false,
					    title : "&nbsp; " + title,
					    shadow : false,	
						buttons:[
							{ 
								text:'확인', iconCls:'icon-ok', handler:function(){
									$('#dlgForm').dialog('close');
									$('#dlgForm').removeAttr('style');
								} 
							}/*,
							{
								text:'취소', handler:function(){
									$('#dlgForm').dialog('close');
									$('#dlgForm').removeAttr('style');
								}
							}*/
						]
				});
			
			$('#dlgForm').dialog('center');
			returnp.api.call('findMyMembers', queryParam , function(res){
				if (res.resultCode  == "100") {
					console.log(res)
					$('#member_list_grid').datagrid({
						data : res.rows
					})
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
		});
}
/* 해당 결제에 대한 포인트 백 세부 레코드 리스트 생성*/
function loadPaymentPointbackRecord(title,params){
	//console.log("loadPaymentPointbackRecord");
	//console.log(params);
	var queryParam = $.param(params);
	$("#dlgForm").load("/api/paymentPointbackRecord/template/paymentPointbackRecordList?" + queryParam,
			function(response, status, xhr) {
			
					$('#dlgForm').dialog({
						width:1200,
						cache: false,
					    modal : true,
					    closable : true,
					    border : 'thick',
					    constrain : true,
					    shadow : true,
					    collapsible : false,
					    minimizable : false,
					    maximizable: false,
					    title : "&nbsp; " + title,
					    shadow : false,	
						buttons:[
							{ 
								text:'확인', iconCls:'icon-ok', handler:function(){
									$('#dlgForm').dialog('close');
									$('#dlgForm').removeAttr('style');
								} 
							}/*,
							{
								text:'취소', handler:function(){
									$('#dlgForm').dialog('close');
									$('#dlgForm').removeAttr('style');
								}
							}*/
						]
				});
			
			$('#dlgForm').dialog('center');
			returnp.api.call('findPaymentPointbackRecords2', queryParam , function(res){
				if (res.resultCode  == "100") {
					//console.log(res)
					$('#list_search_result').datagrid({
						data : res.rows
					})
					var totalAmount = 0
					for (var i = 0; i < res.rows.length; i++) {
						totalAmount += parseInt(res.rows[i].pointbackAmount);
					}
					$('#list_search_result').datagrid('appendRow',{
						paymentTransactionNo : "소계",
						pointbackAmount : totalAmount
					});
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
		});
}

$.fn.datebox.defaults.formatter = function(date){
	
	var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return [y,formatDate(m),formatDate(d)].join('-');
}
$.fn.datebox.defaults.parser = function(s){
	
	if (!s) return new Date();
    var ss = s.split('-');
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d)
    } else {
        return new Date();
    }
}

$.fn.serializeSearchData = function(){
	
	var $form = $(this);
	var fromElements = $form.find("input.textbox-value");
	var arr = new Array();
    var searchName = "searchKeyword";
    $.each( this.serializeArray(), function(i,o){
    	
    	var keys = Object.keys(o);
    	var obj = new Object();
    	$.each(keys,function(k,key){  
    		
    		var n = searchName + (key=="name"?"Type":"");
    		obj[n] =obj[n]==undefined? o[key]
    		: $.isArray( obj[n] ) ? obj[n].concat( o[key] )
    		: [ obj[n], o[key] ];
    	});
    	var ele = $(fromElements).eq(i).closest("div").find("[textboxname='"+obj.searchKeywordType+"']");
    	var dataObj = eval("new Object(" +ele.data("returnp")+ ")");
    	$.extend(obj,dataObj);
    	arr.push(obj);
    });
    
    var newArr = new Array();
    var obj = new Object();
    $.each( arr, function(i,o){
    	
    	if(o.searchKeywordType=="searchKeywordType"){
    		if(o.searchKeyword=="0" || o.searchKeyword==""){
    			var linkedOptionString = "";
    			$("#searchKeywordType option").each(function()
				{
    				linkedOptionString+= $(this).val()=="0" || $(this).val()=="" ? "" : $(this).val() + " ";
				});
    			
    			linkedOptionString="concat(" + linkedOptionString.trim().split(" ").join(",") + ")";
    			obj.searchKeywordType=linkedOptionString;
    			
    		}else{
    			obj.searchKeywordType=o.searchKeyword;
    		}
    	}else if(o.searchKeywordType=="searchKeyword"){
    		obj.searchKeyword=o.searchKeyword;   
    		obj.compare = o.compare==undefined?"COMPARE.LIKE":o.compare;
    		obj.combind = o.combind==undefined?"COMBIND.UNION":o.combind;
    	}else{
    		newArr.push(o);
    	}
    });
    
    newArr.push(obj);   
    console.log(newArr);
    return newArr;
};


