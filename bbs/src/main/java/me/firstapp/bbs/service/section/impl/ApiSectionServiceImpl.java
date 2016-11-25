package me.firstapp.bbs.service.section.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import me.firstapp.bbs.module.ApiJsonSection;
import me.firstapp.bbs.service.base.impl.ApiBaseServiceImpl;
import me.firstapp.bbs.service.section.ApiSectionService;
import me.firstapp.common.exception.ServiceException;
import me.firstapp.common.exception.StatusHouse;
import me.firstapp.common.exception.StatusObject;
import me.firstapp.common.utils.ShaSign;

@Service
public class ApiSectionServiceImpl extends ApiBaseServiceImpl implements ApiSectionService {
	
//	@Value("${api.server.url}")
//	private String apiUrl;
//	@Value("${api.server.key}")
//	private String apiKey;
//	@Value("${api.server.secret}")
//	private String apiSecret;

	public List<ApiJsonSection> getApiJsonSectionList() {
		Calendar calendar = Calendar.getInstance();
		StringBuilder url = new StringBuilder(apiUrl);
		url.append("/section/getAllSections.do");

		url.append("?apiKey=").append(apiKey);
		url.append("&timestamp=").append(calendar.getTimeInMillis());
		ShaSign shaSign = ShaSign.getSign(apiKey, apiSecret, calendar.getTime());
		url.append("&sign=").append(shaSign.signParams());

		try {
			logger.info("开始调用API查询板块接口");
			ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, null,
					String.class);
			logger.info("结束调用API查询板块接口，返回结果--》" + responseEntity.getBody());

			JSONObject resultJson = JSONObject.parseObject(responseEntity.getBody());
			if (StatusHouse.COMMON_STATUS_OK.getCode().equals(resultJson.getString("code"))) {
				JSONArray jsonArray = resultJson.getJSONArray("items");
				List<ApiJsonSection> sections = new ArrayList<ApiJsonSection>();
				if (jsonArray != null) {
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						ApiJsonSection section = JSONObject.toJavaObject(jsonObject, ApiJsonSection.class);
						sections.add(section);
					}
				}
				return sections;
			} else {
				throw new ServiceException(resultJson.getString("code"), resultJson.getString("msg"));
			}
		} catch (RestClientException e) {
			throw new ServiceException(new StatusObject("error", "访问API接口异常"));
		}
	}

}
