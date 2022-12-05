package com.imhj.movieCrawling.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.imhj.movieCrawling.dto.movieDto;

@Mapper
@Repository
public interface movieDao {
	
	public void insertMovieData(movieDto dto);

}
