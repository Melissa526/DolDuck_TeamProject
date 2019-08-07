package com.my.test.model.biz;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.test.dto.MemberDto;
import com.my.test.dto.MemberJoinDto;
import com.my.test.model.dao.MemberDao;
import com.my.test.model.dao.MemberDaoImpl;

@Service
public class MemberBizImpl implements MemberBiz {

	@Autowired
	private MemberDao dao;
	
	@Override
	public List<MemberDto> selectList() {
		return dao.selectList();
	}

//	@Override
//	public MemberDto selectOne(String id) {
//		return dao.selectOne(id);
//	}

	@Override
	public int insert(MemberDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(MemberDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(String id) {
		return dao.delete(id);
	}

	@Override
	public int muldel(String[] chk) {
		return dao.muldel(chk);
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
	public MemberDto nickCheck(String nickname) {
		return dao.nickCheck(nickname);
	}

	@Override
	public int updateUserInfo(MemberDto dto) {
		return dao.updateUserInfo(dto);
	}

	@Override
	public int updateUserPw(MemberDto dto) {
		return dao.updateUserPw(dto);
	}

	@Override
	public MemberDto findId(String name, String nickname) {
		return dao.findId(name, nickname);
	}

	@Override
	public MemberDto findPw(String name, String id) {
		return dao.findPw(name, id);
	}

	@Override
	public int changePw(String pw, String id) {
		return dao.changePw(pw, id);
	}

	@Override
	public boolean usersNameCheck(String name) {
		boolean isCheck=false;
        int check=dao.usersNameCheck(name);
        // 0보다 크면 중복이 존재 true 반환
        if(check>0) {
            isCheck=true;
        }else {
            // 0이면 중복 없음.
            isCheck=false;
        }
        return isCheck;
	}

//	@Override
//	public MemberDto login(String id, String pw) {
//		return dao.login(id, pw);
//	}
	
	
}
