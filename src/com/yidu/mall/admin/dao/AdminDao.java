package com.yidu.mall.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yidu.mall.admin.model.Admin;
import com.yidu.mall.product.model.MallCategory;
import com.yidu.mall.product.model.MallProduct;
import com.yidu.mall.util.DBUtil;

/**
 * ��̨����servlet
 * @author С��ħ
 *
 */
public class AdminDao {
	/**
	 * ��̨��¼
	 * @param name
	 * @param password
	 * @return
	 */
	public Admin loginAdmin(String name,String password){
		Admin admin = null;
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT * FROM mall_admin WHERE admin_name = ? AND admin_pass = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setString(1, name);
			ps.setString(2, password);
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
			while(set.next()){
				admin = new Admin(set.getString("admin_name"), set.getString("admin_pass"));
				
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
		return admin;
		
	}
}
