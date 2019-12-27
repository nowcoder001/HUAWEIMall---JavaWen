package com.yidu.mall.admin.model;
/**
 * ��վ����ʵ����
 * @author С��ħ
 *
 */
public class Settings {
		
	private String title;  //��վ����
	private String keyWord;  //�ؼ���
	private String describe;  //��վ����
	private String serviceQQ;  //�ͷ�QQ
	private String serviceMobile; //�ͷ��绰
	private String copy;  //��Ȩ����
	private String ICP;  //����������
	private String hotSearch;  //��������
	private String stat;  //ͳ�ƴ���
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
