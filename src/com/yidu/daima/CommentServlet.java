package com.yidu.daima;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.yidu.mall.comment.biz.CommentBiz;
import com.yidu.mall.comment.model.CommentImagesJson;
import com.yidu.mall.comment.model.MallComment;
import com.yidu.mall.user.model.MallUser;

import sun.misc.BASE64Decoder;
/**
 * ����servlet
 * @author С��ħ
 *
 */
public class CommentServlet extends HttpServlet {

	/**
	 * get����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	//��������ҵ���߼���
	CommentBiz commentBiz = new CommentBiz();
	/**
	 * post����
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");   //����ת��
		
		response.setCharacterEncoding("utf-8");   //��Ӧת��
		
		String mod = request.getParameter("mod");   //��ȡ�������  mod
		
		if ("addComment".equals(mod)) {  //�����Ʒ����  addComment
			addComment(request,response);
		}else if ("getCommentByProductId".equals(mod)) {   //������Ʒ��ȡ����   getCommentByProductId
			getCommentByProductId(request,response);
		}else if ("userComment".equals(mod)) {   //�жϵ�¼���û��Ƿ�����۴���Ʒ   userComment
			userComment(request,response);
		}
	}
	/**
	 * �жϵ�¼���û��Ƿ�����۴���Ʒ
	 * @param request  ����
	 * @param response   ��Ӧ
	 * @throws IOException 
	 */
	private void userComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//��ȡ��Ӧ�������
		PrintWriter out = response.getWriter();
		//��session��ȡ�û�����
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//��ȡ�����еĲ���ֵ  ��Ʒid  produId
		int productId = Integer.parseInt(request.getParameter("produId"));
		//����û�������ڿ�
		if (user == null ) {
			//��Ӧ��� 0 
			out.println(0);
			
		}else{
			//��������ҵ���߼��㷽��  �ж��û��Ƿ������
			int count = commentBiz.UserComment(user.getId(), productId);
			//����˵����������Ʒ��������
			if (count > 0) {
				//�ٽ�һ����ѯ  ����ҵ���߼����Ƿ��ѶԴ���Ʒ����״̬����
				int count2 = commentBiz.productCommentStatus(user.getId(), productId);
				
				if (count2 > 0) {  //count2����0˵���Ѿ����۹���������
					 //count �͵���0
					count = 0;
					
				}else{ //����
					//����1
					count = 1;
				}
			}
			//��Ӧ��� count
			out.println(count);
		}
		//�ر���
		out.close();
	}
	/**
	 * ������Ʒ��ȡ����
	 * @param request  ����
	 * @param response   ��Ӧ
	 * @throws IOException  IO�쳣
	 */
	private void getCommentByProductId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ  ��Ʒid proidd
		int productId = Integer.parseInt(request.getParameter("proIdd"));
		//����ҵ���߼��㷽�� ������Ʒid��ȡ����
		List<MallComment> comments = commentBiz.getComments(productId);
		//�����ۼ���ת��json
		String commentJson = JSONArray.fromObject(comments).toString();
		//��Ӧ���json
		out.println(commentJson);
		//�ر���
		out.close();
	}
	/**
	 * �����Ʒ����
	 * @param request   ����
	 * @param response   ��Ӧ
	 * @throws IOException �쳣
	 */
	private void addComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��session��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
			//��ȡ�����еĲ���ֵ
			int productId = Integer.parseInt(request.getParameter("product_id"));  //��ȡ��Ʒ����id
			
			String content = request.getParameter("content");    //��ȡ�����ı�
			//��ȡ�����еĲ���ֵ(����)  contentBase64
			String[] base64 = request.getParameterValues("contentBase64");
			//��ȡ�����еĲ���ֵ(����)  imageName
			String[] imagesName = request.getParameterValues("imageName");
			//����ͼƬ����
			List<CommentImagesJson> imagesJsons = new ArrayList<CommentImagesJson>();
			//��base64ת��ͼƬ
			for (int i =0; i < base64.length;i++) {
				//��base64ת��ͼƬ
				GenerateImage(base64[i], imagesName[i]);
				//����ͼƬjsonʵ���� Ϊ��
				CommentImagesJson imagesJson = null;
				// ��� i ���� 0
				if (i == 0) {
					//��������ͼƬjson��������ݺ�����ͼƬ��װ
					imagesJson = new CommentImagesJson(content, "static/comment_img/"+imagesName[i]);
					
					//����
				}else{  //�ڶ��δ�������ͼƬjson������Ҫ����
					imagesJson = new CommentImagesJson("", "static/comment_img/"+imagesName[i]);
				}
				//��������ͼƬ����
				imagesJsons.add(imagesJson);
			}
			//����ͼƬ����תjson��ʽ
			String imgJson = JSONArray.fromObject(imagesJsons).toString();
			//����ҵ���߼���  ��������
			int count = commentBiz.addComment(imgJson, user.getId(), productId);
			//��Ӧ count
			out.println(count);
			//�ر���
			out.close();
	}
	/**
	 * base64�ַ���ת����ͼƬ  
	 * @param imgStr   ͼƬ·��
	 * @param imgName     ͼƬ����
	 * @return
	 */
    public static boolean GenerateImage(String imgStr,String imgName)  {   //���ֽ������ַ�������Base64���벢����ͼƬ  
    	//�ж�ͼƬ�ĺ�׺��
    	int index = imgName.indexOf(".");
    	String newImgStr = imgName.substring(index+1);
    	
    	imgStr = imgStr.replaceAll("data:image/"+newImgStr+";base64,", "");
        if (imgStr == null) //ͼ������Ϊ��  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64����  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//�����쳣����  
                    b[i]+=256;  
                }  
            }  
            //����jpegͼƬ  
            
            String imgFilePath = "E:\\Java\\ԭ����Ŀ\\project\\HuaweiMall\\HuaweiMall\\WebRoot\\static\\comment_img\\"+imgName;//�����ɵ�ͼƬ  
            //�����
            OutputStream out = new FileOutputStream(imgFilePath);      
            
            out.write(b);   //��Ӧ �ֽ�����  b
            
            out.flush();  //ˢ����Ӧ
            
            out.close();   //�ر����
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }

}
