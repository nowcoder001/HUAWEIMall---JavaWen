$(function(){
    var button = "";   //全局按钮  判断是新增还是修改
    var catId_onchange = 0;  //全局分类id  发生改变的id
	$('#dg').treegrid({
        url:'admin/categoryServlet?mod=getTreeCategory',
        method:'post',
        idField:'id',
        treeField:'name',
        fit:true,
        fitColumns:true,
        animate:true,
        columns:[[
            {field:'sortOrder',title:'排序',width:100},
            {field:'name',title:'分类名称',width:100},
            {field:'_parentId',title:'上级id',width:100,hidden:true},
            {field:'status',title:'状态',width:100,align:'center',
                formatter:function(value,row,index){
                    if (value == '1') {
                        return '<img src=admin/images/yes.png style=width:25px;height:25px; />';
                    }else{
                        return '<img src=admin/images/error.png style=width:25px;height:25px; />';
                    }
            }},
            {field:'id',title:'操作',width:100,align:'center',
                formatter:function(value,row,index){
                    var str = '<a name="update_cat" class="easyui-linkbutton" data-url='+row.id+' ></a><a name="delete_cat" class="easyui-linkbutton" data-url='+row.id+' ></a>'
                    return str;
                }
            }
        ]],
        toolbar:[{
            iconCls:'fa fa-plus-square-o',
            text:'新增分类',
            handler:function(){
                button = "add";
                //清楚表单数据
                $("#ff").form('reset');
                //弹出窗口
                $('#add_category_win').dialog({
                    title: '添加分类',
                    width: 390,
                    height: 390,
                    closed: false,
                    cache: false,
                    modal: true,
                    iconCls:'fa fa-plus-square'
                });
                //给分类树形下拉框添加数据
                $('#category').combotree({
                    url:'admin/categoryServlet?mod=getCategory',
                    lines:true,
                    onLoadSuccess: function () {
                        $(".tree-icon,.tree-file").removeClass("tree-icon tree-file");//去掉最后一级图标
                    },
                    onChange:function(newValue, oldValue){


                    }
                });
            }
        }],
        onLoadSuccess:function(){
            $(".tree-icon,.tree-file").removeClass("tree-file");
            //折叠所有节点
            $('#dg').treegrid('collapseAll');
            //给修改按钮加小图标
            $("a[name='update_cat']").linkbutton({
                text:'修改分类',
                iconCls:'fa fa-scissors',
                plain:true
            }).click(function(event) {   //修改按钮点击事件
                button = "update";
                //获取分类id
                var catId = $(this).attr('data-url');
                //弹出窗口
                $('#add_category_win').dialog({
                    title: '添加分类',
                    width: 390,
                    height: 390,
                    closed: false,
                    cache: false,
                    modal: true,
                    iconCls:'fa fa-plus-square'
                });
                $.ajax({
                    url: 'admin/categoryServlet?mod=getCategoryById',
                    type: 'post',
                    dataType: 'json',
                    data: {
                        catId:catId
                    },
                    success:function(data){
                        catId_onchange = data[0].id;
                        //分类名称
                        $('#cat_name').textbox('setValue',data[0].name);
                        //给分类树形下拉框添加数据
                        $('#category').combotree({
                            url:'admin/categoryServlet?mod=getCategory',
                            lines:true,
                            onLoadSuccess: function () {
                                $(".tree-icon,.tree-file").removeClass("tree-icon tree-file");//去掉最后一级图标
                            },
                            onBeforeExpand:function(node){
                                if (data[0].id == node.id) {
                                    $.messager.show({
                                        title: '提醒消息！',
                                        msg: '<font color="#7FFF00">不可展开此节点</font>',
                                        timeout: 3000,
                                        showType: 'slide'
                                    });
                                    return false;
                                }
                                return true;

                            }
                        }).combotree('setValue',{  //设置下拉框的值
                            id:data[0].id,
                            text:data[0].name
                        });

                        //开关按钮
                        if (data[0].parentId > 0) {

                        }else{
                            $('#isMax').switchbutton('setValue','on');
                        }
                        //分类状态
                        $('#cat_status').combobox('setValue',data[0].status);
                        //分类排序
                        $('#cat_sort').textbox('setValue',data[0].sortOrder);

                    }
                })

            });
            //给删除按钮加小图标
            $("a[name='delete_cat']").linkbutton({
                text:'删除分类',
                iconCls:'fa fa-trash-o',
                plain:true
            }).click(function(event) {  //删除按钮点击事件
                //获取分类id
                var catId = $(this).attr('data-url');
                //弹出确定对话
                $.messager.confirm('确认', '您确认想要删除此分类吗？', function(r) {
                    if(r) {

                        //ajax提交数据
                        $.ajax({
                            url: 'admin/categoryServlet?mod=deleteCatById',
                            type: 'post',
                            dataType: 'text',
                            data: {
                                catId: catId
                            },
                            success:function(data){
                                $.messager.show({
                                    title: '提醒消息！',
                                    msg: '<font color="#7FFF00">'+data+'</font>',
                                    timeout: 3000,
                                    showType: 'slide'
                                });
                                //刷新树形数据表格
                                $('#dg').treegrid('reload');
                            }
                        })
                    }else{

                    }
                });


            });
        },
        onDblClickRow:function(row){
            if ("open" == row.state) {
                $('#dg').treegrid('collapse',row.id);
            }else{
                $('#dg').treegrid('expand',row.id);
            }

        }
    });

    //添加分类表单提交
    $('#ff').form({
        url:'admin/categoryServlet',
        onSubmit: function(param){
            if (button == "add") {
                param.mod = 'addCategory'
                button = "";
            }else{
                param.mod = 'updateCatById',
                param.catId = catId_onchange,
                button = "";
            }

        },
        success:function(data){
            //刷新数据表格
            $('#dg').treegrid('reload');
            //关闭窗口
            $('#add_category_win').dialog('close');
            $.messager.show({
                title: '分类提醒消息！',
                msg: '<font color="#7FFF00">'+data+'</font>',
                timeout: 3000,
                showType: 'slide'
            });
        }
    });
    //提交按钮点击事件
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