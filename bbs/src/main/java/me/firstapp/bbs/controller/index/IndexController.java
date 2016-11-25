package me.firstapp.bbs.controller.index;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import me.firstapp.bbs.base.BaseController;
import me.firstapp.bbs.module.ApiJsonMember;
import me.firstapp.bbs.module.ApiJsonPage;
import me.firstapp.bbs.module.ApiJsonSection;
import me.firstapp.bbs.module.ApiJsonTopic;
import me.firstapp.bbs.service.member.ApiMemberService;
import me.firstapp.bbs.service.section.ApiSectionService;
import me.firstapp.bbs.service.topic.ApiTopicService;
import me.firstapp.common.utils.JsonWriter;
import me.firstapp.common.utils.ResponseUtils;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private ApiSectionService apiSectionService;

	@Autowired
	private ApiTopicService apiTopicService;

	@Autowired
	private ApiMemberService apiMemberService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
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

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return new ModelAndView("auth/register", modelMap);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(String email, String name, String password, String code, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		ApiJsonMember apiJsonMember = apiMemberService.register(email, name, password, code);
		modelMap.put("member", apiJsonMember);
		return new ModelAndView("auth/register", modelMap);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		//String loginToken = UUIDUtils.getLoginToken();
		//request.getSession().setAttribute("loginToken", loginToken);
		//cache.put(MEMBER_CACHE_KEY_ + loginToken, "memberId", result.getObject().getId(), CacheLifeTime.ONE_WEEK);
		return new ModelAndView("auth/login", modelMap);
	}

	@RequestMapping(value = "sendEmailCode.do", method = RequestMethod.POST)
	public void sendEmailCode(String email, HttpServletRequest request, HttpServletResponse response) {
		JSONObject resultJson = apiMemberService.sendEmailCode(email);
		ResponseUtils.renderJson(response, JsonWriter.toJson(resultJson, true));
	}
}
