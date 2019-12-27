package com.yidu.mall.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.mall.comment.model.MallComment;
import com.yidu.mall.order.model.MallOrderItem;
import com.yidu.mall.user.model.MallUser;
import com.yidu.mall.util.DBUtil;

/**
 * 评价数据访问层
 * @author 小恶魔
 *
 */
public class CommentDao {
	/**
	 * 新增商品评价
	 * @param comment  评论内容
	 * @param userId   用户id
	 * @param productId   商品id
	 * @return  修改是否成功
	 */
	public int addComment(String content,int userId,int productId){
		int count = 0;
		
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "INSERT INTO `huaweimall`.`mall_comment` VALUES (NULL, ?, ?, ?, NULL, NOW(), NOW())";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//执行sql语句
			ps.setInt(1, userId);   //用户id
			ps.setInt(2, productId);   //商品id
			ps.setString(3, content);   //评论内容
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
	 * 根据商品id获取评论
	 * @param productId   商品id
	 * @return  评论集合
	 */
	public List<MallComment> getComments(int productId){
		//声明评论集合
		List<MallComment> comments = new ArrayList<MallComment>();
		//评论对象为空
		MallComment comment = null;
		//用户对象为空
		MallUser user = null;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句   三表连接  评论表  用户表  商品表   条件   商品表的id  ？
					String sql = "SELECT m1.*,m2.* FROM mall_comment m1 INNER JOIN mall_user m2 " +
							"ON m1.user_id = m2.id INNER JOIN mall_product m3 " +
							"ON m1.product_id = m3.id WHERE m3.id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置参数 商品id
					ps.setInt(1, productId);
					//执行sql语句
					ResultSet set = ps.executeQuery();
					//获取结果
					
					while(set.next()){
						//创建评论对象
						comment = new MallComment();
						//从数据库获取id   设置评论id,, 
						comment.setId(set.getInt(1));
						//从数据库获取内容   设置评论内容
						comment.setContent(set.getString("content"));
						//从数据库获取回复内容   设置评论回复内容
						comment.setReplyContent(set.getString("reply_content"));
						//从数据库获取创建事件   设置评论创建事件
						comment.setCreateTime(set.getString("create_time"));
						//创建用户对象
						user = new MallUser();
						//从数据库获取用户id   设置用户id
						user.setId(set.getInt(8));
						//从数据库获取用户名字   设置用户名字
						user.setUser_name(set.getString("user_name"));
						//从数据库获取用户头像   设置用户头像
						user.setUser_img(set.getString("user_img"));
						//把 用户对象  set  评论对象
						comment.setUser(user);
						//评论对象 加入 评论集合
						comments.add(comment);
					}
					
					//关闭资源
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		
		return comments;
		
	}
	/**
	 * 判断用户是否可评论
	 * @param userId   用户id
	 * @param productId   商品id
	 * @return  是否可评论
	 */
	public int UserComment(int userId,int productId){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句  2表内连接   订单详情表  订单表  条件  表1用户id等于谁 和  表1商品id  和 表2订单状态大于或等于30 和 表2订单状态小于60
			String sql = "SELECT COUNT(*) FROM mall_order_item m1 INNER JOIN mall_order m2 " +
					"ON m1.order_id = m2.id WHERE m1.user_id = ? AND m1.product_id = ? AND m2.status >= 30 AND m2.status < 60";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置参数  用户id
			ps.setInt(1, userId);
			//设置参数   商品id
			ps.setInt(2, productId);
			//执行sql语句
			//获取结果
			ResultSet set = ps.executeQuery();
			while(set.next()){
				
				count = set.getInt(1);  //获取下标为1 的数据库列
				
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
	 * 用户对此商品的评价状态(已评论还是未评论) 
	 * @param userId    用户id
	 * @param productId    商品id
	 * @return   大于1既说明已经评价过
	 */
	public int productCommentStatus(int userId,int productId){
		
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "SELECT COUNT(*) FROM mall_order_item m1 INNER JOIN mall_comment m2 " +
					"ON m1.product_id = m2.product_id WHERE m1.user_id = ? AND m1.product_id =  ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置参数   用户id
			ps.setInt(1, userId);
			//设置参数    商品id
			ps.setInt(2, productId);
			//执行sql语句
			ResultSet set = ps.executeQuery();
			//获取结果
			
			
			while(set.next()){
				//获取数据库 的数据第一列
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
	 * 获取全部评论信息
	 * @param page   页数
	 * @param rows   行数
	 * @return   评论集合
	 */
	public List<MallComment> getAllComment(int page,int rows){
		//分页计算
		page = (page-1)*rows;
		//声明评论集合
		List<MallComment> comments = new ArrayList<MallComment>();
		//评论对象为空
		MallComment comment = null;
		//用户对象为空
		MallUser user = null;
		//订单详情对象为空
		MallOrderItem orderItem = null;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句   三表连接   评论表 、 用户表、 订单详情表   
					String sql = "SELECT m1.id AS comId,m1.reply_content,m1.content,m1.create_time," +
							"m2.user_img,m2.user_name," +
							"m3.product_id,m3.product_image,m3.product_name ,COUNT(1) FROM mall_comment m1 " +
							"INNER JOIN mall_user m2 ON m1.user_id = m2.id INNER JOIN mall_order_item m3 " +
							"ON m1.product_id = m3.product_id GROUP BY m1.id LIMIT ?,?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//执行sql语句
					//设置参数  页数
					ps.setInt(1, page);
					//设置参数   行数
					ps.setInt(2, rows);
					//获取结果
					ResultSet set = ps.executeQuery();
					while(set.next()){
						//创建评论对象
						comment = new MallComment();
						comment.setId(set.getInt("comId"));
						comment.setContent(set.getString("content"));
						comment.setReplyContent(set.getString("reply_content"));
						comment.setCreateTime(set.getString("create_time"));
						//创建用户对象
						user = new MallUser();
						user.setUser_name(set.getString("user_name"));
						user.setUser_img(set.getString("user_img"));
						//创建订单详情对象
						orderItem = new MallOrderItem();
						orderItem.setProductId(set.getInt("product_id"));
						orderItem.setProductImage(set.getString("product_image"));
						orderItem.setProductName(set.getString("product_name"));
						comment.setOrderItem(orderItem);
						comment.setUser(user);
						comments.add(comment);
					}
					//关闭资源
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		return comments;
		
	}
	/**
	 * 根据id  获取评论内容
	 * @param commentId    评论id
	 * @return  评论对象
	 */
	public MallComment getCommentContent(int commentId){
		MallComment comment = new MallComment();
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "SELECT * FROM mall_comment WHERE id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置参数  评论id
			ps.setInt(1, commentId);
			//执行sql语句
			ResultSet set = ps.executeQuery();
			//获取结果
			
			while(set.next()){
				//评论对象赋值id
				comment.setId(set.getInt("id"));
				//评论对象赋值内容
				comment.setContent(set.getString("content"));
				//评论对象赋值  回复内容
				comment.setReplyContent(set.getString("reply_content"));
				//评论对象赋值  创建时间
				comment.setCreateTime(set.getString("create_time"));
			}
			//关闭资源
			DBUtil.closeSource(con, ps, set);
		} catch (ClassNotFoundException e) {
					
			e.printStackTrace();
		} catch (SQLException e) {
					
			e.printStackTrace();
		}
		
		return comment;
		
	}
	/**
	 * 回复评论
	 * @param commentId    评论id
	 * @param reply   回复内容
	 * @return  是否回复成功
	 */
	public int replyContent(int commentId,String reply){
		int count = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "UPDATE mall_comment SET reply_content = ? WHERE id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//执行sql语句
					ps.setString(1, reply);   //回复内容
					ps.setInt(2, commentId);   //评论id
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
	 * 根据评论id删除评论
	 * @param commentId   评论id
	 * @return  是否删除成功
	 */
	public int deleteComment(int commentId){
		int count = 0;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "DELETE FROM mall_comment WHERE id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//执行sql语句
					ps.setInt(1, commentId);
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
}
