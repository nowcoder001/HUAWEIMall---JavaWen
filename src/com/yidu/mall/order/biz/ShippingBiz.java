package com.yidu.mall.order.biz;

import java.util.List;

import com.yidu.mall.order.dao.ShippingDao;
import com.yidu.mall.order.model.MallShipping;

/**
 * �ջ���ַҵ���߼���
 * @author С��ħ
 *
 */
public class ShippingBiz {
	//�������ݷ��ʲ�
	ShippingDao shippingDao = new ShippingDao();
	/**
	 * ͨ���û�id��ȡ�ջ���ַ
	 * @param userId
	 * @return
	 */
	public List<MallShipping> getShippingById(int userId){
		return shippingDao.getShippingById(userId);
	}
}
