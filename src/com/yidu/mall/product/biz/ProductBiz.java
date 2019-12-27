package com.yidu.mall.product.biz;

import java.util.List;

import com.yidu.mall.product.dao.ProductDao;
import com.yidu.mall.product.model.MallAttrValue;
import com.yidu.mall.product.model.MallProduct;

/**
 * ��Ʒҵ���߼���
 * @author С��ħ
 *
 */
public class ProductBiz {
	ProductDao productDao = new ProductDao();
	/**
	 * ��ȡȫ����Ʒ
	 * @return
	 */
	public List<MallProduct> getAllProduct(){
		return productDao.getAllProduct();
	}
	
	/**
	 * �����Ʒ
	 * @return
	 */
	public int addProduct(MallProduct product){
		return productDao.addProduct(product);
	}
	/**
	 * ��ȡ������Ʒ�����Ϣ
	 * @return
	 */
	public List<MallAttrValue> getAllAttr(){
		return productDao.getAllAttr();
	}
	/**
	 * ������Ʒidɾ����Ʒ
	 * @param proId
	 * @return
	 */
	public int deleteProByProductId(int proId){
		return productDao.deleteProByProductId(proId);
	}
	/**
	 * �������ֻ�ȡ��Ʒ
	 * @param proName
	 * @return
	 */
	public List<MallProduct> getProductByName(String proName){
		return productDao.getProductByName(proName);
	}
	/**
	 * ������Ʒid��ѯ��Ʒ
	 * @param proId
	 * @return
	 */
	public MallProduct getProductById(int proId){
		return productDao.getProductById(proId);
	}
	/**
	 * ��ҳ��ѯ
	 * @param page  ҳ��
	 * @param rows	����
	 * @return
	 */
	public List<MallProduct> getPageProduct(int page, int rows){
		return productDao.getPageProduct(page, rows);
	}
	/**
	 * ����id�޸���Ʒ��Ϣ
	 * @param proId
	 * @return
	 */
	public int updateProById(MallProduct product){
		return productDao.updateProById(product);
	}
	/**
	 * ���ݷ���id��ѯ��Ʒ   �����������ֻ�������
	 * @return
	 */
	public List<MallProduct> getProductByCatId(int[] catId){
		return productDao.getProductByCatId(catId);
	}
	/**
	 * ���ݷ���id��ѯ��Ʒ
	 * @param catId
	 * @return
	 */
	public List<MallProduct> getProductByCatId(int catId){
		return productDao.getProductByCatId(catId);
	}
	/**
	 * ������ѯ �۸�
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<MallProduct> getProductByPrice(int minPrice,int maxPrice){
		return productDao.getProductByPrice(minPrice, maxPrice);
	}
}
