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

	//创建业务逻辑层
	CategoryBiz categoryBiz = new CategoryBiz();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取请求中的参数值
		String mod = request.getParameter("mod");
		
		if ("getCategory".equals(mod)) {  //树形下拉框
			getAllCategory(request,response);
		}else if ("getTreeCategory".equals(mod)) {    //分类树形表格
			getTreeCategory(request,response);
		}else if ("addCategory".equals(mod)) {   //添加分类
			addCategory(request,response);
		}else if ("getCatById".equals(mod)) {   //通过id查询商品分类上级
			getCatById(request,response);
		}else if ("deleteCatById".equals(mod)) {  //根据id删除商品分类
			deleteCatById(request,response);
		}else if ("getCategoryById".equals(mod)) {   //根据id查询商品分类
			getCategoryById(request,response);
		}else if ("updateCatById".equals(mod)) {  //根据id修改商品分类
			updateCatById(request,response);
		}else if ("htmlGetCategorys".equals(mod)) {  //前端分类查询
			htmlGetCategorys(request,response);
		}
		
	}
	/**
	 * 前台分类查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void htmlGetCategorys(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		//获取顶级商品分类
		List<MallCategory> categoriesTop = categoryBiz.getCategory(0);
		
		//获取三级商品分类
		List<MallCategory> categoriesThree = categoryBiz.getCategory(2);
		//把三级商品分类放入顶级商品分类
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
	 * 根据id修改商品分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateCatById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		//获取请求中的参数
		int catId = Integer.parseInt(request.getParameter("catId"));
		String catName = request.getParameter("cat_name");
		String isMax = request.getParameter("isMax");  //获取顶级按钮的值
		int categoryId = 0;  //分类id
		int catSort = Integer.parseInt(request.getParameter("cat_sort"));
		int catStatus = Integer.parseInt(request.getParameter("cat_status"));
		int parentId = 0;  //父id
		if ("on".equals(isMax)) {  //如果是on既说明此新建的分类是顶级
			categoryId = 0;
		}else{
			categoryId = Integer.parseInt(request.getParameter("category"));
		}
		//获取全部分类集合
		List<MallCategory> categories = categoryBiz.getAllCatrgory();
		for (MallCategory category : categories) {
			if (category.getId() == categoryId) {
				parentId = category.getParentId() +1;  //此分类对象是父级   既+1
			}
		}
		MallCategory category = new MallCategory(catId, parentId, catName, catStatus, catSort);
		category.setManagerId(categoryId);
		
		int count = categoryBiz.updateCatById(category);
		
		if (count > 0 ) {
			out.println("修改分类成功！！！");
		}else{
			out.println("修改分类失败！！！");
		}
	}
	/**
	 * 根据id查询商品分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getCategoryById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求的参数值
		int catId = Integer.parseInt(request.getParameter("catId"));
		
		MallCategory category = categoryBiz.getCategoryById(catId);
		
		String catJson = JSONArray.fromObject(category).toString();
		
		out.write(catJson);
	}
	/**
	 * 根据id删除商品分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteCatById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数
		int categoryId = Integer.parseInt(request.getParameter("catId"));
		int count = categoryBiz.deleteCatById(categoryId);
		if (count > 0 ) {
			out.println("删除分类成功！！");
		}else{
			out.println("删除分类失败！！");
		}
	}
	/**
	 * 通过id查询商品分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getCatById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		//获取请求中的参数值
		int id = Integer.parseInt(request.getParameter("id"));
		String categoryName = "";  //分类名称
		int managerId = 0;  //上级id
		if (id > 6) {
			//获取所有分类
			List<MallCategory> categories = categoryBiz.getAllCatrgory();
			//循环查出此id 的上级id
			for (MallCategory category : categories) {
				if (category.getId() == id) {
					managerId = category.getManagerId();
				}
			}
			//循环查出此上级id的名称
			for (MallCategory category : categories) {
				if (managerId == category.getId()) {
					categoryName = category.getName();
				}
			}
		}else{
			categoryName = "无上级";
		}
		
		
		out.write(categoryName);
		out.close();
	}
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数
		String catName = request.getParameter("cat_name");
		String isMax = request.getParameter("isMax");  //获取顶级按钮的值
		int categoryId = 0;  //分类id
		int catSort = Integer.parseInt(request.getParameter("cat_sort"));
		int catStatus = Integer.parseInt(request.getParameter("cat_status"));
		int parentId = 0;  //父id
		if ("on".equals(isMax)) {  //如果是on既说明此新建的分类是顶级
			categoryId = 0;
		}else{
			categoryId = Integer.parseInt(request.getParameter("category"));
		}
		//获取全部分类集合
		List<MallCategory> categories = categoryBiz.getAllCatrgory();
		for (MallCategory category : categories) {
			if (category.getId() == categoryId) {
				parentId = category.getParentId() +1;  //此分类对象是父级   既+1
			}
		}
		MallCategory category = new MallCategory(0, parentId, catName, catStatus, catSort);
		category.setManagerId(categoryId);
		int count = categoryBiz.addCategory(category);
		
		if (count > 0) {
			out.println("添加分类成功！！！");
		}else{
			out.println("添加分类失败！！！");
		}
	}
	/**
	 * 树形表格
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getTreeCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取全部商品分类
		List<MallCategory> categories = categoryBiz.getTreeAllCatrgory();
		
		//对象集合转json格式
		String categoryJson = JSONArray.fromObject(categories).toString();
		
		//拼接json
		categoryJson = "{\"total\":"+categories.size()+",\"rows\":"+categoryJson+"}";
		
		//替换字符串
		categoryJson = categoryJson.replaceAll("managerId", "_parentId");
		if (categoryJson.indexOf("_parentId\":0") != -1) {
			
			categoryJson = categoryJson.replaceAll("_parentId\":0", "_parentId\":null");
		}
		
		//响应json格式出客户端
		out.write(categoryJson);
		out.close();
	}

	/**
	 * 获取全部分类信息  树形下拉框
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
		//树形下拉框
		List<CategoryNodeDto> treeCategorys = categoryBiz.getCatChild(categoryId);
		//List<MallCategory> categorys = categoryBiz.getAllCatrgory();

		String categoryJson = JSONArray.fromObject(treeCategorys).toString();
		//响应会客户端
		out.println(categoryJson);
		out.close();
	}

}
