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
 * ֧���ɹ�����Զ���ת�����servlet
 * @author С��ħ
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
		//��sessionȡ����Ҫ����Ϣ
		HttpSession session = request.getSession();
		MallOrder order = (MallOrder)session.getAttribute("PAY_ORDER");  //��������
		String itemIdStr = (String)session.getAttribute("ITEM_ARRAY");  //�����id
		Integer couponId = (Integer)session.getAttribute("COUPON_ID"); //�Ż�ȯid
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");   //�û�����
		
		if (user != null) {
			//����ɹ�֮������û����ӻ���  (ʵ����  / 20)
			userBiz.updateUserIntegral(user.getId(), order.getPayment() / 20);
		}
		
		//�����ڿ�˵�������Ż�ȯ
		if (couponId != null) {
			//֧���ɹ���ɾ���Ż�ȯ
			couponBiz.deleteCoupon(couponId);
		}
		
		if (itemIdStr != "" || itemIdStr !=  null) {
			String itemId[] = itemIdStr.split(",");
			//ɾ�����ﳵ����Ʒ
			orderBiz.deleteCartProductByItemId(itemId);
		}
		
		order.setStatus(20);
		order.setPaymentType(1);
		//�������ݿ��
		orderBiz.updatePaySuccess(order);
		request.getRequestDispatcher("pay/pay_return.html").forward(request, response);
	}

}
