<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style = "height:100%" >
	<div id ="policy_container" style="width:100%;height:100%">
        <div data-options="region:'west',split:true" style="width:25%">
        	<div id = "node_refund_panel" style = "padding: 10px 20px">
			   <div style = "width: 100%;margin-bottom:1px" >
					 <p style="font-size:14px;font-weight: bold"></p>
		            
				</div>
			   <form id = "policyForm" >
			   
			   <div style = "width: 100%;">
					<input class="value easyui-textbox" id ="soleDistComm"  name="soleDistComm"  data-options="label:'*  총판 수수료 (%)',labelPosition : 'top'" style="width:100%;"> 
				</div>
				
			   <div style = "width: 100%;margin-top : 20px">
					<input class="value easyui-textbox" id ="branchComm"  name="branchComm"  data-options="label:'* 지사 수수료 (%)',labelPosition : 'top'" style="width:100%;"> 
				</div>
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox" id ="agancyComm"  name="agancyComm"  data-options="label:'* 대리점 수수료(%)',labelPosition : 'top'" style="width:100%;"> 
				</div>	
				
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox" id ="affiliateComm"  name="affiliateComm"  data-options="label:'* 협력 업체 수수료(%)',labelPosition : 'top'" style="width:100%;"> 
				</div>	
				
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox" id ="branchRecComm"  name="branchRecComm"  data-options="label:'* 지사 추천인 수수료',labelPosition : 'top'" style="width:100%;"> 
				</div>	
				
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox" id ="agancyRecComm"  name="agancyRecComm"  data-options="label:'* 대리점 추천인 수수료',labelPosition : 'top'" style="width:100%;"> 
				</div>	
				
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox"  id ="affiliateRecComm"  name="affiliateRecComm"  data-options="label:'* 협력 업체 추천인 수수료',labelPosition : 'top'" style="width:100%;"> 
				</div>	
				
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox" id ="customerRecCom"  name="customerRecCom"  data-options="label:'* 고객 추천인 수수료',labelPosition : 'top'" style="width:100%;"> 
				</div>	
				
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox" id ="customerComm"  name="customerComm"  data-options="label:'* 고객 수수료',labelPosition : 'top'" style="width:100%;"> 
				</div>
				
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox"  id ="customerRecManagerComm"  name="customerRecManagerComm"  data-options="label:'* 고객_추천인_영업관리자 수수료',labelPosition : 'top'" style="width:100%;"> 
				</div>	
				
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox"  id ="affiliateRecManagerComm"  name="affiliateRecManagerComm"  data-options="label:'* 협력업체_추천인_영업관리자 수수료',labelPosition : 'top'" style="width:100%;"> 
				</div>	
				
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox"  id ="agancyRecManagerComm"  name="agancyRecManagerComm"  data-options="label:'* 대리점_추천인_영업관리자 수수료',labelPosition : 'top'" style="width:100%;"> 
				</div>	
				
				<div style = "width: 100%;margin-top : 20px">
					<input class="easyui-textbox"  id ="branchRecManagerComm"  name="branchRecManagerComm"  data-options="label:'* 지사_추천인_영업관리자 수수료',labelPosition : 'top'" style="width:100%;"> 
				</div>	
				</form>
				
		    	<div id="node_refund_tool">
					<a href="javascript:void(0)" class="icon-save" style = "margin-right : 5px" onclick="javascript:createPolicy()"></a>
			        <!-- <a href="javascript:void(0)" class="icon-help " style = "margin-right : 5px" onclick="javascript:alert('help')"></a> -->
			    </div>
        	</div>
        </div>
        <div data-options="region:'center',border : false, iconCls:'icon-ok'" style = "width : 100%">
        	<div style = "width : 100%;height : 50%;" style = "height: 100%">
        		<div style="width:33%; display: inline-block;height: 100%">
        			<div class="easyui-panel" id="blue_point_config"  title = "R POINT"  style = "height: 100%; padding: 5px 20px;">
	        			 <div style = "width: 100%;margin-bottom:20px"></div>
	        			 <form id = "policyForm2">
	        			<div style = "width: 100%;">
							<input id ="rate"  class="easyui-textbox" name="rate" data-options="label:'* 포인트 (변경 불가) ',cls :'c3', labelPosition : 'top'"  style="width:100%;border : 1px solid #ff0000"> 
						</div>
						
						<div style = "width: 100%;margin-top : 20px">
							<input id ="real"  class="easyui-textbox" name="real"  data-options="label:'* 실제 포인트',labelPosition : 'top'" style="width:100%;"> 
						</div>	
						 
						 <!-- 실제 서버로 전송되는 전환율 -->
						<input type = "hidden" id =pointTransRate  name="pointTransRate""> 
						
						<div style = "width: 100%;margin-top : 20px">
							<input id ="pointTransLimit"  class="easyui-textbox" name="pointTransLimit"  data-options="label:'* R PAY 전환 비율 ',labelPosition : 'top'" style="width:100%;"> 
						</div>	
						
						<div style = "width: 100%;margin-top : 20px">
							<input id ="gPointMovingMinLimit"  class="easyui-textbox" name="gPointMovingMinLimit"  data-options="label:'* R POINT 이체 최저 포인트 ',labelPosition : 'top'" style="width:100%;"> 
						</div>	
						
						<div style = "width: 100%;margin-top : 20px">
							<input id ="gPointMovingMaxLimit"  class="easyui-textbox" name="gPointMovingMaxLimit"  data-options="label:'* R POINT 이체 최대 포인트 ',labelPosition : 'top'" style="width:100%;"> 
						</div>	
						
						</form>
						
	        			<div id="blue_point_tool">
							<a href="javascript:void(0)" class="icon-save" style = "margin-right : 5px" onclick="javascript:createPolicy()"></a>
					        <!-- <a href="javascript:void(0)" class="icon-help " style = "margin-right : 5px" onclick="javascript:alert('help')"></a> -->
				    	</div>
        			</div> 
        		</div>
        		<div style="width:33%; display: inline-block;height: 100%">
        			<div class="easyui-panel" id="red_point_config" title = "R PAY"  style = "height: 100%;padding: 5px 20px">
        				<div style = "width: 100%;margin-bottom:20px"></div>
        				<form id = "policyForm3">
        				<div style = "width: 100%;">
							<input class="easyui-textbox" id ="redPointAccRate"  name="redPointAccRate"  data-options="label:'* R PAY 자동 적립율',labelPosition : 'top'" style="width:100%;"> 
						</div>
						
						<div style = "width: 100%;margin-top : 20px">
							<input id ="rPointMovingMinLimit"  class="easyui-textbox" name="rPointMovingMinLimit"  data-options="label:'* R PAY 이체 최저 포인트 ',labelPosition : 'top'" style="width:100%;"> 
						</div>	
						
						<div style = "width: 100%;margin-top : 20px">
							<input id ="rPointMovingMaxLimit"  class="easyui-textbox" name="rPointMovingMaxLimit"  data-options="label:'* R PAY 이체 최대 포인트 ',labelPosition : 'top'" style="width:100%;"> 
						</div>	
						
        				</form>
        				<div id="green_point_tool">
							<a href="javascript:void(0)" class="icon-save" style = "margin-right : 5px" onclick="javascript:createPolicy()"></a>
					      <!--   <a href="javascript:void(0)" class="icon-help " style = "margin-right : 5px" onclick="javascript:alert('help')"></a> -->
				    	</div>	
        			</div> 
        		</div>
        		<div style="width:33%; display: inline-block;height: 100%">
        			<div class="easyui-panel" id="membership_config" title = "멤버쉽"  style = "height: 100%;padding: 5px 20px" >
        				<div style = "width: 100%;margin-bottom:20px"></div>
        				<form id = "policyForm4">
        				<div style = "width: 100%;">
							<input class="easyui-textbox" id ="membershipTransLimit"  name="membershipTransLimit"  data-options="label:'* 멤버쉽 전환 비용',labelPosition : 'top'" style="width:100%;"> 
						</div>
        				</form>
        				<div id="membership_tool">
							<a href="javascript:void(0)" class="icon-save" style = "margin-right : 5px" onclick="javascript:createPolicy()"></a>
					       <!--  <a href="javascript:void(0)" class="icon-help " style = "margin-right : 5px" onclick="javascript:alert('help')"></a> -->
				    	</div>
        			</div> 
        		</div>
        	</div>
        	
        	<div style = "width : 100%;height : 50%;margin-top : 2px" >
        		<div style="width:33%; display: inline-block;height: 100%">
        			<div class="easyui-panel" id="rpay_withdraw_config"  title = "R PAY 출금"  style = "height: 100%;padding: 5px 20px">
        				<div style = "width: 100%;margin-bottom:20px"></div>
        				<form id = "policyForm5">
	        				<div style = "width: 100%;">
								<input class="easyui-textbox" id ="rPayWithdrawalMinLimit"  name="rPayWithdrawalMinLimit"  data-options="label:'* R PAY 최저 출금 금액',labelPosition : 'top'" style="width:100%;"> 
							</div>
	        				<div style = "width: 100%;margin-top : 20px">
								<input class="easyui-textbox" id ="rPayWithdrawalMaxLimit"  name="rPayWithdrawalMaxLimit"  data-options="label:'* R PAY 일일 출금 한도',labelPosition : 'top'" style="width:100%;"> 
							</div>
						</form>
        				<div id="rpay_withdraw_panel_tool">
							<a href="javascript:void(0)" class="icon-save" style = "margin-right : 5px" onclick="javascript:createPolicy()"></a>
					       <!--  <a href="javascript:void(0)" class="icon-help " style = "margin-right : 5px" onclick="javascript:alert('help')"></a> -->
				    	</div>
        			</div> 
        		</div>
        		<div style="width:33%; display: inline-block;height: 100%">
        			<div class="easyui-panel" id="etc_config2" title = "기타"  style = "height: 100%">
        				<div id="etc_tool2">
							<a href="javascript:void(0)" class="icon-save" style = "margin-right : 5px" onclick="javascript:alert('save')"></a>
					    <!--     <a href="javascript:void(0)" class="icon-help " style = "margin-right : 5px" onclick="javascript:alert('help')"></a> -->
				    	</div>
        			</div> 
        		</div>
        		<div style="width:33%; display: inline-block;height: 100%">
        			<div class="easyui-panel" id="etc_config3" title = "기타"  style = "height: 100%">
        				<div id="etc_tool3">
							<a href="javascript:void(0)" class="icon-save" style = "margin-right : 5px" onclick="javascript:alert('save')"></a>
					       <!--  <a href="javascript:void(0)" class="icon-help " style = "margin-right : 5px" onclick="javascript:alert('help')"></a> -->
				    	</div>
        			</div> 
        		</div>
        	</div>
        </div>
    </div>
</div>
<script src="resources/js/${viewReqName}.js"></script>
