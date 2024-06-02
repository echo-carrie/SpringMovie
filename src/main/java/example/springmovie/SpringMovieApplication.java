package example.springmovie;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SpringMovieApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringMovieApplication.class, args);
	}
}
