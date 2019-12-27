package com.yidu.daima;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.yidu.mall.comment.biz.CommentBiz;
import com.yidu.mall.comment.model.MallComment;
/**
 * 后台评价servlet
 * @author 小恶魔
 *	data - time  2019/11/20
 */
public class CommentAdminServlet extends HttpServlet {

	/**
	 * get请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	//评论业务逻辑层
	CommentBiz commentBiz = new CommentBiz();
	
	/**
	 * post请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");  //请求转码
		
		response.setCharacterEncoding("utf-8");  //响应转码
		
		String mod = request.getParameter("mod");  //获取请求参数  mod
		
		if ("getAllComment".equals(mod)) {   //获取全部评论
			getAllComment(request,response);
		}else if ("getContent".equals(mod)) {   //获取评论内容
			getContent(request,response);
		}else if ("replyComment".equals(mod)) {   //回复评论
			replyComment(request,response);
		}else if ("deleteComment".equals(mod)) {   //删除评论
			deleteComment(request,response);
		}
		
		
	}
	/**
	 * 删除评论
	 * @param request  请求
	 * @param response  响应
	 * @throws IOException   异常
	 */
	private void deleteComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数值
		int commentId = Integer.parseInt(request.getParameter("comment_id"));
		//调用业务逻辑层   根据评论id 删除评论
		int count = commentBiz.deleteComment(commentId);
		//大于0 
		if (count > 0) {
			//响应输出删除成功
			out.println("删除成功！");
		}else{
			//响应输出  删除失败
			out.println("删除失败！");
		}
		//关闭流
		out.close();
	}
	/**
	 * 回复评论
	 * @param request  请求
	 * @param response  响应
	 * @throws IOException    异常
	 */
	private void replyComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中在参数值
		
		int commentId = Integer.parseInt(request.getParameter("comment_id"));  //请求参数 - comment_id
		
		String replyContent = request.getParameter("reply_content");   //请求参数 - reply_content  回复内容
		//调用业务逻辑层   根据评论id 回复评论
		int count = commentBiz.replyContent(commentId, replyContent);
		//大于0
		if (count > 0) {
			//输出回复成功啦!
			out.println("回复成功啦！");
		}else{
			//输出回复失败
			out.println("回复失败");
		}
		
		out.close();
	}
	/**
	 * 获取评论内容
	 * @param request 请求
	 * @param response   响应
	 * @throws IOException   异常
	 */
	private void getContent(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数值
		int commentId = Integer.parseInt(request.getParameter("comm_id"));
		//调用业务逻辑层   根据评论id 获取评论对象
		MallComment comment = commentBiz.getCommentContent(commentId);
		//把评论对象转换成json
		String commentJson = JSONArray.fromObject(comment).toString();
		//响应输出   评论json
		out.println(commentJson);
		//关闭流
		out.close();
	}

	/**
	 * 获取全部评论
	 * @param request  请求
	 * @param response   响应
	 * @throws IOException  异常
	 */
	private void getAllComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//响应输出对象
		PrintWriter out = response.getWriter();
		
		int page = Integer.parseInt(request.getParameter("page"));  //页数
		int rows = Integer.parseInt(request.getParameter("rows"));  //行数
		//调用评论业务逻辑层  获取全部评论
		List<MallComment> comments = commentBiz.getAllComment(page,rows);
		//评论集合转成json格式
		String commentJson = JSONArray.fromObject(comments).toString();
		//输出json
		out.println(commentJson);
		//关闭流
		out.close();
	}

}
