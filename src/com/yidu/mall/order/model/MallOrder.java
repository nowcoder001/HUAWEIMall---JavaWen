package com.yidu.mall.order.model;

import java.util.List;
import com.yidu.mall.product.model.MallProduct;
import com.yidu.mall.user.model.MallUser;
/**
 * 商城订单实体类
 * @author 小恶魔
 *
 */
public class MallOrder {
	private int id;  //订单id
	private String orderNo;   //订单号
	private double payment;  //实际付款金额
	private int paymentType;   //支付类型
	private int postage;  //运费
	private int status;   //订单状态
	private String paymentTime;  //支付时间
	private String sendTime;  //发货时间
	private String endTime;  //交易完成时间
	private String createTime;  //订单创建时间
	private String updateTime;   //订单更新时间
	private List<MallOrderItem> orderItems;  //子订单明细表集合
	private MallShipping shipping;   //收货地址实体类
	private MallProduct product;  //商品实体类
	private MallOrderItem orderItem;  //订单明细实体类
	private MallUser user;  //用户
	
	public MallUser getUser() {
		return user;
	}
	public void setUser(MallUser user) {
		this.user = user;
	}
	public MallOrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(MallOrderItem orderItem) {
		this.orderItem = orderItem;
	}
	public MallProduct getProduct() {
		return product;
	}
	public void setProduct(MallProduct product) {
		this.product = product;
	}
	public MallShipping getShipping() {
		return shipping;
	}
	public void setShipping(MallShipping shipping) {
		this.shipping = shipping;
	}
	public List<MallOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<MallOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
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
	public MallOrder(int id, String orderNo, int shippingId,
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
	public MallOrder(){
		
	}
	
}
