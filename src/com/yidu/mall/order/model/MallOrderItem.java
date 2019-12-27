package com.yidu.mall.order.model;

import com.yidu.mall.user.model.MallUser;


/**
 * ������ϸ��
 * @author С��ħ
 *
 */
public class MallOrderItem {
	private int id;   //������ϸ��id
	private int productId;  //��Ʒid
	private String productName;  //��Ʒ����
	private String productImage;  //��ƷͼƬ��ַ
	private double current_unit_price;  //���ɶ���ʱ����Ʒ����
	private int quantity;   //��Ʒ����
	private double totalPrice;  //��Ʒ�ܼ�
	private String createTime;  //��ϸ����ʱ��
	private String updateTime;   //��ϸ�����ʱ��
	private MallUser user;  //�û�
	private String orderNo;  //������
	
	
	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public MallUser getUser() {
		return user;
	}


	public void setUser(MallUser user) {
		this.user = user;
	}


	public MallOrderItem(int id,
			String productName, String productImage, double currentUnitPrice,
			int quantity, double totalPrice, String createTime,
			String updateTime) {
		super();
		this.id = id;
		this.productName = productName;
		this.productImage = productImage;
		current_unit_price = currentUnitPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductImage() {
		return productImage;
	}


	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public double getCurrent_unit_price() {
		return current_unit_price;
	}


	public void setCurrent_unit_price(double currentUnitPrice) {
		current_unit_price = currentUnitPrice;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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
	public MallOrderItem(){
		
	}
	
}
