package com.my.test.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScrap{
	
	public static void main(String[] args) {
		WebScrap ws = new WebScrap();
		ws.getMelonChart();
		
	}
	
	public List<Music> getMelonChart(){
		
		List<Music> chart = new ArrayList<Music>();
		//String url = "https://www.melon.com/chart/index.htm";
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
				song.setRank_updown(ele.select(".bullet_icons").text());
				
				String change = ele.select("span.rank_wrap span").text();
				System.out.println(ele.select(".rank").text() + ": " +change);
						
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
			
			System.err.println("ListSize : " + rows.size());
			for(int i=1 ; i<rows.size() ; ++i){
				Element ele = rows.get(i);
				Music song = new Music();
				
				if(ele.select(".ranking").text().length() == 0) continue;
				
				song.setRank(ele.select(".ranking strong").text());
				song.setThumb(ele.select("td a img").attr("src"));
				song.setTitle(ele.select("p.title a").text());
				song.setSinger(ele.select("p.artist a").text());
				song.setAlbum(ele.select("td.left a.album").text());
				song.setRank_change(ele.select("p.change em").text());
				song.setRank_updown(ele.select("p.change span").text());
				
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
		//https://www.genie.co.kr/chart/top200?pg=1
		//https://www.genie.co.kr/chart/top200?pg=2
		
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
					song.setRank_updown(ele.select("td.number span.hide").text());
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

}
	
	
