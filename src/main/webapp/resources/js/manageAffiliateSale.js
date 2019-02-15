		columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			{field:'memberNo',width:30,align:'center',title : '회원 번호',hidden:false},	
			{field:'memberName',width:70,align:'center',title : '회원 이름'},
			{field:'affiliateName',width:100,align:'center',title : '협력업체 상호명'},
			{field:'affiliateSerial',width:100,align:'center',title : '협력업체 T - ID'},
			{field:'memberEmail',width:100,align:'center',title : '협력업체 이메일'},
			{field:'memberPhone',width:70,align:'center',title : '협력업체 전화번호'},
			{field:'totalAffiliteSale',width:100,align:'center',title : '매출 총합',hidden:false, formatter : affiliateSaleFormatter},
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
	returnp.api.call("affilaiteSaleReport", {}, function(res){
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
}


$(document).ready(function(){
	realodPage();
});
