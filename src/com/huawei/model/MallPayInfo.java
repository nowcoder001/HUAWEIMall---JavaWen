package com.huawei.model;
/**
 * ֧����Ϣʵ����
 * @author С��ħ
 *
 */
public class MallPayInfo {
	private int id;   //֧����Ϣ  id
	private int userId;   //�û�id
	private int orderId;  //����id
	private int payPlatform;   //֧��ƽ̨
	private String payStatus;   //֧��״̬
	private String createTime;  //֧����Ϣ����ʱ��
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
