package me.firstapp.repository.member;

import me.firstapp.module.member.Member;
import me.firstapp.repository.base.EntityRepository;

public interface MemberRepository extends EntityRepository<Member> {

	Member getMemberByName(String name);
}
