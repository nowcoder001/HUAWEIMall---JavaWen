package com.yidu.mall.comment.model;
/**
 * 评价商品图片转对象
 * @author 小恶魔
 *
 */
public class CommentImagesJson {
	private String content;  //评论内容字符串
	private String imagesUrl;  //商品评价图片
	
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
