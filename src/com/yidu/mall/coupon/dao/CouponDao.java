package com.yidu.mall.coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.mall.coupon.model.MallCoupon;
import com.yidu.mall.user.model.MallUser;
import com.yidu.mall.util.DBUtil;

/**
 * �Żݾ����ݷ��ʲ�
 * @author С��ħ
 *
 */
public class CouponDao {
	/**
	 * �����û�id��ѯ���û����Żݾ�
	 * @param userId
	 * @return
	 */
	public List<MallCoupon> getCouponByUserId(int userId){
		List<MallCoupon> coupons = new ArrayList<MallCoupon>();
		MallCoupon coupon = null;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "SELECT * FROM mall_coupon WHERE user_id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//ִ��sql���
					ps.setInt(1, userId);
					//��ȡ���
					ResultSet set = ps.executeQuery();
					while(set.next()){
						coupon = new MallCoupon();
						coupon.setId(set.getInt("id"));
						coupon.setDepict(set.getString("depict"));
						coupon.setMoney(set.getInt("money"));
						coupon.setCouponName(set.getString("coupon_name"));
						coupon.setCouponGetTime(set.getString("coupon_gettime"));
						coupon.setCouponUse(set.getString("coupon_use"));
						coupon.setCreateTime(set.getString("create_time"));
						coupons.add(coupon);
						
					}
					//�ر���Դ
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		
		return coupons;
		
	}
	/**
	 * �Ż�ȯ �޸Ķ����ļ۸�
	 * @param money
	 * @param orderId
	 * @return
	 */
	public int updateOrderMoney(int money,int orderId){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "update `huaweimall`.`mall_order` set `payment` = `payment` - ?  where `id` = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql���
			ps.setInt(1, money);
			ps.setInt(2, orderId);
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
	 * �޸��Ż�ȯ״̬
	 * @return
	 */
	public int updateCouponStatus(int couponId,int status){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "update mall_coupon set coupon_use = ? where id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql���
			ps.setInt(1, status);
			ps.setInt(2, couponId);
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
	 * ɾ���Ż�ȯ
	 * @param couponId
	 * @return
	 */
	public int deleteCoupon(int couponId){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "delete from `huaweimall`.`mall_coupon` where `id` = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql���
			ps.setInt(1, couponId);
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
	 * �����û�id���Ż�ȯ���Ʋ�ѯ�Ƿ��д��Ż�ȯ
	 * @param userId   �û�id
	 * @param couponName    �Ż�ȯ����
	 * @return
	 */
	public int getCouponByUserIdAndName(int userId,String couponName){
		int count = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "SELECT COUNT(*) FROM mall_coupon WHERE user_id = ? AND coupon_name = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//ִ��sql���
					ps.setInt(1, userId);
					ps.setString(2, couponName);
					//��ȡ���
					ResultSet set = ps.executeQuery();
					
					while(set.next()){
						count = set.getInt(1);
					}
					//�ر���Դ
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		
		return count;
		
	}
	/**
	 * ע�ἴ�� �Ż�ȯ  ע������
	 * @param userId   �û�id
	 * @return 
	 */
	public int insertCoupon(int userId){
		
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "INSERT INTO mall_coupon VALUES (0,  ?, '��Ϊ�̳�ע�������������Ҿ���', 200, 'ע������', NOW(), 1, NOW(), NOW(),null);";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql���
			ps.setInt(1, userId);
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
	 * �����Ż�ȯ
	 * @param userId   �û�id
	 * @return 
	 */
	public int insertCoupon(int userId,MallCoupon coupon){
		
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "INSERT INTO mall_coupon VALUES (NULL,  ?, ?, ?, ?, NULL, 1, NOW(), NOW(),?);";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql���
			ps.setInt(1, userId);
			ps.setString(2, coupon.getDepict());
			ps.setInt(3, coupon.getMoney());
			ps.setString(4, coupon.getCouponName());
			ps.setString(5, coupon.getCDKEY());
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
	 * ��ѯȫ���Żݾ���Ϣ
	 * @return �����Ż�ȯ����
	 */
	public List<MallCoupon> selectCoupon() {
		//�����Ż�ȯ����
		List<MallCoupon> coupons = new ArrayList<MallCoupon>();
		//����Connection
		Connection connection = null;
		//����PreparedStatement
		PreparedStatement statement = null;
		//����ResultSet
		ResultSet set = null;
		try {
			//���ù�����
			connection = DBUtil.getConnection();
			//SELECT *,(SELECT user_name FROM mall_user)AS 'user_name' FROM mall_coupon
			//��дsql����ѯ�Ż�ȯ��
			String sql  ="select * from mall_coupon";
			//
			statement = connection.prepareStatement(sql);
			//
			set = statement.executeQuery();
			
			while(set.next()){
				//�����Ż�ȯ���ϲ���ֵ
				MallCoupon coupon = new MallCoupon(set.getInt("id"),
						set.getString("depict"), 
						set.getInt("money"), 
						set.getString("coupon_name"),
						set.getString("coupon_getTime"),
						set.getString("coupon_use"),
						set.getString("create_time"),
						set.getString("update_time"));
				coupons.add(coupon);
				}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeSource(connection, statement, set);
		}
		return coupons;
	}
	/**
	 * ��ѯ�û���
	 * �����Ż�ȯ
	 * @return�����û�����
	 */
	public List<MallUser> selectUserName(){
		//�����û�����
		List<MallUser> mallUsers = new ArrayList<MallUser>();
		//����Connection
		Connection connection = null;
		//����PreparedStatement
		PreparedStatement statement = null;
		//����ResultSet
		ResultSet set = null;
		try {
			//���ù�����
			connection = DBUtil.getConnection();
			//��дsql����ѯ�û����Ÿ��û�����
			String sql  ="select id,user_name from mall_user";
			//
			statement = connection.prepareStatement(sql);
			//
			set = statement.executeQuery();
			
			while(set.next()){
				//�����û�����
				MallUser mallUser = new MallUser();
				//���ñ��
				mallUser.setId(set.getInt("id"));
				//�����û�����
				mallUser.setUser_name(set.getString("user_name"));
				//��ӵ��û�������
				mallUsers.add(mallUser);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeSource(connection, statement, set);
		}
		//�����û�����
		return mallUsers;
	}
	/**
	 * �����Ż�ȯ
	 * @param id �û����
	 * @param userid �û����Ʊ��
	 * @return�������ֽ����ж�
	 */
	public int updateCoupon(int id,int userid){
		//��������
		int count = 0;
		try {
			//���ù��߰���
			Connection con = DBUtil.getConnection();
			//ִ��sql��� ���Ż�ȯ���� ���ݱ���޸��û����
			String sql = "update mall_coupon set user_id=? where id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			//�����û���ŵ�ֵ
			ps.setInt(1,userid);
			//���ñ��ֵ
			ps.setInt(2,id);
			//
			count = ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * ��ҳ��ѯ
	 * @param pageҳĿ��
	 * @param rows��Ŀ��
	 * @return�����Ż�ȯ����
	 */
	public List<MallCoupon> selectCoupon(int page,int rows) {
		
		page = (page-1)*rows;
		
		//�����Ż�ȯ����
		List<MallCoupon> coupons = new ArrayList<MallCoupon>();
		//����Connection
		Connection connection = null;
		//����PreparedStatement
		PreparedStatement statement = null;
		//����ResultSet
		ResultSet set = null;
		try {
			//���ù�����
			connection = DBUtil.getConnection();
			//ִ��sql���  ��ѯ�Ż�ȯ�� ���ݴ���������������������
			String sql  ="select * from mall_coupon limit ?,?";
			//
			statement = connection.prepareStatement(sql);
			//����ҳĿ��ֵ
			statement.setInt(1, page);
			//������Ŀ��ֵ
			statement.setInt(2, rows);
			//
			set = statement.executeQuery();
			
			while(set.next()){
				//�����Ż�ȯ���󲢸�ֵ
				MallCoupon coupon = new MallCoupon(set.getInt("id"),
						set.getString("depict"), 
						set.getInt("money"), 
						set.getString("coupon_name"),
						set.getString("coupon_getTime"),
						set.getString("coupon_use"),
						set.getString("create_time"),
						set.getString("update_time"));
				
				coupon.setCDKEY(set.getString("CDKEY"));
				//��ֵ��ӵ��Ż�ȯ������
				coupons.add(coupon);
				}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeSource(connection, statement, set);
		}
		return coupons;
	}
	/**
	 * �����Żݾ�
	 * @param MallCoupon �Ż�ȯ����
	 * @return
	 */
	public int insertCoupon(MallCoupon coupon){
		int count = 0;
		try {
			Connection con = DBUtil.getConnection();
			String sql = "insert into mall_coupon values(0,?,?,?,?,?,?,?,?,null)";
			PreparedStatement ps = con.prepareStatement(sql);
						
			ps.setInt(1, coupon.getId());
			ps.setString(2, coupon.getDepict());
			ps.setInt(3, coupon.getMoney());
			ps.setString(4, coupon.getCouponName());
			ps.setString(5, coupon.getCouponGetTime());
			ps.setString(6, coupon.getCouponUse());
			ps.setString(7, coupon.getCreateTime());
			ps.setString(8, coupon.getUpdateTime());
			
			count = ps.executeUpdate();
			
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
	 * ���ݱ��ɾ���Żݾ�
	 * @param id
	 * @return
	 */
	public int deleteId(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		int count = 0;
		try {
			connection = DBUtil.getConnection();
			String sql  ="delete from mall_coupon where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			count = statement.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeSource(connection, statement, null);
		}
		
		return count;
	}
	/**
	 * ����id�޸��Ż�ȯ
	 * @param id
	 * @param coupon
	 * @return
	 */
	public int updateCoupon(int id,MallCoupon coupon){
		int count = 0;
		try {
			Connection con = DBUtil.getConnection();
			String sql = "update mall_coupon set depict=?,money=?,couponName=?,couponGetTime=?,couponUse=?,createTime=?,updateTime=? where user_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, coupon.getId());
			ps.setString(2, coupon.getDepict());
			ps.setInt(3, coupon.getMoney());
			ps.setString(4, coupon.getCouponName());
			ps.setString(5, coupon.getCouponGetTime());
			ps.setString(6, coupon.getCouponUse());
			ps.setString(7, coupon.getCreateTime());
			ps.setString(8, coupon.getUpdateTime());
			
			count = ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * ��ѯCDK�Ƿ���ȷ
	 * @param CDK   �һ���
	 * @return
	 */
	public int selectCouponCDK(int userId,String CDK){
		int count = 0;
		try {
			Connection con = DBUtil.getConnection();
			String sql = "UPDATE mall_coupon SET user_id = ?,coupon_gettime=NOW() WHERE CDKEY = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, userId);
			ps.setString(2, CDK);
			
			
			count = ps.executeUpdate();
			
			
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return count;
		
	}
	
	/**
	 * �޸��Ż�ȯCDK
	 * @param CDK
	 * @return
	 */
	public int updateCouponCDK(String CDK){
		
		int count = 0;
		try {
			Connection con = DBUtil.getConnection();
			String sql = "UPDATE mall_coupon SET CDKEY = 'huawei' WHERE CDKEY = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, CDK);
			
			
			count = ps.executeUpdate();
			
			
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return count;
		
	}
}
