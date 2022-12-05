package com.imhj.movieCrawling.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.imhj.movieCrawling.dto.MovieDto;
import com.imhj.movieCrawling.dto.MovieEvalutDto;
import com.imhj.movieCrawling.dto.MovieInfoDto;

@Mapper
@Repository
public interface MovieDao {
	
	public void insertMovie(MovieDto dto);
	public MovieDto selectTest();
	
	public void insertMovieEvalut (MovieEvalutDto movieEvalutDto );
	
	public void insertMovieInfo (MovieInfoDto movieInfoDto);
	

}
