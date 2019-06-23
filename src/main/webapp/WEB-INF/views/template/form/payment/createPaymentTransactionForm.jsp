<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="manual_regist_container" style ="padding:5px">
<!-- 	<div id = "alert_register"  class="easyui-panel"  style  = "">
		 <ul>
		      <li style = "margin-bottom : 2px;font-weight: bold">임의 매출 및 적립을 생성할 수 있습니다.</li>
		      <li style = "margin-bottom : 2px;font-weight: bold">협력업체를 선택하지 않을 경우, 각 연결된 개별 노드까지만 임의 매출 및 적립 발생</li>
		      <li style = "margin-bottom : 2px;font-weight: bold">대리점 결제 생성 시  대리님, 지점, 총판까지 적립 발생</li>
		      <li style = "margin-bottom : 2px;font-weight: bold">회원 결제 생성시, 가맹점 정보 입력시 모든 계층 적립이 발생</li>
		    </ul>
	</div> -->
	<div style = "padding:20px 30px" >
	   <form id="createPaymentTransactionForm"   name = "createPaymentTransactionForm" >
			<div style="margin-bottom:12px">
				<select id = "paymentTransactionType" class="easyui-combobox" name="paymentTransactionType"   data-options="label:'* 결제 등록 유형'" style="width:100%;height:25px">
		           	<c:forEach var="paymentTransactionType"  items="${paymentTransactionTypeList}" varStatus="status">
						   <c:if test = "${ paymentTransactionType.useInAdmin == 'Y' }">
							  <option value='${paymentTransactionType.key}' >${paymentTransactionType.value} </option>
						  </c:if>
					</c:forEach>
		         </select>
			</div>

			<div style="margin-bottom:12px"><input id ="memberNo"  class="easyui-textbox" name="memberNo" style="width:100%" data-options="label:'* 구매 회원 번호',labelPosition : 'top'"> </div>
			<div style="margin-bottom:12px"><input id ="memberName"  class="easyui-textbox" name="memberName" style="width:100%" data-options="label:'* 구매 회원 이름',labelPosition : 'top'"> </div>
			<div style="margin-bottom:12px"><input id ="memberEmail"  class="easyui-textbox" name="memberEmail" style="width:100%" data-options="label:'* 구매 회원 이메일',labelPosition : 'top'"> </div>
			<div style="margin-bottom:12px"><input id ="memberPhone"  class="easyui-textbox" name="memberPhone" style="width:100%" data-options="label:'* 구매 회원 전화번호',labelPosition : 'top'"> </div>

			<div style="margin-bottom:12px" id = "targetNodeContainer">
				<select id = "nodeType" class="easyui-combobox" name="nodeType"   data-options="label:'* 노드 타입'" style="width:100%;height:25px"> </select>
			</div>
			<div style="margin-bottom:12px"><input id ="nodeNo"  class="easyui-textbox" name="nodeNo" style="width:100%" data-options="label:'* 노드 번호',labelPosition : 'top'"> </div>
			<div style="margin-bottom:12px"><input id ="nodeName"  class="easyui-textbox" name="nodeName" style="width:100%" data-options="label:'* 노드 이름',labelPosition : 'top'"> </div>
			<div style="margin-bottom:12px"><input id ="nodeEmail""  class="easyui-textbox" name="nodeEmail" style="width:100%" data-options="label:'* 노드 이메일 ',labelPosition : 'top'"> </div>
			<div style="margin-bottom:12px"><input id ="nodePhone""  class="easyui-textbox" name="nodePhone" style="width:100%" data-options="label:'* 노드 핸드폰 ',labelPosition : 'top'"> </div>
			<div id = "affiliatePayementContainer">
				<div style="margin-bottom:12px" id = "affiliateNoContainer"><input id ="affiliateNo"  class="easyui-textbox" name="affiliateNo" style="width:100%" data-options="label:'* 협력 업체(가맹점)  ',labelPosition : 'top'"> </div>
				<div style="margin-bottom:12px" id = "affiliateSerialContainer"><input id ="affiliateSerial""  class="easyui-textbox" name="affiliateSerial" style="width:100%" data-options="label:'* 협력 업체 TID ',labelPosition : 'top'"> </div>
				<div style="margin-bottom:12px" id = "affiliateSerialContainer"><input id ="paymentRouterType""  class="easyui-textbox" name="paymentRouterType" style="width:100%" data-options="label:'* 결제라우터 타입 ',labelPosition : 'top'"> </div>
				<div style="margin-bottom:12px" id = "affiliateSerialContainer"><input id ="paymentRouterName""  class="easyui-textbox" name="paymentRouterName" style="width:100%" data-options="label:'* 결제라우터 명 ',labelPosition : 'top'"> </div>
			<div style="margin-bottom:12px" ><input id ="paymentApprovalAmount"  class="easyui-textbox" name="paymentApprovalAmount" style="width:100%" data-options="label:'* 결제 금액',labelPosition : 'top'"> </div>
				<div style="margin-bottom:12px" id = "paymentApprovalNumberContainer">
				<input id ="paymentApprovalNumber"  class="easyui-textbox" name="paymentApprovalNumber" style="width:83%"  data-options="label:'* 결제 승인 번호',labelPosition : 'top'">
				<a id = "gen_pan" >번호생성</a>
				 </div>
			</div>
			<div style="margin-bottom:12px">
				<select id ="paymentApprovalStatus"  class="easyui-combobox"  name="paymentApprovalStatus"   style="width:100%;height:25px" data-options="label:'* 결제 승인 상태',labelPosition : 'top'" >
					<c:forEach var="approvalStatus"  items="${paymentApprovalStatusList}" varStatus="status">
						   <c:if test = "${ approvalStatus.useInAdmin == 'Y' }">
							  <option value='${approvalStatus.key}' >${approvalStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			<div style="margin-bottom:12px">
				<input id ="paymentApprovalDateTime"  class="easyui-datetimebox" name="paymentApprovalDateTime" style="width:100%" data-options="label:'* 결제 승인(취소) 일자',labelPosition : 'top'" style="width:100%;height:25px"> 
			</div>
		</form>
	</div>
</div>

<script>

$( document ).ready(function() {
	setViewInit();
});

function setViewInit(){
	$('#alert_register').panel({ 
		border: true
	});
	
	/* 위젯 공통 설정 */
	$('.easyui-textbox').textbox({
		labelWidth :150,
		labelPosition : 'left',
		labelAlign:'left',
		height : 25
	});
	
	$('.easyui-combobox').combobox({
		labelWidth :150,
		labelPosition : 'left',
		labelAlign:'left'
	});

	/* 위젯 공통 설정 끝 */

	$('#memberNo').textbox({
		prompt: '회원',
		editable : false,
		icons:[{
			iconCls:'icon-search',
			handler: function(e){
				var v = $(e.data.target).textbox('getValue');
				loadNodeListView({
            		targetElem : "#dlgForm2",
            		title :  "회원  검색",
            		queryOptions : {
            			memberEmail : v,
            			viewReqName : "nodeList",
            			searchNodeType : "1"
            		}
            	}, function callback(selNode){
            		$("#memberNo").textbox('setValue', selNode["memberNo"]);
            		$("#memberName").textbox('setValue', selNode["memberName"]);
            		$("#memberEmail").textbox('setValue', selNode["memberEmail"]);
            		$("#memberPhone").textbox('setValue', selNode["memberPhone"]);
               		
        			$('#nodeNo').textbox('clear');
	        		$('#nodeEmail').textbox('clear');
	        		$('#nodeName').textbox('clear');
	        		$('#nodePhone').textbox('clear');
	        		
            		returnp.api.call('getMember', {memberNo : selNode.memberNo}, function(res){
               			$("#nodeType").combobox("clear");
            			var comboDatas = []; 
            			console.log(res);
            			if (res.resultCode  == "100") {
            				
            				if (res.data.isSoleDist == "Y") comboDatas.push({nodeType : '7', nodeTypeName : "총판"});
            				if (res.data.isBranch == "Y") comboDatas.push({nodeType : '3', nodeTypeName : "지사"});
            				if (res.data.isAgency == "Y") comboDatas.push({nodeType : '4', nodeTypeName : "대리점"});
            				if (res.data.isAffiliate == "Y") comboDatas.push({nodeType : '5', nodeTypeName : "협력 업체"});
            				/*정회원과 추천인에게는 바로 매출을 발생할 수 없기 때문에 제외*/
            				//if (res.data.isRecommender == "Y") comboDatas.push({nodeType : '2', nodeTypeName : "정회원"});
            				//if (res.data.isSaleManager == "Y") comboDatas.push({nodeType : '6', nodeTypeName : "영업 관리자"});
							comboDatas.push({nodeType : '1', nodeTypeName : "일반 회원"});

							$('#nodeType').combobox('loadData', comboDatas);
							$('#nodeType').combobox('select', '1');
        				}else {
        					$.messager.alert('오류 발생', res.message);
        				}
        			});
            	});
			}
		}]
	});
	
	$('#nodeNo').textbox({
		
		prompt: '',
		editable : false,
	});

	$('#affiliateNo').textbox({
		prompt: '협력 업체',
		editable : false,
		icons:[{
			iconCls:'icon-search',
			handler: function(e){
				var nodeType = $('#nodeType').combobox("getValue");
				if (nodeType != "1") {
					$.messager.alert('알림','협력업체 결제 정보를 등록하려면, 노드 타입을 회원을 선택해야 합니다');
					return;
				}
				var v = $(e.data.target).textbox('getValue');
				loadNodeListView({
            		targetElem : "#dlgForm2",
            		title : "협력 업체",
            		queryOptions : {
            			memberEmail : v,
            			viewReqName : "nodeList",
            			searchNodeType :  "5"
            		}
            	}, function callback(selNode){
            		console.log(selNode);
            		$(e.data.target).textbox('setValue', selNode.affiliateNo);
            		$('#affiliateSerial').textbox('setValue', selNode.affiliateSerial);
            		$('#paymentRouterName').textbox('setValue', selNode.paymentRouterName);
            		$('#paymentRouterType').textbox('setValue', selNode.paymentRouterType);
            	});
			}
		}]
	});
	
	$('#memberEmail').textbox({
		editable : false
	});
	$('#memberName').textbox({
		editable : false
	});
	$('#memberPhone').textbox({
		editable : false
	});
	
	$('#paymentRouterType').textbox({
		editable : false,
		prompt: '결제 라우터 타입',
	});
	
	$('#paymentRouterName').textbox({
		editable : false,
		prompt: '결제 라우터 명',
	});
	$('#affiliateSerial').textbox({
		editable : false,
		prompt: '가맹점 T-ID',
	});
	
	$('#paymentApprovalAmount').textbox({
		prompt: '결제 금액',
	});
	$('#paymentApprovalDateTime').datetimebox({
		labelWidth :150,
		labelPosition : 'left',
		labelAlign:'left',
		height : 25,
		showSeconds: true,
	    prompt: '해당 거래 실제 승인(취소)일자',
	    editable : false,
	    showSeconds : true,
	    formatter :dateFormatter
	});
	$('#paymentApprovalNumber').textbox({
		prompt: '결제 승인 번호',
	});
	
	
	$('#gen_pan').linkbutton({
		onClick : function(){
			returnp.api.call('genPan', null, function(res){
				$("#paymentApprovalNumber").textbox('setValue', res.data);
			});
		},
		iconCls:'icon-add'
	});
	
	$('#nodeType').combobox({
		showItemIcon: true,
    	labelWidth :150,
		labelPosition : 'left',
		labelAlign:'left',
        editable: false,
        panelHeight: 'auto',
        multiple:false,
        valueField : "nodeType",
        textField : "nodeTypeName",
        onChange : function(newValue,oldValue){
        	newValue = newValue ? newValue : "1";
        	if (newValue == "1") {
        		$('#nodeNo').textbox('setValue', $('#memberNo').textbox("getValue"));
        		$('#nodeEmail').textbox('setValue', $('#memberEmail').textbox("getValue"));
        		$('#nodePhone').textbox('setValue', $('#memberPhone').textbox("getValue"));
        		$('#nodeName').textbox('setValue', $('#memberName').textbox("getValue"));
        		$('#affiliatePayementContainer').show();
        	}else {
        		var memberNo = $('#memberNo').textbox('getValue');
        		var nodeType = $('#nodeType').combobox('getValue');
        		returnp.api.call('getNodes', {memberNo : memberNo, searchNodeType :nodeType }, function(res){
        			console.log(res);
        			var searchNode;
        			if (res.resultCode  == "100") {
        				if (res.rows.length >= 1) {
        					searchNode = res.rows[0];
        					$('#nodeNo').textbox('setValue', searchNode[keyValueNode[nodeType] + "No"]);
        	        		$('#nodeEmail').textbox('setValue', searchNode[keyValueNode[nodeType] +  "Email"]);
        	        		$('#nodeName').textbox('setValue', searchNode[keyValueNode[nodeType] +  "Name"]);
        	        		$('#nodePhone').textbox('setValue', searchNode[keyValueNode[nodeType] + "Phone"]);
        				}
    				}else {
    				
    				}
    			});
        		$('#affiliatePayementContainer').hide();
        	}
        }
	})
	
	$('#paymentApprovalStatus').combobox({
		showItemIcon: true,
    	labelWidth :150,
		labelPosition : 'left',
		labelAlign:'left',
        editable: false,
        panelHeight: 'auto',
        multiple:false,
        readonly : true
	});
	$('#paymentTransactionType').combobox({
		showItemIcon: true,
        panelHeight: 'auto',
        //readonly : true,
        multiple:false,
        onSelect : function(record){
        /* 	if (record.value == "3") {
        		$('#targetNodeContainer').show();
        	}else {
        		$('#targetNodeContainer').hide();
        	} */
        }
   /*      readonly : true */
	});
	
	/* 개별 위젯 설정 끝 */
	
	/* 개별 위젯 값 세팅 */
	$('#paymentApprovalStatus').combobox('select', '1');
	$('#paymentTransactionType').combobox('select',3);
	$('#paymentApprovalDateTime').datetimebox('setValue', dateNow());
	
	/*
	* 결제 내역 생성시에는 결제 상태느 승인만 가능함
	* (결제 내역 수정인 경우는 다 가능)
	*/ 
/* 	if ($("#action").val() == "create") {
		$("#paymentApprovalStatus").combobox('select', 1);
		$("#paymentApprovalStatus").combobox({readonly : true});
	} */
	
	returnp.api.call('genPan', null, function(res){
		$("#paymentApprovalNumber").textbox('setValue', res.data);
	});
}

function resetForm(){
	$('#memberNo').textbox('clear');
	$('#affiliateNo').textbox('clear');
	$('#paymentApprovalAmount').textbox('clear');
	$('#paymentApprovalNumber').textbox('clear');
	$('#affiliateSerial').textbox('clear');
	$('#paymentApprovalStatus').combobox('select', '1');
	$('#paymentApprovalDateTime').datetimebox("clear");
	$('#paymentTransactionType').combobox('select',2);
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

function createVanPayment(){
	var data = $('#createPaymentTransactionForm').serializeObject();
	return;
	var valid = true;
	for (var prop in data){
		if (data.hasOwnProperty(prop)) {
			if (data[prop] == '') {
				valid = false;
				break;
			}
		}
	}
	if (!valid) {
		$.messager.alert('알림', '입력 항목이 모두 입력되지 않았습니다');
		return;
	}	
	returnp.api.call("createPaymentTransaction", data, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			$('#createPaymentTransactionForm').form('clear');
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
	
}

/* function makePaymentParam(){
	var param = $('#createPaymentTransactionForm').serializeObject();
	console.log(param);
	
	var param = {
		memberNo : 	$('input[name=memberNo]').val(),	
		affiliateNo : 	$('input[name=affiliateNo]').val(),	
		memberEmail : 	$('input[name=memberEmail]').val(),	
		memberName : 	$('input[name=memberName]').val(),	
		memberPhone : 	$('input[name=memberPhone]').val(),	
		affiliateSerial : 	$('input[name=affiliateSerial]').val(),	
		paymentApprovalAmount : 	$('input[name=paymentApprovalAmount]').val(),		
		paymentApprovalStatus : 	$('#paymentApprovalStatus').combobox('getValue'),	
		paymentApprovalNumber : 	$('input[name=paymentApprovalNumber]').val(),	
		paymentApprovalDateTime : 	$('#paymentApprovalDateTime').datetimebox('getValue'),	
		paymentTransactionType : $('#paymentTransactionType').val()
	}; 
	return param;
} */


</script>