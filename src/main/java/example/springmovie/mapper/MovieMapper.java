package example.springmovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.springmovie.entity.Movie;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MovieMapper extends BaseMapper<Movie> {

    // 按热播排行排序
    @Select("SELECT * FROM movies ORDER BY weekly_plays DESC")
    List<Movie> selectMoviesByPopularity();

    @Select("SELECT * FROM movies WHERE genre = #{genre}")
    List<Movie> selectByGenre(String genre);

    @Select("SELECT * FROM movies WHERE region = #{region}")
    List<Movie> selectByRegion(String region);
}
