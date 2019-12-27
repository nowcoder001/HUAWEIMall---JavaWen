package com.yidu.mall.product.biz;

import java.util.ArrayList;
import java.util.List;

import com.yidu.mall.product.dao.CategoryDao;
import com.yidu.mall.product.model.CategoryNodeDto;
import com.yidu.mall.product.model.MallCategory;
/**
 * ���� ҵ���߼���
 * @author С��ħ
 *
 */
public class CategoryBiz {
	
	//�������ݷ��ʲ����
	CategoryDao categoryDao = new CategoryDao();
	
	/**
	 * ��ѯȫ���ķ���
	 * @return
	 */
	public List<MallCategory> getAllCatrgory(){
		return categoryDao.getAllCatrgory();
	}
	/**
	 * �������������
	 * @param id
	 * @return
	 */
	public List<CategoryNodeDto> getCatChild(int categoryId){
		//�����¼�����
		List<MallCategory> categorys = new ArrayList<MallCategory>();
		//��ȡȫ�����༯��
		List<MallCategory> allCategory = categoryDao.getAllCatrgory();
		//����ڵ㼯��
		List<CategoryNodeDto> childCategory = new ArrayList<CategoryNodeDto>();
		
		//���м����в�ѯ�ӽڵ�������
		for (MallCategory category : allCategory) {
			if (categoryId == category.getManagerId()) {
				categorys.add(category);
			}
		}
		
		//�����༯��ת��tree���Լ���
		for (MallCategory category : categorys) {
			CategoryNodeDto categoryNodeDto = new CategoryNodeDto();
			//���ýڵ������ֵ
			categoryNodeDto.setId(category.getId());
			categoryNodeDto.setText(category.getName());
			
			if (category.getParentId() == 2) {
				categoryNodeDto.setState("open");
				categoryNodeDto.setIconCls("fa fa-mobile");
			}else{
				categoryNodeDto.setState("closed");
			}
			
			categoryNodeDto.setChecked("false");
			
			//��ӵ����Լ�����
			childCategory.add(categoryNodeDto);
		}
		
		return childCategory;
		
	}
	/**
	 * ��ѯȫ���ķ���  ���α��
	 * @return
	 */
	public List<MallCategory> getTreeAllCatrgory(){
		
		List<MallCategory> categories = categoryDao.getAllCatrgory();
		
		for (MallCategory category : categories) {
			//��������︸��id  ���� 2  ��˵��û���¼�
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
	 * ��ӷ���
	 * @param category
	 * @return
	 */
	public int addCategory(MallCategory category){
		return categoryDao.addCategory(category);
	}
	/**
	 * ɾ��������Ϣ����id
	 * @return
	 */
	public int deleteCatById(int categoryId){
		return categoryDao.deleteCatById(categoryId);
	}
	/**
	 * ����id��ѯ��Ʒ����
	 * @param catId
	 * @return
	 */
	public MallCategory getCategoryById(int catId){
		return categoryDao.getCategoryById(catId);
	}
	/**
	 * ����id�޸ķ�����Ϣ
	 * @param category
	 * @return
	 */
	public int updateCatById(MallCategory category){
		return categoryDao.updateCatById(category);
	}
	/**
	 * ���ݸ���id��ȡ����
	 * @param parentId
	 * @return
	 */
	public List<MallCategory> getCategory(int parentId){
		return categoryDao.getCategory(parentId);
	}
}
