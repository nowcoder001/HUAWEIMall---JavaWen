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
 * ǰ����Ʒsetvlet
 * @author С��ħ
 *
 */
public class HtmlProductServlet extends HttpServlet {

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
		
		if ("htmlGetProductByName".equals(mod)) {   //ǰ��������Ʒ
			htmlGetProductByName(request,response);
		}else if ("htmlGetProById".equals(mod)) {  //ǰ�˸���id��ѯ��Ʒ
			htmlGetProById(request,response);
		}else if ("userLogin".equals(mod)) {   //�����ѯ�û��Ƿ��ѵ�¼
			userLogin(request,response);
		}else if ("categoryProduct".equals(mod)) {   //��ҳ�����ѯ��Ʒ
			categoryProduct(request,response);
		}else if ("recommendProduct".equals(mod)) {   //������Ʒ���
			recommendProduct(request,response);
		}
		
	}
	/**
	 * ������Ʒ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void recommendProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡȫ����Ʒ
		List<MallProduct> products = productBiz.getAllProduct();
		//������Ʒ
		List<MallProduct> mallProducts = new ArrayList<MallProduct>();
		
		for (int i = 0; i < products.size(); i++) {
			Random ran = new Random();
			//���������
			int num = ran.nextInt(products.size());
			for (int j = 0; j < products.size(); j++) {
				
				if (num == j) {
					
					mallProducts.add(products.get(j));
					
				}
				
			}
			
			
			//������Ʒ��8���Ƚ���ѭ��
			if (mallProducts.size() == 8) {
				break;
			}
		}
		String proJson = JSONArray.fromObject(mallProducts).toString();
		
		out.println(proJson);
		out.close();
	}
	/**
	 * ��ҳ�����ѯ��Ʒ
	 * @param request
	 * @param response
	 */
	private void categoryProduct(HttpServletRequest request,
			HttpServletResponse response) {
		
		//��ȡ�����еĲ���ֵ
		int catId = Integer.parseInt(request.getParameter("cat_id"));
		String catName = request.getParameter("cat_name");
		
		List<MallProduct> products = productBiz.getProductByCatId(catId);
		
		
		
	}
	/**
	 * �����ѯ�û��Ƿ��ѵ�¼
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void userLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��session��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//����û���Ϣ���ڿ�
		if (user == null) {
			out.println("0");  //δ��¼
		}else{
			out.println("1");  //�ѵ�½
		}
		out.close();
	}
	/**
	 * ǰ�˸���id��ѯ��Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void htmlGetProById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���
		int proId = Integer.parseInt(request.getParameter("pro_id"));
		
		MallProduct product = productBiz.getProductById(proId);
		String proJson = JSONArray.fromObject(product).toString();
		
		out.println(proJson);
		
		out.close();
	}
	/**
	 * ǰ��������Ʒ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void htmlGetProductByName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<MallProduct> products = new ArrayList<MallProduct>();  //��Ʒ����
		//��ȡ�����еĲ���ֵ
		String proName = request.getParameter("pro_name");  //��Ʒ����
		
		String catName = request.getParameter("cat_name");  //��������
		
		String minPriceStr = request.getParameter("min_price");  //��С�۸�
		
		String categoryIdStr = request.getParameter("categoryId");   //���ַ����id
		
		if (minPriceStr != null && minPriceStr != "") {  //������ѯ
			
			int maxPrice = Integer.parseInt(request.getParameter("max_price"));   //���۸�
			int minPrice = Integer.parseInt(minPriceStr);
			
			products = productBiz.getProductByPrice(minPrice, maxPrice);
			
		}else if(categoryIdStr != null && categoryIdStr != ""){   //�����ѯ
			String[] catIdStr = categoryIdStr.split(",");
			//���ַ�������ת��int����
			int catId[] = new int[catIdStr.length];
			for (int i = 0; i < catIdStr.length; i++) {
				catId[i] = Integer.parseInt(catIdStr[i]);
			}
			
			products = productBiz.getProductByCatId(catId);
			
		}else{  //������������ѯ
			
			//��������������
			if (catName == null || catName == "") {
				if ("��Ϊ".equals(proName) || proName == null) {  //��ȡ���ǻ�Ϊ
					//��ȡ���е���Ʒ
					products = productBiz.getAllProduct();
					proName = "��Ϊ";
				}else{
					//proName = new String(proName.getBytes("iso8859-1"),"utf-8");
					if ("�ֻ�".equals(proName)) {  //�ֻ�̫�����id�鷳
						
						
					}else if ("����".equals(proName)) {
						int[] cat = {2,9,10};
						//��ѯ����
						products = productBiz.getProductByCatId(cat);
					}else{
						products = productBiz.getProductByName(proName);
					}
					
				}
			}else {
				proName = catName;
				proName = new String(proName.getBytes("ISO-8859-1"),"UTF-8");
				
				if (proName.equals("�ʼǱ�����")) {
					int[] cat = {2,9,10};
					//��ѯ����
					products = productBiz.getProductByCatId(cat);
				}else {
					int catId = Integer.parseInt(request.getParameter("cat_id"));
					products = productBiz.getProductByCatId(catId);
				}
				
			}
		}
		//ȡ�����ϵ�ͼƬ·��
		for (int i = 0; i < products.size(); i++) {
			JSONArray proJson = JSONArray.fromObject(products.get(i).getMallImages());
			JSONObject obj = proJson.getJSONObject(0);
			//����ͼƬ·����ֵ
			products.get(i).setImgUrl((String)obj.get("url"));
			
		}
		if (products.size() < 1) {
			request.setAttribute("PRODUCT_MESSAGE", "�˷����µ���Ʒ��ʱδ���Ŷ��");
		}
		//����������
		request.setAttribute("PRODUCT_LIST", products);  //��Ʒ����
		request.setAttribute("PRO_NAME", proName);
		//��ת
		request.getRequestDispatcher("search.jsp").forward(request, response);
		
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
