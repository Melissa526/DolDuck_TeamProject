package com.my.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.test.dto.MemberDto;
import com.my.test.model.biz.MemberBiz;
import com.my.test.util.Music;
import com.my.test.util.WebScrap;

@Controller
public class HomeController {
	
	@Autowired
	private MemberBiz biz;
	private WebScrap crawling = new WebScrap();
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/************************** 로그인 ***************************/
	@RequestMapping("loginform.do")
	public String loginform() {
		return "/member/login";
	}
	
	@RequestMapping("login.do")
	@ResponseBody
	public Map<String, Boolean> login(String id, String pw, HttpSession session){
		System.out.println("여기 들어옴");
		/*
		 * @ResponseBody: java 객체를 response객체에 binding
		 * @RequestBody: request객체로 넘어오는 데이터를 java 객체
		 * */
		
		MemberDto dto = biz.login(id, pw);
		boolean loginChk = false;
		System.out.println(loginChk+"로그인 체크값");
		if(dto!=null) {
			session.setAttribute("login", dto);
			loginChk=true;
		}
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("loginChk", loginChk);
		
		return map;
	}
	
	/************************** Music Chart 게시판 ***************************/
	@RequestMapping("chart.do")
	public String showMusicChart() {
		return "board/music_chart";
	}
	
	
	@RequestMapping("musicsearch.do")
	@ResponseBody
	public JSONObject getChart() {
		//멜론차트 크롤링해서 List로 return 
		List<Music> list = new ArrayList<Music>();
		list = crawling.getMusicChart();
				
		//JSON타입으로 파싱
		JSONObject chart = new JSONObject();
		JSONArray songArr = new JSONArray();
		
		for(Music musicOne : list) {
			JSONObject song = new JSONObject();
			
			song.put("rank", musicOne.getRank());
			song.put("thumb", musicOne.getThumb());
			song.put("title", musicOne.getTitle());
			song.put("singer", musicOne.getSinger());
			song.put("album", musicOne.getAlbum());
			songArr.add(song);
		}
		chart.put("chart", songArr);
		
		////Realtime 시간얻기
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = dayTime.format(new Date(time));
		chart.put("getTime", str);
		
		return chart;
	}
	
	/************************** Youtube 게시판 ***************************/
	@RequestMapping("youtube.do")
	public String showYoutubeBoard() {
		return "board/youtube";
	}
	
	/************************* market ************************************/
	@RequestMapping("heartShop.do")
	public String heartShop() {
		return "market/heartShop";
	}
	
	@RequestMapping("Goodies.do")
	public String Goodies() {
		return "market/Goodies";
	}
	
	/************************* 회원가입 ***********************************/
	
	@RequestMapping("join.do")
	public String insert() {
		return "member/Join";
		
	}
	
	
}
