package com.yidu.mall.admin.model;
/**
 * 网站设置实体类
 * @author 小恶魔
 *
 */
public class Settings {
		
	private String title;  //网站标题
	private String keyWord;  //关键词
	private String describe;  //网站描述
	private String serviceQQ;  //客服QQ
	private String serviceMobile; //客服电话
	private String copy;  //版权所有
	private String ICP;  //域名备案号
	private String hotSearch;  //热门搜索
	private String stat;  //统计代码
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getServiceQQ() {
		return serviceQQ;
	}
	public void setServiceQQ(String serviceQQ) {
		this.serviceQQ = serviceQQ;
	}
	public String getServiceMobile() {
		return serviceMobile;
	}
	public void setServiceMobile(String serviceMobile) {
		this.serviceMobile = serviceMobile;
	}
	public String getCopy() {
		return copy;
	}
	public void setCopy(String copy) {
		this.copy = copy;
	}
	public String getICP() {
		return ICP;
	}
	public void setICP(String iCP) {
		ICP = iCP;
	}
	public String getHotSearch() {
		return hotSearch;
	}
	public void setHotSearch(String hotSearch) {
		this.hotSearch = hotSearch;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public Settings(String title, String keyWord, String describe,
			String serviceQQ, String serviceMobile, String copy, String iCP,
			String hotSearch, String stat) {
		super();
		this.title = title;
		this.keyWord = keyWord;
		this.describe = describe;
		this.serviceQQ = serviceQQ;
		this.serviceMobile = serviceMobile;
		this.copy = copy;
		ICP = iCP;
		this.hotSearch = hotSearch;
		this.stat = stat;
	}
	
	public Settings(){
		
	}
	
}
