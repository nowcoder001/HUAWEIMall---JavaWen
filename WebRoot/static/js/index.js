/*
* @Author: 首页脚本 - 小恶魔
* @Date:   2019-11-07 15:53:53
* @Last Modified by:   Marte
* @Last Modified time: 2019-11-19 08:55:14
*/

$(function(){
    $.ajax({
        url: 'userServlet',
        type: 'post',
        dataType: 'json',
        data: {
            method:'indexUpdate'
        },
        success:function(data){
            var userId = parseInt(data[0].id);
            //登录成功
            if (userId > 0) {
                 //获取用户信息
                $('#gg_loginName').text(data[0].user_name);
                var imgurl = data[0].user_img+"";
                if (imgurl != null && imgurl != "") {
                    $('#gg_customerPic').attr('src',data[0].user_img);
                };

                //隐藏
                $('#gg_unlogin').attr('style','display:none');
                //显示
                $('#gg_login').attr('style','display:block');
            }else{

            }

        }
    })

    //循环商品分类
    $.ajax({
        url: 'admin/categoryServlet?mod=htmlGetCategorys',
        type: 'post',
        dataType: 'json',
        data: {

        },
        success:function(data){
            //取出第三级的分类

            //循环顶级分类
            var html = '';
            var li = "";
            var ul = '</ul><ul class="subcate-list clearfix">';
            var ul2 = '<ul class="subcate-list clearfix">';
            var count = 0;  //用来计数
            var width = '0px';  //宽度
            //循环html分类代码
            for (var i = 0; i < data.length; i++) {
                var catChild = data[i].categories;
                for (var j = 0; j < catChild.length; j++) {

                    li += '<li class="subcate-item"><a href="htmlProductServlet?mod=htmlGetProductByName&cat_name='+catChild[j].name+'&cat_id='+catChild[j].id+'"target="_blank"><img class="j-lazy"src="'+catChild[j].imagesUrl+'" ><span>'+catChild[j].name+'</span></a></li>';

                    if (j == 0) {
                        li = ul2+li;
                    };
                    if ((j+1) % 4 == 0) {
                        count++;
                        li = li+ul;
                    };

                    if (catChild.length == (j-1)) {
                        li = li+"</ul>";
                    };

                };
                width = 'style="width:'+count * 210+'px"';
                if (width == 'style="width:0px"') {
                    width = "";
                };
                html = html + '<li id="zxnav_'+i+'"class="category-item "><div class="category-item-bg"><div class="category-info"><div class="category-info-detail"><div class="category-title"><a href="javascript:;"target="_blank"><span>'+data[i].name+'</span></a></div><a href="javascript:;"target="_blank"><span>荣耀</span></a><a href="javascript:;"target="_blank"><span>HUAWEI P系列</span></a><i class=""></i></div></div></div><div class="category-panels none" '+width+'>'+li+'</div></li>';

                li = "";

                count = 0;
            };

            //ol标签
            $('.category-list').html(html);


        }
    })

    //热销上方商品
    $.ajax({
        url: 'admin/productServlet?mod=indexTopProduct',
        type: 'post',
        dataType: 'json',
        data: {

        },
        success:function(data){
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<li><a target='_blank' href='product.jsp?id="+data[i].id+"' class='item'><img alt='"+data[i].name+"' src='"+data[i].showImage+"'></a></li>";
            };

            $('#home-promo-list').html(html);
        }
    })
    //热销单品
    $.ajax({
        url: 'htmlProductServlet',
        type: 'post',
        dataType: 'json',
        data: {
            mod:'recommendProduct'
        },
        success:function(data){
            var html = "";
            for (var i = 0; i < data.length; i++) {
                var img = eval('(' +data[i].mallImages+ ')');
                var imgUrl = img[0].url;
                html += '<li class="grid-items ">'+
                            '<a class="thumb" href="product.jsp?id='+data[i].id+'" target="_blank" >'+
                                '<p class="grid-img">'+
                                    '<img src="'+imgUrl+'"/>'+
                                '</p>'+
                                '<div class="grid-title">'+data[i].name+'</div>'+
                                '<p class="grid-price">'+data[i].price+'</p>'+
                                '<p class="grid-tips">'+
                                    '<em class="thumb"><span class="color01" style="background-color:#68BEFF">新品</span></em>'+
                                '</p>'+
                            '</a>'+
                        '</li>';
            };

            $('#grid-list').html(html);

        }
    })

    //网站设置标题
    $.ajax({
        url: 'admin/adminServlet',
        type: 'post',
        dataType: 'json',
        data: {
            mod:'webSet'
        },
        success:function(data){
            $('title').text(data[0].title);
        }
    })


})