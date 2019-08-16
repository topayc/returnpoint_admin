		columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'mainBbsNo',width:7,align:'center',title : '번호'},
			    {field:'bbsType1',width:14,align:'center',title : '분류', formatter : bbsType1Formatter},
			    {field:'bbsType2',width:18,align:'center',title : '타입', formatter : bbsType2Formatter},
			    {field:'title',width:50,align:'left',title : '제목'},
			    {field:'replyCompleted',width:12,align:'center',title : '답변 여부'},
			    {field:'status',width:12,align:'center',title : '상태', formatter : bbsStatusFormatter},
			    {field:'writerNo',width:12,align:'center',title : '회원 번호'},
			    {field:'rerv6',width:30,align:'center',title : '이메일'},
			    {field:'rerv1',width:20,align:'center',title : '상호명'},
			    {field:'rerv2',width:17,align:'center',title : '대표자명'},
			    {field:'rerv3',width:40,align:'center',title : '주소'},
			    {field:'rerv4',width:20,align:'center',title : '담당자'},
			    {field:'rerv5',width:17,align:'center',title : '연락처'},
			    {field:'content',width:70,align:'center',title : '내용', hidden : true},
			    {field:'createTime',width:18,align:'center',title : '등록일', formatter : dateFormatter},
			    {field:'updateTime',width:15,align:'center',title : '수정일', formatter : dateFormatter , hidden : true},
			    ]];

initView();
var bbsType1 = "4";
/**
 * 뷰 초기화
 * @returns
 */
function initView(){
	/* 레이아웃 초기화*/
	$('.easyui-layout').layout();

	/* 패널   초기화*/
	$('.easyui-panel').panel({ border: false });


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
		  			case "modify":
		  				openBoardUpdateForm();
		  				break;
		  			case "remove":
		  				removeBoard();
		  				break;
		  			case "viewBoardContent":
		  				viewBoardContent();
		  				break;

		  			case "reply":
		  				openReplyForm();
		  				break;
		  			}
		  		}
		  	});

		  	var menus = [ /*'수정' ,*/ '내용 보기', '답변 생성 / 수정','삭제'];
		  	var icons = [ /*'icon-edit' ,*/ 'icon-edit','icon-edit', 'icon-remove'];
		  	var actions = [ /*'modify',*/ 'viewBoardContent','reply', 'remove'];

		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			text:   menus[i],
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

	$('#title').textbox({
		label : "제목",
		labelPosition : 'top'
	});

	$('#content').textbox({
		label : "내용",
		labelPosition : 'top',
		multiline:true
	});

	$('#board_reply').textbox({
		labelPosition : 'top',
		multiline:true
	});
}

function setListPager(){
	var pager = $('#node_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[/*{
            iconCls:'icon-add',
            handler:function(){
            	$('#node_list').datagrid('unselectAll');
            	$('#node_list').datagrid('uncheckAll');
            	openBoardCreateForm();
            }
        }*/],
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


function makeFormData(){
	var param = $("#board_reply_form").serializeObject();
	return param;
}

function removeBoard(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}

	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 항목을 정말 삭제하시겠습니까?', function(r){
        if (r){
        	returnp.api.call("removeMainBbs", {mainBbsNo : node.mainBbsNo}, function(res){
        		//console.log(res);
        		if (res.resultCode  == "100") {
        			$.messager.alert('알림', res.message);
        			realodPage();
        		}else {
        			$.messager.alert('알림', res.message);
        		}
        	});
        }
    });
}

function viewBoardContent(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 항목을 선택해주세요');
		 return;
	}
	$('#board_view_container').dialog({
	    title: node.title,
	    width: 700,
	    height: 600,
	    closed: false,
	    cache: false,
	    modal: true,
		buttons : [ {
			text : '확인',
			iconCls : 'icon-ok',
			handler : function() {
				$('#board_view_container').dialog('close');
			}
		} ]
	});
	$('#board_content').html(node.content.replace(/(\n|\r\n)/g, '<br>') );
}

function openReplyForm(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 항목을 선택해주세요');
		 return;
	}

	$('#board_reply_container').dialog({
	    title: "[ " + node.content + " ] 에 대한 답변 달기 ",
	    width: 800,
	    height: 600,
	    closed: false,
	    cache: false,
	    modal: true,
		buttons : [ {
			text : '확인',
			iconCls : 'icon-ok',
			handler : function() {
				reply();
			}
		}, {
			text : '취소',
			handler : function() {
				$('#board_replay_container').dialog('close');
			}
		} ]
	});

	$('#board_content_ori').html(node.content.replace(/(\n|\r\n)/g, '<br>') );
	$("#board_reply_form").form("clear");
	$('#mainBbsNo').val(node.mainBbsNo);
	$('#content').textbox("setValue", "");

	if (node.replyCompleted == "Y") {
		returnp.api.call("getSubBbses", {mainBbsNo : node.mainBbsNo}, function(res){
			if (res.resultCode  == "100") {
				console.log(res);
				$('#content').textbox("setValue", res.rows[0].content);
			}else {
				$.messager.alert('알림', res.message);
			}
		});
	}

}

function reply(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 항목을 선택해주세요');
		 return;
	}
	var param = {mainBbsNo : node.mainBbsNo, content : $("#content").textbox("getValue")}
	console.log(param);
	returnp.api.call("replyBoard", param, function(res){
		//console.log(res);
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
			$('#board_reply_container').dialog('close');
			$('#board_reply_container').removeAttr('style');
			realodPage();
		}else {
			$.messager.alert('알림', res.message);
		}
	});
}

function realodPage(){
	var param = {bbsType1 :bbsType1 }
	returnp.api.call("getMainBbses", param, function(res){
		console.log(">>>> bbs datas");
		console.log(res);
		if (res.resultCode == "100") {
			$('#node_list').datagrid({
				data : res,
				title : '[검색 결과] ' + res.total + " 개의 결과가 검색되었습니다",
			});
			setListPager();
		}else {
			$.messager.alert(res.message, res.data);
		}
	});
}
$(document).ready(function(){
	realodPage();
});
