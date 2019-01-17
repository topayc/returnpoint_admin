	columns = [[
	    		{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'categoryNo',width:50,align:'center',title : '번호',hidden:true},
			    {field:'categoryCode',width:50,align:'center',title : '카테고리 코드',hidden:true},
			    {field:'categoryName',width:100,align:'center',title : '카테고리 이름(국문)'},
			    {field:'categoryNameEn',width:100,align:'center',title : '카테고리 이름(영문)'},
			    {field:'categoryStatus',width:70,align:'center',title : '상태', formatter : categoryStatusFormatter},
			    {field:'createTime',width:70,align:'center',title : '등록일',formatter : dateFormatter},
			    {field:'updateTime',width:70,align:'center',title : '수정일',formatter : dateFormatter},
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
	
	/* 노드 데이타그리드   초기화*/
	$('#cate1_list').datagrid({
		title : '',
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
		onSelect : function(){
			reloadCategoryList2();
		},
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
		  			case "create":
		  				loadCategoryCreateForm("1");
		  				break;
		  			case "sub_create":
		  				loadCategoryCreateForm("2");
		  				break;
		  			case "modify":
		  				 loadCategoryModifyForm("1");
		  				break;
		  			case "remove":
		  				removeCategory("1");
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = [  /*'생성', */'수정', '삭제','하부 카테고리 생성'];
		  	var icons = [ /*'icon-add' , */'icon-edit' , 'icon-remove' , 'icon-add'];
		  	var actions = [/* 'create' , */'modify','remove','sub_create'];
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text:  "<strong>[" + row.categoryName + "]</strong>"  + " " + menus[i],
		  			action: actions[i],
		  			iconCls: icons[i]
		  		});
		  	}
		  	cmenu.menu('show', {
		  		left:e.pageX,
		  		top:e.pageY
		  	});
		},
	    columns:columns,
	});
	
	/* 노드 데이타그리드   초기화*/
	$('#cate2_list').datagrid({
		title : '',
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
		  			case "create":
		  				loadCategoryCreateForm("2");
		  				break;
		  			case "modify":
		  				 loadCategoryModifyForm("2");
		  				break;
		  			case "remove":
		  				removeCategory("2");
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = [  '수정', '삭제'];
		  	var icons = ['icon-edit','icon-remove'];
		  	var actions = ['modify','remove'];
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text:  "<strong>[" + row.categoryName + "]</strong>"  + " " + menus[i],
		  			action: actions[i],
		  			iconCls: icons[i]
		  		});
		  	}
		  	cmenu.menu('show', {
		  		left:e.pageX,
		  		top:e.pageY
		  	});
		},
	    columns:columns,
	    
	});
	setListPager1();
	setListPager2();
}

function setListPager1(){
	var pager = $('#cate1_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' ',
		layout : ['','',''],
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#cate1_list').datagrid('unselectAll');
            	$('#cate1_list').datagrid('uncheckAll');
                loadCategoryCreateForm("1");
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
                  loadCategoryModifyForm("1");
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removeCategory("1");
            }
        }],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
    }); 
	
}

function setListPager2(){
	
	var pager2 = $('#cate2_list').datagrid().datagrid('getPager');
	pager2.pagination({
		displayMsg : ' ',
		layout : ['','',''],
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#cate2_list').datagrid('unselectAll');
            	$('#cate2_list').datagrid('uncheckAll');
                loadCategoryCreateForm("2");
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
                  loadCategoryModifyForm("2");
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removeCategory("2");
            }
        }]
    }); 
}


function statusFormatter(value,row,index){
	var status = row.memberStatus;
	return '&nbsp;<span >'+ status + '</span>';
}

function loadCategoryCreateForm(categoryLevel){
	var selNode;
	var title;
	var data;
	if (categoryLevel == '2') {
		title =  "카테고리 2 생성 "; 
		selNode = $('#cate1_list').datagrid('getSelected');
		if (!selNode) {
			$.messager.alert('카테고리 1 선택', "대 카테고리를 먼저 선택해주세요");
			return;
		}
	    data = {
			targetElem : "#dlgForm",
		    title : title,
	        queryOptions : {
		       	action : "create",
		       	categoryLevel : categoryLevel,
		       	parentCategoryNo : selNode ? selNode.categoryNo  : 0,
		       	parentCategoryName : selNode ? selNode.categoryName  : "",
		       	parentCategoryNameEN : selNode ? selNode.categoryNameEn  : ""
	       }
	    }
	}else if (categoryLevel == '1') {
		title  =  "카테고리 1 생성 ";
	    data = {
			targetElem : "#dlgForm",
		    title : title,
		       queryOptions : {
		      	action : "create",
	         	categoryLevel : categoryLevel
		     }
		 }
	}
  
	//console.log("loadCategoryCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/category/form/createForm?" + queryParam,
		function(response, status, xhr) {
		
			$(data.targetElem).dialog({
				width:550,
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
						createCategory(data);
					} },
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
			
		});
}

function loadCategoryModifyForm(categoryLevel){
	var selectedCate = categoryLevel  == "1" ?   $('#cate1_list').datagrid('getSelected') : $('#cate2_list').datagrid('getSelected');
	if (!selectedCate) {
		 $.messager.alert('알림','수정하실 노드를 선택해주세요');
		 return;
	}
	
	var data = {
		targetElem : "#dlgForm",
        title : "[" + selectedCate.categoryName + "] " + ' 카테고리 수정',
        queryOptions : {
        	action : "modify",
        	categoryNo : selectedCate.categoryNo,
        	categoryLevel : categoryLevel
        }
     }
	
	//console.log("loadCategoryModifyForm");
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/category/form/createForm?" + queryParam,
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
						updateCategory(data);
					} },
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
			returnp.api.call('getCategory', {categoryNo : data.queryOptions.categoryNo, categorylevel : categoryLevel}, function(res){
				if (res.resultCode  == "100") {
					console.log("getCategory 응답");
					console.log(res);
					$('#createCategoryForm').form('load',res.data);
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
			
		});
}


function makeFormData(){
	var param = {
			parentCategoryNo : $('input[name=parentCategoryNo]').val(),	
			parentCategoryName: $('input[name=parentCategoryName]').val(),	
			categoryLevel : $('input[name=categoryLevel]').val(),	
			categoryName : $('input[name=categoryName]').val(),	
			categoryNameEn : $('input[name=categoryNameEn]').val(),	
			categoryStatus : $('#categoryStatus').combobox("getValue")
		}
	return param;
}

function updateCategory(data){
	var param =makeFormData();
	//console.log(param);
	returnp.api.call("updateCategory", param, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			$(data.targetElem).dialog('close');
			$(data.targetElem).removeAttr('style');
			if (data.queryOptions.categoryLevel == "1") {
				reloadCategoryList1(function(){
					$('#cate1_list').datagrid('selectRow', 0);
				});
			}
			if (data.queryOptions.categoryLevel == "2") {
				var rowIndex =  $('#cate1_list').datagrid("getRowIndex",$('#cate1_list').datagrid('getSelected'));
				$('#cate1_list').datagrid('selectRow', rowIndex);
			}
			
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function createCategory(data){	
	var param =makeFormData();
	//console.log("createCategory");
	//console.log(param);
		
	var valid = true;
	for (var prop in param){
		if (param.hasOwnProperty(prop)) {
			if (param[prop] == '') {
				valid = false;
				break;
			}
		}
	}
	
		/* 	if (!valid) {
			$.messager.alert('알림', '입력 항목이 모두 입력되지 않았습니다');
			return;
		}	 */
		
	returnp.api.call("createCategory", param, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			$(data.targetElem).dialog('close');
			$(data.targetElem).removeAttr('style');
			reloadCategoryList1(function(){
				$('#cate1_list').datagrid('selectRow', 0);
			});
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function removeCategory(categoryLevel){
	var node = categoryLevel == "1" ?  $('#cate1_list').datagrid('getSelected') :$('#cate2_list').datagrid('getSelected') 
	var aletTitle = categoryLevel == "1" ? "이 카테고리에 속한 하부 카테고리도 모두 삭제됩니다. 삭제하시겠습니까?": "카테고리를 삭제하시겠습니까?"
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ aletTitle, function(r){
        if (r){
        	var param = {
        		categoryNo : node.categoryNo,
        		categoryLevel : categoryLevel
        	}
        	returnp.api.call("deleteCategory", param, function(res){
        		if (res.resultCode  == "100") {
        			$.messager.alert('알림', res.message);
        			
        			if (categoryLevel == '1'){
        				reloadCategoryList1(function(){
        					$('#cate1_list').datagrid('selectRow', 0);
        				});
        			}
        			
        			if (categoryLevel == "2"){
        				reloadCategoryList2();
        			}
        		}else {
        			//console.log("[오류]");
        			//console.log(res);
        			$.messager.alert('오류 발생', res.message);
        		}
        	});
        }
    });
}

function realodPage(){
	$('#search_btn').click();
}

function reloadCategoryList1(func){
 	returnp.api.call("getCategories", {categoryLevel : 1}, function(res){
		if (res.resultCode  == "100") {
			$('#cate1_list').datagrid({
				data : res.rows
			});
			setListPager1();
			if (func) {
				func();
			}
		}else {
			//console.log("[오류]");
			//console.log(res);
		}
	});
}

function reloadCategoryList2(type){
	var selectedCate1  = $('#cate1_list').datagrid('getSelected');
	if (!selectedCate1) {
		 $.messager.alert('알림','선택하신 카테고리가 없습니다');
		 return;
	}
	
	returnp.api.call("getCategories", {categoryLevel : 2, parentCategoryNo :  selectedCate1.categoryNo}, function(res){
		if (res.resultCode  == "100") {
			$('#cate2_list').datagrid({
				data : res.rows
			});
			setListPager2();
		}else {
			//console.log("[오류]");
			//console.log(res);
		}
	});
}

$(document).ready(function(){
	reloadCategoryList1(function(){
		$('#cate1_list').datagrid('selectRow', 0);
	});
});
