package me.firstapp.bbs.controller.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/topic")
public class TopicController {

	@RequestMapping(value = "/create.htm")
	public String showCreateTopicView() {
		return "topic/createTopic";
	}
}
