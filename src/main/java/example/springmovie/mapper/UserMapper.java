package example.springmovie.mapper;

import example.springmovie.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "type", column = "type")
    })
    User findByUsername(String username);

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "type", column = "type")
    })
    User selectUserById(Integer id); // 参数类型为Integer

    @Insert("INSERT INTO user(username, password, type) VALUES(#{username}, #{password}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);
}