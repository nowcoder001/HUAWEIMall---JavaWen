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
 * ��̨����servlet
 * @author С��ħ
 *	data - time  2019/11/20
 */
public class CommentAdminServlet extends HttpServlet {

	/**
	 * get����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	//����ҵ���߼���
	CommentBiz commentBiz = new CommentBiz();
	
	/**
	 * post����
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");  //����ת��
		
		response.setCharacterEncoding("utf-8");  //��Ӧת��
		
		String mod = request.getParameter("mod");  //��ȡ�������  mod
		
		if ("getAllComment".equals(mod)) {   //��ȡȫ������
			getAllComment(request,response);
		}else if ("getContent".equals(mod)) {   //��ȡ��������
			getContent(request,response);
		}else if ("replyComment".equals(mod)) {   //�ظ�����
			replyComment(request,response);
		}else if ("deleteComment".equals(mod)) {   //ɾ������
			deleteComment(request,response);
		}
		
		
	}
	/**
	 * ɾ������
	 * @param request  ����
	 * @param response  ��Ӧ
	 * @throws IOException   �쳣
	 */
	private void deleteComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		int commentId = Integer.parseInt(request.getParameter("comment_id"));
		//����ҵ���߼���   ��������id ɾ������
		int count = commentBiz.deleteComment(commentId);
		//����0 
		if (count > 0) {
			//��Ӧ���ɾ���ɹ�
			out.println("ɾ���ɹ���");
		}else{
			//��Ӧ���  ɾ��ʧ��
			out.println("ɾ��ʧ�ܣ�");
		}
		//�ر���
		out.close();
	}
	/**
	 * �ظ�����
	 * @param request  ����
	 * @param response  ��Ӧ
	 * @throws IOException    �쳣
	 */
	private void replyComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�������ڲ���ֵ
		
		int commentId = Integer.parseInt(request.getParameter("comment_id"));  //������� - comment_id
		
		String replyContent = request.getParameter("reply_content");   //������� - reply_content  �ظ�����
		//����ҵ���߼���   ��������id �ظ�����
		int count = commentBiz.replyContent(commentId, replyContent);
		//����0
		if (count > 0) {
			//����ظ��ɹ���!
			out.println("�ظ��ɹ�����");
		}else{
			//����ظ�ʧ��
			out.println("�ظ�ʧ��");
		}
		
		out.close();
	}
	/**
	 * ��ȡ��������
	 * @param request ����
	 * @param response   ��Ӧ
	 * @throws IOException   �쳣
	 */
	private void getContent(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		int commentId = Integer.parseInt(request.getParameter("comm_id"));
		//����ҵ���߼���   ��������id ��ȡ���۶���
		MallComment comment = commentBiz.getCommentContent(commentId);
		//�����۶���ת����json
		String commentJson = JSONArray.fromObject(comment).toString();
		//��Ӧ���   ����json
		out.println(commentJson);
		//�ر���
		out.close();
	}

	/**
	 * ��ȡȫ������
	 * @param request  ����
	 * @param response   ��Ӧ
	 * @throws IOException  �쳣
	 */
	private void getAllComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//��Ӧ�������
		PrintWriter out = response.getWriter();
		
		int page = Integer.parseInt(request.getParameter("page"));  //ҳ��
		int rows = Integer.parseInt(request.getParameter("rows"));  //����
		//��������ҵ���߼���  ��ȡȫ������
		List<MallComment> comments = commentBiz.getAllComment(page,rows);
		//���ۼ���ת��json��ʽ
		String commentJson = JSONArray.fromObject(comments).toString();
		//���json
		out.println(commentJson);
		//�ر���
		out.close();
	}

}
