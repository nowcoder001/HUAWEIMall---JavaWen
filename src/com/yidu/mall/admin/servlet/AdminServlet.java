package com.yidu.mall.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.yidu.mall.admin.biz.AdminBiz;
import com.yidu.mall.admin.model.Admin;
import com.yidu.mall.admin.model.Settings;

public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	AdminBiz adminBiz = new AdminBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String mod = request.getParameter("mod");
		
		if ("adminLogin".equals(mod)) {   //����Ա��¼
			adminLogin(request,response);
		}else if ("webSet".equals(mod)) {   //��̨��վ����
			webSet(request,response);
		}else if ("updateWebSet".equals(mod)) {   //�޸���վ����
			updateWebSet	(request,response);
		}
	}
	/**
	 * �޸���վ����
	 * @param request
	 * @param response
	 */
	private void updateWebSet(HttpServletRequest request,
			HttpServletResponse response) {
		
		//��ȡ�����еĲ���
		String title = request.getParameter("title");
		String keyWord = request.getParameter("keyWord");
		String describe = request.getParameter("describe");
		String serviceQQ = request.getParameter("serviceQQ");
		String serviceMobile = request.getParameter("serviceMobile");
		String copy = request.getParameter("copy");
		String ICP = request.getParameter("ICP");
		String hotSearch = request.getParameter("hotSearch");
		String stat = request.getParameter("stat");
		Settings settings = new Settings(title, keyWord, describe, serviceQQ, serviceMobile, copy, ICP, hotSearch, stat);
		
		//��ȡȫ��������
		ServletContext application = this.getServletContext();
		application.setAttribute("WEB_SET", settings);
		
		
	}
	/**
	 * ��̨��վ����
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void webSet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡȫ��������
		ServletContext application = this.getServletContext();
		Settings settings = (Settings) application.getAttribute("WEB_SET");
		
		if (settings == null) {
			settings = new Settings("��Ϊ�̳�(Vmall.com)_��Ϊ�ֻ�����ҫ�ֻ���������Ʒ����_��������Ƽ�", 
					"����̳�ϵͳ,�����̳�ϵͳ,���û��̳�ϵͳ,�����̳�ϵͳ,΢���̳�ϵͳ,�̳�Դ��,�ֻ��ƶ��̳�ϵͳ,b2b2c�̳�ϵͳ,����ϵͳ,����ϵͳ",
					"�����̳�ϵͳ�߱���������ҵ����������л������ܣ����䰲ȫ�ȶ��������á���Чרҵ������Ӯ�����û��Ĺ㷺������Ϊ�û��ṩ��һ���ͳɱ�����Ч�ʵ������̳ǽ��跽����", 
					"1551282386", 
					"88888888", 
					"��������Ƽ�����",
					"³ICP��17015644��-2",
					"B2C�̳�ϵͳ", 
					"<script type='text/javascript' src='https://s22.cnzz.com/z_stat.php?id=1261989361&web_id=1261989361'></script>");
			
			
		}
		application.setAttribute("WEB_SET", settings);
		String webSetJson = JSONArray.fromObject(settings).toString();
		out.println(webSetJson);
		
	}

	/**
	 * ����Ա��¼
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void adminLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		Admin admin = adminBiz.loginAdmin(name, password);
		if (admin !=  null) {
			//����session
			HttpSession session = request.getSession();
			
			session.setAttribute("ADMIN", admin);
			out.println(1);
		}else{
			out.println(0);
		}
		
		
		
		
	}

}
