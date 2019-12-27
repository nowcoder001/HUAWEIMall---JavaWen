package com.yidu.mall.product.model;
/**
 * 商品规格实体类  (购买的商品是什么规格的)
 * @author 小恶魔
 *
 */
public class ProductSpecs {
	private int speceId;  //规格表id
	private String productSpece;  //商品规格详情  json格式
	public int getSpeceId() {
		return speceId;
	}
	public void setSpeceId(int speceId) {
		this.speceId = speceId;
	}
	public String getProductSpece() {
		return productSpece;
	}
	public void setProductSpece(String productSpece) {
		this.productSpece = productSpece;
	}
	
	public ProductSpecs(int speceId, String productSpece) {
		super();
		this.speceId = speceId;
		this.productSpece = productSpece;
	}
	
}
