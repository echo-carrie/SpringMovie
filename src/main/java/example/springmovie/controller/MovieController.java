package example.springmovie.controller;

import com.github.pagehelper.PageInfo;
import example.springmovie.entity.Movie;
import example.springmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

//    在postman中输入下列网址进行访问，其中如果有10页，访问了第十一页，那么返回的依然是第十页的数据，在编代码时要注意这一点。
    @GetMapping("/popular")
//    测试：http://localhost:8080/movies/popular?page=1&size=10
    public PageInfo<Movie> getMoviesByPopularity(@RequestParam int pageNum, @RequestParam int pageSize) {
        return movieService.getMoviesByPopularity(pageNum, pageSize);
    }

    @GetMapping("/genre")
//    测试：http://localhost:8080/movies/genre?genre="犯罪"&page=1&size=10
    public PageInfo<Movie> getMoviesByGenre(@RequestParam String genre, @RequestParam int pageNum, @RequestParam int pageSize) {
        return movieService.getMoviesByGenre(genre, pageNum, pageSize);
    }

//    测试：http://localhost:8080/movies/region?region=美国&pageNum=1&pageSize=2
    @GetMapping("/region")
    public PageInfo<Movie> getMoviesByRegion(@RequestParam String region, @RequestParam int pageNum, @RequestParam int pageSize) {
        return movieService.getMoviesByRegion(region, pageNum, pageSize);
    }
}
