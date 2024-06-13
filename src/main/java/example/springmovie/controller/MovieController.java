package example.springmovie.controller;

import com.github.pagehelper.PageInfo;
import example.springmovie.entity.Movie;
import example.springmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/index")
    public String movies(){
        // 注意这里返回的是"forward:/movies.html"，这将转发请求到静态资源
        return "movies";
    }

//    在postman中输入下列网址进行访问，其中如果有10页，访问了第十一页，那么返回的依然是第十页的数据，在编代码时要注意这一点。
    @GetMapping("/popular")
//    按照播放量分类展示，按照周播放量来排序决定，即example.springmovie.entity.Movie中的weekly_plays
//    测试：http://localhost:8080/movies/popular?pageNum=1&pageSize=10
    public String getMoviesByPopularity(@RequestParam int pageNum, @RequestParam int pageSize, Model model) {
        System.out.println(movieService.getMoviesByPopularity(pageNum,pageSize).getList().get(0));
        model.addAttribute("movies", movieService.getMoviesByPopularity(pageNum, pageSize).getList());
        return "popular";
    }

    @GetMapping("/genre")
//    按照类型分页展示，根据类型，即genre进行搜索，分页展示类型相同的
//    测试：http://localhost:8080/movies/genre?genre=科幻&pageNum=1&pageSize=10
    public String getMoviesByGenre(@RequestParam String genre, @RequestParam int pageNum, @RequestParam int pageSize, Model model) {
        System.out.println(movieService.getMoviesByGenre(genre, pageNum, pageSize).getList());
        model.addAttribute("reslist", movieService.getMoviesByGenre(genre, pageNum, pageSize).getList());
        return "movies";
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
