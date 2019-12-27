<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>帐号中心</title>




<link href="https://id1.cloud.huawei.com/CAS/up/amw_rss_42/logos/icons/huawei.ico" type="image/x-icon" rel="shortcut icon">
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
<body class="userCenter-ucInfo">



	<div class="topbar1">
		<div class="tp wp mkcl">
			<div class="childLogo1">
			     <span class="s1"> <img src="static/image/head-top.png"></span>
			</div>


			<div class="tbr1">



						<b class="user"></b>


				<a id = "user_iphone">${sessionScope.LOGIN_USER.user_name}</a>
				<span> | </span>
				<a>退出</a>
			</div>


			<div class="left1">
				<p class="s2"><span>|</span>
			  <span class="logo_text1">帐号中心</span></p>
			</div>

		</div>
	</div>

	<div id="showSupportInfo" style="display:none;">${sessionScope.LOGIN_USER.id}</div>





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
				<li data-menu="1" class="" id="authTest_account_security">
				<a href ="Account.jsp" >帐号与安全</a>



				</li>
				<li data-menu="2" class="sel" id="authTest_personal_info">
					<a class="menuSlt_info_a completeInfo" href="">个人信息
						<span class="menuSlt_info_circle_red"></span>
					</a>




				</li>


				<li data-menu="5" class="" id="authTest_securitycenter"><a class="completeInfo" href="">安全中心</a>



				</li>

				<li data-menu="4" class="" id="setting_btn"><a href="">设置</a>



				</li>
			</ul>
			<b class="navsign" style="display:none"></b>
		</div>

	</div>



	<div class="wp">
		<div class="pannel">
	
			<div class="upic">
				<a href="" id="uploadImg"><img id="headPic" src="static/image/pic.png" width="160px" height:"160px"="" onerror="loadDefaultPic(this);" orginsrc="https://id1.cloud.huawei.com/CAS/up/amw_rss_42/css/portal/userCenter_rss/images/pic.png?cas20190909"></a>
				<p id="uploadImgDiv">编辑头像</p>
			</div>

			<div id="upload_dialog_show" class="hidden"></div>

			<div class="umsg">
				<p class="title">公开信息</p>
				<p class="line">
				    <a id="nickNameLink" href="javascript:;" class="more">编辑</a>
					昵称：
					<span class="uinfo"><label id="nickname" class="space-pre">${sessionScope.LOGIN_USER.user_name}</label></span>
			    </p>
				<p class="line">
				    <a id="loginIDLink" href="javascript:;" class="more" style="display: block;">编辑</a>
					手机号：
					<span class="uinfo"><label id="loginID" class="space-pre">${sessionScope.LOGIN_USER.phone}</label></span>
			    </p>



				<p class="line">
					<a id="genderLink" href="javavscript:;" class="more">编辑</a>
					邮箱：
					<span class="uinfo"><label id="gender">${sessionScope.LOGIN_USER.email}</label></span>
				</p>


				  <p class="line">
				  	<a id="cityLink" href="javascript:;" class="more">选择</a>
				  	地区：
				  	<span class="uinfo"><label id="cityarea">${sessionScope.LOGIN_USER.state}</label></span>
				  </p>

				<p class="title">非公开信息</p>


				<div class="line member_name">
					生日日期：
					<span><label id="birthday">${sessionScope.LOGIN_USER.user_date}</label></span>
				
				</div>
			</div>
		
		</div>
	</div>

<!-- 底部  -->

	<div class="wp ft">
		<p>


						<a href="" class="rule" target="_blank">华为帐号与云空间用户协议</a>
						<em class="foot_em">|</em>
						<a href="" class="rule" target="_blank">关于华为帐号与云空间隐私的声明</a>
						<em class="foot_em">|</em>
						<a href="" class="ifaq" target="_blank">常见问题</a>







        		  <em class="foot_em">|</em>
	              <a href="" class="rule" target="_blank">Cookies</a>





		</p>




				<p>Copyright&nbsp;©&nbsp;2011-2019&nbsp;&nbsp;华为软件技术有限公司&nbsp;&nbsp;版权所有&nbsp;&nbsp;保留一切权利&nbsp;&nbsp;苏B2-20070200号&nbsp;|&nbsp;苏ICP备09062682号-9</p>
				<p></p>





	</div>

<!-- 修改名称弹出  -->

<div class="global_dialog_confirm_main" id = "updateName" style="display: none; overflow-y: visible; bottom: auto; top: 128.5px;">
<div class="global_dialog_confirm_title"><h3 class="ellipsis" id="dialog_title" title="昵称">昵称</h3></div>
    <div class="global_dialog_confirm_content" style="overflow-y: visible; height: auto;"><div>
    <div class="dinput-UI5 margin16" id="nicknameBox" style="margin-bottom:0">
    <label class="lb_opacity_Class" style="display: block; position: absolute; cursor: text; float: left; z-index: 2; padding-left: 0px; padding-top: 10px;" for="nicknametext">
    </label><input id="nicknametext" type="text" maxlength="20" name = "names" style="z-index: 1;"></div><p id="error_info" class="error-tips-EMUI5"></p></div></div>
    <div class="global_dialog_confirm_ft">
    <div class="dialog-left-btn-wrap l" id="dialog_leftBtn"><div class="global_dialog_confirm_nor" role="cancel" title="取消"><span>取消</span></div></div>
    <div class="dialog-left-btn-wrap l" id="Qdialog_leftBtn"><div class="global_dialog_confirm_nor" role="cancel" title="确定"><span>确定</span></div></div>
    </div>
    </div>
    <div class="dialog-cancel" role="cancel2" id="dialog_cancel_btn">
    </div>
    </div>


<!-- 修改手机号弹出  -->
<div class="global_dialog_confirm_main"  id = "updatePhone" style="display: none; overflow-y: visible; bottom: auto; top: 136.5px;">
<div class="global_dialog_confirm_title"><h3 class="ellipsis" id="dialog_title" title="华为号">手机号</h3></div>    
<div class="global_dialog_confirm_content" style="overflow-y: visible; height: auto;"><div><p class="line-EMUI5 font-13">华为号用于登录，只能设置一次。</p>
<div class="line8-EMUI5"></div>
<div class="dinput-UI5 input8 " id="inputdiv"><input id="loginIdText" type="text" maxlength="20"></div>
<div class="errorDiv error-tips-EMUI5">
</div></div></div>
    <div class="global_dialog_confirm_ft">        
    <div class="dialog-left-btn-wrap l" id="Qcancel">
    <div class="global_dialog_confirm_nor" role="cancel" title="取消"><span>取消</span></div></div>       
    
    <div class="dialog-left-btn-wrap l" id="reqest">
    <div class="global_dialog_confirm_nor" role="cancel" title="确定"><span>确定</span></div></div>  
     </div>    
     </div><div class="dialog-cancel" role="cancel2" id="dialog_cancel_btn"></div></div>


<!-- 修改邮箱弹出  -->

<div class="global_dialog_confirm_main"  id = "updateEmail" style="display: none;bottom: auto; top: 146.5px;">
<div class="global_dialog_confirm_title"><h3 class="ellipsis" id="dialog_title" title="邮箱">邮箱</h3></div>    
<div class="global_dialog_confirm_content" style=" height: auto;"><div><p class="line-EMUI5 font-13"style = "display: none;">华为号用于登录，只能设置一次。</p>
<div class="line8-EMUI5"></div>
<div class="dinput-UI5 input8 " id="inputdiv"><input id="loginEmail" type="text" maxlength="20"></div>
<div class="errorDiv error-tips-EMUI5">
</div></div></div>
    <div class="global_dialog_confirm_ft">        
    <div class="dialog-left-btn-wrap l" id="Qcancels">
    <div class="global_dialog_confirm_nor" role="cancel" title="取消"><span>取消</span></div></div>       
    
    <div class="dialog-left-btn-wrap l" id="reqests">
    <div class="global_dialog_confirm_nor" role="cancel" title="确定"><span>确定</span></div></div>  
     </div>    
     </div><div class="dialog-cancel" role="cancel2" id="dialog_cancel_btn"></div></div>


<!-- 修改地区弹出  -->

<div class="global_dialog_confirm_main"  id = "updateCata" style="display: none;bottom: auto; top: 146.5px;">
<div class="global_dialog_confirm_title"><h3 class="ellipsis" id="dialog_title" title="地区">地区</h3></div>    
<div class="global_dialog_confirm_content" style=" height: auto;"><div>
<div class="line8-EMUI5"></div>
<div class="dinput-UI5 input8 " id="inputdiv"><input id="city" type="text" maxlength="20" style="z-index: 1;"></div>
<div class="errorDiv error-tips-EMUI5">
</div></div></div>
    <div class="global_dialog_confirm_ft">        
    <div class="dialog-left-btn-wrap l" id="Ccancels">
    <div class="global_dialog_confirm_nor" role="cancel" title="取消"><span>取消</span></div></div>       
    
    <div class="dialog-left-btn-wrap l" id="Creqests">
    <div class="global_dialog_confirm_nor" role="cancel" title="确定"><span>确定</span></div></div>  
     </div>    
     </div><div class="dialog-cancel" role="cancel2" id="dialog_cancel_btn"></div></div>


<script type="text/javascript">



$(function(){



//修改用户名
 $("#nickNameLink").click(function(){
		if($("#updateName").is(':hidden')){//如果当前隐藏
		$("#updateName").show();//那么就示div
		}else{//否则
		$("#updateName").hide();//就隐藏div
		}
    });
    
    
     $("#Qdialog_leftBtn").click(function(){//点击确定
      
      var user_names = $("#nicknametext").val();
      var user_id = $("#showSupportInfo").text();
     
    $.ajax({
        url : "userServlet",
        type : "post",
        dataType : "text",
       data: {
				method:'userUpdateMassage',
				user_id:user_id,
				names:user_names,
				Massage:'user_name'
				},
        success:function(data){
       	$("#updateName").hide();
       	$("#nickname").html(user_names);
       	$("#user_iphone").html(user_names);
        }
    });
         
	
   	 });
   
   
    $("#dialog_leftBtn").click(function(){//点击取消
		
		$("#updateName").hide();//就隐藏div
		
    });
    
    
 
 //修改手机号
 $("#loginIDLink").click(function(){
		if($("#updatePhone").is(':hidden')){//如果当前隐藏
		$("#updatePhone").show();//那么就示div
		}else{//否则
		$("#updatePhone").hide();//就隐藏div
		}
    });
    
    
     $("#reqest").click(function(){//点击确定
      
      var user_iphone = $("#loginIdText").val();
      var user_id = $("#showSupportInfo").text();
     
    $.ajax({
        url : "userServlet",
        type : "post",
        dataType : "text",
       data: {
				method:'userUpdateMassage',
				user_id:user_id,
				user_phone:user_iphone,
				Massage:'phone'
				},
        success:function(data){
       	$("#updatePhone").hide();
       	$("#loginID").html(user_iphone);
       		
        }
    });
         
	
   	 });
   
   
    $("#Qcancel").click(function(){//点击取消
		
		$("#updatePhone").hide();//就隐藏div
		
    });
    
 
 //修改邮箱
 $("#genderLink").click(function(){
		if($("#updateEmail").is(':hidden')){//如果当前隐藏
		$("#updateEmail").show();//那么就示div
		}else{//否则
		$("#updateEmail").hide();//就隐藏div
		}
    });
    
    
     $("#reqests").click(function(){//点击确定
      
      var user_email = $("#loginEmail").val();
      var user_id = $("#showSupportInfo").text();
     
    $.ajax({
        url : "userServlet",
        type : "post",
        dataType : "text",
       data: {
				method:'userUpdateMassage',
				user_id:user_id,
				emails:user_email,
				Massage:'email'
				},
        success:function(data){
       	$("#updateEmail").hide();
       	$("#gender").html(user_email);
       		
        }
    });
         
	
   	 });
   
   
    $("#Qcancels").click(function(){//点击取消
		
		$("#updateEmail").hide();//就隐藏div
		
    });   
    

   
   
  //修改地区
  $("#cityLink").click(function(){
		if($("#updateCata").is(':hidden')){//如果当前隐藏
		$("#updateCata").show();//那么就示div
		}else{//否则
		$("#updateCata").hide();//就隐藏div
		}
    });
    
    
     $("#Creqests").click(function(){//点击确定
      
      var user_city = $("#city").val();
      var user_id = $("#showSupportInfo").text();
     
    $.ajax({
        url : "userServlet",
        type : "post",
        dataType : "text",
       data: {
				method:'userUpdateMassage',
				user_id:user_id,
				city:user_city,
				Massage:'state'
				},
        success:function(data){
       	$("#updateCata").hide();
       	$("#cityarea").html(user_city);
       		
        }
    });
         
	
   	 });
   
   
    $("#Ccancels").click(function(){//点击取消
		
		$("#updateCata").hide();//就隐藏div
		
    });   
    

  
  });
  
  
  
</script>

</body></html>