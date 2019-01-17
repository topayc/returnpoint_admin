<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="manual_regist_container" style ="padding:5px">
	<!--  <div id = "alert_register"  class="easyui-panel"  style  = "">
		 <ul>
		      <li style = "margin-bottom : 2px;font-weight: bold">관리자에 의한 수동 등록  시, 제휴 가맹점 정보가 입력되지 않아도 생성 및 적립이 가능</li>
		      <li style = "margin-bottom : 2px;font-weight: bold">관리자에 의한 수동 등록을 제외하고는 , 일반회원 대상으로는 정상 결제만 가능</li>
		    </ul>
		</div> -->
	<div style = "padding:20px 30px" >
	   <form id="createPaymentTransactionForm"   name = "createPaymentTransactionForm" >
	   		<input type = "hidden" name = "action"  id = "action" value = "${action}"/>
			<div style="margin-bottom:20px">
				<select id = "paymentTransactionType" class="easyui-combobox" name="paymentTransactionType"   data-options="label:'* 결제 등록 유형'" style="width:100%;height:25px">
		           	<c:forEach var="paymentTransactionType"  items="${paymentTransactionTypeList}" varStatus="status">
						   <c:if test = "${ paymentTransactionType.useInAdmin == 'Y' }">
							  <option value='${paymentTransactionType.key}' >${paymentTransactionType.value} </option>
						  </c:if>
					</c:forEach>
		         </select>
			</div>
			<div style="margin-bottom:20px" id = "targetNodeContainer">
				<select id = "targetNodeType" class="easyui-combobox" name="targetNodeType"   data-options="label:'* 대상 노드'" style="width:100%;height:25px"> 
					<c:forEach var="nodeType" items="${nodeTypeList}" varStatus="status">
						 <option value="${nodeType.key}" <c:if test="${nodeType.key == searchCondition.searchNodeType}">selected="selected"</c:if>><strong>${nodeType.value}</strong></option>
					</c:forEach>
				</select>
			</div>
			<div style="margin-bottom:20px"><input id ="nodeNo"  class="easyui-textbox" name="nodeNo" style="width:100%" data-options="label:'* 노드 번호',labelPosition : 'top'"> </div>
			<div style="margin-bottom:20px"><input id ="nodeName""  class="easyui-textbox" name="nodeName" style="width:100%" data-options="label:'* 노드 이름',labelPosition : 'top'"> </div> 
			<div style="margin-bottom:20px"><input id ="nodeEmail""  class="easyui-textbox" name="nodeEmail" style="width:100%" data-options="label:'* 노드 이메일 ',labelPosition : 'top'"> </div>
			<div style="margin-bottom:20px"><input id ="nodePhone""  class="easyui-textbox" name="nodePhone" style="width:100%" data-options="label:'* 노드 핸드폰 ',labelPosition : 'top'"> </div>
			<div style="margin-bottom:20px"><input id ="memberNo"  class="easyui-textbox" name="memberNo" style="width:100%" data-options="label:'* 회원 번호',labelPosition : 'top'"> </div>
			<div style="margin-bottom:20px"><input id ="memberName"  class="easyui-textbox" name="memberName" style="width:100%" data-options="label:'* 회원 이름',labelPosition : 'top'"> </div>
			<div style="margin-bottom:20px"><input id ="memberEmail"  class="easyui-textbox" name="memberEmail" style="width:100%" data-options="label:'* 회원 이름',labelPosition : 'top'"> </div>
			<div style="margin-bottom:20px" id = "affiliateNoContainer"><input id ="affiliateNo"  class="easyui-textbox" name="affiliateNo" style="width:100%" data-options="label:'* 협력 업체(가맹점)  ',labelPosition : 'top'"> </div>
			<div style="margin-bottom:20px" id = "affiliateSerialContainer"><input id ="affiliateSerial""  class="easyui-textbox" name="affiliateSerial" style="width:100%" data-options="label:'* 협력 업체 SN ',labelPosition : 'top'"> </div>
			<div style="margin-bottom:20px" ><input id ="paymentApprovalAmount"  class="easyui-textbox" name="paymentApprovalAmount" style="width:100%" data-options="label:'* 결제 금액',labelPosition : 'top'"> </div>
			<div style="margin-bottom:20px" id = "paymentApprovalNumberContainer"><input id ="paymentApprovalNumber"  class="easyui-textbox" name="paymentApprovalNumber" style="width:100%" data-options="label:'* 결제 승인 번호',labelPosition : 'top'"> </div>
			<div style="margin-bottom:20px">
				<select id ="paymentApprovalStatus"  class="easyui-combobox"  name="paymentApprovalStatus"   style="width:100%;height:25px" data-options="label:'* 결제 승인 상태',labelPosition : 'top'" >
					<c:forEach var="approvalStatus"  items="${paymentApprovalStatusList}" varStatus="status">
						   <c:if test = "${ approvalStatus.useInAdmin == 'Y' }">
							  <option value='${approvalStatus.key}' >${approvalStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			<div style="margin-bottom:20px">
				<input id ="paymentApprovalDateTime"  class="easyui-datetimebox" name="paymentApprovalDateTime" style="width:100%" data-options="label:'* 결제 승인(취소) 일자',labelPosition : 'top'" style="width:100%;height:25px"> 
			</div>
		</form>
	</div>
	 
</div>

<script>
setViewInit();

function setViewInit(){
	$('#alert_register').panel({ 
		border: true,
		title:'* 주의 사항',
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

	$('#nodeNo').textbox({
		
		prompt: '회원',
		editable : false,
		icons:[{
			iconCls:'icon-search',
			handler: function(e){
				var v = $(e.data.target).textbox('getValue');
				loadNodeListView({
            		targetElem : "#dlgForm2",
            		title : nodeTypeFormatter($('#targetNodeType').combobox("getValue"), null, null) + " 검색",
            		queryOptions : {
            			memberEmail : v,
            			viewReqName : "nodeList",
            			searchNodeType :  $('#targetNodeType').combobox("getValue")
            		}
            	}, function callback(selNode){
            		console.log(selNode);
            		var targetNodeType = $('#targetNodeType').combobox("getValue");
            		var fNode = keyValueNode[targetNodeType];
            		$(e.data.target).textbox('setValue', selNode[fNode  + "No"]);
            	
            		$("#memberNo").textbox('setValue', selNode["memberNo"]);
            		$("#memberName").textbox('setValue', selNode["memberName"]);
            		$("#memberEmail").textbox('setValue', selNode["memberEmail"]);
            		$("#nodeName").textbox('setValue', selNode[fNode  + "Name"]);
            		$("#nodeEmail").textbox('setValue', selNode[fNode  + "Email"]);
            		$("#nodePhone").textbox('setValue', selNode[fNode  + "Phone"]);
            		var ptType = $("#paymentTransactionType").combobox('getValue');
            	});
			}
		}]
	});

	$('#affiliateNo').textbox({
		prompt: '협력 업체',
		editable : false,
		icons:[{
			iconCls:'icon-search',
			handler: function(e){
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
            		$(e.data.target).textbox('setValue', selNode.affiliateNo);
            		$('#affiliateSerial').textbox('setValue', selNode.affiliateSerial);
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
	$('#affiliateSerial').textbox({
		editable : false,
		prompt: '가맹점 실제 Serial Number',
	});
	
	$('#paymentApprovalAmount').textbox({
		prompt: '협력 업체 결제 금액',
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
		prompt: '협력 업체 결제승인 번호',
	});
	$('#targetNodeType').combobox({
		showItemIcon: true,
    	labelWidth :150,
		labelPosition : 'left',
		labelAlign:'left',
        editable: false,
        panelHeight: 'auto',
        multiple:false,
        onSelect : function(record){
        	if (record.value != "1") {
        		$('#affiliateNoContainer').hide();
        		$('#affiliateSerialContainer').hide();
        		$('#paymentApprovalNumberContainer').hide();
        	}else {
        		$('#affiliateNoContainer').show();
        		$('#affiliateSerialContainer').show();
        		$('#paymentApprovalNumberContainer').show();
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
        editable : false,
        multiple:false,
        onSelect : function(record){
        	if (record.value == "3") {
        		$('#targetNodeContainer').show();
        	}else {
        		$('#targetNodeContainer').hide();
        	}
        }
   /*      readonly : true */
	});
	
	/* 개별 위젯 설정 끝 */
	
	/* 개별 위젯 값 세팅 */
	$('#paymentApprovalStatus').combobox('select', '1');
	$('#paymentTransactionType').combobox('select',3);
	
	/*
	* 결제 내역 생성시에는 결제 상태느 승인만 가능함
	* (결제 내역 수정인 경우는 다 가능)
	*/ 
/* 	if ($("#action").val() == "create") {
		$("#paymentApprovalStatus").combobox('select', 1);
		$("#paymentApprovalStatus").combobox({readonly : true});
	} */
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
	var data = makeVanPaymentParam();
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

function makeVanPaymentParam(){
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
	//console.log(param);
	return param;
}


</script>