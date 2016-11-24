package me.firstapp.service.member;

import me.firstapp.module.member.Member;

public interface MemberService {

	Member findMemberByName(String name);

	Member findMemberByEmail(String email);

	Member findMemberByMobile(String mobile);

	/**
	 * 用户新增
	 * 
	 * @param name
	 *            昵称（必传）
	 * @param password
	 *            密码（必传）
	 * @param avatar
	 *            头像
	 * @param email
	 *            邮箱（必传）
	 * @param mobile
	 *            手机号
	 * @param signature
	 *            个性签名
	 * @param url
	 *            个人主页
	 */
	Member addMember(String name, String password, String avatar, String email, String mobile, String signature,
			String url);

}
