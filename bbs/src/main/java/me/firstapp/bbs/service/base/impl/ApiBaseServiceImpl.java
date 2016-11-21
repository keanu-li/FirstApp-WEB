package me.firstapp.bbs.service.base.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import me.firstapp.bbs.service.base.ApiBaseService;
import me.firstapp.common.utils.SystemConfig;

@Service
public class ApiBaseServiceImpl implements ApiBaseService {
	protected Logger logger = LoggerFactory.getLogger(ApiBaseServiceImpl.class);
	protected String apiUrl = SystemConfig.API_URL;
	protected String apiKey = SystemConfig.API_KEY;
	protected String apiSecret = SystemConfig.API_SECRET;
	
	@Autowired
	protected RestTemplate restTemplate;
}
