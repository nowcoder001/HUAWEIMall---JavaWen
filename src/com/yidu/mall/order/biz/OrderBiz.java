package com.yidu.mall.order.biz;

import java.util.List;

import com.yidu.mall.order.dao.OrderDao;
import com.yidu.mall.order.model.MallOrder;
import com.yidu.mall.order.model.MallOrderItem;
import com.yidu.mall.product.model.MallProduct;

/**
 * ����ҵ���߼���
 * @author С��ħ
 *
 */
public class OrderBiz {
	//�������ݷ��ʲ����
	OrderDao orderDao = new OrderDao();
	/**
	 * ����������ϸ��
	 * @param order
	 * @return
	 */
	public int createOrderItem(int orderId,MallOrderItem orderItem){
		return orderDao.createOrderItem(orderId, orderItem);
	}
	/**
	 * ����������
	 * @param order
	 * @return
	 */
	public int createOrder(int userId,MallOrder order){
		return orderDao.createOrder(userId, order);
	}
	/**
	 * ��ѯ����Ķ���id
	 * @return 
	 */
	public int getOrderId(){
		return orderDao.getOrderId();
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
	 * ���ݶ���id��ȡ������Ϣ����Ʒ��Ϣ
	 * @param orderId
	 * @return
	 */
	public MallOrder getOrderAndProductById(int orderId){
		return orderDao.getOrderAndProductById(orderId);
	}
	/**
	 * ֧���ɹ����޸����ݿ��
	 * @param order
	 * @return
	 */
	public int updatePaySuccess(MallOrder order){
		return orderDao.updatePaySuccess(order);
	}
	/**
	 * �����û�id ��ȡ������Ϣ
	 * @param userId
	 * @return
	 */
	public List<MallOrder> getOrderByUserId(int userId,int noRedo){
		return orderDao.getOrderByUserId(userId,noRedo);
	}
	/**
	 * ��ѯ���ﳵ�Ƿ����
	 * @param userId
	 * @return
	 */
	public String getCartCount(int userId){
		return orderDao.getCartCount(userId);
	}
	/**
	 * ��ȡ�û����ﳵ����Ʒ
	 * @param userId
	 * @return
	 */
	public List<MallOrderItem> getOrderItemsByUserId(int userId){
		return orderDao.getOrderItemsByUserId(userId);
	}
	/**
	 * ������ϸ��id��ȡ��Ʒ��Ϣ
	 * @param itemId
	 * @return
	 */
	public List<MallOrderItem> getOrderItem(String[] itemId){
		return orderDao.getOrderItem(itemId);
	}
	/**
	 * ֧���ɹ�ɾ�����ﳵ����Ʒ
	 * @param itemId
	 * @return
	 */
	public int deleteCartProductByItemId(String[] itemId){
		return orderDao.deleteCartProductByItemId(itemId);
	}
	/**
	 * ��ȡ��״̬�Ķ���
	 * @param status
	 * @return
	 */
	public List<MallOrder> getProductStatus(int status,int noRedo,int userId){
		return orderDao.getProductStatus(status,noRedo,userId);
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
	 * ���ݶ���id��ȡ��������
	 * @param orderId
	 * @return
	 */
	public MallOrder getOrderInfoProduct(int orderId){
		return orderDao.getOrderInfoProduct(orderId);
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
	 * ɾ�����ﳵ����Ʒ
	 * @param itemId
	 * @return
	 */
	public int deleteItemOrder(int itemId){
		return orderDao.deleteItemOrder(itemId);
	}
	/**
	 * ���ݶ����Ų�ѯ����id
	 * @param orderNo
	 * @return
	 */
	public int getOrderIdByOrderNo(String orderNo){
		return orderDao.getOrderIdByOrderNo(orderNo);
	}
}
