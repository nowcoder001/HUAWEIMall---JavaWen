package com.yidu.mall.express.biz;

import java.util.List;

import com.yidu.mall.express.dao.ShippingDao;
import com.yidu.mall.express.model.MallShipping;
/**
 * �ջ���ַҵ���߼���
 * @author С��ħ
 *
 */
public class ShippingBiz {
	//�������ݷ��ʲ�
	ShippingDao shippingDao = new ShippingDao();
	/**
	 * �����û�id��ȡ�û����ջ���ַ
	 * @param userId
	 * @return
	 */
	public List<MallShipping> getShippingByUserId(int userId){
		return shippingDao.getShippingByUserId(userId);
	}
	/**
	 * ����ջ���ַ
	 * @param shipping
	 * @return
	 */
	public int addAddress(MallShipping shipping,int userId){
		return shippingDao.addAddress(shipping, userId);
	}
	/**
	 * ��ѯ�û����ջ���ַ�Ƿ���Ĭ�ϵĵ�ַ
	 * @param userId
	 * @return
	 */
	public int selectDefault(int userId){
		return shippingDao.selectDefault(userId);
	}
	/**
	 * ����id��ȡ�ջ���ַ��Ϣ
	 * @param shippingId
	 * @return
	 */
	public MallShipping getShippingById(int shippingId){
		return shippingDao.getShippingById(shippingId);
	}
	/**
	 * ����idɾ���ջ���ַ
	 * @param shippingId
	 * @return
	 */
	public int deleteShipping(int shippingId){
		return shippingDao.deleteShipping(shippingId);
	}
	/**
	 * ����id��ѯ���ջ���ַ�Ƿ�ΪĬ�ϵ�ַ
	 * @param shippingId
	 * @return
	 */
	public int selectMyDefault(int shippingId){
		return shippingDao.selectMyDefault(shippingId);
	}
	/**
	 * �޸��ջ���ַ
	 * @param shipping
	 * @return
	 */
	public int updateAddress(int userId,MallShipping shipping){
		return shippingDao.updateAddress(userId, shipping);
	}
}
