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
 * �������ݷ��ʲ�
 * @author С��ħ
 *
 */
public class CommentDao {
	/**
	 * ������Ʒ����
	 * @param comment  ��������
	 * @param userId   �û�id
	 * @param productId   ��Ʒid
	 * @return  �޸��Ƿ�ɹ�
	 */
	public int addComment(String content,int userId,int productId){
		int count = 0;
		
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "INSERT INTO `huaweimall`.`mall_comment` VALUES (NULL, ?, ?, ?, NULL, NOW(), NOW())";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//ִ��sql���
			ps.setInt(1, userId);   //�û�id
			ps.setInt(2, productId);   //��Ʒid
			ps.setString(3, content);   //��������
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
	 * ������Ʒid��ȡ����
	 * @param productId   ��Ʒid
	 * @return  ���ۼ���
	 */
	public List<MallComment> getComments(int productId){
		//�������ۼ���
		List<MallComment> comments = new ArrayList<MallComment>();
		//���۶���Ϊ��
		MallComment comment = null;
		//�û�����Ϊ��
		MallUser user = null;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���   ��������  ���۱�  �û���  ��Ʒ��   ����   ��Ʒ���id  ��
					String sql = "SELECT m1.*,m2.* FROM mall_comment m1 INNER JOIN mall_user m2 " +
							"ON m1.user_id = m2.id INNER JOIN mall_product m3 " +
							"ON m1.product_id = m3.id WHERE m3.id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//���ò��� ��Ʒid
					ps.setInt(1, productId);
					//ִ��sql���
					ResultSet set = ps.executeQuery();
					//��ȡ���
					
					while(set.next()){
						//�������۶���
						comment = new MallComment();
						//�����ݿ��ȡid   ��������id,, 
						comment.setId(set.getInt(1));
						//�����ݿ��ȡ����   ������������
						comment.setContent(set.getString("content"));
						//�����ݿ��ȡ�ظ�����   �������ۻظ�����
						comment.setReplyContent(set.getString("reply_content"));
						//�����ݿ��ȡ�����¼�   �������۴����¼�
						comment.setCreateTime(set.getString("create_time"));
						//�����û�����
						user = new MallUser();
						//�����ݿ��ȡ�û�id   �����û�id
						user.setId(set.getInt(8));
						//�����ݿ��ȡ�û�����   �����û�����
						user.setUser_name(set.getString("user_name"));
						//�����ݿ��ȡ�û�ͷ��   �����û�ͷ��
						user.setUser_img(set.getString("user_img"));
						//�� �û�����  set  ���۶���
						comment.setUser(user);
						//���۶��� ���� ���ۼ���
						comments.add(comment);
					}
					
					//�ر���Դ
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		
		return comments;
		
	}
	/**
	 * �ж��û��Ƿ������
	 * @param userId   �û�id
	 * @param productId   ��Ʒid
	 * @return  �Ƿ������
	 */
	public int UserComment(int userId,int productId){
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���  2��������   ���������  ������  ����  ��1�û�id����˭ ��  ��1��Ʒid  �� ��2����״̬���ڻ����30 �� ��2����״̬С��60
			String sql = "SELECT COUNT(*) FROM mall_order_item m1 INNER JOIN mall_order m2 " +
					"ON m1.order_id = m2.id WHERE m1.user_id = ? AND m1.product_id = ? AND m2.status >= 30 AND m2.status < 60";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//���ò���  �û�id
			ps.setInt(1, userId);
			//���ò���   ��Ʒid
			ps.setInt(2, productId);
			//ִ��sql���
			//��ȡ���
			ResultSet set = ps.executeQuery();
			while(set.next()){
				
				count = set.getInt(1);  //��ȡ�±�Ϊ1 �����ݿ���
				
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
	 * �û��Դ���Ʒ������״̬(�����ۻ���δ����) 
	 * @param userId    �û�id
	 * @param productId    ��Ʒid
	 * @return   ����1��˵���Ѿ����۹�
	 */
	public int productCommentStatus(int userId,int productId){
		
		int count = 0;
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT COUNT(*) FROM mall_order_item m1 INNER JOIN mall_comment m2 " +
					"ON m1.product_id = m2.product_id WHERE m1.user_id = ? AND m1.product_id =  ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//���ò���   �û�id
			ps.setInt(1, userId);
			//���ò���    ��Ʒid
			ps.setInt(2, productId);
			//ִ��sql���
			ResultSet set = ps.executeQuery();
			//��ȡ���
			
			
			while(set.next()){
				//��ȡ���ݿ� �����ݵ�һ��
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
	 * ��ȡȫ��������Ϣ
	 * @param page   ҳ��
	 * @param rows   ����
	 * @return   ���ۼ���
	 */
	public List<MallComment> getAllComment(int page,int rows){
		//��ҳ����
		page = (page-1)*rows;
		//�������ۼ���
		List<MallComment> comments = new ArrayList<MallComment>();
		//���۶���Ϊ��
		MallComment comment = null;
		//�û�����Ϊ��
		MallUser user = null;
		//�����������Ϊ��
		MallOrderItem orderItem = null;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���   ��������   ���۱� �� �û��� ���������   
					String sql = "SELECT m1.id AS comId,m1.reply_content,m1.content,m1.create_time," +
							"m2.user_img,m2.user_name," +
							"m3.product_id,m3.product_image,m3.product_name ,COUNT(1) FROM mall_comment m1 " +
							"INNER JOIN mall_user m2 ON m1.user_id = m2.id INNER JOIN mall_order_item m3 " +
							"ON m1.product_id = m3.product_id GROUP BY m1.id LIMIT ?,?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//ִ��sql���
					//���ò���  ҳ��
					ps.setInt(1, page);
					//���ò���   ����
					ps.setInt(2, rows);
					//��ȡ���
					ResultSet set = ps.executeQuery();
					while(set.next()){
						//�������۶���
						comment = new MallComment();
						comment.setId(set.getInt("comId"));
						comment.setContent(set.getString("content"));
						comment.setReplyContent(set.getString("reply_content"));
						comment.setCreateTime(set.getString("create_time"));
						//�����û�����
						user = new MallUser();
						user.setUser_name(set.getString("user_name"));
						user.setUser_img(set.getString("user_img"));
						//���������������
						orderItem = new MallOrderItem();
						orderItem.setProductId(set.getInt("product_id"));
						orderItem.setProductImage(set.getString("product_image"));
						orderItem.setProductName(set.getString("product_name"));
						comment.setOrderItem(orderItem);
						comment.setUser(user);
						comments.add(comment);
					}
					//�ر���Դ
					DBUtil.closeSource(con, ps, set);
				} catch (ClassNotFoundException e) {
							
					e.printStackTrace();
				} catch (SQLException e) {
							
					e.printStackTrace();
				}
		return comments;
		
	}
	/**
	 * ����id  ��ȡ��������
	 * @param commentId    ����id
	 * @return  ���۶���
	 */
	public MallComment getCommentContent(int commentId){
		MallComment comment = new MallComment();
		//��ȡ���Ӷ���
		try {
			Connection con = DBUtil.getConnection();
			//��дsql���
			String sql = "SELECT * FROM mall_comment WHERE id = ?";
			//����Ԥ����ִ�ж���
			PreparedStatement ps = con.prepareStatement(sql);
			//���ò���  ����id
			ps.setInt(1, commentId);
			//ִ��sql���
			ResultSet set = ps.executeQuery();
			//��ȡ���
			
			while(set.next()){
				//���۶���ֵid
				comment.setId(set.getInt("id"));
				//���۶���ֵ����
				comment.setContent(set.getString("content"));
				//���۶���ֵ  �ظ�����
				comment.setReplyContent(set.getString("reply_content"));
				//���۶���ֵ  ����ʱ��
				comment.setCreateTime(set.getString("create_time"));
			}
			//�ر���Դ
			DBUtil.closeSource(con, ps, set);
		} catch (ClassNotFoundException e) {
					
			e.printStackTrace();
		} catch (SQLException e) {
					
			e.printStackTrace();
		}
		
		return comment;
		
	}
	/**
	 * �ظ�����
	 * @param commentId    ����id
	 * @param reply   �ظ�����
	 * @return  �Ƿ�ظ��ɹ�
	 */
	public int replyContent(int commentId,String reply){
		int count = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "UPDATE mall_comment SET reply_content = ? WHERE id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//ִ��sql���
					ps.setString(1, reply);   //�ظ�����
					ps.setInt(2, commentId);   //����id
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
	 * ��������idɾ������
	 * @param commentId   ����id
	 * @return  �Ƿ�ɾ���ɹ�
	 */
	public int deleteComment(int commentId){
		int count = 0;
		//��ȡ���Ӷ���
				try {
					Connection con = DBUtil.getConnection();
					//��дsql���
					String sql = "DELETE FROM mall_comment WHERE id = ?";
					//����Ԥ����ִ�ж���
					PreparedStatement ps = con.prepareStatement(sql);
					//ִ��sql���
					ps.setInt(1, commentId);
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
}
