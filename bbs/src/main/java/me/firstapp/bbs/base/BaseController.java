package me.firstapp.bbs.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.RemoteConnectFailureException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

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
		Map<String, Object> model = new HashMap<String, Object>();
		AbstractJsonObject abstractJsonObject = new AbstractJsonObject();

		if (e instanceof ServiceException) {
			ServiceException wapActionException = (ServiceException) e;
			if (request.getRequestURI().endsWith(REQUEST_HTM)) {
				model.put(SERVICE_EXCEPTION, wapActionException);
				return new ModelAndView(ERROR_PAGE, model);
			}
			if (request.getRequestURI().endsWith(REQUEST_DO)) {
				abstractJsonObject.setCode(wapActionException.getCode());
				abstractJsonObject.setMsg(wapActionException.getStatusObject().getMsg());
				ResponseUtils.renderJson(response, JsonWriter.toJson(abstractJsonObject, true));
				return new ModelAndView();
			}
		}
		if (e instanceof RemoteConnectFailureException) {
			model.put(SERVICE_EXCEPTION, new ServiceException(StatusHouse.COMMON_STATUS_REQUEST_TIMEOUT));
			return new ModelAndView(ERROR_PAGE, model);
		}
		e.printStackTrace();
		if (request.getRequestURI().endsWith(REQUEST_HTM)) {
			model.put(SERVICE_EXCEPTION, new ServiceException(StatusHouse.COMMON_STATUS_ERROR));
			return new ModelAndView(ERROR_PAGE, model);
		} else {
			ResponseUtils.renderJson(response, JsonWriter.toJson(abstractJsonObject, true));
			return new ModelAndView();
		}
	}

}
