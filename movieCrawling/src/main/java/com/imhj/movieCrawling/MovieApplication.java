package com.imhj.movieCrawling;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.imhj.movieCrawling.dao.MovieDao;

@Component
public class MovieApplication implements ApplicationRunner{
	
	@Autowired
	MovieDao movieDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		String url = "https://movie.naver.com/movieChartJson.naver?type=BOXOFFICE";

		try {
			
			String doc = null;
			
			doc = Jsoup.connect(url)
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
			
			System.out.println(movieDao.selectTest());
			
			
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
