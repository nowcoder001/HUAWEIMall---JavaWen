package com.yidu.daima;


/**
 * ���ݷ��ʲ�
 * @author С��
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

	//����ҵ���߼������
	UserBiz biz = new UserBiz();
	//����ҵ���߼���
	OrderBiz orderBiz = new OrderBiz();
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
		
		//ajax��Ӧ���
		PrintWriter out = response.getWriter();
		
		//��ȡ����password
		String password = request.getParameter("password");
		
		//��ȡ����user_iphone
		String user_iphone = request.getParameter("user_iphone");
		
		//����md5���ܷ���
		String numbers = biz.getMD5Str(password);
		
		//�����޸����뷽��
		int a = biz.updatePassword(numbers,user_iphone);
		
		if(a>0){//�޸ĳɹ�
			out.println("�޸ĳɹ�");
		}else{//�޸�ʧ��
			out.println("�޸�ʧ��");
		}
		
		
		
	}
	

	/**
	 * �޸��û���Ϣ
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void updateUserMassage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		

		//ajax��Ӧ���
		PrintWriter out = response.getWriter();
		
		//��ȡ����Massage
		String massage = request.getParameter("Massage");
		
		
		if(massage.equals("user_name")){//��������û���
			
			//��ȡ����names
			String user_name = request.getParameter("names");
			
			//��ȡ����user_id
			String id = request.getParameter("user_id");
			
			//ת��
			int user_id = Integer.parseInt(id);
			
			//�����޸��û����ķ���
			int a = biz.updateUserMassage(massage,user_name,user_id);
			
			//��ȡSession����
			HttpSession sesson =  request.getSession();
			//��ȡ�û���Ϣ
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			//�����޸ĵ��û���
			user.setUser_name(user_name);
			//���浽������
			sesson.setAttribute("LOGIN_USER", user);
			if(a>0){//�޸ĳɹ�
				out.println("�޸ĳɹ�");
			}else{//�޸�ʧ��
				out.println("�޸�ʧ��");
			}
					
					
		}else if(massage.equals("phone")){//��������ֻ�
			
			//��ȡ����ֵuser_phone
			String user_phone = request.getParameter("user_phone");
			//��ȡ����ֵuser_id
			String id = request.getParameter("user_id");
			
			//ת��
			int user_id = Integer.parseInt(id);
			
			//�����޸��ֻ��ķ���
			int a = biz.updateUserMassage(massage,user_phone,user_id);
			
			//��ȡSession����
			HttpSession sesson =  request.getSession();
			//��ȡ�û���Ϣ
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			//�����޸ĵ��ֻ�
			user.setPhone(user_phone);
			//���浽������
			sesson.setAttribute("LOGIN_USER", user);
			if(a>0){//�޸ĳɹ�
				out.println("�޸ĳɹ�");
			}else{//�޸�ʧ��
				out.println("�޸�ʧ��");
			}
			
			
			
			
		}else if(massage.equals("email")){//�����������
			
			//��ȡ����emails
			String user_email = request.getParameter("emails");
			//��ȡ����user_id
			String id = request.getParameter("user_id");
			//ת��		
			int user_id = Integer.parseInt(id);
			
			//�����޸�����ķ���
			int a = biz.updateUserMassage(massage,user_email,user_id);
			
			//��ȡSession����
			HttpSession sesson =  request.getSession();
			
			//��ȡ�û���Ϣ
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			//�����޸ĵ��ֻ�
			user.setEmail(user_email);
			//���浽������LOGIN_USER
			sesson.setAttribute("LOGIN_USER", user);
			
			if(a>0){//�޸ĳɹ�
				out.println("�޸ĳɹ�");
			}else{//�޸�ʧ��
				out.println("�޸�ʧ��");
			}
			
			
			
			
		}else if(massage.equals("state")){//������ڵ���
			
			//��ȡ����city
			String user_city = request.getParameter("city");
			//��ȡ����user_id
			String id = request.getParameter("user_id");
			
			//ת��		
			int user_id = Integer.parseInt(id);
			
			//�����޸�
			int a = biz.updateUserMassage(massage,user_city,user_id);
			
			//��ȡSession����
			HttpSession sesson =  request.getSession();
			
			//��ȡ�û���Ϣ
			MallUser user =  (MallUser) sesson.getAttribute("LOGIN_USER");
			//�����޸ĵĵ���
			user.setEmail(user_city);
			//���浽������
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
	 * @param request ����
	 * @param response ��Ӧ
	 */
	private void getOrderProduct(HttpServletRequest request,
			HttpServletResponse response) {
		//��ȡsession����
		HttpSession session = request.getSession();
		//��ȡ�û���Ϣ		
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {//���û���ϢΪ��ʱ��δ��½ʱ��
			try {
				//��ת����¼����
				
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//����id��ȡ����ʵ���༯��
		List<MallOrder> orders = orderBiz.getOrderByUserId(user.getId(),1);
			int noPay = 0;  //δ֧��
			int yesPay = 0;  //��֧��
			int comment = 0;  //������
				//ѭ��MallOrder
			for (MallOrder mallOrder : orders) {
				
				if (mallOrder.getStatus() == 10) {//����״̬����10ʱs
					noPay++;//δ֧��+
				}else if (mallOrder.getStatus() == 20) {//����״̬����20ʱs
					yesPay++;//��֧��+
				}else if (mallOrder.getStatus() == 30) {//����״̬����30ʱs
					comment++;//������+
				}
				
			}
			//���浽������
			request.setAttribute("ORDER_LIST", orders);//����
			request.setAttribute("ORDER_SIZE", orders.size());//��������
			request.setAttribute("NO_PAY", noPay);//δ֧��״̬
			request.setAttribute("YES_PAY", yesPay);//��֧��״̬
			request.setAttribute("COMMENT", comment);//������
			
			try {
				//��ת����Ա���Ľ���
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
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void menberGetInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡsession����
		HttpSession session = request.getSession();
		//��ȡ�û���Ϣ
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		
		if (user == null) {//������ڿ�
			
			//��Ӧ���
			out.println("1");
			
		}else{
			//ת����json��ʽ
			String userJson = JSONArray.fromObject(user).toString();
			//��Ӧ���
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
				
				//��ȡ�����в�����ֵUserPhone
				String phone = request.getParameter("UserPhone");
		
				if(phone == null || phone == ""){//���ֵΪ��
					
					//ajax��Ӧ���
					
					PrintWriter out = response.getWriter();
					
					
					//���շ�ҳ��ҳ�����Ŀ��(ת��int)
					int page = Integer.parseInt(request.getParameter("page"));
					int rows = Integer.parseInt(request.getParameter("rows"));
					
					
					//�������ݵļ���
					List<MallUser> mallUsers = biz.SelectUserMassage();
					
					//��ҳ����
					List<MallUser> manag = biz.getPaging(rows, page);
					
					//����ҳ����ת����json��ʽ
					String user = JSONArray.fromObject(manag).toString();
					
					//ƴ��DataGrid������Ҫ��json���ݸ�ʽ
					String userJson = "{\"total\":"+mallUsers.size()+",\"rows\":"+user+"}";
					//��Ӧ�ؿͻ���
								
					out.write(userJson);
					out.close();
					
				}else{
					
					//ajax��Ӧ���
					PrintWriter out = response.getWriter();
					
					//�����ֻ��Ż�ȡ�������ݼ���
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
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException 
	 */
	private void DeleteUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//��ȡ�����еĲ���ֵdetId
		String advIdStr = request.getParameter("detId");
		//ת��
		int id = Integer.parseInt(advIdStr);
		//����ɾ���ķ���
		int count = biz.deleteUser(id);
		//��Ӧ
		PrintWriter out = response.getWriter();
		if (count > 0 ) {//ɾ���ɹ�
			out.println("ɾ���ɹ�");
		}else{//ɾ��
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
		
		//ajax��Ӧ���
		PrintWriter out = response.getWriter();
		//��ȡ�����в���ֵid
		String UserId = request.getParameter("id");
		//ת��
		int Id = Integer.parseInt(UserId);
		//��ȡ�������ݸ���id
		MallUser mallUser = biz.getupdateUser(Id); 
		//ת��json��ʽ
		String cityJson = JSONArray.fromObject(mallUser).toString();
		//���
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
		//��ȡ�����в���id
		String id = request.getParameter("id");
		
		//ת��
		int userid  = Integer.parseInt(id);
		
		//��ȡ�����и��ֲ���
		String name = request.getParameter("name");//��ȡname
		String pass = request.getParameter("pass");//��ȡpass
		String ihone = request.getParameter("ihones");//��ȡihones
		String email = request.getParameter("emails");//��ȡemails
		String date = request.getParameter("describes");//��ȡdescribes
		String nationalitys = request.getParameter("nationalitys");//��ȡnationalitys
		String images = request.getParameter("images");//images
		
		
		//���������浽�û�������
		MallUser mallUser = new MallUser(userid, name, pass, email, ihone, nationalitys, date,images);
	
		//�����޸ĵķ���
		int count = biz.updateUser(mallUser);
		
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
				
		//ajax��Ӧ���
		PrintWriter out = response.getWriter();
		
		//��ȡ���������ֵUserPhone
		String phone = request.getParameter("UserPhone");
		
		//�����ֻ��Ż�ȡ������Ϣ���浽������
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
			//��ת����ҳ��
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
		//ajax��Ӧ���
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
			//ת����json��ʽ
			String userJson = JSONArray.fromObject(user).toString();
			//���
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
		String pass = request.getParameter("formBeanpassword");//��ȡformBeanpassword
		
		String phone = request.getParameter("formBeanusername");//��ȡformBeanusername
	
		String state = request.getParameter("citys");//��ȡcitys
		
		String date = request.getParameter("datatimes");//��ȡdatatimes
				
		//MD5���ܷ���
		String numbers = biz.getMD5Str(pass);
		
		//����������û���
		String userNames = biz.getStringRandom(10);
		
		//�����û�����
		MallUser user = new MallUser(0,userNames,numbers,null, phone, state, date,null);
		
		//�����߼�ҵ���
		int a = biz.UserRegister(user);
		
		//ajax��Ӧ
		PrintWriter out = response.getWriter();
		if(a>0){ //ע��ɹ�
			//���
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
		String iphone = request.getParameter("userAccount");//��ȡuserAccount
		String pass = request.getParameter("password");//��ȡpassword
		
		//������������û����ķ���
		String numbers = biz.getMD5Str(pass);
		
		//�����߼�ҵ���
		MallUser user = biz.Usermass(iphone,numbers);
		
		//ajax��Ӧ
		PrintWriter out = response.getWriter();
		if (user != null) {  //��¼�ɹ�(�ǵ��ڿ�)
			//����id��ѯ���û��Ƿ���ڹ��ﳵ
			String orderId = orderBiz.getCartCount(user.getId());
			//���С�ڼ�˵��û�й��ﳵ
			if (orderId == "" || orderId == null) {//���ڿ�ʱ
				//�������ﳵʵ�������
				MallOrder order = new MallOrder();
				order.setOrderNo("88888");//���ù��ﳵ������
				//����״̬Ϊһ��Ϊ���ﳵ
				order.setStatus(100);
				//�����ջ���ַʵ�������
				MallShipping shipping = new MallShipping();
				//���ջ���ַid��Ϊ0
				shipping.setId(0);
				//���浽���ﳵ
				order.setShipping(shipping);
				//���ô��������ķ���
				orderBiz.createOrder(user.getId(), order);
			}
			//��ȡsession����
			HttpSession session = request.getSession();
			//���浽������
			session.setAttribute("LOGIN_USER", user);
			//���
			out.println(1);
			
		}else{   //��¼ʧ��
			out.println(2);
		}
		
	}

}
