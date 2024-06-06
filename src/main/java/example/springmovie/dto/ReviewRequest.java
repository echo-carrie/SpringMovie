package example.springmovie.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ReviewRequest {

    private Long userId;
    private Long movieId;
    private int rating;
    private String comment;

}
