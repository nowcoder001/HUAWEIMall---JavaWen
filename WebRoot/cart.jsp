<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
  <meta name="keywords" content="华为商城,华为,华为官网, 华为荣耀,华为手机官网,荣耀手机,华为荣耀, 荣耀3C,荣耀畅玩版,荣耀3X,荣耀X1,华为P7,vmall,huawei">
  <meta name="description" content="华为商城是华为旗下面向全国服务的电子商务平台官网，提供正品华为手机、荣耀手机(华为荣耀3C、畅玩版、3X、X1、华为P7等)、平板电脑、配件等华为、荣耀产品。品质保证！">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <title>华为商城</title>
<link href="static/css/app.b417256.css" rel="stylesheet"></head>
<script src="static/js/jquery-2.2.1.js"></script>
<link rel="shortcut icon" href="static/image/favicon.ico">
<body>

	<div id="app">
   <div class="sc">
    <div class="shortcut">
     <div class="layout">
      <div class="s-sub">
       <ul>
        <li><a href="index.jsp" target="_blank">首页</a></li>
        <li><a href="index.jsp" target="_blank">华为官网</a></li>
        <li><a href="index.jsp" target="_blank">荣耀官网</a></li>
        <li><a href="index.jsp" target="_blank">花粉俱乐部</a></li>
        <li><a href="index.jsp" target="_blank">V码(优购码)</a></li>
        <li><a href="index.jsp" target="_blank">企业购</a></li> 
        <!----> 
        <li><a href="index.jsp">Select Region</a></li> 
        <li>
         <div class="s-dropdown">
          <div class="h">
           <a class="icon-dropdown">更多精彩</a>
          </div> 
          <div class="b">
           <div class="dropdown-more w-119">
            <dt>
             <a href="#" target="_blank">EMUI</a>
            </dt> 
            <dt>
             <a href="#" target="_blank">应用市场</a>
            </dt> 
            <dt>
             <a href="#" target="_blank">华为终端云空间</a>
            </dt> 
            <dt>
             <a href="#" target="_blank">开发者联盟</a>
            </dt>
           </div>
          </div>
         </div></li>
       </ul>
      </div> 
      <div class="s-main s-main-no-minicart">
       <ul>
        <li>
         <div class="header-toolbar">
          <div class="s-dropdown">
           <div class="h">
            <a href="#" rel="nofollow" target="_blank" class="icon-dropdown"><span>156******42</span></a>&nbsp; 
           </div> 
           <div class="b">
            <div class="dropdown-i-mall">
             <div class="i-mall-prompt clearfix">
              <div class="user-head fl">
               <p class="user-img"><a href="#" rel="nofollow" timetype="timestamp" target="_blank"><img src="https://res.vmallres.com/20191020/staticpc/img/img_logged_in.png" alt="默认头像" id="customerPic" /></a></p>
              </div> 
              <div class="user-info fl">
               <div id="user-vip-level-tips-index" class="user-level icon-vip-level-0">
                <em></em> 
                <p><span id="canvas-index" style="width: 0%;"></span></p>
               </div> 
               <div id="vip-info" class="user-info-detail clearfix">
                <a id="authentication_n" href="/authmember/accesstoken" rel="nofollow" class="icon-realname disabled">未实名</a> 
                <a href="#" rel="nofollow" timetype="timestamp" class="icon-mail">消息(<span id="top-newMsgCount">0</span>)</a>
               </div>
              </div>
             </div> 
             <div class="i-mall-uc">
              <dl class="clearfix">
               <dt>
                <span class="fl">我的订单</span>
                <a href="#" timetype="timestamp" class="fr">更多</a>
               </dt>
              </dl> 
              <div class="i-mall-uc-con">
               <dl class="clearfix">
                <dd>
                 <a href="#" timetype="timestamp">待支付</a>
                </dd> 
                <dd>
                 <a href="#" timetype="timestamp">待收货</a>
                </dd> 
                <dd>
                 <a href="#" timetype="timestamp">待评价</a>
                </dd> 
                <dd>
                 <a href="#" timetype="timestamp">退换货</a>
                </dd> 
                <dd>
                 <a href="#" timetype="timestamp">旧机回收</a>
                </dd>
               </dl>
              </div>
             </div> 
             <div class="i-mall-huaban">
              <ul class="clearfix">
               <li><a href="#" target="_blank" id="point"><p class="p-price"><span id="userPointBalance">5</span></p> <p class="p-dec">积分</p></a></li> 
               <li><a href="#" rel="nofollow" target="_blank"><p class="p-price"><span id="top-couponCount">4</span></p> <p class="p-dec">优惠券</p></a></li> 
               <li><a href="#" rel="nofollow" target="_blank"><p class="p-price"><span id="balanceAmount">0.00</span></p> <p class="p-dec">代金券</p></a></li>
              </ul>
             </div> 
             <div class="i-out">
              <a href="#" rel="nofollow">退出登录</a>
             </div>
            </div>
           </div>
          </div>
         </div></li> 
        <li class="hide"></li> 
        <li><a href="orderServlet?mod=htmlMyOrder&status=1" timetype="timestamp" target="_blank">我的订单</a></li> 
        <li>
         <div class="s-dropdown s-dropdown-link">
          <div class="h">
           <a href="#" target="_blank" class="icon-dropdown">客户服务</a>
          </div> 
          <div class="b">
           <div class="dropdown-more">
            <dl>
             <dt>
              <a href="#" target="_blank">服务中心</a>
             </dt> 
             <dt>
              <a href="#" target="_blank">联系客服</a>
             </dt>
            </dl>
           </div>
          </div>
         </div></li> 
        <li>
         <div class="s-dropdown">
          <div class="h">
           <a href="#" target="_blank" class="icon-dropdown">网站导航</a>
          </div> 
          <div class="b">
           <div class="dropdown-navs clearfix">
            <a href="#">
             <div class="dropdown-navs-icon">
              <span></span>商城首页
             </div></a> 
            <dl>
             <dt>
              频道
             </dt> 
             <dd>
              <div class="item">
               <a href="#" target="_blank">华为专区</a>
              </div> 
              <div class="item">
               <a href="#" target="_blank">荣耀专区</a>
              </div> 
              <div class="item">
               <a href="#" target="_blank">企业购</a>
              </div>
             </dd>
            </dl> 
            <dl>
             <dt>
              产品
             </dt> 
             <dd>
              <div class="item">
               <a href="#" target="_blank">手机</a>
              </div> 
              <div class="item">
               <a href="#" target="_blank">智能家居</a>
              </div> 
              <div class="item">
               <a href="#" target="_blank">平板&amp;笔记本</a>
              </div> 
              <div class="item">
               <a href="#" target="_blank">通用配件</a>
              </div> 
              <div class="item">
               <a href="#" target="_blank">智能穿戴</a>
              </div> 
              <div class="item">
               <a href="#" target="_blank">专属配件</a>
              </div>
             </dd>
            </dl> 
            <dl>
             <dt>
              增值服务
             </dt> 
             <dd>
              <div class="item">
               <a href="#" target="_blank">以旧换新</a>
              </div> 
              <div class="item">
               <a href="#" target="_blank">礼品包装</a>
              </div> 
              <div class="item">
               <a href="#" target="_blank">补购保障</a>
              </div> 
              <div class="item">
               <a href="#" target="_blank">99元换电池</a>
              </div>
             </dd>
            </dl> 
            <dl>
             <dt>
              会员
             </dt> 
             <dd>
              <div class="item">
               <a href="#" target="_blank">会员频道</a>
              </div>
             </dd>
            </dl>
           </div>
          </div>
         </div></li> 
        <li>
         <div class="s-dropdown">
          <div class="h">
           <a href="#" class="icon-dropdown">手机版</a>
          </div> 
          <div class="b">
           <div class="dropdown-code">
            <div class="clearfix dropdown-code-detail">
             <a href="#" target="_blank"><img src="https://res.vmallres.com/pimages//sale/2018-12/vdXKxUJLNEyWA82x5BBx.jpg" class="code-img" /></a> 
             <div class="code-info">
              <h2>华为商城APP</h2> 
              <p class="red">新人专享好礼<br />最高获5000积分</p> 
              <span class="icon-andrid fl mgr-5"></span> 
              <span class="icon-ios fl mgr-5"></span> 
              <span class="icon-wechat fl"></span>
             </div>
            </div> 
            <div class="clearfix dropdown-code-detail">
             <a><img src="https://res.vmallres.com/pimages/sale/2018-11/20181116180507506.jpg" class="code-img" /></a> 
             <div class="code-info">
              <h2>华为商城公众号</h2> 
              <p>微信扫一扫</p> 
              <span class="icon-wechat"></span>
             </div>
            </div> 
            <div class="clearfix dropdown-code-detail">
             <a><img src="https://res.vmallres.com/pimages//sale/2018-12/6Y7SW4v4hnJ9kl9fDC9d.jpg" class="code-img" /></a> 
             <div class="code-info">
              <h2>微信小程序</h2> 
              <p>微信扫一扫</p> 
              <span class="icon-wechat"></span>
             </div>
            </div>
           </div>
          </div>
         </div></li>
       </ul>
      </div>
     </div> 
     <!---->
    </div> 
    <div class="header order-header">
     <div class="layout">
      <div class="left">
       <div class="logo logo-word">
        <a href="#" title="华为商城"><img src="https://res.vmallres.com/pimages//sale/2019-01/AF2GXbxrWmRqvyNfYzCl.png" alt="华为商城" /></a> 
        <span>我的购物车</span>
       </div>
      </div> 
      <div class="right">
       <div class="progress-area progress-area-3">
        <div id="progress-cart" class="progress-sc-area">
         我的购物车
        </div>
       </div>
      </div>
     </div>
    </div> 
    <div class="layout ">
     <!----> 
     <!----> 
     <div class="hr-20"></div> 
     <div class="sc-list">
      <div class="sc-pro-title clearfix">
       <label class="checkbox"> 选择</label> 
       <ul class="clearfix">
        <li>商品</li> 
        <li>单价</li> 
        <li>数量</li> 
        <li>小计</li> 
        <li>操作</li>
       </ul>
      </div> 
      <form id="cart-form" autocomplete="off" method="get">
       <div class="sc-pro">
        <div id="price_count">
        
        <c:forEach items="${requestScope.CART_LIST }" var="cart" varStatus="row">
        	<div class="sc-pro-list clearfix" id="div_${row.index }">
          <label class="checkbox"><input readonly="readonly" class="vam" data-value="${cart.id }" id="${row.index }" /> </label> 
          <div class="sc-pro-area">
           <div class="sc-pro-main clearfix">
            <a href="#" target="_blank" class="p-img"><img src="${cart.productImage }" alt="${cart.productName }" /></a> 
            <div class="tips-1 p-stock-tips" style="display: none;">
             限购件 
            </div> 
            <ul>
             <li><a href="#" target="_blank" title="${cart.productName }" class="p-name">
               <!----> ${cart.productName }</a> 
              <!----> 
              <!----></li> 
             <li>
              <div class="p-price">
               <span>&yen;&nbsp;${cart.current_unit_price }</span> 
              </div></li> 
             <li>
              <div class="p-stock">
               <div class="p-stock-area">
                <input type="number" class="p-stock-text" value="${cart.quantity }"/> 
                <p class="p-stock-btn"><a href="#" class="disabled">−</a> <a href="#" class="">+</a></p>
               </div>
              </div></li> 
             <li class="p-price-total${row.index }" data-value="${cart.totalPrice }">&yen;&nbsp;${cart.totalPrice }</li> 
             <li><a href="javascript:;" seed="cart-item-del" class="p-del" data-id="${cart.id }" data-index="${row.index }">删除</a></li>
            </ul>
           </div> 
           <!----> 
           <!----> 
           
          </div>
         </div> 
        </c:forEach>
         
         
         
         
         <!---->
        </div>
		
		
		
       </div>
      </form>
     </div> 
     <div id="total_tool"></div> 
     <div class="">
   <div class="sc-total-tool layout clearfix ">
    <div class="sc-total-control">
     <label class="checkbox"><input id="checkall" readonly="readonly" class="vam" /> 全选</label> 
     <a href="javascript:;">删除</a>
    </div> 
    <div class="sc-total-btn ">
     <a href="javascript:;" id="sub-button">立即结算</a>
    </div> 
    <div class="sc-total-price">
     <p><label>总计：</label> <span id="totalPri">&yen;&nbsp; 0</span> <em><b>不含运费</b></em></p> 
     <div class="total-choose">
      已选择 
      <em id="count_">0</em>件商品
     </div>
    </div>
   </div>
  </div>
     <div id="invalidItemList" style="display: none;">
      <div class="box-errors">
       <span>以下商品暂不可购买</span>
      </div> 
      <div class="order-no-list"></div>
     </div> 
    </div> 
    <div  style="margin-top:50px;">
     <div   class="slogan-container">
      <div   class="slogan">
       <ul  >
        <li   class="s1"><a   target="_blank" href="https://www.vmall.com/help/faq-934.html" rel="nofollow"><i  ></i>百强企业&nbsp;品质保证</a></li> 
        <li   class="s2"><a   target="_blank" href="https://www.vmall.com/help/faq-834.html" rel="nofollow"><i  ></i>7天退货&nbsp;15天换货</a></li> 
        <li   class="s3"><a   target="_blank" href="https://www.vmall.com/help/faq-4367.html" rel="nofollow"><i  ></i> <span  >48元起免运费</span></a></li> 
        <li   class="s4"><a   target="_blank" href="http://consumer.huawei.com/cn/support/service-center/index.htm" rel="nofollow"><i  ></i>1000家维修网点&nbsp;全国联保</a></li>
       </ul>
      </div>
     </div> 
     <div   class="service-container">
      <div   class="service">
       <div   class="service-l fl">
        <dl   class="s1">
         <dt  >
          <p   class="title">购物相关</p>
         </dt> 
         <dd  >
          <ol  >
           <li  ><a   target="_blank" href="https://www.vmall.com/help/faq-4355.html" rel="nofollow">购物指南</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/help/faq-4367.html" rel="nofollow">配送方式</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/help/faq-4357.html" rel="nofollow">支付方式</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/help/category-16.html" rel="nofollow">常见问题</a></li>
          </ol>
         </dd>
        </dl> 
        <dl   class="s2">
         <dt  >
          <p   class="title">保修与退换货</p>
         </dt> 
         <dd  >
          <ol  >
           <li  ><a   target="_blank" href="https://www.vmall.com/help/faq-833.html" rel="nofollow">保修政策</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/help/faq-834.html" rel="nofollow">退换货政策</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/help/faq-5259.html" rel="nofollow">退换货流程</a></li> 
           <li  ><a   target="_blank" href="http://consumer.huawei.com/cn/support/warranty-query/index.htm" rel="nofollow">保修状态查询</a></li> 
           <li  ><a   target="_blank" href="http://c-ver.huawei.com/web/" rel="nofollow">配件防伪查询</a></li>
          </ol>
         </dd>
        </dl> 
        <dl   class="s3">
         <dt  >
          <p   class="title">维修与技术支持</p>
         </dt> 
         <dd  >
          <ol  >
           <li  ><a   target="_blank" href="http://consumer.huawei.com/cn/support/service-center/index.htm" rel="nofollow">售后网点</a></li> 
           <li  ><a   target="_blank" href="http://consumer.huawei.com/cn/support/reservation/index.htm" rel="nofollow">预约维修</a></li> 
           <li  ><a   target="_blank" href="http://consumer.huawei.com/cn/support/express-repair/index.htm" rel="nofollow">手机寄修</a></li> 
           <li  ><a   target="_blank" href="http://consumer.huawei.com/cn/support/sparepart-price/index.htm" rel="nofollow">备件价格查询</a></li> 
           <li  ><a   target="_blank" href="http://consumer.huawei.com/cn/support/door-to-door-service/" rel="nofollow">上门服务</a></li>
          </ol>
         </dd>
        </dl> 
        <dl   class="s4">
         <dt  >
          <p   class="title">特色服务</p>
         </dt> 
         <dd  >
          <ol  >
           <li  ><a   target="_blank" href="https://consumer.huawei.com/cn/support/distinguish/" rel="nofollow">防伪查询</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/order/tcsProductIndex" rel="nofollow">补购保障</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/recycle" rel="nofollow">以旧换新</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/product/10086344499475.html#10086230774569" rel="nofollow">礼品包装</a></li>
          </ol>
         </dd>
        </dl> 
        <dl   class="s5">
         <dt  >
          <p   class="title">关于我们</p>
         </dt> 
         <dd  >
          <ol  >
           <li  ><a   target="_blank" href="https://www.huawei.com/cn/about-huawei/corporate-information" rel="nofollow">公司介绍</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/help/faq-939.html" rel="nofollow">华为商城简介</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/offshop/hwshoplist" rel="nofollow">华为线下门店</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/offshop/shoplist" rel="nofollow">荣耀线下门店</a></li> 
           <li  ><a   target="_blank" href="https://www.vmall.com/job" rel="nofollow">诚征英才</a></li> 
           <li  ><a   href="javascript:;" title="意见反馈">意见反馈</a></li>
          </ol>
         </dd>
        </dl> 
        <dl   id="footerS6Swiper" class="s6 swiper-container">
         <dt   class="relative">
          <p   class="title">友情链接</p> 
          <p   class="button"><span   class="btn btn-prev disabled"> &lt; </span> <span   class="btn btn-next"> &gt; </span></p>
         </dt> 
         <dd   class="swiper-wrapper service-list clearfix" style="height: 108.696px; width: 1379.96px;">
          <ol   class="swiper-slide swiper-slide-visible swiper-slide-active" style="width: 153.329px; height: 108.696px;">
           <li  ><a  >华为集团</a></li> 
           <li  ><a   href="https://consumer.huawei.com/cn/" target="_blank" textvalue="华为CBG官网">华为CBG官网</a></li> 
           <li  ><a  >荣耀官网</a></li> 
           <li  ><a   textvalue="花粉俱乐部">花粉俱乐部</a></li> 
           <li  ><a   href="https://www.vmall.com/shopdc/sitemap.html" target="_blank">网站地图</a><br   /></li>
          </ol> 
          <ol   class="swiper-slide" style="width: 153.329px; height: 108.696px;">
           <li  ><a  >爱旅官网</a></li> 
           <li  ><a  >万表网</a></li> 
           <li  ><a  >中商情报网</a></li> 
           <li  ><a  >刷机精灵</a></li>
          </ol> 
          <ol   class="swiper-slide" style="width: 153.329px; height: 108.696px;">
           <li  ><a   textvalue="华为手机">华为手机</a></li> 
           <li  ><a  >智能电视</a></li> 
           <li  ><a  >UC浏览器</a></li> 
           <li  ><a  >中关村商城</a></li>
          </ol> 
          <ol   class="swiper-slide" style="width: 153.329px; height: 108.696px;">
           <li  ><a  >酷狗音乐</a></li> 
           <li  ><a  >百信手机网</a></li> 
           <li  ><a  >卡宝宝网</a></li> 
           <li  ><a   textvalue="多特软件下载">多特软件下载</a></li>
          </ol> 
          <ol   class="swiper-slide" style="width: 153.329px; height: 108.696px;">
           <li  ><a  >同步助手</a></li> 
           <li  ><a  >蜂鸟摄影网</a></li> 
           <li  ><a  >奇珀论坛</a></li> 
           <li  ><a  >家具商城</a></li>
          </ol> 
          <ol   class="swiper-slide" style="width: 153.329px; height: 108.696px;">
           <li  ><a  >欧宝丽珠宝</a></li> 
           <li  ><a  >亿智蘑菇</a></li> 
           <li  ><a  >安卓市场</a></li> 
           <li  ><a  >阿里巴巴生意经</a></li>
          </ol> 
          <ol   class="swiper-slide" style="width: 153.329px; height: 108.696px;">
           <li  ><a  >手机大全</a></li> 
           <li  ><a  >安卓软件园</a></li> 
           <li  ><a  >智机论坛</a></li> 
           <li  ><a  >电子产品世界</a></li>
          </ol> 
          <ol   class="swiper-slide" style="width: 153.329px; height: 108.696px;">
           <li  ><a  >历趣网</a></li> 
           <li  ><a  >网购返利</a></li> 
           <li  ><a   title="Apple110">Apple110</a></li> 
           <li  ><a   textvalue="91手机门户网">91手机门户网</a></li> 
           <li  ><a  >移动叔叔</a></li>
          </ol> 
          <ol   class="swiper-slide" style="width: 153.329px; height: 108.696px;">
           <li  ><a  >荣耀Magic</a></li> 
           <li  ><a  >携程租车</a></li>
          </ol>
         </dd>
        </dl>
       </div> 
       <div   class="service-r fl">
        <dl   class="s7">
         <dt  >
          <p   class="title">400-088-6888</p>
         </dt> 
         <dd  >
          <ol  >
           <li  ><a  >7x24小时客服热线（仅收市话费）</a></li>
          </ol>
         </dd> 
         <dd  >
          <a   rel="nofollow" href="https://celia.consumer.huawei.com/vmall/#/?businessUUID=902d812b8bdb44e089e34544fb5d77e2&amp;k=1&amp;from=06&amp;remark=&amp;enterurl=https%3A%2F%2Fwww.vmall.com%2Fcart2%3Ft%3D15732227365401573222792903" target="_blank" class="service-btn btn-line-primary"><i  ></i> 在线客服</a>
         </dd>
        </dl> 
        <div   class="service-code clearfix">
         <h2  >关注Vmall：</h2> 
         <ul   class="clearfix">
          <li   class="iconfont"><a   href="javascript:;"></a> 
           <div   class="service-code-img">
            <img   src="https://res.vmallres.com/pimages/sale/2018-11/20181116180507506.jpg" />
           </div></li> 
          <li   class="iconfont"><a   target="_blank" href="https://www.toutiao.com/c/user/52373534151/#mid=52586950033" rel="nofollow"></a></li> 
          <li   class="iconfont"><a   target="_blank" href="https://weibo.com/shophuawei" rel="nofollow"></a></li> 
          <li   class="iconfont"><a   target="_blank" href="https://club.huawei.com/" rel="nofollow"></a></li>
         </ul>
        </div>
       </div>
      </div>
     </div> 
     <div   class="hungBar">
      <div   class="hungBar-content">
       <a   onclick="pushMyCartMsg()" href="/cart2" timetype="timestamp" title="购物车" class="hungBar-cart"><span  >3</span> <i  >购物车</i></a> 
       <a   href="https://celia.consumer.huawei.com/vmall/#/?businessUUID=902d812b8bdb44e089e34544fb5d77e2&amp;k=1&amp;from=06&amp;remark=&amp;enterurl=https%3A%2F%2Fwww.vmall.com%2Fcart2%3Ft%3D15732227365401573222792903" title="在线客服" target="_blank" class="hungBar-service" style="display: block;"><i  >在线客服</i></a> 
       <a   href="javascript:;" title="意见反馈" class="hungBar-feedback" style="display: block;"><i  >意见反馈</i></a> 
       <!---->
      </div>
     </div> 
     <div   class="footer-container">
      <div   class="footer">
       <div   class="footer-warrant-area clearfix">
        <p   class="footer-warrant-logo fl"><a   href="https://www.vmall.com"><img   src="https://res.vmallres.com/pimages/sale/2019-01/wLelYbpMVWbj9Xpaa5Fk.png" /></a></p> 
        <div   class="fl">
         <ul   class="footer-warrant-link">
          <li  ><a   href="https://www.huawei.com/cn/">华为集团</a></li> 
          <li  ><a   href="https://consumer.huawei.com/cn/">华为CBG官网</a></li> 
          <li  ><a   href="http://www.honor.cn/">荣耀官网</a></li> 
          <li  ><a   href="https://club.huawei.com/">花粉俱乐部</a></li> 
          <li  ><a   href="http://appstore.huawei.com/">华为应用市场</a></li> 
          <li  ><a   href="https://emui.huawei.com/cn/">EMUI</a></li> 
          <li  ><a   href="https://cloud.huawei.com">华为终端云空间</a></li> 
          <li  ><a   href="https://developer.huawei.com/cn/">开发者联盟</a></li> 
          <li  ><a   href="https://m.vmall.com/">华为商城手机版</a></li> 
          <li  ><a   href="https://www.vmall.com/shopdc/sitemap.html">网站地图</a></li>
         </ul> 
         <a   href="https://www.vmall.com/help/faq-2635.html" target="_blank" title="隐私声明" rel="nofollow">隐私声明</a> 
         <a   href="https://www.vmall.com/help/faq-778.html" target="_blank" title="服务协议" rel="nofollow"> 服务协议</a> 
         <a   href="https://www.vmall.com/help/faq-11810.html" target="_blank" title="COOKIES" rel="nofollow"> COOKIES</a> Copyright &copy; 2012-2019 华为终端有限公司 版权所有 
         <a   target="_blank" href="http://beian.miit.gov.cn/" rel="nofollow">粤ICP备19015064号-4</a>
         <em  >|</em>
         <a   href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=44190002003939" target="_blank" rel="nofollow">粤公网安备 44190002003939号</a>
         <em  >|</em>备案主体编号：44201919072182 
        </div> 
        <p   class="fr footer-warrant-img"><a   href="http://privacy.truste.com/privacy-seal/validation?rid=81be9ca0-c821-4e2c-ad4b-ff7cff98f75f&amp;amp;lang=zh-cn" target="_blank" rel="nofollow" class="fl"><img   src="https://res.vmallres.com/pimages//sale/2018-05/20180522101715775.jpg" alt="TRUSTe隐私认证" title="TRUSTe隐私认证" /></a><a   href="https://res.vmallres.com/pimages//sale/2018-06/20180615181402966.jpg" target="_blank" rel="nofollow" class="fl"><img   src="https://res.vmallres.com/pimages//template/content/2016/20160226162415360.png" alt="电子营业执照" title="电子营业执照" /></a></p>
       </div>
      </div>
     </div> 
     <!---->
    </div> 
    <div data-v-0ab31ad2="" style="display: none;">
     <div data-v-0ab31ad2="" class="ol_box_mask" style="visibility: visible; width: 100%; height: 100%; z-index: 500;"></div> 
     <div data-v-0ab31ad2="" class="ol_box_4" style="visibility: visible; position: fixed; top: 50%; margin-top: -250px; left: 50%; margin-left: -350px; z-index: 500; width: 700px;">
      <div data-v-0ab31ad2="" class="box-ct">
       <div data-v-0ab31ad2="" class="box-header">
        <a data-v-0ab31ad2="" title="关闭" class="box-close"></a> 
        <span data-v-0ab31ad2="" class="box-title">- 领取优惠券 -</span>
       </div> 
       <div data-v-0ab31ad2="" class="box-content">
        <div data-v-0ab31ad2="" class="product-roll">
         <div data-v-0ab31ad2="" class="product-roll-list">
          <ul data-v-0ab31ad2="" class="clearfix"></ul>
         </div> 
         <p data-v-0ab31ad2="" class=""><span data-v-0ab31ad2=""></span> 
          <!----> 
          <!----> 
          <!----> 
          <!----></p>
        </div>
       </div>
      </div>
     </div>
    </div> 
    <div id="gift2019Chooser" style="display: none;">
     <div class="ol_box_mask" style="width: 100%; height: 100%; z-index: 499;"></div> 
     <div class="ol_box_4" style="width: 700px; margin-left: -350px; height: 568px; margin-top: -284px; position: fixed; left: 50%; z-index: 500; top: 50%;">
      <div class="box-header">
       <div class="box-tl"></div> 
       <div class="box-tc">
        <div class="box-tc1"></div> 
        <div class="box-tc2">
         <a title="关闭" class="box-close"></a>
         <span class="box-title">赠品选择</span>
        </div>
       </div> 
       <div class="box-tr"></div>
      </div> 
      <div class="box-content" style="padding-bottom: 0px;">
       <!----> 
      </div> 
      <div class="box-button">
       <a class="box-cancel"><span>取消</span></a> 
       <a class="box-ok"><span>确认</span></a>
      </div>
     </div>
    </div>
   </div>
  </div>
<script type="text/javascript">!function(e){var r=window.webpackJsonp;window.webpackJsonp=function(n,c,a){for(var i,u,s,f=0,l=[];f<n.length;f++)u=n[f],t[u]&&l.push(t[u][0]),t[u]=0;for(i in c)Object.prototype.hasOwnProperty.call(c,i)&&(e[i]=c[i]);for(r&&r(n,c,a);l.length;)l.shift()();if(a)for(f=0;f<a.length;f++)s=o(o.s=a[f]);return s};var n={},t={3:0};function o(r){if(n[r])return n[r].exports;var t=n[r]={i:r,l:!1,exports:{}};return e[r].call(t.exports,t,t.exports,o),t.l=!0,t.exports}o.e=function(e){var r=t[e];if(0===r)return new Promise(function(e){e()});if(r)return r[2];var n=new Promise(function(n,o){r=t[e]=[n,o]});r[2]=n;var c=document.getElementsByTagName("head")[0],a=document.createElement("script");a.type="text/javascript",a.charset="utf-8",a.async=!0,a.timeout=12e4,o.nc&&a.setAttribute("nonce",o.nc),a.src=o.p+"staticpc/js/"+e+"."+{0:"2bae34f"}[e]+".js";var i=setTimeout(u,12e4);function u(){a.onerror=a.onload=null,clearTimeout(i);var r=t[e];0!==r&&(r&&r[1](new Error("Loading chunk "+e+" failed.")),t[e]=void 0)}return a.onerror=a.onload=u,c.appendChild(a),n},o.m=e,o.c=n,o.d=function(e,r,n){o.o(e,r)||Object.defineProperty(e,r,{configurable:!1,enumerable:!0,get:n})},o.n=function(e){var r=e&&e.__esModule?function(){return e.default}:function(){return e};return o.d(r,"a",r),r},o.o=function(e,r){return Object.prototype.hasOwnProperty.call(e,r)},o.p="https://res.vmallres.com/20191020/",o.oe=function(e){throw console.error(e),e}}([]);</script><script type="text/javascript" src="static/js/vendor.3939be2.js"></script>
<script type="text/javascript" src="static/js/app.2f0c840.js"></script>
<script type="text/javascript" src="static/js/cart.js"></script>
</body>

</html>
