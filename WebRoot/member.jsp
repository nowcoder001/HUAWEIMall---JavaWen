<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">

	<title>首页_个人中心_华为商城</title>
	<link rel="shortcut icon" href="#">
	<link href="./Style/css/ec.core.base.min.css" rel="stylesheet" type="text/css">

	<link href="./Style/css/main.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="static/js/jquery-2.2.1.js"></script>
<script type="text/javascript">
  	$(function(){
		$.ajax({
			url:"userServlet",
			type:"post",
			data:{
				method:'menberGetInfo',
			},
			dataType:"json",
			success:function(data){

                if (data == "1") {
                    window.location.href = 'login.jsp';
                }else{
                   $('#phone').html(data[0].user_name);

                    if(data[0].user_img == null || data[0].user_img == ""){

                        $('#userImg').attr('src',"./Style/image/pg1.png")
                    }else{
                        $('#userImg').attr('src',data[0].user_img);

                    }
                }


			}
		});
  	});
</script>
</head>
<body class="wide">
<%@include file="head.jsp" %>

<div id="base_index" class="mc-index">
	<div class="g">
	    <div class="fr u-4-5" style = "float:right;margin-top: 30px;">
        <div class="mc-detail">
            <div class="clearfix">
                <div class="mc-user-card fl">
                    <div class="user-head fl">
                        <a class="user-img" href="#" >
                            <img  id="userImg"src="" >
                        </a>
                    </div>
                    <div class="mc-user-info fl ">
                        <a class="name" id="phone"  href="#" id="phone" ></a>
                        <a id="user-vip-level-tips-index-member" class="user-level icon-vip-level-0" target="_blank" href="#">
                            <em></em>
                            <p><span id="canvas-index-member" style="width: 0%"></span></p>
                            <div class="data">0/100</div>
                        </a>
                        <div class="mc-user-btn">
                            <a href="information.jsp" class="color-1">会员权益</a>
                            <a href="information.jsp"  class="color-2">帐号中心</a>
                        </div>
                    </div>
                    <div class="mc-user-info fl hide"><!--未登录-->
                        <a href="#" class="mc-user-login-btn">登录／注册</a>
                        <p>登陆后可查看您的会员权益</p>
                    </div>
                </div>

                <input type="hidden" id="myUserScores" value="0">
                    <input type="hidden" id="myGradeCode" value="0">
                    <input type="hidden" id="minScores" value="0">
                    <input type="hidden" id="nextNeedScores" value="100">
                <div class="mc-user-huaban fl">
                    <ul class="clearfix">
                        <li>
                            <p class="p-price"><span id="balanceicon_myCenter" class=""><a href="integral.jsp" target="_blank">0</a></span></p>
                            <p class="p-dec">积分</p>
                            <a id="balanceicon_myCenter_url" href="integral.jsp" class="p-btn">去查看</a>
                        </li>
                        <li>
                            <p class="p-price"><span id="couponicon_myCenter"><a href="coupon.jsp">0</a></span></p>
                            <p class="p-dec">优惠券</p>
                            <a id="couponicon_myCenter_url" href="coupon.jsp" class="p-btn">去查看</a>
                        </li>
                        <li>
                            <p class="p-price"><span id="balanceAmount_myCenter" class=""><a href="#" target="_blank">0.00</a></span></p>
                            <p class="p-dec">代金券</p>
                            <a id="balanceAmount_myCenter_url" href="#" class="p-btn">去查看</a>
                        </li>
                        <li>
                            <p class="p-price"><span id="petalicon_myCenter" class="p-price-no"><a href="#" target="_blank">0</a></span></p>
                            <p class="p-dec">花瓣</p>
                            <a id="petalicon_myCenter_url" href="#" class="p-btn">去查看</a>
                        </li>
                    </ul>
                </div>
            </div>
        <div class="hr-18"></div>
        <div class="fl mc-middle" style="margin-top: 20px;">
            <div class="mc-detail mc-order">
                <div class="mc-title ">
                    <h3>我的订单</h3>
                    <a href="#">全部订单</a>
                </div>
                <div class="mc-user-portal">
                	<ul class="clearfix">
                        <li class="portal-icon-1 " id="portal-icon1" style="margin-right: 160px;">
                        	<c:if test="${requestScope.NO_PAY > 0 }">
	                            
	                            <span id="un_payment_wechat" style="display:  block;"><em>${requestScope.NO_PAY }</em></span>
	                            <a href="orderServlet?mod=htmlMyOrder&status=10"><span style="display:none;"></span>待付款</a>
                            </c:if>
                            <c:if test="${requestScope.NO_PAY == 0 }">
	                            
	                            <span id="un_payment_wechat" style="display:  none;"></span>
	                            <a href="orderServlet?mod=htmlMyOrder&status=10"><span ></span>待付款</a>
                            </c:if>
                        </li>
                        <li></li>
                        <li class="portal-icon-2 " id="portal-icon2" style="margin-right: 160px;">
                        	<c:if test="${requestScope.YES_PAY > 0 }">
                            	
                            	<span id="un_received_wechat" style="display:  block;"><em>${requestScope.YES_PAY }</em></span>
                            	<a href="orderServlet?mod=htmlMyOrder&status=30"><span id="un_received" style="display:none"></span>待收货</a>
                            </c:if>
                            <c:if test="${requestScope.YES_PAY == 0 }">
                            	
                            	<span id="un_received_wechat" style="display:  none;"></span>
                            	<a href="orderServlet?mod=htmlMyOrder&status=30"><span id="un_received"></span>待收货</a>
                            </c:if>
                        </li>
                        <li></li>
                        <li class="portal-icon-3 " id="portal-icon3">
                        	<c:if test="${requestScope.COMMENT > 0 }">
                            	<a href="orderServlet?mod=htmlMyOrder&status=20"><span id="un_remark"><em>${requestScope.COMMENT }</em></span>待评价</a>
                            </c:if>
                            <c:if test="${requestScope.COMMENT == 0 }">
                            	<a href="orderServlet?mod=htmlMyOrder&status=20"><span id="un_remark"></span>待评价</a>
                            </c:if>
                        </li>
                    </ul>
                    
                </div>

                 <div class="mc-order-list">
                    <ul>
                    	<c:forEach items="${requestScope.ORDER_LIST }" var="order" varStatus="row">
                    		<li class="clearfix">
	                            <div class="p-img-list">
	                                <div class="p-img">
	                                    <img src="${order.orderItem.productImage }">
	                                </div>
	                            </div>
	                            <div class="p-num">${order.orderItem.productName }</div>
	                            <c:if test="${order.status == 10 }">
	                            	<div id="orderStatus_11690425916" class="p-state">
		                                           待付款
		                            </div>
	                            </c:if>
	                            <c:if test="${order.status == 20 }">
	                            	<div id="orderStatus_11690425916" class="p-state">
		                                           已付款
		                            </div>
	                            </c:if>
	                            <c:if test="${order.status == 30 }">
	                            	<div id="orderStatus_11690425916" class="p-state">
		                                           待收货
		                            </div>
	                            </c:if>
	                            <c:if test="${order.status == 50 }">
	                            	<div id="orderStatus_11690425916" class="p-state">
		                                           已完成
		                            </div>
	                            </c:if>
	                            
	                            <div class="p-price">¥${order.payment }</div>
	                            <div class="p-operate">
	                            	<c:if test="${order.status == 10 }">
	                            		<a href="orderServlet?mod=orderDetail&order_id=${order.id }" class="p-btn">去支付</a>
	                            	</c:if>
	                                    
	                                <a href="orderServlet?mod=orderDetail&order_id=${order.id }" class="p-link">订单详情</a>
	                            </div>
	                        </li>
	                        
	                        <c:if test="${row.index ==  4}">
	                        	<c:set var="flag" value="false"/>
	                        </c:if>
                    	</c:forEach>
                        
                    </ul>
                </div>
            </div>
            <div class="hr-18"></div>
		  <div id="personal_center_box" class="mc-detail relative hide">
		  	<div class="mc-title"><h3>会员专享</h3><a href="#">更多会员权益</a></div>
		  	<div class="mc-member">
		  		<div class="mc-member-detail swiper-container" id="exchange-point-roll">
		  			<ul class="clearfix swiper-wrapper" id="personal_center_ul">
		  			</ul>
		  			<div class="grid-btn btn-prev swiper-button-prev disabled"><span></span></div>
		  			<div class="grid-btn btn-next swiper-button-next disabled"><span></span></div>
		  		</div>
		  	</div>
		  </div>
            <div class="mc-detail">
                <div class="mc-title">
                    <h3>为你推荐</h3>
                </div>
                <div class="mc-recommend">
                <!--左图右文-->
                <ul class="msg-type-4">
                    </ul>
                    		<div class="banner">
                    			<input class="msg-type-5" type="hidden" value="">
                        		<a href="#" target="_blank"><img src="./Style/image/pg7.jpg"></a>
                    		</div>
                    		<div class="banner">
                    			<input class="msg-type-5" type="hidden" value="">
                        		<a href="#" target="_blank"><img src="./Style/image/pg8.jpg"></a>
                    		</div>
                </div>
            </div>
        </div>
        <div class="fr mc-right" style="margin-top: 20px;">
                <div class="mc-detail">
                    <div class="mc-title">
                        <h3>我的消息</h3>
                    </div>
                    <div class="mc-news">
                        <ul>
                            <li><a href="couponServlet?mod=getCouponUser"><em class="icon-2"></em>您有2张优惠券到账，进来看看吧</a>
                            </li>
                        </ul>
                    </div>
                </div>

				<div class="hr-18"></div>
        <p><a href="#" class="banner" target="_blank" title="个人中心订单列表下广告位"><img src="./Style/image/pg2.png" title="以旧换新" style="float:none;"></a></p>
        <div class="hr-18"></div>
		<div id="grid_config_box" class="mc-detail hide"></div>
  <div id="grid_config_hr" class="hr-18 hide"></div>
		<p><a href="" class="banner" target="_blank" title="个人中心订单列表下广告位"><img src="./Style/image/pg3.png" title="新人见面礼" style="float:none;"></a></p>
		<div class="hr-18"></div>
		<div class="mc-detail">
    		<div class="mc-title">
        		<h3>华为移动生活</h3>
    		</div>
    		<div class="mc-banner-list">
        		<ul>
            	 <li>
              <a href="#" target="_blank" title="个人中心订单列表下广告位"><img src="./Style/image/pg5.png" title="视频卡" style="float:none;"></a>
              </li>
		            <li>
              <a href="#" target="_blank" title="个人中心订单列表下广告位"><img src="./Style/image/pg6.png" title="音乐卡" style="float:none;"></a>
              </li>
		            <li>
              <a href="" target="_blank" title="个人中心订单列表下广告位"><img src="./Style/image/pg4.png" title="花币卡" style="float:none;"></a>
              </li>
        		</ul>
    		</div>
		</div>
		</div>
	</div>
	</div>
	<div class="fr u-1-5" style="float:left;">
		<div class="breadcrumb-area fcn">
		<a href="/index.html" >首页</a>&nbsp;&gt;&nbsp;
		<em id="personCenter"><a href="/member/" >我的商城</a>
		</em><em id="pathPoint">&nbsp;&gt;&nbsp;</em>
		<span id="pathTitle"></span></div>
		<%@include file="menu.jsp" %>
	</div >
	</div>
<%@include file="font.jsp"%>
</body>
</html>
