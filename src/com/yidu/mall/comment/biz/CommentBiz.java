package com.yidu.mall.comment.biz;

import java.util.List;

import com.yidu.mall.comment.dao.CommentDao;
import com.yidu.mall.comment.model.MallComment;

/**
 * 评论业务逻辑层
 * @author 小恶魔
 *
 */
public class CommentBiz {
	CommentDao commentDao = new CommentDao();
	/**
	 * 新增商品评价
	 * @param comment
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int addComment(String content,int userId,int productId){
		return commentDao.addComment(content, userId, productId);
	}
	/**
	 * 根据商品id获取评论
	 * @return
	 */
	public List<MallComment> getComments(int productId){
		return commentDao.getComments(productId);
	}
	/**
	 * 判断用户是否可评论
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int UserComment(int userId,int productId){
		return commentDao.UserComment(userId, productId);
	}
	/**
	 * 用户对此商品的评价状态(已评论还是未评论) 大于1既说明已经评价过
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int productCommentStatus(int userId,int productId){
		return commentDao.productCommentStatus(userId, productId);
	}
	/**
	 * 获取全部评论信息
	 * @return
	 */
	public List<MallComment> getAllComment(int page,int rows){
		return commentDao.getAllComment(page,rows);
	}
	/**
	 * 根据id  获取评论内容
	 * @param commentId
	 * @return
	 */
	public MallComment getCommentContent(int commentId){
		return commentDao.getCommentContent(commentId);
	}
	/**
	 * 回复评论
	 * @param commentId
	 * @param reply
	 * @return
	 */
	public int replyContent(int commentId,String reply){
		return commentDao.replyContent(commentId, reply);
	}
	/**
	 * 根据评论id删除评论
	 * @param commentId
	 * @return
	 */
	public int deleteComment(int commentId){
		return commentDao.deleteComment(commentId);
	}
}
