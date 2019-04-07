		var columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'giftCardPaymentNo',width:75,align:'center',title : '등록번호',hidden:false},
			    {field:'affiliateNo',width:80,align:'center',title : '제휴점 번호'},
			    {field:'affiliateName',width:100,align:'center',title : '제휴점 명'},
			    {field:'affiliateEmail',width:100,align:'center',title : '제휴점 이메일'},
			    {field:'affiliatePhone',width:100,align:'center',title : '제휴점 핸드폰'},
			    {field:'giftCardIssueNo',width:100,align:'center',title : '발행 상품권 번호', hidden: true},
			    {field:'giftCardName',width:100,align:'center',title : '상품권 이름'},
			    {field:'pinNumber',width:150,align:'center',title : '핀 번호'},
			    {field:'giftCardPaymentAmount',width:80,align:'center',title : '상품권 금액', formatter : numberRedFormatter},
			    {field:'refundRate',width:60,align:'center',title : '수수료율', formatter :  refundeRatePercenFormatter},
			    {field:'refundAmount',width:80,align:'center',title : '실 결제 금액', formatter : numberGreenFormatter},
			    {field:'refundStatus',width:100,align:'center',title : '결제 상태',formatter : refundStatusFormatter},
			    {field:'bankName',width:100,align:'center',title : '결제 은행'},
			    {field:'backAccount',width:120,align:'center',title : '결제 계좌'},
			    {field:'accountOwner',width:100,align:'center',title : '계좌주'},
			    {field:'createTime',width:130,align:'center',title : '등록일', formatter : dateFormatter},
			    {field:'updateTime',width:100,align:'center',title : '수정일', formatter : dateFormatter, hidden : true},
			    ]];
initView();

/**
 * 뷰 초기화 
 * @returns
 */
function initView(){
	/* 레이아웃 초기화*/
	$('.easyui-layout').layout();
	
	/* 패널   초기화*/
	$('.easyui-panel').panel({ border: false, fit : true }); 
	
	/* 폼 초기화*/
	$('#searchForm').form();
	
	/* 검색어 입력 박스 초기화 */
	$('#searchKeyword').textbox({ 
		width: 300,
		prompt : "검색할 단어를 입력해주세요" ,
		inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
			keyup:function(e){
				if(e.keyCode==13)
					realodPage();
			}
		})
	});
	
	$('.easyui-panel').panel();
	/* 노드 타입 셀렉트 박스  초기화*/
	$('#searchProductCategory').combobox({
		width: 150,
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		readonly:true,
	});
	
	/* 노드 상태 셀렉트 박스  초기화*/
	$('#searchProductStatus').combobox({
		width: 150,
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	/* 검색어 타입 셀렉트 박스  초기화*/
	$('#searchKeywordType').combobox({
		width: 150,
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});

	/* 검색 시작일 갤린더 박스  초기화*/
	$('#searchDateStart').datebox({	   
	    prompt : "검색 시작 일자",
	    labelPosition: 'top',
	    width: 170
	});
	
	/* 검색 종료일 갤린더 박스  초기화*/
	$('#searchDateEnd').datebox({	  
	    prompt : "검색 종료 일자",
	    labelPosition: 'top',
	    width: 170
	});
	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
		onClick : function(){
			realodPage();
		},
		iconCls:'icon-search'
	});
	
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
			/*$('#searchForm').form('clear');*/
			$('#searchNodeStatus').combobox('select', 0);
			$('#searchKeywordType').combobox('select', 0);
			$('#searchKeyword').textbox('clear');
			$('#searchDateStart').datetimebox('clear');
			$('#searchDateEnd').datetimebox('clear');
		}
	});
	
	/* 노드 데이타그리드   초기화*/
	$('#gift_card_payment_list').datagrid({
		title : '[검색 결과]',
		singleSelect:true,
		collapsible:false,
		//autoRowHeight: false,
		fitColumns:true,
		selectOnCheck : true,
		checkOnSelect : true,
		border:false,
		rownumbers : true,
		pagination: true,
		pagePosition : "top",
		onSelect : function(){},
		onLoadSuccess : function(){
			//$(this).datagrid('freezeRow',0).datagrid('freezeRow',1);
		},	
		onRowContextMenu : function(e, index, row){
			e.preventDefault();
		  	$(this).datagrid("selectRow", index);
		  	var cmenu = $('<div/>').appendTo('body');
		  	cmenu.menu({
		  		onClick : function(item){
		  			switch(item.action){
		  			case "modify":
		  				updateGiftCardOrder();
		  				break;
		  			case "remove":
		  				removeGiftCardOrder();
		  				break;
		  			case "view_order_detail":
		  				viewOrderDetail();
		  				break;
		  			}
		  		},
		  		itemHeight : 27
		  	});
		  	
		  	var menuArr = [];
			var selectedOrder = $('#gift_card_payment_list').datagrid('getSelected');
		  	
		  	cmenu.menu("appendItem", {
		  		id : "paymentStatusItem",
		  		text: '결제상태변경',
		  		/*	iconCls: 'icon-ok',*/
		  		onclick: function(){}
		  	});
		  	
		  	item = cmenu.menu('findItem', '결제상태변경');  
		  	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.refundStatus == "1" ? roundLabel("결제 처리중", "#04B404") : "결제 처리중",
		  		iconCls: row.refundStatus == "1" ? 'icon-ok' : "",
		  		onclick: function(){
		  			changeGiftCardRefundStatus('1');
		  		}
		  	});
			cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.refundStatus == "2" ? roundLabel("결제 완료", "#04B404") : "결제 완료",
				 iconCls: row.refundStatus == "2" ? 'icon-ok' : "",
		  		onclick: function(){
		  			changeGiftCardRefundStatus('2');
		  		}
		  	});
			cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.refundStatus == "3" ? roundLabel("결제 보류", "#04B404") : "결제 보류",
		  		iconCls: row.refundStatus == "3" ? 'icon-ok' : "",
		  		onclick: function(){
		  			changeGiftCardRefundStatus('3');
		  		}
		  	});
			cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.refundStatus == "4" ? roundLabel("사용자 결제 취소", "#04B404") : "사용자 결제 취소",
		  		iconCls: row.refundStatus == "4" ? 'icon-ok' : "",
		  		onclick: function(){
		  			changeGiftCardRefundStatus('4');
		  		}
		  		
		  	});
			cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.refundStatus == "5" ? roundLabel("관리자 결제 취소", "#04B404") : "관리자 결제 취소",
		  		iconCls: row.refundStatus == "5" ? 'icon-ok' : "",
		  		onclick: function(){
		  			changeGiftCardRefundStatus('5');
		  		}
		  	});
			
		  	
		  	cmenu.menu('show', {
		  		left:e.pageX,
		  		top:e.pageY
		  	})
		  	
		},
	    columns:columns
	    
	});
	setListPager();
}

function changeGiftCardRefundStatus(refundStatus){
	var node = $('#gift_card_payment_list').datagrid('getSelected');
	returnp.api.call("changeGiftCardRefundStatus", {giftCardPaymentNo : node.giftCardPaymentNo, refundStatus : refundStatus}, function(res){
		//console.log(res);
		if (res.resultCode  == "100") {
			//$.messager.alert('알림', res.message);
			var index = $('#gift_card_payment_list').datagrid('getRowIndex',node);
            $('#gift_card_payment_list').datagrid("updateRow", {
                index: index,
                row: res.data
            });
            $('#gift_card_payment_list').datagrid('unselectAll');
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function setListPager(){
	var pager = $('#gift_card_payment_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[/*{
            iconCls:'icon-add',
            handler:function(){
            	$('#gift_card_payment_list').datagrid('unselectAll');
            	$('#gift_card_payment_list').datagrid('uncheckAll');
            	loadProductCreateForm();
            }
        }*//*,{
            iconCls:'icon-edit',
            handler:function(){
              loadAgencyModifyForm();
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removeProduct();
            }
        },{
            iconCls:'icon-more',
            handler:function(){
            	var node = $('#gift_card_payment_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','자세히 보실 항목을  선택해주세요');
            		 return;
            	}
            }
        }*/],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
        onSelectPage:function(page,rows){        	
        	var opts = $('#gift_card_payment_list').datagrid('options');
        	opts.pageSize=rows;
        	opts.pageNumber = page;
        	realodPage();
    	}
    }); 
}

/**
 * 노드 타입에 따라 리스트 그리드 컬럼 헤더 변경
 * @param param
 * @returns
 */
function setListColumnHeader(nodeType){
	$('#gift_card_payment_list').datagrid({
		columns : columns
	});
}


/**
 * 검색 실행시 필요한 쿼리 데이타 구성 
 * @returns
 */
function makeSearchParam(){
	
	var param = $('#searchForm').serializeObject();
	var opts = $('#gift_card_payment_list').datagrid('options');
	var total = $('#gift_card_payment_list').datagrid('getData').total;
	
	$.extend(param, {
		pagination : opts.pagination,
		pageSize : opts.pageSize,
		page : opts.pageNumber,
		total : total,
		offset : (opts.pageNumber-1) * opts.pageSize
	});
	
	return param;
}


function makeFormData(){
	var param = $("#createGiftCardPaymentForm").serializeObject();
	return param;
}

function createGiftCardPayment(param){
	var param =makeFormData();
	var valid = true;
	var fieldName = '';
	for (var prop in param){
		if (param.hasOwnProperty(prop)) {
			if (param[prop] == '') {
				valid = false;
				fieldName = prop;
				break;
			}
		}
	}
	var fieldKorName = '';
	if (!valid) {
		fieldKorName = $("#" +fieldName).prev().children("span").html();
		$.messager.alert('알림', "<strong><span style = 'color : #FF3232'> " + fieldKorName + "</span></strong> 항목이 입력되지 않았습니다");
		return false;
	}else {
		returnp.api.call("createGiftCardPayment", param, function(res){
			//console.log(res);
			if (res.resultCode  == "100") {
				$.messager.alert('알림', res.message);
				$(data.targetElem).dialog('close');
				$(data.targetElem).removeAttr('style');
				realodPage();
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		});
	}
		
}

function updateGiftCardOrder(param){
	var param =makeFormData();
	var valid = true;
	var fieldName = '';
	for (var prop in param){
		if (param.hasOwnProperty(prop)) {
			if (param[prop] == '') {
				valid = false;
				fieldName = prop;
				break;
			}
		}
	}
	var fieldKorName = '';
	
	if (!valid) {
		fieldKorName = $("#" +fieldName).prev().children("span").html();
		$.messager.alert('알림', "<strong><span style = 'color : #FF3232'> " + fieldKorName + "</span></strong> 항목이 입력되지 않았습니다");
		return false;
	}else {
		returnp.api.call("updateGiftCardPayment", param, function(res){
			if (res.resultCode  == "100") {
				$.messager.alert('알림', res.message);
				realodPage();
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		});
	}
}

function removeGiftCardOrder(){
	var node = $('#gift_card_payment_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        			giftCardPaymentNo : node.giftCardPaymentNo
        	}
        	returnp.api.call("deleteGiftCardPayment", param, function(res){
        		if (res.resultCode  == "100") {
        			$.messager.alert('알림', res.message);
        			realodPage();
        		}else {
        			$.messager.alert('오류 발생', res.message);
        		}
        	});
        }
    });
}

function viewOrderDetail(){
	var order = $('#gift_card_payment_list').datagrid('getSelected');
	$('#more_detail_view').dialog({
		title: order.orderName,
		width: 800,
		height: "830",
		closed: false,
		cache: false,
		modal: true,
		onOpen : function(){
			var order = $('#gift_card_payment_list').datagrid('getSelected');
			var propertyOrder = { total : Object.keys(order).length , rows:[] };
			var exitArr = ['pagination', 'pageSize', 'page', 'total', 'order','offset', 'productNo']
			//var columns = [[]];
			for (var property in order) {
				if (order.hasOwnProperty(property)){
					if (!exitArr.hasValue(property) ) {
						propertyOrder['rows'].push({name : property, nameKor : orderColumnKorFormatter(property), value : propGridValueForamtter(property, order[property]), group : "주문 정보", editor : null})
						//columns[0].push({field: property, width : 100, align : "center", title : property});
					}
				}
			}
			$('#order_overview').propertygrid({
				data : propertyOrder,
				showGroup: false,
				scrollbarSize: 0,
				border : true,
				columns:[[
					{field:'nameKor',title:'항목',width:4,resizable:true},
					{field:'value',title:'값',width:8, resizable:false}
					]]
			});
			
			console.log(columns);
			/* 주문 상세 아이템 리스트*/
/*			
			$('#order_item_list').datagrid({
				singleSelect:true,
				collapsible:false,
				border : true,
				//autoRowHeight: false,
				fitColumns:true,
				selectOnCheck : true,
				checkOnSelect : true,
				border:false,
				rownumbers : true,
			    columns:orderListItemColumns,
				onRowContextMenu : function(e, index, row){
					e.preventDefault();
				  	$(this).datagrid("selectRow", index);
				  	var cmenu = $('<div/>').appendTo('body');
				  	cmenu.menu({
				  		onClick : function(item){
				  			switch(item.action){
				  			case "view_gift_list":
				  				viewGiftList();
				  				break;
				  			}
				  		}
				  	});
				  	
				  	var menus = [ '해당 주문 아이템의 상품권 발행 리스트 보기'];
				  	var icons = ['icon-more'];
				  	var actions = ['view_gift_list'];
				  	
				  	for(var i=0; i<menus.length; i++){
				  		cmenu.menu('appendItem', {
				  			data : row,
				  			no : row.memberNo,
				  			text:  "<strong>[" + row.orderItemName + "]</strong>"  + " " + menus[i],
				  			action: actions[i],
				  			iconCls: icons[i]
				  		});
				  	}
				  	cmenu.menu('show', {
				  		left:e.pageX,
				  		top:e.pageY
				  	});
				},
			});
			returnp.api.call("selectGiftCardItemsOrders", {orderNumber : order.orderNumber}, function(res){
				console.log("### 주문 아이템");
				console.log(res);
				if (res.resultCode == "100") {
					setOrderListColumnHeader();
					$('#order_item_list').datagrid({
						data : res
					});
				}else {
					$.messager.alert(res.message, res.data);
				}
			});*/
		
		}
	});
	
}

function realodPage(){
	var param = makeSearchParam();
	returnp.api.call("selectGiftCardPayment", param, function(res){
		console.log(res);
		if (res.resultCode == "100") {
			setListColumnHeader(param.searchNodeType);
			$('#gift_card_payment_list').datagrid({
				data : res,
				title : '[검색 결과] ' + res.total + " 개의 결과가 검색되었습니다",
			});
			setListPager();
		}else {
			$.messager.alert(res.message, res.data);
		}
	});
}

$(document).ready(function(){
	$('#search_btn').click();
});