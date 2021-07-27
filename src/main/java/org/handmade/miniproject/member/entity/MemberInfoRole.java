package org.handmade.miniproject.member.entity;

import java.util.Objects;

public class MemberInfoRole {
	private String member_info_username;
	private String member_role_username;
	public MemberInfoRole() {}
	public MemberInfoRole(String member_info_username, String member_role_username) {
		super();
		this.member_info_username = member_info_username;
		this.member_role_username = member_role_username;
	}
	public String getAccount_num() {
		return member_info_username;
	}
	public void setMember_info_username(String member_info_username) {
		this.member_info_username = member_info_username;
	}
	public String getMember_role_username() {
		return member_role_username;
	}
	public void setMember_role_username(String member_role_username) {
		this.member_role_username = member_role_username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MemberInfoRole role = (MemberInfoRole) o;
		return Objects.equals(member_info_username, role.member_info_username) && Objects.equals(member_role_username, role.member_role_username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(member_info_username, member_role_username);
	}

	@Override
	public String toString() {
		return "MemberRole{" +
				"member_info_username='" + member_info_username + '\'' +
				", member_role_username='" + member_role_username + '\'' +
				'}';
	}
}
