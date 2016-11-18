package me.firstapp.bbs.controller.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import me.firstapp.bbs.base.BaseController;

@Controller
@RequestMapping("/topic")
public class TopicController extends BaseController {

	@RequestMapping(value = "/create.htm")
	public String showCreateTopicView() {
		
		
		return "topic/createTopic";
	}
}
