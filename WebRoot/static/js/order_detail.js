$(function(){
    //获取订单的状态
    var status = parseInt($('.order-state-progress').attr('data-url'));

    if (status == 20) {
        //获取时间
        var time = $("#li1").attr('data-value');
        $('#p1').text(time);
        $('#li1').attr('class','first completed');

        //获取时间
        var time = $("#li2").attr('data-value');
        $('#p2').text(time);
        $('#li2').attr('class','first completed');

        //订单啊信息group
        $('#order_info3').attr('style','display:block;');
        $('#order_info4').attr('class','list-group-item border-0 end');
    }else if (status == 30) {
        var time = $("#li1").attr('data-value');
        $('#p1').text(time);
        //获取时间
        var time = $("#li3").attr('data-value');
        $('#p3').text(time);
        //获取时间
        var time = $("#li2").attr('data-value');
        $('#p2').text(time);
        $('#li3').attr('class','first completed');

        //订单啊信息group
        $('#order_info2').attr('style','display:block;');
        $('#order_info3').attr('style','display:block;');
        $('#order_info4').attr('style','display:block;');
        $('#order_info4').attr('class','list-group-item border-0 end');
        $('#order_info3').attr('class','list-group-item border-0 end');
    }else if (status == 50) {
        //获取时间
        var time = $("#li4").attr('data-value');
        $('#p4').text(time);
        var time = $("#li1").attr('data-value');
        $('#p1').text(time);
        //获取时间
        var time = $("#li2").attr('data-value');
        $('#p2').text(time);
        var time = $("#li3").attr('data-value');
        $('#p3').text(time);
        $('#li4').attr('class','first completed');
        $('#li0').attr('class','first');
        //订单啊信息group
        $('#order_info2').attr('style','display:block;');
        $('#order_info3').attr('style','display:block;');
        $('#order_info4').attr('style','display:block;');
        $('#order_info4').attr('class','list-group-item border-0 end');
        $('#order_info3').attr('class','list-group-item border-0 end');
        $('#order_info2').attr('class','list-group-item border-0 end');
        $('#order_info1').attr('style','display:block;');
    };



})