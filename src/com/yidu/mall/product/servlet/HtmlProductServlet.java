package com.yidu.mall.product.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.yidu.mall.product.biz.ProductBiz;
import com.yidu.mall.product.model.MallProduct;
import com.yidu.mall.user.model.MallUser;
/**
 * 前端商品setvlet
 * @author 小恶魔
 *
 */
public class HtmlProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	//创建业务逻辑层对象
	ProductBiz productBiz = new ProductBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取请求中的参数值
		String mod = request.getParameter("mod");
		
		if ("htmlGetProductByName".equals(mod)) {   //前端搜索商品
			htmlGetProductByName(request,response);
		}else if ("htmlGetProById".equals(mod)) {  //前端根据id查询商品
			htmlGetProById(request,response);
		}else if ("userLogin".equals(mod)) {   //购买查询用户是否已登录
			userLogin(request,response);
		}else if ("categoryProduct".equals(mod)) {   //首页分类查询商品
			categoryProduct(request,response);
		}else if ("recommendProduct".equals(mod)) {   //热销单品随机
			recommendProduct(request,response);
		}
		
	}
	/**
	 * 热销单品随机
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void recommendProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取全部商品
		List<MallProduct> products = productBiz.getAllProduct();
		//热销商品
		List<MallProduct> mallProducts = new ArrayList<MallProduct>();
		
		for (int i = 0; i < products.size(); i++) {
			Random ran = new Random();
			//生成随机数
			int num = ran.nextInt(products.size());
			for (int j = 0; j < products.size(); j++) {
				
				if (num == j) {
					
					mallProducts.add(products.get(j));
					
				}
				
			}
			
			
			//热销单品有8个既结束循环
			if (mallProducts.size() == 8) {
				break;
			}
		}
		String proJson = JSONArray.fromObject(mallProducts).toString();
		
		out.println(proJson);
		out.close();
	}
	/**
	 * 首页分类查询商品
	 * @param request
	 * @param response
	 */
	private void categoryProduct(HttpServletRequest request,
			HttpServletResponse response) {
		
		//获取请求中的参数值
		int catId = Integer.parseInt(request.getParameter("cat_id"));
		String catName = request.getParameter("cat_name");
		
		List<MallProduct> products = productBiz.getProductByCatId(catId);
		
		
		
	}
	/**
	 * 购买查询用户是否已登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void userLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//从session获取用户信息
		HttpSession session = request.getSession();
		
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//如果用户信息等于空
		if (user == null) {
			out.println("0");  //未登录
		}else{
			out.println("1");  //已登陆
		}
		out.close();
	}
	/**
	 * 前端根据id查询商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void htmlGetProById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数
		int proId = Integer.parseInt(request.getParameter("pro_id"));
		
		MallProduct product = productBiz.getProductById(proId);
		String proJson = JSONArray.fromObject(product).toString();
		
		out.println(proJson);
		
		out.close();
	}
	/**
	 * 前端搜索商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void htmlGetProductByName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<MallProduct> products = new ArrayList<MallProduct>();  //商品集合
		//获取请求中的参数值
		String proName = request.getParameter("pro_name");  //商品名字
		
		String catName = request.getParameter("cat_name");  //分类名字
		
		String minPriceStr = request.getParameter("min_price");  //最小价格
		
		String categoryIdStr = request.getParameter("categoryId");   //各种分类的id
		
		if (minPriceStr != null && minPriceStr != "") {  //条件查询
			
			int maxPrice = Integer.parseInt(request.getParameter("max_price"));   //最大价格
			int minPrice = Integer.parseInt(minPriceStr);
			
			products = productBiz.getProductByPrice(minPrice, maxPrice);
			
		}else if(categoryIdStr != null && categoryIdStr != ""){   //分类查询
			String[] catIdStr = categoryIdStr.split(",");
			//将字符串数组转成int类型
			int catId[] = new int[catIdStr.length];
			for (int i = 0; i < catIdStr.length; i++) {
				catId[i] = Integer.parseInt(catIdStr[i]);
			}
			
			products = productBiz.getProductByCatId(catId);
			
		}else{  //不属于条件查询
			
			//这是搜索框搜索
			if (catName == null || catName == "") {
				if ("华为".equals(proName) || proName == null) {  //获取的是华为
					//获取所有的商品
					products = productBiz.getAllProduct();
					proName = "华为";
				}else{
					//proName = new String(proName.getBytes("iso8859-1"),"utf-8");
					if ("手机".equals(proName)) {  //手机太多分类id麻烦
						
						
					}else if ("电脑".equals(proName)) {
						int[] cat = {2,9,10};
						//查询电脑
						products = productBiz.getProductByCatId(cat);
					}else{
						products = productBiz.getProductByName(proName);
					}
					
				}
			}else {
				proName = catName;
				proName = new String(proName.getBytes("ISO-8859-1"),"UTF-8");
				
				if (proName.equals("笔记本电脑")) {
					int[] cat = {2,9,10};
					//查询电脑
					products = productBiz.getProductByCatId(cat);
				}else {
					int catId = Integer.parseInt(request.getParameter("cat_id"));
					products = productBiz.getProductByCatId(catId);
				}
				
			}
		}
		//取出集合的图片路径
		for (int i = 0; i < products.size(); i++) {
			JSONArray proJson = JSONArray.fromObject(products.get(i).getMallImages());
			JSONObject obj = proJson.getJSONObject(0);
			//搜索图片路径赋值
			products.get(i).setImgUrl((String)obj.get("url"));
			
		}
		if (products.size() < 1) {
			request.setAttribute("PRODUCT_MESSAGE", "此分类下的商品暂时未添加哦！");
		}
		//请求作用域
		request.setAttribute("PRODUCT_LIST", products);  //商品集合
		request.setAttribute("PRO_NAME", proName);
		//跳转
		request.getRequestDispatcher("search.jsp").forward(request, response);
		
	}
	/**
     * session初始化设置
     * @return
     */
    public String[] getSessionUrl(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	//设置初始值
		String[] imgUrlAttay = new String[4];
		imgUrlAttay[0] = "static/picture/XNZ6iLzxhezRvEFGlvyh.jpg";
		imgUrlAttay[1] = "static/picture/nqtkI7ojKOtXfGkGOZqN.jpg";
		imgUrlAttay[2] = "static/picture/tRaawAXjR20vfjRVXa9E.jpg";
		imgUrlAttay[3] = "static/picture/cms2TAeSlBpW9ljTqNG2.jpg";
		//放入session里
		session.setAttribute("IMG_ARRAY", imgUrlAttay);
		return imgUrlAttay;
    	
    }
    /**
     * session初始化设置
     * @return
     */
    public int[] getSessionId(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	int[] productId = new int[4];
		productId[0] = 1;
		productId[1] = 48;
		productId[2] = 56;
		productId[3] = 54;
		//放入session里
		session.setAttribute("PRODUCT_ID_ARRAY", productId);
		return productId;
    }
}
