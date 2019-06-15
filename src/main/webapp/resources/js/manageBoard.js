
columns = [[
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
	    {field:'boardNo',width:50,align:'center',title : '번호', hidden : true},
	    {field:'boardType',width:50,align:'center',title : '게시물 타입', hidden : true},
	    {field:'boardName',width:70,align:'center',title : '게시 타입'},
	    {field:'boardCate',width:70,align:'center',title : '분류' , formatter : categoryFomatter},
	    {field:'boardTitle',width:150,align:'left',title : '제목', formatter : boardLevelTitleFormatter},
	    {field:'boardContent',width:200,align:'left',title : '내용', hidden : true},
	    {field:'boardWriterName',width:100,align:'center',title : '등록자'},
	    {field:'boardWriterEmail',width:100,align:'center',title : '등록자 이메일'},
	    {field:'boardWriterNo',width:80,align:'center',title : '작성자 No', hidden : true},
	    {field:'boardWriterType',width:60,align:'center',title : '등록 분류'},
	    {field:'isPublic',width:50,align:'center',title : '공개 여부'},
	    {field:'replyStatus',width:80,align:'center',title : '답변 여부', formatter : boardReplyStatusFormatter},
	    {field:'viewHitCount',width:60,align:'center',title : '조회수',hidden : true },
	    {field:'boardRef',width:50,align:'center',title : 'ref' ,hidden : true },
	    {field:'boardLevel',width:50,align:'center',title : 'level',hidden : true},
	    {field:'createTime',width:90,align:'center',title : '등록일',formatter : dateFormatter},
	    {field:'updateTime',width:90,align:'center',title : '수정일',formatter : dateFormatter, hidden : true}
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
	$('#searchBoardType').combobox({
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
			reloadPage();
		},
		iconCls:'icon-search'
	});
	
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
		/*	$('#searchForm').form('clear');*/
			$('#searchBoardType').combobox('select', 0);
			$('#searchKeywordType').combobox('select', 0);
			$('#searchKeyword').textbox('clear');
			$('#searchDateStart').datetimebox('clear');
			$('#searchDateEnd').datetimebox('clear');
		}
	});
	
	/* 노드 데이타그리드   초기화*/
	$('#board_list').datagrid({
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
		  				loadBoardCreateForm();
		  				break;
		  			case "modify":
		  				loadBoardUpdateForm();
		  				break;
		  			case "remove":
		  				removeBoard();
		  				break;
		  			case "more_detail":
		  				viewBoardDetai();
		  				break;
		  			case "createReply":
		  				loadReplyBoardCreateForm();
		  				break;
		  				
		  			case "modifyReply":
		  				loadReplyBoardUpdateForm();
		  				break;
		  			case "removeReply":
		  				removeReply();
		  				break;
		  			case "viewReplyDetail":
		  				viewReplyDetail();
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = [  '수정', '삭제','내용 보기' ];
		  	var icons = ['icon-edit' ,'icon-remove','icon-more'];
		  	var actions = ['modify','remove','more_detail'];
		  	if (row.boardType !='1'  && row.boardType != '2' && row.replyStatus == '1' && row.boardLevel == 1) {
		  		menus.push("답변 달기");
		  		icons.push('icon-redo');
		  		actions.push("createReply")
		  	}
		  	
			if (row.boardType !='1'  && row.boardType != '2' && row.replyStatus == '2' && row.boardLevel == 1) {
				menus.push("-");menus.push("답변 보기");menus.push("답변 수정");menus.push("답변 삭제");
				icons.push('');icons.push('');icons.push('');icons.push('');
				actions.push("");actions.push("viewReplyDetail");actions.push("modifyReply");actions.push("removeReply");
		  	}
		  	
		  	for(var i=0; i<menus.length; i++){
		  		if (menus[i] == "-") {
		  			cmenu.menu('appendItem', {
		  				separator: true
		  			});
		  		}else {
		  			cmenu.menu('appendItem', {
		  				data : row,
		  				no : row.memberNo,
		  				text:  "<strong></strong>"  + " " + menus[i],
		  				action: actions[i],
		  				iconCls: icons[i]
		  			});
		  		}
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
	var pager = $('#board_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#board_list').datagrid('unselectAll');
            	$('#board_list').datagrid('uncheckAll');
            	loadBoardCreateForm();
            }
        },{
            iconCls:'icon-reload',
            handler:function(){
            	loadPage();
            }
        }],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
    }); 
}
function makeFormData(){
	var param = {
			boardType : $('input[name=boardType]').val(),	
			isPublic : $('input[name=isPublic]').val(),	
			boardTitle : $('input[name=boardTitle]').val(),	
			boardContent  :  $('#boardContent').html()
		}
	if (param.boardType  !="1" && param.boardType  !="2"){
		param.boardCate = $('input[name=boardCate]').val();
	}
	return param;
}

function viewBoardDetai(){
	var node = $('#board_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','항목을 선택해주세요');
		 return;
	}
	
	$.messager.alert({
		width: 800,
		height: 700,
		title : node.boardTitle, 
		msg : node.boardContent
	});
}

function loadBoardCreateForm(){
	var data = {
	        targetElem : "#dlgForm",
	           title :" Board 생성",
	           queryOptions : {
	           	action : "create"
	           }
	        };
		
		//console.log("loadMemberCreateForm");
		var queryParam = $.param(data.queryOptions);
		$(data.targetElem).load("/api/board/form/createForm?" + queryParam,
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
						{ text:'확인', iconCls:'icon-ok', handler:function(){
							createBoard(data);
						} },
						{ text:'취소', handler:function(){
							$(data.targetElem).removeAttr('style');
							$(data.targetElem).dialog('close');
							$(data.targetElem).dialog('destroy');
							$(data.targetElem).empty();
						}
					}]
				});
				$(data.targetElem).dialog('center');
				
			});
}
function loadReplyBoardCreateForm(){
	var node = $('#board_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','답글을 등록하실 항목을 선택해주세요');
		 return;
	}
	
	var data = {
			targetElem : "#dlgForm",
			title : "[ " + node.boardTitle+ " ] 에 대한 답글 생성",
			queryOptions : {
				action : "create",
				mainSubject : "N",
				boardNo : node.boardNo
			}
	};
	
	//console.log("loadMemberCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/board/form/createForm?" + queryParam,
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
				{ text:'확인', iconCls:'icon-ok', handler:function(){
					createReply(data);
				} },
				{ text:'취소', handler:function(){
					$(data.targetElem).removeAttr('style');
					$(data.targetElem).dialog('close');
					$(data.targetElem).empty();
				}
				}]
		});
		$(data.targetElem).dialog('center');
		
	});
}
function loadReplyBoardUpdateForm(){
	var node = $('#board_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 항목을 선택해주세요');
		 return;
	}
	
	var data = {
		targetElem : "#dlgForm",
        title : " [" +node.boardTitle + "] 의 답글 수정",
        queryOptions : {
          	action : "modify",
          	boardNo : node.boardNo,
          	mainSubject : "N"
           }
       };
	
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/board/form/createForm?" + queryParam,
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
					{ text:'확인', iconCls:'icon-ok', handler:function(){
						updateReply(data);
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

function loadBoardUpdateForm(data){
	var node = $('#board_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 항목 선택해주세요');
		 return;
	}
	
	var data = {
		targetElem : "#dlgForm",
        title : " 수정" + " [" +node.boardTitle + "] ",
        queryOptions : {
          	action : "modify",
          	boardNo : node.boardNo
           }
       };
	
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/board/form/createForm?" + queryParam,
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
					{ text:'확인', iconCls:'icon-ok', handler:function(){
						updateBoard(data);
					} },
					{ text:'취소', handler:function(){
						$(data.targetElem).removeAttr('style');
						$(data.targetElem).dialog('close');
					}
				}]
			});
			$(data.targetElem).dialog('center');
			
			returnp.api.call('getBoard', {boardNo : data.queryOptions.boardNo}, function(res){
				if (res.resultCode  == "100") {
					console.log("Board Info : ");
					console.log(res);
					$('#createBoardForm').form('load',res.data);
					$('#boardContent').texteditor("setValue", res.data.boardContent)
					 $('#boardType').combobox({readonly : true});
				}else {
            			$.messager.alert('오류 발생', res.message);
				}
			});
			
		});
}

function createBoard(data){
	var param = makeFormData();
	console.log(param);
	var valid = true;
	for (var prop in param){
		if (param.hasOwnProperty(prop)) {
			if (!param[prop] ||  param[prop] == '') {
				valid = false;
				break;
			}
		}
	}
	
	if (!valid) {
		$.messager.alert('알림', '입력 항목이 모두 입력되지 않았습니다');
		return;
	}	
	
	returnp.api.call("createBoard", param, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			$(data.targetElem).removeAttr('style');
			$(data.targetElem).dialog('close');
			realodPage();
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}
function createReply(data){
	var node = $('#board_list').datagrid('getSelected');
	var param = {
			boardTitle : "Re : 답변글",
			boardContent  :  $('#boardContent').html()
		}
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
	
	returnp.api.call("createBoard", param, function(res){
		//console.log(res);
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			$(data.targetElem).removeAttr('style');
			$(data.targetElem).dialog('close');
			realodPage();
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function updateBoard(data){
	var param = makeFormData();
	
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
	
	returnp.api.call("updateBoard", param, function(res){
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

function updateReply(data){
	var param = {
			boardTitle : "Re : 답변글",
			boardContent  :  $('#boardContent').html()
		}
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
	
	returnp.api.call("updateBoard", param, function(res){
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

function removeBoard(){
	var node = $('#board_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        		boardNo : node.boardNo
        	}
        	returnp.api.call("deleteBoard", param, function(res){
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

function viewReplyDetail(){
	var node = $('#board_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','답변을 보실 항목을 선택해주세요');
		 return;
	}
	
	returnp.api.call("getReply", {boardNo : node.boardNo}, function(res){
		console.log("getReply");
		console.log(res);
		if (res.resultCode  == "100") {
			$.messager.alert({
				width: 800,
				height: 700,
				title : res.data.boardTitle, 
				msg : res.data.boardContent
			});
		}else {
			//console.log("[오류]");
			//console.log(res);
			$.messager.alert('오류 발생', res.message);
		}
	});
	
}
function removeReply(){
	var node = $('#board_list').datagrid('getSelected');
	if (!node) {
		$.messager.alert('알림','답변을 보실 항목을 선택해주세요');
		return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	returnp.api.call("deleteReply", {boardNo : node.boardNo}, function(res){
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
	returnp.api.call("getBoards", {}, function(res){
		//console.log("node datas");
		console.log(res);
		if (res.resultCode == "100") {
			$('#board_list').datagrid({
				data : res,
				title : '[검색 결과] ' + res.total + " 개의 결과가 검색되었습니다",
			});
			setListPager();
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

$(document).ready(function(){
	realodPage();
});
