package me.firstapp.bbs.service.topic.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import me.firstapp.bbs.module.ApiJsonPage;
import me.firstapp.bbs.module.ApiJsonTopic;
import me.firstapp.bbs.service.base.impl.ApiBaseServiceImpl;
import me.firstapp.bbs.service.topic.ApiTopicService;
import me.firstapp.common.exception.ServiceException;
import me.firstapp.common.exception.StatusHouse;
import me.firstapp.common.exception.StatusObject;
import me.firstapp.common.utils.ShaSign;
import me.firstapp.common.utils.StrUtils;

@Service
public class ApiTopicServiceImpl extends ApiBaseServiceImpl implements ApiTopicService {
	@Autowired
	private RestTemplate restTemplate;

	public ApiJsonTopic addTopic(Long memberId, Long sectionId, String title, String content) {
		if (memberId == null) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "用户id");
		}
		if (sectionId == null) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "板块id");
		}
		if (StrUtils.isNULL(title)) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "标题");
		}

		Calendar calendar = Calendar.getInstance();
		StringBuilder url = new StringBuilder(apiUrl);
		url.append("/topic/addTopic.do");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		params.put("content", content);

		url.append("?apiKey=").append(apiKey);
		url.append("&timestamp=").append(calendar.getTimeInMillis());
		url.append("&memberId=").append(memberId);
		url.append("&sectionId=").append(sectionId);

		ShaSign shaSign = ShaSign.getSign(apiKey, apiSecret, calendar.getTime());
		shaSign.addParam("memberId", memberId);
		shaSign.addParam("sectionId", sectionId);

		url.append("&sign=").append(shaSign.signParams());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
		HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(params), headers);

		try {
			logger.info("开始调用API话题新增接口");
			ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.POST, formEntity,
					String.class);
			logger.info("结束调用API话题新增接口，返回结果--》" + responseEntity.getBody());

			JSONObject resultJson = JSONObject.parseObject(responseEntity.getBody());
			if (StatusHouse.COMMON_STATUS_OK.getCode().equals(resultJson.getString("code"))) {
				return JSONObject.toJavaObject(resultJson.getJSONObject("object"), ApiJsonTopic.class);
			} else {
				throw new ServiceException(resultJson.getString("code"), resultJson.getString("msg"));
			}
		} catch (RestClientException e) {
			throw new ServiceException(new StatusObject("error", "访问API接口异常"));
		}
	}

	public ApiJsonPage<ApiJsonTopic> findTopicsPage(Integer pageNo, Integer pageSize, Long sectionId) {
		Calendar calendar = Calendar.getInstance();
		StringBuilder url = new StringBuilder(apiUrl);
		url.append("/topic/findTopics.do");

		url.append("?apiKey=").append(apiKey);
		url.append("&timestamp=").append(calendar.getTimeInMillis());
		url.append("&pageNo=").append(pageNo == null ? 1 : pageNo);
		url.append("&pageSize=").append(pageSize == null ? 20 : pageSize);
		url.append("&sectionId=").append(sectionId);

		ShaSign shaSign = ShaSign.getSign(apiKey, apiSecret, calendar.getTime());
		shaSign.addParam("pageNo", pageNo);
		shaSign.addParam("pageSize", pageSize);
		shaSign.addParam("sectionId", sectionId);
		url.append("&sign=").append(shaSign.signParams());

		try {
			logger.info("开始调用API话题查询接口");
			ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, null,
					String.class);
			logger.info("结束调用API话题查询接口，返回结果--》" + responseEntity.getBody());

			JSONObject resultJson = JSONObject.parseObject(responseEntity.getBody());
			if (StatusHouse.COMMON_STATUS_OK.getCode().equals(resultJson.getString("code"))) {
				JSONObject pageObject = resultJson.getJSONObject("page");
				ApiJsonPage<ApiJsonTopic> apiJsonPage = null;
				if (pageObject != null) {
					apiJsonPage = new ApiJsonPage<ApiJsonTopic>();
					apiJsonPage.setTotalCount(pageObject.getInteger("totalCount"));
					apiJsonPage.setPageSize(pageObject.getInteger("pageSize"));
					apiJsonPage.setPageNo(pageObject.getInteger("pageNo"));
					apiJsonPage.setFirstResult(pageObject.getInteger("firstResult"));
					apiJsonPage.setFirstPage(pageObject.getBooleanValue("firstPage"));
					apiJsonPage.setNextPage(pageObject.getInteger("nextPage"));
					apiJsonPage.setPrePage(pageObject.getInteger("prePage"));
					apiJsonPage.setTotalPage(pageObject.getInteger("totalPage"));
					apiJsonPage.setLastPage(pageObject.getBooleanValue("lastPage"));
					JSONArray jsonArray = pageObject.getJSONArray("list");
					List<ApiJsonTopic> topics = null;
					if (jsonArray != null) {
						topics = new ArrayList<ApiJsonTopic>();
						for (int i = 0; i < jsonArray.size(); i++) {
							JSONObject tObject = jsonArray.getJSONObject(i);
							ApiJsonTopic apiJsonTopic = JSONObject.toJavaObject(tObject, ApiJsonTopic.class);
							topics.add(apiJsonTopic);
						}
					}
					apiJsonPage.setList(topics);
				}
				return apiJsonPage;
			} else {
				throw new ServiceException(resultJson.getString("code"), resultJson.getString("msg"));
			}
		} catch (RestClientException e) {
			throw new ServiceException(new StatusObject("error", "访问API接口异常"));
		}
	}

	public ApiJsonTopic findTopic(Long topicId) {
		if (topicId == null) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "话题id");
		}

		Calendar calendar = Calendar.getInstance();
		StringBuilder url = new StringBuilder(apiUrl);
		url.append("/topic/findTopicDetail.do");

		url.append("?apiKey=").append(apiKey);
		url.append("&timestamp=").append(calendar.getTimeInMillis());
		url.append("&topicId=").append(topicId);

		ShaSign shaSign = ShaSign.getSign(apiKey, apiSecret, calendar.getTime());
		shaSign.addParam("topicId", topicId);
		url.append("&sign=").append(shaSign.signParams());

		try {
			logger.info("开始调用API话题详情查询接口");
			ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, null,
					String.class);
			logger.info("结束调用API话题详情查询接口，返回结果--》" + responseEntity.getBody());

			JSONObject resultJson = JSONObject.parseObject(responseEntity.getBody());
			if (StatusHouse.COMMON_STATUS_OK.getCode().equals(resultJson.getString("code"))) {
				ApiJsonTopic topic = JSONObject.toJavaObject(resultJson.getJSONObject("object"), ApiJsonTopic.class);
				return topic;
			} else {
				throw new ServiceException(resultJson.getString("code"), resultJson.getString("msg"));
			}
		} catch (RestClientException e) {
			throw new ServiceException(new StatusObject("error", "访问API接口异常"));
		}
	}

}
