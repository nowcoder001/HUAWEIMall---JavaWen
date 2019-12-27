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
 * ��Ա����servlet
 * @author С��ħ
 *
 */
public class MenberServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	UserBiz userBiz = new UserBiz();//�û�ҵ���߼���
	CouponBiz couponBiz = new CouponBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String mod = request.getParameter("mod");
		
		if ("getIntegral".equals(mod)) {   //��ȡ����
			getIntegral(request,response);
		}else if ("exChangeCoupon".equals(mod)) {   //�һ��Ż�ȯ
			exChangeCoupon(request,response);
		}
		
	}
	/**
	 * �һ��Ż�ȯ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void exChangeCoupon(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��session��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {
			out.println(0);
		}else{
			//��ȡ����
			double Integral = userBiz.getTotalPrice(user.getId());
			if (Integral < 1) {
				
				out.println(0);
				
			}else{
				
				//�һ��Żݾ�
				int count = userBiz.updateUserIntegral(user.getId(), Integral-10);   //����10����
				//��һ���Ż�ȯ
				MallCoupon coupon = new MallCoupon();
				coupon.setDepict("ʹ�û��ֶһ����Ż�ȯ");
				coupon.setMoney(50);
				coupon.setCouponName("�����Ż�ȯ");
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
	 * ��ȡ����
	 * @param request  ����
	 * @param response   ��Ӧ
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getIntegral(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		//��session��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {
			response.sendRedirect("login.jsp");
		}else{
			//��ȡ����
			double Integral = userBiz.getTotalPrice(user.getId());
			
			//����ܻ��������Ż�ȯ
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
