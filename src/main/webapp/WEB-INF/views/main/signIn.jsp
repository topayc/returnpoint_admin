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
	    <form id = "loginForm" method="post" action="/j_spring_security_check">
	      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	      <div style ="height:14px"class="form-group">
	        <span class = "blue_message">${message}</span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="text" class="form-control border_gray opa" placeholder="EMAIL"  id = "adminEmail" name = "j_username" value ="${userSession.userId}">
	        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="password" class="form-control border_gray opa" placeholder="PASSWORD"  id ="adminPassword" name = "j_password" value ="${userSession.userPassword}">
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
</body>
</html>
