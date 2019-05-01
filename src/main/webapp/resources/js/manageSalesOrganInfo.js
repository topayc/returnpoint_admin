/**
 * 뷰 초기화 
 * @returns
 */
function initView(){
	/* 레이아웃 초기화*/
	$('.easyui-layout').layout();
	
	/* 패널   초기화*/
	$('.easyui-panel').panel({ border: false });
	var columns = [[
		//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
		   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
		    {field:'name',width:20,align:'left',title : '항목',hidden:false, formatter : addBoldFomatter},
		    {field:'value',width:75,align:'letf',title : '내용'}
		 ]];
	var grid = $('#sales_organ_info_grid').propertygrid({
		 showGroup: false,
		 scrollbarSize: 0,
		 autoRowHeight : true,
		  columns:columns
	});
}

function realodPage(){
	returnp.api.call("selectGiftCardSalesOrgan", {organCode : organCode}, function(res){
		console.log(res);
		if (res.resultCode == "100") {
			var data = {};
			data.total = Object.keys(res.data).length;
			data.rows = [];
			//data.rows.push({name:"조직 등록 번호",value: res.data.giftCardSalesOrganNo, group:"조직 정보",editor:null});
			//data.rows.push({name:"부모 조직 번호",value: slashFormatter(res.data.parentOrganNo), group:"조직 정보",editor:null});
			data.rows.push({name:"소속 조직명",value: slashFormatter(res.data.parentOrganName), group:"조직 정보",editor:null});
			data.rows.push({name:"조직 분류",value: organTypeFormatter(null, {organType : res.data.organType} , null), group:"조직 정보",editor:null});
			data.rows.push({name:"조직 이름)",value: res.data.organName, group:"조직 정보",editor:null});
			data.rows.push({name:"조직 코드(ID)",value: res.data.organCode, group:"조직 정보",editor:null});
			data.rows.push({name:"조직 패스워드(PW)",value: res.data.organPassword, group:"조직 정보",editor:null});
			data.rows.push({name:"조직 명의자",value: res.data.organOwner, group:"조직 정보",editor:null});
			data.rows.push({name:"사업자 번호",value: res.data.organBusinessNumber, group:"조직 정보",editor:null});
			data.rows.push({name:"판매 수수료",value: res.data.saleOrganSaleFeeRate, group:"조직 정보",editor:null});
			data.rows.push({name:"상태",value: organStatusFormatter(null, {organStatus : res.data.organStatus} , null), group:"조직 정보",editor:null});
			data.rows.push({name:"핸드폰",value: res.data.organPhone, group:"조직 정보",editor:null});
			data.rows.push({name:"이메일",value: res.data.organEmail, group:"조직 정보",editor:null});
			data.rows.push({name:"전화번호",value: res.data.organTel, group:"조직 정보",editor:null});
			data.rows.push({name:"주소",value: res.data.organAddr, group:"조직 정보",editor:null});
			data.rows.push({name:"은행명",value: res.data.organBankName, group:"조직 정보",editor:null});
			data.rows.push({name:"계좌번호",value: res.data.organBankAccount, group:"조직 정보",editor:null});
			data.rows.push({name:"계좌주",value: res.data.organBankAccountOwner, group:"조직 정보",editor:null});
			data.rows.push({name:"등록일",value: dateFormatter(res.data.createTime), group:"조직 정보",editor:null});
			data.rows.push({name:"수정일",value: dateFormatter(res.data.updateTime), group:"조직 정보",editor:null});
			$('#sales_organ_info_grid').propertygrid({
				data : data
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
