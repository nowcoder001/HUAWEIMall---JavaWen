package com.yidu.mall.order.biz;

import java.util.List;

import com.yidu.mall.order.dao.OrderDao;
import com.yidu.mall.order.model.MallOrder;
import com.yidu.mall.order.model.MallOrderItem;
import com.yidu.mall.product.model.MallProduct;

/**
 * 订单业务逻辑层
 * @author 小恶魔
 *
 */
public class OrderBiz {
	//创建数据访问层对象
	OrderDao orderDao = new OrderDao();
	/**
	 * 创建订单明细表
	 * @param order
	 * @return
	 */
	public int createOrderItem(int orderId,MallOrderItem orderItem){
		return orderDao.createOrderItem(orderId, orderItem);
	}
	/**
	 * 创建订单表
	 * @param order
	 * @return
	 */
	public int createOrder(int userId,MallOrder order){
		return orderDao.createOrder(userId, order);
	}
	/**
	 * 查询最近的订单id
	 * @return 
	 */
	public int getOrderId(){
		return orderDao.getOrderId();
	}
	/**
	 * 进入支付界面  修改订单表
	 * @param orderId
	 * @return
	 */
	public int updateOrderById(int shippingId,String orderId){
		return orderDao.updateOrderById(shippingId, orderId);
	}
	/**
	 * 根据订单id获取订单信息和商品信息
	 * @param orderId
	 * @return
	 */
	public MallOrder getOrderAndProductById(int orderId){
		return orderDao.getOrderAndProductById(orderId);
	}
	/**
	 * 支付成功后修改数据库表
	 * @param order
	 * @return
	 */
	public int updatePaySuccess(MallOrder order){
		return orderDao.updatePaySuccess(order);
	}
	/**
	 * 根据用户id 获取订单信息
	 * @param userId
	 * @return
	 */
	public List<MallOrder> getOrderByUserId(int userId,int noRedo){
		return orderDao.getOrderByUserId(userId,noRedo);
	}
	/**
	 * 查询购物车是否存在
	 * @param userId
	 * @return
	 */
	public String getCartCount(int userId){
		return orderDao.getCartCount(userId);
	}
	/**
	 * 获取用户购物车的商品
	 * @param userId
	 * @return
	 */
	public List<MallOrderItem> getOrderItemsByUserId(int userId){
		return orderDao.getOrderItemsByUserId(userId);
	}
	/**
	 * 根据明细表id获取商品信息
	 * @param itemId
	 * @return
	 */
	public List<MallOrderItem> getOrderItem(String[] itemId){
		return orderDao.getOrderItem(itemId);
	}
	/**
	 * 支付成功删除购物车的商品
	 * @param itemId
	 * @return
	 */
	public int deleteCartProductByItemId(String[] itemId){
		return orderDao.deleteCartProductByItemId(itemId);
	}
	/**
	 * 获取各状态的订单
	 * @param status
	 * @return
	 */
	public List<MallOrder> getProductStatus(int status,int noRedo,int userId){
		return orderDao.getProductStatus(status,noRedo,userId);
	}
	/**
	 * 根据重复的订单id获取下面的子商品（多个）
	 * @param orderId
	 * @return
	 */
	public List<MallOrderItem> getChildProduct(int orderId){
		return orderDao.getChildProduct(orderId);
	}
	/**
	 * 根据订单id获取订单集合
	 * @param orderId
	 * @return
	 */
	public MallOrder getOrderInfoProduct(int orderId){
		return orderDao.getOrderInfoProduct(orderId);
	}
	/**
	 * 根据id多表删除(订单表、订单明细表)
	 * @param orderId
	 * @return
	 */
	public int deleteOrder(int orderId){
		return orderDao.deleteOrder(orderId);
	}
	/**
	 * 删除购物车的商品
	 * @param itemId
	 * @return
	 */
	public int deleteItemOrder(int itemId){
		return orderDao.deleteItemOrder(itemId);
	}
	/**
	 * 根据订单号查询订单id
	 * @param orderNo
	 * @return
	 */
	public int getOrderIdByOrderNo(String orderNo){
		return orderDao.getOrderIdByOrderNo(orderNo);
	}
}
