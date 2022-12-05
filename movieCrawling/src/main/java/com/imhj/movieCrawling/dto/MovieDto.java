package com.imhj.movieCrawling.dto;

import java.util.Date;

public class MovieDto {
	
	int id;
	String url;
	int movieCode;
	Date regDt;
	Date uptDt;
	
	

	public MovieDto() {
		// TODO Auto-generated constructor stub
	}
	


	public MovieDto(String url, int movieCode) {
		this.url = url;
		this.movieCode = movieCode;
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

	@Override
	public String toString() {
		return "movieDto [id=" + id + ", url=" + url + ", movie_code=" + movieCode + ", reg_dt=" + regDt + ", upt_dt="
				+ uptDt + "]";
	}
	
	
	
	


}
