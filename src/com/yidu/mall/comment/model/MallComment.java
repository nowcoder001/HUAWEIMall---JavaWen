package com.yidu.mall.comment.model;

import com.yidu.mall.order.model.MallOrderItem;
import com.yidu.mall.user.model.MallUser;

/**
 * ��Ʒ����ʵ����
 * @author С��ħ
 *
 */
public class MallComment {
	private int id;  //id
	private String userName;  //�û�����
	private String productName;  //��Ʒ����
	private String content;  //��������
	private String createTime;  //���۴���ʱ��
	private String updateTime;  //���۸���ʱ��
	private MallUser user;  //�û�
	private String replyContent; //�ظ�������
	private MallOrderItem orderItem;  //��������ʵ����
	
	
	public MallOrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(MallOrderItem orderItem) {
		this.orderItem = orderItem;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public MallUser getUser() {
		return user;
	}
	public void setUser(MallUser user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public MallComment(int id, String userName, 
			String productName, String content, String createTime,
			String updateTime) {
		super();
		this.id = id;
		this.userName = userName;
		this.productName = productName;
		this.content = content;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	public MallComment(){
		
	}
}