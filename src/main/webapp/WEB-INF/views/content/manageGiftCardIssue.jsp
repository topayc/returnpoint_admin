<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div style = "padding: 10px;"  id = "fileTab">
    
	<div title="<strong>상품권 발행</strong>"  style="padding:20px;display:none;" id="container"  style="display:none;">
		<div style = "width:50%" >
			<ul style ='font-weight: bold';'>
				<li style ="margin-bottom:5px;font-size:12px">상품권은 모바일과 실물상품권 2가지를 발행할 수 있지만, 혼용할 수 없습니다.</li>
				<li style ="margin-bottom:5px;font-size:12px">상품권은 한번에 <span style = "color: red">50,000</span> 매 까지 일괄 발행이 가능합니다</li>
				<li style ="margin-bottom:5px;font-size:12px">판매 가능한 상품권만 발행할 수 있습니다.</li>
				<li style ="margin-bottom:5px;font-size:12px">많은 수량을 생성할 경우 비동기로 생성이 되며, 생성 시작 및 완료 상태는 발행 내역에서 확인 가능합니다.</li>
			<!-- 	<li style ="margin-bottom:5px;font-size:12px">상품권 발생 및 관리 절차.
					<ul style ='font-weight: bold';'>
						<li style ="margin-bottom:5px;font-size:12px">상품권 생성</li>
						<li style ="margin-bottom:5px;font-size:12px">상품권 발행 내역 관리 페이지에서 생성 완료 확인</li>
						<li style ="margin-bottom:5px;font-size:12px">입금 완료로 변경하면 요청한 상품권 내역이 생성</li>
					</ul>
				</li> -->
				
			</ul>
		</div>
		
		<div style = "padding:20px 15px;width:50%" >
		   <form id="giftCardIssueForm"   name = "giftCardIssueForm"  enctype="multipart/form-data" method="post">
				<div style="margin-bottom:20px;" >
					<select id ="giftCardIssuerType"  name="giftCardIssuerType" style="width:100%;" >
						<option value= "12">판매점</option>
					</select> 
				</div>
				<input type = "hidden" id ="giftCardSalesOrganNo"  name="giftCardSalesOrganNo"  > 
				<div style="margin-bottom:20px;;" ><input id ="giftCardSalesOrganName"  name="giftCardSalesOrganName" style="width:100%;" > </div>
				<div style="margin-bottom:20px;;" ><input id ="giftCardSalesOrganCode"  name="giftCardSalesOrganCode" style="width:100%;" > </div>
				
				<div style="margin-bottom:20px;">
					<select id ="giftCardNo" name="giftCardNo"   style="width:100%" >
						<c:forEach var="giftCard"  items="${giftCards}" varStatus="status">
							  <c:if test = "${ giftCard.productStatus == '1' }">
								  <option value='${giftCard.productNo}' >${giftCard.productName} - <fmt:formatNumber value="${giftCard.productPrice}" pattern="###,###,###,###"/>원 상품권</option>
							  </c:if>
						</c:forEach>
					</select>
				</div>
				<div style="margin-bottom:20px;;" >
					<select id ="giftCardType"  name="organCode" style="width:100%;" >
						<c:forEach var="giftCardType"  items="${giftCardTypes}" varStatus="status">
							  <c:if test = "${ giftCardType.useInAdmin == 'Y' }">
								  <option value='${giftCardType.key}' >${giftCardType.value}</option>
							  </c:if>
						</c:forEach>
					</select> 
				</div>
				<div style="margin-bottom:20px;;" ><input id ="qty"  name="qty" style="width:100%;"> </div>
				<div style="margin-top:20px;padding:5px 0;display:inline-block;">
					<a href="javascript:void(0)" id = "create_btn"  style="width:60px;margin-right : 5px">발행</a>
					<a href="javascript:void(0)" id = "reset_btn"   style="width:60px">취소</a>
				</div>
			</form>
		</div>
	</div>
	
	<div title="<strong>기타</strong>"  style="padding:20px;display:none;" id="container"   style="display:none;">
	</div>
	
</div>

<script>

$('#fileTab').tabs({
    border:true,
    plain : true,
    pill : false,
    onSelect:function(title){
    }
});

/* 검색 버튼  초기화*/
$('#create_btn').linkbutton({
	onClick : function(){
		issueGiftCard()
	},
	iconCls:'icon-ok'
});

/* 리셋 버튼  초기화*/
$('#reset_btn').linkbutton({
	onClick : function(){
		resetForm();
	}
});


$('#giftCardNo').combobox({
	label : roundLabel("발행 상품권"),
	labelWidth : 100,
	showItemIcon: true,
	editable: false,
	width: 500,
	panelHeight: 'auto',
	labelPosition: 'left',
	multiple:false,
	//height : 25
});
$('#giftCardType').combobox({
	label : roundLabel("상품권 유형"),
	labelWidth : 100,
	showItemIcon: true,
	editable: false,
	width: 500,
	panelHeight: 'auto',
	labelPosition: 'left',
	multiple:false,
	//height : 25
});

$('#giftCardIssuerType').combobox({
	label : roundLabel("발주처 타입"),
	labelWidth : 100,
	showItemIcon: true,
	editable: false,
	width: 500,
	panelHeight: 'auto',
	labelPosition: 'left',
	multiple:false,
	//height : 25
});

$('#giftCardSalesOrganName').textbox({
	prompt: '검색 버튼을 눌러 검색',
	label : roundLabel("발주처 검색"),
	labelWidth : 100,
	width: 500,
	/* height : 25, */
	editable : false,
	icons : [{
        iconCls:'icon-search',
        handler: function(e){
            var issuerType =  $("#giftCardIssuerType").combobox("getValue");
            var issuerTypeName =  $("#giftCardIssuerType").combobox("getText");
        	var v = $(e.data.target).textbox('getValue');
            loadNodeListView({
                targetElem : "#dlgForm2",
                title : "상품권 " + issuerTypeName + " 검색",
                queryOptions : {
                    memberEmail : v,
                    viewReqName : "nodeList",
                    searchNodeType :  issuerType,
                    width : '65%'
                    
                }
            },function callback(selNode){
            	$("#giftCardSalesOrganNo").val(selNode.giftCardSalesOrganNo);
                $("#giftCardSalesOrganName").textbox('setValue', selNode.organName);
                $("#giftCardSalesOrganCode").textbox('setValue', selNode.organCode);
            });
        }
    }]
});
$('#giftCardSalesOrganCode').textbox({
	prompt: '발주처 코드',
	label : roundLabel("발주처 코드"),
	labelWidth : 100,
	width: 500,
	/* height : 25, */
	editable : false
});

$('#qty').numberbox({
	label : roundLabel("수량 (매수)"),
	labelWidth : 100,
	prompt: '수량 - 숫자만 입력 가능',
	width: 500,
	/* height : 25, */
	min:1,
	groupSeparator : ",",
	max : 50000,
	//suffix : " 매"
});


function makeFormData(){
	var param = $("#giftCardIssueForm").serializeObject();
	return param;
}
function roundLabel(str){
	return '<span style = "border-radius: 15px;background-color: #444444;padding: 5px;color : #ffffff;font-weight : bold;">'+ str +'</span>';
}
function resetForm(){
	$("#giftCardSalesOrganNo").val("");
	$('#giftCardIssueForm').form("reset");;
}

function issueGiftCard(){
	$('#submit_btn').linkbutton('disable');
	$('#reset_btn').linkbutton('disable');
}
</script>
<script src="resources/js/${viewReqName}.js"></script>
