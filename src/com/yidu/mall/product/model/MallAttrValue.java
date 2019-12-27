package com.yidu.mall.product.model;
/**
 * 商品属性value实体类
 * @author 小恶魔
 *
 */
public class MallAttrValue {
	private int id;  //属性value  的id
	private String attrKeyName;  //属性key的名称
	private String attrValueName;  //属性value的名称
	private String categoryName;  //分类名称
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
