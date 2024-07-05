package example.springmovie.controller;

import example.springmovie.entity.Movie;
import example.springmovie.service.EChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class EChartsController {

    @Autowired
    private EChartsService eChartsService;

    @GetMapping("/all")
    public List<Movie> getAllMovies() {
        return eChartsService.getAllMovies();
    }

    @GetMapping("/popular")
    public List<Movie> getMoviesByPopularity() {
        return eChartsService.getMoviesByPopularity();
    }

    // Add other endpoints as needed
}
