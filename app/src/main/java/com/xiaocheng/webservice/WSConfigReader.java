package com.xiaocheng.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


/**
 * 读取webservice配置信息
 * @author kehaofei
 *
 */
public class WSConfigReader {
	private static Logger log = Logger.getLogger(WSConfigReader.class);
	
	private static WSConfigReader instance = null;
	public Map<String, WSConfig> mapConfig = new HashMap<String, WSConfig>();
	
	public WSConfigReader() {
		readConfigInfo();
	}
	
	public Map<String, WSConfig> getConfig() {
		return mapConfig;
	}
	
	/**
	 * 服务器地址配置文件读取，加载一次<li>synchronized
	 * @return
	 */
	public static WSConfigReader getInstance() {
		if (instance == null) {
    		syncInit();
    	}
        return instance;
	}
	
	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new WSConfigReader();
		}
	}
	/**
	 * 
	 * @author kehaofei
	 * @TODO 读取服务地址配置文件
	 * @createtime  2015-12-30  下午1:57:28
	 */
	public void readConfigInfo() {
        
		FileInputStream fi = null;
		String thisClassPath = this.getClass().getResource("")
				.getPath().substring(1);
		String filePath = File.separator + thisClassPath
				.substring(0, thisClassPath.lastIndexOf("classes")) + "classes";
		String FILE = filePath + File.separator + "config" 
				+ File.separator + "webservice-config.xml";
		
		System.out.println(FILE);

		try {
	
			fi = new FileInputStream(FILE);
			
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(fi);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> elWSs = root.getChildren("ws");
			for (Element elWS: elWSs) {
				WSConfig config = new WSConfig();
				String system = elWS.getChild("system").getText();
				config.setSystem(system);
				config.setUrl(elWS.getChild("url").getText());
				config.setNamespace(elWS.getChild("namespace").getText());
				mapConfig.put(system, config);
			}
		} catch (FileNotFoundException e) {
			log.error("readConfigInfo FileNotFoundException：没有找到文件", e);
		} catch (JDOMException e) {
			log.error("readConfigInfo JDOMException", e);
		} catch (IOException e) {
			log.error("readConfigInfo IOException", e);
		} catch (Exception e) {
			log.error("readConfigInfo Exception", e);
		} finally {
			try {
				fi.close();
			} catch (IOException e) {
				log.error("IOException" , e);
			}
		}
	}
	
}
