package me.firstapp.bbs.controller.index;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import me.firstapp.bbs.module.ApiJsonPage;
import me.firstapp.bbs.module.ApiJsonSection;
import me.firstapp.bbs.module.ApiJsonTopic;
import me.firstapp.bbs.service.section.ApiSectionService;
import me.firstapp.bbs.service.topic.ApiTopicService;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private ApiSectionService apiSectionService;

	@Autowired
	private ApiTopicService apiTopicService;

	@RequestMapping(value = "/index.htm")
	public ModelAndView index(@RequestParam(value = "pageNo", required = true, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sectionId", required = true, defaultValue = "1") Long sectionId,
			HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<ApiJsonSection> sections = apiSectionService.getApiJsonSectionList();
		ApiJsonPage<ApiJsonTopic> topicPage = apiTopicService.findTopicsPage(pageNo, pageSize, sectionId);
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= topicPage.getTotalPage(); i++) {
			pages.add(i);
		}
		modelMap.put("sections", sections);
		modelMap.put("topicPage", topicPage);
		modelMap.put("pages", pages);
		modelMap.put("sectionId", sectionId);
		return new ModelAndView("index/index", modelMap);
	}
}
