package me.firstapp.service.topic.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.firstapp.common.exception.ServiceException;
import me.firstapp.common.exception.StatusHouse;
import me.firstapp.common.utils.StrUtils;
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

	public Topic addTopic(Integer memberId, Integer sectionId, String title, String content) {
		if (memberId == null) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "用户id");
		}
		if (sectionId == null) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "板块id");
		}
		if (StrUtils.isNULL(title)) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "title");
		}
		if (StrUtils.isNULL(content)) {
			throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "content");
		}

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

}
