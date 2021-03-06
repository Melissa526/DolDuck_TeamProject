package com.my.dolduck.model.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dolduck.model.dto.MemberDto;
import com.my.dolduck.model.dto.MemberJoinDto;
import com.my.dolduck.model.dto.SelectDto;
import com.my.dolduck.model.dao.MemberDao;
import com.my.dolduck.model.dto.VoteDto;

@Service
public class MemberBizImpl implements MemberBiz {

	@Autowired
	private MemberDao dao;
	
	@Override
	public List<SelectDto> selectList() {
		return dao.selectList();
	}

	@Override
	public int insertUser(Map<String, String> map) {
		return dao.insertUser(map);
	}

	@Override
	public Map<String, Object> selectUser(String user_id) {
		return dao.selectUser(user_id);
	}

	@Override
	public MemberDto loginDo(String id, String pw) {
		return dao.loginDo(id, pw);
	}

	@Override
	public MemberDto loginChk(String id, String pw) {
		return dao.loginChk(id, pw);
	}

	@Override
	public int register(Map<String, String> map) {
		return dao.register(map);
	}

	@Override
	public MemberJoinDto idCheck(String id) {
		return dao.idCheck(id);
	}

	@Override
	public int updateMember(Map<String, String> map) {
		return dao.updateMember(map);
	}

	@Override
	public int updateUserPw(MemberDto dto) {
		return dao.updateUserPw(dto);
	}

	@Override
	public SelectDto idSearch(String name, String email) {
		return dao.idSearch(name, email);
	}

	@Override
	public SelectDto pwSearch(String id, String email) {
		return dao.pwSearch(id, email);
	}

	@Override
	public int changePw(String pw, String id) {
		return dao.changePw(pw, id);
	}

	@Override
	public int purchaseHeart(int amount, String id) {
	
		return dao.purchaseHeart(amount, id);
	}

	@Override
	public int purchaseVote(int amount, String id) {
		return dao.purchaseVote(amount, id);
	}

	public int updateRole(String role, String id) {
		return dao.updateRole(role, id);
	}

	@Override
	public int deleteMember(String member_id) {
		return dao.deleteMember(member_id);
	}

	@Override
	public SelectDto selectId(String id) {
		return dao.selectId(id);
	}


	
}
