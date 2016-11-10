package me.firstapp.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.firstapp.module.member.Member;
import me.firstapp.repository.member.MemberRepository;
import me.firstapp.service.member.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public Member getMemberByName(String name) {
		return memberRepository.getMemberByName(name);
	}

}
