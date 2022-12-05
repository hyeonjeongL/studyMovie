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
		
		String url = "https://movie.naver.com/movieChartJson.naver?type=BOXOFFICE";
		
		try {
			
			String doc = Jsoup.connect(url)
					.header("Host", "movie.naver.com")
                    .header("Referer", "https://movie.naver.com/")
					.ignoreContentType(true)
					.execute().body();
			
			//System.out.println(doc);
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(doc);
			JSONObject jsonObj = (JSONObject) obj;
			
			JSONObject movieList = (JSONObject) jsonObj.get("movieChartList");
			System.out.println(movieList);
			System.out.println("---------------------------------------------");
			JSONArray movieArray = (JSONArray) movieList.get("BOXOFFICE");
			System.out.println(movieArray);
			System.out.println("---------------------------------------------");

			for(int i=0; i<movieArray.size(); i++) {
				System.out.println("********************** "+ (i+1) + "위 영화 **********************");
				JSONObject movieObj = (JSONObject) movieArray.get(i);
				
				System.out.println(movieObj.get("posterImageUrl"));
				System.out.println(movieObj.get("movieCode"));
		
				
			}
			
			//String movieCode = (String) jsonObj.get("movieCode");
			String movieCode = String.valueOf(jsonObj.get("movieCode"));
			//String movieTitle = (String) jsonObj.get("movieTitle");
			String movieTitle = String.valueOf(jsonObj.get("movieTitle"));
			System.out.println(movieCode);
			System.out.println(movieTitle);
			System.out.println("parser ==> " + parser.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Elements element = doc.select("");
	
		
	}

}
