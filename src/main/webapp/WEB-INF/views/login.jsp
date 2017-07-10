<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>   
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>
        <meta charset="utf-8">
        <title>物联网云平台</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="resources/assets1/css/reset.css">
        <link rel="stylesheet" href="resources/assets1/css/supersized.css">
        <link rel="stylesheet" href="resources/assets1/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>
 	<c_rt:choose>
  	<c_rt:when test="${status}">
  		<% response.sendRedirect("home"); %>
	<%-- 	<jsp:forward page="home.jsp"></jsp:forward>  --%>
	</c_rt:when>
  
	<c_rt:otherwise>

        <div class="page-container">
            <h1>物联网云平台</h1>
            <form action="/controller/login" method="post">
                <input type="text" name="userName" class="username" placeholder="账号">
                <input type="password" name="password" class="password" placeholder="密码">
                <button type="submit">登录</button>
                <div class="error"><span>+</span></div>
            </form>
            <div class="connect">
             
               
            </div>
        </div>
     

        <!-- Javascript -->
        <script src="resources/assets1/js/jquery-1.8.2.min.js"></script>
        <script src="resources/assets1/js/supersized.3.2.7.min.js"></script>
        <script src="resources/assets1/js/supersized-init.js"></script>
        <script src="resources/assets1/js/scripts.js"></script>
          </c_rt:otherwise>
</c_rt:choose>

    </body>

</html>

