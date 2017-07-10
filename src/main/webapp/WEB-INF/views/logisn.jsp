<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>   
<!--Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>智能护花物联网云平台</title>
<link href="resources/css/style.css" rel="stylesheet" type="text/css" media="all"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="智能护花物联网云平台 Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
<!--web-fonts-->
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css' />
<!--web-fonts-->
</head>

<body>
 <c_rt:choose>
  <c_rt:when test="${status}">
  		<% response.sendRedirect("home"); %>
	<%-- 	<jsp:forward page="home.jsp"></jsp:forward>  --%>
	</c_rt:when>
  
<c_rt:otherwise>
<!--header-->
	<div class="header-w3l">
		<h1> Transparent Login Form</h1>
	</div>
<!--//header-->

<!--main-->
<div class="main-content-agile">
	<div class="sub-main-w3">	
		<form action="/xiao7/login" method="post">
			<input placeholder="Username or E-mail" name="Name" class="user" type="text" required=""><br>
			<input  placeholder="Password" name="Password" class="pass" type="password" required=""><br>
			<input type="submit" value="">
		</form>
	</div>
</div>
<!--//main-->

<!--footer-->
<div class="footer">
	<p>&copy; Xiao 7. © SHU | Design by W3layouts</p>
</div>
<!--//footer-->
 </c_rt:otherwise>
</c_rt:choose>
</body>
</html>