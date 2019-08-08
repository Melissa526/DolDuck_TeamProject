package com.my.test.dto;

import java.util.Date;

public class MemberDto {
	private int member_seq;
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_phone;
	private String memebr_addr;
	private String memebr_email;
	private Date member_regdate;
	private String member_enabled;
	private String member_role;
	private int member_heart;
	private int member_vote;

	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String username, String password, boolean enabled, 
			boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, int member_seq, String member_id, String member_phone, String memebr_addr,
			String memebr_email, String member_role, int member_heart, int member_vote ){
		//userClass에 보내는 코드
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		this.member_seq = member_seq;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_phone = member_phone;
		this.memebr_addr = memebr_addr;
		this.memebr_email = memebr_email;
		this.member_regdate = member_regdate;
		this.member_enabled = member_enabled;
		this.member_role = member_role;
		this.member_heart = member_heart;
		this.member_vote = member_vote;
	}
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMemebr_addr() {
		return memebr_addr;
	}
	public void setMemebr_addr(String memebr_addr) {
		this.memebr_addr = memebr_addr;
	}
	public String getMemebr_email() {
		return memebr_email;
	}
	public void setMemebr_email(String memebr_email) {
		this.memebr_email = memebr_email;
	}
	public Date getMember_regdate() {
		return member_regdate;
	}
	public void setMember_regdate(Date member_regdate) {
		this.member_regdate = member_regdate;
	}
	public String getMember_enabled() {
		return member_enabled;
	}
	public void setMember_enabled(String member_enabled) {
		this.member_enabled = member_enabled;
	}
	public String getMember_role() {
		return member_role;
	}
	public void setMember_role(String member_role) {
		this.member_role = member_role;
	}
	
	public int getMember_heart() {
		return member_heart;
	}

	public void setMember_heart(int member_heart) {
		this.member_heart = member_heart;
	}

	public int getMember_vote() {
		return member_vote;
	}
	public void setMember_vote(int member_vote) {
		this.member_vote = member_vote;
	}
	@Override
	public String toString() {
		return "MemberDto [member_seq=" + member_seq + ", member_id=" + member_id + ", member_phone=" + member_phone
				+ ", memebr_addr=" + memebr_addr + ", memebr_email=" + memebr_email + ", member_regdate="
				+ member_regdate + ", member_role=" + member_role + ", member_heart=" + member_heart + ", member_vote="
				+ member_vote + "]";
	}
	
	
}
