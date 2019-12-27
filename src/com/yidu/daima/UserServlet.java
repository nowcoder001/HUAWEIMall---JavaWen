package com.yidu.daima;


/**
 * 数据访问层
 * @author 小谷
 * @time 2019/11/20
 * @version 1.0
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

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
		
		//ajax响应输出
		PrintWriter out = response.getWriter();
		
		//获取参数password
		String password = request.getParameter("password");
		
		//获取参数user_iphone
		String user_iphone = request.getParameter("user_iphone");
		
		//调用md5加密方法
		String numbers = biz.getMD5Str(password);
		
		//调用修改密码方法
		int a = biz.updatePassword(numbers,user_iphone);
		
		if(a>0){//修改成功
			out.println("修改成功");
		}else{//修改失败
			out.println("修改失败");
		}
		
		
		
	}
	

	/**
	 * 修改用户信息
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void updateUserMassage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		

		//ajax响应输出
		PrintWriter out = response.getWriter();
		
		//获取参数Massage
		String massage = request.getParameter("Massage");
		
		
		if(massage.equals("user_name")){//如果等于用户名
			
			//获取参数names
			String user_name = request.getParameter("names");
			
			//获取参数user_id
			String id = request.getParameter("user_id");
			
			//转型
			int user_id = Integer.parseInt(id);
			
			//调用修改用户名的方法
			int a = biz.updateUserMassage(massage,user_name,user_id);
			
			//获取Session对象
			HttpSession sesson =  request.getSession();
			//获取用户信息
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			//设置修改的用户名
			user.setUser_name(user_name);
			//保存到作用域
			sesson.setAttribute("LOGIN_USER", user);
			if(a>0){//修改成功
				out.println("修改成功");
			}else{//修改失败
				out.println("修改失败");
			}
					
					
		}else if(massage.equals("phone")){//如果等于手机
			
			//获取参数值user_phone
			String user_phone = request.getParameter("user_phone");
			//获取参数值user_id
			String id = request.getParameter("user_id");
			
			//转型
			int user_id = Integer.parseInt(id);
			
			//调用修改手机的方法
			int a = biz.updateUserMassage(massage,user_phone,user_id);
			
			//获取Session对象
			HttpSession sesson =  request.getSession();
			//获取用户信息
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			//设置修改的手机
			user.setPhone(user_phone);
			//保存到作用域
			sesson.setAttribute("LOGIN_USER", user);
			if(a>0){//修改成功
				out.println("修改成功");
			}else{//修改失败
				out.println("修改失败");
			}
			
			
			
			
		}else if(massage.equals("email")){//如果等于邮箱
			
			//获取参数emails
			String user_email = request.getParameter("emails");
			//获取参数user_id
			String id = request.getParameter("user_id");
			//转型		
			int user_id = Integer.parseInt(id);
			
			//调用修改邮箱的方法
			int a = biz.updateUserMassage(massage,user_email,user_id);
			
			//获取Session对象
			HttpSession sesson =  request.getSession();
			
			//获取用户信息
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			//设置修改的手机
			user.setEmail(user_email);
			//保存到作用域LOGIN_USER
			sesson.setAttribute("LOGIN_USER", user);
			
			if(a>0){//修改成功
				out.println("修改成功");
			}else{//修改失败
				out.println("修改失败");
			}
			
			
			
			
		}else if(massage.equals("state")){//如果等于地区
			
			//获取参数city
			String user_city = request.getParameter("city");
			//获取参数user_id
			String id = request.getParameter("user_id");
			
			//转型		
			int user_id = Integer.parseInt(id);
			
			//调用修改
			int a = biz.updateUserMassage(massage,user_city,user_id);
			
			//获取Session对象
			HttpSession sesson =  request.getSession();
			
			//获取用户信息
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			//设置修改的地区
			user.setEmail(user_city);
			//保存到作用域
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
	 * @param request 请求
	 * @param response 响应
	 */
	private void getOrderProduct(HttpServletRequest request,
			HttpServletResponse response) {
		//获取session对象
		HttpSession session = request.getSession();
		//获取用户信息		
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {//当用户信息为空时（未登陆时）
			try {
				//跳转到登录界面
				
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//根据id获取订单实体类集合
		List<MallOrder> orders = orderBiz.getOrderByUserId(user.getId(),1);
			int noPay = 0;  //未支付
			int yesPay = 0;  //已支付
			int comment = 0;  //待评价
				//循环MallOrder
			for (MallOrder mallOrder : orders) {
				
				if (mallOrder.getStatus() == 10) {//订单状态等于10时s
					noPay++;//未支付+
				}else if (mallOrder.getStatus() == 20) {//订单状态等于20时s
					yesPay++;//已支付+
				}else if (mallOrder.getStatus() == 30) {//订单状态等于30时s
					comment++;//待评价+
				}
				
			}
			//保存到作用域
			request.setAttribute("ORDER_LIST", orders);//集合
			request.setAttribute("ORDER_SIZE", orders.size());//集合数量
			request.setAttribute("NO_PAY", noPay);//未支付状态
			request.setAttribute("YES_PAY", yesPay);//已支付状态
			request.setAttribute("COMMENT", comment);//待评价
			
			try {
				//跳转到会员中心界面
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
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void menberGetInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取session对象
		HttpSession session = request.getSession();
		//获取用户信息
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		
		if (user == null) {//如果等于空
			
			//响应输出
			out.println("1");
			
		}else{
			//转换成json格式
			String userJson = JSONArray.fromObject(user).toString();
			//响应输出
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
				
				//获取请求中参数的值UserPhone
				String phone = request.getParameter("UserPhone");
		
				if(phone == null || phone == ""){//如果值为空
					
					//ajax响应输出
					
					PrintWriter out = response.getWriter();
					
					
					//接收分页的页码和条目数(转型int)
					int page = Integer.parseInt(request.getParameter("page"));
					int rows = Integer.parseInt(request.getParameter("rows"));
					
					
					//所有数据的集合
					List<MallUser> mallUsers = biz.SelectUserMassage();
					
					//分页集合
					List<MallUser> manag = biz.getPaging(rows, page);
					
					//将分页集合转换成json格式
					String user = JSONArray.fromObject(manag).toString();
					
					//拼接DataGrid网格需要的json数据格式
					String userJson = "{\"total\":"+mallUsers.size()+",\"rows\":"+user+"}";
					//响应回客户端
								
					out.write(userJson);
					out.close();
					
				}else{
					
					//ajax响应输出
					PrintWriter out = response.getWriter();
					
					//根据手机号获取所有数据集合
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
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 
	 */
	private void DeleteUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//获取请求中的参数值detId
		String advIdStr = request.getParameter("detId");
		//转型
		int id = Integer.parseInt(advIdStr);
		//调用删除的方法
		int count = biz.deleteUser(id);
		//响应
		PrintWriter out = response.getWriter();
		if (count > 0 ) {//删除成功
			out.println("删除成功");
		}else{//删除
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
		
		//ajax响应输出
		PrintWriter out = response.getWriter();
		//获取请求中参数值id
		String UserId = request.getParameter("id");
		//转型
		int Id = Integer.parseInt(UserId);
		//获取所有数据根据id
		MallUser mallUser = biz.getupdateUser(Id); 
		//转成json格式
		String cityJson = JSONArray.fromObject(mallUser).toString();
		//输出
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
		//获取请求中参数id
		String id = request.getParameter("id");
		
		//转型
		int userid  = Integer.parseInt(id);
		
		//获取请求中各种参数
		String name = request.getParameter("name");//获取name
		String pass = request.getParameter("pass");//获取pass
		String ihone = request.getParameter("ihones");//获取ihones
		String email = request.getParameter("emails");//获取emails
		String date = request.getParameter("describes");//获取describes
		String nationalitys = request.getParameter("nationalitys");//获取nationalitys
		String images = request.getParameter("images");//images
		
		
		//将参数保存到用户对象中
		MallUser mallUser = new MallUser(userid, name, pass, email, ihone, nationalitys, date,images);
	
		//调用修改的方法
		int count = biz.updateUser(mallUser);
		
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
				
		//ajax响应输出
		PrintWriter out = response.getWriter();
		
		//获取请求参数的值UserPhone
		String phone = request.getParameter("UserPhone");
		
		//根据手机号获取所有信息保存到集合中
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
			//跳转到主页面
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
		//ajax响应输出
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
			//转换成json格式
			String userJson = JSONArray.fromObject(user).toString();
			//输出
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
		String pass = request.getParameter("formBeanpassword");//获取formBeanpassword
		
		String phone = request.getParameter("formBeanusername");//获取formBeanusername
	
		String state = request.getParameter("citys");//获取citys
		
		String date = request.getParameter("datatimes");//获取datatimes
				
		//MD5加密方法
		String numbers = biz.getMD5Str(pass);
		
		//生成随机的用户名
		String userNames = biz.getStringRandom(10);
		
		//创建用户对象
		MallUser user = new MallUser(0,userNames,numbers,null, phone, state, date,null);
		
		//调用逻辑业务层
		int a = biz.UserRegister(user);
		
		//ajax响应
		PrintWriter out = response.getWriter();
		if(a>0){ //注册成功
			//输出
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
		String iphone = request.getParameter("userAccount");//获取userAccount
		String pass = request.getParameter("password");//获取password
		
		//调用随机生成用户名的方法
		String numbers = biz.getMD5Str(pass);
		
		//调用逻辑业务层
		MallUser user = biz.Usermass(iphone,numbers);
		
		//ajax响应
		PrintWriter out = response.getWriter();
		if (user != null) {  //登录成功(非等于空)
			//根据id查询此用户是否存在购物车
			String orderId = orderBiz.getCartCount(user.getId());
			//如果小于既说明没有购物车
			if (orderId == "" || orderId == null) {//等于空时
				//创建购物车实体类对象
				MallOrder order = new MallOrder();
				order.setOrderNo("88888");//设置购物车订单号
				//订单状态为一百为购物车
				order.setStatus(100);
				//创建收货地址实体类对象
				MallShipping shipping = new MallShipping();
				//将收货地址id设为0
				shipping.setId(0);
				//保存到购物车
				order.setShipping(shipping);
				//调用创建订单的方法
				orderBiz.createOrder(user.getId(), order);
			}
			//获取session对象
			HttpSession session = request.getSession();
			//保存到作用域
			session.setAttribute("LOGIN_USER", user);
			//输出
			out.println(1);
			
		}else{   //登录失败
			out.println(2);
		}
		
	}

}
