package example.springmovie.mapper;

import example.springmovie.entity.Movie;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MovieCreatorMapper {
    @Select("SELECT DISTINCT m.* FROM movies m " +
            "JOIN movie_creators mc ON m.id = mc.movie_id " +
            "WHERE mc.name = #{name} ORDER BY m.weekly_plays DESC")
    List<Movie> selectMoviesByName(@Param("name") String name);

}
