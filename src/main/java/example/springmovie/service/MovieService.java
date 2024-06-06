package example.springmovie.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import example.springmovie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import example.springmovie.mapper.MovieMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private DataSource dataSource;

    // 按热播排行展示
    public PageInfo<Movie> getMoviesByPopularity(int pageNum, int pageSize) {
        // 使用PageHelper进行分页和排序
        // PageHelper.startPage(pageNum, pageSize);一定要紧贴下面这个查询语句
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieMapper.selectMoviesByPopularity();
        // 返回查询到的分页信息，如果有需求可以使用pagebean自定义
        return new PageInfo<>(movies);
    }

    // 按类型展示
    public PageInfo<Movie> getMoviesByGenre(String genre, int pageNum, int pageSize) {
        // 使用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieMapper.selectByGenre(genre);
        return new PageInfo<>(movies);
    }

    // 按地区展示
    public PageInfo<Movie> getMoviesByRegion(String region, int pageNum, int pageSize) {
        // 使用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieMapper.selectByRegion(region);
        return new PageInfo<>(movies);
    }
   //获取选择电影的信息
    public Movie getVideoById(Long videoId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Movie movie = null;

        try {
            // 获取数据库连接
            conn = dataSource.getConnection();

            // 准备查询语句
            String query = "SELECT * FROM moviedb1.movies WHERE id = ?";
            pstmt = conn.prepareStatement(query);

            // 设置查询参数
            pstmt.setLong(1, videoId);

            // 执行查询
            rs = pstmt.executeQuery();

            // 处理结果集
            if (rs.next()) {
                // 创建Movie对象并设置电影信息
                movie = new Movie();
                movie.setId(rs.getLong("id"));
                movie.setTitle(rs.getString("title"));
                movie.setVip(rs.getBoolean("is_vip"));
                // 其他字段以此类推
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        } finally {
            // 关闭资源
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return movie;
    }
}
