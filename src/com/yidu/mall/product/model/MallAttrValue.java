package com.yidu.mall.product.model;
/**
 * ��Ʒ����valueʵ����
 * @author С��ħ
 *
 */
public class MallAttrValue {
	private int id;  //����value  ��id
	private String attrKeyName;  //����key������
	private String attrValueName;  //����value������
	private String categoryName;  //��������
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttrKeyName() {
		return attrKeyName;
	}
	public void setAttrKeyName(String attrKeyName) {
		this.attrKeyName = attrKeyName;
	}
	public String getAttrValueName() {
		return attrValueName;
	}
	public void setAttrValueName(String attrValueName) {
		this.attrValueName = attrValueName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public MallAttrValue(int id, String attrKeyName, String attrValueName,
			String categoryName) {
		super();
		this.id = id;
		this.attrKeyName = attrKeyName;
		this.attrValueName = attrValueName;
		this.categoryName = categoryName;
	}
	
}
