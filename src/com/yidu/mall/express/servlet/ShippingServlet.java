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
 * �ջ���ַservlet
 * @author С��ħ
 *
 */
public class ShippingServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	//����ҵ���߼������
	ShippingBiz shippingBiz = new ShippingBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//��ȡ����Ĳ���
		String mod = request.getParameter("mod");
		
		if ("getShippingByUserId".equals(mod)) {   //��ȡ�û����ջ���ַ
			getShippingByUserId(request,response);
		}else if ("addAddress".equals(mod)) {   //�����û��ջ���ַ
			addAddress(request,response);
		}else if ("selectDefault".equals(mod)) {  //��ѯ�û��Ƿ���Ĭ�ϵ��ջ���ַ
			selectDefault(request,response);
		}else if ("getShippingById".equals(mod)) {   //����id��ȡ�ջ���ַ
			getShippingById(request,response);
		}else if ("deleteAddress".equals(mod)) {     //����idɾ���ջ���ַ
			deleteAddress(request,response);
		}else if ("updateAddress".equals(mod)) {   //����id�޸��ջ���ַ
			updateAddress(request,response);
		}
		
	}
	/**
	 * ����id�޸��ջ���ַ
	 * @param request
	 * @param response
	 */
	private void updateAddress(HttpServletRequest request,
			HttpServletResponse response) {
		//��session�л�ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		
		//��ȡ�����еĲ���ֵ
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
	 * ����idɾ���ջ���ַ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ����Ĳ���ֵ
		int shippingId = Integer.parseInt(request.getParameter("id"));
		
		int count = shippingBiz.deleteShipping(shippingId);
		
		if (count > 0) {
			out.println(1);
		}else{
			out.println(0);
		}
	}
	/**
	 * ����id��ȡ�ջ���ַ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getShippingById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		int shippingId = Integer.parseInt(request.getParameter("id"));
		
		MallShipping shipping = shippingBiz.getShippingById(shippingId);
		
		String shpJson = JSONArray.fromObject(shipping).toString();
		
		out.println(shpJson);
		
	}
	/**
	 * ��ѯ�û��Ƿ���Ĭ�ϵ��ջ���ַ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void selectDefault(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		//��session�л�ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		
		int count = shippingBiz.selectDefault(user.getId());
		int count2 = 0;
		if (count > 0) {
			//��ȡ�����еĲ���ֵ
			int shippingId = Integer.parseInt(request.getParameter("shpId"));
			count2 = shippingBiz.selectMyDefault(shippingId);
		}else{
			out.println(count);
		}
		//���Ĭ�ϵ�ַ���Ǵ˵�ַ�Ļ��ȿ�������ѡ��Ĭ�ϵ�ַ
		if (count2 > 0) {
			out.println(0);
		}else{
			out.println(1);
		}
		
	}
	/**
	 * �����û��ջ���ַ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addAddress(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//����������ж������ջ���ַ����֧�������½��Ļ������ջ������½���
		String payAdd = request.getParameter("payAdd");
		
		//��session�л�ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//��ȡ�����еĲ���ֵ
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
	 * ��ȡ�û����ջ���ַ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getShippingByUserId(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		//��session�л�ȡ�ջ���ַ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		
		//�ж�user�Ƿ�Ϊ��
		if (user == null) {
			response.sendRedirect("login.jsp");
		}else{
			
			List<MallShipping> shippings = shippingBiz.getShippingByUserId(user.getId());
			
			request.setAttribute("SHIPPING_LIST", shippings);
			
			request.getRequestDispatcher("shippingaddress.jsp").forward(request, response);
		}
		
	}

}
