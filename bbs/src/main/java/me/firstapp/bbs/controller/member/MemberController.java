package me.firstapp.bbs.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import me.firstapp.bbs.base.BaseController;

@Controller
public class MemberController extends BaseController {

	@RequestMapping(value = "/test.htm")
	public String test(){
		System.out.println(11111111);
		return "hello";
	}
}
