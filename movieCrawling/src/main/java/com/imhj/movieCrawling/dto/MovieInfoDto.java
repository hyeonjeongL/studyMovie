package com.imhj.movieCrawling.dto;

import java.util.Date;

public class MovieInfoDto {
	
	int id;
	int movieCode;
	String title;
	String avgPoint;
	Date regDt;
	Date uptDt;
	
	public MovieInfoDto() {
		// TODO Auto-generated constructor stub
	}

	public MovieInfoDto(int id, int movieCode, String title, String avgPoint, Date regDt, Date uptDt) {
		super();
		this.id = id;
		this.movieCode = movieCode;
		this.title = title;
		this.avgPoint = avgPoint;
		this.regDt = regDt;
		this.uptDt = uptDt;
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

	@Override
	public String toString() {
		return "movieInfoDto [id=" + id + ", movieCode=" + movieCode + ", title=" + title + ", avgPoint=" + avgPoint
				+ ", regDt=" + regDt + ", uptDt=" + uptDt + "]";
	}
	

	

}
