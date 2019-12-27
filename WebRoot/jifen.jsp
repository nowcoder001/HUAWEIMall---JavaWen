<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>我的积分 - 华为商城</title>

    <link rel="stylesheet" href="static/css/ec.core.base.min.css">
    <link rel="stylesheet" href="static/css/main.min.css">
    <style type="text/css">
        .hwep-channel-description{
            display:none;
        }
        .minicart-pro-item .pro-other .p-title span.p-mini-tag-suit {
            display: block;
            border-radius: 2px;
            background: #ca141d;
            color: #fff;
            width: 42px;
            height: 17px;
            line-height: 17px;
            text-align: center;
            position: absolute;
            left: 0;
            border: none;
        }
        .minicart-pro-item .pro-other li.disabled .p-title span.p-mini-tag-suit {
            color: #fff;
        }
    </style>
    <script type="text/javascript">
        function setmouesbackimage(obj){
            for(var i=1;i<=3;i++){
                if(i == obj){
                    document.getElementById(i).style.display = "block";
                }else{
                    document.getElementById(i).style.display = "none";
                }
            }
        }
    </script>
</head>
<body>
<%@include file="head.jsp" %>
<div class="g" style="margin-top: 20px;">
<div class="fr u-4-5">
        <div class="section-header">
            <div class="fl">
                <h2><span>我的积分</span></h2>
            </div>
        </div>
        <div class="hr-20"></div>
        <div class="myGather-info">
            <div class="myGather-point">
                <dl>
                    <dt>可用积分</dt>
                    <dd><span id="usablePoint">${requestScope.JI_FEN }</span></dd>
                </dl>
            </div>
            <div class="myGather-redeem">
                <dl>
                    <dt>可换优惠券</dt>
                    <dd><span id="usableCash">${requestScope.COUPON_COUNT }</span><em>张/100元</em></dd>
                </dl>
            </div>
            <div class="myGather-timeout">
                <dl>
                    <dt>将要过期的积分：<span id="expireTime"></span></dt>
                    <dd><span id="expirePoint">0</span><em>基本不会过期</em></dd>
                </dl>
            </div>
        </div>
        <div class="hr-50"></div>
        <div class="my-tab">
            <div id="ec-tab-grade" class="ec-tab">
                <ul>
                    <li class="current"><a href="javascript:;" onmouseover="setmouesbackimage(1)"><span>积分说明</span></a></li>
                    <li class=""><a href="javascript:;" onmouseover="setmouesbackimage(2)"><span>常见问题</span></a></li>
                    <li class=""><a href="javascript:;" onmouseover="setmouesbackimage(3)"><span>积分明细</span></a></li>
                </ul>
                <div id="listBorder" class="ec-tab-arrow" style="width: 94px; left: 0px;"></div>
            </div>
        </div>

        <!-- 20160913-积分说明-start -->
        <!--  -->
            <div class="hwep-channel-description" id="1" style="display:block;"><div class="hwep-rule"><p><strong><span style="font-size:16px;"></span></strong></p><p><strong><span style="font-size:14px;">一、积分是什么？</span></strong></p><p><span style="font-size:14px;">积分是用户（指注册并登录华为帐号的用户，以下简称“用户”）在华为商城、华为视频、华为阅读、华为主题、华为音乐、华为云空间、华为应用市场、华为游戏中心、会员中心消费，参与活动或完成任务获得的回馈。用户可在华为的积分商城、华为商城个人中心等查看所获得的积分。如用户注销华为帐号，该用户所获的积分将被华为清空并作废。</span></p><p><span style="font-size:14px;">积分可以用于兑换优惠券、实物礼品、抽奖资格等福利，也可在华为商城下单购物时抵扣订单金额。具体的积分获取、使用规则请见下文规定。</span></p><p><br></p><p><strong><span style="font-size:14px;">二、如何获得积分？</span></strong></p><p><span style="font-size:14px;">（一）在华为商城购物获得积分</span></p><p><span style="font-size:14px;">用户在华为商城购买华为或荣耀品牌实物商品可获得实际支付金额数值的10%的积分，具体规则如下：</span></p><p><span style="font-size:14px;">1. 购物端口：用户须在华为商城APP、电脑版、触屏版等端口购物才可获得积分，在华为商城今日头条小程序、百度小程序购物无法获得积分。如用户通过华为商城微信小程序购物，使用华为帐号登录进行购买才可获得积分，使用微信帐号购物无法获得积分。</span></p><p><span style="font-size:14px;">2. 积分商品：用户须购买华为或荣耀品牌实物商品才可获得积分，购买虚拟商品、企业购商品、第三方商家商品等无法获得积分。</span></p><p><span style="font-size:14px;">3. 回馈的积分数量：回馈的积分数量为实际支付金额数值的10%，<strong>实际支付金额</strong>指用户实际以银行卡、第三方支付、华为商城代金券等支付的订单金额（不包括运费，订单中使用花瓣、积分发生的抵扣，各种优惠券及下单立减、满减等活动优惠）。积分小数点后数字将进行四舍五入，例如，<strong>积分商品的实际支付金额为994元，发放99个积分；积分商品的实际支付金额为995元，发放100个积分。如单个订单包含不同类型商品，将根据所含积分商品的实际支付金额计算积分</strong>。</span></p><p><span style="font-size:14px;">4. 积分发放：积分会在用户订单完成（订单已显示完成且未办理退货手续）后发放到用户的个人中心。购物所获积分如未使用，将在下一个自然年年底过期。</span></p><p><span style="font-size:14px;">5. 积分扣除：获得积分后若发生退货，华为将在订单退货流程处于“等待退款”状态后扣除退货商品对应的积分。如果扣除积分时用户的华为帐号中已无足够的积分，对于不足的部分，华为将按照100:1的比例将积分换算成人民币金额，并从应向用户支付的退款中扣除。例如，<strong>用户购物获得100积分，用户退货时其华为帐号的积分数量为0，华为将从其退款金额中再扣除1元</strong>。</span></p><p><span style="font-size:14px;">（二）在华为终端云服务消费获得积分</span></p><p><span style="font-size:14px;">用户在华为视频、华为阅读、华为主题、华为音乐、华为云空间、华为应用市场、华为游戏中心消费（购买花币卡除外），订单完成后，可获得实际支付金额（不包括订单中使用阅饼、花币以及各种优惠券等发生的抵扣）数值的50%的积分。积分小数点后数字将进行四舍五入，例如，购买999元的商品，发放500个积分。</span></p><p><span style="font-size:14px;">（三）参与华为商城活动获得积分</span></p><p><span style="font-size:14px;">用户在华为商城参加签到、评价商品等活动，可以获取积分，积分获取规则和使用有效期等以具体的活动规则为准。</span></p><p><span style="font-size:14px;">（四）在会员中心APP完成任务获得积分</span></p><p><span style="font-size:14px;">用户在会员中心APP领取并完成指定任务后，可获得相应积分，具体以任务规则为准。</span></p><p><br></p><p><strong><span style="font-size:14px;">三、如何使用积分？</span></strong></p><p><span style="font-size:14px;">（一）在华为商城购物使用积分抵扣订单金额</span></p><p><span style="font-size:14px;">1. 用户可在华为商城（华为商城今日头条小程序、百度小程序除外）下单购物时使用积分抵扣订单金额。如用户通过华为商城微信小程序购物，使用华为帐号登录购物才能使用积分抵扣订单金额，使用微信帐号购物不支持积分抵扣。每100积分可在下单购物时抵扣1元，积分使用的最小值是100。</span></p><p><span style="font-size:14px;">2. 华为商城的<strong>抢购商品、优购码商品、优享购商品、企业购商品、第三方商家商品和虚拟商品</strong>不支持积分抵扣，其他商品是否可用积分抵扣以商品确认订单页面相关提示为准。</span></p><p><span style="font-size:14px;">3. 使用积分、花瓣或其他优惠时，单个订单中可使用花瓣、积分抵扣的金额累计不超过该订单中允许使用花瓣和积分抵扣的商品<strong>应付总金额</strong>（指扣除运费、优惠券/下单立减/满减等活动优惠金额后的订单金额）的30%。</span></p><p><span style="font-size:14px;">4. 订单一经提交无法再修改积分使用数量，如需修改请重新提交订单，用户自行取消订单后积分将在一定时间内返还到其华为帐号。使用积分后若发生退货，华为将在订单退货流程处于“等待退款”状态后将退货商品使用的积分返还到用户的华为帐号。</span></p><p><span style="font-size:14px;">5. 积分不可兑现、转让、售卖或跨帐号使用，且使用积分抵扣部分不开发票，使用积分抵扣部分作为销售折扣在发票上单独列示。</span></p><p><span style="font-size:14px;">（二）使用积分获取福利</span></p><p><span style="font-size:14px;">用户可在华为的积分商城、华为商城会员频道等使用积分兑换优惠券、实物礼品或抽奖机会等福利，具体参与条件、兑换规则等请您留意相应页面中的规则。</span></p><p><br></p><p><strong><span style="font-size:14px;">四、积分有效期提示</span></strong></p><p><span style="font-size:14px;">获得但未使用的积分，将在下一个自然年年底过期（活动页面积分有效期有特殊约定的除外），例如，2019年12月31日将清空并作废用户在2018年度获得但未使用的积分。积分过期作废，请注意及时使用。</span></p><p><br></p><p><strong><span style="font-size:14px;">五、违约行为</span></strong></p><p><strong><span style="font-size:14px;">凡以违反积分规则的方式或采用不正当手段（包括但不限于作弊、恶意刷分、扰乱/破坏系统、恶意利用系统或者规则漏洞）获取、使用积分，华为有权根据其行为恶劣程度决定扣除用户帐号内所有或部分积分，对于已使用积分，华为有权要求该用户返还已抵扣的订单金额或所兑换的礼品或权益。</span></strong></p></div></div>
        <!-- 20160913-积分说明-end -->
        <!-- 20160913-常见问题-start -->
            <div class="hwep-channel-description" id="2" style="display: none;"><div class="hwep-faq"><p style="margin:5px 0;line-height:150%"><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">Q1</span></b><b><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">积分是什么？</span></span></b></p><p style="margin:5px 0;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">A</span><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span></span><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">积分是用户在华为商城、华为视频、华为阅读、华为主题、华为音乐、华为云空间、华为应用市场、会员中心消费，参与活动或完成任务获得的回馈。</span><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;"></span></span></p><p style="margin:5px 0 25px;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif"> </span></p><p style="margin:5px 0;line-height:150%"><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">Q2</span></b><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：积分有什么用？</span></b></p><p style="margin:5px 0;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">A</span><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：积分可以用于兑换优惠券、实物礼品、抽奖资格等福利，也可在华为商城下单购物时抵扣订单金额（部分商品不支持），每100积分可抵扣1元</span></span><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">。</span></span></p><p style="margin:5px 0 25px;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif"> </span></p><p style="margin:5px 0;line-height:150%"><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">Q3</span></b><b><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：如何获得</span><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">积分？</span></span></b></p><p style="margin:5px 0;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">A</span><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span></span><span style="font-family:微软雅黑, sans-serif;line-height:150%;font-size:14px;">可以通过以下4种途径获得积分：</span></p><p style="margin:5px 0;line-height:150%"><span style="font-family:微软雅黑, sans-serif;font-size:14px;">1）在华为商城购物获得积分：用户在华为商城购买华为或荣耀品牌实物商品可获得实际支付金额数值的10%的积分；</span></p><p style="margin:5px 0;line-height:150%"><span style="font-family:微软雅黑, sans-serif;font-size:14px;">2）在华为终端云服务消费获得积分：用户在华为视频、华为阅读、华为主题、华为音乐、华为云空间、华为应用市场消费（购买花币卡除外），订单完成后，可获得实际支付金额数值的50%的积分；</span></p><p style="margin:5px 0;line-height:150%"><span style="font-family:微软雅黑, sans-serif;font-size:14px;">3）参与华为商城活动获得积分：用户在华为商城参加签到、评价商品等活动，可以获取积分，积分获取规则和使用有效期等请以具体的活动规则为准；</span></p><p style="margin:5px 0;line-height:150%"><span style="font-family:微软雅黑, sans-serif;font-size:14px;">4）在会员中心APP完成任务获得积分：用户在会员中心APP领取并完成指定任务后，可获得相应积分；</span></p><p style="margin:5px 0;line-height:150%"><span style="font-family:微软雅黑, sans-serif;line-height:150%;font-size:14px;">详细内容以积分规则、页面相关说明为准。</span></p><p style="margin:5px 0 25px;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif"> </span></p><p style="margin:5px 0;line-height:150%"><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">Q4</span></b><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：积分的有效期是多久？</span></b><br></p><p style="margin:5px 0;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">A</span><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span></span><span style="font-family:微软雅黑, sans-serif;font-size:14px;">获得但未使用的积分，将在下一个自然年年底过期（活动页面积分有效期有特殊约定的除外），例如，2019年12月31日将清空并作废用户在2018年度获得但未使用的积分。积分过期作废，请注意及时使用。</span><span style="font-family:微软雅黑, sans-serif;font-size:14px;">若在使用积分有效期之外发生退款，该部分积分不予退还。</span></p><p style="margin:5px 0 25px;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif"> </span></p><p style="margin:5px 0;line-height:150%"><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">Q5</span></b><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：如何使用积分？</span></b></p><p style="margin:5px 0;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">A</span><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span></span><span style="font-family:微软雅黑, sans-serif;font-size:14px;">用户可在华为商城（华为商城今日头条小程序、百度小程序除外）下单购物时使用积分抵扣订单金额。每100积分可在下单购物时抵扣1元，积分使用的最小值是100。商品是否可用积分抵扣以商品确认订单页面相关提示为准。</span></p><p><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">用户也可在华为的积分商城、华为商城会员频道等使用积分兑换优惠券、实物礼品或抽奖机会等福利，具体参与条件、兑换规则等请您留意相应页面中的规则。</span></p><p><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;"><br></span></p><p style="margin:5px 0;line-height:150%"><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">Q6</span></b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">在华为商城购物使用积分抵扣订单金额有上限吗？</span></b><br></p><p style="margin:5px 0;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">A</span><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span></span><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">使用积分、花瓣或其他优惠时，单个订单中可使用花瓣、积分抵扣的金额累计不超过该订单中允许使用花瓣和积分抵扣的商品应付总金额（指扣除运费、优惠券/下单立减/满减等活动优惠金额后的订单金额）的30%。</span></p><p style="margin:5px 0 25px;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif"> </span></p><p style="margin:5px 0;line-height:150%"><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">Q7</span></b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">积分在哪些情况会被扣减？</span></b></p><p style="margin:5px 0;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">A</span><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span><span style="font-family:微软雅黑, sans-serif;font-size:14px;">商品退货扣减：您退货后，需扣除该商品所获得的积分，如账户积分已使用，则从商品退款金额中扣除相应现金。如订单中享有其他优惠，则以其他优惠规则办理相应退还手续。</span></p><p><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">违约行为扣减：凡以违反积分规则的方式或采用不正当手段（包括但不限于作弊、恶意刷分、扰乱/破坏系统、恶意利用系统或者规则漏洞）获取、使用积分，华为有权根据其行为恶劣程度决定扣除用户帐号内所有或部分积分，对于已使用积分，华为有权要求该用户返还已抵扣的订单金额或所兑换的礼品或权益。</span></p><p><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;"><br></span></p><p style="margin:5px 0;line-height:150%"><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">Q8</span></b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">使用积分下单的商品退货后，积分是否退回？多久退回？</span></b><br></p><p style="margin:5px 0;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">A</span><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：商品退货后积分将在一段时间内退回到原帐号内，如果正常退还的积分在退还时已经过期，则不予退还。</span></p><p style="margin:5px 0 25px;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif"> </span></p><p style="margin:5px 0;line-height:150%"><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">Q9</span></b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">积分是否支持抵扣运费？</span></b></p><p style="margin:5px 0;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">A</span><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：积分不可抵扣运费。</span></span></p><p style="margin:5px 0 25px;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif"> </span></p><p style="margin:5px 0;line-height:150%"><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">Q10</span></b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span><b><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">之前的购买会补送积分么？</span></b><br></p><p style="margin:5px 0;line-height:150%"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">A</span><span style="line-height:150%;font-family:微软雅黑, sans-serif"><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">：</span></span><span style="font-family:微软雅黑, sans-serif;font-size:14px;">华为商城购物：2016年11月1日0:00以后符合规定的订单均可享受积分赠送，此前订单不补送积分。</span></p><p><span style="line-height:150%;font-family:微软雅黑, sans-serif;font-size:14px;">华为终端云服务消费：2019年5月17日00:00以后在华为视频、华为阅读、华为主题、华为音乐、华为云空间、华为应用市场消费（购买花币卡除外），符合规定的订单均可享受积分赠送，此前订单不补送积分。</span></p></div></div>


        <div class="">
            <div class="hwep-channel-description" id="3" style="display: none;">
                <div class="list-group-title">
                    <table cellspacing="0" cellpadding="0" border="0">
                        <thead>
                        <tr>
                            <th class="col-date">日期</th>
                            <th class="col-exp">收入/支出</th>
                            <th class="col-soure">备注</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="list-group hidden" id="list-group"><div class="list-group-empty">您暂时没有相关记录。</div></div>

                <div class="list-group-empty" id="list-group-empty">您暂时没有相关记录。</div>

                <div class="list-group-page">
                    <div class="pager" id="list-pager">
                        <ul></ul>
                    </div>
                </div>
        </div>
        <input type="hidden" value="https://member.vmall.com" id="domainUc">


        </div>
</div>
<%@include file="menu.jsp" %>
</div>

<%@include file="font.jsp"%>
</body>
</html>
