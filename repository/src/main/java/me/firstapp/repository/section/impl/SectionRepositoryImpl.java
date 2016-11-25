package me.firstapp.repository.section.impl;

import org.springframework.stereotype.Repository;

import me.firstapp.common.repository.impl.EntityRepositoryImpl;
import me.firstapp.module.section.Section;
import me.firstapp.repository.section.SectionRepository;

@Repository
public class SectionRepositoryImpl extends EntityRepositoryImpl<Section> implements SectionRepository {

	@Override
	protected Class<Section> getPersistentClass() {
		return Section.class;
	}

}
