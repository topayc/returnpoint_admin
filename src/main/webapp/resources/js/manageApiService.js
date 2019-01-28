		columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'apiServiceNo',width:50,align:'center',title : '번호',hidden:false},
			    {field:'company',width:100,align:'center',title : '회사명'},
			    {field:'project',width:110,align:'center',title : '프로젝트 명'},
			    {field:'rfId',width:100,align:'center',title : 'RF ID'},
			    {field:'apiService',width:90,align:'center',title : 'API 서비스 분류', formatter : serviceNameFormatter},
			    {field:'apiServiceName',width:130,align:'center',title : 'API 서비스 이름', hidden: false},
			    {field:'domain',width:100,align:'center',title : '도메인'},
			    {field:'ip',width:80,align:'center',title : 'IP'},
			    {field:'apiServiceStatus',width:50,align:'center',title : '상태', formatter : serviceStatusFormatter},
			    {field:'apiKey',width:200,align:'center',title : 'API Key'},
			    {field:'expireTime',width:100,align:'center',title : '만료일',formatter : dateFormatter},
			    {field:'createTime',width:100,align:'center',title : '등록일',formatter : dateFormatter, hidden: false},
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
	$('#searchKeyword').textbox({ prompt : "검색할 단어를 입력해주세요" });
	
	/* 서비스 타입 셀렉트 박스  초기화*/
	$('#searchApiServiceType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	/* 서비스 상태 셀렉트 박스  초기화*/
	$('#searchApiServiceStatus').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});

	/* 검색 시작일 갤린더 박스  초기화*/
	$('#searchDateStart').datetimebox({
	    showSeconds: true,
	    prompt : "검색 시작 일자",
	    labelPosition: 'top',
	    formatter : function(date){
	    	var year = date.getFullYear();  
	    	var month = date.getMonth()+1;  
	    	var d = date.getDate();  
	    	var hours = date.getHours();  
	    	var minutes  = date.getMinutes();  
	    	var seconds = date.getSeconds();  
	    	return  year + "-" + formatDate(month) + "-" + formatDate(d) + " "+ formatDate(hours) + ":" + formatDate(minutes) + ":" + formatDate(seconds);
	    }
	});
	
	/* 검색 종료일 갤린더 박스  초기화*/
	$('#searchDateEnd').datetimebox({
	    showSeconds: true,
	    prompt : "검색 종료 일자",
	    labelPosition: 'top',
	    formatter : function(date){
	    	var year = date.getFullYear();  
	    	var month = date.getMonth()+1;  
	    	var d = date.getDate();  
	    	var hours = date.getHours();  
	    	var minutes  = date.getMinutes();  
	    	var seconds = date.getSeconds();  
	    	return  year + "-" + formatDate(month) + "-" + formatDate(d) + " "+ formatDate(hours) + ":" + formatDate(minutes) + ":" + formatDate(seconds);
	    }
	});
	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
		onClick : function(){
			//$.messager.progress();
			var param = makeSearchParam();
			//console.log("검색 쿼리 데이타");
			//console.log(param);
			
			returnp.api.call("getApiServices", param, function(res){
				//console.log("apiService datas");
				//console.log(res);
				if (res.resultCode == "100") {
					setListColumnHeader(param.searchApiServiceType);
					$('#api_service_list').datagrid({
						data : res.rows,
						title : '[검색 결과] ' + res.rows.length + " 개의 결과가 검색되었습니다",
					});
					setListPager();
				}else {
					$.messager.alert('오류 발생', message);
				}
			});
		},
		iconCls:'icon-search'
	});
	
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
			/*$('#searchForm').form('clear');*/
			$('#searchApiServiceStatus').combobox('select', 0);
			$('#searchKeywordType').combobox('select', 0);
			$('#searchKeyword').textbox('clear');
			$('#searchDateStart').datetimebox('clear');
			$('#searchDateEnd').datetimebox('clear');
		}
	});
	/* 서비스 데이타그리드   초기화*/
	$('#api_service_list').datagrid({
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
		  				loadApiServiceModifyForm()
		  				break;
		  			case "remove":
		  				removeApiService();
		  				break;
		  			}
		  		},
		  		itemHeight : 27
		  	});
		  	
		  	var menus = [  '수정', '삭제'];
		  	var icons = ['icon-edit','icon-remove'];
		  	var actions = ['modify','remove'];
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text:  "<strong>[" + row.project + "]</strong>"  + "&nbsp;&nbsp;" + menus[i],
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
	var pager = $('#api_service_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#api_service_list').datagrid('unselectAll');
            	$('#api_service_list').datagrid('uncheckAll');
            	loadApiServiceCreateForm();
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
              loadApiServiceModifyForm();
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removeApiService();
            }
        },{
            iconCls:'icon-more',
            handler:function(){
            	var apiService = $('#api_service_list').datagrid('getSelected');
            	if (!apiService) {
            		 $.messager.alert('알림','자세히 보실 항목을  선택해주세요');
            		 return;
            	}
            }, 
            layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
        }]
    }); 
}

/**
 * 서비스 타입에 따라 리스트 그리드 컬럼 헤더 변경
 * @param param
 * @returns
 */
function setListColumnHeader(apiServiceType){
	$('#api_service_list').datagrid({
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
	var opts = $('#api_service_list').datagrid('options');
	var total = $('#api_service_list').datagrid('getData').total;
	
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

function loadApiServiceCreateForm(){
	
	var data = {
    	targetElem : "#dlgForm",
        title : "API SERVICE 연동 프로젝트 생성",
        queryOptions : {
        	action : "create",
       }
    }
	
	//console.log("loadApiServiceCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/apiService/form/createForm?" + queryParam,
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
						var apiServiceType = $('input[name=searchApiServiceType]').val();
						createApiService(data);
					} },
					{ text:'취소', handler:function(){
						//console.log("닫을 DIV : " + data.targetElem);	
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
		});
}

function loadApiServiceModifyForm(){
   	var apiService = $('#api_service_list').datagrid('getSelected');
	if (!apiService) {
		 $.messager.alert('알림','수정하실 서비스를 선택해주세요');
		 return;
	}
	
	var data = {
        targetElem : "#dlgForm",
        title : "[" +apiService.apiServiceName + "] " + " API SERVICE 연동 프로젝트 수정",
        queryOptions : {
        	action : "modify",
        	apiServiceNo : apiService.apiServiceNo
       	}
     }
	
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/apiService/form/createForm?" + queryParam,
		function(response, status, xhr) {
			//console.log("오픈할 DIV : " + response);	
		
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
						updateApiService(data);
					} },
					{ text:'취소', handler:function(){
						//console.log("닫을 DIV : " + data.targetElem);	
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
			
			returnp.api.call('getApiService', {apiServiceNo : data.queryOptions.apiServiceNo}, function(res){
				if (res.resultCode  == "100") {
					console.log(res);
					$('#createApiServiceForm').form('load',res.data);					
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
			
		});
}


function makeFormData(){
	$("#apiServiceName").val($("#company").val()  + "_" +  $('#apiService').combobox("getText"))
	var param = $("#createApiServiceForm").serializeArray();
	return param;
}

function updateApiService(data){
	var param =makeFormData();
	//console.log(param);
	returnp.api.call("updateApiService", param, function(res){
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

function createApiService(data){	
	var param =makeFormData();
	for (var prop in param){
		if (param.hasOwnProperty(prop)) {
			if (param[prop] == '') {
				valid = false;
				break;
			}
		}
	}
	returnp.api.call("createApiService", param, function(res){
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

function removeApiService(){
	var apiService = $('#api_service_list').datagrid('getSelected');
	if (!apiService) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        			apiServiceNo : apiService.apiServiceNo
        	}
        	returnp.api.call("deleteApiService", param, function(res){
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

function realodPage(){
	$('#search_btn').click();
}

$(document).ready(function(){
	$('#search_btn').click();
});
