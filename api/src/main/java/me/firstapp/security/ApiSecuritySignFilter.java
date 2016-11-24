package me.firstapp.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import me.firstapp.common.exception.StatusHouse;
import me.firstapp.common.json.SingleObject;
import me.firstapp.common.utils.JsonWriter;
import me.firstapp.common.utils.MD5Utils;
import me.firstapp.common.utils.ResponseUtils;
import me.firstapp.module.security.ApiClient;
import me.firstapp.service.security.ApiClientService;

public class ApiSecuritySignFilter implements Filter {

	// 请求来源类型：APP,WEB
	public final static String REQUEST_SOURCE_TYPE = "_REQUEST_SOURCE_TYPE_";

	static Logger logger = LoggerFactory.getLogger(ApiSecuritySignFilter.class);

	// 是否执行接口签名验证
	private static boolean API_CLIENT_AUTH = true;

	@Autowired
	private ApiClientService apiClientService;

	/**
	 * 将Request请求参数的值放入Map之中，同一参数名如果有多个值，多个值使用逗号分隔组成一个新的字符串值
	 * 
	 * @param request
	 */
	private Map<String, String> getParamMap(HttpServletRequest request) {
		logger.info("getParamMap(..) IN  => " + request);
		Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				try {
					value = URLDecoder.decode(values[i], "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.warn("Decode参数" + name + "的值失败，继续使用原值:" + e.getMessage());
				}
				valueStr = (i == values.length - 1) ? valueStr + value : valueStr + value + ",";
			}
			paramMap.put(name, valueStr);
		}
		logger.info("getParamMap(..) OUT => " + JsonWriter.toJson(paramMap, true));
		return paramMap;
	}

	/**
	 * 过滤值为空的参数和签名参数
	 * 
	 * @param paramMap
	 * @return
	 */
	private Map<String, String> filterMap(Map<String, String> paramMap) {
		logger.info("filterMap(..) IN => " + JsonWriter.toJson(paramMap, true));
		Map<String, String> result = new HashMap<String, String>();
		if (paramMap == null || paramMap.size() <= 0) {
			return result;
		}
		for (String key : paramMap.keySet()) {
			String value = paramMap.get(key);
			if (value == null || "".equals(value.trim()) || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		logger.info("filterMap(..) OUT => " + JsonWriter.toJson(result, true));
		return result;
	}

	/**
	 * 将请求参数除去签名参数后生成key=value值对，并且以&号连接的字符串
	 * 
	 * @param request
	 * @return
	 */
	private String getParamString(HttpServletRequest request) {
		logger.info("getParamString(..) IN => " + request);
		Map<String, String> paramMap = getParamMap(request);
		paramMap = filterMap(paramMap);

		List<String> keys = new ArrayList<String>(paramMap.keySet());
		Collections.sort(keys);
		String paramString = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = paramMap.get(key);
			if (i == keys.size() - 1) {
				paramString = paramString + key + "=" + value;
			} else {
				paramString = paramString + key + "=" + value + "&";
			}
		}
		logger.info("getParamString(..) OUT => " + paramString);
		return paramString;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// 先判断是否要使用api授权模式
		if (!API_CLIENT_AUTH) {
			logger.info("根据配置不执行接口调用的签名认证");
			chain.doFilter(request, response);
		} else {
			// 从request中获取apiKey和sign模式
			String apiKey = request.getParameter("apiKey");
			String sign = request.getParameter("sign");
			String timestamp = request.getParameter("timestamp");
			SingleObject<Object> resultJsonObject = new SingleObject<Object>();
			// 判断apiKey,sign,timestamp三个参数是否齐全
			if (apiKey == null || sign == null || timestamp == null) {
				logger.error(
						"apiKey或者sign或者timestamp不存在：apiKey=" + apiKey + ", sign=" + sign + ", timestamp=" + timestamp);
				resultJsonObject.setStatusObject(StatusHouse.ACCOUNT_VERIFY_FAILED);
				ResponseUtils.renderJson(res, JsonWriter.toJson(resultJsonObject, true));
			} else {
				logger.info("接口接受外部程序调用 => apiKey=" + apiKey + ",requestURL=" + req.getRequestURL() + ",requestURI="
						+ req.getRequestURI());
				ApiClient apiClient = apiClientService.findApiClientByKey(apiKey);
				if (apiClient == null) {
					logger.error("根据apiKey查询不到apiSecret：apiKey=" + apiKey);
					resultJsonObject.setStatusObject(StatusHouse.ACCOUNT_VERIFY_FAILED);
					ResponseUtils.renderJson(res, JsonWriter.toJson(resultJsonObject, true));
				} else {
					if (apiClient.getSourceType() != null) {
						request.setAttribute(REQUEST_SOURCE_TYPE, String.valueOf(apiClient.getSourceType()));
					}
					String content = getParamString(req) + "&apiSecret=" + apiClient.getApiSecret();
					if (MD5Utils.verify(content, sign)) {
						chain.doFilter(request, response);
					} else {
						logger.error("签名验证未通过：内容=>" + content + ", 签名=>" + sign);
						resultJsonObject.setStatusObject(StatusHouse.ACCOUNT_VERIFY_FAILED);
						ResponseUtils.renderJson(res, JsonWriter.toJson(resultJsonObject, true));
					}
				}
			}
		}
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
