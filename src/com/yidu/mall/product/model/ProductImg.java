package com.yidu.mall.product.model;
/**
 * ��ƷͼƬʵ���� - ת��json��ʽ
 * @author С��ħ
 *
 */
public class ProductImg {
	private String imgDepict;  //��ͼ����
	private String url;  //��ͼͼƬ·��
	private int type;  //ͼƬ��С���ж���ͼ���Ǹ�ͼ��
	private int productId;  //��Ʒid
	
	
	
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
