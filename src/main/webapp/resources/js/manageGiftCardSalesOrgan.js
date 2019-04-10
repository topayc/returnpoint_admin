		columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'giftCardSalesOrganNo',width:65,align:'center',title : '등록 No',hidden:false},
			    {field:'organType',width:65,align:'center',title : '구분', formatter : organTypeFormatter},
			    {field:'parentOrganNo',width:65,align:'center',title : '소속 No', hidden:true},
			    {field:'parentOrganName',width:160,align:'center',title : '소속'},
			    {field:'organName',width:160,align:'center',title : '사업장'},
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
	
	closeGiftSalesOrganform();
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
		width: 150,
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	/* 노드 상태 셀렉트 박스  초기화*/
	$('#searchSalesOrganStatus').combobox({
		width: 150,
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
		width: 150,
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
	    labelPosition: 'top',
	    width: 170
	});
	
	/* 검색 종료일 갤린더 박스  초기화*/
	$('#searchDateEnd').datebox({	  
	    prompt : "검색 종료 일자",
	    labelPosition: 'top',
	    width: 170
	});
	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
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
		},
		iconCls:'icon-search'
	});
	
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
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
		  				openGiftSalesOrganForm("modify");
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
	
	$('#organPassword').textbox({
		label : roundLabel("비밀 번호"),
		prompt: '비밀번호',
		width: 600
	});
	
	$('#parentOrganName').textbox({
		label : roundLabel("소속 이름"),
		prompt: '검색 이름',
		width: 600,
		editable : false
	});
	
	$('#parentOrganNo').textbox({
		label : roundLabel("소속 총판"),
		prompt: '총판 검색 ',
		width: 600,
		editable : false
	});
	
	$('#organCode').textbox({
		label :roundLabel("조직 코드 "),
		editable: false,
		prompt: '자동생성 - 입력할 필요 없음   ',
		width: 600
	});
	 
	$('#organOwner').textbox({
		label :roundLabel("사업 명의"),
		prompt: '사업자 명의자',
		width: 600
	});
	
	$('#organName').textbox({
		label :roundLabel("사업 이름"),
		prompt: '사업체 이름',
		width: 600
	});
	
	$('#organBusinessNumber').textbox({
		label :roundLabel("사업 번호"),
		prompt: '사업자 번호',
		width: 600
	});
	
	$('#organStatus').combobox({
		label :roundLabel("조직 상태"),
		prompt: '조직 상태',
		width: 600,
		labelPosition : 'left',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		multiple:false,
		required:true,
	});
	
	$('#saleOrganSaleFeeRate').numberbox({
		label :roundLabel("판매 수수료"),
		width: 600,
		min : 0,
		max : 1,
		precision:2,
		prompt: '상품권 판매 수수료 - 소숫점 입력'
	});
	
	$('#organEmail').textbox({
		label :roundLabel("이메일"),
		width: 600,
		prompt: '이메일'
	});
	
	$('#organPhone').textbox({
		label :roundLabel("핸드폰"),
		width: 600,
		prompt: '핸드폰'
	});
	
	$('#organTel').textbox({
		label :roundLabel("전화 번호"),
		width: 600,
		prompt: '전화번호'
	});
	
	$('#organAddr').textbox({
		label :roundLabel("사업 주소"),
		width: 600,
		prompt: '주소'
	});
	
	$('#organBankName').textbox({
		label :roundLabel("은행 이름"),
		width: 600,
		prompt: '은행 이름'
	});
	
	$('#organBankAccount').textbox({
		label :roundLabel("은행 계좌"),
		width: 600,
		prompt: '은행 계좌'
	});
	
	$('#organBankAccountOwner').textbox({
		label :roundLabel("계좌주"),
		width: 600,
		prompt: '계좌주'
	});
	
	$('#create_btn').linkbutton({
		onClick : function(){
			//$.messager.progress();
			var param = makeSearchParam();
			//console.log("검색 쿼리 데이타");
			//console.log(param);
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
            	openGiftSalesOrganForm("create"); // 본사 생성
            	//loadGiftCardSalesOrganCreateForm();
            }
        }/*,{
            iconCls:'icon-add',
            handler:function(){
            	$('#data_list').datagrid('unselectAll');
            	$('#data_list').datagrid('uncheckAll');
            	openGiftSalesOrganForm("11"); //총판 생성
            	//loadGiftCardSalesOrganCreateForm();
            }
        },{
            iconCls:'icon-add',
            handler:function(){
            	$('#data_list').datagrid('unselectAll');
            	$('#data_list').datagrid('uncheckAll');
            	openGiftSalesOrganForm("12"); //판매점 생성
            	//loadGiftCardSalesOrganCreateForm();
            }
        }*//*,{
            iconCls:'icon-edit',
            handler:function(){
              loadAgencyModifyForm();
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removeGiftCardSalesOrgan();
            }
        },{
            iconCls:'icon-more',
            handler:function(){
            	var node = $('#data_list').datagrid('getSelected');
            	if (!node) {
            		 $.messager.alert('알림','자세히 보실 항목을  선택해주세요');
            		 return;
            	}
            }
        }*/],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep','refresh','info'],
        onSelectPage:function(page,rows){        	
        	var opts = $('#data_list').datagrid('options');
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
	$('#data_list').datagrid({
		columns : columns
	});
}

function formatDate(date) {
	var sDate;
	if (date < 10) {
		sDate = "0"+ date;
	}else {
		sDate = date.toString();
	}
	
	return sDate;
}

/**
 * 검색 실행시 필요한 쿼리 데이타 구성 
 * @returns
 */
function makeSearchParam(){
	
	var param = $('#searchForm').serializeObject();
	var opts = $('#data_list').datagrid('options');
	var total = $('#data_list').datagrid('getData').total;
	
	$.extend(param, {
		pagination : opts.pagination,
		pageSize : opts.pageSize,
		page : opts.pageNumber,
		total : total,
		offset : (opts.pageNumber-1) * opts.pageSize
	});
	
	return param;
}

function submitGiftCardSalesOrganForm(){
	var params = makeFormData();
	var action = $("#action").val();
	var apiName = null;
	var organType = $('#organType').combobox("getValue");
	
	/*본사의 수정 및 생성시에는 아래의 프로퍼티를 제거*/
	if (organType == "10"){
		delete params.parentOrganNo;
		delete params.parentOrganName;
	}
	
	if (action == "create") {
		delete params.giftCardSalesOrganNo;
	}
	
	console.log(params);
	
	var valid = true;
	var fieldName = '';
	
	for (var prop in params){
		if (params.hasOwnProperty(prop)) {
			if (params[prop] == '' && prop !=  "organCode") {
				valid = false;
				fieldName = prop;
				break;
			}
		}
	}
	
	var fieldKorName = '';
	if (!valid) {
		if (organType == "10" && ( fieldName == "parentOrganNo" || fieldName == "parentOrganName")) {
		} else {
			fieldKorName = $("#" +fieldName).prev().children("span").html();
			$.messager.alert('알림', "<strong><span style = 'color : #FF3232'> " + fieldKorName + "</span></strong> 항목이 입력되지 않았습니다");
			return false;
			
		}
	}
	
	if (action == "create") {
		apiName = "createGiftCardSalesOrgan";
		delete params.giftCardSalesOrganNo;
	}else if (action =="modify"){
		apiName = "updateGiftCardSalesOrgan"
	}
	
	returnp.api.call(apiName, params, function(res){
		console.log(res)
		if (res.resultCode  == "100") {
			$.messager.confirm( '알림', res.message, function(r){ closeGiftSalesOrganform(); realodPage();});
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function openGiftSalesOrganForm(action){
	$("#createGiftCardSalesOrganForm").form("reset");
	$("#action").val(action);
	$("#giftCardSalesOrganNo").val('');
	var node; 
	
	var searchOptions = {};
	if (action == "modify"){
		$("#reset_create_btn").hide();
		node = $('#data_list').datagrid('getSelected');
		if (!node) {
			 $.messager.alert('알림','수정하실 상품권 조직을 선택해주세요');
			 return;
		}
		
		returnp.api.call('selectGiftCardSalesOrgans', {giftCardSalesOrganNo : node.giftCardSalesOrganNo}, function(res){
			console.log(res)
			if (res.resultCode  == "100") {
				if (res.rows.length == 1) {
					$('#createGiftCardSalesOrganForm').form('load',res.rows[0]);
				}
			}else {
				$.messager.alert('오류 발생', res.message);
			}
		});
	}else {
		$("#reset_create_btn").show();
	}
	$('#organType').combobox({
		label :roundLabel("조직 타입"),
		prompt: '조직 타입',
		width: 600,
		labelPosition : 'left',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		multiple:false,
		required:true,
		onSelect : function(record){
			console.log(record);
			var searchOptions = {};
			if (record.value == "11"){
				searchOptions.label = roundLabel("소속 본사");
		        searchOptions.prompt = "소속 본사";
		        searchOptions.icons = [{
		            iconCls:'icon-search',
		            handler: function(e){
		                var v = $(e.data.target).textbox('getValue');
		                loadNodeListView({
		                    targetElem : "#dlgForm2",
		                    title : "상품권 영업 본사 검색",
		                    queryOptions : {
		                        memberEmail : v,
		                        viewReqName : "nodeList",
		                        searchNodeType :  "10",
		                        width : '65%'
		                        
		                    }
		                },function callback(selNode){
		                    $(e.data.target).textbox('setValue', selNode.giftCardSalesOrganNo);
		                    $("#parentOrganName").textbox('setValue', selNode.organName);
		                });
		            }
		        }];
		        $("#parentOrganNo").textbox("show");
		        $("#parentOrganName").textbox("show");
		        $("#parentOrganNo").textbox(searchOptions);
		    }else if (record.value == "12"){
		        searchOptions.label = roundLabel("소속 총판");
		        searchOptions.prompt = "소속 총판";
		        searchOptions.icons = [{
		            iconCls:'icon-search',
		            handler: function(e){
		                var v = $(e.data.target).textbox('getValue');
		                loadNodeListView({
		                    targetElem : "#dlgForm2",
		                    title : "상품권 영업 총판 검색",
		                    queryOptions : {
		                        memberEmail : v,
		                        viewReqName : "nodeList",
		                        searchNodeType :  "11",
		                        width : '65%'
		                        
		                    }
		                },function callback(selNode){
		                    $(e.data.target).textbox('setValue', selNode.giftCardSalesOrganNo);
		                    $("#parentOrganName").textbox('setValue', selNode.organName);
		                });
		            }
		        }];
		        $("#parentOrganNo").textbox("show");
		        $("#parentOrganName").textbox("show");
		        $("#parentOrganNo").textbox(searchOptions);
		    }else if (record.value == "10") {
		    	$("#parentOrganNo").textbox("hide");
		    	$("#parentOrganName").textbox("hide");
		    }
			
		}
	});
	$("#createOrglayout").layout("expand", "south");
}

function closeGiftSalesOrganform(){
	$("#createOrglayout").layout("collapse", "south");
	$("#createGiftCardSalesOrganForm").form("reset");
}



function makeFormData(){
	var param = $("#createGiftCardSalesOrganForm").serializeObject();
	return param;
}


function removeGiftCardSalesOrgan(){
	var node = $('#data_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        			giftCardSalesOrganNo : node.giftCardSalesOrganNo
        	}
        	returnp.api.call("deleteGiftCardSalesOrgan", param, function(res){
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
	$('#search_btn').click();
});
