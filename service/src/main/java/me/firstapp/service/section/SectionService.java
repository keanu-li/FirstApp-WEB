package me.firstapp.service.section;

import java.util.List;

import me.firstapp.module.section.Section;

public interface SectionService {

	/**
	 * 获取全部板块
	 */
	List<Section> findAll();
}
