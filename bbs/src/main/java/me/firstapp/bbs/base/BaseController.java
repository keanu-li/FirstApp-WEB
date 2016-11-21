package me.firstapp.bbs.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.RemoteConnectFailureException;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import me.firstapp.bbs.module.ApiJsonMember;
import me.firstapp.common.exception.ServiceException;
import me.firstapp.common.exception.StatusHouse;
import me.firstapp.common.json.AbstractJsonObject;
import me.firstapp.common.utils.JsonWriter;
import me.firstapp.common.utils.ResponseUtils;

public class BaseController implements HandlerExceptionResolver {
	private static final String REQUEST_HTM = ".htm";
	private static final String REQUEST_DO = ".do";
	private static final String SERVICE_EXCEPTION = "_exception";
	private static final String ERROR_PAGE = "error/error";

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {

		e.printStackTrace();
		ModelMap modelMap = new ModelMap();
		AbstractJsonObject abstractJsonObject = new AbstractJsonObject();

		if (e instanceof ServiceException) {
			ServiceException serviceException = (ServiceException) e;
			if (request.getRequestURI().endsWith(REQUEST_HTM)) {
				modelMap.put(SERVICE_EXCEPTION, serviceException);
				return new ModelAndView(ERROR_PAGE, modelMap);
			} else if (request.getRequestURI().endsWith(REQUEST_DO)) {
				abstractJsonObject.setCode(serviceException.getCode());
				abstractJsonObject.setMsg(serviceException.getStatusObject().getMsg());
				ResponseUtils.renderJson(response, JsonWriter.toJson(abstractJsonObject, true));
				return new ModelAndView();
			} else {
				return new ModelAndView();
			}
		} else if (e instanceof RemoteConnectFailureException) {
			modelMap.put(SERVICE_EXCEPTION, new ServiceException(StatusHouse.COMMON_STATUS_REQUEST_TIMEOUT));
			return new ModelAndView(ERROR_PAGE, modelMap);
		} else {
			return new ModelAndView(ERROR_PAGE, modelMap);
		}

	}

	protected ApiJsonMember getLoginMember() {
		ApiJsonMember member = new ApiJsonMember();
		member.setId(1L);
		return member;
	}

}
