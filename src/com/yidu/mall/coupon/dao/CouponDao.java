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
 * 优惠卷数据访问层
 * @author 小恶魔
 *
 */
public class CouponDao {
	/**
	 * 根据用户id查询此用户的优惠卷
	 * @param userId
	 * @return
	 */
	public List<MallCoupon> getCouponByUserId(int userId){
		List<MallCoupon> coupons = new ArrayList<MallCoupon>();
		MallCoupon coupon = null;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "SELECT * FROM mall_coupon WHERE user_id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//执行sql语句
					ps.setInt(1, userId);
					//获取结果
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
					//关闭资源
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		
		return coupons;
		
	}
	/**
	 * 优惠券 修改订单的价格
	 * @param money
	 * @param orderId
	 * @return
	 */
	public int updateOrderMoney(int money,int orderId){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "update `huaweimall`.`mall_order` set `payment` = `payment` - ?  where `id` = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句
			ps.setInt(1, money);
			ps.setInt(2, orderId);
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
	 * 修改优惠券状态
	 * @return
	 */
	public int updateCouponStatus(int couponId,int status){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "update mall_coupon set coupon_use = ? where id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句
			ps.setInt(1, status);
			ps.setInt(2, couponId);
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
	 * 删除优惠券
	 * @param couponId
	 * @return
	 */
	public int deleteCoupon(int couponId){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "delete from `huaweimall`.`mall_coupon` where `id` = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句
			ps.setInt(1, couponId);
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
	 * 根据用户id和优惠券名称查询是否有此优惠券
	 * @param userId   用户id
	 * @param couponName    优惠券名称
	 * @return
	 */
	public int getCouponByUserIdAndName(int userId,String couponName){
		int count = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "SELECT COUNT(*) FROM mall_coupon WHERE user_id = ? AND coupon_name = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//执行sql语句
					ps.setInt(1, userId);
					ps.setString(2, couponName);
					//获取结果
					ResultSet set = ps.executeQuery();
					
					while(set.next()){
						count = set.getInt(1);
					}
					//关闭资源
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		
		return count;
		
	}
	/**
	 * 注册即送 优惠券  注册大礼包
	 * @param userId   用户id
	 * @return 
	 */
	public int insertCoupon(int userId){
		
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "INSERT INTO mall_coupon VALUES (0,  ?, '华为商城注册大礼包，你来我就送', 200, '注册大礼包', NOW(), 1, NOW(), NOW(),null);";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句
			ps.setInt(1, userId);
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
	 * 新增优惠券
	 * @param userId   用户id
	 * @return 
	 */
	public int insertCoupon(int userId,MallCoupon coupon){
		
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "INSERT INTO mall_coupon VALUES (NULL,  ?, ?, ?, ?, NULL, 1, NOW(), NOW(),?);";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句
			ps.setInt(1, userId);
			ps.setString(2, coupon.getDepict());
			ps.setInt(3, coupon.getMoney());
			ps.setString(4, coupon.getCouponName());
			ps.setString(5, coupon.getCDKEY());
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
	 * 查询全部优惠卷信息
	 * @return 返回优惠券集合
	 */
	public List<MallCoupon> selectCoupon() {
		//创建优惠券集合
		List<MallCoupon> coupons = new ArrayList<MallCoupon>();
		//创建Connection
		Connection connection = null;
		//创建PreparedStatement
		PreparedStatement statement = null;
		//创建ResultSet
		ResultSet set = null;
		try {
			//调用工具类
			connection = DBUtil.getConnection();
			//SELECT *,(SELECT user_name FROM mall_user)AS 'user_name' FROM mall_coupon
			//编写sql语句查询优惠券表
			String sql  ="select * from mall_coupon";
			//
			statement = connection.prepareStatement(sql);
			//
			set = statement.executeQuery();
			
			while(set.next()){
				//创建优惠券集合并赋值
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
	 * 查询用户名
	 * 赠送优惠券
	 * @return返回用户集合
	 */
	public List<MallUser> selectUserName(){
		//创建用户集合
		List<MallUser> mallUsers = new ArrayList<MallUser>();
		//创建Connection
		Connection connection = null;
		//创建PreparedStatement
		PreparedStatement statement = null;
		//创建ResultSet
		ResultSet set = null;
		try {
			//调用工具类
			connection = DBUtil.getConnection();
			//编写sql语句查询用户表编号跟用户名字
			String sql  ="select id,user_name from mall_user";
			//
			statement = connection.prepareStatement(sql);
			//
			set = statement.executeQuery();
			
			while(set.next()){
				//创建用户对象
				MallUser mallUser = new MallUser();
				//设置编号
				mallUser.setId(set.getInt("id"));
				//设置用户名字
				mallUser.setUser_name(set.getString("user_name"));
				//添加到用户对象中
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
		//返回用户对象
		return mallUsers;
	}
	/**
	 * 赠送优惠券
	 * @param id 用户编号
	 * @param userid 用户名称编号
	 * @return返回数字进行判断
	 */
	public int updateCoupon(int id,int userid){
		//生命变量
		int count = 0;
		try {
			//调用工具包类
			Connection con = DBUtil.getConnection();
			//执行sql语句 在优惠券表中 根据编号修改用户编号
			String sql = "update mall_coupon set user_id=? where id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			//设置用户编号的值
			ps.setInt(1,userid);
			//设置编号值
			ps.setInt(2,id);
			//
			count = ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 分页查询
	 * @param page页目数
	 * @param rows条目数
	 * @return返回优惠券集合
	 */
	public List<MallCoupon> selectCoupon(int page,int rows) {
		
		page = (page-1)*rows;
		
		//创建优惠券集合
		List<MallCoupon> coupons = new ArrayList<MallCoupon>();
		//创建Connection
		Connection connection = null;
		//创建PreparedStatement
		PreparedStatement statement = null;
		//创建ResultSet
		ResultSet set = null;
		try {
			//条用工具类
			connection = DBUtil.getConnection();
			//执行sql语句  查询优惠券表 根据传来的俩个参数进行排序
			String sql  ="select * from mall_coupon limit ?,?";
			//
			statement = connection.prepareStatement(sql);
			//设置页目数值
			statement.setInt(1, page);
			//设置条目数值
			statement.setInt(2, rows);
			//
			set = statement.executeQuery();
			
			while(set.next()){
				//创建优惠券对象并赋值
				MallCoupon coupon = new MallCoupon(set.getInt("id"),
						set.getString("depict"), 
						set.getInt("money"), 
						set.getString("coupon_name"),
						set.getString("coupon_getTime"),
						set.getString("coupon_use"),
						set.getString("create_time"),
						set.getString("update_time"));
				
				coupon.setCDKEY(set.getString("CDKEY"));
				//将值添加到优惠券集合中
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
	 * 新增优惠卷
	 * @param MallCoupon 优惠券对象
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
	 * 根据编号删除优惠卷
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
	 * 根据id修改优惠券
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 查询CDK是否正确
	 * @param CDK   兑换码
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return count;
		
	}
	
	/**
	 * 修改优惠券CDK
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return count;
		
	}
}
