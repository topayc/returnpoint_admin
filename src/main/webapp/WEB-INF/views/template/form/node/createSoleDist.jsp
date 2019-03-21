<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createSoleDistForm"  enctype="multipart/form-data"  method="post" action = "<c:url value='/createAbmsProject'/>">
			<div style="margin-bottom:30px"><input id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="soleDistEmail"  name="soleDistEmail" style="width:100%" data-options="label:'총판 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="soleDistName"  name="soleDistName" style="width:100%" data-options="label:'총판 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="soleDistAddress"  name="soleDistAddress" style="width:100%" data-options="label:'총판 주소',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="soleDistTel"  name="soleDistTel" style="width:100%" data-options="label:'총판 전화번호',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="soleDistPhone"  name="soleDistPhone" style="width:100%" data-options="label:'총판 모바일',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"> 
				<select class = "easyui-combobox"  id ="soleDistStatus" name="soleDistStatus"   style="width:100%" data-options="label:'총판 상태',labelWidth :140,labelAlign:'left',labelPosition : 'left'"> 
					<c:forEach var="nodeStatus"  items="${nodeStatuses}" varStatus="status">
						   <c:if test = "${ nodeStatus.useInAdmin == 'Y' }">
							  <option value='${nodeStatus.key}' >${nodeStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
	
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
	            		$("#soleDistEmail").textbox('setValue', selNode.memberEmail);
	            		$("#soleDistName").textbox('setValue', selNode.memberName);
	            	});
				}
			}]
		});

		$('#soleDistEmail').textbox({
			label : roundLabel("총판 이메일"),
			prompt: '총판 이메일 ', 
			editable : false,
		});
		
		$('#soleDistName').textbox({
			label : roundLabel("총판명"),
			prompt: '총판 상호 및 기타 이름', 
		});
		$('#soleDistAddress').textbox({
			label : roundLabel("총판 주소"),
			prompt: '총판 주소', 
		});
		$('#soleDistTel').textbox({
			label : roundLabel("전화 번호"),
			prompt: '총판 전화 번호', 
		});
		$('#soleDistPhone').textbox({
			label : roundLabel("총판 핸드폰"),
			prompt: '총판 핸드폰 번호', 
		});
		$('#soleDistStatus').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
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