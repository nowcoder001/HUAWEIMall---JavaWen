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
<title>退款单_个人中心_&#x534e;&#x4e3a;&#x5546;&#x57ce;</title>
<link rel="shortcut icon" href="https://www.vmall.com/favicon.ico" />
<link href="./Style/css/ec.core.base.min.css" rel="stylesheet" type="text/css">

<link href="./Style/css/main.min.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="static/image/favicon.ico">
<script src="static/js/jquery-2.2.1.js"></script>
</head>
<body class="wide">
<%@include file="head.jsp" %>

	<div class="g">
	    <div class="fr u-4-5"><!-- 20141212-栏目-start -->
<div class="section-header">
	<div class="fl">
		<h2>
			<span>我的退款</span>
		</h2>
	</div>

</div>
<!-- 20141212-栏目-end -->

<!-- 20151208-防诈骗提示-start -->
<div class="order-pay-success-area">
	<div class="b"><div class="prevention-fraud"><p class="prevention-fraud-tips">注意：华为商城不会以订单异常、系统升级为由，要求您点击任何链接进行退款！ 请谨防<span>钓鱼链接或诈骗电话</span>！</p></div></div>
</div>
<!-- 20151208-防诈骗提示-end -->
<div class="myOrder-cate myExch-cate">
	<ul>
		<li class="current"><a href="javascript:;"  onclick="ec.member.refundList.cateLive(this,1);"><span>最近六个月<em></em></span></a></li>
		<li><a href="javascript:;" onclick="ec.member.refundList.cateLive(this,0);"><span>六个月以前<em></em></span></a></li>
	</ul>
</div>
<div class="hr-20"></div>

<!-- 20141225-我的退款单-列表-start -->
<div class="myExch-record">
	<div class="list-group-title" id="list-group-title">
		<table border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="col-no">订单号</th>
					<th class="col-no">退款编号</th>
					<th class="col-date">申请退款时间</th>
					<th>退款金额</th>
					<th  class="col-state">退款状态</th>
				</tr>
			</thead>
		</table>
	</div>

	<div class="list-group" id="list-group">
			<div class="list-group-empty">您暂时没有相关记录。</div>
	</div>
	<div class="list-group-page">
		<div class="pager" id="list-pager">
        </div>
	</div>
</div><!-- 20141225-我的退换货单-列表-end -->
</div>
<div class="fr u-1-5">
<div class="breadcrumb-area fcn"><a href="/index.html" >首页</a>&nbsp;&gt;&nbsp;<em id="personCenter"><a href="/member/" >我的商城</a></em><em id="pathPoint">&nbsp;&gt;&nbsp;</em><span id="pathTitle"></span></div>	
<%@include file="menu.jsp" %>
</div >
</div>
<%@include file="font.jsp"  %>
</body>
</html>
