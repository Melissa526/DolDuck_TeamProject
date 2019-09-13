package com.my.dolduck;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dolduck.model.biz.BroadcastBiz;
import com.my.dolduck.model.biz.MemberBiz;
import com.my.dolduck.model.dto.MemberDto;
import com.my.dolduck.util.Music;
import com.my.dolduck.util.Translate;
import com.my.dolduck.util.WebScrap;

@Controller
public class HomeController {
	@Autowired
	private MemberBiz biz;
	
	@Autowired
	private BroadcastBiz b_biz;

	private WebScrap crawling = new WebScrap();
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/home.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	@RequestMapping("admin.do")
	public String selectList(Model model) {
		model.addAttribute("list",biz.selectList());
		return "admin/admin";
	}
	
	/************************** 로그아웃 ***************************/
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:home.do";
	}
	
	/************************** Music Chart ***************************/
	@RequestMapping("chart.do")
	public String showMusicChart() {
		return "utils/music_chart";
	}
	
	
	@RequestMapping(value = "musicsearch.do", method = {RequestMethod.POST})
	public @ResponseBody JSONObject getChart(String site) {
		
		List<Music> list = new ArrayList<Music>();

		switch(site) {
		case "melon": 
			list = crawling.getMelonChart();
			break;
		case "bugs":
			list = crawling.getBugsChart();
			break;
		case "genie":
			list = crawling.getGenieChart();
			break;
		} 
				
		//JSON Object
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
		
		////Realtime
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = dayTime.format(new Date(time));
		chart.put("getTime", str);
		
		return chart;
	}
	
	/************************** Youtube 게시판  ***************************/
	@RequestMapping("youtube.do")
	public String showYoutubeBoard() {
		return "utils/youtube";
	}
	
	
	/************************* market ************************************/
	@RequestMapping("heartShop.do")
	public String heartShop() {
		return "market/heartShop";
	}

	@RequestMapping("buy-heart.do")
	@ResponseBody
	public String payHeart(@RequestParam int amount, @RequestParam int price, Authentication auth) {
		
		MemberDto dto = (MemberDto)auth.getPrincipal();
		String userId = dto.getUsername();
		String result = "";
		
		int res = biz.purchaseHeart(amount, userId);
		System.err.println("하트구매 Result: " + res);
		
		if(res>0) {
			result = "succeed";
		}else {
			result = "failed";
		}
		return result;
	}
	
	@RequestMapping("buy-vote.do")
	@ResponseBody
	public String payVote(@RequestParam int amount, @RequestParam  int price, Authentication auth) {
		
		MemberDto dto = (MemberDto)auth.getPrincipal();
		String userId = dto.getUsername();
		String result;
		
		
		int res = biz.purchaseVote(amount, userId);
		System.out.println(userId + "님이  " + amount + "구매합니다~");
		
		System.err.println("Controller Result => " + res);
		if(res>0) {
			result = "succeed";
		}else {
			result = "failed";
		}
		return result;
	}
	
	
	public String chatDo() {
		return "";
	}
	
	// 번역기페이지로 이동
	@RequestMapping("/translate-form.do")
	public String translate() {
		return "utils/translate";
	}
	
	// 번역실행
	@RequestMapping(value = "/translate.do")
	public String translate(Model model, String inputQuery) {
		System.out.println("inputQuery:"+inputQuery);
		Translate translate = new Translate();
		translate.Translate(inputQuery);
		String outputQuery = translate.Translate(inputQuery);
		System.out.println("outputQuery:"+outputQuery);
		model.addAttribute("outputQuery",outputQuery);
		
		return "utils/translate";
	}
	
	@RequestMapping("/error.do")
	public String error(Model model,@RequestParam String kind) {
		model.addAttribute("kind", kind);
		return "error";
	}
}
