package com.imhj.movieCrawling.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.naming.directory.NoSuchAttributeException;

import org.apache.tomcat.util.threads.TaskThread;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.imhj.movieCrawling.MovieApplication;
import com.imhj.movieCrawling.dao.MovieDao;
import com.imhj.movieCrawling.dto.MovieDto;
import com.imhj.movieCrawling.dto.MovieEvalutDto;
import com.imhj.movieCrawling.dto.MovieInfoDto;
import com.imhj.movieCrawling.dto.MovieSectionDto;

@Component
public class NaverMovie implements Callable<Map<String, String>> {
//Callable Runnable과 유사하지만 Runable은 return값이 void고 Callable은 작업할 내용과 리턴값을 지정 할 수 있다.
	@Autowired
	MovieDao movieDao;
	
	Map<String,String> localPage;
	
	public NaverMovie() {
		// TODO Auto-generated constructor stub
	}
	
	public NaverMovie(Map<String,String> parsingPage) {
		// TODO Auto-generated constructor stub
		System.out.println(parsingPage);
		this.localPage = parsingPage;
	}
	
	@Override
	synchronized public Map<String, String> call() throws Exception {
		String url = "";
		String key = "";
		
		for (Map.Entry<String, String> page: localPage.entrySet()) {
		  //  System.out.println("key: " + page.getKey() + " value: " + page.getValue());
		url =  String.valueOf(page.getValue());
		key = String.valueOf(page.getKey());
		
		}
		List<MovieDto> movieDtoList = NaverMovie.parsingMovie(url, key);
		for (MovieDto movieDto : movieDtoList) {
			System.out.println(url);
			System.out.println(key);
			movieDao.insertMovie(movieDto);
			System.out.println(movieDto);
		}
		
		System.out.println("final ==> " + localPage.get("COMMING"));
		List<MovieInfoDto> movieInfoDtoList = NaverMovie.parsingMovieInfo(movieDtoList);
		for (MovieInfoDto movieInfoDto : movieInfoDtoList)
			movieDao.insertMovieInfo(movieInfoDto);
		
		List<MovieSectionDto> movieSectionDtoList = NaverMovie.parsingMovieSection(movieInfoDtoList);
		for (MovieSectionDto movieSectionDto : movieSectionDtoList)
			movieDao.insertSection(movieSectionDto);
		
		List<MovieEvalutDto> movieEvalutDtoList = NaverMovie.parsingMoiveEvalut(movieInfoDtoList);
		for (MovieEvalutDto movieEvalutDto : movieEvalutDtoList)
			movieDao.insertMovieEvalut(movieEvalutDto);


		return localPage;
	}
	
	public static List<MovieDto> parsingMovie(String url, String key) throws Exception {
		List<MovieDto> movieDtoList = new ArrayList<>();
//		Map<String, String> parsingPage = new HashMap<>(1) ;
//		String url="";
//		String key="";
//			NaverMovie nm = new NaverMovie(parsingPage);
//			System.out.println(""+nm.localPage);
//			nm.localPage.get("BOXOFFICE");
//			nm.localPage.get("https://movie.naver.com/movieChartJson.naver?type=BOXOFFICE");
//			System.out.println(nm);
			

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
					MovieDto dto = new MovieDto(movieUrl, movieCode, key);

					movieDtoList.add(dto);

//파이널이 enum

				}
			} catch (Exception e) {
				System.out.println(e.getMessage()); // 에러메세지를 세세하게 분
			}
		System.out.println(">>>>>>>>>>>1<<<<<<<<<<<<" + Thread.currentThread().getName());
		System.out.println(movieDtoList);
		
		return movieDtoList;
	}

	public static List<MovieInfoDto> parsingMovieInfo(List<MovieDto> movieDtoList) {
		List<MovieInfoDto> movieInfoDtoList = new ArrayList<>();

		for (MovieDto movieDto : movieDtoList) {

			try {
				Document doc = Jsoup.connect(movieDto.getUrl()).header("Host", "movie.naver.com").get();
				// 어쩔땐 execute() 되고 왜 이런경우엔 get으로 해야 에러가 안날까?

				Elements movieDiv = doc.select("div.mv_info_area");

				String title = movieDiv.select("div.mv_info > h3 > a:nth-child(1)").text();
				String avgPoint = movieDiv.select("#actualPointPersentBasic > div > span > span").text();
				int movieCode = movieDto.getMovieCode();

				MovieInfoDto movieInfoDto = new MovieInfoDto(movieCode, title, avgPoint, movieDto.getMovieSection());// 디자인패턴에
																														// 대해서
																														// 공부
																														// 할
																														// 것
				movieInfoDtoList.add(movieInfoDto);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		System.out.println(">>>>>>>>>>>2<<<<<<<<<<<<" + Thread.currentThread().getName());
		return movieInfoDtoList;
	}

	public static List<MovieSectionDto> parsingMovieSection(List<MovieInfoDto> movieInfoDtoList) {
		List<MovieSectionDto> movieSectionDtoList = new ArrayList<>();

		for (MovieInfoDto movieInfoDto : movieInfoDtoList) {
			System.out.println(movieInfoDto.getMovieCode());

			int id = movieInfoDto.getId();
			MovieSectionDto movieSectionDto = new MovieSectionDto(id, movieInfoDto.getMovieSection());
			System.out.println(movieSectionDto);
			System.out.println(id);

			movieSectionDtoList.add(movieSectionDto);
		}
		System.out.println(">>>>>>>>>>>3<<<<<<<<<<<<" + Thread.currentThread().getName());
		return movieSectionDtoList;
	}

	public static List<MovieEvalutDto> parsingMoiveEvalut(List<MovieInfoDto> movieInfoDtoList) {
		List<MovieEvalutDto> movieEvalutDtoList = new ArrayList<>();

		for (MovieInfoDto movieInfoDto : movieInfoDtoList) {
			for (int j = 1; j < 3; j++) {
				try {
					String evalUrl = String.format(
							"https://movie.naver.com/movie/bi/mi/pointWriteFormList.naver?code=%s"
									+ "&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page=%d",
							movieInfoDto.getMovieCode(), j);
					Document doc = Jsoup.connect(evalUrl).header("Host", "movie.naver.com").get();

					Elements evalutDiv = doc.select("div.score_result>ul>li");
					System.out.println("<<<<<<<<<<" + movieInfoDto.getTitle() + j + "페이지");
					for (Element el : evalutDiv) {
						String avgPointEval = el.select("div.star_score > em").text();
						System.out.println("점수 : " + avgPointEval);
						String collectDay = el.select("div.score_reple > dl > dt > em:nth-child(2)").text();
						System.out.println("댓글 쓴 날 : " + collectDay);
						String content = el.select("div.score_reple > p").text();
						System.out.println("댓글 내용 : " + content);
						System.out.println("-------------------------------------------");

						MovieEvalutDto movieEvalutDto = new MovieEvalutDto(movieInfoDto.getId(), avgPointEval, content,
								collectDay);
						movieEvalutDtoList.add(movieEvalutDto);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return movieEvalutDtoList;
	}
	
	
	

}
