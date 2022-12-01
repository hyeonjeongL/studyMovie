package com.imhj.movieCrawling.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class movieCrawling {
	
	public static void main(String[] args) throws Exception, InterruptedException {
		
		final String url = "https://movie.naver.com/movie/bi/mi/basic.naver?code=222301";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
			System.out.println(doc.data());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Elements element = doc.select("");
	
		
	}

}
