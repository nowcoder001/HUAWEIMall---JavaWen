<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>下单界面</title>
    <link rel="stylesheet" href="static/css/xiadan.css">
    <link rel="stylesheet" href="static/css/xiadan2.css">
    <link rel="shortcut icon" href="static/image/favicon.ico">
    <script src="static/js/jquery-2.2.1.js"></script>
    <style type="text/css">
    	.order-submit .order-submit-btn {
		    float: right;
		    margin-top: 20px;
		    text-align: center;
		    background: #ca141d;
		    color: #fff;
		    width: 172px;
		    height: 46px;
		    line-height: 46px;
		    font-size: 18px;
		    border-radius: 2px;
		}
		.fade{
		    width:100%;
		    height:100%;
		    background:rgba(0, 0, 0, 0.5);
		    position: fixed;
		    left: 0;
		    top: 0;
		    z-index: 99;
		}
		/*弹出层*/
		.succ-pop{
		    width: 940px;
		    height: 580px;
		    background: #fff;
		    position: fixed;
		    left: 50%;
		    top: 50%;
		    margin-left: -460px;
		    margin-top: -300px;
		    z-index: 999;
		    border-radius: 5px;
		}
		.succ-pop h3.title{
		    text-align: center;
		    font-size: 22px;
		    color: #ce002c;
		    margin: 50px;
		}
		.box-ok {
		    display: inline-block;
		    min-width: 170px;
		    height: 44px;
		    line-height: 44px;
		    background: #ca141d;
		    border: 1px solid #ca141d;
		    font-size: 18px;
		    color: #fff;
		    margin: 0 5px;
		    white-space: nowrap;
		    border-radius: 2px;
		}
		.box-cancel {
		    display: inline-block;
		    min-width: 170px;
		    height: 44px;
		    line-height: 44px;
		    border: 1px solid #ddd;
		    background: #fff;
		    font-size: 18px;
		    margin: 0 5px;
		    white-space: nowrap;
		    border-radius: 2px;
		}
		.succ-pop p{
			text-align: center;
		    font-size: 22px;
		    color: #ce002c;
		}
    </style>
</head>
<body class="wide order">
<div class="shortcut">
            <div class="layout">
                <!-- 顶部左边 -->
                <div class="s-sub">
                    <ul>
                        <li><a href="#" >首页</a></li>
                        <li><a href="#" target="_blank">华为官网</a></li>
                        <li><a href="#" target="_blank">荣耀官网</a></li>
                        <li><a href="#" target="_blank">花粉俱乐部</a></li>
                        <li><a href="#" rel="nofollow">V码(优购码)</a></li>
                        <li><a href="#" target="_blank" >企业购</a></li>
                        <li class="s-hwep hide" id="li-enterprise-preferential"></li>
                        <li><a href="javascript:;" onclick="showSelectRegion();pushHeaderMsg('Select Region','')">Select Region</a></li>
                        <li>
                            <div class="s-dropdown">
                                <div class="h" id="h" onmouseover="more()" onmouseout="moreOut()">
                                    <a class="icon-dropdown">更多精彩</a>
                                </div>
                                <div class="b" id="show1" onmouseover="more()" onmouseout="moreOut()">
                                    <div id="bb" class="dropdown-more w-119">
                                        <dl>
                                            <dt><a href="#" target="_blank">EMUI</a></dt>
                                            <dt><a href="#" target="_blank">应用市场</a></dt>
                                            <dt><a href="#" target="_blank">华为终端云空间</a></dt>
                                            <dt><a href="#" target="_blank">开发者联盟</a></dt>
                                        </dl>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="s-main ">
                    <img src="static/images/bg71.png" class="hide">
                    <ul>
                        <li id="login_reg_">
                            <div class="header-toolbar">
                                <a id="top-index-loginUrl" href="#" rel="nofollow">请登录</a>
                                <a href="#" rel="nofollow" >&nbsp;&nbsp;注册</a>
                            </div>
                        </li>
                        <!-- 登录成功后的需要显示 -->
                        <li id="login_status" class="hide" style="display: none;">
						   <div id="top_login" class="header-toolbar">
						    <div class="s-dropdown">
						     <div class="h h-wide" id="up_loginName-hover">
						      <a class="icon-dropdown" href="#" rel="nofollow" target="_blank" onclick="pushLoginMsg('已登录','用户名')"><span id="up_loginName"></span></a>&nbsp;
						     </div>
						     <div class="b">
						      <!-- 2017-06-19-个人信息-start -->
						      <div class="dropdown-i-mall">
						       <div class="i-mall-prompt clearfix">
						        <div class="user-head fl">
						         <p class="user-img"> <a href="#" rel="nofollow" timetype="timestamp" target="_blank" onclick="pushLoginMsg('已登录','头像')"> <img id="customerPic" src="https://res.vmallres.com/20191020/images/echannel/misc/img_logged_in.png" alt="默认头像" imgpath="https://res.vmallres.com/20191020/images" /> </a> </p>
						        </div>
						        <div class="user-info fl">
						         <div id="user-vip-level-tips-index" class="user-level icon-vip-level-0">
						          <em></em>
						          <p><span id="canvas-index" style="width: 0%"></span></p>
						         </div>
						         <div class="user-info-detail clearfix" id="vip-info">
						          <a id="authentication_y" class="icon-realname hide" style="display: none;">已实名</a>
						          <a id="authentication_n" href="#" rel="nofollow" class="icon-realname disabled hide" onclick="pushLoginMsg('已登录','未实名')" style="display: inline;">未实名</a>
						          <a class="icon-mail" href="#" rel="nofollow" timetype="timestamp" onclick="pushLoginMsg('已登录','消息中心')">消息(<span id="top-newMsgCount">0</span>)</a>
						         </div>
						        </div>
						       </div>
						       <div class="i-mall-uc">
						        <dl class="clearfix">
						         <dt>
						          <span class="fl">我的订单</span>
						          <a class="fr" href="#" timetype="timestamp" onclick="pushLoginMsg('已登录','更多')">更多</a>
						         </dt>
						        </dl>
						        <div class="i-mall-uc-con">
						         <dl class="clearfix">
						          <dd>
						           <a href="#" timetype="timestamp" onclick="pushLoginMsg('已登录','待支付')">待支付</a>
						          </dd>
						          <dd>
						           <a href="#" timetype="timestamp" onclick="pushLoginMsg('已登录','待收货')">待收货</a>
						          </dd>
						          <dd>
						           <a href="#" timetype="timestamp" onclick="pushLoginMsg('已登录','待评价')">待评价</a>
						          </dd>
						          <dd>
						           <a href="#" timetype="timestamp" onclick="pushLoginMsg('已登录','退换货')">退换货</a>
						          </dd>
						          <dd>
						           <a href="#" timetype="timestamp" onclick="pushLoginMsg('已登录','旧机回收')">旧机回收</a>
						          </dd>
						         </dl>
						        </div>
						       </div>
						       <div class="i-mall-huaban">
						        <ul class="clearfix">
						         <li> <a href="#" target="_blank" id="point" onclick="pushLoginMsg('已登录','积分')"> <p class="p-price"><span id="userPointBalance">5</span></p> <p class="p-dec">积分</p> </a> </li>
						         <li> <a href="#" rel="nofollow" target="_blank" onclick="pushLoginMsg('已登录','优惠券')"> <p class="p-price"><span id="top-couponCount">5</span></p> <p class="p-dec">优惠券</p> </a> </li>
						         <li> <a href="#" rel="nofollow" target="_blank" onclick="pushLoginMsg('已登录','代金券')"> <p class="p-price"><span id="balanceAmount">0.00</span></p> <p class="p-dec">代金券</p> </a> </li>
						        </ul>
						       </div>
						       <div class="i-out">
						        <a href="#" rel="nofollow">退出登录</a>
						       </div>
						      </div>
						      <!-- 2017-06-19-个人信息-end -->
                        <li class="hide"></li>
                        <li>
                            <a href="#" timetype="timestamp">我的订单</a>
                        </li>
                        <li>
                            <div class="s-dropdown s-dropdown-link">
                                <div class="h" onmouseover="more2()" onmouseout="moreOut2()">
                                    <a class="icon-dropdown" >客户服务</a>
                                </div>
                                <div class="b" onmouseover="more2()" onmouseout="moreOut2()">
                                    <div id="bbb" class="dropdown-more">
                                        <dl>
                                            <dt><a href="#" target="_blank">服务中心</a></dt>
                                            <dt><a href="#" target="_blank">联系客服</a></dt>
                                        </dl>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="s-dropdown">
                                <div class="h">
                                    <a class="icon-dropdown">网站导航</a>
                                </div>
                                <div class="b"></div>
                            </div>
                        </li>
                        <li class="downloadApp">
                            <div class="s-dropdown">
                                <div class="h">
                                    <a class="icon-dropdown">手机版</a>
                                </div>
                                <div class="b"></div>
                            </div>
                        </li>
                        <li>
                            <div class="s-dropdown s-dropdown-minicart">
                                <div class="h h-wide" onmouseover="more3()" onmouseout="moreOut3()">
                                    <a href="#" class="icon-minicart">
                                        <span>购物车(<span></span>)</span>
                                    </a>
                                </div>
                                <div class="b" id="bbbb" onmouseover="more3()" onmouseout="moreOut3()">
                                    <!-- 2017-06-19-迷你购物车-start -->
                                    <div class="dropdown-minicart">
                                        <div class="minicart-pro-empty minicart-pro-empty-index" id="minicart-pro-empty">
                                            <p><span class="icon-minicart"></span></p>
                                            <p id="cartInfo">您的购物车是空的，赶紧选购吧~</p>

                                        </div>
                                        <div class="minicart-pro-list minicart-pro-list-scroll hide" id="minicart-pro-list-block" style="display: none;">
                                            <ul id="minicart-goods-list"></ul>
                                            <div class="minicart-pro-settleup" id="minicart-pro-settleup" style="display: none;">
                                                <p>
                                                    <span>总计：</span>
                                                    <span><b id="micro-cart-totalPrice">¥&nbsp;0</b><s id="micro-cart-totalOriginPrice">¥&nbsp;0</s></span>
                                                </p>
                                                <a class="button-minicart" id="button-minicart-go2confirm" href="javascript:;" onclick="ec.minicart.confirm()" style="display: none;">结算</a>
                                                <a class="button-minicart disabled" id="disbutton-minicart-go2confirm" style="">结算</a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 2017-06-19-迷你购物车-end -->
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
<div class="">

    <div class="header order-header">
    <div class="layout">
        <div class="left">
            <!-- 2017-06-19-logo-文字-start -->
            <div class="logo logo-word">
                <a href="https://www.vmall.com/" title="华为商城"><img src="static/img/X1.png" alt="华为商城"></a>
                <span>确认订单</span>
            </div>
            <!-- 2017-06-19-logo-文字-end -->
        </div>

        <div class="right">
            <!--头部 -->
            <!-- <div class="order-header">
                <div class="fr"> -->
                        <!--步骤-两步骤 -->
                        <div class="progress-area progress-area-2">
                            <!--核对订单 -->
                            <div id="progress-confirm" class="progress-co-area hide" style="display: block;">填写核对订单信息</div>
                            <!--成功提交订单 -->
                            <div id="progress-submit" class="progress-sso-area hide">成功提交订单</div>
                        </div>
                <!-- </div>
            </div> -->
        </div>
    </div>
</div>
<div class="layout order" id="pointShowMonitor">
<div>
    <div class="hr-20" id="order-address-hr"></div>
    <div class="order-detail-area order-address" id="order-address-mod">
        <div class="h">
                        收货地址
            <a id="upAddAddressButton" href="#" class="address-add-btn" style="display: none;">新增收货地址</a>
        </div>
        <div class="order-address-list" id="order-address-list">
            <ul class="clearfix" id="shippinglist">
            <c:forEach items="${requestScope.SHIPPING_LIST}" var="shipping" varStatus="row">

            	<li id="myAddress-${row.index+1 }" index="${shipping.id }" class="current" style="border: 1px solid #eaeaea;">
            <div class="address-supplement hide">
                <a href="javascript:;" class="address-add-btn">请完善街道信息</a>
            </div>
                <span id="defaultAddress${row.index }" class="address-status" data-url="${shipping.receiverDefault}1" style="display:none;">默认地址</span>
            <div class="address-main"">
                <p class="clearfix"><b id="consignee-name-76983738">${shipping.receiverName}</b>
                    <span id="consignee-tel-76983738">${shipping.receiverMobile}</span>
                </p>
            <div class="address-detail" id="address-detail-76983738">${shipping.receiverProvince}&nbsp;${shipping.receiverCity}&nbsp;${shipping.receiverDistrict}&nbsp;${shipping.receiverAddress}</div>
            </div>
                </li>
                <c:if test="${i eq 3}">
              		<c:set var="flag" value="false"/>
       			</c:if>

            </c:forEach>

                    <li id="address-empty"><div class="address-empty"><a href="javascript:;" class="address-add-btn">新增收货地址</a>
            </div>
                </li>
            </ul>
        </div>
        <div class="f">
            <div id="address-more">
            <a href="javascript:;" class="address-expand hide" style="display: none;">查看全部地址</a><!--收缩class="address-shrink"-->
            <a href="javascript:;" class="address-shrink hide" style="display: none;">收起地址</a>
            </div>
        </div>
           <input name="orderDistrict" id="order-district" type="hidden">
    </div>
    <div class="hr-10"></div>

</div>

    <div class="hr-10"></div>
    <div class="order-detail-area">

    <div class="order-detail clearfix" id="order-pro-list">

        <div class="order-list">

            <div class="order-list-detail">
                <!-- 需要循环 -->
                <c:choose>

                    <c:when test="${requestScope.TYPE == 'order'}">    <!--商品界面下单 -->
                        <div class="order-main clearfix ">
                            <a style="cursor:default" ahref="/product/10086727877036.html#2601010138901" class="p-img" target="_blank">
                                <img alt="${requestScope.PRODUCT_INFO.name}" src="${requestScope.PRODUCT_IMAGE}">

                            </a>
                            <ul class="payconfirm_" data-value="0">
                                <li>
                                    <a style="cursor:default" ahref="" class="p-name" title="${requestScope.PRODUCT_INFO.name}" target="_blank" seed="item-name">${requestScope.PRODUCT_INFO.name}</a>
                                    <input type="hidden" class="skuCodeQuanClass" value="${requestScope.PRODUCT_COU} " id="2601010138901">
                                </li>
                                <li>x${requestScope.PRODUCT_COU}</li>
                                <li class="p-price">
                                        ¥&nbsp;${requestScope.PRODUCT_INFO.price}
                                </li>
                            </ul>
                        </div>
                    </c:when>

                    <c:otherwise>  <!--购物车 -->

                        <c:forEach items="${requestScope.ORDEROTEM_LIST}" var="item">
                            <div class="order-main clearfix ">
                            <a style="cursor:default" ahref="/product/10086727877036.html#2601010138901" class="p-img" target="_blank">
                                <img alt="${item.productName}" src="${item.productImage}">

                            </a>
                            <ul class="payconfirm_" id="cart_id" data-value="${requestScope.ORDER_ID}">
                                <li>
                                    <a style="cursor:default" ahref="" class="p-name" title="${item.productName}" target="_blank" seed="item-name">${item.productName}</a>
                                    <input type="hidden" class="skuCodeQuanClass" value="${item.quantity} " id="2601010138901">
                                </li>
                                <li>x${item.quantity}</li>
                                <li class="p-price">
                                        ¥&nbsp;${item.totalPrice}
                                </li>
                            </ul>
                        </div>

                        </c:forEach>

                    </c:otherwise>

                </c:choose>


                <!--自选套餐-->


            </div>
            <div class="order-list-info">
                    <div class="order-invoice">
                        <div class="h"><span>发票信息</span><em>注：如果商品由第三方卖家销售，发票内容由其卖家决定，发票由卖家开具并寄出</em></div>
                        <div class="b">
                            <em id="invoice-info-typeVMALL-HUAWEIDEVICE">电子普通发票</em>
                            <span id="invoice-info-titleVMALL-HUAWEIDEVICE">个人</span>
                        </div>
                    </div>
                    <!--20170717-订单表单-发票信息-end -->
                    <input type="hidden" value="" id="routingTagID">
<!-- 20170718-订单优惠-start -->
<div class="order-discount">
            <div class="h"><span>优惠与抵用</span><em>仅适用于自营实物商品</em></div>


    <!--仅仅只是为了上传数据没有任何逻辑-->
    <form id="order-normal-form" autocomplete="off" method="post">
            <input name="couSkuIds" type="hidden" value="2601010138901">
            <input name="couSkuCode" type="hidden" value="2601010138901">
            <input name="couSkuNum" type="hidden" value="1">
    <input name="CsrfToken" type="hidden" value="36723870B41F40A0A9D1371F1E465391A65CDC9BE014C1BF"></form>
            <form autocomplete="off" method="post"><!--不是真正意义上的表单 是为了 防止firefox刷新的时候 页面数据没有刷新-->
            <div class="order-coupon">
                <span id="order-couponDiscount" class="hide"></span>
                    <input id="couponInfo" type="hidden" value="">
                    <p id="coupon-info-desc"><span id="coupon-info-amount"></span><b id="b_yuan"><span id="coupon-info-discount"></span></b></p>
                    <a href="" class="hide"" id="coupon-edit-link">修改</a>
                    <a href="javascript:;" class="red" id="coupon-use-link">使用优惠券</a>
            </div>
            <input name="CsrfToken" type="hidden" value="36723870B41F40A0A9D1371F1E465391A65CDC9BE014C1BF"></form>
        
        <div class="order-discount-tip hide" id="order-discount-tip">注：积分与花瓣合计抵扣不能超过自营商品应付款的30%</div>
</div>
</div>
        </div>

        <div class="order-total">
          <!--TCS商品不展示快递方式-->
            <div class="order-total-info">
                 <p>商品由<span>华为商城</span>选择合作快递</p>
                     <p>
                        <strong id="order_confirm_tipsMsg" style="display: none;"></strong>
                        <em id="order_confirm_tips" class="hide" style="display: none;"><b>“预计送达时间”功能处于试运营，可能会略有偏差，最终以实际到货时间为准，如有疑问请您联系商城客服。感谢您对华为商城的支持和理解！</b></em>
                     </p>
            </div>
            <div class="order-total-price">
                <div class="clearfix">
                <div class="order-price-detail">
                    <ul>
                        <li><strong>商品总金额：</strong>
                           <span>
                                        ¥&nbsp;<em id="order-cost-area" data-oldval=" 4299.00">${requestScope.TORALPRICT}</em>
                           </span>
                        </li>

                            <li><strong>运费：</strong>
                                <span>
                                       ¥&nbsp;<em id="order-deliveryCharge">0.00</em>
                                </span>
                            </li>

                            <li><strong>优惠金额：</strong>
                                <span>
                                        <em class="order-total-activity">
                                            <i><span class="icon-sales">网易云摄影课程兑换券</span></i>
                                        </em>
                                    -&nbsp;¥&nbsp;<em id="coupon_money">0.00</em>
                                </span>
                            </li>
                            <li>
                                <strong>结算金额：</strong>
                                <span><b></b>
                                        <span><b>¥&nbsp;</b><b id="cash_payment" data-oldval="${requestScope.TORALPRICT}">${requestScope.TORALPRICT}</b></span>

                                </span>
                            </li>
                    </ul>


                    </div>
                </div>

            </div>

        </div>
    </div>
        </div>
    <div class="hr-10"></div>
<div class="order-detail-area clearfix" style="margin: 0px 0px 30px;">
    <div class="order-submit"><!-- 20170718-订单提交-start -->

        <div class="order-submit-info">
                   <div class="order-submit-price">应付总额：<b>¥<span id="payableTotal">${requestScope.TORALPRICT}</span></b></div>

            <div class="order-submit-integral">
                <span id="isHidePoint"><em>
                可获得积分：<em id="canHavePoint">${requestScope.TORALPRICT / 100}</em></em></span>
            </div>
        </div>


        <div class="clearfix hide" id="no-delivery-address-tips" style="display: none;"><div class="report-errors"><span id="no-choose-address">未选择收货地址不可下单</span></div></div>

         <div class="clearfix">
            <a href="javascript:void(0);" id="checkoutSubmit" data-url="orderServlet?mod=htmlPayUpdateOrder&order_id=${requestScope.ORDER_ID }&itemId=${requestScope.ITEMID_ARRAY }&shipping_id=" class="order-submit-btn" seed="checkout-submit" ><span>提交订单</span></a>
         </div>
    </div><!-- 20170718-订单提交-end -->
</div>
<div class="hide" id="unpay-order"></div>
<div class="hide" id="erroCompany"></div>
</div>
</div>
		<div id="fade" class="fade" style="display: none"></div>
		<div id="succ-pop" class="succ-pop" style="display: none">
			<div style="float:right;"><a href="javascript:;" class="box-ok"><center>关闭</center></a></div>
		    <iframe src="addAddress.jsp" width="940px" height="580px" frameborder="no"  border="0" marginwidth="0" marginheight="0" scrolling="no"  allowtransparency="yes"></iframe>
		</div>
		
		
		<!-- 优惠劵选择 -->
		<div class="ol_box_mask" style="visibility: visible;width: 1653px;height: 1707px;z-index: 5000;display:none"></div>
		<div id="order-coupon-box" class="ol_box_4 ol_box_title" style="visibility: visible; position: fixed; top: 129px; left: 477px; z-index: 5000; width: 700px; height: 549px;display:none">
   <div class="box-ct"> 
    <div class="box-header"> 
     <div class="box-tl"></div> 
     <div class="box-tc"> 
      <div class="box-tc1"></div> 
      <div class="box-tc2">
       <a href="javascript:;" title="关闭" class="box-close"></a>
       <span class="box-title">使用优惠券</span>
      </div> 
     </div> 
     <div class="box-tr"></div> 
    </div> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;background:#fff;"> 
     <tbody>
      <tr> 
       <td class="box-cl"></td> 
       <td class="box-cc"> 
        <div class="box-content" style="height: 500px; overflow-y: auto;"> 
         <!--20170728-优惠券-start --> 
         <div class="order-roll"> 
          <div class="order-roll-tab"> 
           <a href="javascript:;" class="current" id="coupon_tab" >可用优惠券</a> 
          </div> 
          <!--可用优惠券列表start--> 
          <div id="order-roll-coupon"> 
           <div class="toast-error" id="toast">
            请先取消已选的优惠券再进行选择
           </div> 
           <div class="order-roll-con"> 
            <div class="order-roll-list"> 
             <input type="hidden" value="BRA0H2B6JNG4PZ77U,"  /> 
             <ul class="clearfix" id="eff_couponUlId_VMALL-HUAWEIDEVICE"> 
             <c:forEach items="${requestScope.COUPON_LIST }" var="coupon" varStatus="row">
             
             	<c:if test="${coupon.couponUse == 1 }">
             		<li name="effCouponInfo"   class="order-roll-detail" id="li_${row.index }" data="${row.index }" data-money="${coupon.money }" data-id="${coupon.id }"> 
		               <div class="order-roll-info"> 
		                <em>&yen;</em> 
		                <div class="order-roll-price clearfix"> 
		                 <span>${coupon.money }</span>
		                 <strong>优惠券</strong> 
		                </div> 
		                <p title="【华为商城】 ${coupon.couponName }" class="order-roll-word">【华为商城】 ${coupon.couponName }</p> 
		               </div> 
		               <p class="order-roll-explain order-roll-explain-more">使用描述：${coupon.depict }
		               	<a href="javascript:;" class="btn"></a>
		               </p> 
		             </li>
             	</c:if>
             		 
             </c:forEach>
              
             </ul> 
            </div> 
           </div> 
           <div class="box-button"> 
            <a href="javascript:;" class="box-cancel" id="not_use_coupon_btn" ><span id="not_use_coupon_text">不使用优惠券</span></a> 
            <a href="javascript:;" class="box-sure" id="use_coupon_btn" ><span>立即使用</span></a> 
           </div> 
          </div> 
          <!--可用优惠券列表end--> 
          <input type="hidden" id="keyongID" value="1" /> 
          <input type="hidden" id="bukeyongID" value="0" /> 
         </div> 
        </div> 
        <div class="box-button" style="display: none;"> 
         <a class="box-cancel" href="javascript:;"><span>取消</span></a> 
         <a class="box-ok" href="javascript:;"><span>确定</span></a> 
        </div> </td> 
       <td class="box-cr"></td> 
      </tr> 
     </tbody>
    </table> 
    <div class="box-bottom" style=""> 
     <div class="box-bl"></div> 
     <div class="box-bc"></div> 
     <div class="box-br"></div> 
    </div>
   </div>
  </div>
		
<%@include file="font.jsp"  %>
<script src="static/js/jquery-2.2.1.js"></script>
<script type="text/javascript" src="static/js/order_confirm.js"></script>
<script type="text/javascript">
	$(function(){
		    $.ajax({
		        url: 'userServlet',
		        type: 'post',
		        dataType: 'json',
		        data: {
		            method:'indexUpdate'
		        },
		        success:function(data){
		            var userId = parseInt(data[0].id);
		            //登录成功
		            if (userId > 0) {
                        //获取用户信息
                        $('#up_loginName').text(data[0].user_name);
                        //隐藏登录注册按钮
                        $('#login_reg_').attr('style','display:none');
                        //显示个人信息
                        $('#login_status').attr('style','display:block');
		            }else{

		            }

		        }
		    })

		})
</script>
</body>
</html>
