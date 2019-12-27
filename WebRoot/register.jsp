<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8" />
    <title>注册-华为账号</title>
    <link href="static/css/dialogs.css" rel="stylesheet" type="text/css">

    <link href="static/css/commons.css" rel="stylesheet" type="text/css">

    <link href="static/css/zh-cns.css" rel="stylesheet" type="text/css">


     <!-- 导入国家下拉样式脚本  -->

     <link href="static/combobox/drop.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="static/combobox/jquery.min.js"></script>
	<script type="text/javascript" src="static/combobox/Popt.js"></script>
	<script type="text/javascript" src="static/combobox/city.json.js"></script>
	<script type="text/javascript" src="static/combobox/citySet.js"></script>
	<link rel="shortcut icon" href="static/image/favicon.ico">
	<script type="text/javascript" src="static/laydate/laydate.js"></script>

</head>

<body class="reg themeName-red" id="loginform">
    <!-- 头部  -->

<div class="head-background">
    <div class="head_center">
        <div class="main-logo adHeadPicContainer" style="width:143px"> <img src="static/image/head-top.png" class="wihteBgPic"> </div> <div class="website-name"> <b style="font-size: 18px;margin-top: -5px;float: left;color:#cccccc;">|</b> <b style="font-size: 18px;margin-top: -3px;float: left; margin-left:18px;">华为商城</b> </div>
    </div>
</div>

		<div class="wp1 relative">
		<div class="register-content" id="registerForm">
			<div class="reg-tab clearFix" style="visibility: visible;">

					<a class="phone-wrap l sel" href="javascript:void(0)" onclick="gotoRegisterByPhoneLink()"> <i class="phone-icon"></i>
						<span id="tabPhone" class="tab-phone" style="width: 66px;">手机号</span>
					</a>
					<a class="mail-wrap l" href="javascript:void(0)" onclick="gotoRegisterByEmailLink()"> <i class="mail-icon"></i>
						<span id="tabMail" class="tab-email" style="width: 66px;">电子邮箱</span>
					</a>


			</div>
			<div class="login r">已有华为帐号
                <a href="login.jsp" class="login-a" title="登录">登录</a><span class="ar-eg-opposite">&gt;</span>
            </div>
        <div class="reg-detail">

        <div class="registerTitle">
            <div class="mianTitle">注册华为帐号</div>
            <div class="subTitle">用于访问所有华为终端云服务</div>
         </div>

         <!-- 选择国家开始 -->
         <div class="input-container" id="chooseCountry">
         	<input class="input" name="citys" id="city" type="text" placeholder="请选择" autocomplete="off" readonly="true"/><s></s>
                   <div class="l input-left">国家/地区</div>
                   <div id = "cityError"></div>
        </div>
        <!--手机号码 -->

        <div class="input-container" id="errormsgemail-box">
                   <div class="l input-left">手机号码</div>
       			   <div class="r input-right">
                   </div>
                   <div class="input-content">

                   <input type="text" class="text" name="formBeanusername" tabindex="1" id="username" maxlength="50" autocomplete="off" oninput = "value=value.replace(/[^\d]/g,'')">
      				<div id = "iphoneErrors"></div>
                   </div>
        </div>
        <center><font id="mobile" color="red"></font></center>
        <!-- 输入邮件验证码 -->
        <div class="input-container" id="errRandomCode-box">
               <div class="l input-left">
               短信验证码
               </div>
               <div class="r input-right">
               			<input type="button" class="get-code dbtn2" id="getValiCode" value="获取验证码">

               </div>

               <div class="input-content">

                   <input type="text" autocomplete="off" class="verify vam ime-disabled text" id="authCode" style="vertical-align: middle;" name="formBean.authCode" maxlength="8" tabindex="3">

               </div>
        </div>
        <div id="msg_phoneRandomCode"></div>
       <!-- 设置密码 -->
				<div class="set-password">
					<div class="input-container" id="pwdDiv">
						<div class="l input-left">
							密码
						</div>
						<div class="input-content">
							<input  type="password" class="pwd-input text" id="password" name="formBeanpassword" style="z-index: 1;">
							<div id = "passErrors"></div>
						</div>
					</div>
					<div id="msg_password"></div>
					<div id="pwd_check_dialog"></div>

					<div class="input-container " id="confirmpwdDiv">
						<div class="l input-left">
							确认密码
						</div>
						<div class="input-content">
							<input  id="confirmPwd" type="password" class="text vam" name="checkPassword"  style="z-index: 1;">
							<div id = "spassErrors"></div>
						</div>
					</div>
					<div id="msg_checkPassword"></div>

                <!-- 出生日期 -->

                    <div class="input-container input-birthday " id="birthDateWrap">
                        <div class="l input-left">
                            	出生日期
                        </div>
                        <div class="input-content birthDateContent">
                            <div id="birthDate" class="birthday-choose l">
                             <input type="text" class="text" name="datatimes" tabindex="1" id="test1" maxlength="50" autocomplete="off">
                            <div id = "timeErrors"></div>
                            </div>
                        </div>
                    </div>
                    <div id="birthdayError"><center id="mess" style="display:none;"><font color="red">输入的内容不可为空</font></center></div>

        </div>
        <!-- 按钮 -->
        <div class="reg-btn" align="center">
                <input type="button" class="btn btn-reg sel" id="btnSubmit" name = "Abutton" value="注册" tabindex="7">
                <div id="register_msg" style="position:relative;left:26%;"></div>
                <div id="register_msg2" class="vam error hide" style="margin-left: -60px;"></div>
        </div>

        </div>

        </div>

      </div>

    <div class="box-shadow"></div>
    <input type="hidden" id="countryRegion" value="">
    <div id="selectCountryCodeDiv"></div>
    <div id="notCurrentSiteWarning"></div>


<!-- 底部  -->
<!-- 用于展示所有支持的国家 -->
<div class="wp1 ft">
    <div class="reg-content">
        <p class="footer">

                            <a id="foot_EULA" href="javascript:;" class="rule" target="_blank">华为帐号与云空间用户协议</a>
                            <em class="foot_em">|</em>
                            <a id="foot_privacy" href="javascript:;" class="rule" target="_blank">关于华为帐号与云空间隐私的声明</a>
                            <span id="faqPart" class="foot_em"><em style='font-style: normal'>|</em> <a style="padding:0 10px;" target="blank" href="javascript:;">常见问题</a></span>

            <span id="cookieBox">

                      <em class="foot_em">|</em>
                      <a id="foot_cookies" href="index.html" class="rule" target="_blank">Cookies</a>

           </span>

        </p>

                <p id="copyrightPart" class="footer">Copyright © 2011-2019  华为软件技术有限公司  版权所有  保留一切权利  苏B2-20070200号 | 苏ICP备09062682号-9</p>

    </div>
</div>

</body>


<script type="text/javascript">
	//执行一个laydate实例
	laydate.render({
	  elem: '#test1' //指定元素
	});
	$(function(){
        var code = 0;
        //获取手机验证码
        $('#getValiCode').click(function(event) {
            var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
            if (myreg.test($("#username").val())) {

                var count=3000;
                var originalArray=new Array;//原数组
                //给原数组originalArray赋值
                for (var i=0;i<count;i++){
                    originalArray[i]=i+1;
                }
                originalArray.sort(function(){ return 0.5 - Math.random(); });

                code = originalArray[0];

                $.ajax({
                    url: 'code.jsp',
                    type: 'get',
                    dataType: 'json',
                    data: {
                        mobile:$("#username").val(),
                        code:code
                    },
                    success:function(data){

                    }
                })
                $('#getValiCode').attr('disabled','true');
                $('#getValiCode').attr('style','color:#d6e2f9;');
            }else{
                $('#mobile').text('请输入正确的手机格式');
                return false
            }
        });

    $("#btnSubmit").click(function() {

        if ($("#city").val() == null || $("#city").val() == "" || $("#password").val() == null || $("#password").val() == "" || $("#username").val() == null || $("#username").val() == "" || $("#test1").val() == null || $("#test1").val() == "" || $("#confirmPwd").val() == null || $("#confirmPwd").val() == "") {

            $('#mess').attr('style','display:block;');

            return false;
        }else{

            if ($("#username").val().length == 11) {


                if (code == parseInt($('#authCode').val())) {

                    $.ajax({
                        url: 'userServlet',
                        type: 'post',
                        dataType: 'text',
                        data: {
                            method:'Abuttons',
                            citys:$("#city").val(),
                            formBeanpassword:$("#password").val(),
                            formBeanusername:$("#username").val(),
                            datatimes:$("#test1").val(),
                            checkPassword:$("#confirmPwd").val()
                        },
                        success:function(data){
                            if(data == 7){
                            window.location.href = "login.jsp";
                            }

                        }
                    });

                }else{
                    $('#mobile').text('输入手机验证码不正确');
                    return false;
                }



            }else{
                $('#mobile').text('手机号码格式为11位数字');
                return false
            }







        }

    });


    });



</script>

<script type="text/javascript">


		$("#city").click(function (e) {
			SelCity(this,e);
			});
			$("s").click(function (e) {
			SelCity(document.getElementById("city"),e);
			});

</script>
</html>
