package example.springmovie.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
public class User {

    // Getters and setters
    @TableId
    private Long id;

    private String username;

    private String password;

    private String email;

    private Boolean isVip;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
