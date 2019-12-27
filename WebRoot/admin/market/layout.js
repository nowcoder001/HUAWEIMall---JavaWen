$(function(){
    $('#dd').dialog('close');
    /*$.ajax({
        url: 'admin/productServlet?mod=productSet',
        type: 'post',
        dataType: 'json',
        data: {

        },
        success:function(data){
            for (var i = 0; i < data.length; i++) {
                image[i] = data[i].url;
                productId[i] = data[i].productId;

            };
        }
    })*/
    var productId = 0;  //全局id
    var index = 0;  //全局index
    $('#tt').tabs({
        border:false,
        fit:true,
        onSelect:function(title){

        }
    });

    $('#dg').datagrid({
        url:'admin/productServlet',
        fitColumns:true,
        height:270,
        title:'首页热销上方商品',
        singleSelect:true,
        queryParams:{
            mod:'indexTopProduct'
        },
        columns:[[
            {field:'id',title:'商品id',width:100},
            {field:'index',title:'排序',width:100,
            formatter:function(value,row,index){
                return '<font color="red" size = "5" >'+(index+1)+'</font>';
            }},
            {field:'mallImages',title:'商品信息',width:130,
            formatter:function(value,row,index){
                //字符串转json格式
                var img = eval('(' +value+ ')');
                var imgUrl = img[0].url;
                return '<img src="'+imgUrl+'" width="45px" height="45px"/><a href="product.jsp?id='+row.id+'" target="_blank">'+row.name+'</a>';

            }},
            {field:'indexlocation',title:'首页位置',width:100,
            formatter:function(value,row,index){
                return '首页热销商品上方';

            }},
            {field:'showImage',title:'显示图片',width:100,
            formatter:function(value,row,index){
                return '<img src="'+value+'" width="65px" height="45px"/>';

            }},
            {field:'set',title:'操作',width:100,
            formatter:function(value,row,index){
                return '<a name="update_product" class="easyui-linkbutton" data-id="'+row.id+'" data-index="'+index+'"></a><a name="update_product_image" class="easyui-linkbutton" data-img="'+row.showImage+'" data-index="'+index+'"></a>';

            }},
        ]],
        onLoadSuccess:function(){
            $('a[name="update_product"]').linkbutton({plain:true,iconCls:'fa fa-sun-o',text:'修改商品'}).click(function(event) {

                //获取id
                var id = $(this).attr('data-id');
                //获取下标
                var index = $(this).attr('data-index');
                var selectHtml = "选择商品&nbsp;<select id='cc'"+
                "class='easyui-combogrid' name='product_name' style='width:100px;'></select>";

                $.messager.confirm('修改商品', selectHtml, function(r) {
                    if (r) {

                        $('#dg').datagrid('load',{
                            mod:'indexTopProduct',
                            product_id:productId,
                            index:index
                        })

                        $.messager.show({
                            title: '提醒消息！',
                            msg: '<font color="#7FFF00">修改成功</font>',
                            timeout: 3000,
                            showType: 'slide'
                        });
                    }
                })

                $('#cc').combogrid({
                    panelWidth:505,
                    panelHeight:200,
                    fit:true,
                    fitColumns:true,
                    value:id,
                    idField:'id',
                    textField:'name',
                    url:'admin/productServlet?mod=product&page=1&rows=999',
                    columns:[[
                        {field:'name',title:'商品名称',width:100,
                        formatter:function(value,row,index){
                            return '<a href="product.jsp?id='+row.id+'" target="_blank"><font color="red">'+row.name+'</font></a>';
                        }},
                        {field:'mallImages',title:'商品图片',width:60,
                        formatter:function(value,row,index){
                            //字符串转json格式
                            var img = eval('(' +value+ ')');
                            var imgUrl = img[0].url;
                            return '<img src="'+imgUrl+'" width="45px" height="45px"/>';

                        }},
                        {field:'price',title:'商品价格(元)',width:60},
                        {field:'stock',title:'库存',width:60}
                    ]],
                    onClickRow:function(index,row){
                        productId = row.id;

                    }

                });

            });

            $('a[name="update_product_image"]').linkbutton({plain:true,iconCls:'fa fa-image',text:'修改显示图片'}).click(function(event) {
                //获取原来图片
                var img = $(this).attr('data-img');
                index = $(this).attr('data-index');

                $('#oldImage').html('<img src="'+img+'" width="140px" heihgt="100px"/>');
                $('#dd').dialog('open');


            });
        }
    });


    $('#submit_button').click(function(event) {
        //获取更新后的图片
        var url = $('#fileput').filebox('getText');
        //获取base64
        var base = $('#Img').attr('src');
        $('#dg').datagrid('load',{
            mod:'indexTopProduct',
            img_url:url,
            index:index,
            base64:base
        })
        $('#dd').dialog('close');
        $.messager.show({
            title: '提醒消息！',
            msg: '<font color="#7FFF00">修改成功</font>',
            timeout: 3000,
            showType: 'slide'
        });

    });

    //后台网站设置
    $.ajax({
        url: 'admin/adminServlet',
        type: 'post',
        dataType: 'json',
        data: {
            mod:'webSet'
        },
        success:function(data){
            //标题
            $('input[name="info[web_title]"]').val(data[0].title);
            //关键词
            $('input[name="info[web_keywords]"]').val(data[0].keyWord);
            //描述
            $('textarea[name="info[web_description]"]').val(data[0].describe);
            //客服qq
            $('input[name="info[web_qq]"]').val(data[0].serviceQQ);
            //客服电话
            $('input[name="info[web_phone]"]').val(data[0].serviceMobile);
            //版权所有
            $('input[name="info[web_copyright]"]').val(data[0].copy);
            //备案
            $('input[name="info[web_icp]"]').val(data[0].ICP);
            //热门搜索
            $('input[name="info[web_hotword]"]').val(data[0].hotSearch);
            //统计
            $('textarea[name="info[web_tongji]"]').val(data[0].stat);

        }
    })


    $('input[name="pesubmit"]').linkbutton({
        onClick:function(){

            $.ajax({
                url: 'admin/adminServlet',
                type: 'post',
                dataType: 'json',
                data: {
                    mod:'updateWebSet',
                    title:$('input[name="info[web_title]"]').val(),
                    keyWord:$('input[name="info[web_keywords]"]').val(),
                    describe:$('textarea[name="info[web_description]"]').val(),
                    serviceQQ:$('input[name="info[web_qq]"]').val(),
                    serviceMobile:$('input[name="info[web_phone]"]').val(),
                    copy:$('input[name="info[web_copyright]"]').val(),
                    ICP:$('input[name="info[web_icp]"]').val(),
                    hotSearch:$('input[name="info[web_hotword]"]').val(),
                    stat:$('textarea[name="info[web_tongji]"]').val(),
                },
                success:function(data){

                }
            })

            window.location.reload();

                    $.messager.show({
                        title: '提醒消息！',
                        msg: '<font color="#7FFF00">设置成功</font>',
                        timeout: 3000,
                        showType: 'slide'
                    });
        }
    });

})

