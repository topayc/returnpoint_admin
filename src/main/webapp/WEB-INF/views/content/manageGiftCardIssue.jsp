<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div style = "padding: 10px;"  id = "fileTab">
    
	<div title="<strong>상품권 발행</strong>"  style="padding:20px;display:none;" id="container"  style="display:none;">
		<div style = "width:50%" >
			<ul style ='font-weight: bold';'>
				<li style ="margin-bottom:5px;font-size:12px">상품권은 모바일과 실물상품권 2가지를 발행할 수 있지만, 혼용할 수 없습니다.</li>
				<li style ="margin-bottom:5px;font-size:12px">상품권은 한번에 100,000 매 까지 일괄 발행이 가능합니다</li>
				<li style ="margin-bottom:5px;font-size:12px">판매 가능한 상품권만 발행할 수 있습니다.</li>
			</ul>
		</div>
		
		<div style = "padding:20px 15px;width:50%" >
		   <form id="giftCardIssueForm"   name = "giftCardIssueForm"  enctype="multipart/form-data" method="post">
				<div style="margin-bottom:20px;">
					<select id ="giftCardNo" name="giftCardNo"   style="width:100%" data-options="label:'상품권',labelWidth :100,labelPosition : 'left'">
						<c:forEach var="giftCard"  items="${giftCards}" varStatus="status">
							  <c:if test = "${ giftCard.productStatus == '1' }">
								  <option value='${giftCard.productNo}' >${giftCard.productName} - <fmt:formatNumber value="${giftCard.productPrice}" pattern="###,###,###,###"/>원 상품권</option>
							  </c:if>
						</c:forEach>
						
					</select>
				</div>
				<div style="margin-bottom:20px;;" ><input id ="giftCardType"  name="organCode" style="width:100%;" data-options="labelWidth :100,labelPosition : 'left'"> </div>
				<div style="margin-bottom:20px;;" ><input id ="giftCardSalesOrganNo"  name="giftCardSalesOrganNo" style="width:100%;" data-options="labelWidth :100,labelPosition : 'left'"> </div>
				<div style="margin-bottom:20px;;" ><input id ="giftCardSalesOrganName"  name="giftCardOrganNo" style="width:100%;" data-options="labelWidth :100,labelPosition : 'left'"> </div>
				<div style="margin-bottom:20px;;" ><input id ="qty"  name="qty" style="width:100%;" data-options="labelWidth :100,labelPosition : 'left'"> </div>
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
	showItemIcon: true,
	editable: false,
	width: 500,
	panelHeight: 'auto',
	labelPosition: 'left',
	multiple:false,
});
$('#giftCardType').combobox({
	label : roundLabel("상품권 유형"),
	showItemIcon: true,
	editable: false,
	width: 500,
	panelHeight: 'auto',
	labelPosition: 'left',
	multiple:false,
});

$('#giftCardSalesOrganNo').textbox({
	prompt: '발주처 번호',
	label : roundLabel("발주처 번호"),
	width: 500,
	editable : false,
	icons : [{
        iconCls:'icon-search',
        handler: function(e){
            var v = $(e.data.target).textbox('getValue');
            loadNodeListView({
                targetElem : "#dlgForm2",
                title : "상품권 영업 본사 검색",
                queryOptions : {
                    memberEmail : v,
                    viewReqName : "nodeList",
                    searchNodeType :  "10",
                    width : '65%'
                    
                }
            },function callback(selNode){
                $(e.data.target).textbox('setValue', selNode.giftCardSalesOrganNo);
                $("#parentOrganName").textbox('setValue', selNode.organName);
            });
        }
    }]
});

$('#giftCardSalesOrganName').textbox({
	label : roundLabel("발주처 이름"),
	prompt: '발주처 이름',
	width: 500,
	editable : false
});
$('#qty').textbox({
	label : roundLabel("수량 (매수)"),
	prompt: '수량',
	width: 500,
	editable : false
});


function makeFormData(){
	var param = $("#giftCardIssueForm").serializeObject();
	return param;
}
function roundLabel(str){
	return '<span style = "border-radius: 15px;background-color: #444444;padding: 5px;color : #ffffff;font-weight : bold;">'+ str +'</span>';
}
function resetForm(){
	$('#giftCardIssueForm').form("reset");;
}

function issueGiftCard(){
	$('#submit_btn').linkbutton('disable');
	$('#reset_btn').linkbutton('disable');
}
</script>
<script src="resources/js/${viewReqName}.js"></script>
