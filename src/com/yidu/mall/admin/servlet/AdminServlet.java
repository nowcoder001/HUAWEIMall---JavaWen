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
		
		if ("adminLogin".equals(mod)) {   //管理员登录
			adminLogin(request,response);
		}else if ("webSet".equals(mod)) {   //后台网站设置
			webSet(request,response);
		}else if ("updateWebSet".equals(mod)) {   //修改网站设置
			updateWebSet	(request,response);
		}
	}
	/**
	 * 修改网站设置
	 * @param request
	 * @param response
	 */
	private void updateWebSet(HttpServletRequest request,
			HttpServletResponse response) {
		
		//获取请求中的参数
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
		
		//获取全局作用域
		ServletContext application = this.getServletContext();
		application.setAttribute("WEB_SET", settings);
		
		
	}
	/**
	 * 后台网站设置
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void webSet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取全局作用域
		ServletContext application = this.getServletContext();
		Settings settings = (Settings) application.getAttribute("WEB_SET");
		
		if (settings == null) {
			settings = new Settings("华为商城(Vmall.com)_华为手机、荣耀手机、官网正品保障_基因重组科技", 
					"免费商城系统,网上商城系统,多用户商城系统,分销商城系统,微信商城系统,商城源码,手机移动商城系统,b2b2c商城系统,网店系统,购物系统",
					"网上商城系统具备电商零售业务所需的所有基本功能，以其安全稳定、简单易用、高效专业等优势赢得了用户的广泛好评，为用户提供了一个低成本、高效率的网上商城建设方案。", 
					"1551282386", 
					"88888888", 
					"基因重组科技所有",
					"鲁ICP备17015644号-2",
					"B2C商城系统", 
					"<script type='text/javascript' src='https://s22.cnzz.com/z_stat.php?id=1261989361&web_id=1261989361'></script>");
			
			
		}
		application.setAttribute("WEB_SET", settings);
		String webSetJson = JSONArray.fromObject(settings).toString();
		out.println(webSetJson);
		
	}

	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void adminLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		Admin admin = adminBiz.loginAdmin(name, password);
		if (admin !=  null) {
			//存入session
			HttpSession session = request.getSession();
			
			session.setAttribute("ADMIN", admin);
			out.println(1);
		}else{
			out.println(0);
		}
		
		
		
		
	}

}
