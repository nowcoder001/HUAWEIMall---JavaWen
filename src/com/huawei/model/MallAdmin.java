package com.huawei.model;
/**
 * ����Աʵ����
 * @author С��ħ
 *
 */
public class MallAdmin {
	private int id;  //����Ա��id
	private String adminId;  //����Ա�˺�
	private String password;  //����Ա����
	private String adminName;  //����Ա����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public MallAdmin(int id, String adminId, String password, String adminName) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.password = password;
		this.adminName = adminName;
	}
	
	
}
