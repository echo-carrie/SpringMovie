package example.springmovie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("example.springmovie.mapper")
public class SpringMovieApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringMovieApplication.class, args);
	}
}
