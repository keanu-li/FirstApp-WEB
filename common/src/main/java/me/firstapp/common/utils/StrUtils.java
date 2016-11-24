package me.firstapp.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串的帮助类，提供静态方法，不可以实例化。
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class StrUtils {

	/**
	 * 禁止实例化
	 */
	private StrUtils() {
	}

	/**
	 * 判断字符串是否为邮件地址
	 * 
	 * @param text
	 *            字符串
	 */
	public static boolean isMail(String text) {
		// 若为空，返回false
		if (isNULL(text)) {
			return false;
		}
		Pattern p = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher m = p.matcher(text);
		return m.matches();
	}

	/**
	 * 判断字符串只存在大小写字母、数字、下划线
	 * 
	 * @param text
	 */
	public static boolean isAbc123_(String text) {
		if (isNULL(text)) {
			return false;
		}
		Pattern p = Pattern.compile("[0-9A-Za-z_]*");
		Matcher m = p.matcher(text);
		return m.matches();
	}

	/**
	 * 判断字符串是否为网址
	 * 
	 * @param text
	 *            字符串
	 */
	public static boolean isURL(String text) {
		// 若为空，返回false
		if (isNULL(text)) {
			return false;
		}
		String regex = "^((https|http|ftp|rtsp|mms)?://)" + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
				+ "(([0-9]{1,3}\\.){3}[0-9]{1,3}" + "|" + "([0-9a-z_!~*'()-]+\\.)*"
				+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." + "[a-z]{2,6})" + "(:[0-9]{1,4})?" + "((/?)|"
				+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		return m.matches();
	}

	/**
	 * 判断是否为手机号
	 * 
	 * @param mobile
	 */
	public static boolean isMobileNO(String mobile) {
		Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param text
	 *            字符串
	 */
	public static boolean isNULL(String text) {
		if (text == null || text.trim().equals("") || "null".equalsIgnoreCase(text)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param text
	 *            字符串
	 */
	public static boolean isNotNULL(String text) {
		return !isNULL(text);
	}

	/**
	 * 输入流转换字符串
	 * 
	 * @param in
	 */
	public static String getStrFromInputStream(InputStream in) {
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = "";
			while ((line = bf.readLine()) != null) {
				buffer.append(line);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}
