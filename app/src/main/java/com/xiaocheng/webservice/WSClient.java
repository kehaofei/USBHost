package com.xiaocheng.webservice;

import org.apache.log4j.Logger;
/**
 * webservice的客户端调用
 * 
 * @author kehaofei
 * 
 */
public class WSClient {
	private static Logger log = Logger.getLogger(WSClient.class);
	private WSConfig config = null;

	public WSClient(WSConfig config) {
		this.config = config;
	}
	
	
}
