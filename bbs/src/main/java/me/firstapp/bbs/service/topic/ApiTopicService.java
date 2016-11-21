package me.firstapp.bbs.service.topic;

import me.firstapp.bbs.module.ApiJsonPage;
import me.firstapp.bbs.module.ApiJsonTopic;

public interface ApiTopicService {

	/**
	 * 新增话题
	 * 
	 * @param memberId
	 *            用户id (必填)
	 * @param sectionId
	 *            板块id (必填)
	 * @param title
	 *            标题 (必填)
	 * @param content
	 *            内容
	 * @return
	 */
	ApiJsonTopic addTopic(Long memberId, Long sectionId, String title, String content);

	/**
	 * 分页获取全部话题
	 * 
	 * @param pageNo
	 *            页码(必填)
	 * @param pageSize
	 *            页容量(必填)
	 * @param sectionId
	 *            板块id(非必填)
	 */
	ApiJsonPage<ApiJsonTopic> findTopicsPage(Integer pageNo, Integer pageSize, Long sectionId);

	/**
	 * 通过话题id查询话题
	 * 
	 * @param topicId
	 *            话题id(必填)
	 */
	ApiJsonTopic findTopic(Long topicId);
}
