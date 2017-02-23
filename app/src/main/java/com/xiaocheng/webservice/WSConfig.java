package com.xiaocheng.webservice;

/**
 * WEBSERVICE的配置信息
 * 
 * @author kehaofei
 * 
 */
public class WSConfig {
	//被调用的系统
	/**
	 * @
	 */
	private String system = "";
	//wsdl地址
	private String url = "";
	//命名空间
	private String namespace = "";
	
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
}
