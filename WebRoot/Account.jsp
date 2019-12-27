<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>账号中心</title>


    <link href="Style/css/ec.core.css" rel="stylesheet" type="text/css">
    <link href="Style/css/jquery-ui.min.css" rel="stylesheet" type="text/css">
    <link href="Style/css/jquery.cascrop.css" rel="stylesheet" type="text/css">
    <link href="Style/css/common.css" rel="stylesheet" type="text/css">
    <link href="Style/css/dialog.css" rel="stylesheet" type="text/css">
    <link href="Style/css/common(1).css" rel="stylesheet" type="text/css">
    <link href="Style/css/zh-cn_css.css" rel="stylesheet" type="text/css">
	<link rel="shortcut icon" href="static/image/favicon.ico">
    <script type="text/javascript" src="static/js/jquery-2.2.1.js"></script>





</head>



<body class="userCenter-ucSecurity">
    <div class="topbar1">
        <div class="tp wp mkcl">
            <div class="childLogo1">
                 <span class="s1"> <img src="static/image/head-top.png"></span>
            </div>
        <div class="tbr1">



						<b class="user"></b>


				<a id= "user_iphone">${sessionScope.LOGIN_USER.phone}</a>
				<span> | </span>
				<a>退出</a>
			</div>
       <div class="left1">
                <p class="s2"><span>|</span>
              <span class="logo_text1">帐号中心</span></p>
            </div>

        </div>
    </div>

    <div id="showSupportInfo" style="display:none;"></div>





    <!-- banner -->
    <div id="bannerBox" class="banner_box" style="display:none;">
        <span class="banner_text">本网站使用cookie功能，为您提供最佳的用户体验。
            <a href="javascript:void(0)" class="rule" id="gotocookie" target="_blank">
                了解更多
            </a>
        </span>
        <span id="banner_close_btn" class="banner_close"></span>
    </div>


    <div class="menu wp mkcl">
        <div class="mnr">
            <ul class="nav mkcl">
                <li data-menu="1" class="sel" id="authTest_account_security">
                <a href="#">帐号与安全</a>



                </li>
                <li data-menu="2" class="" id="authTest_personal_info">
                    <a class="menuSlt_info_a completeInfo" href="information.jsp">个人信息
                        <span class="menuSlt_info_circle_red"></span>
                    </a>




                </li>


                <li data-menu="5" class="" id="authTest_securitycenter"><a class="completeInfo" href="#">安全中心</a>


                </li>


                <li data-menu="4" class="" id="setting_btn"><a href="#">设置</a>



                </li>
            </ul>
            <b class="navsign" style="display:none"></b>
        </div>

    </div>


    <div class="wp">
        <div class="pannel account-panel">
            <div class="ptitle2 account_column">帐号</div>
            <div class="uc-account-des">用于登录和验证身份，同时也是您个人信息的一部分。如有更换，请及时更新。</div>

                    <div class="line">
                             <div class="uctb mkcl">


                                    <div class="r">

                                <span id="changePhoneBtn_0" class="btn completeInfo">更换</span>

                                    </div>

                                <img class="l" src="static/images/phone.png">
                                <div class="detail">
                                 <p class="dtop">手机号


                                 </p>

              </div>
           </div>
        </div>

        <div class="line">
                   <div class="uctb mkcl">




                                <div class="r">

                                    <span id="bindEmailBtn" class="btn completeInfo">绑定</span>
                                </div>


                        <img class="l" src="static/images/Email.png">
                        <div class="detail">
                            <p class="dtop">邮件地址 <span class="userAcctColor" id="emailVal_" anonyaccount=""> </span>
                            <span>(未绑定)</span></p>

                        </div>
                  </div>
                </div>


        </div>


            <div class="pannel">
                <div class="ptitle2">绑定</div>

                    <div class="line" id="dvThirdAcc">
                        <div class="uctb mkcl">
                            <!-- 绑定/解绑按钮 -->
                            <div class="r">
                                <span  class="btn completeInfo long-unbindBtn">绑定</span>
                                    <span id="btnTips0"></span>

                            </div>




                            <img class="l thirdAccInfoImg" src="static/images/wap_weixin.png">
                            <div class="detail thirdAccountStyle">
                                <p class="dtop">微信</p>
                            </div>
                        </div>
                    </div>

                    <div class="line" id="dvThirdAcc">
                        <div class="uctb mkcl">
                            <!-- 绑定/解绑按钮 -->
                            <div class="r">


                                    <span id="btnBound1" class="btn completeInfo long-unbindBtn" >绑定</span>
                                    <span id="btnTips1"></span>

                            </div>

                            <img class="l thirdAccInfoImg" src="static/images/wap_qq.png">
                            <div class="detail thirdAccountStyle">
                                <p class="dtop">QQ</p>
                            </div>
                        </div>
                    </div>

                    <div class="line" id="dvThirdAcc">
                        <div class="uctb mkcl">
                            <!-- 绑定/解绑按钮 -->
                            <div class="r">


                                    <span id="btnBound2" class="btn completeInfo long-unbindBtn" >绑定</span>
                                    <span id="btnTips2"></span>

                            </div>

                            <img class="l thirdAccInfoImg" src="static/images/wap_weibo.png">
                            <div class="detail thirdAccountStyle">
                                <p class="dtop">微博</p>
                            </div>
                        </div>
                    </div>

            </div>


        <div class="pannel">
            <div class="ptitle2">安全</div>

            <div class="line">
                <div class="uctb mkcl">


                    <div class="r">
                        <b class="btn completeInfo" id="twoVerifyBtn" optype="0">开启</b>
                    </div>





                    <img class="l" src="static/images/account_p.png">
                    <div class="detail">
                        <p class="dtop">帐号保护</p>
                        <p>开启后，在非受信任设备或网站上登录，需通过验证码验证身份。</p>
                    </div>
                </div>
            </div>








            <div class="line">
                <div class="uctb mkcl">
                    <div class="r">
                        <span id="security_phone_set_btn" class="btn  completeInfo">设置</span>

                    </div>
                    <img class="l" src="static/images/lock_e.png">
                    <div class="detail">
                        <p class="dtop">安全手机号 <span id="security_phone_text" val="(未设置)">(未设置)</span><span id="security_phone_tip"></span> </p>
                        <p>用于重置密码和验证身份</p>
                    </div>
                </div>
            </div>










            <div class="line">
                <div class="uctb mkcl">
                        <div class="r">
                            <span id="security_email_set_btn" class="btn  completeInfo">设置</span>
                        </div>
                    <img class="l" src="static/images/lock_e.png">
                    <div class="detail">
                        <p class="dtop">安全邮件地址 <span id="security_email_text">(未设置)</span></p>


                           <p>用于重置密码和验证身份</p>




                    </div>
                </div>
            </div>




            <div class="line">
                <div class="uctb mkcl">
                    <div class="r">
                        <span id="cpsw" class="btn completeInfo">修改</span>
                    </div>
                    <img class="l" src="static/images/modify.png">
                    <div class="detail">
                        <p class="dtop modifyPwd">修改密码</p>
                    </div>
                </div>
            </div>



          <!-- 针对全面登出进行相关的开发 -->
          <div class="line">
                <div class="uctb mkcl">
                    <div class="r">
                        <span class="btn completeInfo" id="logoutAllBrowser">退出</span>
                    </div>
                    <img class="l" src="static/images/ic_quit.png">
                    <div class="detail">
                        <p class="dtop">从所有浏览器退出</p>
                        <!-- 立即退出当前帐号在所有浏览器的登录 -->
                        <p>立即退出当前帐号在所有浏览器的登录</p>
                    </div>
                </div>
            </div>
        </div>


    </div>




<!-- 底部  -->

    <div class="wp ft">
        <p>


                        <a href="#" class="rule" target="_blank">华为帐号与云空间用户协议</a>
                        <em class="foot_em">|</em>
                        <a href="#" class="rule" target="_blank">关于华为帐号与云空间隐私的声明</a>
                        <em class="foot_em">|</em>
                        <a href="#" class="ifaq" target="_blank">常见问题</a>







                  <em class="foot_em">|</em>
                  <a href="#" class="rule" target="_blank">Cookies</a>





        </p>




                <p>Copyright&nbsp;©&nbsp;2011-2019&nbsp;&nbsp;华为软件技术有限公司&nbsp;&nbsp;版权所有&nbsp;&nbsp;保留一切权利&nbsp;&nbsp;苏B2-20070200号&nbsp;|&nbsp;苏ICP备09062682号-9</p>
                <p></p>





    </div>




<!-- 修改密码弹出  -->

<div class="global_dialog_confirm_main" id= "xg1" style="display: none;height: 600px; overflow-y: visible; bottom: 8px; top: 80px;">
<from id ="HupdatePassword" method="post">
<div class="global_dialog_confirm_title">
<h3 class="ellipsis" id="dialog_title" title="修改密码">修改密码</h3>
</div>

<div class="global_dialog_confirm_content" style="overflow-y: scroll;">
<div>
<div style="margin-bottom:8px;font-size:15px">输入旧密码</div>

<div class="dinput-UI5" id="oldPasswordDiv" style="margin-bottom:0">

<div class="ico-div">
<img class="eyeoff" id="oldPasswdEye" src="static/image/eyeoff.png">
</div>

<div class="input-div">
<input class="input-scroll" maxlength="32" type="password" id="oldPassword" placeholder="旧密码" name = "oldpassword">
</div>

</div>

<div id="old_pwd_error_info" class="error-tips-EMUI5" style="margin-bottom:14px">

</div>

<div style="margin-bottom:8px;font-size:15px">输入新密码</div>

<div class="line8-EMUI5">
<div class="dinput-UI5" id="passwordDivTip" style="margin-bottom:0">

<div class="ico-div">
<img class="eyeoff" id="pwdPic" src="static/image/eyeoff.png">
</div>

<div class="input-div">
<input  class="input-scroll cas_error" maxlength="32" type="password"  id="newPassword" placeholder="新密码" name = "newpassword">
</div>

</div>

<div id="new_pwd_error_info" class="error-tips-EMUI5" style="margin-bottom:14px"></div>

</div>

<div class="line-EMUI5 zero-margin">

<div class="dinput-UI5" id="confirmPasswordDiv" style="margin-bottom:0">

<div class="input-div">
<input  class="input-scroll" maxlength="32" type="password" id="confirmPassword" placeholder="确认新密码" name = "determinepassword">
</div>

<span id=""></span>
</div>
<div id="confirm_pwd_error_info" class="error-tips-EMUI5" style="margin-bottom:14px">

</div>
</div>


<div id="msg_error_highIP" class="error-tips-EMUI5 addbtmpad-msg-top2pbtom14"></div>


<div class="stLogoutConfirm">
<input type="checkbox" class="checkbox vam" id="stLogoutCheckbox" name="stLogoutCheckbox" tabindex="4">
<label for="stLogoutCheckbox">
<i id="stLogoutCheckbox_icon" class="checkBox-icon marginR6 tick-off-icon"></i><div id="spans" style="color:red"></div></label>

<div class="st_faq_box position_rel">

<span id="st_faq_icon"></span>

<div id="st_faq_des" class="st_faq_position">
<span class="st_faq_arrow"></span>若认为有人知道您的密码，您可以强制注销所有正使用您华为帐号的其他设备和网站。<br>
您将收到提示，要求您重新登录使用您华为帐号的设备和网站。
</div>

</div>
</div>


<div class="line-EMUI5">

<div class="normal-tips-EMUI5 pwd-format-des-head">密码需满足以下要求： </div>

<div id="pwdLength" class="gray-tips-EMUI5 ">
<div class="pwd-format l formatNoIcon"></div>
<span>至少8个字符</span>
</div>

<div id="pwdComplexity" class="gray-tips-EMUI5 ">
<div class="pwd-format l formatNoIcon"></div>
<span>至少含一个大写字母一个小写字母和数字</span>
</div>
</div>



<div class="line8-EMUI5">

<div class="normal-tips-EMUI5">密码强度：&nbsp;<span id="pwdComplexFlag"></span></div>

</div>

<div class="line8-EMUI5">
<div class="gray-tips-EMUI5">
<div class="pwd-complex" style="margin-bottom:8px;">
<div id="pwdStong">
</div>
</div>
</div>
<div style="margin-bottom:16px;padding-left:8px; color:#999">勿使用其他帐号的密码。</div>
</div>


</div>

</div>


   <div class="global_dialog_confirm_ft">

      <div class="dialog-left-btn-wrap l" id="dialog_leftBtn">
      <div class="global_dialog_confirm_nor" role="cancel" title="取消" id = "cancel">
      <span>取消</span>


      </div>
      </div>

     <div class="dialog-left-btn-wrap l" id="dialog_leftBtn">
     <div class="global_dialog_confirm_nor" role="cancel" title="确定" id = "confirm">
     <span>确定</span>
      </div>
      </div>
      </div>
      </from>
      </div>


</body>



<script type="text/javascript">


$(function(){

	 var furg = true;
 	 $("#oldPasswdEye").click(function(){//点击小眼睛图标

		if(furg){//如何点击显示密码
		$("#oldPassword").attr("type","text");
		$("#oldPasswdEye").attr("src","static/image/eyeon.png");
		furg = false;
		return furg;
		}else {//不点击则隐藏
			$("#oldPassword").attr("type","password");
			$("#oldPasswdEye").attr("src","static/image/eyeoff.png");
			furg = true;
			return furg;
		}
    });
	 $("#cpsw").click(function(){//点击修改密码
		if($("#xg1").is(':hidden')){//如果当前隐藏
		$("#xg1").show();//那么就显示div
		}else{//否则
		$("#xg1").hide();//就隐藏div
		}
    });

	 $("#pwdPic").click(function(){//点击小眼睛图标

		if(furg){//如何点击显示密码
		$("#newPassword").attr("type","text");
		$("#pwdPic").attr("src","static/image/eyeon.png");
		furg = false;
		return furg;
		}else {//不点击则隐藏
			$("#newPassword").attr("type","password");
			$("#pwdPic").attr("src","static/image/eyeoff.png");
			furg = true;
			return furg;
		}
    });


    $("#cancel").click(function(){//点击取消

		$("#xg1").hide();//就隐藏div

    });





    //密码强度正则表达式
		var password = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/;

		$("#newPassword").change(function(){
			  if(!password.test($("#newPassword").val())){

            }


		});

		$("#newPassword").change(function(){

			if ($("#confirmPassword").val() != $("#newPassword").val() && $("#confirmPassword").val() != null){

            $("#spans").html("两次密码输入不一致");

			}else{

			$("#spans").hide();

			}

		});


      $("#confirm").click(function(){//点击确定

      var password = $("#newPassword").val();
      var user_iphone = $("#user_iphone").text();

    $.ajax({
        url : "userServlet",
        type : "post",
        dataType : "text",
       data: {
				method:'userUpdatepassword',
				user_iphone:user_iphone,
				password:password
				},
        success : function (data){

            var count = parseInt(data);

            if (count > 0 ) {
                $("#xg1").hide();//就隐藏div
            }else{
                 alert('修改失败');
            }


        }
    });


   	 });



});
</script>


</html>