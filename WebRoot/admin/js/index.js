$(function(){
    $('li[title]').click(function(event) {
        //获取各自的data-url属性
        var url = $(this).attr('data-url');
        //获取文本值
        var text = $(this).text();
        //获取各自的图标名称
        var icon = $(this).attr('title');
        //判断tab页是否存在
        if ($('#tt').tabs('exists',text)) {
            //选中
            $('#tt').tabs('select',text);
        }else{
            // 否则添加tab页
            $('#tt').tabs('add',{
                title:text,
                content:'<iframe src='+url+' frameborder="0" width="100%" height="100%"></iframe>',
                closable:true,
                tools:[{
                    iconCls:icon
                }]
            });
        }
    });
})