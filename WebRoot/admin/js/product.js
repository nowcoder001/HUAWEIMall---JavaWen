var deleteis = 0;  //全局删除判断
$(function(){
    var button = "";   //判断是进行新增还是修改
    var pro_id = 0;   //全局商品id
    $('#dg').datagrid({
        url:'admin/productServlet?mod=product',  //setvlet地址
        fit:true,
        striped:true,
        pagination:true,
        columns:[[
            {field:'ck',checkbox:true,},
            {field:'subImages',title:'商品图片',width:155,halign:'center',formatter:function(value,rec){
                //将json字符串转成json对象
                var obj = eval('(' + value + ')');
                //显示出图片
                return "<img src="+obj[0].url+" style='width:60px;height:60px;'>";
            }},
            {field:'name',title:'商品名称',width:155,halign: 'center'},
            {field:'coding',title:'商品编号',width:155,halign: 'center'},
            {field:'categoryName',title:'商品分类',width:155,halign: 'center',formatter:function(value,row,index){
                return row.category.name;
            }},
            {field:'price',title:'单价',width:155,halign:'center'},
            {field:'stock',title:'库存',width:155,halign:'center'},
            {field:'sales',title:'销量',width:155,halign:'center',
                formatter:function(value,row,index){
                    return '销量待写';
                }
            },
            {field:'status',title:'上架',width:155,halign:'center',align:'center',
                formatter:function(value,row,index){
                    if (value == '1') {
                        return '<img src=admin/images/yes.png style=width:30px;height:30px; />';
                    }else{
                        return '<img src=admin/images/error.png style=width:30px;height:30px; />';
                    }
            }},
            {field:'id',title:'操作',width:140,halign: 'center',
                formatter:function(value,row,index){
                    var str = '<a name="update_pro" class="easyui-linkbutton" data-url='+row.id+' ></a><a name="delete_pro" class="easyui-linkbutton" data-url='+row.id+' ></a>'
                    return str;
                }
            },
        ]],
        toolbar: [{
            iconCls: 'fa fa-plus-square-o',
            text:'添加商品',
            handler: function(){
                button = "add";
                $('#add_product_win').dialog({
                    title: '添加商品',
                    width: 1000,
                    height: 700,
                    closed: false,
                    cache: false,
                    modal: true,
                    iconCls:'fa fa-plus-square'
                });

                //给分类树形下拉框添加数据
                $('#pro_category').combotree({
                    url:'admin/categoryServlet?mod=getCategory',
                    lines:true,
                    onLoadSuccess: function () {
                        $(".tree-icon,.tree-file").removeClass("tree-icon tree-file");//去掉最后一级图标
                    }
                });

                //商品运费输入框
                $('#pro_postage').textbox({
                    disabled:true
                });
                //清楚表单数据
                $("#ff").form('reset');
                //清空五张图片
                for (var i = 0; i < 5; i++) {
                    //赋五张初始图片
                    if (i == 0) {
                        $('#Img').attr('src',"admin/images/f0ab5713d0941e5c.jpg");
                    }else{
                        $('#Img'+(i+1)).attr('src',"admin/images/f0ab5713d0941e5c.jpg");
                    }

                };
            }
        },'-',{
            //iconCls: 'fa fa-scissors',
            //搜索文本框
            text:'商品名称：<input class="easyui-textbox" type="text" id="pro_name" />'

        },{
            //搜索按钮
            iconCls: 'fa fa-search',
            text:'搜索商品',
            handler:function(){
                //获取搜索文本框的名字
                var proName = $('#pro_name').val();

                $('#dg').datagrid('load',{
                    proName:proName
                });

            }
        },'-',{
            //刷新按钮
            iconCls: 'fa fa-refresh fa-spin',
            text:'刷新商品列表',
            handler:function(){
                //重新加载所有数据
                $('#dg').datagrid('load',{
                    mod:'product'

                })

            }
        }],
        onLoadSuccess:function(data){
            //给工具栏a标签添加小图标
            //修改按钮
            $("a[name='update_pro']").linkbutton({text:'修改',plain:true,iconCls:'fa fa-scissors'}).click(function(event) {
                button = "update";
                //弹出窗口
                $('#add_product_win').dialog({
                    title: '添加商品',
                    width: 1000,
                    height: 700,
                    closed: false,
                    cache: false,
                    modal: true,
                    iconCls:'fa fa-plus-square'
                });

                //获取商品id
                var proId = $(this).attr('data-url');
                pro_id = proId;
                $.ajax({
                    url: 'admin/productServlet?mod=getProductById',
                    type: 'post',
                    dataType: 'json',
                    data: {
                        proId:proId
                    },
                    success:function(data){
                        //获取json数据
                        $("#username").textbox('setValue',data[0].name);
                        //商品分类
                        //给分类树形下拉框添加数据
                        $('#pro_category').combotree({
                            url:'admin/categoryServlet?mod=getCategory',
                            lines:true,

                        }).combotree('setValue',data[0].category.id);

                        //商品价格
                        $("#pro_price").numberbox('setValue',data[0].price);
                        //商品库存
                        $("#pro_stock").textbox('setValue',data[0].stock);
                        //商品运费禁止输入
                        $('#pro_postage').textbox({
                            disabled:true
                        });
                        //商品状态
                        $('#pro_status').combobox('setValue',data[0].status);
                        //商品详情
                        $('#pro_detail').textbox('setValue',data[0].detailText);

                        var imgJson = eval('(' + data[0].mallImages + ')');
                        //循环给img和input文件上传标签赋值
                        for (var i = 0; i < imgJson.length; i++) {
                            //给img标记赋值
                            if (i == 0) {
                                $('#Img').attr('src',imgJson[i].url);
                            }else{
                                $('#Img'+(i+1)).attr('src',imgJson[i].url);
                            }

                            //给文件上传框赋值
                            //获取路径最后的文件名
                            var imgFileName = imgJson[i].url.replace(/^http:\/\/[^/]+/, "");
                            var addr = imgJson[i].url.substr(imgJson[i].url.lastIndexOf('/', imgJson[i].url.lastIndexOf('/') - 1) + 1);
                            var index = addr.lastIndexOf("\/");
                            //js 获取字符串中最后一个斜杠后面的内容
                            var addrLast = decodeURI(addr.substring(index + 1, addr.length));
                            $('#fileputHB'+(i+1)).filebox('setText',addrLast);

                        };
                    }
                });
            });

            //删除按钮
            $("a[name='delete_pro']").linkbutton({text:'删除',plain:true,iconCls:'fa fa-trash-o'}).click(function(event) {
                //获取商品id
                var proId = $(this).attr('data-url');
                //弹出确定对话
                $.messager.confirm('确认', '您确认想要删除此商品吗？', function(r) {
                    if(r) {

                        //ajax提交数据
                        $.ajax({
                            url: 'admin/productServlet?mod=delete_pro',
                            type: 'post',
                            dataType: 'text',
                            data: {
                                proId: proId
                            },
                            success:function(data){
                                $.messager.show({
                                    title: '提醒消息！',
                                    msg: '<font color="#7FFF00">'+data+'</font>',
                                    timeout: 3000,
                                    showType: 'slide'
                                });
                                //刷新数据表格
                                $('#dg').datagrid('reload');
                            }
                        })
                    }else{

                    }
                });


            });
        }
    });

    //点击提交按钮   提交数据
    $('#ff').form({
        url:'admin/productServlet',
        onSubmit: function(param){
            if (button == 'add') {  //如果是add既说明是新增商品
                param.mod = 'addProduct';

            }else{   //否则就是修改商品
                param.mod = 'updatePro';
                param.proId = pro_id;
                //获取五张图片的文件名  传给后台服务器
                //声明数组
                var fileName = new Array();
                for (var i = 0; i < 5; i++) {
                    fileName[i] = $('#fileputHB'+(i+1)).filebox('getText');
                };
                param.fileName1 = fileName[0];
                param.fileName2 = fileName[1];
                param.fileName3 = fileName[2];
                param.fileName4 = fileName[3];
                param.fileName5 = fileName[4];

            }
            //传图片的src属性值给后台
            param.imgBase1 = $('#Img').attr('src');
            param.imgBase2 = $('#Img2').attr('src');
            param.imgBase3 = $('#Img3').attr('src');
            param.imgBase4 = $('#Img4').attr('src');
            param.imgBase5 = $('#Img5').attr('src');
        },
        success:function(data){
            //刷新数据表格
            $('#dg').datagrid('reload');
            $('#add_product_win').dialog('close');
            $.messager.show({
                title: '提醒消息！',
                msg: '<font color="#7FFF00">'+data+'</font>',
                timeout: 3000,
                showType: 'slide'
            });
        }
    });
    //点击新增按钮提交ajax数据
    $('#submit').click(function(event) {
        if ($("#ff").form('validate')) {
            //新增商品，添加
            $('#ff').submit();
        }else{
            $.messager.show({
                height: 42,
                timeout: 2000,
                showSpeed: 300,
                msg: '<i class="fa fa-times"></i>&nbsp; <font color="red">您还有信息未填完整或正确哟！(会自动关闭)</font>',
                style: {
                    right: '',
                    top: document.body.scrollTop + document.documentElement.scrollTop + 20,
                    bottom: '',
                    'z-index': 999,
                    'box-shadow': '0 1px 6px rgba(0,0,0,.2)'
                }
            });
        }
    });


})

//删除商品方法
function deleteProduct(proId){
    $.ajax({
        url: 'admin/productServlet?mod=delete_pro&proId='+proId+'',
        type: 'post',
        dataType: 'text',
        data: {
            proId: proId
        },
        success:function(data){

        }
    })

}
