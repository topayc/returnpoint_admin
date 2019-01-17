	columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'adminFileNo',width:30,align:'center',title : '번호',hidden:true},
			    {field:'uploadType',width:50,align:'center',title : '등록 타입'},
			    {field:'fileNodeType',width:50,align:'center',title : '파일 노드'},
			    {field:'fileName',width:100,align:'center',title : '파일 이름'},
			    {field:'orginalFileName',width:100,align:'center',title : '파일 원본 이름'},
			    {field:'fileLocalPath',width:100,align:'center',title : '로컬 경로'},
			    {field:'fileWebPath',width:100,align:'center',title : '웹 경로'},
			    {field:'createTime',width:70,align:'center',title : '등록일',formatter : dateFormatter},
			    {field:'updateTime',width:70,align:'center',title : '수정일',formatter : dateFormatter},
			    {field:'regAdminEmail',width:70,align:'center',title : '등록자',hidden:false},
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
	
	$('#file_list').datagrid({
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
		  			case "create":
		  				break;
		  			case "modify":
		                  loadPointConversionModifyForm();
		  				break;
		  			case "remove":
		  				removeFile();
		  				break;
		  			case "more_detail":
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = [   '삭제' ];
		  	var icons = ['icon-remove'];
		  	var actions = ['remove'];
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text: /*'[' + row.memberEmail + '] ' +*/ menus[i],
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
	setListPager();
}

function setListPager(){
	var pager = $('#file_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#file_list').datagrid('unselectAll');
            	$('#file_list').datagrid('uncheckAll');
                loadFileUploadCreateForm();
            }
        },{
            iconCls:'icon-reload',
            handler:function(){
            	reloadPage();
            }
        }],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
    }); 
}

/**
 * 노드 타입에 따라 리스트 그리드 컬럼 헤더 변경
 * @param param
 * @returns
 */
function setListColumnHeader(nodeType){
	$('#file_list').datagrid({
		columns : columns
	});
}



/**
 * 검색 실행시 필요한 쿼리 데이타 구성 
 * @returns
 */
function makeSearchParam(){
	var param = {
		pageSize : 10,
		page : 0,
		serachDateStart :  $('#searchDateStart').datetimebox('getValue'),
		searchDateEnd :  $('#searchDateEnd').datetimebox('getValue'),
		searchNodeType :  $('input[name=searchNodeType]').val(),
		searchNodeNo :  "0",
		searchConversionStatus :  $('input[name=searchConversionStatus]').val(),
		searchKeywordType : $('input[name=searchKeywordType]').val(),
		searchKeyword :  $('input[name=searchKeyword]').val()
	};
	//console.log(JSON.stringify(param));
	return param;
}

function loadFileUploadCreateForm(){
  	var data = {
        targetElem : "#dlgForm",
        title : "파일 업로드",
        queryOptions : {
          	action : "create",
         }
     }
  	
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/adminFileUpload/form/createFileUploadForm?" + queryParam,
		function(response, status, xhr) {
			$(data.targetElem).dialog({
				width:600,
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
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
					}
				}]
			});
		});
}



function makeFormData(){
	var param = {
	
		}
	return param;
}


function uploadFile(data){	
	var valid = true;
	for (var prop in param){
		if (param.hasOwnProperty(prop)) {
			if (param[prop] == '') {
				valid = false;
				break;
			}
		}
	}
	
	if (!valid) {
		$.messager.alert('알림', '입력 항목이 모두 입력되지 않았습니다');
		return;
	}
		
	returnp.api.call("uploadFile", param, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			$(data.targetElem).dialog('close');
			realodPage();
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function removeFile(){
	var node = $('#file_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', ' 해당 항목을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        		adminFileNo : node.adminFileNo
        	}
        	returnp.api.call("removeAdminFile", param, function(res){
        		if (res.resultCode  == "100") {
        			$.messager.alert('알림', res.message);
        			reloadPage();
        		}else {
        			$.messager.alert('오류 발생', res.message);
        		}
        	});
        }
    });
}

function reloadPage(){
	returnp.api.call("getAdminFiles", {}, function(res){
		console.log(res);
		if (res.resultCode == "100") {
			$('#file_list').datagrid({
				data : res,
				title : '[검색 결과] ' + res.total + " 개의 결과가 검색되었습니다",
			});
			setListPager();
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

$(document).ready(function(){
	reloadPage();
});
