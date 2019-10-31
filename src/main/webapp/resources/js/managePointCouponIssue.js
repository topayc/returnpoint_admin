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
});

function makeFormData(){
	var param = $("#pointCouponCreate").serializeObject();
	return param;
}

function resetForm(){
	$('#pointCouponCreate').form("reset");;
}

