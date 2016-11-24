package me.firstapp.common.utils;

import java.io.File;
import java.util.Map;
import java.util.Set;

import io.jstack.sendcloud4j.SendCloud;
import io.jstack.sendcloud4j.mail.Email;
import io.jstack.sendcloud4j.mail.Result;
import io.jstack.sendcloud4j.mail.Substitution;
import io.jstack.sendcloud4j.mail.Substitution.Sub;

public class SendMail {
	private static final String API_USER = "keanu_test_p1hmrQ";
	private static final String API_KEY = "4AI7udUbCXDAirdZ";

	private static SendCloud sendCloud = SendCloud.createWebApi(API_USER, API_KEY);

	/**
	 * 普通邮件，邮件内容支持 HTML 或文本
	 * 
	 * @param from
	 *            发信邮箱
	 * @param to
	 *            收信邮箱
	 * @param fromName
	 *            发信人
	 * @param html
	 *            html文本
	 * @param subject
	 *            邮件标题
	 * @param attachment
	 *            附件
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Result sendGeneralMail(String from, String to, String fromName, String html, String subject,
			File attachment) {
		Email email = Email.general()//
				.from(from)// 发信邮箱
				.fromName(fromName)// 发信人
				.html(html)// html文本
				.subject(subject)// 邮件标题
				.attachment(attachment)// 附件
				.to(to);// 收信邮箱
		return sendCloud.mail().send(email);
	}

	/**
	 * 模板邮件
	 * 
	 * @param from
	 *            发信邮箱
	 * @param fromName
	 *            发信人
	 * @param to
	 *            收信邮箱
	 * @param attachment
	 *            添加附件
	 * @param templateName
	 *            模板名称
	 * @param keyValues
	 *            模板参数
	 */
	@SuppressWarnings("rawtypes")
	public static Result sendTemplateMail(String from, String fromName, String to, File attachment, String templateName,
			Map<String, String> keyValues) {
		Sub sub = Substitution.sub();
		Set<String> keys = keyValues.keySet();
		for (String key : keys) {
			sub.set(key, keyValues.get(key));
		}
		Email email = Email.template(templateName)// 模板名称
				.from(from)// 发信邮箱
				.fromName(fromName)// 发信人
				.substitutionVars(sub)// 模板参数
				.attachment(attachment)// 添加附件
				.to(to);// 收信邮箱

		return sendCloud.mail().send(email);
	}

	public static void main(String[] args) {
		Result result = sendGeneralMail("test@qq.com", "1046566144@qq.com", "keanu", "这是测试", "测试", null);
		System.out.println("请求是否成功：" + result.isSuccess());// API 请求是否成功
		System.out.println("返回码:" + result.getStatusCode());// API 返回码
		System.out.println("返回码的中文解释:" + result.getMessage());// API 返回码的中文解释
	}
}
