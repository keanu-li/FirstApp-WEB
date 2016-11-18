package me.firstapp.api.section;

import java.util.List;

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
import me.firstapp.common.json.ListObject;
import me.firstapp.common.utils.JsonWriter;
import me.firstapp.common.utils.ResponseUtils;
import me.firstapp.module.section.Section;
import me.firstapp.service.section.SectionService;

@Controller
@RequestMapping(value = "/section")
@Api(value = "/section", description = "Section相关接口")
public class SectionApi {

	@Autowired
	private SectionService sectionService;

	@RequestMapping(value = "/getAllSections.do", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有板块", notes = "获取所有板块")
	public void getAllSections(HttpServletRequest request, HttpServletResponse response) {
		ListObject<Section> listResult = new ListObject<Section>();
		try {
			List<Section> sections = sectionService.findAll();
			listResult.setItems(sections);
			listResult.setStatusObject(StatusHouse.COMMON_STATUS_OK);
		} catch (ServiceException e) {
			listResult.setStatusObject(e.getStatusObject());
		} catch (Exception e) {
			e.printStackTrace();
			listResult.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
		}
		ResponseUtils.renderJson(response, JsonWriter.toJson(listResult, true));
	}
}
