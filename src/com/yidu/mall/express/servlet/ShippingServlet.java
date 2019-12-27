package com.yidu.mall.express.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.yidu.mall.express.biz.ShippingBiz;
import com.yidu.mall.express.model.MallShipping;
import com.yidu.mall.user.model.MallUser;
/**
 * 收货地址servlet
 * @author 小恶魔
 *
 */
public class ShippingServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	//创建业务逻辑层对象
	ShippingBiz shippingBiz = new ShippingBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取请求的参数
		String mod = request.getParameter("mod");
		
		if ("getShippingByUserId".equals(mod)) {   //获取用户的收货地址
			getShippingByUserId(request,response);
		}else if ("addAddress".equals(mod)) {   //新增用户收货地址
			addAddress(request,response);
		}else if ("selectDefault".equals(mod)) {  //查询用户是否有默认的收货地址
			selectDefault(request,response);
		}else if ("getShippingById".equals(mod)) {   //根据id获取收货地址
			getShippingById(request,response);
		}else if ("deleteAddress".equals(mod)) {     //根据id删除收货地址
			deleteAddress(request,response);
		}else if ("updateAddress".equals(mod)) {   //根据id修改收货地址
			updateAddress(request,response);
		}
		
	}
	/**
	 * 根据id修改收货地址
	 * @param request
	 * @param response
	 */
	private void updateAddress(HttpServletRequest request,
			HttpServletResponse response) {
		//从session中获取用户信息
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		
		//获取请求中的参数值
		int shppingId = Integer.parseInt(request.getParameter("shpId"));
		String consignee = request.getParameter("consignee");
		String mobile = request.getParameter("mobile");
		String phone = request.getParameter("phone");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String county = request.getParameter("county");
		String address = request.getParameter("address");
		String zipCode = request.getParameter("zipCode");
		String defaultFlag = request.getParameter("defaultFlag");
		if (defaultFlag != null) {
			defaultFlag = "true";
		}else {
			defaultFlag = "false";
		}
		MallShipping shipping = new MallShipping(shppingId, defaultFlag, consignee, phone, mobile, province, city, county, address, zipCode, null, null);
		
		int count = shippingBiz.updateAddress(user.getId(), shipping);
		
		try {
			response.sendRedirect("shippingServlet?mod=getShippingByUserId");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 根据id删除收货地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求的参数值
		int shippingId = Integer.parseInt(request.getParameter("id"));
		
		int count = shippingBiz.deleteShipping(shippingId);
		
		if (count > 0) {
			out.println(1);
		}else{
			out.println(0);
		}
	}
	/**
	 * 根据id获取收货地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getShippingById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数值
		int shippingId = Integer.parseInt(request.getParameter("id"));
		
		MallShipping shipping = shippingBiz.getShippingById(shippingId);
		
		String shpJson = JSONArray.fromObject(shipping).toString();
		
		out.println(shpJson);
		
	}
	/**
	 * 查询用户是否有默认的收货地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void selectDefault(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		//从session中获取用户信息
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		
		int count = shippingBiz.selectDefault(user.getId());
		int count2 = 0;
		if (count > 0) {
			//获取请求中的参数值
			int shippingId = Integer.parseInt(request.getParameter("shpId"));
			count2 = shippingBiz.selectMyDefault(shippingId);
		}else{
			out.println(count);
		}
		//如果默认地址正是此地址的话既可以自由选择默认地址
		if (count2 > 0) {
			out.println(0);
		}else{
			out.println(1);
		}
		
	}
	/**
	 * 新增用户收货地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addAddress(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//这个参数是判断新增收货地址是在支付界面新建的还是在收货管理新建的
		String payAdd = request.getParameter("payAdd");
		
		//从session中获取用户信息
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//获取请求中的参数值
		String consignee = request.getParameter("consignee");
		String mobile = request.getParameter("mobile");
		String phone = request.getParameter("phone");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String county = request.getParameter("county");
		String address = request.getParameter("address");
		String zipCode = request.getParameter("zipCode");
		String defaultFlag = request.getParameter("defaultFlag");
		if (payAdd == null) {
			if (defaultFlag != null) {
				defaultFlag = "true";
			}else {
				defaultFlag = "false";
			}
		}
		
		MallShipping shipping = new MallShipping(0, defaultFlag, consignee, phone, mobile, province, city, county, address, zipCode, null, null);
		int count = shippingBiz.addAddress(shipping, user.getId());
		
		if (payAdd == null) {
			response.sendRedirect("shippingServlet?mod=getShippingByUserId");
		}else{
			out.println(count);
		}
		
		
		
	}

	/**
	 * 获取用户的收货地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getShippingByUserId(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		//从session中获取收货地址
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		
		//判断user是否为空
		if (user == null) {
			response.sendRedirect("login.jsp");
		}else{
			
			List<MallShipping> shippings = shippingBiz.getShippingByUserId(user.getId());
			
			request.setAttribute("SHIPPING_LIST", shippings);
			
			request.getRequestDispatcher("shippingaddress.jsp").forward(request, response);
		}
		
	}

}
