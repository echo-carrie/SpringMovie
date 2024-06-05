package example.springmovie.service;

import com.github.pagehelper.PageHelper;
import example.springmovie.entity.Movie;
import example.springmovie.mapper.MovieMapper;
import example.springmovie.util.pageHelperUtil.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
//@Transactional
public class MovieServiceImpl implements MovieService {
    private final MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    @Override
    public PageBean<Movie> getHotMovies(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieMapper.selectHotMovies(pageNum, pageSize);
        long total = movies.size(); // 假设每页显示pageSize条数据，则总页数为total/pageSize
        return new PageBean<>(movies, total);
    }

    @Override
    public PageBean<Movie> getMoviesByGenre(String genre, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieMapper.selectByGenre(genre, pageNum, pageSize);
        long total = movies.size(); // 同上
        return new PageBean<>(movies, total);
    }

    @Override
    public PageBean<Movie> getMoviesByRegion(String region, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieMapper.selectByRegion(region, pageNum, pageSize);
        long total = movies.size(); // 同上
        return new PageBean<>(movies, total);
    }
}
