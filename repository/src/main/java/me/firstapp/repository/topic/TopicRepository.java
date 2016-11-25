package me.firstapp.repository.topic;

import me.firstapp.common.repository.EntityRepository;
import me.firstapp.common.utils.Page;
import me.firstapp.module.topic.Topic;

public interface TopicRepository extends EntityRepository<Topic> {
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
	 * 根据话题标题查询话题
	 * 
	 * @param title
	 *            话题标题 (必传)
	 */
	Topic findTopicByTitle(String title);
}
