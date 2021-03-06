package com.my.dolduck.model.dao;

import java.util.List;
import java.util.Map;

import com.my.dolduck.model.dto.MemberDto;
import com.my.dolduck.model.dto.MemberJoinDto;

import com.my.dolduck.model.dto.VoteDto;
import com.my.dolduck.model.dto.SelectDto;

public interface MemberDao {
	String namespace = "member.";
	
	public List<SelectDto> selectList();
	public int insertUser(Map<String, String> map);
	public Map<String, Object> selectUser(String user_id);
	public MemberDto loginDo(String id, String pw);
	public MemberDto loginChk(String id, String pw);
	public int register(Map<String, String> map);
	public MemberJoinDto idCheck(String id);
	public int updateRole(String role, String id);
	public int updateMember(Map<String, String> map);
	public int updateUserPw(MemberDto dto);
	public SelectDto idSearch(String name, String email);
	public SelectDto pwSearch(String id, String email);
	public int changePw(String pw, String id);
	public int usersNameCheck(String name);

	public int deleteMember(String member_id);
	public int purchaseHeart(int amount, String id);
	public int purchaseVote(int amount, String id);
	public SelectDto selectId(String id);


}
