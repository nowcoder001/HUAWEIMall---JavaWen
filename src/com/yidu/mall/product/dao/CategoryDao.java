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
 * ����������ݷ��ʲ�
 * @author С��ħ
 *
 */
public class CategoryDao {
	/**
	 * ��ѯȫ���ķ���
	 * @return
	 */
	public List<MallCategory> getAllCatrgory(){
		MallCategory category = null;
		List<MallCategory> categories = new ArrayList<MallCategory>();
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "select * from mall_category";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			//ִ��
			
			ResultSet set = ps.executeQuery();
			
			//��ȡ�����
			while(set.next()){
				
				category = new MallCategory(set.getInt("id"), 
						set.getInt("parent_id"),
						set.getString("name"), 
						set.getInt("status"), 
						set.getInt("sort_order"));
				category.setManagerId(set.getInt("manager_id"));
				categories.add(category);
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
		
		return categories;
		
	}
	/**
	 * ��ӷ���
	 * @param category
	 * @return
	 */
	public int addCategory(MallCategory category){
		int count = 0;
		
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "INSERT INTO mall_category VALUES (null,?,?,?,?,NOW(),NOW(),?,null)";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, category.getParentId());
					ps.setString(2, category.getName());
					ps.setInt(3, category.getStatus());
					ps.setInt(4, category.getSortOrder());
					ps.setInt(5, category.getManagerId());
					//ִ��
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
	 * ɾ��������Ϣ����id
	 * @return
	 */
	public int deleteCatById(int categoryId){
		
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "delete from mall_category where id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, categoryId);
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
	 * ����id��ѯ��Ʒ����
	 * @param catId
	 * @return
	 */
	public MallCategory getCategoryById(int catId){
		MallCategory category = null;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "select * from mall_category where id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, catId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					
					//��ȡ�����
					while(set.next()){
						
						category = new MallCategory(set.getInt("id"), 
								set.getInt("parent_id"),
								set.getString("name"), 
								set.getInt("status"), 
								set.getInt("sort_order"));
						category.setManagerId(set.getInt("manager_id"));
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
		return category;
		
	}
	/**
	 * ����id�޸ķ�����Ϣ
	 * @param category
	 * @return
	 */
	public int updateCatById(MallCategory category){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "UPDATE mall_category SET parent_id = ?,`name`=?,`status`=?,sort_order=?,update_time=NOW(),manager_id=? WHERE id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//������Ӧ����
			ps.setInt(1, category.getParentId());
			ps.setString(2, category.getName());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getSortOrder());
			ps.setInt(5, category.getManagerId());
			ps.setInt(6, category.getId());
			//ִ��
			
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
	 * ���ݸ���id��ȡ����
	 * @param parentId
	 * @return
	 */
	public List<MallCategory> getCategory(int parentId){
		List<MallCategory> categories = new ArrayList<MallCategory>();
		MallCategory category = null;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "SELECT * FROM mall_category WHERE parent_id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//������Ӧ����
					ps.setInt(1, parentId);
					//ִ��
					
					ResultSet set = ps.executeQuery();
					
					//��ȡ�����
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
					//�ر���Դ
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
