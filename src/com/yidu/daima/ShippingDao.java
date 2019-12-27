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
 * �ջ���ַ���ݷ��ʲ�
 * @author ����
 *
 */
public class ShippingDao {
	/**
	 * �����û�id��ȡ�û����ջ���ַ
	 * @param userId �û�ID
	 * @return �ջ���ַ��Ϣ
	 */
	public List<MallShipping> getShippingByUserId(int userId){
		List<MallShipping> shippings = new ArrayList<MallShipping>();
		MallShipping shipping = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select * from mall_shipping where user_id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, userId);
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
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
			//�ر���Դ
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
	 * ����ջ���ַ
	 * @param shipping �û�ID
	 * @return �Ƿ�ɹ����
	 */
	public int addAddress(MallShipping shipping,int userId){
		int count = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "insert into `huaweimall`.`mall_shipping` values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW());";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
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
	 * ��ѯ�û����ջ���ַ�Ƿ���Ĭ�ϵĵ�ַ
	 * @param userId �û�ID
	 * @return �Ƿ���Ĭ�ϵ�ַ
	 */
	public int selectDefault(int userId){
		int count = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "select count(*) from mall_shipping where user_id = ? and `default` = 'true'";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, userId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					while(set.next()){
						count = set.getInt(1);
					}
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
		
		return count;
	}
	/**
	 * ����id��ȡ�ջ���ַ��Ϣ
	 * @param shippingId �ջ���ַID
	 * @return �ջ���ַ��Ϣ
	 */
	public MallShipping getShippingById(int shippingId){
		MallShipping shipping = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select * from mall_shipping where id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, shippingId);
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
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
			//�ر���Դ
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
	 * ����idɾ���ջ���ַ
	 * @param shippingId �ջ��ַID
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public int deleteShipping(int shippingId){
		
		int count = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "delete from `huaweimall`.`mall_shipping` where `id` = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, shippingId);
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
	 * ����id��ѯ���ջ���ַ�Ƿ�ΪĬ�ϵ�ַ
	 * @param shippingId �ջ��ַID
	 * @return �Ƿ�ΪĬ�ϵ�ַ
	 */
	public int selectMyDefault(int shippingId){
		int count = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "select count(*) from mall_shipping where id = ? and `default` = 'true'";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, shippingId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					while(set.next()){
						count = set.getInt(1);
					}
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
		
		return count;
		
	}
	/**
	 * �޸��ջ���ַ
	 * @param shipping �û�ID
	 * @return �Ƿ��޸ĳɹ�
	 */
	public int updateAddress(int userId,MallShipping shipping){
		int count = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "update `huaweimall`.`mall_shipping` set `user_id` = ? , `default` = ? , `receiver_name` = ? , `receiver_phone` = ? , `receiver_mobile` = ? , `receiver_province` = ? , `receiver_city` = ? , `receiver_district` = ? , `receiver_address` = ? , `receiver_zip` = ? , `update_time` = NOW() where `id` = ? ";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
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
	
}
