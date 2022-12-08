package com.imhj.movieCrawling.dto;

import java.util.Date;

public class MovieInfoDto {
	
	int id;
	int movieCode;
	String title;
	String avgPoint;
	Date regDt;
	Date uptDt;
	String movieSection;
	
	public MovieInfoDto() {
		// TODO Auto-generated constructor stub
	}

	public MovieInfoDto(int movieCode, String title, String avgPoint, String movieSection) {
		super();
		this.movieCode = movieCode;
		this.title = title;
		this.avgPoint = avgPoint;
		this.movieSection = movieSection;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAvgPoint() {
		return avgPoint;
	}

	public void setAvgPoint(String avgPoint) {
		this.avgPoint = avgPoint;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public Date getUptDt() {
		return uptDt;
	}

	public void setUptDt(Date uptDt) {
		this.uptDt = uptDt;
	}

	public String getMovieSection() {
		return movieSection;
	}

	public void setMovieSection(String movieSection) {
		this.movieSection = movieSection;
	}

	@Override
	public String toString() {
		return "MovieInfoDto [id=" + id + ", movieCode=" + movieCode + ", title=" + title + ", avgPoint=" + avgPoint
				+ ", regDt=" + regDt + ", uptDt=" + uptDt + ", movieSection=" + movieSection + "]";
	}

	
	

	

}
