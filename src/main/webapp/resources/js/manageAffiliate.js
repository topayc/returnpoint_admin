	columns = [[
	    	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'affiliateNo',width:10,align:'center',title : '번호',hidden:false},
			    {field:'memberName',width:25,align:'center',title : '회원 이름'},
			    {field:'affiliateName',width:50,align:'center',title : '가맹점 이름'},
			    {field:'affiliateCode',width:40,align:'center',title : '가맹점 코드',hidden:false},
			    {field:'affiliateSerial',width:35,align:'center',title : 'T-ID'},
			    {field:'affiliateComm',width:20,align:'center',title : '환급율'},
			    {field:'affiliateType',width:35,align:'center',title : '타입', formatter : affiliateTypeFormatter},
			    {field:'affiliateStatus',width:15,align:'center',title : '상태',formatter : nodeStatusFormatter},
			    {field:'greenPointAmount',width:30,align:'center',title : 'G POINT', formatter : numberGreenFormatter},
			    {field:'redPointAmount',width:30,align:'center',title : 'R POINT', formatter : numberRedFormatter},
			    {field:'agencyName',width:20,align:'center',title : '소속 대리점', hidden:false,formatter : slashFormatter},
			    {field:'recommenderName',width:20,align:'center',title : '추천인'},
			    {field:'greenPointAccStatus',width:15,align:'center',title : 'G 적립', formatter : ynFormatter},
			    {field:'redPointAccStatus',width:15,align:'center',title : 'R 적립', formatter : ynFormatter},
			    {field:'greenPointUseStatus',width:15,align:'center',title : 'G 사용', formatter : ynFormatter},
			    {field:'redPointUseStatus',width:15,align:'center',title : 'R 사용', formatter : ynFormatter},
//			    {field:'affiliateAddress',width:40,align:'center',title : '가맹점 주소',hidden:true},
//			    {field:'affiliateTel',width:40,align:'center',title : '가맹점 전화',hidden:true},
//			    {field:'affiliatePhone',width:30,align:'center',title : '가맹점 핸드폰',hidden:true},
			    {field:'createTime',width:30,align:'center',title : '등록일',formatter : dateFormatter},
			    {field:'updateTime',width:30,align:'center',title : '수정일',formatter : dateFormatter,hidden:true},
			    {field:'memberNo',width:15,align:'center',title : 'memberNo',hidden:true},
			    {field:'branchNo',width:15,align:'center',title : 'barachNo',hidden:true},
			    {field:'recommenderNo',width:15,align:'center',title : 'recommenderNo',hidden:true}
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
	$('#searchNodeType').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		labelPosition: 'top',
		multiple:false,
		required:true,
		readonly:true
	});
	
	/* 노드 상태 셀렉트 박스  초기화*/
	$('#searchNodeStatus').combobox({
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
			//$.messager.progress();
			var param = makeSearchParam();
			//console.log("검색 쿼리 데이타");
			//console.log(param);
			
			returnp.api.call("getNodes", param, function(res){
				
				if (res.resultCode == "100") {
					setListColumnHeader(param.searchNodeType);
					console.log(res);
					$('#node_list').datagrid({
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
			$('#searchForm').form('clear');
			$('#searchNodeStatus').combobox('select', 0);
			$('#searchKeywordType').combobox('select', 0);
			$('#searchKeyword').textbox('clear');
			$('#searchDateStart').datebox('clear');
			$('#searchDateEnd').datebox('clear');
		}
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
		  				break;
		  			case "modify":
		                  loadAffiliateModifyForm();
		  				break;
		  			case "remove":
		  				removeAffiliate();
		  				break;
		  			case "more_detail":
		  				break;
		  			case "point_acc_view":
		  				var node = $('#node_list').datagrid('getSelected');
		  				if (!node) {
		  					 $.messager.alert('알림','세부 내역을 확인하실 항목을 선택해주세요');
		  					 return;
		  				}
		  				loadPaymentPointbackRecord(
		  					"Green Point 누적 지급 현황", 
		  					{
		  						memberNo : node.memberNo,
		  						nodeType : 5
		  					}
		  				);
		  				break;
		  			}
		  		}
		  	});
		  	
		  	var menus = [  '수정', '삭제','상세 정보','포인트 누적 현황' ];
		  	var icons = ['icon-edit','icon-remove','icon-tip', 'icon-large-chart'];
		  	var actions = ['modify','remove','more_detail','point_acc_view'];
		  	
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
	setListPager();
}

function setListPager(){
	var pager = $('#node_list').datagrid().datagrid('getPager');
	pager.pagination({
		displayMsg : ' {from} to {to} of {total}',
		buttons:[{
            iconCls:'icon-add',
            handler:function(){
            	$('#node_list').datagrid('unselectAll');
            	$('#node_list').datagrid('uncheckAll');
                loadAffiliateCreateForm();
            }
        },{
            iconCls:'icon-edit',
            handler:function(){
                  loadAffiliateModifyForm();
            }
        },{
            iconCls:'icon-remove',
            handler:function(){
            	removeAffiliate();
            }
        },{
            iconCls:'icon-tip',
            handler:function(){
            }
        }],
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

function statusFormatter(value,row,index){
	var status = row.memberStatus;
	return '&nbsp;<span >'+ status + '</span>';
}

function loadAffiliateCreateForm(){
	
	var nodeType = $('input[name=searchNodeType]').val();
  	var data = {
        targetElem : "#dlgForm",
        title : "협력 업체(가맹점) 생성",
        queryOptions : {
          	action : "create",
         }
     }
  	
	//console.log("loadAffiliateCreateForm");
	var queryParam = $.param(data.queryOptions);
	$(data.targetElem).load("/api/affiliate/form/createForm?" + queryParam,
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
								createAffiliate(data);
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

function loadAffiliateModifyForm(){
	
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','수정하실 노드를 선택해주세요');
		 return;
	}
	
	var data = {
		targetElem : "#dlgForm",
        title : ' 협력 업체 수정',
        queryOptions : {
        	action : "modify",
        	affiliateNo : node.affiliateNo,
        	memberAddressNo : node.memberAddressNo
        }
     }
	
	//console.log("loadAffiliateModifyForm");
	var queryParam = $.param(data.queryOptions);
	data.targetElem = data.targetElem || "#dlgForm";
	var queryParam = $.param(data.queryOptions);
	
	$(data.targetElem).load("/api/affiliate/form/createForm?" + queryParam,
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
							updateAffiliate(data);
						} },
						{ text:'취소', handler:function(){
							//console.log("닫을 DIV : " + data.targetElem);	
							$(data.targetElem).dialog('close');
							$(data.targetElem).removeAttr('style');
						}
					}]
				});
				$(data.targetElem).dialog('center');
				returnp.api.call('getAffiliateCommand', {
					affiliateNo : data.queryOptions.affiliateNo
					,memberAddressNo: data.queryOptions.memberAddressNo}, function(res){
						console.log(res.data);
					if (res.resultCode  == "100") {		
						$('#createAffiliateForm').form('load',res.data);
						//$('#memberNo').textbox({disabled : true });
						$("#affiliateRoad").textbox('setValue', res.data.zipNo + " " + res.data.roadFullAddr+ " " + res.data.addrDetail);
						modifyModeCallback(res.data.zipNo + " " + res.data.jibunAddr + " " + res.data.addrDetail,res.data.lat,res.data.lng);
						
					}else {
						$.messager.alert('오류 발생', res.message);
					}
				});
			
		});
}


function makeFormData(){
	var param = $("#createAffiliateForm").serializeArray();
	return param;
}

function updateAffiliate(data){
	var param =makeFormData();
	//console.log(param);
	returnp.api.call("updateAffiliate", param, function(res){
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

function createAffiliate(data){	
	var param =makeFormData();
	//console.log("createAffiliate");
	//console.log(param);
		
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
		
	returnp.api.call("createAffiliate", param, function(res){
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

function removeAffiliate(){
	var node = $('#node_list').datagrid('getSelected');
	if (!node) {
		 $.messager.alert('알림','삭제하실 항목을 선택해주세요');
		 return;
	}
	
	$.messager.confirm('삭제', /*item.data.memberEmail +*/ ' 해당 내용을 정말로 삭제하시겠습니까?', function(r){
        if (r){
        	var param = {
        		affiliateNo : node.affiliateNo
        	}
        	returnp.api.call("deleteAffiliate", param, function(res){
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

function loadMap(address) {
	
   var geocoder = new google.maps.Geocoder();		
   geocoder.geocode( { 'address': address}, function(results, status) {		    	
      if (status == google.maps.GeocoderStatus.OK) {		
        var lat = results[0].geometry.location.lat();		
        var lng = results[0].geometry.location.lng();		 	   	
    	var mapOptions = {
					center: new google.maps.LatLng(lat, lng),
                	zoom: 18, 
                	mapTypeId: google.maps.MapTypeId.ROADMAP
           	};		 	   
 	   	var map = new google.maps.Map(document.getElementById("map-canvas"),mapOptions);	
 	    //style="width:50%; height:100px;"
 	   	//$("#map-canvas").css("width","100%");
 	   	//$("#map-canvas").css("height","100px");
		marker = new google.maps.Marker({
		           map: map,
		           icon: 'resources/images/marker.gif',
		           position: results[0].geometry.location				
		       });	
		var content = address +"<br/>" + lat + "," +  lng; // 말풍선 안에 들어갈 내용				
		$("#lat").val(lat);
		$("#lng").val(lng);				
		// 마커를 클릭했을 때의 이벤트.
		var infowindow = new google.maps.InfoWindow({ content: content});
		google.maps.event.addListener(marker, "click", function() {infowindow.open(map,marker);});	
      	} else {
        	alert("Geocode was not successful for the following reason: " + status);
      	}

    });

}
	
function loadMap2(address,lat,lng) {
	
	$("#lat").val(lat);
	$("#lng").val(lng);	
	
	if(address !=''){
		//$("#map-canvas").css("width","100%");
 	   	//$("#map-canvas").css("height","100px");
	}
	
	var mapOptions = {
				center: new google.maps.LatLng(lat, lng),
               	zoom: 18, 
               	mapTypeId: google.maps.MapTypeId.ROADMAP
          	};
	   
	var map = new google.maps.Map(document.getElementById("map-canvas"),mapOptions);	     
	marker = new google.maps.Marker({
	           map: map,
	           icon: 'resources/images/marker.gif',
	           position: new google.maps.LatLng(lat, lng)		
	       });
	
	var content = address +"<br/>" + lat + "," +  lng; // 말풍선 안에 들어갈 내용
	
	// 마커를 클릭했을 때의 이벤트.
	var infowindow = new google.maps.InfoWindow({ content: content});
	google.maps.event.addListener(marker, "click", function() {infowindow.open(map,marker);});
}

function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	
	$("#roadFullAddr").val(roadFullAddr);
	$("#roadAddrPart1").val(roadAddrPart1);
	$("#roadAddrPart2").val(roadAddrPart2);
	$("#addrDetail").val(addrDetail);
	$("#engAddr").val(engAddr);
	$("#jibunAddr").val(jibunAddr);
	$("#zipNo").val(zipNo);
	$("#admCd").val(admCd);
	$("#rnMgtSn").val(rnMgtSn);
	$("#bdMgtSn").val(bdMgtSn);
	
	$("#affiliateAddress").textbox('setValue', zipNo + " " + jibunAddr + " " + addrDetail);
	$("#affiliateRoad").textbox('setValue', zipNo + " " + roadFullAddr+ " " + addrDetail);
	
	loadMap(jibunAddr);
}

function modifyModeCallback(jibunAddr,lat,lng){	
	loadMap(jibunAddr,lat,lng);
}

function realodPage(){
	
	$('#search_btn').click();
}

$(document).ready(function(){
	$('#search_btn').click();
});
