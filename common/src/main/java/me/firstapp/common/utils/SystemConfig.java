package me.firstapp.common.utils;

/**
 * 配置文件Property读取
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class SystemConfig {
	public static ConfigReload API_CONFIG_RELOAD = new ConfigReload("/api.properties");

	public static String API_URL = API_CONFIG_RELOAD.getProp().getProperty("api.server.url");
	public static String API_KEY = API_CONFIG_RELOAD.getProp().getProperty("api.server.key");
	public static String API_SECRET = API_CONFIG_RELOAD.getProp().getProperty("api.server.secret");
}
