package example.springmovie.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import example.springmovie.dto.ReviewRequest;
import example.springmovie.entity.Movie;
import example.springmovie.entity.Review;
import example.springmovie.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Transactional
    public void saveReview(Long movie_id, ReviewRequest reviewRequest) {
        Review review=new Review(reviewRequest);
        review.setMovieId(movie_id);
        review.setCreatedAt(LocalDateTime.now());
        review.setCreatedAt(LocalDateTime.now());
        reviewMapper.insertReview(review);
    }

//    public List<Review> getReviewsByMovieId(Long movie_id) {
//        return reviewMapper.getReviewsByMovieId(movie_id);
//    }

    public PageInfo<Review> getReviewsByMovieId(Long movie_id, int pageNum, int pageSize) {

        // 使用 PageHelper 进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Review> reviews = reviewMapper.getReviewsByMovieId(movie_id);
        return new PageInfo<>(reviews);
    }
}
