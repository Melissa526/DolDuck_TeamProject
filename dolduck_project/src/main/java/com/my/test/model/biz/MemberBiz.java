package com.my.test.model.biz;

import java.util.List;
import java.util.Map;

import com.my.test.dto.MemberDto;
import com.my.test.dto.MemberJoinDto;
import com.my.test.dto.SelectDto;

public interface MemberBiz {
	public List<SelectDto> selectList();

	
	public int insertUser(Map<String, String> map);
	public Map<String, Object> selectUser(String user_id);
	public MemberDto loginDo(String id, String pw);
	public MemberDto loginChk(String id, String pw);
	public int register(Map<String, String> map);
	public MemberJoinDto idCheck(String id);
	public int updateRole(MemberDto dto);
	public int updateMember(MemberDto dto);
	public int updateUserPw(MemberDto dto);
	public MemberDto idSearch(String name, String email);
	public MemberDto pwSearch(String id, String email);
	public int changePw(String pw, String id);
	
	public int purchaseHeart(int amount, String id);
	public int purchaseVote(int amount, String id);


	
}
