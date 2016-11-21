package me.firstapp.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * MD5工具类
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class MD5Utils {

	static Logger logger = LoggerFactory.getLogger(MD5Utils.class);

	static Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	public static String sign(String content) {
		return passwordEncoder.encodePassword(content, null);
	}

	public static boolean verify(String content, String sign) {
		if (logger.isDebugEnabled()) {
			logger.debug("verify(..) IN => {content: " + content + ", sign: " + sign + "}");
		}
		try {
			String signed = sign(content);
			if (logger.isDebugEnabled()) {
				logger.debug("verify(..) content => " + content + ", content sign => " + signed);
			}
			return signed.equals(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		String s = "username=john&password=123&appSecret=order_blank_api&timestamp=111";
		System.out.println(MD5Utils.sign(s));
	}
}
