var columns = [[
	
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
	    {field:'paymentTransactionNo',width:20,align:'center',title : '등록 번호',hidden:false},
	    {field:'memberName',width:25,align:'center',title : '결제 회원 이름'},
	    {field:'nodeType',width:20,align:'center',title : '노드',formatter : nodeTypeFormatter ,hidden :  true},
	    {field:'memberEmail',width:40,align:'center',title : '회원 이메일',hidden : true},
	    {field:'memberPhone',width:30,align:'center',title : '결제 회원 핸드폰'},
	    {field:'nodeNo',width:20,align:'center',title : '대상 번호', hidden : true},
	    {field:'nodeName',width:30,align:'center',title : '노드 이름',hidden : true},
	    {field:'nodeEmail',width:45,align:'left',title : '노드 이메일',hidden : true},
	    {field:'memberNo',width:20,align:'center',title : '회원 번호', hidden : true},
	    {field:'affiliateNo',width:30,align:'center',title : '협력업체 번호', hidden : true},
	    {field:'affiliateName',width:30,align:'center',title : '협력업체 이름', hidden : false},
	    {field:'affiliateSerial',width:30,align:'center',title : 'T-ID'},
	    {field:'paymentApprovalNumber',width:40,align:'center',title : '승인 번호'},
	    {field:'paymentApprovalAmount',width:25,align:'center',title : '승인 금액', formatter : numberBlueFormatter},
	    {field:'paymentApprovalStatus',width:25,align:'center',title : '승인 상태', formatter : PaymentApprovalStatusFormatter},
	    {field:'paymentTransactionType',width:20,align:'center',title : '등록 형태', formatter : paymentTransactionRegistFormatter},
	    {field:'pointBackStatus',width:25,align:'center',title : '적립 상태', formatter : pointBackStatusFormatter},
	    {field:'paymentApprovalDateTime',width:35,align:'center',title : '승인 일자', formatter : dateFormatter},
	    {field:'createTime',width:35,align:'center',title : '등록일',formatter : dateFormatter,hidden : false},
	    {field:'regAdminNo',width:15,align:'center',title : '등록자', formatter : registAdminFormatter},
	    {field:'updateTime',width:40,align:'center',title : '수정일',formatter : dateFormatter, hidden : true}
	 ]]
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
	$('#searchKeyword').textbox({ prompt : "검색할 단어를 입력해주세요" });
	
	
	$('#searchPaymentApprovalStatus').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});

	$('#searchPaymentTransactionType').combobox({
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
		 prompt : "검색 시작 일자",
	    showSeconds: false,
	    labelPosition: 'top',
	});
	
	/* 검색 종료일 갤린더 박스  초기화*/
	$('#searchDateEnd').datetimebox({
		 prompt : "검색 종료 일자",
		showSeconds: false,
	    labelPosition: 'top',
	});
	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
		onClick : function(){
			var param = makeSearchParam();
			returnp.api.call("getPaymentTransactionCommands", param, function(res){
				console.log("getPaymentTransactions");
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
			//$('#searchForm').form('clear');
			$('#searchPaymentApprovalStatus').combobox('select', 0);
			$('#paymentType').combobox('select', 0);
			$('#keywordType').combobox('select', 0);
			$('#searchKeyword').textbox('clear');
			$('#searchDateStart').datetimebox('clear');
			$('#searchDateEnd').datetimebox('clear');
		}
	});
	
	
	var editData = [
		{key:"1",value:"입금(결제) 확인중"},
		{key:"2",value:"입금(결제) 확인 완료"},
		{key:"3",value:"입금(결제) 취소 확인중"},
		{key:"4",value:"입금(결제) 취소 완료"}
	]
	
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
		onRowContextMenu : function(e, index, row){
			e.preventDefault();
		  	$(this).datagrid("selectRow", index);
		  	var cmenu = $('<div/>').appendTo('body');
		  	cmenu.menu({
		  		onClick : function(item){
		  			switch(item.action){
		  			case "create":
		  				break;
		  			case "modify":
		  				loadPaymentTransactionModifyForm();
		  				break;
		  			case "cancel":
		  				cancelPaymentTransaction();
		  				break;	
		  			case "remove":
		  				removePaymentTransaction();
		  				break;
		  			case "more_detail":
		  				var node = $('#node_list').datagrid('getSelected');
		  				if (!node) {
		  					 $.messager.alert('알림','세부 내역을 확인하실 항목을 선택해주세요');
		  					 return;
		  				}
		  				var title = node.paymentApprovalStatus == "1" ? '해당 결제의  G POINT 적립 내역' : node.paymentApprovalStatus == "2" ? "해당 결제의 G-POINT 적립 취소 내역" : "";
		  				loadPaymentPointbackRecord(
		  						title, 
		  					{paymentTransactionNo : node.paymentTransactionNo}
		  				);
		  				break;
		  			case "re_pointback":
		  				reAccumulate();
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var detailTitile1 = row.paymentApprovalStatus == "1" ? '해당 결제의  G POINT 적립 내역' : row.paymentApprovalStatus == "2" ? "해당 결제의 G-POINT 적립 취소 내역" : "";
		  	var detailTitile2 = row.paymentApprovalStatus == "0" ? '결제 승인 취소' : "";
		  	var menus = [/*'결제 내역 수정' , */detailTitile1 ,'결제 승인 취소 ' , /*'결제 내역 삭제' , */ '재적립 요청(해당내역 삭제 및 취소후 재적립 진행'];
		  	var icons = [/*'icon-edit' , */'icon-more', 'icon-clear' , /*'icon-remove' ,*/ 'icon-redo'];
		  	var actions = [/*'modify' , */'more_detail', 'cancel' , /*'remove' , */'re_pointback' ];
		  	
		  /*	if (row.pointBackStatus == '1' || row.pointBackStatus == '2') {
		  		menus.push("해당 결제 내역 포인트 적립 재 요청");
		  		icons.push("icon-redo");
		  		actions.push("re_pointback")
		  	}*/
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text: /*'[' + row.memberEmail + '] ' +*/ menus[i],
		  			action: actions[i],
		  			iconCls: icons[i]
		  		});
		  	}
		  	cmenu.menu('show', {
		  		left:e.pageX,
		  		top:e.pageY
		  	});
		},
	    columns: columns
	});
}

$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
            var ed = $(this).datagrid('getEditor', param);
            if (ed){
                if ($(ed.target).hasClass('textbox-f')){
                    $(ed.target).textbox('textbox').focus();
                } else {
                    $(ed.target).focus();
                }
            }
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	},
    enableCellEditing: function(jq){
        return jq.each(function(){
            var dg = $(this);
            var opts = dg.datagrid('options');
            opts.oldOnClickCell = opts.onClickCell;
            opts.onClickCell = function(index, field){
               if (field == 'paymentStatus') {
            		if (opts.editIndex != undefined){
                        if (dg.datagrid('validateRow', opts.editIndex)){
                            dg.datagrid('endEdit', opts.editIndex);
                            opts.editIndex = undefined;
                        } else {
                            return;
                        }
                    }
                    dg.datagrid('selectRow', index).datagrid('editCell', {
                        index: index,
                        field: field
                    });
                    opts.editIndex = index;
                    opts.oldOnClickCell.call(this, index, field);
               }else {
            	   dg.datagrid('endEdit', opts.editIndex);
            	   dg.datagrid('selectRow', index);
               }
            
            }
        });
    }
});

function setListPager(){
	var pager = $('#node_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#node_list').datagrid('unselectAll');
            	$('#node_list').datagrid('uncheckAll');
                loadPaymentTransactionCreateForm();
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
            	loadPaymentTransactionModifyForm();
            }
        },{
            iconCls:'icon-clear',
            handler:function(){
            	cancelPaymentTransaction();
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removePaymentTransaction();
            }
        },{
            iconCls:'icon-more',
            handler:function(){
            	var node = $('#node_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','자세히 보실 항목을  선택해주세요');
            		 return;
            	}
            	loadPaymentPointbackRecord(
	  				"결제 내역에 따른 Green 포인트 지급 현황", 
	  				{paymentTransactionNo : node.paymentTransactionNo}
	  			);
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
	
	var param = $('#searchForm').serializeObject();
	var opts = $('#node_list').datagrid('options');
	var total = $('#node_list').datagrid('getData').total;
	
	$.extend(param, {
		searchNodeType : 10,
		pagination : opts.pagination,
		pageSize : opts.pageSize,
		page : opts.pageNumber,
		total : total,
		offset : (opts.pageNumber-1) * opts.pageSize
	});
	return param;
}

function loadPaymentTransactionCreateForm(){
	var data = {
    		targetElem : "#dlgForm",
        	title :"POS 결제 내역 생성",
        	queryOptions : {
    			action : "create",
        	}
      }
	//console.log("loadPaymentTransactionCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/paymentTransaction/form/createForm?" + queryParam,
			function(response, status, xhr) {
			//console.log("오픈할 DIV : " + data.targetElem);	
			
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
						createPaymentTransaction(data);
					} },
					{ text:'취소', handler:function(){
						//console.log("닫을 DIV : " + data.targetElem);	
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
			
		});
}

function loadPaymentTransactionModifyForm(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 항목을 내역을 선택해주세요');
		 return;
	}
	var data = {
			targetElem : "#dlgForm",
    		title : "POS 결제 내역 수정",
    		queryOptions : {
    			action : "modify",
    			paymentTransactionNo : node.paymentTransactionNo
        	}
     }
	
	//console.log("loadPaymentTransactionModifyForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/paymentTransaction/form/createForm?" + queryParam,
			function(response, status, xhr) {
			//console.log("오픈할 DIV : " + data.targetElem);	
			
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
						updatePaymentTransaction(data);
					} },
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
			returnp.api.call('getPaymentTransaction', {paymentTransactionNo : data.queryOptions.paymentTransactionNo}, function(res){
				if (res.resultCode  == "100") {
					//console.log("getVanPaymentTransaction 정보 로딩 : ");
					//console.log(res);
					$('#createPaymentTransactionForm').form('load',res.data);
					$('#memberNo').textbox({disabled : true });
					$('#paymentTransactionType').combobox('select',res.data.regType);
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
			
		});
}

function makeFormData(){
	var param = $('#createPaymentTransactionForm').serializeObject();
	return param;
}

function updatePaymentTransaction(data){
	var param =makeFormData();
	//console.log("updatePaymentTransaction");
	//console.log(param);
	
	var valid = true;
	for (var prop in param){
		if (param.hasOwnProperty(prop)) {
			if (param[prop] == '') {
				valid = false;
				break;
			}
		}
	}
	
	if (!valid) {
		$.messager.alert('알림', '입력 항목이 모두 입력되지 않았습니다');
		return;
	}	
	returnp.api.call("updatePaymentTransaction", param, function(res){
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

function createPaymentTransaction(data){
	var param =makeFormData();
	console.log("createPaymentTransaction");
	var valid = true;
	for (var prop in param){
		if (param.hasOwnProperty(prop)) {
			if (param[prop] == '' || !param[prop]) {
				valid = false;
				break;
			}
		}
	}
	
	if (!valid) {
		$.messager.alert('알림', '입력 항목이 모두 입력되지 않았습니다');
		return;
	}	
	
	returnp.api.call("createNewPaymentTransaction", param, function(res){
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

 function reAccumulate(){
	 var node = $('#node_list').datagrid('getSelected');
	 if (!node) {
		 $.messager.alert('알림','포인트 백 작업을 진행할 항목을 선택해주세요');
		 return;
	 }

	 if (node.pointBackStatus == "7" || node.pointBackStatus == "8"){
		 $.messager.alert('알림','문제가 발생하여 적립이 중지되었거나 취소 중지가 된 내역으로 재 요청을 할 수 없습니다');
		 return;
	 } else if (node.pointBackStatus == "3") {
		 $.messager.alert('알림','해당 결제는 정상적인 적립 처리가 완료된 상태입니다.재 요청을 할 수 없습니다');
		 return;
	 }else if (node.pointBackStatus == "4" || node.pointBackStatus == "5" ||node.pointBackStatus == "6"){
		 $.messager.alert('알림','해당 내역인 이미 취소되었나 취소중인 내역으로 재 적립 요청을 할 수 없습니다');
		 return;
	 }
	 else {
		 $.messager.confirm(
			 '적립 재요청', 
			 '해당 내역에 대하여 재적립을 수행합니다',
			 function(r){
				 if (r) {
					 returnp.api.call(
						"reaccumulatePaymentTransaction", 
						 {paymentTransactionNo: node.paymentTransactionNo, reaccmulatetType : "2"}, 
						 function(res){
							$.messager.alert('알림', res.message);
						}
					 );
				 }else {
					 alert(node.paymentTransactionNo);
				 }
			 }
		 );
	 }
 }
 
function cancelPaymentTransaction(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','취소하실 거래 내역을 선택해주세요');
		 return;
	}
	if (node.pointBackStatus == "4" || node.pointBackStatus == "5"  || 
			node.pointBackStatus == "6" || node.pointBackStatus == "7" || 
			node.pointBackStatus == "8"){
		$.messager.alert('알림','해당 내역은 취소할 수 없는 내역입니다');
		return;
	}
	$.messager.confirm(
		'알림', 
		"해당 거래 내역이 취소됩니다. </br>취소 시 관련 계층의 G Point가  취소된 포인트만큼 차감됩니다. 진행하시겠습니까?",
		function(r){
			if (r) {
				var param = { paymentTransactionNo : node.paymentTransactionNo }
				returnp.api.call("cancelPaymentTransaction", param, function(res){
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

function removePaymentTransaction(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        			paymentTransactionNo : node.paymentTransactionNo
        	}
        	returnp.api.call("deleteVanPaymentTransaction", param, function(res){
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

function realodPage(){
	$('#search_btn').click();
}

$(function(){
	$('#node_list').datagrid().datagrid('enableCellEditing');
	setListPager();
	realodPage();
})
