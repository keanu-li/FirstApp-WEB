package me.firstapp.service.topic;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import me.firstapp.module.topic.Topic;

public interface TopicService {

	/**
	 * 新增话题
	 * 
	 * @param memberId
	 *            用户id(必传)
	 * @param sectionId
	 *            板块id(必传)
	 * @param title
	 *            标题(必传)
	 * @param content
	 *            内容(必传)
	 */
	@Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
	Topic addTopic(Integer memberId, Integer sectionId, String title, String content);
}
