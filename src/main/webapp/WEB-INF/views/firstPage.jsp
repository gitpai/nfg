<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>   
<!DOCTYPE HTML>
<html>
  <head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="keywords" content="scclui框架">
	<meta name="description" content="scclui为轻量级的网站后台管理系统模版。">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="stylesheet" href="resources/css/sccl.css">
    <title>首页</title>
    
  </head>
  
  <body>
  <c_rt:choose>
  <c_rt:when test="${status}">
  <div align="center"><font  color="green" > <h1 style="font-size: 1.5em;font-weight:700;margin-bottom:5px;">智能护花云平台</h1></font></div>
  <div align="center">
  	<img  src="resources/image/bg1.jpg" width="94%" height="60%">
  </div>
		
  <div  class="choosePad">
  	<a href="" style="background-color:#FFCC22; margin-right:0;"><i class="icon-font" >&#xe600;</i><br>所有设备<span>1</span>个</a>
  	<a href="" style="background-color:#33AECC;"><i class="icon-font" >&#xe604;</i><br>添加设备</a>
  	<a href="" style="background-color:#666699; margin-right:0;"><i class="icon-font" >&#xe602;</i><br>使用手册</a>
  	<a href="" style="background-color:#CCCC99;"><i class="icon-font" >&#xe605;</i><br>关于我们</a>
  </div>	
  </c_rt:when>
  
  <c_rt:otherwise>
  		<% response.sendRedirect("login"); %>
  		<%-- <jsp:forward page="home.jsp"></jsp:forward>  --%>
  </c_rt:otherwise>
  </c_rt:choose>
  </body>
</html>