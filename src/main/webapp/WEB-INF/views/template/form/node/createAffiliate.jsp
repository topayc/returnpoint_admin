<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  style ="margin: 10px">
	<div style = "padding:5px;padding-bottom:5px" >	
		
	   	<form id="createAffiliateForm"  enctype="multipart/form-data" name = "createAffiliateForm" method="post" action = "<c:url value='/createAbmsProject'/>">
			<div style="display:none;">
			<input type="text" id="memberAddressNo"  name="memberAddressNo" />
			<input type="text" id="nodeNo"  name="nodeNo" />
			<!-- <input type="text" id="nodeType"  name="nodeType" /> -->
			<input type="text" id="roadFullAddr"  name="roadFullAddr" />
			<input type="text" id="roadAddrPart1"  name="roadAddrPart1" />
			<input type="text" id="addrDetail"  name="addrDetail" />
			<input type="text" id="roadAddrPart2"  name="roadAddrPart2" />
			<input type="text" id="engAddr"  name="engAddr" />
			<input type="text" id="jibunAddr"  name="jibunAddr" />
			<input type="text" id="zipNo"  name="zipNo" />
			<input type="text" id="admCd"  name="admCd" />
			<input type="text" id="rnMgtSn"  name="rnMgtSn" />
			<input type="text" id="bdMgtSn"  name="bdMgtSn" />
			<input type="text" id="lat"  name="lat" />
			<input type="text" id="lng"  name="lng" />
			</div>
			<div style="margin-bottom:8px"><input id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:8px"><input id ="affiliateName"  name="affiliateName" style="width:100%" data-options="label:'협력업체 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:8px"><input id ="affiliateEmail"  name="affiliateEmail" style="width:100%" data-options="label:'협력업체 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			
			<div style="margin-bottom:8px"> 
				<select class = "easyui-combobox"  id ="affiliateType" name="affiliateType"   style="width:100%" data-options="label:'협력업체 타입',labelWidth :140,labelAlign:'left',labelPosition : 'left'"> 
					<c:forEach var="affiliateType"  items="${affiliateTypes}" varStatus="status">
						<option value='${affiliateType.key}' >${affiliateType.value} </option>
					</c:forEach>
				</select> 
			</div>

			<div style="margin-bottom:8px">
				<input id ="affiliateSerial"  name="affiliateSerial" style="width:86%" data-options="label:'협력업체 TID ',labelWidth :140,labelPosition : 'left'"> 
				<a id = "gen_tid" >TID 생성</a>
			</div>
			
			<div style="margin-bottom:8px"><input id ="affiliateComm"  name="affiliateComm" style="width:100%" data-options="label:'환급율) ',labelWidth :140,labelPosition : 'left'" value = "${policy.affiliateComm}"> </div>
			
			<div style="margin-bottom:8px"> 
				<select class = "easyui-combobox"  id ="category1No" name="category1No"   style="width:100%" data-options="label:'카테고리1',labelWidth :140,labelAlign:'left',labelPosition : 'left'"> 
					<option value = "0">선택안함</option>
					<c:forEach var="category"  items="${categories1}" varStatus="status">
						<option value='${category.categoryNo}' >${category.categoryName} </option>
					</c:forEach>
				</select> 
			</div>
			<div style="margin-bottom:8px"> 
				<select class = "easyui-combobox"  id ="category2No" name="category2No"   style="width:100%" data-options="label:'카테고리2',labelWidth :140,labelAlign:'left',labelPosition : 'left'"> 
					<c:forEach var="nodeStatus"  items="${nodeStatuses}" varStatus="status">
						   <option value = "0">선택 안함</option>
					</c:forEach>
				</select> 
			</div>
			<div style="margin-bottom:8px"><input id ="affiliateAddress"  name="affiliateAddress" style="width:100%" data-options="label:'협력업체 주소',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:8px"><input id ="affiliateRoad"  name="affiliateRoad" style="width:100%" data-options="label:'협력업체 도로명',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:8px"><div id="map-canvas" style="height:200px;;border : 1px solid #aaaaaa;background-color : #ffffff"></div></div>
			<div style="margin-bottom:8px"><input id ="affiliateTel"  name="affiliateTel" style="width:100%" data-options="label:'협력업체 전화번호',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:8px"><input id ="affiliatePhone"  name="affiliatePhone" style="width:100%" data-options="label:'협력업체 모바일',labelWidth :140,labelPosition : 'left'"> </div>
			
			<div style="margin-bottom:8px"><input id ="agencyNo"  name="agencyNo" style="width:100%" data-options="label:'대리점 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:8px">
				<input id ="agencyName"  name="agencyName" style="width:100%" data-options="label:'대리점 이름 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:8px"><input id ="recommenderNo"  name="recommenderNo" style="width:100%" data-options="label:'추천인 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:8px">
				<input id ="recommenderName"  name="recommenderName" style="width:100%" data-options="label:'추천인 이름 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			<div style="margin-bottom:8px"> 
				<select class = "easyui-combobox"  id ="affiliateStatus" name="affiliateStatus"   style="width:100%" data-options="label:'협력 업체 상태',labelWidth :140,labelAlign:'left',labelPosition : 'left'"> 
					<c:forEach var="nodeStatus"  items="${nodeStatuses}" varStatus="status">
						   <c:if test = "${ nodeStatus.useInAdmin == 'Y' }">
							  <option value='${nodeStatus.key}' >${nodeStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
	
			<div style="margin-bottom:5px"> 
				<select class = "easyui-combobox"  id ="regType" name="regType"   style="width:100%" data-options="label:'등록 타입',labelWidth :140,labelAlign:'left',abelPosition : 'left'" style="width:100%;height:30px"> 
					<c:forEach var="reigistType"  items="${registTypes}" varStatus="status">
						    <c:if test = "${ reigistType.useInAdmin == 'Y' }">
							  <option value='${reigistType.key}' >${reigistType.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
		</form>
	</div>
</div>

<script>
	function setViewInit(){
		$('#memberNo').textbox({
			label : roundLabel("회원 검색"),
			prompt: '참조할 회원 검색 ',
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');
					loadNodeListView({
	            		targetElem : "#dlgForm2",
	            		title : "회원 검색",
	            		queryOptions : {
	            			memberEmail : v,
	            			viewReqName : "nodeList",
	            			searchNodeType :  "1"
	            			
	            		}
	            	},function callback(selNode){
	            		$(e.data.target).textbox('setValue', selNode.memberNo);
	            		$("#affiliateEmail").textbox('setValue', selNode.memberEmail);
	            		if ($("#affiliateName").textbox('getValue').length ==0 ) {
		            		$("#affiliateName").textbox('setValue', selNode.memberName);
	            		}  
	            	});
				}
			}]
		});
		
		$('#affiliateAddress').textbox({
			label : roundLabel("주소"),
			prompt: '위치정보 조회',
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');				
					window.open("/api/locationView","pop","width=570,height=420, scrollbars=yes, resizable=yes");
				}
			}]
		});
		
/* 		$('#recommenderNo').textbox({
			prompt: '참조할 추천인 검색 ',
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');
					loadNodeListView({
	            		targetElem : "#dlgForm2",
	            		title : "추천인 검색",
	            		queryOptions : {
	            			memberEmail : v,
	            			viewReqName : "nodeList",
	            			searchNodeType :  "2"
	            			
	            		}
	            	},function callback(selNode){
	            		$(e.data.target).textbox('setValue', selNode.recommenderNo);
	            	});
				}
			}]
		}); */
		

		$('#recommenderNo').textbox({
			label : roundLabel("추천인 검색"),
			prompt: '추천인 번호 ',
			editable : false,
			buttonText:'추천인 검색',
			buttonAlign:'right',
			onClickButton : function(data){
				var v = $('#recommenderNo').textbox('getValue');
				loadNodeListView({
            		targetElem : "#dlgForm2",
            		title : "추천인 검색",
            		queryOptions : {
            			memberEmail : v,
            			viewReqName : "nodeList",
            			searchNodeType :  "1"
            		}
            	},function callback(selNode){
            		$('#recommenderNo').textbox('setValue', selNode.memberNo);
            		$('#recommenderName').textbox('setValue', selNode.memberName)
            	});
			}
		});
		
		$('#recommenderName').textbox({
			label : roundLabel("추천인 이름"),
			prompt: '추천인 이름',
			readonly :true
		});

		$('#agencyName').textbox({
			label : roundLabel("대리점 이름"),
			prompt: '대리점 이름',
			readonly :true
		});
		
		$('#agencyNo').textbox({
			prompt: '참조할 대리점 검색 ',
			label : roundLabel("대리점 검색"),
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');
					loadNodeListView({
	            		targetElem : "#dlgForm2",
	            		title : "대리점 검색",
	            		queryOptions : {
	            			memberEmail : v,
	            			viewReqName : "nodeList",
	            			searchNodeType :  "4"
	            		}
	            	},function callback(selNode){
	            		console.log(selNode)
	            		$(e.data.target).textbox('setValue', selNode.agencyNo);
	            		$("#agencyName").textbox('setValue', selNode.agencyName);
	            	});
				}
			}]
		});

		
		$('#affiliateSerial').textbox({
			label : roundLabel("협력업체 TID"),
			prompt: '협력 업체 실제 TID'
		});

		$('#affiliateComm').textbox({
			label : roundLabel("환급율"),
			prompt: '환급 수수료'
		});
		
		/* Tid  생성 버튼*/
		$('#gen_tid').linkbutton({
			onClick : function(){
				var param = {affiliateType : $('#affiliateType').combobox("getValue")};
				returnp.api.call('genTid', param, function(res){
					$("#affiliateSerial").textbox('setValue', res.data);
				});
			},
			iconCls:'icon-add'
		});

		$('#affiliateEmail').textbox({
			label : roundLabel("협력업체 이메일"),
			prompt: '협력 업체 이메일 ', 
			editable : false,
		});
		
		$('#affiliateName').textbox({
			label : roundLabel("협력업체 명"),
			prompt: '협력업체 상호 및 기타 이름', 
		});
		
		
		$('#affiliateAddress').textbox({
			label : roundLabel("협력업체 지번"),
			prompt: '협력업체 지번 주소', 
			editable : false
		});
		
		$('#affiliateRoad').textbox({
			label : roundLabel("협력업체 도로명"),
			prompt: '협력업체 도로명 주소', 
			editable : false
		});
		$('#affiliateTel').textbox({
			label : roundLabel("협력업체 전화번호"),
			prompt: '협력업체 전화 번호', 
		});
		$('#affiliatePhone').textbox({
			label : roundLabel("협력업체 핸드폰"),
			prompt: '협력업체 핸드폰 번호', 
		});
		var init = true
		$('#affiliateType').combobox({
			label : roundLabel("협력업체 타입"),
			multiple:true, 
			panelHeight: 'auto',
			 onSelect : function(record){
				/* 오프라인 사업자인 경우 */
				 if (record.value == "A001"){
					 $('#gen_tid').linkbutton('disable');
				 }else {
					$('#gen_tid').linkbutton('enable');
					/* $('#affiliateSerial').textbox("clear"); */
				 }
			 }
		});
		
		$('#category2No').combobox({
			label : roundLabel("카테고리 2"),
			valueField : "categoryNo",
			textField : "categoryName",
			showItemIcon: true,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});
		
		$('#category1No').combobox({
			showItemIcon: true,
            panelHeight: 'auto',
            label : roundLabel("카테고리 1"),
            multiple:false,
            required:true,
            onSelect : function(record){
            	console.log(record)
            	if (record.value == "0") {
            		$('#category2No').combobox('clear');
            		var data = [];
            		data.push( {categoryNo: "0" , categoryName : "선택 안함"});
            		$('#category2No').combobox('loadData', data);
            		$('#category2No').combobox('select', 0);
            	}else {
            		returnp.api.call("getCategories", {categoryLevel : 2, parentCategoryNo :  record.value}, function(res){
            			if (res.resultCode  == "100") {
            				$('#category2No').combobox('clear');
            				var data = [];
            				data.push( {categoryNo: "0" , categoryName : "선택 안함"});
            				for (var i = 0; i < res.rows.length; i++){
	            				data.push({categoryNo:res.rows[i].categoryNo ,categoryName : res.rows[i].categoryName});
            				}
            				$('#category2No').combobox('loadData', data);
            				$('#category2No').combobox('select', 0);
            			}else {
            				//console.log("[오류]");
            				//console.log(res);
            			}
            		});
            	}
            }
		});
		
		$('#affiliateStatus').combobox({
			showItemIcon: true,
			readonly : true,
            panelHeight: 'auto',
            label : roundLabel("협력업체 상태"),
            multiple:false,
            required:true
		});
		
		$('#regType').combobox({
			showItemIcon: true,
            readonly : true,
            label : roundLabel("등록 타입"),
            panelHeight: 'auto',
            multiple:false,
		});
		$('#regType').combobox('select',"A");
		
	}
	setViewInit();
	

</script>