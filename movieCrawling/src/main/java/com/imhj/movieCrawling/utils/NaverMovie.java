package com.imhj.movieCrawling.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.directory.NoSuchAttributeException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.imhj.movieCrawling.dto.MovieDto;
import com.imhj.movieCrawling.dto.MovieInfoDto;

public class NaverMovie {
	
	public NaverMovie() {

	}
	
	public static List<MovieDto> parsingMovie() {
		List<MovieDto> movieDtoList = new ArrayList<>(); 
		Map<String, String> urls = ParsingUrl.getParsingUrl();
		for (String key : urls.keySet()) {
			String url = urls.get(key);
			System.out.println(url);

			try {

				String doc = null;

				doc = Jsoup.connect(url).header("Host", "movie.naver.com").header("Referer", "https://movie.naver.com/")
						.ignoreContentType(true).execute().body();

				JSONParser parser = new JSONParser();
				Object obj = parser.parse(doc);
				JSONObject jsonObj = (JSONObject) obj;

				JSONObject movieList = (JSONObject) jsonObj.get("movieChartList");
				JSONArray movieArray = (JSONArray) movieList.get(key);

				for (Object movie : movieArray) {
					JSONObject movieObj = (JSONObject) movie;

					String movieStr = String.valueOf(movieObj.get("movieCode"));
					int movieCode = Integer.parseInt(movieStr);
					String movieUrl = "https://movie.naver.com/movie/bi/mi/basic.naver?code=" + movieStr;
					
					MovieDto dto = new MovieDto(movieUrl, movieCode);
					movieDtoList.add(dto);
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		return movieDtoList;
	}
	
	public static List<MovieInfoDto> parsingMovieInfo(){
		List<MovieInfoDto> movieInfoDtoList = new ArrayList<>();
		
		try {
			Document doc = Jsoup.connect(movieUrl).header("Host", "movie.naver.com").get();
			// 어쩔땐 execute() 되고 왜 이런경우엔 get으로 해야 에러가 안날까?

			Elements movieDiv = doc.select("div.mv_info_area");

			String title = movieDiv.select("div.mv_info > h3 > a:nth-child(1)").text();
			String avgPoint = movieDiv.select("#actualPointPersentBasic > div > span > span").text();

			System.out.println(avgPoint);
			System.out.println(title);
			System.out.println(movieCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return movieInfoDtoList;
	}




}
