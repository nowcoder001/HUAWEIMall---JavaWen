package com.yidu.mall.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.mall.product.model.MallCategory;
import com.yidu.mall.product.model.MallProduct;
import com.yidu.mall.util.DBUtil;

/**
 * 分类操作数据访问层
 * @author 小恶魔
 *
 */
public class CategoryDao {
	/**
	 * 查询全部的分类
	 * @return
	 */
	public List<MallCategory> getAllCatrgory(){
		MallCategory category = null;
		List<MallCategory> categories = new ArrayList<MallCategory>();
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select * from mall_category";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				
				category = new MallCategory(set.getInt("id"), 
						set.getInt("parent_id"),
						set.getString("name"), 
						set.getInt("status"), 
						set.getInt("sort_order"));
				category.setManagerId(set.getInt("manager_id"));
				categories.add(category);
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
		
		return categories;
		
	}
	/**
	 * 添加分类
	 * @param category
	 * @return
	 */
	public int addCategory(MallCategory category){
		int count = 0;
		
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "INSERT INTO mall_category VALUES (null,?,?,?,?,NOW(),NOW(),?,null)";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, category.getParentId());
					ps.setString(2, category.getName());
					ps.setInt(3, category.getStatus());
					ps.setInt(4, category.getSortOrder());
					ps.setInt(5, category.getManagerId());
					//执行
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
	 * 删除分类信息根据id
	 * @return
	 */
	public int deleteCatById(int categoryId){
		
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "delete from mall_category where id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, categoryId);
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
	/**
	 * 根据id查询商品分类
	 * @param catId
	 * @return
	 */
	public MallCategory getCategoryById(int catId){
		MallCategory category = null;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "select * from mall_category where id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, catId);
					//执行
					
					ResultSet set = ps.executeQuery();
					
					//获取结果集
					while(set.next()){
						
						category = new MallCategory(set.getInt("id"), 
								set.getInt("parent_id"),
								set.getString("name"), 
								set.getInt("status"), 
								set.getInt("sort_order"));
						category.setManagerId(set.getInt("manager_id"));
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
		return category;
		
	}
	/**
	 * 根据id修改分类信息
	 * @param category
	 * @return
	 */
	public int updateCatById(MallCategory category){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "UPDATE mall_category SET parent_id = ?,`name`=?,`status`=?,sort_order=?,update_time=NOW(),manager_id=? WHERE id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, category.getParentId());
			ps.setString(2, category.getName());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getSortOrder());
			ps.setInt(5, category.getManagerId());
			ps.setInt(6, category.getId());
			//执行
			
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
	 * 根据父级id获取分类
	 * @param parentId
	 * @return
	 */
	public List<MallCategory> getCategory(int parentId){
		List<MallCategory> categories = new ArrayList<MallCategory>();
		MallCategory category = null;
		//获取连接对象
				try {
					Connection con = DBUtil.getConnection();
					//编写sql语句
					String sql = "SELECT * FROM mall_category WHERE parent_id = ?";
					//创建预编译执行对象
					PreparedStatement ps = con.prepareStatement(sql);
					//设置相应参数
					ps.setInt(1, parentId);
					//执行
					
					ResultSet set = ps.executeQuery();
					
					//获取结果集
					while(set.next()){
						
						category = new MallCategory(set.getInt("id"), 
								set.getInt("parent_id"),
								set.getString("name"), 
								set.getInt("status"), 
								set.getInt("sort_order"));
						category.setManagerId(set.getInt("manager_id"));
						category.setImagesUrl(set.getString("img_url"));
						categories.add(category);
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
		return categories;
		
	}
}
