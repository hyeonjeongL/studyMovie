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
public class NaverMovie implements Callable<Map<String,String>>{
	private static final String MSG_TEMPLATE = "출력 중입니다.";
	

	@Override
	public Map<String,String> call() throws Exception {
		//Thread.sleep(1000);
		String url="";
		Map<String, String> urls = ParsingUrl.getParsingUrl();
		for (String key : urls.keySet()) {
			url = urls.get(key);
		}
		System.out.println("두통오져");
		System.out.println(Thread.currentThread().getName());

		
		//스레드 동시 실행 수는 3
		
		ExecutorService	executorService = Executors.newFixedThreadPool(3);
		
		List<Future<String>> list = new ArrayList<Future<String>>();
		
		
		executorService.shutdown();
		
		try {
			if(!executorService.awaitTermination(5, TimeUnit.MINUTES)) {
				//타임아웃 후에도 아직 실행이 끝나지 않았다.
				executorService.shutdown();
			}
		}catch (Exception e) {
			// 종료 대기 시에 뭔가 오류가 발생했다.
			System.out.println(e.getMessage());
			executorService.shutdown();
		}
			return urls;
	}
	
		
//		List<Object> list = new ArrayList<>();
//		list.add(list);
//		System.out.println(String.format(MSG_TEMPLATE));
//		System.out.println(Thread.currentThread().getName());
		
	
	public static void main(String[] args) {
		
	}

//	public static void main(String[] args) {
//
//		Thread thread = new Thread();
//		thread.start();
//		for (int i = 0; i < 10; i++) {
//			//System.out.println(i + "번째 " + Thread.currentThread().getName() + "시작");
//System.out.println("쓰레드 시작");
//		}
//	}
//
//		static class Task implements Runnable {
//			
//		@Override
//		public void run() {
//			System.out.println(">>>>>>>>" + Thread.currentThread().getName());
//			for (int i = 0; i < 5; i++) {
//				System.out.println(Thread.currentThread().getName() + ":" + i);
//			}
//			}
//	}

	public static List<MovieDto> parsingMovie() throws Exception{
		List<MovieDto> movieDtoList = new ArrayList<>();

		Map<String, String> urls = ParsingUrl.getParsingUrl();
		for (String key : urls.keySet()) {
			String url = urls.get(key);

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
				e.printStackTrace(); // 에러메세지를 세세하게 분
			}
		}

		System.out.println(">>>>>>>>>>><<<<<<<<<<<<"+Thread.currentThread().getName());
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
		System.out.println(">>>>>>>>>>><<<<<<<<<<<<"+Thread.currentThread().getName());
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
		System.out.println(">>>>>>>>>>><<<<<<<<<<<<"+Thread.currentThread().getName());
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
