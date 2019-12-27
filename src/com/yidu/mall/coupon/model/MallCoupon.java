package com.yidu.mall.coupon.model;
/**
 * 商城优惠卷实体类
 * @author 小恶魔
 *
 */
public class MallCoupon {
	private int id;  //优惠券表id
	private String depict; //优惠券描述
	private int money;   //金额数量
	private String couponName;  //优惠券名称
	private String couponGetTime;  //优惠券获得时间
	private String couponUse;  //是否可使用
	private String createTime;  //创建时间
	private String updateTime;  //更新 时间
	private String CDKEY;  //优惠券兑换码
	
	
	public String getCDKEY() {
		return CDKEY;
	}
	public void setCDKEY(String cDKEY) {
		CDKEY = cDKEY;
	}
	public MallCoupon(int id, String depict, int money,
			String couponName, String couponGetTime, String couponUse,
			String createTime, String updateTime) {
		super();
		this.id = id;
		this.depict = depict;
		this.money = money;
		this.couponName = couponName;
		this.couponGetTime = couponGetTime;
		this.couponUse = couponUse;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepict() {
		return depict;
	}
	public void setDepict(String depict) {
		this.depict = depict;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCouponGetTime() {
		return couponGetTime;
	}
	public void setCouponGetTime(String couponGetTime) {
		this.couponGetTime = couponGetTime;
	}
	public String getCouponUse() {
		return couponUse;
	}
	public void setCouponUse(String couponUse) {
		this.couponUse = couponUse;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public MallCoupon(){
		
	}
}
