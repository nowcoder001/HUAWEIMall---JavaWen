package com.yidu.mall.user.model;

import java.util.List;

import com.yidu.mall.coupon.model.MallCoupon;

/**
 * 华为商城用户实体类
 * @author 小恶魔
 *
 */
public class MallUser {
	private int id;
	private String user_name; //用户名
	private String password;  //用户密码
	private String email;  //邮箱
	private String phone; 
	private String state;  //国籍
	private String user_date;  //出生日期
	private String user_img;  //用户头像
	private MallCoupon coupon;  //优惠卷
	private List<MallCoupon> coupons;  //优惠卷集合
	
	public List<MallCoupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<MallCoupon> coupons) {
		this.coupons = coupons;
	}
	public MallCoupon getCoupon() {
		return coupon;
	}
	public void setCoupon(MallCoupon coupon) {
		this.coupon = coupon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUser_date() {
		return user_date;
	}
	public void setUser_date(String userDate) {
		user_date = userDate;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String userImg) {
		user_img = userImg;
	}
	public MallUser(int id, String userName, String password, String email,
			String phone, String state, String userDate, String userImg) {
		super();
		this.id = id;
		user_name = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.state = state;
		user_date = userDate;
		user_img = userImg;
	}
	public MallUser(){
		
	}
}
