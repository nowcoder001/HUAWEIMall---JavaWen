package com.yidu.mall.comment.model;
/**
 * ������ƷͼƬת����
 * @author С��ħ
 *
 */
public class CommentImagesJson {
	private String content;  //���������ַ���
	private String imagesUrl;  //��Ʒ����ͼƬ
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	
	
	
	public CommentImagesJson(String content,String imagesUrl){
		this.content = content;
		this.imagesUrl = imagesUrl;
	}
}
