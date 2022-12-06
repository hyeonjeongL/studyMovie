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

@Component
public class MovieInfoApplication implements ApplicationRunner {

	@Autowired
	MovieDao movieDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
//		try {
//		System.out.println("---------------------------");
//		Document doc2 = Jsoup.connect(movieUrl).header("Host", "movie.naver.com").get();
//		// 어쩔땐 execute() 되고 왜 이런경우엔 get으로 해야 에러가 안날까?
//
//		Elements movieDiv = doc2.select("div.mv_info");
//		// System.out.println(movieDiv);
//		for (Element el : movieDiv) {
//			String title = el.select("div.mv_info >h3 >a").text();
//			String code = el.select("div.mv_info>a").attr("href");
//			String avgPoint = el.select("span.st_on").text();
//
//			System.out.println(avgPoint);
//			System.out.println(title);
//			System.out.println(movieCode);
//
//			MovieInfoDto movieInfoDto = new MovieInfoDto(movieCode, title, avgPoint);
//			// movieDao.insertMovieInfo(movieInfoDto);
//		}
//		System.out.println("---------------------------");
//		String EvalUrl = "https://movie.naver.com/movie/bi/mi/pointWriteFormList.naver?code=" + movieCode
//				+ "&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false";
//		Document doc3 = Jsoup.connect(EvalUrl).header("Host", "movie.naver.com").get();
//
//		Elements evalutDiv = doc3.select("div.score_result");
//		for (Element el : evalutDiv) {
//
//			for (i = 0; i < 10; i++) {
//				String avgPointEval = el
//						.select("div.score_result > ul > li:nth-child(" + i + ")> div.star_score > em")
//						.text();
//				System.out.println(avgPointEval);
//				String collectDay = el.select("div.score_result > ul > li:nth-child(" + i
//						+ ") > div.score_reple > dl > dt > em:nth-child(2)").text();
//				System.out.println(collectDay);
//				String content = el
//						.select("div.score_result >ul> li>div.score_reple > p> span#_filtered_ment_" + i)
//						.text();
//				System.out.println(content);
//
//				MovieEvalutDto movieEvalutDto = new MovieEvalutDto(avgPointEval, content, collectDay);
//			movieDao.insertMovieEvalut(movieEvalutDto);
//			}

//		}
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}

	}
}
