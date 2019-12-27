package com.yidu.mall.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.mall.product.model.MallAttrValue;
import com.yidu.mall.product.model.MallCategory;
import com.yidu.mall.product.model.MallProduct;
import com.yidu.mall.util.DBUtil;

/**
 * 商品数据访问层
 * @author 小恶魔
 *
 */
public class ProductDao {
	
	/**
	 * 获取全部商品
	 * @return
	 */
	public List<MallProduct> getAllProduct(){
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		//分类对象
		MallCategory category = null;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select m1.*,m2.name as cat_name from mall_product m1 inner join mall_category m2 on m1.category_id = m2.id";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				
				category = new MallCategory();
				category.setName(set.getString("cat_name"));
				
				product = new MallProduct(set.getInt("id"),
						set.getString("name"), 
						set.getString("coding"), 
						set.getString("subtitle"),
						set.getString("mail_images"), 
						set.getString("sub_images"), 
						set.getString("detail_images"), 
						set.getString("detail_text"), 
						set.getDouble("price"), 
						set.getInt("stock"), 
						set.getInt("status"));
				
				product.setCategory(category);
				products.add(product);
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
		return products;
		
	}
	/**
	 * 添加商品
	 * @return
	 */
	public int addProduct(MallProduct product){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "INSERT INTO mall_product VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW())";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, product.getCategory().getId());
			ps.setString(2, product.getName());
			ps.setString(3, product.getCoding());
			ps.setString(4, product.getSubtitle());
			ps.setString(5, product.getMallImages());
			ps.setString(6, product.getSubImages());
			ps.setString(7, product.getDetailImages());
			ps.setString(8, product.getDetailText());
			ps.setDouble(9,product.getPrice());
			ps.setInt(10, product.getStock());
			ps.setInt(11, product.getStatus());
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
	 * 获取所有商品规格信息
	 * @return
	 */
	public List<MallAttrValue> getAllAttr(){
		MallAttrValue attrValue = null;
		List<MallAttrValue> attrValues = new ArrayList<MallAttrValue>();
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "SELECT a1.*,a2.attr_name AS attr_value,a3.name FROM mall_attr_key a1 INNER JOIN mall_attr_value a2 ON a1.id = a2.attr_id INNER JOIN mall_category a3 ON a1.category_id = a3.id";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				attrValue = new MallAttrValue(set.getInt("id"), set.getString("attr_name"), 
						set.getString("attr_value"), set.getString("name"));
				
				attrValues.add(attrValue);
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
		
		return attrValues;
		
	}
	/**
	 * 根据商品id删除商品
	 * @param proId
	 * @return
	 */
	public int deleteProByProductId(int proId){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "delete from mall_product where id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, proId);
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
	 * 根据名字获取商品
	 * @param proName
	 * @return
	 */
	public List<MallProduct> getProductByName(String proName){
		
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		//分类对象
		MallCategory category = null;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select m1.*,m2.name as cat_name from mall_product m1 inner join mall_category m2 on m1.category_id = m2.id where m1.name like ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setString(1, "%"+proName+"%");
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				
				category = new MallCategory();
				category.setName(set.getString("cat_name"));
				
				product = new MallProduct(set.getInt("id"),
						set.getString("name"), 
						set.getString("coding"), 
						set.getString("subtitle"),
						set.getString("mail_images"), 
						set.getString("sub_images"), 
						set.getString("detail_images"), 
						set.getString("detail_text"), 
						set.getDouble("price"), 
						set.getInt("stock"), 
						set.getInt("status"));
				
				product.setCategory(category);
				products.add(product);
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
		return products;
		
	}
	/**
	 * 根据商品id查询商品
	 * @param proId
	 * @return
	 */
	public MallProduct getProductById(int proId){
		MallProduct product = null;
		//分类对象
		MallCategory category = null;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "SELECT m1.*,m2.name AS cat_name,m2.id AS cat_id FROM mall_product m1 INNER JOIN mall_category m2 ON m1.category_id = m2.id WHERE m1.id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, proId);
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				
				category = new MallCategory();
				category.setName(set.getString("cat_name"));
				category.setId(set.getInt("cat_id"));
				product = new MallProduct(set.getInt("id"),
						set.getString("name"), 
						set.getString("coding"), 
						set.getString("subtitle"),
						set.getString("mail_images"), 
						set.getString("sub_images"), 
						set.getString("detail_images"), 
						set.getString("detail_text"), 
						set.getDouble("price"), 
						set.getInt("stock"), 
						set.getInt("status"));
				
				product.setCategory(category);
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
		return product;
		
	}
	/**
	 * 分页查询
	 * @param page  页数
	 * @param rows	行数
	 * @return
	 */
	public List<MallProduct> getPageProduct(int page, int rows){
		//算出分页
		page = (page-1)*rows;
		
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		//分类对象
		MallCategory category = null;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select m1.*,m2.name as cat_name from mall_product m1 inner join mall_category m2 on m1.category_id = m2.id limit ?,?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, page);
			ps.setInt(2, rows);
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				
				category = new MallCategory();
				category.setName(set.getString("cat_name"));
				
				product = new MallProduct(set.getInt("id"),
						set.getString("name"), 
						set.getString("coding"), 
						set.getString("subtitle"),
						set.getString("mail_images"), 
						set.getString("sub_images"), 
						set.getString("detail_images"), 
						set.getString("detail_text"), 
						set.getDouble("price"), 
						set.getInt("stock"), 
						set.getInt("status"));
				
				product.setCategory(category);
				products.add(product);
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
		return products;
	}
	/**
	 * 根据id修改商品信息
	 * @param proId
	 * @return
	 */
	public int updateProById(MallProduct product){
		int count = 0;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "update mall_product set `name` = ?,category_id=?,mail_images=?,detail_images=?,detail_text=?,price=?,stock=?,`status`=?,update_time=now() where id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setString(1, product.getName());
			ps.setInt(2, product.getCategory().getId());
			ps.setString(3, product.getMallImages());
			ps.setString(4, product.getDetailImages());
			ps.setString(5, product.getDetailText());
			ps.setDouble(6, product.getPrice());
			ps.setInt(7, product.getStock());
			ps.setDouble(8, product.getStatus());
			ps.setInt(9, product.getId());
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
	 * 根据分类id查询商品   比如搜索：手机、电脑
	 * @return
	 */
	public List<MallProduct> getProductByCatId(int[] catId){
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		//分类对象
		MallCategory category = null;
		String cat = "";
		//算出有多少个问号
		if (catId.length == 1) {
			cat = "(?)";
		}else{
			cat = "(";
			for (int i = 0; i < catId.length; i++) {
				if (catId.length == (i+1)) {
					cat = cat+"?)";
				}else{
					cat = cat+"?,";
				}
				
			}
		}
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "select m1.*,m2.name as cat_name from mall_product m1 inner join mall_category m2 on m1.category_id = m2.id where m2.id in "+cat;
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			for (int i = 0; i < catId.length; i++) {
				ps.setInt(i+1, catId[i]);
			}
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				
				category = new MallCategory();
				category.setName(set.getString("cat_name"));
				
				product = new MallProduct(set.getInt("id"),
						set.getString("name"), 
						set.getString("coding"), 
						set.getString("subtitle"),
						set.getString("mail_images"), 
						set.getString("sub_images"), 
						set.getString("detail_images"), 
						set.getString("detail_text"), 
						set.getDouble("price"), 
						set.getInt("stock"), 
						set.getInt("status"));
				
				product.setCategory(category);
				products.add(product);
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
		return products;
	}
	/**
	 * 根据分类id查询商品
	 * @param catId
	 * @return
	 */
	public List<MallProduct> getProductByCatId(int catId){
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "SELECT m1.* FROM mall_product m1 INNER JOIN mall_category m2 " +
					"ON m1.category_id = m2.id WHERE m2.id = ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, catId);
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				
				product = new MallProduct(set.getInt("id"),
						set.getString("name"), 
						set.getString("coding"), 
						set.getString("subtitle"),
						set.getString("mail_images"), 
						set.getString("sub_images"), 
						set.getString("detail_images"), 
						set.getString("detail_text"), 
						set.getDouble("price"), 
						set.getInt("stock"), 
						set.getInt("status"));
				
				products.add(product);
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
		return products;
		
	}
	/**
	 * 条件查询 价格
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<MallProduct> getProductByPrice(int minPrice,int maxPrice){
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		//分类对象
		MallCategory category = null;
		//获取连接对象
		try {
			Connection con = DBUtil.getConnection();
			//编写sql语句
			String sql = "SELECT m1.*,m2.name AS cat_name FROM mall_product m1 INNER JOIN mall_category m2 ON m1.category_id = m2.id WHERE m1.price <= ? AND m1.price >= ?";
			//创建预编译执行对象
			PreparedStatement ps = con.prepareStatement(sql);
			//设置相应参数
			ps.setInt(1, maxPrice);
			ps.setInt(2, minPrice);
			//执行
			
			ResultSet set = ps.executeQuery();
			
			//获取结果集
			while(set.next()){
				
				category = new MallCategory();
				category.setName(set.getString("cat_name"));
				
				product = new MallProduct(set.getInt("id"),
						set.getString("name"), 
						set.getString("coding"), 
						set.getString("subtitle"),
						set.getString("mail_images"), 
						set.getString("sub_images"), 
						set.getString("detail_images"), 
						set.getString("detail_text"), 
						set.getDouble("price"), 
						set.getInt("stock"), 
						set.getInt("status"));
				
				product.setCategory(category);
				products.add(product);
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
		return products;
	}
	
	
	
}
