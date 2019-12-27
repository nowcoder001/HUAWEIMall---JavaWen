package com.yidu.mall.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yidu.mall.coupon.biz.CouponBiz;
import com.yidu.mall.order.biz.OrderBiz;
import com.yidu.mall.order.model.MallOrder;
import com.yidu.mall.user.biz.UserBiz;
import com.yidu.mall.user.model.MallUser;
/**
 * 支付成功后会自动跳转到这个servlet
 * @author 小恶魔
 *
 */
public class PayServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	OrderBiz orderBiz = new OrderBiz();
	CouponBiz couponBiz = new CouponBiz();
	UserBiz userBiz = new UserBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从session取出需要的信息
		HttpSession session = request.getSession();
		MallOrder order = (MallOrder)session.getAttribute("PAY_ORDER");  //订单对象
		String itemIdStr = (String)session.getAttribute("ITEM_ARRAY");  //详情表id
		Integer couponId = (Integer)session.getAttribute("COUPON_ID"); //优惠券id
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");   //用户对象
		
		if (user != null) {
			//购买成功之后给此用户增加积分  (实付款  / 20)
			userBiz.updateUserIntegral(user.getId(), order.getPayment() / 20);
		}
		
		//不等于空说明用了优惠券
		if (couponId != null) {
			//支付成功后删除优惠券
			couponBiz.deleteCoupon(couponId);
		}
		
		if (itemIdStr != "" || itemIdStr !=  null) {
			String itemId[] = itemIdStr.split(",");
			//删除购物车的商品
			orderBiz.deleteCartProductByItemId(itemId);
		}
		
		order.setStatus(20);
		order.setPaymentType(1);
		//更新数据库表
		orderBiz.updatePaySuccess(order);
		request.getRequestDispatcher("pay/pay_return.html").forward(request, response);
	}

}
