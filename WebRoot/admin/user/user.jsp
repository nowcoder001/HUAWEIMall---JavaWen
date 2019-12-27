<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superRed.css" />
	<link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="admin/user/user.css">
    
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.min.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superBlue.css" id="themeCss"/>
    <script type="text/javascript" charset="utf-8" src="admin/js/jquery-easyui-1.8.6/theme/super/super.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/locale/easyui-lang-zh_CN.js"></script>
    
  </head>
  
  <body>
    <table id = "tbs"></table>

  
  <div id="insmage" style="display: none;" class="form">
	<form id = "uptfsb" method="post">
	<center>
	<div class="item">
		<span>用户名：</span>
		<input id="user_name" name="name"class="easyui-validatebox easyui-textbox" data-options="width:300">
	</div>
	<div class="item">
		<span>密　码：</span>
		<input id="user_pass" name="pass"class="easyui-validatebox easyui-textbox" data-options="width:300">
	</div>
    <div class="item">
        <span>手机号：</span>
        <input id="user_iphones" name="ihones"class="easyui-validatebox easyui-textbox" data-options="width:300">
    </div>
    <div class="item">
        <span>邮箱：</span>
        <input id="user_emails" name="emails"class="easyui-validatebox easyui-textbox" data-options="width:300">
    </div>
    <div class="item">
        <span>出生日期：</span>
        <input id="user_date" name="describes"class="easyui-validatebox easyui-textbox" data-options="width:300">
    </div>
    <div class="item">
        <span>国籍：</span>
        <input id="user_sata" name="nationalitys"class="easyui-validatebox easyui-textbox" data-options="width:300">
    </div>
     <div class="item">
        <span ><font size="3">用户头像:</font></span>
    </div>

	<div class="item">
		<span></span>
		<label><input id="updatebutton" type="button" value="修改员工" class="easyui-linkbutton success" data-options="plain:true,width:80,height:40"/></label>
	</div>
	</center>
	</form>
</div>
 
  </body>
  <script type="text/javascript" src="admin/user/user.js"></script>
</html>
