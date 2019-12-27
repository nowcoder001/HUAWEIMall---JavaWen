<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'coupon.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/icon.css">
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.min.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superBlue.css" id="themeCss"/>
    <script type="text/javascript" charset="utf-8" src="admin/js/jquery-easyui-1.8.6/theme/super/super.js"></script>
	<script type="text/javascript" charset="utf-8" src="admin/js/echarts.min.js"></script>
	<script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="admin/coupon/coupon.js"></script>

  	<style type="text/css">
		table.imagetable {
		    font-family: verdana,arial,sans-serif;
		    font-size:22px;
		}
		table.imagetable th {
		    padding: 22px;
		}
		table.imagetable td {
		    padding: 22px;
		}
		#h1{
			font-size:250%;
		}
	</style>
  </head>
  
  <body>
  <table border="1" align="center" id="objtab">

   </table>
   <div id="win" style="display: none;">
  	<form action="couponServlet?mod=insert" method="post" id="ff">
    	<table id="objtab" class="imagetable">
    		<tr>
    			<td align="left" >优惠券id：</td>
    			<td><input id="id" name="id" type="text" value="0" readonly="readonly"/></td>  			
    			<td align="right">优惠券描述：</td>
    			<td><input type="text" name="depict" id="depict"/></td>
    		</tr>
	    	<tr>    			
    		</tr>
    		<tr>
    			<td align="left">金额数量：</td>
    			<td><input type="text" name="money" id="money"/></td>
    			<td align="right">优惠券名称：</td>
    			<td><input type="text" name="couponName" id="couponName"/></td>
    		</tr>
    		<tr>    			
    		</tr>
    		<tr>
    			<td align="left">优惠券获得日期：</td>
    			<td><input type="text" name="couponGetTime" id="couponGetTime"/></td>
    			<td align="right">是否可用：</td>
    			<td><input type="text" name="couponUse" id="couponUse"/></td>
    		</tr>
    		<tr>
    		</tr>
	    	<tr>
    			<td align="left">创建时间：</td>
    			<td><input id="createTime"  type="text" name="createTime"/></td>
    			<td align="right">更新时间：</td>
    			<td><input id="updateTime"  type="text" name="updateTime" /></td>
    		</tr>
    		<tr>
	  			<td>
		  			<input id="a" type="reset" value="新增"/>
	  			</td>
	  		</tr>
	    	</table>
    	</form>
   </div>
   
   <div id="user" style="display: none;">
   <center>
   			<h1 id="h1">请选择你要赠送的用户！</h1>
   			<br>
   			<select id="userName" class="easyui-combotree" style="width:200px;" ></select>
   			<br/>
   			<br/>
   			<a id="submit" style="width: 100px;height: 50px; font-size: 30px;" class="easyui-linkbutton primary" data-options="iconCls:'fa fa-check'" />提交</a>
   	</center>
   </div>
  </body>
</html>
