$(function(){
    $('#dd').dialog('close');
    $('#dg').datagrid({
        url:'admin/commentAdminServlet?mod=getAllComment',
        pagination:true,
        striped:true,
        fit:true,
        fitColumns:true,
        singleSelect:true,
        columns:[[
            {field:'index',title:'序号/评论时间',width:100,align:'center',
                formatter:function(value,row,index){
                    return '<font color="red" size = "5">'+index+'</font><br/><br/><font color="#bbb">'+row.createTime+'</font>';
            }},
            {field:'content',title:'评价内容',width:100,
                formatter:function(value,row,index){
                    //把内容json格式转换成json对象
                    var contentJson = eval('(' +row.content+ ')');
                    var contentHtml = '<p>'+contentJson[0].content+'</p>';
                    for (var i = 0; i < contentJson.length; i++) {
                        contentHtml += '<img src="'+contentJson[i].imagesUrl+'" width="45px" height="45px"/>';
                    };
                    return contentHtml;
            }},
            {field:'replyContent',title:'回复的内容',width:100,
                formatter:function(value,row,index){
                    if (value == null || value == "") {
                        return '<img src="./Style/image/reply_168.png" width="43px" height="35px"/>商品未回复';
                    }else{
                        return '<img src="./Style/image/success_.png" width="45px" height="45px"/>回复：'+value;
                    }
            }},
            {field:'user',title:'评论用户',width:100,align:'center',
                formatter:function(value,row,index){
                    if (row.user_img == null || row.user_img == "") {
                        return '<img src="./Style/image/pg1.png" width="45px" height="45px"/><p>'+row.user.user_name+'</p>'
                    }else{
                        return '<img src="'+row.user.user_img+'" width="45px" height="45px"/><p>'+row.user.user_name+'</p>'
                    }
            }},
            {field:'orderItem',title:'商品信息',width:100,align:'left',
                formatter:function(value,row,index){
                    return '<a href="product.jsp?id='+row.orderItem.productId+'" target="_blank"><img src="'+row.orderItem.productImage+'" width="45px" height="45px"/> '+row.orderItem.productName+'</a>'
            }},
            {field:'id',title:'操作',width:100,
                formatter:function(value,row,index){
                    return '<a name="reply_comment" class="easyui-linkbutton" data-id="'+row.id+'"></a><a name="delete_comment" class="easyui-linkbutton" data-id="'+row.id+'"></a>';
            }},
        ]],
        onLoadSuccess:function(data){
                $('a[name="reply_comment"]').linkbutton({plain:true,iconCls:'fa fa-mail-reply',text:'回复'}).click(function(event) {
                    //获取评论id
                    var commentId = $(this).attr('data-id');
                    $.ajax({
                        url: 'admin/commentAdminServlet',
                        type: 'post',
                        dataType: 'json',
                        data: {
                            mod:'getContent',
                            comm_id:commentId
                        },
                        success:function(data){
                            var contentJson = eval('(' +data[0].content+ ')');
                            var contentHtml = '<p>'+contentJson[0].content+'</p>';
                            for (var i = 0; i < contentJson.length; i++) {
                                contentHtml += '<img src="'+contentJson[i].imagesUrl+'" width="45px" height="45px"/>';
                            };
                            var reply = "";  //回复
                            //回复内容
                            if (data[0].replyContent == null || data[0].replyContent == "") {

                            }else{
                                reply = data[0].replyContent;
                            }

                            //评论内容
                            $('#comment_show').html(contentHtml+'<br/><br/>');
                            //回复
                            $('#reply_content').textbox('setValue',reply);
                            //赋值
                            $('#submit_button').attr('data-id',commentId);
                        }
                    })


                    //弹出窗口
                    $('#dd').dialog('open');
                    $('#dd').dialog({
                        title:'回复评论',
                        width:400,
                        height:300,
                    });


                });;

                $('a[name="delete_comment"]').linkbutton({plain:true,iconCls:'fa fa-trash',text:'删除评论'}).click(function(event) {
                    //获取评论id
                    var id = $(this).attr('data-id');
                    $.messager.confirm('请选择清楚哦', '是否删除吗！！', function(r) {
                        if (r) {
                            $.ajax({
                                url: 'admin/commentAdminServlet',
                                type: 'post',
                                dataType: 'text',
                                data: {
                                    mod:'deleteComment',
                                    comment_id:id
                                },
                                success:function(data){
                                    $('#dg').datagrid('reload');
                                    $.messager.show({
                                        title: '信息通知',
                                        msg: '<font color="#7FFF00">'+data+'</font>',
                                        timeout: 3000,
                                        showType: 'slide'
                                    });
                                }
                            })

                        }
                    });



                });;
        }
    });

    $('#submit_button').click(function(event) {
        var id = $(this).attr('data-id');

        var value = $('#reply_content').textbox('getValue');

        $.ajax({
            url: 'admin/commentAdminServlet',
            type: 'post',
            dataType: 'text',
            data: {
                mod:'replyComment',
                comment_id:id,
                reply_content:value
            },
            success:function(data){
                $('#dg').datagrid('reload');
                $('#dd').dialog('close');
                $.messager.show({
                    title: '回复信息详情',
                    msg: '<font color="#7FFF00">'+data+'</font>',
                    timeout: 3000,
                    showType: 'slide'
                });
            }
        })


    });

})