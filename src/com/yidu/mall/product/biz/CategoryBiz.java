package com.yidu.mall.product.biz;

import java.util.ArrayList;
import java.util.List;

import com.yidu.mall.product.dao.CategoryDao;
import com.yidu.mall.product.model.CategoryNodeDto;
import com.yidu.mall.product.model.MallCategory;
/**
 * 分类 业务逻辑层
 * @author 小恶魔
 *
 */
public class CategoryBiz {
	
	//创建数据访问层对象
	CategoryDao categoryDao = new CategoryDao();
	
	/**
	 * 查询全部的分类
	 * @return
	 */
	public List<MallCategory> getAllCatrgory(){
		return categoryDao.getAllCatrgory();
	}
	/**
	 * 树形下拉框分类
	 * @param id
	 * @return
	 */
	public List<CategoryNodeDto> getCatChild(int categoryId){
		//分类下级集合
		List<MallCategory> categorys = new ArrayList<MallCategory>();
		//获取全部分类集合
		List<MallCategory> allCategory = categoryDao.getAllCatrgory();
		//分类节点集合
		List<CategoryNodeDto> childCategory = new ArrayList<CategoryNodeDto>();
		
		//所有集合中查询子节点分类对象
		for (MallCategory category : allCategory) {
			if (categoryId == category.getManagerId()) {
				categorys.add(category);
			}
		}
		
		//将分类集合转成tree属性集合
		for (MallCategory category : categorys) {
			CategoryNodeDto categoryNodeDto = new CategoryNodeDto();
			//设置节点的属性值
			categoryNodeDto.setId(category.getId());
			categoryNodeDto.setText(category.getName());
			
			if (category.getParentId() == 2) {
				categoryNodeDto.setState("open");
				categoryNodeDto.setIconCls("fa fa-mobile");
			}else{
				categoryNodeDto.setState("closed");
			}
			
			categoryNodeDto.setChecked("false");
			
			//添加到属性集合中
			childCategory.add(categoryNodeDto);
		}
		
		return childCategory;
		
	}
	/**
	 * 查询全部的分类  树形表格
	 * @return
	 */
	public List<MallCategory> getTreeAllCatrgory(){
		
		List<MallCategory> categories = categoryDao.getAllCatrgory();
		
		for (MallCategory category : categories) {
			//如果分类里父级id  等于 2  既说明没有下级
			if (category.getParentId() == 2) {
				category.setChecked("open");
				category.setIconCls("fa fa-mobile");
			}else{
				category.setChecked("closed");
			}
			
			
		}
		
		return categories;
	}
	/**
	 * 添加分类
	 * @param category
	 * @return
	 */
	public int addCategory(MallCategory category){
		return categoryDao.addCategory(category);
	}
	/**
	 * 删除分类信息根据id
	 * @return
	 */
	public int deleteCatById(int categoryId){
		return categoryDao.deleteCatById(categoryId);
	}
	/**
	 * 根据id查询商品分类
	 * @param catId
	 * @return
	 */
	public MallCategory getCategoryById(int catId){
		return categoryDao.getCategoryById(catId);
	}
	/**
	 * 根据id修改分类信息
	 * @param category
	 * @return
	 */
	public int updateCatById(MallCategory category){
		return categoryDao.updateCatById(category);
	}
	/**
	 * 根据父级id获取分类
	 * @param parentId
	 * @return
	 */
	public List<MallCategory> getCategory(int parentId){
		return categoryDao.getCategory(parentId);
	}
}
