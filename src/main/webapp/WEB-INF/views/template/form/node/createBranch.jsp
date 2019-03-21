<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="createBranchForm" style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createForm"  enctype="multipart/form-data" name = "createForm" method="post" action = "<c:url value='/createAbmsProject'/>">
			<div style="margin-bottom:20px"><input id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="branchEmail"  name="branchEmail" style="width:100%" data-options="label:'지사 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="branchName"  name="branchName" style="width:100%" data-options="label:'지사 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="branchAddress"  name="branchAddress" style="width:100%" data-options="label:'지사 주소',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="branchTel"  name="branchTel" style="width:100%" data-options="label:'지사 전화번호',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="branchPhone"  name="branchPhone" style="width:100%" data-options="label:'지사 모바일',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="soleDistNo"  name="soleDistNo" style="width:100%" data-options="label:' 총판 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="soleDistName"  name="soleDistName" style="width:100%" data-options="label:' 총판 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="soleDistEmail"  name="soleDistEmail" style="width:100%" data-options="label:' 총판 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="recommenderNo"  name="recommenderNo" style="width:100%" data-options="label:'추천인 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px">
				<input id ="recommenderName"  name="recommenderName" style="width:100%" data-options="label:'추천인 이름 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			<div style="margin-bottom:20px">
				<select id ="branchStatus" name="branchStatus"   style="width:100%" data-options="label:'지사 상태',labelWidth :140,labelPosition : 'left'"> 
					<c:forEach var="nodeStatus"  items="${nodeStatuses}" varStatus="status">
						   <c:if test = "${ nodeStatus.useInAdmin == 'Y' }">
							  <option value='${nodeStatus.key}' >${nodeStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			
			<div style="margin-bottom:20px"> 
				<select class = "easyui-combobox"  id ="regType" name="regType"   style="width:100%" data-options="label:'등록 타입',labelWidth :140,labelAlign:'left',labelPosition : 'left'" style="width:100%;height:30px"> 
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
	            		$('#branchEmail').textbox('setValue', selNode.memberEmail);
	            		$('#branchName').textbox('setValue', selNode.memberName);
	            	});
				}
			}]
		});
		
		$('#soleDistNo').textbox({
			label : roundLabel("총판 검색"),
			prompt: '참조할 총판 검색 ',
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');
					loadNodeListView({
	            		targetElem : "#dlgForm2",
	            		title : "총판 검색",
	            		queryOptions : {
	            			memberEmail : v,	
	            			viewReqName : "nodeList",
	            			searchNodeType :  "7"
	            			
	            		}
	            	},function callback(selNode){
	            		$(e.data.target).textbox('setValue', selNode.soleDistNo);
	            		$('#soleDistName').textbox('setValue', selNode.soleDistName);
	            		$('#soleDistEmail').textbox('setValue', selNode.soleDistEmail);
	            		$('#soleDistName').textbox('setValue', selNode.soleDistName);
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
	            	}, function callback(selNode){
	            		$('#recommenderNo').textbox('setValue', selNode.memberNo);
	            		$('#recommenderName').textbox('setValue', selNode.memberName);
	            	});
				}
			}]
		});

		$('#soleDistName').textbox({
			label : roundLabel("총판명"),
			prompt: '총판 이름',
			readonly :true
		});

		$('#recommenderName').textbox({
			label : roundLabel("추천인 이름"),
			prompt: '추천인 이름',
			readonly :true
		});
		
		$('#branchName').textbox({
			label : roundLabel("지사명"),
			prompt: '지사 상호 및 이름 ', 
		});
		$('#branchEmail').textbox({
			label : roundLabel("회원 검색"),
			editable : false,
			prompt: '지사 이메일',
		});
		$('#soleDistEmail').textbox({
			label : roundLabel("총판 이메일"),
			editable : false,
		});
		$('#branchAddress').textbox({
			label : roundLabel("지사 주소"),
			prompt: '지사 주소', 
		});
		
		$('#branchTel').textbox({
			label : roundLabel("지사 전화번호"),
			prompt: '지사 전화번호 ', 
		});
		$('#branchPhone').textbox({
			label : roundLabel("지사 핸드폰"),
			prompt: '지사 핸드폰', 
		});
		$('#branchStatus').combobox({
			label : roundLabel("지사 상태"),
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