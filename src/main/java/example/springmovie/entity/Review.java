package example.springmovie.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import example.springmovie.dto.ReviewRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
public class Review {
    @TableId
    private Long id;
    private Long userId;
    private Long movieId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Review(){

    }

    public Review(ReviewRequest reviewRequest){
        userId=reviewRequest.getUserId();
        rating=reviewRequest.getRating();
        comment=reviewRequest.getComment();
    }
    // Getters and Setters
}
