package com.yidu.daima;

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
 * @author ����
 * �汾��1.0
 * ʱ�䣺2019-11-1
 */
public class CouponDao {
	/**
	 * �����û�id��ѯ���û����Żݾ�
	 * @param userId
	 * @return
	 */
	public List<MallCoupon> getCouponByUserId(int userId){
		//�����Ż�ȯ����
		List<MallCoupon> coupons = new ArrayList<MallCoupon>();
		//�����Ż�ȯ������ڿ�
		MallCoupon coupon = null;
		//��ȡ���Ӷ���
				try {
					//���ù��߰�����.getconne����
					Connection con = DBUtil.getConnection();
					//��дsql��䣬�����û�id��ѯ�Ż�ȯ�ȱ�
					String sql = "select * from mall_coupon where user_id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//ִ��sql���
					ps.setInt(1, userId);
					//��ȡ���
					ResultSet set = ps.executeQuery();
					while(set.next()){
						//new���Ż�ȯ����
						coupon = new MallCoupon();
						//����id����ȡ��
						coupon.setId(set.getInt("id"));
						//����depict����ȡ��
						coupon.setDepict(set.getString("depict"));
						//����Ǯ����ȡ��
						coupon.setMoney(set.getInt("money"));
						//�����Ż�ȯ���ƣ���ȡ�Ż�ȯ���ƣ�
						coupon.setCouponName(set.getString("coupon_name"));
						//�����Ż�ȯ��ȡʱ��
						coupon.setCouponGetTime(set.getString("coupon_gettime"));
						//�����Ż�ȯ�û�
						coupon.setCouponUse(set.getString("coupon_use"));
						//����createʱ��
						coupon.setCreateTime(set.getString("create_time"));
						//��ӵ��Ż�ȯ������
						coupons.add(coupon);
						
					}
					//�ر���Դ���߰�
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		//�����Ż�ȯ����
		return coupons;
		
	}
	/**
	 * �Ż�ȯ �޸Ķ����ļ۸�
	 * @param money���
	 * @param orderId ����id
	 * @return ��������
	 */
	public int updateOrderMoney(int money,int orderId){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			//���ù��߰�����.getconne����
			Connection con = DBUtil.getConnection();
			//��дsql��䣬����id�޸Ķ����� ����payment = payment - ?�޸�payment����
			String sql = "update mall_order set payment = payment - ?  where id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql��� Ǯ  ����id
			ps.setInt(1, money);
			ps.setInt(2, orderId);
			//���� �ر���Դp.eu
			count = ps.executeUpdate();
			//�ر���Դ���߰����һ��Ϊnull
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
					
			e.printStackTrace();
		} catch (SQLException e) {
					
			e.printStackTrace();
		}
		//��������
		return count;
	}
	/**
	 * �޸��Ż�ȯ״̬
	 * @return��������
	 */
	public int updateCouponStatus(int couponId,int status){
		//����������ֵ0
		int count = 0;
		//��ȡ���Ӷ���
		try {
			//���ù��߰�����.��ȡconne����
			Connection con = DBUtil.getConnection();
			//��дsql��� ����id�޸��Ż�ȯ���е��Ż�ȯ�û���
			String sql = "update mall_coupon set coupon_use = ? where id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sqlps��� status  ���Ż�ȯid
			ps.setInt(1, status);
			ps.setInt(2, couponId);
			//���� �ر���Դp.eu
			count = ps.executeUpdate();
			//�ر���Դ���߰����һ��Ϊ��
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
	 * @param couponId�Ż�ȯid
	 * @return ��������
	 */
	public int deleteCoupon(int couponId){
		//������������ֵ
		int count = 0;
		//��ȡ���Ӷ���
		try {
			//���ù��߰�����.getconne����
			Connection con = DBUtil.getConnection();
			//��дsql���  ����idɾ��  �Ż�ȯ��
			String sql = "delete from mall_coupon where id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql�����ɭ.�������� �Ż�ȯid
			ps.setInt(1, couponId);
			//���� �ر���Դp.eu
			count = ps.executeUpdate();
			//�ر���Դ���߰����һ��Ϊ��
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
	 * @return��������
	 */
	public int getCouponByUserIdAndName(int userId,String couponName){
		int count = 0;
		//��ȡ���Ӷ���
				try {
					//���ù��߰�����.getconne����
					Connection con = DBUtil.getConnection();
					//��дsql���  �����û�id���Ż�ȯ���Ʋ�ѯ�Ƿ��д��Ż�ȯ
					String sql = "select count(*) from mall_coupon where user_id = ? and coupon_name = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//ִ��sql���  �û���ţ��Ż�ȯ����
					ps.setInt(1, userId);
					ps.setString(2, couponName);
					//��ȡ���
					ResultSet set = ps.executeQuery();
					
					while(set.next()){
						//��������ֵ1
						count = set.getInt(1);
					}
					//�ر���Դ���߰�
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		//��������
		return count;
		
	}
	/**
	 * ע�ἴ�� �Ż�ȯ  ע������
	 * @param userId   �û�id
	 * @return ��������
	 */
	public int insertCoupon(int userId){
		//��������
		int count = 0;
		
		try {
			//��ȡ���Ӷ���
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "INSERT INTO mall_coupon VALUES (0,  ?, '��Ϊ�̳�ע�������������Ҿ���', 200, 'ע������', NOW(), 1, NOW(), NOW());";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql���
			ps.setInt(1, userId);
			//���� �ر���Դp.eu
			count = ps.executeUpdate();
			//�ر���Դ���߰����һ��Ϊ��
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
	 * @return ��������
	 */
	public int insertCoupon(int userId,MallCoupon coupon){
		//��������
		int count = 0;
		//��ȡ���Ӷ���
		try {
			//���ù��߰�����.getconne����
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "INSERT INTO mall_coupon VALUES (NULL,  ?, ?, ?, ?, NOW(), 1, NOW(), NOW(),?);";
			//����Ԥ����ִ�ж���ps
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql��� 1�û����
			ps.setInt(1, userId);
			//2D
			ps.setString(2, coupon.getDepict());
			//3M
			ps.setInt(3, coupon.getMoney());
			//4CN
			ps.setString(4, coupon.getCouponName());
			//5CD
			ps.setString(5, coupon.getCDKEY());
			//���� �ر���Դp.eu
			count = ps.executeUpdate();
			//�ر���Դ���һ��Ϊ��
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
					
			e.printStackTrace();
		} catch (SQLException e) {
					
			e.printStackTrace();
		}
		//��������
		return count;
		
	}
	
	
	/**
	 * ��ѯȫ���Żݾ���Ϣ
	 * @return �����Ż�ȯ����
	 */
	public List<MallCoupon> selectCoupon() {
		//�����Ż�ȯ����
		List<MallCoupon> coupons = new ArrayList<MallCoupon>();
		//����C
		Connection con = null;
		//����Ԥ����ִ�ж���
		PreparedStatement ps = null;
		//����R
		ResultSet set = null;
		try {
			//���ù�����C
			con = DBUtil.getConnection();
			//��дsql����ѯ�Ż�ȯ��
			String sql  ="select * from mall_coupon";
			//����Ԥ����ִ�ж���
			ps = con.prepareStatement(sql);
			//s��ȡ���p.eq
			set = ps.executeQuery();
			
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
			DBUtil.closeSource(con, ps, set);
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
		//����C
		Connection con = null;
		//����P
		PreparedStatement ps = null;
		//����R
		ResultSet set = null;
		try {
			//���ù�����
			con = DBUtil.getConnection();
			//��дsql����ѯ�û����Ÿ��û�����
			String sql  ="select id,user_name from mall_user";
			//����Ԥ����ִ�ж���
			ps = con.prepareStatement(sql);
			//s�ر���Դp.eq
			set = ps.executeQuery();
			
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
			DBUtil.closeSource(con, ps, set);
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
			//����Ԥ����ִ�ж���ps
			PreparedStatement ps = con.prepareStatement(sql);
			//�����û���ŵ�ֵ �û����
			ps.setInt(1,userid);
			//���ñ��ֵ id
			ps.setInt(2,id);
			//���� �ر���Դp.eu
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
			//����Ԥ����ִ�ж���ps
			statement = connection.prepareStatement(sql);
			//����ҳĿ��ֵ
			statement.setInt(1, page);
			//������Ŀ��ֵ
			statement.setInt(2, rows);
			//set�ر���Դ.e
			set = statement.executeQuery();
			
			while(set.next()){
				//�����Ż�ȯ���󲢸�ֵ1���
				MallCoupon coupon = new MallCoupon(set.getInt("id"),
						//2d
						set.getString("depict"), 
						//3m
						set.getInt("money"), 
						//4cn
						set.getString("coupon_name"),
						//5cg
						set.getString("coupon_getTime"),
						//6cu
						set.getString("coupon_use"),
						//7ct
						set.getString("create_time"),
						//8ut
						set.getString("update_time"));
				//����cdk����ȡcdk��
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
			//�ر���Դ
			DBUtil.closeSource(connection, statement, set);
		}
		//���ؼ���
		return coupons;
	}
	/**
	 * �����Żݾ�
	 * @param MallCoupon �Ż�ȯ����
	 * @return ��������
	 */
	public int insertCoupon(MallCoupon coupon){
		//��������
		int count = 0;
		try {
			//C���ù�����D
			Connection con = DBUtil.getConnection();
			//��дsql��� ���Ż�ȯ���в���9��ֵ���ҵ�һ��ֵΪ��
			String sql = "insert into mall_coupon values(0,?,?,?,?,?,?,?,?)";
			//ps ִ��sql���
			PreparedStatement ps = con.prepareStatement(sql);
			//���ñ��
			ps.setInt(1, coupon.getId());
			//����d
			ps.setString(2, coupon.getDepict());
			//3m
			ps.setInt(3, coupon.getMoney());
			//4cn
			ps.setString(4, coupon.getCouponName());
			//5cg
			ps.setString(5, coupon.getCouponGetTime());
			//6cu
			ps.setString(6, coupon.getCouponUse());
			//7ct
			ps.setString(7, coupon.getCreateTime());
			//8ut
			ps.setString(8, coupon.getUpdateTime());
			//���� �ر���Դp.eu
			count = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��������
		return count;
	}
	/**
	 * ���ݱ��ɾ���Żݾ�
	 * @param id ���
	 * @return ��������
	 */
	public int deleteId(int id) {
		//����c����
		Connection con = null;
		//����ps����
		PreparedStatement ps = null;
		//��������
		int count = 0;
		try {
			//���ù�����
			con = DBUtil.getConnection();
			//��дsql���  ����idɾ���Ż�ȯ���һ������
			String sql  ="delete from mall_coupon where id = ?";
			//ִ��sql���
			ps = con.prepareStatement(sql);
			//����ֵid
			ps.setInt(1, id);
			//���� �ر���Դp.eu
			count = ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر���Դ ���һ��Ϊ��
			DBUtil.closeSource(con, ps, null);
		}
		//��������
		return count;
	}
	
	/**
	 * ��ѯCDK�Ƿ���ȷ
	 * @param CDK   �һ���
	 * @return ��������
	 */
	public int selectCouponCDK(int userId,String CDK){
		//��������
		int count = 0;
		try {
			//���ù�����
			Connection con = DBUtil.getConnection();
			//��дsql��� ����cdkey���޸��Ż�ȯ���е��û�id
			String sql = "update mall_coupon set user_id = ? where CDKEY = ?";
			//ִ��sql���ps
			PreparedStatement ps = con.prepareStatement(sql);
			//ps����ֵid  csk
			ps.setInt(1, userId);
			ps.setString(2, CDK);
			//���� �ر���Դp.eu
			count = ps.executeUpdate();
			//�رչ�������Դ  ���һ��Ϊ��
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//��������
		return count;
		
	}
	
	/**
	 * �޸��Ż�ȯCDK
	 * @param CDK�һ���
	 * @return��������
	 */
	public int updateCouponCDK(String CDK){
		//��������
		int count = 0;
		try {
			//���ù�����
			Connection con = DBUtil.getConnection();
			//��дsql��� ����cdkey�޸��Ż�ȯ���е�CDKEY���ڻ�Ϊ
			String sql = "update mall_coupon set CDKEY = 'huawei' where CDKEY = ?";
			//ִ��sql���ps
			PreparedStatement ps = con.prepareStatement(sql);
			//����ֵcdk
			ps.setString(1, CDK);
			//���� �ر���Դp.eu
			count = ps.executeUpdate();
			//������ر���Դ ���һ��Ϊ��
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//��������
		return count;
		
	}
}
