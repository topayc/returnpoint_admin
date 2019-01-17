<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  style ="padding:5px;margin: 15px">
	<div style = "padding:3px;padding-top:1px;padding-bottom:5px" id = "pointConvertRequestFormContainer">
	   <form id="createPointConvertRequestForm"  enctype="multipart/form-data" name = "createPointConvertRequestForm" method="post" action = "<c:url value='/createAbmsProject'/>">
			<div style="margin-bottom:20px"><input id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="memberName"  name="memberName" style="width:100%" data-options="label:'회원 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="memberEmail"  name="memberEmail" style="width:100%" data-options="label:'회원 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"> 
				<select class = "easyui-combobox"  id ="regType" name="regType"   style="width:100%" data-options="label:'등록 타입',labelWidth :140,labelAlign:'left',abelPosition : 'left'" style="width:100%;height:30px"> 
					<c:forEach var="reigistType"  items="${registTypes}" varStatus="status">
						    <c:if test = "${ reigistType.useInAdmin == 'Y' }">
							  <option value='${reigistType.key}' >${reigistType.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>	
			<div style="margin-bottom:20px"><input id ="pointTransLimit"  name="pointTransLimit" style="width:100%" data-options="cls : 'c3',label:'포인트 최저 전환 금액 ',labelWidth :140,labelPosition : 'left'"> </div>
		</form>
	</div>
</div>
<script>
	var pointAction = "${action}";
	var pointConvertRequestNo = "${pointConvertRequestNo}";
	
	function drawPointRequestView(point){
		point.nodeTypeName = nodeTypeFormatter(null, point, null);
		var panel = $("<div></div>")
		.addClass("easyui-panel point-panel")
		.css({"margin-bottom" : "20px" ,"padding" : "10px 10px"})
		.attr("data-options" , "collapsible:true, tools:'#" + point.nodeType+ "_tool'")
		.attr("title" , "* " + point.nodeTypeName + " G 포인트");
	
	var innerPanel = $("<div></div>").css("margin-bottom", "10px");
	var pointLimitInput= $("<input/>")
		.addClass("easyui-textbox")
		.attr("id", point.nodeType + "_pointBalance")
		.attr("name","pointBalance")
		.css({'width' : "100%", "margin-top": "40px"})
		.attr("data-options", "label:'보유 포인트', readonly : true, labelWidth : 100, labelPosition: 'left'")
		.val(point.pointAmount)
	innerPanel.append(pointLimitInput);
	
	var innerPanel2 = $('<div></div>')
	var convertPointInput = $("<input/>")
		.addClass("easyui-textbox")	
		.attr("id", point.nodeType+ "_convertPointAmount")
		.attr("name",point.nodeType + "_convertPointAmount")
		.css({'width' : "100%", "margin-top": "40px"})
		.attr("data-options", "label:'전환할 포인트', labelWidth : 100, labelPosition: 'left'")
		.val(point.convertPointAmount  ?  point.convertPointAmount : "0")
		
	innerPanel2.append(convertPointInput);
	
	var innerPanel3 = $('<div></div>').css({"margin-top" : "10px"});
	var pointArr = ["100","1,000","10,000","100,000","1,000,000"]
	for (var j = 0; j < pointArr.length; j++){
		var pointselect = $("<a></a>")
			.addClass("easyui-linkbutton point c1")
			.css("margin-right" , "5px")
			.attr("point", pointArr[j])
			.attr("target" , point.nodeType + "_convertPointAmount")
			.attr("data-options", "width:'19%'")
			.bind("click", function(){
				var oldPoint = parseInt($("#" + $(this).attr("target")).textbox('getValue'));
				var plusPoint = getNumber($(this).attr("point"));
				$("#" + $(this).attr("target")).textbox({value : oldPoint + plusPoint})
			})
		pointselect.html(pointArr[j]);
		innerPanel3.append(pointselect);
	}
	
	var toolPanel = $("<div></div>").attr("id", point.nodeType + "_tool" );
	var toolSave = $('<a></a>')
		.addClass("icon-add")
		.css("margin-right" , "5px")
		.attr("nodeType", point.nodeType )
		.attr("memberNo", point.memberNo)
		.attr("greenPointNo", point.greenPointNo)
		.attr("nodeTypeName", point.nodeTypeName)
		.bind("click", function(){
			var memberNo = $(this).attr("memberNo")
			var greenPointNo = $(this).attr("greenPointNo");
			var nodeType = $(this).attr("nodeType")
			var nodeTypeName = $(this).attr("nodeTypeName");
			
			var pointTransLimit = parseInt($("#pointTransLimit").val());
			if (!$.isNumeric($("input[name=" + nodeType + "_convertPointAmount]").val()) ) {
				$.messager.alert('알림', "전환 포인트는 숫자만 입력가능합니다");
				return;
			}
			
			var convertPointAmount = parseInt($("input[name=" + nodeType + "_convertPointAmount]").val());
			console.log('convertPointAmount : ' + convertPointAmount);
		    if (convertPointAmount < pointTransLimit) {
				$.messager.alert('알림', "포인트 전환은 최저 " + pointTransLimit + " 이상 가능합니다");
				return;
			}
			
			var pointBalance = parseFloat($("#" + nodeType + "_pointBalance").val());
			if (pointBalance < convertPointAmount) {
				$.messager.alert('포인트 잔액 초과', "현재 해당 포인트 잔고는 " +  pointBalance + " 입니다. 확인 후 다시 시도해주세요");
				return;
			}
			var param = {
				memberNo : memberNo,
				greenPointNo : greenPointNo,
				nodeType : nodeType,
				requestNodeTypeName : nodeTypeName,
				convertPointAmount : convertPointAmount,
				regType : $('#regType').combobox('getValue')
			}
			returnp.api.call("createPointConvertRequest", param, function(res){
				if (res.resultCode  == "100") {
					//console.log("포인트 전환 요청 응답");
					//console.log(res);
					$.messager.alert('알림', res.message);
					$("#" + param.nodeType  + "_pointBalance").textbox({value : res.data.pointAmount});	
					$("#" + param.nodeType  + "_convertPointAmount").textbox({value : 0});	
					realodPage();
				}else {	
					$.messager.alert('오류 발생', res.message);
				}
			});
		});
		toolPanel.append(toolSave);
		panel.append(innerPanel);	
		panel.append(innerPanel2);	
		panel.append(innerPanel3);	
		panel.append(toolPanel);
		panel.show();
		$("#createPointConvertRequestForm").append(panel);
		$('.easyui-textbox').textbox();
		$('.easyui-panel').panel();
		$('.point-panel').panel('collapse');
		$('.point-panel').eq(0).panel('expand');
		$('.easyui-linkbutton').linkbutton();
	}
	
	function setViewInit(){
		$('.easyui-textbox').textbox();
		$('.easyui-panel').panel();
		
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
	            		$("#memberEmail").textbox('setValue', selNode.memberEmail);
	            		$("#memberName").textbox('setValue', selNode.memberName);
	            		
	            		returnp.api.call("getGreenPoints", {memberNo : selNode.memberNo}, function(res){
	            			if (res.resultCode  == "100") {
	            				//console.log("green point");
	            				//console.log(res);
	            				//console.log(res.rows.length);
	            				for (var i = 0; i < res.rows.length ; i ++){
	            					var point = res.rows[i];
	            					drawPointRequestView(point)
	            				}
	            					
	            			}else {
	            				$.messager.alert('오류 발생', res.message);
	            			}
	            		});
	            	});
					$("#dlgForm").dialog('doLayout');
					$("#dlgForm").dialog('center');
				}
			}]
		});
		
		$('#memberEmail').textbox({
			prompt: '회원 이메일 ',
			editable : false
		});
		
		$('#memberName').textbox({
			editable : false,
			prompt: '회원 이름 '
		});

		$('#pointTransLimit').textbox({
			editable : false,
			prompt: '회원 이름 ',
			value : "${policy.pointTransLimit}"
		});
		
		$('#regType').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});
		
		if (pointAction == "modify") {
			returnp.api.call("getPointConvertRequest", {pointConvertRequestNo : pointConvertRequestNo}, function(res){
				if (res.resultCode  == "100") {
					//console.log("getGreenPoint");
					//console.log(res)
					drawPointRequestView(res.data);
				
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
		}
	}
	setViewInit();
</script>