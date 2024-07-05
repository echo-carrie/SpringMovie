package example.springmovie.service;

import example.springmovie.entity.Movie;
import example.springmovie.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EChartsService {

    @Autowired
    private MovieMapper movieMapper;

    public List<Movie> getAllMovies() {
        return movieMapper.selectAllMovies();
    }

    public List<Movie> getMoviesByPopularity() {
        return movieMapper.selectMoviesByPopularity();
    }

    // Add other methods as needed
}
