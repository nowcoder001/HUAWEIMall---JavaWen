package com.yidu.mall.coupon.biz;

import java.util.List;

import com.yidu.mall.coupon.dao.CouponDao;
import com.yidu.mall.coupon.model.MallCoupon;
import com.yidu.mall.user.model.MallUser;
/**
 * 优惠卷业务逻辑层
 * @author 小恶魔
 *
 */
public class CouponBiz {
	CouponDao couponDao = new CouponDao();
	/**
	 * 根据用户id查询此用户的优惠卷
	 * @param userId
	 * @return
	 */
	public List<MallCoupon> getCouponByUserId(int userId){
		return couponDao.getCouponByUserId(userId);
	}
	/**
	 * 优惠券 修改订单的价格
	 * @param money
	 * @param orderId
	 * @return
	 */
	public int updateOrderMoney(int money,int orderId){
		return couponDao.updateOrderMoney(money, orderId);
	}
	/**
	 * 修改优惠券状态
	 * @return
	 */
	public int updateCouponStatus(int couponId,int status){
		return couponDao.updateCouponStatus(couponId, status);
	}
	/**
	 * 删除优惠券
	 * @param couponId
	 * @return
	 */
	public int deleteCoupon(int couponId){
		return couponDao.deleteCoupon(couponId);
	}
	/**
	 * 根据用户id和优惠券名称查询是否有此优惠券
	 * @param userId   用户id
	 * @param couponName    优惠券名称
	 * @return
	 */
	public int getCouponByUserIdAndName(int userId,String couponName){
		return couponDao.getCouponByUserIdAndName(userId, couponName);
	}
	/**
	 * 注册即送 优惠券  注册大礼包
	 * @param userId   用户id
	 * @return 
	 */
	public int insertCoupon(int userId){
		return couponDao.insertCoupon(userId);
	}
	/**
	 * 新增优惠券
	 * @param userId   用户id
	 * @return 
	 */
	public int insertCoupon(int userId,MallCoupon coupon){
		return couponDao.insertCoupon(userId, coupon);
	}
	
	/**
	 * 查询全部优惠卷信息
	 * @return
	 */
	public List<MallCoupon> selectCoupon() {
		
		return couponDao.selectCoupon();
	}
	/**
	 * 赠送优惠券
	 * @return
	 */
	public int updateCoupon(int id,int userid){
		
		return couponDao.updateCoupon(id,userid);
	}
	/**
	 * 分页查询
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
	 * 新增优惠卷
	 * @param MallCoupon
	 * @return
	 */
	public int insertCoupon(MallCoupon coupon){
		
		return couponDao.insertCoupon(coupon);
	}
	/**
	 * 根据编号删除优惠卷
	 * @param id
	 * @return
	 */
	public int deleteId(int id) {
		
		return couponDao.deleteId(id);
	}
	/**
	 * 根据id修改优惠券
	 * @param id
	 * @param coupon
	 * @return
	 */
	public int updateCoupon(int id,MallCoupon coupon){
		
		return couponDao.updateCoupon(id, coupon);
	}
	/**
	 * 查询用户名
	 * 赠送优惠券
	 * @return
	 */
	public List<MallUser> selectUserName() {
		
		return couponDao.selectUserName();
	}
	/**
	 * 查询CDK是否正确
	 * @param CDK   兑换码
	 * @return
	 */
	public int selectCouponCDK(int userId,String CDK){
		return couponDao.selectCouponCDK(userId,CDK);
	}
	/**
	 * 修改优惠券CDK
	 * @param CDK
	 * @return
	 */
	public int updateCouponCDK(String CDK){
		return couponDao.updateCouponCDK(CDK);
	}
}
