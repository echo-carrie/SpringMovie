package example.springmovie.controller;

import com.github.pagehelper.PageInfo;
import example.springmovie.entity.Movie;
import example.springmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
//    在postman中输入下列网址进行访问，其中如果有10页，访问了第十一页，那么返回的依然是第十页的数据，在编代码时要注意这一点。
    @GetMapping("/popular")
//    按照播放量分类展示，按照周播放量来排序决定，即example.springmovie.entity.Movie中的weekly_plays
//    测试：http://localhost:8080/movies/popular?page=1&size=10
    public PageInfo<Movie> getMoviesByPopularity(@RequestParam int pageNum, @RequestParam int pageSize) {
        return movieService.getMoviesByPopularity(pageNum, pageSize);
    }

    @GetMapping("/genre")
//    按照类型分页展示，根据类型，即genre进行搜索，分页展示类型相同的
//    测试：http://localhost:8080/movies/genre?genre="犯罪"&page=1&size=10
    public PageInfo<Movie> getMoviesByGenre(@RequestParam String genre, @RequestParam int pageNum, @RequestParam int pageSize) {
        return movieService.getMoviesByGenre(genre, pageNum, pageSize);
    }

//    按照地区分类展示，根据类型搜索，即region进行搜索，分页展示地区相同的
//    测试：http://localhost:8080/movies/region?region=美国&pageNum=1&pageSize=2
    @GetMapping("/region")
    public PageInfo<Movie> getMoviesByRegion(@RequestParam String region, @RequestParam int pageNum, @RequestParam int pageSize) {
        return movieService.getMoviesByRegion(region, pageNum, pageSize);
    }

//    进行本周排行，本月排行，全部排行，按好评排行等
//    测试：
//  /weekly 每周播放量
//  /monthly 每月播放量
//  /total 总播放量
//  /good_reviews 好评量
// 使用路径变量 {rankingType} 来获取排行类型，并分页展示电影排行
    @GetMapping("/ranking/{rankingType}")
    public ResponseEntity<PageInfo<Movie>> getRankingMovies(
            @PathVariable String rankingType,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Movie> page = movieService.getRankingMovies(rankingType, pageNum, pageSize);
        return ResponseEntity.ok(page);
    }
}
