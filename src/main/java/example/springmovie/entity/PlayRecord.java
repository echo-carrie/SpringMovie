package example.springmovie.entity;


import java.util.Date;

public class PlayRecord {
    private int id;
    private Long user_id;
    private Long movie_id;
    private Date created_at;
    private int play_time;

    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public void setCreated_at(java.sql.Timestamp timestamp) {
        this.created_at= timestamp;
    }

    public void setVideoId(Long videoId) {
        this.movie_id=videoId;
    }
    public void setPlayTime() {
        this.play_time+=1;
    }
}
