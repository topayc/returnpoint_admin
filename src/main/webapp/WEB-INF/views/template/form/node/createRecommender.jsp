<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="createNode" style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createRecommenderForm"  enctype="multipart/form-data" name = "createForm" method="post"  >
			
			<div style="margin-bottom:30px"> <input class = "easyui-textbox"  id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 검색 ',labelAlign:'left',labelPosition : 'left'"> </div>
			<!-- <div style="margin-bottom:20px"> <input class = "easyui-textbox"  id ="memberEmail"  name="memberEmail" style="width:100%" data-options="label:'회원 이메일 ',labelAlign:'left',labelPosition : 'left', readonly : true"> </div> -->
			
			<div style="margin-bottom:30px"><input class = "easyui-textbox" id ="recommenderEmail"  name="recommenderEmail" style="width:100%" data-options="label:'추천인 이메일 ',labelAlign:'left',labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input class = "easyui-textbox" id ="recommenderName"  name="recommenderName" style="width:100%" data-options="label:'추천인 이름 ',labelAlign:'left',labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input class = "easyui-textbox" id ="recommenderAddress"  name="recommenderAddress" style="width:100%" data-options="label:'추천인 주소',labelAlign:'left',labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input class = "easyui-textbox" id ="recommenderTel"  name="recommenderTel" style="width:100%" data-options="label:'추천인 전화번호',labelAlign:'left',labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input class = "easyui-textbox" id ="recommenderPhone"  name="recommenderPhone" style="width:100%" data-options="label:'추천인 모바일',labelAlign:'left',labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"> <input class = "easyui-textbox"  id ="saleManagerNo"  name="saleManagerNo" style="width:100%" data-options="label:'영업 관리자  ',labelAlign:'left',labelPosition : 'left'"> </div>
			
			<div style="margin-bottom:30px"> 
				<select class = "easyui-combobox"  id ="recommenderStatus" name="recommenderStatus"   style="width:100%" data-options="label:'추천인 상태',labelAlign:'left',labelPosition : 'left'"> 
					<c:forEach var="nodeStatus"  items="${nodeStatuses}" varStatus="status">
						   <c:if test = "${ nodeStatus.useInAdmin == 'Y' }">
							  <option value='${nodeStatus.key}' >${nodeStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
	
			<div style="margin-bottom:30px"> 
				<select class = "easyui-combobox"  id ="regType" name="regType"   style="width:100%" data-options="label:'등록 타입',labelAlign:'left',abelPosition : 'left'" style="width:100%;height:30px"> 
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
		$('#memeberStatusContainer').panel();
		
		$('.easyui-textbox').textbox({
		});
		
		$('.easyui-combobox').combobox({
			labelPosition : 'left',
			labelAlign:'left',
			panelHeight: 'auto'
		});
		
		$('#memberNo').textbox({
			prompt: '회원 검색',
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
	            	}, function callback(selNode){
	            		$(e.data.target).textbox('setValue', selNode.memberNo);
	            		$('#memberEmail').textbox('setValue', selNode.memberEmail);
	            		$('#recommenderEmail').textbox('setValue', selNode.memberEmail);
	            		$('#recommenderName').textbox('setValue', selNode.memberName);
	            	});
				}
			}]
		});
		
		$('#saleManagerNo').textbox({
			prompt: '영업 관리자 검색',
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');
					loadNodeListView({
	            		targetElem : "#dlgForm2",
	            		title : "영업 관리자",
	            		queryOptions : {
	            			memberEmail : v,
	            			viewReqName : "nodeList",
	            			searchNodeType :  "6"
	            		}
	            	}, function callback(selNode){
	            		$('#saleManagerNo').textbox('setValue', selNode.saleManagerNo);
	            	});
				}
			}]
		});
		
		$('#memberEmail').textbox({
			editable : false,
		});
		$('#recommenderEmail').textbox({});
		$('#recommenderName').textbox({});
		$('#recommenderAddress').textbox({});
		$('#recommenderTel').textbox({});
		$('#recommenderPhone').textbox({});
		
		$('#recommenderCode').textbox({
			editable : false,
			prompt: '추천인 코드 생성 ', 
			buttonText:'코드 생성',
			 onClickButton : function(){
				var key = $('input[name=recommenderEmail]').val();
				if (!key || key == '') {
					$.messager.alert('알림',"회원 검색을 하신 후 이메일이 입력된 후에 코드 생성을 해주세요");
					return;
				}
				
				var data = {
					key : key,
					nodeType : 2
				}
				returnp.api.call('genCode', data, function(res){
					if (res.resultCode  == "100") {
						//console.log(res);
						$(data.targetElem).dialog('close');
						$('#recommenderCode').textbox('setValue', res.data);
					}else {
						$.messager.alert('오류 발생', res.message);
					}
				});
			 }
		});
		
		$('#paymentAmount').textbox({
			editable : false,
		});
		

		$('#paymentStatus').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            label: '결제 상태',
            labelPosition: 'left',
            multiple:false,
            required:true
		});

		$('#paymentType').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            label: '결제 방법',
            labelPosition: 'left',
            multiple:false,
            required:true
		});
		
		$('#regType').combobox({
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