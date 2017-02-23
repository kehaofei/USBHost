package com.xiaocheng.webservice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

/**
 * WS管理，client端调用服务在此执行
 * 
 * @author kehaofei
 * 
 */
public class WSClientManager {
	/** 主站系统 */
	public static final String SYSTEM_MasterStation = "MasterStation";

	private static Logger logger = Logger.getLogger(WSClientManager.class);
	private static WSClientManager instance = null;
	private static Map<String, WSClient> mapClient = new HashMap<String, WSClient>();

	private WSClientManager() {
		System.out.println("WSClientManager\t单例模式初始化一次");
		Map<String, WSConfig> mapConfig = WSConfigReader.getInstance().getConfig();
		
		Iterator<?> iter = mapConfig.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) iter.next();
			String system = (String) entry.getKey();
			WSConfig config = (WSConfig) entry.getValue();
			WSClient wsClient = new WSClient(config);
			mapClient.put(system, wsClient);
		}
		System.out.println("访问服务器的结果:\t1");
	}

	/**
	 * 初始化服务端连接，初始化一次<li>synchronized
	 * @return
	 */
	public static WSClientManager getInstance() {
		if (instance == null) {
			syncInit();
		}
		return instance;
	}

	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new WSClientManager();
		}
	}

}
