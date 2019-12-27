<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'layout.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="admin/market/layout.css">
	<link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superRed.css" />
	<link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/icon.css">
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.min.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superBlue.css" id="themeCss"/>
    <script type="text/javascript" charset="utf-8" src="admin/js/jquery-easyui-1.8.6/theme/super/super.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/locale/easyui-lang-zh_CN.js"></script>
	<link rel="shortcut icon" href="static/image/favicon.ico">
	
  </head>
  
  <body>
  		<div id="tt" class="easyui-tabs" style="width:500px;height:250px;" data-options="border:false,fit:true">   
		    <div title="网站设置" style="padding:20px;display:none;" data-options="iconCls:'fa fa-cogs',">   
		    
		        <div class="right_main">
					<form method="post" enctype="multipart/form-data">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="wenzhang mat20">
					<tbody><tr>
						<td align="right" width="150">网站标题：</td>
						<td><input type="text" name="info[web_title]" value="PHPSHE B2C商城系统演示站" class="inputall input500"></td>
					</tr>
					<tr>
						<td align="right">关&nbsp;&nbsp;键 词：</td>
						<td><input type="text" name="info[web_keywords]" value="免费商城系统,网上商城系统,多用户商城系统,分销商城系统,微信商城系统,商城源码,手机移动商城系统,b2b2c商城系统,网店系统,购物系统,php商城系统,phpshe,简好网络" class="inputall input500"></td>
					</tr>
					<tr>
						<td align="right">网站描述：</td>
						<td><textarea name="info[web_description]" style="width:505px;height:100px;"></textarea></td>
					</tr>
					<tr>
						<td align="right">客服电话：</td>
						<td><input type="text" name="info[web_phone]" value="15839823500" class="inputall input500"></td>
					</tr>
					<tr>
						<td align="right">客服 QQ：</td>
						<td><input type="text" name="info[web_qq]" value="76265959" class="inputall input500"></td>
					</tr>
					<tr>
						<td align="right">版权所有：</td>
						<td><input type="text" name="info[web_copyright]" value="2008-2018 简好网络" class="inputall input500"></td>
					</tr>
					<tr>
						<td align="right">ICP备案号：</td>
						<td><input type="text" name="info[web_icp]" value="豫ICP备17013559号-1" class="inputall input500"></td>
					</tr>
					<tr>
						<td align="right">热门搜索：</td>
						<td>
							<input type="text" name="info[web_hotword]" value="PHPSHE,B2C商城系统,简好网络" class="inputall input500">
							<span class="c999 mal10">（多个请用“,”隔开）</span>
						</td>
					</tr>
					<tr>
						<td align="right">统计代码：</td>
						<td>
							<textarea name="info[web_tongji]" style="width:505px;height:120px;" class="fl"></textarea>
							<span class="fl c999 mal10" style="margin-top:45px">（第三方流量统计代码）</span>
							<div class="clear"></div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<input type="hidden" name="pe_token" value="c4dd17ffc2dc9709c65fd02c7ef10457">
							<input type="button" name="pesubmit" value="提 交" class="tjbtn">
						</td>
					</tr>
					</tbody></table>
					</form>
				</div>
				
				
		    </div>   
		    <div title="首页热销设置" data-options="iconCls:'fa fa-shopping-bag',closable:false" style="overflow:auto;padding:20px;display:none;">   
		        <table id="dg"></table>
		    </div>   
		</div> 
  		<!-- 修改首页显示图片的窗口 -->
    	<div id="dd" class="easyui-dialog" title="修改显示的图片" style="width:500px;height:400px;">
    		<table align="center">
    			<tr>
    				<td ><font color="red">现在显示的图片：</font><br><br></td>
    				<td><p id="oldImage"></p><br><br></td>
    				
    			</tr>
    			
    			<tr>
    				<td><input multiple style="width:200px"id='fileput' name='fileput' class='easyui-filebox' data-options="onChange:change_photo,buttonText: '选择文件',prompt:'选择一张图片...',required:true"></td>
    				<td>
	    				<div id="Imgdiv">
		    			<img id="Img" width="100px" height="100px"/>
		    			</div>
	    			</td>
    			</tr>
    			<tr>
    				<td><a href="javascript:;" id="submit_button" class="easyui-linkbutton primary" style="width:100px;height:60px;">确定提交</a></td>
    			</tr>
    		</table>
    		
    	</div>
  </body>
  <script type="text/javascript" src="admin/market/layout.js"></script>
  <script type="text/javascript">
  	function change_photo(){

    PreviewImage($("input[name='fileput']")[0], 'Img', 'Imgdiv');

}

function PreviewImage(fileObj,imgPreviewId,divPreviewId) {
        var allowExtention = ".jpg,.bmp,.gif,.jpeg,.png";//允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;
        var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase();
        if (allowExtention.indexOf(extention) > -1) {
            if (fileObj.files) {//HTML5实现预览，兼容chrome、火狐7+等
                if (window.FileReader) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var tempDivPreview=document.getElementById(divPreviewId);
                        tempDivPreview.style.display="block";
                        document.getElementById(imgPreviewId).setAttribute("src", e.target.result);
                    }
                    if(fileObj.files[0]) {
                        reader.readAsDataURL(fileObj.files[0]);
                    }
                }
            } else {
                document.getElementById(imgPreviewId).setAttribute("src", fileObj.value);

            }
        } else {
            alert("仅支持" + allowExtention + "为后缀名的文件!");
            fileObj.value = "";//清空选中文件
            fileObj.outerHTML = fileObj.outerHTML;
        }
}
  </script>
</html>
