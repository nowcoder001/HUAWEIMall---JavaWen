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
	 * �û�ע��
	 * @return
	 */
	public int UserRegister(MallUser user){
		int count = 0;
		
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "INSERT INTO mall_user VALUES(NULL,?,?,?,?,?,?,?,NOW(),0)";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//ִ��sql���
					ps.setString(1, user.getUser_name());
					ps.setString(2, user.getPassword());
					ps.setString(3, user.getEmail());
					ps.setString(4, user.getPhone());
					ps.setString(5, user.getState());
					ps.setString(6, user.getUser_date());
					ps.setString(7, user.getUser_img());
					//��ȡ���
					count = ps.executeUpdate();
					//�ر���Դ
					DBUtil.closeSource(con, ps, null);
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				return count;
		
	}
	
	
	/**
	 * �û���¼
	 * @param  phone
	 * @param  password
	 * @return  count
	 */
	public int Userlogin(String phone, String password){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select count(*) from mall_user where phone = ? and password = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql���
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
	 * ��ѯ�û���Ϣ
	 * @param  phone
	 * @param  password
	 * @return  count
	 */
	public MallUser Usermass(String phone, String password){
		
		MallUser mallUser = null;
		
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select * from mall_user where phone = ? and password = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			
			//ִ��sql���
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
	 * ��ѯ�����û���Ϣ
	 * @return  count
	 */
	public List<MallUser> SelectUserMassage(){
		List<MallUser> malluser = new ArrayList<MallUser>(); 
		MallUser mallUser = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select * from mall_user";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql���
	
			
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
	 * ��ҳ
	 * @return
	 */
	public List<MallUser> getPaging(int rows,int page){
		List<MallUser> mallUsers = new ArrayList<MallUser>();
		MallUser mallUser = null;
		page = (page-1)*rows;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT * FROM mall_user LIMIT ?,?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,page);
			ps.setInt(2,rows);
			//ִ��sql���
			ResultSet set = ps.executeQuery();
			
			//��ȡ���
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
			//�ر���Դ
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
	 * ����ID�û�
	 * @param id
	 * @return
	 */
	public int deleteUser(int id){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "DELETE FROM mall_user WHERE id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql���
			ps.setInt(1, id);
			//��ȡ���
			count = ps.executeUpdate();
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
	 * ��ȡҪ�޸ĵ��û���Ϣ
	 * @param  phone
	 * @param  password
	 * @return  count
	 */
	public MallUser getupdateUser(int id){
		
		MallUser mallUser = null;
		
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select * from mall_user where id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			
			//ִ��sql���
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
	 * �޸Ĺ����Ϣ
	 * @param advId
	 * @return
	 */
	public int updateUser(MallUser mallUser){
		PreparedStatement ps =null;
		int count = 0;
		Connection con = null;
		//��ȡ���Ӷ���
		try {
			con = DBUtil.getConnection();
			//��дsql���
			String sql = "UPDATE mall_user SET user_name = ?,password = ?," +
					"email=?,phone=?,state=?,user_date=?,user_img=? WHERE id = ?";
			//����Ԥ����ִ�ж���
			ps = con.prepareStatement(sql);
			//ִ��sql���
			
			ps.setString(1, mallUser.getUser_name());
			ps.setString(2, mallUser.getPassword());
			ps.setString(3, mallUser.getEmail());
			ps.setString(4, mallUser.getPhone());
			ps.setString(5, mallUser.getState());
			ps.setString(6, mallUser.getUser_date());
			ps.setString(7, mallUser.getUser_img());
			ps.setInt(8, mallUser.getId());
			
			//��ȡ���
			count = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر���Դ
			DBUtil.closeSource(con, ps, null);
		}
		return count;
		
	}
	
	
	/**
	 * �����ֻ��Ż�ȡ�û���Ϣ
	 * @param proName
	 * @return
	 */
	public List<MallUser> getUsermassageByphone(String phone){
		
		List<MallUser> mallUsers = new ArrayList<MallUser>();
		MallUser mallUser = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select * from mall_user where phone like ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setString(1,"%"+phone+"%");
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
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
			//�ر���Դ
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
	 * �޸��û�����
	 * @param password  phone
	 * @return
	 */
	public int updatePassword(String password,String phone){
		PreparedStatement ps =null;
		int count = 0;
		Connection con = null;
		//��ȡ���Ӷ���
		try {
			con = DBUtil.getConnection();
			//��дsql���
			String sql = "UPDATE mall_user SET password = ? WHERE phone = ?";
			//����Ԥ����ִ�ж���
			ps = con.prepareStatement(sql);
			//ִ��sql���
			
		
			ps.setString(1,password);
			ps.setString(2,phone);

			
			//��ȡ���
			count = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر���Դ
			DBUtil.closeSource(con, ps, null);
		}
		return count;
		
	}
	
	/**
	 * �޸�ҳ���û���Ϣ
	 * @param password  phone
	 * @return
	 */
	public int updateUserMassage(String Massage,String column,int id){
		PreparedStatement ps =null;
		int count = 0;
		Connection con = null;
		//��ȡ���Ӷ���
		try {
			con = DBUtil.getConnection();
			//��дsql���
			String sql = "UPDATE mall_user SET "+Massage+" = ? WHERE id = ?";
			//����Ԥ����ִ�ж���
			ps = con.prepareStatement(sql);
			//ִ��sql���			
		
			ps.setString(1,column);
			ps.setInt(2,id);
			
			//��ȡ���
			count = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر���Դ
			DBUtil.closeSource(con, ps, null);
		}
		return count;
		
	}
	
	/**
	 * �����û�id��ȡ����
	 * @param userId
	 * @return
	 */
	public double getTotalPrice(int userId){
		double totalPrice = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "SELECT ji_fen FROM mall_user WHERE id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, userId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					
					//��ȡ�����
					while(set.next()){
						
						totalPrice = set.getDouble(1);
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
		
		return totalPrice;
		
	}
	/**
	 * �޸��û�����
	 * @param userId
	 * @param Integral
	 * @return
	 */
	public int updateUserIntegral(int userId,double Integral){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "UPDATE mall_user SET  ji_fen=? WHERE id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setDouble(1, Integral);
			ps.setInt(2, userId);
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
