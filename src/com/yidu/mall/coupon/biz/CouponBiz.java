package com.yidu.mall.coupon.biz;

import java.util.List;

import com.yidu.mall.coupon.dao.CouponDao;
import com.yidu.mall.coupon.model.MallCoupon;
import com.yidu.mall.user.model.MallUser;
/**
 * �Żݾ�ҵ���߼���
 * @author С��ħ
 *
 */
public class CouponBiz {
	CouponDao couponDao = new CouponDao();
	/**
	 * �����û�id��ѯ���û����Żݾ�
	 * @param userId
	 * @return
	 */
	public List<MallCoupon> getCouponByUserId(int userId){
		return couponDao.getCouponByUserId(userId);
	}
	/**
	 * �Ż�ȯ �޸Ķ����ļ۸�
	 * @param money
	 * @param orderId
	 * @return
	 */
	public int updateOrderMoney(int money,int orderId){
		return couponDao.updateOrderMoney(money, orderId);
	}
	/**
	 * �޸��Ż�ȯ״̬
	 * @return
	 */
	public int updateCouponStatus(int couponId,int status){
		return couponDao.updateCouponStatus(couponId, status);
	}
	/**
	 * ɾ���Ż�ȯ
	 * @param couponId
	 * @return
	 */
	public int deleteCoupon(int couponId){
		return couponDao.deleteCoupon(couponId);
	}
	/**
	 * �����û�id���Ż�ȯ���Ʋ�ѯ�Ƿ��д��Ż�ȯ
	 * @param userId   �û�id
	 * @param couponName    �Ż�ȯ����
	 * @return
	 */
	public int getCouponByUserIdAndName(int userId,String couponName){
		return couponDao.getCouponByUserIdAndName(userId, couponName);
	}
	/**
	 * ע�ἴ�� �Ż�ȯ  ע������
	 * @param userId   �û�id
	 * @return 
	 */
	public int insertCoupon(int userId){
		return couponDao.insertCoupon(userId);
	}
	/**
	 * �����Ż�ȯ
	 * @param userId   �û�id
	 * @return 
	 */
	public int insertCoupon(int userId,MallCoupon coupon){
		return couponDao.insertCoupon(userId, coupon);
	}
	
	/**
	 * ��ѯȫ���Żݾ���Ϣ
	 * @return
	 */
	public List<MallCoupon> selectCoupon() {
		
		return couponDao.selectCoupon();
	}
	/**
	 * �����Ż�ȯ
	 * @return
	 */
	public int updateCoupon(int id,int userid){
		
		return couponDao.updateCoupon(id,userid);
	}
	/**
	 * ��ҳ��ѯ
	 * @param page
	 * @param rows
	 * @return
	 */
	List<MallCoupon> userList = couponDao.selectCoupon(); 
	public List<MallCoupon> selectCoupon(int page,int rows) {
		List<MallCoupon> pageUserList = couponDao.selectCoupon(page, rows);
		
		return pageUserList;
	}
	/**
	 * �����Żݾ�
	 * @param MallCoupon
	 * @return
	 */
	public int insertCoupon(MallCoupon coupon){
		
		return couponDao.insertCoupon(coupon);
	}
	/**
	 * ���ݱ��ɾ���Żݾ�
	 * @param id
	 * @return
	 */
	public int deleteId(int id) {
		
		return couponDao.deleteId(id);
	}
	/**
	 * ����id�޸��Ż�ȯ
	 * @param id
	 * @param coupon
	 * @return
	 */
	public int updateCoupon(int id,MallCoupon coupon){
		
		return couponDao.updateCoupon(id, coupon);
	}
	/**
	 * ��ѯ�û���
	 * �����Ż�ȯ
	 * @return
	 */
	public List<MallUser> selectUserName() {
		
		return couponDao.selectUserName();
	}
	/**
	 * ��ѯCDK�Ƿ���ȷ
	 * @param CDK   �һ���
	 * @return
	 */
	public int selectCouponCDK(int userId,String CDK){
		return couponDao.selectCouponCDK(userId,CDK);
	}
	/**
	 * �޸��Ż�ȯCDK
	 * @param CDK
	 * @return
	 */
	public int updateCouponCDK(String CDK){
		return couponDao.updateCouponCDK(CDK);
	}
}
