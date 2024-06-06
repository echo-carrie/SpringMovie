package example.springmovie.controller;

import com.github.pagehelper.PageInfo;
import example.springmovie.dto.ReviewRequest;
import example.springmovie.entity.Movie;
import example.springmovie.entity.Review;
import example.springmovie.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//评论
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        // getter 和 setter 方法
    }
    // POST http://localhost:8080/reviews/post-review/2
//    {
//        "userId":"2",
//            "movieId":"1",
//            "rating":5,
//            "comment":"不好看"
//    }
    @PostMapping("/post-review/{movie_id}")
    public ResponseEntity<?> createReview(@PathVariable Long movie_id, @RequestBody ReviewRequest reviewRequest) {
        try {
            reviewRequest.setMovieId(movie_id);
            reviewService.saveReview(movie_id,reviewRequest);
            // 假设saveReview方法已经处理了所有必要的逻辑，并且评论已经成功保存
            return ResponseEntity.status(HttpStatus.CREATED).body("Review successfully posted for movie: " + movie_id);
        } catch (Exception e) {
            // 处理异常，返回错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error posting review: " + e.getMessage());
        }
    }
//GET http://localhost:8080/reviews/show-reviews/4?pageNum=3&pageSize=2
@GetMapping("/show-reviews/{movie_id}")
public ResponseEntity<?> getReviewsByMovieId(
        @PathVariable Long movie_id,
        @RequestParam(defaultValue = "1") int pageNum,
        @RequestParam(defaultValue = "10") int pageSize) {
    try {
        PageInfo<Review> page = reviewService.getReviewsByMovieId(movie_id, pageNum, pageSize);
        //当页面为空、超出页码、页码为空时返回页码不符合规范的报错，可以根据需要更改
        if (page == null || page.getPages() == 0 || pageNum > page.getPages()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Page number is out of range or not acceptable");
        } else {
            return ResponseEntity.ok(page);
        }

    } catch (Exception e) {
        // 可以根据异常类型做更细致的处理
        // 例如，如果是数据库找不到数据的异常，可以返回404
        // 如果是其他运行时异常，可以返回500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error retrieving reviews for movie_id: " + movie_id + ", Error: " + e.getMessage());
    }
}

}
