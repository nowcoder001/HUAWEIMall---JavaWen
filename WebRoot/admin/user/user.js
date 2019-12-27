$(function(){





    $('#tbs').datagrid({
        url:'userServlet?method=UserMassage',
        fitColumns:true,
        fit:true,
        striped:true,
        pagination:true,
        columns:[[
			{field:'id',title:'用户编号',width:200,resizable:false,align:'center',sortable:true,halign:'center'},
			{field:'user_name',title:'用户名称',width:260,align:'center',sortable:true,halign:'center'},
			{field:'email',title:'用户邮箱',width:260,align:'center',halign:'center'},
			{field:'user_date',title:'出生日期',width:260,align:'center',halign:'center'},
			{field:'integral',title:'积分',width:200,align:'center',halign:'center'},
			{field:'phone',title:'手机号码',width:200,align:'center',halign:'center'},
			{field:'state',title:'国籍地区',width:200,align:'center',halign:'center'}

			]],
			//分页
			pagination:true,
			pageList:[2,4,6,8,10],
			pageNumber:1,
			pageSize:2,
			toolbar: [{
				text:'删除员工信息',
				iconCls: 'fa fa-trash',
				handler: function(){

					var rows = $('#tbs').datagrid('getSelected');
					$.ajax({
						url: 'userServlet',
						type: 'post',
						dataType: 'text',
						data: {
							method:'ajaxDelete',
							detId:rows.id
						},
						success:function(data){
							$('#tbs').datagrid('deleteRow',$('#tbs').datagrid('getRowIndex',rows))
						}
					})


				}
			},'-',{
				text:'修改员工信息',
				iconCls: 'fa fa-pencil',
				handler:function(){
					$('#insmage').css('display','block');
					$('#insmage').dialog({
						title: '修改员工',
						width: 600,
						height: 600,
						closed: false,
						cache: false,
						modal: true
					});


					var rows = $("#tbs").datagrid("getSelected");

					$('#uptfsb').form('submit',{
						url:'userServlet',
						onSubmit: function(param){
							param.method = 'Update';
							param.id = rows.id;
						},
						success:function(data){



							var data = eval('('+data+')');
							var a = 0;
							for (var i = 0; i < data.length; i++) {
							$('#user_name').textbox('setValue',data[i].user_name);
							$('#user_pass').textbox('setValue',data[i].password);
							$('#user_iphones').textbox('setValue',data[i].phone);
							$('#user_emails').textbox('setValue',data[i].email);
							$('#user_date').textbox('setValue',data[i].user_date);
							$('#user_sata').textbox('setValue',data[i].state);
							}

						}

					});

					},

				},'-',{
		            //iconCls: 'fa fa-scissors',
		            //搜索文本框
		            text:'商品名称：<input class="easyui-textbox" type="text" id="pro_iphone" />'

		        },{
		            //搜索按钮
		            iconCls: 'fa fa-search',
		            text:'搜索商品',
		            handler:function(){
		                //获取搜索文本框的名字
		                var phone = $('#pro_iphone').val();
		                $('#tbs').datagrid('load',{

		                	UserPhone:phone

		                });

		            }
		        },'-',{
		            //刷新按钮
		            iconCls: 'fa fa-refresh fa-spin',
		            text:'刷新商品列表',
		            handler:function(){
		                //重新加载所有数据
		                $('#tbs').datagrid('load',{
		                	method:'UserMassage'

		                })

		            }
		        }]


    });




	//修改表单提交
	$('#updatebutton').click(function(){



		var i = $("#tbs").datagrid("getSelected");


		$('#uptfsb').form('submit',{
			url:'userServlet',
			onSubmit: function(param){
				param.method = 'userUpdate';
				param.id = i.id;
				return $(this).form('validate');
			},
			success:function(data){
				$('#insmage').dialog('close',true);
				$('#tbs').datagrid('reload');
			}

		});
	});


});

