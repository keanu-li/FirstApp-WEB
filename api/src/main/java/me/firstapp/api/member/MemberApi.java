package me.firstapp.api.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.firstapp.module.member.Member;
import me.firstapp.service.member.MemberService;

@Controller
public class MemberApi {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/getMemberByName", method = RequestMethod.GET)
	public void getMemberByName(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(111);
		Member member = memberService.getMemberByName("keanu");
		System.out.println(member.getMobile());
	}
}
