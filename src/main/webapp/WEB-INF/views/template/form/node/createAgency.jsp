<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createAgencyForm"  enctype="multipart/form-data" name = "createForm" method="post" action = "<c:url value='/createAbmsProject'/>">
			<input type = "hidden" id = "orgMemberNo" value = "">
			<div style="margin-bottom:30px"><input id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="agencyName"  name="agencyName" style="width:100%" data-options="label:'대리점 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="agencyEmail"  name="agencyEmail" style="width:100%" data-options="label:'대리점 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="agencyAddress"  name="agencyAddress" style="width:100%" data-options="label:'대리점 주소',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="agencyTel"  name="agencyTel" style="width:100%" data-options="label:'대리점 전화번호',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="agencyPhone"  name="agencyPhone" style="width:100%" data-options="label:'대리점 모바일',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="branchNo"  name="branchNo" style="width:100%" data-options="label:'지사 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px">
				<input id ="branchName"  name="branchName" style="width:100%" data-options="label:'지사 이름 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			<div style="margin-bottom:30px"><input id ="recommenderNo"  name="recommenderNo" style="width:100%" data-options="label:'추천인 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px">
				<input id ="recommenderName"  name="recommenderName" style="width:100%" data-options="label:'추천인 이름 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:20px"> 
				<select class = "easyui-combobox"  id ="agencyStatus" name="agencyStatus"   style="width:100%" data-options="label:'추천인 상태',labelWidth :140,labelAlign:'left',labelPosition : 'left'"> 
					<c:forEach var="nodeStatus"  items="${nodeStatuses}" varStatus="status">
						   <c:if test = "${ nodeStatus.useInAdmin == 'Y' }">
							  <option value='${nodeStatus.key}' >${nodeStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
	
			<div style="margin-bottom:20px"> 
				<select class = "easyui-combobox"  id ="regType" name="regType"   style="width:100%" data-options="label:'등록 타입',labelWidth :140,labelAlign:'left',abelPosition : 'left'" style="width:100%;height:30px"> 
					<c:forEach var="reigistType"  items="${registTypes}" varStatus="status">
						    <c:if test = "${ reigistType.useInAdmin == 'Y' }">
							  <option value='${reigistType.key}' >${reigistType.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
		</form>
	</div>
</div>
<script>
	function setViewInit(){
		$('#memberNo').textbox({
			label : roundLabel("회원 검색"),
			prompt: '참조할 회원 검색 ',
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');
					loadNodeListView({
	            		targetElem : "#dlgForm2",
	            		title : "회원 검색",
	            		queryOptions : {
	            			memberEmail : v,
	            			viewReqName : "nodeList",
	            			searchNodeType :  "1"
	            			
	            		}
	            	},function callback(selNode){
	            		$(e.data.target).textbox('setValue', selNode.memberNo);
	            		$("#agencyEmail").textbox('setValue', selNode.memberEmail);
	            		$("#agencyName").textbox('setValue', selNode.memberName);
	            	});
				}
			}]
		});
		
		$('#branchNo').textbox({
			label : roundLabel("지사 검색"),
			prompt: '참조할 지사',
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');
					loadNodeListView({
	            		targetElem : "#dlgForm2",
	            		title : "지사 검색",
	            		queryOptions : {
	            			memberEmail : v,
	            			viewReqName : "nodeList",
	            			searchNodeType : "3"
	            			
	            		}
	            	},function callback(selNode){
	            		$(e.data.target).textbox('setValue', selNode.branchNo);
	            		$("#branchName").textbox('setValue', selNode.branchName);
	            	});
				}
			}]
		});
		
		$('#recommenderNo').textbox({
			label : roundLabel("추천인 검색"),
			prompt: '참조할 추천인 검색 ',
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');
					loadNodeListView({
	            		targetElem : "#dlgForm2",
	            		title : "추천인 검색",
	            		queryOptions : {
	            			memberEmail : v,
	            			viewReqName : "nodeList",
	            			searchNodeType :  "1"
	            			
	            		}
	            	},function callback(selNode){
	            		$(e.data.target).textbox('setValue', selNode.memberNo);
	            		$('#recommenderName').textbox("setValue", selNode.memberName);
	            	});
				}
			}]
		});

		$('#branchName').textbox({
			label : roundLabel("지사명"),
			prompt: '지사 이름',
			readonly :true
		});

		$('#recommenderName').textbox({
			label : roundLabel("추천인 이름"),
			prompt: '추천인 이름',
			readonly :true
		});
		
		$('#agencyEmail').textbox({
			label : roundLabel("대리점 이메일"),
			editable : false,
		});
		$('#agencyName').textbox({
			label : roundLabel("대리점명"),
			prompt: '대리점 상호 및 이름', 
		});
		$('#agencyAddress').textbox({
			label : roundLabel("대리점 주소"),
			prompt: '대리점 주소', 
		});
		$('#agencyTel').textbox({
			label : roundLabel("대리점 전화번호"),
			prompt: '대리점 전화번호', 
		});
		$('#agencyPhone').textbox({
			label : roundLabel("대리점 핸드폰"),
			prompt: '대리점 핸드폰 번호', 
		});
		$('#agencyStatus').combobox({
			label : roundLabel("대리점 상태"),
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            multiple:false,
            required:true
			
		});
		$('#regType').combobox({
			label : roundLabel("등록 타입"),
			showItemIcon: true,
            readonly : true,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});
		$('#regType').combobox('select',"A");
	}
	setViewInit();
</script>