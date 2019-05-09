<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style = "padding: 10px;"  id = "fileTab">
    
	<div title="<strong>매출 파일</strong>"  style="padding:20px;display:none;" id="container"   style="display:none;">
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
					<a id = "submit_btn"  style="width:80px;margin-right : 5px;margin-left:10px" >등록</a>
					<a id = "reset_btn"   style="width:80px">리셋</a>		  		
		        </div>
					
				<div style="margin-top : 5px;margin-bottom:30px;text-align:right">
				 </div>
			</form>
		</div>
	</div>
	
	<div title="<strong>밴사 결제 내역 파일</strong>"  style="padding:20px;display:none;" id="container"   style="display:none;">
				<div style = "width:50%" >
			<ul style ='font-weight: bold';'>
				<li style ="margin-bottom:5px;font-size:12px">밴사로 받은 결제 취소 내역을 등록하여, 포인트를 회수합니다.</li>
				<li style ="margin-bottom:5px;font-size:12px">파일은 엑셀만 가능하며, 지정한 형식대로 작성되어야 합니다.</li>
				<li style ="margin-bottom:5px;font-size:12px">엑셀 파일의 포맷을 보려면 <a href = "#">여기</a>를 클릭하세요</li>
				<li style ="font-size:12px">등록한 매출 파일은 서버에 저장됩니다.</li>
			</ul>
		</div>
		
		<div style = "padding:20px 25px;width:50%" >
		   <form id="uploadVanFileForm"   name = "uploadVanFileForm"  enctype="multipart/form-data" method="post">
				<div style="margin-bottom:20px">
		            <input id = "vanFile"  name = "vanFile" type = "text" style="width:70%" accept=".xlsx">
					<a id = "submit_btn2"  style="width:80px;margin-right : 5px;margin-left:10px" >등록</a>
					<a id = "reset_btn2"   style="width:80px">리셋</a>		  		
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
    pill : false,
    justified : false,
    onSelect:function(title){
    }
});

$('#salesFile').filebox({
    buttonText: '&nbsp;&nbsp;<strong>매출 파일 선택</strong>&nbsp;&nbsp;',
    buttonAlign: 'left',
    height: '25px'
});

/* 검색 버튼  초기화*/
$('#submit_btn').linkbutton({
	onClick : function(){
		uploadSalesFile()
	},
	iconCls:'icon-ok'
});

/* 리셋 버튼  초기화*/
$('#reset_btn').linkbutton({
	onClick : function(){
		resetForm();
	}
});

$('#vanFile').filebox({
    buttonText: '&nbsp;&nbsp;<strong>밴사 취소 내역 파일</strong>&nbsp;&nbsp;',
    buttonAlign: 'left',
    height: '25px'
});

/* 검색 버튼  초기화*/
$('#submit_btn2').linkbutton({
	onClick : function(){
		uploadSalesFile()
	},
	iconCls:'icon-ok'
});

/* 리셋 버튼  초기화*/
$('#reset_btn2').linkbutton({
	onClick : function(){
		resetForm();
	}
});

function resetForm(){
	$('#salesFile').filebox("clear");
}

function uploadSalesFile(){
	$('#submit_btn').linkbutton('disable');
	$('#reset_btn').linkbutton('disable');
	$('#uploadSalesFileForm').form('submit', {
		url: "/api/upload/salesFile",
		type: 'POST',
		ajax:true,
		iframe: false,
		onSubmit: function(){
			$("#salesFile").filebox("files")
			if ($("#salesFile").filebox("files").length == 0) {
				$.messager.alert("알림", "업로드할 매출 파일을 선택해주세요");
				$('#submit_btn').linkbutton('enable');
				$('#reset_btn').linkbutton('enable');
				return false;
			}else {
				$('#progress_loading').show();
				return true;
			}
		},
		success: function(res){
			console.log(res);
			resetForm();
			$('#reset_btn').linkbutton('enable');
			$('#submit_btn').linkbutton('enable');
			res = JSON.parse(res)
			$.messager.alert("알림", res.message);
		}
	});
}
</script>
<script src="resources/js/${viewReqName}.js"></script>
