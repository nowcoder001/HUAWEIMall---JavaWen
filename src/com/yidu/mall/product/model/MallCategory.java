package com.yidu.mall.product.model;

import java.util.List;

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
	private int managerId;  //�ϼ�id   0 ˵�����ϼ���߼�
	private String iconCls;  //���α����Ҫ
	private String checked;  //���α����Ҫ
	private List<MallCategory> categories;  //���α����Ҫ
	private String spec;  //����˵��
	private String imagesUrl;   //����ͼƬ
	
	public String getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public List<MallCategory> getCategories() {
		return categories;
	}
	public void setCategories(List<MallCategory> categories) {
		this.categories = categories;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
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
	public MallCategory(){
		
	}
	
}
