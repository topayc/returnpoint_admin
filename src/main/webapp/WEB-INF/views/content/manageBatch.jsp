<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	
<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;width:100%}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
.tg .tg-w1ol{font-size:12px;border-color:#9b9b9b;text-align:left;vertical-align:top}
.tg .tg-qj91{font-size:12px;background-color:#efefef;color:#333333;border-color:#9b9b9b;text-align:left;vertical-align:top}
</style>
<div class = "easyui-panel" style = "padding: 20px;height:100%" >
<form id="manageBatchForm"   name = "manageBatchForm"  method = "get">
<table class="tg">
  <tr>
    <td width = "100px" class="tg-qj91">배치 서버 선택</td>
    <td class="tg-w1ol">	
    	 <div style="width:100%;">
			<select id = "batchServerAddr" class="easyui-combobox" name="batchServerAddr"  style="width:100%;" >
				<option value='100.100.100.101' >직접 입력</option>
				<option value='100.100.100.102' >100.100.100.101</option>
				<option value='100.100.100.103' >100.100.100.102</option>
				<option value='100.100.100.104'>100.100.100.103</option>
				<option value='100.100.100.105' >100.100.100.104</option>
	       </select>
		</div>
	</td>
    <td width = "100px" class="tg-qj91">배치 서버 포트</td>
    <td width = "20%" class="tg-w1ol">
	    <div style="width:100%;">
			<input id ="batchServerAddrEx"  name="batchServerAddrEx"  class="easyui-textbox"   style="width:100%;">
	   </div>
    </td>
    
    <td width = "100px"  class="tg-qj91">배치 횟수</td>
    <td width = "20%" class="tg-w1ol">
	    <div style="width:100%;">
			<input id ="batchServerAddrEx"  name="batchServerAddrEx"  class="easyui-textbox"   style="width:100%;" value  = "1">
	   </div>
    </td>
  </tr>
  <tr>
    <td class="tg-qj91">배치 제어 명령</td>
    <td class="tg-w1ol">
    	<div style="width:100%;" >
		<select id = "batchCmd" class="easyui-combobox" name="batchCmd"  style="width:100%;" >
			<option value='INIT_BATCH' >배치 초기화</option>
			<option value='START_BATCH_ALL' >배치 전체 즉시 시작</option>
			<option value='UPDATE_BATCH' >배치 업데이트</option>
			<option value='START_BATCH_MEMBER' >특정 회원 배치</option>
			<option value='STOP_BATCH' >배치 중지</option>
			<option value='TEST_BATCH' >배치 테스트</option>
	       </select>
	</div>
    </td>
    <td class="tg-qj91">배치 비밀 번호</td>
    <td class="tg-w1ol">
    	<div style="width:100%;">
		<input id ="batchPass"  name="batchPass" class="easyui-textbox"  style="width:100%" > 
	</div>
    </td>
      <td class="tg-qj91"></td>
    <td class="tg-w1ol">
	    <div style="width:100%;">
	   </div>
    </td>
  </tr>
  <tr>
    <td class="tg-qj91">배치 전문 데이타 </td>
    <td class="tg-w1ol" colspan="5">
    	<div style="width:100%;">
		<input id ="batchData"  name="batchData"  class="easyui-textbox" style="width:100%;height:200px"  multiline="true" >
	 </div>
    </td>
  </tr>
</table>
<div style="display:inline-block;margin-top: 10px">
	<a href="javascript:void(0)"  class = "easyui-linkbutton" style="width:80px;margin-right : 2px;">배치 선택</a>
	<a href="javascript:void(0)"  class = "easyui-linkbutton" style="width:80px;margin-right : 2px;margin-left:5px">배치 로그</a>
	<a href="javascript:void(0)"  class = "easyui-linkbutton" style="width:80px;margin-right : 2px;margin-left:5px">확인</a>
	<a href="javascript:void(0)" class = "easyui-linkbutton"  style="width:80px">리셋</a>
</div>
</form>	
</div>
<script src="resources/js/${viewReqName}.js"></script>
