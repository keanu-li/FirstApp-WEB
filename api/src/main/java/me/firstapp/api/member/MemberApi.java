package me.firstapp.api.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import me.firstapp.module.member.Member;
import me.firstapp.service.member.MemberService;

@Controller
@RequestMapping(value = "/member")
@Api(value = "/member", description = "Member相关接口")
public class MemberApi {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/getMemberByName.do", method = RequestMethod.GET)
	@ApiOperation(value = "根据会员名称查询会员", notes = "根据会员名称查询会员")
	public void getMemberByName(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(111);
		Member member = memberService.getMemberByName("keanu");
		System.out.println(member.getMobile());
	}
}
