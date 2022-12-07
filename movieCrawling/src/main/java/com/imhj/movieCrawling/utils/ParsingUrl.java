package com.imhj.movieCrawling.utils;

import java.util.HashMap;
import java.util.Map;

public class ParsingUrl {
	
	public static Map<String, String> getParsingUrl(){
		Map<String, String> url = new HashMap<>();
		
		url.put("BOXOFFICE", "https://movie.naver.com/movieChartJson.naver?type=BOXOFFICE");
		url.put("CURRENT", "https://movie.naver.com/movieChartJson.naver?type=CURRENT");
		url.put("COMMING", "https://movie.naver.com/movieChartJson.naver?type=COMMING");
		
		return url;
	}

}
