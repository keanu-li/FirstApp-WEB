package me.firstapp.bbs.service.section;

import java.util.List;

import me.firstapp.bbs.module.ApiJsonSection;

public interface ApiSectionService {

	/**
	 * 获取所有板块
	 */
	List<ApiJsonSection> getApiJsonSectionList();
}
