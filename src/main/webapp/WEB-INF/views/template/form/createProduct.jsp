<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createProductForm"  enctype="multipart/form-data" name = "createProductForm" method="post"  >
			<div style="margin-bottom:30px"><input id ="productName"  name="productName" style="width:100%" data-options="label:'상품 이름 ',labelWidth :100,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="productPrice"  name="productPrice" style="width:100%" data-options="label:'상품 가격 ',labelWidth :100,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="productSalePrice"  name="productSalePrice" style="width:100%" data-options="label:'상품 판매 가격 ',labelWidth :100,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px">
				<select id ="productStatus" name="productStatus"   style="width:100%" data-options="label:'상품 상태',labelWidth :100,labelPosition : 'left'">
					<c:forEach var="productStatus"  items="${productStatusList}" varStatus="status">
						    <c:if test = "${ productStatus.useInAdmin == 'Y' }">
							  <option value='${productStatus.key}' >${productStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			<div style="margin-bottom:30px"><textarea id ="productDes"  name="productDes" style="width:100%" data-options="label:'상품 설명 ',labelWidth :100,labelPosition : 'left'"></textarea> </div>
			<div style="margin-bottom:30px">
				<input id = productImg1  name = "productImg1" type = "text" style="width:100%" accept=".xlsx">
			</div>
			<div style="margin-bottom:30px">
				<input id = productImg2  name = "productImg2" type = "text" style="width:100%" accept=".xlsx">
			</div>
			
		</form>
	</div>
	<div id="viewImage"></div>
</div>
<script>
	function setViewInit(){
		
		$('#productName').textbox({ });

		$('#productPrice').numberbox({
			groupSeparator:',',
			onChange : function(newValue,oldValue){
				$('#productSalePrice').numberbox("setValue", newValue);
			}
		});

		$('#productSalePrice').numberbox({
			groupSeparator:','
		});
		$('#productDes').textbox({
			height: 150,
			multiline:true	
		});
		
		$('#productStatus').combobox({
			showItemIcon: true,
            editable: false,
            multiple:false,
            required:true,
            panelHeight: 'auto',
		});
		
		$('#productImg1').filebox({
			label:'상품 이미지 1',
			labelWidth : 100,
			buttonText: '&nbsp;&nbsp;<strong>파일 선택</strong>&nbsp;&nbsp;',
		    buttonAlign: 'right',
		    height: '25px'
		});
		
		$('#productImg2').filebox({
			label:'상품 이미지 2',
			labelWidth : 100,
			buttonText: '&nbsp;&nbsp;<strong>파일 선택</strong>&nbsp;&nbsp;',
		    buttonAlign: 'right',
		    height: '25px'
		});
	}
	setViewInit();
</script>