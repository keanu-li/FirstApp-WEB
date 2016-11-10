package me.firstapp.repository.member.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import me.firstapp.module.member.Member;
import me.firstapp.repository.base.EntityRepositoryImpl;
import me.firstapp.repository.member.MemberRepository;

@Repository
public class MemberRepositoryImpl extends EntityRepositoryImpl<Member> implements MemberRepository {
	@Override
	protected Class<Member> getPersistentClass() {
		return Member.class;
	}

	public Member getMemberByName(String name) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder(" FROM Member bean WHERE 1=1 ");

		sql.append(" AND bean.name=:name ");
		parameters.put("name", name);

		List<Member> results = super.query(Member.class, sql.toString(), parameters);
		return results.isEmpty() ? null : results.get(0);
	}
}
