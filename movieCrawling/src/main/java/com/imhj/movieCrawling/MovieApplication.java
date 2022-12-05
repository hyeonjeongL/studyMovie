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
import com.imhj.movieCrawling.dto.MovieInfoDto;

@Component
public class MovieApplication implements ApplicationRunner {

	@Autowired
	MovieDao movieDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Map<String, Object> parsingMovie = new HashMap<>();

		String url = null;

		try {
			url = "https://movie.naver.com/movieChartJson.naver?type=BOXOFFICE";
			String doc = null;

			doc = Jsoup.connect(url).header("Host", "movie.naver.com").header("Referer", "https://movie.naver.com/")
					.ignoreContentType(true).execute().body();

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(doc);
			JSONObject jsonObj = (JSONObject) obj;

			JSONObject movieList = (JSONObject) jsonObj.get("movieChartList");
			JSONArray movieArray = (JSONArray) movieList.get("BOXOFFICE");

			for (int i = 0; i < movieArray.size(); i++) {
				System.out.println("********************** " + (i + 1) + "위 영화 **********************");
				JSONObject movieObj = (JSONObject) movieArray.get(i);

				String movieStr = String.valueOf(movieObj.get("movieCode"));
				System.out.println(movieObj.get("movieCode"));
				int movieCode = Integer.parseInt(movieStr);
				String movieUrl = "https://movie.naver.com/movie/bi/mi/basic.naver?code=" + movieStr;

				MovieDto dto = new MovieDto(movieUrl, movieCode);
				//movieDao.insertMovie(dto);

				//Jsoup.connect(movieUrl).header("Host", "movie.naver.com").execute();

				try {
					System.out.println("---------------------------");
					Document doc2 = Jsoup.connect(movieUrl).header("Host", "movie.naver.com").get();
					//어쩔땐 execute() 되고 왜 이런경우엔 get으로 해야 에러가 안날까?

					Elements movieDiv = doc2.select("div.mv_info");
					//System.out.println(movieDiv);
					for (Element el : movieDiv) {
						String title = el.select("div.mv_info >h3 >a").text();
						String code = el.select("div.mv_info>a").attr("href");
						String avgPoint = el.select("span.st_on").text();

						System.out.println(avgPoint);
						System.out.println(title);
						System.out.println(movieCode);
						
						MovieInfoDto movieInfoDto = new MovieInfoDto(movieCode, title, avgPoint);
						//movieDao.insertMovieInfo(movieInfoDto);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				

				// String movieCode = (String) jsonObj.get("movieCode");
				// String movieCode = String.valueOf(jsonObj.get("movieCode"));
				// String movieTitle = (String) jsonObj.get("movieTitle");
				// String movieTitle = String.valueOf(jsonObj.get("movieTitle"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
