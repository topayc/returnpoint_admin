var columns = [[
	
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
	    {field:'vanPaymentTransactionNo',width:20,align:'center',title : '등록 번호',hidden:false},
	    {field:'memberNo',width:20,align:'center',title : '회원 번호', hidden: true},
	    {field:'memberName',width:25,align:'center',title : '회원 이름'},
	    {field:'memberEmail',width:40,align:'center',title : '회원 이메일'},
	    {field:'memberPhone',width:30,align:'center',title : '회원 핸드폰'},
	    {field:'affiliateNo',width:30,align:'center',title : '협력업체 번호', hidden : true},
	    {field:'affiliateSerial',width:35,align:'center',title : '협력업체 Serial'},
	    {field:'approvalPayAmount',width:25,align:'center',title : '승인 금액', formatter : numberBlueFormatter},
	    {field:'approvalNumber',width:25,align:'center',title : '승인 번호'},
	    {field:'vanPaymentStatus',width:30,align:'center',title : '승인 상태', formatter : vanPaymentStatusFormatter},
	    {field:'pointBackStatus',width:40,align:'center',title : 'G 포인트 지급 상태', formatter : pointBackStatusFormatter},
	    {field:'approvalDateTime',width:40,align:'center',title : '승인 일자', formatter : dateFormatter},
	    {field:'vanTransactionType',width:25,align:'center',title : '등록 형태', formatter : vanTransactionRegistFormatter},
	    {field:'regAdminNo',width:15,align:'center',title : '등록자', formatter : registAdminFormatter},
	    {field:'createTime',width:40,align:'center',title : '등록일',formatter : dateFormatter},
	    {field:'updateTime',width:40,align:'center',title : '수정일',formatter : dateFormatter}
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
	
	
	$('#searchVanPaymentStatus').combobox({
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
			returnp.api.call("getVanPaymentTransactions", param, function(res){
				//console.log(res);
				if (res.resultCode == "100") {
					$('#node_list').datagrid({
						data : res.rows,
						title : '[검색 결과] ' + res.rows.length + " 개의 결과가 검색되었습니다",
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
			$('#searchVanPaymentStatus').combobox('select', 0);
			//$('#paymentType').combobox('select', 0);
			//$('#keywordType').combobox('select', 0);
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
		  				loadVanPaymentTransactionModifyForm();
		  				break;
		  			case "cancel":
		  				cancelVanPaymentTransaction();
		  				break;	
		  			case "remove":
		  				removeVanPaymentTransaction();
		  				break;
		  			case "more_detail":
		  				var node = $('#node_list').datagrid('getSelected');
		  				if (!node) {
		  					 $.messager.alert('알림','세부 내역을 확인하실 항목을 선택해주세요');
		  					 return;
		  				}
		  				loadPaymentPointbackRecord(
		  					"결제 내역에 따른 Green 포인트 지급 현황", 
		  					{vanPaymentTransactionNo : node.vanPaymentTransactionNo}
		  				);
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = ['결제 내역 수정' , '결제 내역 취소 ' , '결제 내역 삭제' , '이 결제의  G 포인트 지급 현황'];
		  	var icons = ['icon-edit' , 'icon-clear' , 'icon-remove' , 'icon-more'];
		  	var actions = ['modify' , 'cancel' , 'remove' , 'more_detail'];
		  	
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
            	$('#request_list').datagrid('unselectAll');
            	$('#request_list').datagrid('uncheckAll');
                loadVanPaymentTransactionCreateForm();
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
            	loadVanPaymentTransactionModifyForm();
            }
        },{
            iconCls:'icon-clear',
            handler:function(){
            	cancelVanPaymentTransaction();
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removeVanPaymentTransaction();
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
	  				{vanPaymentTransactionNo : node.vanPaymentTransactionNo}
	  			);
            }
        }],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
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
		searchDateStart :  $('#searchDateStart').datetimebox('getValue'),
		searchDateEnd :  $('#searchDateEnd').datetimebox('getValue'),
		searchVanPaymentStatus :  $('input[name=searchVanPaymentStatus]').val(),
/*		paymentType :  $('input[name=paymentType]').val(),
		keywordType : $('input[name=keywordType]').val(),*/
		searchKeyword :  $('input[name=searchKeyword]').val()
	};
	
	//console.log("makeSearchParam");
	//console.log(JSON.stringify(param));
	return param;
}

function loadVanPaymentTransactionCreateForm(){
	var data = {
    		targetElem : "#dlgForm",
        	title :"POS 결제 내역 생성",
        	queryOptions : {
    			action : "create",
        	}
      }
	//console.log("loadVanPaymentTransactionCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/vanPaymentTransaction/form/createForm?" + queryParam,
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
							createVanPaymentTransaction(data);
						} },
						{ text:'취소', handler:function(){
							console.log("닫을 DIV : " + data.targetElem);	
							$(data.targetElem).dialog('close');
							$(data.targetElem).removeAttr('style');
						}
				}]
			});
			$(data.targetElem).dialog('center');
			
		});
}

function loadVanPaymentTransactionModifyForm(){
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
    			vanPaymentTransactionNo : node.vanPaymentTransactionNo
        	}
     }
	
	//console.log("loadVanPaymentTransactionModifyForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/vanPaymentTransaction/form/createForm?" + queryParam,
		function(response, status, xhr) {
		console.log("오픈할 DIV : " + data.targetElem);	
		if(status!="success")return;
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
						updateVanPaymentTransaction(data);
					} },
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
			}]
		});
		$(data.targetElem).dialog('center');
		returnp.api.call('getVanPaymentTransaction', {vanPaymentTransactionNo : data.queryOptions.vanPaymentTransactionNo}, function(res){
			if (res.resultCode  == "100") {
				console.log("getVanPaymentTransaction 정보 로딩 : ");
				console.log(res);
				$('#createVanPaymentForm').form('load',res.data);
				$('#memberNo').textbox({disabled : true });
				$('#vanTransactionType').combobox('select',res.data.regType);
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		});
		
	});
}

function makeFormData(){
	var param = {
		memberNo : 	$('input[name=memberNo]').val(),	
		affiliateNo : 	$('input[name=affiliateNo]').val(),	
		memberEmail : 	$('input[name=memberEmail]').val(),	
		memberName : 	$('input[name=memberName]').val(),	
		memberPhone : 	$('input[name=memberPhone]').val(),	
		affiliateSerial : 	$('input[name=affiliateSerial]').val(),	
		approvalPayAmount : 	$('input[name=approvalPayAmount]').val(),		
		vanPaymentStatus : 	$('#vanPaymentStatus').combobox('getValue'),	
		approvalNumber : 	$('input[name=approvalNumber]').val(),	
		approvalDateTime : 	$('#approvalDateTime').datetimebox('getValue'),	
		vanTransactionType : $('#vanTransactionType').val()
	};
	//console.log(param);
	return param;
}

function updateVanPaymentTransaction(data){
	var param =makeFormData();
	//console.log("updateVanPaymentTransaction");
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
	
	returnp.api.call("updateVanPaymentTransaction", param, function(res){
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

function createVanPaymentTransaction(data){
	var param =makeFormData();
	//console.log("createVanPaymentTransaction");
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
		
	returnp.api.call("createVanPaymentTransaction", param, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			$(data.targetElem).dialog('close');
			$(data.targetElem).removeAttr('style');
			realodPage();
		}else {
			//console.log("[오류]");
			//console.log(res);
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function cancelVanPaymentTransaction(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','취소하실 거래 내역을 선택해주세요');
		 return;
	}
	$.messager.confirm(
		'알림', 
		"해당 거래 내역이 취소됩니다. </br>취소 시 관련 계층의 G Point가  취소된 포인트만큼 차감됩니다. 진행하시겠습니까?",
		function(r){
			if (r) {
				var param = { vanPaymentTransactionNo : node.vanPaymentTransactionNo }
				returnp.api.call("cancelVanPaymentTransaction", param, function(res){
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

function removeVanPaymentTransaction(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        		vanPaymentTransactionNo : node.vanPaymentTransactionNo
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


