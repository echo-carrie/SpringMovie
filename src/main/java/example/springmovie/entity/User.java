package example.springmovie.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
//使用data进行依赖注入
//setter、getter方法会对get、set进行注入
public class User {
    //    标识主键
    @TableId
    private Long id;

    private String username;

    private String password;

    private String email;

    private Boolean isVip;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
