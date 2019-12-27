package com.yidu.mall.product.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import sun.misc.BASE64Decoder;


import com.yidu.mall.product.biz.ProductBiz;
import com.yidu.mall.product.model.MallAttrValue;
import com.yidu.mall.product.model.MallCategory;
import com.yidu.mall.product.model.MallProduct;
import com.yidu.mall.product.model.Product;
import com.yidu.mall.product.model.ProductImg;

/**
 * 商品servlet
 * @author 小恶魔
 *
 */
public class ProductServlet extends HttpServlet {

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
		if ("product".equals(mod)) {  //查询出所有商品
			getAllProduct(request,response);
		}else if ("addProduct".equals(mod)) {  //添加商品
			addProduct(request,response);
		}else if ("getAllSpecs".equals(mod)) {  //获取所有规格信息
			getAllSpecs(request,response);
		}else if ("delete_pro".equals(mod)) {  //根据id删除商品
			deleteProduct(request,response);
		}else if ("getProductById".equals(mod)) {  //根据id获取商品
			getProductById(request,response);
		}else if ("updatePro".equals(mod)) {   //根据id修改商品
			updateProById(request,response);
		}else if ("indexTopProduct".equals(mod)) {   //首页 热销上方的四个商品
			indexTopProduct(request,response);
		}
	}
	
	/**
	 * 首页 热销上方的四个商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void indexTopProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//设置4个商品初始值
		HttpSession session = request.getSession();
		String[] imgUrlAttay = (String[])session.getAttribute("IMG_ARRAY");
		int[] productId = (int[])session.getAttribute("PRODUCT_ID_ARRAY");
		
		if (imgUrlAttay == null) {
			//获取初始值
			imgUrlAttay = getSessionUrl(request);
			productId = getSessionId(request);
			
		}
		
		//获取请求中的参数值
		String idStr = request.getParameter("product_id");
		String imgUrl = request.getParameter("img_url");
		//修改商品的操作
		if (idStr != null && idStr != "") {
			int index = Integer.parseInt(request.getParameter("index"));
			int proId = Integer.parseInt(idStr);
			productId[index] = proId;
			
		}
		//修改显示图片操作
		if (imgUrl != null && imgUrl != "") {
			int index = Integer.parseInt(request.getParameter("index"));
			String base64 = request.getParameter("base64");
			GenerateImage(base64, imgUrl);
			imgUrlAttay[index] = "static/product_image/"+imgUrl;
		}
		
		PrintWriter out = response.getWriter();
		
		//设置默认的四个商品
		List<MallProduct> mallProducts = new ArrayList<MallProduct>();  
		//取出所有商品
		List<MallProduct> products = productBiz.getAllProduct();
		
		//把对应商品取出
		for (int i = 0; i < products.size(); i++) {
			
			for (int j = 0; j < productId.length; j++) {
				if (productId[j] == products.get(i).getId()) {
					mallProducts.add(products.get(i));
					
				}
			}
			
		}
		//把显示的图片放入集合
		for (int i = 0; i < mallProducts.size(); i++) {
			mallProducts.get(i).setShowImage(imgUrlAttay[i]);
		}
		String productJson = JSONArray.fromObject(mallProducts).toString();
		
		out.println(productJson);
		
		out.close();
	}
	/**
	 * 根据id 修改商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateProById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数值
		int proId = Integer.parseInt(request.getParameter("proId"));
		String proName = request.getParameter("pro_name");   //商品名称
		String proCategoryIdStr = request.getParameter("pro_category");   //商品分类
		int proCategoryId = Integer.parseInt(proCategoryIdStr);
		int proStatus = Integer.parseInt(request.getParameter("pro_status"));   //商品状态
		String proPriceStr = request.getParameter("pro_price");   //商品价格
		double proPrice = Double.parseDouble(proPriceStr);
		String proStockStr = request.getParameter("pro_stock");   //商品库存
		int proStock = Integer.parseInt(proStockStr);
		String proDetail = request.getParameter("pro_detail");   //商品详情参数
		//声明字符串数组  商品图片
		String[] proImgName = new String[5];
				
		//获取图片base64数据   
		String[] imgSrc = new String[5];
		//循环转成图片
		for (int i = 0; i < 5; i++) {
			proImgName[i] = request.getParameter("fileName"+(i+1));
			imgSrc[i] = request.getParameter("imgBase"+(i+1));
			//由base64转成图片
			GenerateImage(imgSrc[i],proImgName[i]);
		}
		//创建商品主图片实体对象集合转json格式
		List<ProductImg> productImgs = new ArrayList<ProductImg>();
		//创建商品副图片实体对象集合转json格式
		List<ProductImg> minImages = new ArrayList<ProductImg>();
		//循环集合添加主副图图片实体对象
		for (int i = 0; i < 5; i++) {
			//主图
			productImgs.add(new ProductImg(proName,"static/product_image/"+proImgName[i],1));
			//副图
			minImages.add(new ProductImg(proName,"static/product_image/"+proImgName[i],2));
		}
				
		//主图的json格式
		String maxImg = JSONArray.fromObject(productImgs).toString();
		//副图的json格式
		String minImg = JSONArray.fromObject(minImages).toString();
		//创建商品实体类对象
		MallProduct product = new MallProduct(proId, proName, null, proName, maxImg, minImg, proDetail, proDetail, proPrice, proStock, proStatus);
		//创建商品分类实体对象
		MallCategory category = new MallCategory();
		category.setId(proCategoryId);
		product.setCategory(category);
		
		int count = productBiz.updateProById(product);
		
		if (count > 0) {
			out.println("商品修改成功！！！");
		}else{
			out.println("商品修改失败！！！");
		}
		out.close();
	}
	/**
	 * 根据id获取商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getProductById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		//获取请求的参数值
		int proId = Integer.parseInt(request.getParameter("proId"));
		
		MallProduct product = productBiz.getProductById(proId);
		
		//集合转成json格式
		String productJson = JSONArray.fromObject(product).toString();
		
		out.println(productJson);
		out.close();
	}

	/**
	 * 根据id删除商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数值
		int proId = Integer.parseInt(request.getParameter("proId"));
		
		int count = productBiz.deleteProByProductId(proId);
		
		if (count > 0) {
			out.println("删除商品成功！");
		}else{
			out.println("删除商品失败！");
		}
		out.close();
	}
	/**
	 * 获取所有规格信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllSpecs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取所有规格信息
		List<MallAttrValue> attrValues = productBiz.getAllAttr();
		//获取请求中的参数值
		String attrKey = request.getParameter("attrKey");
		//包括内存的规格集合
		if (attrKey.equals("RAM")) {
			//包括内存的规格集合
			List<MallAttrValue> attrRAM = new ArrayList<MallAttrValue>();
			//内存的json格式
			for (MallAttrValue mallAttrValue : attrValues) {
				if (mallAttrValue.getAttrKeyName().equals("内存")) {
					attrRAM.add(mallAttrValue);
				}
			}
			//集合转成json格式
			String attrJson = JSONArray.fromObject(attrRAM).toString();
			out.println(attrJson);
		}else{  //颜色的规格集合
			//包括内存的规格集合
			List<MallAttrValue> attrColor = new ArrayList<MallAttrValue>();
			//内存的json格式
			for (MallAttrValue mallAttrValue : attrValues) {
				if (mallAttrValue.getAttrKeyName().equals("颜色")) {
					attrColor.add(mallAttrValue);
				}
			}
			//集合转成json格式
			String attrJson = JSONArray.fromObject(attrColor).toString();
			out.println(attrJson);
		}
		
		out.close();
		
	}
	/**
	 * 添加商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void addProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数值
		String proName = request.getParameter("pro_name");   //商品名称
		String proCategoryIdStr = request.getParameter("pro_category");   //商品分类
		int proCategoryId = Integer.parseInt(proCategoryIdStr);
		int proStatus = Integer.parseInt(request.getParameter("pro_status"));   //商品状态
		String proPriceStr = request.getParameter("pro_price");   //商品价格
		double proPrice = Double.parseDouble(proPriceStr);
		String proStockStr = request.getParameter("pro_stock");   //商品库存
		int proStock = Integer.parseInt(proStockStr);
		String proDetail = request.getParameter("pro_detail");   //商品详情参数
		
		//声明字符串数组  商品图片
		String[] proImgName = new String[5];
		
		//获取图片base64数据   
		String[] imgSrc = new String[5];
		//循环转成图片
		for (int i = 0; i < 5; i++) {
			proImgName[i] = request.getParameter("fileputHB"+(i+1));
			imgSrc[i] = request.getParameter("imgBase"+(i+1));
			//由base64转成图片
			GenerateImage(imgSrc[i],proImgName[i]);
			//改变图片的大小用于小图显示
			changeSize(proImgName[i]);
		}
		//创建商品主图片实体对象集合转json格式
		List<ProductImg> productImgs = new ArrayList<ProductImg>();
		//创建商品副图片实体对象集合转json格式
		List<ProductImg> minImages = new ArrayList<ProductImg>();
		//循环集合添加主副图图片实体对象
		for (int i = 0; i < 5; i++) {
			//主图
			productImgs.add(new ProductImg(proName,"static/product_image/"+proImgName[i],1));
			//副图
			minImages.add(new ProductImg(proName,"static/product_image/"+proImgName[i],2));
			
		}
		//主图的json格式
		String maxImg = JSONArray.fromObject(productImgs).toString();
		//副图的json格式
		String minImg = JSONArray.fromObject(minImages).toString();
		//生成商品唯一编码
		String coding = getRandom(9);
		//创建商品实体类对象
		MallProduct product = new MallProduct(0, proName, coding, proName, maxImg, minImg, proDetail, proDetail, proPrice, proStock, proStatus);
		//创建商品分类实体对象
		MallCategory category = new MallCategory();
		category.setId(proCategoryId);
		product.setCategory(category);
		//调用业务逻辑层添加商品方法
		int count = productBiz.addProduct(product);
		
		if (count > 0) {
			out.println("新增商品成功！");
		}else{
			out.println("新增商品失败！");
		}
		out.close();
	}
	/**
	 * 获取全部商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数
		String proName = request.getParameter("proName");
		
		if (proName == null || "".equals(proName)) { //获取全部商品
			//获取请求中的分页参数
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			List<MallProduct> pageProducts = productBiz.getPageProduct(page, rows);
			List<MallProduct> products = productBiz.getAllProduct();
			//集合转成json格式
			String productJson = JSONArray.fromObject(pageProducts).toString();
			//拼接DataGrid网格需要的json数据格式
			productJson = "{\"total\":"+products.size()+",\"rows\":"+productJson+"}";
			//响应会客户端
			out.write(productJson);
		}else{   //模糊搜索商品
			
			List<MallProduct> mallProducts = productBiz.getProductByName(proName);
			
			//集合转成json格式
			String productJson = JSONArray.fromObject(mallProducts).toString();
			//响应会客户端
			out.write(productJson);
		}
		out.close();
	}
	/**
	 * 生成随机数 - 商品编号
	 * @param len
	 * @return
	 */
	public static String getRandom(int len) {
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < len; i++) {
            rs.append(r.nextInt(10));
        }
        return rs.toString();
    }
	
	/**
	 * base64字符串转化成图片  
	 * @param imgStr
	 * @return
	 */
    public static boolean GenerateImage(String imgStr,String imgName)  {   //对字节数组字符串进行Base64解码并生成图片  
    	//判断图片的后缀名
    	int index = imgName.indexOf(".");
    	String newImgStr = imgName.substring(index+1);
    	
    	imgStr = imgStr.replaceAll("data:image/"+newImgStr+";base64,", "");
        if (imgStr == null) //图像数据为空  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgFilePath = "E:\\Java\\原创项目\\project\\HuaweiMall\\HuaweiMall\\WebRoot\\static\\product_image\\"+imgName;//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }
    /**
     * 改变图片的尺寸
     *
     * @param newWidth, newHeight, path
     * @return boolean
     */
    public boolean changeSize(String path) {
    	
    	String filePath = "E:\\Java\\原创项目\\project\\HuaweiMall\\HuaweiMall\\WebRoot\\static\\product_image\\";
    	//判断图片的后缀名
    	int index = path.indexOf(".");
    	String newImgStr = path.substring(index+1);
    	
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath+path));

            //字节流转图片对象
            Image bi = ImageIO.read(in);
            //构建图片流
            BufferedImage tag = new BufferedImage(78, 78, BufferedImage.TYPE_INT_RGB);
            //绘制改变尺寸后的图
            tag.getGraphics().drawImage(bi, 0, 0, 78, 78, null);
            //改变文件名
        	path = path.replaceAll("428_428","78_78");
            //输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filePath+path));
            
            ImageIO.write(tag, newImgStr, out);
            in.close();
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
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
