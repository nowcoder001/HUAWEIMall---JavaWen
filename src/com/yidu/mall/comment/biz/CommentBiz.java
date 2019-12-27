package com.yidu.mall.comment.biz;

import java.util.List;

import com.yidu.mall.comment.dao.CommentDao;
import com.yidu.mall.comment.model.MallComment;

/**
 * ����ҵ���߼���
 * @author С��ħ
 *
 */
public class CommentBiz {
	CommentDao commentDao = new CommentDao();
	/**
	 * ������Ʒ����
	 * @param comment
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int addComment(String content,int userId,int productId){
		return commentDao.addComment(content, userId, productId);
	}
	/**
	 * ������Ʒid��ȡ����
	 * @return
	 */
	public List<MallComment> getComments(int productId){
		return commentDao.getComments(productId);
	}
	/**
	 * �ж��û��Ƿ������
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int UserComment(int userId,int productId){
		return commentDao.UserComment(userId, productId);
	}
	/**
	 * �û��Դ���Ʒ������״̬(�����ۻ���δ����) ����1��˵���Ѿ����۹�
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int productCommentStatus(int userId,int productId){
		return commentDao.productCommentStatus(userId, productId);
	}
	/**
	 * ��ȡȫ��������Ϣ
	 * @return
	 */
	public List<MallComment> getAllComment(int page,int rows){
		return commentDao.getAllComment(page,rows);
	}
	/**
	 * ����id  ��ȡ��������
	 * @param commentId
	 * @return
	 */
	public MallComment getCommentContent(int commentId){
		return commentDao.getCommentContent(commentId);
	}
	/**
	 * �ظ�����
	 * @param commentId
	 * @param reply
	 * @return
	 */
	public int replyContent(int commentId,String reply){
		return commentDao.replyContent(commentId, reply);
	}
	/**
	 * ��������idɾ������
	 * @param commentId
	 * @return
	 */
	public int deleteComment(int commentId){
		return commentDao.deleteComment(commentId);
	}
}
