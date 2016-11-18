package me.firstapp.repository.topic.impl;

import org.springframework.stereotype.Repository;

import me.firstapp.module.topic.Topic;
import me.firstapp.repository.base.EntityRepositoryImpl;
import me.firstapp.repository.topic.TopicRepository;

@Repository
public class TopicRepositoryImpl extends EntityRepositoryImpl<Topic> implements TopicRepository {

	@Override
	protected Class<Topic> getPersistentClass() {
		return Topic.class;
	}

}
