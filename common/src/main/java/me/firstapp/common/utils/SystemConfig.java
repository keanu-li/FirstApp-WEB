package me.firstapp.common.utils;

public class SystemConfig {
	public static ConfigReload API_CONFIG_RELOAD = new ConfigReload("/api.properties");
	
	public static String API_URL = API_CONFIG_RELOAD.getProp().getProperty("api.server.url");
}
