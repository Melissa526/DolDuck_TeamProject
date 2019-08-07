package com.my.test.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.test.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<MemberDto> selectList() {
		
		List<MemberDto> list = new ArrayList<MemberDto>();
		try {
			list = sqlSession.selectList(namespace+"selectList");
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public MemberDto selectOne(String id) {
		MemberDto dto = new MemberDto();
		try {
			dto = sqlSession.selectOne(namespace+"selectOne",id);
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public int insert(MemberDto dto) {
		int res = 0;
		try {
			res = sqlSession.insert(namespace+"insert",dto);
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int update(MemberDto dto) {
		int res = 0;
		try {
			res = sqlSession.update(namespace+"update",dto);
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int delete(String id) {
		int res = 0;
		try {
			res = sqlSession.update(namespace+"delete",id);
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int muldel(String[] chk) {
		
		int res = 0;
		List<String> list = new ArrayList<String>();
		
		for(String i : chk) {
			list.add(i);
		}
		
		res = sqlSession.delete(namespace + "muldel", list);
		
		return res;
	}

	@Override
	public MemberDto login(String id, String pw) {
		MemberDto dto = null;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		try {
			dto = sqlSession.selectOne(namespace+"login",map);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
