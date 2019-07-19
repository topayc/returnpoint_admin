
initView();
function initView(){
	$('#aa').accordion({
		animate:true
	});
	
	/* 패널   초기화*/
	$('.easyui-panel').panel({ border: false });
	/* 폼 초기화*/
	$('#searchForm').form();
	
	/* 검색어 입력 박스 초기화 */
	$('#keyword').textbox({ 
		width: 200,
		prompt : "검색할 단어를 입력해주세요" ,
		inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
			keyup:function(e){
				if(e.keyCode==13)
					realodPage();
			}
		})
	});
	
	
	$('#year').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 100
	});

	$('#month').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 80
	});

	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
		onClick : function(){
		},
		width : 70,
		iconCls:'icon-search'
	});
	
	/* 지급  초기화*/
	$('#act_point').linkbutton({
		onClick : function(){
			accSalesPoint();
		},
		width : 110,
		iconCls:'icon-ok'
	});
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
			$('#searchForm').form('reset');
		},
		width : 70
	});
	
}

function accSalesPoint(){
	var param = $('#searchForm').serializeObject(); 
	$.messager.confirm(
			 '영업자 포인트 지급', 
			 param.year + "년 " + param.month +  '월 기간의 매출 내역에 대하여 영업자 포인트를 지급합니다',
			 function(r){
				 if (r) {
					 returnp.api.call(
						"salePontAcc", 
						 param, 
						 function(res){
							$.messager.alert('알림', res.message);
						 }
					 );
				 }else {
				 }
			 }
		 );
}