package com.yidu.mall.order.model;

import com.yidu.mall.product.model.MallCategory;

/**
 * ��Ʒʵ����
 * @author С��ħ
 *
 */
public class MallProduct {
	private int id;  //��Ʒid
	private String name;  //��Ʒ����
	private String coding;  //��Ʒ��š���ƷΨһ����ʾ���
	private String subtitle; //��Ʒ������
	private String mallImages;  //��Ʒ��ͼ  json��ʽ
	private String subImages;   //��Ʒ��ͼ
	private String detailImages;  //��Ʒ����ͼƬ
	private String detailText;   //��Ʒ����������������...��
	private double price; //��Ʒ�۸�
	private int stock;  //�������
	private int status;  //��Ʒ״̬�� 1 - ����  2 - �¼�  3 - ɾ��
	private String imgUrl;   //ͼƬ·��
	
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	private MallCategory category;
	
	public MallCategory getCategory() {
		return category;
	}
	public void setCategory(MallCategory category) {
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoding() {
		return coding;
	}
	public void setCoding(String coding) {
		this.coding = coding;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getMallImages() {
		return mallImages;
	}
	public void setMallImages(String mallImages) {
		this.mallImages = mallImages;
	}
	public String getSubImages() {
		return subImages;
	}
	public void setSubImages(String subImages) {
		this.subImages = subImages;
	}
	public String getDetailImages() {
		return detailImages;
	}
	public void setDetailImages(String detailImages) {
		this.detailImages = detailImages;
	}
	public String getDetailText() {
		return detailText;
	}
	public void setDetailText(String detailText) {
		this.detailText = detailText;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public MallProduct(int id, String name, String coding,
			String subtitle, String mallImages, String subImages,
			String detailImages, String detailText, double price, int stock,
			int status) {
		super();
		this.id = id;
		this.name = name;
		this.coding = coding;
		this.subtitle = subtitle;
		this.mallImages = mallImages;
		this.subImages = subImages;
		this.detailImages = detailImages;
		this.detailText = detailText;
		this.price = price;
		this.stock = stock;
		this.status = status;
	}
	
	public MallProduct(){
		
	}
}
