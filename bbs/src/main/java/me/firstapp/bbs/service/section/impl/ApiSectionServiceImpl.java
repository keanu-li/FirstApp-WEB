package me.firstapp.bbs.service.section.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import me.firstapp.bbs.module.ApiJsonSection;
import me.firstapp.bbs.service.section.ApiSectionService;
import me.firstapp.common.exception.ServiceException;
import me.firstapp.common.exception.StatusObject;
import me.firstapp.common.utils.SystemConfig;

@Service
public class ApiSectionServiceImpl implements ApiSectionService {
	static Logger logger = LoggerFactory.getLogger(ApiSectionService.class);
	@Autowired
	private RestTemplate restTemplate;

	public List<ApiJsonSection> getApiJsonSectionList() {
		StringBuilder url = new StringBuilder(SystemConfig.API_URL);
		url.append("/section/getAllSections.do");
		try {
			logger.info("开始调用API查询板块接口");
			ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.POST, null,
					String.class);
			logger.info("结束调用API查询板块接口，返回结果--》" + responseEntity.getBody());
			
		} catch (RestClientException e) {
			throw new ServiceException(new StatusObject("error", "访问CRM接口异常"));
		}
		return null;
	}

}
