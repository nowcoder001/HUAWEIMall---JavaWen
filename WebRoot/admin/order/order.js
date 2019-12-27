
$(function(){
    var shpId = 0;
    var nowPrice = 0;
    $('#tt').datagrid({
        fit:true,
        fitColumns:true,
        pagination:true,
        singleSelect:true,
        url:'admin/adminOrderServlet?mod=getAllOrder',
        columns:[[
        {field:'orderNo',title:'<i class="fa fa-circle-o-notch fa-spin"></i>订单号',width:80,align:'center',sortable:true},
        {field:'user',title:'<i class="fa fa-user"></i>所属用户',width:100,align:'center',
            formatter:function(value,row,index){
                return row.user.user_name;
            }},
        {field:'payment',title:'<i class="fa fa-money"></i>实付款(单位元)',width:80,align:'center'},
        {field:'status',title:'<i class="fa fa-paypal"></i>状态',width:80,align:'center',
            formatter:function(value,row,index){
                if (row.status == 10) {
                    return '未支付';
                }else if(row.status == 20){
                    return '已支付/等待发货';
                }else if(row.status == 30){
                    return '已发货';
                }else if(row.status == 50){
                    return '已完成';
                }else if(row.status == 0){
                    return '已取消';
                }
            }},
        {field:'id',title:'<i class="fa fa-cogs"></i>操作',width:60,align:'center',
            formatter:function(value,row,index){
                var province = row.shipping.receiverProvince;
                var city = row.shipping.receiverCity;
                var district = row.shipping.receiverDistrict;
                var address = row.shipping.receiverAddress;
                var userId = row.user.id;
                var buttonName = "";
                var actionHtml = "";
                if (row.status == 10) {
                    buttonName = '立即付款';
                    actionHtml = '<p><a name="statusButton" class="easyui-linkbutton" data-id="'+row.id+'" data-status="'+row.status+'">'+buttonName+'</a><a name="up_price" class="easyui-linkbutton" data-id="'+row.id+'" data-price="'+row.payment+'"></a></p><a name="up_shipping" class="easyui-linkbutton" data-orderid="'+row.id+'" data-id="'+row.shipping.id+'" data-user="'+userId+'" data-address="'+province+''+city+''+district+''+address+'"></a><a name="delete_order" class="easyui-linkbutton" data-id="'+row.id+'"></a>';
                }else if(row.status == 20){
                    buttonName = '立即发货';
                    actionHtml = '<p><a name="statusButton" class="easyui-linkbutton" data-id="'+row.id+'" data-status="'+row.status+'" data-address="'+province+''+city+''+district+''+address+'">'+buttonName+'</a></p><a name="up_shipping" class="easyui-linkbutton" data-orderid="'+row.id+'" data-id="'+row.shipping.id+'" data-user="'+userId+'" data-address="'+province+''+city+''+district+''+address+'"></a><a name="delete_order" class="easyui-linkbutton" data-id="'+row.id+'"></a>';
                }else if(row.status == 30){
                    buttonName = '点击完成';
                    actionHtml = '<p><a name="statusButton" class="easyui-linkbutton" data-id="'+row.id+'" data-status="'+row.status+'">'+buttonName+'</a></p><a name="delete_order" class="easyui-linkbutton" data-id="'+row.id+'"></a>';
                }else if(row.status == 50){
                    actionHtml = '订单已完成';
                }else if(row.status == 0){
                    actionHtml = '<a name="delete" class="easyui-linkbutton" data-id="'+row.id+'"></a>';
                }

                return actionHtml;
            }},
        ]],
        view: detailview,
        detailFormatter: function(rowIndex, rowData){
            var product = rowData.orderItems;
            var shippingHtml = '<table id="one-column-emphasis" summary="2007 Major IT Companies Profit" style="float:left;">'+
                                '<colgroup>'+
                                    '<col class="oce-first" />'+
                                '</colgroup>'+
                                '<thead>'+
                                    '<tr><th>收货地址信息</th></tr>'+
                                '</thead>'+
                                '<tbody>'+
                                    '<tr>'+
                                       '<td scope="col">收货人姓名：</td>'+
                                       ' <td scope="col">'+rowData.shipping.receiverName+'</td>'+
                                    '</tr>'+
                                    '<tr>'+
                                       ' <td>手机号码：</td>'+
                                        '<td>'+rowData.shipping.receiverMobile+'</td>'+
                                    '</tr>'+
                                    '<tr>'+
                                        '<td>收货地址：</td>'+
                                        '<td>'+rowData.shipping.receiverProvince+''+rowData.shipping.receiverCity+''+rowData.shipping.receiverDistrict+''+rowData.shipping.receiverAddress+'</td>'+
                                    '</tr>'+
                                '</tbody>'+
                            '</table>';
            var html = "";
            var th = '<thead><tr>'+


                        '<th scope="col">商品信息</th>'+
                        '<th scope="col">商品单价</th>'+
                        '<th scope="col">商品数量</th>'+
                        '<th scope="col">商品小计</th>'+
                        '</thead>'+
                        '</tr></thead>';
            var table = '<table id="newspaper-a" summary="Employee Pay Sheet" style="width: 50%;float:left;">';

            var html1 = '';
            for (var i = 0; i < rowData.orderItems.length; i++) {

                html1 = html1+ '<tr>'+
                            '<td><a href="product.jsp?id='+product[i].productId+'" target="_blank"><img src="'+product[i].productImage+'" style="width:78px;height:78px;border:0px;"/>'+product[i].productName+'</a></td>'+
                            '<td>'+product[i].current_unit_price+'</td>'+
                            '<td>'+product[i].quantity+'</td>'+
                            '<td>'+product[i].totalPrice+'</td>'+
                        '</tr>';


            };
            html1 = table+th+html1+'</table>';

            return html1+shippingHtml;
        },
        onLoadSuccess:function(data){
            //状态按钮
            $("a[name='statusButton']").linkbutton({plain:true,iconCls:'fa fa-scissors'}).click(function(event) {
                //获取订单id
                var id = $(this).attr('data-id');
                //获取状态信息
                var status = parseInt($(this).attr('data-status'));
                //支付订单
                if (status == 10) {
                    $.messager.confirm('确认', '您确认要帮这个家伙直接支付啦？', function(r) {
                        if(r) {
                            //ajax请求后台获取数据
                            $.ajax({
                                url: 'admin/adminOrderServlet',
                                type: 'post',
                                dataType: 'json',
                                data: {
                                    mod:'getOrderItemByOrderId',
                                    order_id:id
                                },
                                success:function(data){

                                    if (data.length > 1) {

                                        //把订单明细的id拼接
                                        var itemId = new Array();
                                        //算出总价
                                        var totalPrice = 0;
                                        for (var i = 0; i < data.length; i++) {
                                            itemId[i] = data[i].id;
                                            totalPrice += data[i].totalPrice;
                                        };

                                        window.open('orderServlet?mod=htmlPayButton&itemid='+itemId.toString()+'&order_no='+data[0].orderNo+'&order_price='+totalPrice+'&order_name=购物车商品&order_id='+id+'');
                                    }else{

                                        window.open('orderServlet?mod=htmlPayButton&itemid=&order_no='+data[0].orderNo+'&order_price='+data[0].totalPrice+'&order_name='+data[0].productName+'&order_id='+id+'');

                                    }

                                }
                            });
                        }else{

                        }
                    });


                }else if(status == 20){  //已付款状态
                    //获取快递信息
                    var address = $(this).attr('data-address');
                    $.messager.confirm('请选择清楚哦', '即将发往的地址:'+address+'', function(r) {
                        if(r) {

                            $.ajax({
                                url: 'admin/adminOrderServlet?mod=updateOrderStatus',
                                type: 'post',
                                dataType: 'text',
                                data: {
                                    order_id:id,
                                    status:30
                                },
                                success:function(data){

                                    var count = parseInt(data);
                                    if (count > 0) {
                                        $('#tt').datagrid('reload');
                                        $.messager.show({
                                            title: '提醒消息！',
                                            msg: '<font color="#7FFF00">发货成功！</font>',
                                            timeout: 3000,
                                            showType: 'slide'
                                        });
                                    }else{
                                        $.messager.show({
                                            title: '提醒消息！',
                                            msg: '<font color="#7FFF00">发货失败！</font>',
                                            timeout: 3000,
                                            showType: 'slide'
                                        });
                                    }
                                }
                            })


                        }
                    });

                }else if(status == 30){   //已发货状态

                    $.messager.confirm('请选择清楚哦', '顾客是否收到商品？', function(r) {
                        if(r) {

                            $.ajax({
                                url: 'admin/adminOrderServlet?mod=updateOrderStatus',
                                type: 'post',
                                dataType: 'text',
                                data: {
                                    order_id:id,
                                    status:50
                                },
                                success:function(data){

                                    var count = parseInt(data);
                                    if (count > 0) {
                                        $('#tt').datagrid('reload');
                                        $.messager.show({
                                            title: '提醒消息！',
                                            msg: '<font color="#7FFF00">此订单已完成！</font>',
                                            timeout: 3000,
                                            showType: 'slide'
                                        });
                                    }else{
                                        $.messager.show({
                                            title: '提醒消息！',
                                            msg: '<font color="#7FFF00">设置失败！</font>',
                                            timeout: 3000,
                                            showType: 'slide'
                                        });
                                    }
                                }
                            })


                        }
                    });

                }


            });;

            //修改价格按钮
            $("a[name='up_price']").linkbutton({text:'修改价格',plain:true,iconCls:'fa fa-jpy'}).click(function(event) {
                //获取订单实付款
                var price = $(this).attr('data-price');
                nowPrice = price;
                //获取订单id
                var id = $(this).attr('data-id');
                var html = '<p>现在的订单实付款为：<font color="red">'+price+'</font>元(每次调度为100￥)</p><br/>实付款：<input id="ss" class="easyui-numberspinner" required="required" data-options="increment:100,min:20,max:999999,editable:false,value:'+price+'">';
                $.messager.confirm('修改订单价格', html, function(r) {
                    if (r) {
                        $.ajax({
                            url: 'admin/adminOrderServlet',
                            type: 'post',
                            dataType: 'text',
                            data: {
                                mod:'updateOrderPrice',
                                order_id:id,
                                price:nowPrice
                            },
                            success:function(data){
                                $('#tt').datagrid('reload');
                                $.messager.show({
                                    title: '提醒消息！',
                                    msg: '<font color="#7FFF00">'+data+'</font>',
                                    timeout: 3000,
                                    showType: 'slide'
                                });
                            }
                        })

                    };

                })

                //数字微调框
                $('#ss').numberspinner({
                    onChange:function(newValue,oldValue){
                        nowPrice = newValue;
                    }
                });

            });;
            //修改地址按钮
            $("a[name='up_shipping']").linkbutton({text:'修改地址',plain:true,iconCls:'fa fa-home'}).click(function(event) {
                //获取订单id
                var orderId = $(this).attr('data-orderid');
                //获取地址id
                var shippingId = $(this).attr('data-id');
                shpId = shippingId;
                //获取用户id
                var userId = $(this).attr('data-user');
                //获取原本地址
                var address = $(this).attr('data-address');
                //拼接下拉框html
                var selectHtml = "<p>原本地址为："+address+"</p><font color='red'>选择地址&nbsp;</font><select id='cc'"+
                "class='easyui-combogrid' name='address' style='width:100px;'></select>";

                $.messager.confirm('请选择清楚哦', selectHtml, function(r) {
                    if (r) {
                        $.ajax({
                            url: 'admin/adminOrderServlet?mod=updateAddress',
                            type: 'post',
                            dataType: 'text',
                            data: {
                                shipping_id:shpId,
                                order_id:orderId
                            },
                            success:function(data){
                                $('#tt').datagrid('reload');
                                $.messager.show({
                                    title: '提醒消息！',
                                    msg: '<font color="#7FFF00">'+data+'</font>',
                                    timeout: 3000,
                                    showType: 'slide'
                                });

                            }
                        });


                    };

                });
                $('#cc').combogrid({
                    panelWidth:505,
                    panelHeight:200,
                    value:shippingId,
                    idField:'id',
                    textField:'receiverName',
                    url:'admin/adminOrderServlet?mod=userIdGetAddress&user_id='+userId+'',
                    columns:[[
                        {field:'receiverName',title:'收货人',width:60},
                        {field:'receiverMobile',title:'手机号码',width:120},
                        {field:'receiverProvince',title:'省',width:60},
                        {field:'receiverCity',title:'市区',width:100},
                        {field:'receiverDistrict',title:'区/县',width:60},
                        {field:'receiverAddress',title:'详细地址',width:100}
                    ]],
                    onClickRow:function(index,row){
                        //点击行 获取相应行的id
                        shpId = row.id;

                    }

                });
            });
            //取消订单
            $("a[name='delete_order']").linkbutton({text:'取消订单',plain:true,iconCls:'fa fa-remove'}).click(function(event) {
                //获取订单id
                var id = $(this).attr('data-id');
                $.messager.confirm('请选择清楚哦', '想清楚了吗？要取消订单?确定顾客不会打你？', function(r) {
                    if (r) {
                        $.ajax({
                                url: 'admin/adminOrderServlet?mod=updateOrderStatus',
                                type: 'post',
                                dataType: 'text',
                                data: {
                                    order_id:id,
                                    status:0
                                },
                                success:function(data){

                                    var count = parseInt(data);
                                    if (count > 0) {
                                        $('#tt').datagrid('reload');
                                        $.messager.show({
                                            title: '提醒消息！',
                                            msg: '<font color="#7FFF00">此订单已取消！</font>',
                                            timeout: 3000,
                                            showType: 'slide'
                                        });
                                    }else{
                                        $.messager.show({
                                            title: '提醒消息！',
                                            msg: '<font color="#7FFF00">设置失败！</font>',
                                            timeout: 3000,
                                            showType: 'slide'
                                        });
                                    }
                                }
                            })

                    }
                });


            });;
            //删除订单
            $("a[name='delete']").linkbutton({text:'删除订单',plain:true,iconCls:'fa fa-remove'}).click(function(event) {
                var id = $(this).attr('data-id');
                $.messager.confirm('请选择清楚哦', '您确定要删除订单吗？', function(r) {
                    if (r) {
                        $.ajax({
                            url: 'admin/adminOrderServlet',
                            type: 'post',
                            dataType: 'text',
                            data: {
                                mod:'deleteOrder',
                                order_id:id
                            },
                            success:function(data){
                                $('#tt').datagrid('reload');
                                    $.messager.show({
                                        title: '提醒消息！',
                                        msg: '<font color="#7FFF00">'+data+'</font>',
                                        timeout: 3000,
                                        showType: 'slide'
                                    });
                            }
                        })

                    }
                });



            });
        },
        onClickRow:function(index, row){
            $(this).datagrid('expandRow',index)
        }

        });



})