package me.firstapp.api.member;

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
import me.firstapp.common.service.EmailService;
import me.firstapp.common.utils.JsonWriter;
import me.firstapp.common.utils.ResponseUtils;
import me.firstapp.common.utils.StrUtils;
import me.firstapp.module.member.Member;
import me.firstapp.service.member.MemberService;

@Controller
@RequestMapping(value = "/member")
@Api(value = "/member", description = "Member相关接口")
public class MemberApi {

	@Autowired
	private MemberService memberService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "register.do", method = RequestMethod.POST)
	@ApiOperation(value = "用户注册", notes = "用户注册")
	public void register(String mobile, String email, String name, String password, String code,
			HttpServletRequest request, HttpServletResponse response) {
		SingleObject<Member> resultJsonObject = new SingleObject<Member>();
		try {
			if (StrUtils.isNULL(code)) {
				throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "code");
			}
			if (StrUtils.isNULL(email)) {
				throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "email");
			}
			if (StrUtils.isNULL(name)) {
				throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "name");
			}
			if (StrUtils.isNULL(password)) {
				throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "password");
			}
			if (!StrUtils.isMail(email)) {
				throw new ServiceException(StatusHouse.EMAIL_FORMAT_WRONG);
			}
			Member member = null;
			if (StrUtils.isNotNULL(mobile)) {
				if (!StrUtils.isMobileNO(mobile)) {
					throw new ServiceException(StatusHouse.MOBILE_FORMAT_WRONG);
				}
				member = memberService.findMemberByMobile(mobile);
				if (member != null) {
					throw new ServiceException(StatusHouse.MOBILE_IS_EXIST);
				}
			}
			member = memberService.findMemberByEmail(email);
			if (member != null) {
				throw new ServiceException(StatusHouse.EMAIL_IS_EXIST);
			}
			member = memberService.findMemberByName(name);
			if (member != null) {
				throw new ServiceException(StatusHouse.NAME_IS_EXIST);
			}
			String _code = emailService.getMAilCode(email);
			if (StrUtils.isNULL(_code) || !_code.equals(code)) {
				throw new ServiceException(StatusHouse.VERIFY_CODE_WRONG);
			}
			member = memberService.addMember(name, password, null, email, mobile, null, null);
			resultJsonObject.setStatusObject(StatusHouse.COMMON_STATUS_OK);
			resultJsonObject.setObject(member);
		} catch (ServiceException e) {
			resultJsonObject.setStatusObject(e.getStatusObject());
		} catch (Exception e) {
			e.printStackTrace();
			resultJsonObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
		}
		ResponseUtils.renderJson(response, JsonWriter.toJson(resultJsonObject, true));
	}

	@RequestMapping(value = "sendEmailCode.do", method = RequestMethod.POST)
	@ApiOperation(value = "发送邮件验证码", notes = "发送邮件验证码")
	public void sendEmailCode(String email, HttpServletRequest request, HttpServletResponse response) {
		SingleObject<String> resultJsonObject = new SingleObject<String>();
		try {
			if (StrUtils.isNULL(email)) {
				throw new ServiceException(StatusHouse.COMMON_STATUS_PARAM_REQUIRED_ERROR, "email");
			}
			if (!StrUtils.isMail(email)) {
				throw new ServiceException(StatusHouse.EMAIL_FORMAT_WRONG);
			}
			String code = emailService.sendMailCode(email);
			System.out.println("邮件验证码发送成功:" + code);
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
