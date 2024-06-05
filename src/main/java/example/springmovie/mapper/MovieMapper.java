package example.springmovie.mapper;

import example.springmovie.entity.Movie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 继承 BaseMapper 来获得 MyBatis Plus 的 CRUD 支持
public interface MovieMapper extends BaseMapper<Movie> {

    // 使用 MyBatis Plus 的 @Select 注解来定义 SQL 查询
    @Select("SELECT * FROM movies ORDER BY total_plays DESC LIMIT #{pageNum}, #{pageSize}")
    List<Movie> selectHotMovies(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    @Select("SELECT * FROM movies WHERE genre = #{genre} ORDER BY total_plays DESC LIMIT #{pageNum}, #{pageSize}")
    List<Movie> selectByGenre(@Param("genre") String genre, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    @Select("SELECT * FROM movies WHERE region = #{region} ORDER BY total_plays DESC LIMIT #{pageNum}, #{pageSize}")
    List<Movie> selectByRegion(@Param("region") String region, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);


}