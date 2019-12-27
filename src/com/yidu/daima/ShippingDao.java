package com.yidu.daima;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.mall.express.model.MallShipping;
import com.yidu.mall.util.DBUtil;

/**
 * 收货地址数据访问层
 * @author 死神
 *
 */
public class ShippingDao {
	/**
	 * 根据用户id获取用户的收货地址
	 * @param userId 用户ID
	 * @return 收货地址信息
	 */
	public List<MallShipping> getShippingByUserId(int userId){
		List<MallShipping> shippings = new ArrayList<MallShipping>();
		MallShipping shipping = null;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select * from mall_shipping where user_id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, userId);
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				shipping = new MallShipping();
				shipping.setId(set.getInt("id"));
				shipping.setReceiverDefault(set.getString("default"));
				shipping.setReceiverName(set.getString("receiver_name"));
				shipping.setReceiverPhone(set.getString("receiver_phone"));
				shipping.setReceiverMobile(set.getString("receiver_mobile"));
				shipping.setReceiverProvince(set.getString("receiver_province"));
				shipping.setReceiverCity(set.getString("receiver_city"));
				shipping.setReceiverDistrict(set.getString("receiver_district"));
				shipping.setReceiverAddress(set.getString("receiver_address"));
				shipping.setReceiverZip(set.getString("receiver_zip"));
				shipping.setCreateTime(set.getString("create_time"));
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
	/**
	 * 添加收货地址
	 * @param shipping 用户ID
	 * @return 是否成功添加
	 */
	public int addAddress(MallShipping shipping,int userId){
		int count = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "insert into `huaweimall`.`mall_shipping` values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW());";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, userId);
					ps.setString(2, shipping.getReceiverDefault());
					ps.setString(3, shipping.getReceiverName());
					ps.setString(4, shipping.getReceiverPhone());
					ps.setString(5, shipping.getReceiverMobile());
					ps.setString(6, shipping.getReceiverProvince());
					ps.setString(7, shipping.getReceiverCity());
					ps.setString(8, shipping.getReceiverDistrict());
					ps.setString(9, shipping.getReceiverAddress());
					ps.setString(10, shipping.getReceiverZip());
					//执行
					
					count = ps.executeUpdate();
					
					//获取结果集
					//关闭资源
					DBUtil.closeSource(con, ps, null);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return count;
		
	}
	/**
	 * 查询用户的收货地址是否有默认的地址
	 * @param userId 用户ID
	 * @return 是否有默认地址
	 */
	public int selectDefault(int userId){
		int count = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "select count(*) from mall_shipping where user_id = ? and `default` = 'true'";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, userId);
					//执行
					
					ResultSet set = ps.executeQuery();
					while(set.next()){
						count = set.getInt(1);
					}
					//获取结果集
					//关闭资源
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return count;
	}
	/**
	 * 根据id获取收货地址信息
	 * @param shippingId 收货地址ID
	 * @return 收货地址信息
	 */
	public MallShipping getShippingById(int shippingId){
		MallShipping shipping = null;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select * from mall_shipping where id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, shippingId);
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				shipping = new MallShipping();
				shipping.setId(set.getInt("id"));
				shipping.setReceiverDefault(set.getString("default"));
				shipping.setReceiverName(set.getString("receiver_name"));
				shipping.setReceiverPhone(set.getString("receiver_phone"));
				shipping.setReceiverMobile(set.getString("receiver_mobile"));
				shipping.setReceiverProvince(set.getString("receiver_province"));
				shipping.setReceiverCity(set.getString("receiver_city"));
				shipping.setReceiverDistrict(set.getString("receiver_district"));
				shipping.setReceiverAddress(set.getString("receiver_address"));
				shipping.setReceiverZip(set.getString("receiver_zip"));
				shipping.setCreateTime(set.getString("create_time"));
				
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
		return shipping;
		
	}
	/**
	 * 根据id删除收货地址
	 * @param shippingId 收获地址ID
	 * @return 是否删除成功
	 */
	public int deleteShipping(int shippingId){
		
		int count = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "delete from `huaweimall`.`mall_shipping` where `id` = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, shippingId);
					//执行
					
					count = ps.executeUpdate();
					
					//获取结果集
					//关闭资源
					DBUtil.closeSource(con, ps, null);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return count;
		
	}
	/**
	 * 根据id查询此收货地址是否为默认地址
	 * @param shippingId 收获地址ID
	 * @return 是否为默认地址
	 */
	public int selectMyDefault(int shippingId){
		int count = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "select count(*) from mall_shipping where id = ? and `default` = 'true'";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, shippingId);
					//执行
					
					ResultSet set = ps.executeQuery();
					while(set.next()){
						count = set.getInt(1);
					}
					//获取结果集
					//关闭资源
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return count;
		
	}
	/**
	 * 修改收货地址
	 * @param shipping 用户ID
	 * @return 是否修改成功
	 */
	public int updateAddress(int userId,MallShipping shipping){
		int count = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "update `huaweimall`.`mall_shipping` set `user_id` = ? , `default` = ? , `receiver_name` = ? , `receiver_phone` = ? , `receiver_mobile` = ? , `receiver_province` = ? , `receiver_city` = ? , `receiver_district` = ? , `receiver_address` = ? , `receiver_zip` = ? , `update_time` = NOW() where `id` = ? ";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, userId);
					ps.setString(2, shipping.getReceiverDefault());
					ps.setString(3, shipping.getReceiverName());
					ps.setString(4, shipping.getReceiverPhone());
					ps.setString(5, shipping.getReceiverMobile());
					ps.setString(6, shipping.getReceiverProvince());
					ps.setString(7, shipping.getReceiverCity());
					ps.setString(8, shipping.getReceiverDistrict());
					ps.setString(9, shipping.getReceiverAddress());
					ps.setString(10, shipping.getReceiverZip());
					ps.setInt(11, shipping.getId());
					//执行
					
					count = ps.executeUpdate();
					
					//获取结果集
					//关闭资源
					DBUtil.closeSource(con, ps, null);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return count;
		
	}
	
}
