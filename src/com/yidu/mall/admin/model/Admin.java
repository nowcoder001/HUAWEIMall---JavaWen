package com.yidu.mall.admin.model;
/**
 * 管理员实体类
 * @author 小恶魔
 *
 */
public class Admin {
	private String name;  //用户名
	private String password;  //密码
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	
}
