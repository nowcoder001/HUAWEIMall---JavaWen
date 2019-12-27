<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>修改订单</title>

    <link rel="stylesheet" href="static/css/ec.core.base.min.css">
    <link rel="stylesheet" href="static/css/main.min.css">
    <link rel="shortcut icon" href="static/image/favicon.ico">
    <script src="static/js/jquery-2.2.1.js"></script>
</head>
<body>
<%@include file="head.jsp"  %>
    <div class="g" >
    <div class="breadcrumb-area fcn"><a href="/index.html">首页</a>&nbsp;&gt;&nbsp;<em id="personCenter"><a href="/member/">我的商城</a></em><em id="pathPoint"></em><span id="pathTitle"></span></div>
<%@include file="menu.jsp" %>
<div class="fr u-4-5">

<!-- 20141212-栏目-start -->
<div class="section-header">
    <div class="fl">
        <h2><span>订单号：${requestScope.ORDER_LIST.orderNo }</span></h2>
    </div>
    <div class="fr">
        <div class="section-header-button">
                <a class="button-operate-edit-cancel" href="orderServlet?mod=orderDetail&order_id=${requestScope.ORDER_LIST.id }">
                    <span>取消修改</span>
                </a>
            <a class="button-operate-edit" href="" return false;" name="11690425916">
                <span>确认修改</span>
            </a>
        </div>
    </div>
</div>
<!-- 20141212-栏目-end -->
    <div class="hr-50"></div>
<!-- 20141226-订单编辑-start -->
<div class="order-edit">

    <!-- 20141226-订单编辑-收货地址-start -->
            <div class="order-address">
                <h3 class="title">收货人信息</h3>
                <div class="order-address-list" id="order-address-list"><ol>


                <c:forEach items="${requestScope.SHIPPINGS }" var="shipping" varStatus="row">
                	<li id="myAddress-76983738" class="current">
	                <div class="address-main">
	                <c:if test="${requestScope.SHP_ID == shipping.id}">
	                	<span class="address-mark" id="${row.index }"><i></i>寄送至</span>
	                	<input type="radio" class="radio" name="myAddress" value="${shipping.id }" checked="checked" data-num = "${row.index }">
	                </c:if>
	                <c:if test="${requestScope.SHP_ID != shipping.id}">
	                	<span class="address-mark" style="display:none;" id="${row.index }"><i></i>寄送至</span>
	                	<input type="radio" class="radio" name="myAddress" value="${shipping.id }" data-num = "${row.index }">
	                </c:if>

	                <label class="address-info" for="input-myAddress76983738">
	                <b>${shipping.receiverName }</b>
	                <span>${shipping.receiverProvince }${shipping.receiverCity }${shipping.receiverDistrict }&nbsp;${shipping.receiverAddress }</span>
	                <div id="order-address-tips" class="address-tips-stages hidden"><div>
	                <label class="label-error"></label>
	                <p>为确保商品尽快送达，请补充街道信息。</p>
	                </div>
	                <div class="f"><s class="icon-arrow-down"></s></div>
	                </div>
	                <input type="hidden" id="needL4Addr" value="false">
	                <input type="hidden" id="needModify" value="false">
	                <span class="tellNum">电话：${shipping.receiverMobile }</span>
	                </label>
	                </div>
	                <c:if test="${shipping.receiverDefault == true }">
	                	<div class="address-sub">
		                	<p class="a-edit"></p>
		                	<p class="a-state"><span class="default">默认地址</span></p>
		                </div>
	                </c:if>


	                </li>
                </c:forEach>


                </ol></div>
                <input type="hidden" value="No need" id="randomFlag">
                <!-- 20140813-订单-表单-地址-空数据-start -->
                <div class="order-address-empty" id="order-address-empty" style="display: none;">您还没有收货地址，马上&nbsp;
                <a href="" ">添加</a>&nbsp;吧！
                </div><!-- 20140813-订单-表单-地址-空数据-end -->
                <div id="address-more" class="hide" style="display: none;">
                    <a class="address-expand hide" href="javascript:;" title="更多地址"><span>更多地址</span></a>
                </div>
            </div>
    <!-- 20141226-订单编辑-收货地址-end -->
    <div class="hr-50"></div>

    <div class="order-invoice">
        <h3 class="title">发票信息<span>（注：如果商品由第三方卖家销售，发票内容由其卖家决定，发票由卖家开具并寄出）</span></h3>

    </div>
    <!-- 20160909-订单-表单-发票信息-end -->
    <div class="hr-50"></div>


    <!-- 20141226-订单编辑-商品清单-start -->
    <div class="order-edit-pro">
            <!-- 20141223-商品清单-商品列表-start -->
            <div class="order-pro-record">
                <div class="list-group-caption">
                    <h3>商品清单</h3>
                </div>
                <div class="list-group-title">
                    <table cellspacing="0" cellpadding="0" border="0">
                        <thead>
                            <tr>
                                <th class="col-pro first">商品名称</th>
                                <th class="col-price">单价/元</th>
                                <th class="col-quty">数量</th>
                                <th class="col-pay">小计/元</th>
                                <th class="col-state">状态</th>
                                <th class="col-dely end">配送方式</th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="list-group" id="list-group">
                    <div class="list-group-item">
                        <div class="o-pro">
                            <table cellspacing="0" cellpadding="0" border="0">
                                <tbody>
                                  <!--普通单品数量-->

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
                                        <td class="col-price">
                                                    <em>¥</em>
                                                    <span>${item.current_unit_price }</span>
                                        </td>
                                        <td class="col-quty">${item.quantity }</td>
                                            <td class="col-pay" >
                                                        <em>¥</em>
                                                        <span>${item.totalPrice }</span>
                                            </td>
                                            <c:if test="${requestScope.ORDER_LIST.status == 10 && row.index == 0}">
                                            	<td class="col-state" rowspan="${fn:length(requestScope.ORDER_LIST.orderItems) }">未支付</td>
                                            </c:if>
                                            <c:if test="${requestScope.ORDER_LIST.status == 20 && row.index == 0}">
                                            	<td class="col-state" rowspan="${fn:length(requestScope.ORDER_LIST.orderItems) }">已支付</td>
                                            </c:if>
                                            <c:if test="${requestScope.ORDER_LIST.status == 30 && row.index == 0}">
                                            	<td class="col-state" rowspan="${fn:length(requestScope.ORDER_LIST.orderItems) }">已发货</td>
                                            </c:if>
                                            <c:if test="${requestScope.ORDER_LIST.status == 50 && row.index == 0}">
                                            	<td class="col-state" rowspan="${fn:length(requestScope.ORDER_LIST.orderItems) }">已完成</td>
                                            </c:if>
                                            <c:if test="${row.index == 0}">
                                            	<td class="col-dely  end" rowspan="${fn:length(requestScope.ORDER_LIST.orderItems) }">
                                                    <p>
                                                        <label class="inputbox">
                                                            <input class="radio" type="radio" checked="checked"><span>默认物流</span><em><span id="changeFree">0.00</span>元</em>
                                                        </label>
                                                    </p>
                                                </td>
                                            </c:if>


                                    </tr>
									</c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 20141223-商品清单-商品列表-end -->
        <!-- 20141223-商品清单-赠品列表-start -->
        <!-- 20141223-商品清单-赠品列表-end -->

        <!-- 20141223-商品清单-合计-start -->
        <div class="order-pro-total">
            <div class="fl"></div>
            <div class="fr">
                <div class="order-pro-cost">
                    <table cellspacing="0" cellpadding="0" border="0">
                        <tbody>
                            <tr>
                                <th>商品金额总计：</th>
                                <td>
                                        ¥<span>${requestScope.TOTAL_PRICE }</span>
                                </td>
                            </tr>
                            <tr>
                                <th>运费：</th>
                                <td><em>¥</em><span id="deliveryFree">0.00</span></td>
                            </tr>
                            <tr>
                                <th>使用优惠券：</th>
                                <td><em>-&nbsp;¥</em><span id="couponValue">0.00</span></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="order-pro-cost-total">合计（含运费）：<em></em>
                    <span id="orderTotal">
                            ¥${requestScope.TOTAL_PRICE }
                    </span>

                        <p class="pdt-10">可获得积分：<b class="fw-normal">${requestScope.TOTAL_PRICE / 20 }</b></p>
                </div>
            </div>
        </div>
        <!-- 20141223-商品清单-合计-end -->
        <div class="order-edit-action">
            <a href="orderServlet?mod=orderDetail&order_id=${requestScope.ORDER_LIST.id }" class="button-action-cancel"><span>取消修改</span></a>
            <a href="javascript:;" class="button-action-edit" data-url="orderServlet?mod=orderUpdateConfirm&order_id=${requestScope.ORDER_LIST.id}&shipping_id="><span>确认修改</span></a>
        </div>
    </div>
</div>

<input type="hidden" value="https://customization.vmall.com" id="domainEdit">
<input type="hidden" value="" id="addresseePhoneID">
<input type="hidden" id="gouldAddress" name="unionPaySwitch" value="1">
<input type="hidden" id="updateOrder" name="updateOrder" value="1">



<style>
.button-style-s1 {
  text-align: center;
  background-color: #FAFAFA;
  border: 1px solid #DEDEDE;
  color: #333333!important;
  width: 118px;
  height: 28px;
  line-height: 28px;
  font-size: 14px;
}
a.button-style-s1 {
  display: inline-block;
  *display: inline;
  *zoom: 1;
}
a.button-style-s1:hover {
  text-decoration: none;
}
</style>
</div>
</div>
<%@include file="font.jsp"  %>
</body>
<script type="text/javascript">
    $(function(){
        var shippingId = 0;
        $('input[name="myAddress"]').click(function(event) {
            var i = parseInt($(this).attr('data-num'));
            shippingId = $(this).val();
            $('#'+i).attr('style','display:block');
            $('.address-mark').each(function(index, val) {
                if (i != index) {
                    $(this).attr('style','display:none');
                };
            });
        });

        $('.button-action-edit').click(function(event) {
            var url = $(this).attr('data-url');
            url = url+shippingId;
            $.ajax({
                url: url,
                type: 'post',
                dataType: 'text',
                data: {

                },
                success:function(data){
                    var count = parseInt(data);
                    if (count > 0) {
                        alert('更改成功');
                    }else{
                        alert('更改失败');
                    }
                }
            })

        });
    });



</script>
</html>
