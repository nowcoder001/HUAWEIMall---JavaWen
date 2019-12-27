//全局sku
var color_sku = "极光蓝";  //颜色
var type_sku = "全网通4GB+64GB";  //版本
var proId = 0; //全局id
//颜色
function pro_color(obj,num){
    color_sku = obj.title;
    //颜色
    for (var i = 0; i < 3; i++) {
        if ((i+1) == num) {
            document.getElementById("pro_color"+(num)).setAttribute('style','border:1px solid #ca141d;');
        }else{
            document.getElementById("pro_color"+(i+1)).setAttribute('style','border:1px solid #a4a4a4;');
        }

    };
    document.getElementById("pro-select-sku").innerHTML = color_sku+" / "+type_sku+" / 官方标配";

}
function pro_type(obj,num){
    type_sku = obj.title;
    //版本
    for (var i = 0; i < 3; i++) {
        if ((i+1) == num) {
            document.getElementById("pro_type"+(num)).setAttribute('style','border:1px solid #ca141d;');
        }else{
            document.getElementById("pro_type"+(i+1)).setAttribute('style','border:1px solid #a4a4a4;');
        }

    };
    document.getElementById("pro-select-sku").innerHTML = color_sku+" / "+type_sku+" / 官方标配";
}

$(function(){

    var chilLi = $(".product-gallery-thumbs ul#pro-gallerys").children();
    //alert(chilLi.children('a').css('border'));
    //给图片的li标签都加上id
    chilLi.each(function(index, val) {
        var obj = $(val);

        obj.attr('id','li'+(index+1));
    });

    //加载网页获取最开始的图片
    $(".current a").css('border', '1px solid #ca141d');
    //鼠标移上去的事件
    $("#pro-gallerys li a img").mouseover(function(event) {
        //加载网页获取最开始的图片
        $("#pro-gallerys li a").css('border', '1px');
        //首先获取img的src属性
        var imgSrc = $(this).attr('src');
        imgSrc = imgSrc.replace('78_78','428_428');
        //替换图片src路径
        $("#pic-container div a img").attr('src',imgSrc);
        //对应的父节点更改a标签的样式
        $(this).parent().css('border', '1px solid #ca141d');

    });

    //鼠标移开的事件
    $("#pro-gallerys li a img").mouseout(function() {
        //对应的父节点更改a标签的样式
        var imga = $(this).parent();

        //获取id
        var liId = imga.parent().attr('id');
        //循环所有子元素
        chilLi.each(function(index, val) {
            var obj = $(val);

            if ('li'+(index+1) == liId) {
                imga.css('border', '1px solid #ca141d');
            }else{
                obj.children('a').css('border', '1px');
            }
        });

    });

    //点击打开视频
    $('.product-gallery-open').click(function(event) {
        //按钮隐藏
        $(this).css('display', 'none');
        //主图隐藏
        $('.product-gallery-img').css('display', 'none');
        //视频显示
        $('#video-container').css('display', 'block');
        //关闭视频按钮显示
        $('#gallery-video-close').css('display', 'block');
        //视频播放
        $('video').trigger('play');
    });

    //点击关闭视频
    $('#gallery-video-close').click(function(event) {
        //播放视频按钮显示
        $('.product-gallery-open').css('display', 'block');
        //主图显示
        $('.product-gallery-img').css('display', 'block');
        //视频隐藏
        $('#video-container').css('display', 'none');
        //关闭视频按钮隐藏
        $('#gallery-video-close').css('display', 'none');
        //视频暂停
        $('video').trigger('pause');
    });
    var id = UrlParam.paramValues("id");  //商品id
    proId = id;
    //ajax访问服务端 获取手机信息
    $.ajax({
        url: 'htmlProductServlet?mod=htmlGetProById&pro_id='+id+'',
        type: 'post',
        dataType: 'json',
        data: {

        },
        success:function(data){
            //商品名称
            $('#pro-name').text(data[0].name);
            $('#pro-price').html('<em>￥</em>'+data[0].price);
            $('#pro-sku-code2').text(data[0].coding);
            //商品副图
            var imgJson = eval('('+data[0].subImages+')');
            //拿出一张商品主图
            var mainImg = eval('('+data[0].mallImages+')');
            for (var i = 0; i < imgJson.length; i++) {
                $('#min_img'+(i+1)).attr('src',imgJson[i].url);
            };

            //设置第一张主图
            $('#main_img').attr('src',mainImg[0].url);
            //设置详情图片
            $('#kindPicture').html(data[0].detailImages);
            //设置title
            $('title').text(data[0].name);
            //设置弹窗内容
            $('h3.title').html(data[0].name+'添加购物车成功!');
            //给a标签赋值
            $('#box-ok').attr('data-url',data[0].id);
            //给下单的a标签赋值
            $('#product_buy').attr('data-url',data[0].id);
        }
    })

    //购买手机界面  数量增减操作
    $('#pro-quantity-plus').click(function(event) {
        //增加
        var pro_count = parseInt($('#pro-quantity').attr('value'));
        $('#pro-quantity').attr('value',pro_count+1);
        if (pro_count > 1) {
            $('#pro-quantity-minus').css('cursor', 'pointer');
        };
    });

    $('#pro-quantity-minus').click(function(event) {
        //减少操作
        var pro_count = parseInt($('#pro-quantity').attr('value'));
        if (pro_count > 1) {
            $('#pro-quantity').attr('value',pro_count-1);
        }
        if (pro_count == 1) {
            $('#pro-quantity-minus').css('cursor', 'not-allowed');
        };

    }).mouseover(function(event) {
        var pro_count = parseInt($('#pro-quantity').attr('value'));
        if (pro_count > 1) {
            $('#pro-quantity-minus').css('cursor', 'pointer');
        }else{
            $('#pro-quantity-minus').css('cursor', 'not-allowed');
        }
    });

    //隐藏弹出的窗口
    $('#cancel').click(function(event) {
        $('.fade').css('display', 'none');
        $('.succ-pop').css('display', 'none');
    });

    $('a.product-button02').click(function(event) {

        $.ajax({
            url: 'htmlProductServlet?mod=userLogin',
            type: 'post',
            dataType: 'text',
            data: {

            },
            success:function(data){
                //暂时修改为  是登录成功
                if (data == 0) {
                    document.getElementById("fade").style.display = 'block';
                    document.getElementById("succ-pop").style.display = 'block';
                    document.getElementById('h3_title').innerText = '还未登录，不可操作'
                    var a = document.getElementById('box-ok');
                    a.innerText = '去登录';
                    a.href = 'login.jsp';   //设置登录路径
                }else{  //登录成功执行下单
                    //获取商品id
                    var pro_id = $('#product_buy').attr('data-url');
                    //获取商品数量
                    var pro_count = $('#pro-quantity').val();
                    location.href = "orderServlet?mod=confirmOrder&pro_id="+pro_id+"&pro_count="+pro_count+"";
                }
            }
        })
    });

    //订单评价
    $('#submit_button').click(function(event) {
        var content = $('#counseltextid').val();
        var imgArray = new Array();   //图片的base64 数组
        var imgName = new Array();   //图片名字数组
        //获取商品id
        var pro_id = $('#product_buy').attr('data-url');
        $('#showui').children().each(function(index, val) {
             imgArray[index] = $('#srcimgid'+index).attr('src');
             imgName[index] = $('#srcimgid'+index).attr('data-filename');
        })

        $.ajax({
            url: 'commentServlet',
            type: 'post',
            traditional: true,
            dataType: 'text',
            data: {
                mod:'addComment',
                product_id:pro_id,
                content:content,
                contentBase64:imgArray,
                imageName:imgName
            },
            success:function(data){
                var count = parseInt(data);
                if (count > 0) {
                    //成功之后请空内容
                    $('#showui').html('');
                    $('#counseltextid').val('');

                    getProductComment();

                    //评论成功后隐藏评论框
                    $('#myConsultationForm').attr('style','display:none');
                }else{
                    //失败之后就不变
                    alert('评论失败');
                }
            }
        })


    });
    //获取用户商品评论状态
    getProductComment();

    $.ajax({
        url: 'commentServlet',
        type: 'post',
        dataType: 'text',
        data: {
            mod:'userComment',
            produId:proId+''
        },
        success:function(data){
            var count = parseInt(data);
            if (count > 0) {
                $('#myConsultationForm').attr('style','display:block');
            }else{
                $('#myConsultationForm').attr('style','display:none');
            }
        }
    })

});
//把商品加入购物车
function addcart(){
    $.ajax({
        url: 'htmlProductServlet?mod=userLogin',
        type: 'post',
        dataType: 'text',
        data: {

        },
        success:function(data){
            if (data == 0) {
                document.getElementById("fade").style.display = 'block';
                document.getElementById("succ-pop").style.display = 'block';
                document.getElementById('h3_title').innerText = '还未登录，不可操作'
                var a = document.getElementById('box-ok');
                a.innerText = '去登录';
                a.href = 'login.jsp';   //设置登录路径

            }else{ //否则就把商品加入购物车

                //获取商品id
                var proId = document.getElementById('product_buy').getAttribute('data-url');
                //获取商品数量
                var proCount = document.getElementById('pro-quantity').value;
                $.ajax({
                    url: 'orderServlet?mod=cartOrder',
                    type: 'post',
                    dataType: 'text',
                    data: {
                        pro_id:proId,
                        pro_count:proCount
                    },
                    success:function(data){
                        document.getElementById("fade").style.display = 'block';
                        document.getElementById("succ-pop").style.display = 'block';
                        document.getElementById('h3_title').innerText = data;
                        var a = document.getElementById('box-ok');
                        a.innerText = '去购物车';
                        a.href = 'orderServlet?mod=getUserCart';   //设置购物车路径

                    }
                })

            }
        }
    })

}

function getProductComment(){
    //获取商品的评价
    $.ajax({
        url: 'commentServlet',
        type: 'post',
        dataType: 'json',
        data: {
            mod:'getCommentByProductId',
            proIdd:proId+'' //商品id
        },
        success:function(data){
            if (data.length > 0) {
                var html = "";
                var txImg = "static/images/defaultface_user_after.png";  //用户头像
                var reply = "";   //客服回复
                for (var i = 0; i < data.length; i++) {
                    if (data[i].user.user_img == null || data[i].user.user_img == "") {

                    }else{
                        var txImg = data[i].user.user_img;  //用户头像
                    }
                    //把内容json格式转换成json对象
                    var imgJson = eval('(' +data[i].content+ ')');
                    var imgUrl = "";
                    for (var j = 0; j < imgJson.length; j++) {
                        imgUrl += ' <a href="#" class="img-wrap">'+
                                    '<img src="'+imgJson[j].imagesUrl+'">'+
                                '</a>';
                    }
                    if (data[i].replyContent == null || data[i].replyContent == "") {

                    }else{
                        //客服回复
                        reply = '<div class="product-reply">'+
                                                '<div class="product-reply-detail" replycount="1">'+
                                                '<div class="product-admin-reply clearfix">'+
                                                '<div class="admin-user-img">'+
                                                '<img src="static/images/defaultface_customer_service.png"></div>'+
                                                '<dl>'+
                                                '<dt class="red">华为商城客服</dt>'+
                                                '<dd>'+data[i].replyContent+'</dd>'+
                                                '</dl>'+
                                                '</div>'+
                                                '</div>'+
                                                '</div>'+
                                                '</div>';

                    }


                    html += '<li class="clearfix">'+
                            '<div class="product-comment-user">'+
                                '<p class="product-comment-user-img">'+
                                    '<img src="'+txImg+'" alt="">'+
                                        '<i class="icon-vip-level-0"></i>'+
                                '</p>'+
                                '<p class="product-comment-user-name">'+data[i].user.user_name+'</p>'+
                            '</div>'+
                            '<div class="product-user-comment">'+
                                '<div class="product-user-comment-detial">'+
                                    '<div class="h clearfix">'+
                                        '<div class="product-star">'+
                                            '<span class="starRating-area">'+
                                                '<s style="width:100%"></s>'+
                                            '</span>'+
                                        '</div>'+
                                        '<div class="product-user-comment-impress">'+
                                        '<dl></dl>'+
                                    '</div>'+
                                    '<div class="fr">'+
                                        '<div class="product-user-comment-time">'+data[i].createTime+'</div>'+
                                        '</div>'+
                                    '</div>'+
                                        '<div class="product-user-comment-word">'+imgJson[0].content+'</div>'+
                                            '<div class="user-comment-img">'+
                                                '<p class="img-small clearfix">'+
                                                   ' '+imgUrl+' '+
                                                    '</p>'+
                                                '<div class="clearfix">'+
                                                '</div>'+
                                                '</div>'+
                                                '</div>'+reply+'</li>';

                    };



                document.getElementById('product-comment-list').innerHTML = html;

            }else{

                document.getElementById('product-comment-list').innerHTML = '<p><center><font color="red" size="6">此商品暂时还未评论，来做第一个评论的幸运儿吧！(沙发)</font></center></p>';

            }


        }
    })
}
