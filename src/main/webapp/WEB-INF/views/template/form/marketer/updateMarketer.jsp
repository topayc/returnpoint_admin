<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="createNode" style ="padding:5px;margin: 20px" >
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="updateMarketerForm"  name = "updateMarketerForm" >
			
			<div style="margin-bottom:30px">
				<input id ="marketerDegree"  name="marketerDegree" style="width:100%" data-options="label:'마케터 레벨',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="marketerCode"  name="marketerCode" style="width:100%" data-options="label:'마케터 아이디',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'연결 회원 검색 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="memberName"  name="memberName" style="width:100%" data-options="label:'연결 회원 이름 ',labelWidth :140,labelPosition : 'left'"> 
			</div>

			<div style="margin-bottom:30px">
				<input id ="memberEmail"  name="memberEmail" style="width:100%" data-options="label:'연결 회원 이메일 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			
			<div style="margin-bottom:30px">
				<select id ="marketerStatus" name="marketerStatus"   style="width:100%" data-options="label:'마케터 상태',labelWidth :140,labelPosition : 'left'">
					<c:forEach var="marketerStatus"  items="${marketerStatuses}" varStatus="status">
						   <c:if test = "${ marketerStatus.useInAdmin == 'Y' }">
							  <option value='${marketerStatus.key}' >${marketerStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			
		</form>
	</div>
</div>
<script>
	function setViewInit(){
		$('#memeberStatusContainer').panel();

		$('#marketerCode').textbox({
			readonly : true
		});
		
		$('#marketerDegree').textbox({
			readonly : true
		});
		
		
		$('#memberName').textbox({
			readonly : true
		});

		$('#memberEmail').textbox({
			readonly : true
		});
		
		$('#memberNo').textbox({
			prompt: '연결 회원',
			editable : false,
			buttonText:'연결 회원 검색',
			buttonAlign:'right',
			onClickButton : function(data){
				var v = $('#memberNo').textbox('getValue');
				loadNodeListView({
            		targetElem : "#dlgForm2",
            		title : "회원 검색",
            		queryOptions : {
            			memberEmail : v,
            			viewReqName : "nodeList",
            			searchNodeType :  "1"
            		}
            	},function callback(selNode){
            		$('#memberNo').textbox('setValue', selNode.memberNo);
            		$('#memberName').textbox('setValue', selNode.memberName)
            		$('#memberEmail').textbox('setValue', selNode.memberEmail)
            	});
			}
		});
		
		$('#marketerStatus').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});

	}
	setViewInit();
</script>