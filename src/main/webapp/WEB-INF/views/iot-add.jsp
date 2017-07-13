<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>   
<!DOCTYPE html>
<html lang="en" style="background-color: #FFF;">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>添加物联网设备</title>
    <meta name="description" content="添加物联网设备">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="resources/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="resources/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
     <link rel="stylesheet" href="resources/assets/css/amazeui.min.css"/>
     <link rel="stylesheet" href="resources/assets/css/amazeui.datatables.min.css"/>
   <link rel="stylesheet" href="resources/assets/css/app.css"> 
	<!-- <script src="resources/assets/js/app.js"></script> 
<script src="resources/assets/js/amazeui.min.js"></script> -->
  
    <!--<script src="http://api.map.baidu.com/getscript?v=2.0&ak=8579ee3b967425dc22f0c3953825ec95&services=&t=20170506025723"></script>-->
</head>

<body data-type="login" class="theme-white">
	 <c_rt:choose>
  <c_rt:when test="${status}">
  <script src="resources/assets/js/theme.js"></script>
<div >
    <!-- 风格切换 -->
  
    <div class="tpl-login">
        <div class="tpl-login-content" >
            <div class="tpl-login-title">添加设备</div>
            <span class="tpl-login-content-info">
               		   添加一个设备，添加后的设备允许与云端数据交互
              </span>
            <form class="am-form tpl-form-line-form" action="iot-add"  method="POST">
                <span th:text="${addInfo}" style="color: red">${addInfo}</span>
                <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
                <div class="am-form-group">
                    <input type="text" name="name" class="tpl-form-input" id="name" placeholder="设备名称">
                </div>

                <div class="am-form-group">
                    <input type="text" name="serial" class="tpl-form-input" id="devId" placeholder="设备编号">
                </div>            

                    <div class="am-form-group">
                        <input type="text" name="desc" class="tpl-form-input" id="desc" placeholder="描述（选择填写）">
                    </div>
                
                    <div class="am-form-group">
                        <button type="submit"
                                class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交
                        </button>
                    </div>
            </form>
        </div>

  
    </div>
</div>

<script>

		$(function () {
			　	　$('form').bind('submit',function () {  //给form标签绑定submit事件
			　　　	　var i=0;			　
			　　　　var t=$('#name').val();  //判断textarea标签是否填写
			　　　　if (t=='') {
			　　　　　　alert('请填写设备名称');
		　　　　		return false;
			　　　　}
				  var t=$('#devId').val();  //判断textarea标签是否填写
				　　　　if (t=='') {
				　　　　　　alert('请填写设备编号');
						return false;
				　　　　}
				
				
			　　　
			　　});
		
			});



</script>

		
  </c_rt:when>
  
  <c_rt:otherwise>
  		<% response.sendRedirect("login"); %>
  		<%-- <jsp:forward page="home.jsp"></jsp:forward>  --%>
  </c_rt:otherwise>
  </c_rt:choose>


</body>

</html>