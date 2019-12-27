package com.yidu.mall.order.model;

import com.yidu.mall.user.model.MallUser;


/**
 * 订单明细表
 * @author 小恶魔
 *
 */
public class MallOrderItem {
	private int id;   //订单明细表id
	private int productId;  //商品id
	private String productName;  //商品名称
	private String productImage;  //商品图片地址
	private double current_unit_price;  //生成订单时的商品单价
	private int quantity;   //商品数量
	private double totalPrice;  //商品总价
	private String createTime;  //明细表创建时间
	private String updateTime;   //明细表更新时间
	private MallUser user;  //用户
	private String orderNo;  //订单号
	
	
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
