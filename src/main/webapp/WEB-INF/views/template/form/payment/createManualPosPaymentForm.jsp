<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="manual_regist_container" style ="padding:5px">
	<div style = "padding:40px 30px" >
	   <form id="createVanPaymentForm"   name = "createPaymentForm" >
			<div style="margin-bottom:30px">
				<input id ="memberNo"  class="easyui-textbox" name="memberNo" style="width:100%" data-options="label:'* 회원  ',labelPosition : 'top'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="memberEmail""  class="easyui-textbox" name="memberEmail" style="width:100%" data-options="label:'* 회원 이메일 ',labelPosition : 'top'"> 
			</div>

			<div style="margin-bottom:30px">
				<input id ="memberName""  class="easyui-textbox" name="memberName" style="width:100%" data-options="label:'* 회원 이름',labelPosition : 'top'"> 
			</div>

			
			<div style="margin-bottom:30px">
				<input id ="memberPhone""  class="easyui-textbox" name="memberPhone" style="width:100%" data-options="label:'* 회원 핸드폰 ',labelPosition : 'top'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="affiliateNo"  class="easyui-textbox" name="affiliateNo" style="width:100%" data-options="label:'* 협력 업체(가맹점)  ',labelPosition : 'top'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="affiliateSerial"  class="easyui-textbox" name="affiliateSerial" style="width:100%" data-options="label:'* 협력 업체 SN ',labelPosition : 'top'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="approvalPayAmount"  class="easyui-textbox" name="approvalPayAmount" style="width:100%" data-options="label:'* 결제 금액',labelPosition : 'top'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="approvalNumber"  class="easyui-textbox" name="approvalNumber" style="width:100%" data-options="label:'* 결제 승인 번호',labelPosition : 'top'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<select id ="vanPaymentStatus"  class="easyui-combobox"  name="vanPaymentStatus"   style="width:100%;height:25px" data-options="label:'* 결제 상태',labelPosition : 'top'" >
					<c:forEach var="vanPaymentStatus"  items="${vanPaymentStatuses}" varStatus="status">
						   <c:if test = "${ vanPaymentStatus.useInAdmin == 'Y' }">
							  <option value='${vanPaymentStatus.key}' >${vanPaymentStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="approvalDateTime"  class="easyui-datetimebox" name="approvalDateTime" style="width:100%" data-options="label:'* 결제 승인(취소) 일자',labelPosition : 'top'" style="width:100%;height:25px"> 
			</div>
			
			<div style="margin-bottom:30px">
				<select id = "vanTransactionType" class="easyui-combobox" name="vanTransactionType" disabled = 'disabled'  data-options="label:'* 결제 내역 등록타입'" style="width:100%;height:25px">
		           <option value="1">VAN 자동 등록</option> 
		            <option value="2" selected = "selected'">관리자 수동 등록</option>
		         </select>
			</div>
			
			<div style="margin-top : 35px;margin-bottom:30px;text-align:right">
		  		<a id = "submit_btn"  style="width:80px;margin-right : 5px;margin-left:10px" >등록</a>
				<a id = "reset_btn"   style="width:80px">리셋</a>
			 </div>
		</form>
	</div>
	 
</div>

<script>
setViewInit();

function setViewInit(){
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
	
	/* 개별 위젯 설정 */
	$('#manual_regist_container').panel({
		iconCls:'icon-add',
		/* iconCls:'fa fa-plus-square', */
		width : "600",
	    title : " &nbsp;POS 결제 내역 수동 등록"
	});

	$('#memberNo').textbox({
		
		prompt: '회원',
		editable : false,
		icons:[{
			iconCls:'icon-search',
			handler: function(e){
				var v = $(e.data.target).textbox('getValue');
				loadNodeListView({
            		targetElem : "#dlgForm2",
            		title : "회원 검색",
            		queryOptions : {
            			memberEmail : v,
            			viewReqName : "nodeList",
            			searchNodeType :  "1"
            		}
            	}, function callback(selNode){
            		//console.log(selNode);
            		$(e.data.target).textbox('setValue', selNode.memberNo);
            		$("#memberName").textbox('setValue', selNode.memberName);
            		$("#memberEmail").textbox('setValue', selNode.memberEmail);
            		$("#memberPhone").textbox('setValue', selNode.memberPhone);
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
		editable : false,
	});
	
	$('#memberName').textbox({
		editable : false,
	});
	
	$('#memberPhone').textbox({
		editable : false,
	});
	
	
	$('#affiliateSerial').textbox({
		editable : false,
		prompt: '가맹점 실제 Serial Number',
	});
	
	$('#approvalPayAmount').textbox({
		prompt: '협력 업체 결제 금액',
	});
	
	$('#approvalDateTime').datetimebox({
		labelWidth :150,
		labelPosition : 'left',
		labelAlign:'left',
		height : 25,
		showSeconds: true,
	    prompt: '해당 거래 실제 승인(취소)일자',
	    editable : false,
	    showSeconds : true,
	    formatter : function(date){
	    	var year = date.getFullYear();  
	    	var month = date.getMonth()+1;  
	    	var d = date.getDate();  
	    	var hours = date.getHours();  
	    	var minutes  = date.getMinutes();  
	    	var seconds = date.getSeconds();  
	    	return  year + "-" + formatDate(month) + "-" + formatDate(d) + " "+ formatDate(hours) + ":" + formatDate(minutes) + ":" + formatDate(seconds);
	    }
	});
	
	$('#approvalNumber').textbox({
		prompt: '협력 업체 결제승인 번호',
	});

	$('#vanPaymentStatus').combobox({
		showItemIcon: true,
        data: [
            {value:'1',text:' 결제 승인',iconCls:'icon-ok',selected:true},
            {value:'2',text:' 결제 취소',iconCls:'icon-ok'},
            {value:'3',text:' 결제 승인 오류',iconCls:'icon-ok'}
        ],
    	labelWidth :150,
		labelPosition : 'left',
		labelAlign:'left',
        editable: false,
        panelHeight: 'auto',
        multiple:false,
	});
	
	$('#vanTranactionType').combobox({
		showItemIcon: true,
        editable: false,
        panelHeight: 'auto',
        multiple:false,
	});
	
	/* 검색 버튼  초기화*/
	$('#submit_btn').linkbutton({
		onClick : function(){
			createVanPayment()
		},
		iconCls:'icon-ok'
	});
	
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
			resetForm();
		}
	});
	/* 개별 위젯 설정 끝 */
	
	/* 개별 위젯 값 세팅 */
	$('#vanPaymentStatus').combobox('select', '1');
	$('#vanTranactionType').combobox('select',2);
}

function resetForm(){
	$('#memberNo').textbox('clear');
	$('#affiliateNo').textbox('clear');
	$('#approvalPayAmount').textbox('clear');
	$('#approvalNumber').textbox('clear');
	$('#affiliateSerial').textbox('clear');
	$('#vanPaymentStatus').combobox('select', '1');
	$('#approvalDateTime').datetimebox("clear");
	$('#vanTranactionType').combobox('select',2);
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
	if (adminId != 'topayc') {
		 $.messager.alert('알림','해당 기능을 사용할 수 있는 권한이 업습니다');
		 return;
	}
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
	returnp.api.call("createVanPaymentTransaction", data, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			$('#createVanPaymentForm').form('clear');
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
		approvalPayAmount : 	$('input[name=approvalPayAmount]').val(),		
		vanPaymentStatus : 	$('#vanPaymentStatus').combobox('getValue'),	
		approvalNumber : 	$('input[name=approvalNumber]').val(),	
		approvalDateTime : 	$('#approvalDateTime').datetimebox('getValue'),	
		vanTransactionType : $('#vanTransactionType').val()
	};
	//console.log(param);
	return param;
}

</script>