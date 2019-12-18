
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
	    {field:'pointCodeIssueRequestNo',width:15,align:'center',title : '발행 요청 번호',hidden:true},
	    {field:'pointCodeIssueNo',width:15,align:'center',title : '등록 번호',hidden:true},
	    {field:'memberNo',width:15,align:'center',title : '회원 번호',hidden:true},
	    {field:'memberName',width:50,align:'center',title : '회원 이름'},
	    {field:'memberEmail',width:60,align:'center',title : '회원 이메일'},
	    {field:'pointCode',width:80,align:'center',title : '포인트코드'},
	    {field:'useStatus',width:40,align:'center',title : '사용 상태', formatter : pointCodeIssueUseStatusFormatter},
	    {field:'payAmount',width:40,align:'center',title : '기준 금액', formatter : numberFormatter},
	    {field:'accPointRate',width:25,align:'center',title : '적립율', formatter : percentFormatter},
	    {field:'accPointAmount',width:40,align:'center',title : '적립 금액', formatter : numberFormatter},
	    {field:'accTargetRange',width:55,align:'center',title : '적립 대상',  formatter : accTargetRangeFormatter},
	    {field:'status',width:30,align:'center',title : '상태',  formatter : pointCodeIssuerequestStatusFormatter},
	    {field:'depositAmount',width:35,align:'center',title : '입금 금액',  formatter : numberFormatter},
	    {field:'uploadFile',width:55,align:'center',title : '업로드 파일'},
	    {field:'publisher',width:30,align:'center',title : '발행자'},
/*	    {field:'useStartTime',width:40,align:'center',title : '사용 시작일', formatter : dateFormatter},
	    {field:'useEndTime',width:40,align:'center',title : '사용 종료일', formatter : dateFormatter},*/
	    {field:'createTime',width:40,align:'center',title : '등록일',formatter : dateFormatter}
	    ]];

var pointback_record_columns = [[
	  {field:'pointCouponPointbackRecordNo',width:50,align:'center',title : '번호',hidden: true},
	  {field:'pointCouponTransactionNo',width:50,align:'center',title : '참조 번호',hidden:true},
	  {field:'registerMemberNo',width:60,align:'center',title : '회원 번호',hidden:true},
	  {field:'registerMemberName',width:70,align:'center',title : '등록자 이름',hidden:false},
	  {field:'registerMemberEmail',width:100,align:'center',title : '등록자 이메일',hidden: true},
	  {field:'registerMemberPhone',width:80,align:'center',title : '등록자 핸드폰',hidden:false},
	  {field:'memberNo',width:100,align:'center',title : '수취자 회원 번호',hidden  : true},
	  {field:'memberName',width:100,align:'center',title : '수취자 이름'},
	  {field:'memberEmail',width:150,align:'center',title : '수취자 이메일'},
	  {field:'memberPhone',width:100,align:'center',title : '수취자 핸드폰'},
	  {field:'payAmount',width:90,align:'center',title : '기준 금액', formatter : numberBoldFormatter},
	  {field:'accRate',width:70,align:'center',title : '적립율'},
	  {field:'accPoint',width:100,align:'center',title : '지급 G 포인트', formatter : numberGreenFormatter},
	  {field:'couponType',width:100,align:'center',title : '적립 타입',formatter : pointCouponTypeFormatter},
	  {field:'nodeNo',width:70,align:'center',title : 'nodeNo',hidden:true},
	  {field:'createTime',width:120,align:'center',title : '등록일', formatter : dateFormatter},
	  {field:'updateTime',width:120,align:'center',hidden:true, title : '수정일', formatter : dateFormatter},
	  ]]
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
	
	

	$('#searchPointbackStatus').combobox({
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
			returnp.api.call("selectPointCodeTransactionReports", param, function(res){
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
					}
					setSummary(res, '연도별 포인트코드 발급 적립 총계');
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
			returnp.api.call("selectPointCodeTransactionReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					if (res.rows.length < 1) {
						//$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					setSummary(res, '일별 포인트코드 발급 적립 총계');
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
			returnp.api.call("selectPointCodeTransactionReports", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					
					if (res.rows.length < 1) {
						//$.messager.alert('알림', "검색 결과가 없습니다.");
					}
					
					setSummary(res, '월별 포인트코드 발급 적립 총계');
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
			returnp.api.call("selectPeriodPointCodeTransactionReports", param, function(res){
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
						shStr = '[' + param.searchDateStart + " ~ "+  oriDateEnd + "] 포인트코드 발급 적립 총계 :";
					}else {
						shStr = "[전체 기간 포인트코드 발급 적립 총계] : "
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
			loadPointCodeTransactions(index, row);
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
			 cmenu.menu('appendItem', {
				 id : "pc_10",  // the parent item element
				 text:  "영수증 보기 ",
				 //iconCls: 'icon-ok',
				 onclick: function(){
					 viewFile(row.uploadFile);
				 }
			 });
			 
			 cmenu.menu('appendItem', {
				 id : "pc_10",  // the parent item element
				 text:  "세부  적립 내역 보기",
				 //iconCls: 'icon-ok',
				 onclick: function(){
					 viewPointbackList();
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


function viewPointbackList(){
  var node = $('#node_list').datagrid('getSelected');
  if (!node) {
     $.messager.alert('알림','세부 적립 리스트를 보실 적립 항목을 선택해 주세요');
     return;
  }
  
  $('#pointback_record_dlg').dialog({
    width : 1400,
    height : 400,
    cache : false,
    modal : true,
    closable : true,
    border : 'thick',
    constrain : true,
    shadow : true,
    collapsible : false,
    minimizable : false,
    maximizable : false,
    title : "&nbsp; " + "세부 적립 정보",
    shadow : false,
    onOpen : function(){
    	$('#pointback_record_list').datagrid({
    		singleSelect:true,
    		collapsible:false,
    		fitColumns:true,
    		selectOnCheck : true,
    		checkOnSelect : true,
    		border:false,
    		rownumbers : true,
    		pagination: false,
    		onLoadSuccess : function(){
    		},  
    		columns: pointback_record_columns
    	});
    
    	  
    	var param = {pointCodeTransactionNo : node.pointCodeTransactionNo};
    	returnp.api .call( 'selectPointCodePointbackRecords', param, function(res) {
    		console.log(res)
    		if (res.resultCode == "100") {
    			$('#pointback_record_list').datagrid('loadData', []);
    			$('#pointback_record_list') .datagrid({ data : res.rows })
    			var totalAccPoint= 0
    			for (var i = 0; i < res.rows.length; i++) {
    				totalAccPoint += parseInt(res.rows[i].accPoint);
    			}
    			$('#pointback_record_list') .datagrid( 'appendRow', { pointCodePointbackRecordNo : "소계", accPoint : totalAccPoint });
    		} else {
    			$.messager.alert('오류 발생', res.message);
    		}
    	});
    },  
    buttons : [ {
      text : '확인',
      iconCls : 'icon-ok',
      handler : function() {
        $('#pointback_record_dlg').dialog('close');
        $('#pointback_record_dlg').removeAttr('style');
      }
    } /*
     * , { text:'취소', handler:function(){
     * $('#dlgForm').dialog('close');
     * $('#dlgForm').removeAttr('style'); } }
     */
    ]
  });
  

}

function viewFile(path){
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

function loadPointCodeTransactions(index, row){
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
	returnp.api.call("loadPointCodeTransactions", param, function(res){
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
