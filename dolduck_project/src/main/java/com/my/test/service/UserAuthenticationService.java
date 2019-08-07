package com.my.test.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.my.test.dto.MemberDto;

public class UserAuthenticationService implements UserDetailsService {
	
	@Inject 
	SqlSessionTemplate sqlsession;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	public UserAuthenticationService() {}
	
	public UserAuthenticationService(SqlSessionTemplate sqlsession) {

		this.sqlsession = sqlsession;
	}

	//로그인 인증을 처리하는 코드 
	//파라미터로 입력된 아이디 값에 해당하는 테이블의 레코드를 읽어옴
	//정보가 없으면 예외를 발생시킴 , 정보가 있으면 해당 정보가  map(dto)로 리턴됨	
	
	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		 
		Map<String, Object> user = sqlsession.selectOne("member.selectUser", user_id);
		//비밀번호 체크로직은 시큐리티안에 숨어있음 , 따라서 id만 보내도 id pw 모두 체크하여 일치하면 map으로 정보가 넘어옴 
		
		System.out.println("UserAuthenticationService: 1");
		System.out.println("id: "+user_id);
		System.out.println("user"+user);
		
		if(user == null) {
			throw new UsernameNotFoundException(user_id);
		}
		
		List<GrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority(user.get("AUTHORITY").toString()));
				
		return new MemberDto(user.get("USERNAME").toString(), 
							   user.get("PASSWORD").toString(), 
							   (Integer)Integer.valueOf(user.get("ENABLED").toString())==1, 
							   true, true, true, authority,
							   (Integer)Integer.valueOf(user.get("MEMBER_SEQ").toString()),
							   user.get("MEMBERNAME").toString(),
							   user.get("MEMBER_PHONE").toString(),
							   user.get("MEMBER_ADDR").toString(),
							   user.get("MEMBER_EMAIL").toString(),
							   user.get("AUTHORITY").toString(),
							   (Integer)Integer.valueOf(user.get("MEMBER_VOTE").toString())
				);
		

	}

}
