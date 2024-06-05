//package example.springmovie.util.pageHelperUtil;
//
//import com.github.pagehelper.PageInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.util.Properties;
//
//@Configuration
//public class PageHelperConfig {
//    @Bean
//    public PageInterceptor pageHelper() {
//        PageInterceptor pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("offsetAsPageNum","true");
//        properties.setProperty("rowBoundsWithCount","true");
//        properties.setProperty("reasonable","true");
//        properties.setProperty("helperDialect", "mysql");
//        properties.setProperty("supportMethodsArguments","true");
//        properties.setProperty("params","count=countSql");
//        pageInterceptor.setProperties(properties);
//        return pageInterceptor;
//    }
//}