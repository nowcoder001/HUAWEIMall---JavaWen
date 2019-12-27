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
 * ��̨����ҵ���߼���
 * @author С��ħ
 *
 */
public class AdminOrderBiz {
	AdminOrderDao adminOrderDao = new AdminOrderDao();
	
	OrderDao orderDao = new OrderDao();
	/**
	 * ��ȡȫ��������Ϣ
	 * @return
	 */
	public List<MallOrder> getAllOrders(int page,int rows){
		
		//��ȡȫ�������������ظ������ţ�
		List<MallOrder> mallOrders = adminOrderDao.getAllOrders();
		
		List<Integer> orderIds = new ArrayList<Integer>();
		//�ҳ��ظ�������
		for (MallOrder mallOrder : mallOrders) {
			orderIds.add(mallOrder.getId());
		}
		Set<Integer> set = new HashSet<Integer>();
		set.addAll(orderIds);
        Collection<Integer> rs = org.apache.commons.collections.CollectionUtils.disjunction(orderIds, set);
        //��ȡȫ���������������ظ������ţ�
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
	 * �����ظ��Ķ���id��ȡ���������Ʒ�������
	 * @param orderId
	 * @return
	 */
	public List<MallOrderItem> getChildProduct(int orderId){
		return orderDao.getChildProduct(orderId);
	}
	/**
	 * ��ȡȫ��������Ϣ(�������ظ�)
	 * @return
	 */
	public List<MallOrder> getAllOrdersNoRedo(){
		return adminOrderDao.getAllOrdersNoRedo();
	}
	/**
	 * �޸Ķ���״̬
	 * @param orderId
	 * @return
	 */
	public int updateOrderAddress(int orderId,int status){
		return adminOrderDao.updateOrderAddress(orderId, status);
	}
	/**
	 * ����֧������  �޸Ķ�����
	 * @param orderId
	 * @return
	 */
	public int updateOrderById(int shippingId,String orderId){
		return orderDao.updateOrderById(shippingId, orderId);
	}
	/**
	 * �޸Ķ����۸�
	 * @param orderId
	 * @param price
	 * @return
	 */
	public int updateOrderPrice(int orderId,double price){
		return adminOrderDao.updateOrderPrice(orderId, price);
	}
	/**
	 * ����id���ɾ��(������������ϸ��)
	 * @param orderId
	 * @return
	 */
	public int deleteOrder(int orderId){
		return orderDao.deleteOrder(orderId);
	}
	/**
	 * �����ʽ���ϸ
	 * @return
	 */
	public List<MallOrder> getOrderMoneylog(){
		return adminOrderDao.getOrderMoneylog();
	}
}
