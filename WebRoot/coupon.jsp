<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>优惠券</title>
    <link rel="stylesheet" href="static/css/you.css">
    <link rel="stylesheet" href="static/css/you2.css">
    <link rel="shortcut icon" href="static/image/favicon.ico">
    <script src="static/js/jquery-2.2.1.js"></script>
    <style type="text/css" media="screen">
        .alert {
    display: none;
    position: fixed;
    top: 50%;
    left: 50%;
    min-width: 300px;
    max-width: 600px;
    transform: translate(-50%,-50%);
    z-index: 99999;
    text-align: center;
    padding: 15px;
    border-radius: 3px;
}

.alert-success {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
}

.alert-info {
    color: #31708f;
    background-color: #d9edf7;
    border-color: #bce8f1;
}

.alert-warning {
    color: #8a6d3b;
    background-color: #fcf8e3;
    border-color: #faebcc;
}

.alert-danger {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
}
    </style>
</head>
<body>
<%@include file="head.jsp"  %>
<div class="g">
<div class="breadcrumb-area fcn"><a href="/index.html">首页</a>&nbsp;&gt;&nbsp;<em id="personCenter"><a href="/member/">我的商城</a></em><em id="pathPoint"></em><span id="pathTitle"></span></div>
<%@include file="menu.jsp" %>
<div class="fr u-4-5" style="width:944px;"><!-- 20141212-栏目-start -->
<div class="section-header">
    <div class="fl">
        <h2><span>我的优惠券</span></h2>
    </div>
    <div class="fr right">
        <!--
            width = 鼠标定位的li宽度
            left = 鼠标定位的li的前面兄弟节点宽度和
        -->
        <div class="ec-tab" id="ec-tab">
            <ul>
                <li class="current"><a href="" ><span>未使用</span></a></li>
            </ul>
            <div class="ec-tab-arrow" style="width: 68px; left: 0px;"></div>
        </div>
    </div>
</div>
<!-- 20141212-栏目-end -->
 <div class="my-change clearfix" id="my-change-box" style="">
    <input id="my-change-code" type="text" maxlength="32" placeholder="请输入兑换码" class="my-change-input">
    <a href="javascript:;" id="my-change-buttom"  class="my-change-btn disabled">兑换</a>
    <div class="fl">
        <span><h3 style="font-size: 20px;margin-top: 5px;margin-left: 10px;">使用积分兑换输入："积分兑换"  (10积分/次)</h3></span>
    </div>
 </div>
 <div class="alert"></div>
<!-- 20171222-优惠券-列表-start -->
<div class="my-cuopon-record" id="list-group">
    <!--class="my-cuopon-detail"为优惠卷， 选中时添加class="current", 不可选添加class="disabled"-->
    <!--class="my-cuopon-detail my-cuopon-discount"为折扣卷， 选中时添加class="current", 不可选添加class="disabled"-->
    <!--class="my-cuopon-detail my-cuopon-type"为免邮卷， 选中时添加class="current", 不可选添加class="disabled"-->
        <table cellpadding="0" cellspacing="" class="my-cuopon-list">
        <colgroup>
	        <col width="485">
	        <col>
        </colgroup>
        <tbody>
    	  <tr >
    		<c:forEach items="${requestScope.COUPON_LIST }" var="coupon" varStatus="row">


    			<td id="${row.index }">
    			<div class="my-cuopon-detail">

                	<div class="my-cuopon-main clearfix">
                        <div class="my-cuopon-info">
                            <em>¥</em>
                            <span>${coupon.money }</span>
                        </div>
                        <div class="my-cuopon-word">
                        	<p title="【华为商城】 ${coupon.couponName }">【华为商城】 ${coupon.couponName }</p>
                        	<p>${coupon.couponGetTime }</p>
                        	<c:if test="${coupon.couponUse == 1}">
                        		<p >状态：可使用</p>
                        	</c:if>
                        	<c:if test="${coupon.couponUse == 0}">
                        		<p >状态：不可使用</p>
                        	</c:if>
                        </div>
                        <a href="htmlProductServlet?mod=htmlGetProductByName" target="_blank" class="my-cuopon-btn">去使用</a>
                    </div>
                     <p class="my-cuopon-explain">详情描述：${coupon.depict }<a href="javascript:;" class="btn-cuopon hide"></a></p>
                    <!--判断P标签的高度超过35px，即为多行文字-->
                    <!--第一种class="my-cuopon-explain",文字一行-->
                    <!--第二种class="my-cuopon-explain my-cuopon-explain-more",文字一行超出截断有下拉箭头-->
                    <!--第三种class="my-cuopon-explain my-cuopon-explain-more my-cuopon-explain-open",文字多行有向上箭头-->
                </div>
    		</td>
    		<c:if test="${(row.index+1) % 2 == 0}">
    			</tr>
    			<tr>
    		</c:if>

    		</c:forEach>

    	</tbody>
    </table>
    <c:if test="${requestScope.COUPON_SIZE == 0 }">
    	<div class="list-group">
        	<div class="list-group-empty">您暂时没有相关记录。</div>
        </div>
    </c:if>


</div>
<div id="pro-add-success-mask" style="visibility:hidden;font-size:18px;width:348px;position: absolute;"></div>
<!-- 20171222-优惠券-列表-end -->
</div>
</div>
<%@include file="font.jsp"  %>
</body>

<script type="text/javascript">
	$(function(){
        //监听事件
	   $('#my-change-code').bind('input propertychange', function() {
            if ($(this).val().length > 0) {
                $('#my-change-buttom').attr('class','my-change-btn');
            }else{
                $('#my-change-buttom').attr('class','my-change-btn disabled');
            }
        });


       $('#my-change-buttom').click(function(event) {
           if ($(this).attr('class') == 'my-change-btn') {
                //获取兑换码
                var cdk = $('#my-change-code').val();
                //输入正确的兑换码
                $.ajax({
                    url: 'couponServlet',
                    type: 'post',
                    dataType: 'text',
                    data: {
                        mod:'changeCoupon',
                        CDK:cdk
                    },
                    success:function(data){


                        var count = parseInt(data);

                        if (count > 0) {
                            $('<div>').appendTo('body').addClass('alert alert-success').html('兑换成功，两秒后刷新页面').show().delay(2000).fadeOut();
                            //两秒后刷新
                                    setTimeout(function (){

                                        window.location.reload();
                                    }, 2000);


                        }else{


                            var msg = "您使用的是积分兑换码\n\n是否使用积分兑换优惠券(100元)";
                             if (confirm(msg)==true){
                                $.ajax({
                                    url: 'menberServlet',
                                    type: 'post',
                                    dataType: 'text',
                                    data: {
                                        mod:'exChangeCoupon'
                                    },
                                    success:function(data){
                                        var count = parseInt(data);

                                        if (count > 0) {
                                            $('<div>').appendTo('body').addClass('alert alert-success').html('兑换成功，两秒后刷新页面').show().delay(2000).fadeOut();
                                            //两秒后刷新
                                            setTimeout(function (){

                                                window.location.reload();
                                            }, 2000);


                                        }else{
                                            $('<div>').appendTo('body').addClass('alert alert-success').html('积分不足或者兑换错误，兑换失败').show().delay(5000).fadeOut();

                                        }

                                    }
                                })


                             }else{

                              return false;
                             }


                        }

                    }
                })




           }else{

            return false;
           }
       });




	})

</script>
</html>
