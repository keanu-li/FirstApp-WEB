package me.firstapp.common.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * SHA签名
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class ShaSign {

	Logger logger = LoggerFactory.getLogger(ShaSign.class);

	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	private Map<String, List<Object>> paramMap = new HashMap<String, List<Object>>();

	private String apiKey;

	private String apiSecret;

	// 待签名的参数转换为字符串，类似name=value&name1=value1的形式
	protected String paramsToString() {
		List<String> names = new ArrayList<String>(paramMap.keySet());
		Collections.sort(names);
		StringBuilder paramBuilder = new StringBuilder();
		for (int j = 0; j < names.size(); j++) {
			String name = names.get(j);
			List<Object> values = paramMap.get(name);
			String str = "";
			for (int i = 0; i < values.size(); i++) {
				str += values.get(i);
				if (i < values.size() - 1) {
					str += ",";
				}
			}
			paramBuilder.append(name).append("=").append(str);
			if (j < names.size() - 1) {
				paramBuilder.append("&");
			}
		}
		logger.debug("输出参数为：" + paramBuilder.toString());
		return paramBuilder.toString();
	}

	// 增加待签名的参数
	public ShaSign addParam(String name, Object value) {
		if (value == null || value.toString().trim().equals("")) {
			logger.info("参数值不存在，忽略当前参数: name=" + name + ", value=" + value);
		} else {
			List<Object> values = paramMap.containsKey(name) ? paramMap.get(name) : new ArrayList<Object>();
			values.add(value);
			paramMap.put(name, values);
		}
		return this;
	}

	public String signParams() {
		String paramString = paramsToString() + "&apiSecret=" + this.apiSecret;
		String sign = passwordEncoder.encodePassword(paramString, null);
		logger.info("签名参数为：" + paramString + ", 输出签名为：" + sign);
		return sign;
	}

	/**
	 * 得到签名
	 * 
	 * @param userName
	 */
	public static ShaSign getSign(String apiKey, String apiSecret, Date now) {
		ShaSign shaSign = new ShaSign();
		shaSign.setApiKey(apiKey);
		shaSign.setApiSecret(apiSecret);

		shaSign.addParam("apiKey", shaSign.apiKey);
		shaSign.addParam("timestamp", now.getTime());
		return shaSign;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

}
