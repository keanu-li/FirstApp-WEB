package me.firstapp.service.section.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.firstapp.module.section.Section;
import me.firstapp.repository.section.SectionRepository;
import me.firstapp.service.section.SectionService;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionRepository sectionRepository;

	public List<Section> findAll() {
		return sectionRepository.findAll();
	}

}
