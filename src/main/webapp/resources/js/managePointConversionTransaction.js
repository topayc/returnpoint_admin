
var summary_columns = [[
	    {field:'searchDate',width:20,align:'center',title : '검색 기준 연/월/일',formatter : addBoldFomatter},
	    {field:'totalCount',width:10,align:'center',title : '전환 건수 ', formatter : numberFormatter},
	    {field:'totalConversionTotalPoint',width:20,align:'right',title : '전환 G 포인트 소계', formatter  : numberGreenFormatter},
	    {field:'totalConversionAccPoint',width:20,align:'right',title : '전환 R 포인트 소계', formatter  : numberRedFormatter},
	    {field:'ss1',width:40,align:'center',title : '비고'},
	 ]];


columns = [[
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
	    {field:'pointConversionTansactionNo',width:15,align:'center',title : '번호',hidden:false},
	    {field:'memberName',width:40,align:'center',title : '회원 이름'},
	    {field:'memberEmail',width:50,align:'center',title : '회원 이메일'},
	    {field:'nodeType',width:30,align:'center',title : '전환 노드 타입', formatter : nodeTypeFormatter},
	    {field:'conversionTotalPoint',width:40,align:'center',title : '전환 대상 포인트', formatter : numberGreenFormatter},
	    {field:'conversionAccPoint',width:40,align:'center',title : '전환 포인트', formatter : numberRedFormatter},
	    {field:'conversionAccRate',width:30,align:'center',title : 'R 적용 전환율', formatter : percentFormatter},
	    {field:'conversionStatus',width:30,align:'center',title : '전환 상태', formatter : conversionStatusFormatter},
	    {field:'createTime',width:40,align:'center',title : '등록일',formatter : dateFormatter},
	    {field:'updateTime',width:40,align:'center',title : '수정일',formatter : dateFormatter},
	    {field:'memberNo',width:15,align:'center',title : 'memberNo',hidden:true},
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
	});
	
	/* 노드 상태 셀렉트 박스  초기화*/
	$('#searchConversionStatus').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
	});
	
	$('#searchDateType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 80
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
	
	

	
	/* 검색어 입력 박스 초기화 */
	$('#searchKeyword').textbox({
		width: 180,
		prompt : "이름 / 아이디 " ,
		  labelPosition: 'top'
	});
	
	$('#search_total_year_btn').linkbutton({
		onClick : function(){
			$('#searchForm').form('reset');
			$('#node_list').datagrid('loadData', []);
			$('#node_list').datagrid('getPanel').panel('setTitle', "");
			
			var param = {searchType  : "year"}
			returnp.api.call("selectPointConversionReports", param, function(res){
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
						$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					setSummary(res, '연도별 전환 총계');
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
			returnp.api.call("selectPointConversionReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					if (res.rows.length < 1) {
						$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					setSummary(res, '일별 전환 총계');
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
			returnp.api.call("selectPointConversionReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
						$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					setSummary(res, '월별 전환 총계');
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
			console.log("파라메터");
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
			returnp.api.call("selectPeriodPointConversionReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
						$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					$('#summary_table').datagrid({
						data : res
					});
					var shStr = "";
					if (param.searchDateStart != ''  ) {
						shStr = '[' + param.searchDateStart + " ~ "+  oriDateEnd + "] 매출 총계 :";
					}else {
						shStr = "[전체 기간 매출 총계] : "
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
			loadPointConversions(index, row);
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

	    columns: columns
	});
	setListPager2();
	setListPager();
}

function loadPointConversions(index, row){
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
	returnp.api.call("loadPointConversions", param, function(res){
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
            	console.log(rows);
            	var cartes = [];
            	var saleCounts = [] 
            	var paySumDatas = [];
            	var payAppDatas = [];
            	var payCancelDatas = [];
            	for (var i = 0; rows.length; i++){
            		if (i  == rows.length - 1) break;
            		cartes.push(rows[i].searchDate);
            		saleCounts.push(rows[i].payCase);
            		paySumDatas.push(rows[i].salesSum);
            		payAppDatas.push(rows[i].salesApprovalSum);
            		payCancelDatas.push(-rows[i].salesCancelSum);
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
            	        name: '최종 결제 금액',
            	        data:paySumDatas
            	    }, {
            	        name: '결제 승인 금액',
            	        data: payAppDatas
            	    }, {
            	        name: '결제 취소 금액',
            	        data:payCancelDatas
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
        	loadPointConversions("pager", node);
    	}
    }); 
}

function setSummary(res, str){
	$('#summary_table').datagrid({
		data : res,
		title : '['+str+ '] ' ,
	});
	var totalCount = 0;
	var totalConversionTotalPoint = 0
	var totalConversionAccPoint = 0; 
	for (var i = 0; i < res.rows.length; i++) {
		totalCount+=parseInt(res.rows[i].totalCount);
		totalConversionTotalPoint += parseFloat(res.rows[i].totalConversionTotalPoint);
        totalConversionAccPoint+=parseFloat(res.rows[i].totalConversionAccPoint);
    }
	$('#summary_table').datagrid({
		title : '[' +str+ ']  : ' +numberGreenFormatter(totalConversionAccPoint),
	});
	$('#summary_table') .datagrid( 
		'appendRow', 
		{ searchDate : "총계", totalCount : totalCount, totalConversionTotalPoint : totalConversionTotalPoint , totalConversionAccPoint :  totalConversionAccPoint });
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
/*	$('#node_list').datagrid().datagrid('enableCellEditing');
	setListPager();
	realodPage();*/
})
