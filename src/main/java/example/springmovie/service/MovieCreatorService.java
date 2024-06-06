package example.springmovie.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import example.springmovie.entity.Movie;
import example.springmovie.mapper.MovieCreatorMapper;
import example.springmovie.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCreatorService {
    @Autowired
    private MovieCreatorMapper movieCreatorMapper;

    // 分页核心代码
    public PageInfo<Movie> searchMoviesByCreator(String name, int pageNum, int pageSize) {
        // 使用 PageHelper 进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieCreatorMapper.selectMoviesByName(name);
        return new PageInfo<>(movies);
    }

}
