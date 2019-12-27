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
    <div class="g"  style="margin: 0;width: 920px;margin-left: 30px;">
    <div class="fr u-4-5"><!-- 20141212-栏目-start -->
<div class="section-header">
    <div class="fl">
        <h2><span>新增收货地址</span></h2>
    </div>
</div><!-- 20141212-栏目-end -->
<div class="hr-20"></div>
<!-- 20141216-我的收货地址-表单-编辑地址-start -->
<div class="myAddress-edit" id="myAddress-edit">
    <div class="form-edit-panels" id="form-edit-panels">
        <form id="myAddress-form" action="shippingServlet?mod=addAddress&payAdd=yes" autocomplete="off" method="post" data-type="add">

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
                                <label class="inputbox"><input id="defaultFlag" type="checkbox" class="checkbox" name="defaultFlag" id="myAddress-default">
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
                                    <input type="button" id="button-ok" class="button-action-ok" value="添加新地址"><a href="javascript:;" id="button-cancel" class="button-action-cancel"><span>清&nbsp;&nbsp;空</span></a>
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

<input type="hidden" id="gouldAddress" name="unionPaySwitch" value="1">
</div>
</div>
</body>
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

$(function(){
    //支付下单界面不可选择默认地址
    $('#defaultFlag').click(function(event) {
        $('#defaultFlag').prop('checked',false);
        $('#default_address').text('在下单界面不可选择默认地址');
    });

    //收货地址之类的信息不可为空否则不可提交
    $('#button-ok').click(function(event) {
        var name = $("input[name='consignee']").val().length;
        var mobile = $("input[name='mobile']").val().length;
        var address = $("textarea[name='address']").val().length;
        if (name == 0) {
            $('#consignee-msg').html('<font color="red">请输入收货人</font>').attr('class','label-error');
            event.preventDefault();
            return false;
        };
        if (mobile == 0) {
            $('#tel-msg').html('<font color="red">请输入手机号</font>').attr('class','label-error');
            event.preventDefault();
            return false;
        };
        if (address == 0) {
            $('#address-msg').html('<font color="red">请输入详细地址</font>').attr('class','label-error');
            event.preventDefault();
            return false;
        };

        $.ajax({
            url: 'shippingServlet?mod=addAddress&payAdd=yes',
            type: 'post',
            dataType: 'text',
            data: {
                consignee:$('#consignee').val(),
                mobile:$('#mobile').val(),
                phone:$('#phone').val(),
                province:$('#province').val(),
                city:$('#city').val(),
                county:$('#county').val(),
                address:$('#address').val(),
                zipCode:$('#zipCode').val(),
                defaultFlag:$('#defaultFlag').prop('checked'),

            },
            success:function(data){
                var count = parseInt(data);
                if (count > 0) {
                    parent.location.reload();
                }else{
                    alert('新增失败');
                }
            }
        })


    });

    $('#mobile').click(function(event) {
        $('#mobile_id').attr('style','display: none');

        $('#tel-msg').html('').attr('class','');
    });

    $('#phone').click(function(event) {
        $('#phone_id').attr('style','display: none');

    });
    $('#address').click(function(event) {
        $('#address_id').attr('style','display: none');
        $('#address-msg').html('').attr('class','');
    });

    $('#consignee').click(function(event) {
        $('#consignee-msg').html('').attr('class','');
    });

})

function resetAddress(){
    status = "";
    document.getElementById("consignee").value = "";
    document.getElementById("mobile").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("address").value = "";
    document.getElementById("zipCode").value = "";
    document.getElementById('defaultFlag').checked = false;
    document.getElementById("button-ok").value = '添加新地址';

    document.getElementById("mobile_id").setAttribute('style','display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);');
    document.getElementById("phone_id").setAttribute('style','display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);');
    document.getElementById("address_id").setAttribute('style','display: block; position: absolute; cursor: text; float: left; z-index: 2; color: rgb(153, 153, 153);');
}
    </script>
</html>
