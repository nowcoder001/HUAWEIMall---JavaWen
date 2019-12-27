package com.yidu.mall.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.mall.order.model.MallShipping;
import com.yidu.mall.util.DBUtil;

/**
 * 订单包   收货地址访问层
 * @author 小恶魔
 *
 */
public class ShippingDao {
	/**
	 * 通过用户id获取收货地址
	 * @param userId
	 * @return
	 */
	public List<MallShipping> getShippingById(int userId){
		List<MallShipping> shippings = new ArrayList<MallShipping>();
		MallShipping shipping = null;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "select m2.* from mall_user m1 inner join mall_shipping m2 on m1.id = m2.user_id where m1.id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, userId);
					//执行
					
					ResultSet set = ps.executeQuery();
					
					//获取结果集
					while(set.next()){
						shipping = new MallShipping(set.getInt("id"),
								set.getString("default"), 
								set.getString("receiver_name"), 
								set.getString("receiver_phone"), 
								set.getString("receiver_mobile"), 
								set.getString("receiver_province"), 
								set.getString("receiver_city"), 
								set.getString("receiver_district"), 
								set.getString("receiver_address"), 
								set.getString("receiver_zip"), 
								set.getString("create_time"), 
								set.getString("update_time"));
						
						shippings.add(shipping);
					}
					//关闭资源
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return shippings;
		
	}
}
