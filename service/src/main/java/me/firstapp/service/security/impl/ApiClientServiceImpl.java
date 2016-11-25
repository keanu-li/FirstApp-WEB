package me.firstapp.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import me.firstapp.common.exception.ServiceException;
import me.firstapp.common.exception.StatusHouse;
import me.firstapp.common.utils.StrUtils;
import me.firstapp.module.security.ApiClient;
import me.firstapp.repository.security.ApiClientRepository;
import me.firstapp.service.security.ApiClientService;

@Service
public class ApiClientServiceImpl implements ApiClientService {

	@Autowired
	private ApiClientRepository apiClientRepositoryl;

	@Cacheable(value = "apiClient", key = "#apiKey")
	public ApiClient findApiClientByKey(String apiKey) {
		if (StrUtils.isNULL(apiKey)) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "apiKey");
		}
		return apiClientRepositoryl.findApiClientByKey(apiKey);
	}

}
