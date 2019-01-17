<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createApiServiceForm"  enctype="multipart/form-data" name = "createApiServiceForm" method="post" >
			<input type = "hidden"  id = "apiServiceNo" name = "apiServiceNo"/>			
			
			<div style="margin-bottom:30px"><input id ="company" name="company" style="width:100%" data-options="label:'회사명 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="project" name="project" style="width:100%" data-options="label:'프로젝트명',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px">
				<select class = "easyui-combobox"  id ="apiService" name="apiService"   style="width:100%" data-options="label:'서비스 종류',labelWidth :140,labelAlign:'left',labelPosition : 'left'"> 
					<c:forEach var="apiServiceType"  items="${apiServiceTypes}" >
						  <option value='${apiServiceType.key}' >${apiServiceType.value} </option>
					</c:forEach>
				</select> 
			</div>
			<input id ="apiServiceName" name="apiServiceName" type = "hidden">
			<div style="margin-bottom:30px"><input id ="domain" name="domain" style="width:100%" data-options="label:'도메인',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="ip" name="ip" style="width:100%" data-options="label:'아이피(I.P)',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="apiKey" name="apiKey" style="width:100%" data-options="label:'API KEY ',labelWidth :140,labelPosition : 'left'"> </div>
			
			<div style="margin-bottom:20px"> 
				<select class = "easyui-combobox"  id ="apiServiceStatus" name="apiServiceStatus"   style="width:100%" data-options="label:'서비스 상태',labelWidth :140,labelAlign:'left',labelPosition : 'left'"> 
					<c:forEach var="apiServiceStatus"  items="${apiServiceStatuses}" varStatus="status">
						  <option value='${apiServiceStatus.key}' >${apiServiceStatus.value} </option>
					</c:forEach>
				</select> 
			</div>	
			
		</form>
	</div>
</div>
<script>
function setViewInit(){
	$('#apiService').combobox({
		showItemIcon: true,
        editable: false,
        panelHeight: 'auto',
        multiple:false,
        required:true,
        onSelect : function(){
        }
	});
	
	$('#company').textbox({
		prompt: '회사명', 
	});
	$('#project').textbox({
		prompt: '프로젝트명', 
	});
	$('#domain').textbox({
		prompt: 'www.xxxxx.xxx', 
	});
	$('#ip').textbox({
		prompt: '000.000.000.000', 
	});
	
	$('#apiKey').textbox({
		prompt: 'API KEY', 
		editable : false,
		buttonText:'발급',
		onClickButton :
			function(e){
				var param = $("#createApiServiceForm").serializeArray();
				returnp.api.call("makeApiKey", param, function(res){
					console.log(res);
					if (res.resultCode  == "100") {
						$.messager.alert('알림', res.message ,'', function(){
							$("#apiKey").textbox('setValue', res.data);	
						});	
					}else {
						$.messager.alert('오류 발생', res.message);
					}
				});
			}
	});	
	$('#apiServiceStatus').combobox();
	$('#apiServiceStatus').combobox('select',"1");
}
$(document).ready(function(){
setViewInit();
})
</script>