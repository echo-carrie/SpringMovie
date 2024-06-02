package example.springmovie.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VideoMapper {

    @Select("SELECT type FROM videos WHERE id = #{id}")

    String findVideoTypeById(Long id);
}