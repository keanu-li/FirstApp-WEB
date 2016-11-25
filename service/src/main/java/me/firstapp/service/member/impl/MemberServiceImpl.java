package me.firstapp.service.member.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.firstapp.common.utils.MD5Utils;
import me.firstapp.module.member.Member;
import me.firstapp.repository.member.MemberRepository;
import me.firstapp.service.member.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public Member findMemberByName(String name) {
		return memberRepository.findMemberByName(name);
	}

	public Member findMemberByEmail(String email) {
		return memberRepository.findmemberByEmail(email);
	}

	public Member findMemberByMobile(String mobile) {
		return memberRepository.findmemberByMobile(mobile);
	}

	public Member addMember(String name, String password, String avatar, String email, String mobile, String signature,
			String url) {
		Member member = new Member();
		member.setName(name);
		member.setPassword(MD5Utils.sign(password));
		member.setAvatar(avatar);
		member.setEmail(email);
		member.setMobile(mobile);
		member.setSignature(signature);
		member.setUrl(url);
		member.setRegisterTime(Calendar.getInstance().getTime());
		memberRepository.save(member);
		return member;
	}

}
