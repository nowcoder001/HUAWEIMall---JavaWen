<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'comment.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="shortcut icon" href="static/image/favicon.ico">
<link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superRed.css" />
	<link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/icon.css">
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.min.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superBlue.css" id="themeCss"/>
    <script type="text/javascript" charset="utf-8" src="admin/js/jquery-easyui-1.8.6/theme/super/super.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/locale/easyui-lang-zh_CN.js"></script>
	<link rel="shortcut icon" href="static/image/favicon.ico">
	<style type="text/css">
		.table_ tr{
			
		}
	</style>
  </head>
  
  <body>
  	<table id="dg"></table>
  	<!-- 回复窗口 -->
  	<div id="dd" class="easyui-dialog">
  		<center>
  		<table class="table_">
  			<tr align="center">
  				<td colspan="2" align='center'><font color="red" size="4">回复评论详情</font><br/><br/></td>
  			</tr>
  			<tr>
  				<td><font size="3">评论内容：</font></td>
  				<td id="comment_show"></td>
  			</tr>
  			<tr>
  				<td><font size="3">回复内容：</font></td>
  				<td ><input type="text" id="reply_content" class="easyui-textbox" data-options="iconCls:'fa fa-mail-reply' ,iconAlign:'left'" style="width:230px;height:30px;"/><br/><br/><br/></td>
  			</tr>
  			<tr>
  				<td colspan="2"><input id="submit_button" type="button" class="easyui-linkbutton primary"  value="回复评论" data-options="iconCls:'fa fa-check'" style="width: 100px;height: 60px;font-size: 15;"/></td>
  			</tr>
  		</table>
  		</center>
  	</div>
  </body>
  <script type="text/javascript" src="admin/js/comment.js"></script>
</html>
