package example.springmovie.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import example.springmovie.entity.PlayRecord;
import example.springmovie.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface RecordMapper extends BaseMapper<PlayRecord> {
    @Insert("INSERT INTO moviedb1.play_records(id, user_id, movie_id, play_time, created_at) VALUES(#{id}, #{user_id}, #{movie_id}, #{play_time}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertRecord(PlayRecord playRecord);

}
