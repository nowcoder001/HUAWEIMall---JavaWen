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
 * ������   �ջ���ַ���ʲ�
 * @author С��ħ
 *
 */
public class ShippingDao {
	/**
	 * ͨ���û�id��ȡ�ջ���ַ
	 * @param userId
	 * @return
	 */
	public List<MallShipping> getShippingById(int userId){
		List<MallShipping> shippings = new ArrayList<MallShipping>();
		MallShipping shipping = null;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "select m2.* from mall_user m1 inner join mall_shipping m2 on m1.id = m2.user_id where m1.id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, userId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					
					//��ȡ�����
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
}
