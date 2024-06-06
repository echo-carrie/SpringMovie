package example.springmovie.service;

import example.springmovie.entity.Movie;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import example.springmovie.util.pageHelperUtil.PageBean;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private MovieService movieService; // 假设MovieService已经存在并注入

    public String generateExcelReportForHotMovies(String filePath) throws IOException {
        int pageSize = 10; // 假设每页显示10条数据
        int pageNum = 1; // 从第一页开始
        PageBean<Movie> pageBean = movieService.getHotMovies(pageNum, pageSize);
        List<Movie> movies = pageBean.getList(); // 获取电影列表

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("热播电影榜单");

        // 创建标题行
        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("排名");
        titleRow.createCell(1).setCellValue("电影名称");
        titleRow.createCell(2).setCellValue("总播放次数");

        // 填充数据
        int rowNum = 1;
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(String.valueOf(i + 1)); // 排名
            row.createCell(1).setCellValue(movie.getTitle()); // 电影名称
            row.createCell(2).setCellValue(String.valueOf(movie.getTotalPlays())); // 总播放次数
        }

        // 写入文件
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }
        workbook.close();

        return "Excel报表生成成功，文件保存在：" + filePath;
    }
}