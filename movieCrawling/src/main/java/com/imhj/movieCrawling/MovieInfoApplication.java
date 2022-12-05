package com.imhj.movieCrawling;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.imhj.movieCrawling.dao.MovieDao;
import com.imhj.movieCrawling.dto.MovieDto;

@Component
public class MovieInfoApplication implements ApplicationRunner {

	@Autowired
	MovieDao movieDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Map<String, Object> parsingMovie = new HashMap<>();

		String url = "https://movie.naver.com/movie/bi/mi/basic.naver?code=195973";

		List<MovieDto> urlList = new ArrayList<>();
		
		for (MovieDto urlCode : urlList) {
			try {
				
				Document doc = Jsoup.connect(url).get();
				Elements movieDiv = doc.select("div.mv_info");
				for (Element el : movieDiv) {
					String title = el.select("div.mv_info >h3 >a").text();
					String movieCode = el.select("div.mv_info>a").attr("href");
					String avgPoint =el.select("span.st.on").text();
					
					System.out.println(title);
				}

				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
System.out.println("sooooooooooo diff");
	}
}
