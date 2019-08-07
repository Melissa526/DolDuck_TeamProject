package com.my.test.model.dao;

import java.util.List;

import com.my.test.dto.MemberDto;

public interface MemberDao {
	String namespace = "member.";
	
	public List<MemberDto> selectList();
	public MemberDto selectOne(String id);
	public int insert(MemberDto dto);
	public int update(MemberDto dto);
	public int delete(String id);
	public int muldel(String[] chk);
	
	public MemberDto login(String id, String pw);
}
