package me.firstapp.api.topic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import me.firstapp.common.exception.ServiceException;
import me.firstapp.common.exception.StatusHouse;
import me.firstapp.common.json.SingleObject;
import me.firstapp.common.utils.JsonWriter;
import me.firstapp.common.utils.ResponseUtils;
import me.firstapp.module.topic.Topic;
import me.firstapp.service.topic.TopicService;

@Controller
@RequestMapping(value = "/topic")
@Api(value = "/topic", description = "Topic相关接口")
public class TopicApi {

	@Autowired
	private TopicService topicService;

	@RequestMapping(value = "/createTopic.do", method = RequestMethod.GET)
	@ApiOperation(value = "创建话题", notes = "创建话题")
	public void createTopic(Integer memberId, Integer sectionId, String title, String content,
			HttpServletRequest request, HttpServletResponse response) {
		SingleObject<Topic> resultJsonObject = new SingleObject<Topic>();
		try {
			Topic topic = topicService.addTopic(memberId, sectionId, title, content);
			resultJsonObject.setObject(topic);
			resultJsonObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
		} catch (ServiceException e) {
			resultJsonObject.setStatusObject(e.getStatusObject());
		} catch (Exception e) {
			e.printStackTrace();
			resultJsonObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
		}
		ResponseUtils.renderJson(response, JsonWriter.toJson(resultJsonObject, true));
	}
}
