

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
	
	$('#searchAffiliate').combobox({
		labelPosition : 'top',
		showItemIcon: true,
		editable: true,
		labelPosition: 'top',
		multiple:false,
		required:true,
		width: 240,
		onChange : function(oldValue, newValue){
	/*		$('#search_btn').click();
			if (newValue =='0') {
				$('#searchForm').form('reset');
				$('#registerAffiliateDetailForm').form('reset');
			}else {
			}*/
		}
	});
	
	/* 검색 버튼  초기화*/
	$('#search_btn').linkbutton({
		onClick : function(){
			var affiliateNo = $('#searchAffiliate').combobox('getValue').trim();
			var buisnessName= $('#searchAffiliate').combobox('getText').trim();
			if (affiliateNo == '0') {
				$.messager.alert("알림", "정보를 등록 및 수정할 제휴 업체를 선택해주세요");
				return;
			}
			
			var param = {affiliateNo : affiliateNo}
			returnp.api.call("selectAffiliateDetails", param, function(res){
				console.log(res);
				if (res.resultCode == "100") {
					if (res.rows.length > 0) {
						$('#registerAffiliateDetailForm').form('load',res.rows[0]);
						$('#preview').attr('src', "");
						$('#preview').attr('src', "https://www.returnp.com");
					}else {
						$('#registerAffiliateDetailForm').form('reset');
						$('#affiliateNo').textbox("setValue", affiliateNo);
						$('#buisnessName').textbox("setValue", buisnessName);
						$.messager.alert("알림","해당 업체의 정보가 등록되어 있지 않습니다</br>등록을 하시려면 아래 정보를 기입후 확인버튼을 눌러주세요");
					}
				}else {
					$.messager.alert("알립", res.message);
				}
			});
		},
		width : 70,
		iconCls:'icon-search'
	});

	/* 리셋 버튼  초기화*/
	$('#reset_btn').linkbutton({
		onClick : function(){
			$('#searchForm').form('reset');
			$('#registerAffiliateDetailForm').form('reset');
		},
		width : 70
	});
	
	$("#afffiliateNotice").textbox({
		multiline:true,
		height: 150
	});
	$("#overview").textbox({
		multiline:true,
		height: 150
	});
	$("#affiliateNews").textbox({
		multiline:true,
		height: 150
	});
	$("#etc").textbox({
		multiline:true,
		height: 150
	});

	$("#etcLink").textbox({
		prompt:"(입력 예제)  youTube:https://www.youTube.com"
	});
	
	$("#commonWeb").textbox({
		prompt:"https://www.youTube.com"
	});

	$("#businessNumber").textbox({
		prompt:"123-12-131243"
	});


	$('#submit_btn').linkbutton({
		onClick : function(){
			$("#registerAffiliateDetailForm").submit();
		},
		iconCls:'icon-ok'
	});
	
	$("#registerAffiliateDetailForm").form({
		url:'/api/affiliateDetail/update',
		ajax : true,
	    onSubmit: function(param){
	    },
	    success:function(res){
	    	console.log("세부 정보 등록 결과")
	    	console.log(res);
	    	res = JSON.parse(res);
	    	$.messager.alert("알림", res.message);
	    	if (res.resultCode == "100") {
	    		$('#registerAffiliateDetailForm').form('load',res.data);
	    		$('#preview').attr('src', "");
				$('#preview').attr('src', "https://www.returnp.com");
	    	}else {
	    	}
	    }
	});
	
	$('.easyui-textbox').textbox({
		labelPosition : 'top',
	});

	$('#amImage1').filebox({
		accept: 'image/*',
		buttonText: '&nbsp;&nbsp;&nbsp;&nbsp;이미지1&nbsp;&nbsp;&nbsp;&nbsp;     ',
	    buttonAlign: 'left',
	    labelPosition : 'top'
	});
	
	$('#amImage2').filebox({
		accept: 'image/*',
		buttonText: '&nbsp;&nbsp;&nbsp;&nbsp;이미지2&nbsp;&nbsp;&nbsp;&nbsp; ',
	    buttonAlign: 'left',
	    labelPosition : 'top'
	});
	
	$('#amImage3').filebox({
		accept: 'image/*',
		buttonText: '&nbsp;&nbsp;&nbsp;&nbsp;이미지3&nbsp;&nbsp;&nbsp;&nbsp;',
	    buttonAlign: 'left',
	    labelPosition : 'top'
	});

	$('#amImage4').filebox({
		accept: 'image/*',
		buttonText: '&nbsp;&nbsp;&nbsp;&nbsp;이미지3&nbsp;&nbsp;&nbsp;&nbsp;',
		buttonAlign: 'left',
		labelPosition : 'top'
	});
}


$(function(){
	initView();
/*	$('#node_list').datagrid().datagrid('enableCellEditing');
	setListPager();
	realodPage();*/
})
