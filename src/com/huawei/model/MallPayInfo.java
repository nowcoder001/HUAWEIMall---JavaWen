package com.huawei.model;
/**
 * 支付信息实体类
 * @author 小恶魔
 *
 */
public class MallPayInfo {
	private int id;   //支付信息  id
	private int userId;   //用户id
	private int orderId;  //订单id
	private int payPlatform;   //支付平台
	private String payStatus;   //支付状态
	private String createTime;  //支付信息创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getPayPlatform() {
		return payPlatform;
	}
	public void setPayPlatform(int payPlatform) {
		this.payPlatform = payPlatform;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public MallPayInfo(int id, int userId, int orderId, int payPlatform,
			String payStatus, String createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.orderId = orderId;
		this.payPlatform = payPlatform;
		this.payStatus = payStatus;
		this.createTime = createTime;
	}
	
	
}
