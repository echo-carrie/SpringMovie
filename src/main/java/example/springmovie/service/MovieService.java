package example.springmovie.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import example.springmovie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import example.springmovie.mapper.MovieMapper;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieMapper movieMapper;

    // 按热播排行展示
    public PageInfo<Movie> getMoviesByPopularity(int pageNum, int pageSize) {
        // 使用PageHelper进行分页和排序
        // PageHelper.startPage(pageNum, pageSize);一定要紧贴下面这个查询语句
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieMapper.selectMoviesByPopularity();
        // 返回查询到的分页信息，如果有需求可以使用pagebean自定义
        return new PageInfo<>(movies);
    }

    // 按类型展示
    public PageInfo<Movie> getMoviesByGenre(String genre, int pageNum, int pageSize) {
        // 使用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieMapper.selectByGenre(genre);
        return new PageInfo<>(movies);
    }

    // 按地区展示
    public PageInfo<Movie> getMoviesByRegion(String region, int pageNum, int pageSize) {
        // 使用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieMapper.selectByRegion(region);
        return new PageInfo<>(movies);
    }
}
