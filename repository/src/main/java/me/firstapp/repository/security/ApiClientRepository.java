package me.firstapp.repository.security;

import me.firstapp.module.security.ApiClient;
import me.firstapp.repository.base.EntityRepository;

public interface ApiClientRepository extends EntityRepository<ApiClient> {

	/**
	 * 通过key获取ApiClient
	 * 
	 * @param apiKey
	 */
	ApiClient findApiClientByKey(String apiKey);
}
