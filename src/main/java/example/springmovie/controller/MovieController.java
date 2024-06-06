package example.springmovie.controller;

import example.springmovie.entity.Movie;
import example.springmovie.service.ExcelService;
import example.springmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import example.springmovie.util.pageHelperUtil.PageBean;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ExcelService excelService;

    @Autowired
    public MovieController(MovieService movieService, ExcelService excelService) {
        this.movieService = movieService;
        this.excelService = excelService;
    }

    // 按热播排行展示电影，支持分页
    @GetMapping("/hot")
    public PageBean<Movie> getHotMovies(@RequestParam(defaultValue = "1") int pageNum,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        return movieService.getHotMovies(pageNum, pageSize);
    }

    // 按类型展示电影，支持分页
    @GetMapping("/genre")
    public PageBean<Movie> getMoviesByGenre(@RequestParam (defaultValue = "犯罪")String genre,
                                            @RequestParam(defaultValue = "1") int pageNum,
                                            @RequestParam(defaultValue = "10") int pageSize) {
        return movieService.getMoviesByGenre(genre, pageNum, pageSize);
    }

    // 按地区展示电影，支持分页
    @GetMapping("/region")
    public PageBean<Movie> getMoviesByRegion(@RequestParam (defaultValue = "美国")String region,
                                             @RequestParam(defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        return movieService.getMoviesByRegion(region, pageNum, pageSize);
    }

    @GetMapping("/excel/hot")
    public String generateHotMoviesExcelReport() {
        String filePath = "hot_movies.xlsx"; // 指定文件保存路径
        try {
            return excelService.generateExcelReportForHotMovies(filePath);
        } catch (IOException e) {
            return "Excel报表生成失败：" + e.getMessage();
        }
    }
}