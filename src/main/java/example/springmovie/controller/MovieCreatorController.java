package example.springmovie.controller;

import com.github.pagehelper.PageInfo;
import example.springmovie.entity.Movie;
import example.springmovie.service.MovieCreatorService;
import example.springmovie.service.MovieService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

//     http://localhost:8080/movies/searchByName?name=陈凯歌
@Controller
@RequestMapping("/movies")
public class MovieCreatorController {
    @Autowired
    private MovieCreatorService movieCreatorService;

//     分页搜索电影主创
//    @GetMapping("/searchByName")
//    public ResponseEntity<PageInfo<Movie>> searchMoviesByName(
//            @RequestParam String name,
//            @RequestParam(defaultValue = "1") int pageNum,
//            @RequestParam(defaultValue = "10") int pageSize) {
//        PageInfo<Movie> page = movieCreatorService.searchMoviesByCreator(name, pageNum, pageSize);
//        return ResponseEntity.ok(page);
//    }

    @GetMapping("/searchByName")
    public  String searchMoviesByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            Model model) {
        System.out.println(movieCreatorService.searchMoviesByCreator(name, pageNum, pageSize).getList());
//        PageInfo<Movie> page = movieCreatorService.searchMoviesByCreator(name, pageNum, pageSize);
        model.addAttribute("reslist", movieCreatorService.searchMoviesByCreator(name, pageNum, pageSize).getList());
        return "movies";
    }

}
