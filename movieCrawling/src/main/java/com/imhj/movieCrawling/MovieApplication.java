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
import com.imhj.movieCrawling.dto.MovieEvalutDto;
import com.imhj.movieCrawling.dto.MovieInfoDto;
import com.imhj.movieCrawling.dto.MovieSectionDto;
import com.imhj.movieCrawling.utils.NaverMovie;
import com.imhj.movieCrawling.utils.ParsingUrl;

@Component
public class MovieApplication implements ApplicationRunner {

	@Autowired
	MovieDao movieDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		List<MovieDto> movieDtoList = NaverMovie.parsingMovie();
//		for (MovieDto movieDto : movieDtoList)
//			movieDao.insertMovie(movieDto);

		List<MovieInfoDto> movieInfoDtoList = NaverMovie.parsingMovieInfo(movieDtoList);
//		for (MovieInfoDto movieInfoDto : movieInfoDtoList)
//			movieDao.insertMovieInfo(movieInfoDto);

		List<MovieSectionDto> movieSectionDtoList = NaverMovie.parsingMovieSection(movieInfoDtoList);
//		for (MovieSectionDto movieSectionDto : movieSectionDtoList)
//			movieDao.insertSection(movieSectionDto);

		List<MovieEvalutDto> movieEvalutDtoList = NaverMovie.parsingMoiveEvalut(movieInfoDtoList);
//		for (MovieEvalutDto movieEvalutDto : movieEvalutDtoList)
//			movieDao.insertMovieEvalut(movieEvalutDto);
		
		
	 CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		 return "hello";
	 });
	}

}

//-------------------------------------------------------------------------------------------------------------------
//@Component
//public class MovieApplication implements ApplicationRunner {
//	@Autowired
//	MovieDao movieDao;
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		// Map<String, Object> parsingMovie = new HashMap<>();
//		String url = null;
//		try {
//			url = "https://movie.naver.com/movieChartJson.naver?type=BOXOFFICE";
//			String doc = null;
//			doc = Jsoup.connect(url).header("Host", "movie.naver.com").header("Referer", "https://movie.naver.com/")
//					.ignoreContentType(true).execute().body();
//			JSONParser parser = new JSONParser();
//			Object obj = parser.parse(doc);
//			JSONObject jsonObj = (JSONObject) obj;
//			JSONObject movieList = (JSONObject) jsonObj.get("movieChartList");
//			JSONArray movieArray = (JSONArray) movieList.get("BOXOFFICE");
//
//			for (int i = 0; i < movieArray.size(); i++) {
//				System.out.println("********************** " + (i + 1) + "위 영화 **********************");
//				JSONObject movieObj = (JSONObject) movieArray.get(i);
//			for (Object movie : movieArray) {
//				System.out.println("*******************************************");
//				JSONObject movieObj = (JSONObject) movie;
//
//				String movieStr = String.valueOf(movieObj.get("movieCode"));
//				System.out.println(movieObj.get("movieCode"));
//				int movieCode = Integer.parseInt(movieStr);
//				String movieUrl = "https://movie.naver.com/movie/bi/mi/basic.naver?code=" + movieStr;
//
//				MovieDto dto = new MovieDto(movieUrl, movieCode);
//				//movieDao.insertMovie(dto);
//
//				//Jsoup.connect(movieUrl).header("Host", "movie.naver.com").execute();
//				 movieDao.insertMovie(dto);
//
//				try {
//					System.out.println("---------------------------");
//					Document doc2 = Jsoup.connect(movieUrl).header("Host", "movie.naver.com").get();
//					//어쩔땐 execute() 되고 왜 이런경우엔 get으로 해야 에러가 안날까?
//
//					Elements movieDiv = doc2.select("div.mv_info");
//					//System.out.println(movieDiv);
//					for (Element el : movieDiv) {
//						String title = el.select("div.mv_info >h3 >a").text();
//						String code = el.select("div.mv_info>a").attr("href");
//						String avgPoint = el.select("span.st_on").text();
//
//						System.out.println(avgPoint);
//						System.out.println(title);
//						System.out.println(movieCode);
//					// 어쩔땐 execute() 되고 왜 이런경우엔 get으로 해야 에러가 안날까?
//
//					Elements movieDiv = doc2.select("div.mv_info_area");
//
//					String title = movieDiv.select("div.mv_info > h3 > a:nth-child(1)").text();
//					String avgPoint = movieDiv.select("#actualPointPersentBasic > div > span > span").text();
//
//					System.out.println(avgPoint);
//					System.out.println(title);
//					System.out.println(movieCode);
//
//					int id = movieDao.selectId(movieCode);
//
//					MovieInfoDto movieInfoDto = new MovieInfoDto(id, movieCode, title, avgPoint);
//						 movieDao.insertMovieInfo(movieInfoDto);
//
//					System.out.println("---------------------------");
//					for (int j = 1; j < 3; j++) {
//
//						String EvalUrl = "https://movie.naver.com/movie/bi/mi/pointWriteFormList.naver?code="
//								+ movieCode
//								+ "&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page="
//								+ j;
//						Document doc3 = Jsoup.connect(EvalUrl).header("Host", "movie.naver.com").get();
//
//						Elements evalutDiv = doc3.select("div.score_result>ul>li");
//
//						//이 방법으론 할 수 없음
////						for (int k = 1; k < 10; k++) {
////							String avgPointEval = evalutDiv
////									.select("div.score_result > ul > li:nth-child(" + k + ")> div.star_score > em")
////									.text();
////							System.out.println("점수 : "+avgPointEval);
////							String collectDay = evalutDiv.select("div.score_result > ul > li:nth-child(" + k
////									+ ") > div.score_reple > dl > dt > em:nth-child(2)").text();
////							System.out.println("댓글 쓴 날 : "+collectDay);
////							String content = evalutDiv
////									.select("div.score_result >ul> li>div.score_reple > p> span#_filtered_ment_" + k)
////									.text();
////							System.out.println("댓글 내용 : "+content);
////
////							MovieEvalutDto movieEvalutDto = new MovieEvalutDto(movieInfoDto.getId(), avgPointEval,
////									content, collectDay);
////							//movieDao.insertMovieEvalut(movieEvalutDto);
////						}
//
//						MovieInfoDto movieInfoDto = new MovieInfoDto(movieCode, title, avgPoint);
//						//movieDao.insertMovieInfo(movieInfoDto);
//						//이 경우, 어떤 방법이 있는지 알아보자
//					for(Element el : evalutDiv) {
//						String avgPointEval = el
//								.select("div.star_score > em")
//								.text();
//						System.out.println("점수 : "+avgPointEval);
//						String collectDay = el.select("div.score_reple > dl > dt > em:nth-child(2)").text();
//						System.out.println("댓글 쓴 날 : "+collectDay);
//						String content = el
//								.select("div.score_reple > p")
//								.text();
//						System.out.println("댓글 내용 : "+content);
//						System.out.println("---------------------------------");
//						System.out.println(movieInfoDto);
//
//						MovieEvalutDto movieEvalutDto = new MovieEvalutDto(movieInfoDto.getId(), avgPointEval,
//								content, collectDay);
//						movieDao.insertMovieEvalut(movieEvalutDto);
//
//					}
//					}
//
//				} catch (Exception e) {
//					e.printStackTrace(); 
//					e.printStackTrace();
//				}
//
//
//
//				// String movieCode = (String) jsonObj.get("movieCode");
//				// String movieCode = String.valueOf(jsonObj.get("movieCode"));
//				// String movieTitle = (String) jsonObj.get("movieTitle");
//				// String movieTitle = String.valueOf(jsonObj.get("movieTitle"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} 
//		}