<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Expires" content="-1"> 
  <meta http-equiv="Pragma" content="no-cache"> 
<META http-equiv="Expires" content="-1"> 
<META http-equiv="Pragma" content="no-cache"> 
<META http-equiv="Cache-Control" content="No-Cache"> 

  <title>ReturnP Admin</title>
  <link rel="stylesheet" type="text/css" href="resources/js/lib/easyui/themes/bootstrap/easyui.css">
  <link rel="stylesheet" type="text/css" href="resources/js/lib/easyui/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="resources/js/lib/easyui/themes/color.css">
  <link rel="stylesheet" type="text/css" href="resources/js/lib/easyui/texteditor.css">
  <link rel="stylesheet" type="text/css" href="resources/css/default.css">
<!--   <link type="text/css" rel="stylesheet" href="resources/css/font-awesome.min.css"> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
  <script type="text/javascript" src="resources/js/lib/jquery.min.js"></script>
  <script type="text/javascript" src="resources/js/lib/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="resources/js/lib/jquery-number.js"></script>
  <script type="text/javascript" src="resources/js/lib/easyui/jquery.portal.js"></script>
  <script type="text/javascript" src="resources/js/lib/easyui/jquery.texteditor.js"></script>
<!-- <script type="text/javascript" src="resources/js/lib/easyui/datagrid-cellediting.js"></script> -->
  <script type="text/javascript" src="resources/js/returnp.js"></script>
  <script type="text/javascript" src="resources/js/returnpCommon.js"></script>
  <script type="text/javascript" src="resources/js/returnPHelper.js"></script>
  <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyB-bv2uR929DOUO8vqMTkjLI_E6QCDofb4"></script>		
</head>
<style>

	/* .datagrid-row{
    	height:30px;
	} */
	
	#progress_loading
	{
	 position: absolute;
	 left: 50%;
	 top: 45%;
	 border : 1px solid #444444;
	 padding : 5px 5px;
	 background: #ffffff;
	 z-index : 10000000000
	}

	.datagrid-header .datagrid-cell{
        font-family : '돋움''
    }
  .datagrid-cell{
     font-family : '맑은 고딕''
  }
</style>
<body class="dashboard">
    <div class="container easyui-layout"  style="width:100%">
    	<form id = "logOutForm" method="post" action="/j_spring_security_logout">
        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div data-options="region:'north',border : true" style="height:30px;" >
        	<span style = "position: absolute;left : 15px;top:6px;display :none;font-weight : bold" id = "returnp_noti_membership">
        		<i class = "fa fa-exclamation-circle" style = "color : red"></i>
        		&nbsp;<span style="color: white;font-size:9pt; background-color:red;padding:2px 3px;border-radius: 5px;" >정회원 고객 입금 확인 요청</span>&nbsp;&nbsp;정회원 신청 관리 메뉴에서 확인해주세요 </span>
        	<div style="position: absolute;right : 0px;margin-top:6px;margin-right:25px" class ="member-panel">
        		
        		<span style ="font-weight: bold">${user.username} - 관리자</span>
        		<a href ="#" id = "pass_chn_btn"   style ="margin-left : 30px;font-weight: bold">PASSWORD</a>
        		
					<a href ="javascript:document.all.logOutForm.submit();" style ="margin-left : 10px;font-weight: bold">LOGOUT</a>
				
			</div>
        </div>
        </form>
        <div data-options="region:'south',split:false" style="height:1px;"></div>
        <div data-options="region:'west',split:true" title="&nbsp;ReturnP Control Panel " style="width:300px;padding:5px">
        <ul id="submenu_tree" class="easyui-tree,">
            <li>
                <span><span style = "font-weight : bold">RETURN POINT</span></span>
                <ul>
                	 <li>
                        <span>
                        	<span is = "site_admin" class="sub_menu" menu_deps= '1'  style = "font-weight : bold">운영자 패널</span>
                        </span>
                        <ul>
                            <li id = "dashBoard"  data-options = "iconCls :'icon-info'">
                            	<span  style = "font-weight : bold">대쉬 보드</span>
                            	<ul>
		                            <li id = "dashBoard"  data-options = "iconCls :'icon-info'">
		                            	<a class= "sub_menu"  view_req_name = "dashBoard"  menu_deps= '2' >
		                            		<span style = "font-weight : bold">Dash Board </span>
		                            	</a> 
		                            </li>
		                             <li data-options = "iconCls :'icon-info'"><a class= "sub_menu"  view_req_name = "managePolicy"  menu_deps= '2' >
		                            	<span style = "font-weight : bold">Return Point 정책</span></a> 
		                            </li>
		                        </ul>
                            </li>
                            
                             <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">조직 관리</span>
		                         <ul>
			                         <li id = "manageSoleDist"  data-options = "iconCls :'icon-info'"><a class= "sub_menu"  view_req_name = "manageSoleDist"  node = "7"  menu_deps= '2' >
	                        		<span style = "font-weight : bold">총판(7)</span></a> 
		                        	</li>
		                        	
		                        	<li id= "manageBranch"  data-options = "iconCls :'icon-info'"><a class= "sub_menu"  view_req_name = "manageBranch"  node = "3"  menu_deps= '2' >
		                        		<span style = "font-weight : bold">지사(3)</span></a> 
		                        	</li>
		                        	
		                        	<li id = "manageAgency"  data-options = "iconCls :'icon-info'"><a class= "sub_menu"  view_req_name = "manageAgency"  node = "4"  menu_deps= '2' >
		                        		<span style = "font-weight : bold">대리점(4)</span></a> 
		                        	</li>
		
		                        	<li id = "manageAffiliate"  data-options = "iconCls :'icon-info'"><a class= "sub_menu"  view_req_name = "manageAffiliate"  node = "5"  menu_deps= '2' >
		                        		<span style = "font-weight : bold">협력업체(5)</span></a> 
		                        	</li>
		
		
		                        	<li id = "manageMember" data-options = "iconCls :'icon-info'"><a class= "sub_menu"  view_req_name = "manageMember"  node = "1"  menu_deps= '2' >
		                        		<span style = "font-weight : bold">일반회원(1)</span></a> 
		                        	</li>

		                        	<li id = "manageRecommender"  data-options = "iconCls :'icon-info'"><a class= "sub_menu"  view_req_name = "manageRecommender"  node = "2"  menu_deps= '2' >
		                        		<span style = "font-weight : bold;">정회원(2) </span></a> 
		                        	</li>
		                        	<li id = "manageSaleManager"  data-options = "iconCls :'icon-info'"><a class= "sub_menu"  view_req_name = "manageSaleManager"  node = "6"  menu_deps= '2' >
		                        		<span style = "font-weight : bold;"> 영업 관리자(6)</span></a> 
		                        	</li>
		                        </ul>
                            </li>
                            
                                           <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">포인트 관리</span>
		                         <ul>
		                         	<li id = "manageGreenPoint" data-options = "iconCls :'icon-info'">
					                   	<a class= "sub_menu"  view_req_name = "manageTotalGreenPoint"  menu_deps= '2' >
					                   		<span style = "font-weight : bold">통합 포인트 조회</span>
					                   	</a>
					                    </li>
					                    
			                         <li id = "manageGreenPoint" data-options = "iconCls :'icon-info'">
					                   	<a class= "sub_menu"  view_req_name = "manageGreenPoint"  menu_deps= '2' >
					                   		<span style = "font-weight : bold">G.POINT 관리</span>
					                   	</a>
					                    </li>
					                   <li id = "manageRedPoint"  data-options = "iconCls :'icon-info'">
					                   	<a class= "sub_menu"  view_req_name = "manageRedPoint"  menu_deps= '2' >
					                   		<span style = "font-weight : bold">R.POINT 관리</span>
					                   	</a> 
					                   </li> 
		                        </ul>
                            </li> 
                            
                               <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">카테고리 관리</span>
		                         <ul>
   									<li id = "managePosPayment" data-options = "iconCls :'icon-info'">
   										<a class= "sub_menu"  view_req_name = "manageCategory" menu_deps= '2' >
   											<span style = "font-weight : bold">가맹점 카테고리 관리</span>
   										</a> 
   									</li>
		                        </ul>
                            </li>     
                            
                              <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">결제 및 매출 관리</span>
		                         <ul>
		                           <!--  <li id = "managePosPayment" data-options = "iconCls :'icon-info'">
		                            	<a class= "sub_menu"  view_req_name = "managePaymentTransaction" menu_deps= '2' >
		                            		<span style = "font-weight : bold">원본 결제 내역 관리</span>
		                            	</a> 
		                            </li> -->
		                             <li id = "managePosPayment" data-options = "iconCls :'icon-info'">
		                            	<a class= "sub_menu"  view_req_name = "managePaymentTransaction" menu_deps= '2' >
		                            		<span style = "font-weight : bold; ">결제 및 매출 관리</span>
		                            	</a> 
		                            </li>

		                            <li id = "managePosPayment" data-options = "iconCls :'icon-info'">
		                            	<a class= "sub_menu"  view_req_name = "manageOverlapPaymentTransaction" menu_deps= '2' >
		                            		<span style = "font-weight : bold; ">중복 결제</span>
		                            	</a> 
		                            </li>
		                            
		                             
		                          	 <li data-options = "iconCls :'icon-manage'">
		                          		<a class= ""  view_req_name = "manageFileUpload" menu_deps= '2' >
		                          			<span style = "font-weight : bold">세부 매출 조회</span>
		                          		</a>
		                          	</li>
		                          	
		                         <!--     <li id = "managePosPayment" data-options = "iconCls :'icon-info'">
		                            	<a class= "sub_menu"  view_req_name = "managePaymentTransaction" menu_deps= '2' >
		                            		<span style = "font-weight : bold">관리자 수동 생성 결제 관리</span>
		                            	</a> 
		                            </li> -->
		                     <!--        <li id = "managePosPayment" data-options = "iconCls :'icon-info'"><a class= "sub_menu"  view_req_name = "managePaymentTransaction" menu_deps= '2' ><span style = "color : blue; font-weight : bold">결제별 Green Point 적립 관리</span></a> </li> -->
		                            <li id = "managePosPayment" data-options = "iconCls :'icon-info'">
		                            	<a class= "sub_menu"  view_req_name = "manageAffiliateSale" menu_deps= '2' >
		                            		<span style = "font-weight : bold">협력업체별 매출 조회</span>
		                            	</a> 
		                            </li>
		                            
		                            <li id = "managePosPayment" data-options = "iconCls :'icon-info'">
		                            	<a class= "sub_menu"  view_req_name = "managePaymentPointbackRecord" menu_deps= '2' >
		                            		<span style = "font-weight : bold">결제별 R.POINT 세부 적립내역</span>
		                            	</a> 
		                            </li>
		                          	<li data-options = "iconCls :'icon-manage'">
		                          		<a class= "sub_menu"  view_req_name = "manualPosPaymentRegister" menu_deps= '2' >
		                          			<span style = "font-weight : bold">매출 수동 등록</span>
		                          		</a>
		                          	</li>
		                          	 
		                          	 <li data-options = "iconCls :'icon-manage'">
		                          		<a class= "sub_menu"  view_req_name = "manageFileUpload" menu_deps= '2' >
		                          			<span style = "font-weight : bold">매출 파일 등록 </span>
		                          		</a>
		                          	</li>
				                </ul>
                            </li> 
                            
                             <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">상품권 관리</span>
		                         <ul>
		                            	<!-- <li id = "" data-options = "iconCls :'icon-info'">
                            				<a class= "sub_menu"  view_req_name = ""  menu_deps= '2' >
                            					<span style = "font-weight : bold">상품권 판매 조직 관리</span>
                            				</a> 
                            			</li> -->
                            			<li id = "manageGiftCardProduct" data-options = "iconCls :'icon-info'">
                            				<a class= "sub_menu"  view_req_name = "manageProduct"  menu_deps= '2' >
                            					<span style = "font-weight : bold">상품권 관리</span>
                            				</a> 
                            			</li>
                            			
                            			<li id = "" data-options = "iconCls :'icon-info'">
                            				<a class= "sub_menu"  view_req_name = "manageGiftProductOrder"  menu_deps= '2' >
                            					<span style = "font-weight : bold">상품권 발행 관리</span>
                            				</a> 
                            			</li>
                            			
                            			<li id = "" data-options = "iconCls :'icon-info'">
                            				<a class= "sub_menu"  view_req_name = "manageGiftPayment"  menu_deps= '2' >
                            					<span style = "font-weight : bold">상품권 결제 관리</span>
                            				</a> 
                            			</li>
		                        </ul>
                            </li> 
                            
                            <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">외부 연동 관리</span>
		                         <ul>
		                         	 <li id="manageApiService" data-options = "iconCls :'icon-info'">
                        			 	<a class= "sub_menu"  view_req_name = "manageApiService"  menu_deps= '2' >
                        			 		<span style = "font-weight : bold">연동 API 관리</span>
                        			 	</a> 
                        			 </li>
		                        </ul>
                            </li>
                            <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">특수 조직 관리</span>
		                         <ul>
		                         	<li id = "manageMembershipRequest"  data-options = "iconCls :'icon-info'">
		                         		<a class= "sub_menu"  view_req_name = "manageMarketer"  menu_deps= '2' >
		                         			<span style = "font-weight : bold">마케팅 코드, ID 관리</span>
		                         		</a> 
		                         	</li>
		                        </ul>
                            </li>
                            
                             <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">정회원 신청 관리</span>
		                         <ul>
		                         	<li id = "manageMembershipRequest"  data-options = "iconCls :'icon-info'">
		                         		<a class= "sub_menu"  view_req_name = "manageMembershipRequest"  menu_deps= '2' >
		                         			<span style = "font-weight : bold">정회원 신청 관리</span>
		                         		</a> 
		                         	</li>
		                        </ul>
                            </li>
                            
                             <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">G,R 포인트 적립 현황</span>
		                         <ul>
		                         	<li id = "manageConvertPointRequest" data-options = "iconCls :'icon-info'">
		                         		<a class= "sub_menu"  view_req_name = "manageConvertPointRequest"  menu_deps= '2' >
		                         			<span style = "font-weight : bold">R.POINT, G.POINT 적립 요청</span>
		                         		</a> 
		                         	</li>
                            		<li id = "managePointConversionTransaction" data-options = "iconCls :'icon-info'">
                            			<a class= "sub_menu"  view_req_name = "managePointConversionTransaction"  menu_deps= '2' >
                            				<span style = "font-weight : bold">R.POINT 자동적립 현황</span>
                            			</a> 
                            		</li>
		                        </ul>
                            </li>
                                 <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">포인트 출금 관리</span>
		                         <ul>
			                          <li id = "manageGreenPoint" data-options = "iconCls :'icon-info'">
				                          	<a class= "sub_menu"  view_req_name = "manageMemberBanckAccount"  menu_deps= '2' >
				                          		<span style = "font-weight : bold">은행 계좌 관리</span>
				                          	</a> 
			                          	</li>
                            			<li id = "manageGreenPoint" data-options = "iconCls :'icon-info'">
                            				<a class= "sub_menu"  view_req_name = "managePointWithdrawal"  menu_deps= '2' >
                            					<span style = "font-weight : bold">R.POINT  출금 관리 </span>
                            				</a> 
                            			</li>
		                        </ul>
                            </li>  
                            
                             <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">포인트 사용 관리</span>
		                         <ul>
			                          <li id = "manageGreenPoint" data-options = "iconCls :'icon-info'">
				                          	<a class= "sub_menu"  view_req_name = "manageRedPointUse"  menu_deps= '2' >
				                          		<span style = "font-weight : bold">R.POINT 사용 관리</span>
				                          	</a> 
			                          	</li>
                            			<li id = "manageGreenPoint" data-options = "iconCls :'icon-info'">
                            				<a class= "sub_menu"  view_req_name = "managePointTransfer"  menu_deps= '2' >
                            					<span style = "font-weight : bold">포인트 이체(선물) 관리</span>
                            				</a> 
                            			</li>
		                        </ul>
                            </li>   
                            
                             <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">자료 파일 관리</span>
		                         <ul>
                          			  <li id = "manageNodeFileUpload" data-options = "iconCls :'icon-info'">
                          			  	<a class= "sub_menu"  view_req_name = "manageUploadFile" menu_deps= '2' >
                          			  	<span style = "font-weight : bold">노드 파일(코드, 정보) 업로드/관리</span>
                          			  	</a> 
                          			  </li>
                            		  <li id = "managePosPayment" data-options = "iconCls :'icon-info'">
                            		  	<a class= "sub_menu"  view_req_name = "managePosPayment" menu_deps= '2' >
                            		  		<span style = "font-weight : bold">VAN 결제 내역 파일 </span>
                            		  	</a> 
                            		  </li>
		                        </ul>
                            </li>   
                            
                             <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">사이트 운영 관리</span>
		                         <ul>
                        			<li data-options = "iconCls :'icon-info'"><a class= "sub_menu"  view_req_name = "manageBoard" menu_deps= '2' >
                        				<span style = "font-weight : bold">FAQ, 상담, 공지 게시판 관리</span></a> 
                        			</li>
                        			
                        				<li data-options = "iconCls :'icon-info'">
                            			<a class= "sub_menu"  view_req_name = "manageBanner" menu_deps= '2' ><span style = "font-weight : bold">광고 배너 관리</span></a> 
                            		</li>
                            		
                        			<li data-options = "iconCls :'icon-info'">
                            			<a class= "sub_menu"  view_req_name = "manageEvent" menu_deps= '2' ><span style = "font-weight : bold">이벤트 관리 </span></a> 
                            		</li>
                            		
                            		<li data-options = "iconCls :'icon-info'">
                            			<a class= "sub_menu"  view_req_name = "managePosPayment" menu_deps= '2' ><span style = "font-weight : bold">메뉴 관리 </span></a> 
                            		</li>
                            		<li data-options = "iconCls :'icon-info'">
                            			<a class= "sub_menu"  view_req_name = "managePosPayment" menu_deps= '2' ><span style = "font-weight : bold">컨텐츠 관리 </span></a> 
                            		</li>
                            		<li data-options = "iconCls :'icon-info'">
                            			<a class= "sub_menu"  view_req_name = "managePosPayment" menu_deps= '2' ><span style = "font-weight : bold">고객 문의 관리 </span></a>
                            		 </li>
		                        </ul>
                            </li> 
                            
                             <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">통계 관리</span>
		                         <ul>
                          			  <li data-options = "iconCls :'icon-info'">
	                          			  <a class= "sub_menu"  view_req_name = "managePosPayment" menu_deps= '2' >
	                          			  	<span style = "font-weight : bold">리포트 생성 </span>
	                          			  </a> 
                          			  </li>
		                        </ul>
                            </li>                               
                                                 
                        </ul>
                    </li>
                    
                    <li>
                        <span>
                        	<span class="sub_menu" menu_deps= '1'  style = "font-weight : bold">시스템 관리자 패널</span>
                        </span>
                        <ul>
     						 <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">서버 관리</span>
		                         <ul>
                        	   		<li data-options = "iconCls :'icon-info'">
                        	  			<a class= "sub_menu"  view_req_name = "manageBatch"  menu_deps= '2' >
                        	  				<span style = "font-weight : bold">Batch Server 관리</span>
                        	  			</a> 
                        	  		</li>
                        	   		
                        	   		<li data-options = "iconCls :'icon-info'">
                        	   			<a class= "sub_menu"  view_req_name = "dashBoard"  menu_deps= '2' >
                        	   				<span style = "font-weight : bold">Web Server 관리</span>
                        	   			</a> 
                        	   		</li>
                        	  		<li data-options = "iconCls :'icon-info'">
                        	  			<a class= "sub_menu"  view_req_name = "dashBoard"  menu_deps= '2' >
                        	  				<span style = "font-weight : bold">Admin Server 관리</span>
                        	  			</a> 
                        	  		</li>
                        	  		
                        	  		<li data-options = "iconCls :'icon-info'">
                        	 			<a class= "sub_menu"  view_req_name = "configVan"  menu_deps= '2' >
                        	 				<span style = "font-weight : bold">VAN Transaction Server 서버 관리</span>
                        	 			</a> 
                        	 		</li>
                        	  		
                        	   		<li id="manageApiService" data-options = "iconCls :'icon-info'">
                        	   			<a class= "sub_menu"  view_req_name = "dashBoard"  menu_deps= '2' >
                        	   				<span style = "font-weight : bold">API 서버 관리</span>
                        	   				</a> 
                        	   			</li>
                        	   		<li data-options = "iconCls :'icon-info'">
                        	   			<a class= "sub_menu"  view_req_name = "dashBoard"  menu_deps= '2' >
                        	   				<span style = "font-weight : bold">Database(샤딩) 관리</span>
                        	   			</a> 
                        	   		</li>		                         
		                        </ul>
                            </li>   
     						 <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "">외부 연동 관리</span>
		                         <ul>
                        			 <li id="manageApiService" data-options = "iconCls :'icon-info'">
                        			 	<a class= "sub_menu"  view_req_name = "manageApiService"  menu_deps= '2' >
                        			 		<span style = "font-weight : bold">API , 서비스 관리</span>
                        			 	</a> 
                        			 </li>
                        	 		<li data-options = "iconCls :'icon-info'">
                        	 			<a class= "sub_menu"  view_req_name = "configVan"  menu_deps= '2' >
                        	 				<span style = "font-weight : bold">연동 클라이언트 관리</span>
                        	 			</a> 
                        	 		</li>
                        	 		<li data-options = "iconCls :'icon-info'">
                        	 			<a class= "sub_menu"  view_req_name = "configVan"  menu_deps= '2' >
                        	 				<span style = "font-weight : bold">외부 연동 API 관리</span>
                        	 			</a> 
                        	 		</li>
                        	 		<li data-options = "iconCls :'icon-info'">
                        	 			<a class= "sub_menu"  view_req_name = "configVan"  menu_deps= '2' >
                        	 				<span style = "font-weight : bold">VAN 가맹점 정보 조회 및 연동</span>
                        	 			</a> 
                        	 		</li>                         
		                        </ul>
                            </li>       
     						 <li data-options = "iconCls :'icon-info'">
                            	 <span  style = "font-weight : bold">관리자 생성 및 권한 관리</span>
		                         <ul>
                            		<li data-options = "iconCls :'icon-info'">
                            		<a class= "sub_menu"  view_req_name = "manageManager" menu_deps= '2' >
                            			<span style = "font-weight : bold">관리자 관리</span>
                            		</a> 
                            	</li>		                         
		                        </ul>
                            </li>                                                                                 
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
        </div>
        <div  class="content" id="content_container"  data-options="region:'center'" title="&nbsp;&nbsp;" style="padding:5px">
        </div>
    </div>
	<div id="modal_area">
	<!-- 모달 다이얼로그 DIV  -->
	<div id = "dlgForm" style ="padding:5px" class="modal_dialog"> </div>
	<div id = "dlgForm2" style ="padding:5px" class="modal_dialog"> </div>
	</div>
	 <!-- Progress loading bar DIV  -->
    <div id = "progress_loading">
		<img src="resources/images/progress_loading.gif"/>
	</div>
	<script>
		$.fn.serializeObject = function () {
		    "use strict";
		    var result = {};
		    var extend = function (i, element) {
		        var node = result[element.name];
		        if ('undefined' !== typeof node && node !== null) {
		           if ($.isArray(node)) {
		               node.push(element.value);
		           } else {
		               result[element.name] = [node, element.value];
		           }
		        } else {
		            result[element.name] = element.value;
		        }
		    };
		 
		    $.each(this.serializeArray(), extend);
		    return result;
		};
	</script>
    <script>
   		var isAjax = false;
    	Array.prototype.hasValue = function(value) {
    		var i;
    		for (i=0; i<this.length; i++) { if (this[i] === value) return true; }
    		return false;
     	}
    
    	var selectedMenu = {};
	    var appNavigation = {deps1 : "",deps2 : "",deps3 :""};
    	
    	function loadView(menuText, viewReqName, nodeNo){
    		$.ajaxSetup({ async : true });
    		var param = {viewReqName : viewReqName};
    		var viewArr = [
    			"manageNodeFileUpload",
				"dashBoard",
				"manageApiService",
    			"managePointConversionTransaction",
    			"managePolicy",
    			"manageMembershipRequest",
    			"manageConvertPointRequest", 
    			"manageGreenPoint", 
    			"manageRedPoint", 
    			"manageSoleDist", 
    			"manageBranch",
    			"manageAgency",
    			"manageAffiliate",
    			"manageBoard",
    			"manageRecommender",
    			"manageSaleManager",
    			"manageMember"];
    		
    		if (viewArr.hasValue(viewReqName)){
    			param.pageSize = 10;
    			param.page = 0;
    			
    			param.searchDateStart = '';
    			param.searchDateEnd = '';
    			param.searchNodeType = nodeNo;
    			param.searchNodeStaus = "0";
    			param.searchKeywordType = "0";
    			param.searchKeyword = "";
    		}
    		//console.log("loadView");
    		//console.log(param);
    		
    		param = $.param(param);
    		$('#content_container').load("/springSecurity/handleContent?" + param,
   				function(response, status, xhr) {
    			}
    		);
    	}
    	
    	function updateNavigator(){
    		var contentPanel = $('.container').layout('panel','center');
    		var naviArr = [];
    		for (var property in appNavigation){
    			if (!appNavigation[property] ) continue;
    			naviArr.push(appNavigation[property]);
    		}
    		var naviStr = naviArr.join(' > ');
    		contentPanel.panel({title :  naviStr});
    	}
    	
    	$(document).ready(function(){
    		$('#progress_loading').hide();
    	})
        .ajaxStop(function(){
        	isAjax = false;
    		$('#progress_loading').hide(); 
    	});
    	
    	$(document).ready(function(){
    		$('#progress_loading').hide()
    		$('#submenu_tree').tree({
    			onLoadSuccess : function(){
    				var initNode = $('#submenu_tree').tree('find','dashBoard');;
    				var page = sessionStorage.getItem("page");

    				if (page) {
    					if ($('#submenu_tree').tree('find',page)) {
	    					initNode = $('#submenu_tree').tree('find',page);
    					}
    				}
    				$('#submenu_tree').tree('select', initNode.target);
    			},
    			animate : false,
    			lines : true,
    			onSelect : function(node){
    				if (isAjax) return;
    				var deps = $(node.target).find('.sub_menu').attr('menu_deps');
    				var menuText = $(node.text).text();
    				var menuCode = $(node.target).find(".sub_menu").attr("view_req_name");
    				var nodeNo  = $(node.target).find(".sub_menu").attr("node");
    				sessionStorage.setItem("page", menuCode );
    				switch (deps) {
    				case "1": 
    					appNavigation = {deps1 : menuText, deps2 : '', deps3:''};
    					updateNavigator();
    					break;
    				case "2": 
    					var parentNode = $('#submenu_tree').tree('getParent', node.target);
    					appNavigation['deps1'] = $(parentNode.text).text();
    					appNavigation['deps2'] = menuText;
    					updateNavigator();
    					loadView(menuText,menuCode, nodeNo); 
    					break;
    				case "3":
    					break;
    				}
    			}
    		});
    		
    		//$('#submenu_tree').tree("collapseAll");
    		
    		//$('#submenu_tree').tree("collapseAll");
    		//$('#submenu_tree').tree("toggle", $('#submenu_tree').tree('getRoot').target);
    	//	$('#submenu_tree').tree("toggle,  $('#submenu_tree').tree('getRoot').target);
    		//setIntervalMembershipRequestCheck(5000);
    	});
    	
    	function setIntervalMembershipRequestCheck(intervalTime){
    		setInterval(function(){
    			returnp.api.call("getMembershipRequests", {searchPaymentStatus : "6"}, function(res){
    				//console.log(res);
    				if (res.resultCode  == "100") {
    					if (res.rows.length > 0) {
    						$("#returnp_noti_membership").show();
    					}else {
    						$("#returnp_noti_membership").hide();
    					}
    				}else {
    					$.messager.alert('오류 발생', res.message);
    				}
    			}, true);
    		}, intervalTime);
    	}
    </script>
</body>
</html>
