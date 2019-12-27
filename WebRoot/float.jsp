<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>My JSP 'float.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>

  <body>
    <div class="hungBar j-hungBar">
        <div class="hungBar-content">
        <div class="hungBar-common">
        <a target="_blank"  href="orderServlet?mod=getUserCart" timetype="timestamp" class="hungBar-cart"><span style="" id="cart_num"></span><i>购物车</i></a>
        <a target="_blank" id="tools-nav-service-robotim" href="http://wpa.qq.com/msgrd?v=3&uin=1551282386&site=qq&menu=yes" class="hungBar-service" style="display: block;"><i>在线客服</i></a>
        <a id="tools-nav-survery"  href="javascript:;" class="hungBar-feedback" style="display: block;"><i>意见反馈</i></a>
        <a href="javascript:;" class="hungBar-top" id="hungBar-top" style="display: none;"><i>返回顶部</i></a>
        </div>
    </div>
</div>

  </body>

  <script type="text/javascript">
  	$(function(){
  	 $.ajax({
       url: 'orderServlet',
       type: 'post',
       dataType: 'text',
       data: {
          mod:'ajaxGetUserCart',
          cartFloat:'11'
       },
       success:function(data){
        var count = parseInt(data);
        $('#cart_num').text(count);

       }
     })


  	})
  </script>
</html>
