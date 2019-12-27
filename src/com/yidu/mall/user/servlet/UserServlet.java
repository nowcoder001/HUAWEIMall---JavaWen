package com.yidu.mall.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.yidu.mall.coupon.biz.CouponBiz;
import com.yidu.mall.order.biz.OrderBiz;
import com.yidu.mall.order.model.MallOrder;
import com.yidu.mall.order.model.MallShipping;
import com.yidu.mall.user.biz.UserBiz;
import com.yidu.mall.user.model.MallUser;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		doPost(request, response);
	}

								//创建业务逻辑层对象
								UserBiz biz = new UserBiz();
	//订单业务逻辑层
	OrderBiz orderBiz = new OrderBiz();
	//优惠券业务逻辑层
	CouponBiz couponBiz = new CouponBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		
		if ("Abuttons".equals(method)) {   //注册
			UserRegisters(request,response);
		}else if("logins".equals(method)){  //登录
			UserLogin(request,response);
		}else if ("indexUpdate".equals(method)) {  //登录成功后的改变首页信息
			indexUpdate(request,response);
		}else if ("logOut".equals(method)) {   //登出操作
			logOut(request,response);
		}else if("UserMassage".equals(method)){//查询所有用户信息
			getUserMassage(request,response);
		}else if("ajaxDelete".equals(method)){//删除用户
			DeleteUser(request,response);
		}else if("Update".equals(method)){//进入修改页面
			intoUpdateUser(request,response);
		}else if("userUpdate".equals(method)){//修改用户数据
			UpdateUser(request,response);
		}else if("userphone".equals(method)){  //根据手机号模糊查询
			GetUserMassageByphone(request,response);
		}else if ("menberGetInfo".equals(method)) {   //会员中心
			menberGetInfo(request,response);
		}else if ("getOrderProduct".equals(method)) {   //会员中心 - 获取用户订单商品
			getOrderProduct(request,response);
		}else if("userUpdatepassword".equals(method)){//修改密码
			updatePassword(request,response);
		}else if("userUpdateMassage".equals(method)){  //修改用户数据
			updateUserMassage(request,response);
		}
		
	}
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void updatePassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		String password = request.getParameter("password");
		
		String user_iphone = request.getParameter("user_iphone");
		
		String numbers = biz.getMD5Str(password);
		
		int a = biz.updatePassword(numbers,user_iphone);
		
		out.println(a);
		
		
		
	}
	

	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void updateUserMassage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		

		
		PrintWriter out = response.getWriter();
		
		String massage = request.getParameter("Massage");
		
		if(massage.equals("user_name")){
			
			
			String user_name = request.getParameter("names");
			
			String id = request.getParameter("user_id");
					
			int user_id = Integer.parseInt(id);
			
			int a = biz.updateUserMassage(massage,user_name,user_id);
			
			HttpSession sesson =  request.getSession();
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			user.setUser_name(user_name);
			sesson.setAttribute("LOGIN_USER", user);
			if(a>0){//修改成功
				out.println("修改成功");
			}else{//修改失败
				out.println("修改失败");
			}
					
					
		}else if(massage.equals("phone")){
			String user_phone = request.getParameter("user_phone");
			
			String id = request.getParameter("user_id");
					
			int user_id = Integer.parseInt(id);
			
			int a = biz.updateUserMassage(massage,user_phone,user_id);
			
			HttpSession sesson =  request.getSession();
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			user.setPhone(user_phone);
			sesson.setAttribute("LOGIN_USER", user);
			if(a>0){//修改成功
				out.println("修改成功");
			}else{//修改失败
				out.println("修改失败");
			}
			
			
			
			
		}else if(massage.equals("email")){
			
			String user_email = request.getParameter("emails");
			
			String id = request.getParameter("user_id");
					
			int user_id = Integer.parseInt(id);
			
			int a = biz.updateUserMassage(massage,user_email,user_id);
			
			HttpSession sesson =  request.getSession();
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			user.setEmail(user_email);
			sesson.setAttribute("LOGIN_USER", user);
			
			if(a>0){//修改成功
				out.println("修改成功");
			}else{//修改失败
				out.println("修改失败");
			}
			
			
			
			
		}else if(massage.equals("state")){
			
			String user_city = request.getParameter("city");
			
			String id = request.getParameter("user_id");
					
			int user_id = Integer.parseInt(id);
			
			int a = biz.updateUserMassage(massage,user_city,user_id);
			
			HttpSession sesson =  request.getSession();
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			user.setEmail(user_city);
			sesson.setAttribute("LOGIN_USER", user);
			
			if(a>0){//修改成功
				out.println("修改成功");
			}else{//修改失败
				out.println("修改失败");
			}
			
			
			
			
		}
		
	
		
		
	}
	/**
	 * 获取用户订单商品
	 * @param request
	 * @param response
	 */
	private void getOrderProduct(HttpServletRequest request,
			HttpServletResponse response) {
		//从session获取会员信息
		HttpSession session = request.getSession();
				
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {
			try {
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			
			List<MallOrder> orders = biz.getOrderByUserId(user.getId(), 1);
			int noPay = 0;  //未支付
			int yesPay = 0;  //已支付
			int comment = 0;  //待评价
			for (MallOrder mallOrder : orders) {
				
				if (mallOrder.getStatus() == 10) {
					noPay++;
				}else if (mallOrder.getStatus() == 20) {
					yesPay++;
				}else if (mallOrder.getStatus() == 30) {
					comment++;
				}
				
			}
			request.setAttribute("ORDER_LIST", orders);
			request.setAttribute("ORDER_SIZE", orders.size());
			request.setAttribute("NO_PAY", noPay);
			request.setAttribute("YES_PAY", yesPay);
			request.setAttribute("COMMENT", comment);
			
			try {
				request.getRequestDispatcher("member.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 会员中心
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void menberGetInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//从session获取会员信息
		HttpSession session = request.getSession();
		
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		
		if (user == null) {
			
			out.println("1");
			
		}else{
			String userJson = JSONArray.fromObject(user).toString();
			
			out.println(userJson);
		}
		
	}
	/**
	 * 获取所有用户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	
				
	private void getUserMassage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
				
		
				String phone = request.getParameter("UserPhone");
		
				if(phone == null || phone == ""){
					
					PrintWriter out = response.getWriter();
					
					
					//接收分页的页码和条目数
					int page = Integer.parseInt(request.getParameter("page"));
					int rows = Integer.parseInt(request.getParameter("rows"));
					
					
					//所有数据的集合
					List<MallUser> mallUsers = biz.SelectUserMassage();
					
					List<MallUser> manag = biz.getPaging(rows, page);
					
					//将集合转换成json格式
					String user = JSONArray.fromObject(manag).toString();
					
					//拼接DataGrid网格需要的json数据格式
					String userJson = "{\"total\":"+mallUsers.size()+",\"rows\":"+user+"}";
					//响应回客户端
					
					out.write(userJson);
					out.close();
					
				}else{
					
					PrintWriter out = response.getWriter();
					
					
					List<MallUser> mallUsers = biz.getUsermassageByphone(phone);
					
					//将集合转换成json格式
					String user = JSONArray.fromObject(mallUsers).toString();
					
					
					//响应回客户端
					
					out.write(user);
					out.close();
					
					
				}
		
			
		
		
	}
	
	/**
	 * ajax方式删除用户
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void DeleteUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//获取请求中参数
		//获取请求中的参数值
		String advIdStr = request.getParameter("detId");
	
		int id = Integer.parseInt(advIdStr);
		int count = biz.deleteUser(id);
		PrintWriter out = response.getWriter();
		if (count > 0 ) {
			out.println("删除成功");
		}else{
			out.println("删除失败");
		}
	}
	
	
	/**
	 * 进入修改页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void intoUpdateUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		//获取请求中参数值
		String UserId = request.getParameter("id");
		int Id = Integer.parseInt(UserId);
		MallUser mallUser = biz.getupdateUser(Id); 
		System.out.println(mallUser.getPhone());
		String cityJson = JSONArray.fromObject(mallUser).toString();
		out.write(cityJson);
		
		
		
	}
		
	/**
	 * 修改用户数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void UpdateUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		//获取请求中参数
		String id = request.getParameter("id");
		int userid  = Integer.parseInt(id);
		//获取标题
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String ihone = request.getParameter("ihones");
		String email = request.getParameter("emails");
		String date = request.getParameter("describes");
		String nationalitys = request.getParameter("nationalitys");
		String images = request.getParameter("images");
		
		
		
		MallUser mallUser = new MallUser(userid, name, pass, email, ihone, nationalitys, date,images);
		System.out.println(userid+name+pass+email+ihone+date+email+nationalitys+images);
		//修改
		int count = biz.updateUser(mallUser);
		System.out.println(count);
		if (count > 0) {
			out.println("修改成功");
		}else{
			out.println("修改失败");
		}
		
		
		
	}
	
	
	/**
	 * 根据手机查询用户信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void GetUserMassageByphone(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		
		PrintWriter out = response.getWriter();
		
		String phone = request.getParameter("UserPhone");
		
		List<MallUser> mallUsers = biz.getUsermassageByphone(phone);
		
		//将集合转换成json格式
		String user = JSONArray.fromObject(mallUsers).toString();
		
		//响应回客户端
		
		out.write(user);
		out.close();
		
	}
	/**
	 * 注销操作
	 * @param request
	 * @param response
	 */
	private void logOut(HttpServletRequest request, HttpServletResponse response) {
		
		//从session取出用户信息
		HttpSession session = request.getSession();
		//将session的值移除
		session.removeAttribute("LOGIN_USER");
		
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 登录成功后的改变首页信息
	 * @param request
	 * @param response
	 */
	private void indexUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//从session获取用户信息
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {  //如果用户等于空
			out.println("[{\"id\":\"0\"}]");
		}else{
			String userJson = JSONArray.fromObject(user).toString();
			out.println(userJson);
		}
		
		
	}


	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void UserRegisters(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//获取用户的各种信息参数
		String pass = request.getParameter("formBeanpassword");
		
		String phone = request.getParameter("formBeanusername");
	
		String state = request.getParameter("citys");
		
		String date = request.getParameter("datatimes");
		
		String checkPassword = request.getParameter("checkPassword");
		
		String numbers = biz.getMD5Str(pass);
		
		String userNames = biz.getStringRandom(10);
		
		//创建用户对象
		MallUser user = new MallUser(0,userNames,numbers,null, phone, state, date,null);
		
		//调用逻辑业务层
		int a = biz.UserRegister(user);
		
		
		PrintWriter out = response.getWriter();
		if(a>0){  //注册成功
			out.println(7);
		}
		
	}
	
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void UserLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//获取用户的各种信息参数
		String iphone = request.getParameter("userAccount");
		String pass = request.getParameter("password");
		
		String numbers = biz.getMD5Str(pass);
		
		//调用逻辑业务层
		MallUser user = biz.Usermass(iphone,numbers);
		
		PrintWriter out = response.getWriter();
		if (user != null) {  //登录成功
			//查询此用户是否存在购物车
			String orderId = orderBiz.getCartCount(user.getId());
			//如果小于既说明没有购物车
			if (orderId == "" || orderId == null) {
				//创建购物车
				MallOrder order = new MallOrder();
				order.setOrderNo("88888");  //购物车专属id
				order.setStatus(100);
				MallShipping shipping = new MallShipping();
				shipping.setId(0);
				order.setShipping(shipping);
				orderBiz.createOrder(user.getId(), order);
			}
			
			//查询用户是否已经送过注册大礼包(优惠券)
			int count = couponBiz.getCouponByUserIdAndName(user.getId(), "注册大礼包");
			//小于1既说明没有赠送注册大礼包
			if (count < 1 ) {
				//赠送注册大礼包
				couponBiz.insertCoupon(user.getId());
				
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("LOGIN_USER", user);
			
			out.println(1);
			
		}else{   //登录失败
			out.println(2);
		}
		
	}

}
