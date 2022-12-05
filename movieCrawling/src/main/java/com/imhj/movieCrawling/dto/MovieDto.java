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
	

	public MovieDto(int id, String url, int movie_code, Date reg_dt, Date upt_dt) {
		super();
		this.id = id;
		this.url = url;
		this.movieCode = movie_code;
		this.regDt = reg_dt;
		this.uptDt = upt_dt;
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
