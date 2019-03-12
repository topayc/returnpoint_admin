		columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'productNo',width:50,align:'center',title : '등록 번호',hidden:false},
			    {field:'productCategory',width:60,align:'center',title : '카테고리'},
			    {field:'productName',width:100,align:'center',title : '상품 이름'},
			    {field:'productPrice',width:60,align:'center',title : '상품 가격', formatter : numberGreenFormatter},
			    {field:'productSalePrice',width:60,align:'center',title : '상품 판매 가격', formatter : numberRedFormatter},
			    {field:'productDes',width:140,align:'center',title : '상품 상세 설명'},
			    {field:'productStatus',width:60,align:'center',title : '상품 상태', formatter  : productStatusFormatter},
			    {field:'productImgPath1',width:100,align:'center',title : '상품 이미지1', formatter  : imageTagFormatter},
			    {field:'productImgPath2',width:100,align:'center',title : '상품 이미지2' , formatter  : imageTagFormatter},
			    {field:'createTime',width:100,align:'center',title : '등록일', formatter : dateFormatter},
			    {field:'updateTime',width:100,align:'center',title : '수정일', formatter : dateFormatter},
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
	$('.easyui-panel').panel({ border: false });
	
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
			//console.log("검색 쿼리 데이타");
			//console.log(param);
			
			returnp.api.call("selectProducts", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					setListColumnHeader(param.searchNodeType);
					$('#product_list').datagrid({
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
	$('#product_list').datagrid({
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
		  				loadProductModifyForm()
		  				break;
		  			case "remove":
		  				removeProduct();
		  				break;
		  			case "view_product_image1":
		  				viewProductImage1();
		  				break;
		  			case "view_product_image2":
		  				viewProductImage2();
		  				break;
		  			}
		  		},
		  		itemHeight : 27
		  	});
		  	
		  	var menus = [  '수정', '삭제','상품 이미지1 보기','상품 이미지2 보기' ];
		  	var icons = ['icon-edit','icon-remove','icon-detail','icon-detail'];
		  	var actions = ['modify','remove','view_product_image1','view_product_image2'];
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text:  "<strong>[" + row.productName + " 상품 ]</strong>"  + " " + menus[i],
		  			action: actions[i],
		  			iconCls: icons[i]
		  		});
		  	}
		  	cmenu.menu('show', {
		  		left:e.pageX,
		  		top:e.pageY
		  	});
		},
	    columns:columns
	    
	});
	setListPager();
}

function setListPager(){
	var pager = $('#product_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#product_list').datagrid('unselectAll');
            	$('#product_list').datagrid('uncheckAll');
            	loadProductCreateForm();
            }
        }/*,{
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
            	var node = $('#product_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','자세히 보실 항목을  선택해주세요');
            		 return;
            	}
            }
        }*/],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
        onSelectPage:function(page,rows){        	
        	var opts = $('#product_list').datagrid('options');
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
	$('#product_list').datagrid({
		columns : columns
	});
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

/**
 * 검색 실행시 필요한 쿼리 데이타 구성 
 * @returns
 */
function makeSearchParam(){
	
	var param = $('#searchForm').serializeObject();
	var opts = $('#product_list').datagrid('options');
	var total = $('#product_list').datagrid('getData').total;
	
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

function loadProductCreateForm(){
	var data = {
    	targetElem : "#dlgForm",
        title : " 상품 생성",
        queryOptions : {
        	action : "create",
       }
    }
	
	//console.log("loadAgencyCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/product/form/createForm?" + queryParam,
		function(response, status, xhr) {	
			$(data.targetElem).dialog({
				width:600,
				cache : false,
			    modal : true,
			    closable : true,
			    border : 'thick',
			    shadow : true,
			    collapsible : false,
			    minimizable : false,
			    maximizable: false,
			    title : "&nbsp; " + data.title,
			    shadow : false,	
				buttons:[
					{ text:'확인', iconCls:'icon-ok', handler:function(){
						var nodeType = $('input[name=searchNodeType]').val();
						createProduct(data);
					} },
					{ text:'취소', handler:function(){
						console.log("닫을 DIV : " + data.targetElem);	
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
		});
}

function loadProductModifyForm(){
   	var node = $('#product_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 노드를 선택해주세요');
		 return;
	}
	
	var data = {
        targetElem : "#dlgForm",
        title : "[" +node.productName + "] " + " 수정",
        queryOptions : {
        	action : "modify",
        	productNo : node.productNo
       	}
     }
	//console.log("loadAgencyrModifyForm");
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/product/form/createForm?" + queryParam,
		function(response, status, xhr) {
		//console.log("오픈할 DIV : " + data.targetElem);	
		
			$(data.targetElem).dialog({
				width:600,
				cache : false,
			    modal : true,
			    closable : true,
			    border : 'thick',
			    shadow : true,
			    collapsible : false,
			    minimizable : false,
			    maximizable: false,
			    title : "&nbsp; " + data.title,
			    shadow : false,	
				buttons:[
					{ text:'확인', iconCls:'icon-ok', handler:function(){
						updateProduct(data);
					} },
					{ text:'취소', handler:function(){
						console.log("닫을 DIV : " + data.targetElem);	
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
			returnp.api.call('selectProducts', {productNo : data.queryOptions.productNo}, function(res){
				console.log(res)
				if (res.resultCode  == "100") {
					if (res.rows.length == 1) {
						$('#createProductForm').form('load',res.rows[0]);
					}
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
			
		});
}


function makeFormData(){
	var param = $("#createProductForm").serializeObject();
	return param;
}

function updateProduct(data){
	$('#createProductForm').form('submit', {
		url: "/api/product/update",
		type: 'POST',
		ajax:true,
		iframe: false,
		onSubmit: function(){
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
			if (!valid) {
				$.messager.alert('알림', fieldName + ' 항목이 입력되지 않았습니다');
				return false;
			}else {
			/*	$("#productImg1").filebox("files")
				if ($("#productImg1").filebox("files").length == 0) {
					$.messager.alert("알림", "상품 이미지 파일 1을  선택해주세요");
					return false;
				}
				
				$("#productImg2").filebox("files")
				if ($("#productImg2").filebox("files").length == 0) {
					$.messager.alert("알림", "상품 이미지 파일 2를  선택해주세요");
					return false;
				}*/
				
				$('#progress_loading').show();
				return true
			}	 
		
		},
		success: function(res){
			res = JSON.parse(res);
			console.log(res);
			if (res.resultCode  == "100") {
				$.messager.alert('알림', res.message);
				$(data.targetElem).dialog('close');
				$(data.targetElem).removeAttr('style');
				realodPage();
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		}
	});
}

function createProduct(data){	
	$('#createProductForm').form('submit', {
		url: "/api/product/create",
		type: 'POST',
		ajax:true,
		iframe: false,
		onSubmit: function(){
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
			if (!valid) {
				$.messager.alert('알림', fieldName + ' 항목이 입력되지 않았습니다');
				return false;
			}else {
			/*	$("#productImg1").filebox("files")
				if ($("#productImg1").filebox("files").length == 0) {
					$.messager.alert("알림", "상품 이미지 파일 1을  선택해주세요");
					return false;
				}
				
				$("#productImg2").filebox("files")
				if ($("#productImg2").filebox("files").length == 0) {
					$.messager.alert("알림", "상품 이미지 파일 2를  선택해주세요");
					return false;
				}*/
				
				$('#progress_loading').show();
				return true
			}	 
		
		},
		success: function(res){
			res = JSON.parse(res);
			console.log(res);
			if (res.resultCode  == "100") {
				$.messager.alert('알림', res.message);
				$(data.targetElem).dialog('close');
				$(data.targetElem).removeAttr('style');
				realodPage();
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		}
	});
}

function removeProduct(){
	var node = $('#product_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        			productNo : node.productNo
        	}
        	returnp.api.call("deleteProduct", param, function(res){
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

function viewProductImage1(){
	var node = $('#product_list').datagrid('getSelected');
	var img1 = node.productImgPath1;
	window.open(img1, node.productName, "width=700, height=500, left=100, top=50"); 
}

function viewProductImage2(){
	var node = $('#product_list').datagrid('getSelected');
	var img2 = node.productImgPath2;
	window.open(img2, node.productName, "width=700, height=500, left=100, top=50"); 
}

function realodPage(){
	$('#search_btn').click();
}

$(document).ready(function(){
	$('#search_btn').click();
});
