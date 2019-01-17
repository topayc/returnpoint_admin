<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input type = 'hidden' value = ''${nodeSearch.searchCallback}'/>
<div id="searchContainer" style ="padding:5px;margin: 3px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <div style="margin-bottom:25px;margin-top:17x"> <input id ="listSearchKeyword"  class = "easyui-textbox" name="keyword" style="width:100%" > </div>
		<table id = "search_result" style ="width:100%"></table>
	</div>
</div>

<script>
list_columns = [[
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},	    
	    {field:'apiService',width:70,align:'center',title : 'API 분류'},
	    {field:'apiServiceName',width:70,align:'center',title : 'API 분류명'},
	    ]];


$('#listSearchKeyword').searchbox({
	prompt : "검색어를 입력해주세요",	
	iconAlign : 'right',
 	searcher: function(){
		var param = $("#listSearchListForm").serializeArray();
		returnp.api.call("getApiServiceList", param, function(res){
			if (res.resultCode  == "100") {
				$('#search_result').datagrid({
					title : "[검색 결과][검색어 : "+ keyword +"] 총 " + res.total +  " 건의 검색 결과",
					data : res.rows
				});
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		});
	} 
});

function setViewInit2(){
	var params = $("#listSearchListForm").serializeArray();	
	returnp.api.call("getApiServiceList", params, function(res){
		console.log(res)
		if (res.resultCode  == "100") {
			console.log(res)
			$('#search_result').datagrid({
				title : "[검색 결과] 총 " + res.total +  " 건의 검색 결과",
				data : res.rows
			});
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}
setViewInit2();

/* 검색 버튼  초기화*/
$('#list_search_btn').linkbutton({
	onClick : function(){
		var param = $("#listSearchListForm").serializeArray();
		returnp.api.call("/api/apiService/list", param, function(res){
			if (res.resultCode  == "100") {
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
		$('#listSearchKeyword').textbox('clear');
	}
});

$('#search_result').datagrid({
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
	
    columns:list_columns
});

</script>