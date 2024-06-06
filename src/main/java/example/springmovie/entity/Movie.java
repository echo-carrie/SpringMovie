package example.springmovie.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

import lombok.Data;
@Data
@Getter
@Setter
//使用data进行依赖注入
//setter、getter方法会对get、set进行注入
public class Movie {
//    标识主键
    @TableId
    private Long id;

    private String title;

    private String genre;

    private String region;

    private boolean isVip;

    private int weeklyPlays;

    private int monthlyPlays;

    private int totalPlays;

    private int goodReviews;

    private String coverUrl;

    private LocalDate releaseDate;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    // 构造方法、Getter和Setter方法



    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}