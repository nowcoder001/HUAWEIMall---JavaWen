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
 * @author С��ħ
 *
 */
public class OrderDao {
	/**
	 * ����������ϸ��
	 * @param order
	 * @return
	 */
	public int createOrderItem(int orderId,MallOrderItem orderItem){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "insert into `huaweimall`.`mall_order_item` values(null, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1,orderItem.getUser().getId());
			ps.setInt(2, orderId);
			ps.setInt(3, orderItem.getProductId());
			ps.setString(4, orderItem.getProductName());
			ps.setString(5, orderItem.getProductImage());
			ps.setDouble(6, orderItem.getCurrent_unit_price()); //��Ʒ����
			ps.setInt(7, orderItem.getQuantity());   //��Ʒ����
			ps.setDouble(8, orderItem.getTotalPrice());  //��Ʒ�ܼ�   (��Ʒ����*��Ʒ����)
			//ִ��
			
			count = ps.executeUpdate();
			
			//��ȡ�����
			
			//�ر���Դ
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
	 * ����������
	 * @param order
	 * @return
	 */
	public int createOrder(int userId,MallOrder order){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "insert into `huaweimall`.`mall_order` values(null, ?,?, ?, ?, 0, 0, ?, null, null, null, NOW(), NOW())";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setString(1, order.getOrderNo());
			ps.setInt(2, userId);
			ps.setDouble(3, order.getPayment());
			ps.setInt(4, order.getShipping().getId());
			ps.setInt(5, order.getStatus());
			
			//ִ��
			
			count = ps.executeUpdate();
			
			//��ȡ�����
			
			//�ر���Դ
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
	 * ��ѯ����Ķ���id
	 * @return 
	 */
	public int getOrderId(){
		int orderId = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "select id FROM mall_order ORDER BY id DESC LIMIT 0,1";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					//ִ��
					
					ResultSet set = ps.executeQuery();
					
					//��ȡ�����
					while(set.next()){
						orderId = set.getInt(1);
					}
					//�ر���Դ
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
	 * ����֧������  �޸Ķ�����
	 * @param orderId
	 * @return     
	 */
	public int updateOrderById(int shippingId,String orderId){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "update `huaweimall`.`mall_order` set `shipping_id` = ?  where `id` = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, shippingId);
			ps.setString(2, orderId);
			//ִ��
			count = ps.executeUpdate();
			
			//��ȡ�����
			//�ر���Դ
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
	 * ���ݶ���id��ȡ������Ϣ����Ʒ��Ϣ
	 * @param orderId
	 * @return
	 */
	public MallOrder getOrderAndProductById(int orderId){
		MallOrder order = new MallOrder();
		MallProduct product = new MallProduct();
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "select m1.order_no,m1.payment,m1.id as order_id,m3.* from mall_order m1 inner join mall_order_item m2 on m1.id = m2.order_id inner join mall_product m3 on m2.product_id = m3.id where m1.id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, orderId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					//��ȡ�����
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
					//�ر���Դ
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
	 * ֧���ɹ����޸����ݿ��
	 * @param order
	 * @return
	 */
	public int updatePaySuccess(MallOrder order){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "update `huaweimall`.`mall_order` set `payment_type` = ?,`status`=?,`payment_time`=NOW()  where `id` = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, order.getPaymentType());
			ps.setInt(2, order.getStatus());
			ps.setInt(3, order.getId());
			//ִ��
			count = ps.executeUpdate();
			
			//��ȡ�����
			//�ر���Դ
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
	 * �����û�id ��ȡ������Ϣ
	 * @param userId
	 * @return
	 */
	public List<MallOrder> getOrderByUserId(int userId,int noRedo){
		List<MallOrder> orders = new ArrayList<MallOrder>();
		MallOrder order = null;
		MallOrderItem orderItem = null;
		String sqlStr = "";
		//���ǲ���ȫ��
		if (noRedo == 0) {
			 sqlStr = "select m1.*,m3.* from mall_order m1 inner join mall_user m2 " +
						"on m1.user_id = m2.id inner join mall_order_item m3 " +
						"on m1.id = m3.order_id inner join mall_product m4 " +
						"on m3.product_id = m4.id where m2.id = ?";
			 
			 //ȥ���ظ�����
		}else{
			sqlStr = "SELECT m1.*,m3.*,COUNT(1) FROM mall_order m1 INNER JOIN mall_user m2 " +
					"ON m1.user_id = m2.id INNER JOIN mall_order_item m3 " +
					"ON m1.id = m3.order_id INNER JOIN mall_product m4 " +
					"ON m3.product_id = m4.id WHERE m2.id = ? GROUP BY m1.id";
		}
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = sqlStr;
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, userId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					//��ȡ�����
					while(set.next()){
						//���ﳵ�Ķ�������Ҫ
						if (set.getString("order_no").equals("88888")) {
							
						}else{
							//��Ҫѡ���˵�ַ�Ķ���
							if (set.getInt("shipping_id") > 0) {
								order = new MallOrder();
								orderItem = new MallOrderItem();
								order.setId(set.getInt(1));
								order.setOrderNo(set.getString("order_no"));
								order.setPayment(set.getDouble("payment"));
								order.setPostage(set.getInt("postage"));
								order.setStatus(set.getInt("status"));
								order.setPaymentTime(set.getString("payment_time"));
								//������ϸʵ����
								orderItem.setProductId(set.getInt("product_id"));
								orderItem.setProductName(set.getString("product_name"));
								orderItem.setProductImage(set.getString("product_image"));
								orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
								orderItem.setQuantity(set.getInt("quantity"));
								
								order.setOrderItem(orderItem);
								
								orders.add(order);
								
								
							}else{  //����ɾ��û��ѡ���ַ�Ķ���
								deleteOrder(set.getInt(1));
							}
							
						}
							
						
					}
					//�ر���Դ
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
	 * ��ѯ���ﳵ�Ƿ����
	 * @param userId
	 * @return
	 */
	public String getCartCount(int userId){
		String orderId = "";
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select id from mall_order where order_no = '88888' and user_id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, userId);
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
			while(set.next()){
				orderId = set.getString(1);
			}
			//�ر���Դ
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
	 * ��ȡ�û����ﳵ����Ʒ
	 * @param userId
	 * @return
	 */
	public List<MallOrderItem> getOrderItemsByUserId(int userId){
		List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
		
		MallOrderItem orderItem = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select m2.* from mall_order m1 inner join mall_order_item m2 " +
					"on m1.id = m2.order_id inner join mall_product m3 " +
					"on m2.product_id = m3.id where m1.user_id = ? and m1.order_no = '88888'";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, userId);
			//ִ��
			
			ResultSet set = ps.executeQuery();
			//��ȡ�����
			while(set.next()){
				orderItem = new MallOrderItem();
				//������ϸʵ����
				orderItem.setId(set.getInt("id"));
				orderItem.setProductId(set.getInt("product_id"));
				orderItem.setProductName(set.getString("product_name"));
				orderItem.setProductImage(set.getString("product_image"));
				orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
				orderItem.setQuantity(set.getInt("quantity"));
				orderItem.setTotalPrice(set.getDouble("total_price"));
				orderItems.add(orderItem);
			}
			//�ر���Դ
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
	 * ������ϸ��id��ȡ��Ʒ��Ϣ
	 * @param itemId
	 * @return
	 */
	public List<MallOrderItem> getOrderItem(String[] itemId){
		List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
		MallOrderItem orderItem = null;
		String item = "";
		//����ж��ٸ��ʺ�
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
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select m2.*,m1.* from mall_order_item m1 inner join mall_product m2 " +
					"on m1.product_id = m2.id where m1.id in "+item;
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			for (int i = 0; i < itemId.length; i++) {
				ps.setString(i+1, itemId[i]);
			}
			
			//ִ��
			
			ResultSet set = ps.executeQuery();
			//��ȡ�����
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
			//�ر���Դ
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
	 * ֧���ɹ�ɾ�����ﳵ����Ʒ
	 * @param itemId
	 * @return
	 */
	public int deleteCartProductByItemId(String[] itemId){
		int count = 0;
		String item = "";
		//����ж��ٸ��ʺ�
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
				//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "DELETE FROM mall_order_item WHERE id IN "+item;
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					for (int i = 0; i < itemId.length; i++) {
						ps.setString(i+1, itemId[i]);
					}
					
					//ִ��
					count = ps.executeUpdate();
					
					//��ȡ�����
					//�ر���Դ
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
	 * ��ȡ��״̬�Ķ���
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
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = sqlStr;
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, status);
					ps.setInt(2, userId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					//��ȡ�����
					while(set.next()){
						//���ﳵ�Ķ�������Ҫ
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
							//������ϸʵ����
							orderItem.setProductId(set.getInt("product_id"));
							orderItem.setProductName(set.getString("product_name"));
							orderItem.setProductImage(set.getString("product_image"));
							orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
							orderItem.setQuantity(set.getInt("quantity"));
							
							order.setOrderItem(orderItem);
							
							orders.add(order);
						}
							
						
					}
					//�ر���Դ
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
	 * �����ظ��Ķ���id��ȡ���������Ʒ�������
	 * @param orderId
	 * @return
	 */
	public List<MallOrderItem> getChildProduct(int orderId){
		List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
		MallOrderItem orderItem = null;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "SELECT m1.*,m2.order_no FROM mall_order_item m1 INNER JOIN mall_order m2 ON m1.order_id = m2.id WHERE m1.order_id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, orderId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					//��ȡ�����
					while(set.next()){
						orderItem = new MallOrderItem();
						//������ϸʵ����
						orderItem.setProductId(set.getInt("product_id"));
						orderItem.setProductName(set.getString("product_name"));
						orderItem.setProductImage(set.getString("product_image"));
						orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
						orderItem.setQuantity(set.getInt("quantity"));
						orderItem.setTotalPrice(set.getDouble("total_price"));
						orderItem.setOrderNo(set.getString("order_no"));
						orderItems.add(orderItem);
					}
					//�ر���Դ
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
	 * ���ݶ���id��ȡ��������
	 * @param orderId
	 * @return
	 */
	public MallOrder getOrderInfoProduct(int orderId){
		MallOrder order = new MallOrder();
		List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
		MallOrderItem orderItem = null;
		MallShipping shipping = new MallShipping();
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "SELECT m3.receiver_address,m3.id AS shippingId,m3.receiver_city,m3.receiver_district,m3.receiver_province," +
							"m1.send_time,m1.end_time,m1.payment_time,m1.create_time,m1.payment,m1.status,m1.order_no,m1.id AS orderId," +
							"m2.* FROM mall_order m1 INNER JOIN mall_order_item m2 " +
							"ON m1.id = m2.order_id INNER JOIN mall_shipping m3 ON m3.id = m1.shipping_id WHERE m1.id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, orderId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					//��ȡ�����
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
							//������ϸʵ����
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
					//�ر���Դ
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
	 * ����id���ɾ��(������������ϸ��)
	 * @param orderId
	 * @return
	 */
	public int deleteOrder(int orderId){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "DELETE m1,m2 FROM mall_order_item m1 INNER JOIN mall_order m2 ON m1.order_id = m2.id WHERE m1.order_id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, orderId);
			//ִ��
			
			count = ps.executeUpdate();
			
			//��ȡ�����
			
			//�ر���Դ
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
	 * ɾ�����ﳵ����Ʒ
	 * @param itemId
	 * @return
	 */
	public int deleteItemOrder(int itemId){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "DELETE FROM mall_order_item WHERE id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, itemId);
			//ִ��
			
			count = ps.executeUpdate();
			
			//��ȡ�����
			
			//�ر���Դ
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
	 * ���ݶ����Ų�ѯ����id
	 * @param orderNo
	 * @return
	 */
	public int getOrderIdByOrderNo(String orderNo){
		int orderId = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "select id from mall_order where order_no = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setString(1, orderNo);
					//ִ��
					ResultSet set = ps.executeQuery();
					//��ȡ�����
					
					//�ر���Դ
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
