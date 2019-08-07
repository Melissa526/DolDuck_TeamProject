package com.my.test.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.test.dto.MemberDto;
import com.my.test.model.dao.MemberDao;

@Service
public class MemberBizImpl implements MemberBiz {

	@Autowired
	private MemberDao dao;
	
	@Override
	public List<MemberDto> selectList() {
		return dao.selectList();
	}

	@Override
	public MemberDto selectOne(String id) {
		return dao.selectOne(id);
	}

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
	public MemberDto login(String id, String pw) {
		return dao.login(id, pw);
	}
}
