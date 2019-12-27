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

<title>消息中心_个人中心_&#x534e;&#x4e3a;&#x5546;&#x57ce;</title>
<link rel="shortcut icon" href="https://www.vmall.com/favicon.ico" />

<link href="./Style/css/ec.core.base.min.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="static/image/favicon.ico">
<link href="./Style/css/main.min.css" rel="stylesheet" type="text/css">
<script src="static/js/jquery-2.2.1.js"></script>
</head>
<body class="wide">
<%@include file="head.jsp"  %>

	<div class="g">
	    <div class="fr u-4-5">﻿<!-- 20141212-栏目-start -->
<div class="section-header section-header-new">
    <div class="fl">
        <h2><span>消息中心</span></h2>
    </div>
</div>
<!-- 20141212-栏目-end -->
<div class="mymail-hd">
	<div class="tab">
    	<ul class="clearfix">
    		<li class="current"><a href="javascript:;" onclick="ec.member.msg.check(this,'activitymsg');"><span id="li-activitymsg">活动消息<em></em></span></a></li>
    		<li><a href="javascript:;" onclick="ec.member.msg.check(this,'logisticsmsg');"><span id="li-logisticsmsg">交易物流<em></em></span></a></li>
    		<li><a href="javascript:;" onclick="ec.member.msg.check(this,'ntfmsg');"><span id="li-ntfmsg">通知消息<em></em></span></a></li>
    		<li><a href="javascript:;" onclick="ec.member.msg.check(this,'InteractiveMsg');"><span id="li-InteractiveMsg">互动消息<em></em></span></a></li>
    	</ul>
	</div>
</div>
<!-- 20190703-活动消息-列表-start -->

<div class="mymail-record mymail-record-activity" id="msgcenterid">
	<ul class="mymail-record-detial" id="msgcenterid-ul">
	  <!-- sysMsgVoList -->
		 <!-- sysMsgVoEx -->
		<li>
			<div class="record-con">
				<div class="record-title clearfix">
					<a href="javascript:;">&#x8363;&#x8000;&#x5e74;&#x8d27;&#x8282;&#xff0c;&#x8d85;&#x503c;&#x5e74;&#x8d27;&#x793c;&#x5305;&#x9650;&#x91cf;&#x9001;</a>
					<span class="record-time">2019-01-07 17:15:29</span>
				</div>
				<div class="record-detail">
						<p><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif">多款热销潮品限时直降，还有超值优惠券等你领用，<a href="https://msale.vmall.com/honor.html?cid=98981">年终扫货快人一步&gt;&gt;</a></span></span></p>

				</div>
			</div>

		</li>
 <!-- sysMsgVoEx -->
		<li>
			<div class="record-con">
				<div class="record-title clearfix">
					<a href="javascript:;">&#x7cbe;&#x9009;&#x5723;&#x8bde;&#x597d;&#x793c;&#xff0c;&#x603b;&#x6709;&#x4e00;&#x6b3e;&#x9002;&#x5408;&#x4f60;</a>
					<span class="record-time">2018-12-21 21:04:19</span>
				</div>
				<div class="record-detail">
						<p><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif">Mate20|X购机限量赠圣诞礼包，<a href="https://msale.vmall.com/huawei.html?cid=98928">Pick新机&gt;&gt;</a></span></span></p>

<p><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif">荣耀V20年度钜献，预订<a href="https://msale.vmall.com/honor.html?cid=98071">赢日本豪华游轮双人游&gt;&gt;</a></span></span></p>

				</div>
			</div>

		</li>
 <!-- sysMsgVoEx -->
		<li>
			<div class="record-con">
				<div class="record-title clearfix">
					<a href="javascript:;">&#x6696;&#x51ac;&#x949c;&#x732e;&#xff0c;&#x534e;&#x4e3a;&#x591a;&#x6b3e;&#x65b0;&#x54c1;&#x6765;&#x88ad;</a>
					<span class="record-time">2018-12-13 18:24:26</span>
				</div>
				<div class="record-detail">
						<p><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif">华为畅享9新品上市，订金1元最高抵50，购华为FreeBuds 2&nbsp;无线耳机，<a href="https://msale.vmall.com/cx9.html?cid=98071">晒单抽好礼&gt;&gt;</a></span></span></p>

				</div>
			</div>

		</li>
 <!-- sysMsgVoEx -->
		<li>
			<div class="record-con">
				<div class="record-title clearfix">
					<a href="javascript:;">1212&#x5e74;&#x7ec8;&#x72c2;&#x6b22;&#x8fdb;&#x884c;&#x65f6;</a>
					<span class="record-time">2018-12-12 14:23:17</span>
				</div>
				<div class="record-detail">
						<p><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif">最高省2100，好货特惠秒杀低至1元起，整点购机赠配件，<a href="https://msale.vmall.com/1212.html?cid=98071">还有超值神券免费领&gt;&gt;</a></span></span></p>

				</div>
			</div>

		</li>
 <!-- sysMsgVoEx -->
		<li>
			<div class="record-con">
				<div class="record-title clearfix">
					<a href="javascript:;">12.12&#x20;AI&#x7cbe;&#x5f69;&#xff0c;&#x8d2d;&#x72c2;&#x6b22;</a>
					<span class="record-time">2018-12-10 16:43:27</span>
				</div>
				<div class="record-detail">
						<p><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif">老用户购华为Mate20|X系列享1年碎屏险，1212元神券每天10:08限量领取，<a href="https://msale.vmall.com/huawei.html?cid=5131">更多优惠&gt;&gt;</a></span></span><br />
&nbsp;</p>

				</div>
			</div>

		</li>
    </ul>
   
    </div>
    
</div>
 <div class="fr u-1-5">
<%@include file="menu.jsp" %>
</div >

</div>
<%@include file="font.jsp"  %>
</div>

</div>



</body>
</html>
