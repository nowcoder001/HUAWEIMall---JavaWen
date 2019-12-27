$(function(){
	var button = "";   //判断是进行新增还是修改
	var coupon_id = 0;   //全局商品id
	var id = 0;//优惠券id
	//表单
	//是否存在
	$('#couponName').blur(function(){
		$.ajax({
			url:"couponServlet",
			type:"post",
			data:{
				method:2,
				couponName:$("#couponName").val()
			},
			dataType:"json",
			success:function(data){
				$('#abc').html(data.message);
			}
		});
	});
	//表单提交
	$('ff').form({
		url:'couponServlet?mod=insert',
		onSubmit:function(param){
		},
		success:function(data){
			alert(data);
		//刷新数据表格
		$('#objtab').datagrid('reload');
		}
	});
	$('#a').click(function(){
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
	//必输
	$('#id').numberbox({
		min:0,
		max:1000,
		required: true
	});
	$('#couponName').validatebox({
		required: true
	});
	$('#depict').validatebox({
		required: true
	});
	$('#money').validatebox({
		required: true
	});
	$('#couponGetTime').validatebox({
		required: true
	});
	$('#couponUse').validatebox({
		required: true
	});
	$('#createTime').validatebox({
		required: true
	});
	$('#updateTime').validatebox({
		required: true
	});


	$('#objtab').datagrid({
		url:'couponServlet?mod=select',
		striped:true,
		singleSelect:true,
		fit:true,
		loadMsg:'数据疯狂加载中...',
		fitColumns:true,
		remoteSort:false,//关闭远程服务端排序
		pagination:true,
		pageList:[20,40,50],
		pageNumber:1,
		pageSize:20,
			columns:[[
				{field:'id',title:'id',width:150,align:'left',
				formatter:function(value,row,index){
					coupon_id = row.id;
					return row.id;
					}
				},
				{field:'depict',title:'优惠券描述',width:150,align:'left',sortable:true},
				{field:'money',title:'金额数量',width:150,align:'left'},
				{field:'couponName',title:'优惠券名称',width:150,align:'left'},
				{field:'couponGetTime',title:'优惠券获得时间',width:150,align:'left',sortable:true,
				formatter:function(value,row,index){

						if (value == "" || value == null) {
							return '未有用户获得';
						}else{
							return value;
						}

            	}
				},
				{field:'couponUse',title:'是否可使用',width:150,halign:'center',align:'center',
				formatter:function(value,row,index){

						if (value == '1') {
                        return '<img src=admin/images/yes.png style=width:30px;height:30px; />';
	                    }else{
	                        return '<img src=admin/images/error.png style=width:30px;height:30px; />';
	                    }

            	}},
            	{field:'CDKEY',title:'CDK兑换码',width:150,align:'center',
            		formatter:function(value,row,index){

            			if (value == null || value == "") {
            				return '此优惠券不需兑换码';
            			}else if(value == 'huawei'){
            				return '已被兑换！';
            			}else{
            				return value;
            			}

            		}
            	},
				{field:'createTime',title:'创建时间',width:150,align:'left'},

			]],
			//绑定事件
			onDblClickRow: function(index,field,value){

			},
			toolbar: [{
				iconCls: 'fa fa-trash-o',
				text:'删除',
				handler: function(){
				var rows = $("#objtab").datagrid("getSelected");
				id = rows.id;
					$.ajax({
						url: 'couponServlet?mod=delete',
						type: 'post',
						dataType:'text',
						data: {
						    id:rows.id
						},
						success:function(data){
							alert(data);
							$("#objtab").datagrid('deleteRow',$('#objtab').datagrid('getRowIndex',rows));
						}
					})
				}
			},'-',{
				iconCls:'fa fa-chevron-down',
				text:'<select id="cc1"class="easyui-combobox"name="dept"style="width:200px;"><option value="50">50元</option><option value="100">100元</option><option value="200">200元</option><option value="300">300元</option><option value="500">500元</option><option value="1000">1000元</option></select>',
				handler:function(){

				}
			},'-',{
				iconCls:'fa fa-chevron-down',
				text:'<select id="cc2"class="easyui-combobox"name="dept"style="width:200px;"><option value="10">10张</option><option value="50">50张</option><option value="100">100张</option></select>'
			},'-',{
				iconCls:'fa fa-plus-circle',
				text:'生成优惠券',
				handler:function(){
					//获取生成的金额
					var money = $('#cc1').val();
					//获取生成的张数
					var count = $('#cc2').val();
					$.ajax({
						url: 'couponServlet?mod=createCoupons',
						type: 'post',
						dataType: 'text',
						data: {
							money:money,
							count:count
						},success:function(data){
							var count = parseInt(data);
							$('#objtab').datagrid('reload');
							$.messager.show({
                                    title: '提醒消息！',
                                    msg: '<font color="#7FFF00">生成了'+data+'张优惠券</font>',
                                    timeout: 3000,
                                    showType: 'slide'
                                });
						}
					})
				}
			}
		]
	});

	//赠送
	$('#submit').click(function(){
		var rows = $("#objtab").datagrid("getSelected");
		$.ajax({
			url: 'couponServlet?mod=updateCoupon',
			type: 'post',
			dataType:'text',
			data: {
				id:rows.id,
				couponId:coupon_id
			},
			success:function(data){
				$('#user').window('close');
				$.messager.show({
                                    title: '提醒消息！',
                                    msg: '<font color="#7FFF00">'+data+'</font>',
                                    timeout: 3000,
                                    showType: 'slide'
                                });
			}
		});

	});
});
