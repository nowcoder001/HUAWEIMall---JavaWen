<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>订单详情</title>

    <link rel="stylesheet" href="static/css/ec.core.base.min.css">
    <link rel="stylesheet" href="static/css/main.min.css">
    <link rel="shortcut icon" href="static/image/favicon.ico">
    <script src="static/js/jquery-2.2.1.js"></script>
</head>
<body>
<%@include file="head.jsp"  %>
<div class="g">
<div class="breadcrumb-area fcn"><a href="/index.html">首页</a>&nbsp;&gt;&nbsp;<em id="personCenter"><a href="/member/">我的商城</a></em><em id="pathPoint"></em><span id="pathTitle"></span></div>
<%@include file="menu.jsp" %>
<div class="fr u-4-5" style="width: 940px;">
<!-- 20141212-栏目-start -->
<div class="section-header">
    <div class="fl">
        <h2><span>订单号：${requestScope.ORDER_LIST.orderNo }</span></h2>
    </div>
    <div class="fr">
        <div class="section-header-button">
            <!-- 增加正在配货状态下（19）可以取消订单 -->
                        
                        <c:if test="${requestScope.ORDER_LIST.status > 0 && requestScope.ORDER_LIST.status < 50}">
                        	<a class="button-operate-cancel-order" href="orderServlet?mod=deleteOrder&order_id=${requestScope.ORDER_LIST.id }" title="取消订单"  onClick="return confirm('确定删除订单吗(想好了吗)?');">
	                            <span>取消订单</span>
	                        </a>
                        </c:if>
                        <c:if test="${requestScope.ORDER_LIST.status < 20 }">
                        	<a class="button-operate-edit" href="orderServlet?mod=orderUpdate&order_id=${requestScope.ORDER_LIST.id }" ><span>修改订单</span></a>
                        	<a class="button-operate-pay"  href="${requestScope.SERVLET_URL }">
	                        <span>
	                                立即支付
	                        </span>
	                        </a>
                        </c:if>
                        
        </div>
    </div>
</div>

                <div class="order-detail-state">
                    <!-- 20141223-订单进度-start -->
                    <div class="order-state-progress" data-url="${requestScope.ORDER_LIST.status}">
                        <ol >
                            <li id="li0" class="first completed" >
                                <s></s>
                                <dl>
                                    <dt>提交订单</dt>
                                    <dd>
                                        <p>${requestScope.ORDER_LIST.createTime }</p>
                                    </dd>
                                </dl>
                            </li>
                            <li id="li1" data-value="${requestScope.ORDER_LIST.paymentTime }" >
                            <s></s>
                            <dl>
                                <dt>付款成功</dt>
                                <dd>
                                    <p id="p1"></p>
                                </dd>
                            </dl>
                            </li>
        					<li id="li2" data-value="${requestScope.ORDER_LIST.paymentTime }">
        						<s></s>
        						<dl>
                					<dt>正在配货</dt>
            					<dd>
                					<p id="p2"></p>
            					</dd>
       						 </dl>
        				</li>
				        <li id="li3" data-value="${requestScope.ORDER_LIST.sendTime }">
				        <s></s>
				        <dl>
				                <dt>等待收货</dt>
				            <dd>
				                  <p id="p3"></p>
				            </dd>
				        </dl>
				        </li>

				        <li id="li4" data-value="${requestScope.ORDER_LIST.endTime }">
				        <!-- <li > -->
				        <s></s>
				        <dl>
                    	<dt>已完成</dt>
            			<dd>
                    		<p id="p4"></p>

		            </dd>
		        </dl>
		        </li>

        <!-- 非手机保险单独售卖需求 -->
        </ol>
        </div>
        <!-- 20141223-订单进度-end -->
        </div>
        <!-- 20141223-订单详情-订单状态-end -->
<div class="hr-50"></div>
<!-- 20141223-订单详情-订单处理信息-start -->
<div class="order-detail-process-record">
    <div class="list-group-caption">
        <h3>订单处理信息</h3>
    </div>
    <div class="list-group-title">
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th class="col-date">处理时间</th>
                <th class="col-info">处理信息</th>
                <!--<th class="col-operator">操作人</th>-->
            </tr>
            </thead>
        </table>
    </div>
    <div class="list-group" id="order-list-group">
    					<div id="order_info1" class="list-group-item border-0 latest" style="display: none;">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                <tr>
                                    <td class="col-date col-state"><s></s>${requestScope.ORDER_LIST.endTime }</td>
                                    <td class="col-info">您的订单已完成</td>
                                    <!--<td class="col-operator">system</td>-->
                                </tr>
                                </tbody>
                            </table>
                        </div>
    					<div id="order_info2" class="list-group-item border-0 latest" style="display: none;">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                <tr>
                                    <td class="col-date col-state"><s></s>${requestScope.ORDER_LIST.sendTime }</td>
                                    <td class="col-info">您的订单已发货 - 等待收货</td>
                                    <!--<td class="col-operator">system</td>-->
                                </tr>
                                </tbody>
                            </table>
                        </div>
    					<div id="order_info3" class="list-group-item border-0 latest" style="display: none;">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                <tr>
                                    <td class="col-date col-state"><s></s>${requestScope.ORDER_LIST.paymentTime }</td>
                                    <td class="col-info">您的订单已付款 - 正在配货</td>
                                    <!--<td class="col-operator">system</td>-->
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div id="order_info4" class="list-group-item border-0 latest">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                <tr>
                                    <td class="col-date col-state"><s></s>${requestScope.ORDER_LIST.createTime }</td>
                                    <td class="col-info">您的订单已确认</td>
                                    <!--<td class="col-operator">system</td>-->
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div id="order_info5" class="list-group-item border-0 end">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                <tr>
                                    <td class="col-date col-state"><s></s>${requestScope.ORDER_LIST.createTime }</td>
                                    <td class="col-info">您提交了订单，请等待系统确认</td>
                                    <!--<td class="col-operator">15673111542</td>-->
                                </tr>
                                </tbody>
                            </table>
                        </div>
						<div id="order_info5" class="list-group-item border-0 end">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                <tr>
                                	<div class="list-group-caption">
						                <h3>物流信息<span class="">
						                </span>
						                </h3>
						            </div>
                                    <td class="col-date col-state"><s></s>收货地址：</td>
                                    <td class="col-info">${requestScope.ORDER_LIST.shipping.receiverProvince} - ${requestScope.ORDER_LIST.shipping.receiverCity} - ${requestScope.ORDER_LIST.shipping.receiverDistrict} - ${requestScope.ORDER_LIST.shipping.receiverAddress}</td>
                                    <!--<td class="col-operator">15673111542</td>-->
                                </tr>
                                </tbody>
                            </table>
                        </div>
    				</div>
				</div>
			<!-- 20141223-订单详情-收货信息-start -->
<div class="hr-50"></div>
    <div class="order-detail-pro">
        <div class="order-pro-record">
            <div class="list-group-caption">
                <h3>商品清单<span class="">
                </span>
                </h3>
            </div>
            <div class="list-group-title">
                <table border="0" cellpadding="0" cellspacing="0">
                    <thead>
                    <tr>
                        <th class="col-pro first">商品名称</th>
                        <th class="col-int">赠送积分</th>
                        <th class="col-price">单价/元</th>
                        <th class="col-quty">数量</th>
                        <th class="col-pay">小计/元</th>
                        <th class="col-state end">状态</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="list-group">
                    <div class="list-group-item">
                        <div class="o-pro">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tbody>
									<c:forEach items="${requestScope.ORDER_LIST.orderItems }" var="item" varStatus="row">
										<tr>
                                        <input type="hidden" class="skuCodeQuanClass" value="1" id="2601010138901">
                                        <td class="col-pro-img">
                                            <p class="p-img">
                                                <a title="${item.productName }" href="" target="_blank" >
                                                    <img alt="${item.productName }" src="${item.productImage }">
                                                </a>
                                            </p>
                                        </td>
                                        <td class="col-pro-info">
                                            <p class="p-name">
                                                <a title="${item.productName }" target="_blank" href="#" >${item.productName }</a>
                                            </p>
                                        </td>
                                        <td class="col-int">${item.totalPrice / 20 }</td>
                                        <td class="col-price">
                                                    <em>¥</em>
                                                    <span>${item.current_unit_price }</span>
                                        </td>
                                        <td class="col-quty">${item.quantity }</td>
                                            <td class="col-pay" >
                                                        <em>¥</em>
                                                        <span>${item.totalPrice }</span>
                                            </td>
                                            <c:if test="${requestScope.ORDER_LIST.status == 10 }">
                                            	<td class="col-state end">未支付</td>
                                            </c:if>
                                            <c:if test="${requestScope.ORDER_LIST.status == 20 }">
                                            	<td class="col-state end">已支付</td>
                                            </c:if>
                                            <c:if test="${requestScope.ORDER_LIST.status == 30 }">
                                            	<td class="col-state end">已发货</td>
                                            </c:if>
                                            <c:if test="${requestScope.ORDER_LIST.status == 50 }">
                                            	<td class="col-state end">已完成</td>
                                            </c:if>
                                        
                                    </tr>
									</c:forEach>
                                    

                                </tbody>
                            </table>
                        </div>
                          <!-- 20141223-商品清单-赠品列表-start -->
            <div class="order-pro-record">
                <div class="list-group">
                </div>
            </div>

                    </div>
            </div>
        </div>
        <div class="order-pro-total">
            <div class="order-pro-cost">
                <table border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <th>商品金额总计：</th>
                        <td><em></em>
                                ¥<span id="totalOriginalPriceIDD">${requestScope.TOTAL_PRICE }</span>
                        </td>
                    </tr>
                    <tr>
                        <th>使用优惠券：</th>
                        <td><em>-&nbsp;¥</em><span>${requestScope.TOTAL_PRICE - requestScope.PAYMENT }</span></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="order-pro-cost-total">合计（含运费）：<em></em>
                <span>
                    ¥${requestScope.PAYMENT }
                </span>

                    <p class="pdt-10">可获得积分：<b class="fw-normal">${requestScope.PAYMENT / 20 }</b></p>
                <!--订单类型是10：新订金订单 且支付状态非已支付时 展示订金活动配置的“订单列表/详情说明”自定义文案-->
            </div>
        </div>
        <!-- 20141223-商品清单-合计-end -->
    </div>
    <!-- 20141223-订单详情-商品清单-end -->
    		<c:if test="${requestScope.ORDER_LIST.status < 20 }">
    			<div class="order-detail-action">
                    <a class="button-operate-pay"  href="${requestScope.SERVLET_URL }">
                <span>
                        立即支付
                </span>
                    </a>
                </div>
    		</c:if>
                

<div class="hr-40"></div>
<div>

</div>

</div>
</div>
<%@include file="font.jsp"  %>
<script type="text/javascript" src="static/js/order_detail.js"></script>
</body>

</html>
