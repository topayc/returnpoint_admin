		columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			{field:'memberNo',width:50,align:'center',title : '회원 번호',hidden:false},	
			{field:'memberName',width:100,align:'center',title : '이름'},
			{field:'memberEmail',width:100,align:'center',title : 'E-MAIL'},
			{field:'memberPhone',width:70,align:'center',title : '전화 번호'},
			{field:'memberRPoint',width:100,align:'center',title : 'R-POINT',hidden:false, formatter : redPointFormatter},
			{field:'soleDistGPoint',width:100,align:'center',title : '총판 G-POINT',hidden:false, formatter : greenPointFormatter},
			{field:'branchGPoint',width:100,align:'center',title : '지점 G-POINT',hidden:false, formatter : greenPointFormatter},
			{field:'agencyGPoint',width:100,align:'center',title : '대리점 G-POINT',hidden:false, formatter : greenPointFormatter},
			{field:'affiliateGPoint',width:100,align:'center',title : '협력업체 G-POINT',hidden:false, formatter : greenPointFormatter},
			{field:'memberGPoint',width:100,align:'center',title : '회원 G-POINT',hidden:false, formatter : greenPointFormatter},
			{field:'totalGPoint',width:100,align:'center',title : 'G-POINT 총계', formatter : greenPointTotalFormatter},
			{field:'totalPoint',width:100,align:'center',title : '포인트 총계', formatter : sumTotalPoint},
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
		prompt : "" ,
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
			
		returnp.api.call("getMyTotalPointInfo", param, function(res){
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
	    columns:columns
	    
	});
	setListPager();
}

function setListPager(){
	var pager = $('#node_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
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


function realodPage(){
	$('#search_btn').click();
}


$(document).ready(function(){
//	$('#search_btn').click();
});
