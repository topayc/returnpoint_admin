		columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'giftCardSalesOrganNo',width:65,align:'center',title : '등록 No',hidden:false},
			    {field:'organType',width:65,align:'center',title : '구분', formatter : organTypeFormatter},
			    {field:'parentOrganNo',width:65,align:'center',title : '소속 No', hidden:true},
			    {field:'organName',width:160,align:'center',title : '사업장'},
			    {field:'parentOrganName',width:160,align:'center',title : '소속'},
			    {field:'organOwner',width:100,align:'center',title : '사업주'},
			    {field:'organBusinessNumber',width:130,align:'center',title : '사업자 번호'},
			    {field:'saleOrganSaleFeeRate',width:100,align:'center',title : '판매 수수료'},
			    {field:'organCode',width:90,align:'center',title : '코드(ID)'},
			    {field:'organPassword',width:90,align:'center',title : '비밀번호'},
			    {field:'organStatus',width:60,align:'center',title : '상태', formatter : organStatusFormatter},
			    {field:'organAccountInfo',width:220,align:'center',title : '은행 정보' , hidden : true, formatter : organAccountInfoFormatter},
			    {field:'organBankName',width:100,align:'center',title : '은행명' , hidden : false},
			    {field:'organBankAccountOwner',width:100,align:'center',title : '계좌주', hidden : false},
			    {field:'organBankAccount',width:120,align:'center',title : '계좌번호', hidden : false },
			    {field:'organPhone',width:100,align:'center',title : '핸드폰'},
			    {field:'organEmail',width:100,align:'center',title : '이메일'},
			    {field:'organTel',width:100,align:'center',title : '전화번호' },
			    {field:'organAddr',width:100,align:'center',title : '사업장 주소'},
			    {field:'createTime',width:100,align:'center',title : '등록일', formatter : dateFormatter},
			    {field:'updateTime',width:100,align:'center',title : '수정일', hidden : true},
			    ]];
initView();

/**
 * 뷰 초기화 
 * @returns
 */
function initView(){
	/* 레이아웃 초기화*/
	$('.easyui-layout').layout();
	
	$('#createOrglayout').layout({
		onCollapse : function(region){
		}, 
		onExpand : function(region){
		}, 
	});
	
	closeCapitalForm();
	/* 패널   초기화*/
	$('.easyui-panel').panel({ border: false });
	
	/* 폼 초기화*/
	$('#searchForm').form();
	
	/* 검색어 입력 박스 초기화 */
	$('#searchKeyword').textbox({ 
		width: 300,
		prompt : "검색할 단어를 입력해주세요" ,
		inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
			keyup:function(e){
				if(e.keyCode==13)
					realodPage();
			}
		})
	});
	
	/* 노드 상태 셀렉트 박스  초기화*/
	$('#searchSalesOrganType').combobox({
		width: 100,
		label : "상태",
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
		width: 140,
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});

	$('#searchCapitalWithdrawalDate').combobox({
		width: 80,
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
		width: 40,
		onClick : function(){
			//$.messager.progress();
			var param = makeSearchParam();
			//console.log("검색 쿼리 데이타");
			//console.log(param);
			
			returnp.api.call("selectGiftCardSalesOrgans", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					setListColumnHeader(param.searchNodeType);
					$('#data_list').datagrid({
						data : res,
						title : '[검색 결과] ' + res.total + " 개의 결과가 검색되었습니다",
					});
					setListPager();
				}else {
					$.messager.alert(res.message, res.data);
				}
			});
		}
	});
	
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		width: 40,
		onClick : function(){
			/*$('#searchForm').form('clear');*/
			$('#searchNodeStatus').combobox('select', 0);
			$('#searchKeywordType').combobox('select', 0);
			$('#searchKeyword').textbox('clear');
			$('#searchDateStart').datetimebox('clear');
			$('#searchDateEnd').datetimebox('clear');
		}
	});
	
	/* 노드 데이타그리드   초기화*/
	$('#data_list').datagrid({
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
		  				openCapitalForm("modify");
		  				break;
		  			case "remove":
		  				removeGiftCardSalesOrgan();
		  				break;
		  			}
		  		},
		  		itemHeight : 27
		  	});
		  	
		  	var menus = [  '수정', '삭제' ];
		  	var icons = ['icon-edit','icon-remove'];
		  	var actions = ['modify','remove'];
		  	
		  	for(var i=0; i<menus.length; i++){
		  		cmenu.menu('appendItem', {
		  			data : row,
		  			no : row.memberNo,
		  			text:  "<strong>[" + row.organName + " 조직 ]</strong>"  + " " + menus[i],
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
	
	/*폼 초기화 */
	
	$('#capitalProductName').textbox({
		//label : roundLabel("캐피탈 상품 이름"),
		label : "<b>* 캐피탈 상품 이름</b>",
		labelPosition : 'top',
		labelWidth :130,
		width: 600
	});
	
	
	$('#create_btn').linkbutton({
		onClick : function(){
			var param = makeSearchParam();
			submitGiftCardSalesOrganForm();
		},
		iconCls:'icon-ok'
	});
	$('#reset_create_btn').linkbutton({
		onClick : function(){
			$("#createGiftCardSalesOrganForm").form("reset");
		}
	});
}

function setListPager(){
	var pager = $('#data_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#data_list').datagrid('unselectAll');
            	$('#data_list').datagrid('uncheckAll');
            	openCapitalForm("create"); // 본사 생성
            }
        }],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
        onSelectPage:function(page,rows){        	
        	var opts = $('#data_list').datagrid('options');
        	opts.pageSize=rows;
        	opts.pageNumber = page;
        	realodPage();
    	}
    }); 
}

function closeCapitalForm(){
	$("#createProdcutLayout").layout("collapse", "south");
	$("#createProdcutForm").form("reset");
}

function openCapitalForm(){
	$("#createProdcutLayout").layout("expand", "south");
	$("#createProdcutForm").form("reset");
}

function realodPage(){
	$('#search_btn').click();
}

$(document).ready(function(){
	$('#search_btn').click();
});
