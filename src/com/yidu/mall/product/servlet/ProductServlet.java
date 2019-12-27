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
 * ��Ʒservlet
 * @author С��ħ
 *
 */
public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	//����ҵ���߼������
	ProductBiz productBiz = new ProductBiz();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//��ȡ�����еĲ���ֵ
		String mod = request.getParameter("mod");
		if ("product".equals(mod)) {  //��ѯ��������Ʒ
			getAllProduct(request,response);
		}else if ("addProduct".equals(mod)) {  //�����Ʒ
			addProduct(request,response);
		}else if ("getAllSpecs".equals(mod)) {  //��ȡ���й����Ϣ
			getAllSpecs(request,response);
		}else if ("delete_pro".equals(mod)) {  //����idɾ����Ʒ
			deleteProduct(request,response);
		}else if ("getProductById".equals(mod)) {  //����id��ȡ��Ʒ
			getProductById(request,response);
		}else if ("updatePro".equals(mod)) {   //����id�޸���Ʒ
			updateProById(request,response);
		}else if ("indexTopProduct".equals(mod)) {   //��ҳ �����Ϸ����ĸ���Ʒ
			indexTopProduct(request,response);
		}
	}
	
	/**
	 * ��ҳ �����Ϸ����ĸ���Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void indexTopProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//����4����Ʒ��ʼֵ
		HttpSession session = request.getSession();
		String[] imgUrlAttay = (String[])session.getAttribute("IMG_ARRAY");
		int[] productId = (int[])session.getAttribute("PRODUCT_ID_ARRAY");
		
		if (imgUrlAttay == null) {
			//��ȡ��ʼֵ
			imgUrlAttay = getSessionUrl(request);
			productId = getSessionId(request);
			
		}
		
		//��ȡ�����еĲ���ֵ
		String idStr = request.getParameter("product_id");
		String imgUrl = request.getParameter("img_url");
		//�޸���Ʒ�Ĳ���
		if (idStr != null && idStr != "") {
			int index = Integer.parseInt(request.getParameter("index"));
			int proId = Integer.parseInt(idStr);
			productId[index] = proId;
			
		}
		//�޸���ʾͼƬ����
		if (imgUrl != null && imgUrl != "") {
			int index = Integer.parseInt(request.getParameter("index"));
			String base64 = request.getParameter("base64");
			GenerateImage(base64, imgUrl);
			imgUrlAttay[index] = "static/product_image/"+imgUrl;
		}
		
		PrintWriter out = response.getWriter();
		
		//����Ĭ�ϵ��ĸ���Ʒ
		List<MallProduct> mallProducts = new ArrayList<MallProduct>();  
		//ȡ��������Ʒ
		List<MallProduct> products = productBiz.getAllProduct();
		
		//�Ѷ�Ӧ��Ʒȡ��
		for (int i = 0; i < products.size(); i++) {
			
			for (int j = 0; j < productId.length; j++) {
				if (productId[j] == products.get(i).getId()) {
					mallProducts.add(products.get(i));
					
				}
			}
			
		}
		//����ʾ��ͼƬ���뼯��
		for (int i = 0; i < mallProducts.size(); i++) {
			mallProducts.get(i).setShowImage(imgUrlAttay[i]);
		}
		String productJson = JSONArray.fromObject(mallProducts).toString();
		
		out.println(productJson);
		
		out.close();
	}
	/**
	 * ����id �޸���Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateProById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		int proId = Integer.parseInt(request.getParameter("proId"));
		String proName = request.getParameter("pro_name");   //��Ʒ����
		String proCategoryIdStr = request.getParameter("pro_category");   //��Ʒ����
		int proCategoryId = Integer.parseInt(proCategoryIdStr);
		int proStatus = Integer.parseInt(request.getParameter("pro_status"));   //��Ʒ״̬
		String proPriceStr = request.getParameter("pro_price");   //��Ʒ�۸�
		double proPrice = Double.parseDouble(proPriceStr);
		String proStockStr = request.getParameter("pro_stock");   //��Ʒ���
		int proStock = Integer.parseInt(proStockStr);
		String proDetail = request.getParameter("pro_detail");   //��Ʒ�������
		//�����ַ�������  ��ƷͼƬ
		String[] proImgName = new String[5];
				
		//��ȡͼƬbase64����   
		String[] imgSrc = new String[5];
		//ѭ��ת��ͼƬ
		for (int i = 0; i < 5; i++) {
			proImgName[i] = request.getParameter("fileName"+(i+1));
			imgSrc[i] = request.getParameter("imgBase"+(i+1));
			//��base64ת��ͼƬ
			GenerateImage(imgSrc[i],proImgName[i]);
		}
		//������Ʒ��ͼƬʵ����󼯺�תjson��ʽ
		List<ProductImg> productImgs = new ArrayList<ProductImg>();
		//������Ʒ��ͼƬʵ����󼯺�תjson��ʽ
		List<ProductImg> minImages = new ArrayList<ProductImg>();
		//ѭ�������������ͼͼƬʵ�����
		for (int i = 0; i < 5; i++) {
			//��ͼ
			productImgs.add(new ProductImg(proName,"static/product_image/"+proImgName[i],1));
			//��ͼ
			minImages.add(new ProductImg(proName,"static/product_image/"+proImgName[i],2));
		}
				
		//��ͼ��json��ʽ
		String maxImg = JSONArray.fromObject(productImgs).toString();
		//��ͼ��json��ʽ
		String minImg = JSONArray.fromObject(minImages).toString();
		//������Ʒʵ�������
		MallProduct product = new MallProduct(proId, proName, null, proName, maxImg, minImg, proDetail, proDetail, proPrice, proStock, proStatus);
		//������Ʒ����ʵ�����
		MallCategory category = new MallCategory();
		category.setId(proCategoryId);
		product.setCategory(category);
		
		int count = productBiz.updateProById(product);
		
		if (count > 0) {
			out.println("��Ʒ�޸ĳɹ�������");
		}else{
			out.println("��Ʒ�޸�ʧ�ܣ�����");
		}
		out.close();
	}
	/**
	 * ����id��ȡ��Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getProductById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		//��ȡ����Ĳ���ֵ
		int proId = Integer.parseInt(request.getParameter("proId"));
		
		MallProduct product = productBiz.getProductById(proId);
		
		//����ת��json��ʽ
		String productJson = JSONArray.fromObject(product).toString();
		
		out.println(productJson);
		out.close();
	}

	/**
	 * ����idɾ����Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		int proId = Integer.parseInt(request.getParameter("proId"));
		
		int count = productBiz.deleteProByProductId(proId);
		
		if (count > 0) {
			out.println("ɾ����Ʒ�ɹ���");
		}else{
			out.println("ɾ����Ʒʧ�ܣ�");
		}
		out.close();
	}
	/**
	 * ��ȡ���й����Ϣ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllSpecs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ���й����Ϣ
		List<MallAttrValue> attrValues = productBiz.getAllAttr();
		//��ȡ�����еĲ���ֵ
		String attrKey = request.getParameter("attrKey");
		//�����ڴ�Ĺ�񼯺�
		if (attrKey.equals("RAM")) {
			//�����ڴ�Ĺ�񼯺�
			List<MallAttrValue> attrRAM = new ArrayList<MallAttrValue>();
			//�ڴ��json��ʽ
			for (MallAttrValue mallAttrValue : attrValues) {
				if (mallAttrValue.getAttrKeyName().equals("�ڴ�")) {
					attrRAM.add(mallAttrValue);
				}
			}
			//����ת��json��ʽ
			String attrJson = JSONArray.fromObject(attrRAM).toString();
			out.println(attrJson);
		}else{  //��ɫ�Ĺ�񼯺�
			//�����ڴ�Ĺ�񼯺�
			List<MallAttrValue> attrColor = new ArrayList<MallAttrValue>();
			//�ڴ��json��ʽ
			for (MallAttrValue mallAttrValue : attrValues) {
				if (mallAttrValue.getAttrKeyName().equals("��ɫ")) {
					attrColor.add(mallAttrValue);
				}
			}
			//����ת��json��ʽ
			String attrJson = JSONArray.fromObject(attrColor).toString();
			out.println(attrJson);
		}
		
		out.close();
		
	}
	/**
	 * �����Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void addProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���ֵ
		String proName = request.getParameter("pro_name");   //��Ʒ����
		String proCategoryIdStr = request.getParameter("pro_category");   //��Ʒ����
		int proCategoryId = Integer.parseInt(proCategoryIdStr);
		int proStatus = Integer.parseInt(request.getParameter("pro_status"));   //��Ʒ״̬
		String proPriceStr = request.getParameter("pro_price");   //��Ʒ�۸�
		double proPrice = Double.parseDouble(proPriceStr);
		String proStockStr = request.getParameter("pro_stock");   //��Ʒ���
		int proStock = Integer.parseInt(proStockStr);
		String proDetail = request.getParameter("pro_detail");   //��Ʒ�������
		
		//�����ַ�������  ��ƷͼƬ
		String[] proImgName = new String[5];
		
		//��ȡͼƬbase64����   
		String[] imgSrc = new String[5];
		//ѭ��ת��ͼƬ
		for (int i = 0; i < 5; i++) {
			proImgName[i] = request.getParameter("fileputHB"+(i+1));
			imgSrc[i] = request.getParameter("imgBase"+(i+1));
			//��base64ת��ͼƬ
			GenerateImage(imgSrc[i],proImgName[i]);
			//�ı�ͼƬ�Ĵ�С����Сͼ��ʾ
			changeSize(proImgName[i]);
		}
		//������Ʒ��ͼƬʵ����󼯺�תjson��ʽ
		List<ProductImg> productImgs = new ArrayList<ProductImg>();
		//������Ʒ��ͼƬʵ����󼯺�תjson��ʽ
		List<ProductImg> minImages = new ArrayList<ProductImg>();
		//ѭ�������������ͼͼƬʵ�����
		for (int i = 0; i < 5; i++) {
			//��ͼ
			productImgs.add(new ProductImg(proName,"static/product_image/"+proImgName[i],1));
			//��ͼ
			minImages.add(new ProductImg(proName,"static/product_image/"+proImgName[i],2));
			
		}
		//��ͼ��json��ʽ
		String maxImg = JSONArray.fromObject(productImgs).toString();
		//��ͼ��json��ʽ
		String minImg = JSONArray.fromObject(minImages).toString();
		//������ƷΨһ����
		String coding = getRandom(9);
		//������Ʒʵ�������
		MallProduct product = new MallProduct(0, proName, coding, proName, maxImg, minImg, proDetail, proDetail, proPrice, proStock, proStatus);
		//������Ʒ����ʵ�����
		MallCategory category = new MallCategory();
		category.setId(proCategoryId);
		product.setCategory(category);
		//����ҵ���߼��������Ʒ����
		int count = productBiz.addProduct(product);
		
		if (count > 0) {
			out.println("������Ʒ�ɹ���");
		}else{
			out.println("������Ʒʧ�ܣ�");
		}
		out.close();
	}
	/**
	 * ��ȡȫ����Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���
		String proName = request.getParameter("proName");
		
		if (proName == null || "".equals(proName)) { //��ȡȫ����Ʒ
			//��ȡ�����еķ�ҳ����
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			List<MallProduct> pageProducts = productBiz.getPageProduct(page, rows);
			List<MallProduct> products = productBiz.getAllProduct();
			//����ת��json��ʽ
			String productJson = JSONArray.fromObject(pageProducts).toString();
			//ƴ��DataGrid������Ҫ��json���ݸ�ʽ
			productJson = "{\"total\":"+products.size()+",\"rows\":"+productJson+"}";
			//��Ӧ��ͻ���
			out.write(productJson);
		}else{   //ģ��������Ʒ
			
			List<MallProduct> mallProducts = productBiz.getProductByName(proName);
			
			//����ת��json��ʽ
			String productJson = JSONArray.fromObject(mallProducts).toString();
			//��Ӧ��ͻ���
			out.write(productJson);
		}
		out.close();
	}
	/**
	 * ��������� - ��Ʒ���
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
	 * base64�ַ���ת����ͼƬ  
	 * @param imgStr
	 * @return
	 */
    public static boolean GenerateImage(String imgStr,String imgName)  {   //���ֽ������ַ�������Base64���벢����ͼƬ  
    	//�ж�ͼƬ�ĺ�׺��
    	int index = imgName.indexOf(".");
    	String newImgStr = imgName.substring(index+1);
    	
    	imgStr = imgStr.replaceAll("data:image/"+newImgStr+";base64,", "");
        if (imgStr == null) //ͼ������Ϊ��  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64����  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//�����쳣����  
                    b[i]+=256;  
                }  
            }  
            //����jpegͼƬ  
            String imgFilePath = "E:\\Java\\ԭ����Ŀ\\project\\HuaweiMall\\HuaweiMall\\WebRoot\\static\\product_image\\"+imgName;//�����ɵ�ͼƬ  
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
     * �ı�ͼƬ�ĳߴ�
     *
     * @param newWidth, newHeight, path
     * @return boolean
     */
    public boolean changeSize(String path) {
    	
    	String filePath = "E:\\Java\\ԭ����Ŀ\\project\\HuaweiMall\\HuaweiMall\\WebRoot\\static\\product_image\\";
    	//�ж�ͼƬ�ĺ�׺��
    	int index = path.indexOf(".");
    	String newImgStr = path.substring(index+1);
    	
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath+path));

            //�ֽ���תͼƬ����
            Image bi = ImageIO.read(in);
            //����ͼƬ��
            BufferedImage tag = new BufferedImage(78, 78, BufferedImage.TYPE_INT_RGB);
            //���Ƹı�ߴ���ͼ
            tag.getGraphics().drawImage(bi, 0, 0, 78, 78, null);
            //�ı��ļ���
        	path = path.replaceAll("428_428","78_78");
            //�����
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
     * session��ʼ������
     * @return
     */
    public String[] getSessionUrl(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	//���ó�ʼֵ
		String[] imgUrlAttay = new String[4];
		imgUrlAttay[0] = "static/picture/XNZ6iLzxhezRvEFGlvyh.jpg";
		imgUrlAttay[1] = "static/picture/nqtkI7ojKOtXfGkGOZqN.jpg";
		imgUrlAttay[2] = "static/picture/tRaawAXjR20vfjRVXa9E.jpg";
		imgUrlAttay[3] = "static/picture/cms2TAeSlBpW9ljTqNG2.jpg";
		//����session��
		session.setAttribute("IMG_ARRAY", imgUrlAttay);
		return imgUrlAttay;
    	
    }
    /**
     * session��ʼ������
     * @return
     */
    public int[] getSessionId(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	int[] productId = new int[4];
		productId[0] = 1;
		productId[1] = 48;
		productId[2] = 56;
		productId[3] = 54;
		//����session��
		session.setAttribute("PRODUCT_ID_ARRAY", productId);
		return productId;
    }
}
