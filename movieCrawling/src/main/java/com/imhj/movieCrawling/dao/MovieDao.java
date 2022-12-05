package com.imhj.movieCrawling.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.imhj.movieCrawling.dto.MovieDto;
import com.imhj.movieCrawling.dto.MovieEvalutDto;
import com.imhj.movieCrawling.dto.MovieInfoDto;

@Mapper
@Repository
public interface MovieDao {
	
	int insertMovie(MovieDto movieDto);
	
	public MovieDto selectTest();
	
	int insertMovieEvalut (MovieEvalutDto movieEvalutDto );
	
	int insertMovieInfo (MovieInfoDto movieInfoDto);
	

}
