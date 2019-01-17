
columns = [[
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
	    {field:'memberNo',width:20,align:'center',title : '번호',hidden:false},
	    {field:'memberName',width:40,align:'center',title : '이름'},
	    {field:'memberEmail',width:60,align:'center',title : '이메일'},
	    {field:'memberPassword',width:30,align:'center',title : '비밀번호', hidden: true},
	    {field:'memberPassword2',width:30,align:'center',title : '비밀번호2', hidden: true},
	    {field:'memberPhone',width:40,align:'center',title : '핸드폰'},
	    {field:'memberStatus',width:30,align:'center',title : '상태',formatter : nodeStatusFormatter},
	    {field:'recommenderName',width:30,align:'center',title : '추천인', hidden:false,formatter : slashFormatter},
	    {field:'recommenderEmail',width:60,align:'center',title : '추천인 이메일', hidden:true, formatter : slashFormatter},
	    {field:'greenPointAmount',width:40,align:'center',title : 'G Point', formatter : numberGreenFormatter},
	    {field:'redPointAmount',width:40,align:'center',title : 'R Point', formatter : numberRedFormatter},
	    {field:'memberAuthType',width:30,align:'center',title : '인증방법', formatter : authTypeFormatter,  hidden: true},
	    {field:'memberType',width:20,align:'center',title : '유형',hidden:true},
	    {field:'isSoleDist',width:20,align:'center',title : '총판', formatter : ynFormatter},
	    {field:'isRecommender',width:20,align:'center',title : '정회원', formatter : ynFormatter},
	    {field:'isSaleManager',width:30,align:'center',title : '영업관리자', formatter : ynFormatter},
	    {field:'isAgency',width:20,align:'center',title : '대리점', formatter : ynFormatter},
	    {field:'isBranch',width:20,align:'center',title : '지사', formatter : ynFormatter},
	    {field:'isAffiliate',width:25,align:'center',title : '협력업체', formatter : ynFormatter},
	    {field:'greenPointAccStatus',width:20,align:'center',title : 'G 적립', formatter : ynFormatter},
	    {field:'redPointAccStatus',width:20,align:'center',title : 'R 적립', formatter : ynFormatter},
	    {field:'greenPointUseStatus',width:20,align:'center',title : 'G 사용', formatter : ynFormatter},
	    {field:'redPointUseStatus',width:20,align:'center',title : 'R 사용', formatter : ynFormatter},
	    {field:'regType',width:40,align:'center',title : '등록 유형',formatter : registTypeFormatter, hidden: true},
	    {field:'regAdminNo',width:20,align:'center',title : '등록자', formatter : registAdminFormatter, hidden: true},
	    {field:'createTime',width:50,align:'center',title : '등록일',formatter : dateFormatter},
	    {field:'updateTime',width:30,align:'center',title : '수정일',hidden: true, hidden: true}
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
	$('#searchNodeType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		readonly:true,
	});
	
	/* 노드 상태 셀렉트 박스  초기화*/
	$('#searchNodeStatus').combobox({
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
	$('#searchDateStart').datebox({	   
	    prompt : "검색 시작 일자",
	    labelPosition: 'top'
	});
	
	/* 검색 종료일 갤린더 박스  초기화*/
	$('#searchDateEnd').datebox({	  
	    prompt : "검색 종료 일자",
	    labelPosition: 'top',
	});
	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
		onClick : function(){
			//$.messager.progress();
			var param = makeSearchParam();
			console.log("검색 쿼리 데이타");
			console.log(param);
			
			returnp.api.call("getNodes", param, function(res){
				console.log("node datas");
				console.log(res);
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
		iconCls:'icon-search'
	});
	
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
			/*$('#searchForm').form('clear');*/
		/*	$('#searchNodeType').combobox('select',0);*/
			$('#searchNodeStatus').combobox('select', 0);
			$('#searchKeywordType').combobox('select', 0);
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
		onRowContextMenu : function(e, index, row){
			e.preventDefault();
		  	$(this).datagrid("selectRow", index);
		  	var cmenu = $('<div/>').appendTo('body');
		  	cmenu.menu({
		  		onClick : function(item){
		  			switch(item.action){
		  			case "create":
		  				loadMemberCreateForm();
		  				break;
		  			case "modify":
		  				loadMemberUpdateForm();
		  				break;
		  			case "remove":
		  				removeMember();
		  				break;
		  			case "more_detail":
		  				loadMemberDetailInfo();
		  				break;
		  			case "point_acc_view":
		  				var node = $('#node_list').datagrid('getSelected');
		  				if (!node) {
		  					 $.messager.alert('알림','세부 내역을 확인하실 항목을 선택해주세요');
		  					 return;
		  				}
		  				loadPaymentPointbackRecord(
		  					"Green Point 누적 지급 현황", 
		  					{
		  						memberNo : node.memberNo,
		  						nodeType : 1
		  					}
		  				);
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = [  '수정', '삭제','상세 정보','포인트 누적 현황' ];
		  	var icons = ['icon-edit','icon-remove','icon-more', 'icon-large-chart'];
		  	var actions = ['modify','remove','more_detail','point_acc_view'];
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text:  "<strong>[" + row.memberName + "]</strong>"  + " " + menus[i],
		  			action: actions[i],
		  			iconCls: icons[i]
		  		});
		  	}
		  	cmenu.menu('show', {
		  		left:e.pageX,
		  		top:e.pageY
		  	});
		},
	    columns:columns
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

function loadMemberCreateForm(){
	var nodeType = $('input[name=nodeType]').val();
	var data = {
    	targetElem : "#dlgForm",
        title :" 회원 생성",
        queryOptions : {
        	action : "create",
        }
    };
	
	//console.log("loadMemberCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/member/form/createForm?" + queryParam,
		function(response, status, xhr) {
		
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
						createMember(data);
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

function loadMemberUpdateForm(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 회원를 선택해주세요');
		 return;
	}
	
	var nodeType = $('input[name=nodeType]').val();
	var data = {
        targetElem : "#dlgForm",
        title : "[" +node.memberName + "] " + " 회원 수정",
        queryOptions : {
        	action : "modify",
        	memberNo : node.memberNo
        }
    };
	
	//console.log("loadMemberUpdateForm");
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/member/form/createForm?" + queryParam,
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
						updateMember(data);
					} },
					{ text:'취소', handler:function(){
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}
				}]
			});
			$(data.targetElem).dialog('center');
			
			returnp.api.call('getMemberCommand', {memberNo : data.queryOptions.memberNo}, function(res){
				if (res.resultCode  == "100") {
					console.log(res);
					$('#createMemberForm').form('load',res.data);
					//$('#memberEmail').textbox({ disabled : true });
					$('#regType').combobox('select',res.data.regType);
					
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
			
		});
}

function loadMemberDetailInfo(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','상세보기할 회원을 선택해주세요');
		 return;
	}
	
	var data = {
		targetElem : "#dlgForm",
	    title : "[" +node.memberName + "] " + " 회원 상세 보기",
	    queryOptions : {
        	action : "memberDetailView",
        	memberNo : node.memberNo
        }
	};

	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/member/form/createForm?" + queryParam,
		function(response, status, xhr) {
			$(data.targetElem).dialog({
				width:800,
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
					{ text:'확인', handler:function(){
						$(data.targetElem).dialog('close');
						$(data.targetElem).removeAttr('style');
					}}]
			});
			$(data.targetElem).dialog('center');
		});
}

function makeFormData(){
	var param = {
			memberEmail : $('input[name=memberEmail]').val(),	
			memberPassword : $('input[name=memberPassword]').val(),	
			memberPassword2 : $('input[name=memberPassword2]').val(),	
			recommenderNo: $('input[name=recommenderNo]').val(),	
			memberTel: $('input[name=memberTel]').val(),	
			memberName: $('input[name=memberName]').val(),	
			memberPhone : $('input[name=memberPhone]').val(),	
			recommenderNo : $('input[name=recommenderNo]').val(),	
			memberAuthType : $('#memberAuthType').combobox('getValue'),	
			memberStatus : $('#memberStatus').combobox('getValue'),	
			regType : $('#regType').combobox('getValue')
		}
	return param;
}

function createMember(data){
	var emailCheck = $("#checkEmailMultiple").val();
	if (emailCheck == "N") {
		$.messager.alert('알림', "이메일 중복확인을 하지 않았습니다. 이메일 중복을 확인해주세요");
		return;
	}

	var phoneCheck = $("#checkPhoneMultiple").val();
	if (phoneCheck == "N") {
		$.messager.alert('알림', "이메일 중복확인을 하지 않았습니다. 이메일 중복을 확인해주세요");
		return;
	}
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
		
	returnp.api.call("createMember", param, function(res){
		//console.log(res);
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

function updateMember(data){
	var param =makeFormData();
	returnp.api.call("updateMember", param, function(res){
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

function removeMember(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        		memberNo : node.memberNo
        	}
        	returnp.api.call("deleteMember", param, function(res){
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
$(document).ready(function(){
	realodPage();
});
