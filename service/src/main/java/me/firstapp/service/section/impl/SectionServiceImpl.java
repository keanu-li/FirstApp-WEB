package me.firstapp.service.section.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import me.firstapp.module.section.Section;
import me.firstapp.repository.section.SectionRepository;
import me.firstapp.service.section.SectionService;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionRepository sectionRepository;

	@Cacheable(value = "sections")
	public List<Section> findAll() {
		return sectionRepository.findAll();
	}

	@CachePut("sections")
	public Section addSection(String name, String description) {
		Section section = new Section();
		section.setName(name);
		section.setDescription(description);
		sectionRepository.save(section);
		return section;
	}

}
