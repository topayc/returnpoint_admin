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
	
	$('#payAmount').numberbox({
		label : roundLabel("기준 금액"),
		labelWidth : 100,
		prompt: '숫자만 입력 가능',
		width: 600,
		/* height : 25, */
		min:1,
		groupSeparator : ",",
	/*	max : 50000,*/
		onChange : function(newValue, oldValue){
			var rate = parseInt($('#accPointRate').combobox("getValue"));
			$("#accPointAmount").numberbox("setValue", parseInt(newValue) * (rate / 100));
		}
	});	
	
	$('#accPointRate').combobox({
		label : roundLabel("포인트 적립율"),
		labelWidth : 100,
		showItemIcon: true,
		editable: false,
		width: 600,
		panelHeight: 'auto',
		labelPosition: 'left',
		multiple:false,
		onSelect : function(record){
			var rate = parseInt(record.value);
			var amount = parseInt($('#payAmount').numberbox("getValue"));
			if (!isNaN(amount)){
				$("#accPointAmount").numberbox("setValue", amount * (rate / 100));
			}
		}
		//height : 25
	});

	$('#accTargetRange').combobox({
		label : roundLabel("적립 대상"),
		labelWidth : 100,
		showItemIcon: true,
		editable: false,
		width: 600,
		panelHeight: 'auto',
		labelPosition: 'left',
		multiple:false,
		onSelect : function(record){
		}
	//height : 25
	});



	$('#accPointAmount').numberbox({
		label : roundLabel("적립 포인트"),
		labelWidth : 100,
		editable : false,
		prompt: '적립 포인트',
		width: 600,
		/* height : 25, */
		min:1,
		groupSeparator : ",",
		suffix : " 포인트"
	});	
	
	
	$('#qty').numberbox({
		label : roundLabel("수량 (매수)"),
		labelWidth : 100,
		prompt: '숫자만 입력 가능',
		width: 600,
		/* height : 25, */
		min:1,
		groupSeparator : ",",
		max : 50000,
		onChange : function(newValue, oldValue){
		},
		suffix : " 매"
	});	
	
	$('#create_btn').linkbutton({
		onClick : function(){
			createPointCoupon()
		},
		iconCls:'icon-ok'
	});

	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
			resetForm();
		}
	});
});

function createPointCoupon(){
	var valid = true;
	var fieldName = '';
	var params = makeFormData();	
	
	for (var prop in params){
		if (params.hasOwnProperty(prop)) {
			if (!params[prop] ||  params[prop] == '') {
				valid = false;
				fieldName = prop;
				/*console.log(fieldName);*/
				break;
			}
		}
	}
	
	if (!valid ) {
		$.messager.alert('알림', "<strong><span style = 'color : #FF3232'> " + fieldName + "</span></strong> 항목이 입력되지 않았습니다");
		return;
	}
	returnp.api.call("createPointCoupon", params, function(res){
		$('#create_btn').linkbutton('enable');
		$('#reset_btn').linkbutton('enable')

		if (res.resultCode  == "100") {
			$.messager.confirm( '알림', res.message, function(r){  resetForm()});
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function makeFormData(){
	var param = $("#pointCouponCreate").serializeObject();
	return param;
}

function resetForm(){
	$('#pointCouponCreate').form("reset");;
}

