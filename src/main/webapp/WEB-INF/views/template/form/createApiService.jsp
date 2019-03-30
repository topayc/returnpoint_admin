<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createApiServiceForm"  enctype="multipart/form-data" name = "createApiServiceForm" method="post" >
			<input type = "hidden"  id = "apiServiceNo" name = "apiServiceNo"/>			
			<div style="margin-bottom:30px"><input id ="affiliateNo"  name="affiliateNo" style="width:100%" data-options="label:'API 연결 가맹점 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="company" name="company" style="width:100%" data-options="label:'회사명 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="project" name="project" style="width:100%" data-options="label:'프로젝트명',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="tId"" name="tId" style="width:100%" data-options="label:'T ID',labelWidth :140,labelPosition : 'left'"> </div>
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
	$('#affiliateNo').textbox({
		label : roundLabel("API 연결 가맹점"),
		prompt: 'API 연결 가맹점 검색 ',
		editable : false,
		icons:[{
			iconCls:'icon-search',
			handler: function(e){
				var v = $(e.data.target).textbox('getValue');
				loadNodeListView({
            		targetElem : "#dlgForm2",
            		title : "가맹점 검색",
            		queryOptions : {
            			memberEmail : v,
            			viewReqName : "nodeList",
            			searchNodeType :  "5"
            			
            		}
            	},function callback(selNode){
            		$(e.data.target).textbox('setValue', selNode.affiliateNo);
            		$("#company").textbox('setValue', selNode.affiliateName);
            		$("#project").textbox('setValue', selNode.affiliateName + "_쇼핑몰 API 연동");
            		$("#tId").textbox('setValue', selNode.affiliateSerial);
            	});
			}
		}]
	});
	
	$('#apiService').combobox({
		label : roundLabel("제공 서비스 타입"),
		showItemIcon: true,
        editable: false,
        panelHeight: 'auto',
        multiple:false,
        required:true,
        onSelect : function(){
        }
	});
	
	$('#company').textbox({
		label : roundLabel("회사명"),
		prompt: '회사명', 
	});
	$('#project').textbox({
		label : roundLabel("프로젝트 명"),
		prompt: '프로젝트명', 
	});
	$('#domain').textbox({
		label : roundLabel("도메인"),
		prompt: 'www.xxxxx.xxx', 
	});
	
	$('#tId').textbox({
		label : roundLabel("T ID"),
		prompt: 'T ID', 
		editable : false,
/* 		buttonText:'발급',
		onClickButton :
			function(e){
				var param = $("#createApiServiceForm").serializeArray();
				returnp.api.call("makeRfId", param, function(res){
					console.log(res);
					if (res.resultCode  == "100") {
						$.messager.alert('알림', res.message ,'', function(){
							$("#rfId").textbox('setValue', res.data);	
						});	
					}else {
						$.messager.alert('오류 발생', res.message);
					}
				});
			} */
	});	
	$('#ip').textbox({
		label : roundLabel("아이피(IP)"),
		prompt: '000.000.000.000', 
	});
	
	$('#apiKey').textbox({
		label : roundLabel("API KEY"),
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
	$('#apiServiceStatus').combobox({
		label : roundLabel("서비스 상태"),
	});
	$('#apiServiceStatus').combobox('select',"1");
}
$(document).ready(function(){
setViewInit();
})
</script>