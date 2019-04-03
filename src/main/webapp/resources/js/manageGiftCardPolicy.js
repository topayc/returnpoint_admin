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
	var properties  = {"total":7,"rows":[
		{"name":"상품권 판매시 본사 수수료","value":"0.12","group":"정산","editor":"text"},
		{"name":" 상품권 판매시 총판 수수료","value":"0.12","group":"정산","editor":"text"},
		{"name":" 상품권 판매시 판매점 수수료","value":"0.12","group":"정산","editor":"text"},
	    {"name":" 상품권 결제 수수료","value":"0.12","group":"정산","editor":"text"},
	    {"name":" 상품권 적립율","value":"0.12","group":"정산","editor":"text"},

	    {"name":" 회사명","value":"리턴포인트","group":"회사 운영 정보","editor":"text"},
	    {"name":" 웹사이트","value":"https://www.returnp.com","group":"회사 운영 정보","editor":"text"},
	    {"name":"회사 은행명","value":"신한은행","group":"회사 운영 정보","editor":"text"},
	    {"name":"은행 계좌","value":"121-121-1212","group":"회사 운영 정보","editor":"text"},
	    {"name":"계좌주","value":"김홍기","group":"회사 운영 정보","editor":"text"},
	    {"name":"구매 대금 입금시 입금텍스트","value":"주문명_판매점명_입금금액","group":"회사 운영 정보" , "editor":"text" },
	    {"name":" 고객센터 전화번호","value":"bill@gmail.com","group":"고객 센터 정보","editor":"text"},
	    {"name":" 고객센터 운영시간","value":"bill@gmail.com","group":"고객 센터 정보","editor":"text"},
	    {"name":"고객센터 이메일","value":"topayc@naver.com","group":"고객 센터 정보","editor":"text"}
	]}
	$('#gift_card_policy_grid').propertygrid({
		data : properties,
	    showGroup: true,
	    scrollbarSize: 0,
	    border : false,
	    columns:[[
			{field:'name',title:'정책 항목',width:4,resizable:true},
			{field:'value',title:'정책 값',width:8, resizable:false}
		]]
	});
}

