package me.firstapp.repository.topic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import me.firstapp.common.repository.Page;
import me.firstapp.module.topic.Topic;
import me.firstapp.repository.base.EntityRepositoryImpl;
import me.firstapp.repository.topic.TopicRepository;

@Repository
public class TopicRepositoryImpl extends EntityRepositoryImpl<Topic> implements TopicRepository {

	@Override
	protected Class<Topic> getPersistentClass() {
		return Topic.class;
	}

	public Page<Topic> findTopicsPage(Integer pageNo, Integer pageSize, Long sectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder(" FROM Topic bean WHERE 1=1 ");

		if (sectionId != null) {
			sql.append(" AND bean.section.id=:sectionId ");
			parameters.put("sectionId", sectionId);
		}

		return super.queryForPage(Topic.class, sql.toString(), parameters, pageNo, pageSize);
	}

	public Topic findTopicByTitle(String title) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder(" FROM Topic bean WHERE 1=1 ");

		sql.append(" AND bean.title=:title ");
		parameters.put("title", title);

		List<Topic> topics = super.query(Topic.class, sql.toString(), parameters);
		return topics.isEmpty() ? null : topics.get(0);
	}

}
