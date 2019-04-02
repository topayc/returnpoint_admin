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
