package com.yidu.mall.order.biz;

import java.util.List;

import com.yidu.mall.order.dao.ShippingDao;
import com.yidu.mall.order.model.MallShipping;

/**
 * 收货地址业务逻辑层
 * @author 小恶魔
 *
 */
public class ShippingBiz {
	//创建数据访问层
	ShippingDao shippingDao = new ShippingDao();
	/**
	 * 通过用户id获取收货地址
	 * @param userId
	 * @return
	 */
	public List<MallShipping> getShippingById(int userId){
		return shippingDao.getShippingById(userId);
	}
}
