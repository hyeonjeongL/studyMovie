package com.imhj.movieCrawling.dto;

import java.util.Date;

public class MovieDto {
	
	int id;
	String url;
	int movieCode;
	Date regDt;
	Date uptDt;
	String movieSection;
	
	

	public MovieDto() {
		// TODO Auto-generated constructor stub
	}
	


	public MovieDto(String url, int movieCode, String movieSection) {
		this.url = url;
		this.movieCode = movieCode;
		this.movieSection = movieSection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
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
		return "MovieDto [id=" + id + ", url=" + url + ", movieCode=" + movieCode + ", regDt=" + regDt + ", uptDt="
				+ uptDt + ", movieSection=" + movieSection + "]";
	}



	
	
	
	
	


}
