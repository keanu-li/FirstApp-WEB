package me.firstapp.api.topic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import me.firstapp.common.exception.ServiceException;
import me.firstapp.common.exception.StatusHouse;
import me.firstapp.common.json.JsonPage;
import me.firstapp.common.json.PageObject;
import me.firstapp.common.json.SingleObject;
import me.firstapp.common.utils.JsonWriter;
import me.firstapp.common.utils.Page;
import me.firstapp.common.utils.ResponseUtils;
import me.firstapp.common.utils.StrUtils;
import me.firstapp.module.topic.Topic;
import me.firstapp.service.topic.TopicService;

@Controller
@RequestMapping(value = "/topic")
@Api(value = "/topic", description = "Topic相关接口")
public class TopicApi {

	@Autowired
	private TopicService topicService;

	@RequestMapping(value = "/addTopic.do", method = RequestMethod.POST)
	@ApiOperation(value = "创建话题", notes = "创建话题")
	public void addTopic(@RequestBody Map<String, Object> body, Long memberId, Long sectionId,
			HttpServletRequest request, HttpServletResponse response) {
		SingleObject<Topic> resultJsonObject = new SingleObject<Topic>();
		try {
			if (memberId == null) {
				throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "用户id");
			}
			if (sectionId == null) {
				throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "板块id");
			}
			String title = (String) body.get("title");
			if (StrUtils.isNULL(title)) {
				throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "title");
			}
			String content = (String) body.get("content");
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

	@RequestMapping(value = "/findTopics.do", method = RequestMethod.GET)
	@ApiOperation(value = "分页查询话题", notes = "分页查询话题")
	public void findTopics(@RequestParam(value = "pageNo", required = true, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sectionId", required = true, defaultValue = "1") Long sectionId,
			HttpServletRequest request, HttpServletResponse response) {
		PageObject<Topic> resultJsonPage = new PageObject<Topic>();
		try {
			Page<Topic> topicPage = topicService.findTopicsPage(pageNo, pageSize, sectionId);
			JsonPage<Topic> jsonPage = new JsonPage<Topic>();
			jsonPage.setList(topicPage.getEntities());
			jsonPage.setPageNo(topicPage.getPageNo());
			jsonPage.setPageSize(topicPage.getPageSize());
			jsonPage.setTotalCount(topicPage.getEntityCount());
			resultJsonPage.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			resultJsonPage.setPage(jsonPage);
		} catch (ServiceException e) {
			resultJsonPage.setStatusObject(e.getStatusObject());
		} catch (Exception e) {
			e.printStackTrace();
			resultJsonPage.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
		}
		ResponseUtils.renderJson(response, JsonWriter.toJson(resultJsonPage, true));
	}

	@RequestMapping(value = "/findTopicDetail.do", method = RequestMethod.GET)
	@ApiOperation(value = "查询话题详情", notes = "查询话题详情")
	public void findTopicDetail(Long topicId, HttpServletRequest request, HttpServletResponse response) {
		SingleObject<Topic> resultJsonObject = new SingleObject<Topic>();
		try {
			if (topicId == null) {
				throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "话题id");
			}

			Topic topic = topicService.findTopicById(topicId);
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
