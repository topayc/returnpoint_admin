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
		<table id = "member_list_grid" style ="width:100%;height:400px"></table>
	</div>
</div>

<script>

var memberColumns = [[
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
	    {field:'memberNo',width:20,align:'center',title : '번호',hidden:false},
	    {field:'memberName',width:40,align:'center',title : '이름'},
	    {field:'memberEmail',width:60,align:'center',title : '이메일'},
	    {field:'memberPassword',width:30,align:'center',title : '비밀번호', hidden: true},
	    {field:'memberPassword2',width:30,align:'center',title : '비밀번호2', hidden: true},
	    {field:'memberPhone',width:40,align:'center',title : '핸드폰'},
	    {field:'memberStatus',width:30,align:'center',title : '상태',formatter : nodeStatusFormatter},
	    {field:'recommenderName',width:30,align:'center',title : '추천인', hidden:false,formatter : slashFormatter},
	    {field:'recommenderEmail',width:60,align:'center',title : '추천인 이메일', hidden:true, formatter : slashFormatter},
	    {field:'country',width:20,align:'center',title : '국가', formatter : slashFormatter},
	    {field:'greenPointAmount',width:40,align:'center',title : 'R POINT', formatter : numberGreenFormatter},
	    {field:'redPointAmount',width:40,align:'center',title : 'R PAY', formatter : numberRedFormatter},
	    {field:'memberAuthType',width:30,align:'center',title : '인증방법', formatter : authTypeFormatter,  hidden: true},
	    {field:'memberType',width:20,align:'center',title : '유형',hidden:true},
	    {field:'isSoleDist',width:20,align:'center',title : '총판', formatter : ynFormatter},
	    {field:'isRecommender',width:20,align:'center',title : '정회원', formatter : ynFormatter},
	    {field:'isSaleManager',width:30,align:'center',title : '영업관리자', formatter : ynFormatter},
	    {field:'isAgency',width:20,align:'center',title : '대리점', formatter : ynFormatter},
	    {field:'isBranch',width:20,align:'center',title : '지사', formatter : ynFormatter},
	    {field:'isAffiliate',width:25,align:'center',title : '협력업체', formatter : ynFormatter},
	    {field:'greenPointAccStatus',width:20,align:'center',title : 'G 적립', formatter : ynFormatter},
	    {field:'redPointAccStatus',width:20,align:'center',title : 'R 적립', formatter : ynFormatter},
	    {field:'greenPointUseStatus',width:20,align:'center',title : 'G 사용', formatter : ynFormatter},
	    {field:'redPointUseStatus',width:20,align:'center',title : 'R 사용', formatter : ynFormatter},
	    {field:'regType',width:40,align:'center',title : '등록 유형',formatter : registTypeFormatter, hidden: true},
	    {field:'regAdminNo',width:20,align:'center',title : '등록자', formatter : registAdminFormatter, hidden: true},
	    {field:'createTime',width:50,align:'center',title : '등록일',formatter : dateFormatter},
	    {field:'updateTime',width:30,align:'center',title : '수정일',hidden: true, hidden: true}
	 ]];
$('#member_list_grid').datagrid({
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
	
    columns:memberColumns
});


</script>
