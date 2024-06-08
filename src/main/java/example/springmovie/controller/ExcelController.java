package example.springmovie.controller;

import com.github.pagehelper.PageInfo;
import example.springmovie.entity.Movie;
import example.springmovie.service.ExcelService;
import example.springmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class ExcelController {

    private final ExcelService excelService;
    private final MovieService movieService;

    @Autowired
    public ExcelController(ExcelService excelService, MovieService movieService) {
        this.excelService = excelService;
        this.movieService = movieService;
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportMovies() {
        String filePath = "movies.xlsx"; // 指定文件保存路径
        try {
            // 假设我们要获取第一页，每页10条数据
            PageInfo<Movie> pageInfo = movieService.getRankingMovies("total", 1, 10);
            List<Movie> movies = pageInfo.getList(); // 获取电影列表

            // 调用ExcelService的export方法
            excelService.exportMoviesToExcel(movies, filePath);

            return ResponseEntity.ok("电影数据导出成功，文件路径：" + filePath);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("导出失败：" + e.getMessage());
        }
    }
}