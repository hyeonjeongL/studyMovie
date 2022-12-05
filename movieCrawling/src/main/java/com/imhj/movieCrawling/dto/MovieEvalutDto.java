package com.imhj.movieCrawling.dto;

import java.util.Date;

public class MovieEvalutDto {
	
	int id;
	int movieInfoId;
	String avePoint;
	String content;
	String collectDay;
	Date regDt;
	
	public MovieEvalutDto() {
		// TODO Auto-generated constructor stub
	}

	public MovieEvalutDto(int id, int movieInfoId, String avePoint, String content, String collectDay, Date regDt) {
		super();
		this.id = id;
		this.movieInfoId = movieInfoId;
		this.avePoint = avePoint;
		this.content = content;
		this.collectDay = collectDay;
		this.regDt = regDt;
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

	public String getAvePoint() {
		return avePoint;
	}

	public void setAvePoint(String avePoint) {
		this.avePoint = avePoint;
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
		return "movieEvalutDto [id=" + id + ", movieInfoId=" + movieInfoId + ", avePoint=" + avePoint + ", content="
				+ content + ", collectDay=" + collectDay + ", regDt=" + regDt + "]";
	}

	
	

}
