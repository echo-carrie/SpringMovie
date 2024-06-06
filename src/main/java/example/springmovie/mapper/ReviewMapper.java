package example.springmovie.mapper;
import example.springmovie.entity.Review;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper {

    //插入评论，时间是插入时的数据
    @Insert("INSERT INTO reviews (user_id, movie_id, rating, comment, created_at, updated_at) " +
            "VALUES (#{userId}, #{movieId}, #{rating}, #{comment}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertReview(Review review);

    //获取所以movie对应的评论
    @Select("SELECT * FROM reviews WHERE movie_id = #{movie_id}")
    List<Review> getReviewsByMovieId(Long movie_id);
}
