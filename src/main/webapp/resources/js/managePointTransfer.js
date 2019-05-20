	columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'pointTransferTransactionNo',width:100,align:'center',title : '이체 번호',hidden:false},
			    {field:'pointTransferer',width:100,align:'center',title : '송금자 No',hidden:true},
			    {field:'pointTransfererName',width:100,align:'center',title : '송금자'},
			    {field:'pointTransfererEmail',width:100,align:'center',title : '송금자 이메일 '},
			    {field:'pointReceiver',width:100,align:'center',title : '수취인 No',hidden:true},
			    {field:'pointTransferAmount',width:100,align:'center',title : '송금 포인트',formatter : numberBlueFormatter},
			    {field:'pointNode',width:100,align:'center',title : '포인트 노드', formatter : pointTransferNodeType},
			    {field:'pointType',width:100,align:'center',title : '포인트 타입', formatter : pointTypeFormatter},
			    {field:'pointTransferType',width:100,align:'center',title : '송금 유형', formatter : pointTransferType},
			    {field:'pointTransferStatus',width:100,align:'center',title : '송금 상태', formatter : pointTransferStatus},
			    {field:'pointReceiverName',width:100,align:'center',title : '수취인'},
			    {field:'createTime',width:100,align:'center',title : '송금일',formatter : dateFormatter},
			    {field:'updateTime',width:100,align:'center',title : '수정일',formatter : dateFormatter,hidden:true}

			    ]];
initView();

/**
 * 뷰 초기화 
 * @returns
 */
function initView(){
	/* 레이아웃 초기화*/
	$('.easyui-layout').layout();
	
	/* 패널   초기화*/
	$('.easyui-panel').panel({ border: false });
	
	/* 폼 초기화*/
	$('#searchForm').form();
	
    /* 검색어 입력 박스 초기화 */	
    $('#searchKeyword').textbox({ 
        prompt : "검색할 단어를 입력해주세요" ,
        inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
            keyup:function(e){
                if(e.keyCode==13)
                    realodPage();
            }
        })
    });
	
	/* 노드 타입 셀렉트 박스  초기화*/
	$('#searchPointTransferType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	

	/* 검색 시작일 갤린더 박스  초기화*/
	$('#searchDateStart').datetimebox({
	    showSeconds: true,
	    prompt : "검색 시작 일자",
	    labelPosition: 'top',
	    formatter : function(date){
	    	var year = date.getFullYear();  
	    	var month = date.getMonth()+1;  
	    	var d = date.getDate();  
	    	var hours = date.getHours();  
	    	var minutes  = date.getMinutes();  
	    	var seconds = date.getSeconds();  
	    	return  year + "-" + formatDate(month) + "-" + formatDate(d) + " "+ formatDate(hours) + ":" + formatDate(minutes) + ":" + formatDate(seconds);
	    }
	});
	
	/* 검색 종료일 갤린더 박스  초기화*/
	$('#searchDateEnd').datetimebox({
	    showSeconds: true,
	    prompt : "검색 종료 일자",
	    labelPosition: 'top',
	    formatter : function(date){
	    	var year = date.getFullYear();  
	    	var month = date.getMonth()+1;  
	    	var d = date.getDate();  
	    	var hours = date.getHours();  
	    	var minutes  = date.getMinutes();  
	    	var seconds = date.getSeconds();  
	    	return  year + "-" + formatDate(month) + "-" + formatDate(d) + " "+ formatDate(hours) + ":" + formatDate(minutes) + ":" + formatDate(seconds);
	    }
	});
	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
		onClick : function(){
			var param = makeSearchParam();
			returnp.api.call("getPointTransferTransactions", param, function(res){
				console.log("DATA");
				console.log(res);
				if (res.resultCode == "100") {
					$('#node_list').datagrid({
						data : res,
						title : '[검색 결과] ' + res.total + " 개의 결과가 검색되었습니다",
					});
					setListPager();
				}else {
					$.messager.alert('오류 발생', message);
				}
			});
		},
		iconCls:'icon-search'
	});
	
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
			$('#searchForm').form('clear');
			$('#searchPointTransferType').combobox('select', 0);
			$('#searchKeyword').textbox('clear');
			$('#searchDateStart').datetimebox('clear');
			$('#searchDateEnd').datetimebox('clear');
		}
	});
	
	/* 노드 데이타그리드   초기화*/
	$('#node_list').datagrid({
		title : '[검색 결과]',
		singleSelect:true,
		collapsible:false,
		//autoRowHeight: false,
		fitColumns:true,
		selectOnCheck : true,
		checkOnSelect : true,
		border:false,
		rownumbers : true,
		pagination: true,
		pagePosition : "top",
		pageSize : returnpCommon.appInfo.gridPageSize,
		onSelect : function(){},
		onLoadSuccess : function(){
			//$(this).datagrid('freezeRow',0).datagrid('freezeRow',1);
		},	
		/*onRowContextMenu : function(e, index, row){
			e.preventDefault();
		  	$(this).datagrid("selectRow", index);
		  	var cmenu = $('<div/>').appendTo('body');
		  	cmenu.menu({
		  		onClick : function(item){
		  			switch(item.action){
		  			case "modify":
		                  loadPointTransferModifyForm();
		  				break;
		  			case "remove":
		  				removePointTransfer();
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = [  '이체 내역 수정', '이체 내역 삭제'];
		  	var icons = ['icon-edit','icon-remove'];
		  	var actions = ['modify','remove'];
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text: '[' + row.memberEmail + '] ' + menus[i],
		  			action: actions[i],
		  			iconCls: icons[i]
		  		});
		  	}
		  	cmenu.menu('show', {
		  		left:e.pageX,
		  		top:e.pageY
		  	});
		},*/
	    columns:columns,
	    
	});
	setListPager();
}

function setListPager(){
	var pager = $('#node_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#node_list').datagrid('unselectAll');
            	$('#node_list').datagrid('uncheckAll');
            	loadMemberCreateForm();
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
            	loadMemberUpdateForm();
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removeMember();
            }
        },{
            iconCls:'icon-more',
            handler:function(){
            	var node = $('#node_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','자세히 보실 항목을  선택해주세요');
            		 return;
            	}
            }
        }],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
        onSelectPage:function(page,rows){        	
        	var opts = $('#node_list').datagrid('options');
        	opts.pageSize=rows;
        	opts.pageNumber = page;
        	realodPage();
    	}
    }); 
}



/**
 * 검색 실행시 필요한 쿼리 데이타 구성 
 * @returns
 */
function makeSearchParam(){
	var param = {
		pageSize : 10,
		page : 0,
		serachDateStart :  $('#searchDateStart').datetimebox('getValue'),
		searchDateEnd :  $('#searchDateEnd').datetimebox('getValue'),
		searchPointTransferType :  $('input[name=searchPointTransferType]').val(),
		searchKeyword :  $('input[name=searchKeyword]').val()
	};
	
	var opts = $('#node_list').datagrid('options');
	var total = $('#node_list').datagrid('getData').total;
	
	$.extend(param, {
		pagination : opts.pagination,
		pageSize : opts.pageSize,
		page : opts.pageNumber,
		total : total,
		offset : (opts.pageNumber-1) * opts.pageSize
	});
	//console.log(JSON.stringify(param));
	return param;
}

function loadPointTransferCreateForm(){
	
	var data = {
	        targetElem : "#dlgForm",
	        title : "포인트 이체(선물) 생성",
	        queryOptions : {
	          	action : "create",
	         }
	     }
	  	
		var queryParam = $.param(data.queryOptions);
		$(data.targetElem).load("/api/pointTransferTransaction/form/createForm?" + queryParam,
			function(response, status, xhr) {
				
				$(data.targetElem).dialog({
					width:600,
				    modal : true,
				    cache : false,
				    closable : true,
				    border : 'thick',
				    shadow : true,
				    collapsible : false,
				    minimizable : false,
				    maximizable: false,
				    title : "&nbsp; " + data.title,
				    shadow : false,	
					buttons:[
						{ text:'확인', iconCls:'icon-ok', handler:function(){
							createPointTransfer(data);
						} },
						{ text:'취소', handler:function(){
							$(data.targetElem).removeAttr('style');
							$(data.targetElem).dialog('close');
						}
					}]
			});
			$(data.targetElem).dialog('center');
		});
}

function loadPointTransferModifyForm(){
	
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 노드를 선택해주세요');
		 return;
	}
	
	var data = {
		targetElem : "#dlgForm",
        title : ' 협력 업체 수정',
        queryOptions : {
        	action : "modify",
        	affiliateNo : node.affiliateNo,
        	memberAddressNo : node.memberAddressNo
        }
     }
	
	//console.log("loadPointTransferModifyForm");
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/affiliate/form/createForm?" + queryParam,
		function(response, status, xhr) {
		console.log("오픈할 DIV : " + data.targetElem);	
		
				$(data.targetElem).dialog({
					width:650,
					cache : false,
				    modal : true,
				    closable : true,
				    border : 'thick',
				    shadow : true,
				    collapsible : false,
				    minimizable : false,
				    maximizable: false,
				    title : "&nbsp; " + data.title,
				    shadow : false,	
					buttons:[
						{ text:'확인', iconCls:'icon-ok', handler:function(){
							updatePointTransfer(data);
						} },
						{ text:'취소', handler:function(){
							//console.log("닫을 DIV : " + data.targetElem);	
							$(data.targetElem).dialog('close');
							$(data.targetElem).removeAttr('style');
						}
					}]
				});
				$(data.targetElem).dialog('center');
				returnp.api.call('getAffiliate', {
					affiliateNo : data.queryOptions.affiliateNo
					,memberAddressNo: data.queryOptions.memberAddressNo}, function(res){
						console.log(res.data);
					if (res.resultCode  == "100") {		
						
						$('#createAffiliateForm').form('load',res.data);
						$('#memberNo').textbox({disabled : true });
						$('#regType').combobox('select',res.data.regType);
						$("#affiliateRoad").textbox('setValue', res.data.zipNo + " " + res.data.roadFullAddr+ " " + res.data.addrDetail);
						modifyModeCallback(res.data.zipNo + " " + res.data.jibunAddr + " " + res.data.addrDetail,res.data.lat,res.data.lng);
						
					}else {
						$.messager.alert('오류 발생', res.message);
					}
				});
			
		});
}


function makeFormData(){
	var param = $("#createPointTransfer").serializeObject();
	return param;
}

function updatePointTransfer(data){
	var param =makeFormData();
	//console.log(param);
	
	var valid = true;
	for (var prop in param){
		if (param.hasOwnProperty(prop)) {
			if (param[prop] == '' || param[prop].length < 1) {
				valid = false;
				break;
			}
		}
	}
	
	returnp.api.call("updatePointTransfer", param, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			$(data.targetElem).dialog('close');
			$(data.targetElem).removeAttr('style');
			realodPage();
			
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function createPointTransfer(data){	
	var param =makeFormData();
	console.log(param);
	
	var valid = true;
	for (var prop in param){
		if (param.hasOwnProperty(prop)) {
			if (param[prop] == '' || param[prop].length < 1) {
				valid = false;
				break;
			}
		}
	}
	
	if (!valid) {
		$.messager.alert('알림', '입력 항목이 모두 입력되지 않았습니다');
		return;
	}	
	
	
	if (param.pointTransferer == param.pointReceiver) {
		$.messager.alert('알림', '자기 자신에게 포인트를 송금할 수 없습니다');
		return;
	}
	
	if (!$.isNumeric(param.pointTransferAmount) ) {
		$.messager.alert('알림', "보내실 포인트는 숫자만 입력가능합니다");
		return;
	}
	var gPointMovingMinLimit = parseInt($("#gPointMovingMinLimit").val());
	var gPointMovingMaxLimit = parseInt($("#gPointMovingMaxLimit").val());
	var rPointMovingMinLimit = parseInt($("#rPointMovingMinLimit").val());
	var rPointMovingMaxLimit = parseInt($("#rPointMovingMaxLimit").val());
	
	var result = true;
	var pointTransferAmount = parseInt($("#pointTransferAmount").val());
	if (param.pointType == "1") {
		if (pointTransferAmount < gPointMovingMinLimit || pointTransferAmount >gPointMovingMinLimit ) {
			result = false;
			$.messager.alert(
				'알림', 
				"* Green Point는 최저" +  numberBoldFormatter(gPointMovingMinLimit)  + " 이상  최고 " + numberBoldFormatter(gPointMovingMaxLimit)   + " 까지 송금 가능합니다");
		}
		
	}else if (param.pointType  == "2"){
		if (pointTransferAmount < rPointMovingMinLimit || pointTransferAmount >rPointMovingMaxLimit ) {
			result = false;
			$.messager.alert(
					'알림', 
					"* Red Point는 최저" +  numberBoldFormatter(rPointMovingMinLimit)  + " 이상  최고 " + numberBoldFormatter(rPointMovingMaxLimit)   + " 까지 송금 가능합니다");
		}
	}
	
	if (result) {
		returnp.api.call("createPointTransferTransaction", param, function(res){
			if (res.resultCode  == "100") {
				$.messager.alert('알림', res.message);
				$(data.targetElem).dialog('close');
				$(data.targetElem).removeAttr('style');
				realodPage();
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		});
	}
}

function removePointTransfer(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        		affiliateNo : node.affiliateNo
        	}
        	returnp.api.call("deletePointTransfer", param, function(res){
        		if (res.resultCode  == "100") {
        			$.messager.alert('알림', res.message);
        			realodPage();
        		}else {
        			//console.log("[오류]");
        			//console.log(res);
        			$.messager.alert('오류 발생', res.message);
        		}
        	});
        }
    });
}




function modifyModeCallback(jibunAddr,lat,lng){	
	loadMap(jibunAddr,lat,lng);
}

function realodPage(){
	$('#search_btn').click();
}

$(document).ready(function(){
	$('#search_btn').click();
});
