$(function(){
    var servlet = "";
    var money = 0;  //优惠券金额
    var couponId;  //优惠券id
    //获取收货地址的li标签有多少个
    var shippings = $('#shippinglist').children('li').length;
    //收货地址的li标签大于3 既隐藏新增收货地址的li标签
    if (shippings > 3) {
        $('#address-empty').css('display', 'none');
        $('#upAddAddressButton').css('display','block');
    }else{
        $('#address-empty').css('display', 'block');
    }
    for (var i = 0; i < shippings; i++) {
        //如果data-url属性为true 既为默认地址
        var defaul = $('#defaultAddress'+i).attr('data-url');
        if (defaul == 'true1') {
            $('#defaultAddress'+i).css('display', 'block');
        }else{
            $('#defaultAddress'+i).css('display', 'none');
        }
    };



    //全局变量
    var isSelect = false; //是否选择收货地址
    var num = 0;
    //点击li标签
    $('#shippinglist').children('li').click(function(event) {

        //判断是否有用户选择了收货地址
        for (var i = 1; i <= 3; i++) {

        };
        //获取id后的数字
        var liId = $(this).attr('id');
        //获取收货地址id
        var shippingId = $(this).attr('index');
        liId = liId.replace(/[^0-9]/ig,"");
        for (var i = 1; i <= 3; i++) {
            if (liId == i) {
                $("#myAddress-"+i).attr('style','background: url(static/img/btn-address.png) no-repeat;');
                isSelect = true;

            }else{
                $("#myAddress-"+i).attr('style','border: 1px solid #eaeaea;');
            }
        };
        //获取提交按钮的href
        var submit_href = $('#checkoutSubmit').attr('data-url');
        if (num == 0) {
            servlet = submit_href;
            num++;
        };
        $('#checkoutSubmit').attr('data-url',servlet);

        submit_href = $('#checkoutSubmit').attr('data-url');
        if ($('.payconfirm_').attr('data-value') == 0) {

            //设置提交按钮的href  可知道选择的是哪个收货地址id
            $('#checkoutSubmit').attr('data-url',submit_href+''+shippingId);

        }else{  //属于购物车的地址
            $('#checkoutSubmit').attr('data-url',submit_href+''+shippingId+"&type=cart");
        }


    });
    //没选择收货地址不可提交订单
    $('#checkoutSubmit').click(function(event) {
        var submit_href = $('#checkoutSubmit').attr('data-url');
        //为ture 既说明选择了收货地址
        if (isSelect) {
            location.href = submit_href;
        }else{
            $('#no-delivery-address-tips').attr('style','display: block');
        }

    });

    //隐藏弹出的窗口
    $('.box-ok').click(function(event) {
        $('.fade').css('display', 'none');
        $('.succ-pop').css('display', 'none');
    });

    $('#address-empty').click(function(event) {
        $('.fade').css('display', 'block');
        $('.succ-pop').css('display', 'block');
    });

    //打开优惠卷窗口
    $('#coupon-use-link').click(function(event) {
        $('.ol_box_mask').css('display','block');
        $('#order-coupon-box').css('display', 'block');

    });

    //关闭优惠券窗口
    $('.box-close').click(function(event) {
        $('.ol_box_mask').css('display','none');
        $('#order-coupon-box').css('display', 'none');
    });
    $('#not_use_coupon_btn').click(function(event) {
        $('.ol_box_mask').css('display','none');
        $('#order-coupon-box').css('display', 'none');
    });

    //只可选择一个
    $('li[name="effCouponInfo"]').click(function(event) {
        //获取li 的下标
        var j = $(this).attr('data');
        //获取优惠券id
        couponId = $(this).attr('data-id');
        //获取优惠券金额
        money = $(this).attr('data-money');
        if ($('li[name="effCouponInfo"]').length == 1) {
            $("#li_"+j).attr('class','order-roll-detail current');
        }else{

            for (var i = 0; i < $('li[name="effCouponInfo"]').length; i++) {
                if (j == i) {
                    $("#li_"+i).attr('class','order-roll-detail current');
                }else{
                    $("#li_"+i).attr('class','order-roll-detail');
                }
            };

        }

    });
    var payment = parseInt($('#cash_payment').text());
    //选择使用了优惠卷
    $('#use_coupon_btn').click(function(event) {

        //alert($('#eff_couponUlId_VMALL-HUAWEIDEVICE').children('li').length);
        var count = 0;
        for (var i = 0; i < $('li[name="effCouponInfo"]').length; i++) {
            if ($("#li_"+i).attr('class') == 'order-roll-detail current') {

                count++;

             }
        };


        if (count > 0 ) {
            if (servlet == "" || servlet == null) {
                alert('先选择收货地址再选择优惠券');
            }else{
                //修改价格显示show
                $('#coupon_money').text(money);

                $('#cash_payment').text(payment-money);
                $('#payableTotal').text(payment-money);

                //获取跳转地址
                var url = $('#checkoutSubmit').attr('data-url');

                $('#checkoutSubmit').attr('data-url',url+'&coupon_id='+couponId+'&money='+money);

                $('.ol_box_mask').css('display','none');
                $('#order-coupon-box').css('display', 'none');
            }
        }else{

            alert('请选择优惠券');
        }


    });
})