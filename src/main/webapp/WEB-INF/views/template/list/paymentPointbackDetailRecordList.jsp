<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style ="padding: 5px;height : 100%">
	<div  >
<%-- 	   	<div class="easyui-panel" title="" style="width:100%;padding:5px 5px;margin-bottom : 2px; height: 100%">
	        <form id="listSearchListForm" method="post">
	             <div style="margin-bottom:10px;display:inline-block;width:200px">
	                <select id = "listSearchNodeType" class="easyui-combobox" name="listSearchNodeType" style="width:100%" data-options = "labelPosition:'left'">
						<option value="0">전체</option>
	                	<c:forEach var="nodeType" items="${nodeTypeList}" varStatus="status">
						   <option value="${nodeType.key}">${nodeType.value}</option>
						</c:forEach>
	                </select> 
	            </div>
	            <div style="margin-bottom:10px;width:600px;display:inline-block;">
	                <input class="easyui-textbox"  id ="listSearchKeyword" name="listSearchKeyword"  style="width:100%">
	            </div>
	            
	            <div style="padding:5px 0;display:inline-block;margin-left : 10px">
		            <a href="javascript:void(0)" id = "list_search_btn"  style="width:80px;margin-right : 5px;">검색</a>
		            <a href="javascript:void(0)" id = "list_reset_btn"   style="width:70px;">리셋</a>
	       	 	</div>
	        </form>
    	</div> --%>
		<table id = "list_search_result" style ="width:100%;height:400px"></table>
	</div>
</div>

<script>
var columns = [[
		{field:'paymentPointbackRecordNo',width:60,align:'center',title : '번호',hidden:true},
		{field:'paymentTransactionNo',width:70,align:'center',title : '참조 결제번호',hidden:false},
		{field:'memberNo',width:60,align:'center',title : '회원 번호',hidden:true},
		{field:'memberEmail',width:150,align:'center',title : '이메일'},
		{field:'memberName',width:100,align:'center',title : '이름'},
		{field:'nodeType',width:70,align:'center',title : '노드 타입', formatter : nodeTypeFormatter},
		{field:'paymentApprovalAmount',width:100,align:'center',title : '기준 결제 금액', formatter : numberBoldFormatter},
		{field:'accRate',width:60,align:'center',title : '적립율'},
		{field:'pointbackAmount',width:100,align:'center',title : '지급 G 포인트', formatter : numberGreenFormatter},
		{field:'memberPhone',width:100,align:'center',title : '핸드폰'},
		{field:'nodeNo',width:70,align:'center',title : 'nodeNo',hidden:true},
		{field:'createTime',width:120,align:'center',title : '등록일', formatter : dateFormatter},
		{field:'updateTime',width:120,align:'center',hidden:true, title : '수정일', formatter : dateFormatter},
	 ]];

$('#listSearchKeyword').searchbox({
	prompt : "검색어를 입력해주세요",
	/* height : 30, */
	iconAlign : 'right',
/* 	searcher: function(){
		var param = makeSearchListParam();
		returnp.api.call("findPaymentPointbackRecords", param, function(res){
			if (res.resultCode  == "100") {
				//console.log(res)
				//console.log($('#search_result').length)
				$('#list_search_result').datagrid({
					title : "[검색 결과][검색어 : "+ keyword +"] 총 " + res.total +  " 건의 검색 결과",
					data : res.rows
				});
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		});
	} */
});

function makeSearchListParam(){
	var keyword = $("#listSearchKeyword").searchbox("getValue");
	/* var listSearchNodeType = $('input[name=listSearchNodeType]').val(); */
	var param = {
		searchKeyword :  keyword 
/* 		searchNodeType : listSearchNodeType */
	};
	return param;
}
/* 검색 버튼  초기화*/
$('#list_search_btn').linkbutton({
	onClick : function(){
		var param = makeSearchListParam();
		returnp.api.call("findPaymentPointbackRecords", param, function(res){
			if (res.resultCode  == "100") {
				//console.log(res)
				//console.log($('#list_search_result').length)
				$('#search_result').datagrid({
					title : "[검색 결과][검색어 : "+ keyword +"] 총 " + res.total +  " 건의 검색 결과",
					data : res.rows
				});
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		});
	},
	iconCls:'icon-ok'
});

$('#list_reset_btn').linkbutton({
	onClick : function(){
		$('#listSearchListForm').form('clear');
	/* 	$('#listSearchNodeType').combobox('select', 0); */
		$('#listSearchKeyword').textbox('clear');
	}
});

/* 노드 타입 셀렉트 박스  초기화*/
/* $('#listSearchNodeType').combobox({
	showItemIcon: true,
	editable: false,
	panelHeight: 'auto',
	labelPosition: 'left',
	multiple:false,
	width: 270,
	label : "검색 그룹"
}); */

$('#list_search_result').datagrid({
	/* title : "[검색 결과]", */
	singleSelect:true,
	collapsible:false,
	fitColumns:true,
	selectOnCheck : true,
	checkOnSelect : true,
	rownumbers : true,
	border : true,
	/* pagination: true, */
	autoRowHeight : false,
	onSelect : function(){},
	/* pagePosition : "top", */
	onLoadSuccess : function(){},
	
    columns:columns
});

</script>