package me.firstapp.bbs.controller.topic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import me.firstapp.bbs.base.BaseController;
import me.firstapp.bbs.module.ApiJsonMember;
import me.firstapp.bbs.module.ApiJsonSection;
import me.firstapp.bbs.module.ApiJsonTopic;
import me.firstapp.bbs.service.section.ApiSectionService;
import me.firstapp.bbs.service.topic.ApiTopicService;

@Controller
@RequestMapping("/topic")
public class TopicController extends BaseController {

	@Autowired
	private ApiSectionService apiSectionService;

	@Autowired
	private ApiTopicService apiTopicService;

	@RequestMapping(value = "/showAddTopicView.htm")
	public ModelAndView showAddTopicView(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<ApiJsonSection> sections = apiSectionService.getApiJsonSectionList();
		modelMap.put("sections", sections);
		return new ModelAndView("topic/createTopic", modelMap);
	}

	@RequestMapping(value = "/addTopic.htm")
	public String addTopic(Long sectionId, String title, String content, HttpServletRequest request,
			HttpServletResponse response) {
		ApiJsonMember member = getLoginMember();
		apiTopicService.addTopic(member.getId(), sectionId, title, content);
		return "topic/createTopic";
	}

	@RequestMapping(value = "/{topicId}", method = RequestMethod.GET)
	public ModelAndView showTopicDetail(@PathVariable Long topicId, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		if (!modelMap.containsAttribute("topic")) {
			ApiJsonTopic topic = apiTopicService.findTopic(topicId);
			modelMap.put("topic", topic);
		}
		return new ModelAndView("topic/topicDetail", modelMap);
	}
}
