<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>新增地址</title>

    <link rel="stylesheet" href="static/css/ec.core.base.min.css">
    <link rel="stylesheet" href="static/css/main.min.css">
    <link rel="shortcut icon" href="static/image/favicon.ico">
    <link rel="stylesheet" href="static/layui/css/layui.css">
    <script src="static/js/jquery-2.2.1.js"></script>
</head>
<body>
<%@include file="head.jsp"  %>
    <div class="g" >
    <div class="breadcrumb-area fcn"><a href="/index.html">首页</a>&nbsp;&gt;&nbsp;<em id="personCenter"><a href="/member/">我的商城</a></em><em id="pathPoint"></em><span id="pathTitle"></span></div>
<%@include file="menu.jsp" %>
    <div class="fr u-4-5"><!-- 20141212-栏目-start -->
<div class="section-header">
    <div class="fl">
        <h2><span>收货地址管理</span></h2>
    </div>
</div><!-- 20141212-栏目-end -->
<div class="hr-20"></div>
<!-- 20141216-我的收货地址-表单-编辑地址-start -->
<div class="myAddress-edit" id="myAddress-edit">
    <div class="form-edit-panels" id="form-edit-panels">
        <form id="myAddress-form" action="shippingServlet?mod=addAddress" autocomplete="off" method="post" data-type="add">

            <div class="form-edit-table">
                <table cellspacing="0" cellpadding="0" border="0">
                    <tbody>
                        <tr>
                            <th><span class="required">*</span><label for="consignee">收货人：</label></th>
                            <td>
                                <input maxlength="20" type="text" name="consignee" id="consignee" class="text span-130" validator="validator11571795048118">
                                <label id="consignee-msg"></label>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="required">*</span><label for="mobile">手机号码：</label></th>
                            <td>
                                <div class="inline-block vam">
                                    <label id="mobile_id" style="display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);" class="text span-130 ime-disabled" for="mobile">请输入11位手机号码</label><input maxlength="20" type="text" name="mobile"  id="mobile" class="text span-130 ime-disabled" alt="tel-msg" validator="validator51571795048118" style="z-index: 1;">
                                    <label class="titlebox" for="phone">备选号码：</label>
                                </div>
                                <div class="inline-block vam relative"><label id="phone_id" style="display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);" class="text span-310 ime-disabled" for="phone">固话或其他手机号码</label><input maxlength="50" type="text" name="phone" id="phone" class="text span-310 ime-disabled" alt="tel-msg" validator="validator61571795048118" style="z-index: 1;"></div>
                                <div><label id="tel-msg"></label></div>
                            </td>
                        </tr>
                        <tr>
                            <th rowspan="2" class="selecte-vat"><span class="required">*</span><label for="province">收货地址：</label></th>
                            <td class="relative">
                                <!--20170627  收货地址 start-->
                                <!--以下内容隐藏时添加class="hide"，显示去掉class="hide"-->
								<div class="layui-form">
							        <div class="layui-form-item" id="area-picker">
							            <div class="layui-input-inline" style="width: 200px;">
							                <select  name="province"  id="province" class="province-selector" data-value="广东省" lay-filter="province-1">
							                    <option value="">请选择省</option>
							                </select>
							            </div>
							            <div class="layui-input-inline" style="width: 200px;">
							                <select name="city" id="city"  class="city-selector" data-value="深圳市" lay-filter="city-1">
							                    <option value="">请选择市</option>
							                </select>
							            </div>
							            <div class="layui-input-inline" style="width: 200px;">
							                <select name="county" id="county"  class="county-selector" data-value="龙岗区" lay-filter="county-1">
							                    <option value="">请选择区</option>
							                </select>
							            </div>
							        </div>
							    </div>

<!-- <div id="myAddress-region" class="inline-block"></div> -->
<label id="myAddress-msg"></label>                              <!--20170627  收货地址 end-->
                            </td>
                        </tr>
                        <tr class="tr-rel">
                            <td>
                                <div class="inline-block relative">
                                    <label id="address_id" style="display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);" class="textarea span-574" for="address">如选择不到您的地区，请在此处详细描述</label><textarea name="address" id="address" class="textarea span-574" validator="validator31571795048118" style="z-index: 1;"></textarea>
                                </div>
                                <label class="vat" id="address-msg"></label>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="zipCode-add">邮编：</label></th>
                            <td>
                                <input maxlength="6" type="text" class="text span-130 ime-disabled" name="zipCode" id="zipCode" validator="validator41571795048118">
                                <label id="zipCode-msg"></label>
                            </td>
                        </tr>

                        <tr>
                            <th>&nbsp;</th>
                            <td>
                                <label class="inputbox"><input id="defaultFlag" type="checkbox" class="checkbox" name="defaultFlag" value="1" id="myAddress-default">
                                	<span>设为默认收货地址</span>
                                	<br/>
                                	<font id="default_address" color="red"></font>
                                </label>
                            </td>
                        </tr>
                        <tr class="tr-action">
                            <th>&nbsp;</th>
                            <td>
                                <div id="form-edit-button">
                                    <input type="submit" id="button-ok" class="button-action-ok" value="添加新地址"><a href="javascript:;" id="button-cancel" class="button-action-cancel" onclick="resetAddress()"><span>清&nbsp;&nbsp;空</span></a>
                                    <label class="label-info" id="submit-msg"></label>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        <input name="CsrfToken" type="hidden" value="835E6AF841B3320FAE138649453133B337EA9E60D9584692"></form>
    </div>
</div>
<!-- 20141216-我的收货地址-表单-编辑地址-end -->
<div class="hr-30"></div>
<!-- 20141216-我的收货地址-列表-start -->
<!--表单-我的收货地址 -->
<div class="myAddress-record hide" id="myAddress-record" style="display: block;">
    <div class="list-group-title">
        <table cellspacing="0" cellpadding="0" border="0">
            <thead>
                <tr>
                    <th class="col-name">收货人</th>
                    <th class="col-address">收货地址</th>
                    <th class="col-zip">邮编</th>
                    <th class="col-tel">手机/电话</th>
                    <th class="col-operate">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <div class="list-group" id="list-group"><div class="list-group-item" id="myAddress-area-76983738">
    	<table cellspacing="0" cellpadding="0" border="0">
    		<tbody>
    		
    		<c:forEach items="${requestScope.SHIPPING_LIST }" var="shipping" varStatus="row">
    			<tr id="tr${row.index }">
    			<td class="col-name">${shipping.receiverName }</td>
    			<td class="col-address">${shipping.receiverProvince }&nbsp;&nbsp;${shipping.receiverCity }&nbsp;&nbsp;${shipping.receiverDistrict }&nbsp;&nbsp;${shipping.receiverAddress }</td>
    			<td class="col-zip">${shipping.receiverZip }</td>
    			<td class="col-tel"><p>${shipping.receiverMobile }</p></td>
    			<td class="col-operate">
    				<p class="p-edit">
    					<a class="edit" href="javascript:;" id="edit_address"  onclick="editAddress(this,${shipping.id})" data-url="${shipping.id}" title="编辑">
    						<span style="visibility: visible;opacity: 1;">编辑</span>
    					</a>
    				</p>
    				<p class="p-del">
    					<a class="del" href="javascript:;" id="delete_address" onclick="deleteAddress(${row.index},${shipping.id})" title="删除">
    						<span style="visibility: visible;opacity: 1;">删除</span>
    					</a>
    				</p>
    				<c:if test="${shipping.receiverDefault == true}">
    					<p class="p-state">
	    					<span class="default">默认地址</span>
	    				</p>
    				</c:if>
    				
    			</td>
    		</tr>
    		</c:forEach>
    		
    		
    		
    	</tbody>
    </table>
  </div>
</div>
</div>

<input type="hidden" id="gouldAddress" name="unionPaySwitch" value="1">
</div>
</div>
<%@include file="font.jsp"  %>
</body>
<script src="static/js/shipping.js"></script>
<script src="static/layui/layui.js"></script>
<script>
        //配置插件目录
        layui.config({
            base: './mods/'
            , version: '1.0'
        });
        //一般直接写在一个js文件中
        layui.use(['layer', 'form', 'layarea'], function () {
            var layer = layui.layer
                , form = layui.form
                , layarea = layui.layarea;

            layarea.render({
                elem: '#area-picker',
                change: function (res) {
                    //选择结果
                    console.log(res);
                }
            });
        });
    </script>
</html>
