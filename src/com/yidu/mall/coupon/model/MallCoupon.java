package com.yidu.mall.coupon.model;
/**
 * �̳��Żݾ�ʵ����
 * @author С��ħ
 *
 */
public class MallCoupon {
	private int id;  //�Ż�ȯ��id
	private String depict; //�Ż�ȯ����
	private int money;   //�������
	private String couponName;  //�Ż�ȯ����
	private String couponGetTime;  //�Ż�ȯ���ʱ��
	private String couponUse;  //�Ƿ��ʹ��
	private String createTime;  //����ʱ��
	private String updateTime;  //���� ʱ��
	private String CDKEY;  //�Ż�ȯ�һ���
	
	
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
