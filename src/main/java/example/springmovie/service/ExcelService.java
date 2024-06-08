package example.springmovie.service;

import example.springmovie.entity.Movie;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public void exportMoviesToExcel(List<Movie> movies, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("电影榜单");

        // 创建标题行
        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("ID");
        titleRow.createCell(1).setCellValue("标题");
        titleRow.createCell(2).setCellValue("类型");
        titleRow.createCell(3).setCellValue("总播放量");
        // ... 添加其他列标题

        // 填充电影数据
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(movie.getId().toString());
            row.createCell(1).setCellValue(movie.getTitle());
            row.createCell(2).setCellValue(movie.getGenre());
            row.createCell(3).setCellValue(String.valueOf(movie.getTotalPlays()));
            // ... 添加其他数据
        }

        // 设置样式等操作...

        // 保存Excel文件
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }

        // 关闭工作簿
        workbook.close();
    }
}