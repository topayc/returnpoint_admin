		var columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'giftCardIssueNo',width:70,align:'center',title : '등록번호'},
			    {field:'giftCardOrderNo',width:70,align:'center',title : '주문 등록 번호', hidden : true},
			    {field:'orderNumber',width:100,align:'center',title : '주문 번호', hidden : false},
			    {field:'orderName',width:100,align:'center',title : '주문명', hidden : false},
			    {field:'ordererName',width:100,align:'center',title : '주문자', hidden : true},
			    {field:'ordererPhone',width:100,align:'center',title : '주문자 핸드폰', hidden : true},
			    {field:'giftCardNo',width:100,align:'center',title : '상품권 번호', hidden : true},
			    {field:'giftCardName',width:120,align:'center',title : '상품권 이름'},
			    {field:'pinNumber',width:200,align:'center',title : '핀 번호'/*,formatter : addBoldFomatter*/},
			    {field:'giftCardStatus',width:80,align:'center',title : '상태', formatter : giftCardStatusFormatter},
			    {field:'giftCardType',width:80,align:'center',title : '타입', formatter : giftCardTypeFormatter2},
			    {field:'accableStatus',width:80,align:'center',title : '적립 가능', formatter : accableStatusFormatter},
			    {field:'payableStatus',width:80,align:'center',title : '결제 가능', formatter : payableStatusFormatter},
			    {field:'giftCardAmount',width:100,align:'center',title : '상품권 금액',formatter : numberFormatter},
			    {field:'giftCardSalePrice',width:100,align:'center',title : '판매 금액', formatter : numberGreenFormatter},
			    {field:'accQrData',width:130,align:'center',title : '적립 QR 데이타',hidden : true},
			    {field:'payQrData',width:130,align:'center',title : '결제 QR 데이타',hidden : true},
			    {field:'accQrCodeWebPath',width:100,align:'center',title : '적립 QR Code', hidden : true},
			    {field:'payQrCodeWebPath',width:100,align:'center',title : '결제 QR Code', hidden : true},
			    {field:'accQrScanner',width:130,align:'center',title : '적립 QR 스캐너'},
			    {field:'payQrScanner',width:130,align:'center',title : '결제 QR 스캐너'},
			    {field:'accQrScanTime',width:140,align:'center',title : '적립 QR 스캔일', formatter : dateFormatter},
			    {field:'payQrScanTime',width:140,align:'center',title : '결제 QR 스캔일', formatter : dateFormatter},
			    {field:'issueTime',width:180,align:'center',title : '발행일', formatter : dateFormatter},
			    {field:'expirationTime',width:180,align:'center',title : '만료일', formatter : dateFormatter},
			    {field:'createTime',width:10,align:'center',title : '등록일', formatter : dateFormatter, hidden : true},
			    {field:'updateTime',width:100,align:'center',title : '수정일', formatter : dateFormatter, hidden : true},
			    ]];
		var issueDetailColumns= [[
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
					reloadPage();
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
			reloadPage();
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
	$('#gift_card_issue_list').datagrid({
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
		  				updateGiftCardIssue();
		  				break;
		  			case "remove":
		  				removeGiftCardIssue();
		  				break;
		  			case "view_order_detail":
		  				viewOrderDetail();
		  				break;
		  			}
		  		},
		  		itemHeight : 27
		  	});
		  	
		  	var menuArr = [];
			var selectedIssue = $('#gift_card_issue_list').datagrid('getSelected');
		  	if (selectedIssue.giftCardType == 1) {
		  		cmenu.menu("appendItem", {
		  			id : "m_1",
		  			text: '모바일전송',
		  			/*	iconCls: 'icon-ok',*/
		  			onclick: function(){}
		  		});
		  		
		  		item = cmenu.menu('findItem', '모바일전송');  
		  		cmenu.menu('appendItem', {
		  			parent: item.target,  // the parent item element
		  			text:  "카톡으로 보내기",
		  			//iconCls: 'icon-ok',
		  			onclick: function(){
		  				sendGiftCardByKakao();
		  			}
		  		});
		  		
		  		item = cmenu.menu('findItem', '모바일전송');  
		  		cmenu.menu('appendItem', {
		  			parent: item.target,  // the parent item element
		  			text:  "모바일로 보내기",
		  			//iconCls: 'icon-ok',
		  			onclick: function(){
		  				sendGiftCardByMobile()
		  			}
		  		});
		  		
		  	}
		 	cmenu.menu('appendItem', {
		  		separator: true
		  	});
			cmenu.menu('appendItem', {
	  			id : "m_3",  // the parent item element
	  			text:  "상품권QR코드 보기",
	  			//iconCls: 'icon-ok',
	  			onclick: function(){
	  			}
	  		});
			
			item = cmenu.menu('findItem', '상품권QR코드 보기');  
	  		cmenu.menu('appendItem', {
	  			parent: item.target,  // the parent item element
	  			text:  "적립 QR Code 보기",
	  			//iconCls: 'icon-ok',
	  			onclick: function(){
	  				if (selectedIssue.accQrCodeWebPath == null || selectedIssue.accQrCodeWebPath == ""){
	  					createQrCode(selectedIssue.giftCardIssueNo, "A");
	  				}else {
	  					viewQrCode(selectedIssue.accQrCodeWebPath,"적립 큐알");
	  				}
	  			}
	  		});
	  		
	  		item = cmenu.menu('findItem', '상품권QR코드 보기');  
	  		cmenu.menu('appendItem', {
	  			parent: item.target,  // the parent item element
	  			//iconCls: 'icon-ok',
	  			text:  "결제 QR Code 보기",
	  			onclick: function(){
	  				if (selectedIssue.payQrCodeWebPath == null || selectedIssue.payQrCodeWebPath == ""){
	  					createQrCode(selectedIssue.giftCardIssueNo, "P");
	  				}else {
	  					viewQrCode(selectedIssue.payQrCodeWebPath,"결제 큐알");
	  				}
	  			}
	  		});
	  	 	cmenu.menu('appendItem', {
		  		separator: true
		  	});
	  	  	cmenu.menu("appendItem", {
		  		id : "m_2",
		  		text: '발행 내역 상세 보기',
		  		/*	iconCls: 'icon-ok',*/
		  		onclick: function(){
		  			viewDetailIssue();
		  		}
		  	});
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
	var pager = $('#gift_card_issue_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[/*{
            iconCls:'icon-add',
            handler:function(){
            	$('#gift_card_issue_list').datagrid('unselectAll');
            	$('#gift_card_issue_list').datagrid('uncheckAll');
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
            	var node = $('#gift_card_issue_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','자세히 보실 항목을  선택해주세요');
            		 return;
            	}
            }
        }*/],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
        onSelectPage:function(page,rows){        	
        	var opts = $('#gift_card_issue_list').datagrid('options');
        	opts.pageSize=rows;
        	opts.pageNumber = page;
        	reloadPage();
    	}
    }); 
}

function viewDetailIssue(){
	var order = $('#gift_card_issue_list').datagrid('getSelected');
	$('#more_detail_view').dialog({
		title: order.orderName,
		width: 800,
		height: "830",
		closed: false,
		cache: false,
		modal: true,
		onOpen : function(){
			var issue = $('#gift_card_issue_list').datagrid('getSelected');
			var propertyIssue= { total : Object.keys(order).length , rows:[] };
			var exitArr = ['pagination', 'pageSize', 'page', 'total', 'order','offset', 'productNo']
			//var columns = [[]];
			for (var property in issue) {
				if (issue.hasOwnProperty(property)){
					if (!exitArr.hasValue(property) ) {
						propertyIssue['rows'].push({name : property, nameKor : issueColumnKorFormatter(property), value : issueGridValueForamtter(property, issue[property]), group : "발행 상품권 정보", editor : null})
						//columns[0].push({field: property, width : 100, align : "center", title : property});
					}
				}
			}
			$('#issue_overview').propertygrid({
				data : propertyIssue,
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
/**
 * 노드 타입에 따라 리스트 그리드 컬럼 헤더 변경
 * @param param
 * @returns
 */
function setListColumnHeader(nodeType){
	$('#gift_card_issue_list').datagrid({
		columns : columns
	});
}


/**
 * 검색 실행시 필요한 쿼리 데이타 구성 
 * @returns
 */
function makeSearchParam(){
	
	var param = $('#searchForm').serializeObject();
	var opts = $('#gift_card_issue_list').datagrid('options');
	var total = $('#gift_card_issue_list').datagrid('getData').total;
	
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
	var param = $("#createGiftCardIssueForm").serializeObject();
	return param;
}

function createQrCode(giftCardIssueNo, type){
	returnp.api.call("createQr", {giftCardIssueNo : giftCardIssueNo, type : type}, function(res){
		if (res.resultCode  == "100") {
			console.log(res);
			var node = $('#gift_card_issue_list').datagrid('getSelected');
			var index = $('#gift_card_issue_list').datagrid('getRowIndex',node);
			
			$('#gift_card_issue_list').datagrid("updateRow", {
				index: index,
				row: res.data
			});
			var path = type == "A" ?  res.data.accQrCodeWebPath : res.data.payQrCodeWebPath 
			viewQrCode(path , type == "A" ? "적립 큐알": "결제 큐알");
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}
function viewQrCode(path, title){
	var w = window.open(path, "QR Code", "width=400, height=400, left=100, top=100"); 
	w.document.title = title;
}

function updateGiftCardIssue(data){
	var param =makeFormData();
	returnp.api.call("updateGiftCardIssue", param, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			reloadPage();
		}else {
			//console.log("[오류]");
			//console.log(res);
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function removeGiftCardIssue(){
	var node = $('#gift_card_issue_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        			giftCardIssueNo : node.giftCardIssueNo
        	}
        	returnp.api.call("deleteGiftCardIssue", param, function(res){
        		if (res.resultCode  == "100") {
        			$.messager.alert('알림', res.message);
        			reloadPage();
        		}else {
        			//console.log("[오류]");
        			//console.log(res);
        			$.messager.alert('오류 발생', res.message);
        		}
        	});
        }
    });
}

function reloadPage(){
	var param = makeSearchParam();
	returnp.api.call("selectGiftCardIssue", param, function(res){
		console.log(res);
		if (res.resultCode == "100") {
			setListColumnHeader(param.searchNodeType);
			$('#gift_card_issue_list').datagrid({
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
