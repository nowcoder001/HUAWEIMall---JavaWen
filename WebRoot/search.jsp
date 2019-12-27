<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>华为商城 - 搜索结果</title>
    <link rel="stylesheet" href="static/css/ecc.core.base.min.css">
    <link rel="stylesheet" href="static/css/mainn.min.css">
    <link rel="shortcut icon" href="static/image/favicon.ico">
    <script src="static/js/jquery-2.2.1.js"></script>
</head>
<body>
<%@include file="head.jsp"  %>
<div class="search" style="background: #F5F5F5">
    <div class="layout">
            <div class="breadcrumb-area">
                    <a onclick="ec.search.clearOption();" title="全部结果">全部结果</a>&nbsp;&gt;&nbsp;<span class="bold">"${requestScope.PRO_NAME}"</span>
            </div>
        <!-- 20170118-搜索提示-end -->

        <!-- 20140726-商品类别-start -->

        <div class="pro-cate-area">
            <!-- 20190321-商品类别-属性-start -->
            <!-- 20190321-商品类别-分类-start -->
            <div id="category_box" class="pro-cate-attr clearfix">
                <div class="p-title">分类：</div>
                <div class="p-values">
                    <div class="p-expand  p-single">
                        <ul class="clearfix" id="category_ul">
                        <li id="label-zero" class=""><a href="htmlProductServlet?mod=htmlGetProductByName&categoryId=1,7,8,22,23,24,25,26,27,28,29" >手机</a></li>
                        <li id="label-1" class=""><a href="htmlProductServlet?mod=htmlGetProductByName&categoryId=2,9,10,30,31,32" >笔记本&amp;平板</a></li>
                        <li id="label-2" class=""><a href="htmlProductServlet?mod=htmlGetProductByName&categoryId=3,11,12,13,33,34,35" >智能穿戴</a></li>
                        <li id="label-4" class=""><a href="htmlProductServlet?mod=htmlGetProductByName&categoryId=4,14,15,16,36,37,38,39,40,42,42,43,44,45,46,47,48,49,50,51" >智能家居&amp;智慧屏</a></li>
                        <li id="label-5" class=""><a href="htmlProductServlet?mod=htmlGetProductByName&categoryId=20,21,68,69,70,71" >增值服务&amp;其他</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- 20190321-商品类别-分类-end -->


           <!-- 20190321-商品类别-价格-start -->

            <div id="price-box" class="pro-cate-attr clearfix">
                <div class="p-title">价格区间：</div>
                <div class="p-values">
                    <div class="p-expand">
                        <ul class="clearfix" id="price-ul">
                         <li class="">
                         <input class="price-tags" type="hidden" value="0">
                         <a href="htmlProductServlet?mod=htmlGetProductByName&min_price=0&max_price=999" >0-999元</a>
                         </li>
                         <li class="">
                         <input class="price-tags" type="hidden" value="1">
                         <a href="htmlProductServlet?mod=htmlGetProductByName&min_price=1000&max_price=1999" >1000-1999元</a>
                         </li>
                         <li class="">
                         <input class="price-tags" type="hidden" value="2">
                         <a href="htmlProductServlet?mod=htmlGetProductByName&min_price=2000&max_price=3999" >2000-3999元</a>
                         </li>
                         <li class="">
                         <input class="price-tags" type="hidden" value="3">
                         <a href="htmlProductServlet?mod=htmlGetProductByName&min_price=4000&max_price=5999" >4000-5999元</a>
                         </li>
                         <li class="">
                         <input class="price-tags" type="hidden" value="4">
                         <a href="htmlProductServlet?mod=htmlGetProductByName&min_price=6000&max_price=999999" >6000元及以上</a>
                         </li>
                        </ul>
                    </div>
                </div>
            </div>

            <!-- 20190321-商品类别-属性-end -->

            <!-- 20140726-商品类别-排序-start -->
            <div class="pro-cate-sort clearfix">
                <div class="p-title">排序：</div>
                <!--<div class="p-default">
                    <ul class="clearfix">
                        <li id="sort-register_date" class="sort-desc selected" onclick="ec.search.sort('register_date')"><a href="javascript:;" class="sort-added" title="最新">最新<s></s></a></li>
                    </ul>
                </div>-->
                <!-- 20170218 临时去掉最新（综合） 选项， p-values 添加fl类，style="margin-left=30px;"  -->
                <div class="p-values">
                    <div class="p-expand">
                        <ul class="clearfix" id="sort-type">
                            <!-- 升序选择（从低到高）： sort-asc selected   降序选择（从高到低）： sort-desc selected -->
                            <li id="sort-overAll" class="selected" onclick="ec.search.sort('overAll','sort-overAll',1)"><a href="javascript:;" data_type="relevance" title="综合">综合<s></s></a></li>
                            <li id="sort-register_date" onclick="ec.search.sort('register_date','sort-register_date',2)"><a href="javascript:;" data_type="newes" title="最新">最新<s></s></a></li>
                            <li id="sort-sale" class="" onclick="ec.search.sort('sale_number','sort-sale',3)"><a href="javascript:;" data_type="popularity" title="评价">评价<s></s></a></li>
                            <li id="sort-price" class="" onclick="ec.search.sort('price','sort-price',4)"><a href="javascript:;" class="sort-price" title="价格从低到高">价格<s></s></a></li>
                        </ul>
                    </div>
                </div>
            </div><!-- 20140726-商品类别-排序-end -->
        </div><!-- 20140726-商品类别-end -->

        <div class="hr-20"></div>

        <!-- 20140726-频道-列表-start -->
        <div class="channel-list">
                <!-- 20140727-商品列表-start -->
                <div class="pro-list">
                    <ul class=" clearfix" id="pro-list">
                    <center><font color="red" size="10">${requestScope.PRODUCT_MESSAGE }</font></center>
                    	
                    	<c:forEach items="${requestScope.PRODUCT_LIST}" var="product">
                    		<li>
                                <!-- 鼠标悬停追加ClassName：hover -->
                                <div class="pro-panels ">
                                    <input type="hidden" id="cmmdtyIndex" name="cmmdtyIndex" value="0">
                                    <input type="hidden" id="itemdata_0" skuid="10086858397117" itemid="10086341244716" itemname="HUAWEI Mate 30 Pro" data-code="${request.coding} }">
                                    <a href="product.jsp?id=${product.id}" target="_blank" >
                                        <p class="p-img"><img src="${product.imgUrl}" alt="HUAWEI Mate 30 Pro"></p>
                                        <p class="p-name" title="${product.name}">${product.name}</p>
                                        <p class="p-price"><b>¥${product.price}</b><span>多配置可选</span></p>

                                    </a>
                                </div>
                            </li>
                    	</c:forEach>
                            
                                                </ul>
                </div><!-- 20140727-商品列表-end -->
            <!-- 分页-start -->
            <div id="search-pager" class="pager">
            <ul>
            <li class="pgNext link first first-empty">&lt;
            </li>
            <li class="pgNext link pre pre-empty">&lt;
            </li><span class="qpages">
            <li class="page-number link pgCurrent">1</li><li class="page-number link">2</li><li class="page-number link">3</li><li class="page-number link">4</li><li class="page-number link">5</li><li class="text">...</li><li class="page-number link page-number-last">22</li></span><li class="pgNext link next">&gt;</li><li class="pgNext link last">&gt;|</li><li class="text quickPager"><span class="fl">绗�</span><div id="chatpage"><input id="quickPager" class="pagenum fl" value="1" style="width:20px;"><a id="enter" class="enter fl" href="javascript:void(0)"></a></div><span class="fl">&nbsp;/22&nbsp;椤�</span></li></ul></div><!-- 分页-end -->
            <div class="hr-20"></div>
        </div><!-- 20140726-频道-列表-end -->
</div>
    </div>
<%@include file="font.jsp"  %>
</body>

</html>
