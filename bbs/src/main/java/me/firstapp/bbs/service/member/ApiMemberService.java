package me.firstapp.bbs.service.member;

import com.alibaba.fastjson.JSONObject;

import me.firstapp.bbs.module.ApiJsonMember;

public interface ApiMemberService {

	ApiJsonMember register(String email, String name, String password, String code);

	JSONObject sendEmailCode(String email);
}
