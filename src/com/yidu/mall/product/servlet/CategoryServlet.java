package com.yidu.mall.product.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.yidu.mall.product.biz.CategoryBiz;
import com.yidu.mall.product.model.CategoryNodeDto;
import com.yidu.mall.product.model.MallCategory;

public class CategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	//����ҵ���߼���
	CategoryBiz categoryBiz = new CategoryBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//��ȡ�����еĲ���ֵ
		String mod = request.getParameter("mod");
		
		if ("getCategory".equals(mod)) {  //����������
			getAllCategory(request,response);
		}else if ("getTreeCategory".equals(mod)) {    //�������α��
			getTreeCategory(request,response);
		}else if ("addCategory".equals(mod)) {   //��ӷ���
			addCategory(request,response);
		}else if ("getCatById".equals(mod)) {   //ͨ��id��ѯ��Ʒ�����ϼ�
			getCatById(request,response);
		}else if ("deleteCatById".equals(mod)) {  //����idɾ����Ʒ����
			deleteCatById(request,response);
		}else if ("getCategoryById".equals(mod)) {   //����id��ѯ��Ʒ����
			getCategoryById(request,response);
		}else if ("updateCatById".equals(mod)) {  //����id�޸���Ʒ����
			updateCatById(request,response);
		}else if ("htmlGetCategorys".equals(mod)) {  //ǰ�˷����ѯ
			htmlGetCategorys(request,response);
		}
		
	}
	/**
	 * ǰ̨�����ѯ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void htmlGetCategorys(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		//��ȡ������Ʒ����
		List<MallCategory> categoriesTop = categoryBiz.getCategory(0);
		
		//��ȡ������Ʒ����
		List<MallCategory> categoriesThree = categoryBiz.getCategory(2);
		//��������Ʒ������붥����Ʒ����
		for (MallCategory category : categoriesTop) {
			List<MallCategory> category2 = new ArrayList<MallCategory>();
			for (MallCategory mallCategory : categoriesThree) {
				
				if (mallCategory.getManagerId() == category.getId()) {
					
					category2.add(mallCategory);
					
					category.setCategories(category2);
				}
				
			}
		}
		
		String catJson = JSONArray.fromObject(categoriesTop).toString();
		
		out.println(catJson);
	}
	/**
	 * ����id�޸���Ʒ����
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateCatById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		//��ȡ�����еĲ���
		int catId = Integer.parseInt(request.getParameter("catId"));
		String catName = request.getParameter("cat_name");
		String isMax = request.getParameter("isMax");  //��ȡ������ť��ֵ
		int categoryId = 0;  //����id
		int catSort = Integer.parseInt(request.getParameter("cat_sort"));
		int catStatus = Integer.parseInt(request.getParameter("cat_status"));
		int parentId = 0;  //��id
		if ("on".equals(isMax)) {  //�����on��˵�����½��ķ����Ƕ���
			categoryId = 0;
		}else{
			categoryId = Integer.parseInt(request.getParameter("category"));
		}
		//��ȡȫ�����༯��
		List<MallCategory> categories = categoryBiz.getAllCatrgory();
		for (MallCategory category : categories) {
			if (category.getId() == categoryId) {
				parentId = category.getParentId() +1;  //�˷�������Ǹ���   ��+1
			}
		}
		MallCategory category = new MallCategory(catId, parentId, catName, catStatus, catSort);
		category.setManagerId(categoryId);
		
		int count = categoryBiz.updateCatById(category);
		
		if (count > 0 ) {
			out.println("�޸ķ���ɹ�������");
		}else{
			out.println("�޸ķ���ʧ�ܣ�����");
		}
	}
	/**
	 * ����id��ѯ��Ʒ����
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getCategoryById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ����Ĳ���ֵ
		int catId = Integer.parseInt(request.getParameter("catId"));
		
		MallCategory category = categoryBiz.getCategoryById(catId);
		
		String catJson = JSONArray.fromObject(category).toString();
		
		out.write(catJson);
	}
	/**
	 * ����idɾ����Ʒ����
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteCatById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���
		int categoryId = Integer.parseInt(request.getParameter("catId"));
		int count = categoryBiz.deleteCatById(categoryId);
		if (count > 0 ) {
			out.println("ɾ������ɹ�����");
		}else{
			out.println("ɾ������ʧ�ܣ���");
		}
	}
	/**
	 * ͨ��id��ѯ��Ʒ����
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getCatById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		//��ȡ�����еĲ���ֵ
		int id = Integer.parseInt(request.getParameter("id"));
		String categoryName = "";  //��������
		int managerId = 0;  //�ϼ�id
		if (id > 6) {
			//��ȡ���з���
			List<MallCategory> categories = categoryBiz.getAllCatrgory();
			//ѭ�������id ���ϼ�id
			for (MallCategory category : categories) {
				if (category.getId() == id) {
					managerId = category.getManagerId();
				}
			}
			//ѭ��������ϼ�id������
			for (MallCategory category : categories) {
				if (managerId == category.getId()) {
					categoryName = category.getName();
				}
			}
		}else{
			categoryName = "���ϼ�";
		}
		
		
		out.write(categoryName);
		out.close();
	}
	/**
	 * ��ӷ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡ�����еĲ���
		String catName = request.getParameter("cat_name");
		String isMax = request.getParameter("isMax");  //��ȡ������ť��ֵ
		int categoryId = 0;  //����id
		int catSort = Integer.parseInt(request.getParameter("cat_sort"));
		int catStatus = Integer.parseInt(request.getParameter("cat_status"));
		int parentId = 0;  //��id
		if ("on".equals(isMax)) {  //�����on��˵�����½��ķ����Ƕ���
			categoryId = 0;
		}else{
			categoryId = Integer.parseInt(request.getParameter("category"));
		}
		//��ȡȫ�����༯��
		List<MallCategory> categories = categoryBiz.getAllCatrgory();
		for (MallCategory category : categories) {
			if (category.getId() == categoryId) {
				parentId = category.getParentId() +1;  //�˷�������Ǹ���   ��+1
			}
		}
		MallCategory category = new MallCategory(0, parentId, catName, catStatus, catSort);
		category.setManagerId(categoryId);
		int count = categoryBiz.addCategory(category);
		
		if (count > 0) {
			out.println("��ӷ���ɹ�������");
		}else{
			out.println("��ӷ���ʧ�ܣ�����");
		}
	}
	/**
	 * ���α��
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getTreeCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//��ȡȫ����Ʒ����
		List<MallCategory> categories = categoryBiz.getTreeAllCatrgory();
		
		//���󼯺�תjson��ʽ
		String categoryJson = JSONArray.fromObject(categories).toString();
		
		//ƴ��json
		categoryJson = "{\"total\":"+categories.size()+",\"rows\":"+categoryJson+"}";
		
		//�滻�ַ���
		categoryJson = categoryJson.replaceAll("managerId", "_parentId");
		if (categoryJson.indexOf("_parentId\":0") != -1) {
			
			categoryJson = categoryJson.replaceAll("_parentId\":0", "_parentId\":null");
		}
		
		//��Ӧjson��ʽ���ͻ���
		out.write(categoryJson);
		out.close();
	}

	/**
	 * ��ȡȫ��������Ϣ  ����������
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		int categoryId = 0;
		
		if (id == "" || id == null) {
			categoryId = 0;
		}else{
			categoryId = Integer.parseInt(id);
		}
		//����������
		List<CategoryNodeDto> treeCategorys = categoryBiz.getCatChild(categoryId);
		//List<MallCategory> categorys = categoryBiz.getAllCatrgory();

		String categoryJson = JSONArray.fromObject(treeCategorys).toString();
		//��Ӧ��ͻ���
		out.println(categoryJson);
		out.close();
	}

}
