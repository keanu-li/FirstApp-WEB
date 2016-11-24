package me.firstapp.bbs.service.member.impl;

import java.util.Calendar;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.alibaba.fastjson.JSONObject;

import me.firstapp.bbs.module.ApiJsonMember;
import me.firstapp.bbs.service.base.impl.ApiBaseServiceImpl;
import me.firstapp.bbs.service.member.ApiMemberService;
import me.firstapp.common.exception.ServiceException;
import me.firstapp.common.exception.StatusHouse;
import me.firstapp.common.exception.StatusObject;
import me.firstapp.common.security.ShaSign;
import me.firstapp.common.utils.StrUtils;

@Service
public class ApiMemberServiceImpl extends ApiBaseServiceImpl implements ApiMemberService {

	public ApiJsonMember register(String email, String name, String password, String code) {
		if (StrUtils.isNULL(email)) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "email");
		}
		if (!StrUtils.isMail(email)) {
			throw new ServiceException(StatusHouse.EMAIL_FORMAT_WRONG);
		}
		if (StrUtils.isNULL(name)) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "name");
		}
		if (StrUtils.isNULL(password)) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "password");
		}
		if (StrUtils.isNULL(code)) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "code");
		}

		Calendar calendar = Calendar.getInstance();
		StringBuilder url = new StringBuilder(apiUrl);
		url.append("/member/register.do");

		url.append("?apiKey=").append(apiKey);
		url.append("&timestamp=").append(calendar.getTimeInMillis());
		url.append("&email=").append(email);
		url.append("&name=").append(name);
		url.append("&password=").append(password);
		url.append("&code=").append(code);

		ShaSign shaSign = ShaSign.getSign(apiKey, apiSecret, calendar.getTime());
		shaSign.addParam("email", email);
		shaSign.addParam("name", name);
		shaSign.addParam("password", password);
		shaSign.addParam("code", code);

		url.append("&sign=").append(shaSign.signParams());

		try {
			logger.info("开始调用API用户注册接口");
			ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.POST, null,
					String.class);
			logger.info("结束调用API用户注册接口，返回结果--》" + responseEntity.getBody());
			JSONObject resultJson = JSONObject.parseObject(responseEntity.getBody());
			if (StatusHouse.COMMON_STATUS_OK.getCode().equals(resultJson.getString("code"))) {
				return JSONObject.toJavaObject(resultJson.getJSONObject("object"), ApiJsonMember.class);
			} else {
				throw new ServiceException(resultJson.getString("code"), resultJson.getString("msg"));
			}
		} catch (RestClientException e) {
			throw new ServiceException(new StatusObject("error", "访问API接口异常"));
		}
	}

	public JSONObject sendEmailCode(String email) {
		if (StrUtils.isNULL(email)) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "email");
		}
		if (!StrUtils.isMail(email)) {
			throw new ServiceException(StatusHouse.EMAIL_FORMAT_WRONG);
		}
		Calendar calendar = Calendar.getInstance();
		StringBuilder url = new StringBuilder(apiUrl);
		url.append("/member/sendEmailCode.do");

		url.append("?apiKey=").append(apiKey);
		url.append("&timestamp=").append(calendar.getTimeInMillis());
		url.append("&email=").append(email);

		ShaSign shaSign = ShaSign.getSign(apiKey, apiSecret, calendar.getTime());
		shaSign.addParam("email", email);
		url.append("&sign=").append(shaSign.signParams());

		try {
			logger.info("开始调用API邮件验证码发送接口");
			ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.POST, null,
					String.class);
			logger.info("结束调用API邮件验证码发送接口，返回结果--》" + responseEntity.getBody());
			JSONObject resultJson = JSONObject.parseObject(responseEntity.getBody());
			if (StatusHouse.COMMON_STATUS_OK.getCode().equals(resultJson.getString("code"))) {
				return resultJson;
			} else {
				throw new ServiceException(resultJson.getString("code"), resultJson.getString("msg"));
			}
		} catch (RestClientException e) {
			throw new ServiceException(new StatusObject("error", "访问API接口异常"));
		}
	}

}
