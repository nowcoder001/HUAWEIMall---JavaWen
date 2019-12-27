<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>华为商品 - 后台管理</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/icon.css">
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.min.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superBlue.css" id="themeCss"/>
    <script type="text/javascript" charset="utf-8" src="admin/js/jquery-easyui-1.8.6/theme/super/super.js"></script>
	<link rel="stylesheet" type="text/css" href="admin/css/index.css" />
	<script type="text/javascript" charset="utf-8" src="admin/js/echarts.min.js"></script>
	<script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/locale/easyui-lang-zh_CN.js"></script>
	<link rel="shortcut icon" href="static/image/favicon.ico">
  </head>

  <body id="main" class="easyui-layout">
    <div data-options="region:'north',border:false" class="super-north">
    	<!--顶部-->
			<div class="super-navigation">
				<!--系统名称-->
				<div class="super-navigation-title" style="width: 250px;">华为商城后台管理系统</div>
				<!--自定义导航-->
				<div class="super-navigation-main">
					<div class="super-setting-left">
						<ul>
							<li><i class="fa fa-commenting-o"></i>消息</li>
							<li><i class="fa fa-envelope-o"></i>邮件</li>
							<li><i class="fa fa-bell-o"></i>通知</li>
						</ul>
					</div>
					<div class="super-setting-right">
						<ul>
							<li>
								<div class="super-setting-icon">
									<i class="fa fa-gears"></i>
								</div>
								<div id="mm" class="easyui-menu">
									<div>个人中心</div>
									<div id="themeSetting">主题</div>
									<div class="menu-sep"></div>
									<div id="logout">退出</div>
								</div>
							</li>
							<li class="user">
								<span class="user-icon"><img src="admin/images/favicon.jpg"/></span>管理员
							</li>
						</ul>
					</div>
			</div>
		</div>
    </div>
    <div data-options="region:'west',title:'管理总览',border:false" class="super-west">
      <!--左侧导航-->
      <div class="easyui-accordion" data-options="border:false,fit:true,selected:true,animate:true">
        <div title="商品中心" data-options="iconCls:'fa fa-shopping-bag' " >
          <ul>
            <li title="fa fa-shopping-cart" data-url="admin/product/product.jsp"><i class="fa fa-shopping-cart"></i>&nbsp;商品列表</li>
            <li title='fa fa-reorder' data-url="admin/product/category.jsp"><i class="fa fa-reorder"></i>&nbsp;商品分类</li>
            <li title='fa fa-comments' data-url="admin/product/comment.jsp"><i class="fa fa-comments"></i>&nbsp;评价管理</li>
          </ul>
        </div>
        <div title="交易中心" data-options="iconCls:'fa fa-bar-chart'">
          <ul>
            <li title="fa fa-file-text-o" data-url='admin/order/order.jsp'><i class="fa fa-file-text-o"></i>&nbsp;订单列表</li>
            <li title="fa fa-list-alt" data-url='admin/order/moneylog.jsp'><i class="fa fa-list-alt"></i>&nbsp;资金明细</li>
          </ul>
        </div>
        <div title="营销中心" data-options="iconCls:'fa fa-usd'">
          <ul>
            <li title="fa fa-ticket" data-url='admin/coupon/coupon.jsp'><i class="fa fa-ticket"></i>&nbsp;优惠券管理</li>
          </ul>
        </div>
        <div title="用户中心" data-options="iconCls:'fa fa-user-circle-o'">
          <ul>
            <li title="fa fa-user-circle" data-url='admin/user/user.jsp'><i class="fa fa-user-circle"></i>&nbsp;用户列表</li>
          </ul>
        </div>
        <div title="控制面板" data-options="iconCls:'fa fa-cog fa-spin'">
          <ul>
            <li title="fa fa-bank" data-url='admin/market/layout.jsp'><i class="fa fa-bank"></i>&nbsp;网站设置</li>
          </ul>
        </div>
      </div>

    </div>
    <div data-options="region:'center'" style="padding-top: 2px;">
    	<div id="tt" class="easyui-tabs" data-options="border:false,fit:true" >   
		    <div title="首页总览" data-options="iconCls:'fa fa-home'" >   
		       			<!-- 首页总览 -->
						<div id="cc" fit="true" class="easyui-layout" >   
						    <div data-options="region:'north'" style="height:15%;margin:5px;">
						    	<!-- 首页总览上方嵌套布局 -->
						    	<div class="easyui-layout" fit="true">
							    <div data-options="region:'east',boder:true" style="width:33%;">
							    
							    	<div class="dfkMain">
								    	<div class="dfk_img2">
								    		<i></i>
								    	</div>
								    	<div class="dfk_info">
											<a href="#" target="_blank"><p>6</p>待发货订单</a>
										</div>
							    	</div>
							    	
							    </div>   
							    <div data-options="region:'west',border:true" style="width:33%;">
							    
							    	<div class="dfkMain">
								    	<div class="dfk_img1">
								    		<i></i>
								    	</div>
								    	<div class="dfk_info">
											<a href="#" target="_blank"><p>10</p>待付款订单</a>
										</div>
							    	</div>
							    	
							    </div>   
							    <div data-options="region:'center' ,border:true" >
							    
							    	<div class="dfkMain">
								    	<div class="dfk_img3">
								    		<i></i>
								    	</div>
								    	<div class="dfk_info">
											<a href="#" target="_blank"><p>7</p>退款货</a>
										</div>
							    	</div>
							    	
							    </div>   
							</div> 
						    </div>   
						    <div title="流量统计" data-options="region:'south' ,collapsible:false" style="height:45%;margin:5px;">
						    	<!-- 统计图表 -->
						    	<div id="map-main" style="width: 470px;height:270px; float:left;"></div>
						    	
								<div>
									<div class="gzhfs_right">
										<ul>
										<li class="shop_ico3">
											<div class="padd10">
												<h3>429</h3>
												<div class="mat5">今日访客</div>
											</div>
										</li>
										<li class="shop_ico3">
											<div class="padd10">
												<h3>9</h3>
												<div class="mat5">今日注册</div>
											</div>
										</li>
										<li class="shop_ico3">
											<div class="padd10">
												<h3>2</h3>
												<div class="mat5">今日签到</div>
											</div>
										</li>
										<li class="shop_ico3">
											<div class="padd10">
												<h3>543</h3>
												<div class="mat5">昨日访客</div>
											</div>
										</li>
										<li class="shop_ico3" style="margin-bottom:20px">
											<div class="padd10">
												<h3>0</h3>
												<div class="mat5">昨日注册</div>
											</div>
										</li>
										<li class="shop_ico3" style="margin-bottom:20px;">
											<div class="padd10">
												<h3>0</h3>
												<div class="mat5">昨日签到</div>
											</div>
										</li>
										</ul>
									</div>
								</div>
						    </div> 
						    <div title="数据总览" data-options="region:'center' "  style="margin-left:5px;">
						    
						    	<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="wenzhang_bak tj_tb">
									<tbody><tr>
										<td class="aright" width="80"><span>商品总数：</span></td>
										<td><a href="#" target="_blank">27 <span>个</span></a></td>
									</tr>
									<tr style="background:#f9fafc">
										<td class="aright"><span>商品分类：</span></td>
										<td><a href="#" target="_blank">51 <span>个</span></a></td>
									</tr>
									<tr>
										<td class="aright"><span>商品品牌：</span></td>
										<td><a href="#" target="_blank">8 <span>个</span></a></td>
									</tr>
									<tr style="background:#f9fafc">
										<td class="aright"><span>评价总数：</span></td>
										<td><a href="#" target="_blank">5 <span>个</span></a></td>
									</tr>
									<tr>
										<td class="aright"><span>订单总数：</span></td>
										<td><a href="#" target="_blank">51 <span>个</span></a></td>
									</tr>
									<tr style="background:#f9fafc">
										<td class="aright"><span>退款退货：</span></td>
										<td><a href="#" target="_blank">21 <span>个</span></a></td>
									</tr>
									</tbody>
									</table>
									
									<table width="100%" border="0" cellspacing="0" cellpadding="0" class="wenzhang_bak tj_tb">
										<tbody><tr>
											<td class="aright" width="80"><span>访客总数：</span></td>
											<td><a href="admin.php?mod=tongji" target="_blank">2927 <span>人</span></a></td>
										</tr>
										<tr style="background:#f9fafc">
											<td class="aright" width="80"><span>会员总数：</span></td>
											<td><a href="admin.php?mod=user" target="_blank">9 <span>人</span></a></td>
										</tr>
										<tr>
											<td class="aright"><span>账户余额：</span></td>
											<td><a href="admin.php?mod=moneylog" target="_blank">0.0 <span>元</span></a></td>
										</tr>
										<tr style="background:#f9fafc">
											<td class="aright"><span>积分余额：</span></td>
											<td><a href="admin.php?mod=pointlog" target="_blank">266 <span>个</span></a></td>
										</tr>
										<tr>
											<td class="aright" width="80"><span>提现总数：</span></td>
											<td><a href="admin.php?mod=cashout" target="_blank">10 <span>笔</span></a></td>
										</tr>
										<tr style="background:#f9fafc">
											<td class="aright"><span>签到总数：</span></td>
											<td><a href="admin.php?mod=sign&amp;act=list" target="_blank">5 <span>次</span></a></td>
										</tr>
										</tbody></table>
						    </div>   
						</div> 
				
		    </div>
		</div>  
    </div>

	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('map-main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '华为商城销量统计图'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["手机","笔记本电脑","智能穿戴","智能家具","热销配件","增值服务"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	
	</script>
	<script type="text/javascript" src="admin/js/index.js"></script>
</body>
</html>
