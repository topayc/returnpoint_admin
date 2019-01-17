<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >	
	   <input type = "hidden"  id ="gPointMovingMinLimit"  name="gPointMovingMinLimit"  value = "${policy.gPointMovingMinLimit}" />
	   <input type = "hidden"  id ="gPointMovingMaxLimit"  name="gPointMovingMaxLimit" value = "${policy.gPointMovingMaxLimit}" />
	   <input type = "hidden"  id ="rPointMovingMinLimit"  name="rPointMovingMinLimit" value = "${policy.rPointMovingMinLimit}"/>
	   <input type = "hidden"  id ="rPointMovingMaxLimit"  name="rPointMovingMaxLimit" value = "${policy.rPointMovingMaxLimit}"/>
	   
	   <form id="createPointTransfer"  enctype="multipart/form-data" name = "createPointTransfer" method="post"">
			<div style="margin-bottom:20px"><input id ="pointTransferer"  name="pointTransferer" style="width:100%" data-options="label:'보낼 회원 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="pointTransfererName"  name="pointTransfererName" style="width:100%" data-options="label:'보낼 회원 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="pointTransfererEmail"  name="pointTransfererEmail" style="width:100%" data-options="label:'보낼 회원 메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><span id = "pointTransferMinMax"></span> </div>
			<div style="margin-bottom:20px"> 
				<select class = "easyui-combobox"  id ="pointType" name="pointType"   style="width:100%" data-options="label:'보낼 포인트',labelWidth :140,labelAlign:'left',abelPosition : 'left'" style="width:100%;height:30px"> 
					<c:forEach var="pointType"  items="${pointTypes}" varStatus="status">
						    <c:if test = "${ pointType.useInAdmin == 'Y' }">
							  <option value='${pointType.key}' >${pointType.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			
			<div style="margin-bottom:20px" id = "pointNodeContainer"> 
				<select class = "easyui-combobox"  id ="pointNode" name="pointNode"   style="width:100%" data-options="label:'보낼 포인트 종류',labelWidth :140,labelAlign:'left',abelPosition : 'left'" style="width:100%;height:30px"> 
				</select> 
			</div>
			
			<div style="margin-bottom:20px"><input id ="curPointAmount"  name="curPointAmount" style="width:100%" data-options="label:'현재 노드 보유 포인트 ',labelWidth :140,labelPosition : 'left'"> </div>
				<div style="margin-bottom:20px"> 
				<select class = "easyui-combobox"  id ="pointTransferType" name="pointTransferType"   style="width:100%" data-options="label:'보내는 유형',labelWidth :140,labelAlign:'left',abelPosition : 'left'" style="width:100%;height:30px"> 
					<c:forEach var="pointTransferType"  items="${pointTransferTypeList}" varStatus="status">
						    <c:if test = "${ pointTransferType.useInAdmin == 'Y' }">
							  <option value='${pointTransferType.key}' >${pointTransferType.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			
			<div style="margin-bottom:20px"><input id ="pointTransferAmount"  name="pointTransferAmount" style="width:100%" data-options="label:'보낼 금액 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="pointReceiver"  name="pointReceiver" style="width:100%" data-options="label:'받는 회원 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="pointReceiverName"  name="pointReceiverName" style="width:100%" data-options="label:'받는 회원 이름',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"><input id ="pointReceiverEmail"  name="pointReceiverEmail" style="width:100%" data-options="label:'받는 회원 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
				<div style="margin-bottom:20px"> 
				<select class = "easyui-combobox"  id ="pointTransferStatus" name="pointTransferStatus"   style="width:100%" data-options="label:'상태',labelWidth :140,labelAlign:'left',abelPosition : 'left'" style="width:100%;height:30px"> 
					<c:forEach var="pointTransferStatus"  items="${pointTransferStatusList}" varStatus="status">
						    <c:if test = "${ pointTransferStatus.useInAdmin == 'Y' }">
							  <option value='${pointTransferStatus.key}' >${pointTransferStatus.value} </option>
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
		$('#pointTransferer').textbox({
			prompt: '보낼 회원 검색 ',
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
	            		$("#pointTransfererName").textbox('setValue', selNode.memberName);
	            		$("#pointTransfererEmail").textbox('setValue', selNode.memberEmail);
	                  	var poinTransferer = $("#pointTransferer").val().trim();
						var pointType =  	$('input[name=pointType]').val().trim();
	                  	if (poinTransferer !='' ) {
	                	 	if (pointType == "1") {
	                    		returnp.api.call("getGreenPoints", {memberNo : poinTransferer}, function(res){
	                    			console.log(res);
	                    			if (res.resultCode  == "100") {
	                    				$('#pointNode').combobox("clear");
	                    				var data = [];
	                    				
	                    				for (var i = 0; i < res.rows.length ; i ++){
	                    					data.push({ nodeType: res.rows[i].nodeType , nodeTypeName : nodeTypeFormatter( null,res.rows[i],  null) + " : " + res.rows[i].pointAmount});
	                    				}
										data.push()
										$('#pointNode').combobox('loadData', data);
										$('#pointNode').combobox('select', '1');
	                    			}else {
	                    				$.messager.alert('오류 발생', res.message);
	                    			}
	                    		});
	                    	}else if (pointType == "2"){
	                    		$('#pointNodeContainer').hide();
	                    		returnp.api.call("getRedPoint", {memberNo : poinTransferer}, function(res){
	                    			console.log(res);
	                    			if (res.resultCode  == "100") {
	                    				for (var i = 0; i < res.rows.length ; i ++){
	                    				}
	                    					
	                    			}else {
	                    			}
	                    		});
	                    	}            		
	                	}
	            	});
				}
			}]
		});
		
		$('#pointReceiver').textbox({
			prompt: '받을 회원검색 ',
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
	            		$("#pointReceiverName").textbox('setValue', selNode.memberName);
	            		$("#pointReceiverEmail").textbox('setValue', selNode.memberEmail);
	            	});
				}
			}]
		});
		
		$('#pointTransfererName').textbox({
			prompt: '보낼 사람 이름', 
			editable : false,
		});
		$('#pointTransfererEmail').textbox({
			prompt: '보낼 사람 이메일', 
			editable : false,
		});
		
		$('#pointType').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            multiple:false,
            required:true,
            onSelect : function(record){
            	var text = ""; 
            	if (record.value == "1") {
            		text = "* Green Point는 최저 " +  numberBoldFormatter($('#gPointMovingMinLimit').val()) + " 이상 "  + " 최고 " + numberBoldFormatter($('#gPointMovingMaxLimit').val()) + " 까지 송금 가능합니다";
            		$('#pointNodeContainer').show();
            	}else if (record.value == "2"){
            		text = "* Red Point는 최저 " +  numberBoldFormatter($('#rPointMovingMinLimit').val()) + " 이상 "  + " 최고 " + numberBoldFormatter($('#rPointMovingMaxLimit').val()) + " 까지 송금 가능합니다";
            		$('#pointNodeContainer').hide();
            	}
            	$('#pointTransferMinMax').html(text);
            	
            	var poinTransferer = $("#pointTransferer").val().trim();
            	if (poinTransferer !='' ) {
            	 	if (record.value == "1") {
                		returnp.api.call("getGreenPoints", {memberNo : poinTransferer}, function(res){
                			console.log(res);
                			if (res.resultCode  == "100") {
                				for (var i = 0; i < res.rows.length ; i ++){
                					var point = res.rows[i];
                				}
                					
                			}else {
                				$.messager.alert('오류 발생', res.message);
                			}
                		});
                	}else if (record.value == "2"){
                		 returnp.api.call("getRedPointCommand", {memberNo : poinTransferer}, function(res){
                			console.log(res);
                			if (res.resultCode  == "100") {
                				$('#curPointAmount').textbox('setValue', res.data.pointAmount);
                			}else {	
                			}
                		});
                	}            		
            	}
            }
		});

		$('#pointTransferType').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});
		$('#pointNode').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            multiple:false,
            required:true,
            valueField: 'nodeType',
            textField: 'nodeTypeName',
            onSelect : function(record){
            	$('#curPointAmount').textbox('setValue', record.nodeTypeName.split(":")[1].trim());
            }
		});

		$('#curPointAmount').textbox({
			editable : false,
		});
		
		$('#pointTransferAmount').textbox({
			prompt: '보낼 포인트', 
		});

		
		$('#pointReceiverName').textbox({
			prompt: '받을 사람 이름', 
			editable : false,
		});
		$('#pointReceiverEmail').textbox({
			prompt: '받을 사랑 이메일', 
			editable : false,
		});
		
		$('#pointTransferStatus').combobox({
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
		$('#pointType').combobox('select',"1");
	}
	setViewInit();
</script>