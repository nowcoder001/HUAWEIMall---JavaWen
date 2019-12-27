<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>支付界面</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/cashierStyle.css">
    <link rel="shortcut icon" href="static/image/favicon.ico">
    <script src="static/js/jquery-2.2.1.js"></script>
</head>
<body>
<div class="container" style="">
    <!--<div class="header clearfix"></div>-->
    <div class="warp warp-content clearfix" style="min-height: 585px;">
        <div class="contentArea clearfix">

    <meta charset="utf-8">

<div class="logo_name clearfix">
    <a class="huawei_logo" href="https://www.vmall.com"><img class="logoImg" logoname="logoSrc" logoattr="flag" srcs="https://res.vmallres.com/ips/pc/20190829/img/" alt="huawei" src="https://res.vmallres.com/ips/pc/20190829/img/Vmall_red.png?20190829"></a>
    <span class="i18n" name="msg_Checkout">收银台</span>
</div>

            <div class="cashier_order_area clearfix" id="cashierHome">
                    <div class="cashi%%er_orderInfo clearfix">
                        <div class="orderInfo_img pull-left">
                            <img src="static/img/Z1.png">
                        </div>
                        <div class="orderInfo_words pull-left">
                            <div class="orderInfo_words_01 clearfix">
                                <div class="orderWords01 clearfix">
                                    <h4 class="pull-left" name="msg_prompt">订单提交成功，只差付款了~</h4>
                                    <div class="orderNum pull-left">
                                        <span name="msg_orderNo">订单号：</span>
                                        <span style="color: #da3232;">${requestScope.ORDER.orderNo }</span>
                                    </div>
                                    <div class="payNum pull-right">
                                        <span class="pull-right"></span>
                                        <span class="payNumAll pull-right">¥${requestScope.ORDER.payment }</span>
                                        <span class="payTitle pull-right">订单金额：</span>
                                    </div>
                                </div>
                                <h5 class="clearfix">
                                    <div class="orderInfo_show pull-right">
                                        <span class="pull-right" name="msg_orderInf">订单详情</span>
                                        <img src="https://res.vmallres.com/ips/pc/20190829/img/down arrow_icon.png?20190829">
                                    </div>
                                </h5>
                                <p class="inventory-warning clearfix" name="msg_warningPay">订单含付款减库存商品，支付完成才会为您预留库存，库存不足时系统将自动取消未支付的订单。</p>

                            </div>
                            <div class="orderDetails clearfix">
                                <div class="orderDetails_words">
                                    <ul>
                                        <li show=""><span name="msg_sendNomber">订单编号：</span>
                                            <span style="color: #da3232;">${requestScope.ORDER.orderNo }</span>
                                        </li>
                                        <li show=""><span name="msg_goodsName">商品名称：</span><span>${requestScope.ORDER.product.name }</span>
                                        </li>
                                        <li show=""><span name="msg_billInf">发票信息：</span><span>电子普通发票</span><span>个人</span>
                                        </li>
                                        <li show=""><span name="msg_buyTime">购买时间：</span><span id="time_date"></span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <ul class="nav nav-tabs" id="payTab">
                        <li class="COMMON active" name="common_pay" sort="0">
                            <a href="#common_pay" name="msg_common_pay" data-toggle="tab">常用</a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <ul class="tab-pane fade in active" name="COMMON" id="common_pay">
                            <li bankcode="ALIPAY" banktype="THIP" act="true" class="select">

                                <div class="act-line">
                                    <i class="recomType">推荐</i>

                                </div>
                                <div class="pay_typeIco tooltip-test clearfix" id="alipay" bankcode="ALIPAY" banktype="THIP" data-toggle="tooltip" title="" data-original-title="支付宝">
                                    <img src="static/img/Z2.png" alt="支付宝">
                                    <i id="alipay_i" class="alipay_i"></i>
                                </div>
                                <div class="payTool-descr clearfix"></div>
                            </li>

                            <li bankcode="WXPAY" banktype="THIP" act="true">

                                <div class="act-line">
                                    <i class="recomType">推荐</i>

                                </div>
                                <div class="pay_typeIco tooltip-test clearfix" id="wxpay" bankcode="WXPAY" banktype="THIP" data-toggle="tooltip" title="" data-original-title="微信支付">
                                    <img src="static/img/Z3.png" alt="微信支付">
                                    <i id="wxpay_i" class="wxpay_i"></i>
                                </div>
                                <div class="payTool-descr clearfix"></div>
                            </li>
                        </ul>
                     </div>

                </div>
            <ul class="payBtn clearfix">
                <li>
                    <button class="confirmPay pull-right" name="msg_payBtn" id="confirmPay" data-url="orderServlet?mod=htmlPayButton&itemid=${requestScope.ITEM_ARRAY }&order_no=${requestScope.ORDER.orderNo }&order_price=${requestScope.ORDER.payment }&order_name=${requestScope.ORDER.product.name }&order_id=${requestScope.ORDER.id}">确认支付</button>
                    <div id="cashCoupon">

                        </div>
                </li>
                <p class="disabled-btn-info clearfix" name="msg_selAlert">请在当前页面先选择支付方式</p>
            </ul>
        </div>
    </div>




    <meta charset="utf-8">


<footer>
    <div class="warp clearfix" id="vmallFoot" style="padding: 21px 0 27px;">
        <div class="vmall_logo_01 pull-left">
            <img src="https://res.vmallres.com/ips/pc/20190829/img/Vmall.png?20190829" alt="vmall_logo_01">
        </div>
        <div class="certification_info pull-left">
            <p style="margin-top: 16px;">
                <a title="隐私声明" target="_blank" href="https://www.vmall.com/help/faq-2635.html">隐私声明</a>
                <a target="_blank" href="https://www.vmall.com/help/faq-778.html">服务协议</a><span>Copyright © 2012-2019 华为终端有限公司 粤ICP备19015064号-4 版权所有 保留一切权利</span>
            </p>
        </div>
    </div>
    <div class="warp clearfix" id="pmallFoot" style="padding: 20px 0px; text-align: right; color: rgb(180, 180, 180); display: none;">
        Copyright © 2012-2019 华为终端有限公司 粤ICP备19015064号-4 版权所有 保留一切权利
    </div>
    <div id="standardFoot" style="display: none;">
    </div>
</footer>

</div>
</body>
<script type="text/javascript" src="static/js/pay.js"></script>
<script type="text/javascript">
	var day2 = new Date();
	day2.setTime(day2.getTime());
	var s2 = day2.getFullYear()+"-" + (day2.getMonth()+1) + "-" + day2.getDate();
	document.getElementById('time_date').innerText = s2;
</script>
</html>
