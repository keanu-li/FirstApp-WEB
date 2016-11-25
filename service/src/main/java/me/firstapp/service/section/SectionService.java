package me.firstapp.service.section;

import java.util.List;

import me.firstapp.module.section.Section;

public interface SectionService {

	/**
	 * 获取全部板块
	 */
	List<Section> findAll();

	/**
	 * 新增
	 * 
	 * @param name
	 *            板块名称 （必传）
	 * @param description
	 *            板块描述
	 */
	Section addSection(String name, String description);
}
