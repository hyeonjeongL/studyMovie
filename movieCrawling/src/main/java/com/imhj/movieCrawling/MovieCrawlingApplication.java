package com.imhj.movieCrawling;

import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.imhj.movieCrawling.service.movieCrawler;

@SpringBootApplication
public class MovieCrawlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCrawlingApplication.class, args);

	}

}
