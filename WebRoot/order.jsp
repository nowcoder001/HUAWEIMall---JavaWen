<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>华为商城 - 我的订单</title>
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
    <div class="fr u-4-5"  style="width: 940;">
<div class="section-header">
    <div class="fl">
        <h2><span>我的订单</span></h2>
    </div>
    <div class="fr">
        <div class="ec-tab" id="ec-tab">
            <ul>
                <li class="current"><a href=""><span>最近六月内订单<em id="count-seltime-0">${requestScope.ALL_PAY }</em></span></a></li>
                <li><a href=""><span>六个月前订单<em id="count-seltime-1" style="display: none;">0</em></span></a></li>
            </ul>
            <div class="ec-tab-arrow" style="width: 135.797px; left: 0px;"></div>
        </div>
    </div>
</div>
<div class="myOrder-cate" id="myOrder-cate">
    <ul>
        <li class="current"><a href="orderServlet?mod=htmlMyOrder&status=1"><span>全部有效订单<em></em></span></a></li>
        <li>
            <a href="orderServlet?mod=htmlMyOrder&status=10">
                <span>
                    待支付
                    <em id="count-seltime-2" data-num="1">
                            ${requestScope.NO_PAY }
                    </em>
                </span>
            </a>
        </li>
        <em id="count-seltime-2-wechat" style="display:none">
                0
        </em>
        <em id="count-seltime-3-wechat" style="display:none">
                0
        </em>
        <li><a href="orderServlet?mod=htmlMyOrder&status=20" ><span>待评价<em id="count-seltime-2" data-num="${requestScope.YES_PAY }" >${requestScope.YES_PAY }</em></span></a></li>
        <li><a href="orderServlet?mod=htmlMyOrder&status=30" ><span>待收货<em id="count-seltime-2" data-num="${requestScope.SHIPPING }">${requestScope.SHIPPING }</em></span></a></li>
        <li><a href="orderServlet?mod=htmlMyOrder&status=50" ><span>已完成<em id="count-seltime-2" data-num="${requestScope.OK_ORDER }" >${requestScope.OK_ORDER }</em></span></a></li>
    </ul>
</div>
<div class="myOrder-record" id="myOrders-list-content">
    <div class="myOrder-control hide" id="myOrder-control-bottom-up">
        <label class="inputbox" for="checkAllBox">
            <input class="checkbox" type="checkbox" id="checkAllBox" name="checkAllBox"><span>全选</span>
        </label>
        <a href="" class="button-operate-merge-pay" id="topButton" ><span>合并支付</span></a>
    </div>
    <div class="list-group-title">
        <table border="0" cellpadding="0" cellspacing="0">
            <thead>
                <tr>
                    <th class="col-pro">商品</th>
                    <th class="col-price">单价/元</th>
                    <th class="col-quty">数量</th>
                    <th class="col-pay">实付款/元</th>
                    <th class="col-operate">订单状态及操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <div class="list-group" id="list-group">
    <!-- 订单循环开始 -->
    			<c:forEach items="${requestScope.ORDER_LIST }" var="order" varStatus="row">
    				<div class="list-group-item">
                    <div class="o-info">
                            <div class="col-info">
                                <input type="checkbox" class="checkbox" name="payCheck" value="11520428858" paymentstatus="2" clearorder="false">
                                <span class="o-date">${order.paymentTime }</span>
                                <span class="o-no">订单号：<a href="" title="${order.orderNo }">${order.orderNo }</a></span>
                            </div>
                            <!-- 判断是否是未支付还是已支付... -->
                            <c:if test="${order.status == 10 }">
								<div class="col-state">
	                                订单已确认&nbsp;|&nbsp;
	                                                                未支付
	                            </div>
							</c:if>
							<c:if test="${order.status == 20 }">
								<div class="col-state">
	                                订单已确认&nbsp;|&nbsp;已支付
	                            </div>
							</c:if>
							<c:if test="${order.status == 30 }">
								<div class="col-state">
	                                订单已确认&nbsp;|&nbsp;已发货
	                            </div>
							</c:if>
							<c:if test="${order.status == 50 }">
								<div class="col-state">
	                                订单已确认&nbsp;|&nbsp;已完成
	                            </div>
							</c:if>
                            
                    </div>
                    <div class="o-pro">
                        <table border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                            	<c:choose> 

								     <c:when test="${order.orderItems == null || fn:length(order.orderItems) == 0}">    <!--如果子商品集合等于0或者空 --> 
										 <tr>
                                            <td class="col-pro-img">
                                                <p class="p-img">
                                                    <a title="${order.orderItem.productName }" href="product.jsp?id=${order.orderItem.productId }" target="_blank">
                                                        <img alt="${order.orderItem.productName }" src="${order.orderItem.productImage }">
                                                    </a>
                                                </p>
                                            </td>
                                            <td class="col-pro-info">
                                                <p class="p-name">

                                                        <a title="${order.orderItem.productName }" target="_blank" href="product.jsp?id=${order.orderItem.productId }">${order.orderItem.productName }</a>
                                                </p>
                                            </td>
                                            <td class="col-price">
                                                    <em>¥</em><span>${order.orderItem.current_unit_price }</span>
                                            </td>
                                            <td class="col-quty">${order.orderItem.quantity }</td>
                                                    <td rowspan="3" class="col-pay">
                                                        <p>
                                                                    <em>¥</em><span>${order.payment }</span>

                                                        </p>
                                                    </td>


                                                        <td rowspan="3" class="col-operate">
                                                        	<c:choose> 

															     <c:when test="${order.status < 20 }">    <!--如果 --> 
																	<p class="p-button"> <a class="button-operate-pay" href="orderServlet?mod=orderDetail&order_id=${order.id }">
                                                                  		<span>
                                                                            立即支付
                                                                  		</span></a>
                                                              	 	</p>
                                                              		<p class="p-link"><a href="orderServlet?mod=orderUpdate&order_id=${order.id }">修改订单</a></p>
                                                            		<p class="p-link"><a href="orderServlet?mod=deleteOrder&order_id=${order.id }" onClick="return confirm('确定删除订单吗(想好了吗)?');">取消订单</a></p>
                                                            		<p class="p-link"><a href="orderServlet?mod=orderDetail&order_id=${order.id }">订单详情</a></p>
															 	</c:when>
															   <c:otherwise>  <!--否则 -->    
                                                            		<p class="p-link"><a href="orderServlet?mod=deleteOrder&order_id=${order.id }" onClick="return confirm('确定删除订单吗(想好了吗)?');">取消订单</a></p>
                                                            		<p class="p-link"><a href="orderServlet?mod=orderDetail&order_id=${order.id }">订单详情</a></p>
															  </c:otherwise> 
															
															</c:choose>
                                                              
                                                        </td>
                                    		</tr>
                                    		
								 	</c:when>      
								
								     <c:otherwise>  <!--否则子商品有集合 -->    
								     	<!-- 循环子商品 -->
								     	<c:forEach items="${order.orderItems }" var="item" varStatus="row">
								     		<tr>
												<td class="col-pro-img">
													<p class="p-img">
														<a title="${item.productName }" href="product.jsp?id=${item.productId }" target="_blank">
														   <img alt="${item.productName }" src="${item.productImage }">
														 </a>
													</p>
												</td>
												<td class="col-pro-info">
													<p class="p-name">
																									
														<a title="${item.productName }" target="_blank" href="product.jsp?id=${item.productId }">${item.productName }</a>
													</p>
												</td>
												<td class="col-price">
													<em>¥</em><span>${item.current_unit_price }</span>
												</td>
				        						<td class="col-quty">${item.quantity }</td>
				        						<!-- 只执行一次 -->
				        						<c:if test="${row.index == 0 }">
				        						
				        								<td rowspan="${fn:length(order.orderItems) }" class="col-pay">
				        							<p>
														<em>¥</em><span>${order.payment }</span>
																
								        			</p>
				        						</td>
				        						<td rowspan="${fn:length(order.orderItems) }" class="col-operate">
				        							
													<c:choose> 

															     <c:when test="${order.status < 20 }">    <!--如果 --> 
																	<p class="p-button"> <a class="button-operate-pay" href="orderServlet?mod=orderDetail&order_id=${order.id }">
                                                                  		<span>
                                                                            立即支付
                                                                  		</span></a>
                                                              	 	</p>
                                                              		<p class="p-link"><a href="orderServlet?mod=orderUpdate&order_id=${order.id }">修改订单</a></p>
                                                            		<p class="p-link"><a href="orderServlet?mod=deleteOrder&order_id=${order.id }" onClick="return confirm('确定删除订单吗(想好了吗)?');">取消订单</a></p>
                                                            		<p class="p-link"><a href="orderServlet?mod=orderDetail&order_id=${order.id }">订单详情</a></p>
															 	</c:when>
															   <c:otherwise>  <!--否则 -->    
                                                            		<p class="p-link"><a href="orderServlet?mod=deleteOrder&order_id=${order.id }" onClick="return confirm('确定删除订单吗(想好了吗)?');">取消订单</a></p>
                                                            		<p class="p-link"><a href="orderServlet?mod=orderDetail&order_id=${order.id }">订单详情</a></p>
															  </c:otherwise> 
															
															</c:choose>
				        						</td>
				        						
				        					</c:if>
				        						
				        				</tr>
								     	</c:forEach>
											
								  </c:otherwise> 
										
								</c:choose>
                                       
                            </tbody>
                        </table>
                    </div>



                </div>
    			</c:forEach>
                
                <!-- 订单循环结束 -->
    </div>
    <div class="myOrder-control hide" id="myOrder-control-bottom">
        <label class="inputbox" id="bottomCheckBoxDiv" name="bottomCheckBoxDiv">
                <input type="checkbox" class="checkbox" id="bottomCheckAllBox" name="bottomCheckAllBox"><span>全选</span>
        </label>
        <a href="" class="button-operate-merge-pay" id="bottomButton"><span>合并支付</span></a>
    </div>
    <div class="list-group-page">
        <div class="pager" id="list-pager"></div>
    </div>
</div>
<input type="hidden" id="colid" value="1">
<input type="hidden" id="checkid" value="all">
<input type="hidden" id="cancelReson" value="">


<form id="clearPay" action="/clearPay/order">
<input name="ips" id="clear-Ips" type="hidden">
<input name="CsrfToken" type="hidden" value="a69a65dd-b7c4-4e29-9c46-ffce8efffddd">
</form>

</div>
</div>
<%@include file="font.jsp"  %>
</body>
</html>
