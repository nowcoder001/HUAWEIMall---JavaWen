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
 * 作者：晨初
 * 时间：2019-11-1
 * 版本：1.0
 */
public class CouponServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}
	//创建dao层方法
	CouponBiz couponBiz = new CouponBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//转码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String mod = request.getParameter("mod");
		
		if ("getCouponUser".equals(mod)) {   //根据用户获取优惠卷
			getCouponUser(request,response);
		}else if ("ajaxGetCouponUser".equals(mod)) {   //ajax获取优惠券
			ajaxGetCouponUser(request,response);
		}else if (mod.equals("select")) {
			CouponSelect(request, response);//查询优惠卷表
		}else if (mod.equals("delete")) {
			Coupondelete(request, response);//根据编号删除
		}else if (mod.equals("insert")) {
			CouponInsert(request, response);//新增
		}else if (mod.equals("selectUser")) {
			UserSelect(request, response);//查询用户表
		}else if (mod.equals("updateCoupon")) {
			updateCoupon(request, response);//修改用户表，赠送
		}else if ("createCoupons".equals(mod)) {   //生成优惠券
			createCoupons(request,response);
		}else if ("changeCoupon".equals(mod)) {   //兑换优惠券
			changeCoupon(request,response);
		}
		
	}
	/**
	 * 兑换优惠券
	 * @param request 请求
	 * @param response 相应
	 * @throws IOException 异常
	 */
	private void changeCoupon(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out = response.getWriter();
		//创建sesion
		HttpSession session = request.getSession();
		//从session中获取用户信息 保存到登录_用户对象中
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//用户等于空
		if (user == null) {
			out.println("重新登录");
		}else{
			//获取请求中的参数值
			String CDK = request.getParameter("CDK");
			//调用查找优惠券cdk方法，用户获取编号 ，
			int count = couponBiz.selectCouponCDK(user.getId(),CDK);
			
			if (count > 0 ) {
				//兑换成功后 设置此优惠券不可再兑换
				couponBiz.updateCouponCDK(CDK);
			}
			out.println(count);
		}
	}
	/**
	 * 生成优惠券
	 * @param request请求
	 * @param response相应
	 * @throws IOException异常 
	 */
	private void createCoupons(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out = response.getWriter();
		//获取请求中的参数值 钱
		int money = Integer.parseInt(request.getParameter("money"));   //生成优惠券的金额
		//生成优惠券的数量
		int count = Integer.parseInt(request.getParameter("count"));
		int cou = 0;  //计数器
		//循环生成优惠券《
		for (int i = 0; i < count; i++) {
			//创建优惠券对象
			MallCoupon coupon = new MallCoupon();
			//声明字符串 CKDEY 获取SR并设置字符串10
			String CDKEY = getStringRandom(10);
			//优惠券set名称  华+m
			coupon.setCouponName("华为商城-"+money+"元优惠券");
			//set钱
			coupon.setMoney(money);
			//设置dep 兑换码+m
			coupon.setDepict("兑换码兑换："+money+"元优惠券");
			//设置cDK
			coupon.setCDKEY(CDKEY);
			//创建优惠券 调用新增优惠券方法
			couponBiz.insertCoupon(0, coupon);
			//计数器++
			cou++;
		}
		//输出 计数器
		out.println(cou);
		
	}

	/**
	 * 查询用户表
	 * @param request 请求
	 * @param response 相应
	 * @throws IOException 异常
	 */
	public void UserSelect(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//创建输出流
		PrintWriter out = response.getWriter();
		//获取json数据格式MallUser集合 调用查找用户名称方法
		List<MallUser> mallUsers = couponBiz.selectUserName();
		//将MallUser集合转换成JSONArray格式
		String couponsJson = JSONArray.fromObject(mallUsers).toString();
		//响应回客户端
		out.write(couponsJson);
		//关闭流
		out.close();
	}
	/**
	 * 查询优惠卷表
	 * @param request 请求
	 * @param response 相应
	 * @throws IOException 异常
	 */
	public void CouponSelect(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//接收分页的页码和条目数
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		//创建输出流
		PrintWriter out = response.getWriter();
		//获取优惠券集合的json数据格式
		List<MallCoupon> classJson = couponBiz.selectCoupon();
		//分页优惠券集合
		List<MallCoupon> coupons = couponBiz.selectCoupon(page, rows);
		//将分页集合转换成json格式
		String couponsJson = JSONArray.fromObject(coupons).toString();
		//数量，分页集合
		couponsJson = "{\"total\":"+classJson.size()+",\"rows\":"+couponsJson+"}";
		//响应回客户端
		out.write(couponsJson);
		//关闭流
		out.close();
	}
	/**
	 * 赠送优惠卷
	 * @param request 请求
	 * @param response 相应
	 * @throws IOException 异常
	 */
	public void updateCoupon(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取优惠券id
		int couponid = Integer.parseInt(request.getParameter("couponId"));
		//获取用户id
		int userid = Integer.parseInt(request.getParameter("id"));
		//调用修改优惠券方法
		int count = couponBiz.updateCoupon(couponid,userid);
		//创建输出流
		PrintWriter out = response.getWriter();
		if (count > 0) {
			out.println("赠送商品成功！");
		}else{
			out.println("赠送商品失败！");
		}
		out.close();
	}
	/**
	 * 根据编号删除数据
	 * @param request 请求
	 * @param response 相应
	 * @throws IOException 异常
	 */
	public void Coupondelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取id
		int id = Integer.parseInt(request.getParameter("id"));
		//调用根据id删除优惠券方法
		int count = couponBiz.deleteId(id);
		//创建输出流
		PrintWriter out = response.getWriter();
		//如果大于0
		if (count > 0) {
			out.println("删除商品成功！");
		}else{
			out.println("删除商品失败！");
		}
		//关闭流
		out.close();
	}
	
	/**
	 * 新增优惠券
	 * @param request 请求
	 * @param response 相应
	 * @throws IOException 异常
	 */
	public void CouponInsert(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取请求中的参数值
		int id = Integer.parseInt(request.getParameter("id"));//金额数量
		String depict = request.getParameter("depict");//优惠券描述
		int money = Integer.parseInt(request.getParameter("money"));//金额数量
		String couponName = request.getParameter("couponName");//优惠券名称
		String couponGetTime = request.getParameter("couponGetTime");//优惠券获得时间
		String couponUse = request.getParameter("couponUse");//是否可使用
		String createTime = request.getParameter("createTime");//创建时间
		String updateTime = request.getParameter("updateTime"); //更新 时间
		//创建优惠券对象
		MallCoupon coupon = new MallCoupon(id, depict, money, couponName, couponGetTime, couponUse, createTime, updateTime);
		//调用新增方法
		int count = couponBiz.insertCoupon(coupon);
		//创建输出流
		PrintWriter out = response.getWriter();
		//如果大于0
		if (count > 0) {
			out.println("新增商品成功！");
		}else{
			out.println("新增商品失败！");
		}
		//关闭流
		out.close();
	}
	
	/**
	 * ajax获取优惠券
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException  异常
	 */
	private void ajaxGetCouponUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out = response.getWriter();
		//从session获取用户信息
		HttpSession session = request.getSession();
		//从session中获取用户信息 保存到登录_用户对象中
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//如果用户等于空
		if (user == null) {
			//输出0
			out.println(0);
		}else {
			//创建优惠券集合，调用获取优惠券方法（获取用户id）
			List<MallCoupon> coupons = couponBiz.getCouponByUserId(user.getId());
			//输出优惠券数量
			out.println(coupons.size());
		}
	}
	/**
	 * 根据用户获取优惠卷
	 * @param request请求
	 * @param response相应
	 */
	private void getCouponUser(HttpServletRequest request,
			HttpServletResponse response) {
		
		//创建session
		HttpSession session = request.getSession();
		//从session中获取用户信息 保存到登录_用户对象中
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//如果用户等于空
		if (user == null) {
			try {
				//r2重定向登录网页s
				response.sendRedirect("login.jsp");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block e.p
				e.printStackTrace();
			}
		}else{
			//调用根据用户获取优惠券方法 (获取用户编号)，用一个优惠券集合保存
			List<MallCoupon> coupons = couponBiz.getCouponByUserId(user.getId());
			//r1请求作用域setA  请求 2优惠券.数量
			request.setAttribute("COUPON_SIZE", coupons.size());
			//r1请求作用域setA  优惠券集合  优惠券集合对象
			request.setAttribute("COUPON_LIST", coupons);
			
			try {
				//转发 1请求.gReq优惠券网页.f
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
	 * 生成随机用户名，数字和字母组成  
	 * @param length 长度
	 * @return返回字符串
	 */
    public String getStringRandom(int length) {  
        //声明字符串并赋值空
        String val = "";  
        //声明随机对象
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
            // 声明字符串 ON,随机对象.nI,如果随机出的数字 余 2 等于0？ 就 为  char  否or则 为  num
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum)) {  
                //声明整数 temp ，随机.nI2余 2 等于0？， 输出是大写字母(65)还是小写字母(97)  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97; 
                //字符串加等(字符类型)(随机对象.nI26加temp)
                val += (char)(random.nextInt(26) + temp);  
                //数字
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }

}
