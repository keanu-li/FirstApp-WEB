package me.firstapp.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * http工具类
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class HttpUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	public static String getRequestParameters(HttpServletRequest request) {
		StringBuilder paramBuilder = new StringBuilder();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					try {
						value = URLDecoder.decode(values[i], "UTF-8");
					} catch (UnsupportedEncodingException e) {
						logger.warn("Decode参数" + name + "的值失败，继续使用原值:" + e.getMessage());
					}
					valueStr = (i == values.length - 1) ? valueStr + value : valueStr + value + ",";
				}
			}
			paramBuilder.append(name).append("=").append(valueStr).append("&");
		}
		String paramString = paramBuilder.length() > 0 ? paramBuilder.substring(0, paramBuilder.length() - 1)
				: paramBuilder.toString();
		logger.debug("getRequestParameters(..) In => " + paramString);
		return paramString;
	}

	public static List<Integer> getIntArrayList(String[] values) {
		List<Integer> results = new ArrayList<Integer>();
		if (values != null) {
			for (String value : values) {
				if (StringUtils.hasText(value)) {
					results.add(new Integer(value));
				}
			}
		}
		return results;
	}

	/**
	 * 获取请求Body
	 *
	 * @param request
	 * @return
	 */
	public static String getBodyString(ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
