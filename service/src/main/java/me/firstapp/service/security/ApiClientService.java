package me.firstapp.service.security;

import me.firstapp.module.security.ApiClient;

public interface ApiClientService {
	/**
	 * 通过key获取ApiClient
	 * 
	 * @param apiKey
	 */
	ApiClient findApiClientByKey(String apiKey);
}
