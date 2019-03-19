<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style ="padding: 5px;height : 100%">
	<div  >
		<table id = "search_result" style ="width:100%;height:400px"></table>
	</div>
</div>

<script>

$('#search_result').datagrid({
	title : "[검색 결과]",
	singleSelect:true,
	collapsible:false,
	fitColumns:true,
	selectOnCheck : true,
	checkOnSelect : true,
	border:true,	
	rownumbers : false,
	pagination: false,
	autoRowHeight : false,
	onSelect : function(){},
	onLoadSuccess : function(){},
	
    columns:nodeColumn(nodeType)
});


function nodeColumn(nodeType){
	var columns ; 
	
	switch(nodeType){
	case "1":   //일반 회원
		columns = [[
			//{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'memberNo',width:20,align:'center',title : '번호',hidden:false},
			    {field:'memberName',width:40,align:'center',title : '이름'},
			    {field:'memberEmail',width:60,align:'center',title : '이메일'},
			    {field:'memberPassword',width:30,align:'center',title : '비밀번호', hidden: true},
			    {field:'memberPassword2',width:30,align:'center',title : '비밀번호2', hidden: true},
			    {field:'memberPhone',width:40,align:'center',title : '핸드폰'},
			    {field:'memberStatus',width:30,align:'center',title : '상태',formatter : nodeStatusFormatter},
			    {field:'recommenderName',width:30,align:'center',title : '추천인', hidden:false,formatter : slashFormatter},
			    {field:'recommenderEmail',width:60,align:'center',title : '추천인 이메일', hidden:true, formatter : slashFormatter},
			    {field:'country',width:20,align:'center',title : '국가', formatter : slashFormatter},
			    {field:'greenPointAmount',width:40,align:'center',title : 'R POINT', formatter : numberGreenFormatter},
			    {field:'redPointAmount',width:40,align:'center',title : 'R PAY', formatter : numberRedFormatter},
			    {field:'memberAuthType',width:30,align:'center',title : '인증방법', formatter : authTypeFormatter,  hidden: true},
			    {field:'memberType',width:20,align:'center',title : '유형',hidden:true},
			    {field:'isSoleDist',width:20,align:'center',title : '총판', formatter : ynFormatter},
			    {field:'isRecommender',width:20,align:'center',title : '정회원', formatter : ynFormatter},
			    {field:'isSaleManager',width:30,align:'center',title : '영업관리자', formatter : ynFormatter},
			    {field:'isAgency',width:20,align:'center',title : '대리점', formatter : ynFormatter},
			    {field:'isBranch',width:20,align:'center',title : '지사', formatter : ynFormatter},
			    {field:'isAffiliate',width:25,align:'center',title : '협력업체', formatter : ynFormatter},
			    {field:'greenPointAccStatus',width:20,align:'center',title : 'G 적립', formatter : ynFormatter},
			    {field:'redPointAccStatus',width:20,align:'center',title : 'R 적립', formatter : ynFormatter},
			    {field:'greenPointUseStatus',width:20,align:'center',title : 'G 사용', formatter : ynFormatter},
			    {field:'redPointUseStatus',width:20,align:'center',title : 'R 사용', formatter : ynFormatter},
			    {field:'regType',width:40,align:'center',title : '등록 유형',formatter : registTypeFormatter, hidden: true},
			    {field:'regAdminNo',width:20,align:'center',title : '등록자', formatter : registAdminFormatter, hidden: true},
			    {field:'createTime',width:50,align:'center',title : '등록일',formatter : dateFormatter},
			    {field:'updateTime',width:30,align:'center',title : '수정일',hidden: true, hidden: true}
			 ]];
		break;   
	case "2":   // 정회원(추천인)
		columns = [[
				{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'recommenderNo',width:20,align:'center',title : '번호',hidden:false},
			    {field:'recommenderCode',width:40,align:'center',title : '정회원 코드'},
			    {field:'recommenderName',width:50,align:'center',title : '정회원 이름'},
			    {field:'recommenderStatus',width:20,align:'center',title : '정회원 상태'},
			    {field:'recommenderAddress',width:50,align:'center',title : '정회원 주소'},
			    {field:'recommenderTel',width:40,align:'center',title : '정회원 전화'},
			    {field:'recommenderPhone',width:30,align:'center',title : '정회원 핸드폰'},
			    {field:'createTime',width:30,align:'center',title : '등록일'},
			    {field:'updateTime',width:30,align:'center',title : '수정일',hidden:true},
			    {field:'memberNo',width:15,align:'center',title : 'memberNo',hidden:true},
			    {field:'saleManagerNo',width:15,align:'center',title : 'recommenderNo',hidden:true}
			    ]];
		break;
	case "3":    // 지사
		columns = [[
				{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'branchNo',width:20,align:'center',title : '번호',hidden:false},
			    {field:'branchCode',width:40,align:'center',title : '지사 코드'},
			    {field:'branchName',width:50,align:'center',title : '지사 이름'},
			    {field:'branchStatus',width:20,align:'center',title : '지사 상태'},
			    {field:'branchAddress',width:50,align:'center',title : '지사 주소'},
			    {field:'branchTel',width:40,align:'center',title : '지사 전화'},
			    {field:'branchPhone',width:30,align:'center',title : '지사 핸드폰'},
			    {field:'createTime',width:30,align:'center',title : '등록일'},
			    {field:'updateTime',width:30,align:'center',title : '수정일',hidden:true},
			    {field:'memberNo',width:15,align:'center',title : 'memberNo',hidden:true},
			    {field:'recommenderNo',width:15,align:'center',title : 'recommenderNo',hidden:true}
			    ]];
		break;
	case "4":     // 대리점
		columns = [[
				{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'agencyNo',width:20,align:'center',title : '번호',hidden:false},
			    {field:'agencyCode',width:40,align:'center',title : '대리점 코드'},
			    {field:'agencyName',width:40,align:'center',title : '대리점 이름'},
			    {field:'agencyStatus',width:20,align:'center',title : '대리점  상태'},
			    {field:'agencyAddress',width:50,align:'center',title : '대리점 주소'},
			    {field:'agencyTel',width:40,align:'center',title : '대리점 전화'},
			    {field:'agencyPhone',width:30,align:'center',title : '대리점 핸드폰'},
			    {field:'createTime',width:30,align:'center',title : '등록일'},
			    {field:'updateTime',width:30,align:'center',title : '수정일',hidden:true},
			    {field:'memberNo',width:15,align:'center',title : 'memberNo',hidden:true},
			    {field:'branchNo',width:15,align:'center',title : 'barachNo',hidden:true},
			    {field:'recommenderNo',width:15,align:'center',title : 'recommenderNo',hidden:true}
			    ]];
		break;
	case "5":     // 협력 업체(가맹점)
		columns = [[
				{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'affiliateNo',width:20,align:'center',title : '번호'},
			    {field:'affiliateCode',width:40,align:'center',title : '가맹점 코드'},
			    {field:'affiliateSerial',width:40,align:'center',title : 'Serial 번호'},
			    {field:'affiliateName',width:50,align:'center',title : '가맹점 이름'},
			    {field:'affiliateType',width:50,align:'center',title : '가맹점 타입', formatter: affiliateTypeFormatter},
			    {field:'affiliateStatus',width:20,align:'center',title : '가맹점  상태',hidden:true},
			    {field:'affiliateAddress',width:50,align:'center',title : '가맹점 주소',hidden:true},
			    {field:'affiliateTel',width:40,align:'center',title : '가맹점 전화'},
			    {field:'affiliatePhone',width:30,align:'center',title : '가맹점 핸드폰'},
			    {field:'createTime',width:30,align:'center',title : '등록일'},
			    {field:'updateTime',width:30,align:'center',title : '수정일',hidden:true},
			    {field:'memberNo',width:15,align:'center',title : 'memberNo',hidden:true},
			    {field:'branchNo',width:15,align:'center',title : 'barachNo',hidden:true},
			    {field:'recommenderNo',width:15,align:'center',title : 'recommenderNo',hidden:true}
			    ]];
		break;
	case "6":    // 영업 관리자	
		columns = [[
				{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'saleManagerNo',width:20,align:'center',title : '번호',hidden:false},
			    {field:'saleManagerCode',width:40,align:'center',title : '관리자 코드'},
			    {field:'saleManagerName',width:50,align:'center',title : '관리자 이름'},
			    {field:'saleManagerStatus',width:40,align:'center',title : '관리자  상태'},
			    {field:'saleManagerAddress',width:50,align:'center',title : '관리자 주소'},
			    {field:'saleManagerTel',width:40,align:'center',title : '관리자 전화'},
			    {field:'saleManagerPhone',width:30,align:'center',title : '관리자 핸드폰'},
			    {field:'createTime',width:30,align:'center',title : '등록일'},
			    {field:'updateTime',width:30,align:'center',title : '수정일',hidden:true},
			    {field:'memberNo',width:15,align:'center',title : 'memberNo',hidden:true}
			    ]];
		break;
	case "7":    // 총판	
		columns = [[
				{field:'check',width:30,align:'center',title : '선택',checkbox : true},
			   // {field:'action',width:20,align:'center', halign : 'center',formatter : projectActionFormatter},
			    {field:'soleDistNo',width:20,align:'center',title : '번호',hidden:false},
			    {field:'soleDistCode',width:40,align:'center',title : '총판 코드'},
			    {field:'soleDistName',width:50,align:'center',title : '총판 이름'},
			    {field:'soleDistStatus',width:40,align:'center',title : '총판  상태'},
			    {field:'soleDistAddress',width:50,align:'center',title : '총판 주소'},
			    {field:'soleDistTel',width:40,align:'center',title : '총판 전화'},
			    {field:'soleDistPhone',width:30,align:'center',title : '총판 핸드폰'},
			    {field:'createTime',width:30,align:'center',title : '등록일'},
			    {field:'updateTime',width:30,align:'center',title : '수정일',hidden:true},
			    {field:'memberNo',width:15,align:'center',title : 'memberNo',hidden:true}
			    ]];
		break;

	}
	return columns;
}

</script>
