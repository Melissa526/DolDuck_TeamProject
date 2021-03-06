package com.my.dolduck.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.my.dolduck.model.dto.VoteCrawlingDto;

public class WebScrap{

	public List<Music> getMelonChart(){
		
		List<Music> chart = new ArrayList<Music>();
		try {
			
			Document doc = Jsoup.connect("https://www.melon.com/chart/index.htm").get();
			Elements rows = doc.select("tr");
		
			for(int i=1 ; i<rows.size() ; ++i) {
				Element ele = rows.get(i);
				Music song = new Music();
				
				if(ele.select(".rank").text().length() == 0) {
					continue; 
				}	
				//System.out.println("["+ele.select(".rank").text()+"위] "+ ele.select(".rank02 a").text() + "-" + ele.select(".rank01 a").text());
				song.setRank(ele.select(".rank").text());
				song.setThumb(ele.select("img").attr("src"));
				song.setTitle(ele.select(".rank01 a").text());
				song.setSinger(ele.select(".rank02 .checkEllipsis a").text());
				song.setAlbum(ele.select(".rank03 a").text());
				
				chart.add(song);
			}
			
		} catch (IOException e) {
			System.err.println("JSoup 크롤링 에러 (Melon)");
		}
		return chart;
	}
	
	public List<Music> getBugsChart(){
		
		List<Music> chart = new ArrayList<Music>();
		
		try {
			Document doc = Jsoup.connect("https://music.bugs.co.kr/chart").get();
			Elements rows = doc.select("tr");
			
			for(int i=1 ; i<rows.size() ; ++i){
				Element ele = rows.get(i);
				Music song = new Music();
				
				if(ele.select(".ranking").text().length() == 0) continue;
				
				song.setRank(ele.select(".ranking strong").text());
				song.setThumb(ele.select("td a img").attr("src"));
				song.setTitle(ele.select("p.title a").text());
				song.setSinger(ele.select("p.artist a").text());
				song.setAlbum(ele.select("td.left a.album").text());
				
				chart.add(song);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("JSoup 크롤링 에러 (Bugs)");
		}
		
		return chart;
	}
	
	public List<Music> getGenieChart(){
		
		List<Music> chart = new ArrayList<Music>();
		
		try {
			for(int a=1 ; a<3 ; a++) {
				Document doc = Jsoup.connect("https://www.genie.co.kr/chart/top200?pg="+a).get();
				Elements rows = doc.select("tr.list");
				
				for(int i=0 ; i<rows.size() ; ++i){
					Element ele = rows.get(i);
					Music song = new Music();
					
					if(ele.select(".list").text().length() == 0) continue;
					
					String rank = ele.select("td.number").text();
					song.setRank(rank.split(" ")[0]);
					song.setThumb("https:"+ele.select("td a.cover img").attr("src"));
					song.setTitle(ele.select("td.info a.title").text());
					song.setSinger(ele.select("td.info a.artist").text());
					song.setAlbum(ele.select("td.info a.albumtitle").text());
					
					chart.add(song);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("JSoup 크롤링 에러 (Genie)");
		}
		
		return chart;
	}
	
	public List<VoteCrawlingDto> getVoteChart(String item) {
		List<VoteCrawlingDto> voteChart = new ArrayList<VoteCrawlingDto>();
		int page = 0;
		System.out.println("webitem:"+item);
		// item -> page 변환 case문
		switch(item) {
		case "star-idol": page = 3; break;
		case "girl-idol": page = 1; break;
		case "girl-group": page = 0; break;
		case "boy-group": page = 4; break;
		case "boy-idol": page = 5; break;
		case "new-idol": page = 2; break;
		}
		System.out.println("page:"+page);
		try {
			Document doc = Jsoup.connect("http://idolpick.donga.com/poll.php?p="+page).get();
			Elements rows = doc.select("li");

			for (int i = 1; i < rows.size(); ++i) {
				Element ele = rows.get(i);
				VoteCrawlingDto voteCrawlingDto = new VoteCrawlingDto();

				if (ele.select("img").attr("src") != "" && ele.select("img").attr("alt") != "") {
					voteCrawlingDto.setName(ele.select("img").attr("alt"));
					voteCrawlingDto.setImg(ele.select("img").attr("src"));

					voteChart.add(voteCrawlingDto);
				}
			}
		} catch (IOException e) {
			System.err.println("JSoup 크롤링 에러 (VoteCrawling.java)");
		}
		return voteChart;
	}

}
	
	
