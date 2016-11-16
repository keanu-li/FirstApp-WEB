package me.firstapp.bbs.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@RequestMapping(value = "/test.htm")
	public String test(){
		System.out.println(11111111);
		return "hello";
	}
}
