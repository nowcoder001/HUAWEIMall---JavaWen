package com.yidu.mall.product.model;
/**
 * ��Ʒ���ʵ����  (�������Ʒ��ʲô����)
 * @author С��ħ
 *
 */
public class ProductSpecs {
	private int speceId;  //����id
	private String productSpece;  //��Ʒ�������  json��ʽ
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
