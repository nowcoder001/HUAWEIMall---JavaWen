package com.yidu.mall.order.biz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yidu.mall.order.dao.AdminOrderDao;
import com.yidu.mall.order.dao.OrderDao;
import com.yidu.mall.order.model.MallOrder;
import com.yidu.mall.order.model.MallOrderItem;

/**
 * 后台订单业务逻辑层
 * @author 小恶魔
 *
 */
public class AdminOrderBiz {
	AdminOrderDao adminOrderDao = new AdminOrderDao();
	
	OrderDao orderDao = new OrderDao();
	/**
	 * 获取全部订单信息
	 * @return
	 */
	public List<MallOrder> getAllOrders(int page,int rows){
		
		//获取全部订单（包括重复订单号）
		List<MallOrder> mallOrders = adminOrderDao.getAllOrders();
		
		List<Integer> orderIds = new ArrayList<Integer>();
		//找出重复订单号
		for (MallOrder mallOrder : mallOrders) {
			orderIds.add(mallOrder.getId());
		}
		Set<Integer> set = new HashSet<Integer>();
		set.addAll(orderIds);
        Collection<Integer> rs = org.apache.commons.collections.CollectionUtils.disjunction(orderIds, set);
        //获取全部订单（不包括重复订单号）
        List<MallOrder> order = adminOrderDao.getAllOrdersNoRedo(page,rows);
       
        for (Integer id : rs) {
			
        	for (MallOrder mallOrder : order) {
				if (mallOrder.getId() == id) {
					
					List<MallOrderItem> orderItems = orderDao.getChildProduct(id);
					mallOrder.setOrderItems(orderItems);
				}
			}
        	
		}
        
		return order;
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
	 * 获取全部订单信息(不包括重复)
	 * @return
	 */
	public List<MallOrder> getAllOrdersNoRedo(){
		return adminOrderDao.getAllOrdersNoRedo();
	}
	/**
	 * 修改订单状态
	 * @param orderId
	 * @return
	 */
	public int updateOrderAddress(int orderId,int status){
		return adminOrderDao.updateOrderAddress(orderId, status);
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
	 * 修改订单价格
	 * @param orderId
	 * @param price
	 * @return
	 */
	public int updateOrderPrice(int orderId,double price){
		return adminOrderDao.updateOrderPrice(orderId, price);
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
	 * 订单资金明细
	 * @return
	 */
	public List<MallOrder> getOrderMoneylog(){
		return adminOrderDao.getOrderMoneylog();
	}
}
