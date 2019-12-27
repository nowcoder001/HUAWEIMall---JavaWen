$(function(){
    $('#dg').datagrid({
        url:'admin/adminOrderServlet?mod=moneylog',
        fit:true,
        fitColumns:true,
        columns:[[
            {field:'paymentTime',title:'交易时间',width:100},
            {field:'orderNo',title:'订单号',width:100},
            {field:'price',title:'用户名',width:100,
            formatter:function(value,row,index){
                return row.user.user_name;
            }},
            {field:'sss',title:'交易类型',width:100,
            formatter:function(value,row,index){
                return '支付宝支付';
            }},
            {field:'payment',title:'资金明细',width:100,
            formatter:function(value,row,index){
                return "<font color='red'>-"+value+"元</font>";
            }},
        ]]
    });
})