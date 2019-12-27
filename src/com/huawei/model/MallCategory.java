package com.huawei.model;
/**
 * 类别实体类
 * @author 小恶魔
 *
 */
public class MallCategory {
	private int id;  // 分类id
	private int parentId; //父类别id    当id = 0 说明是一级类别，根节点。
	private String name;  // 类别名称
	private int status;  //类别的状态  1、正常  2、停止使用
	private int sortOrder;  //排序编号(具体看excel)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public MallCategory(int id, int parentId, String name, int status,
			int sortOrder) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.status = status;
		this.sortOrder = sortOrder;
	}
	
	
}
