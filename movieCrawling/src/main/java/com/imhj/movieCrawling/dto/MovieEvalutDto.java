package com.imhj.movieCrawling.dto;

import java.util.Date;

public class MovieEvalutDto {
	
	int id;
	int movieInfoId;
	String avgPoint;
	String content;
	String collectDay;
	Date regDt;
	
	public MovieEvalutDto() {
		// TODO Auto-generated constructor stub
	}

	public MovieEvalutDto(int movieInfoId, String avgPoint, String content, String collectDay) {
		super();
		this.movieInfoId = movieInfoId;
		this.avgPoint = avgPoint;
		this.content = content;
		this.collectDay = collectDay;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieInfoId() {
		return movieInfoId;
	}

	public void setMovieInfoId(int movieInfoId) {
		this.movieInfoId = movieInfoId;
	}

	public String getAvgPoint() {
		return avgPoint;
	}

	public void setAvgPoint(String avePoint) {
		this.avgPoint = avePoint;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCollectDay() {
		return collectDay;
	}

	public void setCollectDay(String collectDay) {
		this.collectDay = collectDay;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "movieEvalutDto [id=" + id + ", movieInfoId=" + movieInfoId + ", avgPoint=" + avgPoint + ", content="
				+ content + ", collectDay=" + collectDay + ", regDt=" + regDt + "]";
	}

	
	

}
