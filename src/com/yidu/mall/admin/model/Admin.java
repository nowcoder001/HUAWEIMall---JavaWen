package com.yidu.mall.admin.model;
/**
 * ����Աʵ����
 * @author С��ħ
 *
 */
public class Admin {
	private String name;  //�û���
	private String password;  //����
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
