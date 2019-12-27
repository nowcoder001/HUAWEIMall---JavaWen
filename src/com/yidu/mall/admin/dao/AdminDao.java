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
 * 后台管理servlet
 * @author 小恶魔
 *
 */
public class AdminDao {
	/**
	 * 后台登录
	 * @param name
	 * @param password
	 * @return
	 */
	public Admin loginAdmin(String name,String password){
		Admin admin = null;
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "SELECT * FROM mall_admin WHERE admin_name = ? AND admin_pass = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setString(1, name);
			ps.setString(2, password);
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				admin = new Admin(set.getString("admin_name"), set.getString("admin_pass"));
				
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
		return admin;
		
	}
}
