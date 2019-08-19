	columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'recommenderNo',width:20,align:'center',title : '번호',hidden:false},
			    {field:'recommenderCode',width:40,align:'center',title : '코드'},
			    {field:'recommenderName',width:30,align:'center',title : '정회원 이름'},
			    {field:'recommenderEmail',width:50,align:'center',title : '정회원 이메일'},
			    {field:'recommenderStatus',width:30,align:'center',title : '정회원 상태', formatter : recommenderStausFormatter},
			    {field:'greenPointAmount',width:30,align:'center',title : 'G POINT', formatter : numberGreenFormatter},
			    {field:'redPointAmount',width:30,align:'center',title : 'R POINT', formatter : numberRedFormatter},
			    {field:'greenPointAccStatus',width:20,align:'center',title : 'G 적립', formatter : ynFormatter},
			    {field:'redPointAccStatus',width:20,align:'center',title : 'R 적립', formatter : ynFormatter},
			    {field:'greenPointUseStatus',width:20,align:'center',title : 'G 사용', formatter : ynFormatter},
			    {field:'redPointUseStatus',width:20,align:'center',title : 'R 사용', formatter : ynFormatter},
			    {field:'recommenderAddress',width:40,align:'center',title : '정회원 주소'},
			    {field:'recommenderTel',width:40,align:'center',title : '정회원 전화'},
			    {field:'recommenderPhone',width:30,align:'center',title : '정회원 핸드폰'},
			    {field:'createTime',width:30,align:'center',title : '등록일',formatter : dateFormatter},
			    {field:'updateTime',width:30,align:'center',title : '수정일',hidden:true,formatter : dateFormatter},
			    {field:'memberNo',width:15,align:'center',title : 'memberNo',hidden:true},
			    {field:'saleManagerNo',width:15,align:'center',title : 'recommenderNo',hidden:true}
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
		readonly:true,
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

	/* 검색 시작일 갤린더 박스  초기화*/
	$('#searchDateStart').datebox({	   
	    prompt : "검색 시작 일자",
	    labelPosition: 'top'
	});
	
	/* 검색 종료일 갤린더 박스  초기화*/
	$('#searchDateEnd').datebox({	  
	    prompt : "검색 종료 일자",
	    labelPosition: 'top',
	});
	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
		onClick : function(){
			//$.messager.progress();
			var param = makeSearchParam();
			//console.log("검색 쿼리 데이타");
			//console.log(param);
			
			returnp.api.call("getNodes", param, function(res){
				//console.log("node datas");
				//console.log(res);
				if (res.resultCode == "100") {
					setListColumnHeader(param.searchNodeType);
					$('#node_list').datagrid({
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
		  			case "create": loadRecommenderCreateForm(); break;
		  			case "modify": loadRecommenderModifyForm(); break;
		  			case "remove": removeRecommender(); break;
		  			case "more_detail": 
		  				break; 
		  			case "point_acc_view":
		  				var node = $('#node_list').datagrid('getSelected');
		  				if (!node) {
		  					 $.messager.alert('알림','세부 내역을 확인하실 항목을 선택해주세요');
		  					 return;
		  				}
		  				loadPaymentPointbackRecord(
		  					"Green Point 누적 지급 현황", 
		  					{
		  						memberNo : node.memberNo,
		  						nodeType : 2
		  					}
		  				);
		  			}
		  		}
		  	});
		  	
		  	var menus = [ '수정', '삭제','상세 정보','포인트 누적 현황' ];
		  	var icons = [ 'icon-edit' ,'icon-remove','icon-more', 'icon-large-chart'];
		  	var actions = [ 'modify', 'remove','more_detail','point_acc_view'];
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text:  "<strong>[" + row.recommenderName + "]</strong>"  + " " + menus[i],
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
		buttons:[/*{
            iconCls:'icon-add',
            handler:function(){
            	$('#node_list').datagrid('unselectAll');
            	$('#node_list').datagrid('uncheckAll');
            	loadRecommenderCreateForm();
            	
            }
        },*/{
            iconCls:'icon-add',
            handler:function(){
            	loadRecommenderCreateForm();
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
            	loadRecommenderModifyForm();
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removeRecommender();
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
        }],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
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

function loadRecommenderCreateForm(){
	var nodeType = $('input[name=nodeType]').val();
	
	var data = {
		targetElem : "#dlgForm",
    	title :nodeTypeArrs[searchFormData.nodeType] + " 생성",
    	queryOptions : {
    		action : "create",
    	}
    }
	
	//console.log("loadRecommenderCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/recommender/form/createForm?" + queryParam,
			function(response, status, xhr) {
		
				$(data.targetElem).dialog({
					width:600,
					cache: false,
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
							createRecommender(data);
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

function loadRecommenderModifyForm(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 노드를 선택해주세요');
		 return;
	}
	var data = {
    	targetElem : "#dlgForm",
        title : "[" +node.recommenderName + "] " + " 정회원 수정",
    	queryOptions : {
    		action : "modify",
    		recommenderNo : node.recommenderNo
    	}
    }
	//console.log("loadRecommenderModifyForm");
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	//console.log("/handleNodeForm?" + queryParam);
	
	$(data.targetElem).load("/api/recommender/form/createForm?" + queryParam,
		function(response, status, xhr) {
		
			$(data.targetElem).dialog({
				width:600,
				cache: false,
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
						updateRecommender(data);
					} },
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
			
			returnp.api.call('getRecommender', {recommenderNo : data.queryOptions.recommenderNo}, function(res){
				if (res.resultCode  == "100") {
					console.log(res);
					$('#createRecommenderForm').form('load',res.data);
					$('#memberNo').textbox({disabled : true });
					$('#recommenderCode').textbox({disabled : true });
					$('#regType').combobox('select',res.data.regType);
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
			
		});
}


function makeFormData(){
	var param = {
			memberNo : $('input[name=memberNo]').val(),	
			recommenderEmail : $('input[name=recommenderEmail]').val(),	
			recommenderName : $('input[name=recommenderName]').val(),	
			recommenderAddress : $('input[name=recommenderAddress]').val(),	
			recommenderTel: $('input[name=recommenderTel]').val(),	
			recommenderPhone: $('input[name=recommenderPhone]').val(),	
			saleManagerNo : $('input[name=saleManagerNo]').val(),	
			recommenderStatus : $('#recommenderStatus').combobox('getValue'),	
			regType : $('#regType').combobox('getValue')
		}
	return param;
}

function updateRecommender(data){
	var param =makeFormData();
	//console.log(param);
	returnp.api.call("updateRecommender", param, function(res){
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

function createRecommender(data){
	var param =makeFormData();
	//console.log("createRecommender");
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
		
	returnp.api.call("createRecommender", param, function(res){
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

function removeRecommender(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        		recommenderNo : node.recommenderNo
        	}
        	returnp.api.call("deleteRecommender", param, function(res){
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
	realodPage();
});
