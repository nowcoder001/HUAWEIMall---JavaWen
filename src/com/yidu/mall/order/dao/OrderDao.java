package com.yidu.mall.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.mall.order.model.MallOrder;
import com.yidu.mall.order.model.MallOrderItem;
import com.yidu.mall.order.model.MallShipping;
import com.yidu.mall.product.model.MallProduct;
import com.yidu.mall.util.DBUtil;

/**
 * 
 * @author 小恶魔
 *
 */
public class OrderDao {
	/**
	 * 创建订单明细表
	 * @param order
	 * @return
	 */
	public int createOrderItem(int orderId,MallOrderItem orderItem){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "insert into `huaweimall`.`mall_order_item` values(null, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1,orderItem.getUser().getId());
			ps.setInt(2, orderId);
			ps.setInt(3, orderItem.getProductId());
			ps.setString(4, orderItem.getProductName());
			ps.setString(5, orderItem.getProductImage());
			ps.setDouble(6, orderItem.getCurrent_unit_price()); //商品单价
			ps.setInt(7, orderItem.getQuantity());   //商品数量
			ps.setDouble(8, orderItem.getTotalPrice());  //商品总价   (商品单价*商品数量)
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
	 * 创建订单表
	 * @param order
	 * @return
	 */
	public int createOrder(int userId,MallOrder order){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "insert into `huaweimall`.`mall_order` values(null, ?,?, ?, ?, 0, 0, ?, null, null, null, NOW(), NOW())";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setString(1, order.getOrderNo());
			ps.setInt(2, userId);
			ps.setDouble(3, order.getPayment());
			ps.setInt(4, order.getShipping().getId());
			ps.setInt(5, order.getStatus());
			
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
	 * 查询最近的订单id
	 * @return 
	 */
	public int getOrderId(){
		int orderId = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "select id FROM mall_order ORDER BY id DESC LIMIT 0,1";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					//执行
					
					ResultSet set = ps.executeQuery();
					
					//获取结果集
					while(set.next()){
						orderId = set.getInt(1);
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
		return orderId;
		
	}
	/**
	 * 进入支付界面  修改订单表
	 * @param orderId
	 * @return     
	 */
	public int updateOrderById(int shippingId,String orderId){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "update `huaweimall`.`mall_order` set `shipping_id` = ?  where `id` = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, shippingId);
			ps.setString(2, orderId);
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
	 * 根据订单id获取订单信息和商品信息
	 * @param orderId
	 * @return
	 */
	public MallOrder getOrderAndProductById(int orderId){
		MallOrder order = new MallOrder();
		MallProduct product = new MallProduct();
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "select m1.order_no,m1.payment,m1.id as order_id,m3.* from mall_order m1 inner join mall_order_item m2 on m1.id = m2.order_id inner join mall_product m3 on m2.product_id = m3.id where m1.id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, orderId);
					//执行
					
					ResultSet set = ps.executeQuery();
					//获取结果集
					while(set.next()){
						order.setId(set.getInt("order_id"));
						order.setOrderNo(set.getString("order_no"));
						order.setPayment(set.getDouble("payment"));
						product.setId(set.getInt("id"));
						product.setName(set.getString("name"));
						product.setCoding(set.getString("coding"));
						product.setMallImages(set.getString("mail_images"));
						product.setPrice(set.getDouble("price"));
						product.setStock(set.getInt("stock"));
						
						order.setProduct(product);
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
		return order;
		
	}
	/**
	 * 支付成功后修改数据库表
	 * @param order
	 * @return
	 */
	public int updatePaySuccess(MallOrder order){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "update `huaweimall`.`mall_order` set `payment_type` = ?,`status`=?,`payment_time`=NOW()  where `id` = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, order.getPaymentType());
			ps.setInt(2, order.getStatus());
			ps.setInt(3, order.getId());
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
	 * 根据用户id 获取订单信息
	 * @param userId
	 * @return
	 */
	public List<MallOrder> getOrderByUserId(int userId,int noRedo){
		List<MallOrder> orders = new ArrayList<MallOrder>();
		MallOrder order = null;
		MallOrderItem orderItem = null;
		String sqlStr = "";
		//这是查找全部
		if (noRedo == 0) {
			 sqlStr = "select m1.*,m3.* from mall_order m1 inner join mall_user m2 " +
						"on m1.user_id = m2.id inner join mall_order_item m3 " +
						"on m1.id = m3.order_id inner join mall_product m4 " +
						"on m3.product_id = m4.id where m2.id = ?";
			 
			 //去除重复数据
		}else{
			sqlStr = "SELECT m1.*,m3.*,COUNT(1) FROM mall_order m1 INNER JOIN mall_user m2 " +
					"ON m1.user_id = m2.id INNER JOIN mall_order_item m3 " +
					"ON m1.id = m3.order_id INNER JOIN mall_product m4 " +
					"ON m3.product_id = m4.id WHERE m2.id = ? GROUP BY m1.id";
		}
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = sqlStr;
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, userId);
					//执行
					
					ResultSet set = ps.executeQuery();
					//获取结果集
					while(set.next()){
						//购物车的订单不需要
						if (set.getString("order_no").equals("88888")) {
							
						}else{
							//需要选择了地址的订单
							if (set.getInt("shipping_id") > 0) {
								order = new MallOrder();
								orderItem = new MallOrderItem();
								order.setId(set.getInt(1));
								order.setOrderNo(set.getString("order_no"));
								order.setPayment(set.getDouble("payment"));
								order.setPostage(set.getInt("postage"));
								order.setStatus(set.getInt("status"));
								order.setPaymentTime(set.getString("payment_time"));
								//订单明细实体类
								orderItem.setProductId(set.getInt("product_id"));
								orderItem.setProductName(set.getString("product_name"));
								orderItem.setProductImage(set.getString("product_image"));
								orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
								orderItem.setQuantity(set.getInt("quantity"));
								
								order.setOrderItem(orderItem);
								
								orders.add(order);
								
								
							}else{  //否则删除没有选择地址的订单
								deleteOrder(set.getInt(1));
							}
							
						}
							
						
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
		
		return orders;
		
	}
	/**
	 * 查询购物车是否存在
	 * @param userId
	 * @return
	 */
	public String getCartCount(int userId){
		String orderId = "";
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select id from mall_order where order_no = '88888' and user_id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, userId);
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				orderId = set.getString(1);
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
		
		return orderId;
		
	}
	/**
	 * 获取用户购物车的商品
	 * @param userId
	 * @return
	 */
	public List<MallOrderItem> getOrderItemsByUserId(int userId){
		List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
		
		MallOrderItem orderItem = null;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select m2.* from mall_order m1 inner join mall_order_item m2 " +
					"on m1.id = m2.order_id inner join mall_product m3 " +
					"on m2.product_id = m3.id where m1.user_id = ? and m1.order_no = '88888'";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, userId);
			//执行
			
			ResultSet set = ps.executeQuery();
			//获取结果集
			while(set.next()){
				orderItem = new MallOrderItem();
				//订单明细实体类
				orderItem.setId(set.getInt("id"));
				orderItem.setProductId(set.getInt("product_id"));
				orderItem.setProductName(set.getString("product_name"));
				orderItem.setProductImage(set.getString("product_image"));
				orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
				orderItem.setQuantity(set.getInt("quantity"));
				orderItem.setTotalPrice(set.getDouble("total_price"));
				orderItems.add(orderItem);
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
		
		return orderItems;
		
	}
	/**
	 * 根据明细表id获取商品信息
	 * @param itemId
	 * @return
	 */
	public List<MallOrderItem> getOrderItem(String[] itemId){
		List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
		MallOrderItem orderItem = null;
		String item = "";
		//算出有多少个问号
		if (itemId.length == 1) {
			item = "(?)";
		}else{
			item = "(";
			for (int i = 0; i < itemId.length; i++) {
				if (itemId.length == (i+1)) {
					item = item+"?)";
				}else{
					item = item+"?,";
				}
				
			}
		}
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select m2.*,m1.* from mall_order_item m1 inner join mall_product m2 " +
					"on m1.product_id = m2.id where m1.id in "+item;
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			for (int i = 0; i < itemId.length; i++) {
				ps.setString(i+1, itemId[i]);
			}
			
			//执行
			
			ResultSet set = ps.executeQuery();
			//获取结果集
			while(set.next()){
				orderItem = new MallOrderItem();
				orderItem.setId(set.getInt("id"));
				orderItem.setProductId(set.getInt("product_id"));
				orderItem.setProductName(set.getString("product_name"));
				orderItem.setProductImage(set.getString("product_image"));
				orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
				orderItem.setQuantity(set.getInt("quantity"));
				orderItem.setTotalPrice(set.getDouble("total_price"));
				orderItems.add(orderItem);
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
		return orderItems;
	}
	/**
	 * 支付成功删除购物车的商品
	 * @param itemId
	 * @return
	 */
	public int deleteCartProductByItemId(String[] itemId){
		int count = 0;
		String item = "";
		//算出有多少个问号
				if (itemId.length == 1) {
					item = "(?)";
				}else{
					item = "(";
					for (int i = 0; i < itemId.length; i++) {
						if (itemId.length == (i+1)) {
							item = item+"?)";
						}else{
							item = item+"?,";
						}
						
					}
				}
				//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "DELETE FROM mall_order_item WHERE id IN "+item;
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					for (int i = 0; i < itemId.length; i++) {
						ps.setString(i+1, itemId[i]);
					}
					
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
	 * 获取各状态的订单
	 * @param status
	 * @return
	 */
	public List<MallOrder> getProductStatus(int status,int noRedo,int userId){
		List<MallOrder> orders = new ArrayList<MallOrder>();
		MallOrder order = null;
		MallOrderItem orderItem = null;
		String sqlStr = "";
		if (noRedo == 0) {
			sqlStr = "SELECT m1.*,m3.* FROM mall_order m1 INNER JOIN mall_user m2 " +
					"ON m1.user_id = m2.id INNER JOIN mall_order_item m3 " +
					"ON m1.id = m3.order_id INNER JOIN mall_product m4 " +
					"ON m3.product_id = m4.id WHERE m1.status = ? AND m1.user_id = ?";
		}else{
			sqlStr = "SELECT m1.*,m3.*,COUNT(1) FROM mall_order m1 INNER JOIN mall_user m2 " +
					"ON m1.user_id = m2.id INNER JOIN mall_order_item m3 " +
					"ON m1.id = m3.order_id INNER JOIN mall_product m4 " +
					"ON m3.product_id = m4.id WHERE m1.status = ? AND m1.user_id = ? GROUP BY m1.id";
		}
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = sqlStr;
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, status);
					ps.setInt(2, userId);
					//执行
					
					ResultSet set = ps.executeQuery();
					//获取结果集
					while(set.next()){
						//购物车的订单不需要
						if (set.getString("order_no").equals("88888")) {
							
						}else{
							order = new MallOrder();
							orderItem = new MallOrderItem();
							order.setId(set.getInt(1));
							order.setOrderNo(set.getString("order_no"));
							order.setPayment(set.getDouble("payment"));
							order.setPostage(set.getInt("postage"));
							order.setStatus(set.getInt("status"));
							order.setPaymentTime(set.getString("payment_time"));
							//订单明细实体类
							orderItem.setProductId(set.getInt("product_id"));
							orderItem.setProductName(set.getString("product_name"));
							orderItem.setProductImage(set.getString("product_image"));
							orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
							orderItem.setQuantity(set.getInt("quantity"));
							
							order.setOrderItem(orderItem);
							
							orders.add(order);
						}
							
						
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
		
		return orders;
	}
	/**
	 * 根据重复的订单id获取下面的子商品（多个）
	 * @param orderId
	 * @return
	 */
	public List<MallOrderItem> getChildProduct(int orderId){
		List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
		MallOrderItem orderItem = null;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "SELECT m1.*,m2.order_no FROM mall_order_item m1 INNER JOIN mall_order m2 ON m1.order_id = m2.id WHERE m1.order_id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, orderId);
					//执行
					
					ResultSet set = ps.executeQuery();
					//获取结果集
					while(set.next()){
						orderItem = new MallOrderItem();
						//订单明细实体类
						orderItem.setProductId(set.getInt("product_id"));
						orderItem.setProductName(set.getString("product_name"));
						orderItem.setProductImage(set.getString("product_image"));
						orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
						orderItem.setQuantity(set.getInt("quantity"));
						orderItem.setTotalPrice(set.getDouble("total_price"));
						orderItem.setOrderNo(set.getString("order_no"));
						orderItems.add(orderItem);
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
		
		return orderItems;
		
	}
	/**
	 * 根据订单id获取订单集合
	 * @param orderId
	 * @return
	 */
	public MallOrder getOrderInfoProduct(int orderId){
		MallOrder order = new MallOrder();
		List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
		MallOrderItem orderItem = null;
		MallShipping shipping = new MallShipping();
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "SELECT m3.receiver_address,m3.id AS shippingId,m3.receiver_city,m3.receiver_district,m3.receiver_province," +
							"m1.send_time,m1.end_time,m1.payment_time,m1.create_time,m1.payment,m1.status,m1.order_no,m1.id AS orderId," +
							"m2.* FROM mall_order m1 INNER JOIN mall_order_item m2 " +
							"ON m1.id = m2.order_id INNER JOIN mall_shipping m3 ON m3.id = m1.shipping_id WHERE m1.id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, orderId);
					//执行
					
					ResultSet set = ps.executeQuery();
					//获取结果集
					while(set.next()){
						shipping.setId(set.getInt("shippingId"));
						shipping.setReceiverCity(set.getString("receiver_city"));
						shipping.setReceiverDistrict(set.getString("receiver_district"));
						shipping.setReceiverProvince(set.getString("receiver_province"));
						shipping.setReceiverAddress(set.getString("receiver_address"));
							orderItem = new MallOrderItem();
							order.setStatus(set.getInt("status"));
							order.setId(set.getInt("orderId"));
							order.setOrderNo(set.getString("order_no"));
							order.setPaymentTime(set.getString("payment_time"));
							order.setPayment(set.getDouble("payment"));
							order.setSendTime(set.getString("send_time"));
							order.setEndTime(set.getString("end_time"));
							order.setCreateTime(set.getString("create_time"));
							//订单明细实体类
							orderItem.setId(set.getInt("id"));
							orderItem.setProductId(set.getInt("product_id"));
							orderItem.setProductName(set.getString("product_name"));
							orderItem.setProductImage(set.getString("product_image"));
							orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
							orderItem.setQuantity(set.getInt("quantity"));
							orderItem.setTotalPrice(set.getDouble("total_price"));
							
							orderItems.add(orderItem);
							
					}
					order.setShipping(shipping);
					order.setOrderItems(orderItems);
					//关闭资源
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return order;
		
	}
	/**
	 * 根据id多表删除(订单表、订单明细表)
	 * @param orderId
	 * @return
	 */
	public int deleteOrder(int orderId){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "DELETE m1,m2 FROM mall_order_item m1 INNER JOIN mall_order m2 ON m1.order_id = m2.id WHERE m1.order_id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, orderId);
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
	 * 删除购物车的商品
	 * @param itemId
	 * @return
	 */
	public int deleteItemOrder(int itemId){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "DELETE FROM mall_order_item WHERE id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, itemId);
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
	 * 根据订单号查询订单id
	 * @param orderNo
	 * @return
	 */
	public int getOrderIdByOrderNo(String orderNo){
		int orderId = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "select id from mall_order where order_no = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setString(1, orderNo);
					//执行
					ResultSet set = ps.executeQuery();
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
		
		return orderId;
		
	}
}
