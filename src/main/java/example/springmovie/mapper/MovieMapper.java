package example.springmovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.springmovie.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieMapper extends BaseMapper<Movie> {
    // 电影展示

    // 按热播排行排序
    @Select("SELECT * FROM movies ORDER BY weekly_plays DESC")
    List<Movie> selectMoviesByPopularity();

    @Select("SELECT * FROM movies WHERE genre = #{genre}")
    List<Movie> selectByGenre(String genre);

    @Select("SELECT * FROM movies WHERE region = #{region}")
    List<Movie> selectByRegion(String region);

    // 电影排行

    // 按本周播放次数排行
    @Select("SELECT * FROM movies WHERE weekly_plays > 0 ORDER BY weekly_plays DESC")
    List<Movie> selectByWeeklyRanking();

    // 按本月播放次数排行
    @Select("SELECT * FROM movies WHERE monthly_plays > 0 ORDER BY monthly_plays DESC")
    List<Movie> selectByMonthlyRanking();

    // 按总播放次数排行
    @Select("SELECT * FROM movies ORDER BY total_plays DESC")
    List<Movie> selectByTotalRanking();

    // 按好评次数排行
    @Select("SELECT * FROM movies ORDER BY good_reviews DESC")
    List<Movie> selectByGoodReviewsRanking();

    @Select("SELECT * FROM movies WHERE id =#{movieId}")
    Movie getVideoById(Long movieId);

}
