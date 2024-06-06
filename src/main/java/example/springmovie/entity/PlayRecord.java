package example.springmovie.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PlayRecord {
    @TableId
    private int id;
    private Long user_id;
    private Long movie_id;
    private Date created_at;
    private int play_time;

    public void setPlayTime() {
        this.play_time+=1;
    }
}
