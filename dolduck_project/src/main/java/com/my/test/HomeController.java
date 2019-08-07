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
import com.my.test.vote.Star;
import com.my.test.vote.StarScrap0;
import com.my.test.vote.StarScrap1;
import com.my.test.vote.StarScrap2;
import com.my.test.vote.StarScrap3;
import com.my.test.vote.StarScrap4;
import com.my.test.vote.StarScrap5;

@Controller
public class HomeController {
	
	@Autowired
	private MemberBiz biz;
	private WebScrap crawling = new WebScrap();
	private StarScrap0 star0 = new StarScrap0();
	private StarScrap1 star1 = new StarScrap1();
	private StarScrap2 star2 = new StarScrap2();
	private StarScrap3 star3 = new StarScrap3();
	private StarScrap4 star4 = new StarScrap4();
	private StarScrap5 star5 = new StarScrap5();
	
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
	
	/************************* Vote 게시판 ************************************/
	@RequestMapping("vote.do")
	public String showStarChart() {
		return "vote/star";
	}
	
	
	@RequestMapping("votesearch0.do")
	@ResponseBody
	public JSONObject getStar0() {
		System.out.println();
		List<Star> list = new ArrayList<Star>();
		
		list = star0.getStarChart();
		
		//JSON타입으로 파싱
		JSONObject starChart = new JSONObject();
		JSONArray starArr = new JSONArray();

		for(Star starOne : list) {
			JSONObject star0 = new JSONObject();
			
			star0.put("name", starOne.getName());
			star0.put("img", starOne.getImg());
			
			starArr.add(star0);
		}
	////Realtime 시간얻기
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String str = dayTime.format(new Date(time));
			starChart.put("getTime", str);
		starChart.put("starChart", starArr);
		System.out.println("starChart:"+starChart);
		return starChart;
	}
	
	@RequestMapping("votesearch1.do")
	@ResponseBody
	public JSONObject getStar1() {
		System.out.println();
		List<Star> list = new ArrayList<Star>();
		
		list = star1.getStarChart();
		
		//JSON타입으로 파싱
		JSONObject starChart = new JSONObject();
		JSONArray starArr = new JSONArray();

		for(Star starOne : list) {
			JSONObject star1 = new JSONObject();
			
			star1.put("name", starOne.getName());
			star1.put("img", starOne.getImg());
			
			starArr.add(star1);
		}
	////Realtime 시간얻기
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String str = dayTime.format(new Date(time));
			starChart.put("getTime", str);
		starChart.put("starChart", starArr);
		System.out.println("starChart:"+starChart);
		return starChart;
	}
	
	@RequestMapping("votesearch2.do")
	@ResponseBody
	public JSONObject getStar2() {
		System.out.println();
		List<Star> list = new ArrayList<Star>();
		
		list = star2.getStarChart();
		
		//JSON타입으로 파싱
		JSONObject starChart = new JSONObject();
		JSONArray starArr = new JSONArray();

		for(Star starOne : list) {
			JSONObject star2 = new JSONObject();
			
			star2.put("name", starOne.getName());
			star2.put("img", starOne.getImg());
			
			starArr.add(star2);
		}
	////Realtime 시간얻기
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String str = dayTime.format(new Date(time));
			starChart.put("getTime", str);
		starChart.put("starChart", starArr);
		System.out.println("starChart:"+starChart);
		return starChart;
	}
	
	@RequestMapping("votesearch3.do")
	@ResponseBody
	public JSONObject getStar3() {
		System.out.println();
		List<Star> list = new ArrayList<Star>();
		
		list = star3.getStarChart();
		
		//JSON타입으로 파싱
		JSONObject starChart = new JSONObject();
		JSONArray starArr = new JSONArray();

		for(Star starOne : list) {
			JSONObject star = new JSONObject();
			
			star.put("name", starOne.getName());
			star.put("img", starOne.getImg());
			
			starArr.add(star);
		}
	////Realtime 시간얻기
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String str = dayTime.format(new Date(time));
			starChart.put("getTime", str);
		starChart.put("starChart", starArr);
		System.out.println("starChart:"+starChart);
		return starChart;
	}
	
	@RequestMapping("votesearch4.do")
	@ResponseBody
	public JSONObject getStar4() {
		System.out.println();
		List<Star> list = new ArrayList<Star>();
		
		list = star4.getStarChart();
		
		//JSON타입으로 파싱
		JSONObject starChart = new JSONObject();
		JSONArray starArr = new JSONArray();

		for(Star starOne : list) {
			JSONObject star = new JSONObject();
			
			star.put("name", starOne.getName());
			star.put("img", starOne.getImg());
			
			starArr.add(star);
		}
	////Realtime 시간얻기
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String str = dayTime.format(new Date(time));
			starChart.put("getTime", str);
		starChart.put("starChart", starArr);
		System.out.println("starChart:"+starChart);
		return starChart;
	}
	
	@RequestMapping("votesearch5.do")
	@ResponseBody
	public JSONObject getStar5() {
		System.out.println();
		List<Star> list = new ArrayList<Star>();
		
		list = star5.getStarChart();
		
		//JSON타입으로 파싱
		JSONObject starChart = new JSONObject();
		JSONArray starArr = new JSONArray();

		for(Star starOne : list) {
			JSONObject star = new JSONObject();
			
			star.put("name", starOne.getName());
			star.put("img", starOne.getImg());
			
			starArr.add(star);
		}
	////Realtime 시간얻기
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String str = dayTime.format(new Date(time));
			starChart.put("getTime", str);
		starChart.put("starChart", starArr);
		System.out.println("starChart:"+starChart);
		return starChart;
	}
	
	
	
	/************************* 회원가입 ***********************************/
	
	@RequestMapping("join.do")
	public String insert() {
		return "member/Join";
		
	}
	
	
}
