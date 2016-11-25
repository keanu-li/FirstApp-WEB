package me.firstapp.repository.security;

import me.firstapp.common.repository.EntityRepository;
import me.firstapp.module.security.ApiClient;

public interface ApiClientRepository extends EntityRepository<ApiClient> {

	/**
	 * 通过key获取ApiClient
	 * 
	 * @param apiKey
	 */
	ApiClient findApiClientByKey(String apiKey);
}
