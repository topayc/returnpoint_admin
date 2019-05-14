

columns = [[
	//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
	   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
	    {field:'giftCardPolicyNo',width:60,align:'center',title : '등록번호', hidden : true},
	    {field:'companyName',width:100,align:'center',title : '회사명',editor:'text' },
	    {field:'salesCommissionTarget',width:140,align:'center',title : 'Happy World 포인트 적립자',editor:'text' },
	    {field:'salesCommissionRate',width:120,align:'center',title : 'Happy World - 수수료율',editor:'text' },
	    {field:'web',width:100,align:'center',title : '웹 사이트',editor:'text', formatter : linkFormatter },
	    {field:'headSaleFeeRate',width:90,align:'center',title : '본사 판매수수료' ,editor:'text' },
	    {field:'distSaleFeeRate',width:90,align:'center',title : '총판 판매 수수료',editor:'text' },
	    {field:'saleOrganSaleFeeRate',width:90,align:'center',title : '판매점 판매 수수료',editor:'text' },
	    {field:'giftCardAccRate',width:80,align:'center',title : '사용자 적립율',editor:'text' },
	    {field:'giftCardPayRefundRate',width:90,align:'center',title : '결제 수수료율',editor:'text' },
	    {field:'bankName',width:100,align:'center',title : '은행명',editor:'text' },
	    {field:'bankAccount',width:100,align:'center',title : '은행 계좌',editor:'text' },
	    {field:'bankAccountOwner',width:100,align:'center', title : '계좌주',editor:'text' },
	    {field:'csTel',width:100,align:'center',title : 'CS 전화',editor:'text',  hidden : true },
	    {field:'csOperationTime',width:100,align:'center',title : 'CS 운영시간',editor:'text', hidden : true },
	    {field:'csEmail',width:100,align:'center',title : 'CS 이메일',editor:'text',hidden : true },
	    {field:'bankDepositText',width:100,align:'center',title : '입금 텍스트',editor:'text',hidden : true },
	    {field:'createTime',width:100,align:'center',title : '등록일', formatter : dateFormatter , hidden : true},
	    {field:'updateTime',width:100,align:'center',title : '수정일', formatter : dateFormatter, hidden : true},
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
	var grid = $('#gift_card_policy_list').datagrid({
		singleSelect:true,
		collapsible:false,
		fitColumns:true,
		selectOnCheck : false,
		checkOnSelect : false,
		border:false,
		rownumbers : true,
	    columns:columns
	});
	
	grid.datagrid('enableCellEditing').datagrid('gotoCell', {
    /*    index: 0,
        field: 'productid'*/
    });
	
	$('#update_btn').linkbutton({
		onClick : function(){
			updateGiftCardPolicy();
		},
		iconCls:'icon-ok'
	});
}

function updateGiftCardPolicy(){
	var data =  $('#gift_card_policy_list').datagrid("getData");
	console.log(data);
	if (data.rows.length == 0) {return; }
	delete data.rows[0].createTime;
	delete data.rows[0].updateTime;
	returnp.api.call("updateGiftCardPolicy", data.rows[0], function(res){
		if (res.resultCode == "100") {
			realodPage();
		}else {
			$.messager.alert(res.message, res.data);
		}
	});
}

function setListColumnHeader(nodeType){
	$('#gift_card_policy_list').datagrid({
		columns : columns
	});
}

function realodPage(){
	returnp.api.call("selectGiftCardPolicy", {giftCardPolicyNo : 1}, function(res){
		console.log(res);
		if (res.resultCode == "100") {
			setListColumnHeader("1");
			$('#gift_card_policy_list').datagrid({
				data : res
			});
		}else {
			$.messager.alert(res.message, res.data);
		}
	});
}
$(document).ready(function(){
	initView();
	realodPage();
});
