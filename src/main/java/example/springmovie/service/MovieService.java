package example.springmovie.service;

import com.github.pagehelper.PageHelper;
import example.springmovie.entity.Movie;
import example.springmovie.mapper.MovieMapper;
import example.springmovie.util.pageHelperUtil.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MovieService {
    PageBean<Movie> getHotMovies(int pageNum, int pageSize);
    PageBean<Movie> getMoviesByGenre(String genre, int pageNum, int pageSize);
    PageBean<Movie> getMoviesByRegion(String region, int pageNum, int pageSize);
}

