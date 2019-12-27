$(function(){
    //全局变量
    var payMod = 0;
    $('span.pull-right').click(function(event) {
        var display = $('.orderDetails_words').css('display');
        if (display == 'none') {
            $('.orderDetails_words').attr('style','display:block');
        }else{
            $('.orderDetails_words').attr('style','display:none');
        }

    });
    //选择支付宝支付还是微信支付
    $('#wxpay').click(function(event) {
        payMod = 2;
        $('#wxpay').css('border','1px solid #DA3232');
        $('#alipay').css('border','1px solid #CCCCCC');
        $('#wxpay_i').attr('style','background: url(static/img/select_icon.png) no-repeat 0');
        $('#alipay_i').attr('style','');
    });
    //支付宝支付
    $('#alipay').click(function(event) {
        payMod = 1;
        $('#wxpay').css('border','1px solid #CCCCCC');
        $('#alipay').css('border','1px solid #DA3232');

        $('#alipay_i').attr('style','background: url(static/img/select_icon.png) no-repeat 0');
        $('#wxpay_i').attr('style','');
    });

    //支付按钮
    $('#confirmPay').click(function(event) {
        //获取data-url
        var servlet = $('#confirmPay').attr('data-url');

        location.href = servlet;
    });
})