package com.my.test.dto;

public class MemberJoinDto {
	private String member_id;
	private String member_phone;
	private String memebr_addr;
	private String member_email;
	
	public MemberJoinDto() {
		super();
	}
	public MemberJoinDto(String member_id, String member_phone, String memebr_addr, String member_email) {
		super();
		this.member_id = member_id;
		this.member_phone = member_phone;
		this.memebr_addr = memebr_addr;
		this.member_email = member_email;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
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
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	
	
}
