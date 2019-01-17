<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="createNode" style ="padding:5px;margin: 20px" >
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createMemberForm"  enctype="multipart/form-data" name = "createForm" method="post" action = "<c:url value='/createAbmsProject'/>">
			<div style="margin-bottom:30px">
				<input id ="memberEmail"  name="memberEmail" style="width:100%" data-options="label:'회원 이메일 ',labelWidth :140,labelPosition : 'left'"> 
				<input type ="hidden" id = "checkEmailMultiple"  name = "checkEmailMultiple" value = "N"/>
 			</div>
			
			<div style="margin-bottom:30px">
				<input id ="memberName"  name="memberName" style="width:100%" data-options="label:'이름 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			
			<div style="margin-bottom:30px">
				<input id ="memberPhone"  name="memberPhone" style="width:100%" data-options="label:'핸드폰',labelWidth :140,labelPosition : 'left'"> 
				<input type ="hidden" id = "checkPhoneMultiple"  name = "checkPhoneMultiple" value = "N"/>
			</div>

		<!-- 	<div style="margin-bottom:30px">
				<div style = "display:inline-block;width:140px "><span>모바일</span></div>
				<div style = "display:inline-block;width: 100%">
					<input id ="memberPhone1"  name="memberPhone" style="width:100%" > 
				</div>
				<div style = "display:inline-block;width: 100%">
					<input id ="memberPhone2"  name="memberPhone" style="width:100%" > 
				</div>
				<div style = "display:inline-block;width: 100%">
					<input id ="memberPhone3"  name="memberPhone" style="width:100%" > 
				</div>
				<input type ="hidden" id = "checkPhoneMultiple"  name = "checkPhoneMultiple" value = "N"/>
			</div> -->
			
			<div style="margin-bottom:30px">
				<input id ="memberPassword"  name="memberPassword" style="width:100%" data-options="label:'비밀번호 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="memberPassword2"  name="memberPassword2" style="width:100%" data-options="label:'비밀번호2 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<!-- <div style="margin-bottom:30px">
				<input id ="memberTel""  name="memberTel" style="width:100%" data-options="label:'전화번호',labelWidth :140,labelPosition : 'left'"> 
			</div> -->

			<div style="margin-bottom:30px">
				<input id ="recommenderNo"  name="recommenderNo" style="width:100%" data-options="label:'추천인 검색 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input id ="recommenderName"  name="recommenderName" style="width:100%" data-options="label:'추천인 이름 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
	<!-- 		<div style="margin-bottom:20px">
				<select id ="memberType" name="memberType"   style="width:100%" data-options="label:'회원 유형',labelPosition : 'top'">
				</select> 
			</div> -->
			
			<div style="margin-bottom:30px">
				<select id ="memberStatus" name="memberStatus"   style="width:100%" data-options="label:'회원 상태 ',labelWidth :140,labelPosition : 'left'">
					<c:forEach var="nodeStatus"  items="${nodeStatuses}" varStatus="status">
						   <c:if test = "${ nodeStatus.useInAdmin == 'Y' }">
							  <option value='${nodeStatus.key}' >${nodeStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			
			<div style="margin-bottom:30px">
				<select id ="memberAuthType" name="memberAuthType"   style="width:100%" data-options="label:'인증 방법',labelWidth :140,labelPosition : 'left'">
					<c:forEach var="authType"  items="${authTypes}" varStatus="status">
						    <c:if test = "${ authType.useInAdmin == 'Y' }">
							  <option value='${authType.key}' >${authType.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			
			<div style="margin-bottom:30px">
				<select id ="regType" name="regType"   style="width:100%" data-options="label:'등록 유형',labelWidth :140,labelPosition : 'left'">
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

		$('#memberEmail').textbox({
			prompt: '이메일 입력',
			buttonText:'중복/유효성',
			onClickButton : function(data){
				var email = $('input[name=memberEmail]').val();
				if (!email || email == '') {
					$.messager.alert('알림',"사용하고자 하는 이메일을 입력해주세요");
					return;
				}
				var data = {
					email : email
				}
				returnp.api.call('checkEmailDuplicated', data, function(res){
					if (res.resultCode  == "100") {
						$.messager.alert('알림', res.message);
						$(data.targetElem).dialog('close');
						$("#checkEmailMultiple").val("Y")
					}else {
						$.messager.alert('알림', res.message);
						$('#memberEmail').textbox("clear");
						$("#checkEmailMultiple").val("N")
					}
				});
			}
		});
		
		$('#memberPhone').textbox({
			prompt: '핸드폰 번호 입력',
			buttonText:'중복/유효성',
			onClickButton : function(data){
				var phone = $('input[name=memberPhone]').val();
				if (!phone || phone == '') {
					$.messager.alert('알림',"사용하고자 하는 핸드폰 번호를 입력해주세요");
					return;
				}
				var data = {
						phone : phone
				}
				returnp.api.call('checkPhoneDuplicated', data, function(res){
					if (res.resultCode  == "100") {
						$.messager.alert('알림', res.message);
						$(data.targetElem).dialog('close');
						$("#checkPhoneMultiple").val("Y")
					}else {
						$.messager.alert('알림', res.message);
						$('#memberPhone').textbox("clear");
						$("#checkPhoneMultiple").val("N")
					}
				});
			}
		});
		
		$('#memberPassword').textbox({
			prompt: '비밀번호 입력',
		});
		
		$('#memberPassword2').textbox({
			prompt: '비밀번호 입력',
		});
		
		$('#memberName').textbox({
			prompt: '비밀번호 입력',
		});
		
		$('.toggle').linkbutton({
			toggle : true,
		});
		
		$('#recommenderNo').textbox({
			prompt: '추천인 번호',
			editable : false,
			buttonText:'추천인 검색',
			buttonAlign:'right',
			onClickButton : function(data){
				var v = $('#recommenderNo').textbox('getValue');
				loadNodeListView({
            		targetElem : "#dlgForm2",
            		title : "추천인",
            		queryOptions : {
            			memberEmail : v,
            			viewReqName : "nodeList",
            			searchNodeType :  "1"
            		}
            	},function callback(selNode){
            		$('#recommenderNo').textbox('setValue', selNode.memberNo);
            		$('#recommenderName').textbox('setValue', selNode.memberName)
            	});
			}
		});

		$('#recommenderName').textbox({
			prompt: '추천인 이름',
			readonly :true
		});
		
		$('#memberTel').textbox({
			prompt: '전화번호',
		});
		$('#memberPhone').textbox({
			prompt: '핸드폰 번호',
		});
		
		$('#memberAuthType').combobox({
			showItemIcon: true,
            readonly :true,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});
		$('#memberAuthType').combobox('select', "1");
		
		$('#memberStatus').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});

		$('#regType').combobox({
			showItemIcon: true,
            panelHeight: 'auto',
            multiple:false,
            required:true,
            readonly : true
		});
		$('#regType').combobox('select',"A");
		
		$('#memberStatus').combobox({
			showItemIcon: true,
            editable: false,
            multiple:false,
            required:true
		});
	}
	setViewInit();
</script>