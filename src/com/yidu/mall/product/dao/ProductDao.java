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
 * ��Ʒ���ݷ��ʲ�
 * @author С��ħ
 *
 */
public class ProductDao {
	
	/**
	 * ��ȡȫ����Ʒ
	 * @return
	 */
	public List<MallProduct> getAllProduct(){
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		//�������
		MallCategory category = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select m1.*,m2.name as cat_name from mall_product m1 inner join mall_category m2 on m1.category_id = m2.id";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
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
			//�ر���Դ
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
	 * �����Ʒ
	 * @return
	 */
	public int addProduct(MallProduct product){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "INSERT INTO mall_product VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW())";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
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
	/**
	 * ��ȡ������Ʒ�����Ϣ
	 * @return
	 */
	public List<MallAttrValue> getAllAttr(){
		MallAttrValue attrValue = null;
		List<MallAttrValue> attrValues = new ArrayList<MallAttrValue>();
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT a1.*,a2.attr_name AS attr_value,a3.name FROM mall_attr_key a1 INNER JOIN mall_attr_value a2 ON a1.id = a2.attr_id INNER JOIN mall_category a3 ON a1.category_id = a3.id";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
			while(set.next()){
				attrValue = new MallAttrValue(set.getInt("id"), set.getString("attr_name"), 
						set.getString("attr_value"), set.getString("name"));
				
				attrValues.add(attrValue);
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
		
		return attrValues;
		
	}
	/**
	 * ������Ʒidɾ����Ʒ
	 * @param proId
	 * @return
	 */
	public int deleteProByProductId(int proId){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "delete from mall_product where id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, proId);
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
	/**
	 * �������ֻ�ȡ��Ʒ
	 * @param proName
	 * @return
	 */
	public List<MallProduct> getProductByName(String proName){
		
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		//�������
		MallCategory category = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select m1.*,m2.name as cat_name from mall_product m1 inner join mall_category m2 on m1.category_id = m2.id where m1.name like ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setString(1, "%"+proName+"%");
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
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
			//�ر���Դ
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
	 * ������Ʒid��ѯ��Ʒ
	 * @param proId
	 * @return
	 */
	public MallProduct getProductById(int proId){
		MallProduct product = null;
		//�������
		MallCategory category = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT m1.*,m2.name AS cat_name,m2.id AS cat_id FROM mall_product m1 INNER JOIN mall_category m2 ON m1.category_id = m2.id WHERE m1.id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, proId);
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
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
			//�ر���Դ
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
	 * ��ҳ��ѯ
	 * @param page  ҳ��
	 * @param rows	����
	 * @return
	 */
	public List<MallProduct> getPageProduct(int page, int rows){
		//�����ҳ
		page = (page-1)*rows;
		
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		//�������
		MallCategory category = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select m1.*,m2.name as cat_name from mall_product m1 inner join mall_category m2 on m1.category_id = m2.id limit ?,?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, page);
			ps.setInt(2, rows);
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
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
			//�ر���Դ
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
	 * ����id�޸���Ʒ��Ϣ
	 * @param proId
	 * @return
	 */
	public int updateProById(MallProduct product){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "update mall_product set `name` = ?,category_id=?,mail_images=?,detail_images=?,detail_text=?,price=?,stock=?,`status`=?,update_time=now() where id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setString(1, product.getName());
			ps.setInt(2, product.getCategory().getId());
			ps.setString(3, product.getMallImages());
			ps.setString(4, product.getDetailImages());
			ps.setString(5, product.getDetailText());
			ps.setDouble(6, product.getPrice());
			ps.setInt(7, product.getStock());
			ps.setDouble(8, product.getStatus());
			ps.setInt(9, product.getId());
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
	/**
	 * ���ݷ���id��ѯ��Ʒ   �����������ֻ�������
	 * @return
	 */
	public List<MallProduct> getProductByCatId(int[] catId){
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		//�������
		MallCategory category = null;
		String cat = "";
		//����ж��ٸ��ʺ�
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
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select m1.*,m2.name as cat_name from mall_product m1 inner join mall_category m2 on m1.category_id = m2.id where m2.id in "+cat;
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			for (int i = 0; i < catId.length; i++) {
				ps.setInt(i+1, catId[i]);
			}
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
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
			//�ر���Դ
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
	 * ���ݷ���id��ѯ��Ʒ
	 * @param catId
	 * @return
	 */
	public List<MallProduct> getProductByCatId(int catId){
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT m1.* FROM mall_product m1 INNER JOIN mall_category m2 " +
					"ON m1.category_id = m2.id WHERE m2.id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, catId);
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
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
			//�ر���Դ
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
	 * ������ѯ �۸�
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<MallProduct> getProductByPrice(int minPrice,int maxPrice){
		List<MallProduct> products = new ArrayList<MallProduct>();
		MallProduct product = null;
		//�������
		MallCategory category = null;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT m1.*,m2.name AS cat_name FROM mall_product m1 INNER JOIN mall_category m2 ON m1.category_id = m2.id WHERE m1.price <= ? AND m1.price >= ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, maxPrice);
			ps.setInt(2, minPrice);
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
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
			//�ر���Դ
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
