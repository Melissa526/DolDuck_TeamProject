package com.my.test.model.biz;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.my.test.dto.MemberDto;
import com.my.test.dto.MemberJoinDto;

public interface MemberBiz {
	public List<MemberDto> selectList();
//	public MemberDto selectOne(String id);
	public int insert(MemberDto dto);
	public int update(MemberDto dto);
	public int delete(String id);
	public int muldel(String[] chk);
	
//	public MemberDto login(String id, String pw);
	
	public int insertUser(Map<String, String> map);
	public Map<String, Object> selectUser(String user_id);
	public MemberDto loginDo(String id, String pw);
	public MemberDto loginChk(String id, String pw);
	public int register(Map<String, String> map);
	public MemberJoinDto idCheck(String id);
	public MemberDto nickCheck(String nickname);
	public int updateUserInfo(MemberDto dto);
	public int updateUserPw(MemberDto dto);
	public MemberDto findId(String name, String nickname);
	public MemberDto findPw(String name, String id);
	public int changePw(String pw, String id);
	public boolean usersNameCheck(String name);
}
