package com.yidu.mall.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.yidu.mall.order.model.MallOrder;
import com.yidu.mall.order.model.MallOrderItem;
import com.yidu.mall.order.model.MallShipping;
import com.yidu.mall.user.model.MallUser;
import com.yidu.mall.util.DBUtil;

/**
 * ��̨�������ݷ��ʲ�
 * @author С��ħ
 *
 */
public class AdminOrderDao {
	/**
	 * ��ȡȫ��������Ϣ(�����ظ�)
	 * @return
	 */
	public List<MallOrder> getAllOrders(){
		List<MallOrder> orders = new ArrayList<MallOrder>();
		
		MallOrder order = null;
		MallUser user = null;
		MallOrderItem orderItem = null;
		MallShipping shipping = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT m1.*,m2.*,m3.*,m4.* FROM mall_order m1 INNER JOIN mall_order_item m2 " +
					"ON m1.id = m2.order_id INNER JOIN mall_user m3 " +
					"ON m1.user_id =  m3.id INNER JOIN mall_shipping m4 " +
					"ON m1.shipping_id = m4.id";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			//ִ��
			
			ResultSet set = ps.executeQuery();
			//��ȡ�����
			while(set.next()){
				List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
				
				orderItem = new MallOrderItem();
				user = new MallUser();
				order = new MallOrder();
				shipping = new MallShipping();
				shipping.setId(set.getInt(34));
				shipping.setReceiverName(set.getString("receiver_name"));
				shipping.setReceiverMobile(set.getString("receiver_mobile"));
				shipping.setReceiverCity(set.getString("receiver_city"));
				shipping.setReceiverDistrict(set.getString("receiver_district"));
				shipping.setReceiverProvince(set.getString("receiver_province"));
				shipping.setReceiverAddress(set.getString("receiver_address"));
				order.setShipping(shipping);
				user.setId(set.getInt(24));
				user.setUser_name(set.getString("user_name"));
							
				orderItem.setId(set.getInt(14));
				orderItem.setProductName(set.getString("product_name"));
				orderItem.setProductImage(set.getString("product_image"));
				orderItem.setProductId(set.getInt(17));
				orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
				orderItem.setQuantity(set.getInt("quantity"));
				orderItem.setTotalPrice(set.getDouble("total_price"));
				orderItems.add(orderItem);
				order.setOrderItems(orderItems);
				
				order.setId(set.getInt(1));
				order.setOrderNo(set.getString("order_no"));
				order.setPayment(set.getDouble("payment"));
				order.setPaymentType(set.getInt("payment_type"));
				order.setPostage(set.getInt("postage"));
				order.setStatus(set.getInt("status"));
				order.setPaymentTime(set.getString("payment_time"));
				order.setSendTime(set.getString("send_time"));
				order.setEndTime(set.getString("end_time"));
				order.setCreateTime(set.getString("create_time"));
				order.setUser(user);
							
				order.setUser(user);
				
				
				orders.add(order);
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
	 * ��ȡȫ��������Ϣ(�������ظ�)��ҳ
	 * @return
	 */
	public List<MallOrder> getAllOrdersNoRedo(int page,int rows){
		
		page = (page-1)*rows;
		List<MallOrder> orders = new ArrayList<MallOrder>();
		
		MallOrder order = null;
		MallUser user = null;
		MallOrderItem orderItem = null;
		MallShipping shipping = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT m1.*,m2.*,m3.*,m4.*,COUNT(1) FROM mall_order m1 INNER JOIN mall_order_item m2 " +
					"ON m1.id = m2.order_id INNER JOIN mall_user m3 " +
					"ON m1.user_id =  m3.id INNER JOIN mall_shipping m4 " +
					"ON m1.user_id = m4.user_id where m1.order_no != 88888 GROUP BY m1.id LIMIT ?,?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, page);
			ps.setInt(2, rows);
			//ִ��
			
			ResultSet set = ps.executeQuery();
			//��ȡ�����
			while(set.next()){
				if (set.getString("order_no").equals("88888")) {
					
				}else{
					List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
					
					orderItem = new MallOrderItem();
					user = new MallUser();
					order = new MallOrder();
					
					order.setShipping(getShippingById(set.getInt("shipping_id")));
					
					user.setId(set.getInt(25));
					user.setUser_name(set.getString("user_name"));
								
					orderItem.setId(set.getInt(14));
					orderItem.setProductName(set.getString("product_name"));
					orderItem.setProductImage(set.getString("product_image"));
					orderItem.setProductId(set.getInt(17));
					orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
					orderItem.setQuantity(set.getInt("quantity"));
					orderItem.setTotalPrice(set.getDouble("total_price"));
					orderItems.add(orderItem);
					order.setOrderItems(orderItems);
					
					order.setId(set.getInt(1));
					order.setOrderNo(set.getString("order_no"));
					order.setPayment(set.getDouble("payment"));
					order.setPaymentType(set.getInt("payment_type"));
					order.setPostage(set.getInt("postage"));
					order.setStatus(set.getInt("status"));
					order.setPaymentTime(set.getString("payment_time"));
					order.setSendTime(set.getString("send_time"));
					order.setEndTime(set.getString("end_time"));
					order.setCreateTime(set.getString("create_time"));
					order.setUser(user);
								
					order.setUser(user);
					
					
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
	 * ��ȡȫ��������Ϣ(�������ظ�)
	 * @return
	 */
	public List<MallOrder> getAllOrdersNoRedo(){
		
		List<MallOrder> orders = new ArrayList<MallOrder>();
		
		MallOrder order = null;
		MallUser user = null;
		MallOrderItem orderItem = null;
		MallShipping shipping = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT m1.*,m2.*,m3.*,m4.*,COUNT(1) FROM mall_order m1 INNER JOIN mall_order_item m2 " +
					"ON m1.id = m2.order_id INNER JOIN mall_user m3 " +
					"ON m1.user_id =  m3.id INNER JOIN mall_shipping m4 " +
					"ON m1.shipping_id = m4.id GROUP BY m1.id";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			//ִ��
			
			ResultSet set = ps.executeQuery();
			//��ȡ�����
			while(set.next()){
				List<MallOrderItem> orderItems = new ArrayList<MallOrderItem>();
				
				orderItem = new MallOrderItem();
				user = new MallUser();
				order = new MallOrder();
				shipping = new MallShipping();
				shipping.setId(set.getInt(34));
				shipping.setReceiverName(set.getString("receiver_name"));
				shipping.setReceiverMobile(set.getString("receiver_mobile"));
				shipping.setReceiverCity(set.getString("receiver_city"));
				shipping.setReceiverDistrict(set.getString("receiver_district"));
				shipping.setReceiverProvince(set.getString("receiver_province"));
				shipping.setReceiverAddress(set.getString("receiver_address"));
				
				order.setShipping(shipping);
				user.setId(set.getInt(25));
				user.setUser_name(set.getString("user_name"));
							
				orderItem.setId(set.getInt(14));
				orderItem.setProductName(set.getString("product_name"));
				orderItem.setProductImage(set.getString("product_image"));
				orderItem.setProductId(set.getInt(17));
				orderItem.setCurrent_unit_price(set.getDouble("current_unit_price"));
				orderItem.setQuantity(set.getInt("quantity"));
				orderItem.setTotalPrice(set.getDouble("total_price"));
				orderItems.add(orderItem);
				order.setOrderItems(orderItems);
				
				order.setId(set.getInt(1));
				order.setOrderNo(set.getString("order_no"));
				order.setPayment(set.getDouble("payment"));
				order.setPaymentType(set.getInt("payment_type"));
				order.setPostage(set.getInt("postage"));
				order.setStatus(set.getInt("status"));
				order.setPaymentTime(set.getString("payment_time"));
				order.setSendTime(set.getString("send_time"));
				order.setEndTime(set.getString("end_time"));
				order.setCreateTime(set.getString("create_time"));
				order.setUser(user);
							
				order.setUser(user);
				
				
				orders.add(order);
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
	 * �޸Ķ���״̬
	 * @param orderId
	 * @return
	 */
	public int updateOrderAddress(int orderId,int status){
		
		int count = 0;
		String sqlStr = "";
		if (status == 20) {
			sqlStr = "UPDATE `huaweimall`.`mall_order` SET `status` = ?,payment_time=NOW() , `update_time` = NOW() WHERE `id` = ? ;";
		}else if (status == 30) {
			sqlStr = "UPDATE `huaweimall`.`mall_order` SET `status` = ?,send_time=NOW() , `update_time` = NOW() WHERE `id` = ?" ;
		}else if (status == 50) {
			sqlStr = "UPDATE `huaweimall`.`mall_order` SET `status` = ?,end_time=NOW() , `update_time` = NOW() WHERE `id` = ? ;" ;
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
			ps.setInt(2, orderId);
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
	 * �޸Ķ����۸�
	 * @param orderId
	 * @param price
	 * @return
	 */
	public int updateOrderPrice(int orderId,double price){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "UPDATE `huaweimall`.`mall_order` SET `payment` = ? WHERE `id` = ?;";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setDouble(1, price);
			ps.setInt(2, orderId);
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
	 * ��ϻ�ȡȫ������ʹ��
	 * @param shippingId
	 * @return
	 */
	public MallShipping getShippingById(int shippingId){
		MallShipping shipping = new MallShipping();
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "SELECT * FROM mall_shipping WHERE id=?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, shippingId);
					//ִ��
					ResultSet set = ps.executeQuery();
					
					//��ȡ�����
					while(set.next()){
						shipping.setId(set.getInt("id"));
						shipping.setReceiverName(set.getString("receiver_name"));
						shipping.setReceiverMobile(set.getString("receiver_mobile"));
						shipping.setReceiverCity(set.getString("receiver_city"));
						shipping.setReceiverDistrict(set.getString("receiver_district"));
						shipping.setReceiverProvince(set.getString("receiver_province"));
						shipping.setReceiverAddress(set.getString("receiver_address"));
						
					}
					//�ر���Դ
					DBUtil.closeSource(con, ps, null);
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
	 * �����ʽ���ϸ
	 * @return
	 */
	public List<MallOrder> getOrderMoneylog(){
		
		List<MallOrder> orders = new ArrayList<MallOrder>();
		MallOrder order = null;
		MallUser user = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT m1.*,m2.user_name FROM mall_order m1 INNER JOIN mall_user m2 ON m1.user_id = m2.id WHERE `status` >= 20 AND `status` != 100";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			
			//ִ��
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
			while(set.next()){
				order = new MallOrder();
				user = new MallUser();
				order.setPaymentTime(set.getString("payment_time"));
				order.setPayment(set.getDouble("payment"));
				order.setOrderNo(set.getString("order_no"));
				user.setUser_name(set.getString("user_name"));
				
				order.setUser(user);
				
				orders.add(order);
			}
			//�ر���Դ
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
		
	}
}
