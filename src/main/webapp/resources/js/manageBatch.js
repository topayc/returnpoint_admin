
initView();
function initView(){
	$('.easyui-panel').panel({ border: false });
	/* 위젯 공통 설정 */
	$('.easyui-textbox').textbox({
		labelPosition : 'left',
		labelAlign:'left',
	});

	$('.easyui-linkbutton').linkbutton();
	
	$('#batchData').textbox({
		multiline : true,
	});
	
	$('#batchCount').numberspinner({
	    min: 1,
	    max: 100,
	    editable: true,
	    value : 1
	});
	
	$('#batchServerAddr').combobox({
		showItemIcon: true,
        editable: false,
        panelHeight: 'auto',
        multiple:false,
	});
	$('#batchCmd').combobox({
		showItemIcon: true,
        editable: false,
        panelHeight: 'auto',
        multiple:false,
	});
}


$(document).ready(function(){
});