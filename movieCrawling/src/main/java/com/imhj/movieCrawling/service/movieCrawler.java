package com.imhj.movieCrawling.service;

import java.util.Iterator;

import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class movieCrawler {
	
	public void crawling(String url) {
		
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// html
		//Elements element = doc.select("ul#flick0");
		JSONParser parser = new JSONParser(doc.toString());
		//Object obj = parser.parse( jsonStr );
		//JSONObject jsonObj = (JSONObject) obj;
		System.out.println("parser ==> " + parser);
		System.out.println("------------------------------------");
		
		//Iterator<Element> ie1 = element.select("p.mv_title").iterator();
		
		//while(ie1.hasNext()) {
		//	System.out.println(ie1.next().text());
		//}
		//System.out.println("why?");
	}

}
