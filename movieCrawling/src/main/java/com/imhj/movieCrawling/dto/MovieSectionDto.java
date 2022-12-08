package com.imhj.movieCrawling.dto;

import java.util.Date;

public class MovieSectionDto {
	
	int id;
	int movieInfoId;
	String sections;
	Date regDt;
	Date uptDt;
	
	public MovieSectionDto() {
		// TODO Auto-generated constructor stub
	}

	public MovieSectionDto(int movieInfoId, String sections) {
		super();
		this.movieInfoId = movieInfoId;
		this.sections = sections;

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

	public String getSections() {
		return sections;
	}

	public void setSections(String sections) {
		this.sections = sections;
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
		return "MovieSectionDto [id=" + id + ", movieInfoid=" + movieInfoId + ", sections=" + sections + ", regDt="
				+ regDt + ", uptDt=" + uptDt + "]";
	}
	
	

}