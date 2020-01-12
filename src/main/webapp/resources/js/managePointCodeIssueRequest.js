
var summary_columns = [[
	    {field:'searchDate',width:20,align:'center',title : '검색 기준 연/월/일',formatter : addBoldFomatter},
	    {field:'totalCount',width:10,align:'center',title : '요청 건수 ', formatter : numberFormatter},
	    {field:'totalPayAmount',width:20,align:'center',title : '기준 금액 소계', formatter : numberFormatter},
	    {field:'totalAccPointAmount',width:20,align:'center',title : '적립 포인트 소계', formatter : numberFormatter},
	    {field:'totalDepositAmount',width:20,align:'center',title : '입금 금액 소계', formatter : numberFormatter},
	    {field:'totalCompleteDeposit',width:20,align:'center',title : '입금 완료 소계', formatter : numberFormatter},
	    {field:'totalNotCompleteDeposit',width:20,align:'center',title : '입금 미완료 소계', formatter : numberFormatter},
	    {field:'totalDistTop',width:20,align:'center',title : '정산 13.5%', formatter : numberRedFormatter},
	    {field:'totalDistMe',width:20,align:'center',title : '정산 1.5%', formatter : numberGreenFormatter},
	    {field:'ss1',width:40,align:'center',title : '비고'},
	 ]];


var columns = [[
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
	    {field:'pointCodeIssueRequestNo',width:15,align:'center',title : '번호',hidden:false},
	    {field:'memberNo',width:15,align:'center',title : '회원번호',hidden:true},
	    {field:'memberName',width:40,align:'center',title : '회원이름'},
	    {field:'memberEmail',width:60,align:'center',title : '회원이메일'},
	    {field:'memberPhone',width:45,align:'center',title : '회원전화'},
	    {field:'issueType',width:40,align:'center',title : '발행타입', formatter : issueTypeFormatter2},
	    {field:'affiliateName',width:60,align:'center',title : '가맹점', formatter : nullCheckFormatter},
	    {field:'payAmount',width:40,align:'center',title : '기준금액', formatter : numberFormatter},
	    {field:'accPointRate',width:25,align:'center',title : '적립율', formatter : percentFormatter},
	    {field:'accPointAmount',width:40,align:'center',title : '적립금액', formatter : numberFormatter},
	    {field:'depositor',width:50,align:'center',title : '입금자명',formatter : depositorFormatter},
	    {field:'depositAmount',width:35,align:'center',title : '입금금액',  formatter : numberFormatter},
	    {field:'accTargetRange',width:45,align:'center',title : '적립대상',  formatter : accTargetRangeFormatter},
	    {field:'status',width:60,align:'center',title : '상태',  formatter : pointCodeIssuerequestStatusFormatter},
	    {field:'uploadFile',width:55,align:'center',title : '업로드파일', hidden:true},
	    {field:'publisher',width:18,align:'center',title : '발행자',hidden:true},
/*	    {field:'useStartTime',width:40,align:'center',title : '사용 시작일', formatter : dateFormatter},
	    {field:'useEndTime',width:40,align:'center',title : '사용 종료일', formatter : dateFormatter},*/
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
	
	
	$('#searchDepositStatus').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 120
	});

	$('#searchStatus').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 170
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
			returnp.api.call("selectPointCodeIssueRequestReports", param, function(res){
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
			returnp.api.call("selectPointCodeIssueRequestReports", param, function(res){
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
			returnp.api.call("selectPointCodeIssueRequestReports", param, function(res){
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
			returnp.api.call("selectPeriodPointCodeIssueRequestReports", param, function(res){
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
						shStr = "[전체 기간 포인트코드 발급요청 적립 총계] : "
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
			loadPointCodeIssueRequests(index, row);
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

			 var item = null;
			 var findItemCode = "상태 변경"
			 
			 cmenu.menu('appendItem', {
				 id : "pc_10",  // the parent item element
				 text:  "선택 항목 입금금액 합산 ",
				 //iconCls: 'icon-ok',
				 onclick: function(){
					 calcDepositAmount();
				 }
			 });
			 
			 cmenu.menu('appendItem', { separator: true });
			 cmenu.menu('appendItem', {
				 id : "pc_2",  // the parent item element
				 text:  findItemCode,
				 //iconCls: 'icon-ok',
				 onclick: function(){
				 }
			 });
			 
			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.status == "1" ? roundLabel("입금확인중", "#04B404") : "입금확인중",
						 onclick: function(){
							 changePointCodeIssueRequestsStatus("1");
						 }
			 });
			 
			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.status == "2" ? roundLabel(" 입금확인 요청중", "#04B404") : "입금확인 요청중",
						 onclick: function(){
							 changePointCodeIssueRequestsStatus("2");
						 }
			 });
			 
			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.status == "3" ? roundLabel("입금 완료 ", "#04B404") : "입금 완료 ",
						 onclick: function(){
							 changePointCodeIssueRequestsStatus("3");
						 }
			 });
			 
			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.status == "4" ? roundLabel("처리 완료", "#04B404") : "처리 완료",
						 onclick: function(){
							/* changePointCodeIssueRequestsStatus({pointCodeIssueRequestNo : row.pointCodeIssueRequestNo, status : "4"});*/
						 }
			 });
			 
			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.status == "5" ? roundLabel("입금 취소 ", "#04B404") : "입금 취소 ",
						 onclick: function(){
							 changePointCodeIssueRequestsStatus("5");
						 }
			 });
			 
			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.status == "6" ? roundLabel("처리 불가  ", "#04B404") : "처리 불가 ",
						 onclick: function(){
							 changePointCodeIssueRequestsStatus("6");
						 }
			 });
			 
			 cmenu.menu('appendItem', { separator: true });
			 cmenu.menu('appendItem', {
				 id : "pc_10",  // the parent item element
				 text:  "영수증 보기 ",
				 //iconCls: 'icon-ok',
				 onclick: function(){
					 viewReceiptImage(row.uploadFile);
				 }
			 });
			
			 if (row.status == 3) {
				 cmenu.menu('appendItem', { separator: true });
				 cmenu.menu('appendItem', {
					 id : "pc_2",  // the parent item element
					 text:  "포인트 코드 발급",
					 //iconCls: 'icon-ok',
					 onclick: function(){
						 if (row.status !=  "3") {
							 $.messager.alert('알림', "해당 요청건은 입금이 완료된 상태가 아닙니다.</br>입금 확인후 발급하시기 바랍니다.");
							 return;
						 }
						 //issuePointCode({pointCodeIssueRequestNo :row.pointCodeIssueRequestNo , memberNo : row.memberNo })
						 issuePointCodes();
					 }
				 });
			 }
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

function calcDepositAmount(){
	var totalAmount = 0;
	
	var selectedRows =  $('#node_list').datagrid('getSelections');
	if (selectedRows.length < 1) {
		$.messager.alert('알림', "선택된 항목이 없습니다.");
	}
	
	for (var i = 0; i < selectedRows.length; i++){
		totalAmount+=selectedRows[i].depositAmount;
	}
	$.messager.alert('총금액', "<b style = 'font-size : 15px'> 금액 : " + numberFormatter(totalAmount)+"</b> ");
	
	
}

function loadPointCodeIssueRequests(index, row){
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
	returnp.api.call("loadPointCodeIssueRequests", param, function(res){
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

/*단건 포인트코드 발행  */
function issuePointCode(options){
	returnp.api.call("issuePointCode", options, function(res){
		$.messager.alert('알림', res.message);
		if (res.resultCode == "100") {
			var node = $('#summary_table').datagrid('getSelected');
        	loadPointCodeIssueRequests("pager", node);
		}else {
			$.messager.alert('알림', res.message);
		}
	});
}

/*다수의 포인트 코드 발행 */
function issuePointCodes(){
	var params = {issueRequests : []}
	var selectedRows =  $('#node_list').datagrid('getSelections');
	if (selectedRows.length < 1) {
		$.messager.alert('알림', "선택된 항목이 없습니다.");
	}
	
	for (var i = 0; i < selectedRows.length; i++){
		if (selectedRows[i].status == '4') {
			$.messager.alert(
				'알림', "코드 발행 요청이 잘못되었습니다.</br>" + 
				"발행번호 " +selectedRows[i].pointCodeIssueRequestNo + " 번의 발행요청건은 이미 코드발급이 완료된 상태입니다. </br>" + 
				"확인후 다시 시도해주세요"
			);
			return;
		}
		
		if (selectedRows[i].status != '3') {
			$.messager.alert(
				'알림', "코드 발행 요청이 잘못되었습니다.</br>" + 
				"발행번호 " + selectedRows[i].pointCodeIssueRequestNo + " 번의 발행요청건은 입금 완료 상태가 아닙니다. </br>" + 
				"확인후 다시 시도해주세요"
			);
			return;
		}
		

		params.issueRequests.push(selectedRows[i].pointCodeIssueRequestNo + "_" + selectedRows[i].memberNo + "_" + selectedRows[i].accPointAmount + "_" + selectedRows[i].memberName)
	}
	
	returnp.api.call("issuePointCodes", params, function(res){
		$.messager.alert('알림', res.message);
		if (res.resultCode == "100") {
        	var node = $('#summary_table').datagrid('getSelected');
        	loadPointCodeIssueRequests("pager", node);
		}else {
		}
	});
}

/*다수건 상태 변경*/
function changePointCodeIssueRequestsStatus(status){
	var params = {status : status};
	params.pointCodeIssueRequestNos = [];
	
	var selectedRows =  $('#node_list').datagrid('getSelections');
	if (selectedRows.length < 1) {
		$.messager.alert('알림', "선택된 항목이 없습니다.");
	}
	
	for (var i = 0; i < selectedRows.length; i++){
		if (selectedRows[i].status == '4') {
			$.messager.alert(
				'알림', "상태 변경 요청이 잘못되었습니다.</br>" + 
				"발행번호 " +selectedRows[i].pointCodeIssueRequestNo + " 번의 발행요청건은 이미 코드발급이 완료된 상태로 다른 상태로 변경할 수 없습니다 </br>" + 
				"확인후 다시 시도해주세요"
			);
			return;
		}
		
		if (selectedRows[i].status == '3') {
			$.messager.alert(
				'알림', "상태 변경 요청이 잘못되었습니다.</br>" + 
				"발행번호 " + selectedRows[i].pointCodeIssueRequestNo + " 번의 발행요청건은 입금 완료상태로 다른 상태로 변경할 수 없습니다 </br>" + 
				"확인후 다시 시도해주세요"
			);
			return;
		}
		
		params.pointCodeIssueRequestNos.push(selectedRows[i].pointCodeIssueRequestNo);
	}
	
	returnp.api.call("changePointCodeIssueRequestsStatus", params, function(res){
		console.log(res);
		$.messager.alert('알림', res.message);
		if (res.resultCode == "100") {
        	var node = $('#summary_table').datagrid('getSelected');
        	loadPointCodeIssueRequests("pager", node);
		}else {
		}
	});
}

/*단건 상태 변경*/
function changePointCodeIssueRequestStatus(options){
	returnp.api.call("changePointCodeIssueRequestStatus", options, function(res){
		console.log(res);
		$.messager.alert('알림', res.message);
		if (res.resultCode == "100") {
        	var node = $('#summary_table').datagrid('getSelected');
        	loadPointCodeIssueRequests("pager", node);
		}else {
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
        	loadPointCodeIssueRequests("pager", node);
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
		res.rows[i]['totalDistTop'] = parseInt(parseFloat(res.rows[i].totalCompleteDeposit) * 0.9)
		res.rows[i]['totalDistMe'] = parseInt(parseFloat(res.rows[i].totalCompleteDeposit) * 0.1)
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
