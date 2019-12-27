package com.yidu.mall.admin.biz;

import com.yidu.mall.admin.dao.AdminDao;
import com.yidu.mall.admin.model.Admin;

/**
 * 后台管理业务逻辑层
 * @author 小恶魔
 *
 */
public class AdminBiz {
	AdminDao adminDao = new AdminDao();
	/**
	 * 后台登录
	 * @param name
	 * @param password
	 * @return
	 */
	public Admin loginAdmin(String name,String password){
		return adminDao.loginAdmin(name, password);
	}
}
