package me.firstapp.service.topic.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.firstapp.common.exception.ServiceException;
import me.firstapp.common.exception.StatusHouse;
import me.firstapp.common.utils.Page;
import me.firstapp.module.member.Member;
import me.firstapp.module.section.Section;
import me.firstapp.module.topic.Topic;
import me.firstapp.repository.member.MemberRepository;
import me.firstapp.repository.section.SectionRepository;
import me.firstapp.repository.topic.TopicRepository;
import me.firstapp.service.topic.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private SectionRepository sectionRepository;

	public Topic addTopic(Long memberId, Long sectionId, String title, String content) {
		Member member = (Member) memberRepository.getById(memberId);
		if (member == null) {
			throw new ServiceException(StatusHouse.MEMBER_NOT_FOUND);
		}
		Section section = (Section) sectionRepository.getById(sectionId);
		if (section == null) {
			throw new ServiceException(StatusHouse.SECTION_NOT_FOUND);
		}

		Topic topic = new Topic();
		topic.setMember(member);
		topic.setSection(section);
		topic.setTitle(title);
		topic.setContent(content);
		topic.setCreateTime(Calendar.getInstance().getTime());
		topicRepository.save(topic);
		return topic;
	}

	public Page<Topic> findTopicsPage(Integer pageNo, Integer pageSize, Long sectionId) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		return topicRepository.findTopicsPage(pageNo, pageSize, sectionId);
	}

	public Topic findTopicById(Long topicId) {
		return (Topic) topicRepository.getById(topicId);
	}

	public Topic findTopicByTitle(String title) {
		return topicRepository.findTopicByTitle(title);
	}

}
