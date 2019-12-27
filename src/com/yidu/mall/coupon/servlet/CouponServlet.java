package com.yidu.mall.coupon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.yidu.mall.coupon.biz.CouponBiz;
import com.yidu.mall.coupon.model.MallCoupon;
import com.yidu.mall.user.model.MallUser;

public class CouponServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	CouponBiz couponBiz = new CouponBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String mod = request.getParameter("mod");
		
		if ("getCouponUser".equals(mod)) {   //�����û���ȡ�Żݾ�
			getCouponUser(request,response);
		}else if ("ajaxGetCouponUser".equals(mod)) {   //ajax��ȡ�Ż�ȯ
			ajaxGetCouponUser(request,response);
		}else if (mod.equals("select")) {
			CouponSelect(request, response);//��ѯ�Żݾ��
		}else if (mod.equals("delete")) {
			Coupondelete(request, response);//���ݱ��ɾ��
		}else if (mod.equals("update")) {
			Couponupdate(request, response);//�޸�
		}else if (mod.equals("insert")) {
			CouponInsert(request, response);//����
		}else if (mod.equals("selectUser")) {
			UserSelect(request, response);//��ѯ�û���
		}else if (mod.equals("updateCoupon")) {
			updateCoupon(request, response);//�޸��û�������
		}else if ("createCoupons".equals(mod)) {   //�����Ż�ȯ
			createCoupons(request,response);
		}else if ("changeCoupon".equals(mod)) {   //�һ��Ż�ȯ
			changeCoupon(request,response);
		}
		
	}
	/**
	 * �һ��Ż�ȯ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void changeCoupon(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��sesion��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		
		if (user == null) {
			out.println("���µ�¼");
		}else{
			//��ȡ�����еĲ���ֵ
			String CDK = request.getParameter("CDK");
			
			int count = couponBiz.selectCouponCDK(user.getId(),CDK);
			
			if (count > 0 ) {
				//�һ��ɹ��� ���ô��Ż�ȯ�����ٶһ�
				couponBiz.updateCouponCDK(CDK);
			}
			
			out.println(count);
		}
		
		
	}
	/**
	 * �����Ż�ȯ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void createCoupons(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		int money = Integer.parseInt(request.getParameter("money"));   //�����Ż�ȯ�Ľ��
		//�����Ż�ȯ������
		int count = Integer.parseInt(request.getParameter("count"));
		int cou = 0;  //������
		//ѭ�������Ż�ȯ
		for (int i = 0; i < count; i++) {
			MallCoupon coupon = new MallCoupon();
			//����CKD
			String CDKEY = getStringRandom(10);
			
			coupon.setCouponName("��Ϊ�̳�-"+money+"Ԫ�Ż�ȯ");
			coupon.setMoney(money);
			coupon.setDepict("�һ���һ���"+money+"Ԫ�Ż�ȯ");
			coupon.setCDKEY(CDKEY);
			//�����Ż�ȯ
			couponBiz.insertCoupon(0, coupon);
			cou++;
		}
		
		out.println(cou);
		
	}

	/**
	 * ��ѯ�û���
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	public void UserSelect(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//���������
		PrintWriter out = response.getWriter();
		//��ȡjson���ݸ�ʽMallUser����
		List<MallUser> mallUsers = couponBiz.selectUserName();
		//��MallUser����ת����JSONArray��ʽ
		String couponsJson = JSONArray.fromObject(mallUsers).toString();
		//��Ӧ�ؿͻ���
		out.write(couponsJson);
		//�ر���
		out.close();
	}
	/**
	 * ��ѯ�Żݾ��
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	public void CouponSelect(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//���շ�ҳ��ҳ�����Ŀ��
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		//���������
		PrintWriter out = response.getWriter();
		//��ȡ�Ż�ȯ���ϵ�json���ݸ�ʽ
		List<MallCoupon> classJson = couponBiz.selectCoupon();
		//��ҳ�Ż�ȯ����
		List<MallCoupon> coupons = couponBiz.selectCoupon(page, rows);
		//����ҳ����ת����json��ʽ
		String couponsJson = JSONArray.fromObject(coupons).toString();
		//��������ҳ����
		couponsJson = "{\"total\":"+classJson.size()+",\"rows\":"+couponsJson+"}";
		//��Ӧ�ؿͻ���
		out.write(couponsJson);
		//�ر���
		out.close();
	}
	/**
	 * �����Żݾ�
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	public void updateCoupon(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//��ȡ�Ż�ȯid
		int couponid = Integer.parseInt(request.getParameter("couponId"));
		//��ȡ�û�id
		int userid = Integer.parseInt(request.getParameter("id"));
		//�����޸��Ż�ȯ����
		int count = couponBiz.updateCoupon(couponid,userid);
		PrintWriter out = response.getWriter();
		if (count > 0) {
			out.println("������Ʒ�ɹ���");
		}else{
			out.println("������Ʒʧ�ܣ�");
		}
		out.close();
	}
	/**
	 * ���ݱ��ɾ������
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	public void Coupondelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//��ȡid
		int id = Integer.parseInt(request.getParameter("id"));
		//���ø���idɾ���Ż�ȯ����
		int count = couponBiz.deleteId(id);
		//���������
		PrintWriter out = response.getWriter();
		if (count > 0) {
			out.println("ɾ����Ʒ�ɹ���");
		}else{
			out.println("ɾ����Ʒʧ�ܣ�");
		}
		//�ر���
		out.close();
	}
	/**
	 * �޸�
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	public void Couponupdate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//��ȡ�����еĲ���ֵid
		int id = Integer.parseInt(request.getParameter("id"));
		//��ȡdepict�Ż�ȯ����
		String depict = request.getParameter("depict");
		//��ȡ�������
		int money = Integer.parseInt(request.getParameter("money"));
		//��ȡ�Ż�ȯ����
		String couponName = request.getParameter("couponName");
		//��ȡ�Ż�ȯ���ʱ��
		String couponGetTime = request.getParameter("couponGetTime");
		//��ȡ�Ƿ��ʹ��
		String couponUse = request.getParameter("couponUse");
		//��ȡ����ʱ��
		String createTime = request.getParameter("createTime");
		//��ȡ���� ʱ��
		String updateTime = request.getParameter("updateTime"); 
		//�����Ż�ȯ����
		MallCoupon coupon = new MallCoupon(id, depict, money, couponName, couponGetTime, couponUse, createTime, updateTime);
		//�����޸��Ż�ȯ����
		int count = couponBiz.updateCoupon(id, coupon);
		//���������
		PrintWriter out = response.getWriter();
		if (count > 0) {
			out.println("�޸���Ʒ�ɹ���");
		}else{
			out.println("�޸���Ʒʧ�ܣ�");
		}
		//�ر���
		out.close();
	}
	/**
	 * �����Ż�ȯ
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	public void CouponInsert(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//��ȡ�����еĲ���ֵ
		int id = Integer.parseInt(request.getParameter("id"));//�������
		String depict = request.getParameter("depict");//�Ż�ȯ����
		int money = Integer.parseInt(request.getParameter("money"));//�������
		String couponName = request.getParameter("couponName");//�Ż�ȯ����
		String couponGetTime = request.getParameter("couponGetTime");//�Ż�ȯ���ʱ��
		String couponUse = request.getParameter("couponUse");//�Ƿ��ʹ��
		String createTime = request.getParameter("createTime");//����ʱ��
		String updateTime = request.getParameter("updateTime"); //���� ʱ��

		MallCoupon coupon = new MallCoupon(id, depict, money, couponName, couponGetTime, couponUse, createTime, updateTime);
		int count = couponBiz.insertCoupon(coupon);
		PrintWriter out = response.getWriter();
		if (count > 0) {
			out.println("������Ʒ�ɹ���");
		}else{
			out.println("������Ʒʧ�ܣ�");
		}
		out.close();
	}
	
	/**
	 * ajax��ȡ�Ż�ȯ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void ajaxGetCouponUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��session��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		if (user == null) {
			out.println(0);
		}else {
			
			List<MallCoupon> coupons = couponBiz.getCouponByUserId(user.getId());
			out.println(coupons.size());
			
		}
		
	}
	/**
	 * �����û���ȡ�Żݾ�
	 * @param request
	 * @param response
	 */
	private void getCouponUser(HttpServletRequest request,
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
			
			List<MallCoupon> coupons = couponBiz.getCouponByUserId(user.getId());
			request.setAttribute("COUPON_SIZE", coupons.size());
			request.setAttribute("COUPON_LIST", coupons);
			
			try {
				request.getRequestDispatcher("coupon.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//��������û��������ֺ���ĸ���,  
    public String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //����length����ʾ���ɼ�λ�����  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //�����ĸ��������  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //����Ǵ�д��ĸ����Сд��ĸ  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }

}
