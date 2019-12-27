package com.yidu.mall.order.model;

import com.yidu.mall.product.model.MallCategory;

/**
 * 产品实体类
 * @author 小恶魔
 *
 */
public class MallProduct {
	private int id;  //产品id
	private String name;  //商品名称
	private String coding;  //商品编号、产品唯一可显示编号
	private String subtitle; //商品副标题
	private String mallImages;  //产品主图  json格式
	private String subImages;   //产品副图
	private String detailImages;  //商品详情图片
	private String detailText;   //商品文字描述（规格参数...）
	private double price; //商品价格
	private int stock;  //库存数量
	private int status;  //商品状态： 1 - 在售  2 - 下架  3 - 删除
	private String imgUrl;   //图片路径
	
	
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
