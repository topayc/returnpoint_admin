	columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'pointWithdrawalNo',width:30,align:'center',title : '출금 번호',hidden:false},
			    {field:'memberNo',width:30,align:'center',title : '회원 번호', hidden: false},
			    {field:'memberName',width:30,align:'center',title : '회원 이름'},
			    {field:'memberEmail',width:60,align:'center',title : '회원 이메일'},
			    {field:'memberPhone',width:40,align:'center',title : '회원 모바일'},
			    {field:'memberBankAccountNo',width:40,align:'center',title : '은행 번호',hidden:true},
			    {field:'bankName',width:40,align:'center',title : '은행 명'},
			    {field:'accountOwner',width:40,align:'center',title : '계좌 소유주'},
			    {field:'bankAccount',width:40,align:'center',title : '계좌 번호'},
			    {field:'withdrawalAmount',width:40,align:'center',title : '출금 금액', formatter : numberBoldFormatter},
			    {field:'withdrawalStatus',width:50,align:'center',title : '출금 상태', formatter : withdrawalStatusFormatter},
			    {field:'withdrawalPointType',width:15,align:'center',title : '타입'},
			    {field:'withdrawalMessage',width:50,align:'center',title : '출금 메시지', hidden: true},
			    {field:'createTime',width:50,align:'center',title : '등록일',formatter : dateFormatter},
			    {field:'updateTime',width:50,align:'center',title : '출금일',formatter : dateFormatter}
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
		width: 200,
		prompt : "검색할 단어를 입력해주세요" ,
		inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
			keyup:function(e){
				if(e.keyCode==13)
				realodPage();
			}
		})
	});

	/* 노드 상태 셀렉트 박스  초기화*/
	$('#searchWithdrawalStatus').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 120
	});
	
/*	 검색어 타입 셀렉트 박스  초기화
	$('#searchKeywordType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 100
	});*/
	
	/* 검색 시작일 갤린더 박스  초기화*/
	$('#searchDateStart').datebox({	   
	    prompt : "검색 시작 일자",
	    labelPosition: 'top',
	    width: 150
	});
	
	/* 검색 종료일 갤린더 박스  초기화*/
	$('#searchDateEnd').datebox({	  
	    prompt : "검색 종료 일자",
	    labelPosition: 'top',
	    width: 150
	});
	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
		onClick : function(){
			//$.messager.progress();
			var param = makeSearchParam();
			//console.log("검색 쿼리 데이타");
			//console.log(param);
			
			returnp.api.call("getPointWithdrawals", param, function(res){
				if (res.resultCode == "100") {
					setListColumnHeader(param.searchNodeType);
					$('#node_list').datagrid({
						data : res,
						title : '[검색 결과] ' + res.total + " 개의 결과가 검색되었습니다",
					});
					setListPager();
				}else {
					$.messager.alert(res.message, res.data);
				}
			});
		},
		width:60,
		iconCls:'icon-search'
	});
	
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		width:60,
		onClick : function(){
			$('#searchForm').form('clear');
			$('#searchWithdrawalStatus').combobox('select', 0);
			$('#searchKeywordType').combobox('select', 0);
			$('#searchKeyword').textbox('clear');
			$('#searchDateStart').datebox('clear');
			$('#searchDateEnd').datebox('clear');
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
		onRowContextMenu : function(e, index, row){
			e.preventDefault();
		  	$(this).datagrid("selectRow", index);
		  	var cmenu = $('<div/>').appendTo('body');
		  	cmenu.menu({
		  		onClick : function(item){
		  			switch(item.action){
		  			case "create":
		  				loadPointWithdrawalCreateForm();
		  				break;
		  			case "modify":
		  				var node = $('#node_list').datagrid('getSelected');
		            	if (node.withdrawalStatus == "2" ||  node.withdrawalStatus == "4" || node.withdrawalStatus == "5") {
		            		$.messager.alert('알립', "해당 항목은 상태를 변경할 수 없습니다.");
		            		return;
		            	}
		                  loadPointWithdrawalModifyForm();
		  				break;
		  			case "remove":
		  				removePointWithdrawal();
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = [  '수정', '삭제' ];
		  	var icons = ['icon-edit','icon-remove'];
		  	var actions = ['modify','remove'];
		  	
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
            text : "출금정보 생성",
            handler:function(){
            	$('#node_list').datagrid('unselectAll');
            	$('#node_list').datagrid('uncheckAll');
                loadPointWithdrawalCreateForm();
            }
        },{
            iconCls:'icon-add',
            text : "엑셀 변환",
            handler:function(){
            	gridToExcel('#node_list','point_withdrawl.xls');
            }
        },{
            iconCls:'icon-add',
            text : "프린트",
            handler:function(){
            	printGrid('#node_list');
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
 * 노드 타입에 따라 리스트 그리드 컬럼 헤더 변경
 * @param param
 * @returns
 */
function setListColumnHeader(nodeType){
	$('#node_list').datagrid({
		columns : columns
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
		pagination : opts.pagination,
		pageSize : opts.pageSize,
		page : opts.pageNumber,
		total : total,
		offset : (opts.pageNumber-1) * opts.pageSize
	});
	
	return param;
}

function statusFormatter(value,row,index){
	var status = row.memberStatus;
	return '&nbsp;<span >'+ status + '</span>';
}

function loadPointWithdrawalCreateForm(){
	var nodeType = $('input[name=searchNodeType]').val();
  	var data = {
        targetElem : "#dlgForm",
        title : "출금 정보 등록",
        queryOptions : {
          	action : "create",
         }
     }
  	
	//console.log("loadPointWithdrawalCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/pointWithdrawal/form/createForm?" + queryParam,
			function(response, status, xhr) {
					
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
								var nodeType = $('input[name=searchNodeType]').val();
								createPointWithdrawal(data);
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

function loadPointWithdrawalModifyForm(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 노드를 선택해주세요');
		 return;
	}
	
	var data = {
		targetElem : "#dlgForm",
        title : ' 출금 정보 수정',
        queryOptions : {
        	action : "modify",
        	pointWithdrawalNo : node.pointWithdrawalNo
        }
     }
	
	//console.log("loadPointWithdrawalModifyForm");
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/pointWithdrawal/form/createForm?" + queryParam,
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
							updatePointWithdrawal(data);
						} },
						{ text:'취소', handler:function(){
							//console.log("닫을 DIV : " + data.targetElem);	
							$(data.targetElem).dialog('close');
							$(data.targetElem).removeAttr('style');
						}
					}]
				});
				$(data.targetElem).dialog('center');
				returnp.api.call('getPointWithdrawals', { pointWithdrawalNo : data.queryOptions.pointWithdrawalNo}, function(res){
					console.log(res);
					if (res.resultCode  == "100") {		
						if (res.rows.length > 0) {
							$('#createPointWithdrawalForm').form('load',res.rows[0]);
							$('#memberNo').textbox({readonly : true });
							$('#regType').combobox('select',res.rows[0].regType);
						}
					}else {
						$.messager.alert('오류 발생', res.message);
					}	
				});
				
				/*선택한 회원의 R PAY 조회*/
        		returnp.api.call("getRedPointCommand", {memberNo : node.memberNo}, function(res){
            		if (res.resultCode  == "100") {
            			$('#rPayBalance').textbox('setValue', res.data.pointAmount);
            		}else {	
            		}
            	});
        		
        		/*선택한 회원의 출금 계좌 조회*/
        		returnp.api.call("getMemberBankAccounts", {memberNo : node.memberNo}, function(res){
            		console.log("출금 계좌 리스트");
            		console.log(res)
        			if (res.resultCode  == "100") {
        				$('#memberBankAccountNo').combobox("clear");
        				var data = [];
        				
        				for (var i = 0; i < res.rows.length ; i ++){
        					data.push({
        						memberBankAccountNo: res.rows[i].memberBankAccountNo , 
        						memberBankAccountText : "["+ res.rows[i].accountOwner + "] - " + res.rows[i].bankName + " -  " + res.rows[i].bankAccount   
        					});
        					
        				}
						data.push()
						$('#memberBankAccountNo').combobox('loadData', data);
						$('#memberBankAccountNo').combobox('select', node.memberBankAccountNo);
        			}else {	
            		}
            	});
		});
}


function makeFormData(){
	var param = $("#createPointWithdrawalForm").serializeObject();
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
		return false;
	}	
	
	var rPayWithdrawalMaxLimit = parseInt($("#rPayWithdrawalMaxLimit").val());
	var rPayWithdrawalMinLimit = parseInt($("#rPayWithdrawalMinLimit").val());
	var withdrawalAmount = parseInt($("#withdrawalAmount").val());
	var rPayBalance = parseInt($("#rPayBalance").val());
	
	if (withdrawalAmount < rPayWithdrawalMinLimit  || withdrawalAmount > rPayWithdrawalMaxLimit) {
		$.messager.alert(
			'알림', 
			"* R PAY 출금은  최저" +  numberBoldFormatter(rPayWithdrawalMinLimit)  + 
			" 이상  최고 " + numberBoldFormatter(rPayWithdrawalMaxLimit)   + " 까지 출금 가능합니다</br> 확인후 다시 시도해주세요" );
		return false;
	}
	return param;
}

function updatePointWithdrawal(data){
	var param =makeFormData();
	if (param == false) return;
	returnp.api.call("updatePointWithdrawal", param, function(res){
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

function createPointWithdrawal(data){	
	var param =makeFormData();
	
	/* 출금 신청 생성의 경우 보유 RPAY 와 출금 요청 금액한도를 비교*/
	var withdrawalAmount = parseInt($("#withdrawalAmount").val());
	var rPayBalance = parseInt($("#rPayBalance").val());
	
	if ( withdrawalAmount > rPayBalance  ) {
		$.messager.alert('알림', '출금 금액이 현재 보유 R PAY를 초과합니다. </br>다시 확인해주세요');
		return false;
	}
	
	if (param == false) return;
	returnp.api.call("createPointWithdrawal", param, function(res){
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

function removePointWithdrawal(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        			pointWithdrawalNo : node.pointWithdrawalNo
        	}
        	returnp.api.call("deletePointWithdrawal", param, function(res){
        		if (res.resultCode  == "100") {
        			$.messager.alert('알림', res.message);
        			realodPage();
        		}else {
        			$.messager.alert('오류 발생', res.message);
        		}
        	});
        }
    });
}

function realodPage(){
	$('#search_btn').click();
}

$(document).ready(function(){
	$('#search_btn').click();
});
