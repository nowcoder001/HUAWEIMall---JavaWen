package com.yidu.mall.order.model;
/**
 * ��Ʒ���ʵ����
 * @author С��ħ
 *
 */
public class MallProductSpecs {
	private int id;  //��Ʒ����id
	private String productName;  //��Ʒ����
	private String productSpecs;  //��Ʒ�������
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
