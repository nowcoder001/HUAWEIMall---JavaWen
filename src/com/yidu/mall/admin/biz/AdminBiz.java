package com.yidu.mall.admin.biz;

import com.yidu.mall.admin.dao.AdminDao;
import com.yidu.mall.admin.model.Admin;

/**
 * ��̨����ҵ���߼���
 * @author С��ħ
 *
 */
public class AdminBiz {
	AdminDao adminDao = new AdminDao();
	/**
	 * ��̨��¼
	 * @param name
	 * @param password
	 * @return
	 */
	public Admin loginAdmin(String name,String password){
		return adminDao.loginAdmin(name, password);
	}
}
