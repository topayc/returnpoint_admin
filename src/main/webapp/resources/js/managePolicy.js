initView();

function initView(){
	$('#policy_container').layout({
		border : false
	});
	$('#node_refund_panel').panel({
		title : "등급별 포인트 환급 정책",
		border : false,
		tools: "#node_refund_tool"
	});

	$('#blue_point_config').panel({
		tools : "#blue_point_tool"
	});

	$('#red_point_config').panel({
		tools : "#green_point_tool"
	});
	$('#membership_config').panel({
		tools : "#membership_tool"
	});
	$('#etc_config1').panel({
		tools : "#etc_tool1"
	});
	$('#etc_config2').panel({
		tools : "#etc_tool2"
	});
	$('#etc_config3').panel({
		tools : "#etc_tool2"
	});

	$('#rpay_withdraw_config').panel({
		tools : "#rpay_withdraw_panel_tool"
	});
	
	$('#rate').textbox({
		readonly : true,
		value : 1
	});
	$('#real').textbox();
	$('#rPayWithdrawalMinLimit').textbox();
	$('#rPayWithdrawalMaxLimit').textbox();
	$('#pointTransLimit').textbox();
	$('.easyui-textbox').textbox({
	 	/*iconCls : 'icon-ok',*/
		iconAlign : 'left',
		labelWidth : 230,
		labelPosition : 'left',
		labelAlign:'left',
		height :30
	});
	
	$('#policyForm').form();	
}

function submitForm(data){
	data.pointTransRate = data.real / data.rate;
	//console.log(data);

	var valid = true;
	for (var prop in data){
		if (data.hasOwnProperty(prop)) {
			if (!data[prop] || data[prop] == '0' ||data[prop].length < 1) {
				valid = false;
				//console.log("입력항 목 문제 발생( 0 이거나 공백) : " + prop);
			}
		}
	}
	console.log(data);
	if (!valid) {
		$.messager.alert('알림', '입력항목은 0 이 될 수 없으며, 모두 입력되어야 합니다');
		return;
	}
	
	returnp.api.call("createPolicy", data, function(res){
		if (res.resultCode  == "100") {
			$.messager.alert('알림', res.message);
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
}

function createPolicy(){
	var data = {};
	$("form").each(function(){
		$(this).find("input").each(function(){
			var elemName = $(this).attr("name");
			var elemValue = $(this).val();
			if (typeof elemName != 'undefined' && elemName.trim() !=''){
				data[elemName] = elemValue;
			}
		})
	});
	submitForm(data);
}

$(document).ready(function(){
	returnp.api.call("getPolicies", {}, function(res){
		if (res.resultCode  == "100") {
			console.log("loadPolicy");
			console.log(res);
			var data = res.rows[0];
			data.rate = 1;
			data.real = data.pointTransRate;
			//console.log(data);
			for (var prop in data){
				if (data.hasOwnProperty(prop)){
					if (prop != 'pointTransRate') {
						$("#" +prop).textbox('setValue', data[prop]);
					}
				}
			}
		}else {
			$.messager.alert('오류 발생', res.message);
		}
	});
});