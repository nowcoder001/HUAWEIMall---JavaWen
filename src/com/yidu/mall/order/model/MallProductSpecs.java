package com.yidu.mall.order.model;
/**
 * 商品规格实体类
 * @author 小恶魔
 *
 */
public class MallProductSpecs {
	private int id;  //商品规格表id
	private String productName;  //商品名称
	private String productSpecs;  //商品规格详情
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductSpecs() {
		return productSpecs;
	}
	public void setProductSpecs(String productSpecs) {
		this.productSpecs = productSpecs;
	}
	public MallProductSpecs(int id, String productName, String productSpecs) {
		super();
		this.id = id;
		this.productName = productName;
		this.productSpecs = productSpecs;
	}
	
	
}
