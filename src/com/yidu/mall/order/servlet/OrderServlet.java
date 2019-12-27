package com.yidu.mall.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.yidu.mall.coupon.biz.CouponBiz;
import com.yidu.mall.coupon.model.MallCoupon;
import com.yidu.mall.order.biz.OrderBiz;
import com.yidu.mall.order.biz.ShippingBiz;
import com.yidu.mall.order.model.MallOrder;
import com.yidu.mall.order.model.MallOrderItem;
import com.yidu.mall.order.model.MallShipping;
import com.yidu.mall.product.biz.ProductBiz;
import com.yidu.mall.product.model.MallProduct;
import com.yidu.mall.user.model.MallUser;

public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	//����ҵ���߼���
	OrderBiz orderBiz = new OrderBiz();
	ProductBiz productBiz = new ProductBiz();
	ShippingBiz shippingBiz = new ShippingBiz();
	CouponBiz couponBiz = new CouponBiz();  //�Żݾ�
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//��ȡ�����еĲ���ֵ
		String mod = request.getParameter("mod");
		
		if ("confirmOrder".equals(mod)) {  //�����µ��Ĳ���   ȷ�϶���
			confirmOrder(request,response);
		}else if ("htmlPayUpdateOrder".equals(mod)) {   //����֧������  �޸Ķ������ջ���ַ
			htmlPayUpdateOrder(request,response);
		}else if ("htmlPayButton".equals(mod)) {  //���֧����ť ����Ҫ����Ϣ����session
			htmlPayButton(request,response);
		}else if ("htmlMyOrder".equals(mod)) {  //���������ҵĶ�������ȡ������Ϣ
			htmlMyOrder(request,response);
		}else if ("flushConfirmOrder".equals(mod)) {   //�µ����� - ��ֹˢ�¶�����һ��������
			flushConfirmOrder(request,response);
		}else if ("cartOrder".equals(mod)) {   //���빺�ﳵ
			cartOrder(request,response);
		}else if ("getUserCart".equals(mod)) {   //��ȡ�û����ﳵ����Ʒ
			getUserCart(request,response);
		}else if ("cartConfirmOrder".equals(mod)) {   //���ﳵ�µ�����
			cartConfirmOrder(request,response);
		}else if ("orderDetail".equals(mod)) {   //��������ҳ
			orderDetail(request,response);
		}else if ("orderUpdate".equals(mod)) {    //�޸Ķ���ҳ
			orderUpdate(request,response);
		}else if ("orderUpdateConfirm".equals(mod)) {   //ȷ���޸Ķ������ջ���ַ
			orderUpdateConfirm(request,response);
		}else if ("deleteOrder".equals(mod)) {   //ȡ������
			deleteOrder(request,response);
		}else if ("deleteCartProduct".equals(mod)) {  //ɾ�����ﳵ��Ʒ
			deleteCartProduct(request,response);
		}else if ("ajaxGetUserCart".equals(mod)) {    //ajax��ȡ�û����ﳵ
			ajaxGetUserCart(request,response);
		}
		
	}
	/**
	 * ajax��ȡ�û����ﳵ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void ajaxGetUserCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��session���ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//�����еĲ���ֵ
		String cartFloat = request.getParameter("cartFloat");
		if (cartFloat != null) {
			
			if (user == null) {
				out.println(0);
			}else{
				List<MallOrderItem> orderItems = orderBiz.getOrderItemsByUserId(user.getId());  //��ȡ���ﳵ����Ʒ
				out.println(orderItems.size());
			}
			
		}else {
			
			if (user == null) {
				out.println("[{\"id\":0,\"o\":\"0\"}]");
			}else{
				List<MallOrderItem> orderItems = orderBiz.getOrderItemsByUserId(user.getId());  //��ȡ���ﳵ����Ʒ
				String cartJson = JSONArray.fromObject(orderItems).toString();
				out.println(cartJson);
			}
			
		}
		
		
		
	}
	/**
	 * ɾ�����ﳵ��Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteCartProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		int itemId = Integer.parseInt(request.getParameter("item_id"));
		
		int count = orderBiz.deleteItemOrder(itemId);
		
		
		out.println(count);
	}
	/**
	 * ȡ������
	 * @param request
	 * @param response
	 */
	private void deleteOrder(HttpServletRequest request,
			HttpServletResponse response) {
		
		//��ȡ������
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		//ɾ������
		int count = orderBiz.deleteOrder(orderId);
		
		if (count > 0) {
			try {
				response.sendRedirect("orderServlet?mod=htmlMyOrder&status=1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * ȷ���޸Ķ������ջ���ַ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void orderUpdateConfirm(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String orderId = request.getParameter("order_id");
		int shppingId = Integer.parseInt(request.getParameter("shipping_id"));
		//�޸Ķ���
		int count = orderBiz.updateOrderById(shppingId, orderId);
		out.println(count);
		
	}
	/**
	 * �޸Ķ���ҳ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void orderUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MallUser user = getUser(request);
		//��ȡ�����еĲ���ֵ
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		
		MallOrder order = orderBiz.getOrderInfoProduct(orderId);
		List<MallShipping> shippings = shippingBiz.getShippingById(user.getId());
		
		//��ȡȫ�����ջ���ַ
		//�ܼ�
		double total = 0;
		for (MallOrderItem item : order.getOrderItems()) {
			total = total+item.getTotalPrice();
		}
		request.setAttribute("SHP_ID", order.getShipping().getId());
		request.setAttribute("ORDER_LIST", order);
		request.setAttribute("TOTAL_PRICE", total);
		request.setAttribute("SHIPPINGS", shippings);
		
		request.getRequestDispatcher("order_update.jsp").forward(request, response);
	}
	/**
	 * ��������ҳ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void orderDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {
			response.sendRedirect("login.jsp");
			
		}else{
			//��ȡ����Ĳ���ֵ
			int orderId = Integer.parseInt(request.getParameter("order_id"));
			
			MallOrder order = orderBiz.getOrderInfoProduct(orderId);
			//�ܼ�
			double total = 0;
			for (MallOrderItem item : order.getOrderItems()) {
				total = total+item.getTotalPrice();
			}
			request.setAttribute("ORDER_LIST", order);
			request.setAttribute("PAYMENT", order.getPayment());
			request.setAttribute("TOTAL_PRICE", total);
			//���ϴ���1 
			if (order.getOrderItems().size() > 1) {
				if (order.getStatus() > 10) {
					request.getRequestDispatcher("order_detail.jsp").forward(request, response);
					
					//ֱ�ӵ���֧������
				}else{
					String itemId = "";
					
					//ƴ�Ӷ�����ϸid
					for (int i = 0; i < order.getOrderItems().size(); i++) {
						
						if (i == 0) {
							itemId = order.getOrderItems().get(i).getId()+"";
						}else{
							itemId = itemId+","+order.getOrderItems().get(i).getId();
						}
					}
					
					String url = "orderServlet?mod=htmlPayButton&itemid="+itemId+"" +
							"&order_no="+order.getOrderNo()+"" +
							"&order_price="+total+"" +
							"&order_name=���ﳵ��Ʒ" +
							"&order_id="+orderId+"";
					//��·����������������
					request.setAttribute("SERVLET_URL", url);
					request.getRequestDispatcher("order_detail.jsp").forward(request, response);
					
				}
				
			}else{
				
				//�������10 ������֧��״̬
				if (order.getStatus() > 10) {
					request.getRequestDispatcher("order_detail.jsp").forward(request, response);
				}else{
					String url = "orderServlet?mod=htmlPayButton&itemid=" +
							"&order_no="+order.getOrderNo()+"" +
							"&order_price="+total+"" +
							"&order_name="+order.getOrderItems().get(0).getProductName()+"" +
							"&order_id="+orderId+"";
					//��·����������������
					request.setAttribute("SERVLET_URL", url);
					
					request.getRequestDispatcher("order_detail.jsp").forward(request, response);
					
				}
				
				
			}
			
		}
		
		
	}
	/**
	 * ���ﳵ�µ�����
	 * @param request
	 * @param response
	 */
	private void cartConfirmOrder(HttpServletRequest request,
			HttpServletResponse response) {
		//��ȡ�����еĲ���ֵ
		String itemIdStr = request.getParameter("itemId");
		String itemId[] = itemIdStr.split(",");
		double totalPrice = 0;
		List<MallOrderItem> orderItems = orderBiz.getOrderItem(itemId);
		//����ܼ�
		for (MallOrderItem orderItem : orderItems) {
			totalPrice = totalPrice+orderItem.getTotalPrice();
		}
		//��session��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		List<MallShipping> shippings = shippingBiz.getShippingById(user.getId());
		
		session.setAttribute("ORDEROTEM_LIST", orderItems);  //������ϸ����
		session.setAttribute("SHIPPING_LIST", shippings);  //�ջ���ַ����
		session.setAttribute("TORALPRICT", totalPrice);    //�ܼ�
		session.setAttribute("ORDER_ID", "88888");   //���ﳵר��������
		try {
			response.sendRedirect("orderServlet?mod=flushConfirmOrder&type=cart&itemId="+itemIdStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ�û����ﳵ����Ʒ
	 * @param request
	 * @param response
	 */
	private void getUserCart(HttpServletRequest request,
			HttpServletResponse response) {
		//��session��ȡ�û���Ϣ
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
			List<MallOrderItem> orderItems = orderBiz.getOrderItemsByUserId(user.getId());  //��ȡ���ﳵ����Ʒ
			
			request.setAttribute("CART_LIST", orderItems);
			try {
				request.getRequestDispatcher("cart.jsp").forward(request, response);
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
	 * ���ﳵ����
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void cartOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��session��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//��ȡ�����еĲ���ֵ
		int proId = Integer.parseInt(request.getParameter("pro_id"));  //��Ʒid
		int proCount = Integer.parseInt(request.getParameter("pro_count"));  //��Ʒ����
		MallProduct product = productBiz.getProductById(proId);
		//��ѯ�����ﳵ�Ķ�����id
		int orderId = Integer.parseInt(orderBiz.getCartCount(user.getId()));
		//ȡ����һ����ƷͼƬ
		String proImage = getImageUrl(product.getMallImages());
		//����������ϸʵ����
		MallOrderItem orderItem = new MallOrderItem(0, product.getName(), proImage, product.getPrice(), proCount, product.getPrice()*proCount, null, null);
		orderItem.setUser(user);
		orderItem.setProductId(proId);
		//����������ϸ��
		int count = orderBiz.createOrderItem(orderId, orderItem);
		if (count > 0) {
			out.println(product.getName()+"�ɹ����빺�ﳵ!");
		}else{
			out.println(product.getName()+"��ӹ��ﳵʧ��");
		}
		
		
	}
	/**
	 * �µ����� - ��ֹˢ�¶�����һ��������
	 * @param request
	 * @param response
	 */
	private void flushConfirmOrder(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		//��ȡ�����еĲ���ֵ
		String type = request.getParameter("type");
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user != null) {
			List<MallShipping> shippings = shippingBiz.getShippingById(user.getId());
			//��ȡ�û����Żݾ�
			List<MallCoupon> coupons = couponBiz.getCouponByUserId(user.getId());
			if (type == null || type == "") {
				
				//���������򱣴���Ʒ��Ϣ
				request.setAttribute("COUPON_LIST", coupons);
				request.setAttribute("PRODUCT_INFO",session.getAttribute("PRODUCT_INFO"));  //��Ʒ��Ϣ
				request.setAttribute("SHIPPING_LIST",shippings);  //�ջ���ַ����
				request.setAttribute("PRODUCT_IMAGE",session.getAttribute("PRODUCT_IMAGE"));  //��ƷͼƬ·��
				request.setAttribute("PRODUCT_COU",session.getAttribute("PRODUCT_COU"));  //��Ʒ����
				request.setAttribute("TORALPRICT",session.getAttribute("TORALPRICT"));  //�ܼ�
				request.setAttribute("ORDER_ID",session.getAttribute("ORDER_ID"));  //������
				request.setAttribute("TYPE", "order");
				request.setAttribute("ITEMID_ARRAY", 0);   //������ϸ��Id
				
				
			}else{  //���ﳵ
				//��ȡ�����еĲ���ֵ
				String itemIdStr = request.getParameter("itemId");
				request.setAttribute("COUPON_LIST", coupons);
				request.setAttribute("ORDEROTEM_LIST",session.getAttribute("ORDEROTEM_LIST"));  //������ϸ����
				request.setAttribute("SHIPPING_LIST",shippings);  //�ջ���ַ����
				request.setAttribute("TORALPRICT",session.getAttribute("TORALPRICT"));    //�ܼ�
				request.setAttribute("ORDER_ID",session.getAttribute("ORDER_ID"));   //������
				request.setAttribute("TYPE", "cart");   //���ﳵ
				request.setAttribute("ITEMID_ARRAY", itemIdStr);   //������ϸ��Id
			}
			
			try {
				request.getRequestDispatcher("order_confirm.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * ���������ҵĶ�������ȡ������Ϣ
	 * @param request
	 * @param response
	 */
	private void htmlMyOrder(HttpServletRequest request,
			HttpServletResponse response) {
		//��session��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//��δ��¼
		if (user == null) {
			try {
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//�ѵ�¼�Ĳ���
		}else{
			//��ȡȫ������(���������㶩��״̬������)
			List<MallOrder> allOrder = orderBiz.getOrderByUserId(user.getId(),0);
			List<MallOrder> orders = new ArrayList<MallOrder>();
			//��ȡ�����еĲ���ֵ
			int status = Integer.parseInt(request.getParameter("status"));
			//״̬Ϊ1 �����ѯȫ������
			if (status == 1) {
				//�����û�id��ȡ����
				orders = orderBiz.getOrderByUserId(user.getId(),0);
				List<Integer> orderIds = new ArrayList<Integer>();
				//�ҳ��ظ�������
				for (MallOrder mallOrder : orders) {
					orderIds.add(mallOrder.getId());
				}
				Set<Integer> set = new HashSet<Integer>();
				set.addAll(orderIds);
		        Collection<Integer> rs = org.apache.commons.collections.CollectionUtils.disjunction(orderIds, set);
		        
		        //��ȡδ�ظ��Ķ���
		        orders = orderBiz.getOrderByUserId(user.getId(),1);
		         //��ѯ���ظ�����������Ʒ
		        for (Integer orderId : rs) {
		        	for (MallOrder order : orders) {
		        		if (order.getId() == orderId) {
		        			List<MallOrderItem> orderItems = orderBiz.getChildProduct(orderId);
		        			
		        			order.setOrderItems(orderItems);
		        			
						}
					}
				}
			}else{  //����״ֵ̬��ȡ����
				orders = orderBiz.getProductStatus(status,0,user.getId());
				List<Integer> orderIds = new ArrayList<Integer>();
				//�ҳ��ظ�������
				for (MallOrder mallOrder : orders) {
					orderIds.add(mallOrder.getId());
				}
				Set<Integer> set = new HashSet<Integer>();
				set.addAll(orderIds);
		        Collection<Integer> rs = org.apache.commons.collections.CollectionUtils.disjunction(orderIds, set);
		        
		        //��ȡδ�ظ��Ķ���
		        orders = orderBiz.getProductStatus(status, 1,user.getId());
		        
		         //��ѯ���ظ�����������Ʒ
		        for (Integer orderId : rs) {
		        	for (MallOrder order : orders) {
		        		if (order.getId() == orderId) {
		        			List<MallOrderItem> orderItems = orderBiz.getChildProduct(orderId);
		        			order.setOrderItems(orderItems);
		        			
						}
					}
				}
			}
	        
			int noPay = 0;   //δ֧���Ķ�����
			int allPay = orders.size();  //ȫ��������
			int yesPay = 0;   //��֧�������� �������ۣ�
			int shipping = 0;  //�ѷ����Ķ�����
			int okOrder = 0;  //����ɶ�����
			//����ж�����δ֧��
			for (MallOrder order : allOrder) {
				
				if (order.getStatus() == 10) { //δ֧��������
					noPay++;
				}else if(order.getStatus() == 20){  //�Ѹ������
					yesPay++;
				}else if (order.getStatus() == 30) {  //�ѷ���������
					shipping++;
				}else if (order.getStatus() == 50) {   //����ɶ�����
					okOrder++;
				}
				
			}
			
			request.setAttribute("ORDER_LIST", orders);  //��������
			request.setAttribute("NO_PAY", noPay);  //δ֧��������
			request.setAttribute("ALL_PAY", allPay);  //ȫ��������
			request.setAttribute("SHIPPING", shipping);  //�ѷ����Ķ�����
			request.setAttribute("YES_PAY",yesPay);  //��֧���Ķ�����
			request.setAttribute("OK_ORDER", okOrder);  //����ɶ�����
			try {
				request.getRequestDispatcher("order.jsp").forward(request, response);
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
	 * ���֧����ť ����Ҫ����Ϣ����session
	 * @param request
	 * @param response
	 */
	private void htmlPayButton(HttpServletRequest request,
			HttpServletResponse response) {
		
		//��ȡ�����еĲ���
		String orderNo = request.getParameter("order_no");  //������
		double payment = Double.parseDouble(request.getParameter("order_price"));  //ʵ���۸�
		String orderName = request.getParameter("order_name");  //��������
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		String itemidStr = request.getParameter("itemid");
		//��������ʵ����
		MallOrder order = new MallOrder();
		order.setId(orderId);
		order.setOrderNo(orderNo);
		order.setPayment(payment);
		MallProduct product = new MallProduct();  //��Ʒ����ʵ����
		product.setName(orderName);
		order.setProduct(product);
		//����Ϣ����session�С���֧���ɹ��ȸ��±�
		HttpSession session = request.getSession();
		session.setAttribute("PAY_ORDER", order);
		if (itemidStr == null || itemidStr.equals("")) {
			
			session.setAttribute("ITEM_ARRAY", "");
			
		}else{
			session.setAttribute("ITEM_ARRAY", itemidStr);
		}
		
		try {
			request.getRequestDispatcher("pay/alipay.trade.page.pay.jsp?WIDout_trade_no="+orderNo+"&WIDtotal_amount="+payment+"&WIDsubject="+orderName+"&WIDbody=0").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * ����֧������  �޸Ķ������ջ���ַ
	 * @param request
	 * @param response
	 */
	private void htmlPayUpdateOrder(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		//��ȡ�����еĲ���ֵ
		String orderNo = request.getParameter("order_id");
		int shippingId = Integer.parseInt(request.getParameter("shipping_id"));
		//��ȡ�Żݾ�
		String couponIdStr = request.getParameter("coupon_id");
		
		//��ȡ�����еĲ���
		String type = request.getParameter("type");
		session.setAttribute("COUPON_ID", null);
		//����Ʒҳ���µ�
		if (type == null || type == "") {
			//�����ڿ���˵��ʹ�����Ż�ȯ
			if (couponIdStr != null && couponIdStr != "") {
				//�޸Ķ����ļ۸�
				int money = Integer.parseInt(request.getParameter("money"));
				int orderId = Integer.parseInt(orderNo);
				couponBiz.updateOrderMoney(money, orderId);
				int couponId = Integer.parseInt(couponIdStr);
				//�޸��Ż�ȯ״̬
				couponBiz.updateCouponStatus(couponId, 0);
				session.setAttribute("COUPON_ID", couponId);
			}
			
			int orderId = Integer.parseInt(orderNo);
			//�Ѳ���Ҫ��ֵ�Ƴ���
			session.removeAttribute("PRODUCT_INFO");  //��Ʒ��Ϣ
			session.removeAttribute("SHIPPING_LIST");  //�ջ���ַ����
			session.removeAttribute("PRODUCT_IMAGE");  //��ƷͼƬ·��
			session.removeAttribute("PRODUCT_COU");  //��Ʒ����
			session.removeAttribute("TORALPRICT");  //�ܼ�
			session.removeAttribute("ORDER_ID");  //������
			
			//�޸Ķ�����
			orderBiz.updateOrderById(shippingId, orderNo);
			//��ȡ�����ź���Ʒ����Ϣ
			MallOrder order = orderBiz.getOrderAndProductById(orderId);
			int orderNo2 = Integer.parseInt(orderNo);
			order.setId(orderNo2);
			//����������
			request.setAttribute("ORDER", order);
			try {
				request.getRequestDispatcher("pay.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//�ڹ��ﳵ��ѡ����Ʒ�µ�����
		}else{
			//��ȡ�����ӱ�id
			String itemIdStr = request.getParameter("itemId");
			double totalPrice = (Double)session.getAttribute("TORALPRICT");    //�ܼ�
			MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
			//��������ʵ���� 
			MallOrder order = new MallOrder();
			order.setOrderNo(getNumberForPK());
			order.setPayment(totalPrice);
			order.setStatus(10);
			MallShipping shipping = new MallShipping();
			shipping.setId(shippingId);
			order.setShipping(shipping);
			orderBiz.createOrder(user.getId(), order);   //���ﳵ��������
			//��ȡ����id
			int orderId = orderBiz.getOrderId();
			
			//�����ڿ���˵��ʹ�����Ż�ȯ
			if (couponIdStr != null && couponIdStr != "") {
				//�޸Ķ����ļ۸�
				int money = Integer.parseInt(request.getParameter("money"));
				couponBiz.updateOrderMoney(money, orderId);
				int couponId = Integer.parseInt(couponIdStr);
				//�޸��Ż�ȯ״̬
				couponBiz.updateCouponStatus(couponId, 0);
				session.setAttribute("COUPON_ID", couponId);
			}
			
			
			List<MallOrderItem> orderItems = (List<MallOrderItem>)session.getAttribute("ORDEROTEM_LIST");  //������ϸ����
			//ѭ������������ϸ�� �������Ʒ��
			for (MallOrderItem orderItemList : orderItems) {
				
				MallOrderItem orderItem = new MallOrderItem(0, orderItemList.getProductName(), 
						orderItemList.getProductImage(), 
						orderItemList.getCurrent_unit_price(), 
						orderItemList.getQuantity(), 
						orderItemList.getTotalPrice(), null, null);
				
				orderItem.setUser(user);
				orderItem.setProductId(orderItemList.getProductId());
				//����������ϸ��
				orderBiz.createOrderItem(orderId, orderItem);
			}
			//��ȡ�����ź���Ʒ����Ϣ
			MallOrder orderCart = orderBiz.getOrderAndProductById(orderId);
			orderCart.getProduct().setName("���ﳵ��Ʒ");
			int orderNo2 = Integer.parseInt(orderNo);
			order.setId(orderNo2);
			//����������
			request.setAttribute("ORDER", orderCart);
			request.setAttribute("ITEM_ARRAY", itemIdStr);
			try {
				request.getRequestDispatcher("pay.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		session.removeAttribute("ORDEROTEM_LIST");  //������ϸ����
		session.removeAttribute("SHIPPING_LIST");  //�ջ���ַ����
		
		session.removeAttribute("ORDER_ID");   //������
		
		
		
		
	}
	/**
	 * ȷ�϶���
	 * @param request
	 * @param response
	 */
	private void confirmOrder(HttpServletRequest request,
			HttpServletResponse response) {
		//��session�л�ȡ��¼���û�
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");

		//��ȡ�����еĲ���ֵ
		int proId = Integer.parseInt(request.getParameter("pro_id"));  //��Ʒid
		int proCount = Integer.parseInt(request.getParameter("pro_count"));  //��Ʒ����
		String payOrigin = request.getParameter("payOrigin");    //֧���ص�
		MallProduct product = productBiz.getProductById(proId);
		List<MallShipping> shippings = shippingBiz.getShippingById(user.getId());
		//��������ʵ���� 
		MallOrder order = new MallOrder();
		order.setOrderNo(getNumberForPK());
		order.setPayment(product.getPrice()*proCount);
		order.setStatus(10);
		//���õ�ַΪ0
		MallShipping shipping = new MallShipping();
		shipping.setId(0);
		order.setShipping(shipping);
		
		//��������
		orderBiz.createOrder(user.getId(), order);
		
		
		
		//��ȡ����id
		int orderId = orderBiz.getOrderId();
		//ȡ����һ����ƷͼƬ
		String proImage = getImageUrl(product.getMallImages());
		MallOrderItem orderItem = new MallOrderItem(0, product.getName(), proImage, product.getPrice(), proCount, product.getPrice()*proCount, null, null);
		orderItem.setUser(user);
		orderItem.setProductId(proId);
		//����������ϸ��
		orderBiz.createOrderItem(orderId, orderItem);
		
		
		//ȫ�������������򱣴���Ʒ��Ϣ
		session.setAttribute("PRODUCT_INFO", product);  //��Ʒ��Ϣ
		session.setAttribute("SHIPPING_LIST", shippings);  //�ջ���ַ����
		session.setAttribute("PRODUCT_IMAGE", proImage);  //��ƷͼƬ·��
		session.setAttribute("PRODUCT_COU", proCount);  //��Ʒ����
		session.setAttribute("TORALPRICT", product.getPrice()*proCount);  //�ܼ�
		session.setAttribute("ORDER_ID", orderId);  //������
		
		
			try {
				response.sendRedirect("orderServlet?mod=flushConfirmOrder");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	/**
	 * ������ɶ�����   ʱ��+�����
	 * @return
	 */
	public String getNumberForPK(){
    	String id="";
    	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
    	String temp = sf.format(new Date());
		int random=(int) (Math.random()*10000);
		id=temp+random;
		return id;
	}
	/**
	 * ��json��ȡ����һ��ͼ·��
	 * @param imgJson
	 * @return
	 */
	public String getImageUrl(String imgJson){
		JSONArray proJson = JSONArray.fromObject(imgJson);
		JSONObject obj = proJson.getJSONObject(0);
		
		return (String)obj.get("url");
		
	}
	/**
	 * ��ȡ�û���Ϣ
	 * @param request
	 * @return
	 */
	public MallUser getUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		return user;
		
	}
}
