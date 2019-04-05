		var columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'orderNo',width:70,align:'center',title : '등록번호',hidden:true},
			    {field:'orderNumber',width:100,align:'center',title : '주문 번호'},
			    {field:'orderName',width:180,align:'center',title : '주문명', formatter : orderNameFormatter},
			    {field:'orderType',width:90,align:'center',title : '주문자 타입', formatter : orderTypeFormatter},
			    {field:'orderReason',width:90,align:'center',title : '발주 목적', formatter : orderReasonFormatter},
			    {field:'bargainType',width:100,align:'center',title : '거래 타입', formatter : bargainTypeFormatter},
			    {field:'ordererName',width:100,align:'center',title : '주문자'},
			    {field:'ordererId',width:100,align:'center',title : '주문자 코드'},
			    {field:'ordererEmail',width:100,align:'center',title : '주문자 이메일'},
			    {field:'ordererPhone',width:100,align:'center',title : '주문자 핸드폰'},
			    {field:'giftCardNo',width:100,align:'center',title : '상품권 번호', hidden : true},
			    {field:'giftCardName',width:100,align:'center',title : '상품권 이름', hidden : false},
			    {field:'giftCardType',width:100,align:'center',title : '상품권 타입', formatter : giftCardTypeFormatter2},
			    {field:'giftCardAmount',width:100,align:'center',title : '상품권 금액', hidden : true},
			    {field:'giftCardSalePrice',width:100,align:'center',title : '상품권 가격', hidden : false, formatter : numberGreenFormatter},
			    {field:'qty',width:100,align:'center',title : '주문 수량', hidden : false, formatter : numberFormatter},
			    {field:'orderTotalPrice',width:160,align:'center',title : '총 가격', formatter : numberRedFormatter},
			    {field:'paymentType',width:100,align:'center',title : '결제 수단', formatter : paymentTypeFormatter},
			    {field:'paymentStatus',width:150,align:'center',title : '결제 상태' , formatter : paymentStatusFormatter},
			    {field:'orderStatus',width:130,align:'center',title : '주문 상태', formatter : orderStatusFormatter},
			    {field:'issueStatus',width:130,align:'center',title : '발행 상태', formatter : issueStatusFormatter},
			    {field:'noname',width:80,align:'center',title : '발행' , formatter : issueActionFormatter, hidden: false},
			    {field:'deliveryNumber',width:100,align:'center',title : '송장 번호', hidden : true},
			    {field:'receiverName',width:100,align:'center',title : '수취자 이름',hidden : false},
			    {field:'receiverPhone',width:100,align:'center',title : '수취자 핸드폰',hidden : true},
			    {field:'receiverEmail',width:100,align:'center',title : '수취자 이메일',hidden : true},
			    {field:'deliveryAddress',width:100,align:'center',title : '배송지 주소',hidden : true},
			    {field:'deliveryMessage',width:100,align:'center',title : '배송 메세지', hidden : true},
			    {field:'orderTime',width:200,align:'center',title : '주문일',formatter : dateFormatter},
			    {field:'createTime',width:100,align:'center',title : '등록일',hidden : true,formatter : dateFormatter},
			    {field:'updateTime',width:100,align:'center',title : '수정일', hidden : true,formatter : dateFormatter},
			    ]];
		var orderListItemColumns= [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'orderItemNo',width:50,align:'center',title : '등록번호',hidden:false},
			    {field:'orderNumber',width:70,align:'center',title : '주문 번호'},
			    {field:'productNo',width:50,align:'center',title : '상품 번호',},
			    {field:'productName',width:150,align:'center',title : '상품명'},
			    {field:'productPrice',width:90,align:'center',title : '상품 가격',formatter : numberFormatter},
			    {field:'qty',width:100,align:'center',title : '수량', formatter : addBoldFomatter},
			    {field:'totalPrice',width:100,align:'center',title : '총 금액',formatter : numberRedFormatter},
			    {field:'createTime',width:100,align:'center',title : '등록일',formatter : dateFormatter},
			    {field:'updateTime',width:100,align:'center',title : '수정일',formatter : dateFormatter}
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
			//$.messager.progress();
			var param = makeSearchParam();
			/* 본사 발주 상품 검색*/
			param.orderType = "10";
			//console.log("검색 쿼리 데이타");
			//console.log(param);
			
			returnp.api.call("selectGiftCardOrders", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					setListColumnHeader(param.searchNodeType);
					$('#gift_card_order_list').datagrid({
						data : res,
						title : '[검색 결과] ' + res.total + " 개의 결과가 검색되었습니다",
					});
					setListPager();
				}else {
					$.messager.alert(res.message, res.data);
				}
			});
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
	$('#gift_card_order_list').datagrid({
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
		pageSize : returnpCommon.appInfo.gridPageSize,
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
			var selectedOrder = $('#gift_card_order_list').datagrid('getSelected');
		  	
		  	cmenu.menu("appendItem", {
		  		id : "paymentStatusItem",
		  		text: '결제상태',
		  		/*	iconCls: 'icon-ok',*/
		  		onclick: function(){}
		  	});
		  	
		  	item = cmenu.menu('findItem', '결제상태');  
		  	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.paymentStatus == "1" ? roundLabel("입금 결제 확인중", "#04B404") : "입금 결제 확인중",
		  		iconCls: row.paymentStatus == "1" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, paymentStatus : "1"});
		  		}
		  	});
			cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.paymentStatus == "2" ? roundLabel("입금 결제 확인 완료", "#04B404") : "입금 결제 확인 완료",
				 iconCls: row.paymentStatus == "2" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, paymentStatus : "2"});
		  		}
		  	});
			cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.paymentStatus == "3" ? roundLabel("입금 결제 취소", "#04B404") : "입금 결제 취소",
		  		iconCls: row.paymentStatus == "3" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, paymentStatus : "3"});
		  		}
		  	});
			cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.paymentStatus == "4" ? roundLabel("입금 결제 환불 처리중", "#04B404") : "입금 결제 환불 처리중",
		  		iconCls: row.paymentStatus == "4" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, paymentStatus : "4"});
		  		}
		  	});
			cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.paymentStatus == "5" ? roundLabel("입금 결제 환불 완료", "#04B404") : "입금 결제 환불 완료",
		  		iconCls: row.paymentStatus == "5" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, paymentStatus : "5"});
		  		}
		  	});
			
		  	//------------------------------------------------------
		  	cmenu.menu('appendItem', {
		  		separator: true
		  	});
		  	//------------------------------------------------------
			cmenu.menu("appendItem", {
		  		id : "orderStatus",
		  		text: '주문상태',
		  		/*iconCls: 'icon-ok',*/
		  		onclick: function(){}
		  	});
		  	var item = cmenu.menu('findItem', '주문상태');  
		  	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.orderStatus == "1" ? roundLabel("주문 접수", "#04B404") : "주문 접수",
				iconCls: row.orderStatus == "1" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, orderStatus : "1"});
		  		}
		  	});
		  	
		  	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text: '상품 준비중',
		  		text:  row.orderStatus == "2" ? roundLabel("상품 준비중", "#04B404") : "상품 준비중",
				iconCls: row.orderStatus == "2" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, orderStatus : "2"});
		  		}
		  	});
		  	
		  	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.orderStatus == "3" ? roundLabel("상품 준비 완료", "#04B404") : "상품 준비 완료",
				iconCls: row.orderStatus == "3" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, orderStatus : "3"});
		  		}
		  	});
		  	
		  	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.orderStatus == "4" ? roundLabel("배송 준비", "#04B404") : "배송 준비",
				iconCls: row.orderStatus == "4" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, orderStatus : "4"});
		  		}
		  	});
		  	
		  	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.orderStatus == "5" ? roundLabel("배송 중", "#04B404") : "배송 중",
				iconCls: row.orderStatus == "5" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, orderStatus : "5"});
		  		}
		  	});
		  	
		  	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.orderStatus == "6" ? roundLabel("배송 완료", "#04B404") : "배송 완료",
				iconCls: row.orderStatus == "6" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, orderStatus : "6"});
		  		}
		  	});
		  	
		  	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.orderStatus == "7" ? roundLabel("배송 완료", "#04B404") : "주문 처리 완료",
				iconCls: row.orderStatus == "7" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, orderStatus : "7"});
		  		}
		  	});
		  	
		 	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.orderStatus == "8" ? roundLabel("주문 취소", "#04B404") : "주문 취소",
				iconCls: row.orderStatus == "8" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, orderStatus : "8"});
		  		}
		  	});
		 	
		 	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text:  row.orderStatus == "9" ? roundLabel("관리자 주문 취소", "#04B404") : "관리자 주문 취소",
				iconCls: row.orderStatus == "9" ? 'icon-ok' : "",
		  		onclick: function(){
		  			updateGiftCardOrder({orderNo : selectedOrder.orderNo, orderStatus : "9"});
		  		}
		  	});
		  	
		  	
			//------------------------------------------------------
		  	cmenu.menu('appendItem', {
		  		separator: true
		  	});
		  
		  	//------------------------------------------------------
		  	cmenu.menu("appendItem", {
		  		id : "orderStatus",
		  		text: '상품권발행',
		  		/*iconCls: 'icon-ok',*/
		  		onclick: function(){}
		  	});
		  	
		  	item = cmenu.menu('findItem', '상품권발행');  
		  	if (row.paymentStatus == "2" && row.issueStatus == "1"){
		  		cmenu.menu('appendItem', {
		  			parent: item.target,  // the parent item element
		  			text:  "상품권 발행 시작",
		  			iconCls: 'icon-reload',
		  			onclick: function(){
		  				issueGiftCardOrder({orderNo : selectedOrder.orderNo});
		  			}
		  		});
		  	}
		  	if (row.paymentStatus == "2" && row.issueStatus == "2"){
		  		cmenu.menu('appendItem', {
		  			parent: item.target,  // the parent item element
		  			text:  "진행중인 상품권 발행 중지",
		  			iconCls: 'icon-undo',
		  			onclick: function(){
		  				stopGiftCardOrder({orderNo : selectedOrder.orderNo});
		  			}
		  		});
		  	}
		  	if (row.issueStatus == "3"){
		  		cmenu.menu('appendItem', {
		  			parent: item.target,  // the parent item element
		  			text:  "상품권 발행 취소",
		  			iconCls: 'icon-no',
		  			onclick: function(){
		  				cancelGiftCardOrder({orderNo : selectedOrder.orderNo});
		  			}
		  		});
		  	}
		 	
		  	//------------------------------------------------------
		  	cmenu.menu('appendItem', {
		  		separator: true
		  	});
		  	//------------------------------------------------------
		  	cmenu.menu("appendItem", {
		  		text: '주문 내역 상세 보기',
		  		/*	iconCls: 'icon-ok',*/
		  		onclick: function(){
		  			viewOrderDetail();
		  			
		  		}
		  	});
		  	
			//------------------------------------------------------
		  	cmenu.menu('appendItem', {
		  		separator: true
		  	});
		  
		  	//------------------------------------------------------
		  	cmenu.menu("appendItem", {
		  		id : "listGiftCards",
		  		text: '해당 주문의 발행 상품권 리스트  ',
		  		/*iconCls: 'icon-ok',*/
		  		onclick: function(){
		  			if (selectedOrder.issueStatus != "3") {
		  				$.messager.alert('알림', "선택한 주문은 발행이 완료되지 않은 주문 내역입니다");
		  			}else {
		  			}
		  		}
		  	});
		  	
		  	cmenu.menu("appendItem", {
		  		id : "listGiftCards",
		  		text: '상품권 리스트 Excel 변환',
		  		/*iconCls: 'icon-ok',*/
		  		onclick: function(){
		  			if (selectedOrder.issueStatus != "3") {
		  				$.messager.alert('알림', "발행 완료된 주문 및 상품권만 엑셀로 변환할 수 있습니다.");
		  				
		  			}else {
		  				$.messager.confirm('상품권 발생 내역 다운 ' , ' 상품권 발행 리스트를 엑셀로 받으시겠습나까', function(r){
		  			        if (r){
		  			        	window.open("/api/giftCardIssue/issueExcelDownload?giftCardOrderNo=" + selectedOrder.orderNo);
		  			        }
		  			    });
		  			}
		  		}
		  	});
		  	
		  	
		  	//-----------------------------------------------
/*		  	cmenu.menu("appendItem", {
		  		id : "issueStatus",
		  		text: ' 발행 상태',
		  		iconCls: 'icon-ok',
		  		onclick: function(){alert('New Item')}
		  	});
			item = cmenu.menu('findItem', 'issueStatus');  
		 	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text: '미발행',
		  		iconCls: 'icon-excel',
		  		onclick: function(){
		  			
		  		}
		  	});
		 	
		 	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text: '발행중',
		  		iconCls: 'icon-excel',
		  		onclick: function(){
		  			
		  		}
		  	});
		 	
		 	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text: '발행 완료',
		  		iconCls: 'icon-excel',
		  		onclick: function(){
		  			
		  		}
		  	});
		 	
		 	cmenu.menu('appendItem', {
		  		parent: item.target,  // the parent item element
		  		text: '발행 취소 및 삭제',
		  		iconCls: 'icon-excel',
		  		onclick: function(){
		  			
		  		}
		  	});*/
		  	
		  	cmenu.menu('show', {
		  		left:e.pageX,
		  		top:e.pageY
		  	})
		  	
	/*	  	var menus = [  '주문 수정', '주문 삭제','상세 주문내역 보기' ];
		  	var icons = ['icon-edit','icon-remove','icon-detail'];
		  	var actions = ['modify','remove','view_order_detail'];
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text:  "<strong>[" + row.orderName + " 주문 ]</strong>"  + " " + menus[i],
		  			action: actions[i],
		  			iconCls: icons[i]
		  		});
		  	}
		  	cmenu.menu('show', {
		  		left:e.pageX,
		  		top:e.pageY
		  	});*/
		},
	    columns:columns
	    
	});
	setListPager();
}

function setListPager(){
	var pager = $('#gift_card_order_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[/*{
            iconCls:'icon-add',
            handler:function(){
            	$('#gift_card_order_list').datagrid('unselectAll');
            	$('#gift_card_order_list').datagrid('uncheckAll');
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
            	var node = $('#gift_card_order_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','자세히 보실 항목을  선택해주세요');
            		 return;
            	}
            }
        }*/],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
        onSelectPage:function(page,rows){        	
        	var opts = $('#gift_card_order_list').datagrid('options');
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
function setOrderListColumnHeader(nodeType){
	$('#order_item_list').datagrid({
		columns : orderListItemColumns
	});
}

/**
 * 노드 타입에 따라 리스트 그리드 컬럼 헤더 변경
 * @param param
 * @returns
 */
function setListColumnHeader(nodeType){
	$('#gift_card_order_list').datagrid({
		columns : columns
	});
}


/**
 * 검색 실행시 필요한 쿼리 데이타 구성 
 * @returns
 */
function makeSearchParam(){
	
	var param = $('#searchForm').serializeObject();
	var opts = $('#gift_card_order_list').datagrid('options');
	var total = $('#gift_card_order_list').datagrid('getData').total;
	
	$.extend(param, {
		pagination : opts.pagination,
		pageSize : opts.pageSize,
		page : opts.pageNumber,
		total : total,
		offset : (opts.pageNumber-1) * opts.pageSize
	});
	
	return param;
}

function statusFormatter(value,row,index){
	var status = row.memberStatus;
	return '&nbsp;<span >'+ status + '</span>';
}



function stopGiftCardOrder(param){
}

function cancelGiftCardOrder(param){
	var selectedOrder = $('#gift_card_order_list').datagrid('getSelected');
	var confirmMessage = "주문명 - " + selectedOrder.orderName + " 의 주문에 대한 상품권 발행을 취소하시겠습니까?"
	$.messager.confirm('상품권 발행 취소',confirmMessage, function(r){
        if (r){
        	var param = {
        			giftCardOrderNo : selectedOrder.orderNo
        	}
        	returnp.api.call("invalidateGiftCardIssue", param, function(res){
        		if (res.resultCode  == "100") {
        			$.messager.alert('알림', res.message);
        			realodPage();
        		}else {
        			//console.log("[오류]");
        			//console.log(res);
        			$.messager.alert('오류 발생', res.message);
        		}
        	});
        }
    });
}

function issueGiftCardOrder(param){
	var selectedOrder = $('#gift_card_order_list').datagrid('getSelected');
	var confirmMessage = "주문명 - " + selectedOrder.orderName + " 의 주문에 대한 상품권 발행을 실행하시겠습니까?"
	$.messager.confirm('상품권 발행',confirmMessage, function(r){
        if (r){
        	var param = {
        			giftCardOrderNo : selectedOrder.orderNo
        	}
        	returnp.api.call("createBatchGiftCardIssue", param, function(res){
        		if (res.resultCode  == "100") {
        			$.messager.alert('알림', res.message);
        			realodPage();
        		}else {
        			//console.log("[오류]");
        			//console.log(res);
        			$.messager.alert('오류 발생', res.message);
        		}
        	});
        }
    });
}

function updateGiftCardOrder(param){
	returnp.api.call("updateGiftCardOrder", param, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			realodPage();
		}else {
			//console.log("[오류]");
			//console.log(res);
			$.messager.alert('오류 발생', res.message);
		}
	});
}


function removeGiftCardOrder(){
	var node = $('#gift_card_order_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        			giftCardOrderNo : node.giftCardOrderNo
        	}
        	returnp.api.call("deleteGiftCardOrder", param, function(res){
        		if (res.resultCode  == "100") {
        			$.messager.alert('알림', res.message);
        			realodPage();
        		}else {
        			//console.log("[오류]");
        			//console.log(res);
        			$.messager.alert('오류 발생', res.message);
        		}
        	});
        }
    });
}

function viewOrderDetail(){
	var order = $('#gift_card_order_list').datagrid('getSelected');
	$('#more_detail_view').dialog({
		title: order.orderName,
		width: 800,
		height: "830",
		closed: false,
		cache: false,
		modal: true,
		onOpen : function(){
			var order = $('#gift_card_order_list').datagrid('getSelected');
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
		}
	});
	
}

function realodPage(){
	$('#search_btn').click();
}

$(document).ready(function(){
	$('#search_btn').click();
});
