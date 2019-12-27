<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests" />

<title>商品评价_个人中心_&#x534e;&#x4e3a;&#x5546;&#x57ce;</title>
<link rel="shortcut icon" href="https://www.vmall.com/favicon.ico" />
<link href="./Style/css/ec.core.base.min.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="static/image/favicon.ico">
<link href="./Style/css/main.min.css" rel="stylesheet" type="text/css">
<script src="static/js/jquery-2.2.1.js"></script>
</head>
<body class="wide">
<%@include file="head.jsp" %>
<div id ="base_index" class="">
<div class="hr-10"></div>
	
	

	<div class="g" >
		

<div class="hr-10"></div>
	    <div class="fr u-4-5">
		<div class="section-header">
    	<div class="fl">
        <h2><span>商品评价</span></h2>
    	</div>
    	<div class="fr">
    	<div class="ec-tab" id="ec-tab">
    		<ul>
    			<li class="current"><a href="javascript:;" onclick="ec.member.commodity.seltime(this,0);"><span>最近六个月商品</span></a></li>
    			<li><a href="javascript:;" onclick="ec.member.commodity.seltime(this,1);"><span>六个月之前商品</span></a></li>
    		</ul>
    		<div class="ec-tab-arrow" style="width: 124px; left: 0px;"></div>
    	</div>
    </div>
</div>

<div class="hr-20"></div>

<div class="myEval-record">
	<div class="list-group-title" id="list-group-title">
		<table border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="col-pro">商品</th>
					<th class="col-operate">评价</th>
					<th class="col-int">评价经验值</th>
				</tr>
			</thead>
		</table>
	</div>
	<div class="list-group" id="list-group">
	</div>
	<div class="list-group-page">
		<div class="pager" id="list-pager"></div>
	</div>
	</div>
</div>
<input type="hidden" id="colid" value="0"/>
<div class="fr u-1-5">
<div class="breadcrumb-area fcn"><a href="/index.html" >首页</a>&nbsp;&gt;&nbsp;<em id="personCenter"><a href="/member/" >我的商城</a></em><em id="pathPoint">&nbsp;&gt;&nbsp;</em><span id="pathTitle"></span></div>	
<%@include file="menu.jsp" %>
</div >
</div>

</div>
<%@include file="font.jsp"  %>
</body>
</html>
