package com.yidu.mall.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yidu.mall.coupon.biz.CouponBiz;
import com.yidu.mall.coupon.model.MallCoupon;
import com.yidu.mall.user.biz.UserBiz;
import com.yidu.mall.user.model.MallUser;
/**
 * 会员中心servlet
 * @author 小恶魔
 *
 */
public class MenberServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	UserBiz userBiz = new UserBiz();//用户业务逻辑层
	CouponBiz couponBiz = new CouponBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String mod = request.getParameter("mod");
		
		if ("getIntegral".equals(mod)) {   //获取积分
			getIntegral(request,response);
		}else if ("exChangeCoupon".equals(mod)) {   //兑换优惠券
			exChangeCoupon(request,response);
		}
		
	}
	/**
	 * 兑换优惠券
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void exChangeCoupon(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//从session获取用户信息
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {
			out.println(0);
		}else{
			//获取积分
			double Integral = userBiz.getTotalPrice(user.getId());
			if (Integral < 1) {
				
				out.println(0);
				
			}else{
				
				//兑换优惠卷
				int count = userBiz.updateUserIntegral(user.getId(), Integral-10);   //减少10积分
				//多一张优惠券
				MallCoupon coupon = new MallCoupon();
				coupon.setDepict("使用积分兑换的优惠券");
				coupon.setMoney(50);
				coupon.setCouponName("积分优惠券");
				coupon.setCDKEY(null);
				int count2 = couponBiz.insertCoupon(user.getId(), coupon);
				
				if (count > 0) {
					if (count2 > 0) {
						out.println(1);
					}else{
						out.println(0);
					}
				}else{
					out.println(0);
				}
				
			}
			
			
		}
		
	}
	/**
	 * 获取积分
	 * @param request  请求
	 * @param response   响应
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getIntegral(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		//从session获取用户信息
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {
			response.sendRedirect("login.jsp");
		}else{
			//获取积分
			double Integral = userBiz.getTotalPrice(user.getId());
			
			//算出能换多少张优惠券
			int couponCount = (int) (Integral / 10);
			if (couponCount < 0) {
				couponCount = 0;
			}
			
			request.setAttribute("JI_FEN", Integral);
			request.setAttribute("COUPON_COUNT", couponCount);
			
			request.getRequestDispatcher("jifen.jsp").forward(request, response);
		}
		
		
		
		
		
	}

}
