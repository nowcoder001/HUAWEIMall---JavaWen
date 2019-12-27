package com.yidu.mall.product.biz;

import java.util.List;

import com.yidu.mall.product.dao.ProductDao;
import com.yidu.mall.product.model.MallAttrValue;
import com.yidu.mall.product.model.MallProduct;

/**
 * 商品业务逻辑层
 * @author 小恶魔
 *
 */
public class ProductBiz {
	ProductDao productDao = new ProductDao();
	/**
	 * 获取全部商品
	 * @return
	 */
	public List<MallProduct> getAllProduct(){
		return productDao.getAllProduct();
	}
	
	/**
	 * 添加商品
	 * @return
	 */
	public int addProduct(MallProduct product){
		return productDao.addProduct(product);
	}
	/**
	 * 获取所有商品规格信息
	 * @return
	 */
	public List<MallAttrValue> getAllAttr(){
		return productDao.getAllAttr();
	}
	/**
	 * 根据商品id删除商品
	 * @param proId
	 * @return
	 */
	public int deleteProByProductId(int proId){
		return productDao.deleteProByProductId(proId);
	}
	/**
	 * 根据名字获取商品
	 * @param proName
	 * @return
	 */
	public List<MallProduct> getProductByName(String proName){
		return productDao.getProductByName(proName);
	}
	/**
	 * 根据商品id查询商品
	 * @param proId
	 * @return
	 */
	public MallProduct getProductById(int proId){
		return productDao.getProductById(proId);
	}
	/**
	 * 分页查询
	 * @param page  页数
	 * @param rows	行数
	 * @return
	 */
	public List<MallProduct> getPageProduct(int page, int rows){
		return productDao.getPageProduct(page, rows);
	}
	/**
	 * 根据id修改商品信息
	 * @param proId
	 * @return
	 */
	public int updateProById(MallProduct product){
		return productDao.updateProById(product);
	}
	/**
	 * 根据分类id查询商品   比如搜索：手机、电脑
	 * @return
	 */
	public List<MallProduct> getProductByCatId(int[] catId){
		return productDao.getProductByCatId(catId);
	}
	/**
	 * 根据分类id查询商品
	 * @param catId
	 * @return
	 */
	public List<MallProduct> getProductByCatId(int catId){
		return productDao.getProductByCatId(catId);
	}
	/**
	 * 条件查询 价格
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<MallProduct> getProductByPrice(int minPrice,int maxPrice){
		return productDao.getProductByPrice(minPrice, maxPrice);
	}
}
