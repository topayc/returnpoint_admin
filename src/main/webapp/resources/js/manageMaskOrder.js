
var summary_columns = [[
	    {field:'searchDate',width:20,align:'center',title : '검색 기준 연/월/일',formatter : addBoldFomatter},
	    {field:'totalCount',width:10,align:'center',title : '구매 건수 ', formatter : numberFormatter},
	    {field:'totalPayAmount',width:20,align:'center',title : '구매 금액 소계', formatter : numberFormatter},
	    {field:'totalAccPointAmount',width:20,align:'center',title : '적립 포인트 소계', formatter : numberFormatter},
	    {field:'ss1',width:40,align:'center',title : '비고'},
	 ]];


var columns = [[
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
	    {field:'shopProductOrderNo',width:15,align:'center',title : '번호',hidden:false},
	    {field:'memberNo',width:15,align:'center',title : '회원번호',hidden:true},
	    {field:'memberName',width:40,align:'center',title : '회원 이름'},
	    {field:'memberPhone',width:45,align:'center',title : '회원 전화'},
	    {field:'memberEmail',width:45,align:'center',title : '회원 이메일'},
	    {field:'productName',width:60,align:'center',title : '상품명'},
	    {field:'productPrice',width:35,align:'center',title : '상품 1개 단가', formatter : issueTypeFormatter2},
	    {field:'productUnit',width:35,align:'center',title : '판매 단위 ', formatter : issueTypeFormatter2},
	    {field:'productQty',width:20,align:'center',title : '갯수', formatter : issueTypeFormatter2},
	    {field:'orderPrice',width:35,align:'center',title : '총구매 가격', formatter : issueTypeFormatter2},
	    {field:'gPointRate',width:35,align:'center',title : '제공 GPOINT 비율', formatter : issueTypeFormatter2},
	    {field:'gPointRateAmount',width:35,align:'center',title : '제공 G POINT', formatter : issueTypeFormatter2},
	    {field:'orderStatus',width:35,align:'center',title : '주문 상태', formatter : issueTypeFormatter2},
	    {field:'deliveryAddress',width:35,align:'center',title : '배송 주소 ', formatter : issueTypeFormatter2},

	    {field:'createTime',width:40,align:'center',title : '등록일',formatter : dateFormatter}
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
					$('#search_btn').click();
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
	
	$('#searchStatus').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 150
	});
	
	/* 검색어 입력 박스 초기화 */
	$('#searchKeyword').textbox({ 
		labelPosition : 'top',
		width: 180,
		prompt : "" ,
	});
	
	
	$('#search_total_year_btn').linkbutton({
		onClick : function(){
			$('#searchForm').form('reset');
			$('#node_list').datagrid('loadData', []);
			$('#node_list').datagrid('getPanel').panel('setTitle', "");
			
			var param = {searchType  : "year"}
			returnp.api.call("selectOrderReports", param, function(res){
				if (res.resultCode == "100") {
					if (res.rows.length < 1) {
					}
					setSummary(res, '연도별 포인트코드 발급요청 적립 총계');
				}else {
					$.messager.alert('오류 발생', res.message);
				}
			});
		},
	/*	iconCls:'icon-search'*/
	});
	
	$('#search_total_daily_btn').linkbutton({
		onClick : function(){
			$('#searchForm').form('reset');
			$('#node_list').datagrid('loadData', []);
			$('#node_list').datagrid('getPanel').panel('setTitle', "");
			
			var param = {searchType  : "daily"}
			returnp.api.call("selectOrderReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					if (res.rows.length < 1) {
						//$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					setSummary(res, '일별 포인트코드 발급요청 적립 총계');
				}else {
					$.messager.alert('오류 발생', message);
				}
			});
		},
		/*	iconCls:'icon-search'*/
	});
	$('#search_total_month_btn').linkbutton({
		onClick : function(){
			$('#searchForm').form('reset');
			$('#node_list').datagrid('loadData', []);
			$('#node_list').datagrid('getPanel').panel('setTitle', "");
			
			var param = {searchType  : "month"}
			returnp.api.call("selectOrderReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
						//$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					setSummary(res, '월별 포인트코드 발급요청 적립 총계');
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
			console.log("1===============");
			console.log(param);
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
			returnp.api.call("selectPeriodOrderReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
						//$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					$('#summary_table').datagrid({
						data : res
					});
					
					var shStr = "";
					if (param.searchDateStart != ''  ) {
						shStr = '[' + param.searchDateStart + " ~ "+  oriDateEnd + "] 포인트코드 발급요청 적립 총계 :";
					}else {
						shStr = "[전체 기간 구매 적립 총계] : "
					}
					setSummary(res, shStr);
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
	
	
	
	/* 노드 데이타그리드   초기화*/
	$('#summary_table').datagrid({
		singleSelect:false,
		title : '[요약]',
		ctrlSelect : true,
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
			loadOrders(index, row);
		},
		onLoadSuccess : function(){
			//$(this).datagrid('freezeRow',0).datagrid('freezeRow',1);
		},	
		onRowContextMenu : function(e, index, row){
			e.preventDefault();
		 /* 	$(this).datagrid("selectRow", index);*/
		},
	    columns: summary_columns
	});
	
	setSummaryColumn();
	
	/* 노드 데이타그리드   초기화*/
	$('#node_list').datagrid({
		title : '[검색 결과]',
		singleSelect:false,
		collapsible:false,
		//autoRowHeight: false,
		fitColumns:true,
		selectOnCheck : true,
		ctrlSelect : true,
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
				 itemHeight : 27
			 });

			
			 cmenu.menu('show', {
				 left:e.pageX,
				 top:e.pageY
			 })
		 },
	    columns: columns
	});
	setListPager2();
	setListPager();
}

function setSummaryColumn(){
	if (adminId != 'topayc' && adminId != 'mira') {
		$('#summary_table').datagrid('hideColumn', 'totalDistTop');
		$('#summary_table').datagrid('hideColumn', 'totalDistMe');
	}
}

function deleteOrderRequests(){
	var params = {orders : []}
	var selectedRows =  $('#node_list').datagrid('getSelections');
	if (selectedRows.length < 1) {
		$.messager.alert('알림', "선택된 항목이 없습니다.");
	}
	
	for (var i = 0; i < selectedRows.length; i++){
		if (selectedRows[i].status != '1' && selectedRows[i].status != '2') {
			$.messager.alert(
				'알림', "삭제 요청이 잘못되었습니다.</br>" + 
				"발행번호 " +selectedRows[i].pointCodeIssueRequestNo + "</br>" + 
				"상태가 입금확인중 혹은 입금확인요청중인  요청건만 삭제할 수 있습니다."
			);
			return;
		}

		params.orders.push(selectedRows[i].shopProductOrderNo + "_" + selectedRows[i].memberNo + "_" + selectedRows[i].orderPrice + "_" + selectedRows[i].memberName)
	}
	
	
	returnp.api.call("deleteOrderRequests", params, function(res){
		$.messager.alert('알림', res.message);
		if (res.resultCode == "100") {
        	var node = $('#summary_table').datagrid('getSelected');
        	loadOrders("pager", node);
		}else {
		}
	});
}


function loadOrders(index, row){
	//var param = {searchDateStart : row.searchDate, searchDateEnd : row.searchDate};
	if (typeof row.searchDate == 'undefined' || row.searchDate == '총계') {return;}
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
	returnp.api.call("loadOrders", param, function(res){
		console.log(res);
		if (res.resultCode == "100") {
			$('#node_list').datagrid({
				data : res,
				title : '[검색 결과] ' + res.total + " 개의 결과가 검색되었습니다",
			});
			setListPager();
		}else {
			$.messager.alert('알림', res.message);
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
            	console.log(rows);
            	var cartes = [];
            	var totalCount = [] 
            	var totalPayAmount = [];
            	var totalAccPointAmount = [];
            	for (var i = 0; rows.length; i++){
            		if (i  == rows.length - 1) break;
            		cartes.push(rows[i].searchDate);
            		totalCount.push(rows[i].totalCount);
            		totalPayAmount.push(rows[i].totalPayAmount);
            		totalAccPointAmount.push(rows[i].totalAccPointAmount);
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
            	        name: '결제 건수',
            	        saleCounts
            	    },*/ {
            	        name: '적립 기준 금액 소계',
            	        data:totalPayAmount
            	    }, {
            	        name: '적립 소계',
            	        data: totalAccPointAmount
            	    }],

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
        }, {
            iconCls:'icon-add',
            text : "엑셀 변환",
            handler:function(){
                gridToExcel('#summary_table','point_code_sum.xls');
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
	            	gridToExcel('#node_list','point_coupons.xls');
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
        	loadOrders("pager", node);
    	}
    }); 
}

function setSummary(res, str){
	var totalCount = 0;
	var totalPayAmount = 0
	var totalAccPointAmount = 0; 
	var totalDepositAmount = 0; 
	var totalCompleteDeposit = 0; 
	var totalNotCompleteDeposit = 0; 
	var totalDistTop = 0; 
	var totalDistMe = 0; 
	for (var i = 0; i < res.rows.length; i++) {
		totalCount+=parseInt(res.rows[i].totalCount);
		totalPayAmount += parseFloat(res.rows[i].totalPayAmount);
		totalAccPointAmount+=parseFloat(res.rows[i].totalAccPointAmount);
		totalDepositAmount+=parseFloat(res.rows[i].totalDepositAmount);
		totalCompleteDeposit+=parseFloat(res.rows[i].totalCompleteDeposit);
		totalNotCompleteDeposit+=parseFloat(res.rows[i].totalNotCompleteDeposit);
		res.rows[i]['totalDistTop'] = parseInt(parseFloat(res.rows[i].totalCompleteDeposit) / 3 * 2.6)
		res.rows[i]['totalDistMe'] = parseInt(parseFloat(res.rows[i].totalCompleteDeposit) /3 * 0.4)
		totalDistTop+=res.rows[i]['totalDistTop'];
		totalDistMe+=res.rows[i]['totalDistMe'];
    }
	
	$('#summary_table').datagrid({
		data : res,
		title : '['+str+ '] ' ,
	});
	
	$('#summary_table').datagrid({
		title : '[' +str+ ']  : ' +numberGreenFormatter(totalAccPointAmount + "  /   " + totalPayAmount ),
	});
	
	$('#summary_table') .datagrid( 
		'appendRow', 
		{ 
			searchDate : "총계", 
			totalCount : totalCount, 
			totalPayAmount : totalPayAmount ,
			totalAccPointAmount :  totalAccPointAmount,
			totalDepositAmount : totalDepositAmount,
			totalCompleteDeposit : totalCompleteDeposit,
			totalNotCompleteDeposit : totalNotCompleteDeposit,
			totalDistTop : totalDistTop,
			totalDistMe: totalDistMe
			
		});
	setListPager2();
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


function realodPage(){
	$('#search_btn').click();
}

$(function(){
	initView();
	realodPage();
/*	$('#node_list').datagrid().datagrid('enableCellEditing');
	setListPager();
	realodPage();*/
})
