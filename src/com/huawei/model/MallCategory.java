package com.huawei.model;
/**
 * ���ʵ����
 * @author С��ħ
 *
 */
public class MallCategory {
	private int id;  // ����id
	private int parentId; //�����id    ��id = 0 ˵����һ����𣬸��ڵ㡣
	private String name;  // �������
	private int status;  //����״̬  1������  2��ֹͣʹ��
	private int sortOrder;  //������(���忴excel)
	
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
