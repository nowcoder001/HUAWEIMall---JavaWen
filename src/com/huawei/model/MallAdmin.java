package com.huawei.model;
/**
 * 管理员实体类
 * @author 小恶魔
 *
 */
public class MallAdmin {
	private int id;  //管理员表id
	private String adminId;  //管理员账号
	private String password;  //管理员密码
	private String adminName;  //管理员名称
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
