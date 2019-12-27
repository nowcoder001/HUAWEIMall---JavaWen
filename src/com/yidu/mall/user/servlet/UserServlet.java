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

								//����ҵ���߼������
								UserBiz biz = new UserBiz();
	//����ҵ���߼���
	OrderBiz orderBiz = new OrderBiz();
	//�Ż�ȯҵ���߼���
	CouponBiz couponBiz = new CouponBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		
		if ("Abuttons".equals(method)) {   //ע��
			UserRegisters(request,response);
		}else if("logins".equals(method)){  //��¼
			UserLogin(request,response);
		}else if ("indexUpdate".equals(method)) {  //��¼�ɹ���ĸı���ҳ��Ϣ
			indexUpdate(request,response);
		}else if ("logOut".equals(method)) {   //�ǳ�����
			logOut(request,response);
		}else if("UserMassage".equals(method)){//��ѯ�����û���Ϣ
			getUserMassage(request,response);
		}else if("ajaxDelete".equals(method)){//ɾ���û�
			DeleteUser(request,response);
		}else if("Update".equals(method)){//�����޸�ҳ��
			intoUpdateUser(request,response);
		}else if("userUpdate".equals(method)){//�޸��û�����
			UpdateUser(request,response);
		}else if("userphone".equals(method)){  //�����ֻ���ģ����ѯ
			GetUserMassageByphone(request,response);
		}else if ("menberGetInfo".equals(method)) {   //��Ա����
			menberGetInfo(request,response);
		}else if ("getOrderProduct".equals(method)) {   //��Ա���� - ��ȡ�û�������Ʒ
			getOrderProduct(request,response);
		}else if("userUpdatepassword".equals(method)){//�޸�����
			updatePassword(request,response);
		}else if("userUpdateMassage".equals(method)){  //�޸��û�����
			updateUserMassage(request,response);
		}
		
	}
	/**
	 * �޸�����
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
	 * �޸��û���Ϣ
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
			if(a>0){//�޸ĳɹ�
				out.println("�޸ĳɹ�");
			}else{//�޸�ʧ��
				out.println("�޸�ʧ��");
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
			if(a>0){//�޸ĳɹ�
				out.println("�޸ĳɹ�");
			}else{//�޸�ʧ��
				out.println("�޸�ʧ��");
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
			
			if(a>0){//�޸ĳɹ�
				out.println("�޸ĳɹ�");
			}else{//�޸�ʧ��
				out.println("�޸�ʧ��");
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
			
			if(a>0){//�޸ĳɹ�
				out.println("�޸ĳɹ�");
			}else{//�޸�ʧ��
				out.println("�޸�ʧ��");
			}
			
			
			
			
		}
		
	
		
		
	}
	/**
	 * ��ȡ�û�������Ʒ
	 * @param request
	 * @param response
	 */
	private void getOrderProduct(HttpServletRequest request,
			HttpServletResponse response) {
		//��session��ȡ��Ա��Ϣ
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
			int noPay = 0;  //δ֧��
			int yesPay = 0;  //��֧��
			int comment = 0;  //������
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
	 * ��Ա����
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void menberGetInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��session��ȡ��Ա��Ϣ
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
	 * ��ȡ�����û���Ϣ
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	
				
	private void getUserMassage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
				
		
				String phone = request.getParameter("UserPhone");
		
				if(phone == null || phone == ""){
					
					PrintWriter out = response.getWriter();
					
					
					//���շ�ҳ��ҳ�����Ŀ��
					int page = Integer.parseInt(request.getParameter("page"));
					int rows = Integer.parseInt(request.getParameter("rows"));
					
					
					//�������ݵļ���
					List<MallUser> mallUsers = biz.SelectUserMassage();
					
					List<MallUser> manag = biz.getPaging(rows, page);
					
					//������ת����json��ʽ
					String user = JSONArray.fromObject(manag).toString();
					
					//ƴ��DataGrid������Ҫ��json���ݸ�ʽ
					String userJson = "{\"total\":"+mallUsers.size()+",\"rows\":"+user+"}";
					//��Ӧ�ؿͻ���
					
					out.write(userJson);
					out.close();
					
				}else{
					
					PrintWriter out = response.getWriter();
					
					
					List<MallUser> mallUsers = biz.getUsermassageByphone(phone);
					
					//������ת����json��ʽ
					String user = JSONArray.fromObject(mallUsers).toString();
					
					
					//��Ӧ�ؿͻ���
					
					out.write(user);
					out.close();
					
					
				}
		
			
		
		
	}
	
	/**
	 * ajax��ʽɾ���û�
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void DeleteUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//��ȡ�����в���
		//��ȡ�����еĲ���ֵ
		String advIdStr = request.getParameter("detId");
	
		int id = Integer.parseInt(advIdStr);
		int count = biz.deleteUser(id);
		PrintWriter out = response.getWriter();
		if (count > 0 ) {
			out.println("ɾ���ɹ�");
		}else{
			out.println("ɾ��ʧ��");
		}
	}
	
	
	/**
	 * �����޸�ҳ��
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void intoUpdateUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		//��ȡ�����в���ֵ
		String UserId = request.getParameter("id");
		int Id = Integer.parseInt(UserId);
		MallUser mallUser = biz.getupdateUser(Id); 
		System.out.println(mallUser.getPhone());
		String cityJson = JSONArray.fromObject(mallUser).toString();
		out.write(cityJson);
		
		
		
	}
		
	/**
	 * �޸��û�����
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void UpdateUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		//��ȡ�����в���
		String id = request.getParameter("id");
		int userid  = Integer.parseInt(id);
		//��ȡ����
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String ihone = request.getParameter("ihones");
		String email = request.getParameter("emails");
		String date = request.getParameter("describes");
		String nationalitys = request.getParameter("nationalitys");
		String images = request.getParameter("images");
		
		
		
		MallUser mallUser = new MallUser(userid, name, pass, email, ihone, nationalitys, date,images);
		System.out.println(userid+name+pass+email+ihone+date+email+nationalitys+images);
		//�޸�
		int count = biz.updateUser(mallUser);
		System.out.println(count);
		if (count > 0) {
			out.println("�޸ĳɹ�");
		}else{
			out.println("�޸�ʧ��");
		}
		
		
		
	}
	
	
	/**
	 * �����ֻ���ѯ�û���Ϣ
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
		
		//������ת����json��ʽ
		String user = JSONArray.fromObject(mallUsers).toString();
		
		//��Ӧ�ؿͻ���
		
		out.write(user);
		out.close();
		
	}
	/**
	 * ע������
	 * @param request
	 * @param response
	 */
	private void logOut(HttpServletRequest request, HttpServletResponse response) {
		
		//��sessionȡ���û���Ϣ
		HttpSession session = request.getSession();
		//��session��ֵ�Ƴ�
		session.removeAttribute("LOGIN_USER");
		
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��¼�ɹ���ĸı���ҳ��Ϣ
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
		//��session��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {  //����û����ڿ�
			out.println("[{\"id\":\"0\"}]");
		}else{
			String userJson = JSONArray.fromObject(user).toString();
			out.println(userJson);
		}
		
		
	}


	/**
	 * ע��
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void UserRegisters(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//��ȡ�û��ĸ�����Ϣ����
		String pass = request.getParameter("formBeanpassword");
		
		String phone = request.getParameter("formBeanusername");
	
		String state = request.getParameter("citys");
		
		String date = request.getParameter("datatimes");
		
		String checkPassword = request.getParameter("checkPassword");
		
		String numbers = biz.getMD5Str(pass);
		
		String userNames = biz.getStringRandom(10);
		
		//�����û�����
		MallUser user = new MallUser(0,userNames,numbers,null, phone, state, date,null);
		
		//�����߼�ҵ���
		int a = biz.UserRegister(user);
		
		
		PrintWriter out = response.getWriter();
		if(a>0){  //ע��ɹ�
			out.println(7);
		}
		
	}
	
	
	/**
	 * ��¼
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void UserLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//��ȡ�û��ĸ�����Ϣ����
		String iphone = request.getParameter("userAccount");
		String pass = request.getParameter("password");
		
		String numbers = biz.getMD5Str(pass);
		
		//�����߼�ҵ���
		MallUser user = biz.Usermass(iphone,numbers);
		
		PrintWriter out = response.getWriter();
		if (user != null) {  //��¼�ɹ�
			//��ѯ���û��Ƿ���ڹ��ﳵ
			String orderId = orderBiz.getCartCount(user.getId());
			//���С�ڼ�˵��û�й��ﳵ
			if (orderId == "" || orderId == null) {
				//�������ﳵ
				MallOrder order = new MallOrder();
				order.setOrderNo("88888");  //���ﳵר��id
				order.setStatus(100);
				MallShipping shipping = new MallShipping();
				shipping.setId(0);
				order.setShipping(shipping);
				orderBiz.createOrder(user.getId(), order);
			}
			
			//��ѯ�û��Ƿ��Ѿ��͹�ע������(�Ż�ȯ)
			int count = couponBiz.getCouponByUserIdAndName(user.getId(), "ע������");
			//С��1��˵��û������ע������
			if (count < 1 ) {
				//����ע������
				couponBiz.insertCoupon(user.getId());
				
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("LOGIN_USER", user);
			
			out.println(1);
			
		}else{   //��¼ʧ��
			out.println(2);
		}
		
	}

}
