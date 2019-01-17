<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>ReturnP | Login</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <script type="text/javascript" src="resources/js/lib/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="resources/css/default.css">
    <link rel="stylesheet" type="text/css" href="resources/js/lib/easyui/themes/gray/easyui.css">
  <link rel="stylesheet" type="text/css" href="resources/js/lib/easyui/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="resources/js/lib/easyui/themes/color.css">
  <style type="text/css">
  	.blue_message {font-weight:bold;font-family:Gulim, Dotum;font-size:12px;color:#0092DC}
  </style>
</head>

<body class = "section-photo-intro">
	<div class="login-box">
	  <div class="login-logo">
	    <!--  
	    <img src ="resources/images/ci_login.jpg" >
	  	-->
	  </div>
	  <div class="login-box-body">
	    <form id = "loginForm" method="post">
	      <div style ="height:14px"class="form-group">
	        <span class = "blue_message">${message}</span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="text" class="form-control border_gray opa" placeholder="EMAIL"  id = "adminEmail" name = "adminEmail" value ="${userSession.userId}">
	        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="password" class="form-control border_gray opa" placeholder="PASSWORD"  id ="adminPassword" name = "adminPassword" value ="${userSession.userPassword}">
	        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
	      </div>
	      <div class="row">
	        <div class="col-xs-12">
	          <button type="submit" class="btn btn-primary btn-block">로그인</button>
	        </div>
	      </div>
	    </form>
	  </div>
	</div>
	<a href="mms://218.38.127.11/str_lec/2014_gisa_pil/info-pilgi-db-04.wmv">받기</a>
	<!-- <object width="720" height="473" id="gisa79_wm" classid="CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701" type="application/x-oleobject" standby="Microsoft Windows Media Player 구성 요소를 읽어들이는 중..." style="left: 0px; top: 0px;">
			<param name="Filename" value="mms://218.38.127.11/str_lec/2014_gisa_pil/info-pilgi-se-02.wmv">
			<param name="AutoSize" value="1">
			<param name="AutoStart" value="1">
			<param name="AnimationAtStart" value="0">
			<param name="AllowScan" value="-1">
			<param name="AllowChangeDisplaySize" value="1">
			<param name="AutoRewind" value="0">
			<param name="Balance" value="30">
			<param name="BaseURL" value="">
			<param name="BufferingTime" value="10">
			<param name="CaptioningID" value="">
			<param name="ClickToPlay" value="1">
			<param name="CursorType" value="0">
			<param name="CurrentPosition" value="-1">
			<param name="CurrentMarker" value="0">
			<param name="DefaultFrame" value="">
			<param name="DisplayBackColor" value="0">
			<param name="DisplayForeColor" value="16777215">
			<param name="DisplayMode" value="1">
			<param name="DisplaySize" value="0">
			<param name="Enabled" value="-1">
			<param name="EnableContextMenu" value="0">
			<param name="EnablePositionControls" value="-1">
			<param name="EnableFullScreenControls" value="1">
			<param name="EnableTracker" value="-1">
			<param name="InvokeURLs" value="-1">
			<param name="Language" value="-1">
			<param name="Mute" value="0">
			<param name="PlayCount" value="1">
			<param name="PreviewMode" value="0">
			<param name="Rate" value="1">
			<param name="SAMILang" value="">
			<param name="SAMIStyle" value="">
			<param name="SAMIFileName" value="">
			<param name="SelectionStart" value="-1">
			<param name="SelectionEnd" value="-1">
			<param name="SendOpenStateChangeEvents" value="-1">
			<param name="SendWarningEvents" value="-1">
			<param name="SendErrorEvents" value="-1">
			<param name="SendKeyboardEvents" value="0">
			<param name="SendMouseClickEvents" value="1">
			<param name="SendMouseMoveEvents" value="0">
			<param name="SendPlayStateChangeEvents" value="-1">
			<param name="ShowCaptioning" value="0">
			<param name="ShowControls" value="1">
			<param name="ShowAudioControls" value="1">
			<param name="ShowDisplay" value="0">
			<param name="ShowGotoBar" value="0">
			<param name="ShowPositionControls" value="-1">
			<param name="ShowStatusBar" value="1">
			<param name="ShowTracker" value="1">
			<param name="TransparentAtStart" value="">
			<param name="VideoBorderWidth" value="0">
			<param name="VideoBorderColor" value="0">
			<param name="VideoBorder3D" value="0">
			<param name="Volume" value="1">
			<param name="WindowlessVideo" value="0">
			</object> -->
</body>
</html>
