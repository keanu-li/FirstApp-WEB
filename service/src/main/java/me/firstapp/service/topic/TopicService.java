package me.firstapp.service.topic;

import me.firstapp.common.repository.Page;
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
	 *            内容
	 */
	Topic addTopic(Long memberId, Long sectionId, String title, String content);

	/**
	 * 分页查询话题
	 * 
	 * @param pageIndex
	 *            页码(必传)
	 * @param pageSize
	 *            页容量(必传)
	 * @param sectionId
	 *            板块id(非必传)
	 */
	Page<Topic> findTopicsPage(Integer pageNo, Integer pageSize, Long sectionId);

	/**
	 * 根据id查询话题
	 * 
	 * @param topicId
	 *            话题id (必传)
	 */
	Topic findTopicById(Long topicId);

	/**
	 * 根据话题标题查询话题
	 * 
	 * @param title
	 *            话题标题 (必传)
	 */
	Topic findTopicByTitle(String title);
}
