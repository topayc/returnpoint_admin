<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style = "padding: 10px;"  id = "fileTab">
    
	<div title=" 일반 매출 파일"  style="padding:20px;display:none;" id="container"   style="display:none;">
		<div style = "width:50%" >
			<ul style ='font-weight: bold';'>
				<li style ="margin-bottom:5px;font-size:12px">매출 파일 을 업로드함으로써 자동 매출 등록을 할 수 있습니다.</li>
				<li style ="margin-bottom:5px;font-size:12px">파일은 엑셀만 가능하며, 지정한 형식대로 작성되어야 합니다.</li>
				<li style ="margin-bottom:5px;font-size:12px">엑셀 파일의 포맷을 보려면 <a href = "#">여기</a>를 클릭하세요</li>
				<li style ="font-size:12px">등록한 매출 파일은 서버에 저장됩니다.</li>
			</ul>
		</div>
		
		<div style = "padding:20px 25px;width:50%" >
		   <form id="uploadSalesFileForm"   name = "uploadSalesFileForm"  enctype="multipart/form-data" method="post">
				<div style="margin-bottom:20px">
		            <input id = "salesFile"  name = "salesFile" type = "text" style="width:70%" accept=".xlsx">
					<a id = "submit_btn"  class = "submit_btn" style="width:80px;margin-right : 5px;margin-left:10px" >등록</a>
					<a id = "reset_btn"    class = "reset"   style="width:80px">리셋</a>		  		
		        </div>
					
				<div style="margin-top : 5px;margin-bottom:30px;text-align:right">
				 </div>
			</form>
		</div>
	</div>
	
	<div title="캐피탈 매출 파일"  style="padding:20px;display:none;" id="container"   style="display:none;">
				<div style = "width:50%" >
			<ul style ='font-weight: bold';'>
				<li style ="margin-bottom:5px;font-size:12px">캐피탈 프로그램인 수소, 및 쑥뜸, 어싱 매출 파일을 등록하고, 관련 포인트를 지급합니다</li>
				<li style ="margin-bottom:5px;font-size:12px">시스템상의 추천인 관계는 무시되고, 업로드한 파일상의 추천인 관계를 적용하여 포인트가 지급됩니다</li>
			</ul>
		</div>
		
		<div style = "padding:20px 25px;width:50%" >
		   <form id="uploadCapForm"   name = "uploadCap1Form"  enctype="multipart/form-data" method="post">
				<div style="margin-bottom:20px;margin-left : 10px;">
	                <select id = "paymentTransactionType" class="easyui-combobox" name="paymentTransactionType" label="캐피탈 프로그램 종류" style="width:50%">
	                	 <option value = "6">캐피탈 1호 프로그램 (수소)</option>
	                	 <option value = "7">캐피탈 2호 프로그램 (어싱)</option>
	                	
	                </select>	
	            </div>
				<div style="margin-bottom:20px;margin-left : 10px;">
		            <input id = "capFile"  name = "capFile" type = "text" style="width:100%" accept=".xlsx">
		        </div>
		        <div style="margin-bottom:20px">
					<a id = "cap_btn_submit"  class = "submit_btn" style="width:80px;margin-right : 5px;margin-left:10px" >등록</a>
					<a id = "reset_btn2"  class = "reset"   style="width:80px">리셋</a>		  		
		        </div>
		        
					
				<div style="margin-top : 5px;margin-bottom:30px;text-align:right">
				 </div>
			</form>
		</div>
	</div>
	
</div>

<script>
$('#fileTab').tabs({
    border:true,
    plain : false,
    pill : true,
    justified : true,
    onSelect:function(title){
    }
});

$('#salesFile').filebox({
    buttonText: '&nbsp;&nbsp;<strong>일반 매출 파일 선택</strong>&nbsp;&nbsp;',
    buttonAlign: 'left',
    height: '25px'
});

$('#capFile').filebox({
    buttonText: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>캐피탈 파일 선택</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
    buttonAlign: 'left',
    height: '25px',
    buttonWidth: 220,
	width: 500,
});



/* 리셋 버튼  초기화*/
$('.reset').linkbutton({
	onClick : function(){
		resetForm();
	}
});


$('#submit_btn').linkbutton({
	onClick : function(){
		uploadSalesFile()
	},
	iconCls:'icon-ok'
});

$('#cap_btn_submit').linkbutton({
	onClick : function(){
		uploadCapFile()
	},
	iconCls:'icon-ok'
});

$('#paymentTransactionType').combobox({
	label : roundLabel("&nbsp;&nbsp;캐피탈 프로그램 종류&nbsp;&nbsp;"),
	labelWidth : 150,
	showItemIcon: true,
	editable: false,
	width: 500,
	panelHeight: 'auto',
	labelPosition: 'left',
	multiple:false,
	onSelect : function(record){
	}
	//height : 25
});





function resetForm(){
	$('#salesFile').filebox("clear");
	$('#uploadCap1Form').filebox("clear");
	$('#uploadCap2Form').filebox("clear");
}

function uploadSalesFile(){
	$('.submit_btn').linkbutton('disable');
	$('.reset_btn').linkbutton('disable');
	$('#uploadSalesFileForm').form('submit', {
		url: "/api/upload/salesFile",
		type: 'POST',
		ajax:true,
		iframe: false,
		onSubmit: function(){
			if ($("#salesFile").filebox("files").length == 0) {
				$.messager.alert("알림", "업로드할 매출 파일을 선택해주세요");
				$('.submit_btn').linkbutton('enable');
				$('.reset_btn').linkbutton('enable');
				return false;
			}else {
				$('#progress_loading').show();
				return true;
			}
		},
		success: function(res){
			console.log(res);
			resetForm();
			$('.submit_btn').linkbutton('enable');
			$('.reset_btn').linkbutton('enable');
			res = JSON.parse(res)
			$.messager.alert("알림", res.message);
		}
	});
}

function uploadCapFile(){
	$('.submit_btn').linkbutton('disable');
	$('.reset_btn').linkbutton('disable');
	
	$('#uploadCapForm').form('submit', {
		url: "/api/upload/capFile",
		type: 'POST',
		ajax:true,
		iframe: false,
		onSubmit: function(){
			if ($("#capFile").filebox("files").length == 0) {
				$.messager.alert("알림", "업로드할 캐피탈 매출 파일을 선택해주세요");
				$('.submit_btn').linkbutton('enable');
				$('.reset_btn').linkbutton('enable');
				return false;
			}else {
				$('#progress_loading').show();
				return true;
			}
		},
		success: function(res){
			console.log(res);
			resetForm();
			$('.submit_btn').linkbutton('enable');
			$('.reset_btn').linkbutton('enable');
			res = JSON.parse(res)
			$.messager.alert("알림", res.message);
		}
	});
	
}
</script>
<script src="resources/js/${viewReqName}.js"></script>
