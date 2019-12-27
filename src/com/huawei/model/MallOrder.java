package com.huawei.model;
/**
 * �̳Ƕ���ʵ����
 * @author С��ħ
 *
 */
public class MallOrder {
	private int id;  //����id
	private int orderNo;   //������
	private double payment;  //ʵ�ʸ�����
	private int paymentType;   //֧������
	private int postage;  //�˷�
	private int status;   //����״̬
	private String paymentTime;  //֧��ʱ��
	private String sendTime;  //����ʱ��
	private String endTime;  //�������ʱ��
	private String createTime;  //��������ʱ��
	private String updateTime;   //��������ʱ��
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public int getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}
	public int getPostage() {
		return postage;
	}
	public void setPostage(int postage) {
		this.postage = postage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public MallOrder(int id, int orderNo, int userId, int shippingId,
			double payment, int paymentType, int postage, int status,
			String paymentTime, String sendTime, String endTime,
			String createTime, String updateTime) {
		
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.payment = payment;
		this.paymentType = paymentType;
		this.postage = postage;
		this.status = status;
		this.paymentTime = paymentTime;
		this.sendTime = sendTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	
}
