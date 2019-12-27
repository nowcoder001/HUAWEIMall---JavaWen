<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">

<title>华为帐号-登录</title>


<link href="static/css/common.css" rel="stylesheet" type="text/css">

<link href="static/css/ec.core.css" rel="stylesheet" type="text/css">

<link href="static/css/dialog.css" rel="stylesheet" type="text/css">

<link href="static/css/dialogEMUIV6.css" rel="stylesheet" type="text/css">

<link href="static/css/common1.css" rel="stylesheet" type="text/css">

<link href="static/css/zh-cn.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="static/js/jquery-2.2.1.js"></script>
<link rel="shortcut icon" href="static/image/favicon.ico">
</head>
<body class="login themeName-red">

			<!-- 头部  -->
<div class="customer-header">
	<div class="head_center">
			<div class="main-logo adHeadPicContainer"> <img src="static/image/HUAWEI.png" class="adHeadPic"> </div> <div class="website-name"> <b style="font-size: 18px;margin-top: -5px;float: left;color:#cccccc;">|</b> <b class="adHeadTitle">华为商城</b> </div>
	</div>
</div>

			<!--登录 -->
			<div class="login_bg" id="loginform">
				<div style="background-image: url('static/image/login_default_bg.png');" class="g login_adBg">
					<div class="login-area login-right login-area-box">


							<div class="userAccountLogin">

							<div class="h">
										<span class="loginTitle actived loginTitle-left" data-type="account">帐号登录</span><span class="loginTitle splitLine loginTitle-right" data-type="qrCode">扫码登录</span>

							</div>
								<div class="b-account">
									<div class="login-form-marginTop">
										<div class="form-edit-area loginEventForm">
											<div class="userAccountLogin-errorTipsDiv" id="errorTip"></div>
											<table border="0" cellpadding="0" cellspacing="0">
												<tbody>
													<tr>
														<td class="user-input-tr"><input type="text" id="login_userName" name="userAccount" style="border:0px;width: 300px;height: 35px;margin-top: 15px;"/></td>
													</tr>

													<tr>
														<td class="user-input-tr"><input  id="login_password" type="password"  name="password" style="border:0px;width: 300px;height: 35px;margin-top: 15px;"/></td>
													</tr>
													<tr>
														<td id="picAuthCode" class="verify-td referenceTarget">

														</td>
													</tr>
													<tr>
														<td class="mt-links lineHeightFix" id="operLinkTd">

														</td>
													</tr>
													<tr>
														<td>
															<div style="margin-bottom:0px;"><span class="vam error" id="login_msg" style="display:block"></span></div>
															<div class="button-login" id="btnLogin" data-type="accountLogin" tabindex="5"><input type = "button" value = "登  录" id = "Userlogins" style = "border: 0px solid #c70000;background-color: #b40707;color: white;width: 300px;height: 40px" /></div>
														</td>
													</tr>
													<tr>
														<td class="mt-checkbox">
															<span id="rememberNameSpan"><input type="checkbox" class="checkbox vam" id="remember_name" name="remember_name" tabindex="4"><label for="remember_name"><i id="remember_name_icon" class="checkBox-icon tick-off-icon marginR6"></i>记住华为帐号</label></span>
															<div class="hidden remeberTip"></div>
															<span class="mt-links-float r clearWidth floatFix referenceTarget_login_more">
																<a class="btn-primary vam clearWidth" title="更多" id="btn-more">更多</a>
																<div class="set-more">
																	<div id="more_list" class="more-list hiden">
																			<ul>
																				<li id="goConFaq" class="bottom_border pop_up_dialog security-menu">遇到问题</li>
																			</ul>
																			<ul>
																				<li id="appealLink" class="bottom_border pop_up_dialog security-menu">更换手机号</li>
																			</ul>
																			<ul>
																				<li id="goSecurity" class="pop_up_dialog security-menu">安全中心</li>
																			</ul>

																	</div>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td class="mt-links p0">
															<div class="mt-links-float vam clearWidth">
																		<span class="regist"><a id="registText" class="btn-primary clearWidth" href="register.jsp" title="注册帐号">注册帐号</a></span>&nbsp;&nbsp;<span class="forgot"><a class="btn-primary clearWidth" href="javascript:void(0)" onclick="gotoresetpwd()" title="忘记密码？">忘记密码？</a></span>
															</div>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>

									<div class="thirdLoginBlock">

											<div class="thirdLintTitle"><i class="line-left"></i><span class="third-tips">其他方式登录</span><i class="line-right rotateImg"></i></div>

										<div class="thirdLoginDiv"><span class='qqLogin_bigIco' title="QQ" onclick='thirdAccountBind(this)'><a href="javascript:void(0);" tourl='/qq/authorize'><s></s></a></span><span class='alipayLogin_bigIco' title="支付宝" onclick='thirdAccountBind(this)'><a href="javascript:void(0);" tourl='/alipay/authorize'><s></s></a></span><span class='weixinLogin_bigIco' title="微信" onclick='thirdAccountBind(this)'><a href="javascript:void(0);" tourl='/weixin/authorize'><s></s></a></span></div>
									</div>

							</div>
						</div>
					</div>
				</div>
			</div>
	<div>
	<!-- 底部  -->


<div class="customer-footer">
	<div class="ft">
		<!--授权  -->
        <div class="warrant-area">
            <p style="text-align: center;line-height:20px;height:20px;">


						<a href="javascript:;" class="rule" target="_blank">华为帐号与云空间用户协议</a>
						<em class="foot_em">|</em>
						<a href="javascript:;" class="rule" target="_blank">关于华为帐号与云空间隐私的声明</a>
						<span class="foot_em"><em style='font-style: normal'>|</em> <a style="padding:0 10px;" target="blank" href="javascript:;">常见问题</a></span>

        		  <em class="foot_em">|</em>
	              <a href="javascript:;" class="rule" target="_blank">Cookies</a>

            </p>

        	<p style="text-align: center;line-height: 12px;height:12px;margin-top: 10px ">

        				Copyright © 2011-2019  华为软件技术有限公司  版权所有  保留一切权利  苏B2-20070200号 | 苏ICP备09062682号-9

        	</p>
        </div>
    </div>
</div>
	</div>
</body>

<script type="text/javascript">
	$(function(){
    $("#Userlogins").click(function() {
        if ($("#login_userName").val() == null || $("#login_userName").val() == "" || $("#login_password").val() == null || $("#login_password").val() == "") {
            $('#errorTip').html('<i class="loginErrorInfo marginR6"></i>用户名或密码不可留空');
        }else{
            $.ajax({
                url: 'userServlet',
                type: 'post',
                dataType: 'text',
                data: {
                    method:'logins',
                    userAccount:$("#login_userName").val(),
                    password:$("#login_password").val()
                },
                success:function(data){

                if(data == 1){
                    window.location.href = "index.jsp";
                }else{
                    $('#errorTip').html('<i class="loginErrorInfo marginR6"></i>帐号或密码错误');
                }

                }
            });
        }

    });

    });

</script>

</html>
