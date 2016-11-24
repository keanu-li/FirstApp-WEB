package me.firstapp.bbs.module;

import java.io.IOException;

import org.markdown4j.Markdown4jProcessor;

import me.firstapp.common.utils.StrUtils;

public class ApiJsonBase {
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 解析markdown文章
	 * 
	 * @param content
	 */
	public String markedownToHtml(String content) {
		if (StrUtils.isNULL(content)) {
			return "";
		}
		String hContent = "";
		// markdown 转 html 并返回
		try {
			hContent = new Markdown4jProcessor().process(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hContent;
	}

}
