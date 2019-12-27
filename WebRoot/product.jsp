<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="product.html">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>价格_参数_图片_怎么样 - 华为商城</title>
        <link rel="stylesheet" href="static/css/ec.core.base.min.css">
        <link rel="stylesheet" href="static/css/main.min.css">
        <link rel="stylesheet" type="text/css" href="static/css/main.min1.css">
        <link rel="shortcut icon" href="static/image/favicon.ico">
        <link rel="stylesheet" type="text/css" href="static/css/product.css">
        <link rel="shortcut icon" href="static/image/favicon.ico">
        <script src="static/js/jquery-2.2.1.js"></script>
        <link rel="author" href="humans.txt">
        <style type="text/css">
.main{
	width: 80%;
}
.btn {
	padding: 9px 18px;
	background: #40AFFE;
	color: #FFFFFF;
	border-radius: 5px;
}

.upimg {
	position: relative;
	width: 100px;
	height: 100px;
	border-radius: 5px;
	border: dashed #999999;
	background: url(img/addimg.svg) no-repeat;
	background-position: 33px;
}

.upimg input {
	position: absolute;
	width: 100px;
	height: 100px;
	opacity: 0;
}

#showui {
	display: flex;
	justify-content: flex-start;
}

#showui li {
	width: 150px;
	height: 150px;
	position: relative;
	overflow: hidden;
	display: inline-block;
	margin-right: 5px;
}

#showui li img.showimg {
	position: absolute;
	text-align: center;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: 6;
}

.showdiv {
	position: absolute;
	z-index: 9;
	bottom: 0;
	width: calc(100% - 20px);
	padding: 10px;
	display: flex;
	justify-content: space-around;
	background: rgba(0, 0, 0, .6);
}

.showdiv img {
	width: 20px;
	height: 20px;
	cursor: pointer;
}

#showui li:first-child img.left {
	opacity: .6;
	cursor: no-drop;
}

#showui li:last-child img.right {
	opacity: .6;
	cursor: no-drop;
}

.oneright {
	opacity: .6;
	cursor: no-drop !important;
}
        </style>
    </head>
    <body>
    <%@include file="head.jsp"  %>
        <div class="product clearfix">
            <div class="left">
                <!-- 商品图片-start -->
                <div class="product-gallery positionStatic" style="top: 0px;">
                    <div class="relative">
                        <div class="product-gallery-img" id="pic-container">
                            <div id="wrap" style="position:relative;"><a id="product-img" href="static/images/800_800_C6DA586440C023DCF11C23E1AC92AC417D372B89BA7C48BE.png" class="cloud-zoom" rel="adjustX: 10, adjustY:0, zoomWidth:400, zoomHeight:400" style="position: relative; display: block;">
                              <img id="main_img" src="" alt="荣耀Play3" style="display: block;">
                        </a>
                        <div class="mousetrap" style="background-image: none; z-index: 999; position: absolute; width: 450px; height: 450px; left: 0px; top: 0px; cursor: move;"></div>
                            </div>
                        </div><a id="product-img" href="#" class="cloud-zoom" rel="adjustX: 10, adjustY:0, zoomWidth:400, zoomHeight:400">
                            </a><a class="product-gallery-open" id="gallery-video-start">开始</a>
                            <div id="video-container" class="product-gallery-video" style="display: none;">
                                <a class="product-gallery-close"  id="gallery-video-close" style="display: none;">关闭</a>
                            <div class="vcp-player" style="width: 450px; height: 450px;"><div class="vcp-error-tips"></div><div class="vcp-loading" style="display: none;"><div class="vcp-spinner" role="progressbar" style="position: absolute; width: 0px; z-index: 2000000000; left: 50%; top: 50%;"><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-0-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(0deg) translate(16px, 0px); border-radius: 2px;"></div></div><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-1-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(32deg) translate(16px, 0px); border-radius: 2px;"></div></div><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-2-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(65deg) translate(16px, 0px); border-radius: 2px;"></div></div><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-3-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(98deg) translate(16px, 0px); border-radius: 2px;"></div></div><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-4-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(130deg) translate(16px, 0px); border-radius: 2px;"></div></div><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-5-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(163deg) translate(16px, 0px); border-radius: 2px;"></div></div><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-6-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(196deg) translate(16px, 0px); border-radius: 2px;"></div></div><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-7-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(229deg) translate(16px, 0px); border-radius: 2px;"></div></div><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-8-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(261deg) translate(16px, 0px); border-radius: 2px;"></div></div><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-9-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(294deg) translate(16px, 0px); border-radius: 2px;"></div></div><div style="position: absolute; top: -2px; transform: translate3d(0px, 0px, 0px); opacity: 0.25; animation: 1s linear 0s infinite normal none running opacity-60-25-10-11;"><div style="position: absolute; width: 16px; height: 4px; background: rgb(255, 255, 255); box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 1px; transform-origin: left center; transform: rotate(327deg) translate(16px, 0px); border-radius: 2px;"></div></div></div></div><video preload="auto" webkit-playsinline="" playsinline="" x-webkit-airplay="allow" src="static/video/f20.mp4" style="width: 450px; height: 450px;"></video><div class="vcp-poster" style="display: none;"><img class="vcp-poster-pic default"></div><div class="vcp-bigplay"></div><div class="vcp-controls-panel"><div class="vcp-panel-bg"></div><div class="vcp-playtoggle"></div><div class="vcp-timeline"><div class="vcp-slider"><div class="vcp-slider-track" style="width: 100%;"></div><div class="vcp-slider-thumb" style="left: 0%;"></div></div></div><div class="vcp-fullscreen-toggle"></div><div class="vcp-volume"><div class="vcp-volume-bg"></div><div class="vcp-slider-vertical"><div class="vcp-slider-track" style="height: 50%;"></div><div class="vcp-slider-thumb" style="top: 50%;"></div></div><span class="vcp-volume-icon"></span></div></div></div></div>
                    </div>
                    <div class="product-gallery-nav">
                        <a href="" class="product-gallery-back" ></a><!--不可点击添加class="disabled"-->
                        <div class="product-gallery-thumbs">
                            <ul id="pro-gallerys" style="left: 0px;">
                                <li class="">
                                    <a href="#">
                                        <img id="min_img1" src="static/images/78_78_709C777667BF93FFA01B66DD282EB264D820B72A5F4DBC5Dmp.png" alt="荣耀Play3 6.39英寸魅眼全视屏 4000mAh大电池 真4800万AI三摄 麒麟710F自研芯片 全网通4GB+64GB （极光蓝）">
                                    </a>
                                </li>
                                <li class="current">
                                <a href="#">
                                <img id="min_img2" src="static/images/78_78_C6DA586440C023DCF11C23E1AC92AC417D372B89BA7C48BE.png" alt="荣耀Play3 6.39英寸魅眼全视屏 4000mAh大电池 真4800万AI三摄 麒麟710F自研芯片 全网通4GB+64GB （极光蓝）"></a></li><li class=""><a href=""><img id="min_img3" src="static/images/78_78_17198282504C7E85BDEAFF84BADFE868B758E81907128F97.png" alt="荣耀Play3 6.39英寸魅眼全视屏 4000mAh大电池 真4800万AI三摄 麒麟710F自研芯片 全网通4GB+64GB （极光蓝）"></a></li><li class=""><a href=""><img id="min_img4" src="static/images/78_78_83338F14FC73D768D03A8FE34D8C321F94800A95B1D93333.png" alt="荣耀Play3 6.39英寸魅眼全视屏 4000mAh大电池 真4800万AI三摄 麒麟710F自研芯片 全网通4GB+64GB （极光蓝）"></a></li><li class=""><a href=""><img id="min_img5" src="static/images/78_78_4DB08772E27C3E52770085807FBB95D7B415586DC3098D1D.png" alt="荣耀Play3 6.39英寸魅眼全视屏 4000mAh大电池 真4800万AI三摄 麒麟710F自研芯片 全网通4GB+64GB （极光蓝）"></a></li></ul>
                        </div>
                        <a href="" class="product-gallery-forward"></a>
                    </div>
                </div>
            <!-- 20170518-商品图片-end -->
            </div>
            <div class="right relative">
                <div id="pro-add-success-mask" style="visibility:hidden;font-size:18px;width:348px;position: absolute;"></div>
                <div id="cart-tips" class="pro-popup-area hide">

                <div class="b">
                    <div class="pro-add-success">
                        <dl>
                        <dt><s></s></dt>
                            <dd>
                                <div class="box-right-2">

                                    <p>荣耀Play3</p>
                                </div>
                                <div class="box-button">
                                    <a class="box-cancel" href="" onclick="$('#cart-tips').hide()">再逛逛</a>
                                    <a href="" class="box-ok" >去结算</a>
                                </div>
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>


        <!--弹出层-提示信息 -->
        <div id="popup-tips" class="pro-popup-area hide">
            <div class="h">
            <a href="" class="pro-popup-close" title="关闭" onclick="$('#popup-tips').hide()"><span>关闭</span></a>
            </div>

            <div class="b">
                <div class="pro-add-error">
                    <div class="pro-add-error-msg" id="popup-tips-msg">非常抱歉！该商品不能单独销售！</div>
                    <div class="pro-add-error-button"><a class="button-style-1 button-par-define" href="" onclick="$('#popup-tips').hide()" title="知道了">知道了</a></div>
                </div>
            </div>
        </div>

                <!-- 20170518-商品简介-start -->
                <div class="product-property clearfix relative" style="min-height: 462.617px; height: auto; padding-bottom: 103.383px;">
                    <div id="product-property-recommand">
                    <!-- 20170518-商品简介-商品信息-start -->
                    <div class="product-meta">
                        <h1 id="pro-name">荣耀Play3 6.39英寸魅眼全视屏 4000mAh大电池 真4800万AI三摄 麒麟710F自研芯片 全网通4GB+64GB （极光蓝）</h1>
                        <input class="hide" value="10086315545613" id="product_sku">
                        <input class="hide" value="10086693893548" id="product_productId">
                        <div class="product-slogan" id="skuPromWord" style="display: block;"><a href="" class="product-slogan-btn" style="display: none;"></a> <span class="product-slogan-link">【价保11.11】①V1-V5会员赠耳机②晒单限量赠数据线③赢豪华迪拜游！</span></div>
                    </div>

                    <div class="product-info">
                        <div class="product-info-list clearfix">
                        <label>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</label>
                            <div class="product-price clearfix">
                                <div class="product-price-info">
                                      <span id="pro-price-label" class="f24" style="display: none;"></span>
                                      <span id="pro-price" class="f24"><em>¥</em>999.00</span>
                                      <input type="hidden" id="pro-price-hide" value="999.00">
                                      <s id="pro-price-old" style="display: none;"></s>
                                      <b id="pro-price-label-deposit" style=""></b>
                                      <span id="pro-price-deposit" style=""></span>
                                      <b id="pro-price-label-amount" style="display:none">可抵</b>
                                      <span id="pro-price-amount" style="display:none"></span>
                                </div>
                            </div>
                        </div>


                        <div class="product-info-list clearfix hide" id="couponBtn" style="display: none;">
                            <label>优&nbsp;&nbsp;惠&nbsp;&nbsp;券</label>
                            <div class="clearfix" id="couponTag">

                            </div>
                        </div>



                        <!-- 20170518-商品简介促销消息-start -->
                        <div class="product-info-list clearfix " id="product-info-list-new" 2="">
                            <label>促&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;销</label>
                            <div class="product-prom fl show" id="product-prom-all">

                                <div class="product-prom-item clearfix product-parts" id="pro-match-gift" style="display: none;">
                                    <em class="tag">赠品</em>
                                    <div class="product-prom-con">
                                        <div class="product-parts-list" id="pro-gift-list-select"></div>
                                        <a href="" class="product-partscolor" onclick="ec.product.giftshow()">去选择&nbsp;&gt;</a>
                                    </div>
                                </div>



                            <div class="product-prom-item clearfix" style=""><em class="tag">商品赠券</em><div class="product-prom-con" title="购机送华住200元酒店礼包"> <span class="product-prom-word">购机送华住200元酒店礼包 </span></div></div> <div class="product-prom-item clearfix" style=""><em class="tag">赠送积分</em><div class="product-prom-con">购买即赠商城积分，积分可抵现~</div></div></div>
                        </div>
                         <!-- 20170518-商品简介促销消息-end -->
                    </div>

                    <!-- 20180206-商品简介-end -->
                    <div class="hr-20"></div>

                    <!-- 20171024-商品简介-商品编码-start -->
                    <div class="product-description clearfix">
                        <label>商品编码</label>
                        <div class="fl" id="pro-sku-code2">2601010131102</div>
                    </div>
                    <!-- 20171024-商品简介-商品编码-end -->
                    <div class="hr-5"></div>
                    <div class="line"></div>
                    <div class="hr-16"></div>


                    <div id="pro-skus" class=""><dl class="product-choose clearfix  product-choosepic"><label>选择颜色</label><div class="product-choose-detail "><ul><li class="attr1 attr10 attr19" data-attrname="颜色" data-attrcode="152138" data-attrid="1,10,19" data-skuid="10086274621371,10086902545772,10086610873333"><div class="sku"><a id="pro_color1" href="javascript:void(0);" onclick="pro_color(this,1)" title="幻夜黑"><img src="static/images/40_40_FFF52FC8300511223A30ED8401D76D30BCBDBC5A3F75596Amp.png" alt="幻夜黑"><p><span>幻夜黑</span></p></a></div></li><li class="attr4 attr13 attr22 selected" data-attrname="颜色" data-attrcode="152138" data-attrid="4,13,22" data-skuid="10086315545613,10086579574597,10086247510639"><div class="sku"><a id="pro_color2" href="javascript:void(0);" onclick="pro_color(this,2)" title="极光蓝"><img src="static/images/40_40_709C777667BF93FFA01B66DD282EB264D820B72A5F4DBC5Dmp.png" alt="极光蓝"><p><span>极光蓝</span></p></a></div></li>
                    <li class="attr7 attr16 attr25" data-attrname="颜色" data-attrcode="152138" data-attrid="7,16,25" data-skuid="10086007364766,10086635418330,10086166663960"><div class="sku"><a id="pro_color3" href="javascript:void(0);" onclick="pro_color(this,3)" title="魅焰红"><img src="static/images/40_40_BEB780C19A72824F127F1F7ECE2E7B6BD49D05EBC822E042mp.png" alt="魅焰红"><p><span>魅焰红</span></p></a></div></li></ul></div></dl><dl class="product-choose clearfix "><label>选择版本</label><div class="product-choose-detail "><ul>
                    <li class="attr2 attr5 attr8 selected" data-attrname="版本" data-attrcode="733605" data-attrid="2,5,8" data-skuid="10086274621371,10086315545613,10086007364766"><div class="sku"><a id="pro_type1" href="javascript:void(0);" onclick="pro_type(this,1)" title="全网通 4GB+64GB"><p><span>全网通 4GB+64GB</span></p></a></div></li><li class="attr11 attr14 attr17" data-attrname="版本" data-attrcode="733605" data-attrid="11,14,17" data-skuid="10086902545772,10086579574597,10086635418330"><div class="sku"><a id="pro_type2" href="javascript:void(0);" onclick="pro_type(this,2)" title="全网通 6GB+64GB"><p><span>全网通 6GB+64GB</span></p></a></div></li><li class="attr20 attr23 attr26" data-attrname="版本" data-attrcode="733605" data-attrid="20,23,26" data-skuid="10086610873333,10086247510639,10086166663960"><div class="sku"><a id="pro_type3" href="javascript:void(0);" onclick="pro_type(this,3)" title="全网通 4GB+128GB"><p><span>全网通 4GB+128GB</span></p></a></div></li></ul></div></dl><dl class="product-choose clearfix hide" id="bundlePackage"></dl><dl class="product-choose clearfix" id="package_dl"></dl></div>
                    <!--联通合约机套餐-->
                    <div id="contractLst" class="hide">
                        <dl class="product-choose clearfix">
                            <label>合约机</label>
                            <div class="product-choose-detail">
                                <ul id="contractList-ol">
                                </ul>
                            </div>
                        </dl>
                        <form action="/contract/choose-{id}" id="contractForm" class="hide">
                            <input type="hidden" value="" id="gifts" name="gifts">
                        <input name="CsrfToken" type="hidden" value="149A74C53D4A417EDE841DA7A577288472E53D5A79B5DE5B"></form>
                    </div>

                    <!-- 20170518-商品简介-保障服务-start -->
                    <div class="clearfix hide" id="pro-service" style="display: block;">

                    </div>
                    <!-- 20170518-商品简介-保障服务-end -->

                    <input type="hidden" value="" id="interestOrderNow">
                    <!-- 20181026-商品简介-分期免息-start -->
                    <div class="product-choose clearfix hide" id="prd-noInterset" interest-allow="1" interest-button="">
                        <label>分期免息</label>
                        <div class="product-choose-detail relative product-choose-pulldown">
                                <ul>
                                    <li class="" id="hbShow"><!-- 选中li添加class="selected" 点击打开套餐添加class="click"-->
                                        <div class="sku">
                                            <a class="product-pulldown-btn" href="">
                                                <p><span>花呗分期</span></p>
                                            </a>
                                        </div>

                                         <div class="product-stages product-package-mini" id="hbDetail">
                                                <div class="product-stages-con">
                                                    <div class="product-stages-main">
                                                        <ul class="clearfix"></ul>
                                                    </div>
                                                    <div class="tips">
                                                        <h2>花呗分期是什么？</h2>
                                                        <p>花呗分期是蚂蚁金服（支付宝公司）提供的先消费后分期还款的赊购服务。</p>
                                                        <p style="color: #666;margin-top: 12px;">免息活动适用于单款免息商品订单，含多款商品订单仅在免息活动一致时可<br>享用。</p>
                                                    </div>
                                                </div>
                                                <div class="product-package-mini-tool clearfix">
                                                    <div class="fr">
                                                        <a id="interestHBPayNow" href="" class="product-package-mini-btn product-button02 disabled" onclick="ec.product.interest.payByInterest(1);" interest-info=""><span>分期购买<span></span></span></a>
                                                    </div>
                                                </div>
                                            </div>

                                    </li>
                                    <li id="hlShow">
                                        <div class="sku">
                                            <a class="product-pulldown-btn" href="">
                                                <p><span>掌上生活分期</span></p>
                                            </a>
                                        </div>

                                       <div class="product-stages product-package-mini " id="hlDetail">
                                            <div class="product-stages-con">
                                                <div class="product-stages-main">
                                                    <ul class="clearfix"></ul>
                                                </div>
                                                <div class="tips">
                                                    <h2>掌上生活分期是什么？</h2>
                                                    <p>掌上生活分期是招商银行信用卡中心提供的消费分期服务。</p>
                                                    <p style="color: #666;margin-top: 12px;">免息活动适用于单款免息商品订单，含多款商品订单仅在免息活动一致时可<br>享用。</p>
                                                </div>
                                            </div>
                                            <div class="product-package-mini-tool clearfix">
                                                <div class="fr">
                                                    <a id="interestPayHLNow" href="" class="product-package-mini-btn product-button02 disabled" onclick="ec.product.interest.payByInterest(2);" interest-info=""><span>分期购买<span></span></span></a>
                                                </div>
                                            </div>
                                        </div>

                                    </li>
                                </ul>






                        </div>
                    </div>
                    <!-- 20181026-商品简介-分期免息-end -->


                    <!-- 20170518-商品简介-物流现货-start -->
                    <div class="product-pulldown clearfix hide">
                        <label>物流售后</label>
                        <div class="product-pulldown-main relative"><!--鼠标悬浮按钮的图标span选择后 class="product-pulldown-main relative" 改为 class="product-pulldown-main product-pulldown-main-show relative"-->
                            <a href="#" class="product-pulldown-btn">江苏省&gt;南京市&gt;玄武区<span></span></a>
                            <div class="product-pulldown-detail">
                                <div class="product-pulldown-detailmain">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 20170518-商品简介-物流现货-end -->

                    <!-- 推荐-start -->
                    <!-- 推荐-end -->

                    <div class="product-operation-main product-operation-location" style="min-height: 49px;">
                        <!-- 20170518-商品简介-已选择商品-start -->
                        <div class="product-description clearfix">
                            <label>已选择商品:</label>
                            <div id="pro-select-sku" class="product-selected">极光蓝 / 全网通 4GB+64GB / 官方标配 </div>
                        </div>
                        <!-- 20170518-商品简介-已选择商品-end -->

                        <!-- 20170518-商品简介-提示-end -->
                        <div id="product-tips" class="product-tips hide" style="display: none;"></div>
                         <!-- 20170518-商品简介-提示-end -->

                        <!-- 20170518-商品简介-提交操作-start -->
                        <div id="product-operation" class="product-operation relative" style="">
                            <div class="clearfix">
                                <!-- 20170518-商品简介-购买数量-start -->
                                <div class="product-stock" id="pro-quantity-area">
                                    <input id="pro-quantity" type="text" class="product-stock-text" placeholder="1" value="1"><p class="product-stock-btn"><a id="pro-quantity-plus" href="javascript:void(0);">+</a><a id="pro-quantity-minus" href="javascript:void(0);" class="disabled">−</a></p>
                                </div>
                                <div class="product-stock hide" id="pro-quantity-area-nochange" style="display: none;">
                                    <input type="text" class="product-stock-text" placeholder="1" value="1" disabled="disabled">
                                    <p class="product-stock-btn"><a href="" class="disabled">+</a><a href="" class="disabled">−</a></p>
                                </div>
                                <!-- 20170518-商品简介-购买数量-end -->


                                <div class="product-buttonmain">
                                    <!-- 20170518-商品简介-按钮-start -->
                                    <div id="pro-operation" class="product-button clearfix" style="visibility: visible;"><a href="javascript:void(0);" onclick="addcart()" class="product-button01"><span>加入购物车</span></a><a href="javascript:void(0);" id="product_buy"  class="product-button02"><span>立即下单</span></a></div>
                                    <!-- 20170518-商品简介-按钮-end -->
                                    <!-- 20170518-商品简介-协议-start -->
                                    <div class="product-agreement hide" id="product-deposit-agreement-area" style="display: none;">
                                        <input type="checkbox" id="pro-agreement-area-check" checked="">
                                        <span>同意<a href="" onclick="ec.product.showDepositAgreement()">订金支付协议</a></span>
                                    </div>
                                    <!-- 20170518-商品简介-协议-end -->
                                    <!-- 20180212-商品简介-游客购买-start -->

                                    <!-- 20180212-商品简介-游客购买-end -->
                                </div>
                            </div>

                            <div class="product-tips02 " id="goAddressId" style="display:none">
                                    <lable>温馨提示</lable>
                                    <p>
                                        提前设置<a href="/member/myAddress?t=453453454353" target="_blank" onclick="ec.product.pushAddress()">默认收货地址</a>，购买更顺利~
                                    </p>
                            </div>
                        </div>

                        <div class="product-deposit clearfix" id="buyProcessIDD" style="display:none">
                            <h2>购买流程</h2>
                            <ul>
                                <li>1.&nbsp;&nbsp;&nbsp;支付订金（<span id="startDateIDD">提前锁定购买资格</span>）</li>
                                <li>2.&nbsp;&nbsp;&nbsp;支付尾款（<span id="balanceStartDateIDD">暂无</span>）</li>
                                <li>3.&nbsp;&nbsp;&nbsp;等待发货（按支付尾款时间顺序发货）</li>
                            </ul>
                        </div>
                        <div class="gallery-scroll-bottom"></div>
                    </div>
                    <!-- 20170518-商品简介-提交操作-end -->
                    <!-- 20170518-商品简介-按钮-end -->
                    </div>
                </div>
                <!-- 20170518-商品简介-属性-end -->
            </div>
        </div>
        <div class="hr-20"></div>
        <div class="line"></div>
        <!-- 商品详情 -->
        <div class="product-tab" id="product-tab" style="position: static; top: 0px; background: rgb(255, 255, 255); width: 100%; z-index: 100;">
        <!--移到顶部的时候添加class="product-tab-top"-->
            <div class="layout relative" id="layoutRelative">
                <p>
                    <a href="#" id="pro-tab-feature" class="selected">商品详情<em></em></a>
                    <!--选中 a 添加 class="selected"-->
                    <a href="#" id="pro-tab-parameter">规格参数<em></em></a>
                    <a href="#" id="pro-tab-package-service" style="">包装与售后<em></em></a>
                    <a href="#" id="pro-tab-evaluate">用户评价<em></em><span>(6431)</span></a>
                </p>
                <div class="product-tab-btn" style="display: none;">
                <a href="#" id="tab-addcart-button" class="product-button02" style="">加入购物车</a>
                <a href="#" id="tab-notice-button" class="product-button02" style="display: none;">到货通知</a>
                </div>
            </div>
        </div>
        <!-- 20170519-商品详情-start -->
        <div id="pro-tab-feature-content" class="product-detail relative tit1">
            <div id="pro-detail-contents">
                        <div id="pro-detail-content-10086315545613" class="pro-detail-see">
                            <div id="kindPicture">
                                
                            </div>
                            <div id="detail-content-button-10086315545613" class="product-shade hide">
                                <p>
                                    <a id="pro-detail-down-btn-10086315545613" class="product-detail-btn">
                                        查看全部产品详情
                                    </a>
                                </p>
                            </div>
                            <a href="javascript:;" id="pro-detail-up-btn-10086315545613" class="product-detail-btnup hide">收起全部产品详情</a>
                        </div>

                <div class="hide">正在加载...</div>
                <!--<div id="detail-content-button" class="product-shade hide">
                    <p>
                    <a id="pro-detail-down-btn" href="javascript:;" class="product-detail-btn" >
                        查看全部产品详情
                    </a>
                    </p>
                </div>
                <a id="pro-detail-up-btn" href="javascript:;" class="product-detail-btnup hide">收起全部产品详情</a>
                -->
            </div>
        </div>
        <div id="hr60Detail" class="hr-60"></div>

        <!-- 20170519-商品详情-end -->
        <!-- 规格参数-start -->
        <div id="productParameter" class="product-parameter tit2">
            <div class="layout" id="product-parameter-main">
                <h2 id="productParameterId" class="product-title">规格参数</h2>
                <div id="productParameterHr30" class="hr-30"></div>
                <div id="parameterDetailTips" class="hide">正在加载...</div>
                <!--<ul class="product-parameter-main  clearfix"></ul>-->
            </div>

            <div id="productDetailParameter" class="product-parameter-detail relative bg-gray">
            </div>
        </div>


        <!--规格参数-end -->

        <!--评价-start -->
        <div class="bg-gray tit4">
            <div id="pro-tab-evaluate-content" class="layout">
                <div class="product-score clearfix">
                    <div class="product-score-average">
                        <label>好评度</label>
                        <p id="pro-evaluate-avgSorce">100<em>%</em></p>
                    </div>
                    <div class="product-score-impress">
                        <label>买家印象</label>
                        <div class="product-score-impress-info">
                            </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layout">
            <div class="product-comment-tab clearfix">
                <ul class="clearfix">
                    <li class="current"><a href="javascript:;" extratype="1">全部</a></li><!--选中 li 添加 class="current"-->
                 </ul>
            </div>
            <div class="product-comment">
                <ul class="product-comment-list" id="product-comment-list">
                    <li class="clearfix">
                    	<div class="product-comment-user">
                    		<p class="product-comment-user-img">
                    			<img src="static/images/defaultface_user_after.png" alt="">
                    				<i class="icon-vip-level-0"></i>
                    		</p>
                    		<p class="product-comment-user-name">189****7460</p>
                    	</div>
                    	<div class="product-user-comment">
                    		<div class="product-user-comment-detial">
                    			<div class="h clearfix">
                    				<div class="product-star">
                    					<span class="starRating-area">
                    						<s style="width:100%"></s>
                    					</span>
                    				</div>
                    				<div class="product-user-comment-impress">
                    				<dl></dl>
                    			</div>
                    			<div class="fr">
                    				<div class="product-user-comment-time">2019-10-22 00:23:34</div>
                    				</div>
                    			</div>
                    				<div class="product-user-comment-word" id="10086693893548-83377414382846">妈妈很喜欢，算是儿子的小小心意吧，以后挣更多钱，给妈妈买更好的</div>
                    					<div class="user-comment-img">
                    						<p class="img-small clearfix">
                    							<a href="#" class="img-wrap">
                    								<img id="83377414383847" image-large="static/images/71a84f43ed85624bd8d45392.jpg" src="static/images/71a84f43ed85624bd8d45392.jpg"></a>
                    							</p>
                    						<div class="clearfix">
                        </div>
                        </div>
                        </div><div class="product-reply">
                        <div class="product-reply-detail" replycount="1">
                        <div class="product-admin-reply clearfix">
                        <div class="admin-user-img">
                        <img src="static/images/defaultface_customer_service.png"></div>
                        <dl>
                        <dt class="red">华为商城客服</dt>
                        <dd>客服掐指一算，您母亲在收到宝贝时，眼睛肯定是笑得眯成了一条线呐O(n_n)O哈哈~</dd>
                        </dl>
                        </div>
                        </div>
                        </div>
                        </div>
                    </li>
                    
                </ul>
                
                
            </div>
            <!-- 评论 -->
            <div class="product-inquire-edit" id="myConsultationForm">
	            <form autocomplete="off" id="counsel_content_form">
	                <p class="product-inquire-edit-tab">
	                    <label>评价：</label>
	                    <a href="javascript:;" class="selected" onclick="ec.product.switched(1,this)"><em></em>商品评价</a><!--选中 a 添加 class="selected"-->
	                </p>
	                <!-- 图片上传 -->
	                <label style="   display: block;width: 300px;color: red;font-size: 18px;">评价图片(图片文件名不可包含中文)</label>
 					<div class="main">
						<div id="showimg">
							<ul id="showui">
							</ul>
							<div id="showinput">
						
							</div>
						</div>
						<div class="upimg">
							<input type="file" id="upgteimg" value="" multiple />
						</div>
						
						</div>
	                <input type="hidden" name="type" value="1">
	                <textarea placeholder="请详细描述您的情况，我们将热情为您服务" class="textarea" name="question" id="counseltextid" maxlength="100"></textarea>
	                <div class="clearfix">
	                     <input class="product-button02" id="submit_button" value="提交评论" type="button" >
	                    <span class="product-inquire-edit-tips" id="error-span" style="display:none"></span>
	                </div>
	                
						
						
	            </form>
	        </div>
        </div>
        <!--评价-end -->
        <div id="fade" class="fade" style="display: none"></div>
		<div id="succ-pop" class="succ-pop" style="display: none">
		    <h3 class="title" id="h3_title">
		        
		    </h3>
		    <p id="button_p">
		    	<a id="cancel" class="box-cancel">再逛逛</a>
		         <a id="box-ok" href="javascript:;" class="box-ok">去结算</a>
		    </p>
		</div>
        <%@include file="font.jsp"  %>
        
    </body>

<script type="text/javascript" src="js/updateimg.js"></script>
     <script src="static/js/product.js"></script>
    <!-- 两个页面之间传值需要的js -->
    <script type="text/javascript" >
    UrlParam = function() { // url参数
　　var data, index;
　　(function init() {
　　　　data = []; //值，如[["1","2"],["zhangsan"],["lisi"]]
　　　　index = {}; //键:索引，如{a:0,b:1,c:2}
　　　　var u = window.location.search.substr(1);
　　　　if (u != '') {
　　　　　　var params = decodeURIComponent(u).split('&');
　　　　　　for (var i = 0, len = params.length; i < len; i++) {
　　　　　　　　if (params[i] != '') {
　　　　　　　　　　var p = params[i].split("=");
　　　　　　　　　　if (p.length == 1 || (p.length == 2 && p[1] == '')) {// p | p= | =
　　　　　　　　　　　　data.push(['']);
　　　　　　　　　　　　index[p[0]] = data.length - 1;
　　　　　　　　　　} else if (typeof(p[0]) == 'undefined' || p[0] == '') { // =c 舍弃
　　　　　　　　　　　　continue;
　　　　　　　　　　} else if (typeof(index[p[0]]) == 'undefined') { // c=aaa
　　　　　　　　　　　　data.push([p[1]]);
　　　　　　　　　　　　index[p[0]] = data.length - 1;
　　　　　　　　　　} else {// c=aaa
　　　　　　　　　　　　data[index[p[0]]].push(p[1]);
　　　　　　　　　　}
　　　　　　　　}
　　　　　　}
　　　　}
　　})();
　　return {
 　　　　// 获得参数,类似request.getParameter()
　　　　param : function(o) { // o: 参数名或者参数次序
　　　　　　try {
　　　　　　　　return (typeof(o) == 'number' ? data[o][0] : data[index[o]][0]);
　　　　　　} catch (e) {
　　　　　　}
　　　　},
　　　　//获得参数组, 类似request.getParameterValues()
　　　　paramValues : function(o) { // o: 参数名或者参数次序
　　　　　　try {
　　　　　　　　return (typeof(o) == 'number' ? data[o] : data[index[o]]);
　　　　　　} catch (e) {}
　　　　},
　　　　//是否含有paramName参数
　　　　hasParam : function(paramName) {
　　　　　　return typeof(paramName) == 'string' ? typeof(index[paramName]) != 'undefined' : false;
　　　　},
　　　　// 获得参数Map ,类似request.getParameterMap()
　　　　paramMap : function() {
　　　　　　var map = {};
　　　　　　try {
　　　　　　　　for (var p in index) { map[p] = data[index[p]]; }
　　　　　　} catch (e) {}
　　　　　　return map;
　　　　}
  　　}
}();

function showImg(){
            // var img_file =  $("#img_file").val();
            var file =  document.getElementById('img_file').files[0];
            var re = new FileReader();
            re.readAsDataURL(file);
            re.onload = function(re){
                $('#img_id').attr("src", re.target.result);
            }
        }
 
    </script>
</html>
