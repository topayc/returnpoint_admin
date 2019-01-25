<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="createNode" style ="padding:5px;margin: 20px" >
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createMarketerForm"  name = "createMarketerForm" >
			<div style="margin-bottom:30px">
				<input id ="count"  name="count" style="width:100%" data-options="label:'생설할 차수  ',labelWidth :140,labelPosition : 'left'"> 
 			</div>
		</form>
	</div>
</div>
<script>
	function setViewInit(){
		$('#count').textbox({ prompt: '생성할 차수 갯수'});
	}
	setViewInit();
</script>