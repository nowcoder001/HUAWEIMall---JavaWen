<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>My JSP 'product.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superRed.css" />
	<link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/icon.css">
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.min.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="admin/js/jquery-easyui-1.8.6/themes/super/superBlue.css" id="themeCss"/>
    <script type="text/javascript" charset="utf-8" src="admin/js/jquery-easyui-1.8.6/theme/super/super.js"></script>
    <script type="text/javascript" src="admin/js/jquery-easyui-1.8.6/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="admin/css/product.css">
	<link rel="shortcut icon" href="static/image/favicon.ico">
  </head>
  <body>
  		<table id="dg"></table>
  		<!-- 添加商品  弹出窗口 -->
  		<div id="add_product_win">
  			<form id="ff" method="post">
  				<div style="margin:10px">
  					<table class="wenzhang"  >
  						<tr>
  							<div class="form-item">
  								<td align="right"><label for="" class="label-top">商品名称：</label></td>
  								<td style="width: 400px"><input id="username" name="pro_name"class="easyui-validatebox easyui-textbox" prompt="请输入商品名称" data-options="width:300,required:true"></td>
  								<td style="width: 300px" align="right" rowspan="2" valign="top">主图图片：<input multiple style="width:200px" id="fileputHB1" name="fileputHB1" class="easyui-filebox" data-options='onChange:change_photo,buttonText: "选择文件",prompt:"选择一张图片...",required:true'></td>
  								<td rowspan="2"><div id="Imgdiv"><img id="Img" width="100px" height="100px"/></div></td>
  							</div>
  						</tr>
  						<tr>
  							<div class="form-item">
  								<td align="right"><label for="" class="label-top">商品分类：</label></td>
  								<td><select name="pro_category" id="pro_category" class="easyui-combotree" style="width:200px;" ></select> </td>

  							</div>
  						</tr>
  						<tr>
  							<div class="form-item">
  								<td align="right"><label for="" class="label-top">商品状态：</label></td>
  								<td>
  									<select name="pro_status" id="pro_status" class="easyui-combobox" style="width:200px;"  data-options="required:true,panelHeight:75">
  										<option value="1">在售</option>
  										<option value="2">下架</option>
  									</select>
  								</td>
  								<td style="width: 300px" align="right" rowspan="2" valign="top">主图图片：<input multiple style="width:200px" id="fileputHB2" name="fileputHB2" class="easyui-filebox" data-options='onChange:change_photo2,buttonText: "选择文件",prompt:"选择一张图片...",required:true'></td>
  								<td rowspan="2"><div id="Imgdiv2"><img id="Img2" width="100px" height="100px"/></div></td>
  							</div>
  						</tr>
  						<tr>
  							<div class="form-item">
  								<td align="right"><label for="" class="label-top">商品价格：</label></td>
  								<td>
  									<input id="pro_price" name="pro_price" type="text" class="easyui-numberbox" prompt="请输入商品价格" data-options="min:0,precision:2,required:true"></input> 元
  								</td>

  							</div>
  						</tr>
  						<tr>
  							<div class="form-item">
  								<td align="right"><label for="" class="label-top">商品库存：</label></td>
  								<td>
  									<input id="pro_stock" name="pro_stock" type="text" class="easyui-numberbox" prompt="请输入商品库存" data-options="min:0,precision:0,required:true"></input> 件
  								</td>
  								<td style="width: 300px" align="right" rowspan="2" valign="top">主图图片：<input multiple style="width:200px" id="fileputHB3" name="fileputHB3" class="easyui-filebox" data-options='onChange:change_photo3,buttonText: "选择文件",prompt:"选择一张图片...",required:true'></td>
  								<td rowspan="2"><div id="Imgdiv3"><img id="Img3" width="100px" height="100px"/></div></td>
  							</div>
  						</tr>
  						<tr>
  							<div class="form-item">
  								<td align="right"><label for="" class="label-top">商品运费：</label></td>
  								<td>
  									<input name="pro_postage" id="pro_postage" type="text" class="easyui-numberbox" prompt="商品运费10元" data-options="min:0,precision:2,required:true"></input> 元
  								</td>
  							</div>
  						</tr>
  						<tr>
  							<td rowspan="4">详情图片与规格：</td>
  							<td rowspan="4" style="height: 200px">
  								<input name="pro_detail" id="pro_detail" class="easyui-textbox" data-options="multiline:true" prompt="请写html方式添加" style="width:300px;height:150px">
  								<br />
  								<br />
  								<p><a id="submit" style="width: 100px;height: 50px; font-size: 30px;" class="easyui-linkbutton primary" data-options="iconCls:'fa fa-check'" />提交</a></p>
  							</td>
  							<td style="width: 300px" align="right" rowspan="2" valign="top">主图图片：<input multiple style="width:200px" id="fileputHB4" name="fileputHB4" class="easyui-filebox" data-options='onChange:change_photo4,buttonText: "选择文件",prompt:"选择一张图片...",required:true'></td>
  							<td rowspan="2"><div id="Imgdiv4"><img id="Img4" width="100px" height="100px"/></div></td>
  						</tr>
						<tr>
  							<td></td>
  							<td></td>
  						</tr>
						<tr>
  							<td style="300px" align="right" rowspan="2" valign="top">主图图片：<input multiple style="width:200px" id="fileputHB5" name="fileputHB5" class="easyui-filebox" data-options='onChange:change_photo5,buttonText: "选择文件",prompt:"选择一张图片...",required:true'></td>
  							<td rowspan="2"><div id="Imgdiv5"><img id="Img5" width="100px" height="100px"/></div></td>
  						</tr>
  					</table>
				</div>
			</form>
  		</div>
  </body>
  <script type="text/javascript" src="admin/js/product.js"></script>
  <script type="text/javascript">

  	function change_photo(){

        PreviewImage($("input[name='fileputHB1']")[0], 'Img', 'Imgdiv');

    }
    function change_photo2(){
        PreviewImage($("input[name='fileputHB2']")[0], 'Img2', 'Imgdiv2');
    }
    function change_photo3(){
        PreviewImage($("input[name='fileputHB3']")[0], 'Img3', 'Imgdiv3');
    }
    function change_photo4(){
        PreviewImage($("input[name='fileputHB4']")[0], 'Img4', 'Imgdiv4');
    }
    function change_photo5(){
        PreviewImage($("input[name='fileputHB5']")[0], 'Img5', 'Imgdiv5');
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
