<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<html>
<head>
    <meta charset="UTF-8" />
    <title>导航栏</title>
</head>
<body>

<div class="fl u-1-5">

   

<!-- 20170823-左边菜单-start -->
<div class="mc-menu-area">
    <div class="h"><a href="userServlet?method=getOrderProduct"><span>我的商城</span></a></div>
    <div class="b">
        <ul>
            <li>
                <h3 class="icon-mc-mail"><span>我的消息</span></h3>
                <ol>
                    <li><a href="#"><span id="li-msg">消息中心<em></em></span></a></li>
                    <li id="marketIDD"><a href="#"><span id="li-marketS">活动推送</span></a></li>
                </ol>
            </li>
            <li>
                <h3 class="icon-mc-order"><span>订单中心</span></h3>
                <ol>
                    <li id="li-order"><a href="orderServlet?mod=htmlMyOrder&status=1"><span>我的订单<em>（订单数暂留)</em></span></a></li>
                    <li id="li-order-small" style="display: none;"></li>
                    <li id="li-prdRemark"><a href="#"><span>商品评价</span></a></li>
                </ol>
            </li>
            <li>
                <h3 class="icon-mc-asset"><span>我的资产</span></h3>
                <ol>
                    <li id="li-newpoint"><a href="menberServlet?mod=getIntegral"><span>我的积分</span></a></li>
                    <li id="li-coupon"><a href="couponServlet?mod=getCouponUser"><span>我的优惠券</span></a></li>
                    <li id="li-point"><a href="#"><span>等级与特权</span></a></li>
                </ol>
            </li>
            <li>
                <h3 class="icon-mc-support"><span>购买支持</span></h3>
                <ol>
                    <li id="li-myAddress" class="current"><a href="shippingServlet?mod=getShippingByUserId"><span>收货地址管理</span></a></li>
                    <li id="li-authentication"><a href="#"><span>实名认证</span></a></li>
                    <li id="li-enterprise" class="hide"></li><!-- 优惠内购 -->
                    <li id="li-o2o" class="hide"><a href="/o2o?t=1571794979796"><span>O2O商城</span></a></li>
                </ol>
            </li>
            <li>
                <h3 class="icon-mc-help"><a href="#" target="_blank"><span>帮助中心</span></a></h3>
            </li>
        </ul>
    </div>
</div>
<!-- 20170823-左边菜单-end -->
        </div>
        
</body>
</html>
