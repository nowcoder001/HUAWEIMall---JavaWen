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
 * 优惠卷数据访问层
 * @author 晨初
 * 版本：1.0
 * 时间：2019-11-1
 */
public class CouponDao {
	/**
	 * 根据用户id查询此用户的优惠卷
	 * @param userId
	 * @return
	 */
	public List<MallCoupon> getCouponByUserId(int userId){
		//创建优惠券集合
		List<MallCoupon> coupons = new ArrayList<MallCoupon>();
		//创建优惠券对象等于空
		MallCoupon coupon = null;
		//获取连接对象
				try {
					//调用工具包对象.getconne方法
					Connection con = DBUtil.getConnection();
					//编写sql语句，根据用户id查询优惠券比表
					String sql = "select * from mall_coupon where user_id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//执行sql语句
					ps.setInt(1, userId);
					//获取结果
					ResultSet set = ps.executeQuery();
					while(set.next()){
						//new出优惠券对象
						coupon = new MallCoupon();
						//设置id（获取）
						coupon.setId(set.getInt("id"));
						//设置depict（获取）
						coupon.setDepict(set.getString("depict"));
						//设置钱（获取）
						coupon.setMoney(set.getInt("money"));
						//设置优惠券名称（获取优惠券名称）
						coupon.setCouponName(set.getString("coupon_name"));
						//设置优惠券获取时间
						coupon.setCouponGetTime(set.getString("coupon_gettime"));
						//设置优惠券用户
						coupon.setCouponUse(set.getString("coupon_use"));
						//设置create时间
						coupon.setCreateTime(set.getString("create_time"));
						//添加到优惠券集合中
						coupons.add(coupon);
						
					}
					//关闭资源工具包
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		//返回优惠券集合
		return coupons;
		
	}
	/**
	 * 优惠券 修改订单的价格
	 * @param money金额
	 * @param orderId 订单id
	 * @return 返回整数
	 */
	public int updateOrderMoney(int money,int orderId){
		int count = 0;
		//获取连接对象
		try {
			//调用工具包对象.getconne方法
			Connection con = DBUtil.getConnection();
			//编写sql语句，根据id修改订单表 设置payment = payment - ?修改payment减？
			String sql = "update mall_order set payment = payment - ?  where id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句 钱  订单id
			ps.setInt(1, money);
			ps.setInt(2, orderId);
			//整数 关闭资源p.eu
			count = ps.executeUpdate();
			//关闭资源工具包最后一个为null
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
					
			e.printStackTrace();
		} catch (SQLException e) {
					
			e.printStackTrace();
		}
		//返回整数
		return count;
	}
	/**
	 * 修改优惠券状态
	 * @return返回整数
	 */
	public int updateCouponStatus(int couponId,int status){
		//声明整数赋值0
		int count = 0;
		//获取连接对象
		try {
			//调用工具包对象.获取conne方法
			Connection con = DBUtil.getConnection();
			//编写sql语句 根据id修改优惠券表中的优惠券用户列
			String sql = "update mall_coupon set coupon_use = ? where id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sqlps语句 status  ，优惠券id
			ps.setInt(1, status);
			ps.setInt(2, couponId);
			//整数 关闭资源p.eu
			count = ps.executeUpdate();
			//关闭资源工具包最后一个为空
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
	 * @param couponId优惠券id
	 * @return 返回整数
	 */
	public int deleteCoupon(int couponId){
		//声明整数并赋值
		int count = 0;
		//获取连接对象
		try {
			//调用工具包对象.getconne方法
			Connection con = DBUtil.getConnection();
			//编写sql语句  根据id删除  优惠券表
			String sql = "delete from mall_coupon where id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句潘森.设置整数 优惠券id
			ps.setInt(1, couponId);
			//整数 关闭资源p.eu
			count = ps.executeUpdate();
			//关闭资源工具包左后一个为空
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
	 * @return返回整数
	 */
	public int getCouponByUserIdAndName(int userId,String couponName){
		int count = 0;
		//获取连接对象
				try {
					//调用工具包对象.getconne方法
					Connection con = DBUtil.getConnection();
					//编写sql语句  根据用户id和优惠券名称查询是否有此优惠券
					String sql = "select count(*) from mall_coupon where user_id = ? and coupon_name = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//执行sql语句  用户编号，优惠券名称
					ps.setInt(1, userId);
					ps.setString(2, couponName);
					//获取结果
					ResultSet set = ps.executeQuery();
					
					while(set.next()){
						//整数设置值1
						count = set.getInt(1);
					}
					//关闭资源工具包
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		//返回整数
		return count;
		
	}
	/**
	 * 注册即送 优惠券  注册大礼包
	 * @param userId   用户id
	 * @return 返回整数
	 */
	public int insertCoupon(int userId){
		//声明变量
		int count = 0;
		
		try {
			//获取连接对象
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "INSERT INTO mall_coupon VALUES (0,  ?, '华为商城注册大礼包，你来我就送', 200, '注册大礼包', NOW(), 1, NOW(), NOW());";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句
			ps.setInt(1, userId);
			//整数 关闭资源p.eu
			count = ps.executeUpdate();
			//关闭资源工具包最后一个为空
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
	 * @return 返回整数
	 */
	public int insertCoupon(int userId,MallCoupon coupon){
		//声明整数
		int count = 0;
		//获取连接对象
		try {
			//调用工具包对象.getconne方法
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "INSERT INTO mall_coupon VALUES (NULL,  ?, ?, ?, ?, NOW(), 1, NOW(), NOW(),?);";
			//创建预编译执行对象ps
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句 1用户编号
			ps.setInt(1, userId);
			//2D
			ps.setString(2, coupon.getDepict());
			//3M
			ps.setInt(3, coupon.getMoney());
			//4CN
			ps.setString(4, coupon.getCouponName());
			//5CD
			ps.setString(5, coupon.getCDKEY());
			//整数 关闭资源p.eu
			count = ps.executeUpdate();
			//关闭资源最后一个为空
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
					
			e.printStackTrace();
		} catch (SQLException e) {
					
			e.printStackTrace();
		}
		//返回整数
		return count;
		
	}
	
	
	/**
	 * 查询全部优惠卷信息
	 * @return 返回优惠券集合
	 */
	public List<MallCoupon> selectCoupon() {
		//创建优惠券集合
		List<MallCoupon> coupons = new ArrayList<MallCoupon>();
		//创建C
		Connection con = null;
		//创建预编译执行对象
		PreparedStatement ps = null;
		//创建R
		ResultSet set = null;
		try {
			//调用工具类C
			con = DBUtil.getConnection();
			//编写sql语句查询优惠券表
			String sql  ="select * from mall_coupon";
			//创建预编译执行对象
			ps = con.prepareStatement(sql);
			//s获取结果p.eq
			set = ps.executeQuery();
			
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
			DBUtil.closeSource(con, ps, set);
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
		//创建C
		Connection con = null;
		//创建P
		PreparedStatement ps = null;
		//创建R
		ResultSet set = null;
		try {
			//调用工具类
			con = DBUtil.getConnection();
			//编写sql语句查询用户表编号跟用户名字
			String sql  ="select id,user_name from mall_user";
			//创建预编译执行对象
			ps = con.prepareStatement(sql);
			//s关闭资源p.eq
			set = ps.executeQuery();
			
			while(set.next()){
				//创建用户对象
				MallUser mallUser = new MallUser();
				//设置编号
				mallUser.setId(set.getInt("id"));
				//设置用户名字
				mallUser.setUser_name(set.getString("user_name"));
				//添加到用户集合中
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
			//创建预编译执行对象ps
			PreparedStatement ps = con.prepareStatement(sql);
			//设置用户编号的值 用户编号
			ps.setInt(1,userid);
			//设置编号值 id
			ps.setInt(2,id);
			//整数 关闭资源p.eu
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
			//创建预编译执行对象ps
			statement = connection.prepareStatement(sql);
			//设置页目数值
			statement.setInt(1, page);
			//设置条目数值
			statement.setInt(2, rows);
			//set关闭资源.e
			set = statement.executeQuery();
			
			while(set.next()){
				//创建优惠券对象并赋值1编号
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
				//设置cdk（获取cdk）
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
			//关闭资源
			DBUtil.closeSource(connection, statement, set);
		}
		//返回集合
		return coupons;
	}
	/**
	 * 新增优惠卷
	 * @param MallCoupon 优惠券对象
	 * @return 返回整数
	 */
	public int insertCoupon(MallCoupon coupon){
		//声明整数
		int count = 0;
		try {
			//C调用工具类D
			Connection con = DBUtil.getConnection();
			//编写sql语句 在优惠券表中插入9个值并且第一个值为空
			String sql = "insert into mall_coupon values(0,?,?,?,?,?,?,?,?)";
			//ps 执行sql语句
			PreparedStatement ps = con.prepareStatement(sql);
			//设置编号
			ps.setInt(1, coupon.getId());
			//设置d
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
			//整数 关闭资源p.eu
			count = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回整数
		return count;
	}
	/**
	 * 根据编号删除优惠卷
	 * @param id 编号
	 * @return 返回整数
	 */
	public int deleteId(int id) {
		//创建c对象
		Connection con = null;
		//创建ps对象
		PreparedStatement ps = null;
		//声明整数
		int count = 0;
		try {
			//调用工具类
			con = DBUtil.getConnection();
			//编写sql语句  根据id删除优惠券表的一条数据
			String sql  ="delete from mall_coupon where id = ?";
			//执行sql语句
			ps = con.prepareStatement(sql);
			//设置值id
			ps.setInt(1, id);
			//整数 关闭资源p.eu
			count = ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭资源 最后一个为空
			DBUtil.closeSource(con, ps, null);
		}
		//返回整数
		return count;
	}
	
	/**
	 * 查询CDK是否正确
	 * @param CDK   兑换码
	 * @return 返回整数
	 */
	public int selectCouponCDK(int userId,String CDK){
		//声明整数
		int count = 0;
		try {
			//调用工具类
			Connection con = DBUtil.getConnection();
			//编写sql语句 根据cdkey来修改优惠券表中的用户id
			String sql = "update mall_coupon set user_id = ? where CDKEY = ?";
			//执行sql语句ps
			PreparedStatement ps = con.prepareStatement(sql);
			//ps设置值id  csk
			ps.setInt(1, userId);
			ps.setString(2, CDK);
			//整数 关闭资源p.eu
			count = ps.executeUpdate();
			//关闭工具类资源  最后一个为空
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//返回整数
		return count;
		
	}
	
	/**
	 * 修改优惠券CDK
	 * @param CDK兑换码
	 * @return返回整数
	 */
	public int updateCouponCDK(String CDK){
		//声明整数
		int count = 0;
		try {
			//调用工具类
			Connection con = DBUtil.getConnection();
			//编写sql语句 根据cdkey修改优惠券表中的CDKEY等于华为
			String sql = "update mall_coupon set CDKEY = 'huawei' where CDKEY = ?";
			//执行sql语句ps
			PreparedStatement ps = con.prepareStatement(sql);
			//设置值cdk
			ps.setString(1, CDK);
			//整数 关闭资源p.eu
			count = ps.executeUpdate();
			//工具类关闭资源 最后一个为空
			DBUtil.closeSource(con, ps, null);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//返回整数
		return count;
		
	}
}
