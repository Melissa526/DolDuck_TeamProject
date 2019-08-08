package com.my.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.test.dto.MemberDto;
import com.my.test.model.biz.MemberBizImpl;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	BCryptPasswordEncoder passwordEncoder; //비밀번호 암호화 객체
	
	@Inject
	private MemberBizImpl biz;
	
	private String randompassword;
	
	//로그인 페이지로 이동
	@RequestMapping(value="login.do")
	public String loginform() {
		return "/member/login";
	}
	
	//로그인 페이지로 이동
		@RequestMapping(value="/member/login.do")
		public String login() {
			return "/member/login";
		}
		
//	//회원가입페이지로 이동 
//	@RequestMapping("/member/join.do")
//	public String joinform() {
//		return "/member/Join";
//	}
	
		
	//회원가입
	@RequestMapping("register.do") 
	public String userInsert(@RequestParam String user_id, @RequestParam String user_pw,
					@RequestParam String user_name, @RequestParam String user_email,
					@RequestParam String user_phone, @RequestParam String user_addr) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_id", user_id);
		System.out.println("암호화 전 비번" + user_pw);
		String encryptPassword = passwordEncoder.encode(user_pw);
		System.out.println("암호화 후 비번" + encryptPassword);
		map.put("member_pw", encryptPassword);
		map.put("member_name", user_name);
		map.put("member_phone", user_phone);
		map.put("member_addr", user_addr);
		map.put("member_email", user_email);
		
		biz.insertUser(map);	
		
		return "/member/login";
	}

	
	//권한이 없는 사용자에게 안내 페이지 출력
	@RequestMapping("denied")
	public String denied(Model model, AuthenticateAction auth, HttpServletRequest req ) {
		AccessDeniedException ade = (AccessDeniedException)req.getAttribute(WebAttributes.ACCESS_DENIED_403);
		
		model.addAttribute("errMSg", ade);
		return "denied";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginform";
	}
	
	
	
	@RequestMapping(value="idChk.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Boolean> idChk(String id) {
		logger.info("아이디 중복체크");
		
		System.out.println(id);
		boolean idChk = false;
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		
		if(biz.idCheck(id)== null) {
			idChk = true;
			map.put("idChk", idChk);
		}else {
			map.put("idChk", idChk);
		}
		
		return map;
	}

	@RequestMapping(value="nickChk.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Boolean> nickChk(String nickname){
		logger.info("닉네임 중복체크");
		System.out.println(nickname);
		boolean nickChk = false;
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if(biz.nickCheck(nickname) ==null) {
			nickChk = true;
			map.put("nickChk", nickChk);
		}else {
			map.put("nickChk", nickChk);
		}
		return map;
		
	}
	
	
	@RequestMapping(value="sendEmail.do", method=RequestMethod.GET)
	@ResponseBody
	public void sendEmail(String email) throws UnsupportedEncodingException, MessagingException {
		randompassword = MakeRandom.GetRandomPassword();
		System.out.println(randompassword);
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPassword("dkdntvmfjs1");
		mailSender.setPort(587);
		mailSender.setUsername("DOLDuck@gmail.com");
		if(mailSender.getPort()==587) {
			Properties javaMailProperties = new Properties();
			javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
			mailSender.setJavaMailProperties(javaMailProperties);
		}
		
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
		helper.setFrom(new InternetAddress("DOLDuck@gmail.com", "DOLDuck"));
		helper.setTo(new InternetAddress(email,""));
		System.out.println("email:" + email);
		helper.setSubject("[DOLDuck]인증번호 test");
		helper.setText("<a><b style='color:hotpink;'>인증번호 : " +randompassword+"<a>", true);
		
		try {
			mailSender.send(msg);
		} catch (MailException ex) {
			logger.error("메일발송 실패", ex);
		}
		
	}
		
	@ResponseBody
	@RequestMapping(value = "VerifyRecaptcha", method = RequestMethod.POST)
	public int VerifyRecaptcha(HttpServletRequest request) {
		VerifyRecaptcha.setSecretKey("6LfHerAUAAAAACUEUT2MZxaiydRTDktzKSogfRvS");
		String gRecaptchaResponse = request.getParameter("recaptcha");
		System.out.println(gRecaptchaResponse);
		// 0 = 성공, 1 = 실패, -1 = 오류
		try {
			if (VerifyRecaptcha.verify(gRecaptchaResponse))
				return 0;
			else
				return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@RequestMapping(value="emailCheck.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Boolean> email_check(String ranNumPass) {
		
		
		boolean ranChk = false;
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		
		System.out.println(ranNumPass);
		System.out.println(randompassword);
		
		if(randompassword.equals(ranNumPass)) {
			ranChk = true;
			map.put("ranChk", ranChk);
		}else {
			map.put("idchk", ranChk);
		}
		
		return map;
	}
	
	@RequestMapping("test01")
	public String test012(@RequestParam String test) {		
		
	//	System.out.println("auth test 2 : " + auth);
		System.out.println(test);
				
		return "login";
	}
	
	@RequestMapping("test.do")
	public String testpage( Authentication auth) {
		
		//this.auth = auth;
		//Locale locale, Model model, 
		logger.info("test.do 입장~!");		
		
		System.out.println("auth test 1 : " + auth);
		
		MemberDto dto = (MemberDto)auth.getPrincipal();
		String email = dto.getMemebr_email();
		
		System.out.println(email);
		
//		System.out.println("★★★★id★★★="+id);
//		UserInfoDto dto = (UserInfoDto)	auth.getPrincipal();
		logger.info("welcome checkAuth! Authentication is{}.", auth);
//		logger.info("UserAuthenticationService == {}", dto);
		
//		model.addAttribute("auth", auth);
//		model.addAttribute("dto", dto);	
		
		
		System.out.println("★★★★★★★★"+auth.getName());
		System.out.println(""+auth.getAuthorities());

		return "test01";
	}
	
}
