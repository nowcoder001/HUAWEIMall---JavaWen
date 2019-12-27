package com.yidu.mall.order.model;
/**
 * �ջ���ַʵ����
 * @author С��ħ
 *
 */
public class MallShipping {
	private int id;  //�ջ���ַ��id
	private String receiverDefault;  // Ĭ�ϵ�ַ
	private String receiverName;  //�ջ�����
	private String receiverPhone;  //�ջ��̶��绰
	private String receiverMobile;  //�ջ��ƶ��绰
	private String receiverProvince;   //ʡ��
	private String receiverCity;  //����
	private String receiverDistrict;  //��/��
	private String receiverAddress;  //��ϸ��ַ
	private String receiverZip;  //�ʱ��ַ
	private String createTime;  //����ʱ��
	private String updateTime;  //����ʱ��
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReceiverDefault() {
		return receiverDefault;
	}
	public void setReceiverDefault(String receiverDefault) {
		this.receiverDefault = receiverDefault;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiverProvince() {
		return receiverProvince;
	}
	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverDistrict() {
		return receiverDistrict;
	}
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverZip() {
		return receiverZip;
	}
	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
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
	public MallShipping(int id,  String receiverDefault,
			String receiverName, String receiverPhone, String receiverMobile,
			String receiverProvince, String receiverCity,
			String receiverDistrict, String receiverAddress,
			String receiverZip, String createTime, String updateTime) {
		super();
		this.id = id;
		this.receiverDefault = receiverDefault;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverMobile = receiverMobile;
		this.receiverProvince = receiverProvince;
		this.receiverCity = receiverCity;
		this.receiverDistrict = receiverDistrict;
		this.receiverAddress = receiverAddress;
		this.receiverZip = receiverZip;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public MallShipping(){
		
	}
	
}
