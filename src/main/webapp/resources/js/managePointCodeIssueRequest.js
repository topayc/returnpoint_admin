
var summary_columns = [[
	    {field:'searchDate',width:20,align:'center',title : '검색 기준 연/월/일',formatter : addBoldFomatter},
	    {field:'totalCount',width:10,align:'center',title : '요청 건수 ', formatter : numberFormatter},
	    {field:'totalPayAmount',width:20,align:'center',title : '기준 금액 소계', formatter : numberFormatter},
	    {field:'totalAccPointAmount',width:20,align:'center',title : '적립 금액 소계', formatter : numberFormatter},
	    {field:'totalAccPointAmount',width:20,align:'center',title : '입금 금액 소계', formatter : numberFormatter},
	    {field:'ss1',width:40,align:'center',title : '비고'},
	 ]];


var columns = [[
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
	    {field:'pointCodeIssueRequestNo',width:15,align:'center',title : '번호',hidden:false},
	    {field:'memberNo',width:15,align:'center',title : '회원 번호',hidden:true},
	    {field:'memberName',width:50,align:'center',title : '회원 이름'},
	    {field:'memberEmail',width:60,align:'center',title : '회원 이메일'},
	    {field:'issueType',width:40,align:'center',title : '발행 타입'},
	    {field:'payAmount',width:40,align:'center',title : '기준 금액', formatter : numberFormatter},
	    {field:'accPointRate',width:25,align:'center',title : '적립율', formatter : percentFormatter},
	    {field:'accPointAmount',width:40,align:'center',title : '적립 금액', formatter : numberFormatter},
	    {field:'accTargetRange',width:55,align:'center',title : '적립 대상',  formatter : accTargetRangeFormatter},
	    {field:'status',width:30,align:'center',title : '상태',  formatter : pointCodeIssuerequestStatusFormatter},
	    {field:'depositAmount',width:35,align:'center',title : '입금 금액',  formatter : numberFormatter},
	    {field:'depositStatus',width:45,align:'center',title : '입금 상태',  formatter : depositStatusFormatter},
	    {field:'issueStatus',width:40,align:'center',title : '코드 상태', formatter : issueStatusFormatter},
	    {field:'uploadFile',width:55,align:'center',title : '업로드 파일'},
	    {field:'publisher',width:30,align:'center',title : '발행자'},
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
		width: 120
	});
	
	/* 검색어 입력 박스 초기화 */
	$('#searchKeyword').textbox({ 
		labelPosition : 'top',
		width: 200,
		prompt : "아이디 / 이름" ,
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
			loadPointCodeIssueRequests(index, row);
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
				 itemHeight : 27
			 });

			 var item = null;
			 var findItemCode = "입금 상태 변경";
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
				 text:  row.depositStatus == "1" ? roundLabel("입금 확인중", "#04B404") : "입금 확인중",
						 onclick: function(){
							 changePointCodeIssueRequestStatus({pointCodeIssueRequestNo : row.pointCodeIssueRequestNo, depositStatus : "1"});
						 }
			 });

			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.depositStatus== "2" ? roundLabel("입금확인 요청중", "#04B404") : "입금확인 요청중",
						 onclick: function(){
							 changePointCodeIssueRequestStatus({pointCodeIssueRequestNo : row.pointCodeIssueRequestNo, depositStatus : "2"});
						 }
			 });

			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.depositStatus == "3" ? roundLabel("입금 완료", "#04B404") : "입금 완료",
						 onclick: function(){
							 changePointCodeIssueRequestStatus({pointCodeIssueRequestNo : row.pointCodeIssueRequestNo, depositStatus : "3"});
						 }
			 });

			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.depositStatus == "4" ? roundLabel("입금 취소", "#04B404") : "입금 취소",
						 onclick: function(){
							 changePointCodeIssueRequestStatus({pointCodeIssueRequestNo : row.pointCodeIssueRequestNo, depositStatus : "4"});
						 }
			 });
			
			 
			 findItemCode = "상태 변경"
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
				 text:  row.status == "1" ? roundLabel("정상", "#04B404") : "정상",
						 onclick: function(){
							 changePointCodeIssueRequestStatus({pointCodeIssueRequestNo : row.pointCodeIssueRequestNo, status : "1"});
						 }
			 });
			 
			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.status == "2" ? roundLabel(" 사용 중지", "#04B404") : "사용 중지",
						 onclick: function(){
							 changePointCodeIssueRequestStatus({pointCodeIssueRequestNo : row.pointCodeIssueRequestNo, status : "2"});
						 }
			 });
			 
			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.status == "3" ? roundLabel("등록 해제", "#04B404") : "등록 해제",
						 onclick: function(){
							 changeStatus({pointCodeIssueRequestNo : row.pointCodeIssueRequestNo, status : "3"});
						 }
			 });
			 
			 item = cmenu.menu('findItem', findItemCode);  
			 cmenu.menu('appendItem', {
				 parent: item.target,  // the parent item element
				 //iconCls: 'icon-ok',
				 text:  row.status == "4" ? roundLabel("처리 불가", "#04B404") : "처리 불가",
						 onclick: function(){
							 changePointCodeIssueRequestStatus({pointCodeIssueRequestNo : row.pointCodeIssueRequestNo, status : "4"});
						 }
			 });
			 
			 cmenu.menu('appendItem', { separator: true });
			 cmenu.menu('appendItem', {
				 id : "pc_10",  // the parent item element
				 text:  "영수증 보기 ",
				 //iconCls: 'icon-ok',
				 onclick: function(){
					 viewFile(row.uploadFile);
				 }
			 });
			 
			 cmenu.menu('appendItem', { separator: true });
			 cmenu.menu('appendItem', {
				 id : "pc_2",  // the parent item element
				 text:  "포인트 코드 발급",
				 //iconCls: 'icon-ok',
				 onclick: function(){
					 if (row.depositStatus !=  "3") {
						 $.messager.alert('알림', "해당 요청건은 입금이 완료된 상태가 아닙니다.</br>입금 확인후 발급하시기 바랍니다.");
						 return;
					 }
					 issuePointCode({pointCodeIssueRequestNo :row.pointCodeIssueRequestNo , memberNo : row.memberNo })
				 }
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

function viewFile(path){
	/*	var url = "/api/giftCardIssue/downQrCode?giftCardIssueNo=" + giftCardIssueNo + "&type=" + type;
		var w = window.open(path, "QR Code", "width=550, height=550, left=100, top=100"); 
		w.document.title = title;*/
		$('#receipt_img').attr("src", "");
		$('#receipt_img').attr("src", "https://www.returnp.com/cloud/images/receipt/" +path );
		$("#receipt_view").dialog({
			title : type == "영수증",
			modal : true,
			closable : true,
			border : 'thick',
			shadow : true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			shadow : false,
			buttons : [ {
				text : '확인',
				iconCls : 'icon-ok',
				handler : function(){
					$("#qr_code_view").dialog('close');
					$('#qr_code_no').attr("src", "");
				}
			} ]
		});
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

function issuePointCode(options){
	returnp.api.call("issuePointCode", options, function(res){
		console.log(res);
		if (res.resultCode == "100") {
        	var node = $('#summary_table').datagrid('getSelected');
        	loadPointCoupons("pager", node);
		}else {
			$.messager.alert('알림', res.message);
		}
	});
}

function changePointCodeIssueRequestStatus(options){
	returnp.api.call("changePointCodeIssueRequestStatus", options, function(res){
		console.log(res);
		if (res.resultCode == "100") {
        	var node = $('#summary_table').datagrid('getSelected');
        	loadPointCoupons("pager", node);
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
        	loadPointCouponTransactions("pager", node);
    	}
    }); 
}

function loadPointCodes(index, row){
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
	returnp.api.call("loadPointCodes", param, function(res){
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

function setSummary(res, str){
	$('#summary_table').datagrid({
		data : res,
		title : '['+str+ '] ' ,
	});
	var totalCount = 0;
	var totalPayAmount = 0
	var totalAccPointAmount = 0; 
	for (var i = 0; i < res.rows.length; i++) {
		totalCount+=parseInt(res.rows[i].totalCount);
		totalPayAmount += parseFloat(res.rows[i].totalPayAmount);
		totalAccPointAmount+=parseFloat(res.rows[i].totalAccPointAmount);
    }
	$('#summary_table').datagrid({
		title : '[' +str+ ']  : ' +numberGreenFormatter(totalAccPointAmount + "  /   " + totalPayAmount ),
	});
	$('#summary_table') .datagrid( 
		'appendRow', 
		{ searchDate : "총계", totalCount : totalCount, totalPayAmount : totalPayAmount , totalAccPointAmount :  totalAccPointAmount });
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
