<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="head.html">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Untitled</title>
        <link rel="stylesheet" href="static/css/head.css">
        <script src="static/js/head.js"></script>
        <link rel="author" href="humans.txt">

    </head>
    <body>
        <div class="shortcut">
            <div class="layout">
                <!-- 顶部左边 -->
                <div class="s-sub">
                    <ul>
                        <li><a href="index.jsp" >首页</a></li>
                        <li><a href="index.jsp" target="_blank">华为官网</a></li>
                        <li><a href="index.jsp" target="_blank">荣耀官网</a></li>
                        <li><a href="index.jsp" target="_blank">花粉俱乐部</a></li>
                        <li><a href="index.jsp" rel="nofollow">V码(优购码)</a></li>
                        <li><a href="index.jsp" target="_blank" >企业购</a></li>
                        <li class="s-hwep hide" id="li-enterprise-preferential"></li>
                        <li><a href="javascript:;" >Select Region</a></li>
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
                    	<!-- 登录成功之后隐藏 -->
                        <li id="login_reg_">
                            <div class="header-toolbar">
                                <a id="top-index-loginUrl" href="login.jsp" rel="nofollow">请登录</a>
                                <a href="register.jsp" rel="nofollow" >&nbsp;&nbsp;注册</a>
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
						         <p class="user-img"> <a href="userServlet?method=getOrderProduct" rel="nofollow" timetype="timestamp" target="_blank" onclick="pushLoginMsg('已登录','头像')"> <img id="customerPic" src="https://res.vmallres.com/20191020/images/echannel/misc/img_logged_in.png" alt="默认头像" imgpath="https://res.vmallres.com/20191020/images" /> </a> </p>
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
						           <a href="orderServlet?mod=htmlMyOrder" timetype="timestamp" onclick="pushLoginMsg('已登录','待支付')">待支付</a>
						          </dd>
						          <dd>
						           <a href="orderServlet?mod=htmlMyOrder" timetype="timestamp" onclick="pushLoginMsg('已登录','待收货')">待收货</a>
						          </dd>
						          <dd>
						           <a href="orderServlet?mod=htmlMyOrder" timetype="timestamp" onclick="pushLoginMsg('已登录','待评价')">待评价</a>
						          </dd>
						          <dd>
						           <a href="orderServlet?mod=htmlMyOrder" timetype="timestamp" onclick="pushLoginMsg('已登录','退换货')">退换货</a>
						          </dd>
						          <dd>
						           <a href="orderServlet?mod=htmlMyOrder" timetype="timestamp" onclick="pushLoginMsg('已登录','旧机回收')">旧机回收</a>
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
						        <a href="userServlet?method=logOut" rel="nofollow">退出登录</a>
						       </div>
						      </div>
						      <!-- 2017-06-19-个人信息-end -->
						     </div>
						    </div>
						   </div>
						   </li>
                        <li class="hide"></li>
                        <li>
                            <a href="orderServlet?mod=htmlMyOrder&status=1" timetype="timestamp">我的订单</a>
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
                                    <a href="orderServlet?mod=getUserCart" class="icon-minicart">
                                        <span>购物车(<span id="cart_count"></span>)</span>
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
                                            <ul id="minicart-goods-list">


                                            	<li class="minicart-pro-item ">
												   <div class="pro-info clearfix">
												    <div class="p-choose">
												     <i class="icon-choose" id="icon-choose-86l-S0-2601010128902" type="S0" ></i>
												     <input class="hide" name="skuIds" id="checkbox-86l-S0-2601010128902" type="checkbox" checked="checked" />
												    </div>
												    <input class="hide" id="quantity-10086601038627" value="1" data-type="S0" type="text" checked="checked" />
												    <div class="p-img">
												     <a href="/product/10086452612142.html#10086601038627" title="" target="_blank" > <img src="https://res.vmallres.com/pimages//product/6901443323753/78_78_189FA79392B572C21EE009917E689200B6B6089FEB1C4ED9mp.png" alt="荣耀20S 3200万人像超级夜景 4800万超广角AI三摄 麒麟810旗舰级芯片 全网通版6GB+128GB 蝶羽蓝" /> </a>
												    </div>
												    <div class="p-name">
												     <a href="/product/10086452612142.html#10086601038627" title="荣耀20S 3200万人像超级夜景 4800万超广角AI三摄 麒麟810旗舰级芯片 全网通版6GB+128GB 蝶羽蓝" target="_blank" >荣耀20S 3200万人像超级夜景 4800万超广角AI三摄 麒麟810旗舰级芯片 全网通版6GB+128GB 蝶羽蓝</a>
												    </div>
												    <div class="p-dec">
												     <span class="p-slogan"> </span>
												    </div>
												    <div class="p-status">
												     <div class="p-price">
												      <s>
												       &yen;&nbsp;1899.00
												      </s>&nbsp;&nbsp;
												      <b>&yen;&nbsp;1599.00</b>
												      <strong><em>x</em><span>1</span></strong>
												     </div>
												    </div>
												   </div>
												   </li>



                                            </ul>
                                            <div class="minicart-pro-settleup" id="minicart-pro-settleup" style="display: none;">
                                                <p>
                                                    <span>总计：</span>
                                                    <span><b id="micro-cart-totalPrice">¥&nbsp;0</b></span>
                                                </p>
                                                <a class="button-minicart" id="button-minicart-go2confirm" href="orderServlet?mod=getUserCart" style="display: none;">结算</a>
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
        <!-- 头部开始 -->
        <div class="header">
            <div class="layout">
                <div class="left">
                    <div class="logo">
                        <a href="index.jsp">
                            <img src="static/images/SXppnESYv4K11DBxDFc2.png" alt="Vmall.com - 华为商城">
                        </a>
                    </div>
                    <div class="naver">
                        <ul>
                            <li class="img" id="huawei">
                                <a href="htmlProductServlet?mod=htmlGetProductByName" target="_blank">
                                    <img src="static/images/GrguiqzHENWVYHYWyHBM.png">
                                </a>
                            </li>
                            <li class="img" id="huawei">
                                <a href="htmlProductServlet?mod=htmlGetProductByName&pro_name=荣耀" target="_blank">
                                    <img src="static/images/AHwTUFRpDSQyuIZ7tLJz.png">
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span>华为Mate 30</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span>荣耀20</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span>华为P30</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span>荣耀9X</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- 热门搜索框 -->
                <div class="right">
                    <div class="search-bar relative">
                        <div class="search-bar-form">
                            <form action="htmlProductServlet?mod=htmlGetProductByName" method="post"  >
                                <input type="text" class="text" name="pro_name" maxlength="200" autocomplete="off" onfocus="clicks()" onblur="blurs()">
                                <input type="submit" class="button iconfont" value="">
                                <input type="hidden" id="channelType" name="channelType" value="0">
                                <input type="hidden" id="default-search" value="nova 3i|荣耀 Note10">
                            </form>
                        </div>
                        <div class="search-bar-key">
                            <div class="searchBar-key">
                                <a href="#">5G</a><a href="#" style="color:red;">荣耀20S</a>
                            </div>
                        </div>
                        <div class="search-bar-history" id="history" style="display: none;">
                            <div id="search-key" class="con">
                                <p>
                                    <label>热门搜索</label>
                                </p>
                                <ul>
                                    <li>
                                        <a href="#" target="_blank">Mate 30</a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">荣耀20i</a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">Mate 20 X (5G)</a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">nova 5i Pro</a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">荣耀9X</a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">荣耀Play3</a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">畅享10 Plus</a></li><li><a href="#" target="_blank">荣耀20</a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">nova 5</a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">nova 5 Pro</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
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

            $.ajax({
                url: 'orderServlet',
                type: 'post',
                dataType: 'json',
                data: {
                    mod:'ajaxGetUserCart'
                },
                success:function(data){
                    if (data[0].id == 0 || data.length == 0 || data[0].id == "") {
                        $('#minicart-pro-empty').attr('style','display:block;');
                        $('#cart_count').text(0);
                    }else{
                        $('#minicart-pro-empty').attr('style','display:none;');

                        $('#minicart-pro-list-block').attr('style','display:block;');

                        $('#minicart-pro-settleup').attr('style','display:block;');

                        $('#button-minicart-go2confirm').attr('style','display:block;');

                        $('#disbutton-minicart-go2confirm').attr('style','display:none;');

                        $('#cart_count').text(data.length);
                        var html = "";
                        var total = 0;
                        for (var i = 0; i < data.length; i++) {
                            //算出总价
                            total += data[i].totalPrice;
                            html += '<li class="minicart-pro-item ">'+
                                                   '<div class="pro-info clearfix">'+
                                                    '<div class="p-choose">'+
                                                    '</div>'+
                                                    '<input class="hide" id="quantity-10086601038627" value="1" data-type="S0" type="text" checked="checked" />'+
                                                    '<div class="p-img">'+
                                                     '<a href="product.jsp?id='+data[i].productId+'" title="" target="_blank" > <img src="'+data[i].productImage+'" alt="'+data[i].productName+'" /> </a>'+
                                                    '</div>'+
                                                    '<div class="p-name">'+
                                                    ' <a href="product.jsp?id='+data[i].productId+'" title="'+data[i].productName+'" target="_blank" >'+data[i].productName+'</a>'+
                                                    '</div>'+
                                                    '<div class="p-dec">'+
                                                     '<span class="p-slogan"> </span>'+
                                                    '</div>'+
                                                    '<div class="p-status">'+
                                                     '<div class="p-price">'+
                                                      '<b>&yen;&nbsp;'+data[i].totalPrice+'</b>'+
                                                      '<strong><em>x</em><span>'+data[i].quantity+'</span></strong>'+
                                                     '</div>'+
                                                    '</div>'+
                                                   '</div>'+
                                                   '</li>';

                        };

                        $('#minicart-goods-list').html(html);
                        $('#micro-cart-totalPrice').html('¥&nbsp;'+total);

                    }
                }
            })


		})
    </script>
</html>
