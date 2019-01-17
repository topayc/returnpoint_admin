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
	
	
	$('#searchPaymentStatus').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	$('#searchNodeType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	$('#searchPaymentType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	/* 검색어 타입 셀렉트 박스  초기화*/
	$('#searchKeywordType').combobox({
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
			returnp.api.call("getMembershipRequests", param, function(res){
				//console.log("node datas");
				console.log(res);
				if (res.resultCode == "100") {
					$('#request_list').datagrid({
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
			/*$('#searchForm').form('clear');*/
			$('#searchNodeType').combobox('select', 1);
			$('#searchPaymentStatus').combobox('select', 0);
			$('#searchPaymentType').combobox('select', 0);
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
	$('#request_list').datagrid({
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
		  				loadMembershipCreateForm();
		  				break;
		  			case "modify":
		  				loadMembershipModifyForm();
		  				break;
		  			case "remove":
		  				 removeMembershipRequest();
		  				break;
		  			case "more_detail":
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = [  '수정', '삭제','상세' ];
		  	var icons = ['icon-edit','icon-remove','icon-more'];
		  	var actions = ['modify','remove','more_detail'];
		  	
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
	    columns: [[
	    	
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'membershipRequestNo',width:15,align:'center',title : '신청 번호',hidden:false},
			    {field:'memberNo',width:15,align:'center',title : '회원 번호'},
			    {field:'memberEmail',width:40,align:'center',title : '회원 이메일'},
			    {field:'memberName',width:40,align:'center',title : '회원 이름'},
			    {field:'depositor',width:40,align:'center',title : '입금자명'},
			    {field:'paymentAmount',width:30,align:'center',title : '입금 금액', formatter : numberFormatter},
			    {field:'paymentStatus',width:40,align:'center',title : '결제(입금) 상태', styler:paymentStausCellStyler , formatter :paymentStatusFormatter /*, editor:{
                    type:'combobox',
                    options:{
                        valueField:'key',
                        textField:'value',
                        data:editData,
                        required:true
                    }
                }*/},
			    {field:'paymentType',width:30,align:'center',title : '결제 유형', formatter : paymentTypeFormatter},
			    {field:'companyBankAccountNo',width:80,align:'center',title : '입금 은행', formatter : function(value,row,index){
			    	return row.bankName + "(" + row.bankAccount + ") - " + row.bankOwnerName;
			    }},
			    {field:'regType',width:25,align:'center',title : '등록 방법',formatter : registTypeFormatter},
			    {field:'regAdminNo',width:15,align:'center',title : '등록자'},
			    {field:'createTime',width:35,align:'center',title : '등록일',formatter : dateFormatter},
			    {field:'updateTime',width:20,align:'center',title : '수정일',formatter : dateFormatter,hidden:true}
			 ]],
		
		onEndEdit:function(index,row){
	        
			/*var ed = $(this).datagrid('getEditor', {
	                index: index,
	                field: 'productid'
	            });
	            row.productname = $(ed.target).combobox('getText');*/
	        },
	        
	        onBeforeEdit:function(index,row){
	            row.editing = true;
	            $(this).datagrid('refreshRow', index);
	        },

	        /*onEndEdit 다음에 호출됨*/
	        onAfterEdit:function(index,row){
	        	row.editing = false;
	        	$(this).datagrid('endEdit', index);
		        //console.log(index);
		        //console.log(row);
	        },
	        
	        onCancelEdit:function(index,row){
	        	row.editing = false;
	            $(this).datagrid('refreshRow', index);
	        }
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

$(function(){
	$('#request_list').datagrid().datagrid('enableCellEditing');
	setListPager();
})


function setListPager(){
	var pager = $('#request_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#request_list').datagrid('unselectAll');
            	$('#request_list').datagrid('uncheckAll');
                loadMembershipCreateForm();
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
            	loadMembershipModifyForm();
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	var node = $('#request_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
            		 return;
            	}
            	 removeMembershipRequest();
            }
        },{
            iconCls:'icon-more',
            handler:function(){
            	var node = $('#request_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','자세히 보실 항목을  선택해주세요');
            		 return;
            	}
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
		searchPaymentStatus :  $('input[name=searchPaymentStatus]').val(),
		searchPaymentType :  $('input[name=searchPaymentType]').val(),
		searchKeywordType : $('input[name=searchKeywordType]').val(),
		searchKeyword :  $('input[name=searchKeyword]').val()
	};
	//console.log(JSON.stringify(param));
	return param;
}

function loadMembershipCreateForm(){
	var data = {
    		targetElem : "#dlgForm",
        	title :"정회원 신청내역 수동 생성",
        	queryOptions : {
    			action : "create",
        	}
        }
	//console.log("loadRecommenderCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/membershipRequest/form/createForm?" + queryParam,
		function(response, status, xhr) {
		console.log("오픈할 DIV : " + data.targetElem);	
		
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
						createMembershipRequest(data);
					} },
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
			}]
		});
		$(data.targetElem).dialog('center');
		
	});
}

function loadMembershipModifyForm(){
	var node = $('#request_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 항목을 내역을 선택해주세요');
		 return;
	}
	var data = {
			targetElem : "#dlgForm",
    		title : "수정",
    		queryOptions : {
    			action : "modify",
    			membershipRequestNo : node.membershipRequestNo
        	}
     }
	
	//console.log("loadMembershipModifyForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/membershipRequest/form/createForm?" + queryParam,
		function(response, status, xhr) {
		console.log("오픈할 DIV : " + data.targetElem);	
		
			$(data.targetElem).dialog({
				width:600,
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
						updateMembershipRequest(data);
					} },
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
			}]
		});
			$(data.targetElem).dialog('center');
			returnp.api.call('getMembershipRequestCommand', {membershipRequestNo : data.queryOptions.membershipRequestNo}, function(res){
			if (res.resultCode  == "100") {
				console.log(res);
				$('#createMembershipRequest').form('load',res.data);
				$('#memberNo').textbox({disabled : true });
				$('#paymentType').combobox({disabled : true });
				$('#regType').combobox('select',res.data.regType);
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		});
		
	});
}

function makeFormData(){
	var param = {
			memberNo : $('input[name=memberNo]').val(),	
			depositor : $('input[name=depositor]').val(),	
			companyBankAccountNo : $('#companyBankAccountNo').combobox('getValue'),	
			paymentAmount : $('input[name=paymentAmount]').val(),	
			paymentType : $('#paymentType').combobox('getValue'),	
			paymentStatus : $('#paymentStatus').combobox('getValue'),	
			regType : $('#regType').combobox('getValue')
		}
	return param;
}

function updateMembershipRequest(data){
	var param =makeFormData();
	//console.log("updateMembershipRequest");
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
	
	/* 	if (!valid) {
			$.messager.alert('알림', '입력 항목이 모두 입력되지 않았습니다');
			return;
		}	 */
	
	returnp.api.call("updateMembershipRequest", param, function(res){
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
function removeMembershipRequest(){
	var node = $('#request_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        		membershipRequestNo : node.membershipRequestNo
        	}
        	returnp.api.call("deleteMembershipRequest", param, function(res){
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

function createMembershipRequest(data){
	var param =makeFormData();
	//console.log("createRecommender");
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
	
		/* 	if (!valid) {
			$.messager.alert('알림', '입력 항목이 모두 입력되지 않았습니다');
			return;
		}	 */
		
	returnp.api.call("createMembershipRequest", param, function(res){
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

function realodPage(){
	$('#search_btn').click();
}
$(document).ready(function(){
	realodPage();
});
