package com.yidu.mall.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.yidu.mall.admin.model.Admin;
import com.yidu.mall.express.biz.ShippingBiz;
import com.yidu.mall.express.model.MallShipping;
import com.yidu.mall.order.biz.AdminOrderBiz;
import com.yidu.mall.order.biz.OrderBiz;
import com.yidu.mall.order.dao.OrderDao;
import com.yidu.mall.order.model.MallOrder;
import com.yidu.mall.order.model.MallOrderItem;
/**
 * ��̨����servlet
 * @author С��ħ
 *
 */
public class AdminOrderServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	AdminOrderBiz adminOrderBiz = new AdminOrderBiz();
	ShippingBiz shippingBiz = new ShippingBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			String mod = request.getParameter("mod");
			
			if ("getAllOrder".equals(mod)) {   //��ȡȫ��������Ϣ
				getAllOrder(request,response);
			}else if ("getOrderItemByOrderId".equals(mod)) {   //���ݶ���id��ȡ������ϸ��Ʒ
				getOrderItemByOrderId(request,response);
			}else if ("updateOrderStatus".equals(mod)) {    //�޸Ķ���״̬
				updateOrderStatus(request,response);
			}else if ("userIdGetAddress".equals(mod)) {   //�����û�id��ȡ�ջ���ַ
				userIdGetAddress(request,response);
			}else if ("updateAddress".equals(mod)) {   //�޸Ķ������ջ���ַ
				updateAddress(request,response);
			}else if ("updateOrderPrice".equals(mod)) {   //�޸Ķ�����ʵ����۸�
				updateOrderPrice(request,response);
			}else if ("deleteOrder".equals(mod)) {   //����idɾ��������
				deleteOrder(request,response);
			}else if ("moneylog".equals(mod)) {   //�ʽ���ϸ
				moneylog(request,response);
			}
			
		
		
	}
	/**
	 * �ʽ���ϸ
	 * @param request  ����
	 * @param response   ��Ӧ
	 * @throws IOException 
	 */
	private void moneylog(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		List<MallOrder> orders = adminOrderBiz.getOrderMoneylog();
		
		
		String orderJson = JSONArray.fromObject(orders).toString();
		
		out.println(orderJson);
		
		out.close();
	}
	/**
	 * ����idɾ��������
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		
		int count = adminOrderBiz.deleteOrder(orderId);
		if (count > 0) {
			out.println("ɾ�������ɹ���");
		}else{
			out.println("ɾ������ʧ�ܣ�");
		}
	}
	/**
	 * �޸Ķ�����ʵ����۸�
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateOrderPrice(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		double price = Double.parseDouble(request.getParameter("price"));
		int count = adminOrderBiz.updateOrderPrice(orderId, price);
		
		if (count > 0) {
			out.println("�޸ļ۸�ɹ�");
		}else{
			out.println("�޸ļ۸�ʧ��");
		}
		
	}
	/**
	 * �޸Ķ������ջ���ַ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		int shippingId = Integer.parseInt(request.getParameter("shipping_id"));
		String orderId = request.getParameter("order_id");
		
		int count = adminOrderBiz.updateOrderById(shippingId, orderId);
		
		if (count > 0) {
			out.println("�޸ĵ�ַ�ɹ���");
		}else{
			out.println("�޸ĵ�ַʧ�ܣ�");
		}
		
	}
	/**
	 * �����û�id��ȡ�ջ���ַ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void userIdGetAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���
		int userId = Integer.parseInt(request.getParameter("user_id"));
		
		List<MallShipping> shippings = shippingBiz.getShippingByUserId(userId);
		
		String addressJson = JSONArray.fromObject(shippings).toString();
		out.println(addressJson);
	}
	/**
	 * �޸Ķ���״̬
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateOrderStatus(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		int status = Integer.parseInt(request.getParameter("status"));
		
		int count = adminOrderBiz.updateOrderAddress(orderId, status);
		
		out.println(count);
		
	}
	/**
	 * ���ݶ���id��ȡ������ϸ��Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getOrderItemByOrderId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		
		List<MallOrderItem> orderItems = adminOrderBiz.getChildProduct(orderId);
		
		String itemJson = JSONArray.fromObject(orderItems).toString();
		out.println(itemJson);
	}
	/**
	 * ��ȡȫ��������Ϣ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		int listCount = adminOrderBiz.getAllOrdersNoRedo().size();  //��������
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		List<MallOrder> orders = adminOrderBiz.getAllOrders(page,rows);
		
		//ת��json'��ʽ
		String orderJson = JSONArray.fromObject(orders).toString();
		//ƴ�ӷ�ҳ��Ҫ��ʽ
		orderJson = "{\"total\":"+listCount+",\"rows\":"+orderJson+"}";
		out.println(orderJson);
		
	}

}
