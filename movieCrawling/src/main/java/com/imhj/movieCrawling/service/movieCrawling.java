package com.imhj.movieCrawling.service;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class movieCrawling {
	
	public static void main(String[] args) throws Exception, InterruptedException {
		
		final String url = "https://movie.naver.com/movieChartJson.naver?type=BOXOFFICE";
		
		try {
			
			String doc = Jsoup.connect(url)
					.header("Host", "movie.naver.com")
                    .header("Referer", "https://movie.naver.com/")
					.ignoreContentType(true)
					.execute().body();
			
			System.out.println(doc);
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(doc);
			JSONObject jsonObj = (JSONObject) obj;
			
			JSONArray movieArray = (JSONArray) jsonObj.get("BOXOFFICE");
			System.out.println(movieArray);
			 
			
			String movieCode = (String) jsonObj.get("movieCode");
			String movieTitle = (String) jsonObj.get("movieTitle");
			System.out.println(movieCode);
			System.out.println(movieTitle);
			System.out.println("parser ==> " + parser.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Elements element = doc.select("");
	
		
	}

}
