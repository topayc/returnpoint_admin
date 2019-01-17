<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createRedPointForm"  enctype="multipart/form-data" name = "createRedPointForm" >
			<input type = "hidden"  id = "greenPointNo" name = "greenPointNo"/>
			<div style="margin-bottom:30px"><input id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 번호 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="memberName"  name="memberName" style="width:100%" data-options="label:'회원 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="memberEmail"  name="memberEmail" style="width:100%" data-options="label:'회원 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="memberPhone"  name="memberPhone" style="width:100%" data-options="label:'회원 핸드폰 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="pointAmount"  name="pointAmount" style="width:100%" data-options="label:'R PAY ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"> 
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
	            		$('#memberName').textbox('setValue', selNode.memberName);
	            		$('#memberEmail').textbox('setValue', selNode.memberEmail);
	            		$('#memberPhone').textbox('setValue', selNode.memberPhone);
	            	});
				}
			}]
		});
		
		$('#memberName').textbox({
			editable : false,
		});
		
		$('#memberEmail').textbox({
			editable : false,
		});
		
		$('#memberPhone').textbox({
			editable : false,
		});
		
		$('#pointAmount').textbox();

		$('#regType').combobox({
			showItemIcon: true,
            readonly : true,
            panelHeight: 'auto',
            label: '등록 유형',
            multiple:false,
            required:true
		});
		$('#regType').combobox('select',"A");
	}
	setViewInit();
</script>