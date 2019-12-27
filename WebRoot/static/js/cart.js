$(function(){
    var totalPrice = 0;   //总价
    var priceCount = 0;   //商品数量
    //选择样式
    $('input[readonly="readonly"]').click(function(event) {
        var id = $(this).attr('id');
        var class_ = $(this).attr('class');
        if (class_ == 'vam') {
            $(this).attr('class','vam checked');
            //获取商品的价格
            var price = parseInt($('.p-price-total'+id).attr('data-value'));
            totalPrice = price+totalPrice;
            //给总价赋值
            $('#totalPri').html("&yen;&nbsp;"+totalPrice+".00");
            priceCount++;
            $('#count_').text(priceCount);
        }else{
            $(this).attr('class','vam');
            //获取商品的价格
            var price = parseInt($('.p-price-total'+id).attr('data-value'));
            totalPrice = totalPrice-price;
            //给总价赋值
            $('#totalPri').html("&yen;&nbsp;"+totalPrice+".00");
            priceCount--;

            $('#count_').text(priceCount);

        }

    });
    var totalPrice2 = 0;
    $('#checkall').click(function(event) {
        var count = $('#price_count').children('div').length;
        var class_ = $(this).attr('class');
        if (class_ == 'vam') {
            totalPrice = 0;
            $('input[readonly="readonly"]').attr('class','vam');
            priceCount = 0;
            //alert(priceCount);
            //给总价赋值
            $('#totalPri').html("0.00");
        }else{
            totalPrice = 0;
            $('input[readonly="readonly"]').attr('class','vam checked');
            priceCount = count;
            //修改价格
            for (var i = 0; i < count; i++) {
                var price = parseInt($('.p-price-total'+i).attr('data-value'));
                totalPrice = totalPrice+price;

            };
            //给总价赋值
            $('#totalPri').html("&yen;&nbsp;"+totalPrice+".00");
        }



        //修改选择的商品数量
        $('#count_').text(priceCount);
    });

    //结算传数据给后台
    $('#sub-button').click(function(event) {
        //获取所有的明细表id
        var orderItem = new Array();
        var i = 0;
        $('input[readonly="readonly"]').each(function(index, val) {
            if ($(this).attr('class') == 'vam checked') {
                if ($(this).attr('data-value') > 0) {
                    orderItem[i] = $(this).attr('data-value');
                    i++;
                };
            };

        });
        if (priceCount < 1) {
            alert("请选择商品再结算");
            return false;

        }else{

            window.location.href="orderServlet?mod=cartConfirmOrder&itemId="+orderItem.toString()+""

        }
    });

    $('.p-del').click(function(event) {
        var itemId = $(this).attr('data-id');
        //获取div循环的下标
        var index = $(this).attr('data-index');

         if(confirm('确定要删除吗')==true){

           $.ajax({
                url: 'orderServlet?mod=deleteCartProduct',
                type: 'post',
                dataType: 'text',
                data: {
                    item_id:itemId
                },
                success:function(data){
                    var count = parseInt(data);
                    if (count > 0) {
                        //把div隐藏
                        $('#div_'+index).attr('style','display:none');
                    }else{
                        alert('删除失败');
                    }
                }
            })

        }else{

           return false;

        }



    });
})