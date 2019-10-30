
var summary_columns = [[
	    {field:'searchDate',width:20,align:'center',title : '검색 기준 연/월/일',formatter : addBoldFomatter},
	    {field:'withdrawalCase',width:10,align:'center',title : '출금 건수 ', formatter : addBoldFomatter},
	    {field:'withdrawalSum',width:20,align:'center',title : '총 출금 요청 금액', formatter  : numberFormatter},
	    {field:'completeWithdrawalAmountSum',width:20,align:'center',title : '출금 완료', formatter  : numberGreenFormatter},
	    {field:'ingWithdrawalAmountSum',width:20,align:'center',title : '출금 처리중', formatter  : numberFormatter},
	    {field:'holdWithdrawalAmountSum',width:20,align:'center',title : '출금 보류', formatter  : numberFormatter},
	    {field:'userCancelWithdrawalAmountSum',width:20,align:'center',title : '사용자 출금 취소', formatter  : numberFormatter},
	    {field:'adminCancelWithdrawalAmountSum',width:20,align:'center',title : '관리자 출금 취소', formatter  : numberFormatter},
	    {field:'ss1',width:40,align:'center',title : '비고'},
	 ]];


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
	

	/* 검색 시작일 갤린더 박스  초기화*/
	$('#searchDateStart').datetimebox({
		 prompt : "검색 시작일",
	    showSeconds: true,
	    labelPosition: 'top',
	    width: 140,
	    formatter :  searchDateFomatter
	});
	
	/* 검색 종료일 갤린더 박스  초기화*/
	$('#searchDateEnd').datetimebox({
		 prompt : "검색 종료일",
		showSeconds: true,
	    labelPosition: 'top',
	    width: 140,
	    formatter :  searchDateFomatter
	});
	
	$('#searchWithdrawalStatus').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 130
	});

	$('#searchWithdrawalDateType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 130
	});
	
	$('#searchWithdrawalLowLimit').textbox({
		width: 120,
		prompt : "최소 금액" ,
		labelPosition: 'top'
	});

	$('#searchWithdrawalMaxLimit').textbox({
		width: 120,
		prompt : "최대 금액 " ,
		labelPosition: 'top'
	});
	
	/* 검색어 입력 박스 초기화 */
	$('#searchKeyword').textbox({
		width: 180,
		prompt : "이름 / 아이디 " ,
		  labelPosition: 'top'
	});
	
	$('#search_total_year_btn').linkbutton({
		onClick : function(){
			$('#node_list').datagrid('loadData', []);
			$('#node_list').datagrid('getPanel').panel('setTitle', "");
			
			var param = {searchType  : "year"}
			returnp.api.call("selectTotalPointWithdrawalReports", param, function(res){
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
						$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					$('#summary_table').datagrid({
						data : res.rows,
						title : '[연도별 매출 총계] ',
					});
					var total1 = 0
					var total2  = 0;
					 var total3 = 0; 
					 var total4 = 0; 
					 var total5 = 0; 
					 var total6 = 0; 
					 var total7 = 0; 
                    for (var i = 0; i < res.rows.length; i++) {
                    	total1 += parseInt(res.rows[i].withdrawalCase);
                    	total2 +=parseInt(res.rows[i].withdrawalSum);
                    	total3 +=parseInt(res.rows[i].completeWithdrawalAmountSum);
                    	total4 +=parseInt(res.rows[i].ingWithdrawalAmountSum);
                    	total5 +=parseInt(res.rows[i].holdWithdrawalAmountSum);
                    	total6 +=parseInt(res.rows[i].userCancelWithdrawalAmountSum);
                    	total7 +=parseInt(res.rows[i].adminCancelWithdrawalAmountSum);
                    }
					$('#summary_table').datagrid({
						title : '[연도별 매출 총계]  : ' +numberGreenFormatter(total3),
					});
					$('#summary_table') .datagrid( 'appendRow', { 
						 searchDate : "총계", 
				         withdrawalCase : total1, 
				         withdrawalSum : total2, 
				         completeWithdrawalAmountSum :  total3, 
				         ingWithdrawalAmountSum :total4 ,
				         holdWithdrawalAmountSum :total5 ,
				         userCancelWithdrawalAmountSum :total6 ,
				         adminCancelWithdrawalAmountSum :total7 ,
					});
					setListPager2();
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
		},
	/*	iconCls:'icon-search'*/
	});
	
	$('#search_total_daily_btn').linkbutton({
		onClick : function(){
			$('#node_list').datagrid('loadData', []);
			$('#node_list').datagrid('getPanel').panel('setTitle', "");
			
			var param = {searchType  : "daily"}
			returnp.api.call("selectTotalPointWithdrawalReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
						$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					$('#summary_table').datagrid({
						data : res,
						title : '[일별 매출 총계] ' ,
					});
					var total1 = 0
					var total2  = 0;
					 var total3 = 0; 
					 var total4 = 0; 
					 var total5 = 0; 
					 var total6 = 0; 
					 var total7 = 0; 
                    for (var i = 0; i < res.rows.length; i++) {
                    	total1 += parseInt(res.rows[i].withdrawalCase);
                    	total2 +=parseInt(res.rows[i].withdrawalSum);
                    	total3 +=parseInt(res.rows[i].completeWithdrawalAmountSum);
                    	total4 +=parseInt(res.rows[i].ingWithdrawalAmountSum);
                    	total5 +=parseInt(res.rows[i].holdWithdrawalAmountSum);
                    	total6 +=parseInt(res.rows[i].userCancelWithdrawalAmountSum);
                    	total7 +=parseInt(res.rows[i].adminCancelWithdrawalAmountSum);
                    }
					$('#summary_table').datagrid({
						title : '[일별 매출 총계]  : ' +numberGreenFormatter(total3),
					});
					$('#summary_table') .datagrid( 'appendRow', { 
						 searchDate : "총계", 
				         withdrawalCase : total1, 
				         withdrawalSum : total2, 
				         completeWithdrawalAmountSum :  total3, 
				         ingWithdrawalAmountSum :total4 ,
				         holdWithdrawalAmountSum :total5 ,
				         userCancelWithdrawalAmountSum :total6 ,
				         adminCancelWithdrawalAmountSum :total7 ,
					});
					setListPager2();
				}else {
					$.messager.alert('오류 발생', message);
				}
			});
		},
		/*	iconCls:'icon-search'*/
	});
	$('#search_total_month_btn').linkbutton({
		onClick : function(){
			$('#node_list').datagrid('loadData', []);
			$('#node_list').datagrid('getPanel').panel('setTitle', "");
			
			var param = {searchType  : "month"}
			returnp.api.call("selectTotalPointWithdrawalReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
						$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					$('#summary_table').datagrid({
						data : res,
						title : '[월별 매출 총계] ' ,
					});
					var total1 = 0
					var total2  = 0;
					 var total3 = 0; 
					 var total4 = 0; 
					 var total5 = 0; 
					 var total6 = 0; 
					 var total7 = 0; 
                    for (var i = 0; i < res.rows.length; i++) {
                    	total1 += parseInt(res.rows[i].withdrawalCase);
                    	total2 +=parseInt(res.rows[i].withdrawalSum);
                    	total3 +=parseInt(res.rows[i].completeWithdrawalAmountSum);
                    	total4 +=parseInt(res.rows[i].ingWithdrawalAmountSum);
                    	total5 +=parseInt(res.rows[i].holdWithdrawalAmountSum);
                    	total6 +=parseInt(res.rows[i].userCancelWithdrawalAmountSum);
                    	total7 +=parseInt(res.rows[i].adminCancelWithdrawalAmountSum);
                    }
					$('#summary_table').datagrid({
						title : '[월별 매출 총계]  : ' +numberGreenFormatter(total3),
					});
					$('#summary_table') .datagrid( 'appendRow', { 
						searchDate : "총계", 
				         withdrawalCase : total1, 
				         withdrawalSum : total2, 
				         completeWithdrawalAmountSum :  total3, 
				         ingWithdrawalAmountSum :total4 ,
				         holdWithdrawalAmountSum :total5 ,
				         userCancelWithdrawalAmountSum :total6 ,
				         adminCancelWithdrawalAmountSum :total7 , });
					setListPager2();
				}else {
					$.messager.alert('오류 발생', message);
				}
			});
		},
	/*	iconCls:'icon-search'*/
	});
	

	
	$('#search_btn').linkbutton({
		onClick : function(){
			$('#node_list').datagrid('loadData', []);
			$('#node_list').datagrid('getPanel').panel('setTitle', "");
			
			var param = makeSearchParam();
			console.log(param)
			if ( param.searchWithdrawalLowLimit != ''){
				if (!$.isNumeric(param.searchWithdrawalLowLimit)) {
					$.messager.alert('알림', "최소 금액 검색은 숫자만 입력해야 합니다.");
					return;
				}
			}
			
			if (param.searchWithdrawalMaxLimit != ''){
				if (!$.isNumeric(param.searchWithdrawalMaxLimit)) {
					$.messager.alert('알림', "최대 금액 검색은 숫자만 입력해야 합니다.");
					return;
				}
			}
			
			if ((param.searchDateStart == '' &&  param.searchDateEnd == "") || (param.searchDateStart != '' &&  param.searchDateEnd != "") ){
			}else {
				$.messager.alert('알림', "검색 시작일 혹은 검색 종료일을 설정해주세요");
				return;
			} 
			var oriDateEnd;
			if (param.searchDateStart != '' &&  param.searchDateEnd != "") {
				oriDateEnd = param.searchDateEnd;
				var dateArr = param.searchDateEnd.split('-');
				var searchDateEnd = new Date(dateArr[0], dateArr[1], dateArr[2]);
				param.searchDateEnd = searchDateEnd.getFullYear() + "-" + searchDateEnd.getMonth() + "-" + (searchDateEnd.getDate() + 1)
			}
			console.log("매출 조회 파라메터");
			console.log(param);
			returnp.api.call("selectPointWithdrawalReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
						$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					$('#summary_table').datagrid({
						data : res
					});
					var total1 = 0
					var total2  = 0;
					 var total3 = 0; 
					 var total4 = 0; 
					 var total5 = 0; 
					 var total6 = 0; 
					 var total7 = 0; 
                    for (var i = 0; i < res.rows.length; i++) {
                    	total1 += parseInt(res.rows[i].withdrawalCase);
                    	total2 +=parseInt(res.rows[i].withdrawalSum);
                    	total3 +=parseInt(res.rows[i].completeWithdrawalAmountSum);
                    	total4 +=parseInt(res.rows[i].ingWithdrawalAmountSum);
                    	total5 +=parseInt(res.rows[i].holdWithdrawalAmountSum);
                    	total6 +=parseInt(res.rows[i].userCancelWithdrawalAmountSum);
                    	total7 +=parseInt(res.rows[i].adminCancelWithdrawalAmountSum);
                    }
					var shStr = "";
					if (param.searchDateStart != ''  ) {
						shStr = '[' + param.searchDateStart + " ~ "+  oriDateEnd + "] 매출 총계 :";
					}else {
						shStr = "[전체 기간 매출 총계] : "
					}
					
					$('#summary_table').datagrid({
						title : shStr + numberGreenFormatter(total3),
					});
					  $('#summary_table') .datagrid( 'appendRow', 
							  { 
						         searchDate : "총계", 
						         withdrawalCase : total1, 
						         withdrawalSum : total2, 
						         completeWithdrawalAmountSum :  total3, 
						         ingWithdrawalAmountSum :total4 ,
						         holdWithdrawalAmountSum :total5 ,
						         userCancelWithdrawalAmountSum :total6 ,
						         adminCancelWithdrawalAmountSum :total7 ,
						       });
					setListPager2();
				}else {
					$.messager.alert('오류 발생', message);
				}
			});
		},
	/*	iconCls:'icon-search'*/
	});
	
	
	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
			$('#searchForm').form('reset');
		},
		width : 50,
	});
	
	
	var editData = [
		{key:"1",value:"입금(결제) 확인중"},
		{key:"2",value:"입금(결제) 확인 완료"},
		{key:"3",value:"입금(결제) 취소 확인중"},
		{key:"4",value:"입금(결제) 취소 완료"}
	]
	
	/* 노드 데이타그리드   초기화*/
	$('#summary_table').datagrid({
		singleSelect:true,
		title : '[요약]',
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
		onSelect : function(index,row){
			$('#node_list').datagrid('loadData', []);
			$('#node_list').datagrid('getPanel').panel('setTitle', "");
			loadPointWithdrawals(index, row);
		},
		onLoadSuccess : function(){
			//$(this).datagrid('freezeRow',0).datagrid('freezeRow',1);
		},	
		onRowContextMenu : function(e, index, row){
			e.preventDefault();
		  	$(this).datagrid("selectRow", index);
		},
	    columns: summary_columns
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
		            /*	if (node.withdrawalStatus == "2" ||  node.withdrawalStatus == "4" || node.withdrawalStatus == "5") {
		            		$.messager.alert('알립', "해당 항목은 상태를 변경할 수 없습니다.");
		            		return;
		            	}*/
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
	setListPager2();
	setListPager();
}


function loadPointWithdrawals(index, row){
	//var param = {searchDateStart : row.searchDate, searchDateEnd : row.searchDate};
	if (row.searchDate == '총계') {return;}
	var param = {searchDate : row.searchDate};
	var opts = $('#node_list').datagrid('options');
	var total = $('#node_list').datagrid('getData').total;
	if (index == 'pager' ){
		$.extend(param, {
			pagination : opts.pagination,
			pageSize : opts.pageSize,
			page : opts.pageNumber,
			total : total,
			offset : (opts.pageNumber-1) * opts.pageSize
		});
	}else {
		opts.pageNumber = 1;
		$.extend(param, {
			pagination : opts.pagination,
			pageSize : opts.pageSize,
			page : opts.pageNumber,
			total : total,
			offset : (opts.pageNumber-1) * opts.pageSize
		});
	}
	
	$.extend(param, $('#searchForm').serializeObject());
	returnp.api.call("loadPointWithdrawals", param, function(res){
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
}

function setListPager2(){
	var pager = $('#summary_table').datagrid().datagrid('getPager');
	pager.pagination({
		/*displayMsg : ' {from} to {to} of {total}',*/
		buttons:[{
            iconCls:'icon-large-chart',
            text  : "<span style = 'font-weight: 600'>차트 보기</span>",
            handler:function(){
            	var rows = $('#summary_table').datagrid("getRows");
            	if (rows.length == 0) return;
              	var cartes = [];
            	var withdrawalCase = [] 
            	var withdrawalSum = [];
            	var completeWithdrawalAmountSum = [];
            	var ingWithdrawalAmountSum = [];
            	var holdWithdrawalAmountSum = [];
            	var userCancelWithdrawalAmountSum = [];
            	var adminCancelWithdrawalAmountSum = [];
            	for (var i = 0; rows.length; i++){
            		if (i  == rows.length - 1) break;
            		cartes.push(rows[i].searchDate);
            		withdrawalCase.push(rows[i].withdrawalCase);
            		withdrawalSum.push(rows[i].withdrawalSum);
            		completeWithdrawalAmountSum.push(rows[i].completeWithdrawalAmountSum);
            		ingWithdrawalAmountSum.push(rows[i].ingWithdrawalAmountSum);
            		holdWithdrawalAmountSum.push(rows[i].holdWithdrawalAmountSum);
            		userCancelWithdrawalAmountSum.push(rows[i].userCancelWithdrawalAmountSum);
            		adminCancelWithdrawalAmountSum.push(rows[i].adminCancelWithdrawalAmountSum);
            	}
            	
            	$('#chart_container').dialog({
            		title: "chart",
            		width: 1300,
            		height: 700,
            		closed: false,
            		cache: false,
            		modal: true,
            		buttons:  [ {
            			text : '확인',
            			iconCls : 'icon-ok',
            			handler : function() {
            				$("#chart_container").dialog('close');
            			}
            		}]
            	});
            	
            	
            	Highcharts.chart('chart_container', {
            		chart : {
            			zoomType : "x",
            			
            		},
            		title: {
            	        text: ''
            	    },
            	    subtitle: {
            	        text: ''
            	    },
            	    tooltip: {
            	        shared: true,
            	        crosshairs: true
            	    },
            	    xAxis: {
                        categories: cartes,
                        
                    },
            	    yAxis: {
            	        title: {
            	            text: '금액'
            	        }
            	    },
            	    legend: {
            	        layout: 'vertical',
            	        align: 'right',
            	        verticalAlign: 'middle'
            	    },
            	    plotOptions: {
            	        series: {
            	            label: {
            	                connectorAllowed: false
            	            },
            	        }
            	    },


            	    series: [/*{
            	        name: '출금 건수',
            	        saleCounts
            	    },*/ {
            	        name: '총 출금 요청 금액',
            	        data:withdrawalSum
            	    }, {
            	        name: '출금 완료',
            	        data: completeWithdrawalAmountSum
            	    }
            	    , {
            	        name: '출금 처리중',
            	        data:ingWithdrawalAmountSum
            	    }
            	    , {
            	    	name: '출금  보류',
            	    	data:holdWithdrawalAmountSum
            	    }
            	    , {
            	    	name: '사용자 출금 취소',
            	    	data:userCancelWithdrawalAmountSum
            	    }
            	    , {
            	    	name: '관리자 출금 취소',
            	    	data:adminCancelWithdrawalAmountSum
            	    }
            	    
            	    ],

            	    responsive: {
            	        rules: [{
            	            condition: {
            	                maxWidth: 500
            	            },
            	            chartOptions: {
            	                legend: {
            	                    layout: 'horizontal',
            	                    align: 'center',
            	                    verticalAlign: 'bottom'
            	                }
            	            }
            	        }]
            	    }

            	});
            }
        }],
        layout:[ ],
    }); 
}

function setListPager(){
	var pager = $('#node_list').datagrid().datagrid('getPager');
	pager.pagination({
		/*displayMsg : ' {from} to {to} of {total}',*/
		buttons:[
			{
	            iconCls:'icon-add',
	            text : "엑셀 변환",
	            handler:function(){
	            	gridToExcel('#node_list','payment_transactionl.xls');
	            }
	        },{
            iconCls:'icon-chart',
            handler:function(){
            	$('#node_list').datagrid('unselectAll');
            	$('#node_list').datagrid('uncheckAll');
            }
        }],
        layout:['list','sep','first','prev','sep','links','sep','next','last','sep' /*,'refresh','info'*/ ],
        onSelectPage:function(page,rows){        	
        	var opts = $('#node_list').datagrid('options');
        	opts.pageSize=rows;
        	opts.pageNumber = page;
        	
        	var node = $('#summary_table').datagrid('getSelected');
        	if (!node) {
        		 $.messager.alert('알림','선택이 필요합니다.');
        		 return;
        	}
        	loadPointWithdrawals("pager", node);
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

$(function(){
	initView();
/*	$('#node_list').datagrid().datagrid('enableCellEditing');
	setListPager();
	realodPage();*/
})
