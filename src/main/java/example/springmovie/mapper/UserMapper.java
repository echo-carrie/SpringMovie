package example.springmovie.mapper;

import example.springmovie.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "isVip", column = "is_vip"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    User findByUsername(String username);

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "isVip", column = "is_vip"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    User selectUserById(Long id);

    @Insert("INSERT INTO users(username, password, email, is_vip, created_at) VALUES(#{username}, #{password}, #{email}, #{isVip}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);


}
