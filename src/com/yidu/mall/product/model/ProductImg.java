package com.yidu.mall.product.model;
/**
 * 商品图片实体类 - 转换json格式
 * @author 小恶魔
 *
 */
public class ProductImg {
	private String imgDepict;  //主图描述
	private String url;  //主图图片路径
	private int type;  //图片大小（判断主图还是副图）
	private int productId;  //商品id
	
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getImgDepict() {
		return imgDepict;
	}
	public void setImgDepict(String imgDepict) {
		this.imgDepict = imgDepict;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public ProductImg(String imgDepict, String url, int type) {
		super();
		this.type = type;
		this.imgDepict = imgDepict;
		if (type == 1) {
			this.url = url;
		}else{
			this.url = url.replaceAll("428_428","78_78");
			
		}
	}
	
	public ProductImg(){
		
	}
}
