<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>   
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="resources/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="resources/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="resources/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="resources/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="resources/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

<script type="text/javascript" >
/* $(document).ready(function() {
	alert("点击了");
	jQuery(document).on('click', ".updateState", function() {
		
		var cur=$(this);
		cur.prev().text("获取中");
		var data={
				uuid:cur.attr("data-uuid")
		}
		jQuery.ajax({
			type: 'POST',
			 url: "getDeviceState",
			 data:data,
			 dataType: 'json',
			 success: function(json) { 
				if(json.status==0){
					if(json.redirectUrl=="在线")cur.prev().html("<b style='color:#0b4'>在线</b>");
					else cur.prev().text(json.redirectUrl);
					}
				} 
			});


	 });
	setInterval(function(){
		 $(".updateState").click();
	},1000 * 5);
	
}); */
</script>
<title>管理员列表</title>
</head>
<body>

  <c_rt:choose>
  <c_rt:when test="${status}">
</br>
<div align="center"><button  class="btn btn-success" ><a  style="text-decoration:none;color:white" href="iot-sub-add?uuid=${devUuid}">添加子设备</a></button>
<button  class="btn btn-success" ><a  style="text-decoration:none;color:white" onclick="get_uuid('当前设备uuid','iot-uuid','1','300','320','${devUuid}')">获取设备uuid</a></button>
</div>
</br>
  
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="9">设备列表</th>
			</tr>
			<tr class="text-c">
				
				<th width="50">序号</th>
				<th width="50">设备名称</th>
				<th width="50">设备类型</th>		
				<th width="50">设备参数</th>
			</tr>
		</thead>
		<tbody>
			<c_rt:forEach items="${iotSubDevices}" var="iotSubDevices">
			<tr class="text-c">				
				<td class="td-status-id" id="${iotSubDevices.id}">${iotSubDevices.id}</td>
				<td>${iotSubDevices.name}</td>
				<%-- <td>${iotDevices.time}</td> --%>
				<c_rt:choose>
					<c_rt:when test="${iotSubDevices.type==0}"><td class="td-status"><span class="label label-success radius">开关</span></td><td><button class="btn btn-success begin" style="width:48px;height: 23px;font-size:10px;">开始</button></td></c_rt:when>
					<c_rt:when test="${iotSubDevices.type==1}"><td class="td-status"><span class="label label-success radius">温度</span></td><td>${iotSubDevices.value}</td></c_rt:when>
					<c_rt:otherwise><td class="td-status"><span class="label label-success radius">湿度</span></td><td>${iotSubDevices.value}</td></c_rt:otherwise>
				</c_rt:choose>	
				
			
					
				
				<%-- <td>				
				<a  href="javascript:;" onclick="umbrella_list('继电器状态','umbrella-list','1','800','520','${umbrella.device_uuid}')"  style="text-decoration:none">				
				点击查看详情</a></td>	 --%>		
				
			</tr>
		</c_rt:forEach>	
		</tbody>
	</table>
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="resources/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="resources/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="resources/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="resources/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="resources/static/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="resources/static/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="resources/static/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/

/*  function myrefresh() 
 { 
 window.location.reload(); 
 } 
 setTimeout('myrdefresh()',10000);  */
function iot_sub(title,url,id,w,h,uuid){
	layer_show(title,url+'?uuid='+uuid,w,h);
}
 function get_uuid(title,url,id,w,h,uuid){	 
	 ;
		layer_mesg(uuid);
	}
 function layer_mesg(uuid){
	 layer.open({
		    content:uuid
		    ,btn: 'OK'
		  });
	}
/*管理员-删除*/
jQuery(document).on('click', ".begin", function() {	
	var cur = $(this);		
	//cur.text("哈哈哈");
	//cur.attr("class","btn btn-success begin hhhhhhhh")
	var data={
			devUuid:"${devUuid}",
	   		iotSubId:cur.parents("tr").find(".td-status-id").attr("id"),
	   		iotSubOperate:cur.html(),
	} 
   	 jQuery.ajax({
   		 type: 'POST',
   		 url: "relay-operate",
   		 data:data,
   		 dataType: 'json',
   		 success: function(json) { 
   			if(json.status==0){ 		//0代表当前操作为开启	  			
   				cur.text("关闭");	
   				cur.attr("class","btn btn-danger")		
   			}else if(json.status==1){   //1代表当前操作为关闭
   				cur.text("开启");	
   				cur.attr("class","btn btn-success")
   			}else{
   				layer.msg("操作失败");
   	   			}
   		} 
   
   	});	
   	
});	


/*管理员-编辑*/
function admin_edit(title,url,w,h,id){	
	layer_show(title,url+'?id='+id,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}
</script>
		
  </c_rt:when>
  
  <c_rt:otherwise>
  		<% response.sendRedirect("login"); %>
  		<%-- <jsp:forward page="home.jsp"></jsp:forward>  --%>
  </c_rt:otherwise>
  </c_rt:choose>
  
  
	
</body>
</html>