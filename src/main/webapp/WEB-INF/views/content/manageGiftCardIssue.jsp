<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div style = "padding: 10px;"  id = "fileTab">
    
	<div title="<strong>상품권 발행</strong>"  style="padding:20px;display : inline-block" id="container"  style="display:none;">
			<div >
				<ul style ='font-weight: bold';'>
					<li style ="margin-bottom:5px;font-size:12px">상품원 상품 관리 페이지에서 상품권을 등록할 수 있습니다</li>
					<li style ="margin-bottom:5px;font-size:12px">상품권은 모바일과 실물상품권 2가지를 발행할 수 있지만, 혼용할 수 없습니다.</li>
					<li style ="margin-bottom:5px;font-size:12px">상품권은 한번에 <span style = "color: red">50,000</span> 매 까지 일괄 발행이 가능합니다</li>
					<li style ="margin-bottom:5px;font-size:12px">본사 발행의 경우 특수 목적을 위한 선발주 발행으로, 이러한 발행은 입금확인 없이 발행 버튼이 활성화 됩니다.</li>
				<!-- 	<li style ="margin-bottom:5px;font-size:12px">상품권 발생 및 관리 절차.
						<ul style ='font-weight: bold';'>
							<li style ="margin-bottom:5px;font-size:12px">상품권 생성</li>
							<li style ="margin-bottom:5px;font-size:12px">상품권 발행 내역 관리 페이지에서 생성 완료 확인</li>
							<li style ="margin-bottom:5px;font-size:12px">입금 완료로 변경하면 요청한 상품권 내역이 생성</li>
						</ul>
					</li> -->
					
				</ul>
			</div>
			<div style = "width:50%;float: left"  >
			<div style = "padding:20px 15px" >
			   <form id="giftCardIssueForm"   name = "giftCardIssueForm"  enctype="multipart/form-data" method="post">
					<div style="margin-bottom:20px;" >
						<select id ="giftCardOrderType"  name="giftCardOrderType" style="width:100%;" >
							<option value= "10">본사</option>
							<option value= "12">판매점</option>
						</select> 
					</div>
					<input type = "hidden" id ="giftCardSalesOrganNo"  name="giftCardSalesOrganNo"  > 
					<div style="margin-bottom:20px;;" ><input id ="giftCardSalesOrganName"  name="giftCardSalesOrganName" style="width:100%;" > </div>
					<div style="margin-bottom:20px;;" ><input id ="giftCardSalesOrganCode"  name="giftCardSalesOrganCode" style="width:100%;" > </div>
					
					<div style="margin-bottom:20px;">
						<select id ="giftCardNo" name="giftCardNo"   style="width:100%" >
							<c:forEach var="giftCard"  items="${giftCards}" varStatus="status">
								  <c:if test = "${ giftCard.productStatus == '1' }">
									  <option value='${giftCard.productNo}:${giftCard.productPrice}' >${giftCard.productName} - <fmt:formatNumber value="${giftCard.productPrice}" pattern="###,###,###,###"/> 원 상품권</option>
								  </c:if>
							</c:forEach>
						</select>
					</div>
					<div style="margin-bottom:20px;;" >
						<select id ="giftCardType"  name="giftCardType" style="width:100%;" >
							<c:forEach var="giftCardType"  items="${giftCardTypes}" varStatus="status">
								  <c:if test = "${ giftCardType.useInAdmin == 'Y' }">
									  <option value='${giftCardType.key}' >${giftCardType.value}</option>
								  </c:if>
							</c:forEach>
						</select> 
					</div>
					<input type = "hidden" name = "orderReason" id = "orderReason"/>
					<div style="margin-bottom:20px;" ><input id ="qty"  name="qty" style="width:100%;"> </div>
					<div style ="margin-bottom:10px;margin-top:10px;width : 600px;padding : 20px;background-color : #eeeeee"    class = "easyui-panel">
						<span style = "font-size : 16px; font-weight : 700;">총 발행 금액은 <span id = "total_price" style = "font-size : 16px; font-weight : 700;color : #F92F0C">  0 </span>&nbsp;원입니다</span>
					</div>
					<div style="margin-top:10px;padding:5px 0;align: right">
						<a href="javascript:void(0)" id = "create_btn"  style="width:60px;margin-right : 5px">발행</a>
						<a href="javascript:void(0)" id = "reset_btn"   style="width:60px">취소</a>
					</div>
				</form>
				
			</div>
		</div>
		<div style = "width:50%;float: left" ></div>
	</div>
	<div title="<strong>기타</strong>"  style="padding:20px;display:none;" id="container"   style="display:none;">
	</div>
	
</div>

<script>
$(document).ready(function(){
	$('#fileTab').tabs({
	    border:true,
	    plain : true,
	    pill : false,
	    plain : false,
	    onSelect:function(title){}
	});
	
	$('.easyui-panel').panel({ border: true });
	/* 검색 버튼  초기화*/
	
	$('#create_btn').linkbutton({
		onClick : function(){
			orderGiftCard()
		},
		iconCls:'icon-ok'
	});

	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
			resetForm();
		}
	});
	
	$('#giftCardSalesOrganCode').textbox({
		prompt: '주문자 코드',
		label : roundLabel("주문자 코드"),
		labelWidth : 100,
		width: 600,
		/* height : 25, */
		editable : false
	})

	$('#giftCardSalesOrganName').textbox({
		prompt: '검색 버튼을 눌러 검색',
		label : roundLabel("주문자 검색"),
		labelWidth : 100,
		width: 600,
		/* height : 25, */
		editable : false,
		icons : [{
	        iconCls:'icon-search',
	        handler: function(e){
	            var issuerType =  $("#giftCardOrderType").combobox("getValue");
	            var issuerTypeName =  $("#giftCardOrderType").combobox("getText");
	        	var v = $(e.data.target).textbox('getValue');
	            loadNodeListView({
	                targetElem : "#dlgForm2",
	                title : "상품권 " + issuerTypeName + " 검색",
	                queryOptions : {
	                    memberEmail : v,
	                    viewReqName : "nodeList",
	                    searchNodeType :  issuerType,
	                    width : '65%'
	                    
	                }
	            },function callback(selNode){
	            	$("#giftCardSalesOrganNo").val(selNode.giftCardSalesOrganNo);
	                $("#giftCardSalesOrganName").textbox('setValue', selNode.organName);
	                $("#giftCardSalesOrganCode").textbox('setValue', selNode.organCode);
	            });
	        }
	    }]
	});
	
	$('#giftCardOrderType').combobox({
		label : roundLabel("주문자 타입"),
		labelWidth : 100,
		showItemIcon: true,
		editable: false,
		width: 600,
		panelHeight: 'auto',
		labelPosition: 'left',
		multiple:false,
		onSelect : function(record){
			$("#giftCardSalesOrganNo").val("");
			$("#giftCardSalesOrganName").textbox("reset");
			$("#giftCardSalesOrganCode").textbox("reset");
			var orderReason = "";
			if (record.value == "10") {
				orderReason  = "1";
			}
			if (record.value == "12"){
				orderReason = "2"
			}
			$("#orderReason").val(orderReason);
		}
		//height : 25
	});

	
	
	$('#giftCardNo').combobox({
		label : roundLabel("발행 상품권"),
		labelWidth : 100,
		showItemIcon: true,
		editable: false,
		width: 600,
		panelHeight: 'auto',
		labelPosition: 'left',
		multiple:false,
		onSelect : function(record){
			var price = parseInt(record.value.split(":")[1]);
			var qty = $('#qty').val();
			$("#total_price").html(numberBoldFormatter(price * parseInt(qty)));
		}
		//height : 25
	});
	$('#giftCardType').combobox({
		label : roundLabel("상품권 유형"),
		labelWidth : 100,
		showItemIcon: true,
		editable: false,
		width: 600,
		panelHeight: 'auto',
		labelPosition: 'left',
		multiple:false
		//height : 25
	});

	$('#qty').numberbox({
		label : roundLabel("수량 (매수)"),
		labelWidth : 100,
		prompt: '수량 - 숫자만 입력 가능',
		width: 600,
		/* height : 25, */
		min:1,
		groupSeparator : ",",
		max : 50000,
		onChange : function(newValue, oldValue){
			var value = $('#giftCardNo').combobox("getValue");
			var price = parseInt(value.split(":")[1]);
			$("#total_price").html(numberBoldFormatter(price * parseInt(newValue)));
		},
		suffix : " 매"
	});	
});

function makeFormData(){
	var param = $("#giftCardIssueForm").serializeObject();
	return param;
}
function roundLabel(str){
	return '<span style = "border-radius: 15px;background-color: #444444;padding: 5px;color : #ffffff;font-weight : bold;">'+ str +'</span>';
}
function resetForm(){
	$("#giftCardSalesOrganNo").val("");
	$('#giftCardIssueForm').form("reset");;
}

function orderGiftCard(){
	var params = makeFormData();
	var valid = true;
	var fieldName = '';
	
	for (var prop in params){
		if (params.hasOwnProperty(prop)) {
			if (!params[prop] ||  params[prop] == '') {
				valid = false;
				fieldName = prop;
				break;
			}
		}
	}
	
	var fieldKorName = '';
	if (!valid) {
		if (fieldName == 'giftCardSalesOrganNo') {
			fieldKorName = "상품권 판매 조직 번호";
		}else {
			fieldKorName = $("#" +fieldName).prev().children("span").html();
		}
		
		$.messager.alert('알림', "<strong><span style = 'color : #FF3232'> " + fieldKorName + "</span></strong> 항목이 입력되지 않았습니다");
		return false;
	}
	console.log(">> 발행 옵션");
	console.log(params);
	
	params.giftCardNo = params.giftCardNo.split(":")[0].trim();
	$('#create_btn').linkbutton('disable');
	$('#reset_btn').linkbutton('disable');
	
	returnp.api.call("createGiftCardOrder", params, function(res){
		$('#create_btn').linkbutton('enable');
		$('#reset_btn').linkbutton('enable')
		
		if (res.resultCode  == "100") {
			$.messager.confirm( '알림', res.message, function(r){  resetForm()});
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
	
}
</script>
<script src="resources/js/${viewReqName}.js"></script>
