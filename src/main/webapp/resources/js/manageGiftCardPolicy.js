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
		{"name":" 상품권 판매시 본사 수수료","value":"10%","group":"ID Settings","editor":"text"},
		{"name":" 상품권 판매시 총판 수수료","value":"10%","group":"ID Settings","editor":"text"},
		{"name":" 상품권 판매시 판매점 수수료","value":"10%","group":"ID Settings","editor":"text"},
	    {"name":" 상품권 환전 수수료","value":"10%","group":"ID Settings","editor":"text"},
	    {"name":" 정책 담당자","value":"bill@gmail.com","group":"Marketing Settings","editor":{
	        "type":"validatebox",
	        "options":{
	            "validType":"email"
	        }
	    }},
	    {"name":"정잭 문의","value":"false","group":"Marketing Settings","editor":{
	        "type":"checkbox",
	        "options":{
	            "on":true,
	            "off":false
	        }
	    }}
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

