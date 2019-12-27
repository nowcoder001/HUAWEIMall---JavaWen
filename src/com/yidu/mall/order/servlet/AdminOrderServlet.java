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
 * 后台订单servlet
 * @author 小恶魔
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
			
			if ("getAllOrder".equals(mod)) {   //获取全部订单信息
				getAllOrder(request,response);
			}else if ("getOrderItemByOrderId".equals(mod)) {   //根据订单id获取订单明细商品
				getOrderItemByOrderId(request,response);
			}else if ("updateOrderStatus".equals(mod)) {    //修改订单状态
				updateOrderStatus(request,response);
			}else if ("userIdGetAddress".equals(mod)) {   //根据用户id获取收货地址
				userIdGetAddress(request,response);
			}else if ("updateAddress".equals(mod)) {   //修改订单的收货地址
				updateAddress(request,response);
			}else if ("updateOrderPrice".equals(mod)) {   //修改订单的实付款价格
				updateOrderPrice(request,response);
			}else if ("deleteOrder".equals(mod)) {   //根据id删除订单表
				deleteOrder(request,response);
			}else if ("moneylog".equals(mod)) {   //资金明细
				moneylog(request,response);
			}
			
		
		
	}
	/**
	 * 资金明细
	 * @param request  请求
	 * @param response   响应
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
	 * 根据id删除订单表
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数值
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		
		int count = adminOrderBiz.deleteOrder(orderId);
		if (count > 0) {
			out.println("删除订单成功！");
		}else{
			out.println("删除订单失败！");
		}
	}
	/**
	 * 修改订单的实付款价格
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateOrderPrice(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数值
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		double price = Double.parseDouble(request.getParameter("price"));
		int count = adminOrderBiz.updateOrderPrice(orderId, price);
		
		if (count > 0) {
			out.println("修改价格成功");
		}else{
			out.println("修改价格失败");
		}
		
	}
	/**
	 * 修改订单的收货地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数值
		int shippingId = Integer.parseInt(request.getParameter("shipping_id"));
		String orderId = request.getParameter("order_id");
		
		int count = adminOrderBiz.updateOrderById(shippingId, orderId);
		
		if (count > 0) {
			out.println("修改地址成功！");
		}else{
			out.println("修改地址失败！");
		}
		
	}
	/**
	 * 根据用户id获取收货地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void userIdGetAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数
		int userId = Integer.parseInt(request.getParameter("user_id"));
		
		List<MallShipping> shippings = shippingBiz.getShippingByUserId(userId);
		
		String addressJson = JSONArray.fromObject(shippings).toString();
		out.println(addressJson);
	}
	/**
	 * 修改订单状态
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateOrderStatus(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		int status = Integer.parseInt(request.getParameter("status"));
		
		int count = adminOrderBiz.updateOrderAddress(orderId, status);
		
		out.println(count);
		
	}
	/**
	 * 根据订单id获取订单明细商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getOrderItemByOrderId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		
		List<MallOrderItem> orderItems = adminOrderBiz.getChildProduct(orderId);
		
		String itemJson = JSONArray.fromObject(orderItems).toString();
		out.println(itemJson);
	}
	/**
	 * 获取全部订单信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		int listCount = adminOrderBiz.getAllOrdersNoRedo().size();  //几个个数
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		List<MallOrder> orders = adminOrderBiz.getAllOrders(page,rows);
		
		//转成json'格式
		String orderJson = JSONArray.fromObject(orders).toString();
		//拼接分页需要格式
		orderJson = "{\"total\":"+listCount+",\"rows\":"+orderJson+"}";
		out.println(orderJson);
		
	}

}
