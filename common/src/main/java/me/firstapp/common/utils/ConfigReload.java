package me.firstapp.common.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件加载工具类
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class ConfigReload {

	private String CONFIG_FILENAME = "";

	private Properties prop = null;

	public ConfigReload() {
		loadProperties();
	}

	public ConfigReload(String configFile) {
		this.CONFIG_FILENAME = configFile;
		loadProperties();
	}

	public Properties getProp() {
		return prop;
	}

	private void loadProperties() {
		try {
			System.out.println("Loading properties file '" + CONFIG_FILENAME + "'");
			InputStream is = ConfigReload.class.getResourceAsStream(CONFIG_FILENAME);
			prop = new Properties();
			prop.load(is);
		} catch (Exception e) {
			System.err.println("Can't find properties file '" + CONFIG_FILENAME + "'!");
			prop = null;
		}
	}

}
