package me.firstapp.repository.member;

import me.firstapp.common.repository.EntityRepository;
import me.firstapp.module.member.Member;

public interface MemberRepository extends EntityRepository<Member> {

	Member findMemberByName(String name);

	Member findmemberByEmail(String email);

	Member findmemberByMobile(String mobile);
}
