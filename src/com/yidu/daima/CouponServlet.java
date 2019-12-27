package com.yidu.daima;

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
/**
 * ���ߣ�����
 * ʱ�䣺2019-11-1
 * �汾��1.0
 */
public class CouponServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}
	//����dao�㷽��
	CouponBiz couponBiz = new CouponBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ת��
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
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void changeCoupon(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out = response.getWriter();
		//����sesion
		HttpSession session = request.getSession();
		//��session�л�ȡ�û���Ϣ ���浽��¼_�û�������
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//�û����ڿ�
		if (user == null) {
			out.println("���µ�¼");
		}else{
			//��ȡ�����еĲ���ֵ
			String CDK = request.getParameter("CDK");
			//���ò����Ż�ȯcdk�������û���ȡ��� ��
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
	 * @param request����
	 * @param response��Ӧ
	 * @throws IOException�쳣 
	 */
	private void createCoupons(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ Ǯ
		int money = Integer.parseInt(request.getParameter("money"));   //�����Ż�ȯ�Ľ��
		//�����Ż�ȯ������
		int count = Integer.parseInt(request.getParameter("count"));
		int cou = 0;  //������
		//ѭ�������Ż�ȯ��
		for (int i = 0; i < count; i++) {
			//�����Ż�ȯ����
			MallCoupon coupon = new MallCoupon();
			//�����ַ��� CKDEY ��ȡSR�������ַ���10
			String CDKEY = getStringRandom(10);
			//�Ż�ȯset����  ��+m
			coupon.setCouponName("��Ϊ�̳�-"+money+"Ԫ�Ż�ȯ");
			//setǮ
			coupon.setMoney(money);
			//����dep �һ���+m
			coupon.setDepict("�һ���һ���"+money+"Ԫ�Ż�ȯ");
			//����cDK
			coupon.setCDKEY(CDKEY);
			//�����Ż�ȯ ���������Ż�ȯ����
			couponBiz.insertCoupon(0, coupon);
			//������++
			cou++;
		}
		//��� ������
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
		//��ȡjson���ݸ�ʽMallUser���� ���ò����û����Ʒ���
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
		//���������
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
		//�������0
		if (count > 0) {
			out.println("ɾ����Ʒ�ɹ���");
		}else{
			out.println("ɾ����Ʒʧ�ܣ�");
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
		//�����Ż�ȯ����
		MallCoupon coupon = new MallCoupon(id, depict, money, couponName, couponGetTime, couponUse, createTime, updateTime);
		//������������
		int count = couponBiz.insertCoupon(coupon);
		//���������
		PrintWriter out = response.getWriter();
		//�������0
		if (count > 0) {
			out.println("������Ʒ�ɹ���");
		}else{
			out.println("������Ʒʧ�ܣ�");
		}
		//�ر���
		out.close();
	}
	
	/**
	 * ajax��ȡ�Ż�ȯ
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException  �쳣
	 */
	private void ajaxGetCouponUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out = response.getWriter();
		//��session��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		//��session�л�ȡ�û���Ϣ ���浽��¼_�û�������
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//����û����ڿ�
		if (user == null) {
			//���0
			out.println(0);
		}else {
			//�����Ż�ȯ���ϣ����û�ȡ�Ż�ȯ��������ȡ�û�id��
			List<MallCoupon> coupons = couponBiz.getCouponByUserId(user.getId());
			//����Ż�ȯ����
			out.println(coupons.size());
		}
	}
	/**
	 * �����û���ȡ�Żݾ�
	 * @param request����
	 * @param response��Ӧ
	 */
	private void getCouponUser(HttpServletRequest request,
			HttpServletResponse response) {
		
		//����session
		HttpSession session = request.getSession();
		//��session�л�ȡ�û���Ϣ ���浽��¼_�û�������
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//����û����ڿ�
		if (user == null) {
			try {
				//r2�ض����¼��ҳs
				response.sendRedirect("login.jsp");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block e.p
				e.printStackTrace();
			}
		}else{
			//���ø����û���ȡ�Ż�ȯ���� (��ȡ�û����)����һ���Ż�ȯ���ϱ���
			List<MallCoupon> coupons = couponBiz.getCouponByUserId(user.getId());
			//r1����������setA  ���� 2�Ż�ȯ.����
			request.setAttribute("COUPON_SIZE", coupons.size());
			//r1����������setA  �Ż�ȯ����  �Ż�ȯ���϶���
			request.setAttribute("COUPON_LIST", coupons);
			
			try {
				//ת�� 1����.gReq�Ż�ȯ��ҳ.f
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
	
	/**
	 * ��������û��������ֺ���ĸ���  
	 * @param length ����
	 * @return�����ַ���
	 */
    public String getStringRandom(int length) {  
        //�����ַ�������ֵ��
        String val = "";  
        //�����������
        Random random = new Random();  
          
        //����length����ʾ���ɼ�λ�����  
        for(int i = 0; i < length; i++) {  
            // �����ַ��� ON,�������.nI,�������������� �� 2 ����0�� �� Ϊ  char  ��or�� Ϊ  num
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //�����ĸ��������
            if( "char".equalsIgnoreCase(charOrNum)) {  
                //�������� temp �����.nI2�� 2 ����0���� ����Ǵ�д��ĸ(65)����Сд��ĸ(97)  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97; 
                //�ַ����ӵ�(�ַ�����)(�������.nI26��temp)
                val += (char)(random.nextInt(26) + temp);  
                //����
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }

}
