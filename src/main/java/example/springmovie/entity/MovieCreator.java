package example.springmovie.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
@Data
@Getter
@Setter
public class MovieCreator {
    @TableId
    private Long id;

    private Long movieId; // 电影ID，与movies表的主键关联

    private String name; // 主创的名字

    private String role; // 主创的角色（演员或导演）
}
