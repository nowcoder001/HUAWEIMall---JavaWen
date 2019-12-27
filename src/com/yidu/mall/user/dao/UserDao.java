package com.yidu.mall.user.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.mall.user.model.MallUser;
import com.yidu.mall.util.DBUtil;

public class UserDao {
		
	/**
	 * 用户注册
	 * @return
	 */
	public int UserRegister(MallUser user){
		int count = 0;
		
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "INSERT INTO mall_user VALUES(NULL,?,?,?,?,?,?,?,NOW(),0)";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//执行sql语句
					ps.setString(1, user.getUser_name());
					ps.setString(2, user.getPassword());
					ps.setString(3, user.getEmail());
					ps.setString(4, user.getPhone());
					ps.setString(5, user.getState());
					ps.setString(6, user.getUser_date());
					ps.setString(7, user.getUser_img());
					//获取结果
					count = ps.executeUpdate();
					//关闭资源
					DBUtil.closeSource(con, ps, null);
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				return count;
		
	}
	
	
	/**
	 * 用户登录
	 * @param  phone
	 * @param  password
	 * @return  count
	 */
	public int Userlogin(String phone, String password){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select count(*) from mall_user where phone = ? and password = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句
			ps.setString(1, phone);
			ps.setString(2, password);
			
			ResultSet set = ps.executeQuery();
			while(set.next()){
				count = set.getInt(1);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	
	/**
	 * 查询用户信息
	 * @param  phone
	 * @param  password
	 * @return  count
	 */
	public MallUser Usermass(String phone, String password){
		
		MallUser mallUser = null;
		
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select * from mall_user where phone = ? and password = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			
			//执行sql语句
			ps.setString(1, phone);
			ps.setString(2, password);
			
			ResultSet set = ps.executeQuery();
			while(set.next()){
				mallUser = new MallUser(set.getInt("id"),
						set.getString("user_name"),
						set.getString("password"), 
						set.getString("email"), 
						set.getString("phone"), 
						set.getString("state"), 
						set.getString("user_date"),
						set.getString("user_img"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mallUser;
	}
	
	/**
	 * 查询所有用户信息
	 * @return  count
	 */
	public List<MallUser> SelectUserMassage(){
		List<MallUser> malluser = new ArrayList<MallUser>(); 
		MallUser mallUser = null;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select * from mall_user";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句
	
			
			ResultSet set = ps.executeQuery();
			while(set.next()){
				mallUser = new MallUser(set.getInt("id"),
						set.getString("user_name"),
						set.getString("password"), 
						set.getString("email"), 
						set.getString("phone"), 
						set.getString("state"), 
						set.getString("user_date"),
						set.getString("user_img"));
				malluser.add(mallUser);
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return malluser;
	}
	
	/**
	 * 分页
	 * @return
	 */
	public List<MallUser> getPaging(int rows,int page){
		List<MallUser> mallUsers = new ArrayList<MallUser>();
		MallUser mallUser = null;
		page = (page-1)*rows;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "SELECT * FROM mall_user LIMIT ?,?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,page);
			ps.setInt(2,rows);
			//执行sql语句
			ResultSet set = ps.executeQuery();
			
			//获取结果
			while(set.next()){				
				
				mallUser = new MallUser(set.getInt("id"),
						set.getString("user_name"),
						set.getString("password"), 
						set.getString("email"), 
						set.getString("phone"),
						set.getString("state"),
						set.getString("user_date"),
						set.getString("user_img")
						);
				mallUsers.add(mallUser);
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
		return mallUsers;
	}
	
	
	/**
	 * 根据ID用户
	 * @param id
	 * @return
	 */
	public int deleteUser(int id){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "DELETE FROM mall_user WHERE id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句
			ps.setInt(1, id);
			//获取结果
			count = ps.executeUpdate();
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
	 * 获取要修改的用户信息
	 * @param  phone
	 * @param  password
	 * @return  count
	 */
	public MallUser getupdateUser(int id){
		
		MallUser mallUser = null;
		
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select * from mall_user where id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			
			//执行sql语句
			ps.setInt(1,id);
			
			
			ResultSet set = ps.executeQuery();
			while(set.next()){
				mallUser = new MallUser(set.getInt("id"),
						set.getString("user_name"),
						set.getString("password"), 
						set.getString("email"), 
						set.getString("phone"), 
						set.getString("state"), 
						set.getString("user_date"),
						set.getString("user_img"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mallUser;
	}
	
	
	/**
	 * 修改广告信息
	 * @param advId
	 * @return
	 */
	public int updateUser(MallUser mallUser){
		PreparedStatement ps =null;
		int count = 0;
		Connection con = null;
		//获取连接对象
		try {
			con = DBUtil.getConnection();
			//编写sql语句
			String sql = "UPDATE mall_user SET user_name = ?,password = ?," +
					"email=?,phone=?,state=?,user_date=?,user_img=? WHERE id = ?";
			//创建预编译执行对象
			ps = con.prepareStatement(sql);
			//执行sql语句
			
			ps.setString(1, mallUser.getUser_name());
			ps.setString(2, mallUser.getPassword());
			ps.setString(3, mallUser.getEmail());
			ps.setString(4, mallUser.getPhone());
			ps.setString(5, mallUser.getState());
			ps.setString(6, mallUser.getUser_date());
			ps.setString(7, mallUser.getUser_img());
			ps.setInt(8, mallUser.getId());
			
			//获取结果
			count = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭资源
			DBUtil.closeSource(con, ps, null);
		}
		return count;
		
	}
	
	
	/**
	 * 根据手机号获取用户信息
	 * @param proName
	 * @return
	 */
	public List<MallUser> getUsermassageByphone(String phone){
		
		List<MallUser> mallUsers = new ArrayList<MallUser>();
		MallUser mallUser = null;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select * from mall_user where phone like ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setString(1,"%"+phone+"%");
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				
				

				mallUser = new MallUser(set.getInt("id"),
						set.getString("user_name"),
						set.getString("password"), 
						set.getString("email"), 
						set.getString("phone"), 
						set.getString("state"), 
						set.getString("user_date"),
						set.getString("user_img"));
			
				
				mallUsers.add(mallUser);
			
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
		return mallUsers;
		
	}
	
	/**
	 * 修改用户密码
	 * @param password  phone
	 * @return
	 */
	public int updatePassword(String password,String phone){
		PreparedStatement ps =null;
		int count = 0;
		Connection con = null;
		//获取连接对象
		try {
			con = DBUtil.getConnection();
			//编写sql语句
			String sql = "UPDATE mall_user SET password = ? WHERE phone = ?";
			//创建预编译执行对象
			ps = con.prepareStatement(sql);
			//执行sql语句
			
		
			ps.setString(1,password);
			ps.setString(2,phone);

			
			//获取结果
			count = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭资源
			DBUtil.closeSource(con, ps, null);
		}
		return count;
		
	}
	
	/**
	 * 修改页面用户信息
	 * @param password  phone
	 * @return
	 */
	public int updateUserMassage(String Massage,String column,int id){
		PreparedStatement ps =null;
		int count = 0;
		Connection con = null;
		//获取连接对象
		try {
			con = DBUtil.getConnection();
			//编写sql语句
			String sql = "UPDATE mall_user SET "+Massage+" = ? WHERE id = ?";
			//创建预编译执行对象
			ps = con.prepareStatement(sql);
			//执行sql语句			
		
			ps.setString(1,column);
			ps.setInt(2,id);
			
			//获取结果
			count = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭资源
			DBUtil.closeSource(con, ps, null);
		}
		return count;
		
	}
	
	/**
	 * 根据用户id获取积分
	 * @param userId
	 * @return
	 */
	public double getTotalPrice(int userId){
		double totalPrice = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "SELECT ji_fen FROM mall_user WHERE id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, userId);
					//执行
					
					ResultSet set = ps.executeQuery();
					
					//获取结果集
					while(set.next()){
						
						totalPrice = set.getDouble(1);
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
		
		return totalPrice;
		
	}
	/**
	 * 修改用户积分
	 * @param userId
	 * @param Integral
	 * @return
	 */
	public int updateUserIntegral(int userId,double Integral){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "UPDATE mall_user SET  ji_fen=? WHERE id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setDouble(1, Integral);
			ps.setInt(2, userId);
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
