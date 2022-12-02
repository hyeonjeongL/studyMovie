package com.imhj.movieCrawling;

import com.imhj.movieCrawling.service.movieCrawler;

public class application {

	public static void main(String[] args) {
		
		movieCrawler naverMovie = new movieCrawler();
		
		naverMovie.crawling("https://movie.naver.com");

	}

}
