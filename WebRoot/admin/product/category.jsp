<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'category.jsp' starting page</title>
    
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
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.min.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superBlue.css" id="themeCss"/>
    <script type="text/javascript" charset="utf-8" src="admin/js/jquery-easyui-1.8.6/theme/super/super.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/locale/easyui-lang-zh_CN.js"></script>
    <link rel="shortcut icon" href="static/image/favicon.ico">
    <style type="text/css">
    	.wenzhang td {
	    	padding: 10px 5px;
		    height: 28px;
		    line-height: 28px;
		    font-size: 13px;
    	}
    </style>
  </head>
  
  <body>
   <table id="dg"></table>
   <!-- 添加分类  弹出窗口 -->
  		<div id="add_category_win">
  			<form id="ff" method="post">
  				<div style="margin:10px">
  					<table class="wenzhang"  >
  						<tr>
  							<td align="right"><label for="" class="label-top">分类名称：</label></td>
  							<td><input id="cat_name" name="cat_name"class="easyui-validatebox easyui-textbox" prompt="请输入分类名称" data-options="width:200,required:true"></td>
  						</tr>
  						<tr>
  							<td align="right"><label for="" class="label-top">上级分类：</label></td>
  							<td>
  								<font color="red">按钮说明(开：属于顶级节点)</font><br/>
  								<select name="category" id="category" class="easyui-combotree" style="width:200px;" ></select>
  								<input name="isMax" id="isMax" class="easyui-switchbutton" data-options="onText:'开',offText:'关',setValue:'off'">
  							</td>
  						</tr>
  						<tr>
  							<td align="right"><label for="" class="label-top">分类状态：</label></td>
  							<td>
  								<select name="cat_status" id="cat_status" class="easyui-combobox" style="width:200px;" data-options="panelHeight:100">
  									<option value="1">正常</option>
  									<option value="2">停用</option>
  								</select>
  							 </td>
  						</tr>
  						<tr>
  							<td align="right"><label for="" class="label-top">分类排序：</label></td>
  							<td><input name="cat_sort" id="cat_sort" class="easyui-textbox" style="width:200px;"> </td>
  						</tr>
  						<tr>
  							<td colspan="2"><a id="submit" style="width: 100px;height: 50px; font-size: 30px;" class="easyui-linkbutton primary" data-options="iconCls:'fa fa-check'" />提交</a></td>
  						</tr>
  					</table>
				</div>
			</form>
  		</div>
  </body>
  <script type="text/javascript" src="admin/js/category.js"></script>
</html>
