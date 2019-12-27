package com.yidu.mall.express.biz;

import java.util.List;

import com.yidu.mall.express.dao.ShippingDao;
import com.yidu.mall.express.model.MallShipping;
/**
 * 收货地址业务逻辑层
 * @author 小恶魔
 *
 */
public class ShippingBiz {
	//创建数据访问层
	ShippingDao shippingDao = new ShippingDao();
	/**
	 * 根据用户id获取用户的收货地址
	 * @param userId
	 * @return
	 */
	public List<MallShipping> getShippingByUserId(int userId){
		return shippingDao.getShippingByUserId(userId);
	}
	/**
	 * 添加收货地址
	 * @param shipping
	 * @return
	 */
	public int addAddress(MallShipping shipping,int userId){
		return shippingDao.addAddress(shipping, userId);
	}
	/**
	 * 查询用户的收货地址是否有默认的地址
	 * @param userId
	 * @return
	 */
	public int selectDefault(int userId){
		return shippingDao.selectDefault(userId);
	}
	/**
	 * 根据id获取收货地址信息
	 * @param shippingId
	 * @return
	 */
	public MallShipping getShippingById(int shippingId){
		return shippingDao.getShippingById(shippingId);
	}
	/**
	 * 根据id删除收货地址
	 * @param shippingId
	 * @return
	 */
	public int deleteShipping(int shippingId){
		return shippingDao.deleteShipping(shippingId);
	}
	/**
	 * 根据id查询此收货地址是否为默认地址
	 * @param shippingId
	 * @return
	 */
	public int selectMyDefault(int shippingId){
		return shippingDao.selectMyDefault(shippingId);
	}
	/**
	 * 修改收货地址
	 * @param shipping
	 * @return
	 */
	public int updateAddress(int userId,MallShipping shipping){
		return shippingDao.updateAddress(userId, shipping);
	}
}
