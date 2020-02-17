		columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			{field:'greenPointNo',width:45,align:'center',title : '포인트 번호',hidden:false},	
			{field:'memberNo',width:45,align:'center',title : '회원 번호',hidden:false},
			{field:'memberName',width:80,align:'center',title : '이름'},
			{field:'nodeType',width:50,align:'center',title : '분류',formatter : nodeTypeFormatter},
			{field:'nodeNo',width:50,align:'center',title : '노드 번호',hidden:true},
			{field:'memberEmail',width:100,align:'center',title : '이메일'},
			{field:'nodeTypeName',width:100,align:'center',title : '노드 이름',formatter : nodeTypeFormatter, hidden : true},
			{field:'pointAmount',width:100,align:'center',title : 'G POINT', formatter : numberGreenFormatter},
		    {field:'memberPhone',width:80,align:'center',title : '핸드폰'},
			{field:'greenPointCreateTime',width:100,align:'center',title : '등록일',formatter : dateFormatter},
			{field:'greenPointUpdateTime',width:100,align:'center',title : '수정일',formatter : dateFormatter},
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
		prompt : "검색할 단어를 입력해주세요" ,
		inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
			keyup:function(e){
				if(e.keyCode==13)
					realodPage();
			}
		})
	});
	
	/* 노드 타입 셀렉트 박스  초기화*/
	$('#searchNodeType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	/* 노드 상태 셀렉트 박스  초기화*/
	$('#searchNodeStatus').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	/* 노드 상태 셀렉트 박스  초기화*/
	$('#searchOrderType').combobox({
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
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	$('#searchPointMin').textbox({
		width: 140,
		prompt : "최저 포인트" ,
		labelPosition: 'top'
	});

	$('#searchPointMax').textbox({
		width: 140,
		prompt : "최대 포인트 " ,
		labelPosition: 'top'
	});
	
	/* 검색어 타입 셀렉트 박스  초기화*/
	$('#searchPointType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});

	$('#searchNodeStatus').combobox({
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
			
			returnp.api.call("getGreenPoints", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					$('#node_list').datagrid({
						data : res,
						title : '[검색 결과] ' + res.total + " 개의 결과가 검색되었습니다",
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
			$('#searchForm').form('clear');
			$('#searchNodeType').combobox('select', 0);
			$('#searchNodeStatus').combobox('select', 0);
			$('#searchKeywordType').combobox('select', 0);
			$('#searchKeyword').textbox('clear');
			$('#searchDateStart').datetimebox('clear');
			$('#searchDateEnd').datetimebox('clear');
		}
	});
	
	/* 노드 데이타그리드   초기화*/
	$('#node_list').datagrid({
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
		  				loadGreenPointCreateForm()
		  				break;
		  			case "modify":
		  				loadGreenPointModifyForm()
		  				break;
		  			case "remove":
		  				 $.messager.confirm('삭제', item.data.memberEmail + ' 님을 정말로 삭제하시겠습니까?', function(r){
		                     if (r){
		                         alert("삭제합니다");
		                     }
		                 });
		  				break;
		  			case "more_detail":
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = [  '수정', '삭제','상세' ];
		  	var icons = ['icon-edit','icon-remove','icon-more'];
		  	var actions = ['modify','remove','more_detail'];
		  	
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
	    columns:columns
	    
	});
	setListPager();
}

function setListPager(){
	var pager = $('#node_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#node_list').datagrid('unselectAll');
            	$('#node_list').datagrid('uncheckAll');
            	loadGreenPointCreateForm();
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
            	loadGreenPointModifyForm();
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	var node = $('#node_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','삭제하실 노드를 선택해주세요');
            		 return;
            	}
            	var nodeType = $('input[name=nodeType]').val();
            	$.messager.confirm('삭제', '[' + nodeTypeArrs[nodeType] + '] -' + node.memberEmail + ' 님을 정말로 삭제하시겠습니까?', function(r){
                     if (r){
                         alert("삭제합니다");
                     }
                 });
            }
        },{
            iconCls:'icon-more',
            handler:function(){
            	var node = $('#node_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','자세히 보실 항목을  선택해주세요');
            		 return;
            	}
            }
        }]
	,  layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
    onSelectPage:function(page,rows){        	
    	var opts = $('#node_list').datagrid('options');
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
	$('#node_list').datagrid({
		columns : columns
	});
}


/**
 * 검색 실행시 필요한 쿼리 데이타 구성 
 * @returns
 */
function makeSearchParam(){
	var param = $('#searchForm').serializeObject();
	var opts = $('#node_list').datagrid('options');
	var total = $('#node_list').datagrid('getData').total;
	
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

function loadGreenPointCreateForm(){
	
	var data = {
    	targetElem : "#dlgForm",
        title : "G 포인트생성",
        queryOptions : {
        	action : "create",
       }
    }
	
	//console.log("loadGreenPointCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/greenPoint/form/createForm?" + queryParam,
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
					{ text:'확인', iconCls:'icon-ok', handler:function(){
						var nodeType = $('input[name=searchKeywordNodeType]').val();
						createGreenPoint(data);
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

function loadGreenPointModifyForm(){
   	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 노드를 선택해주세요');
		 return;
	}
	
	//console.log(node);
	var data = {
        targetElem : "#dlgForm",
        title : '[ G 포인트 수정',
        queryOptions : {
        	action : "modify",
        	greenPointNo : node.greenPointNo
       	}
     }
	//console.log("loadGreenPointModifyForm");
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/greenPoint/form/createForm?" + queryParam,
		function(response, status, xhr) {
		//console.log("오픈할 DIV : " + data.targetElem);	
		
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
					{ text:'확인', iconCls:'icon-ok', handler:function(){
						updateGreenPoint(data);
					} },
					{ text:'취소', handler:function(){
						console.log("닫을 DIV : " + data.targetElem);	
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
			returnp.api.call('getGreenPointCommand', {greenPointNo : data.queryOptions.greenPointNo}, function(res){
				if (res.resultCode  == "100") {
					console.log(res);
					$('#createGreenPointForm').form('load',res.data);
					$('#greenPointNo').val(res.data.greenPointNo);
					$('#memberName').textbox({ value : res.data.memberName});
					$('#memberEmail').textbox({ value : res.data.memberEmail});
					$('#memberPhone').textbox({ value : res.data.memberPhone});
					$('#pointAmount').textbox({ value : res.data.pointAmount});
					$('#nodeType').textbox({ value : nodeTypeFormatter(null, res.data, null)});
					$('#regType').combobox('select',res.data.regType);
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
			
		});
}


function makeFormData(){
	var param = {
			greenPointNo : $('input[name=greenPointNo]').val(),	
			pointAmount : $('input[name=pointAmount]').val(),	
			regType : $('#regType').combobox('getValue')
		}
	return param;
}

function updateGreenPoint(data){
	var param =makeFormData();
	//console.log(param);
	returnp.api.call("updateGreenPoint", param, function(res){
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

function createGreenPoint(data){	
	var param =makeFormData();
	//console.log("createGreenPoint");
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
		
	returnp.api.call("createGreenPoint", param, function(res){
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

function realodPage(){
	$('#search_btn').click();
}


$(document).ready(function(){
	$('#search_btn').click();
});
