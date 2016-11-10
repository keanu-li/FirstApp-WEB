package me.firstapp.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串的帮助类，提供静态方法，不可以实例化。
 */
public class StrUtils {
	/**
	 * 禁止实例化
	 */
	private StrUtils() {
	}

	public static String formatDouble2(Double d) {
		if (d == null) {
			return "0.00";
		}
		return String.format("%.2f", d);
	}

	public static String formatLong(Long l) {
		if (l == null) {
			return "0";
		}
		return l.toString();

	}

	public static String formatDouble0(Double d) {
		if (d == null) {
			return "0";
		}
		return String.format("%.0f", d);
	}

	/**
	 * 判断字符串是否为邮件地址
	 * 
	 * @param text
	 *            字符串
	 * @return
	 */
	public static boolean isMail(String text) {
		// 若为空，返回false
		if (isNULL(text)) {
			return false;
		}
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
	 */
	public static boolean isNotNULL(String text) {
		return !isNULL(text);
	}

	/**
	 * 处理url
	 * 
	 * url为null返回null，url为空串或以http://或https://开头，则加上http://，其他情况返回原参数。
	 * 
	 * @param url
	 * @return
	 */
	public static String handelUrl(String url) {
		if (url == null) {
			return null;
		}
		url = url.trim();
		if (url.equals("") || url.startsWith("http://") || url.startsWith("https://")) {
			return url;
		} else {
			return "http://" + url.trim();
		}
	}

	public static boolean isLike(String str, String regEx) {
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(str);
		boolean result = m.find();
		return result;
	}

	/**
	 * 去除文字中Html标签,为手机服务
	 * 
	 * @param text
	 *            文本
	 * @return 去除掉
	 */
	public static String removeHtml(String text) {
		if (isNULL(text)) {
			return "";
		}
		return text.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "")
				.replaceAll("&nbsp;", "").replaceAll("\r", "").replaceAll("\n", "");
	}

	/**
	 * 将NULL转为空字符串
	 * 
	 * @param property
	 * @return
	 */
	public static String convertNullToBlankStr(String property) {
		if (isNULL(property)) {
			return "";
		} else {
			return property;
		}
	}

	public static String join(String join, Object[] objects) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < objects.length; i++) {
			if (i == (objects.length - 1)) {
				sb.append(objects[i]);
			} else {
				sb.append(objects[i]).append(join);
			}
		}

		return new String(sb);
	}
	
	public static void main(String[] args) {
		System.out.println(isAbc123_("123qwe_="));
	}
}
